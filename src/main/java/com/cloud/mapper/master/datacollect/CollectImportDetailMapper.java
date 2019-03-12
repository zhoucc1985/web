package com.cloud.mapper.master.datacollect;

import com.cloud.entity.datacollect.CollectImportDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* 导入批次列表查询
* @author zengqh
* @time 2019/01/17
*/
@Mapper
public interface CollectImportDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CollectImportDetail record);

    void updateCountType(CollectImportDetail record);

    int insertSelective(CollectImportDetail record);

    CollectImportDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectImportDetail record);

    int updateByPrimaryKey(CollectImportDetail record);

    List<CollectImportDetail> queryPage(@Param(value="reportName") String reportName,
                                        @Param(value="startTime") Date startTime,
                                        @Param(value="endTime") Date endTime,
                                        @Param(value="deptCode") String deptCode);
    void deleteByBatchId(@Param(value="batchId")String batchId);

    /**
     * 根据批次编号查询模板ID
     * @param batchId 批次编号
     * @return
     */
    Integer getTemplateIdByBatchId(@Param("batchId") String batchId);

    void updateImportDetail(@Param("batchId") String batchId);

    void updateBatchValidResult(@Param("batchId") String batchId);

}