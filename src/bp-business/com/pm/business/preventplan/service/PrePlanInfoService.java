package com.pm.business.preventplan.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.preventplan.dao.PrePlanCheckDetailDAO;
import com.pm.persistence.preventplan.dao.PrePlanInfoDAO;
import com.pm.persistence.preventplan.dao.PrePlanMachineInfoDAO;
import com.pm.persistence.preventplan.dao.PrePlanPropCheckDetailDAO;
import com.pm.persistence.preventplan.dao.PrePlanPropMachineInfoDAO;
import com.pm.persistence.preventplan.dto.PrePlanInfoDTO;
import com.pm.persistence.preventplan.entity.PrePlanCheckDetail;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.pm.persistence.preventplan.entity.PrePlanMachineInfo;
import com.pm.persistence.preventplan.entity.PrePlanPropCheckDetail;
import com.pm.persistence.preventplan.entity.PrePlanPropMachineInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Service
public class PrePlanInfoService {

	@Resource
	private PrePlanInfoDAO prePlanInfoDAO;
	@Resource
	private PrePlanCheckDetailDAO prePlanCheckDetailDAO;
	@Resource
	private PrePlanMachineInfoDAO prePlanMachineInfoDAO;
	@Resource
	private PrePlanPropMachineInfoDAO prePlanPropMachineInfoDAO;
	@Resource
	private PrePlanPropCheckDetailDAO prePlanPropCheckDetailDAO;
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 保養計劃列表頁面
	 */
	public Pagination<PrePlanInfo> queryPrePlanInfoList(PrePlanInfo entity,int pageIndex, int pageSize) {
		return prePlanInfoDAO.queryPrePlanInfoList(entity, pageIndex, pageSize);
	}
  /**
   * 查询entity List
   */
    public List<PrePlanInfo> queryPrePlanInfoListNoPage(PrePlanInfo entity) {
    	return prePlanInfoDAO.queryPrePlanInfoListNoPage(entity);
    }
    
	/**
	 * 修改实体对象entity
	 */
	public int updatePrePlanInfo(PrePlanInfo entity) {
		try {
			logger.info("事务操作方法：PrePlanInfoService--updatePrePlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--updatePrePlanInfo 异常！");
		}
		return prePlanInfoDAO.updatePrePlanInfo(entity);
	}

	/**
	 * 查询预防性维护检查项目
	 */
	public List<String[]> getPrePlanCheckDetail(PrePlanInfo entity) {
		return prePlanCheckDetailDAO.getPrePlanCheckDetail(entity.getPre_plan_id());

	}

	/**
	 * 查询预见性维护检查项目
	 */
	public List<String[]> getPrePlanPropCheckDetail(PrePlanInfo entity) {
		return prePlanCheckDetailDAO.getPrePlanPropCheckDetail(entity.getPre_plan_id());
	}

	/**
	 * 新增保养计划
	 */
	public int savePerPlan(PrePlanInfoDTO prePlanInfoDTO) {
		try {
			logger.info("事务操作方法：PrePlanInfoService--savePerPlan");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--savePerPlan 异常！");
		}
		return prePlanInfoDAO.savePerPlan(prePlanInfoDTO);
	}

	/**
	 * 删除当前计划
	 */
	public int deletePrePlanInfo(PrePlanInfo entity) {
		try {
			logger.info("事务操作方法：PrePlanInfoService--deletePrePlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--deletePrePlanInfo 异常！");
		}
		return prePlanInfoDAO.deletePrePlanInfo(entity);
	}
	
	/**
	 * 批量删除当前计划
	 */
	public int deleteStateBatch(PrePlanInfo entity) {
		int i = 0;
		
		try {
			logger.info("事务操作方法：PrePlanInfoService--deletePrePlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--deletePrePlanInfo 异常！");
		}
		//删除pre_plan_check_detail
		i += prePlanInfoDAO.deleteCheck_detail(entity);
		
		//删除pre_plan_machine_info
		i += prePlanInfoDAO.deleteMachine_info(entity);
		
		//删除pre_plan_info
		i += prePlanInfoDAO.deletePlan_info(entity);
 
		 return i;
	}

	/**
	 * 根据计划Id返回entity
	 */
	public PrePlanInfo getPrePlanInfoById(String id) {
		return prePlanInfoDAO.getPrePlanInfoById(id);
	}

	/**
	 * 根据计划ID得到设备list
	 */
	public List<PrePlanMachineInfo> queryPrePlanMachineInfoListByPrePlanId(String id) {
		PrePlanMachineInfo entity = new PrePlanMachineInfo();
		entity.setPre_plan_id(id);
		return prePlanMachineInfoDAO.queryPrePlanMachineInfoListByPrePlanId(entity);
	}
	
	/**
	 * 根据计划ID得到设备list
	 */
	public List<PrePlanMachineInfo> queryPrePlanMachineInfoList(PrePlanMachineInfo entity) {
		return prePlanMachineInfoDAO.queryPrePlanMachineInfoListByPrePlanId(entity);
	}
	
	/**
	 * 根据计划ID得到设备list
	 */
	public List<PrePlanMachineInfo> queryPrePlanMachineInfoListNoPage(PrePlanMachineInfo entity) {
		return prePlanMachineInfoDAO.queryPrePlanMachineInfoListByPrePlanId(entity);
	}

	/**
	 * 根据计划ID得到检查项目list
	 */
	public List<PrePlanCheckDetail> queryPrePlanCheckDetailListByPlanMachineId(String id) {
		PrePlanCheckDetail entity = new PrePlanCheckDetail();
		entity.setPlan_machine_id(id);
		return prePlanCheckDetailDAO.queryPrePlanCheckDetailListByPlanMachineId(entity);
	}
	
	/**
	 * 保养计划检查项目详情LIst
	 */
	public void updatePreMstThreeTable(PrePlanInfoDTO prePlanInfoDTO) throws SQLException {
		String flag=prePlanInfoDTO.getUpdate_flag();		
		PrePlanInfo prePlanInfo= new PrePlanInfo();		
		try {
			logger.info("事务操作方法：PrePlanInfoService--updatePreMstThreeTable/flag:"+flag);
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--updatePreMstThreeTable 异常！");
		}
		if(flag.equals(PmConstant.UPDATE_FLAG_1)){	//单条更新
			//删除已有数据				
			prePlanInfo.setPre_plan_id(prePlanInfoDTO.getPre_plan_id());
			prePlanInfoDAO.deletePrePlanInfo(prePlanInfo);
			//插入一条最新数据
			//使结束日期=开始日期  循环一次
			prePlanInfoDTO.setPre_plan_date_end(prePlanInfoDTO.getPre_plan_date());
			prePlanInfoDAO.savePrePlanOnePro(prePlanInfoDTO);
		}else if(flag.equals(PmConstant.UPDATE_FLAG_0)){//批量更新				
			//得到对应同一个group_id 的 plan_id PlanList 保存同group_id下的最大日期
			prePlanInfo.setPre_plan_group(prePlanInfoDTO.getPre_plan_group());	
			List<PrePlanInfo> tempList=prePlanInfoDAO.queryPrePlansGroupList(prePlanInfo);
			prePlanInfoDTO.setPre_plan_date_end(tempList.get(0).getPre_plan_date());			
			//批量删除			
			prePlanInfo.setPre_plan_id(prePlanInfoDTO.getPre_plan_id());					
			PrePlanInfo en = prePlanInfoDAO.queryPrePlanInfoListNoPage(prePlanInfo).get(0);
			prePlanInfo.setPre_plan_date(en.getPre_plan_date());	
			prePlanInfoDAO.deletePrePlanBatchPro(prePlanInfo);
			//重新插入
			prePlanInfoDTO.setPre_plan_date(prePlanInfoDTO.getPre_plan_date().substring(0,10));
			prePlanInfoDAO.savePerPlan(prePlanInfoDTO);
		}else if(flag.equals(PmConstant.UPDATE_FLAG_2)){ //批量删除
			//得到对应同一个group_id 的 plan_id PlanList 保存同group_id下的最大日期
			prePlanInfo.setPre_plan_group(prePlanInfoDTO.getPre_plan_group());	
			List<PrePlanInfo> tempList=prePlanInfoDAO.queryPrePlansGroupList(prePlanInfo);
			prePlanInfoDTO.setPre_plan_date_end(tempList.get(0).getPre_plan_date());			
			//批量删除			
			prePlanInfo.setPre_plan_id(prePlanInfoDTO.getPre_plan_id());					
			PrePlanInfo en = prePlanInfoDAO.queryPrePlanInfoListNoPage(prePlanInfo).get(0);
			prePlanInfo.setPre_plan_date(en.getPre_plan_date());	
			prePlanInfoDAO.deletePrePlanBatchPro(prePlanInfo);
		}
	}
	
	/**
	 * 同group LIst
	 */
	public List<PrePlanInfo> queryplanGroupList(PrePlanInfo prePlanInfo) throws SQLException {
		List<PrePlanInfo> tempList=prePlanInfoDAO.queryPrePlansGroupList(prePlanInfo);
		return tempList;
	}
	
	/**
	 * 保养计划检查项目录入信息
	 * @param entity
	 * @param processor
	 * @return
	 * @throws SQLException 
	 */
	public void updatePerThreeTableMob(PrePlanInfo prePlanInfo,PrePlanMachineInfo prePlanMachineInfo,List<PrePlanCheckDetail> checkitemList,Processor processor) {
		//更新checkitemList
		for(int i=0;i<checkitemList.size();i++){
			checkitemList.get(i).setOperate_date(prePlanInfo.getOperate_date());
			checkitemList.get(i).setUpdate_id(processor.getCurrentUserId());
			checkitemList.get(i).setUpdate_time(prePlanInfo.getOperate_date());
			try {
				logger.info("事务操作方法：PrePlanInfoService--updatePerThreeTableMob/updatePrePlanCheckDetail");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：PrePlanInfoService--updatePerThreeTableMob/updatePrePlanCheckDetail 异常！");
			}
			prePlanCheckDetailDAO.updatePrePlanCheckDetail(checkitemList.get(i));
		}
		//计算rate 更新 plan_machine的操作时间（实际时间）
		PrePlanCheckDetail checktemp = new PrePlanCheckDetail();
		checktemp.setPlan_machine_id(prePlanMachineInfo.getPlan_machine_id());
		List<PrePlanCheckDetail> tempList=prePlanCheckDetailDAO.queryPrePlanCheckDetailListByPlanMachineId(checktemp);
		int itemcount=0;
		for(int j=0;j<tempList.size();j++){
			if(!"".equals(tempList.get(j).getCheck_detail())||!"".equals(tempList.get(j).getCheck_detail2())){
				itemcount++;
			}
		}
		prePlanMachineInfo.setOperate_date(prePlanInfo.getOperate_date());
		prePlanMachineInfo.setPre_check_num(itemcount+"");
		prePlanMachineInfo.setM_pm_rate(Math.round((double)itemcount/tempList.size()*100)+"");
		prePlanMachineInfo.setUpdate_id(processor.getCurrentUserId());
		prePlanMachineInfo.setUpdate_time(prePlanInfo.getOperate_date());
		try {
			logger.info("事务操作方法：PrePlanInfoService--updatePerThreeTableMob/updatePrePlanMachineInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--updatePerThreeTableMob/updatePrePlanMachineInfo 异常！");
		}
		prePlanMachineInfoDAO.updatePrePlanMachineInfo(prePlanMachineInfo);
		//计算rate 更新 ins_plan的操作时间（实际时间）
		PrePlanMachineInfo machtemp = new PrePlanMachineInfo();
		machtemp.setPre_plan_id(prePlanInfo.getPre_plan_id());
		List<PrePlanMachineInfo> matempList=prePlanMachineInfoDAO.queryPrePlanMachineInfoListByPrePlanId(machtemp);
		int machcount=0;
		for(int p=0;p<matempList.size();p++){
			if(matempList.get(p).getPre_check_all().equals(matempList.get(p).getPre_check_num())){
				machcount++;
			}
		}
		prePlanInfo.setChk_mach_num(machcount+"");
		prePlanInfo.setPre_pm_rate(Math.round((double)machcount/matempList.size()*100)+"");
		prePlanInfo.setUpdate_id(processor.getCurrentUserId());
		prePlanInfo.setUpdate_time(prePlanInfo.getOperate_date());
		if(PmConstant.PM_RATE.equals(prePlanInfo.getPre_pm_rate())){
			prePlanInfo.setPre_plan_state(PmConstant.PLAN_STATE_02);
		}
		try {
			logger.info("事务操作方法：PrePlanInfoService--updatePerThreeTableMob/updatePrePlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--updatePerThreeTableMob/updatePrePlanInfo 异常！");
		}
		prePlanInfoDAO.updatePrePlanInfo(prePlanInfo);
	}
	/**
	 * 预见性保养计划设备list-手机端
	 * @param preplanid
	 * @return
	 */
    public List<PrePlanPropMachineInfo> queryPrePlanPropMachineInfoListNoPage(PrePlanPropMachineInfo entity) {
        return prePlanPropMachineInfoDAO.queryPrePlanPropMachineInfoListNoPage(entity);
      }
    
    /**
	 * 保存预见性维护设备录入内容-手机端
	 * @param prePlanInfo
	 * @param processor
	 * @return
	 */
    public int updatePrePlanPropMachineInfo(List<PrePlanPropMachineInfo> prePlanPropMachineInfo,String chk_type,Processor processor){
    	int cnt = 0;
    	for(int i=0;i<prePlanPropMachineInfo.size();i++){
    		//检查项目总数
			PrePlanMachineInfo prePlanMachineInfoList = prePlanMachineInfoDAO.getPrePlanMachineInfoByPrePlanId(prePlanPropMachineInfo.get(i).getPre_plan_id());
			prePlanPropMachineInfo.get(i).setPre_check_all(prePlanMachineInfoList.getPre_check_all());
    		//保养类型分类（预见性维修/大修）
    		prePlanPropMachineInfo.get(i).setChk_type(chk_type);
    		//判断数据存储分类update/add
    		if(PmConstant.EDITTYPE_UPDATE.equals(prePlanPropMachineInfo.get(i).getEditType())){
    			prePlanPropMachineInfo.get(i).setUpdate_id(processor.getCurrentUserId());
    			try {
					prePlanPropMachineInfo.get(i).setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
    			if(StringUtils.isNullOrEmpty(prePlanPropMachineInfo.get(i).getProp_machine_id())){
    				try {
    					logger.info("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/deletePrePlanPropMachineInfo");
    					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
    				} catch (Exception e) {
    					logger.error("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/deletePrePlanPropMachineInfo 异常！");
    				}
    				cnt += prePlanPropMachineInfoDAO.deletePrePlanPropMachineInfo(prePlanPropMachineInfo.get(i));
    				try {
    					logger.info("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/deletePrePlanPropCheckDetailByPlanId");
    					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
    				} catch (Exception e) {
    					logger.error("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/deletePrePlanPropCheckDetailByPlanId 异常！");
    				}
    				cnt += prePlanPropCheckDetailDAO.deletePrePlanPropCheckDetailByPlanId(prePlanPropMachineInfo.get(i).getProp_plan_id());
    			}else{
    				try {
    					logger.info("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/updatePrePlanPropMachineInfo");
    					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
    				} catch (Exception e) {
    					logger.error("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/updatePrePlanPropMachineInfo 异常！");
    				}
    				cnt += prePlanPropMachineInfoDAO.updatePrePlanPropMachineInfo(prePlanPropMachineInfo.get(i));
    			}
    		}else{
    			if(!StringUtils.isNullOrEmpty(prePlanPropMachineInfo.get(i).getProp_machine_id())){
    				prePlanPropMachineInfo.get(i).setCreate_id(processor.getCurrentUserId());
        			try {
    					prePlanPropMachineInfo.get(i).setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
    				} catch (Exception e) {
    					System.out.println(e.getMessage());
    				}
        			try {
    					logger.info("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/savePrePlanPropMachineInfo");
    					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
    				} catch (Exception e) {
    					logger.error("事务操作方法：PrePlanInfoService--updatePrePlanPropMachineInfo/savePrePlanPropMachineInfo 异常！");
    				}
        			cnt += prePlanPropMachineInfoDAO.savePrePlanPropMachineInfo(prePlanPropMachineInfo.get(i));
    			}
    		}
    	}
    	return cnt;
    }
    
    /**
     * 查询预见性维护检查内容-手机端
     * @param entity
     * @return
     */
    public List<PrePlanPropCheckDetail> queryPrePlanPropCheckDetailListNoPage(PrePlanPropCheckDetail entity) {
        return prePlanPropCheckDetailDAO.queryPrePlanPropCheckDetailListNoPage(entity);
    }
    
    /**
     * 根据实体对象Id返回entity
     * @author 刘镝
     */
      public PrePlanPropMachineInfo getPrePlanPropMachineInfoById(String id) {
        return prePlanPropMachineInfoDAO.getPrePlanPropMachineInfoById(id);
      }

      /**
  	 * 保存预见性维护检查内容录入内容-手机端
  	 * @param prePlanInfo
  	 * @param processor
  	 * @return
  	 */
      public int updatePrePlanPropCheckitemInfo(List<PrePlanPropCheckDetail> prePlanPropCheckDetail,Processor processor){
      	int cnt = 0;
      	for(int i=0;i<prePlanPropCheckDetail.size();i++){
      		try {
				prePlanPropCheckDetail.get(i).setOperate_date(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
      		//判断数据存储分类update/add
      		if(PmConstant.EDITTYPE_UPDATE.equals(prePlanPropCheckDetail.get(i).getEditType())){
      			prePlanPropCheckDetail.get(i).setUpdate_id(processor.getCurrentUserId());
      			try {
      				prePlanPropCheckDetail.get(i).setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
  				} catch (Exception e) {
  					System.out.println(e.getMessage());
  				}
      			try {
					logger.info("事务操作方法：PrePlanInfoService--updatePrePlanPropCheckitemInfo/updatePrePlanPropCheckDetail");
					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
				} catch (Exception e) {
					logger.error("事务操作方法：PrePlanInfoService--updatePrePlanPropCheckitemInfo/updatePrePlanPropCheckDetail 异常！");
				}
      			cnt += prePlanPropCheckDetailDAO.updatePrePlanPropCheckDetail(prePlanPropCheckDetail.get(i));
      		}else{
      			if(!StringUtils.isNullOrEmpty(prePlanPropCheckDetail.get(i).getCheck_detail())){
      				prePlanPropCheckDetail.get(i).setCreate_id(processor.getCurrentUserId());
          			try {
          				prePlanPropCheckDetail.get(i).setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
      				} catch (Exception e) {
      					System.out.println(e.getMessage());
      				}
          			try {
    					logger.info("事务操作方法：PrePlanInfoService--updatePrePlanPropCheckitemInfo/savePrePlanPropCheckDetail");
    					logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
    				} catch (Exception e) {
    					logger.error("事务操作方法：PrePlanInfoService--updatePrePlanPropCheckitemInfo/savePrePlanPropCheckDetail 异常！");
    				}
          			cnt += prePlanPropCheckDetailDAO.savePrePlanPropCheckDetail(prePlanPropCheckDetail.get(i));
      			}
      		}
      	}
      	return cnt;
      }
      
  	/**
  	 * update state 批量
  	 * @param id
  	 * @param processor
  	 * @return
  	 * @throws Exception 
  	 */
  	public int updateStateBatch(PrePlanInfo prePlanInfo,Processor processor) throws Exception {
  		try {
			logger.info("事务操作方法：PrePlanInfoService--updateStateBatch");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：PrePlanInfoService--updateStateBatch 异常！");
		}
  		int update_flag=prePlanInfoDAO.updateStateBatch(prePlanInfo);
  		return update_flag;
  	}
}
