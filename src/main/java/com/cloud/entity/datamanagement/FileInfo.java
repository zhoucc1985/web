package com.cloud.entity.datamanagement;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import lombok.*;

/**
* ftp信息实体类
* @author zhuJT
* @time 2019/01/11
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 文件路径
    */
    @ApiModelProperty(notes = "文件路径")
    private String filePath;

    /**
    * 文件名
    */
    @ApiModelProperty(notes = "文件名")
    private String fileName;

    /**
    * 文件大小
    */
    @ApiModelProperty(notes = "文件大小")
    private String fileSize;

    /**
    * ftp_id
    */
    @ApiModelProperty(notes = "ftp_id")
    private Long ftpId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", filePath=").append(filePath);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", ftpId=").append(ftpId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}