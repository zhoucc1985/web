package com.cloud.service.datacollection;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.enums.CollectionBatchStatusEnum;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.*;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.datacollection.ImportDataSyncMsgVO;
import com.cloud.entity.datacollection.CollectionBatch;
import com.cloud.entity.datacollection.CollectionDataSource;
import com.cloud.entity.datacollection.CollectionDataStandard;
import com.cloud.entity.report.Report;
import com.cloud.mapper.master.datacollection.CollectionBatchMapper;
import com.cloud.mapper.master.datacollection.CollectionDataSourceMapper;
import com.cloud.mapper.master.datacollection.CollectionDataStandardMapper;
import com.cloud.service.report.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 数据采集异步任务处理类
 *
 * @author Pan jianneng
 * @time 2018/10/21 17:40
 */
@Component
public class AsyncExecutorCommonService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncExecutorCommonService.class);

    @Autowired
    private WebSocketSentMsgManager sentMassage;

    @Autowired
    private CollectionDataCommonService commonService;

    @Resource
    private CollectionDataSourceMapper collectionDataSourceMapper;

    @Resource
    private CollectionDataStandardMapper collectionDataStandardMapper;

    @Resource
    private CollectionBatchMapper collectionBatchMapper;

    private final ReportService reportService;

    @Autowired
    public AsyncExecutorCommonService(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 获取文件地址
     */
    @Value("${data-collection-upload-file-path}")
    private String filePath;

    /**
     * 异步处理导入的excel文件
     *
     * @param filePath
     * @param batchId
     * @return: void
     * @auther: Pan jianneng
     * @date: 2018/10/21 17:53
     */
    @Async("asyncExecutor")
    public void syncHandleData(String filePath, Long batchId,ShiroUser shiroUser) {
        logger.info("开始异步处理文件【"+filePath+"】");
        Long startTime = System.currentTimeMillis();
        Map<String, Object> resMap = CommonUtils.getSucResultMap();
        //根据批次ID查询批次内容
        CollectionBatch cb = collectionBatchMapper.selectByPrimaryKey(batchId);
        //如果导入时间为空，说明之前都没有成功导入过数据，如果失败状态将会是空的
        boolean cbInitStateIsNull = ObjectUtils.isNullObject(cb.getImportTime());
        try {
            List<CollectionDataSource> dataList = new ArrayList<CollectionDataSource>();
            //获取excel sheet数据
            logger.info("读取Excel数据中...");
            List<String[]> excelData = commonService.readExcelAndConvert2DataSource(filePath);
            logger.info("读取Excel数据完成");
            logger.info("Excel数据转换中...");
            /// TODO 得到批次ID，用于禁止用户随便上传其他学校的数据文档
            //isAllowImport(excelData,batchId);
            boolean isAllowImport = true;
            if(isAllowImport){
                dataList = ExcelUtil.coverEntity(excelData,CollectionDataSource.class);
                if (dataList.size() < 1) {
                    modifyCbStatus(cb, cbInitStateIsNull);
                    throw new BusinessException("文档数据为空",String.valueOf(batchId));
                } else {
                    logger.info("Excel数据转换完成");
                    //入库处理
                    saveDataToSourceDB(dataList, cb,shiroUser);
                }
            }else{
                modifyCbStatus(cb, cbInitStateIsNull);
                throw new BusinessException("导入文件不符合要求，确认按照下载模板导入",String.valueOf(batchId));
            }
        } catch (Exception e) {
            ShiroUser user = UserUtils.getInstance().getCurrentlyUserInfo();
            logger.error("错误信息即将要发送给用户"+user.getLoginName());
            modifyCbStatus(cb, cbInitStateIsNull);
            logger.error("异步处理【"+filePath+"】异常:", e);
            Map<String,Long> batchIdMap = new HashMap<String,Long>(0);
            batchIdMap.put("batchId",batchId);
            resMap.put("state", CommonDict.RETURN_STATE_FAIL);
            resMap.put("msg", "" + e.getMessage());
            resMap.put("datas",batchIdMap);
            //发生异常后通知前端
            sentMassage.sendMsgToUser(resMap,user);
        }
        Long end = System.currentTimeMillis();
        Long hms = (end-startTime)/1000%60;
        logger.info("结束异步处理文件【"+filePath+"】耗时："+hms+"秒");
    }

    private void modifyCbStatus(CollectionBatch cb, boolean cbInitStateIsNull) {
        if(cbInitStateIsNull){
            cb.setStatus(null);
        }else{
            cb.setStatus(CollectionBatchStatusEnum.CHECKED.toString());
        }
        collectionBatchMapper.updateByPrimaryKey(cb);
    }

    /**
     * 是否允许导入
     * @author Pan Jianneng
     * @date 2018/11/29 5:11 PM
     * @params [excelData, batchId]
     * @return boolean
     */
    private boolean isAllowImport(List<String[]> excelData,Long batchId){
        if(ObjectUtils.isNotEmptyList(excelData)){
            int clos = excelData.get(0).length;
            for (int j = 0; j < clos; j++) {
                String batchIdCell = excelData.get(0)[j];
                if(batchIdCell.equals(String.valueOf(batchId))){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 保存数据到原始库异常，回滚
     *
     * @param list    当前需要导入的数据集合
     * @param cb 导入批次
     * @return: void
     * @auther: Pan jianneng
     * @date: 2018/10/21 21:56
     */
    @Transactional(rollbackFor = Exception.class)
    void saveDataToSourceDB(List<CollectionDataSource> list, CollectionBatch cb,ShiroUser shiroUser) {
        //冲掉重复
        list = commonService.groupByOrgIdAndStudent(list);
        list.forEach(vo -> {
            vo.setOrgId(shiroUser.getSchoolOrgId()==null?shiroUser.getOrgId():shiroUser.getSchoolOrgId());
            vo.setOrgCode(shiroUser.getSchoolOrgCode()==null?shiroUser.getOrgCode():shiroUser.getSchoolOrgCode());
            vo.setBatchId(cb.getId());
        });
        logger.info("开始入原始库...");
        collectionDataSourceMapper.insertOrUpdateSourceByBatch(list);
        logger.info("入原始库完成");
        cb.setStatus(CollectionBatchStatusEnum.CHECKING.toString());
        cb.setImportTime(new Date());
        collectionBatchMapper.updateByPrimaryKeySelective(cb);
        //给前端发送本次导入的数据数量
        sentMassage.sendMsgToUser(sendSyncMsg(new ImportDataSyncMsgVO(cb.getId(),cb.getBatchNo(), list.size(),false)),shiroUser);
        logger.info("开始校验...");
        //校验数据操作
        List<CollectionDataSource> collectionDataCheckResult = commonService.checkExcelData(list);
        if(ObjectUtils.isEmptyList(collectionDataCheckResult)){
            throw new BusinessException("校验失败，请检查导入数据");
        }
        logger.info("校验完成");
        //完成校验，更新原始数据
        logger.info("更新数据库...");
        collectionDataSourceMapper.insertOrUpdateSourceByBatchNoReturnId(collectionDataCheckResult);
        logger.info("更新数据库完成");
        //根据校验数据获取到标准数据
        List<CollectionDataStandard> standards = commonService.getStandardBySourceCheckDatas(collectionDataCheckResult);
        logger.info("正确数据"+standards.size());
        if(ObjectUtils.isNotEmptyList(standards)){
            collectionDataStandardMapper.insertOrUpdateStandardByBatch(standards);
            deleteIsNoErrorSourceDataByBatchId(cb.getId());
        }
        //完成标准数据入库后修改状态为校验完成
        cb.setStatus(CollectionBatchStatusEnum.CHECKED.toString());
        collectionBatchMapper.updateByPrimaryKeySelective(cb);
        //发消息给前端
        sentMassage.sendMsgToUser(sendSyncMsg(new ImportDataSyncMsgVO(cb.getId(),cb.getBatchNo(), list.size(), standards.size(),
                list.size()-standards.size(),true)),shiroUser);
        Report oldReport = reportService.findByBatchId(cb.getId());
        //已经在报告里边了就不添加了
        if(ObjectUtils.isNullObject(oldReport)){
            //新增一条报告记录
            CollectionBatch collectionBatch = collectionBatchMapper.selectByPrimaryKey(cb.getId());
            Report report = Report.builder().batchId(cb.getId()).generateNumber(0).generateStatus(Report.REPORT_STATUS_NOMINATED).generateAgain((byte) 0)
                    .rtId(collectionBatch.getRtId()).orgId(collectionBatch.getOrgId()).build();
            reportService.save(report);
        }else{
            //修改状态
            reportService.updateGenerateAgainById(true,oldReport.getId());
        }
    }

    /**
     * 发送消息内容
     *
     * @param msgVO
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author Pan Jianneng
     * @date 2018/11/17 11:23 AM
     */
    private Map<String, Object> sendSyncMsg(ImportDataSyncMsgVO msgVO) {
        Map<String, Object> msgMap = CommonUtils.getSucResultMap();
        msgMap.put("datas", msgVO);
        return msgMap;
    }

    /**
     * 启动服务自动校验未能完成校验的原始数据
     * 检查是否有为校验的数据，如果有，那么进行异步数据校验
     *
     * @param
     * @return
     * @author Pan Jianneng
     * @date 2018/11/17 5:51 PM
     */
    @Async("asyncExecutor")
    public void automaticSyncHandleData() {
        List<CollectionDataSource> allSourceData = collectionDataSourceMapper.findAll();
        if (ObjectUtils.isNotEmptyList(allSourceData)) {
            Map<Long, List<CollectionDataSource>> syncDataMap = commonService.groupByOrgIdAndBatchId(allSourceData);
            for (Long batchId : syncDataMap.keySet()) {
                List<CollectionDataSource> syncData = syncDataMap.get(batchId);
                automaticSyncSaveDataToSourceDB(syncData, batchId);
            }
        }
    }

    /**
     * 启动服务对原数据校验及保存处理
     *
     * @param list
     * @param batchId
     * @return void
     * @author Pan Jianneng
     * @date 2018/11/17 7:55 PM
     */
    @Transactional(rollbackFor = RuntimeException.class)
    void automaticSyncSaveDataToSourceDB(List<CollectionDataSource> list, Long batchId) {
        logger.info("批次ID为【" + batchId + "】原始数据处理开始-"+list.size());
        CollectionBatch cb = collectionBatchMapper.selectByPrimaryKey(batchId);
        cb.setStatus(CollectionBatchStatusEnum.CHECKING.toString());
        //更新批次状态为校验中
        collectionBatchMapper.updateByPrimaryKeySelective(cb);
        //校验数据操作
        List<CollectionDataSource> collectionDataCheckResult = commonService.checkExcelData(list);
        logger.info("完成校验的数据-"+collectionDataCheckResult.size());
        if(ObjectUtils.isNotEmptyList(collectionDataCheckResult)){
            //完成校验，更新原始数据
            collectionDataSourceMapper.insertOrUpdateSourceByBatchNoReturnId(collectionDataCheckResult);
            //获取没有错误的原数据转为标准数据
            List<CollectionDataStandard> standards = commonService.getStandardBySourceCheckDatas(collectionDataCheckResult);
            //保存标准数据
            if (ObjectUtils.isNotEmptyList(standards)) {
                collectionDataStandardMapper.insertOrUpdateStandardByBatch(standards);
                deleteIsNoErrorSourceDataByBatchId(batchId);
                Report report = reportService.findByBatchId(batchId);
                if(ObjectUtils.isNotNullObject(report)){
                    reportService.updateGenerateAgainById(true,report.getId());
                }
            }
            //完成标准数据入库后修改状态为校验完成
            cb.setStatus(CollectionBatchStatusEnum.CHECKED.toString());
            collectionBatchMapper.updateByPrimaryKeySelective(cb);
        }
        logger.info("批次ID为【" + batchId + "】原始数据处理完成");
    }

    /**
     * 根据批次ID删除原数据没有错误的记录
     * 这个防止，残留已经保存到标准表中的没有错误的数据
     * @author Pan Jianneng
     * @date 2018/12/3 4:22 PM
     * @param batchId
     * @return void
     */
    public void deleteIsNoErrorSourceDataByBatchId(Long batchId){
        List<CollectionDataSource> noErrorList = collectionDataSourceMapper.findNoErrorByBatchId(batchId);
        List<Long> noErrorIds = commonService.getCollectionDataSourceIdsByList(noErrorList);
        if(ObjectUtils.isNotEmptyList(noErrorIds)){
            commonService.delCollectionDataSourceByIds(noErrorIds);
        }
        logger.info("删除的数据>{1}",noErrorIds.size());
    }

}
