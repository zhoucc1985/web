package com.cloud.entity.datamanagement;

import com.cloud.annotation.execl.ExcelResolve;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 请添加描述
 *
 * @author zhuJT
 * @time 2019/01/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ftpdatasource implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(notes = "id")
    @ExcelResolve(titleName = "id")
    private Long id;

    /**
     * ftp_ip地址
     */
    @ApiModelProperty(notes = "ftp_ip地址")
    @ExcelResolve(titleName = "ftpIP地址")
    private String ftpIp;

    /**
     * FTP端口
     */
    @ApiModelProperty(notes = "FTP端口")
    @ExcelResolve(titleName = "FTP端口")
    private String ftpPort;

    /**
     * 路径
     */
    @ApiModelProperty(notes = "路径")
    @ExcelResolve(titleName = "路径")
    private String path;

    /**
     * 用户名
     */
    @ApiModelProperty(notes = "用户名")
    @ExcelResolve(titleName = "用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(notes = "密码")
    @ExcelResolve(titleName = "密码")
    private String passWord;

    /**
     * 1.成功 2.失败
     */
    @ApiModelProperty(notes = "1.成功 2.失败")
    @ExcelResolve(titleName = "是否连接成功")
    private Integer isConnection;

    /**
     * 1.已生成 2.生成中
     */
    @ApiModelProperty(notes = "1.已生成 2.生成中 3.生成失败 4.初始化")
    @ExcelResolve(titleName = "报告生成状态")
    private Integer reportStatus;

    /**
     * 修改人
     */
    @ApiModelProperty(notes = "修改人")
    @ExcelResolve(titleName = "修改人")
    private String modifiedBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(notes = "修改时间")
    @ExcelResolve(titleName = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifiedTime;

    /**
     * 校验时间
     */
    @ApiModelProperty(notes = "校验时间")
    @ExcelResolve(titleName = "校验时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

    /**
     * 描述
     */
    @ApiModelProperty(notes = "描述")
    @ExcelResolve(titleName = "描述")
    private String remarks;

    /**
     * 1.手动添加 2.自动导入
     */
    @ApiModelProperty(notes = "1.手动添加 2.自动导入")
    @ExcelResolve(titleName = "导入方式")
    private Integer type;

    /**
     * -1.删除 1.生效 2.失效
     */
    @ApiModelProperty(notes = "-1.删除 1.生效 2.失效")
    @ExcelResolve(titleName = "状态")
    private Integer status;

    /**
     * 连接类型 ftp sftp
     */
    @ApiModelProperty(notes = "连接类型")
    @ExcelResolve(titleName = "连接类型")
    private String connType;
    
    /**
     * 组织id
     */
    @ApiModelProperty(notes = "权限控制,组织id")
    @ExcelResolve(titleName = "组织id")
    private String orgId;

    private static final long serialVersionUID = 1L;

    public Ftpdatasource(String ftpIp, String ftpPort, String userName, String passWord, String path,String remarks,String connType , String orgId) {
        this.ftpIp = ftpIp;
        this.ftpPort = ftpPort;
        this.userName = userName;
        this.passWord = passWord;
        this.path = path;
        this.remarks = remarks;
        this.connType = connType;
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ftpIp=").append(ftpIp);
        sb.append(", ftpPort=").append(ftpPort);
        sb.append(", path=").append(path);
        sb.append(", userName=").append(userName);
        sb.append(", passWord=").append(passWord);
        sb.append(", isConnection=").append(isConnection);
        sb.append(", reportStatus=").append(reportStatus);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", remarks=").append(remarks);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", connType=").append(connType);
        sb.append(", orgId=").append(orgId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}