package com.cloud.service;

import java.util.List;

public interface EnumService {
    /**
     * 查询所有的枚举列表
     * @return
     */
   public List<Enum> findAllEnumList();
}
