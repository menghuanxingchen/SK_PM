package com.pm.persistence.projectsummary.dto;

import java.io.Serializable;

import com.repast.core.system.BaseEntity;

public class ProjectSummaryInfoDto extends BaseEntity implements Serializable,Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String workDate1;
	private String workDate2;
	private String workContent1;
	private String workContent2;
	private String workDate;
	private String workContent;
	
	public String getWorkDate1() {
		return workDate1;
	}
	public void setWorkDate1(String workDate1) {
		this.workDate1 = workDate1;
	}
	public String getWorkDate2() {
		return workDate2;
	}
	public void setWorkDate2(String workDate2) {
		this.workDate2 = workDate2;
	}
	public String getWorkContent1() {
		return workContent1;
	}
	public void setWorkContent1(String workContent1) {
		this.workContent1 = workContent1;
	}
	public String getWorkContent2() {
		return workContent2;
	}
	public void setWorkContent2(String workContent2) {
		this.workContent2 = workContent2;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	
}
