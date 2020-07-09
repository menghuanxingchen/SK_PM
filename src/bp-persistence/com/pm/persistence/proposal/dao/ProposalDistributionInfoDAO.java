package com.pm.persistence.proposal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.proposal.entity.ProposalDistributionInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表proposal_distribution_info 的DAO类
 * @author 刘镝
 */
@Component("ProposalDistributionInfoDAO")
public class ProposalDistributionInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM proposal_distribution_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(ProposalDistributionInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_distribution_id", JDBCHelper.EQ, entiy.getProposal_distribution_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_num", JDBCHelper.EQ, entiy.getProposal_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. require_date", JDBCHelper.EQ, entiy.getRequire_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_id", JDBCHelper.EQ, entiy.getUser_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
      }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<ProposalDistributionInfo> queryProposalDistributionInfoList(ProposalDistributionInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ProposalDistributionInfo.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<ProposalDistributionInfo> queryProposalDistributionInfoList2(ProposalDistributionInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), ProposalDistributionInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveProposalDistributionInfo(ProposalDistributionInfo entity) {
    if(entity.getProposal_distribution_id()==null)
    entity.setProposal_distribution_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateProposalDistributionInfo(ProposalDistributionInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteProposalDistributionInfo(ProposalDistributionInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ProposalDistributionInfo getProposalDistributionInfoById(String id) {
    return this.getEntityById(id, ProposalDistributionInfo.class);
  }
}
