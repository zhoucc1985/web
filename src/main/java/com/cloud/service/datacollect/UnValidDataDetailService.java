package com.cloud.service.datacollect;

import com.cloud.common.vo.datacollection.CollectionDataErrorObjVO;
import com.github.pagehelper.PageInfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 17:22 2019/1/14
 * @Description:
 */
@Service
public interface UnValidDataDetailService {

    /**
     * 无效数据的统计头
     * @param batchId
     * @return
     */
    Map<String,Object> invalidDataStat(String batchId);

    /**
     * 分页查询错误列表
     * @param page
     * @param pageSize
     * @param batchId
     * @return
     */
    PageInfo<List<Map<String,Object>>> showInvalidDataList(Integer page, Integer pageSize, String batchId) throws Exception;

    /**
     * 修改无效数据后保存更新（新）
     * @param batchId
     * @param id
     * @param columnDataMap
     * @return
     */
    Map<String,Object> updateByEntity(String batchId,Long id,Map<String,Object> columnDataMap);

    /**
     * 删除数据
     * @param ids id(每个临时表的每条数据都有一个唯一的Id)
     * @param batchId 批次Id
     */
    void deleteInvalidData(List<String> ids,String batchId);

    void exportInvalidData(Long batchId, HttpServletResponse response);

    List<Map<String, Object>> findTableErrorList(String batchId,String tableName);
   
}
