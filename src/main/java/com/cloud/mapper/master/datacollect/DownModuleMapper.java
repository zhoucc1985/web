package com.cloud.mapper.master.datacollect;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: chengt@sunmnet.com
 * @Date: created on 16:58 2019/1/11
 * @Description: 下载模板
 */
public interface DownModuleMapper {

    /**
     * 查询模板结果列表
     * @param system 系统 (全部、学工、教务、一卡通、其他)
     * @param type 类别 (全部、学生、教师、课程、成绩、其他)
     * @param keyWord 搜索关键词
     * @return
     */
    List<Map<String,Object>> findTemplateDataCheckReport(@Param("system") String system,
                                                        @Param("type") String type,
                                                        @Param("keyWord") String keyWord);

    /**
     * 预览模板
     * @param id 模板Id
     * @return
     */
    List findViewDataById(@Param("id") String id);


    /**
     * 下载模板
     * @param system 系统 (全部、学工、教务、一卡通、其他)
     * @param type 类别 (全部、学生、教师、课程、成绩、其他)
     * @param keyWord 搜索关键词
     * @param id 模板I
     * @param oom 单选还是多选 1.单选 2.多选
     * @return
     */
    List findDownModule(@Param("system") String system,
                          @Param("type") String type,
                          @Param("keyWord") String keyWord,
                          @Param("oom") Integer oom,
                          @Param("id") String id);

}
