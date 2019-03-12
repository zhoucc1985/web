package com.cloud.controller.datacollect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.utils.CommonUtils;
import com.cloud.common.vo.datacollect.CollectBatchSearchVo;
import com.cloud.entity.datacollect.CollectRule;
import com.cloud.entity.datacollect.CollectTemplate;
import com.cloud.mapper.master.datacollect.CollectRuleMapper;
import com.cloud.mapper.master.datacollect.CollectTemplateMapper;
import com.github.pagehelper.PageInfo;

/**
 * 规则配置
 * 
 * @author TangJun
 * @date 2019年2月14日 下午4:16:30
 */

@Api(tags="校验规则配置",description = "校验规则配置")
@RestController
@RequestMapping(value = "/ruleSetting")
public class RuleSettingController {

	 private static final Logger logger = LoggerFactory.getLogger(RuleSettingController.class);
	 
	 @Autowired
	 private CollectRuleMapper collectRuleMapper;
	 
//	校验规则配置-校验规则详情
//	findCollectRule
	
	/**
     * 校验规则详情
     * @param collectBatchSearchVo 查询信息 包含分页信息
     * @author TangJun
     */
    @ApiOperation(value="校验规则字段详情",notes = "规则配置查询模板校验规则字段详情")
    @RequestMapping(value = "/findColDetail",method = RequestMethod.POST)
    public Map<String,Object> findColDetail(int page, int pageSize ,String templateName){
        Map<String,Object> resultMap = new HashMap<>();
        try{
        	List<CollectRule>  tempList = collectRuleMapper.findColDetail(page ,pageSize ,templateName);
        	PageInfo<List<Map<String,Object>>> pageInfo = new PageInfo(tempList);
            pageInfo.setPageNum(page);
            pageInfo.setPageSize(pageSize);
            pageInfo.setTotal(Long.parseLong(String.valueOf(collectRuleMapper.findColDetailTotal(templateName))));
            return CommonUtils.getSucResultMap(pageInfo);
        }catch(Exception e){
            logger.error("校验规则字段详情" + e);
            resultMap = CommonUtils.getErrorResultMap("校验规则字段详情失败"+e.getMessage());
            e.printStackTrace();
        }

        return resultMap;
    }
    
    /**
     * 校验规则编辑
     * @param CollectRule
     * @author TangJun
     */
    @ApiOperation(value="校验规则编辑",notes = "校验规则编辑")
    @RequestMapping(value = "/updateCollectRule",method = RequestMethod.POST)
    public Map<String,Object> updateCollectRule(CollectRule rule){
        Map<String,Object> resultMap = new HashMap<>();
        try{
        	collectRuleMapper.updateCollectRule(rule);
        }catch(Exception e){
            logger.error("校验规则编辑出错:" + e);
            resultMap = CommonUtils.getErrorResultMap("校验规则编辑失败"+e.getMessage());
            e.printStackTrace();
            return CommonUtils.getErrorResultMap();
        }

        return CommonUtils.getSucResultMap();
    }
    
    /**
     * 校验规则字段删除
     * @param columnid
     * @author TangJun
     */
    @ApiOperation(value="校验规则删除",notes = "校验规则删除")
    @RequestMapping(value = "/deleteCollectRule",method = RequestMethod.POST)
    public Map<String,Object> deleteCollectRule(String columnId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
        	collectRuleMapper.deleteCollectRule(columnId);
        }catch(Exception e){
            logger.error("导入批次列表查询出错:" + e);
            resultMap = CommonUtils.getErrorResultMap("查询模板结果列表失败"+e.getMessage());
            e.printStackTrace();
            return CommonUtils.getErrorResultMap();
        }

        return CommonUtils.getSucResultMap();
    }
}
