package com.cloud.mapper.master.datamanagement;

import com.cloud.entity.datamanagement.FtpStat;
import com.cloud.entity.vo.qualityReport.FtpQualityReportVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
public interface FtpStatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FtpStat record);

    int insertSelective(FtpStat record);

    FtpStat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FtpStat record);

    int updateByPrimaryKey(FtpStat record);

    /**
     * 查询FTP数据校验报告
     * @return
     */
    List<FtpQualityReportVo> getFtpQualityReport(@Param("orgId")String orgId);
}