package com.repast.core.uiview;

import java.util.List;

/**
 * 前台SELECT控件封装处理
 * @author luandong
 *
 */
public class Select<T> {
	private String id;
	private String selectedValue;
	private List<T> options;
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getSelectedValue() {
		return selectedValue;
	}



	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}



	public List<T> getOptions() {
		return options;
	}



	public void setOptions(List<T> options) {
		this.options = options;
	}



	public void putOptions(List<T> opList,String selectVal){
		this.options = opList;
		this.selectedValue = selectVal;
	}
}
