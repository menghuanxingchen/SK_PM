package com.pm.business.report.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.inspectplan.dao.InsPlanInfoDAO;
import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.preventplan.dao.PrePlanInfoDAO;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.pm.persistence.report.dao.UserReportDAO;
import com.pm.persistence.report.dto.UserReportDto;
import com.repast.core.system.Pagination;

/**
 * 员工业绩报表
 * @author fish
 *
 */
@Service
public class UserReportService {
	
	@Resource
	private UserReportDAO userReportDAO;
	@Resource
	private PrePlanInfoDAO prePlanInfoDAO;
	@Resource
	private InsPlanInfoDAO insPlanInfoDAO;
	
	
	/**
	 * 员工业绩报表查询
	 * @param entity
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	 public Pagination<UserReportDto> queryUserReportList(UserReportDto entity, int pageIndex, int pageSize) {
	    return userReportDAO.queryUserReportList(entity, pageIndex, pageSize);
	 }
	 /**
	  * 员工业绩报表详情
	  */
	 public List<UserReportDto> queryUserReportDetail(UserReportDto entity) {
		 List<UserReportDto> userReportDtoList = new ArrayList<UserReportDto>();
		 if("pre".equals(entity.getType())&&!StringUtils.isNullOrEmpty(entity.getUpdate_id())){
			 PrePlanInfo prePlanInfo = new PrePlanInfo();
			 prePlanInfo.setUpdate_id(entity.getUpdate_id());
			 prePlanInfo.setStart_date(entity.getStart_date());
			 prePlanInfo.setEnd_date(entity.getEnd_date());
			 List<PrePlanInfo> prePlanInfoList = prePlanInfoDAO.queryPrePlanInfoListNoPage(prePlanInfo);
			 for(int i=0;i<prePlanInfoList.size();i++){
				 entity = new UserReportDto();
				 entity.setPlan_nm(prePlanInfoList.get(i).getPre_plan_nm());
				 entity.setPlan_date(prePlanInfoList.get(i).getPre_plan_date());
				 entity.setPm_rate(prePlanInfoList.get(i).getPre_pm_rate());
				 entity.setOperate_time(prePlanInfoList.get(i).getOperate_date());
				 userReportDtoList.add(entity);
			 }
		 }else if(!StringUtils.isNullOrEmpty(entity.getUpdate_id())){
			 InsPlanInfo insPlanInfo = new InsPlanInfo();
			 insPlanInfo.setUpdate_id(entity.getUpdate_id());
			 insPlanInfo.setDate_start(entity.getStart_date());
			 insPlanInfo.setDate_end(entity.getEnd_date());
			 List<InsPlanInfo> insPlanInfoList = insPlanInfoDAO.queryInsPlansList(insPlanInfo);
			 for(int j=0;j<insPlanInfoList.size();j++){
				 entity = new UserReportDto();
				 entity.setPlan_nm(insPlanInfoList.get(j).getIns_plan_nm());
				 entity.setPlan_date(insPlanInfoList.get(j).getIns_plan_date());
				 entity.setPm_rate(insPlanInfoList.get(j).getIns_pm_rate());
				 entity.setOperate_time(insPlanInfoList.get(j).getOperate_time());
				 userReportDtoList.add(entity);
			 }
		 }
	return userReportDtoList;
	 }
}
