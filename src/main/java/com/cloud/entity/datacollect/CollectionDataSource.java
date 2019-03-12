package com.cloud.entity.datacollect;

import annotion.CollectRule;
import com.cloud.common.vo.ExcelResolve;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* 采集数据原数据实体
* @author Pan jianneng
* @date 2018/11/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class    CollectionDataSource  implements Serializable {
    /**
     *
     */
    @ApiModelProperty(notes = "")
    private Long id;

    /**
     * 学生学号
     */
    @ExcelResolve(titleName = "学号")
    @ApiModelProperty(notes = "学生学号")
    @CollectRule(name = "studentNo",ruleType = "text",text = "学生学号")
    private String studentNo;

    /**
     * 姓名
     */
    @CollectRule(name="name")
    @ExcelResolve(titleName = "姓名")
    @ApiModelProperty(notes = "姓名")
    private String name;

    /**
     * 性别 ,校验性别需要添加关联身份证
     * 正则直接判断长度了，读取excel有问题，会在中文后面添加拼音。。。
     */
    @CollectRule(name="sex",relField = "idcard",text = "性别")
    @ExcelResolve(titleName = "性别",regexText = "^(男|女)$",regexDesc = "请检查性别数据，只允许一个字符")
    @ApiModelProperty(notes = "性别")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(notes = "年龄")
    private String age;

    /**
     * 身份证
     */
    @CollectRule(name="idcard",text = "身份证号")
    @ExcelResolve(titleName = "身份证号")
    @ApiModelProperty(notes = "身份证号")
    private String idcard;

    /**
     * 出生日期
     */
    @CollectRule(name = "birthDate",ruleType = "birthday", relField = "idcard",text = "出生日期")
    @ExcelResolve(titleName = "出生日期")
    @ApiModelProperty(notes = "出生日期")
    private String birthDate;

    /**
     * 出生身份
     */
    @CollectRule(name="birthProvince",relField = "idcard",text = "生源省份")
    @ExcelResolve(titleName = "生源省份")
    @ApiModelProperty(notes = "出生省份")
    private String birthProvince;

    /**
     * 出生城市
     */
    @ExcelResolve(titleName = "生源城市")
    @ApiModelProperty(notes = "出生城市")
    private String birthCity;

    /**
     * 高考成绩
     */
    @CollectRule(name="score",text = "高考成绩")
    @ExcelResolve(titleName = "高考成绩")
    @ApiModelProperty(notes = "高考成绩")
    private String score;

    /**
     * 学院名称
     */
    @ExcelResolve(titleName = "学院名称")
    @ApiModelProperty(notes = "学院名称")
    @CollectRule(name = "collegeName",ruleType = "text",text = "学院名称")
    private String collegeName;

    /**
     * 专业名称
     */
    @ExcelResolve(titleName = "专业名称")
    @ApiModelProperty(notes = "专业名称")
    @CollectRule(name = "professionName",ruleType = "text",text = "专业名称")
    private String professionName;

    /**
     * 学校组织机构id
     */
    @ApiModelProperty(notes = "学校组织机构id")
    private Long orgId;

    /**
     * 星座
     */
    @CollectRule(name="constellation",relField = {"birthDate","idcard"},text = "星座")
    @ApiModelProperty(notes = "星座")
    private String constellation;

    /**
     * 报告批次id
     */
    @ApiModelProperty(notes = "报告批次id")
    private Long batchId;

    /**
     * 是否存在错误数据,默认是错误的
     */
    @ApiModelProperty(notes = "是否存在错误数据")
    private boolean isError;

    /**
     * 当前列错误字段信息对象
     */
    @ApiModelProperty(notes = "当前列错误字段信息对象")
    private String errorMsg;

    /**
     * 学校编码
     */
    @ApiModelProperty(notes="学校编码")
    private String orgCode;

    private static final long serialVersionUID = 1L;


    /**
     * 生肖
     */
    @CollectRule(name="shengXiao",relField = {"birthDate","idcard"})
    @ApiModelProperty(notes="生肖")
    private String shengXiao;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
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
        sb.append(", isError=").append(isError);
        sb.append(", errorMsg=").append(errorMsg);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}