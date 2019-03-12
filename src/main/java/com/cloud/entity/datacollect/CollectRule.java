package com.cloud.entity.datacollect;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectRule implements Serializable {
	@ApiModelProperty(notes = "")
	private Integer id;
	@ApiModelProperty(notes = "字段编号")
	private Integer columnId;
	@ApiModelProperty(notes = "规则名称")
	private String ruleName;
	@ApiModelProperty(notes = "规则类型")
	private String ruleType;
	@ApiModelProperty(notes = "格式化字符串")
	private String formatStr;
	@ApiModelProperty(notes = "最小长度")
	private String minLength;
	@ApiModelProperty(notes = "最大长度")
	private String maxLength;
	@ApiModelProperty(notes = "最大值")
	private String maxValue;
	@ApiModelProperty(notes = "最小值")
	private String minValue;
	@ApiModelProperty(notes = "小数位数")
	private String decimalDigits;
	@ApiModelProperty(notes = "正则规则")
	private String regexText;
	@ApiModelProperty(notes = "出错提示语")
	private String error;
	@ApiModelProperty(notes = "是否容许为空")
	private int isNull;
	@ApiModelProperty(notes = "key_word")
	private int keyWord;
	

	@Override
	public String toString() {
		return "CollectRule [id=" + id + ", columnId=" + columnId + ", ruleName=" + ruleName + "keyWord=" + keyWord + ", ruleType=" + ruleType
				+ ", formatStr=" + formatStr + ", minLength=" + minLength + ", maxLength=" + maxLength + ", maxValue="
				+ maxValue + ", minValue=" + minValue + ", decimalDigits=" + decimalDigits + ", regexText=" + regexText
				+ ", error=" + error + ", isNull=" + isNull + "]";
	}



	public CollectRule(Integer columnId, String ruleType, String maxLength, int isNull) {
		super();
		this.columnId = columnId;
		this.ruleType = ruleType;
		this.maxLength = maxLength;
		this.isNull = isNull;
	}
	
	
	
}
