package com.repast.core.uiview.format;

import java.util.Map;

/**
 * 代码转换基础类
 * 此类为转换器定义类，支持后续扩展
 * TODO:此方法可以扩展为双向键值结构，以支持导入等业务处理
 * @author luandong
 *
 */
public abstract class IConvert {
	
	protected String scoureProName;
	protected String targetProName;
	protected Map dataMap;
	
	/**
	 * 初始代码数据
	 */
	protected abstract void initMap();
	
	/**
	 * 取得转换映射数据
	 * @return
	 */
	protected abstract Map getConvertDataMap();

}
