package com.cloud.service.datacollection;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.DateUtils;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.datacollection.CollectionBatchSearchVo;
import com.cloud.entity.datacollection.CollectionBatch;
import com.cloud.entity.datacollection.ReportTemplate;
import com.cloud.mapper.master.datacollection.CollectionBatchMapper;
import com.cloud.mapper.master.datacollection.ReportTemplateMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 *  数据采集
 *
 * @author lq
 * @time 2018/11/17 15:50
 */
@Service
@Transactional(rollbackFor = {})
public class CollectionBatchService {

    private static final Logger logger = LoggerFactory.getLogger(CollectionBatchService.class);


    @Resource
    private CollectionBatchMapper collectionBatchMapper;

    @Resource
    private ReportTemplateMapper reportTemplateMapper;

    /**
     * 查询采集信息列表, 未做数据过滤
     * @param collectionBatchSearchVo 查询信息，包含分页信息
     * @return
     */
    public PageInfo<CollectionBatch> findCollectionBatchList(CollectionBatchSearchVo collectionBatchSearchVo){
        String findDataOrgCode = UserUtils.getInstance().getFindDataOrgCode();
        PageHelper.startPage(collectionBatchSearchVo.getPageNum(), collectionBatchSearchVo.getPageSize());
        List<CollectionBatch> batchList = collectionBatchMapper.searchCollectBatch(
                collectionBatchSearchVo.getStartTime(),collectionBatchSearchVo.getEndTime(),
                collectionBatchSearchVo.getReportName(),findDataOrgCode);
        return new PageInfo<>(batchList);
    }


    /**
     *  添加采集信息记录，每个学校一年同一个模板只能有一条记录
     * @param templId      要添加采集记录的模板ID
     * @throws BusinessException
     */
    public void addCollectionBatchWithRtInfo(Long templId)throws  BusinessException {
        ReportTemplate reportTemplate = reportTemplateMapper.selectByPrimaryKey(templId);
        if(ObjectUtils.isNullObject(reportTemplate)) {
            throw  new BusinessException("找不到对应的模板！");
        }
        ShiroUser user = UserUtils.getInstance().getCurrentlyUserInfo();
        if(!CommonDict.ORG_TYPE_SCHOOL.equals(user.getOrgType())){
            throw new BusinessException("只有学校用户才能选择模板");
        }
        CollectionBatch collectionBatch = new CollectionBatch();
        //批次名称规则，使用模板名称去掉模板两个字，加上年份
        String batchName = reportTemplate.getName().split("模板")[0];
        collectionBatch.setTemplateName(reportTemplate.getName());
        collectionBatch.setReportName(batchName+"-"+DateUtils.currentYear());
        collectionBatch.setRtId(reportTemplate.getId());
        collectionBatch.setOrgId(user.getSchoolOrgId()==null?user.getOrgId():user.getSchoolOrgId());
        addCollectionBatch(collectionBatch);
    }

    /**
     *  添加采集信息记录，每个学校一年同一个模板只能有一条记录
     * @param rtInfo      要添加采集记录的模板信息
     * @throws BusinessException
     */
    public void addCollectionBatchWithRtInfo(CollectionBatch rtInfo)throws  BusinessException {
        CollectionBatch collectionBatch = new CollectionBatch();
        collectionBatch.setRtId(rtInfo.getRtId());
        collectionBatch.setImportTime(new Date());

        LocalDate localDate = LocalDate.now();
        String reportName = rtInfo.getTemplateName() + "_" + localDate.getYear();
        collectionBatch.setReportName(reportName);

        collectionBatch.setBatchNo(DateUtils.formatDateTimeBreif(new Date()));

        Long schoolOrgId = UserUtils.getInstance().getCurrentlyUserInfo().getSchoolOrgId();
        collectionBatch.setOrgId(schoolOrgId);

        addCollectionBatch(collectionBatch);
    }

	/**
     *  添加采集信息记录，每个学校一年同一个模板只能有一条记录
     * @param collectionBatch   要添加采集记录
     * @throws BusinessException
     */
    public void addCollectionBatch(CollectionBatch collectionBatch) throws BusinessException {
        Long orgId = collectionBatch.getOrgId();
        Long temleId = collectionBatch.getRtId();
        collectionBatch.setImportTime(new Date());
        LocalDate importDate = collectionBatch.getImportTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int nowYear = importDate.getYear();
        boolean existsCollectionBatch = checkExistCollectionBatch(orgId,temleId,nowYear);
        if(existsCollectionBatch) {
            throw  new BusinessException("同一份报告模板一年只能使用一次，该模板今年已存在使用记录");
        }
        collectionBatch.setImportTime(null);
        collectionBatch.setBatchNo(String.valueOf(System.currentTimeMillis()));
        collectionBatchMapper.insert(collectionBatch);
    }

    public  boolean checkExistCollectionBatch(Long orgId,Long templeId,int nowYear) throws  BusinessException{
        List<CollectionBatch> collectionBatchList = collectionBatchMapper.selectByYearRptOrg(orgId,nowYear,templeId);
        return ObjectUtils.isNotEmptyList(collectionBatchList);
    }

    /**
     * 根据批次ID查询批次详情
     * @author Pan Jianneng
     * @date 2018/11/28 4:45 PM
     * @param batchId
     * @return com.cloud.entity.datacollection.CollectionBatch
     */
    public CollectionBatch findById(Long batchId){
        return collectionBatchMapper.selectByPrimaryKey(batchId);
    }
}
