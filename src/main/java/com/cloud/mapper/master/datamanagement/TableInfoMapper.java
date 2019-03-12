package com.cloud.mapper.master.datamanagement;

import com.cloud.entity.datamanagement.TableInfo;
import com.cloud.entity.vo.qualityReport.TableInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
public interface TableInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TableInfo record);

    int insertSelective(TableInfo record);

    TableInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TableInfo record);

    int updateByPrimaryKey(TableInfo record);

    /**
     * 根据数据源id查询所有的tables
     * @param sourceId 数据源id
     * @return
     */
    List<TableInfo> selectBySourceId(@Param("sourceId") Long sourceId);

    /**
     * 查询指定系统下的所有表
     * @param dataSourceId 数据源id
     * @param sort
     * @return
     */
    List<TableInfo> getAllTablesByDataSourceId(@Param("dataSourceId") Long dataSourceId, @Param("sort") Long sort);

    List<TableInfoVo> getTableRecord(@Param("id") Long id);
    
    int deleteTableInfoBySourceId(@Param("id") Integer id);
}