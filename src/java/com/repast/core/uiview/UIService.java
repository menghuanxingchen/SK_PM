package com.repast.core.uiview;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("UIService")
public class UIService {

	@Resource
	private UIDAO sysCodelistDAO;

	/**
	 * 根据
	 * @param config
	 * @return
	 */
	public List<Option> queryParameterListByTable(SelectConfig config) {
		return sysCodelistDAO.queryParameterListByTable(config.getTableName(),config.getTextCol(), config.getValueCol(),config.getSql());
	}
	
	public List<Option> queryParameterListByTable(String tableName,String textCol,String valueCol,String sql) {
		return sysCodelistDAO.queryParameterListByTable(tableName,textCol, valueCol,sql);
	}
}
