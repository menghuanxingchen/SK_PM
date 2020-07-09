package com.pm.business.basicinfo.service;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pm.persistence.component.dao.ComponentsSysCodeInfoDAO;
import com.pm.persistence.component.entity.ComponentsSysCodeInfo;
import com.pm.persistence.emergentRepair.dao.EmergentRepairInfoDAO;
import com.pm.persistence.emergentRepair.entity.EmergentRepairInfo;
import com.pm.persistence.machinemanager.dao.MachinePotPartInfoDAO;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.pm.persistence.repair.dao.MaintResStatisDAO;
import com.pm.persistence.repair.dto.MaintAreaStatisDTO;
import com.pm.persistence.repair.dto.MaintResStatisDTO;
import com.pm.persistence.worktimeinfo.dao.WorktimeInfoDAO;
import com.pm.persistence.worktimeinfo.entity.WorktimeInfo;
import com.repast.core.system.EChartsData;
import com.repast.core.system.Pagination;
import com.repast.core.system.PotStopData;
import com.repast.core.system.TSS;
import com.repast.core.util.SortList;
@Service("MaintResStatisService")
public class MaintResStatisService {
	
	@Resource(name="MaintResStatisDAO")
	private MaintResStatisDAO maintResStatisDAO;
	@Resource(name="MachinePotPartInfoDAO")
	private MachinePotPartInfoDAO machinePotPartInfoDAO;
	@Resource(name="ComponentsSysCodeInfoDAO")
    private ComponentsSysCodeInfoDAO componentsSysCodeInfoDAO;
	@Resource(name="EmergentRepairInfoDAO")
    private EmergentRepairInfoDAO emergentRepairInfoDAO;
	@Resource(name="WorktimeInfoDAO")
    private WorktimeInfoDAO worktimeInfoDAO;
	
	public Pagination<MaintResStatisDTO> queryStatisOrderTypeList(MaintResStatisDTO en, int pageIndex, int pageSize) {
		Pagination<MaintResStatisDTO> infoList = 
				maintResStatisDAO.queryStatisOrderTypeList(en,pageIndex,pageSize);
		return infoList;
	}
	
	public Pagination<MaintResStatisDTO> queryPotPartStopReport(MaintResStatisDTO en, int pageIndex, int pageSize) {
		Pagination<MaintResStatisDTO> infoList = 
				maintResStatisDAO.queryPotPartStopReport(en,pageIndex,pageSize);
		return infoList;
	}
	public Pagination<MaintResStatisDTO> queryStatisOrderMachineList(MaintResStatisDTO en, int pageIndex, int pageSize) {
		Pagination<MaintResStatisDTO> infoList = 
				maintResStatisDAO.queryStatisOrderMachineList(en,pageIndex,pageSize);
		return infoList;
	}
	public Pagination<MaintResStatisDTO> queryRepOrderList(MaintResStatisDTO en, int pageIndex, int pageSize) {
		Pagination<MaintResStatisDTO> infoList = 
				maintResStatisDAO.queryRepOrderList(en,pageIndex,pageSize);
		return infoList;
	}
	public Pagination<MaintAreaStatisDTO> queryMaintAreaStatisList(MaintAreaStatisDTO en, int pageIndex, int pageSize) {
		Pagination<MaintAreaStatisDTO> infoList = 
				maintResStatisDAO.queryMaintAreaStatisList(en,pageIndex,pageSize);
		return infoList;
	}

	/**
	 * 三级子部件停机次数报表
	 * @param selTime 所选日期
	 * @return
	 * @author 姜易平
	 */
	public EChartsData queryCodeStopCountECharts(String selTime) {
		List<ComponentsSysCodeInfo> queryDataList = componentsSysCodeInfoDAO.queryDataList();
		List<String> dataX = new ArrayList<String>();
		List<String> dataY = new ArrayList<String>();
		List<Integer> dataZ = new ArrayList<Integer>();
		for (ComponentsSysCodeInfo componentsSysCodeInfo : queryDataList) {
			dataX.add(componentsSysCodeInfo.getCode_nm());
			EmergentRepairInfo erInfo = new EmergentRepairInfo();
			erInfo.setPot_son_part_id(componentsSysCodeInfo.getCode_value());
			erInfo.setRepair_date(selTime);
			erInfo.setJob_type("1");
			dataY.add(String.valueOf(emergentRepairInfoDAO.queryCodeStopCount2(erInfo)));
		}
		return new EChartsData(dataX,dataY,dataZ);
	}

	/**
	 * 二级部件停机次数报表
	 * @param selTime 所选日期
	 * @param selPotid 所选一级设备ID
	 * @return
	 * @author Aubrey
	 */
	public EChartsData queryPotPartStopCountECharts(String selTime, String selPotid) {
		MachinePotPartInfo mppi = new MachinePotPartInfo();
		mppi.setPot_id(selPotid);
		List<MachinePotPartInfo> queryMachinePotPartInfoList = machinePotPartInfoDAO.queryMachinePotPartInfoList(mppi);
		List<String> dataX = new ArrayList<String>();
		List<String> dataY = new ArrayList<String>();
		List<String> dataZ = new ArrayList<String>();
		List<PotStopData> PData = new ArrayList<PotStopData>();
		
		EmergentRepairInfo erInfo2 = new EmergentRepairInfo();
		erInfo2.setPot_id(selPotid);
		erInfo2.setRepair_date(selTime);
		erInfo2.setJob_type("1");
		
		int repairNum = emergentRepairInfoDAO.queryCodeStopCount2(erInfo2);
		int countNum = 0;
		for (MachinePotPartInfo machinePotPartInfo : queryMachinePotPartInfoList) {
			PotStopData ps = new PotStopData();
			ps.setName(machinePotPartInfo.getPot_part_nm());
			
			EmergentRepairInfo erInfo = new EmergentRepairInfo();
			erInfo.setPot_part_id(machinePotPartInfo.getPot_part_id());
			erInfo.setRepair_date(selTime);
			erInfo.setJob_type("1");
			
			ps.setCountNum(String.valueOf(emergentRepairInfoDAO.queryCodeStopCount2(erInfo)));
			PData.add(ps);
		}
		
		//排序 次数倒序
	    Collections.sort(PData, Collections.reverseOrder());
		
	    for (PotStopData p : PData) {
	    	dataX.add(p.getName());
	    	dataY.add(p.getCountNum());
	    
	    	countNum = countNum + Integer.parseInt(p.getCountNum());
			float proportion = 0;
			if(repairNum >= 0){
				proportion = (float)countNum/repairNum;
			}

			DecimalFormat df=new DecimalFormat("0");//设置保留位数
			
	    	dataZ.add(df.format(proportion*100));
	    }
		
		return new EChartsData(dataX,dataY,dataZ);
	}

	/**
	 * 设备停机次数
	 * @param selTime
	 * @param selPotid
	 * @return
	 * @author Aubrey
	 */
	public List<String> queryPotStopCount(String selTime, String selPotid) {
		List<String> count = new ArrayList<String>();
		//一年内设备停机次数
		for (int i = 1; i <= 12; i++) {
			if (i<10) {
				selTime = selTime.substring(0, selTime.length()-2)+"0"+i;
			} else {
				selTime = selTime.substring(0, selTime.length()-2)+i;
			}
			EmergentRepairInfo erInfo = new EmergentRepairInfo();
			erInfo.setPot_id(selPotid);
			erInfo.setRepair_date(selTime);
			erInfo.setJob_type("1");
			System.err.println(selTime);
			count.add(String.valueOf(emergentRepairInfoDAO.queryCodeStopCount2(erInfo)));
		}
		return count;
	}

	/**
	 * 平均停机时间间隔、平均停机时间、平均维修时间报表
	 * @param maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param job_type
	 * @param matchType
	 * @return
	 * @author Aubrey
	 */
	public EChartsData queryReportECharts(MaintResStatisDTO maintResStatisDTO, String selPotId,String job_type,String matchType) {
		String st_year = maintResStatisDTO.getSt_date().substring(0,maintResStatisDTO.getSt_date().length()-2);
		String en_year = maintResStatisDTO.getEn_date().substring(0,maintResStatisDTO.getEn_date().length()-2);
		List<String> dataX = new ArrayList<String>();
		List<String> dataY = new ArrayList<String>();
		List<Integer> dataZ = new ArrayList<Integer>();
		int st_month = 0;
		int en_month = 0;
		if(!st_year.equals(en_year)) {
			st_month = Integer.parseInt(maintResStatisDTO.getSt_date().substring(5));
			en_month = 12;
			int st_month2 = 1;
			int en_month2 = Integer.parseInt(maintResStatisDTO.getEn_date().substring(5));
			TSS tss = queryTSSAndEData(st_month,en_month,dataX,dataY, st_year,selPotId,job_type,matchType);
			dataX = tss.getDataX();
			dataY = tss.getDataY();
			tss = queryTSSAndEData(st_month2,en_month2,dataX,dataY, en_year,selPotId,job_type,matchType);
			dataX = tss.getDataX();
			dataY = tss.getDataY();
		}else {
			st_month = Integer.parseInt(maintResStatisDTO.getSt_date().substring(5));
			en_month = Integer.parseInt(maintResStatisDTO.getEn_date().substring(5));
			TSS tss = queryTSSAndEData(st_month,en_month,dataX,dataY, st_year,selPotId,job_type,matchType);
			dataX = tss.getDataX();
			dataY = tss.getDataY();
		}
		return new EChartsData(dataX,dataY,dataZ);
	}
	
	/**
	 * 查询总时长、停机总时长、停机次数,并计算返回ECharts数据
	 * @param st_month 年的开始月
	 * @param en_month 年的结束月
	 * @param dataX
	 * @param dataY 
	 * @param year 当前处理的年
	 * @param selPotid 设备
	 * @param job_type 职能
	 * @param matchType 计算方式
	 * @return
	 * @author Aubrey  ©Copyright
	 */
	private TSS queryTSSAndEData(int st_month, int en_month, List<String> dataX, List<String> dataY, String year,
			String selPotid, String job_type, String matchType){
		TSS tss = null;
		for (int i = st_month; i <= en_month; i++) {
			String CHMonth = intToStringMonth(i);
			dataX.add(CHMonth);
			//总时长
			WorktimeInfo wi = new WorktimeInfo();
			String month = "";
			if(i<10) {
				month = "0"+String.valueOf(i);
			}else {
				month = String.valueOf(i);
			}
			wi.setDate_group(year+month);
			int totalTime = worktimeInfoDAO.queryTotalTime(wi);
			
			//当月停机时长
			EmergentRepairInfo erInfo2 = new EmergentRepairInfo();
			erInfo2.setPot_id(selPotid);
			erInfo2.setSearch_start(year+month+"-01");
			erInfo2.setSearch_end(year+month+"-31");
			erInfo2.setJob_type(job_type);
			int stopDyTotalTime = emergentRepairInfoDAO.queryDYCodeStopTotalTime(erInfo2);
			//当月停机次数
			int stopDyCount = emergentRepairInfoDAO.queryDYCodeStopCount(erInfo2);
			
			//当月维修时长
			EmergentRepairInfo erInfo3 = new EmergentRepairInfo();
			erInfo3.setPot_id(selPotid);
			erInfo3.setSearch_start(year+month+"-01");
			erInfo3.setSearch_end(year+month+"-31");
		
			int stopDyWXTotalTime = emergentRepairInfoDAO.queryDYWXCodeStopTotalTime(erInfo3);
			//当月维修次数
			int stopDyWXCount = emergentRepairInfoDAO.queryDYWXCodeStopCount(erInfo3);
			
			
			//停机总时长
			EmergentRepairInfo erInfo = new EmergentRepairInfo();
			erInfo.setPot_id(selPotid);
			erInfo.setRepair_date(year+month+"-31");
			erInfo.setJob_type(job_type);
			int stopTotalTime = emergentRepairInfoDAO.queryCodeStopTotalTime(erInfo);
			//停机次数
			int stopCount = emergentRepairInfoDAO.queryCodeStopCount(erInfo);
			
			//维修总时长
			EmergentRepairInfo erInfo4 = new EmergentRepairInfo();
			erInfo4.setPot_id(selPotid);
			erInfo4.setRepair_date(year+month+"-31");

			int stopZWXTotalTime = emergentRepairInfoDAO.queryZWXCodeStopTotalTime(erInfo4);
			//维修次数
			int stopZWXCount = emergentRepairInfoDAO.queryZWXCodeStopCount(erInfo4);
			
			
			//间隔停机总时长
			EmergentRepairInfo erInfo5 = new EmergentRepairInfo();
			erInfo5.setPot_id(selPotid);
			erInfo5.setRepair_date(year+month+"-31");
			erInfo5.setJob_type(job_type);
			int stopjgTotalTime = emergentRepairInfoDAO.queryjgCodeStopTotalTime(erInfo5);
			//间隔停机次数
			int stopjgCount = emergentRepairInfoDAO.queryjgCodeStopCount(erInfo5);
			
			
			//平均停机时间间隔
			if(matchType.equals("MTBF")) {
				if(stopjgTotalTime==0 || stopjgCount==0 ) {
					dataY.add("0");
				}else {
					dataY.add(String.valueOf((totalTime-stopjgTotalTime)/stopjgCount));
				}
			}else if(matchType.equals("MTTA")){
				if(stopTotalTime==0 || stopCount==0) {
					dataY.add("0");
				}else {
					if(st_month == en_month){
						if(stopDyTotalTime ==0 ||stopDyCount == 0){
							dataY.add("0");
						}else{
							dataY.add(String.valueOf(stopDyTotalTime/stopDyCount));
						}
						
					}else{
						dataY.add(String.valueOf(stopTotalTime/stopCount));
					}
					
				}
			}else if(matchType.equals("MTTR")){
				if(stopZWXTotalTime==0 || stopZWXCount==0 ) {
					dataY.add("0");
				}else {
					if(st_month == en_month){
						if(stopDyWXTotalTime ==0 ||stopDyWXCount == 0){
							dataY.add("0");
						}else{
							dataY.add(String.valueOf(stopDyWXTotalTime/stopDyWXCount));
						}
					}else{
						dataY.add(String.valueOf(stopZWXTotalTime/stopZWXCount));
					}
					
				}
			}
			tss = new TSS(totalTime, stopTotalTime, stopCount, dataX, dataY);
		}
		return tss;
	}
	/**
	 * 每日故障
	 * @param maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param job_type
	 * @param matchType
	 * @return
	 * @author 高文超
	 */
	public EChartsData queryMRSJReportECharts(MaintResStatisDTO maintResStatisDTO, String selPotId,String job_type) {
		int st_year = Integer.parseInt(maintResStatisDTO.getSt_date().substring(0,4)) ;
		int st_month = Integer.parseInt(maintResStatisDTO.getSt_date().substring(5,7));
		
		int lastDay = getLastDayOfMonth(st_year,st_month);
		
		
		List<String> dataX = new ArrayList<String>();
		List<String> dataY = new ArrayList<String>();
		List<String> dataZ = new ArrayList<String>();
		
		for(int i=1;i<=lastDay;i++){
			dataX.add(st_month+"-"+i);
			
			
			String stMonth = ""; 
			String stDay = ""; 
			if(st_month < 10){
				stMonth = "0" + st_month;
			}else{
				stMonth = st_month+"";
			}
			if(i < 10){
				stDay = "0" + i;
			}else{
				stDay = i+"";
			}
			
			//停机次数
			String reqpairDate=st_year+"-"+stMonth+"-"+stDay;
			EmergentRepairInfo erInfo = new EmergentRepairInfo();
			erInfo.setPot_id(selPotId);
			erInfo.setRepair_date(reqpairDate);
			erInfo.setJob_type("1");
			dataY.add(String.valueOf(emergentRepairInfoDAO.queryCodeStopCount2(erInfo)));
			
			
			//停机时间
			dataZ.add(String.valueOf(emergentRepairInfoDAO.queryCodeStopTotalTimeByMR(erInfo)));
		}
		
		
		
		
		return new EChartsData(dataX,dataY,dataZ);
	}
	/**
	 * 每日故障
	 * @param maintResStatisDTO 时间
	 * @param selPotid 所选一级设备ID
	 * @param job_type
	 * @param matchType
	 * @return
	 * @author 高文超
	 */
	public EChartsData queryMRCSReportECharts(MaintResStatisDTO maintResStatisDTO, String selPotId,String job_type) {
		int st_year = Integer.parseInt(maintResStatisDTO.getSt_date().substring(0,4)) ;
		int st_month = Integer.parseInt(maintResStatisDTO.getSt_date().substring(5,7));
		
		int lastDay = getLastDayOfMonth(st_year,st_month);
		
		
		List<String> dataX = new ArrayList<String>();
		List<String> dataY = new ArrayList<String>();
		List<String> dataZ = new ArrayList<String>();
		
		for(int i=1;i<=lastDay;i++){
			dataX.add(st_month+"-"+i);
			
			
			String stMonth = ""; 
			String stDay = ""; 
			if(st_month < 10){
				stMonth = "0" + st_month;
			}else{
				stMonth = st_month+"";
			}
			if(i < 10){
				stDay = "0" + i;
			}else{
				stDay = i+"";
			}
			
			//停机次数
			String reqpairDate=st_year+"-"+stMonth+"-"+stDay;
			EmergentRepairInfo erInfo = new EmergentRepairInfo();
			erInfo.setPot_id(selPotId);
			erInfo.setRepair_date(reqpairDate);
			erInfo.setJob_type("1");
			dataY.add(String.valueOf(emergentRepairInfoDAO.queryCodeStopCount2(erInfo)));

			//停机时间
			dataZ.add(String.valueOf(emergentRepairInfoDAO.queryCodeStopTotalTimeByMR(erInfo)));
		}

		return new EChartsData(dataX,dataY,dataZ);
	}
	/**
     * 获取某月的最后一天
     *
     */
    public static Integer getLastDayOfMonth(int year,int month)
    {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
      
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
	/**
	 * 月份中文转换
	 * @param i
	 * @return
	 * @author Aubrey
	 */
	private String intToStringMonth(int i) {
		String CHMonth = "";
		switch (i) {
		case 1:
			CHMonth = "一月";
			break;
		case 2:
			CHMonth = "二月";
			break;
		case 3:
			CHMonth = "三月";
			break;
		case 4:
			CHMonth = "四月";
			break;
		case 5:
			CHMonth = "五月";
			break;
		case 6:
			CHMonth = "六月";
			break;
		case 7:
			CHMonth = "七月";
			break;
		case 8:
			CHMonth = "八月";
			break;
		case 9:
			CHMonth = "九月";
			break;
		case 10:
			CHMonth = "十月";
			break;
		case 11:
			CHMonth = "十一月";
			break;
		case 12:
			CHMonth = "十二月";
			break;

		default:
			break;
		}
		return CHMonth;
	}
}
