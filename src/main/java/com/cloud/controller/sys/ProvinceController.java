package com.cloud.controller.sys;

import com.cloud.common.utils.CommonUtils;
import com.cloud.entity.sys.City;
import com.cloud.entity.sys.Province;
import com.cloud.service.sys.ProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 省份 Controller
 *
 * @author huangYl
 * @date 2018/11/21
 **/
@Api(tags = "省份", description = "省份接口")
@RestController
@RequestMapping(value = "/api")
public class ProvinceController {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceController.class);

    @Autowired
    private ProvinceService provinceService;

    /**
     * 根据ID查询
     *
     * @param  id 省份id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author huangYl
     * @date 2018/11/21 10:45
     */
    @ApiOperation(value = "根据ID查询")
    @RequestMapping(value = "/province/{id}", method = RequestMethod.GET)
    public Map<String, Object> getById(@PathVariable("id") Long id) {
        try {
            Province province = provinceService.getById(id);
            return CommonUtils.getSucResultMap(province);
        } catch (Exception e) {
            logger.error("根据ID查询出错 id = " + id, e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 查询所有省份
     *
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author huangYl
     * @date 2018/11/21 10:46
     */
    @ApiOperation(value = "查询所有省份")
    @RequestMapping(value = "/provinces", method = RequestMethod.GET)
    public Map<String, Object> selectAll() {
        try {
            List<Province> list = provinceService.selectAll();
            return CommonUtils.getSucResultMap(list);
        } catch (Exception e) {
            logger.error("查询所有省份出错", e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }

    /**
     * 根据省份ID查询所有城市
     *
     * @param id 省份id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author huangYl
     * @date 2018/11/21 10:46
     */
    @ApiOperation(value = "根据省份ID查询所有城市")
    @RequestMapping(value = "/province/{id}/city", method = RequestMethod.GET)
    public Map<String, Object> selectCityByProvinceId(@PathVariable("id") Long id) {
        try {
            List<City> list = provinceService.selectCityByProvinceId(id);
            return CommonUtils.getSucResultMap(list);
        } catch (Exception e) {
            logger.error("根据省份ID查询所有城市出错 id = " + id, e);
            return CommonUtils.getErrorResultMap(e.getMessage());
        }
    }
}
