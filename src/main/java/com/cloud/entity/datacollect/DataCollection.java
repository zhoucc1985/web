package com.cloud.entity.datacollect;

import com.cloud.common.vo.ExcelResolve;
import lombok.Data;

/**
 * 添加文件描述
 *
 * @author Pan jianneng
 * @time 2018/10/21 16:12
 */
@Data
public class DataCollection {
    private Long id;
    @ExcelResolve(titleName = "中文名称")
    private String name;
    @ExcelResolve(titleName = "身份证")
    private String identification;
    @ExcelResolve(titleName = "是否异常",isImport = false)
    private String errorStr = "否";
    @ExcelResolve(titleName = "异常信息",isImport = false)
    private String errorMsg = "";
}
