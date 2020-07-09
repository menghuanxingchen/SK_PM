package com.repast.core.uiview.format.impl;

import java.util.Map;

import com.repast.core.uiview.format.IConvert;

/**
 * 通过自定义Map数据进行值转换
 * @author luandong
 *
 */
public class UserMapConvertImpl extends IConvert {



	public UserMapConvertImpl(String scoureProName, String targetProName,Map userMap) {
		super();
		this.scoureProName = scoureProName;
		this.targetProName = targetProName;
		this.dataMap = userMap;
	}
	
	

	@Override
	public Map getConvertDataMap() {
		return this.dataMap;
	}



	@Override
	protected void initMap() {
		
	}

}
