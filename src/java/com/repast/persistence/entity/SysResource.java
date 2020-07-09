
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
@Entity(name="SYS_RESOURCE")
public class SysResource extends BaseEntity implements Serializable, Cloneable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8049039646807891593L;

	public SysResource clone() {

		SysResource o = null;

		try {
			o = (SysResource) super.clone();
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
      *资源名称
      *
     */ 
    
	@Column(name="RESOURCENAME",comment="资源名称")
	private	String resourcename;

    /**
      *url
      *
     */ 
    
	@Column(name="URL",comment="url")
	private	String url;

    /**
      *描述
      *
     */ 
    
	@Column(name="DESCRIPTION",comment="描述")
	private	String description;

    /**
      *上级资源
      *
     */ 
    
	@Column(name="PARENTID",comment="上级资源")
	private	String parentid;

    /**
      *排序
      *
     */ 
    
	@Column(name="SORT",comment="排序")
	private	String sort;

    /**
      *是否启用 0否1是
      *
     */ 
    
	@Column(name="ENABLED",comment="是否启用 0否1是")
	private	String enabled;

    /**
      *是否叶子节点 0否1是
      *
     */ 
    
	@Column(name="ISCHILD",comment="是否叶子节点 0否1是")
	private	String ischild;
	
	/**
     *类别－－0web端1app端
     *
    */
	@Column(name="TYPE",comment="类别 0web端1app端")
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	  * 资源名称
	  *  
	 */
	public void setResourcename(String resourcename) 
	{
		this.resourcename = resourcename;
	}
	/**
	  * @return 资源名称
	  */
	public String getResourcename() {
		return resourcename;
	}

	/**
	  * url
	  *  
	 */
	public void setUrl(String url) 
	{
		this.url = url;
	}
	/**
	  * @return url
	  */
	public String getUrl() {
		return url;
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
	  * 上级资源
	  *  
	 */
	public void setParentid(String parentid) 
	{
		this.parentid = parentid;
	}
	/**
	  * @return 上级资源
	  */
	public String getParentid() {
		return parentid;
	}

	/**
	  * 排序
	  *  
	 */
	public void setSort(String sort) 
	{
		this.sort = sort;
	}
	/**
	  * @return 排序
	  */
	public String getSort() {
		return sort;
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
	  * 是否叶子节点 0否1是
	  *  
	 */
	public void setIschild(String ischild) 
	{
		this.ischild = ischild;
	}
	/**
	  * @return 是否叶子节点 0否1是
	  */
	public String getIschild() {
		return ischild;
	}

}
	