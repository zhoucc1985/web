package com.cloud.controller.datacollect;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.DownLoadUtil;
import com.cloud.common.utils.DownloadZip;
import com.cloud.common.utils.PageResult;
import com.cloud.entity.datacollect.CollectTemplate;
import com.cloud.entity.vo.dataCollect.DetailsListVo;
import com.cloud.mapper.master.datacollect.CollectTemplateMapper;
import com.cloud.service.datacollect.TemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 模板管理
 * @Author: chengt@sunmnet.com
 * @Date: created on 15:35 2019/1/11
 * @Description:
 */

@Api(tags = "数据采集反馈3", description = "模板管理-TemplateController")
@RestController
@RequestMapping(value = "/datacollect/template")
public class TemplateController {

    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    @Resource
    private TemplateService templateService;
    
    @Autowired
    private CollectTemplateMapper collectTemplateMapper;    
    
    @Value("${filepath.downloadbashpath}")
    public String downLoadBasePath;
    
  

    /**
     * 查询模板列表（模板列表展示）
     * @return
     */
    @ApiOperation(value = "查询模板列表",notes="点击导入模板之后展示已有的模板列表")
    @RequestMapping(value = "/showTemplateList",method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "firstTypeCode",value = "系统",required = false ,paramType = "query" ,dataType = "String") ,
    					@ApiImplicitParam(name = "secondTypeCode", value = "类别", required = false, paramType = "query" , dataType = "String"),
    					@ApiImplicitParam(name = "template_name", value = "模板名称", required = false, paramType = "query" ,dataType = "String"),
    					@ApiImplicitParam(name = "page", value = "页数", required = false, paramType = "query" ,dataType = "int"),
    					@ApiImplicitParam(name = "pageSize", value = "页数据", required = false, paramType = "query" ,dataType = "int")})
    public Map<String,Object> showTemplateList(@RequestParam(value = "firstTypeCode",required = false) String firstTypeCode,
    								   @RequestParam(value = "secondTypeCode", required = false ) String secondTypeCode ,
    								   @RequestParam(value = "template_name", required = false ) String template_name ,
    								   @RequestParam(value = "page", required = false ) String page ,
    								   @RequestParam(value = "pageSize", required = false ) String pageSize){
        PageResult pageResult = new PageResult();
        try {
        	List<Map<String ,Object>> templateRow = templateService.findTemplate(firstTypeCode , secondTypeCode , template_name , page , pageSize);
        	List<Map<String ,Object>> templateRowCount = templateService.findTemplateCount(firstTypeCode , secondTypeCode, template_name, page, pageSize);
        	long total = Long.valueOf(String.valueOf( templateRowCount.get(0).get("num")));
        	pageResult.setTotal(total);
        	pageResult.setRows(templateRow);
        	pageResult.setTotalPage(total / Long.valueOf(pageSize));
        	pageResult.setPage(Integer.valueOf(page));
        	pageResult.setPageSize(Integer.valueOf(pageSize));
            return CommonUtils.getSucResultMap(pageResult);
        }catch (Exception e){
            logger.error("查询模板列表信息出错了:"+e);
            return  CommonUtils.getErrorResultMap("查询模板列表失败：");
        }
    }

    /**
     * 预览模板
     * @param templateId 模板Id
     */
    @ApiOperation(value = "预览模板-zengqh-完成", notes = "在查看模板结果列表，然后点击某个模板的预览按钮 查看数据")
    @RequestMapping(value = "/viewTemplateDataByTemplateId", method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "templateId",value = "模板id",required = true ,paramType = "query" ,dataType = "String") })
    public Map<String,Object> viewTemplateDataByTemplateId(@RequestParam("templateId") Integer templateId) {
        try {
           Map<String, Object> resultMap= templateService.viewTemplateDataByTemplateId(templateId);
          return   CommonUtils.getSucResultMap(resultMap);
        } catch (Exception e) {
            logger.error("预览模板出错了:" + e);
            return CommonUtils.getErrorResultMap("预览模板出错了:" + e);
        }
    }

    /**
     * 下载单个模板
     * @param templateId 文件路径
     * @return
     */
    @ApiOperation(value = "下载单个模板-罗紫琪-对接完成", notes = "查看模板结果列表，然后点击下载某个模板")
    @RequestMapping(value = "/downTemplate", method = RequestMethod.GET)
    public  Map<String,Object>  downTemplate(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam("templateId") Integer templateId) throws Exception {
    	try {
            templateService.downTemplate(request,response,templateId);
            return CommonUtils.getSucResultMap();
		} catch (Exception e) {
            logger.error("下载单个模板出错了:" + e.getMessage());
            return CommonUtils.getErrorResultMap("下载单个模板出错了："+e.getMessage());
        }
    }

    /**
     * 删除导入的数据
     * @param id
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "删除导入的数据-未开始", notes = "删除导入的数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "模板id",required = true ,paramType = "query" ,dataType = "String") })
    @RequestMapping(value = "/deleteTemplateData", method = RequestMethod.POST)
    public Map<String, Object> deleteTemplateData(@RequestParam("id") String id) {
        try {
            //TODO  实现删除操作
            return  CommonUtils.getSucResultMap();
        } catch (Exception e) {
            logger.error("下载模板出错:" + e);
            return CommonUtils.getWarnResultMap("删除导入的数据失败：" + e.getMessage());
        }

    }

    /**
     * 批量下载模板
     * @param ids 模板Id列表
     * @return
     */
    @ApiOperation(value = "批量下载模板-唐军-完成", notes = "查看模板结果列表，然后点击一键下载,用压缩包形式批量下载")
    @RequestMapping(value = "/downTemplates", method = RequestMethod.GET)
    public Map<String,Object> downTemplates(HttpServletResponse response, @RequestParam(value="ids") String ids) {
      	String[] idsStr = ids.split(",");
      	List<Integer> idsList = new ArrayList<Integer>();
      	List<CollectTemplate> collectList = new ArrayList<CollectTemplate>();
      	List<String> fileurl = new ArrayList<String>();
      	int idsc=0;
      	System.out.println(downLoadBasePath);
      	for(String str : idsStr){
      		idsList.add(Integer.valueOf(str));
      		idsc++;
      		if(idsc == 1000){
      			List<CollectTemplate>  tempList = collectTemplateMapper.selectByTemplateId(idsList);
      			for(CollectTemplate clt: tempList){
      				fileurl.add(clt.getTemplatePath());
      				collectList.add(clt);
      			}
      			idsc=0;
      			idsList.clear();
      		}
      	}
      	
      	if(idsc>0){
      		List<CollectTemplate> tempList = collectTemplateMapper.selectByTemplateId(idsList);
  			for(CollectTemplate clt : tempList){
  				fileurl.add(clt.getTemplatePath());
  				collectList.add(clt);
  			}
      	}
      	List files = new ArrayList();
      	for(String urlstr : fileurl){
      		File file = new File(downLoadBasePath+urlstr);
      		if(file.exists()){
      			files.add(file);
      		}
      	}
      	if(files.size()==0){
      		return CommonUtils.getErrorResultMap("文件不存在");
      	}
  		DownloadZip dzip = new DownloadZip();
  		try {
              // TODO  批量下载 待实现
  			 dzip.downLoadFiles(files, response);
  		} catch (Exception e) {
  			e.printStackTrace();
  			return CommonUtils.getErrorResultMap("下载模板出错："+e.getMessage());
  		}
  		return null;
      }


}
