package com.cloud.entity.datacollect;

import com.cloud.common.vo.ExcelResolve;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 10:28 2019/1/18
 * @Description:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Basic_class_info implements Serializable {

    @ExcelResolve(titleName = "班级代码")
    @ApiModelProperty(notes = "班级代码")
    private String code;

    @ExcelResolve(titleName = "班级名称")
    @ApiModelProperty(notes = "班级名称")
    private String name;

    @ExcelResolve(titleName = "年级")
    @ApiModelProperty(notes = "年级")
    private String grade;

    @ExcelResolve(titleName = "状态")
    @ApiModelProperty(notes = "状态")
    private String status;

    @ExcelResolve(titleName = "专业代码")
    @ApiModelProperty(notes = "专业代码")
    private String major_code;

    @ExcelResolve(titleName = "专业名称")
    @ApiModelProperty(notes = "专业名称")
    private String major_name;

    @ExcelResolve(titleName = "学院代码")
    @ApiModelProperty(notes = "学院代码")
    private String academy_code;

    @ExcelResolve(titleName = "学院名称")
    @ApiModelProperty(notes = "学院名称")
    private String academy_name;

    @ApiModelProperty(notes = "每条数据的唯一ID")
    private Long id;

    @ApiModelProperty(notes = "批次ID")
    private Long batch_id;

    @ApiModelProperty(notes = "错误信息")
    private String error_msg;

    @ApiModelProperty(notes = "错误标识 1 错误，0正确")
    private boolean isError;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Basic_class_info{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", grade='").append(grade).append('\'');
        sb.append(", status=").append(status);
        sb.append(", major_code='").append(major_code).append('\'');
        sb.append(", major_name='").append(major_name).append('\'');
        sb.append(", academy_code='").append(academy_code).append('\'');
        sb.append(", academy_name='").append(academy_name).append('\'');
        sb.append(", batch_id=").append(batch_id);
        sb.append(", error_msg='").append(error_msg).append('\'');
        sb.append(", is_error=").append(isError);
        sb.append('}');
        return sb.toString();
    }
}
