package com.pm.business.inspectplan.service;


import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.inspectplan.dao.InsPlanCheckDetailDAO;
import com.pm.persistence.inspectplan.dao.InsPlanInfoDAO;
import com.pm.persistence.inspectplan.dao.InsPlanMachineInfoDAO;
import com.pm.persistence.inspectplan.dto.InsPlanInfoDTO;
import com.pm.persistence.inspectplan.entity.InsPlanCheckDetail;
import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.inspectplan.entity.InsPlanMachineInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Service("InspectPlanService")
public class InspectPlanService {
	@Resource(name="InsPlanInfoDAO")
	private InsPlanInfoDAO insPlanInfoDAO;
	@Resource(name="InsPlanMachineInfoDAO")
	private InsPlanMachineInfoDAO insMachineDAO;
	@Resource(name="InsPlanCheckDetailDAO")
	private InsPlanCheckDetailDAO insCheckDAO;
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 保存巡检计划
	 * @param entity
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public void saveInsMstThreeTable(InsPlanInfoDTO insPlanInfoDTO) throws Exception {
		try {
			logger.info("事务操作方法：InspectPlanService--saveInsPlanPro");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--saveInsPlanPro 异常！");
		}
		insPlanInfoDAO.saveInsPlanPro(insPlanInfoDTO);
	}
	/**
	 * delete巡检计划
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public void deleteInspectplans(String ins_plan_id) throws Exception {
		InsPlanInfo entity =new InsPlanInfo();
		entity.setIns_plan_id(ins_plan_id);
		try {
			logger.info("事务操作方法：InspectPlanService--deleteInspectplans");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--deleteInspectplans 异常！");
		}
		insPlanInfoDAO.deleteInsPlanPro(entity);		
	}
	/**
	 * update巡检计划
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateInspectplans(InsPlanInfo insPlanInfo) throws Exception {
		try {
			logger.info("事务操作方法：InspectPlanService--updateInsPlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--updateInsPlanInfo 异常！");
		}
		int update_flag=insPlanInfoDAO.updateInsPlanInfo(insPlanInfo);
		return update_flag;
	}
	/**
	 * 查询巡检计划列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<InsPlanInfo> getInsPlanList(InsPlanInfo insPlanInfo, int pageIndex, int pageSize) {
		Pagination<InsPlanInfo> insPlanInfoList = 
				insPlanInfoDAO.queryInsPlanInfoList(insPlanInfo,pageIndex,pageSize);
		
		return insPlanInfoList;
	}
	/**
	 * 查询巡检计划列表groupby
	 * @param entity
	 * @param processor
	 * @return
	 */
	public Pagination<InsPlanInfo> getInsPlanListGroupby(InsPlanInfo insPlanInfo, int pageIndex, int pageSize) {
		Pagination<InsPlanInfo> insPlanInfoList = 
				insPlanInfoDAO.queryInsPlanInfoListGroupby(insPlanInfo,pageIndex,pageSize);
		
		return insPlanInfoList;
	}
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<String[]> getInsPlanCheckDetailList(String pro) throws SQLException {
		List<String[]> aa=insCheckDAO.getInsPlanCheckDetailList(pro);
		return aa;
	}
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<InsPlanInfo> queryInsPlansListNo(InsPlanInfo insPlanInfo) throws SQLException {
		List<InsPlanInfo> aa=insPlanInfoDAO.queryInsPlansList(insPlanInfo);
		return aa;
	}
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<InsPlanMachineInfo> queryMachineViewList(String id) throws SQLException {
		List<InsPlanMachineInfo> tempList=insPlanInfoDAO.queryMachineInfoViewList(id);
		return tempList;
	}
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<InsPlanCheckDetail> queryCheckItemViewList(String id) throws SQLException {
		List<InsPlanCheckDetail> tempList=insPlanInfoDAO.queryCheckItemViewList(id);
		return tempList;
	}
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public void updateInsMstThreeTable(InsPlanInfoDTO insPlanInfoDTO) throws SQLException {
		String flag=insPlanInfoDTO.getUpdate_flag();		
		InsPlanInfo insPlanInfo= new InsPlanInfo();			
		try {
			logger.info("事务操作方法：InspectPlanService--updateInsMstThreeTable/flag:"+flag);
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--updateInsMstThreeTable 异常！");
		}
		if(flag.equals(PmConstant.UPDATE_FLAG_1)){	//单条更新
			//删除已有数据				
			insPlanInfo.setIns_plan_id(insPlanInfoDTO.getPlan_id());
			insPlanInfoDAO.deleteInsPlanPro(insPlanInfo);
			//插入一条最新数据
			//使结束日期=开始日期  循环一次
			insPlanInfoDTO.setPre_plan_date_end(insPlanInfoDTO.getPre_plan_date());
			insPlanInfoDAO.saveInsPlanOnePro(insPlanInfoDTO);
		}else if(flag.equals(PmConstant.UPDATE_FLAG_0)){//批量更新				
			//得到对应同一个group_id 的 plan_id PlanList 保存同group_id下的最大日期		
			insPlanInfo.setIns_plan_group(insPlanInfoDTO.getPre_plan_group());
			List<InsPlanInfo> tempList=insPlanInfoDAO.queryInsPlansGroupList(insPlanInfo);
			insPlanInfoDTO.setPre_plan_date_end(tempList.get(0).getIns_plan_date());	
			
			//批量删除 同groupid下 删除这条数据的从‘原’计划日期开始的所有数据。
			insPlanInfo.setIns_plan_id(insPlanInfoDTO.getPlan_id());					
			InsPlanInfo en = insPlanInfoDAO.queryInsPlansList(insPlanInfo).get(0);
			insPlanInfo.setIns_plan_date(en.getIns_plan_date());			
			insPlanInfoDAO.deleteInsPlanBatchPro(insPlanInfo);
			//重新插入				
			insPlanInfoDAO.saveInsPlanPro(insPlanInfoDTO);
		}else if(flag.equals(PmConstant.UPDATE_FLAG_2)){ //批量删除
			//得到对应同一个group_id 的 plan_id PlanList 保存同group_id下的最大日期
			insPlanInfo.setIns_plan_group(insPlanInfoDTO.getPre_plan_group());	
			List<InsPlanInfo> tempList=insPlanInfoDAO.queryInsPlansGroupList(insPlanInfo);
			insPlanInfoDTO.setPre_plan_date_end(tempList.get(0).getIns_plan_date());			
			//批量删除 同groupid下 删除这条数据的从‘原’计划日期开始的所有数据。
			insPlanInfo.setIns_plan_id(insPlanInfoDTO.getPlan_id());					
			InsPlanInfo en = insPlanInfoDAO.queryInsPlansList(insPlanInfo).get(0);
			insPlanInfo.setIns_plan_date(en.getIns_plan_date());			
			insPlanInfoDAO.deleteInsPlanBatchPro(insPlanInfo);
		}
	}
	
	/**
	 * 同group LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<InsPlanInfo> queryplanGroupList(InsPlanInfo insPlanInfo) throws SQLException {
		List<InsPlanInfo> tempList=insPlanInfoDAO.queryInsPlansGroupList(insPlanInfo);
		return tempList;
	}
	/**
	 * 查询巡检计划列表
	 * @param entity
	 * @param processor
	 * @return
	 */
	public List<InsPlanInfo> getInsPlanListMob(InsPlanInfo insPlanInfo) {
		List<InsPlanInfo> insPlanInfoList = 
				insPlanInfoDAO.queryInsPlanInfoListMob(insPlanInfo);
		
		return insPlanInfoList;
	}
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<InsPlanCheckDetail> queryCheckItemViewListMob(InsPlanCheckDetail insPlanCheckDetail) throws SQLException {
		List<InsPlanCheckDetail> tempList=insCheckDAO.queryInsPlanCheckListMob(insPlanCheckDetail);
		return tempList;
	}
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<InsPlanMachineInfo> queryMachineViewListMob(InsPlanMachineInfo insPlanMachineInfo) throws SQLException {
		List<InsPlanMachineInfo> tempList=insMachineDAO.queryMachineInfoViewListMob(insPlanMachineInfo);
		return tempList;
	}
	
	/**
	 * 巡检计划检查项目详情LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public void updateInsMstThreeTableMob(InsPlanInfo insPlanInfo,InsPlanMachineInfo insMachineInfo,List<InsPlanCheckDetail> checkitemList,Processor processor) throws SQLException, Exception {
		//更新checkitemList
		for(int i=0;i<checkitemList.size();i++){
			checkitemList.get(i).setOperate_date(insPlanInfo.getOperate_time());
			checkitemList.get(i).setUpdate_id(processor.getCurrentUserId());
			checkitemList.get(i).setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			try {
				logger.info("事务操作方法：InspectPlanService--updateInsMstThreeTableMob/updateInsPlanCheckDetail");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：InspectPlanService--updateInsMstThreeTableMob/updateInsPlanCheckDetail 异常！");
			}
			insCheckDAO.updateInsPlanCheckDetail(checkitemList.get(i));
		}
		//计算rate 更新 plan_machine的操作时间（实际时间）
		InsPlanCheckDetail checktemp = new InsPlanCheckDetail();
		checktemp.setPlan_machine_id(insMachineInfo.getPlan_machine_id());
		List<InsPlanCheckDetail> tempList=insCheckDAO.queryInsPlanCheckListMob(checktemp);
		int itemcount=0;
		for(int j=0;j<tempList.size();j++){
			if(!"".equals(tempList.get(j).getCheck_detail())||!"".equals(tempList.get(j).getCheck_detail2())){
				itemcount++;
			}
		}
		insMachineInfo.setOperate_date(insPlanInfo.getOperate_time());
		insMachineInfo.setIns_check_num(itemcount+"");
		insMachineInfo.setIns_check_rate(Math.round((double)itemcount/tempList.size()*100)+"");
		insMachineInfo.setUpdate_id(processor.getCurrentUserId());
		insMachineInfo.setUpdate_time(insPlanInfo.getOperate_time());
		try {
			logger.info("事务操作方法：InspectPlanService--updateInsMstThreeTableMob/updateInsPlanMachineInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--updateInsMstThreeTableMob/updateInsPlanMachineInfo 异常！");
		}
		insMachineDAO.updateInsPlanMachineInfo(insMachineInfo);
		//计算rate 更新 ins_plan的操作时间（实际时间）
		InsPlanMachineInfo machtemp = new InsPlanMachineInfo();
		machtemp.setIns_plan_id(insPlanInfo.getIns_plan_id());
		List<InsPlanMachineInfo> matempList=insMachineDAO.queryInsPlanMachineList(machtemp);
		int machcount=0;
		for(int p=0;p<matempList.size();p++){
			if(matempList.get(p).getIns_check_all().equals(matempList.get(p).getIns_check_num())){
				machcount++;
			}
		}
		insPlanInfo.setChk_mach_num(machcount+"");
		insPlanInfo.setIns_pm_rate(Math.round((double)machcount/matempList.size()*100)+"");
		if(PmConstant.PM_RATE.equals(insPlanInfo.getIns_pm_rate())){
			insPlanInfo.setIns_plan_state(PmConstant.PLAN_STATE_02);
		}
		insPlanInfo.setUpdate_id(processor.getCurrentUserId());
		insPlanInfo.setUpdate_time(insPlanInfo.getOperate_time());
		try {
			logger.info("事务操作方法：InspectPlanService--updateInsMstThreeTableMob/updateInsPlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--updateInsMstThreeTableMob/updateInsPlanInfo 异常！");
		}
		insPlanInfoDAO.updateInsPlanInfo(insPlanInfo);
	}
	/**
	 * update state 批量
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int updateStateBatch(InsPlanInfo insPlanInfo,Processor processor) throws Exception {
		try {
			logger.info("事务操作方法：InspectPlanService--updateStateBatch");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--updateStateBatch 异常！");
		}
		int update_flag=insPlanInfoDAO.updateStateBatch(insPlanInfo);
		return update_flag;
	}
	/**
	 * delete 批量
	 * @param id
	 * @param processor
	 * @return
	 * @throws Exception 
	 */
	public int deleteBatch(InsPlanInfo insPlanInfo,Processor processor) throws Exception {
		try {
			logger.info("事务操作方法：InspectPlanService--deleteBatch");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：InspectPlanService--deleteBatch 异常！");
		}
		int update_flag=insPlanInfoDAO.deleteBatch(insPlanInfo);
		return update_flag;
	}
	/**
	 * 同group LIst
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public List<InsPlanInfo> queryCheckInsPlansGroupList(InsPlanInfo insPlanInfo) throws SQLException {
		List<InsPlanInfo> tempList=insPlanInfoDAO.queryCheckInsPlansGroupList(insPlanInfo);
		return tempList;
	}
}
