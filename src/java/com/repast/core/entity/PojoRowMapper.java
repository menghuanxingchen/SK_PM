package com.repast.core.entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;


public class PojoRowMapper<T> implements RowMapper<T> {
	private Class<T> mappedClass;
	private Map<String, Field> mappedFields;
	private Field idField = null;

	public PojoRowMapper(Class<T> mappedClass) {
		initialize(mappedClass);
	}

	protected void initialize(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap<String, Field>();
		Field[] fields = this.mappedClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Annotation[] ats = field.getAnnotations();
			if (ats.length > 0) {
				for (int y = 0; y < ats.length; y++) {
					if (ats[y] instanceof Transient) {
						continue;
					}
					if (ats[y] instanceof ID) {
						setIdField(field);
					}
					if (ats[y] instanceof Column) {
						mappedFields.put(
								((Column) ats[y]).name().toLowerCase(), field);
					} else {
						mappedFields.put(field.getName().toLowerCase(), field);
					}
				}
			} else {
				mappedFields.put(field.getName().toLowerCase(), field);
			}
		}
	}

	public final Class<T> getMappedClass() {
		return this.mappedClass;
	}

	public T mapRow(ResultSet rs, int rowNumber) throws SQLException {
		T mappedObject = BeanUtils.instantiate(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory
				.forBeanPropertyAccess(mappedObject);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		for (int index = 1; index <= columnCount; index++) {
			String column = JdbcUtils.lookupColumnName(rsmd, index);
			Field field = (Field) this.mappedFields.get(column.replaceAll(" ",
					"").toLowerCase());
			if (field != null) {
				try {
					Object value = getColumnValue(rs, index, field);
					if (value != null) {
						try {
							bw.setPropertyValue(field.getName(), value);
						} catch (TypeMismatchException e) {
							throw e;
						}
					}

				} catch (NotWritablePropertyException ex) {
					throw ex;
				}
			}
		}
		return mappedObject;
	}

	protected Object getColumnValue(ResultSet rs, int index, Field field)
			throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index, field.getType());
	}

	public Field getIdField() {
		return idField;
	}

	public void setIdField(Field idField) {
		this.idField = idField;
	}

}
