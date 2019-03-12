package com.cloud.service.sys;

import com.cloud.mapper.master.sys.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市 service
 *
 * @author huangYl
 * @date 2018/11/21 10:24
 **/

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;
}
