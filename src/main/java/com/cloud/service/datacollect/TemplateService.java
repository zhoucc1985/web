package com.cloud.service.datacollect;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloud.common.utils.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

public interface TemplateService {

    /**
     * 模板分页查询
     *
     * @param firstTypeCode
     * @param secondTypeCode
     * @param templateName
     * @param page
     * @param pageSize
     * @return
     * @author TangJun
     * @date 2019年1月15日 下午2:04:22
     */
    List<Map<String, Object>> findTemplate(String firstTypeCode , String secondTypeCode,
                                           String templateName, String page, String pageSize);

    /**
     * 模板分页查询count
     *
     * @param firstTypeCode
     * @param secondTypeCode
     * @param template_name
     * @param page
     * @param pageSize
     * @return
     * @author TangJun
     * @date 2019年1月15日 下午2:04:22
     */
    List<Map<String, Object>> findTemplateCount(String firstTypeCode , String secondTypeCode,
                                                String template_name, String page, String pageSize);


    /**
     * 根据模板id查询表名称和临时表名称
     *
     * @param templateId
     * @return
     * @author zengqh
     * @date 2019年1月17日 下午5:48:27
     */
    List<Map<String, Object>> findTableNamesByTemplateId(Integer templateId);

    /**
     * 根据批次编号查询模板表名称和临时表名称
     *
     * @param batchId 批次编号
     * @return Map[ tempTabeleName:value,realTempName:value]
     * @author zengqh
     * @date 2019年1月17日 下午5:48:27
     */
    List<Map<String, Object>> findTableNamesByBatchId(String batchId);


    /**
     * 查询模板数据
     *
     * @param tempName 模板名称
     * @return
     * @author TangJun
     * @date 2019年1月17日 下午6:18:22
     */
    List<Map<String, Object>> findTemplateData(String tempName);
    /**
     * 模板预览
     *
     * @param templateId 模板编号
     * @return
     * @author TangJun
     * @date 2019年1月17日 下午6:18:22
     */
    Map<String,Object> viewTemplateDataByTemplateId(Integer templateId) throws Exception;

    /**
     * 下载单个模板
     * @param request 请求对象
     * @param response 响应对象
     * @param templateId 模板编号
     * @throws Exception
     */
    void downTemplate(HttpServletRequest request, HttpServletResponse response,
                      Integer templateId) throws Exception;
    
    
}
