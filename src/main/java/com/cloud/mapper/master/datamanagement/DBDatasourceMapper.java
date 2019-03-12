package com.cloud.mapper.master.datamanagement;

import com.cloud.common.qo.DBManagementQo;
import com.cloud.entity.datamanagement.DBDatasource;
import com.cloud.entity.vo.qualityReport.DbQualityReportVo;
import com.cloud.entity.vo.qualityReport.EmptyTableVo;
import com.cloud.entity.vo.qualityReport.TableMenuVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
public interface DBDatasourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DBDatasource record);

    int insertSelective(DBDatasource record);

    DBDatasource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DBDatasource record);

    int updateByPrimaryKey(DBDatasource record);

    List<DBDatasource> queryPage(DBManagementQo qo);

    /**
     * 删除数据源（修改状态为-1）
     * @param dataSourceId 数据源id
     */
    void changeStatus(@Param("dataSourceId") Long dataSourceId);

    List<TableMenuVo> findAllTablesGroupBySystem();

    List<DBDatasource> select();

    Long selectIncrId();

    DbQualityReportVo getDBQualityReportById(@Param("id") Long id);

    List<EmptyTableVo> getEmptyTableById(@Param("id") Long id);

    void updateToIsPushById(@Param("id") Long id);
    
    int updateDBDataSourceById(Integer id);
}