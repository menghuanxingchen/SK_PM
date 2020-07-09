package com.pm.business.proposal.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.basicinfo.dao.MachineInfoDAO;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.proposal.dao.ProposalApproveInfoDAO;
import com.pm.persistence.proposal.dao.ProposalDistributionInfoDAO;
import com.pm.persistence.proposal.dao.ProposalInfoDAO;
import com.pm.persistence.proposal.dao.ProposalOperateInfoDAO;
import com.pm.persistence.proposal.dto.ProposalInfoDTO;
import com.pm.persistence.proposal.entity.ProposalApproveInfo;
import com.pm.persistence.proposal.entity.ProposalDistributionInfo;
import com.pm.persistence.proposal.entity.ProposalInfo;
import com.pm.persistence.proposal.entity.ProposalOperateInfo;
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
public class ProposalInfoService {

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
	
	@Resource
	private ProposalInfoDAO proposalInfoDAO;
	
	@Resource
	private ProposalApproveInfoDAO proposalApproveInfoDAO;
	
	@Resource
	private ProposalDistributionInfoDAO proposalDistributionInfoDAO;
	
	@Resource
	private ProposalOperateInfoDAO proposalOperateInfoDAO;
	
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 分页查询维修单entity List
	 * 
	 * @author baitianhong
	 */
//	public Pagination<RepairOrderInfoDTO> queryRepairOrderInfoList(
//			RepairOrderInfoDTO entity, String pageflag,String repair_user_flag,int pageIndex, int pageSize,Processor processor){
//			 if(!"".equals(entity.getStart_dt()) && entity.getStart_dt() != null){
//				 entity.setStart_dt(entity.getStart_dt()+" 00:00:00 ");
//             }
//			 if(!"".equals(entity.getEnd_dt()) && entity.getEnd_dt() != null){
//				 entity.setEnd_dt(entity.getEnd_dt()+" 23:59:59 ");
//             }
//		return repairOrderInfoDAO.queryRepairOrderInfoList2(entity, pageflag, repair_user_flag,pageIndex,
//				pageSize,processor);
//	}
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
	 * 修改维修单实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int updateRepairOrderInfo(RepairOrderInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--updateRepairOrderInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--updateRepairOrderInfo 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--deleteRepairOrderInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--deleteRepairOrderInfo 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--saveDistributionInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveDistributionInfo 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--deleteDistributionInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--deleteDistributionInfo 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--saveOfferInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveOfferInfo 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--saveOfferDetail");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveOfferDetail 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--saveRepairResultInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveRepairResultInfo 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--saveRepairCostDetail");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveRepairCostDetail 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--saveRepairMachineDetail");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveRepairMachineDetail 异常！");
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
	 * 修改审批流实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int updateApproveInfo(ApproveInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--updateApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--updateApproveInfo 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--deleteApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--deleteApproveInfo 异常！");
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
		entity.setRepair_cost_id(reairCostDetailTempDAO.getUUId());
		try {
			logger.info("事务操作方法：ProposalInfoService--saveRepairCostDetailTemp");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveRepairCostDetailTemp 异常！");
		}
		return reairCostDetailTempDAO.saveRepairCostDetailTemp(entity);
	}
	
	/**
	 * 修改临时材料费信息 entity
	 * 
	 * @author baitianhong
	 */
	public int updateRepairCostDetailTemp(RepairCostDetailTemp entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--updateRepairCostDetailTemp");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--updateRepairCostDetailTemp 异常！");
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
			logger.info("事务操作方法：ProposalInfoService--deleteRepairCostDetailTemp");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--deleteRepairCostDetailTemp 异常！");
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
	
	
	
	
	
	
	
	/**
	 * 保存提案書实体对象entity
	 * 
	 * @author baitianhong
	 */
	public String saveProposalInfo(ProposalInfo entity) {
		entity.setId(proposalInfoDAO.getUUId());
		try {
			logger.info("事务操作方法：ProposalInfoService--saveProposalInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveProposalInfo 异常！");
		}
		return proposalInfoDAO.saveProposalInfo2(entity);
	}

	/**
	 * 查询提案書实体对象entity
	 * 
	 * @author baitianhong
	 */
	public ProposalInfo getProposalInfoById(String id) {
		return proposalInfoDAO.getProposalInfoById(id);
	}
	
	/**
	 * 修改提案書实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int updateProposalInfo(ProposalInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--updateProposalInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--updateProposalInfo 异常！");
		}
		return proposalInfoDAO.updateProposalInfo(entity);
	}
	/**
	 * 删除提案書实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int deleteProposalInfo(ProposalInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--deleteProposalInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--deleteProposalInfo 异常！");
		}
		return proposalInfoDAO.deleteProposalInfo(entity);
	}
	/**
	 * 非分页查询提案書entity List
	 * 
	 * @author baitianhong
	 */
	public List<ProposalInfo> queryProposalInfoList(ProposalInfo entity) {
		return proposalInfoDAO.queryProposalInfoList(entity);
	}
	/**
	 * 非分页查询提案書entity List
	 * 
	 * @author baitianhong
	 */
	public List<ProposalInfoDTO> queryProposalInfoList2(ProposalInfo entity) {
		return proposalInfoDAO.queryProposalInfoList2(entity);
	}
	/**
	 * 非分页查询提案書审批流entity List
	 * 
	 * @author baitianhong
	 */
	public List<ProposalApproveInfo> queryProposalApproveInfoList(ProposalApproveInfo entity) {
		return proposalApproveInfoDAO.queryProposalApproveInfoList2(entity);
	}
	/**
	 * 保存审批流实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveApproveInfo(ProposalApproveInfo entity) {
		entity.setApprove_id(proposalApproveInfoDAO.getUUId());
		try {
			logger.info("事务操作方法：ProposalInfoService--saveApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveApproveInfo 异常！");
		}
		return proposalApproveInfoDAO.saveProposalApproveInfo(entity);
	}
	/**
	 * 修改审批流实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int updateProposalApproveInfo(ProposalApproveInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--updateProposalApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--updateProposalApproveInfo 异常！");
		}
		return proposalApproveInfoDAO.updateProposalApproveInfo(entity);
	}
	/**
	 * 删除审批流实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int deleteProposalApproveInfo(ProposalApproveInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--deleteProposalApproveInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--deleteProposalApproveInfo 异常！");
		}
		return proposalApproveInfoDAO.deleteProposalApproveInfo(entity);
	}
	public Pagination<ProposalInfoDTO> queryProposalInfoListBase(
			ProposalInfoDTO entity, int pageIndex, int pageSize){
	 if(!"".equals(entity.getStart_dt()) && entity.getStart_dt() != null){
		 entity.setStart_dt(entity.getStart_dt()+" 00:00:00 ");
     }
	 if(!"".equals(entity.getEnd_dt()) && entity.getEnd_dt() != null){
		 entity.setEnd_dt(entity.getEnd_dt()+" 23:59:59 ");
     }
	 return proposalInfoDAO.queryProposalInfoListBase(entity,pageIndex,pageSize);
	}
	
	/**
	 * 查询分配实体对象entity
	 * 
	 * @author baitianhong
	 */
	public List<ProposalDistributionInfo> queryProposalDistributionInfoList(ProposalDistributionInfo entity) {
		return proposalDistributionInfoDAO.queryProposalDistributionInfoList2(entity);
	}
	/**
	 * 人员分配实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveProposalDistributionInfo(ProposalDistributionInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--saveProposalDistributionInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveProposalDistributionInfo 异常！");
		}
		return proposalDistributionInfoDAO.saveProposalDistributionInfo(entity);
	}
	
	/**
	 * 查询实施实体对象entity
	 * 
	 * @author baitianhong
	 */
	public List<ProposalOperateInfo> queryProposalOperateInfoList(ProposalOperateInfo entity) {
		return proposalOperateInfoDAO.queryProposalOperateInfoList2(entity);
	}
	
	/**
	 * 保存实施结果实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int saveProposalOperateInfo(ProposalOperateInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--saveProposalOperateInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--saveProposalOperateInfo 异常！");
		}
		return proposalOperateInfoDAO.saveProposalOperateInfo(entity);
	}
	
	/**
	 * 修改实施结果实体对象entity
	 * 
	 * @author baitianhong
	 */
	public int updateProposalOperateInfo(ProposalOperateInfo entity) {
		try {
			logger.info("事务操作方法：ProposalInfoService--updateProposalOperateInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProposalInfoService--updateProposalOperateInfo 异常！");
		}
		return proposalOperateInfoDAO.updateProposalOperateInfo(entity);
	}
	
	public List<ProposalInfoDTO> excelDownload(String[] excelList){
		List<ProposalInfoDTO> proposalInfoList = new ArrayList<ProposalInfoDTO>();
		ProposalInfoDTO proposalInfo = null;
		for(String s:excelList){
			proposalInfo = new ProposalInfoDTO();
			proposalInfo = proposalInfoDAO.getProposalInfoByIdForExcel(s);
			proposalInfoList.add(proposalInfo);
		}
		 return proposalInfoList;
	}
}
