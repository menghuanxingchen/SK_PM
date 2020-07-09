package com.repast.core.system;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.entity.EntityReflectData;
import com.repast.core.entity.PojoRowMapper;
import com.repast.core.exception.FrameworkDaoException;
import com.repast.core.handler.SpringServiceFactory;
import com.repast.core.util.DateUtil;

public class BaseDao {
	private static Logger logger = Logger.getLogger("logger");
	private static Map<String, EntityReflectData> entityReflectDatas = new HashMap<String, EntityReflectData>();

	public JdbcTemplate getJdbcTemplate(String jdbcTemplate) {
		return SpringServiceFactory.getBean(jdbcTemplate);
	}

	public JdbcTemplate getJdbcTemplate() {
		return SpringServiceFactory.getBean("jdbcTemplate");
	}

	public String getUUId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	public String firstLetterToUpper(String str) {
		char[] array = str.toCharArray();
		array[0] -= 32;
		return String.valueOf(array);
	}

	private <T> EntityReflectData createEntityReflectData(Class<T> pojoClass) {
		EntityReflectData data = new EntityReflectData();
		data.setTableName(getTableName(pojoClass));
		data.setPrimaryKeyColumnName(getIdFieldName(pojoClass));
		Field keyField = getIdField(pojoClass);
		if (keyField != null) {
			data.setPrimaryKeyFieldName(keyField.getName());
		} else {
			throw new FrameworkDaoException(pojoClass.getName() + "没有设置主键！");
		}
		data.setInsertSql(getInsertSql(pojoClass));
		data.setInsertFields(getInsertFields(pojoClass));
		data.setUpdateSql(getUpdateSql(pojoClass));
		data.setUpdateSqlFields(getUpdateFields(pojoClass));
		data.setDeleteSql(getDeleteSql(pojoClass));
		return data;
	}

	private <T> String[] getInsertFields(Class<T> pojoClass) {
		return getFieldNames(pojoClass);
	}
	
	private <T> String[] getDynamicUpdateFields(Class<T> pojoClass,HashMap<String,String> dynamicCol) {
		String[] fields = getFieldNames(pojoClass);
		Field idField = this.getIdField(pojoClass);
		List<String> obj = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			if ((!fields[i].equalsIgnoreCase(idField.getName()))&&dynamicCol.containsKey(fields[i])) {
				obj.add(fields[i]);
			}
		}
		obj.add(idField.getName());
		String[] rts = new String[obj.size()];
		return obj.toArray(rts);
	}

	private <T> String[] getUpdateFields(Class<T> pojoClass) {
		String[] fields = getFieldNames(pojoClass);
		Field idField = this.getIdField(pojoClass);
		List<String> obj = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].equalsIgnoreCase(idField.getName())) {
				obj.add(fields[i]);
			}
		}
		obj.add(idField.getName());
		String[] rts = new String[obj.size()];
		return obj.toArray(rts);
	}

	private <T> String getInsertSql(Class<T> pojoClass) {
		String table = this.getTableName(pojoClass);
		String sql = "insert into " + table + "(";
		String cname = "";
		String wp = "";
		String[] columns = getColumnNames(pojoClass);
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			if (column != null) {
				cname = cname + "," + column+"";
				wp = wp + ",?";
			}
		}
		return sql + cname.substring(1) + ")values(" + wp.substring(1) + ")";
	}

	private <T> String[] getFieldNames(Class<T> pojoClass) {
		List<String> columns = new ArrayList<String>();
		Field[] fields = pojoClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String column = getColumnName(fields[i]);
			if (column != null && !"serialVersionUID".equals(column)) {
				columns.add(fields[i].getName());
			}
		}
		String[] rts = new String[columns.size()];
		return columns.toArray(rts);
	}

	private <T> String[] getDynamicColumnNames(Class<T> pojoClass,HashMap<String,String> dynamicColMap) {
		List<String> columns = new ArrayList<String>();
		Field[] fields = pojoClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String column = getColumnName(fields[i]);
			if (column != null && !"serialVersionUID".equals(column) && dynamicColMap.containsKey(fields[i].getName())) {
				columns.add(column);
			}
		}
		String[] rts = new String[columns.size()];
		return columns.toArray(rts);
	}
	
	private <T> String[] getColumnNames(Class<T> pojoClass) {
		List<String> columns = new ArrayList<String>();
		Field[] fields = pojoClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String column = getColumnName(fields[i]);
			if (column != null && !"serialVersionUID".equals(column)) {
				columns.add(column);
			}
		}
		String[] rts = new String[columns.size()];
		return columns.toArray(rts);
	}

	private String getColumnName(Field field) {
		Annotation[] ats = field.getAnnotations();
		if (ats.length > 0) {
			for (int y = 0; y < ats.length; y++) {
				if (ats[y] instanceof Transient) {
					return null;
				} else if (ats[y] instanceof Column) {
					return ((Column) ats[y]).name();
				}
			}
		}
		return field.getName();
	}
	
	private HashMap<String, String> toUpdateColMap(String[] updateColArray){
		HashMap<String,String> colMap = new HashMap<String,String>();
		for(int i=0;i<updateColArray.length;i++){
			colMap.put(updateColArray[i],updateColArray[i]);
		}
		return colMap;
	}
	
	private <T> String getDynamicUpdateSql(Class<T> pojoClass,HashMap<String,String> updateColMap) {
		
		String table = this.getTableName(pojoClass);
		String keyField = this.getIdFieldName(pojoClass);
		if (keyField == null) {
			throw new RuntimeException(pojoClass.toString() + "");
		}
		String sql = "update " + table + " set ";
		String cname = "";
		String[] columns = getDynamicColumnNames(pojoClass,updateColMap);
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			if (column != null && !keyField.equalsIgnoreCase(column) ) {
				cname = cname + "," + column + "=?";
			}
		}
		return sql + cname.substring(1) + " where " + keyField + "=?";
	}

	private <T> String getUpdateSql(Class<T> pojoClass) {
		String table = this.getTableName(pojoClass);
		String keyField = this.getIdFieldName(pojoClass);
		if (keyField == null) {
			throw new RuntimeException(pojoClass.toString() + "");
		}
		String sql = "update " + table + " set ";
		String cname = "";
		String[] columns = getColumnNames(pojoClass);
		for (int i = 0; i < columns.length; i++) {
			String column = columns[i];
			if (column != null && !keyField.equalsIgnoreCase(column)) {
				cname = cname + "," + column + "=?";
			}
		}
		return sql + cname.substring(1) + " where " + keyField + "=?";
	}

	private <T> String getDeleteSql(Class<T> pojoClass) {
		String table = this.getTableName(pojoClass);
		String keyField = this.getIdFieldName(pojoClass);
		if (keyField == null) {
			throw new RuntimeException(pojoClass.toString() + "");
		}
		return "delete from  " + table + " where " + keyField + "=?";
	}

	private String getTableName(Class<?> pojoClass) {
		Entity entity = pojoClass.getAnnotation(Entity.class);
		if (entity != null) {
			return entity.name();
		} else {
			return null;
		}
	}

	private Field getIdField(Class<?> pojoClass) {
		Field[] fields = pojoClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Annotation[] ats = field.getAnnotations();
			if (ats.length > 0) {
				for (int y = 0; y < ats.length; y++) {
					if (ats[y] instanceof ID) {

						return field;
					}
				}
			}
		}
		return null;
	}

	private String getIdFieldName(Class<?> pojoClass) {
		Field field = getIdField(pojoClass);
		if (field != null) {
			Annotation[] ats = field.getAnnotations();
			if (ats.length > 0) {
				for (int y = 0; y < ats.length; y++) {
					if (ats[y] instanceof Column) {
						Column c = field.getAnnotation(Column.class);
						return c.name();
					}
				}
			}
			return field.getName();
		}
		return null;
	}

	public int[] saveBatchObjects(List<?> list) {
		Iterator<?> iter = list.iterator();
		String sql = "";
		List<Object[]> lis = new ArrayList<Object[]>();
		while (iter.hasNext()) {
			Object entity = iter.next();
			EntityReflectData reflectData = entityReflectDatas.get(entity.getClass().getName());
			if (reflectData == null) {
				reflectData = createEntityReflectData(entity.getClass());
				synchronized (entityReflectDatas) {
					entityReflectDatas.put(entity.getClass().getName(), reflectData);
				}
			}
			sql = reflectData.getInsertSql();
			BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
			List<Object> obj = new ArrayList<Object>();
			String[] fields = reflectData.getInsertFields();
			for (int i = 0; i < fields.length; i++) {
				if (!"serialVersionUID".equals(fields[i])) {
					obj.add(bw.getPropertyValue(fields[i]));
				}
			}
			lis.add(obj.toArray());
		}
		if (list != null && list.size() > 0) {
			try {
				logger.info("sql saveBatchObjects："+sql);
				logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("sql异常！");
			}
			return getJdbcTemplate().batchUpdate(sql, lis);
		}
		return null;
	}

	public int saveNew(Object entity) {
		EntityReflectData reflectData = entityReflectDatas.get(entity.getClass().getName());
		if (reflectData == null) {
			reflectData = createEntityReflectData(entity.getClass());
			synchronized (entityReflectDatas) {
				entityReflectDatas.put(entity.getClass().getName(), reflectData);
			}
		}
		String sql = reflectData.getInsertSql();
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
		List<Object> obj = new ArrayList<Object>();
		String[] fields = reflectData.getInsertFields();
		for (int i = 0; i < fields.length; i++) {
			if (!"serialVersionUID".equals(fields[i])) {
				obj.add(bw.getPropertyValue(fields[i]));
			}
		}
		try {
			logger.info("sql saveNew："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		return update(sql, obj.toArray());
	}

	public int[] updateBatchObjects(List<?> list) {
		Iterator<?> iter = list.iterator();
		String sql = "";
		List<Object[]> lis = new ArrayList<Object[]>();
		while (iter.hasNext()) {
			Object entity = iter.next();
			EntityReflectData reflectData = entityReflectDatas.get(entity.getClass().getName());
			if (reflectData == null) {
				reflectData = createEntityReflectData(entity.getClass());
				synchronized (entityReflectDatas) {
					entityReflectDatas.put(entity.getClass().getName(), reflectData);
				}
			}
			sql = reflectData.getUpdateSql();
			BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
			List<Object> obj = new ArrayList<Object>();
			String[] fields = reflectData.getUpdateSqlFields();
			for (int i = 0; i < fields.length; i++) {
				if (!"serialVersionUID".equals(fields[i])) {
					obj.add(bw.getPropertyValue(fields[i]));
				}
			}
			lis.add(obj.toArray());
		}
		if (list != null && list.size() > 0) {
			try {
				logger.info("sql updateBatchObjects："+sql);
				logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("sql异常！");
			}
			return getJdbcTemplate().batchUpdate(sql, lis);
		}
		return null;
	}

	public int saveUpdate(Object entity) {
		EntityReflectData reflectData = entityReflectDatas.get(entity.getClass().getName());
		if (reflectData == null) {
			reflectData = createEntityReflectData(entity.getClass());
			synchronized (entityReflectDatas) {
				entityReflectDatas.put(entity.getClass().getName(), reflectData);
			}
		}
		String sql = reflectData.getUpdateSql();
		String[] fields = reflectData.getUpdateSqlFields();
		Field[] field = entity.getClass().getDeclaredFields();
		ArrayList<String> list = new ArrayList<String>();
		((BaseEntity)entity).setDynamic_update_fileld(null);
        for (int j = 0; j < field.length; j++) {
            if(!field[j].isAccessible()){
                field[j].setAccessible(true);
            }
            try {
            	if(field[j].get(entity)!=null && !"serialVersionUID".equals(field[j].getName())){
            		list.add(field[j].getName().toString());
            	}
			} catch (IllegalArgumentException e) {
				System.out.println("saveUpdate 方法传递了一个不合法或不正确的参数。你看看传值的方法是否参数不正确");
			} catch (IllegalAccessException e) {
				System.out.println("saveUpdate IllegalAccessException");
			}
        }
        String[] dynamicUpdateFileld = new String[list.size()];
        for(int n=0;n<list.size();n++){
        	dynamicUpdateFileld[n] = list.get(n);
        }
        ((BaseEntity)entity).setDynamic_update_fileld(dynamicUpdateFileld);
		//luandong 2015-2-27增加dynamic update处理
		if(entity instanceof BaseEntity&&((BaseEntity)entity).getDynamic_update_fileld()!=null){
			HashMap<String,String> map = toUpdateColMap(((BaseEntity)entity).getDynamic_update_fileld());
			sql = getDynamicUpdateSql(entity.getClass(),map);
			fields = getDynamicUpdateFields(entity.getClass(), map);
		}
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
		List<Object> obj = new ArrayList<Object>();
		
		for (int i = 0; i < fields.length; i++) {
			obj.add(bw.getPropertyValue(fields[i]));
		}
		try {
			logger.info("sql saveUpdate："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		return update(sql, obj.toArray());
	}

	public int saveRemove(Object entity) {
		EntityReflectData reflectData = entityReflectDatas.get(entity.getClass().getName());
		if (reflectData == null) {
			reflectData = createEntityReflectData(entity.getClass());
			synchronized (entityReflectDatas) {
				entityReflectDatas.put(entity.getClass().getName(), reflectData);
			}
		}
		String sql = this.getDeleteSql(entity.getClass());
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
		List<Object> obj = new ArrayList<Object>();
		obj.add(bw.getPropertyValue(reflectData.getPrimaryKeyFieldName()));
		try {
			logger.info("sql saveRemove："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		return update(sql, obj.toArray());
	}

	public <T> T getEntityById(String id, Class<T> pojoClass) {
		EntityReflectData reflectData = entityReflectDatas.get(pojoClass.getName());
		if (reflectData == null) {
			reflectData = createEntityReflectData(pojoClass);
			synchronized (entityReflectDatas) {
				entityReflectDatas.put(pojoClass.getName(), reflectData);
			}
		}

		String table = getTableName(pojoClass);
		if (table != null) {
			String fieldName = this.getIdFieldName(pojoClass);
			if (fieldName != null) {
				String sql = "SELECT * FROM " + table + " WHERE " + fieldName + "='" + id + "'";
				return queryObject(sql, pojoClass);
			} else {
				throw new FrameworkDaoException(pojoClass.getName() + "娌￠敓鏂ゆ嫹閿熷�瀹欼D閿熸枻鎷锋伅");
			}
		} else {
			throw new FrameworkDaoException(pojoClass.getName() + "娌￠敓鏂ゆ嫹閿熷�瀹欵ntity閿熸枻鎷锋伅");
		}
	}

	public int executeSQL(String sql) {
		try {
			logger.info("sql executeSQL："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		return update(sql, null);
	}

	public int executeSQL(String sql, Object[] args) {
		try {
			logger.info("sql executeSQL："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		return update(sql, args);
	}

	public <T> T queryObject(String sql, Class<T> pojoClass) {
		return queryObject(sql, null, pojoClass);
	}

	public <T> T queryObject(String sql, Object[] args, Class<T> pojoClass) {
		List<T> rs = queryForList(sql, args, pojoClass);
		if (rs.size() > 0) {
			return rs.get(0);
		} else {
			//return BeanUtils.instantiate(pojoClass);
			return null;
		}
	}

	public <T> List<T> queryForList(String sql, Class<T> pojoClass) {
		return queryForList(sql, null, pojoClass);
	}

	public <T> List<T> queryForList(String sql, Object[] args, Class<T> pojoClass) {
		List<T> rt = getJdbcTemplate().query(sql, args, new PojoRowMapper<T>(pojoClass));
		return rt;
	}

	private int update(String sql, Object[] args) {
		try {
			logger.info("sql update："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		int rt = this.getJdbcTemplate().update(sql, args);
		return rt;
	}

	public int queryForInt(String sql, Object[] args) {
		return this.getJdbcTemplate().queryForInt(sql, args);
	}

	public int[] batchUpdate(final String[] sql) {
		try {
			logger.info("sql batchUpdate："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		int[] rts = this.getJdbcTemplate().batchUpdate(sql);
		return rts;
	}

	public int[] batchUpdate(final String sql, List<Object[]> batchArgs) {
		try {
			logger.info("sql batchUpdate："+sql);
			logger.info("sql操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("sql异常！");
		}
		int[] rts = this.getJdbcTemplate().batchUpdate(sql, batchArgs);
		return rts;
	}

	public SqlRowSet queryForRowSet(String sql) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(sql);
		return rs;
	}

	public SqlRowSet queryForRowSet(String sql, Object[] params) {
		SqlRowSet rs = this.getJdbcTemplate().queryForRowSet(sql, params);
		return rs;
	}

	public <T> Pagination<T> getPage(String sql, Object[] params, Class<T> pojoClass,Integer pageIndex, int pageSize) {
		Long count = (long) queryForInt("select count(*) from (" + sql + ") as dd", params);
		List<T> results = queryForListPagger(sql, params, pojoClass, (int) pageIndex,Pagination.getPageSize(pageSize));
		Pagination<T> page = new Pagination<T>(results, count, pageSize, pageIndex);
		return page;
	}

//	private <T> List<T> queryForListPagger(String sql, Object[] params, Class<T> pojoClass,
//			int pageIndex, int pageSize) {
//		if (pageSize != -1) {
//			sql = "SELECT * FROM (SELECT Z.*, ROWNUM RN FROM (" + sql + ") Z WHERE ROWNUM <= "
//					+ ((pageIndex) * pageSize) + ") WHERE RN >= "
//					+ ((pageIndex - 1) * pageSize + 1);
//		}
//
//		return queryForList(sql, params, pojoClass);
//	}
//
//	private <T> List<T> queryForListPagger(String sql, Class<T> pojoClass, int pageIndex,
//			int pageSize) {
//		if (pageSize != -1) {
//			sql = "SELECT * FROM (SELECT Z.*, ROWNUM RN FROM (" + sql + ") Z WHERE ROWNUM <= "
//					+ ((pageIndex) * pageSize) + ") WHERE RN >= "
//					+ ((pageIndex - 1) * pageSize + 1);
//		}
//		return queryForList(sql, pojoClass);
//	}
	
	private <T> List<T> queryForListPagger(String sql, Object[] params, Class<T> pojoClass,int pageIndex,int pageSize)
    {
		if (pageSize != -1) {
			sql = "" + sql +" LIMIT " + ((pageIndex-1) * pageSize)+ "," + (pageSize) ;
		}
       return queryForList(sql,params, pojoClass);
    }
 
}
