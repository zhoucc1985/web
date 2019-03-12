package com.cloud.mapper.master.sys;

import com.cloud.entity.sys.Province;

import java.util.List;

/**
 * 省份 mapper
 *
 * @author huangYl
 * @time 2018/11/21
 */
public interface ProvinceMapper {
    /**
     * 根据ID查询
     *
     * @param id
     * @return com.cloud.entity.sys.Province
     * @author huangYl
     * @date 2018/11/21 10:21
     */
    Province selectByPrimaryKey(Long id);

    /**
     * 查询所有省份
     *
     * @return java.util.List<com.cloud.entity.sys.Province>
     * @author huangYl
     * @date 2018/11/21 10:31
     */
    List<Province> selectAll();
}