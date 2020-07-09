package com.repast.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.repast.core.system.BaseDao;
import com.repast.persistence.entity.SysRoleResource;
/**
 * 
 * Application name:  
 * Application describing:实体类
 * Copyright:Copyright copy;   
 * Company:  
 * @author xxx
 */
@Component("SysRoleResourceDAO")
public class SysRoleResourceDAO extends BaseDao
{
	private static String QUERY_SYS_ROLE_RESOURCE = "SELECT *FROM SYS_ROLE_RESOURCE WHERE 1=1";
	private static String DELETE_SYS_ROLE_RESOURCE = "DELETE FROM SYS_ROLE_RESOURCE WHERE 1=1";
	/**
     * 
     * 保存实体对象entity
     * @author 
     */
    public int saveSysRoleResource(SysRoleResource entity)
    {
        return this.saveNew(entity);
    }
    /**
     * 
     * 修改实体对象entity
     * @author 
     */
    public int updateSysRoleResource(SysRoleResource entity)
    {
        return this.saveUpdate(entity);
    }
    /**
     * 
     * 删除实体对象entity
     * @author 
     */    
    public int deleteSysRoleResource(SysRoleResource entity)
    {
        return this.saveRemove(entity);
    }
    /**
     * 
     * 根据实体对象Id返回entity
     * @author 
     */    
    public SysRoleResource getSysRoleResourceById(String id)
    {
        return this.getEntityById(id, SysRoleResource.class);
    }
    
    /**
     * 
     * 根据实体对象Id返回List
     * @author 
     */  
    public List<SysRoleResource> querySysRoleResourceByRoleId(String roleId){
    	String sql = QUERY_SYS_ROLE_RESOURCE+" AND ROLEID=?";
    	return this.queryForList(sql, new Object[]{roleId}, SysRoleResource.class);
    }
    
    public int deleteSysRoleResourceByRoleId(String roleId){
    	String sql = DELETE_SYS_ROLE_RESOURCE+" AND ROLEID=?";
    	return this.executeSQL(sql, new Object[]{roleId});
    }
    
}
