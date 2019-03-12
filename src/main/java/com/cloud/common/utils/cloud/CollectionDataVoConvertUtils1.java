package com.cloud.common.utils.cloud;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.utils.ObjectUtils;
import com.cloud.common.vo.datacollection.CollectionDataErrorObjVO;
import com.cloud.entity.datacollect.Basic_class_info;
import com.cloud.entity.datacollection.CollectionDataSource;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 采集数据实体转换工具类
 *
 * @author: Pan Jianneng
 * @create: 2018/11/22 08:53
 */
public class CollectionDataVoConvertUtils1 {


    /**
     * 根据有错误的采集数据原数据转换成页面显示的对象
     * @author Pan Jianneng
     * @date 2018/11/22 11:55 AM
     * @param sources 错误原数据集合
     * @return java.util.List<com.cloud.common.vo.datacollection.CollectionDataErrorListVO>
     */
    public static List<Map<String,CollectionDataErrorObjVO>> convertToErrorListByList(List<Basic_class_info> sources){
        List<Map<String,CollectionDataErrorObjVO>> resultList = new ArrayList<Map<String,CollectionDataErrorObjVO>>();
        List<Map<String,Object>> resultObjList = new ArrayList<Map<String,Object>>();
        sources.forEach(dataSource->{
            //将对象转为map 字段跟值
            Map<String,Object> fieldsAndValueMap = beanToMap(dataSource);
            //将错误信息转为错误对象
            List<CollectionDataErrorObjVO> eObjVOs = JSONObject.parseArray(dataSource.getError_msg(),CollectionDataErrorObjVO.class);
            //将错误对象按照名称转为map
            Map<String,CollectionDataErrorObjVO> errorObjVOMap = new HashMap<String,CollectionDataErrorObjVO>(0);
            if(ObjectUtils.isNotEmptyList(eObjVOs)){
                eObjVOs.forEach(errorObjVO->{
                    String fieldName = errorObjVO.getName();
                    if(!errorObjVOMap.containsKey(fieldName)){
                        errorObjVOMap.put(fieldName,errorObjVO);
                    }
                });
            }else{
                return;
            }
            //每个原始数据属性为一个对象
            for(String field:fieldsAndValueMap.keySet()){
                for(String errorField:errorObjVOMap.keySet()){
                    if(field.equals(errorField)){
                        //将错误数据传入错误对象中
                        CollectionDataErrorObjVO vo = errorObjVOMap.get(errorField);
                        Object fieldValueObj = fieldsAndValueMap.get(field);
                        if(ObjectUtils.isNotNullObject(fieldValueObj)){
                            vo.setValue(fieldValueObj.toString());
                        }
                        errorObjVOMap.put(errorField,vo);
                        fieldsAndValueMap.put(field,errorObjVOMap.get(errorField));
                    }
                }
            }
            resultObjList.add(fieldsAndValueMap);
        });
        //将obj转为错误对象
        resultObjList.forEach(stringObjectMap -> {
            for(String key:stringObjectMap.keySet()){
                Object valueNull = stringObjectMap.get(key);
                if(ObjectUtils.isNotNullObject(valueNull)){
                    String className = stringObjectMap.get(key).getClass().getName();
                    if(className.equals(CollectionDataErrorObjVO.class.getName())){
                        CollectionDataErrorObjVO vo = (CollectionDataErrorObjVO) stringObjectMap.get(key);
                        stringObjectMap.put(key,vo);
                    }
                }
            }
            resultList.add((Map)stringObjectMap);
        });
        return resultList;
    }

    /**
     * 把对象转成map
     * @author Pan Jianneng
     * @date 2018/11/22 7:30 PM
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
}

