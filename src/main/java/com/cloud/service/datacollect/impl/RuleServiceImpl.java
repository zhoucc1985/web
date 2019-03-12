package com.cloud.service.datacollect.impl;

import com.cloud.entity.datacollect.CollectColumns;
import com.cloud.entity.datacollect.CollectRule;
import com.cloud.entity.datacollect.CollectTemplate;
import com.cloud.mapper.master.datacollect.AsyncExecutorValidMapper;
import com.cloud.mapper.master.datacollect.CollectRuleMapper;
import com.cloud.mapper.real.datacollect.RealTestMapper;
import com.cloud.service.datacollect.RealTableService;
import com.cloud.service.datacollect.RuleService;
import com.cloud.service.datacollect.TempTableService;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RuleServiceImpl implements RuleService {
	@Resource
	private TempTableService tempTableService;
	@Resource
	private RealTableService realTableService;
	@Resource
	private AsyncExecutorValidMapper asyncExecutorValidMapper;
	@Resource
	private RealTestMapper realTestMapper;
	@Resource
	private CollectRuleMapper collectRuleMapper;

	@Override
	public void insertCollectColumns() throws Exception {
		// 获取所有真实表
		List<CollectTemplate> realTableNameList = realTableService.findAllRealTableName();
		// 查询表字段所有列表的值
		List<CollectColumns> collectColumnsList = realTableService.findCollectColumns();

		List<String> realTableName = new ArrayList<>();
		// 循环获取真实表表字段
		for (CollectTemplate collectTemplate : realTableNameList) {
			String name = collectTemplate.getRealTableName();
			realTableName.add(name);
		}
		// 查询真实表的列的属性
		List<Map<String, Object>> cloumsList = realTestMapper.getColumnFiledsByTableName(realTableName);
		// List<Map<String, Object>> cloumsList =
		// asyncExecutorValidService.getColumnFiledsByTableName(realTableName);
		for (CollectTemplate collectTemplate : realTableNameList) {
			if (StringUtils.isNullOrEmpty(collectTemplate.getRealTableName())) {
				continue;
			}
			for (Map<String, Object> map : cloumsList) {
				if (StringUtils.isNullOrEmpty((String) map.get("STABLENAME"))) {
					continue;
				}
				if (map.get("STABLENAME").equals(collectTemplate.getRealTableName())) {
					map.put("templateId", collectTemplate.getTemplateId());
				}
			}
		}
		// 往真实列的属性的表中插入SORT
		List<CollectColumns> setCloums = new ArrayList<CollectColumns>();
		for (Map<String, Object> map : cloumsList) {
			String templateId = map.get("templateId").toString();
			String column_ch_name = map.get("SCOLUMNCOMMENT").toString();
			String column_en_name = map.get("SFIELDNAME").toString();
			String table_name = map.get("STABLENAME").toString();
			String sort = map.get("SORT").toString();
			CollectColumns Columns = null;
			Columns = new CollectColumns(templateId, column_ch_name, column_en_name, table_name, sort);
			setCloums.add(Columns);
		}
		/*
		 * //根据TemplateId去重 Set<String> set = new HashSet<>(); for
		 * (CollectColumns c : collectColumnsList) { set.add(c.getTemplateId());
		 * } for (CollectColumns collectColumns : setCloums) {
		 * if(!set.contains(collectColumns.getTemplateId())){
		 * asyncExecutorValidMapper.insertCollectColumns(collectColumns); } }
		 */
		// 清空CollectColumns数据
		asyncExecutorValidMapper.truncateCollectColumne();
		// 新增CollectColumns数据
		for (CollectColumns collectColumns : setCloums) {
			asyncExecutorValidMapper.insertCollectColumns(collectColumns);
		}

		// 往真实列的属性的表中插入ColumnId
		for (CollectColumns collectColumns : collectColumnsList) {
			if (StringUtils.isNullOrEmpty(collectColumns.getTableName())) {
				continue;
			}
			for (Map<String, Object> map : cloumsList) {
				if (StringUtils.isNullOrEmpty((String) map.get("STABLENAME"))) {
					continue;
				}
				if (map.get("STABLENAME").equals(collectColumns.getTableName())
						&& map.get("SFIELDNAME").equals(collectColumns.getColumnEnName())) {
					map.put("ColumnId", collectColumns.getColumnId());
				}
			}
		}
		// 把数据装入对象中
		List<CollectRule> setCloums1 = new ArrayList<CollectRule>();
		for (Map<String, Object> map : cloumsList) {
			String columnId = map.get("ColumnId").toString();
			String rule_type = map.get("IFIELDTYPE").toString();
			String max_length = map.get("IFIELDLENGTH").toString();
			int is_null;
			if (map.get("IREQUIRED").toString().equals("N")) {
				is_null = 1;
			} else {
				is_null = 0;
			}
			CollectRule Column = null;
			Column = new CollectRule(Integer.valueOf(columnId), rule_type, max_length, is_null);
			setCloums1.add(Column);
		}
		/*// 查询出规则表的所有属性
		List<CollectRule> collectRuleList = findCollectRule();
		// 更据ColumnId去重
		Set<String> set1 = new HashSet<>();
		for (CollectRule c : collectRuleList) {
			set1.add(c.getColumnId().toString());
		}
		for (CollectRule collectRule : setCloums1) {
			if (set1.size() > 0) {
				if (set1.contains(collectRule.getColumnId())) {
					asyncExecutorValidMapper.insertCollectRule(collectRule);
				}
			} else {
				asyncExecutorValidMapper.insertCollectRule(collectRule);
			}

		}*/
		// 清空collect_rule数据
		asyncExecutorValidMapper.truncateCollectRule();
		// 新增collect_rule数据
		for (CollectRule collectRule : setCloums1) {
			asyncExecutorValidMapper.insertCollectRule(collectRule);
		}
	}

	@Override
	public List<CollectRule> findCollectRule() {
		return collectRuleMapper.findCollectRule();
	}
}
