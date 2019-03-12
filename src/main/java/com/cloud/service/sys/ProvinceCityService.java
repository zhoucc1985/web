package com.cloud.service.sys;

import com.cloud.entity.sys.ProvinceCity;
import com.cloud.mapper.master.sys.ProvinceCityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省会城市 service
 *
 * @author huangYl
 * @date 2018/11/20
 **/
@Service
public class ProvinceCityService {

    @Autowired
    private ProvinceCityMapper provinceCityMapper;

    public List<ProvinceCity> selectAll() {
        return  provinceCityMapper.selectAll();
    }
}
