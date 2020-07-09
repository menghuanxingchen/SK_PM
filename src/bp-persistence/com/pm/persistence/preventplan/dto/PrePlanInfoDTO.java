package com.pm.persistence.preventplan.dto;

import java.io.Serializable;
import java.util.List;

import com.pm.persistence.preventplan.entity.PrePlanCheckDetail;
import com.pm.persistence.preventplan.entity.PrePlanMachineInfo;
import com.repast.core.system.BaseEntity;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company: 表pre_plan_info 的实体类
 * 
 * @author 刘镝
 */
public class PrePlanInfoDTO extends BaseEntity implements Serializable,Cloneable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户id
	 */
	private String user_id;

	/**
	 * 保养计划名称
	 */
	private String pre_plan_nm;

	/**
	 * 计划周期
	 */
	private String pre_plan_cycle;

	/**
	 * 计划日期
	 */
	private String pre_plan_date;

	/**
	 * 保养计划组id
	 */
	private String pre_plan_group;

	/**
	 * 设备id
	 */
	private String pre_machine_id;

	/**
	 * 检查类型
	 */
	private String chk_type;

	/**
	 * 检查项目id
	 */
	private String check_project_id;

	/**
	 * 设备List
	 */
	private List<PrePlanMachineInfo> machineInfoList;

	/**
	 * 检查项目list
	 */
	private List<PrePlanCheckDetail> checkDetailList;
	
	/**
	   * 保养计划id
	   */
	private String pre_plan_id;
	
	/**
	   * 修改类型（单个修改，批量修改） 
	   */
	private String update_flag;
	/**
	   * 保存同group_id下的最大日期
	   */
	private String pre_plan_date_end;
	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPre_plan_nm() {
		return pre_plan_nm;
	}

	public void setPre_plan_nm(String pre_plan_nm) {
		this.pre_plan_nm = pre_plan_nm;
	}

	public String getPre_plan_cycle() {
		return pre_plan_cycle;
	}

	public void setPre_plan_cycle(String pre_plan_cycle) {
		this.pre_plan_cycle = pre_plan_cycle;
	}

	public String getPre_plan_date() {
		return pre_plan_date;
	}

	public void setPre_plan_date(String pre_plan_date) {
		this.pre_plan_date = pre_plan_date;
	}

	public String getPre_plan_group() {
		return pre_plan_group;
	}

	public void setPre_plan_group(String pre_plan_group) {
		this.pre_plan_group = pre_plan_group;
	}

	public String getPre_machine_id() {
		return pre_machine_id;
	}

	public void setPre_machine_id(String pre_machine_id) {
		this.pre_machine_id = pre_machine_id;
	}

	public String getChk_type() {
		return chk_type;
	}

	public void setChk_type(String chk_type) {
		this.chk_type = chk_type;
	}

	public String getCheck_project_id() {
		return check_project_id;
	}

	public void setCheck_project_id(String check_project_id) {
		this.check_project_id = check_project_id;
	}

	public List<PrePlanMachineInfo> getMachineInfoList() {
		return machineInfoList;
	}

	public void setMachineInfoList(List<PrePlanMachineInfo> machineInfoList) {
		this.machineInfoList = machineInfoList;
	}

	public List<PrePlanCheckDetail> getCheckDetailList() {
		return checkDetailList;
	}

	public void setCheckDetailList(List<PrePlanCheckDetail> checkDetailList) {
		this.checkDetailList = checkDetailList;
	}

	public String getPre_plan_id() {
		return pre_plan_id;
	}

	public void setPre_plan_id(String pre_plan_id) {
		this.pre_plan_id = pre_plan_id;
	}

	public String getUpdate_flag() {
		return update_flag;
	}

	public void setUpdate_flag(String update_flag) {
		this.update_flag = update_flag;
	}

	public String getPre_plan_date_end() {
		return pre_plan_date_end;
	}

	public void setPre_plan_date_end(String pre_plan_date_end) {
		this.pre_plan_date_end = pre_plan_date_end;
	}

}
