package com.cloud.mapper.master.sys;

import com.cloud.entity.sys.ProvinceCity;

import java.util.List;

/**
 * 省会城市 mapper
 *
 * @author huangYl
 * @time 2018/11/20
 */
public interface ProvinceCityMapper {

    /**
     * 查询所有
     *
     * @return java.util.List<com.cloud.entity.sys.ProvinceCity>
     * @author huangYl
     * @date 2018/11/20 15:23
     */
    List<ProvinceCity> selectAll();

}