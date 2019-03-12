package com.cloud.mapper.master.sys;

import com.cloud.entity.sys.City;

import java.util.List;

/**
 * 城市 mapper
 *
 * @author huangYl
 * @time 2018/11/21
 */
public interface CityMapper {
    /**
     * 根据ID查询
     *
     * @param id
     * @return com.cloud.entity.sys.City
     * @author huangYl
     * @date 2018/11/21
     */
    City selectByPrimaryKey(Integer id);

    /**
     * 根据省份ID查询所有城市
     *
     * @param pId 省份id
     * @return java.util.List<com.cloud.entity.sys.City>
     * @author huangYl
     * @date 2018/11/21 10:59
     */
    List<City> selectCityByProvinceId(Long pId);
}