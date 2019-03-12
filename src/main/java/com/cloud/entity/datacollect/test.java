package com.cloud.entity.datacollect;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请添加描述
 * @author zhuJT
 * @time 2019/01/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class test implements Serializable {
    /**
     *
     */
    @ApiModelProperty(notes = "")
    private Integer id;

    /**
     *
     */
    @ApiModelProperty(notes = "")
    private String name;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}