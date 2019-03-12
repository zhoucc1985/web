package com.cloud.service.datacollect;


import com.cloud.mapper.master.datacollect.DownModuleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 16:52 2019/1/11
 * @Description: 数据采集业务处理类
 */

@Service
public class DownModuleService {

    @Resource
    private DownModuleMapper downModuleMapper;

    /**
     * 模板结果列表
     * @param system 系统 (全部、学工、教务、一卡通、其他)
     * @param type 类别 (全部、学生、教师、课程、成绩、其他)
     * @param keyWord 搜索关键词
     * @return
     */
    public List<Map<String,Object>> showTemplateDataCheckReport(String system,String type,String keyWord){

        List<Map<String,Object>> list = downModuleMapper.findTemplateDataCheckReport(system,type,keyWord);

        return list;
    }

    /**
     * 预览模板
     * @param id 模板Id
     * @return
     */
    public Map<String,Object> viewDataById(String id){

        List<Map<String,Object>> list = downModuleMapper.findViewDataById(id);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        return resultMap;
    }

    /**
     * 下载模板
     * @param system 系统 (全部、学工、教务、一卡通、其他)
     * @param type 类别 (全部、学生、教师、课程、成绩、其他)
     * @param keyWord 搜索关键词
     * @param id 模板I
     * @param oom 单选还是多选 1.单选 2.多选
     * @return
     */
    public Map<String,Object> downModule(String system,String type,String keyWord,Integer oom,String id){

        List<Map<String,Object>> list = downModuleMapper.findDownModule(system,type,keyWord,oom,id);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("list",list);
        return resultMap;
    }

}
