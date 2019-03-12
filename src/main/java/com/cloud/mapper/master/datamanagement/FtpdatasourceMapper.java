package com.cloud.mapper.master.datamanagement;

import com.cloud.common.qo.FtpManagementQo;
import com.cloud.entity.datamanagement.Ftpdatasource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
public interface FtpdatasourceMapper {

    int insert(Ftpdatasource record);

    int insertSelective(Ftpdatasource record);

    Ftpdatasource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ftpdatasource record);

    int updateByPrimaryKey(Ftpdatasource record);

    List<Ftpdatasource> queryPage(FtpManagementQo qo);

    Long selectIncrId();

    void updateStatus(@Param("ftpId") Long ftpId);

    /**
     * 查询全部列表
     */
    List<Ftpdatasource> select();

    void changeStatus(@Param("ftpId") Long ftpId);
}