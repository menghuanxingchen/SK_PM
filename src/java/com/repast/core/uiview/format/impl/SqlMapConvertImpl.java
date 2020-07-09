package com.repast.core.uiview.format.impl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.repast.core.tool.ApplicationContextUtil;
import com.repast.core.uiview.Option;
import com.repast.core.uiview.UIService;
import com.repast.core.uiview.format.IConvert;

/**
 * 通过sql方式进行值转换
 * @author luandong
 *
 */
public class SqlMapConvertImpl extends IConvert {


	String tableName;
	String textCol;
	String valueCol;
	String sql;
	
	
	public SqlMapConvertImpl(String scoureProName, String targetProName,String tableName,String textCol,String valueCol,String sql) {
		
		
		super();
		this.scoureProName = scoureProName;
		this.targetProName = targetProName;
		
		this.tableName = tableName;
		this.textCol = textCol;
		this.valueCol = valueCol;
		this.sql = sql;
		initMap();	
	}

	protected void initMap(){
		
		UIService uIService = (UIService)ApplicationContextUtil.getBean("UIService");
		List<Option> list = uIService.queryParameterListByTable(tableName,textCol, valueCol,sql);
		Map dataMap = new HashMap();
		for(Option op:list){
			dataMap.put(op.getValue(), op.getText());
		}
		this.dataMap = dataMap;
	}

	@Override
	protected Map getConvertDataMap() {
		
		return dataMap;
	}

}
