package com.repast.core.uiview;

public class SelectConfig {
	
	private String id;
	
	private String tableName;
	private String textCol;
	private String valueCol;
	
	private String sql;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTextCol() {
		return textCol;
	}

	public void setTextCol(String textCol) {
		this.textCol = textCol;
	}

	public String getValueCol() {
		return valueCol;
	}

	public void setValueCol(String valueCol) {
		this.valueCol = valueCol;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
