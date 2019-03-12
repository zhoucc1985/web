package com.cloud.service.datacollect.impl;

import com.cloud.entity.datacollect.CollectEnum;
import com.cloud.mapper.master.datacollect.CollectEnumMapper;
import com.cloud.service.datacollect.CollectEnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectEnumServiceImpl implements CollectEnumService {
    @Autowired
    private CollectEnumMapper collectEnumMapper;
    @Override
    public Map<String,Map<String,Object>> findEnumData(Integer systemType) {
        String systemName="教学诊改";
        if(systemType==2){
            systemName="学工大数据";
        }
        String  firstLevelName="业务系统分类";
        List<CollectEnum> firestlist=collectEnumMapper.fineEnumsBySystemNameAndLevelName(systemName,firstLevelName);
        String secondLevelName="子分类";
        List<CollectEnum> secondlist=collectEnumMapper.fineEnumsBySystemNameAndLevelName(systemName,secondLevelName);
        Map<String,Map<String,Object>> resultMap=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        for(CollectEnum collectEnum:firestlist){
            map.put(collectEnum.getName(),collectEnum.getValue());
        }
        resultMap.put("firstNameEnums",ListToMap(firestlist,true));
        resultMap.put("secondNameEnums",ListToMap(secondlist,true));
        resultMap.put("firstValueEnums",ListToMap(firestlist,false));
        resultMap.put("secondValueEnums",ListToMap(secondlist,false));
        return resultMap;
    }
    private Map<String,Object>  ListToMap( List<CollectEnum> list,boolean isNameKey) {
        Map<String,Object> map=new HashMap<>();
        for(CollectEnum collectEnum:list){
            if(isNameKey){
                map.put(collectEnum.getName(),collectEnum.getValue());
            }else{
                map.put(collectEnum.getValue(),collectEnum.getName());
            }
        }
        return map;
    }
}
