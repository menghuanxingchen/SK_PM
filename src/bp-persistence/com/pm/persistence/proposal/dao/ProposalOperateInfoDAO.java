package com.pm.persistence.proposal.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.proposal.entity.ProposalOperateInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表proposal_operate_info 的DAO类
 * @author 刘镝
 */
@Component("ProposalOperateInfoDAO")
public class ProposalOperateInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM proposal_operate_info T WHERE 1=1 ";
  private JDBCHelper makeSearch(ProposalOperateInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. proposal_num", JDBCHelper.EQ, entiy.getProposal_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. operate_detail", JDBCHelper.EQ, entiy.getOperate_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. operate_expense", JDBCHelper.EQ, entiy.getOperate_expense());
              jdbcHelper.putParam(JDBCHelper.AND,"T. improvement_effect", JDBCHelper.EQ, entiy.getImprovement_effect());
              jdbcHelper.putParam(JDBCHelper.AND,"T. director_opinion", JDBCHelper.EQ, entiy.getDirector_opinion());
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
  public Pagination<ProposalOperateInfo> queryProposalOperateInfoList(ProposalOperateInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ProposalOperateInfo.class, pageIndex, pageSize);
  }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public List<ProposalOperateInfo> queryProposalOperateInfoList2(ProposalOperateInfo entity) {
      JDBCHelper helper = makeSearch(entity, QueryTableSql);
      return queryForList(helper.getSql(), helper.getParam(), ProposalOperateInfo.class);
    }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveProposalOperateInfo(ProposalOperateInfo entity) {
    if(entity.getId()==null)
    entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateProposalOperateInfo(ProposalOperateInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteProposalOperateInfo(ProposalOperateInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ProposalOperateInfo getProposalOperateInfoById(String id) {
    return this.getEntityById(id, ProposalOperateInfo.class);
  }
}
