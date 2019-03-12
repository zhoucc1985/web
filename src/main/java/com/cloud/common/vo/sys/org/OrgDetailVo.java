package com.cloud.common.vo.sys.org;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.cloud.entity.sys.OrgInfo;

/**
 * 组织机构详情实体类
 *
 * @author huangYl
 * @date 2018/11/14 10:28
 **/
@Data
public class OrgDetailVo {
    /**
     * 组织机构id
     */
    private Long id;
    /**
     * 组织机构号
     */
    private String number;
    /**
     * 组织机构名称
     */
    private String name;
    /**
     * 父组织机构id
     */
    private String parentId;
    /**
     * 父组织机构号
     */
    private String parentNumber;
    /**
     * 父组织机构名称
     */
    private String parentName;
    /**
     * 父组织机构code
     */
    private String parentCode;
    /**
     * 同级的组织机构列表
     */
    private List<OrgInfo> list = new ArrayList<>();

    /**
     * 学校logo地址
     */
    private String schoolLogoPath;
    
    /**
     * 组织机构类型
     */
    private String type;
    /**
     * 组织机构code
     */
    private String code;
}
