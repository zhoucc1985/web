package com.cloud.service.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.common.utils.ObjectUtils;
import com.cloud.entity.sys.City;
import com.cloud.entity.sys.Province;
import com.cloud.mapper.master.sys.CityMapper;
import com.cloud.mapper.master.sys.ProvinceMapper;

/**
 * 省份 service
 *
 * @author huangYl
 * @date 2018/11/21 10:24
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询所有省份
     *
     * @return java.util.List<com.cloud.entity.sys.Province>
     * @author huangYl
     * @date 2018/11/21 10:30
     */
    public List<Province> selectAll() {
        List<Province> list = provinceMapper.selectAll();
        if (ObjectUtils.isEmptyList(list)) {
            return new ArrayList<>();
        }
        return list;
    }

    /**
     * 根据ID查询
     *
     * @param id 省份id
     * @return com.cloud.entity.sys.Province
     * @author huangYl
     * @date 2018/11/21 10:35
     */
    public Province getById(Long id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据省份ID查询所有城市
     *
     * @param pId 省份id
     * @return java.util.List<com.cloud.entity.sys.City>
     * @author huangYl
     * @date 2018/11/21 10:47
     */
    public List<City> selectCityByProvinceId(Long pId) {
        List<City> list = cityMapper.selectCityByProvinceId(pId);
        if (ObjectUtils.isEmptyList(list)) {
            return new ArrayList<>();
        }
        return list;
    }
}
