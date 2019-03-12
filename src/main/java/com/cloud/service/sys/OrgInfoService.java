package com.cloud.service.sys;

import static java.util.Comparator.comparingLong;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.excption.BusinessException;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.utils.UserUtils;
import com.cloud.common.vo.ShiroUser;
import com.cloud.common.vo.sys.org.OrgDetailVo;
import com.cloud.common.vo.sys.org.OrgInfoTreeVo;
import com.cloud.entity.sys.OrgInfo;
import com.cloud.entity.sys.SysUser;
import com.cloud.mapper.master.sys.OrgInfoMapper;
import com.google.common.collect.Lists;

/**
 * 组织机构 service
 *
 * @author huangYl
 * @date 2018/11/9 17:15
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class OrgInfoService {

    private static final Logger logger = LoggerFactory.getLogger(OrgInfoService.class);

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Autowired
    private SysUserService userService;

    /**
     * 学校LOGO图片地址
     */
    @Value("${org_school_logo_file_path}")
    private String schoolLogo;

    /**
     * 根据id获取对象(连表)
     *
     * @param id 组织机构id
     * @return com.cloud.entity.sys.OrgInfo
     * @author huangYl
     * @date 2018/11/12 18:28
     */
    public OrgInfo getById(Long id) {
        return orgInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id获取对象(沒有连表)
     *
     * @param id 组织机构id
     * @return com.cloud.entity.sys.OrgInfo
     * @author huangYl
     * @date 2018/11/26
     */
    public OrgInfo getByOrgId(Long id) {
        return orgInfoMapper.selectById(id);
    }


    /**
     * 根据code查询
     *
     * @param code 组织机构编码
     * @return com.cloud.entity.sys.OrgInfo
     * @author huangYl
     * @date 2018/11/20 17:08
     */
    public OrgInfo getByCode(String code) {
        return orgInfoMapper.selectByCode(code);
    }

    /**
     * 获取当前登录用户的组织机构树（只有当前机构以及下级机构）
     *
     * @return java.util.List<com.cloud.entity.sys.OrgInfo>
     * @author huangYl
     * @date 2018/11/9 17:30
     */
    public List<OrgInfoTreeVo> getUserOrganization() {
        // 获取当前登录用户的组织机构id
        Long orgId = UserUtils.getInstance().getCurrentlyUserInfo().getOrgId();
        if (null != orgId) {
            OrgInfo orgInfo = orgInfoMapper.selectById(orgId);
            List<OrgInfoTreeVo> orgInfoList = orgInfoMapper.getUserOrganization(orgInfo.getCode());
            if (null == orgInfoList || orgInfoList.size() == 0) {
                return Lists.newArrayList();
            }
            //这里需要生成树结构
            List<OrgInfoTreeVo> orgInfoTreeVos = recursionCreateTree(orgInfoList);
            return orgInfoTreeVos;
        }
        throw new BusinessException("登录角色没有任何组织机构");
    }

    /**
     * 获取当前登录用户的组织机构树（含有上级机构以及下级机构）
     *
     * @return java.util.List<com.cloud.common.vo.sys.org.OrgInfoTreeVo>
     * @author huangYl
     * @date 2018/11/12 10:38
     */
    public List<OrgInfoTreeVo> getUserOrganizationTree() {
        List<OrgInfoTreeVo> userOrganization = getUserOrganization();
        OrgInfoTreeVo currentOrg = userOrganization.get(0);
        currentOrg.setStatus(OrgInfoTreeVo.NODE_CURRENT_TYPE);
        OrgInfoTreeVo treeVo = recursionCreateTreeOnParent(currentOrg);
        return Lists.newArrayList(treeVo);
    }


    /**
     * 根据ID获取组织的以及上级机构的基础信息
     *
     * @param id 组织机构 id
     * @return java.util.Map
     * @author huangYl
     * @date 2018/11/12 16:10
     */
    public OrgDetailVo getCurrentAndParentOrg(Long id) {
        OrgDetailVo orgDetailVo = orgInfoMapper.getCurrentAndParentOrg(id);
        if (ObjectUtils.isEmptyObject(orgDetailVo)) {
            throw new BusinessException("获取机构基础信息出错");
        }
        //获取登录用户的机构权限
        String orgCode = UserUtils.getInstance().getCurrentlyUserInfo().getOrgCode();
        int loginCount = checkCodeWithD(orgCode);
        //获取当前节点的机构权限
        OrgInfo orgInfo = orgInfoMapper.selectById(id);
        String currentCode = orgInfo.getCode();
        //判断权限
        int currentCount = checkCodeWithD(currentCode);
        if (currentCount - loginCount > 1) {
            OrgInfo parent = orgInfoMapper.selectByParentNumber(orgInfo.getParentNumber());
            List<OrgInfo> orgList = orgInfoMapper.selectChildrenByParentCode(parent.getParentCode());
            orgDetailVo.setList(orgList);
        }
        return orgDetailVo;
    }

    /**
     * 根据ID获取组织的以及上级机构的基础信息
     *
     * @param id 组织机构 id
     * @return java.util.Map
     * @date 2018/11/24
     */
    public OrgDetailVo getCurrentAndParentOrgDepart(Long id) {
        OrgDetailVo orgDetailVo = orgInfoMapper.getCurrentAndParentOrg(id);
        if (ObjectUtils.isEmptyObject(orgDetailVo)) {
            throw new BusinessException("获取机构基础信息出错");
        }
        return orgDetailVo;
    }

    /**
     * 新增组织机构
     *
     * @param orgInfo 组织机构对象
     * @author huangYl
     * @date 2018/11/12 16:50
     */
    public void addOrganization(OrgInfo orgInfo) {
        if (ObjectUtils.isEmptyString(orgInfo.getName())) {
            logger.error("缺少机构名称参数：name");
            throw new BusinessException("缺少机构名称");
        }
        if (ObjectUtils.isEmptyString(orgInfo.getParentNumber())) {
            logger.error("缺少父机构号参数：parentNumber");
            throw new BusinessException("缺少父机构号");
        }
        // 判断当前层级是否存在同名的机构
        OrgInfo checkOrgInfo = orgInfoMapper.selectByParentNumberAndName(orgInfo.getParentNumber(), orgInfo.getName());
        if (ObjectUtils.isNotEmptyObject(checkOrgInfo)) {
            throw new BusinessException("该机构名称在同级中已经存在，请重新输入");
        }
        // 根据规则生成机构号number
        String newNumber = createNewNumber();
        orgInfo.setNumber(newNumber);
        //获取父组织机构的类型
        OrgInfo parent = orgInfoMapper.selectByParentNumber(orgInfo.getParentNumber());
        //根据类型判断
        switch (parent.getType()) {
            case CommonDict.ORG_TYPE_EDUCATION:
                orgInfo.setType(CommonDict.ORG_TYPE_PROVINCE);
                break;
            case CommonDict.ORG_TYPE_PROVINCE:
                orgInfo.setType(CommonDict.ORG_TYPE_SCHOOL);
                break;
            case CommonDict.ORG_TYPE_SCHOOL:
                orgInfo.setType(CommonDict.ORG_TYPE_DEPARTMENT);
                break;
            case CommonDict.ORG_TYPE_DEPARTMENT:
                orgInfo.setType(CommonDict.ORG_TYPE_DEPARTMENT);
            default:
                break;
        }
        // 生成新的组织机构编码code
        orgInfo.setParentCode(parent.getCode());
        String newCode = createNewCode(parent.getCode());
        orgInfo.setCode(newCode);
        orgInfoMapper.insertSelective(orgInfo);
        // 学校以上的机构级别创建新的管理员
        if (!orgInfo.getType().equals(CommonDict.ORG_TYPE_DEPARTMENT)) {
            userService.addDefaultRoleAndAdmin(orgInfo.getNumber(), orgInfo.getId(),orgInfo.getName());
        }
    }

    /**
     * 根据ID获取同级的全部组织机构
     *
     * @param id 组织机构 id
     * @return java.util.List<com.cloud.entity.sys.OrgInfo>
     * @author huangYl
     * @date 2018/11/12 17:47
     */
    public List<OrgInfo> getSameParentOrg(Long id) {
        OrgInfo orgInfo = orgInfoMapper.selectById(id);
        List<OrgInfo> list = orgInfoMapper.selectChildrenByParentCode(orgInfo.getParentCode());
        if (ObjectUtils.isEmptyList(list)) {
            return Lists.newArrayList();
        }
        return list;
    }


    /**
     * 更新组织机构
     *
     * @param orgInfo 组织机构对象
     * @return void
     * @author huangYl
     * @date 2018/11/14 15:54
     */
    public void updateOrganization(OrgInfo orgInfo) {
        if (ObjectUtils.isEmptyString(orgInfo.getName())) {
            logger.error("缺少机构名称参数：name");
            throw new BusinessException("缺少机构名称");
        }
        if (ObjectUtils.isEmptyString(orgInfo.getParentNumber())) {
            logger.error("缺少父机构号参数：parentNumber");
            throw new BusinessException("缺少父机构号");
        }
        OrgInfo target = orgInfoMapper.selectById(orgInfo.getId());
        if (!orgInfo.getName().equals(target.getName())) {
            // 判断当前层级是否存在同名的机构
            OrgInfo checkOrgInfo = orgInfoMapper.selectByParentNumberAndName(orgInfo.getParentNumber(), orgInfo.getName());
            if (ObjectUtils.isNotEmptyObject(checkOrgInfo)) {
                throw new BusinessException("该机构名称在同级中已经存在，请重新输入");
            }
        }
        //判断该机构是否被引用
        if (!target.getParentNumber().equals(orgInfo.getParentNumber())) {
            // 判断该机构还有没有下级机构
            List<OrgInfo> list = orgInfoMapper.selectChildrenByParentNumber(orgInfo.getNumber());
            if (ObjectUtils.isNotEmptyList(list)) {
                logger.error(String.format("机构号：%s 下级有其他机构，不能修改它的上级机构", orgInfo.getNumber()));
                throw new BusinessException("该机构下级有其他机构，不能修改它的上级机构");
            }
        }
        //只有易班超级管理员可以修改机构号
        if (ObjectUtils.isNotEmptyString(orgInfo.getNumber()) && !target.getNumber().equals(orgInfo.getNumber())) {
            //判断机构号是否存在（本系统中机构号是唯一的）
            Integer count = orgInfoMapper.selectNumberOnly(orgInfo.getNumber());
            if (count > 0) {
                logger.info(String.format("机构号：%s 已存在", orgInfo.getNumber()));
                throw new BusinessException("该机构号已存在，请重新输入");
            } else {
                target.setNumber(orgInfo.getNumber());
            }
        }
        //更新机构名称
        target.setName(orgInfo.getName());
        //判断是否改变父组织机构
        if (!target.getParentNumber().equals(orgInfo.getParentNumber())) {
            //更新父机构号
            target.setParentNumber(orgInfo.getParentNumber());
            //获取父组织机构的类型
            OrgInfo parent = orgInfoMapper.selectByParentNumber(orgInfo.getParentNumber());
            //更新父机构编码
            target.setParentCode(parent.getCode());
            //更新机构编码
            String newCode = createNewCode(parent.getCode());
            target.setCode(newCode);
        }
        orgInfoMapper.updateByPrimaryKeySelective(target);
    }

    /**
     * 根据ID删除组织机构
     *
     * @param id 组织机构id
     * @return void
     * @author huangYl
     * @date 2018/11/14 16:45
     */
    public void deleteOrganization(Long id) {
        ArrayList<Long> delIds = new ArrayList<>();
        OrgInfo orgInfo = orgInfoMapper.selectById(id);
        // 获取当前以及下级的所有机构，倒叙排
        List<OrgInfoTreeVo> orgInfoList = orgInfoMapper.getUserOrganization(orgInfo.getCode());
        orgInfoList = orgInfoList.stream().sorted((a, b) -> (int) (b.getId() - a.getId())).collect(Collectors.toList());
        orgInfoList.forEach(o -> {
            //判断是否有用户使用该机构
            SysUser user = userService.selectUserByOrgId(o.getId());
            if (ObjectUtils.isNotEmptyObject(user)) {
                logger.info(String.format("机构id：%s 有用户引用", o.getId()));
                throw new BusinessException("存在用户数据，不能删除");
            }
            //判断是否数据采集批次使用该机构 TODO
            delIds.add(o.getId());
        });
        //一次性批量删除
        orgInfoMapper.batchDeleteById(delIds);
    }

    /**
     * 根据传入的List集合生成树结构（含有当前机构以及下级机构）
     *
     * @param data 排好序的组织机构列表
     * @return java.util.List<com.cloud.common.vo.sys.org.OrgInfoTreeVo>
     * @author huangYl
     * @date 2018/11/13 16:44
     */
    private List<OrgInfoTreeVo> recursionCreateTree(List<OrgInfoTreeVo> data) {
        Map<String, OrgInfoTreeVo> idMap = new HashMap<>(10);
        LinkedHashMap<String, OrgInfoTreeVo> resultMap = new LinkedHashMap<>();
        for (int i = 0; i < data.size(); i++) {
            OrgInfoTreeVo t = data.get(i);
            if (idMap.containsKey(t.getNumber())) {
                OrgInfoTreeVo oldValue = idMap.get(t.getNumber());
                t.setChildren(oldValue.getChildren());
                idMap.replace(t.getNumber(), t);
            } else {
                idMap.put(t.getNumber(), t);
            }
            if (0 == i) {
                resultMap.put(t.getNumber(), t);
            }

            if (!idMap.containsKey(t.getParentNumber())) {
                //初始化
                idMap.put(t.getParentNumber(), new OrgInfoTreeVo());
            }
            OrgInfoTreeVo parent = idMap.get(t.getParentNumber());
            if (parent.getChildren() == null || parent.getChildren().size() == 0) {
                ArrayList<OrgInfoTreeVo> list = Lists.newArrayList(t);
                parent.setChildren(list);
            } else {
                parent.getChildren().add(t);
            }
        }
        return Lists.newArrayList(resultMap.values());
    }


    /**
     * 递归生成含有父级组织的树结构（含有上级机构以及下级机构））
     *
     * @param orgInfoTree 当前用户的组织机构
     * @return com.cloud.common.vo.sys.org.OrgInfoTreeVo
     * @author huangYl
     * @date 2018/11/12 14:38
     */
    private OrgInfoTreeVo recursionCreateTreeOnParent(OrgInfoTreeVo orgInfoTree) {
        OrgInfoTreeVo treeVo = new OrgInfoTreeVo();
        String zero = "0";
        if (zero.equals(orgInfoTree.getParentNumber()) || null == orgInfoTree.getParentNumber()) {
            return orgInfoTree;
        } else {
            OrgInfo parentOrgInfo = orgInfoMapper.selectByParentNumber(orgInfoTree.getParentNumber());
            BeanUtils.copyProperties(parentOrgInfo, treeVo);
            treeVo.getChildren().add(orgInfoTree);
            treeVo.setStatus(OrgInfoTreeVo.NODE_PARENT_TYPE);
            OrgInfoTreeVo parentTreeVo = recursionCreateTreeOnParent(treeVo);
            return parentTreeVo;
        }
    }

    /**
     * 根据父级组织机构编码生成新的子机构编码
     *
     * @param parentCode 父组织机构编码
     * @return java.lang.String  子组织机构编码
     * @author huangYl
     * @date 2018/11/14 14:19
     */
    private String createNewCode(String parentCode) {
        String newCode = "";
        List<OrgInfo> orgInfoList = orgInfoMapper.selectChildrenByParentCode(parentCode);
        if (ObjectUtils.isEmptyList(orgInfoList)) {
            newCode = parentCode + "D1";
        } else {
            //id从小到大排序
            orgInfoList.sort(comparingLong(OrgInfo::getId));
            //获取最大的子机构，用户设置新添加的子机构code
            OrgInfo maxChild = orgInfoList.get(orgInfoList.size() - 1);
            //获取到最大子机构CODE
            String maxCode = maxChild.getCode();
            //原来最大子机构的序列
            String maxIndex = maxCode.substring(maxCode.lastIndexOf("D") + 1, maxCode.length());
            //新的CODE序列
            Long newIndex = (Long.valueOf(maxIndex) + 1);
            String newIndexStr = "" + newIndex;
            newCode = maxCode.substring(0, maxCode.lastIndexOf("D")) + "D" + newIndexStr;
        }
        return newCode;
    }


    /**
     * 根据系统规则递增生成组织机构编号number默认从(Z + 0001)开始
     *
     * @return 组织机构编号
     */
    private String createNewNumber() {
        // 获取最后一个已 'Z'开头的机构
        String orgNumber = "Z";
        OrgInfo orgInfo = orgInfoMapper.selectLastOrgByZFront();
        if (ObjectUtils.isEmptyObject(orgInfo)) {
            orgNumber += "0001";
        } else {
            String number = orgInfo.getNumber();
            number = number.substring(1);
            Integer newNumber = Integer.valueOf(number) + 1;
            orgNumber = StringUtils.leftPad(newNumber.toString(), 4, '0');
            orgNumber = "Z" + orgNumber;
        }
        return orgNumber;
    }

    /**
     * 获取传入字符串有多少个 'D'
     *
     * @param code 组织机构编码
     * @return int 'D'的数量
     * @author huangYl
     * @date 2018/11/19 16:38
     */
    private int checkCodeWithD(String code) {
        int count = 0;
        for (int i = 0; i < code.length(); i++) {
            String s = String.valueOf(code.charAt(i));
            if ("D".equals(s)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 更新学校信息
     *
     * @param file       文件
     * @param school     更改对象
     * @param uploadLogo 是否更改logo
     * @return
     * @author yuxin
     * @date 2018年11月20日 上午11:03:25
     * @update Pan Jianneng
     * @updatedesc 添加上传logo
     */
    public void updateSchool(MultipartFile file, OrgInfo school, Boolean uploadLogo) throws Exception {
        if (ObjectUtils.isEmptyObject(school.getSchoolNikeName())) {
            throw new Exception("学校简称为空！");
        }
        if (ObjectUtils.isEmptyObject(school.getProvinceId()) ||
                ObjectUtils.isEmptyObject(school.getCityId())) {
            throw new Exception("学校地址为空！");
        }
        if (ObjectUtils.isNotEmptyObject(school.getSchoolMotto()) && 
        		school.getSchoolMotto().length()>100) {
        	throw new Exception("校训应为100字以内！");
        }
        if (uploadLogo) {
            String logoName = saveShcoolLogo(file, school.getId());
            school.setSchoolLogoPath(logoName);
        }
        int updateSchoolResule = orgInfoMapper.updateByPrimaryKeySelective(school);
        if (updateSchoolResule <= 0) {
            throw new Exception("更新学校信息异常");
        }
    }

    /**
     * 保存学校logo,一个学校只保留一张logo
     *
     * @param file
     * @param orgId
     * @return java.lang.String
     * @author Pan Jianneng
     * @date 2018/11/21 3:12 PM
     */
    private String saveShcoolLogo(MultipartFile file, Long orgId) {
        //根目录
        String rootPath = schoolLogo;
        //学校logo保存位置
        String savePath = "/school/logo/" + orgId + "/";
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成唯一文件名
        fileName=generateUniqueFix()+suffix;
        File targetFile = new File(rootPath + savePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        } else {
            //删除之前的logo文件
            File oldLogoFile = new File(rootPath + savePath);
            deleteDirFile(oldLogoFile);
        }
        try {
            FileOutputStream out = new FileOutputStream(rootPath + savePath + fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savePath + fileName;
    }
    
    /**
     * 时间+随机数   生成唯一文件名
     * @param
     * @author yuxin
     * @date 2018年12月21日 下午5:20:33 
     * @return
     */
    public static String generateUniqueFix() {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append(
                random).toString();
    }
    
    /**
     * 删除目录下所有文件
     *
     * @param dir
     * @return boolean
     * @author Pan Jianneng
     * @date 2018/11/21 3:10 PM
     */
    private static void deleteDirFile(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            File temp = null;
            //删除目录中的文件
            for (int i = 0; i < children.length; i++) {
                temp = new File(dir + "/" + children[i]);
                if (temp.isFile()) {
                    temp.delete();
                }
            }
        }
    }

    /**
     * 根据组织机构id查询学校信息是否完善成功
     * @author Pan Jianneng
     * @date 2018/11/26 11:56 AM
     * @return boolean
     */
    public boolean schoolInfoIsComplete(){
        ShiroUser user = UserUtils.getInstance().getCurrentlyUserInfo();
        return orgInfoMapper.schoolInfoIsComplete(user.getSchoolOrgId()==null?user.getOrgId():user.getSchoolOrgId());
    }

    /**
     * 系统根据组织机构生成默认用户和角色
     *
     * @author huangYl
     * @date 2018/11/28 10:57
     */
    public void createDefaultUserAndRole() {
        List<OrgInfo> list = orgInfoMapper.selectAllList();
        list.forEach(o -> {
        	//部门机构没有默认角色和管理员
        	if(!CommonDict.ORG_TYPE_DEPARTMENT.equals(o.getType())){
        		userService.addDefaultRoleAndAdmin(o.getNumber(),o.getId(),o.getName());
        	}
        });
    }
    
    /**
     * 获取用户的组织机构（学校下的部门用户作为学校用户）
     * @param
     * @author yuxin
     * @date 2018年12月14日 上午11:15:52 
     * @return
     */
    public List<OrgInfoTreeVo> getUserOrg() {
    	ShiroUser currentlyUserInfo = UserUtils.getInstance().getCurrentlyUserInfo();
        // 获取当前登录用户的组织机构id
        Long orgId = currentlyUserInfo.getOrgId();
        if (null != orgId) {
        	String orgCode="";
        	//如果是部门机构  返回学校的机构code
        	if(CommonDict.ORG_TYPE_DEPARTMENT.equals(currentlyUserInfo.getOrgType())){
        		orgCode=currentlyUserInfo.getSchoolOrgCode();
        	}else {
        		orgCode=currentlyUserInfo.getOrgCode();
			}
            List<OrgInfoTreeVo> orgInfoList = orgInfoMapper.getUserOrganization(orgCode);
            if (null == orgInfoList || orgInfoList.size() == 0) {
                return Lists.newArrayList();
            }
            //这里需要生成树结构
            List<OrgInfoTreeVo> orgInfoTreeVos = recursionCreateTree(orgInfoList);
            return orgInfoTreeVos;
        }
        throw new BusinessException("登录角色没有任何组织机构");
    }

}
