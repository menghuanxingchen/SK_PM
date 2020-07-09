package com.repast.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.repast.core.system.BaseDao;
import com.repast.core.util.JDBCHelper;
import com.repast.persistence.entity.SysResource;
/**
 * 
 * Application name:  
 * Application describing:实体类
 * Copyright:Copyright copy;   
 * Company:  
 * @author xxx
 */
@Component("SysResourceDAO")
public class SysResourceDAO extends BaseDao
{
	private static String QUERY_SYS_RESOURCE = "SELECT *FROM SYS_RESOURCE WHERE 1=1 ";
	/**
     * 
     * 保存实体对象entity
     * @author 
     */
    public int saveSysResource(SysResource entity)
    {
        return this.saveNew(entity);
    }
    /**
     * 
     * 修改实体对象entity
     * @author 
     */
    public int updateSysResource(SysResource entity)
    {
        return this.saveUpdate(entity);
    }
    /**
     * 
     * 删除实体对象entity
     * @author 
     */    
    public int deleteSysResource(SysResource entity)
    {
        return this.saveRemove(entity);
    }
    /**
     * 
     * 根据实体对象Id返回entity
     * @author 
     */    
    public SysResource getSysResourceById(String id)
    {
        return this.getEntityById(id, SysResource.class);
    }
    /**
     * 
     * 查询所有启用资源-type
     * @author 
     */ 
    public List<SysResource> querySysResource(String type){
    	
    	String sql = QUERY_SYS_RESOURCE+" AND ENABLED='1'";
    	return this.queryForList(sql, new Object[]{}, SysResource.class);
    }
    
    /**
     * 
     * 查询所有启用资源-all
     * @author 
     */ 
    public List<SysResource> querySysResourceBytype(String type){
    	
    	String sql = QUERY_SYS_RESOURCE+" AND ENABLED='1' AND TYPE=? order by sort desc";
    	return this.queryForList(sql, new Object[]{type}, SysResource.class);
    }
    
    /**
     * 
     * 根据id查询启用资源
     * type区分手机端or店内端
     * @author 
     */ 
    public SysResource querySysResourceByidAndtype(String id,String type){
    	
    	String sql = QUERY_SYS_RESOURCE+" AND ENABLED='1' AND id=? and TYPE=?";
    	return this.queryObject(sql, new Object[]{id,type}, SysResource.class);
    }
    
    
    
    private String queryTable = "SELECT T.* FROM SYS_RESOURCE T WHERE 1=1 ";
    
    /**
     * 构造资源查询条件
     * @param entity
     * @param sql
     * @return
     */
    private JDBCHelper makeSearch(SysResource entity, String sql)
    {
        JDBCHelper jdbcHelper = new JDBCHelper(sql);
        if(entity!=null)
        {
        		jdbcHelper.putParam(JDBCHelper.AND, "T.TYPE", JDBCHelper.EQ, entity.getType());
        		jdbcHelper.putParam(JDBCHelper.AND, "T.ID", JDBCHelper.EQ, entity.getId());
        		jdbcHelper.putParam(JDBCHelper.AND, "T.PARENTID", JDBCHelper.EQ, entity.getParentid());
        		
        		jdbcHelper.putOrder(entity);
        		jdbcHelper.putOrder("T.SORT desc");
        }
        return jdbcHelper;
    }
    
    /**
     * 查询资源信息
     * @param sysResource
     * @return
     */
	public List<SysResource> querySysResource(SysResource sysResource) {
		JDBCHelper helper = this.makeSearch(sysResource, queryTable);
		return this.queryForList(helper.getSql(),helper.getParam(), SysResource.class);
	}
    
}
