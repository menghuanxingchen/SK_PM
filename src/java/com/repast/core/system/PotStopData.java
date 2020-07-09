package com.repast.core.system;

import java.util.List;

public class PotStopData implements Comparable<PotStopData>{

	/**
	 * ECharts图表的X轴数据（名称）
	 * 
	 * @author Aubrey
	 */
	private String Name;
	/**
	 * ECharts图表的Y轴数据（停机次数）
	 * 
	 * @author Aubrey
	 */
	private String CountNum;
	/**
	 * ECharts图表的Z轴数据（占比）
	 * 
	 * @author Aubrey
	 */
	private String Proportion;
	

	public PotStopData() {
	}

	public PotStopData(String Name, String CountNum, String Proportion) {
		this.Name = Name;
		this.CountNum = CountNum;
		this.Proportion = Proportion;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getCountNum() {
		return CountNum;
	}

	public void setCountNum(String CountNum) {
		this.CountNum = CountNum;
	}
	public String getProportion() {
		return Proportion;
	}

	public void setProportion(String Proportion) {
		this.Proportion = Proportion;
	}
	
	@Override
    public int compareTo(PotStopData arg0) {
        return this.getCountNum().compareTo(arg0.getCountNum());      //这里定义你排序的规则。
    }
}
