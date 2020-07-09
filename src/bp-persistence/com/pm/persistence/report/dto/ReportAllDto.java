package com.pm.persistence.report.dto;

public class ReportAllDto {
	
	private String planType;
	private String machineType;
	private String start_date;
	private String end_date;
	private String planId;
	private String planNm;
	//liudi add
	private String machineTypeNm;
	private String planTypeNm;
	private String pm_rate;
	
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanNm() {
		return planNm;
	}
	public void setPlanNm(String planNm) {
		this.planNm = planNm;
	}

	public String getPlanTypeNm() {
		return planTypeNm;
	}
	public void setPlanTypeNm(String planTypeNm) {
		this.planTypeNm = planTypeNm;
	}
	public String getMachineTypeNm() {
		return machineTypeNm;
	}
	public void setMachineTypeNm(String machineTypeNm) {
		this.machineTypeNm = machineTypeNm;
	}
	public String getPm_rate() {
		return pm_rate;
	}
	public void setPm_rate(String pm_rate) {
		this.pm_rate = pm_rate;
	}
	
}
