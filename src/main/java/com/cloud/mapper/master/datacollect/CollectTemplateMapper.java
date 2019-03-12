package com.cloud.mapper.master.datacollect;

import com.cloud.common.qo.CheckQo;
import com.cloud.entity.datacollect.CollectTemplate;
import com.cloud.entity.vo.dataCollect.CollectDetailsVo;
import com.cloud.entity.vo.dataCollect.DetailsListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
*  模板管理
* @author zhuJT
* @time 2019/01/21
*/
public interface CollectTemplateMapper {
    int deleteByPrimaryKey(Integer templateId);

    int insert(CollectTemplate record);

    int insertSelective(CollectTemplate record);

    CollectTemplate selectByPrimaryKey(Integer templateId);
    
    int updateByPrimaryKeySelective(CollectTemplate record);

    int updateByPrimaryKeyWithBLOBs(CollectTemplate record);

    int updateByPrimaryKey(CollectTemplate record);

    List<CollectDetailsVo> queryPage(CheckQo qo);



    Map<String,Object> selectSystemCode(Integer systemNameCode);

    List<DetailsListVo> selectDetailsList(@Param("templateId")Integer templateId,@Param("orgId")String orgId);
    
    List<CollectTemplate> selectByTemplateId(List<Integer> ids);

    List<CollectDetailsVo> selectAll();
    
    String findisImport(@Param("batchId") String batchId);

    List<CollectTemplate> findAllRealTableName();

}