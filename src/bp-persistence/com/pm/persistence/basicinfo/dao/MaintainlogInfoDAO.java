package com.pm.persistence.basicinfo.dao;

import org.springframework.stereotype.Component;

import com.pm.persistence.basicinfo.entity.MaintainlogInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表maintainlog_info 的DAO类
 * @author 刘镝
 */
@Component("MaintainlogInfoDAO")
public class MaintainlogInfoDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM maintainlog_info T WHERE 1=1 ";
  public final static String QueryTableSqlNew = ""
  		+ "SELECT T.*,u.user_num,u.dept_code as 'dept_type', u.sub_dept_code as 'subdept_type',c.code_nm as 'subdept_type_nm' "
  		+ " FROM maintainlog_info T,sys_user_info u,sys_code_info c WHERE 1=1 "
  		+ " and t.create_id=u.user_num "
  		+ " and u.sub_dept_code=c.code_value "
  		+ " and c.code_group_value='machTypeCode' ";
  private JDBCHelper makeSearch(MaintainlogInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintainlog_id", JDBCHelper.EQ, entiy.getMaintainlog_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. log_contents", JDBCHelper.EQ, entiy.getLog_contents());
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintain_type", JDBCHelper.EQ, entiy.getMaintain_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintain_time", JDBCHelper.EQ, entiy.getMaintain_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. human_amount", JDBCHelper.LIKE, entiy.getHuman_amount());
              jdbcHelper.putParam(JDBCHelper.AND,"T. time_amount", JDBCHelper.EQ, entiy.getTime_amount());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_state", JDBCHelper.EQ, entiy.getCheck_state());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_user", JDBCHelper.EQ, entiy.getCheck_user());
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintain_user", JDBCHelper.EQ, entiy.getMaintain_user());
              jdbcHelper.putParam(JDBCHelper.AND,"T. log_remark", JDBCHelper.EQ, entiy.getLog_remark());
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintain_time", JDBCHelper.GTQ, entiy.getDate_start());
              jdbcHelper.putParam(JDBCHelper.AND,"T. maintain_time", JDBCHelper.LTQ, entiy.getDate_end());
              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_type", JDBCHelper.EQ, entiy.getDept_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. subdept_type", JDBCHelper.EQ, entiy.getSubdept_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. machineid", JDBCHelper.EQ, entiy.getMachineid());
              jdbcHelper.putOrder("T. maintain_time desc");
     }
      return jdbcHelper;
  }
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<MaintainlogInfo> queryMaintainlogInfoList(MaintainlogInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), MaintainlogInfo.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveMaintainlogInfo(MaintainlogInfo entity) {
    if(entity.getMaintainlog_id()==null)
    entity.setMaintainlog_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateMaintainlogInfo(MaintainlogInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteMaintainlogInfo(MaintainlogInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public MaintainlogInfo getMaintainlogInfoById(String id) {
    return this.getEntityById(id, MaintainlogInfo.class);
  }
  
  /**
   * update state 批量
   * @author sunaibo
   */
    public int updateStateBatch(MaintainlogInfo entity) {
    	String sql=" update maintainlog_info "
    			+ " set confirm_yn='"+entity.getConfirm_yn()+"' "
    			+ " ,update_id='"+entity.getUpdate_id()+"' "
    			+ " ,update_time='"+entity.getUpdate_time()+"' "
    			+ " where maintainlog_id in("+entity.getMaintainlog_id()+") ";
      int flag =  this.executeSQL(sql);
      return flag;
    }
}
