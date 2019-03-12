package com.cloud.entity.datacollection;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 采集数据标准数据实体
* @author Pan jianneng
* @date 2018/11/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDataStandard implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
    * 原数据id
    */
    @ApiModelProperty(notes = "原数据id")
    private Long sourceId;

    /**
    * 学生学号
    */
    @ApiModelProperty(notes = "学生学号")
    private String studentNo;

    /**
    * 学生姓名
    */
    @ApiModelProperty(notes = "学生姓名")
    private String name;

    /**
    * 性别
    */
    @ApiModelProperty(notes = "性别")
    private String sex;

    /**
    * 年龄
    */
    @ApiModelProperty(notes = "年龄")
    private Integer age;

    /**
    * 身份证
    */
    @ApiModelProperty(notes = "身份证")
    private String idcard;

    /**
    * 生日
    */
    @ApiModelProperty(notes = "生日")
    private Date birthDate;

    /**
    * 出生省份
    */
    @ApiModelProperty(notes = "出生省份")
    private String birthProvince;

    /**
    * 出生城市
    */
    @ApiModelProperty(notes = "出生城市")
    private String birthCity;

    /**
    * 学院名称
    */
    @ApiModelProperty(notes = "学院名称")
    private String collegeName;

    /**
    * 专业名称
    */
    @ApiModelProperty(notes = "专业名称")
    private String professionName;

    /**
    * 学校机构id
    */
    @ApiModelProperty(notes = "学校机构id")
    private Long orgId;

    /**
    * 星座
    */
    @ApiModelProperty(notes = "星座")
    private String constellation;

    /**
    * 高考成绩
    */
    @ApiModelProperty(notes = "高考成绩")
    private Double score;

    /**
    * 批次id
    */
    @ApiModelProperty(notes = "批次id")
    private Long batchId;

    /**
    * 是否已经被生成了报告（1:true）
    */
    @ApiModelProperty(notes = "是否已经被生成了报告（1:true）")
    private boolean generate;

    /**
     * 学校编码
     */
    @ApiModelProperty(notes = "学校编码")
    private String orgCode;

   private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", studentNo=").append(studentNo);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", idcard=").append(idcard);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", birthProvince=").append(birthProvince);
        sb.append(", birthCity=").append(birthCity);
        sb.append(", collegeName=").append(collegeName);
        sb.append(", professionName=").append(professionName);
        sb.append(", orgId=").append(orgId);
        sb.append(", constellation=").append(constellation);
        sb.append(", score=").append(score);
        sb.append(", batchId=").append(batchId);
        sb.append(", generate=").append(generate);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}