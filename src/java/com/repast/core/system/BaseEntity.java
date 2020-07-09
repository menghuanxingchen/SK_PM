package com.repast.core.system;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mysql.jdbc.StringUtils;

public class BaseEntity {
	/**
	 * 排序串
	 */
	private String base_order_string;
	
	private String[] dynamic_update_fileld;

	
	
	/**
	 * @description 
	 * @author lingy
	 * @return
	 */
	public String cacheEntityParams(){
		Object entity = this;
		Class<?> clazz = entity.getClass();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("");
		/*获取试题属性*/
		Field[] fieldlist = clazz.getDeclaredFields();
		for (int i = 0; i < fieldlist.length; i++) {
			Field field = fieldlist[i];
			String fieldName = field.getName();
			if("serialVersionUID".equals(fieldName)){
				continue;
			}
			/*根据实体属性获得有值属性的字符串*/
			try {
				PropertyDescriptor fieldNamePD = new PropertyDescriptor(fieldName, clazz);
				Method getfieldValueMethod = fieldNamePD.getReadMethod();
				String classname = clazz.getSimpleName().toLowerCase();
				Object fieldValue = getfieldValueMethod.invoke(entity);
				if(fieldValue!=null && !"".equals(fieldValue.toString().trim())){
					stringBuffer.append(classname+"."+fieldName+"="+fieldValue).append("|");
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		String orderStr = this.getBase_order_string();
		if(!StringUtils.isNullOrEmpty(orderStr)){
			stringBuffer.append("orderstring"+orderStr).append("|");
		}
		String paramscaner = stringBuffer.toString();
		int lastindex = paramscaner.lastIndexOf("|");
		if(lastindex != -1){
			paramscaner = paramscaner.substring(0, lastindex);
		}
		paramscaner = "[" + paramscaner + "]";
		return paramscaner;
	}


	public String getBase_order_string() {
		return base_order_string;
	}


	public void setBase_order_string(String base_order_string) {
		this.base_order_string = base_order_string;
	}


	public String[] getDynamic_update_fileld() {
		return dynamic_update_fileld;
	}


	public void setDynamic_update_fileld(String[] dynamic_update_fileld) {
		this.dynamic_update_fileld = dynamic_update_fileld;
	}


	
	
}
