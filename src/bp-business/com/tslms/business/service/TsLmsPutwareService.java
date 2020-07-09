package com.tslms.business.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.repast.core.system.Pagination;
import com.tslms.persistence.dao.TsLmsPutwareDAO;
import com.tslms.persistence.entity.TsLmsPutware;
@Service("TsLmsPutwareService")
public class TsLmsPutwareService {

	@Resource(name="TsLmsPutwareDAO")
	private TsLmsPutwareDAO tsLmsPutwareDAO;
	
	
	/**
	 * 
	 * @param entity
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Pagination<TsLmsPutware> queryTsLmsPutwareList(TsLmsPutware entity,int pageIndex,int pageSize){
		return tsLmsPutwareDAO.queryTsLmsPutwareList(entity, pageIndex, pageSize);
	}

	
	/**
	 * 保存实体对象entity
	 * @author 
	 */
	public int saveTsLmsPutware(TsLmsPutware entity) {
		return tsLmsPutwareDAO.saveTsLmsPutware(entity);
	}
	
	
	/**
	 * 修改实体对象entity
	 * @author 
	 */
	public int updateTsLmsPutware(TsLmsPutware entity) {
		return tsLmsPutwareDAO.updateTsLmsPutware(entity);
	}
	
	/**
	 * 删除实体对象entity
	 * @author 
	 */
	public int deleteTsLmsPutware(TsLmsPutware entity) {
		return tsLmsPutwareDAO.deleteTsLmsPutware(entity);
	}
	
	/**
	 * 根据实体对象Id返回entity
	 * @author 
	 */
	public TsLmsPutware getTsLmsPutwareById(String id) {
		return tsLmsPutwareDAO.getTsLmsPutwareById(id);
	}
	
}
