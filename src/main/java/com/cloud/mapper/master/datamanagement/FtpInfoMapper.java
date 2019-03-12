package com.cloud.mapper.master.datamanagement;

import com.cloud.entity.datamanagement.FileInfo;
import com.cloud.entity.vo.qualityReport.FtpDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 请添加描述
* @author zhuJT
* @time 2019/01/11
*/
public interface FtpInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    /**
     * 查询指定FTP数据源的质量详情列表
     * @param pageParam
     * @param ftpId
     * @return
     */
    List<FtpDetailVo> getFtps(@Param("ftpId") Long ftpId);
}