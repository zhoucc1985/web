package com.cloud.mapper.master.datacollect;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 *
 * 查询模板
 * @author TangJun
 * @date 2019年1月16日 上午10:31:22
 */
public interface TemplateMapper {



	/**
	 * 查询模板
	 * @param params
	 * @return
	 * @author TangJun
	 * @date 2019年1月16日 上午10:40:30
	 */
	List<Map<String ,Object>> findTemplate(Map<String, Object> params);

	/**
	 * 查询模板count
	 * @param params
	 * @return
	 * @author TangJun
	 * @date 2019年1月16日 上午10:40:30
	 */
	List<Map<String ,Object>> findTemplateCount(Map<String, Object> params);


	/**
	 * 根据id查询模板表名
	 * @param templateId
	 * @return
	 * @author TangJun
	 * @date 2019年1月17日 下午5:42:14
	 */
	List<Map<String ,Object>> findTableNamesByTemplateId(@Param("templateId") Integer templateId);
	/**
	 * 根据id查询模板表名
	 * @param batchId
	 * @return
	 * @author TangJun
	 * @date 2019年1月17日 下午5:42:14
	 */
	List<Map<String ,Object>> findTableNamesByBatchId(@Param("batchId") String batchId);

	/**
	 * 查询模板数据
	 * @param params
	 * @return
	 * @author TangJun
	 * @date 2019年1月17日 下午6:17:19
	 */
	List<Map<String ,Object>> findTemplateData(Map<String ,Object> params);

	/**
	 * 根据批次Id 查询模板Id
	 * @param batchId
	 * @return
	 */
	Integer findTemplateIdByBatchId(@Param("batchId") String batchId);

}
