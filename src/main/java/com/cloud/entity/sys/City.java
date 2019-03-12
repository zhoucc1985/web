package com.cloud.entity.sys;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 城市实体类
 *
 * @author huangYl
 * @time 2018/11/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {
    /**
     * 城市编号
     */
    @ApiModelProperty(notes = "城市编号")
    private Long id;

    /**
     * 城市名称
     */
    @ApiModelProperty(notes = "城市名称")
    private String name;

    /**
     * 省编号
     */
    @ApiModelProperty(notes = "省编号")
    private Long pId;


    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pId=").append(pId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}