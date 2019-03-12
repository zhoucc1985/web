package com.cloud.entity.datamanagement;

import com.cloud.annotation.execl.ExcelResolve;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import lombok.*;

/**
* 统计Ftp信息实体类
* @author zhuJT
* @time 2019/01/11
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FtpStat implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * ftp_id
    */
    @ApiModelProperty(notes = "ftp_id")
    private Long ftpId;

    /**
    * 文件数
    */
    @ApiModelProperty(notes = "文件数")
    @ExcelResolve(titleName = "文件数")
    private Integer fileNum;

    /**
    * 空文件数
    */
    @ApiModelProperty(notes = "空文件数")
    @ExcelResolve(titleName = "空文件数")
    private Integer emptyNum;

    /**
    * 非空文件数
    */
    @ApiModelProperty(notes = "非空文件数")
    @ExcelResolve(titleName = "非空文件数")
    private Integer noemptyNum;

    /**
    * 文件空间大小
    */
    @ApiModelProperty(notes = "文件空间大小")
    @ExcelResolve(titleName = "文件空间大小")
    private String fileSize;

    /**
    * 空表率
    */
    @ApiModelProperty(notes = "空表率")
    @ExcelResolve(titleName = "空表率")
    private String rate;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ftpId=").append(ftpId);
        sb.append(", fileNum=").append(fileNum);
        sb.append(", emptyNum=").append(emptyNum);
        sb.append(", noemptyNum=").append(noemptyNum);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", rate=").append(rate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}