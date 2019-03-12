package com.cloud.entity.datacollect;

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
* @author zhuJT
* @time 2019/01/17
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectImportDetail implements Serializable {
    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Integer id;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Integer templateId;
    /**
    *
    */
    @ApiModelProperty(notes = "")
    private String  templateName;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Integer totalNum;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Integer effectiveNum;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Integer invalidNum;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date loadTime;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private String batchId;

    /**
    * 1、正在导入（导入验证中） 
            2、导入完成（导入验证完成）
    */
    @ApiModelProperty(notes = "1、正在导入（导入验证中）2、导入完成（导入验证完成）")
    private Integer validType;

    /**
    * 标志是否导入完成 
            1、导入完成
            0、未导入完成
            默认值：0  
            相关业务逻辑：
            导入完成 则把temp表数据录到 真实表 并删除temp表数据标记当前批次导入完成
            
    */
    @ApiModelProperty(notes = "标志是否导入完成 1、导入完成 0、未导入完成 默认值：0 相关业务逻辑： 导入完成 则把temp表数据录到 真实表 并删除temp表数据标记当前批次导入完成")
    private Short isimport;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private String deptCode;

    /**
    * 
    */
    @ApiModelProperty(notes = "")
    private Integer userCode;

    /**
    * 1、学工 2、教务  3、一卡通 4、其他
    */
    @ApiModelProperty(notes = "1、学工 2、教务  3、一卡通 4、其他")
    private Integer firstTypeCode;

    /**
    * 1、学生  2、老师  3、课程   4 、学院  5、专业   6、其他
    */
    @ApiModelProperty(notes = "1、学生  2、老师  3、课程   4 、学院  5、专业   6、其他")
    private Integer secondTypeCode;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", templateId=").append(templateId);
        sb.append(", templateName=").append(templateName);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", effectiveNum=").append(effectiveNum);
        sb.append(", invalidNum=").append(invalidNum);
        sb.append(", loadTime=").append(loadTime);
        sb.append(", batchId=").append(batchId);
        sb.append(", validType=").append(validType);
        sb.append(", isimport=").append(isimport);
        sb.append(", deptCode=").append(deptCode);
        sb.append(", userCode=").append(userCode);
        sb.append(", firstTypeCode=").append(firstTypeCode);
        sb.append(", secondTypeCode=").append(secondTypeCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}