package com.cloud.service.datacollect;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 10:50 2019/1/20
 * @Description:
 */
public interface ExcelService {
    /**
     * 导入Excel数据到数据库临时表
     * 1.上传文件   2.读取文件数据内容入库
     * @param file 选择文件
     * @param templateId 模板ID
     * @return
     */
    Map<String,Object> importExeclData(MultipartFile file, Integer templateId) throws Exception;

    void updateCollectImport(Integer templateId, String batchId);

    /**
     * 继续导入 1.新增Excel数据 2.导入修改后的Excel数据
     * @param file
     * @param batchId
     * @return
     */
    Map<String,Object> continueImportData(MultipartFile file, String batchId) throws Exception;

}
