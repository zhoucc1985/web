package com.cloud.mapper.temp.datacollect;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 有关于临时表的增删改查的mapper接口,数据源配置会直接到临时表数据库去查询
 */
@Repository
public interface TempTableMapper {

	/**
	 * 查询临时表中 无效数据列表（实现分页）
	 * @param page
	 * @param pageSize
	 * @param batchId
	 * @param tableName
	 * @return
	 */
	List<Map<String,Object>> findInvalidData(@Param("page") Integer page,@Param("pageSize") Integer pageSize,
											 @Param("batchId") String batchId,@Param("tableName") String tableName);

	/**
	 * 临时表无效数据总条数（作分页总数）
	 * @param batchId
	 * @param tableName
	 * @return
	 */
	Long findInvalidDataTotalCount(@Param("batchId") String batchId,@Param("tableName") String tableName);

	/**
	 * 修改无效数据后保存更新
	 * @param SQL
	 * @param id
	 * @param tableName
	 */
	void updateByEntity(@Param("SQL") String SQL,
						@Param("id") Long id,
						@Param("tableName") String tableName);

	/**
	 * 查询临时表中源数据
	 * @param columns 需要查询的字段名（除公共字段外）
	 * @param tmpTableName 临时表名
	 * @return
	 */
	List<Map<String,Object>> findTmpTableDataList(@Param("columns") List<String> columns,
												  @Param("tmpTableName") String tmpTableName,
												  @Param("id") Long id);

	/**
	 * 更新添加没条错误数据的错误信息
	 * @param id 每一列的唯一ID
	 */
	void updateErrorMsg(@Param("tmpTableName") String tmpTableName,
						@Param("errorMsg") String errorMsg,
						@Param("isError") Integer isError,
						@Param("id") Integer id);

	/**
	 * Excel导入数据库后总数据量
	 * @return
	 */
	Integer exportDataAllCount(@Param("tableName") String tableName,@Param("batchId") String batchId);
	/**
	 * 有效数据总数
	 * @return
	 */
	Integer exportDataEffectiveCount(@Param("tableName") String tableName,@Param("batchId") String batchId);
	/**
	 * 无效数据总数
	 * @param tableName
	 * @return
	 */
	Integer exportDataInvalidCount(@Param("tableName") String tableName,@Param("batchId") String batchId);

	/**
	 * 删除无效数据（可多选）
	 * @param ids 删除的多个Id (每个临时表的每条数据都有一个唯一的Id)
	 * @Param tableName 临时表名
	 */
	void deleteInvalidData(@Param("ids") List<String> ids,@Param("tableName") String tableName);

	/**
	 * 根据批次编号和临时表名查询无效数据
	 * @param batchId
	 * @param tableName
	 * @return
	 */
	List<Map<String, Object>> findTableErrorList(@Param("columnsList") List<String> columnsList,
												 @Param("batchId") String batchId,
												 @Param("tableName") String tableName);

	/**
	 * excel数据插入数据库临时表
	 * @param tmpTableName
	 * @param columnSql
	 * @param sql
	 */
	void insertIntoTmpTable(@Param("tmpTableName") String tmpTableName, @Param("columnSql") String columnSql, @Param("sql") String sql);

	/**
	 * 删除数据库临时表中的无效数据
	 * @param tmpTableName
	 * @param uniqueIdList
	 */
	void delInvalidData(@Param("tmpTableName") String tmpTableName,@Param("uniqueIdList") List<String> uniqueIdList);

	/**
	 * 读取修改后Excel数据并插入到数据库临时表中
	 * @param tmpTableName
	 * @param columnSql
	 * @param sql
	 */
	void insertModifiedData(@Param("tmpTableName") String tmpTableName, @Param("columnSql") String columnSql, @Param("sql") String sql);

	/**
	 * 查找临时表中所有数据，用以批量数据校验
	 * @param columns 列名
	 * @param tmpTableName 临时表名
	 * @return
	 */
	List<Map<String,Object>> findTmpTableDataAll(@Param("columns") List<String> columns,
												 @Param("tmpTableName") String tmpTableName,
												 @Param("batchId") String batchId);

	/**
	 * 删除临时表中当前批次导入的数据
	 * @param batchId
	 * @param tableName
	 */
	void deleteTempDateByBatchId(@Param("batchId") String batchId, @Param("tableName") String tableName);

	void insertRealTableName(@Param("resultStr") String resultStr, @Param("tempTableName") String tempTableName,
							 @Param("realTableName") String realTableName,@Param("batchId") String batchId );

}
