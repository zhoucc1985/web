package com.cloud.common.vo.sys.org;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.cloud.entity.sys.OrgInfo;

/**
 * 组织机构树结构返回实体
 *
 * @author huangYl
 * @date 2018/11/9 18:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class OrgInfoTreeVo extends OrgInfo {

    private static final long serialVersionUID = 1L;
    /**
     * 节点类型
     */
    public static final String NODE_PARENT_TYPE = "PARENT";
    public static final String NODE_CURRENT_TYPE = "CURRENT";
    public static final String NODE_CHILD_TYPE = "CHILD";
    /**
     * 子组织机构结合
     */
    @ApiModelProperty(notes = "子组织机构结合")
    private List<OrgInfoTreeVo> children = new ArrayList<>();

    /**
     * 节点状态
     */
    @ApiModelProperty(notes = "父节点：PARENT，当前节点：CURRENT，子节点：CHILD")
    private String status = NODE_CHILD_TYPE;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", number=").append(getName());
        sb.append(", name=").append(getName());
        sb.append(", schoolNikeName=").append(getSchoolNikeName());
        sb.append(", schoolFoundingTime=").append(getSchoolFoundingTime());
        sb.append(", schoolAddress=").append(getSchoolAddress());
        sb.append(", schoolMotto=").append(getSchoolMotto());
        sb.append(", schoolLogoPath=").append(getSchoolLogoPath());
        sb.append(", schoolId=").append(getSchoolId());
        sb.append(", parentNumber=").append(getParentNumber());
        sb.append(", type=").append(getType());
        sb.append(", children=").append(children);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}
