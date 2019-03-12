package com.cloud.service.datacollection;

import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.*;
import com.cloud.common.vo.datacollection.CollectionDataErrorObjVO;
import com.cloud.entity.datacollection.CollectionDataSource;
import com.cloud.entity.datacollection.CollectionDataStandard;
import com.cloud.mapper.master.datacollection.CollectionDataSourceMapper;
import main.CollectExcuteMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import rule.DateRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * 数据采集公共业务处理工具类
 *
 * @author: Pan Jianneng
 * @create: 2018/11/22 09:02
 */
@Component
public class CollectionDataCommonService {

    private static final Logger logger = LoggerFactory.getLogger(CollectionDataCommonService.class);

//    @Resource
 private CollectionDataSourceMapper collectionDataSourceMapper;
    /**
     *
     * 校验数据
     * 将会标记错误之后返回
      * @param dataList
     * @return: boolean
     * @auther: Pan jianneng
     * @date: 2018/10/21 16:53
     */
    public List<CollectionDataSource> checkExcelData(List<CollectionDataSource> dataList){
        List<CollectionDataSource> resultList = new ArrayList<CollectionDataSource>();
        try {
            CollectExcuteMain main = new CollectExcuteMain(false);
            List<Map<String,Object>> checkResultList = main.collectFilter(dataList);
            checkResultList.forEach(checkResultMap ->{
                CollectionDataSource vo = (CollectionDataSource) checkResultMap.get("data");
                vo.setError(false);
                Map<String,Object> errorMap = (Map) checkResultMap.get("error");
                if(errorMap.size()>0){
                    vo.setError(true);
                    setErrorEntity(vo, errorMap);
                }
                resultList.add(vo);
            });
        } catch (Exception e) {
            logger.error("校验数据失败："+e.getMessage());
            throw new BusinessException("校验数据失败，请稍后再试！");
        }
        return resultList;
    }

    /**
     * 单个数据校验
     * @author Pan Jianneng
     * @date 2018/11/23 3:35 PM
     * @param source
     * @return com.cloud.entity.datacollection.CollectionDataSource
     */
    public CollectionDataSource checkExcelDataSingle(CollectionDataSource source){
        try {
            CollectExcuteMain main = new CollectExcuteMain(false);
            Map<String,Object> checkResultMap = main.collectFilterSingle(source);
            CollectionDataSource vo = (CollectionDataSource) checkResultMap.get("data");
            Map<String,Object> errorMap = (Map) checkResultMap.get("error");
            if(errorMap.size()>0) {
                vo.setError(true);
                setErrorEntity(vo, errorMap);
            }else{
                vo.setError(false);
                vo.setErrorMsg(null);
            }
            return vo;
        } catch (Exception e) {
            logger.error("校验数据失败："+e.getMessage());
            throw new RuntimeException("校验数据失败，请稍后再试！");
        }
    }

    /**
     * 设置错误对象，将对象转为Json,保存到原始数据的错误信息中
     * @author Pan Jianneng
     * @date 2018/11/23 3:33 PM
     * @param vo
     * @param errorMap
     * @return void
     */
    private void setErrorEntity(CollectionDataSource vo, Map<String, Object> errorMap) {
        List<CollectionDataErrorObjVO> errorObjVOs = new ArrayList<CollectionDataErrorObjVO>();
        for(String errorField:errorMap.keySet()){
            CollectionDataErrorObjVO errorObjVO = new CollectionDataErrorObjVO();
            errorObjVO.setError(true);
            errorObjVO.setName(errorField);
            errorObjVO.setMsg(errorMap.get(errorField)==null?null:errorMap.get(errorField).toString());
            errorObjVOs.add(errorObjVO);
        }
        try {
            vo.setErrorMsg(CommonUtils.getJsonStrByObject(errorObjVOs));
        } catch (Exception e) {
            logger.error("设置【"+vo.getName()+"】的错误信息失败"+e.getMessage());
        }
    }

    /**
     * 根据ID批量删除原数据，这个ID是已经保存在标准库里边的原始数据ID
     * @author Pan Jianneng
     * @date 2018/11/17 5:39 PM
     * @param collectionDataSourceIds
     * @return void
     */
    public void delCollectionDataSourceByIds(List<Long> collectionDataSourceIds){
        if(ObjectUtils.isNotEmptyList(collectionDataSourceIds)){
            collectionDataSourceMapper.batchDelSourceById(collectionDataSourceIds);
        }
    }

    /**
     * 根据原始数据集合获取集合id
     * @author Pan Jianneng
     * @date 2018/11/23 11:26 AM
     * @param collectionDataSources
     * @return java.util.List<java.lang.Long>
     */
    public List<Long> getCollectionDataSourceIdsByList(List<CollectionDataSource> collectionDataSources){
        List<Long> ids = new ArrayList<Long>();
        if(ObjectUtils.isNotEmptyList(collectionDataSources)){
            collectionDataSources.forEach(collectionDataSource -> {
                ids.add(collectionDataSource.getId());
            });
        }
        return ids;
    }

    /**
     * 将原数据转为标准数据
     * @author Pan Jianneng
     * @date 2018/11/16 6:12 PM
     * @params [sources]
     * @return java.util.List<com.cloud.entity.datacollection.CollectionDataStandard>
     */
    public List<CollectionDataStandard> convert(List<CollectionDataSource> sources){
        List<CollectionDataStandard> standards = new ArrayList<CollectionDataStandard>();
        if(ObjectUtils.isNotEmptyList(sources)){
            for(CollectionDataSource source:sources){
                CollectionDataStandard standard = new CollectionDataStandard();
                BeanUtils.copyProperties(source,standard);
                standard.setId(null);
                standard.setSourceId(source.getId());
                standard.setGenerate(false);
                standard.setScore(Double.valueOf(source.getScore()));
                standard.setBirthDate(DateRule.convertRightDate(source.getBirthDate()));
                standards.add(standard);
            }
        }
        return standards;
    }

    /**
     * 单个原数据转换
     * @author Pan Jianneng
     * @date 2018/11/23 3:41 PM
     * @param source
     * @return com.cloud.entity.datacollection.CollectionDataStandard
     */
    public CollectionDataStandard convertSingle(CollectionDataSource source){
        CollectionDataStandard standard = new CollectionDataStandard();
        if(ObjectUtils.isNotNullObject(source)){
                BeanUtils.copyProperties(source,standard);
                standard.setId(null);
                standard.setSourceId(source.getId());
                standard.setGenerate(false);
                standard.setScore(Double.valueOf(source.getScore()));
                standard.setBirthDate(DateRule.convertRightDate(source.getBirthDate()));
                return standard;
        }
        return null;
    }

    /**
     * 根据批次ID进行分组
     * @author Pan Jianneng
     * @date 2018/11/17 6:34 PM
     * @param allSourceData
     * @return java.util.Map<java.lang.Long,java.util.List<com.cloud.entity.datacollection.CollectionDataSource>>
     */
    public Map<Long,List<CollectionDataSource>> groupByOrgIdAndBatchId(List<CollectionDataSource> allSourceData){
        Map<Long,List<CollectionDataSource>> resultMap = new LinkedHashMap<Long,List<CollectionDataSource>>();
        allSourceData.forEach(collectionDataSource -> {
            Long key = collectionDataSource.getBatchId();
            if(!resultMap.containsKey(key)){
                List<CollectionDataSource> collectionDataSourceList = new ArrayList<CollectionDataSource>();
                collectionDataSourceList.add(collectionDataSource);
                resultMap.put(key,collectionDataSourceList);
            }else{
                List<CollectionDataSource> collectionDataSourceList = resultMap.get(key);
                collectionDataSourceList.add(collectionDataSource);
                resultMap.put(key,collectionDataSourceList);
            }
        });
        return resultMap;
    }


    /**
     * 根据校验过后的原数据获取没有错误的数据返回，添加到标准数据库中
     * @author Pan Jianneng
     * @date 2018/11/23 10:39 AM
     * @param checkSources 校验后的原数据
     * @return java.util.List<com.cloud.entity.datacollection.CollectionDataStandard>
     */
    public List<CollectionDataStandard> getStandardBySourceCheckDatas(List<CollectionDataSource> checkSources){
        List<CollectionDataStandard> resultList = new ArrayList<CollectionDataStandard>();
        List<CollectionDataSource> noErrors = new ArrayList<CollectionDataSource>();
        checkSources.forEach(checkSource ->{
            if(!checkSource.isError()){
                noErrors.add(checkSource);
            }
        });
        if(ObjectUtils.isNotEmptyList(noErrors)){
            resultList = convert(noErrors);
        }
        return resultList;
    }

    /**
     * 去重复
     * 使用重复数据的最后一个数据
     * @author Pan Jianneng
     * @date 2018/11/27 8:39 PM
     * @param list
     * @return java.util.List<com.cloud.entity.datacollection.CollectionDataSource>
     */
    public List<CollectionDataSource> groupByOrgIdAndStudent(List<CollectionDataSource> list){
        Map<String,CollectionDataSource> map = new HashMap<String,CollectionDataSource>(0);
        list.forEach(collectionDataSource -> {
            String key = collectionDataSource.getOrgId()+"@"+collectionDataSource.getStudentNo();
            if(map.containsKey(key)){
                map.put(key,collectionDataSource);
            }
        });
        if(map.size()>0){
            list.clear();
            for(String key:map.keySet()){
                list.add(map.get(key));
            }
        }
        return list;
    }
    /**
     * 根据文件路径读取excel
     * @author Pan Jianneng
     * @date 2018/11/29 12:55 AM
     * @param filePath
     * @return java.util.List<java.lang.String[]>
     */
    public List<String[]> readExcelAndConvert2DataSource(String filePath) throws Exception {
        List<String[]> readerExcelList = new ArrayList<String[]>();
        try {
            readerExcelList = ExcelCovertToCsvAndReader
                    .readExcel(
                            filePath, 10);
        } catch (Exception e) {
            delFile(filePath);
            logger.error("读取文件【"+filePath+"】失败"+e.getMessage());
            throw  new Exception("导入文件不符合要求，确认按照下载模板导入");
        }
        if(ObjectUtils.isEmptyList(readerExcelList)){
            delFile(filePath);
            logger.error("读取文件【"+filePath+"】失败,请检查导入文件是否是正确");
            throw  new Exception("导入文件不符合要求，确认按照下载模板导入");
        }
        delFile(filePath);
        return readerExcelList;
    }

    /**
     * 删除临时文件
     * @author Pan Jianneng
     * @date 2018/11/29 5:23 PM
     * @param filePath
     * @return void
     */
    private void delFile(String filePath){
        //用完删除
        try {
            Files.deleteIfExists(new File(filePath).toPath());
        } catch (IOException e) {
            logger.error("删除文件【"+filePath+"】失败"+e.getMessage());
        }
    }
}

