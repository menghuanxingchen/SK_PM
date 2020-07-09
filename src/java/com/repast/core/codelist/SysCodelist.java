
package com.repast.core.codelist;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;

/**
 * 
 * Application name:易学籍
 * Application describing:实体类
 * Copyright:沈阳东慧网络科技有限公司
 * Company:www.exueji.com
 * @author xxx
 */
@Entity(name="SYS_CODELIST")
public class SysCodelist extends BaseEntity
{

    /**
      *ID
      *
     */ 
    @ID
	@Column(name="CODELISTID",comment="主键值")
	private	String codelistid;

    /**
      *种类CODE
      *
     */ 
    
	@Column(name="KINDCODE",comment="代码表编号(kindcode)")
	private	String kindcode;

    /**
      *种类名称
      *
     */ 
    
	@Column(name="KINDNAME",comment="代码表名称(kindvalue)")
	private	String kindname;

    /**
      *代码值
      *
     */ 
    
	@Column(name="CODEVALUE",comment="代码值(codevalue)")
	private	String codevalue;

    /**
      *代码名称
      *
     */ 
    
	@Column(name="CODENAME",comment="代码名称(codename)")
	private	String codename;

    /**
      *父级代码值
      *
     */ 
    
	@Column(name="PARENTCODEVALUE",comment="上级代码值")
	private	String parentcodevalue;

    /**
      *序号
      *
     */ 
    
	@Column(name="ORDERNUM",comment="排序值")
	private	int ordernum;

    /**
      *描述
      *
     */ 
    
	@Column(name="MEMO",comment="备注")
	private	String memo;
	
	@Transient
	@Column(name="kindCnt",comment="备注")
	private String kindCnt;
	
	@Column(name="ext1",comment="扩展1")
	private String ext1;
	
	
	@Column(name="ext2",comment="扩展2")
	private String ext2;
	

	/**
	  * ID
	  *  
	 */
	public void setCodelistid(String codelistid) 
	{
		this.codelistid = codelistid;
	}
	/**
	  * @return ID
	  */
	public String getCodelistid() {
		return codelistid;
	}

	/**
	  * 种类CODE
	  *  
	 */
	public void setKindcode(String kindcode) 
	{
		this.kindcode = kindcode;
	}
	/**
	  * @return 种类CODE
	  */
	public String getKindcode() {
		return kindcode;
	}

	/**
	  * 种类名称
	  *  
	 */
	public void setKindname(String kindname) 
	{
		this.kindname = kindname;
	}
	/**
	  * @return 种类名称
	  */
	public String getKindname() {
		return kindname;
	}

	/**
	  * 代码值
	  *  
	 */
	public void setCodevalue(String codevalue) 
	{
		this.codevalue = codevalue;
	}
	/**
	  * @return 代码值
	  */
	public String getCodevalue() {
		return codevalue;
	}

	/**
	  * 代码名称
	  *  
	 */
	public void setCodename(String codename) 
	{
		this.codename = codename;
	}
	/**
	  * @return 代码名称
	  */
	public String getCodename() {
		return codename;
	}

	/**
	  * 父级代码值
	  *  
	 */
	public void setParentcodevalue(String parentcodevalue) 
	{
		this.parentcodevalue = parentcodevalue;
	}
	/**
	  * @return 父级代码值
	  */
	public String getParentcodevalue() {
		return parentcodevalue;
	}

	/**
	  * 序号
	  *  
	 */
	public void setOrdernum(int ordernum) 
	{
		this.ordernum = ordernum;
	}
	/**
	  * @return 序号
	  */
	public int getOrdernum() {
		return ordernum;
	}

	/**
	  * 描述
	  *  
	 */
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	  * @return 描述
	  */
	public String getMemo() {
		return memo;
	}
	public String getKindCnt() {
		return kindCnt;
	}
	public void setKindCnt(String kindCnt) {
		this.kindCnt = kindCnt;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

}
	