package com.cloud.service.datacollect;

import java.util.List;
import java.util.Map;

import com.cloud.entity.datacollect.CollectTemplate;

/**
 * 临时表管理类
 */
public interface TempTableService {
    /**
     * 删除临时表数据
     */
    Integer deleteTempDateByBatchId(String batchId,String tableName);

    /**
     * 根据临时表名称 和 字段编号，查询该字段规则
     * @return  List[Map[String,Object]]
     */
    //List<Map<String,Object>> findRulesByTableName(String tempTableName);
    List<Map<String,Object>> findRulesByRealTableName(String tempTableName);

    /**
     * 查找临时表中一条数据(源数据)
     * @param tmpTableName
     * @return
     */
    List<Map<String,Object>> findTmpTableDataListById(String tmpTableName,String batchId,Long id);

    /**
     * 查询所有真实表名称
     * @return
     */
    List<CollectTemplate> findAllRealTableName();

    /**
     * 更新错误信息
     */
    void updateErrorMsg(String tmpTableName,String errorMsgStr,Integer isError,Integer id);

    /**
     * 修改批次验证结果
     * @param batchId
     */
    void updateBatchValidResult(String batchId);

    /**
     * 查找临时表中所当前批次的数据，用以当前批次批量验证
     */
    List<Map<String,Object>> findTmpTableDataListByBatchId(String tmpTableName,String batchId);

    /**
     * Excel导入数据库后总数据量
     */
    Integer exportDataAllCount(Integer templateId,String batchId);
    /**
     * Excel导入数据库后有效数据量
     */
    Integer exportDataEffectiveCount(Integer templateId,String batchId);
    /**
     * Excel导入数据库后无效数据据量
     */
    Integer exportDataInvalidCount(Integer templateId,String batchId);

}
