package com.cloud.entity.datacollect;

import java.io.Serializable;

import com.cloud.entity.datacollect.CollectTemplate.CollectTemplateBuilder;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectColumns implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "")
	private String columnId;
	@ApiModelProperty(notes = "")
	private String templateId;
	@ApiModelProperty(notes = "字段中文名")
	private String columnChName;
	@ApiModelProperty(notes = "字段英文名")
	private String columnEnName;
	@ApiModelProperty(notes = "表名")
	private String tableName;
	@ApiModelProperty(notes = "字段排序")
	private String sort;

	/*
	 * public String getColumn_id() { return column_id; }
	 * 
	 * public void setColumn_id(String column_id) { this.column_id = column_id;
	 * }
	 * 
	 * public String getTemplateId() { return templateId; }
	 * 
	 * public void setTemplateId(String template_id) { this.templateId =
	 * template_id; }
	 * 
	 * public String getColumn_ch_name() { return column_ch_name; }
	 * 
	 * public void setColumn_ch_name(String column_ch_name) {
	 * this.column_ch_name = column_ch_name; }
	 * 
	 * public String getColumn_en_name() { return column_en_name; }
	 * 
	 * public void setColumn_en_name(String column_en_name) {
	 * this.column_en_name = column_en_name; }
	 * 
	 * public String getTable_name() { return table_name; }
	 * 
	 * public void setTable_name(String table_name) { this.table_name =
	 * table_name; }
	 * 
	 * public String getSort() { return sort; }
	 * 
	 * public void setSort(String sort) { this.sort = sort; }
	 */

	public CollectColumns(String templateId, String columnChName, String columnEnName, String tableName, String sort) {
		super();
		this.templateId = templateId;
		this.columnChName = columnChName;
		this.columnEnName = columnEnName;
		this.tableName = tableName;
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "CollectColumns [columnId=" + columnId + ", templateId=" + templateId + ", columnChName="
				+ columnChName + ", columnEnName=" + columnEnName + ", tableName=" + tableName + ", sort=" + sort + "]";
	}

}
