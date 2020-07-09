package com.repast.core.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

public class DataModel {
	//返回对象List
	private List<?> datas;
	//返回对象List<Map>
	private List<Map<Object,Object>> objMapList;
	//页面初始化对象集合
	private Map<Object,Object> objectMap;
	//分页属性
	private Pagination<?> pagination;
	//验证信息
	private BindingResult result;
	//错误标识
	private boolean success;
	//错误信息
	private String message;
	
	public DataModel(){
		objectMap = new HashMap<Object, Object>();
		success = true;
	}

	public Pagination<?> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<?> pagination) {
		this.pagination = pagination;
	}

	public BindingResult getResult() {
		return result;
	}

	public void setResult(BindingResult result) {
		this.result = result;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

	public List<Map<Object, Object>> getObjMapList() {
		return objMapList;
	}

	public void setObjMapList(List<Map<Object, Object>> objMapList) {
		this.objMapList = objMapList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	public Map<Object, Object> getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(Map<Object, Object> objectMap) {
		objectMap = objectMap;
	}

	public void put(String key,Object o){
		this.objectMap.put(key, o);
	}

}
