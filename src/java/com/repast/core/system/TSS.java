package com.repast.core.system;

import java.util.List;

/**
 * 设备工作总时长、停机总时长、停机次数、报表数据
 * 
 * @author Aubrey
 *
 */
public class TSS {

	// 运行总时长
	private Integer totalTime;
	// 停机总时长
	private Integer StopTotalTime;
	// 停机次数
	private Integer StopCount;
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

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getStopTotalTime() {
		return StopTotalTime;
	}

	public void setStopTotalTime(Integer stopTotalTime) {
		StopTotalTime = stopTotalTime;
	}

	public Integer getStopCount() {
		return StopCount;
	}

	public void setStopCount(Integer stopCount) {
		StopCount = stopCount;
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

	public TSS(Integer totalTime, Integer stopTotalTime, Integer stopCount, List dataX, List dataY) {
		this.totalTime = totalTime;
		this.StopTotalTime = stopTotalTime;
		this.StopCount = stopCount;
		this.dataX = dataX;
		this.dataY = dataY;
	}

	public TSS() {
	}
}
