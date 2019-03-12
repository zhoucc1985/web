package com.cloud.entity.vo.dataCollect;

import com.cloud.annotation.execl.ExcelResolve;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DetailsListVo {
    /**
     * id
     */
    @ApiModelProperty(notes = "模板id")
//    @ExcelResolve(titleName = "模板id")
    private Integer id;

    /**
     * 表名称
     */
    @ApiModelProperty(notes = "表名称")
//    @ExcelResolve(titleName = "表名称")
    private String tableName;

    /**
     * 有效数据量
     */
    @ApiModelProperty(notes = "有效数据量")
//    @ExcelResolve(titleName = "有效数据量")
    private Integer effectiveNum;

    /**
     * 批次id
     */
    @ApiModelProperty(notes = "批次id")
    @ExcelResolve(titleName = "批次id")
    private String batchId;

    /**
     * 导入时间
     */
    @ApiModelProperty(notes = "导入时间")
    @ExcelResolve(titleName = "导入时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loadTime;

}
