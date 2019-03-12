package com.cloud.entity.cloud;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 请添加描述
* @author zengqh
* @time 2019/02/14
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectUsalRule implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Integer id;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private String ruleName;

    /**
    * 1、文本 string
            2、日期 date 
            3、数字 int 
            4、数字 double 
            
    */
    @ApiModelProperty(notes = "1、文本 string 2、日期 date 3、数字 int 4、数字 double ")
    private Integer ruleType;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private String formatStr;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Short minLength;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Short maxLength;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private String maxValue;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private String minValue;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Short decimalDigits;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private String regexText;

    /**
    * 1.如果是日期
            符合yyyy-dd-mm
            
            2.整型
            介于最大值{最大值} 最小值{最小值}
            
            3.浮点型
    */
    @ApiModelProperty(notes = "1.如果是日期 符合yyyy-dd-mm 2.整型 介于最大值{最大值} 最小值{最小值} 3.浮点型")
    private String error;
    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", ruleType=").append(ruleType);
        sb.append(", formatStr=").append(formatStr);
        sb.append(", minLength=").append(minLength);
        sb.append(", maxLength=").append(maxLength);
        sb.append(", maxValue=").append(maxValue);
        sb.append(", minValue=").append(minValue);
        sb.append(", decimalDigits=").append(decimalDigits);
        sb.append(", regexText=").append(regexText);
        sb.append(", error=").append(error);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}