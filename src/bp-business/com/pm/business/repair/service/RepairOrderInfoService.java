package com.pm.business.repair.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.basicinfo.dao.MachineInfoDAO;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.repair.dao.ApproveInfoDAO;
import com.pm.persistence.repair.dao.DistributionInfoDAO;
import com.pm.persistence.repair.dao.OfferDetailDAO;
import com.pm.persistence.repair.dao.OfferInfoDAO;
import com.pm.persistence.repair.dao.RepairCostDetailDAO;
import com.pm.persistence.repair.dao.RepairCostDetailTempDAO;
import com.pm.persistence.repair.dao.RepairMachineDetailDAO;
import com.pm.persistence.repair.dao.RepairOrderInfoDAO;
import com.pm.persistence.repair.dao.RepairResultInfoDAO;
import com.pm.persistence.repair.dto.OfferInfoDTO;
import com.pm.persistence.repair.dto.RepairOrderInfoDTO;
import com.pm.persistence.repair.entity.ApproveInfo;
import com.pm.persistence.repair.entity.DistributionInfo;
import com.pm.persistence.repair.entity.OfferDetail;
import com.pm.persistence.repair.entity.OfferInfo;
import com.pm.persistence.repair.entity.RepairCostDetail;
import com.pm.persistence.repair.entity.RepairCostDetailTemp;
import com.pm.persistence.repair.entity.RepairMachineDetail;
import com.pm.persistence.repair.entity.RepairOrderInfo;
import com.pm.persistence.repair.entity.RepairResultInfo;
import com.pm.persistence.systemfunction.dao.MaintenanceItemInfoDAO;
import com.pm.persistence.systemfunction.entity.MaintenanceItemInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Service
public class RepairOrderInfoService {

	@Resource
	private RepairOrderInfoDAO repairOrderInfoDAO;// 维修单

	@Resource
	private DistributionInfoDAO distributionInfoDAO;// 人员分配

	@Resource
	private OfferInfoDAO offerInfoDAO;// 报价

	@Resource
	private OfferDetailDAO offerDetailDAO;// 报价详情

	@Resource
	private RepairResultInfoDAO repairResultInfoDAO;// 维修结果

	@Resource
	private RepairMachineDetailDAO repairMachineDetailDAO;// 维修结果设备停机信息

	@Resource
	private RepairCostDetailDAO repairCostDetailDAO;// 维修结果费用

	@Resource
	private ApproveInfoDAO approveInfoDAO;// 审批流
	
	@Resource
	private MachineInfoDAO machineInfoDAO;// 设备
	
	@Resource
	private RepairCostDetailTempDAO reairCostDetailTempDAO;// 临时材料费
	
	@Resource
	private MaintenanceItemInfoDAO maintenanceItemInfoDAO;// 维修内容
	
	private static Logger logger = Logger.getLogger("logger");
	
	/**
	 * 分页查询维修单entity List
	 * 
	 * @author baitianhong
	 */
	public Pagination<RepairOrderInfoDTO> queryRepairOrderInfoList(
			RepairOrderInfoDTO entity, String pageflag,String unpayedpageflag,String repair_user_flag,int pageIndex, int pageSize,Processor processor){
			 if(!"".equals(entity.getStart_dt()) && entity.getStart_dt() != null){
				 entity.setStart_dt(entity.getStart_dt()+" 00:00:00 ");
             }
			 if(!"".equals(entity.getEnd_dt()) && entity.getEnd_dt() != null){
				 entity.setEnd_dt(entity.getEnd_dt()+" 23:59:59 ");
             }
		return repairOrderInfoDAO.queryRepairOrderInfoList2(entity, pageflag, unpayedpageflag, repair_user_flag,pageIndex,
				pageSize,processor);
	}
	/**
	 * 非分页查询维修单entity List
	 * 
	 * @author baitianhong
	 */
	public List<RepairOrderInfo> queryRepairOrderInfo(RepairOrderInfo entity) {
		return repairOrderInfoDAO.queryRepairOrderInfoList(entity);
	}
	/**
	 * 非分页查询维修单entity List
	 * 
	 * @author baitianhong
	 */
	public List<RepairOrderInfo> queryRepairOrderInfoList4(RepairOrderInfoDTO entity) {
		if(!"".equals(entity.getStart_dt()) && entity.getStart_dt() != null){
			 entity.setStart_dt(entity.getStart_dt()+" 00:00:00 ");
        }
		 if(!"".equals(entity.getEnd_dt()) && entity.getEnd_dt() != null){
			 entity.setEnd_dt(entity.getEnd_dt()+" 23:59:59 ");
        }
		return repairOrderInfoDAO.queryRepairOrderInfoList4(entity);
	}
	/**
	 * 非分页查询维修单entity List mob
	 * 
	 * @author baitianhong
	 */
	public List<RepairOrderInfoDTO> queryRepairOrderInfoListMob(RepairOrderInfoDTO entity,Processor processor) {
		 if(!"".equals(entity.getStart_dt()) && entity.getStart_dt() != null){
			 entity.setStart_dt(entity.getStart_dt()+" 00:00:00 ");
         }
		 if(!"".equals(entity.getEnd_dt()) && entity.getEnd_dt() != null){
			 entity.setEnd_dt(entity.getEnd_dt()+" 23:59:59 ");
         }
		 entity.setApproval_step(PmConstant.REAPIR);
		return repairOrderInfoDAO.queryRepairOrderInfoListMob(entity,processor);
	}
	/**
	 * 保存维修单实体对象entity
	 * 
	 * @author baitianhong
	 */
	public String saveRepairOrderInfo(RepairOrderInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveRepairOrderInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveRepairOrderInfo 异常！");
		}
		entity.setId(repairOrderInfoDAO.getUUId());
		return repairOrderInfoDAO.saveRepairOrderInfo2(entity);
	}

	/**
	 * 修改维修单实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int updateRepairOrderInfo(RepairOrderInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--updateRepairOrderInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--updateRepairOrderInfo 异常！");
		}
		return repairOrderInfoDAO.updateRepairOrderInfo(entity);
	}

	/**
	 * 删除维修单实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int deleteRepairOrderInfo(RepairOrderInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--deleteRepairOrderInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--deleteRepairOrderInfo 异常！");
		}
		return repairOrderInfoDAO.deleteRepairOrderInfo(entity);
	}

	/**
	 * 查询维修单实体对象entity
	 * 
	 * @author baitianhong
	 */
	public RepairOrderInfo getRepairOrderInfoById(String id) {
		return repairOrderInfoDAO.getRepairOrderInfoById(id);
	}

	/**
	 * 保存人员分配实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveDistributionInfo(DistributionInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveDistributionInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveDistributionInfo 异常！");
		}
		entity.setDistribution_id(distributionInfoDAO.getUUId());
		return distributionInfoDAO.saveDistributionInfo(entity);
	}
	/**
	 * 查询人员分配实体对象entity list
	 * 
	 * @author baitianhong
	 */
	public List<DistributionInfo> queryDistributionInfoList(DistributionInfo entity) {
		return distributionInfoDAO.queryDistributionInfoList(entity);
	}
	/**
	 * 删除人员分配实体对象entity 
	 * 
	 * @author baitianhong
	 */
	public int deleteDistributionInfo(DistributionInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--deleteDistributionInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--deleteDistributionInfo 异常！");
		}
		return distributionInfoDAO.deleteDistributionInfo(entity);
	}
	/**
	 * 保存报价实体对象entity
	 * 
	 * @author baitianhong
	 */
	public String saveOfferInfo(OfferInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveOfferInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveOfferInfo 异常！");
		}
		String str = offerInfoDAO.getUUId();
		entity.setOffer_id(str);
		offerInfoDAO.saveOfferInfo(entity);
		return str;
	}
	
	/**
	 * 保存报价详情实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveOfferDetail(OfferDetail entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveOfferDetail");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveOfferDetail 异常！");
		}
		entity.setOffer_detail_id(offerDetailDAO.getUUId());
		return offerDetailDAO.saveOfferDetail(entity);
	}

	/**
	 * 查询报价详情实体对象entity
	 * 
	 * @author baitianhong
	 */
	public List<OfferInfoDTO> queryOfferDetailListNoPage(OfferDetail entity) {
		return offerDetailDAO.queryOfferDetailListNoPage(entity);
	}

	/**
	 * 查询报价实体对象entity
	 * 
	 * @author baitianhong
	 */
	public List<OfferInfo> queryOfferInfoList(OfferInfo entity) {
		return offerInfoDAO.queryOfferInfoList2(entity);
	}
	
	/**
	 * 保存维修结果实体对象entity
	 * 
	 * @author baitianhong
	 */
	public String saveRepairResultInfo(RepairResultInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveRepairResultInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveRepairResultInfo 异常！");
		}
		String str = repairResultInfoDAO.getUUId();
		entity.setRepair_result_id(str);
		repairResultInfoDAO.saveRepairResultInfo(entity);
		return str;
	}

	/**
	 * 保存维修结果费用实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveRepairCostDetail(RepairCostDetail entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveRepairCostDetail");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveRepairCostDetail 异常！");
		}
		entity.setRepair_cost_id(repairCostDetailDAO.getUUId());
		return repairCostDetailDAO.saveRepairCostDetail(entity);
	}

	/**
	 * 保存维修结果停机时间实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveRepairMachineDetail(RepairMachineDetail entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveRepairMachineDetail");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveRepairMachineDetail 异常！");
		}
		entity.setRepair_machine_id(repairMachineDetailDAO.getUUId());
		return repairMachineDetailDAO.saveRepairMachineDetail(entity);
	}

	/**
	 * 查询维修结果实体对象entity
	 * 
	 * @author baitianhong
	 */
	public RepairResultInfo getRepairResultInfoById(RepairResultInfo entity) {
		return repairResultInfoDAO.getRepairResultInfoById(entity);
	}

	/**
	 * 查询修结果费用实体对象entity list
	 * 
	 * @author baitianhong
	 */
	public List<RepairCostDetail> queryRepairCostDetailList(RepairCostDetail entity) {
		return repairCostDetailDAO.queryRepairCostDetailList2(entity);
	}
	
	/**
	 * 查询修结果停机时间实体对象entity list
	 * 
	 * @author baitianhong
	 */
	public List<RepairMachineDetail> queryRepairMachineDetailList(RepairMachineDetail entity) {
		return repairMachineDetailDAO.queryRepairMachineDetailList2(entity);
	}
	
	/**
	 * 保存审批流实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveApproveInfo(ApproveInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveApproveInfo 异常！");
		}
		entity.setApprove_id(approveInfoDAO.getUUId());
		return approveInfoDAO.saveApproveInfo(entity);
	}

	/**
	 * 修改审批流实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int updateApproveInfo(ApproveInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--updateApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--updateApproveInfo 异常！");
		}
		return approveInfoDAO.updateApproveInfo(entity);
	}
	/**
	 * 删除审批流实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int deleteApproveInfo(ApproveInfo entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--deleteApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--deleteApproveInfo 异常！");
		}
		return approveInfoDAO.deleteApproveInfo(entity);
	}
	/**
	 * 查询审批流 entity list
	 * 
	 * @author baitianhong
	 */
	public List<ApproveInfo> queryApproveInfoListNoPage(ApproveInfo entity) {
		return approveInfoDAO.queryApproveInfoListNoPage(entity);
	}
	
	
	/**
	 * 查询设备信息 entity list
	 * 
	 * @author baitianhong
	 */
	public MachineInfo getMachineInfoById(String id) {
		return machineInfoDAO.getMachineInfoById(id);
	}
	/**
	 * 查询维修内容信息 entity list
	 * 
	 * @author baitianhong
	 */
	public List<MaintenanceItemInfo> queryMaintenanceItemInfoList(MaintenanceItemInfo entity) {
		return maintenanceItemInfoDAO.queryMaintenanceItemInfoList2(entity);
	}
	/**
	 * 查询临时材料费信息 entity list
	 * 
	 * @author baitianhong
	 */
	public List<RepairCostDetailTemp> queryRepairCostDetailTempList(RepairCostDetailTemp entity) {
		return reairCostDetailTempDAO.queryRepairCostDetailTempList2(entity);
	}
	
	/**
	 * 查询临时材料费信息 entity
	 * 
	 * @author baitianhong
	 */
	public RepairCostDetailTemp getRepairCostDetailTempById(String id) {
		return reairCostDetailTempDAO.getRepairCostDetailTempById(id);
	}
	
	/**
	 * 添加临时材料费信息 entity
	 * 
	 * @author baitianhong
	 */
	public int saveRepairCostDetailTemp(RepairCostDetailTemp entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--saveRepairCostDetailTemp");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--saveRepairCostDetailTemp 异常！");
		}
		entity.setRepair_cost_id(reairCostDetailTempDAO.getUUId());
		return reairCostDetailTempDAO.saveRepairCostDetailTemp(entity);
	}
	
	/**
	 * 修改临时材料费信息 entity
	 * 
	 * @author baitianhong
	 */
	public int updateRepairCostDetailTemp(RepairCostDetailTemp entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--updateRepairCostDetailTemp");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--updateRepairCostDetailTemp 异常！");
		}
		return reairCostDetailTempDAO.updateRepairCostDetailTemp(entity);
	}
	
	/**
	 * 删除临时材料费信息 entity
	 * 
	 * @author baitianhong
	 */
	public int deleteRepairCostDetailTemp(RepairCostDetailTemp entity) {
		try {
			logger.info("事务操作方法：RepairOrderInfoService--deleteRepairCostDetailTemp");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：RepairOrderInfoService--deleteRepairCostDetailTemp 异常！");
		}
		return reairCostDetailTempDAO.deleteRepairCostDetailTemp(entity);
	}
	/**
	 * 查询设备列表 手机端
	 * 
	 * @author baitianhong
	 */
	public List<MachineInfo> queryMachineInfoListPopForRepairMob(MachineInfo entity,String ids) {
		return machineInfoDAO.queryMachineInfoListPopForRepairMob(entity,ids);
	}
}
