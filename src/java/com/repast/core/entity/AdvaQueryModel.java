package com.repast.core.entity;

import java.util.Map;
/**
 * @author 
 * 高级查询组件model
 * */
public class AdvaQueryModel {
	
	/**
	 * 高级查询的查询项
	 */
	private String property;
	
	/**
	 * 维度大类的显示在页面上的值
	 */
	private String fClassify;
	
	/**
	 * 维度的页面上显示的值和实际值(map的key/value)
	 */
	private Map<String, String> cClassify;
	
	/**
	 * 维度是否多选
	 * 0是不多选、1是多选
	 */
	private String optionality;
	
	/**
	 * 维度保持位置不动
	 * 0是动、1是不动
	 */
	private String keepstill;
	
	/**
	 * 维度默认值，一般与keepstill一起使用
	 */
	private String defaultval;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getfClassify() {
		return fClassify;
	}
	public void setfClassify(String fClassify) {
		this.fClassify = fClassify;
	}
	public Map<String, String> getcClassify() {
		return cClassify;
	}
	public void setcClassify(Map<String, String> cClassify) {
		this.cClassify = cClassify;
	}
	public String getOptionality() {
		return optionality;
	}
	public void setOptionality(String optionality) {
		this.optionality = optionality;
	}
	public String getKeepstill() {
		return keepstill;
	}
	public void setKeepstill(String keepstill) {
		this.keepstill = keepstill;
	}
	public String getDefaultval() {
		return defaultval;
	}
	public void setDefaultval(String defaultval) {
		this.defaultval = defaultval;
	}
	
	
	
}
