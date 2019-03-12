package com.cloud.service.datacollect;

import com.cloud.entity.datacollect.CollectEnum;

import java.util.List;
import java.util.Map;

public interface CollectEnumService {
    /**
     * 查询枚举类信息，系统名称 和 类别名称
     * @return @return  [firstEnums:ListMap secondEnums:ListMap]
     */
    Map<String,Map<String,Object>> findEnumData(Integer systemType);
}
