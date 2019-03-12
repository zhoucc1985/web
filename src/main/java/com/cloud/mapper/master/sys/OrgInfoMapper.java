package com.cloud.mapper.master.sys;

import com.cloud.common.vo.sys.org.OrgDetailVo;
import com.cloud.common.vo.sys.org.OrgInfoTreeVo;
import com.cloud.entity.sys.OrgInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织机构 mapper
 *
 * @author huangYl
 * @time 2018/11/09
 */
public interface OrgInfoMapper {
    /**
     * 根据id删除
     *
     * @param id 组织机构id
     * @return int
     * @author huangYl
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增机构
     *
     * @param record 组织机构对象
     * @return int
     * @author huangYl
     */
    int insert(OrgInfo record);

    /**
     * 动态新增机构
     *
     * @param record 组织机构对象
     * @return int
     * @author huangYl
     */
    int insertSelective(OrgInfo record);

    /**
     * 根据id查询
     *
     * @param id 机构id
     * @return com.cloud.entity.sys.OrgInfo
     * @author huangYl
     */
    OrgInfo selectByPrimaryKey(Long id);

    /**
     * 根据id查询
     *
     * @param id 机构id
     * @return com.cloud.entity.sys.OrgInfo
     * @author huangYl
     */
    OrgInfo selectById(Long id);

    /**
     * 更新
     *
     * @param record 组织机构对象
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKeySelective(OrgInfo record);

    /**
     * 动态更新
     *
     * @param record 组织机构对象
     * @return int
     * @author huangYl
     */
    int updateByPrimaryKey(OrgInfo record);

    /**
     * 根据当前登录用户组织机构id获取组织机构树
     *
     * @param code 组织机构编码
     * @return java.util.List<com.cloud.entity.sys.OrgInfo>
     * @author huangYl
     * @date 2018/11/9 17:32
     */
    List<OrgInfoTreeVo> getUserOrganization(String code);


    /**
     * 根据parentNumber查找上级父节点
     *
     * @param parentNumber 父组织机构号
     * @return com.cloud.common.vo.sys.org.OrgInfoTreeVo
     * @author huangYl
     * @date 2018/11/12 11:04
     */
    OrgInfo selectByParentNumber(String parentNumber);

    /**
     * 根据ID获取组织的以及上级机构的基础信息
     *
     * @param id 组织机构 id
     * @return com.cloud.common.vo.sys.org.OrgDetailVo
     * @author huangYl
     * @date 2018/11/14 10:36
     */
    OrgDetailVo getCurrentAndParentOrg(Long id);

    /**
     * 查询系统中有多少个相同的机构号
     *
     * @param number 机构号
     * @return java.lang.Integer
     * @author huangYl
     * @date 2018/11/12 17:25
     */
    Integer selectNumberOnly(String number);

    /**
     * 根据父组织机构编码获取所有同级组织机构
     *
     * @param parentCode 父组织机构编码
     * @return java.util.List<com.cloud.entity.sys.OrgInfo>
     * @author huangYl
     * @date 2018/11/12 17:59
     */
    List<OrgInfo> selectChildrenByParentCode(String parentCode);

    /**
     * 批量删除
     *
     * @param delIds id列表
     * @return void
     * @author huangYl
     * @date 2018/11/15 14:30
     */
    void batchDeleteById(@Param("delIds") ArrayList<Long> delIds);

    /**
     * 获取最后一个已 'Z'开头的机构
     *
     * @return com.cloud.entity.sys.OrgInfo
     * @author huangYl
     * @date 2018/11/16 14:33
     */
    OrgInfo selectLastOrgByZFront();

    /**
     * 查询所有数据
     *
     * @return java.util.List<com.cloud.entity.sys.OrgInfo>
     * @author huangYl
     * @date 2018/11/16 19:32
     */
    List<OrgInfo> selectAllList();

    /**
     * 根据父组织机构编号获取所有子组织机构
     *
     * @param number  组织机构号
     * @return java.util.List<com.cloud.entity.sys.OrgInfo>
     * @author huangYl
     * @date 2018/11/19 20:48
     */
    List<OrgInfo> selectChildrenByParentNumber(String number);

    /**
     * 根据组织机构编码查询
     *
     * @param code 组织机构编码
     * @return com.cloud.entity.sys.OrgInfo
     * @author huangYl
     * @date 2018/11/20 17:04
     */
    OrgInfo selectByCode(String code);

    /**
    * 根据父组织机构号和名称查询
    * @param  parentNumber  父组织机构号
    * @param  name  组织机构名
    * @return com.cloud.entity.sys.OrgInfo
    * @author huangYl
    * @date   2018/11/22 17:36
    */
    OrgInfo selectByParentNumberAndName(@Param("parentNumber") String parentNumber,@Param("name") String name);

    /**
     * 查看学校信息是否已经完善，返回0表示已经完善了
     * @author Pan Jianneng
     * @date 2018/11/26 11:48 AM
     * @param id 组织机构ID
     * @return
    */
    boolean schoolInfoIsComplete(@Param(value = "orgId") Long id);
}
