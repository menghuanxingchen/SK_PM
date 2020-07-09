package com.repast.core.service;

import org.springframework.stereotype.Service;

@Service("sysResourceService")
public class SysResourceService {/*
	
	@Resource
	SysResourceDAO sysResourceDAO;
	
	*//**
     * 
     * 查询所有启用资源
     * @author 
     *//* 
	public List<SysResource> querySysResource(String type){
		
		return sysResourceDAO.querySysResource(type);
		
	}
	*//**
     * 
     * 查询所有启用资源-type
     * @author 
     *//* 
	public List<SysResource> querySysResourceByType(String type){
		
		return sysResourceDAO.querySysResourceBytype(type);
		
	}
	
	*//**
	 * 查询全部资源对象
	 * @param sysResource
	 * @return
	 *//*
	public List<SysResource> querySysResourceTree(SysResource sysResource) {
		return sysResourceDAO.querySysResource(sysResource);
	}
	
	*//**
	 * 根据Resource的ID查询数据
	 * @param id
	 * @return
	 *//*
	public SysResource querySysResourceById(String id){
		return sysResourceDAO.getSysResourceById(id);
	}
	
	*//**
	 * 检查ID是否重复
	 * 如果重复返回true
	 * @param sysResource
	 * @return
	 *//*
	public boolean checkResourceIdRepeat(SysResource sysResource) {
		
		if(sysResource.getId()==null)
			return false;
		else{
			SysResource entity =	sysResourceDAO.getSysResourceById(sysResource.getId());
			return entity==null?false:true;
		}
	}
	
	*//**
	 * 保存菜单实体
	 * @param sysResource
	 * @return
	 *//*
	public int addResource(SysResource sysResource,Processor processor) {
		if(sysResource.getId()==null||"".equals(sysResource.getId()))
			sysResource.setId(sysResourceDAO.getUUId());
		
		int flag = sysResourceDAO.saveSysResource(sysResource);
		Log log = new Log(LogConstant.SysResourceModel,LogConstant.OP_INSERT,"新增["+sysResource.getResourcename()+"]菜单!",processor);
		log.putOldAndNewObj(null, sysResource);
		log.saveLog();
		
		return flag;
	}
	
	*//**
	 * 更新资源数据
	 * @param sysResource
	 * @param processor
	 * @return
	 *//*
	public int saveEditResource(SysResource sysResource, Processor processor) {
		SysResource entity =	sysResourceDAO.getSysResourceById(sysResource.getId());
		int flag = sysResourceDAO.updateSysResource(sysResource);
		
		Log log = new Log(LogConstant.SysResourceModel,LogConstant.OP_UPDATE,"修改["+sysResource.getResourcename()+"]菜单!",processor);
		log.putOldAndNewObj(entity, sysResource);
		log.saveLog();
		
		return flag;
	}
	
	*//**
	 * 删除资源数据
	 * @param sysResource
	 * @param processor
	 * @return
	 *//*
	public int deleteResource(SysResource sysResource, Processor processor) {
		sysResource = sysResourceDAO.getSysResourceById(sysResource.getId());
		SysResource entity =	sysResource.clone();
		int flag = sysResourceDAO.deleteSysResource(entity);
		
		Log log = new Log(LogConstant.SysResourceModel,LogConstant.OP_DELETE,"删除["+sysResource.getResourcename()+"]菜单!",processor);
		log.putOldAndNewObj(entity, sysResource);
		log.saveLog();
		
		return flag;		
	}
	

*/}
