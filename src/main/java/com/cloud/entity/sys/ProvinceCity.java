package com.cloud.entity.sys;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 省会城市实体类
 *
 * @author huangYl
 * @time 2018/11/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceCity implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(notes = "id")
    private Long id;

    /**
     * 省名称
     */
    @ApiModelProperty(notes = "省名称")
    private String province;

    /**
     * 省会名称
     */
    @ApiModelProperty(notes = "省会名称")
    private String provinceCity;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", province=").append(province);
        sb.append(", provinceCity=").append(provinceCity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}