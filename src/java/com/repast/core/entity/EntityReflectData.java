package com.repast.core.entity;

public final class EntityReflectData {
	private String tableName;
	private String primaryKeyFieldName;
	private String primaryKeyColumnName;
	private String insertSql;
	private String[] insertFields;
	private String updateSql;
	private String[] updateSqlFields;
	private String deleteSql;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPrimaryKeyFieldName() {
		return primaryKeyFieldName;
	}
	public void setPrimaryKeyFieldName(String primaryKeyFieldName) {
		this.primaryKeyFieldName = primaryKeyFieldName;
	}
	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}
	public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
		this.primaryKeyColumnName = primaryKeyColumnName;
	}
	public String getInsertSql() {
		return insertSql;
	}
	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}
	public String[] getInsertFields() {
		return insertFields;
	}
	public void setInsertFields(String[] insertFields) {
		this.insertFields = insertFields;
	}
	public String getUpdateSql() {
		return updateSql;
	}
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}
	public String[] getUpdateSqlFields() {
		return updateSqlFields;
	}
	public void setUpdateSqlFields(String[] updateSqlFields) {
		this.updateSqlFields = updateSqlFields;
	}
	public String getDeleteSql() {
		return deleteSql;
	}
	public void setDeleteSql(String deleteSql) {
		this.deleteSql = deleteSql;
	}
	
	
}
