
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
@Entity(name="SYS_ROLE_RESOURCE")
public class SysRoleResource extends BaseEntity implements Serializable, Cloneable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2160080353799457938L;

	public SysRoleResource clone() {

		SysRoleResource o = null;

		try {
			o = (SysRoleResource) super.clone();
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
      *角色id
      *
     */ 
    
	@Column(name="ROLEID",comment="角色id")
	private	String roleid;

    /**
      *资源id
      *
     */ 
    
	@Column(name="RESOURCEID",comment="资源id")
	private	String resourceid;

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
	  * 角色id
	  *  
	 */
	public void setRoleid(String roleid) 
	{
		this.roleid = roleid;
	}
	/**
	  * @return 角色id
	  */
	public String getRoleid() {
		return roleid;
	}

	/**
	  * 资源id
	  *  
	 */
	public void setResourceid(String resourceid) 
	{
		this.resourceid = resourceid;
	}
	/**
	  * @return 资源id
	  */
	public String getResourceid() {
		return resourceid;
	}

}
	