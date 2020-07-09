package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.dto.RepairOrderInfoDTO;
import com.pm.persistence.repair.entity.RepairOrderInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_order_info 的DAO类
 * @author 刘镝
 */
@Component("RepairOrderInfoDAO")
public class RepairOrderInfoDAO extends BaseDao {
  public final static String QueryTableSql =  " SELECT * FROM ("
		  									+ " SELECT T.* , "
									  		+ " (SELECT A.dept_code from sys_user_info A where A.user_num = T.create_id) dept_code , "
									  		+ " (SELECT A.dept_code from sys_user_info A where A.user_num = T.repair_user_id) repair_user_dept_code , "
									  		+ " (SELECT B.code_nm from sys_user_info A ,sys_code_info B where A.user_num = T.create_id AND A.dept_code = B.code_value and B.code_group_value='depart_group') dept_nm , "
									  		+ " (SELECT A.sub_dept_code from sys_user_info A where A.user_num = T.create_id) sub_dept_code , "
									  		+ " (SELECT A.sub_dept_code from sys_user_info A where A.user_num = T.repair_user_id) repair_user_sub_dept_code , "
									  		+ " (SELECT A.code_nm from sys_code_info A where A.code_value = T.repair_place and A.code_group_value = 'areacode') repair_place_nm , "
									  		+ " (SELECT A.require_date from distribution_info A where A.repair_order_num = T.repair_order_num) require_date , "
									  		+ " (SELECT A.repair_classify from distribution_info A where A.repair_order_num = T.repair_order_num) repair_classify , "
									  		+ " (SELECT A.tatal_all from offer_info A where A.repair_order_num = T.repair_order_num) tatal_all, "
									  		+ " (SELECT A.tatal_all from repair_result_info A where A.repair_order_num = T.repair_order_num) after_repair_tatal_all, "
									  		+ " (SELECT A.user_nm from sys_user_info A where A.user_num = T.repair_user_id) repair_user_nm "
									  		+ " FROM repair_order_info T ) T"
									  		+ " WHERE 1=1 ";
  private JDBCHelper makeSearch(RepairOrderInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_order_num", JDBCHelper.EQ, entiy.getRepair_order_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_detail", JDBCHelper.EQ, entiy.getRepair_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_user_id", JDBCHelper.EQ, entiy.getRepair_user_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. require_time", JDBCHelper.EQ, entiy.getRequire_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. deal_user_id", JDBCHelper.EQ, entiy.getDeal_user_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_date", JDBCHelper.EQ, entiy.getApproval_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_step", JDBCHelper.EQ, entiy.getApproval_step());
              jdbcHelper.putParam(JDBCHelper.AND,"T. remark", JDBCHelper.EQ, entiy.getRemark());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_place", JDBCHelper.EQ, entiy.getRepair_place());
              jdbcHelper.putParam(JDBCHelper.AND,"T. pay_yn", JDBCHelper.EQ, entiy.getPay_yn());
              jdbcHelper.putOrder("T. repair_order_num desc");
      }
      return jdbcHelper;
  }
  
  private JDBCHelper makeSearch2(RepairOrderInfoDTO entiy, String sql,Processor processor,String unpayedpageflag) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	    	  	  jdbcHelper.putParam(JDBCHelper.AND,"T. repair_detail", JDBCHelper.LIKE, entiy.getRepair_detail());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
	              if(unpayedpageflag.equals(PmConstant.UNPAYEDPAGE_FLAG)){
	            	  jdbcHelper.putParam(JDBCHelper.AND,"T. factory_manager_confirm_date", JDBCHelper.GTQ, entiy.getStart_dt());
		              jdbcHelper.putParam(JDBCHelper.AND,"T. factory_manager_confirm_date", JDBCHelper.LTQ, entiy.getEnd_dt());
	              }else{
	            	  jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.GTQ, entiy.getStart_dt());
		              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.LTQ, entiy.getEnd_dt());
	              }
	              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_code", JDBCHelper.EQ, entiy.getDept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. pay_yn", JDBCHelper.EQ, entiy.getPay_yn());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_step", JDBCHelper.EQ, entiy.getApproval_step());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_type", JDBCHelper.EQ, entiy.getRepair_type());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. deal_user_id", JDBCHelper.EQ, entiy.getDeal_user_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_place", JDBCHelper.EQ, entiy.getRepair_place());
	              jdbcHelper.putParam(JDBCHelper.AND,"repair_classify", JDBCHelper.EQ, entiy.getRepair_classify());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
//	              if(PmConstant.FACTORY_STAFF.equals(processor.getAuth()) && !(PmConstant.FINANCE_DEPART.equals(processor.getSubdept()) && PmConstant.ADMIN_EQUIP_DEPART.equals(processor.getDept()))){
//	            	  entiy.setCreate_id(processor.getCurrentUserId());
//	            	  jdbcHelper.putParam(JDBCHelper.AND,JDBCHelper.EQ, entiy.getCreate_id()," T. create_id" , " T. deal_user_id" , " T. repair_user_id");
//	     	      }
	              if(PmConstant.DEAL_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. require_date desc");
	              }else if(PmConstant.CREATE_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. create_time desc");
	              }else if(PmConstant.PAYED_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. update_time desc");
	              }else if(PmConstant.DONE_TIME_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. factory_manager_confirm_date");
	              }else{
	            	  jdbcHelper.putOrder("T. approval_state asc,T. repair_order_num desc");
	              }
	      }
	      return jdbcHelper;
	  }
  
  private JDBCHelper makeSearchForRepairUser(RepairOrderInfoDTO entiy, String sql,Processor processor) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	    	  	  jdbcHelper.putParam(JDBCHelper.AND,"T. repair_detail", JDBCHelper.LIKE, entiy.getRepair_detail());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.GTQ, entiy.getStart_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.LTQ, entiy.getEnd_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_user_dept_code", JDBCHelper.EQ, entiy.getDept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_user_sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. pay_yn", JDBCHelper.EQ, entiy.getPay_yn());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_step", JDBCHelper.EQ, entiy.getApproval_step());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_type", JDBCHelper.EQ, entiy.getRepair_type());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. deal_user_id", JDBCHelper.EQ, entiy.getDeal_user_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_place", JDBCHelper.EQ, entiy.getRepair_place());
	              jdbcHelper.putParam(JDBCHelper.AND,"repair_classify", JDBCHelper.EQ, entiy.getRepair_classify());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
//	              if(PmConstant.FACTORY_STAFF.equals(processor.getAuth()) && !(PmConstant.FINANCE_DEPART.equals(processor.getSubdept()) && PmConstant.ADMIN_EQUIP_DEPART.equals(processor.getDept()))){
//	            	  entiy.setCreate_id(processor.getCurrentUserId());
//	            	  jdbcHelper.putParam(JDBCHelper.AND,JDBCHelper.EQ, entiy.getCreate_id()," T. create_id" , " T. deal_user_id" , " T. repair_user_id");
//	     	      }
	              if(PmConstant.DEAL_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. require_date desc");
	              }else if(PmConstant.CREATE_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. create_time desc");
	              }else if(PmConstant.PAYED_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. update_time desc");
	              }else{
	            	  jdbcHelper.putOrder("T. approval_state asc,T. repair_order_num desc");
	              }
	      }
	      return jdbcHelper;
	  }
  private JDBCHelper makeSearch3(RepairOrderInfoDTO entiy, String sql,Processor processor) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. account_date", JDBCHelper.GTQ, entiy.getStart_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. account_date", JDBCHelper.LTQ, entiy.getEnd_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_code", JDBCHelper.EQ, entiy.getDept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. pay_yn", JDBCHelper.EQ, entiy.getPay_yn());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_type", JDBCHelper.EQ, entiy.getRepair_type());
	              jdbcHelper.putParam(JDBCHelper.AND,"repair_classify", JDBCHelper.EQ, entiy.getRepair_classify());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
//	              if(PmConstant.FACTORY_STAFF.equals(processor.getAuth()) && !(PmConstant.FINANCE_DEPART.equals(processor.getSubdept()) && PmConstant.ADMIN_EQUIP_DEPART.equals(processor.getDept()))){
//	            	  entiy.setCreate_id(processor.getCurrentUserId());
//	            	  jdbcHelper.putParam(JDBCHelper.AND,JDBCHelper.EQ, entiy.getCreate_id()," T. create_id" , " T. deal_user_id" , " T. repair_user_id");
//	     	      }
	              if(PmConstant.DEAL_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. require_date desc");
	              }else if(PmConstant.CREATE_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. create_time desc");
	              }else if(PmConstant.PAYED_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. update_time desc");
	              }else if(PmConstant.DONE_TIME_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. factory_manager_confirm_date");
	              }else{
	            	  jdbcHelper.putOrder("T. repair_order_num desc");
	              }
	      }
	      return jdbcHelper;
	  }
  
  private JDBCHelper makeSearch4(RepairOrderInfoDTO entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
             jdbcHelper.putParam(JDBCHelper.AND,"T. account_date", JDBCHelper.GTQ, entiy.getStart_dt());
             jdbcHelper.putParam(JDBCHelper.AND,"T. account_date", JDBCHelper.LTQ, entiy.getEnd_dt());
             jdbcHelper.putParam(JDBCHelper.AND,"T. pay_yn", JDBCHelper.EQ, entiy.getPay_yn());
             jdbcHelper.putParam(JDBCHelper.AND,"T. repair_type", JDBCHelper.EQ, entiy.getRepair_type());
//             jdbcHelper.putOrder("T. repair_order_num desc");
             jdbcHelper.putOrder("T. factory_manager_confirm_date");
	      }
	      return jdbcHelper;
	  }
  
  private JDBCHelper makeSearch5(RepairOrderInfoDTO entiy, String sql,Processor processor) {
	  JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	    	  	  jdbcHelper.putParam(JDBCHelper.AND,"T. repair_detail", JDBCHelper.LIKE, entiy.getRepair_detail());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.GTQ, entiy.getStart_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.LTQ, entiy.getEnd_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_code", JDBCHelper.EQ, entiy.getDept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. pay_yn", JDBCHelper.EQ, entiy.getPay_yn());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_step", JDBCHelper.EQ, entiy.getApproval_step());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. repair_type", JDBCHelper.EQ, entiy.getRepair_type());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. deal_user_id", JDBCHelper.EQ, processor.getCurrentUserId());
	              
	              if(PmConstant.DEAL_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. require_date desc");
	              }else if(PmConstant.CREATE_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. create_time desc");
	              }else if(PmConstant.PAYED_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. update_time desc");
	              }else{
	            	  jdbcHelper.putOrder("T. repair_order_num desc");
	              }
	      }
	      return jdbcHelper;
	  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<RepairOrderInfoDTO> queryRepairOrderInfoList(RepairOrderInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), RepairOrderInfoDTO.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public Pagination<RepairOrderInfoDTO> queryRepairOrderInfoList2(RepairOrderInfoDTO entity,String pageflag, String unpayedpageflag,String repair_user_flag,int pageIndex, int pageSize,Processor processor) {
    	 JDBCHelper helper = null;
    	if(pageflag.equals(PmConstant.PAGE_FLAG)){
    		helper = makeSearch3(entity, QueryTableSql,processor);
    	}else if(repair_user_flag.equals(PmConstant.REPAIR_USER)){
    		helper = makeSearchForRepairUser(entity, QueryTableSql,processor);
    	}else{
    		helper = makeSearch2(entity, QueryTableSql,processor,unpayedpageflag);
    	}
      return getPage(helper.getSql(), helper.getParam(), RepairOrderInfoDTO.class, pageIndex, pageSize);
    }
  
  /**
   * 非分页查询entity List
   * @author 刘镝
   */
    public List<RepairOrderInfo> queryRepairOrderInfoList(RepairOrderInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), RepairOrderInfo.class);
    }
    /**
     * 非分页查询entity List
     * @author 刘镝
     */
      public List<RepairOrderInfo> queryRepairOrderInfoList4(RepairOrderInfoDTO entity) {
        JDBCHelper helper = makeSearch4(entity, QueryTableSql);
        return queryForList(helper.getSql(), helper.getParam(), RepairOrderInfo.class);
      }
    /**
     * 非分页查询entity List
     * @author 刘镝
     */
      public List<RepairOrderInfoDTO> queryRepairOrderInfoListMob(RepairOrderInfoDTO entity,Processor processor) {
        JDBCHelper helper = makeSearch5(entity, QueryTableSql,processor);
        return queryForList(helper.getSql(), helper.getParam(), RepairOrderInfoDTO.class);
      }
      
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveRepairOrderInfo(RepairOrderInfo entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }
  /**
   * 保存实体对象entity
   * @author 刘镝
   */
    public String saveRepairOrderInfo2(RepairOrderInfo entity) {
    	String sqlSP = "CALL p_repair_order_info (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,@?)";
		Object[] inPareData = new Object[] { 
				entity.getId(),
				entity.getRepair_detail(),
				entity.getRepair_user_id(),
				entity.getRequire_time(),
				entity.getDeal_user_id(),
				entity.getApproval_date(),
				entity.getApproval_state(),
				entity.getApproval_step(),
				entity.getRemark(),
				entity.getCreate_id(),
				entity.getCreate_time(),
				entity.getUpdate_id(),
				entity.getUpdate_time(),
				entity.getRepair_place(),
				entity.getPay_yn(),
				""
				};
		return this.getJdbcTemplate().queryForObject(sqlSP, inPareData, String.class);
    }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateRepairOrderInfo(RepairOrderInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteRepairOrderInfo(RepairOrderInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public RepairOrderInfo getRepairOrderInfoById(String id) {
    return this.getEntityById(id, RepairOrderInfo.class);
  }
}
