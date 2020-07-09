package com.pm.persistence.proposal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.proposal.dto.ProposalInfoDTO;
import com.pm.persistence.proposal.entity.ProposalInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表proposal_info 的DAO类
 * @author 刘镝
 */
@Component("ProposalInfoDAO")
public class ProposalInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM proposal_info T WHERE 1=1 ";
  public final static String QueryTableSqlBase =  " SELECT * FROM ("
											+ " SELECT T.* , "
									  		+ " (SELECT A.dept_code from sys_user_info A where A.user_num = T.create_id) dept_code , "
									  		+ " (SELECT A.dept_code from sys_user_info A where A.user_num = T.operate_user) operate_user_dept_code , "
									  		+ " (SELECT A.sub_dept_code from sys_user_info A where A.user_num = T.create_id) sub_dept_code , "
									  		+ " (SELECT A.sub_dept_code from sys_user_info A where A.user_num = T.operate_user) operate_user_sub_dept_code , "
									  		+ " (SELECT A.require_date from proposal_distribution_info A where A.proposal_num = T.proposal_num) require_date , "
									  		+ " (SELECT A.code_nm from sys_code_info A where A.code_value = T.proposal_type and A.code_group_value='"+PmConstant.PROPOSAL_ITEM+"') proposal_type_nm , "
									  		+ " (SELECT A.user_nm from sys_user_info A where A.user_num = T.operate_user) operate_user_nm "
									  		+ " FROM proposal_info T ) T"
									  		+ " WHERE 1=1 ";
  public final static String QueryTableSqlExecl = " SELECT T.proposal_num,T.anticipate_result,T.factory_manager_approval_date,T.proposal_detail, (SELECT A.user_nm from sys_user_info A where A.user_num = T.create_id) create_id_nm , "
									  		+ " (SELECT A.user_nm from sys_user_info A where A.user_num = T.deal_user_id) deal_user_id_nm "
									  		+ " ,(SELECT sci.code_nm from sys_user_info A, sys_code_info sci where A.user_num = T.create_id and sci.code_group_value ='depart_group' and sci.code_value = A.dept_code) dept_nm , "
									  		+ " (SELECT A.require_date from proposal_distribution_info A where A.proposal_num = T.proposal_num) require_date , "
									  		+ " (SELECT A.code_nm from sys_code_info A where A.code_value = T.proposal_type and A.code_group_value='proposal_item' ) proposal_type_nm , "
									  		+ " (SELECT A.user_nm from sys_user_info A where A.user_num = T.operate_user) operate_user_nm ,"
									  		+ " (SELECT sci.code_nm from sys_code_info sci where T.approval_state = sci.code_value and sci.code_group_value ='proposal_approve_state' ) as approval_state_nm"
									  		+ " FROM proposal_info T  WHERE 1=1 ";
  private JDBCHelper makeSearch(ProposalInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_num", JDBCHelper.EQ, entiy.getProposal_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_detail", JDBCHelper.EQ, entiy.getProposal_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. deal_user_id", JDBCHelper.EQ, entiy.getDeal_user_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_date", JDBCHelper.EQ, entiy.getApproval_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_step", JDBCHelper.EQ, entiy.getApproval_step());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_type", JDBCHelper.EQ, entiy.getProposal_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. distribute_date", JDBCHelper.EQ, entiy.getDistribute_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. deal_date", JDBCHelper.EQ, entiy.getDeal_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. confirm_date", JDBCHelper.EQ, entiy.getConfirm_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. factory_manager_approval_date", JDBCHelper.EQ, entiy.getFactory_manager_approval_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. anticipate_result", JDBCHelper.EQ, entiy.getAnticipate_result());
              jdbcHelper.putParam(JDBCHelper.AND,"T. resource_input", JDBCHelper.EQ, entiy.getResource_input());
              jdbcHelper.putParam(JDBCHelper.AND,"T. comparative_conclusion", JDBCHelper.EQ, entiy.getComparative_conclusion());
              jdbcHelper.putOrder("T. proposal_num desc");
      }
      return jdbcHelper;
  }
  private JDBCHelper makeSearch2(ProposalInfoDTO entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_detail", JDBCHelper.LIKE, entiy.getProposal_detail());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. deal_user_id", JDBCHelper.EQ, entiy.getDeal_user_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.GTQ, entiy.getStart_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.LTQ, entiy.getEnd_dt());
	              jdbcHelper.putParam(JDBCHelper.AND,"proposal_type_nm", JDBCHelper.LIKE, entiy.getProposal_type_nm());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
				  if(PmConstant.CREATE_USER.equals(entiy.getUserFlag())){
					  jdbcHelper.putParam(JDBCHelper.AND,"T. dept_code", JDBCHelper.EQ, entiy.getDept_code());
					  jdbcHelper.putParam(JDBCHelper.AND,"T. sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
				  }else if(PmConstant.REPAIR_USER.equals(entiy.getUserFlag())){
					  jdbcHelper.putParam(JDBCHelper.AND,"T. operate_user_dept_code", JDBCHelper.EQ, entiy.getDept_code());
					  jdbcHelper.putParam(JDBCHelper.AND,"T. operate_user_sub_dept_code", JDBCHelper.EQ, entiy.getSub_dept_code());
				  }
	              if(PmConstant.DEAL_ORDER.equals(entiy.getOrderItem())){
	            	  jdbcHelper.putOrder("T. require_date desc");
	              }else{
	            	  jdbcHelper.putOrder("T. approval_state asc,T. proposal_num desc");
	              }
	      }
	      return jdbcHelper;
	  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<ProposalInfo> queryProposalInfoList(ProposalInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ProposalInfo.class, pageIndex, pageSize);
  }
  public Pagination<ProposalInfoDTO> queryProposalInfoListBase(ProposalInfoDTO entity, int pageIndex, int pageSize) {
	    JDBCHelper helper = makeSearch2(entity, QueryTableSqlBase);
	    return getPage(helper.getSql(), helper.getParam(), ProposalInfoDTO.class, pageIndex, pageSize);
	  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<ProposalInfo> queryProposalInfoList(ProposalInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), ProposalInfo.class);
    }
    /**
     * 分页查询entity List
     * @author 刘镝
     */
      public List<ProposalInfoDTO> queryProposalInfoList2(ProposalInfo entity) {
        JDBCHelper helper = makeSearch(entity, QueryTableSqlBase);
        return queryForList(helper.getSql(), helper.getParam(), ProposalInfoDTO.class);
      }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveProposalInfo(ProposalInfo entity) {
    if(entity.getId()==null)
    entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateProposalInfo(ProposalInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteProposalInfo(ProposalInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ProposalInfo getProposalInfoById(String id) {
    return this.getEntityById(id, ProposalInfo.class);
  }
  
  /**
   * 保存实体对象entity
   * @author 刘镝
   */
    public String saveProposalInfo2(ProposalInfo entity) {
    	String sqlSP = "CALL p_proposal_info (?,?,?,?,?,?,?,?,?,?,?,?,@?)";
		Object[] inPareData = new Object[] { 
				 entity.getId()
				,entity.getProposal_detail()
				,entity.getDeal_user_id()
				,entity.getApproval_date()
				,entity.getApproval_state()
				,entity.getApproval_step()
				,entity.getCreate_id()
				,entity.getCreate_time()
				,entity.getProposal_type()
				,entity.getAnticipate_result()
				,entity.getResource_input()
				,entity.getComparative_conclusion()
				,""
				};
		return this.getJdbcTemplate().queryForObject(sqlSP, inPareData, String.class);
    }
    
    /**
     * 根据实体对象Id返回entity
     * @author 刘镝
     */
      public ProposalInfoDTO getProposalInfoByIdForExcel(String id) {
      	String sql = QueryTableSqlExecl +" and T.id = '"+id+"'";
        return this.queryObject(sql, ProposalInfoDTO.class);
      }
}
