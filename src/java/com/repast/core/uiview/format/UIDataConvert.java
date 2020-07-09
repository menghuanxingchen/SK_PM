package com.repast.core.uiview.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.repast.core.system.Pagination;

/**
 * 展示数据的转换处理类
 * 支持原始值转换到特定的字段
 * @author luandong
 *
 * @param <T>
 */
public class UIDataConvert<T> {

	private List<IConvert> config = new ArrayList<IConvert>();
	
	private T convert(T obj, String scoureFiled, String targetString,Map<Object,Object> map) {
		
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
		Object key = bw.getPropertyValue(scoureFiled);
		Object value = map.containsKey(key)?map.get(key):null;
		bw.setPropertyValue(targetString, value);
		
		return obj;
	}
	
	private T batchConvert(T obj) {
		
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
		
		Map<Object,Object> map = null;
		Object key = null;
		Object value = null;
		
		for(IConvert iconvert:config){
			map = iconvert.getConvertDataMap();
			key = bw.getPropertyValue(iconvert.scoureProName);
			value = map.containsKey(key)?map.get(key):key;
			bw.setPropertyValue(iconvert.targetProName, value);
		}
		return obj;
	}

	/**
	 * 将List<T>形式数据进行值码转换
	 * @param list
	 * @return
	 */
	public List<T> format(List<T> list) {
		for (T obj : list) {
			for(IConvert iconvert:config){
				convert(obj, iconvert.scoureProName,iconvert.targetProName,iconvert.getConvertDataMap());
			}
		}
		return list;
	}
	
	/**
	 * 将obj数据进行转换
	 * @param obj
	 * @return
	 */
	public T format(T obj) {
		for(IConvert iconvert:config){
			convert(obj, iconvert.scoureProName,iconvert.targetProName,iconvert.getConvertDataMap());
		}
		return obj;
	}
	
	/**
	 * 将分页数据进行值转换
	 * @param list
	 * @return
	 */
	public Pagination<T> format(Pagination<T> list){
		for (T obj : list.getRecords()) {
			
			batchConvert(obj);
			
			/*
			for(IConvert iconvert:config){
				convert(obj, iconvert.scoureProName,iconvert.targetProName,iconvert.getConvertDataMap());
			}
			*/
		}
		return list;
	}
	
	/**
	 * 设置转换处理器
	 * @param iConvert
	 */
	public void addConvert(IConvert iConvert){
		config.add(iConvert);
	}
	
	/*
	public static void main(String[] args){
		UIConvert<SysCodelist> ui = new UIConvert<SysCodelist>();
			
			Map tempMap = new HashMap();
			tempMap.put("001", "abc");
			tempMap.put("002", "1111");
			
			IConvert convert = new UserMapConvertImpl("kindcode", "kindname",tempMap);
			IConvert convert2 = new CodeListConvertImpl(scoureProName, targetProName, "XXXX");
			
			ui.addConvert(convert);
			ui.addConvert(iConvert2);
			
			SysCodelist codeList = new SysCodelist();
			codeList.setKindcode("000");
	
			ui.format(codeList);
			System.out.println("new kindname is = "+codeList.getKindname());
	}
	*/
}
