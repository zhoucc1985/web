package com.cloud.mapper.master.datamanagement;

import com.cloud.entity.datamanagement.TableStat;
import com.cloud.entity.vo.qualityReport.DbQualityReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
public interface TableStatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TableStat record);

    int insertSelective(TableStat record);

    TableStat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TableStat record);

    int updateByPrimaryKey(TableStat record);

    /**
     * 查询数据库校验报告
     * @param pageParam
     * @return
     */
    List<DbQualityReportVo> getDbQualityReport(@Param("orgId") String orgId);

    /**
     * 查询数据库下所有的空表List
     * @param id 数据库Id
     * @return
     */
    List<Map<String,Object>> findEmptyTableList(@Param("id") Integer id);
    
    /**
     * 删除对应的sourceID数据
     * @param id
     * @return
     */
    int deleteTableStatBySourceId(@Param("id") Integer id);
}