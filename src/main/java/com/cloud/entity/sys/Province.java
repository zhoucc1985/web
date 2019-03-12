package com.cloud.entity.sys;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 省份实体类
 *
 * @author huangYl
 * @time 2018/11/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Province implements Serializable {
    /**
     * 省编号
     */
    @ApiModelProperty(notes = "省编号")
    private Long id;

    /**
     * 省名称
     */
    @ApiModelProperty(notes = "省名称")
    private String name;

    /**
     * 拼音码
     */
    @ApiModelProperty(notes = "拼音码")
    private String code;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}