package com.cloud.entity.datacollect;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 模板管理
* @author zhuJT
* @time 2019/01/21
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectTemplate implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "模板编号")
    private Integer templateId;

    /**
    * 
    */
    @ApiModelProperty(notes = "系统名称")
    private Integer systemName;

    /**
    * 模板保存在服务器的路径
    */
    @ApiModelProperty(notes = "模板保存在服务器的路径")
    private String templatePath;

    /**
    * 
    */
    @ApiModelProperty(notes = "子系统名称")
    private String subsystemName;

    /**
    * 
    */
    @ApiModelProperty(notes = "模板名称")
    private String templateName;

    /**
    * 
    */
    @ApiModelProperty(notes = "临时表名称")
    private String tempTableName;

    /**
    * 
    */
    @ApiModelProperty(notes = "真实表名称")
    private String realTableName;

    /**
    * 
    */
    @ApiModelProperty(notes = "组织编号")
    private String orgCode;

    /**
     * 一级系统名称
    * 1、学工 2、教务  3、一卡通 4、其他
    */
    @ApiModelProperty(notes = "一级系统名称:1、学工 2、教务  3、一卡通 4、其他")
    private Integer firstTypeCode;

    /**
     * 系统二级分类
    * 1、学生  2、老师  3、课程   4 、学院  5、专业   6、其他
    */
    @ApiModelProperty(notes = "系统二级分类:1、学生  2、老师  3、课程   4 、学院  5、专业   6、其他")
    private Integer secondTypeCode;

    /**
    * 临时表建表语句
    */
    @ApiModelProperty(notes = "临时表建表语句")
    private String tempTableCreateSql;

    /**
    *  真实表建表语句
    */
    @ApiModelProperty(notes = "真实表建表语句")
    private String realTableCreateSql;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", templateId=").append(templateId);
        sb.append(", systemName=").append(systemName);
        sb.append(", templatePath=").append(templatePath);
        sb.append(", subsystemName=").append(subsystemName);
        sb.append(", templateName=").append(templateName);
        sb.append(", tempTableName=").append(tempTableName);
        sb.append(", realTableName=").append(realTableName);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", firstTypeCode=").append(firstTypeCode);
        sb.append(", secondTypeCode=").append(secondTypeCode);
        sb.append(", tempTableCreateSql=").append(tempTableCreateSql);
        sb.append(", realTableCreateSql=").append(realTableCreateSql);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}