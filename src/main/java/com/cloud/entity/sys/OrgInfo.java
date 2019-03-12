package com.cloud.entity.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* 组织机构实体
* @author huangYl
* @time 2018/11/09
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgInfo implements Serializable {
    /**
    * id
    */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
    * 组织机构号
    */
    @ApiModelProperty(notes = "组织机构号")
    private String number;

    /**
    * 机构名称
    */
    @ApiModelProperty(notes = "机构名称")
    private String name;

    /**
    * 学校简称
    */
    @ApiModelProperty(notes = "学校简称")
    private String schoolNikeName;

    /**
    * 学校成立时间
    */
    @ApiModelProperty(notes = "学校成立时间")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date schoolFoundingTime;

    /**
    * 学校地址
    */
    @ApiModelProperty(notes = "学校地址")
    private String schoolAddress;

    /**
    * 学校校训
    */
    @ApiModelProperty(notes = "学校校训")
    private String schoolMotto;

    /**
    * 学校logo地址
    */
    @ApiModelProperty(notes = "学校logo地址")
    private String schoolLogoPath;

    /**
    * 学校唯一id,目前只有学校才有
    */
    @ApiModelProperty(notes = "学校唯一id,目前只有学校才有")
    private String schoolId;

    /**
    * 父组织机构号
    */
    @ApiModelProperty(notes = "父组织机构号")
    private String parentNumber;

    /**
    * 教育部：EDUCATION，省厅：PROVINCE，学校：SCHOOL
    */
    @ApiModelProperty(notes = "教育部：EDUCATION，省厅：PROVINCE，学校：SCHOOL，部门：DEPARTMENT")
    private String type;

    /**
     * 机构编码 (默认教育局为D000，每加一级后面拼接 D +数字(从1开始)，以此类推)
     */
    @ApiModelProperty(notes = "组织机构编码")
    private String code;

    /**
     * 父级组织机构编码
     */
    @ApiModelProperty(notes = "父级组织机构编码")
    private String parentCode;

    /**
     * 省份id
     */
    @ApiModelProperty(notes = "省份id")
    private Long provinceId;

    /**
     * 城市id
     */
    @ApiModelProperty(notes = "城市id")
    private Long cityId;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 学校名称
     */
    private String cityName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", name=").append(name);
        sb.append(", schoolNikeName=").append(schoolNikeName);
        sb.append(", schoolFoundingTime=").append(schoolFoundingTime);
        sb.append(", schoolAddress=").append(schoolAddress);
        sb.append(", schoolMotto=").append(schoolMotto);
        sb.append(", schoolLogoPath=").append(schoolLogoPath);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", parentNumber=").append(parentNumber);
        sb.append(", type=").append(type);
        sb.append(", code=").append(code);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}