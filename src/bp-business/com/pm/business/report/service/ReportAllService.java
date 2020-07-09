package com.pm.business.report.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pm.persistence.inspectplan.dao.InsPlanCheckDetailDAO;
import com.pm.persistence.inspectplan.dao.InsPlanInfoDAO;
import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.preventplan.dao.PrePlanCheckDetailDAO;
import com.pm.persistence.preventplan.dao.PrePlanInfoDAO;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.pm.persistence.report.dto.ReportAllDto;

/**
 * 总报表
 * @author fish
 *
 */
@Service
public class ReportAllService {

	@Resource
	private PrePlanInfoDAO prePlanInfoDAO;
	@Resource
	private PrePlanCheckDetailDAO prePlanCheckDetailDAO;
	@Resource
	private InsPlanInfoDAO insPlanInfoDAO;
	@Resource
	private InsPlanCheckDetailDAO insPlanCheckDetailDAO;
	
	public List<List<String[]>> getReportPer(ReportAllDto reportAllDto){
		List<List<String[]>> planIdList = new ArrayList<List<String[]>>();   
		List<String[]> planList = new ArrayList<String[]>();
			PrePlanInfo prePlanInfo = new PrePlanInfo();
			prePlanInfo.setStart_date(reportAllDto.getStart_date());
			prePlanInfo.setEnd_date(reportAllDto.getEnd_date());
			prePlanInfo.setMachine_species_id(reportAllDto.getMachineType());
			prePlanInfo.setPre_plan_nm(reportAllDto.getPlanNm());
			//根据条件查询保养计划ID
			List<PrePlanInfo> prePlanIdList =  prePlanInfoDAO.queryPrePlanInfoListNoPage(prePlanInfo); 
			for(int i = 0 ;i < prePlanIdList.size() ;i++){
				//根据保养计划ID查询列表
				planList = prePlanCheckDetailDAO.getPrePlanCheckDetail(prePlanIdList.get(i).getPre_plan_id());
				planIdList.add(planList);
			}
		return planIdList;
	}
	
	public List<PrePlanInfo> getReportPerNmList(ReportAllDto reportAllDto){
			PrePlanInfo prePlanInfo = new PrePlanInfo();
			prePlanInfo.setStart_date(reportAllDto.getStart_date());
			prePlanInfo.setEnd_date(reportAllDto.getEnd_date());
			prePlanInfo.setMachine_species_id(reportAllDto.getMachineType());
			prePlanInfo.setPre_plan_nm(reportAllDto.getPlanNm());
			//根据条件查询保养计划名称
			List<PrePlanInfo> prePlanIdList =  prePlanInfoDAO.queryPrePlanInfoListNoPage(prePlanInfo); 
		return prePlanIdList;
	}
	
	public List<List<String[]>> getReportPerProp(ReportAllDto reportAllDto){
		List<List<String[]>> planIdList = new ArrayList<List<String[]>>();   
		List<String[]> planList = new ArrayList<String[]>();
			PrePlanInfo prePlanInfo = new PrePlanInfo();
			prePlanInfo.setStart_date(reportAllDto.getStart_date());
			prePlanInfo.setEnd_date(reportAllDto.getEnd_date());
			prePlanInfo.setPre_plan_nm(reportAllDto.getPlanNm());
			prePlanInfo.setMachine_species_id(reportAllDto.getMachineType());
			//根据条件查询保养计划ID
			List<PrePlanInfo> prePlanIdList =  prePlanInfoDAO.queryPrePlanInfoListNoPage(prePlanInfo); 
			for(int i = 0 ;i < prePlanIdList.size() ;i++){
				//根据保养计划ID查询列表
				planList = prePlanCheckDetailDAO.getPrePlanPropCheckDetail(prePlanIdList.get(i).getPre_plan_id());
				planIdList.add(planList);
			}
		return planIdList;
	}
	
	public List<List<String[]>> getReportIns(ReportAllDto reportAllDto){
		List<List<String[]>> planIdList = new ArrayList<List<String[]>>();   
		List<String[]> planList = new ArrayList<String[]>();
			InsPlanInfo insPlanInfo = new InsPlanInfo();
			insPlanInfo.setIns_plan_nm(reportAllDto.getPlanNm());
			insPlanInfo.setDate_start(reportAllDto.getStart_date());
			insPlanInfo.setDate_end(reportAllDto.getEnd_date());
			insPlanInfo.setMachine_species_id(reportAllDto.getMachineType());
			//根据条件查询巡检计划ID
			List<InsPlanInfo> insPlanIdList = insPlanInfoDAO.queryInsPlansListNew(insPlanInfo);
			for(int j = 0 ;j < insPlanIdList.size() ;j++){
				try {
					//根据巡检计划ID查询列表
					planList = insPlanCheckDetailDAO.getInsPlanCheckDetailList(insPlanIdList.get(j).getIns_plan_id());
				} catch (SQLException e) {
					System.out.println("ReportAllService is error"+e.getMessage());
				}
				planIdList.add(planList);
			}
		
		return planIdList;
	}
	
	public List<InsPlanInfo> getReportInsNmList(ReportAllDto reportAllDto){
			InsPlanInfo insPlanInfo = new InsPlanInfo();
			insPlanInfo.setIns_plan_nm(reportAllDto.getPlanNm());
			insPlanInfo.setDate_start(reportAllDto.getStart_date());
			insPlanInfo.setDate_end(reportAllDto.getEnd_date());
			insPlanInfo.setMachine_species_id(reportAllDto.getMachineType());
			//根据条件查询巡检计划ID
			List<InsPlanInfo> insPlanIdList = insPlanInfoDAO.queryInsPlansListNew(insPlanInfo);
		return insPlanIdList;
	}
	
}
