package com.cloud.mapper.master.datamanagement;

import com.cloud.entity.datamanagement.FieldInfo;
import com.cloud.entity.vo.qualityReport.FieldDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
public interface FieldInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FieldInfo record);

    int insertSelective(FieldInfo record);

    FieldInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FieldInfo record);

    int updateByPrimaryKey(FieldInfo record);

    /**
     * 查询指定table下的所有字段详情
     * @param tableId 表Id
     * @param sort
     * @return
     */
    List<FieldDetailVo> getFieldsByTableId(@Param("tableId") Long tableId, @Param("sort") Long sort);

    List<Map<String,Object>> findEmptyTableList(@Param("id") Integer id);
}