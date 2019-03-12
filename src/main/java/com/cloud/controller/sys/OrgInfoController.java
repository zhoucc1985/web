package com.cloud.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.CommonUtils;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.sys.org.OrgDetailVo;
import com.cloud.common.vo.sys.org.OrgInfoTreeVo;
import com.cloud.entity.sys.OrgInfo;
import com.cloud.service.sys.OrgInfoService;

/**
 * 组织机构 Controller
 *
 * @author huangYl
 * @date 2018/11/9 17:15
 **/
@Api(tags = "组织机构", description = "组织机构接口")
@RestController
@RequestMapping(value = "/api/organization")
public class OrgInfoController {

    private static final Logger logger = LoggerFactory.getLogger(OrgInfoController.class);

    @Autowired
    private OrgInfoService orgInfoService;

    /**
     * 查询当前登录用户的组织机构树(含上级和下级)
     *
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/9 17:22
     */
    @ApiOperation(value = "查询当前登录用户的组织机构树(含上级和下级)")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> getUserOrganization() {
        try {
            List<OrgInfoTreeVo> orgInfoList = orgInfoService.getUserOrganizationTree();
            Map<String, Object> resultMap = CommonUtils.getSucResultMap();
            resultMap.put("datas", orgInfoList);
            return resultMap;
        } catch (BusinessException e) {
            logger.error("获取用户组织机构出错", e);
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取用户组织机构出错", e);
            return CommonUtils.getErrorResultMap();
        }
    }

    /**
     * 查询当前用户的机构（学校）信息
     *
     * @return 组织机构(学校)信息
     */
    @ApiOperation(value = "根据组织id获得组织机构（学校）全部信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Map<String, Object> findNowUserSchoolInfo() {
    	try {
    		  ShiroUser currentlyUserInfo = UserUtils.getInstance().getCurrentlyUserInfo();
    		  Long orgId=null;
    		  //社团用户   查看到的机构信息是学校的
    		  if(CommonDict.ORG_TYPE_DEPARTMENT.equals(currentlyUserInfo.getOrgType())){
    			  orgId=currentlyUserInfo.getSchoolOrgId();
    		  }else{
    			  orgId=currentlyUserInfo.getOrgId();
    		  }
    		  OrgInfo schoolInfo = orgInfoService.getById(orgId);
    	      return CommonUtils.getSucResultMap(schoolInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询学校信息失败");
			return CommonUtils.getErrorResultMap("查询学校信息失败");
		}
    }

    /**
    * 查询当前登录用户组织机构树信息(只含下级)
    * @param
    * @return com.cloud.entity.sys.OrgInfo
    * @author huangYl
    * @date   2018/11/16 16:05
    */
    @ApiOperation(value = "查询当前登录用户组织机构树信息(只含下级)")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, Object> getNowUserOrg() {
        try {
            List<OrgInfoTreeVo> orgInfoTreeVos = orgInfoService.getUserOrganization();
            Map<String, Object> resultMap = CommonUtils.getSucResultMap();
            resultMap.put("datas", orgInfoTreeVos);
            return resultMap;
        } catch (BusinessException e) {
            logger.error("查询当前登录用户组织机构树信息出错", e);
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询当前登录用户组织机构树信息出错", e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 根据ID获取组织的以及上级机构的基础信息
     *
     * @param id 组织机构 id
     * @return java.util.Map                                                              ,                                                               java.lang.Object>
     * @author huangYl
     * @date 2018/11/12 15:43
     */
    @ApiOperation(value = "根据ID获取组织的以及上级机构的基础信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map<String, Object> getCurrentAndParentOrg(@PathVariable(value = "id") Long id) {
        try {
            OrgDetailVo result = orgInfoService.getCurrentAndParentOrg(id);
            Map<String, Object> resultMap = CommonUtils.getSucResultMap();
            resultMap.put("datas", result);
            return resultMap;
        } catch (BusinessException e) {
            logger.error("获取组织机构基础信息出错，机构id：" + id, e);
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取组织机构基础信息出错，机构id：" + id, e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 新增组织机构
     *
     * @param orgInfo 组织机构对象
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/14 10:04
     */
    @ApiOperation(value = "新增组织机构")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> addOrganization(@RequestBody OrgInfo orgInfo) {
        try {
            orgInfoService.addOrganization(orgInfo);
            return CommonUtils.getSucResultMap();
        } catch (BusinessException e) {
            logger.error("新增组织机构出错", e);
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增组织机构出错", e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 根据ID获取同级的全部组织机构
     *
     * @param id 组织机构 id
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/12 15:43
     */
    @ApiOperation(value = "根据ID获取同级的全部组织机构")
    @RequestMapping(value = "/sameParent/{id}", method = RequestMethod.GET)
    public Map<String, Object> getSameParentOrg(@PathVariable(value = "id") Long id) {
        try {
            List<OrgInfo> list = orgInfoService.getSameParentOrg(id);
            Map<String, Object> resultMap = CommonUtils.getSucResultMap();
            resultMap.put("datas", list);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据ID获取同级的全部组织机构出错,机构id = ", id, e.getMessage());
            return CommonUtils.getErrorResultMap();
        }
    }

    /**
     * 根据ID编辑组织机构
     *
     * @param id      组织机构id
     * @param orgInfo 组织机构对象
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/14 16:41
     */
    @ApiOperation(value = "根据ID编辑组织机构")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Map<String, Object> updateOrganization(@PathVariable("id") Long id, @RequestBody OrgInfo orgInfo) {
        try {
            orgInfoService.updateOrganization(orgInfo);
            return CommonUtils.getSucResultMap();
        } catch (BusinessException e) {
            logger.error("编辑组织机构出错，id =" + id, e);
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑组织机构出错，id =" + id, e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 根据ID删除组织机构
     *
     * @param id 组织机构id
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/14 16:44
     */
    @ApiOperation(value = "根据ID删除组织机构")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteOrganization(@PathVariable("id") Long id) {
        try {
            orgInfoService.deleteOrganization(id);
            return CommonUtils.getSucResultMap();
        } catch (BusinessException e) {
            logger.error("删除组织机构出错，id =" + id, e);
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
            logger.error("删除组织机构出错，id =" + id, e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
	 * 查询当前用户的机构树（用户管理里）
	 * @param
	 * @author yuxin
	 * @date 2018年11月17日 下午1:35:39 
	 * @return
	 */
    @ApiOperation(value = "查询当前用户的机构树（用户管理里）")
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public Map<String, Object> getOrgTree(){
		try {
			List<OrgInfoTreeVo> userOrganization = orgInfoService.getUserOrg();
			return CommonUtils.getSucResultMap(userOrganization);
		} catch (BusinessException e) {
            logger.error("获取机构信息异常");
            return CommonUtils.getWarnResultMap(e.getMessage());
        } catch (Exception e) {
			e.printStackTrace();
			logger.error("获取机构信息异常");
			return CommonUtils.getErrorResultMap();
		}
	}
	
	/**
	 * 编辑学校信息
	 * @param schoolJson 学校信息
     * @param file 学校logo
     * @param uploadLogo 是否上传logo,不对用户显示，当用户不更改图片时，视为不上传
	 * @author yuxin
	 * @date 2018年11月20日 上午11:37:49
     * @update Pan Jianneng
     * @updatedesc 添加上传学校logo
	 * @return
	 */
    @ApiOperation(value = "编辑学校信息")
	@RequestMapping(value="/school",method=RequestMethod.PUT)
	public Map<String,Object> updateSchool(@RequestParam(value = "school") String schoolJson,
                                           @RequestParam(value = "file",required = false) MultipartFile file,
                                           @RequestParam(value = "uploadLogo") Boolean uploadLogo){
		try {
		    OrgInfo orgInfo = CommonUtils.getObjectByJsonStr(schoolJson,OrgInfo.class,"yyyy-MM-dd");
            if(uploadLogo&&ObjectUtils.isEmptyString(file.getOriginalFilename())&& ObjectUtils.isNullObject(orgInfo.getSchoolLogoPath())){
                throw new BusinessException("请选择上传文件！");
            }
            orgInfoService.updateSchool(file,orgInfo,uploadLogo);
			return CommonUtils.getSucResultMap("编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("编辑学校信息异常："+e.getLocalizedMessage());
			return CommonUtils.getErrorResultMap("编辑失败："+e.getLocalizedMessage());
		}
	}

    /**
     * 检查当前登录用户所属学校信息是否完善
     * @author Pan Jianneng
     * @date 2018/11/26 11:59 AM
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
	@ApiOperation(value = "检查学校信息是否完善",notes = "检查学校信息是否已经完善")
    @RequestMapping(value = "/school/info-is-complete/",method = RequestMethod.GET)
	public Map<String,Object> schoolInfoIsComplete(){
        return CommonUtils.getSucResultMap(orgInfoService.schoolInfoIsComplete());
    }


    /**
     * 系统根据组织机构生成默认用户和角色
     *
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/28 10:57
     */
    @ApiOperation(value = "系统根据组织机构生成默认用户和角色")
    @RequestMapping(value = "/create/default",method = RequestMethod.POST)
    public Map<String,Object> createDefaultUserAndRole(){
        try {
            orgInfoService.createDefaultUserAndRole();
            return CommonUtils.getSucResultMap("系统根据组织机构生成默认用户和角色成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return CommonUtils.getErrorResultMap("系统根据组织机构生成默认用户和角色失败");
        }
    }
    
}


