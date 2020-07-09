package com.pm.persistence.scrap.dao;

import org.springframework.stereotype.Component;

import com.pm.persistence.scrap.entity.ScrapInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表scrap_info 的DAO类
 * @author 刘镝
 */
@Component("ScrapInfoDAO")
public class ScrapInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* ,(SELECT machine_nm from machine_info where T.scrap_machine_id = machine_id) as scrap_machine_nm,  "
  										+ " s2.code_nm as machine_type_nm ,u.user_nm  FROM scrap_info T,sys_code_info s1,sys_code_info s2 ,sys_user_info u WHERE 1=1 and use_yn = 'Y' "
  										 + " and s1.code_group_value ='machTypeCode' and t.machine_species_id = s1.code_value "
  											+ " and s2.code_group_value = s1.sub_code_group_value and t.machine_type_id = s2.code_value and u.user_num=t.create_id  ";
  private JDBCHelper makeSearch(ScrapInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. scrap_id", JDBCHelper.EQ, entiy.getScrap_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. scrap_machine_id", JDBCHelper.EQ, entiy.getScrap_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_type_id", JDBCHelper.EQ, entiy.getMachine_type_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machine_species_id", JDBCHelper.EQ, entiy.getMachine_species_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. scrap_num", JDBCHelper.EQ, entiy.getScrap_num());
              jdbcHelper.putParam(JDBCHelper.AND,"T. scrap_reason", JDBCHelper.EQ, entiy.getScrap_reason());
              jdbcHelper.putParam(JDBCHelper.AND,"T. scrap_date", JDBCHelper.GTQ, entiy.getScrap_date_start());
              jdbcHelper.putParam(JDBCHelper.AND,"T. scrap_date", JDBCHelper.LTQ, entiy.getScrap_date_end());
              jdbcHelper.putParam(JDBCHelper.AND,"T. main_machine_yn", JDBCHelper.EQ, entiy.getMain_machine_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. spare_yn", JDBCHelper.EQ, entiy.getSpare_yn());
              jdbcHelper.putParam(JDBCHelper.AND,"T. scrap_state", JDBCHelper.EQ, entiy.getScrap_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. apply_userid", JDBCHelper.EQ, entiy.getApply_userid());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_id1", JDBCHelper.EQ, entiy.getApproval_id1());
              jdbcHelper.putParam(JDBCHelper.AND,"T. approval_id2", JDBCHelper.EQ, entiy.getApproval_id2());
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
  public Pagination<ScrapInfo> queryScrapInfoList(ScrapInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), ScrapInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveScrapInfo(ScrapInfo entity) {
    if(entity.getScrap_id()==null)
    entity.setScrap_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateScrapInfo(ScrapInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteScrapInfo(ScrapInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public ScrapInfo getScrapInfoById(String id) {
    return this.getEntityById(id, ScrapInfo.class);
  }
  
  /**
   * 根据实体对象Id返回entity
   * @author 刘镝
   */
    public ScrapInfo getScrapInfoByScrapId(String id) {
      String sql = QueryTableSql + " and T.scrap_id ='"+id+"'";
      return this.queryObject(sql, ScrapInfo.class);
    }
}
