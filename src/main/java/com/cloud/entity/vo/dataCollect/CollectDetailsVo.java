package com.cloud.entity.vo.dataCollect;

import com.cloud.annotation.execl.ExcelResolve;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CollectDetailsVo {
    /**
     * 模板id
     */
    @ApiModelProperty(notes = "模板id")
    @ExcelResolve(titleName = "模板id")
    private Integer id;

    /**
     * 模板名称
     */
    @ApiModelProperty(notes = "模板名称")
    @ExcelResolve(titleName = "模板名称")
    private String templateName;
    /**
     * 表名称
     */
    @ApiModelProperty(notes = "表名称")
    @ExcelResolve(titleName = "表名称")
    private String tableName;

    /**
     *系统名称
     */
    @ApiModelProperty(notes = "系统名称")
    @ExcelResolve(titleName = "系统名称")
    private Integer systemNameCode;

    /**
     *系统名称--对应枚举表
     */
    @ApiModelProperty(notes = "系统名称--枚举表")
    @ExcelResolve(titleName = "系统名称--枚举表")
    private String systemName;

    /**
     * 系统分类
     */
    @ApiModelProperty(notes = "系统分类")
    @ExcelResolve(titleName = "系统分类")
    private Integer firstTypeCode;

    /**
     * 系统分类--对应枚举表
     */
    @ApiModelProperty(notes = "系统分类--枚举表")
    @ExcelResolve(titleName = "系统分类--枚举表")
    private String firstType;

    /**
     * 子分类
     */
    @ApiModelProperty(notes = "子分类")
    @ExcelResolve(titleName = "子分类")
    private Integer secondTypeCode;

    /**
     * 子分类--对应枚举表
     */
    @ApiModelProperty(notes = "子分类--枚举表")
    @ExcelResolve(titleName = "子分类--枚举表")
    private String secondType;

    /**
     * 有效数据量
     */
    @ApiModelProperty(notes = "有效数据量")
    @ExcelResolve(titleName = "有效数据量")
    private Integer effectiveNum;

}
