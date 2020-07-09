package com.pm.persistence.proposal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.proposal.entity.ProposalApproveInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表proposal_approve_info 的DAO类
 * @author 刘镝
 */
@Component("ProposalApproveInfoDAO")
public class ProposalApproveInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM proposal_approve_info T WHERE 1=1 ";
  public final static String QueryTableSqlBase = "SELECT T.* , "
												+ " (SELECT A.user_nm from sys_user_info A where A.user_num = T.approve_user_id) approve_user_nm "
										  		+ " FROM proposal_approve_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(ProposalApproveInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_id", JDBCHelper.EQ, entiy.getApprove_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_user_id", JDBCHelper.EQ, entiy.getApprove_user_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_date", JDBCHelper.EQ, entiy.getApprove_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_yn", JDBCHelper.EQ, entiy.getApprove_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approve_step", JDBCHelper.EQ, entiy.getApprove_step());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_num", JDBCHelper.EQ, entiy.getProposal_num());
      }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<ProposalApproveInfo> queryProposalApproveInfoList(ProposalApproveInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ProposalApproveInfo.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<ProposalApproveInfo> queryProposalApproveInfoList2(ProposalApproveInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSqlBase);
      return queryForList(helper.getSql(), helper.getParam(), ProposalApproveInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveProposalApproveInfo(ProposalApproveInfo entity) {
    if(entity.getApprove_id()==null)
    entity.setApprove_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateProposalApproveInfo(ProposalApproveInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteProposalApproveInfo(ProposalApproveInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ProposalApproveInfo getProposalApproveInfoById(String id) {
    return this.getEntityById(id, ProposalApproveInfo.class);
  }
}
