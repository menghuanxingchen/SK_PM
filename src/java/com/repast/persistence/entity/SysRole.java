
package com.repast.persistence.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;

/**
 * 
 * Application name:  
 * Application describing:实体类
 * Copyright:Copyright copy;   
 * Company:  
 * @author xxx
 */
@Entity(name="SYS_ROLE")
public class SysRole extends BaseEntity implements Serializable, Cloneable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8876581301281857880L;

	public SysRole clone() {

		SysRole o = null;

		try {
			o = (SysRole) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return o;
	}
	/**
      *
      *
     */ 
    @ID
	@Column(name="ID",comment="id")
	private	String id;

    /**
      *角色名
      *
     */ 
    
	@Column(name="ROLENAME",comment="角色名")
	private	String rolename;

    /**
      *描述
      *
     */ 
    
	@Column(name="DESCRIPTION",comment="描述")
	private	String description;

    /**
      *是否启用 0否1是
      *
     */ 
    
	@Column(name="ENABLED",comment="是否启用 0否1是")
	private	String enabled;

    /**
      *上级角色
      *
     */ 
    
	@Column(name="PARENTROLEID",comment="上级角色")
	private	String parentroleid;

    /**
      *所属餐厅id
      *
     */ 
    
	@Column(name="RESTAURANTID",comment="所属餐厅id")
	private	String restaurantid;

    /**
      *类别0系统角色1餐厅自定义角色
      *
     */ 
    
	@Column(name="TYPE",comment="类别0系统角色1餐厅自定义角色")
	private	String type;

	/**
	  * 
	  * id 
	 */
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	  * @return 
	  */
	public String getId() {
		return id;
	}

	/**
	  * 角色名
	  *  
	 */
	public void setRolename(String rolename) 
	{
		this.rolename = rolename;
	}
	/**
	  * @return 角色名
	  */
	public String getRolename() {
		return rolename;
	}

	/**
	  * 描述
	  *  
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	/**
	  * @return 描述
	  */
	public String getDescription() {
		return description;
	}

	/**
	  * 是否启用 0否1是
	  *  
	 */
	public void setEnabled(String enabled) 
	{
		this.enabled = enabled;
	}
	/**
	  * @return 是否启用 0否1是
	  */
	public String getEnabled() {
		return enabled;
	}

	/**
	  * 上级角色
	  *  
	 */
	public void setParentroleid(String parentroleid) 
	{
		this.parentroleid = parentroleid;
	}
	/**
	  * @return 上级角色
	  */
	public String getParentroleid() {
		return parentroleid;
	}

	/**
	  * 所属餐厅id
	  *  
	 */
	public void setRestaurantid(String restaurantid) 
	{
		this.restaurantid = restaurantid;
	}
	/**
	  * @return 所属餐厅id
	  */
	public String getRestaurantid() {
		return restaurantid;
	}

	/**
	  * 类别0系统角色1餐厅自定义角色
	  *  
	 */
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	  * @return 类别0系统角色1餐厅自定义角色
	  */
	public String getType() {
		return type;
	}

}
	