package com.cloud.entity.datacollect;

import lombok.Data;

@Data
public class CollectEnum {
    private Integer id;
    private String type;
    private String name;
    private String value;
    private Integer parentId;
}
