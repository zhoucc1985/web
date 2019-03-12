package com.cloud.service.datacollect.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloud.common.dict.datacollect.SystemDict;
import com.cloud.common.utils.DownLoadUtil;
import com.cloud.common.utils.MyExcelUtil;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.utils.WebUtils;
import com.cloud.entity.datacollect.CollectEnum;
import com.cloud.entity.datacollect.CollectTemplate;
import com.cloud.mapper.master.datacollect.CollectTemplateMapper;
import com.cloud.service.datacollect.CollectEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloud.mapper.master.datacollect.TemplateMapper;
import com.cloud.service.datacollect.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
    /**
     * 模板下载根目录
     */
    @Value("${filepath.downloadbashpath}")
    public String downLoadBasePath;
    @Autowired
    private TemplateMapper mapper;
    @Autowired
    private CollectTemplateMapper collectTemplateMapper;
    @Autowired
    private CollectEnumService collectEnumService;


    @Override
    public List<Map<String, Object>> findTemplate(String firstTypeCode,
                                                  String secondTypeCode, String template_name, String page,
                                                  String pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstTypeCode", firstTypeCode);
        params.put("secondTypeCode", secondTypeCode);
        params.put("template_name", template_name);
        params.put("page", page);
        params.put("pageSize", pageSize);
        params.put("llimit", (Integer.valueOf(page) - 1) * Integer.valueOf(pageSize));
        params.put("rlimit", pageSize);
        //权限控制加入orgid
        String orgId = UserUtils.getInstance().getFindDataOrgCode();
        params.put("orgId",orgId);
        List<Map<String, Object>> list=mapper.findTemplate(params);
        Map<String,Map<String,Object>> enumsMap=collectEnumService.findEnumData(SystemDict.systemType);
        Map<String,Object>   firstValueEnumsMap = enumsMap.get("firstValueEnums");
        Map<String,Object>   secondValueEnumsMap = enumsMap.get("secondValueEnums");
        for(Map<String, Object> map:list){
            String firstTypeCodeStr= WebUtils.getString(map.get("first_type_code"));
            if(firstValueEnumsMap.containsKey(firstTypeCodeStr)){
                map.put("first_type_code",firstValueEnumsMap.get(firstTypeCodeStr));
            }
            String secondTypeCodeStr= WebUtils.getString(map.get("second_type_code"));
            if(secondValueEnumsMap.containsKey(secondTypeCodeStr)){
                map.put("second_type_code",secondValueEnumsMap.get(secondTypeCodeStr));
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findTemplateCount(String firstTypeCode, String secondTypeCode,
                                                       String template_name, String page, String pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("firstTypeCode", firstTypeCode);
        params.put("secondTypeCode", secondTypeCode);
        params.put("template_name", template_name);
        params.put("page", page);
        params.put("pageSize", pageSize);
        params.put("llimit", (Integer.valueOf(page) - 1) * Integer.valueOf(pageSize));
        params.put("rlimit", pageSize);
        //权限控制加入orgid
        String orgId = UserUtils.getInstance().getFindDataOrgCode();
        params.put("orgId" , orgId);
        return mapper.findTemplateCount(params);
    }

    @Override
    public List<Map<String, Object>> findTableNamesByTemplateId(Integer templateId) {
        return mapper.findTableNamesByTemplateId(templateId);
    }

    @Override
    public List<Map<String, Object>> findTableNamesByBatchId(String batchId) {
        return mapper.findTableNamesByBatchId(batchId);
    }

    @Override
    public List<Map<String, Object>> findTemplateData(String tempName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tempTableName", tempName);
        return mapper.findTemplateData(params);
    }

    @Override
    public  Map<String,Object> viewTemplateDataByTemplateId(Integer templateId) throws Exception {
        CollectTemplate collectTemplate=collectTemplateMapper.selectByPrimaryKey(templateId);
        String filePath=downLoadBasePath+collectTemplate.getTemplatePath();
        Map<String,Object> map=  MyExcelUtil.getTitleAndDatasFromFile(filePath);
        return map;
    }

    @Override
    public void downTemplate(HttpServletRequest request, HttpServletResponse response, Integer templateId) throws Exception {
        CollectTemplate collectTemplate = collectTemplateMapper.selectByPrimaryKey(templateId);
        String filePath = collectTemplate.getTemplatePath();
        filePath = downLoadBasePath + filePath;
        DownLoadUtil.downLoadExcel(request, response, filePath);
    }


}
