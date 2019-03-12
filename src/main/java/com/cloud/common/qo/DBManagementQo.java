package com.cloud.common.qo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * DB数据源查询对象
 * @author zhuJT
 * @date 2018/1/16
 */
@Data
public class DBManagementQo extends BasePageQo{
    /**
     * 数据源名称
     */
    private String sourceName;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 服务器ip
     */
    private String databaseIp;

    /**
     * 连接状态（1.已连接 2.连接失败）
     */
    private Integer connection;

    /**
     * 类型（1.手动添加 2.自动导入）
     */
    private Integer type;

    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 权限组织id
     */
    private String orgId;
}
