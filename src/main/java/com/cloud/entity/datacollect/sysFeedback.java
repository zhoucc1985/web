package com.cloud.entity.datacollect;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 请添加描述
* @author zhuJT
* @time 2019/02/26
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class sysFeedback implements Serializable {
    /**
    * 自动编号
    */
    @ApiModelProperty(notes = "自动编号")
    private Integer id;

    /**
    * 用户编号
    */
    @ApiModelProperty(notes = "用户编号")
    private Integer userId;

    /**
    * 用户姓名
    */
    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    /**
    * 校验规则星
    */
    @ApiModelProperty(notes = "校验规则星")
    private Byte ruleConfigurationStar;

    /**
    * 校验规则配置
    */
    @ApiModelProperty(notes = "校验规则配置")
    private String ruleConfigurationMsg;

    /**
    * 数据源管理星数
    */
    @ApiModelProperty(notes = "数据源管理星数")
    private Byte datasourceStar;

    /**
    * 数据源管理意见
    */
    @ApiModelProperty(notes = "数据源管理意见")
    private String datasourceMsg;

    /**
    * 数据校验报告星数
    */
    @ApiModelProperty(notes = "数据校验报告星数")
    private Byte presentationStar;

    /**
    * 数据校验报告意见
    */
    @ApiModelProperty(notes = "数据校验报告意见")
    private String presentationMsg;

    /**
    * 数据收集星数
    */
    @ApiModelProperty(notes = "数据收集星数")
    private Byte collectionDataStar;

    /**
    * 数据收集意见
    */
    @ApiModelProperty(notes = "数据收集意见")
    private String collectionDataMsg;

    /**
    * 其他意见和建议
    */
    @ApiModelProperty(notes = "其他意见和建议")
    private String otherMsg;

    /**
    * 回复
    */
    @ApiModelProperty(notes = "回复")
    private String reply;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", ruleConfigurationStar=").append(ruleConfigurationStar);
        sb.append(", ruleConfigurationMsg=").append(ruleConfigurationMsg);
        sb.append(", datasourceStar=").append(datasourceStar);
        sb.append(", datasourceMsg=").append(datasourceMsg);
        sb.append(", presentationStar=").append(presentationStar);
        sb.append(", presentationMsg=").append(presentationMsg);
        sb.append(", collectionDataStar=").append(collectionDataStar);
        sb.append(", collectionDataMsg=").append(collectionDataMsg);
        sb.append(", otherMsg=").append(otherMsg);
        sb.append(", reply=").append(reply);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}