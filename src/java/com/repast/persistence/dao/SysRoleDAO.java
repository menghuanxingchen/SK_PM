package com.repast.persistence.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.persistence.entity.SysRole;
/**
 * 
 * Application name:  
 * Application describing:实体类
 * Copyright:Copyright copy;   
 * Company:  
 * @author xxx
 */
@Component("SysRoleDAO")
public class SysRoleDAO extends BaseDao
{
	private static  String  SQL_SYS_ROLE = "select *from SYS_ROLE where 1=1 ";
	private static  String  DELETE_SQL_SYS_ROLE = "delete from SYS_ROLE where 1=1 ";
	
	/**
     * 
     * 保存实体对象entity
     * @author 
     */
    public int saveSysRole(SysRole entity)
    {
        return this.saveNew(entity);
    }
    /**
     * 
     * 修改实体对象entity
     * @author 
     */
    public int updateSysRole(SysRole entity)
    {
        return this.saveUpdate(entity);
    }
    /**
     * 
     * 删除实体对象entity
     * @author 
     */    
    public int deleteSysRole(SysRole entity)
    {
        return this.saveRemove(entity);
    }
    /**
     * 
     * 根据实体对象Id返回entity
     * @author 
     */    
    public SysRole getSysRoleById(String id)
    {
        return this.getEntityById(id, SysRole.class);
    }
    
    /**
	 * 查询店内角色信息
	 * @param restaurantid/餐厅id
	 * @return
	 */
	public List<SysRole> queryRoleByRestaurantid(String restaurantid){
		String sql = SQL_SYS_ROLE+"AND RESTAURANTID=? AND enabled='1'";
		return super.queryForList(sql, new Object[]{restaurantid}, SysRole.class);
	}
	
	/**
	 * 查询店内角色分页
	 * @param restaurantid/餐厅id
	 * @return
	 */
	public Pagination<SysRole> queryRoleByRestaurantidforPage(String restaurantid,int pageIndex,int pageSize){
		String sql = SQL_SYS_ROLE+"AND RESTAURANTID=? AND enabled='1'";
		return super.getPage(sql, new Object[]{restaurantid}, SysRole.class, pageIndex, pageSize);
	}
	
	/**
	 * 删除角色信息
	 * @return
	 */
	public int deleteRoleById(String roid){
		String sql = DELETE_SQL_SYS_ROLE+"AND ID IN('"+roid+"') ";
		return super.executeSQL(sql, new Object[]{});
	}
    
}
