package com.repast.core.system;

import java.util.List;

public class EChartsData {

	/**
	 * ECharts图表的X轴数据（简单化的）
	 * 
	 * @author Aubrey
	 */
	private List dataX;
	/**
	 * ECharts图表的Y轴数据（简单化的）
	 * 
	 * @author Aubrey
	 */
	private List dataY;
	/**
	 * ECharts图表的Z轴数据（简单化的）
	 * 
	 * @author Aubrey
	 */
	private List dataZ;

	public EChartsData() {
	}

	public EChartsData(List dataX, List dataY, List dataZ) {
		this.dataX = dataX;
		this.dataY = dataY;
		this.dataZ = dataZ;
	}

	public List getDataX() {
		return dataX;
	}

	public void setDataX(List dataX) {
		this.dataX = dataX;
	}

	public List getDataY() {
		return dataY;
	}

	public void setDataY(List dataY) {
		this.dataY = dataY;
	}
	
	public List getDataZ() {
		return dataZ;
	}

	public void setDataZ(List dataZ) {
		this.dataZ = dataZ;
	}
}
