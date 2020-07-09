package com.pm.persistence.machinemanager.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company: 表machine_pot_part_info 的实体类
 * 
 * @author 刘镝
 */
@Entity(name = "machine_pot_part_info")
public class MachinePotPartInfo extends BaseEntity implements Serializable, Cloneable {

	public MachinePotPartInfo clone() {
		MachinePotPartInfo o = null;
		try {
			o = (MachinePotPartInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 二级分类id
	 */
	@ID
	@Column(name = "pot_part_id", comment = "二级分类id")
	private String pot_part_id;
	  /**
	   * 一级分类id.group
	   */
	  @Transient
	  @Column(name = "pot_id_group", comment = "一级分类id.group")
	  private String pot_id_group;
	  
	/**
	 * 名称
	 */
	@Column(name = "pot_part_nm", comment = "名称")
	private String pot_part_nm;

	/**
	 * 一级分类id
	 */
	@Column(name = "pot_id", comment = "一级分类id")
	private String pot_id;

	/**
	 * 一级分类名称
	 */
	@Transient
	@Column(name = "pot_nm", comment = "一级分类id")
	private String pot_nm;

	/**
	 * 生产日期
	 */
	@Column(name = "production_date", comment = "生产日期")
	private String production_date;

	/**
	 * 使用年限
	 */
	@Column(name = "use_year", comment = "使用年限")
	private String use_year;

	/**
	 * 供应商
	 */
	@Column(name = "supplier", comment = "供应商")
	private String supplier;

	/**
	 * 
	 */
	@Column(name = "remark", comment = "")
	private String remark;

	/**
	 * sqm系统的标识
	 */
	@Transient
	@Column(name = "sqmMark", comment = "sqm系统的标识")
	private String sqmMark;

	/**
	 * sqm系统排序
	 */
	@Transient
	@Column(name = "sqmOrderNum", comment = "sqm系统排序")
	private String sqmOrderNum;
	
	/**
	 * 首次创建
	 */
	@Column(name = "create_id", comment = "首次创建")
	private String create_id;

	/**
	 * 首次创建时间
	 */
	@Column(name = "create_time", comment = "首次创建时间")
	private String create_time;

	/**
	 * 修改userid
	 */
	@Column(name = "update_id", comment = "修改userid")
	private String update_id;

	/**
	 * 修改时间
	 */
	@Column(name = "update_time", comment = "修改时间")
	private String update_time;

	public String getPot_part_id() {
		return pot_part_id;
	}

	public void setPot_part_id(String pot_part_id) {
		this.pot_part_id = pot_part_id;
	}
	public String getPot_id_group() {
	      return pot_id_group;
	 }

	 public void setPot_id_group(String pot_id_group) {
	      this.pot_id_group = pot_id_group;
	 }
	public String getPot_part_nm() {
		return pot_part_nm;
	}

	public void setPot_part_nm(String pot_part_nm) {
		this.pot_part_nm = pot_part_nm;
	}

	public String getPot_id() {
		return pot_id;
	}

	public void setPot_id(String pot_id) {
		this.pot_id = pot_id;
	}

	public String getPot_nm() {
		return pot_nm;
	}

	public void setPot_nm(String pot_nm) {
		this.pot_nm = pot_nm;
	}

	public String getProduction_date() {
		return production_date;
	}

	public void setProduction_date(String production_date) {
		this.production_date = production_date;
	}

	public String getUse_year() {
		return use_year;
	}

	public void setUse_year(String use_year) {
		this.use_year = use_year;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSqmMark() {
		return sqmMark;
	}

	public void setSqmMark(String sqmMark) {
		this.sqmMark = sqmMark;
	}

	public String getSqmOrderNum() {
		return sqmOrderNum;
	}

	public void setSqmOrderNum(String sqmOrderNum) {
		this.sqmOrderNum = sqmOrderNum;
	}
	
	public String getCreate_id() {
		return create_id;
	}

	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
