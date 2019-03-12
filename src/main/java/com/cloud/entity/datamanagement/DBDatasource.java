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
 * DB数据源实体类
 *
 * @author zhuJT
 * @time 2019/01/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DBDatasource implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(notes = "id")
    @ExcelResolve(titleName = "id")
    private Long id;

    /**
     * 数据源名称
     */
    @ApiModelProperty(notes = "数据源名称")
    @ExcelResolve(titleName = "数据源名称")
    private String sourceName;

    /**
     * 系统名称
     */
    @ApiModelProperty(notes = "系统名称")
    @ExcelResolve(titleName = "系统名称")
    private String systemName;

    /**
     * 品牌名称
     */
    @ApiModelProperty(notes = "品牌名称")
    @ExcelResolve(titleName = "品牌名称")
    private String brandName;

    /**
     * 系统版本号
     */
    @ApiModelProperty(notes = "系统版本号")
    @ExcelResolve(titleName = "系统版本号")
    private String systemVersion;

    /**
     * 数据库类型  1.MySQL 2.SQLServer 3.Oracle
     */
    @ApiModelProperty(notes = "数据库类型")
    @ExcelResolve(titleName = "数据库类型")
    private String databaseType;

    /**
     * 服务器IP
     */
    @ApiModelProperty(notes = "服务器IP")
    @ExcelResolve(titleName = "服务器IP")
    private String serviceIp;

    /**
     * 服务器端口
     */
    @ApiModelProperty(notes = "服务器端口")
    @ExcelResolve(titleName = "服务器端口")
    private String serviceProt;

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
     * 1.服务名 2.SID
     */
    @ApiModelProperty(notes = "1.服务名 2.SID")
    @ExcelResolve(titleName = "oracle类型")
    private Integer oracleType;

    /**
     * 服务名
     */
    @ApiModelProperty(notes = "服务名")
    @ExcelResolve(titleName = "服务名")
    private String serviceName;

    /**
     * sid
     */
    @ApiModelProperty(notes = "sid")
    @ExcelResolve(titleName = "sid")
    private String sid;

    /**
     * 数据库名
     */
    @ApiModelProperty(notes = "数据库名")
    @ExcelResolve(titleName = "数据库名")
    private String dbName;

    /**
     * 1.sqlserver 2000 2.sqlserver 2005以上
     */
    @ApiModelProperty(notes = "1.sqlserver 2000 2.sqlserver 2005以上")
    @ExcelResolve(titleName = "sqlServer版本")
    private String version;

    /**
     * 1.成功 2.失败
     */
    @ApiModelProperty(notes = "1.成功 2.失败")
    @ExcelResolve(titleName = "连接是否成功")
    private Integer isConnection;

    /**
     * 1.已生成 2.生成中
     */
    @ApiModelProperty(notes = "1.已生成 2.生成中 3.生成失败 4.初始化")
    @ExcelResolve(titleName = "报告生成状态")
    private Integer reportStatus;

    /**
     * jdbcurl
     */
    @ApiModelProperty(notes = "jdbcurl")
    @ExcelResolve(titleName = "jdbcurl")
    private String jdbcurl;

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
    @ExcelResolve(titleName = "文件导入类型")
    private Integer type;

    /**
     * -1.删除 1.生效 2.失效
     */
    @ApiModelProperty(notes = "-1.删除 1.生效 2.失效")
    @ExcelResolve(titleName = "状态")
    private Integer status;

    /**
     * 数据库文件
     */
    @ApiModelProperty(notes = "数据库文件")
    @ExcelResolve(titleName = "数据库文件")
    private String dbFile;

    /**
     * 1.已经推送到大数据平台 2.没有推送到数据库平台
     */
    @ApiModelProperty(notes = "1.已经推送到大数据平台 2.没有推送到数据库平台")
    @ExcelResolve(titleName = "是否推送")
    private Integer isPush;
    
    /**
     * 组织id
     */
    @ApiModelProperty(notes = "权限控制,组织id")
    @ExcelResolve(titleName = "组织id")
    private String orgId;


    private static final long serialVersionUID = 1L;

    public DBDatasource(String sourceName, String systemName, String brandName, String systemVersion, String databaseType,
                        String serviceIp, String serviceProt, String userName, String passWord, Integer oracleType, String dbName, String version, String remarks,String dbFile,Integer isPush,String orgId) {
        this.sourceName = sourceName;
        this.systemName = systemName;
        this.brandName = brandName;
        this.systemVersion = systemVersion;
        this.databaseType = databaseType;
        this.serviceIp = serviceIp;
        this.serviceProt = serviceProt;
        this.userName = userName;
        this.passWord = passWord;
        this.oracleType = oracleType;
        this.dbName = dbName;
        this.version = version;
        this.remarks = remarks;
        this.dbFile = dbFile;
        this.isPush = isPush;
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "DBDatasource{" +
                "id=" + id +
                ", sourceName='" + sourceName + '\'' +
                ", systemName='" + systemName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                ", databaseType='" + databaseType + '\'' +
                ", serviceIp='" + serviceIp + '\'' +
                ", serviceProt='" + serviceProt + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", oracleType=" + oracleType +
                ", serviceName='" + serviceName + '\'' +
                ", sid='" + sid + '\'' +
                ", dbName='" + dbName + '\'' +
                ", version='" + version + '\'' +
                ", isConnection=" + isConnection +
                ", reportStatus=" + reportStatus +
                ", jdbcurl='" + jdbcurl + '\'' +
                ", modifiedBy=" + modifiedBy +
                ", modifiedTime=" + modifiedTime +
                ", checkTime=" + checkTime +
                ", remarks='" + remarks + '\'' +
                ", type=" + type + '\'' +
                ", dbFile=" + dbFile + '\'' +
                ", isPush=" + isPush + '\'' +
                ", status=" + status + '\'' +
                ", orgId=" + orgId +
                '}';
    }
}