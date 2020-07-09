
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
@Entity(name="SYS_USER_ROLE")
public class SysUserRole extends BaseEntity implements Serializable, Cloneable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6541006656698370553L;

	public SysUserRole clone() {

		SysUserRole o = null;

		try {
			o = (SysUserRole) super.clone();
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
      *用户id
      *
     */ 
    
	@Column(name="USERID",comment="用户id")
	private	String userid;

    /**
      *角色id
      *
     */ 
    
	@Column(name="ROLEID",comment="角色id")
	private	String roleid;

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
	  * 用户id
	  *  
	 */
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}
	/**
	  * @return 用户id
	  */
	public String getUserid() {
		return userid;
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

}
	