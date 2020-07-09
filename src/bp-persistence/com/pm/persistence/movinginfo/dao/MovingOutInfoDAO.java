package com.pm.persistence.movinginfo.dao;

import org.springframework.stereotype.Component;


import java.util.List;
import org.springframework.stereotype.Component;
import com.mysql.jdbc.StringUtils;
import com.pm.persistence.movinginfo.dto.MovingInfo;
import com.pm.persistence.movinginfo.entity.MovingGoodsInfo;
import com.pm.persistence.movinginfo.entity.MovingInInfo;
import com.pm.persistence.movinginfo.entity.MovingOutInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表moving_out_info 的DAO类
 * @author 刘镝
 */
@Component("MovingOutInfoDAO")
public class MovingOutInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from moving_out_info t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(MovingOutInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutNo", JDBCHelper.EQ, entiy.getMoutNo());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutName", JDBCHelper.EQ, entiy.getMoutName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutCompany", JDBCHelper.EQ, entiy.getMoutCompany());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutCarno", JDBCHelper.EQ, entiy.getMoutCarno());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutPhone", JDBCHelper.EQ, entiy.getMoutPhone());
              jdbcHelper.putParam(JDBCHelper.AND,"T. skApplyNo", JDBCHelper.EQ, entiy.getSkApplyNo());
              jdbcHelper.putParam(JDBCHelper.AND,"T. skApplyName", JDBCHelper.EQ, entiy.getSkApplyName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. skApplyDept", JDBCHelper.EQ, entiy.getSkApplyDept());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutTime", JDBCHelper.EQ, entiy.getMoutTime());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutGoalType", JDBCHelper.EQ, entiy.getMoutGoalType());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutGoalContent", JDBCHelper.EQ, entiy.getMoutGoalContent());
              jdbcHelper.putParam(JDBCHelper.AND,"T. ylbanchuYN", JDBCHelper.EQ, entiy.getYlbanchuYN());
              jdbcHelper.putParam(JDBCHelper.AND,"T. emptyOut", JDBCHelper.EQ, entiy.getEmptyOut());
              jdbcHelper.putParam(JDBCHelper.AND,"T. deliveryOrderYN", JDBCHelper.EQ, entiy.getDeliveryOrderYN());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_yn", JDBCHelper.EQ, entiy.getUser_yn());
      }
      return jdbcHelper;
  }


/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<MovingOutInfo> queryMovingOutInfoList(MovingOutInfo entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), MovingOutInfo.class, pageIndex, pageSize);
  }

/**
 * 查询entity List
 * @author 刘镝
 */
  public List<MovingOutInfo> queryMovingOutInfoList(MovingOutInfo entity) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), MovingOutInfo.class, 1, -1).getRecords();
  }

  
  /**
   * 保存实体对象entity
   * @author 刘镝
   */
    public int saveMovingOutApplyInfo(MovingOutInfo entity) {
  	  entity.setId(this.getUUId());
        int flag =  this.saveNew(entity);
        return flag;
    }
    
    /**
     * 保存实体对象entity
     * @author 刘镝
     */
      public int saveMovingGoodsInfoData(MovingGoodsInfo entity) {
    	  entity.setId(this.getUUId());
          int flag =  this.saveNew(entity);
          return flag;
      }
  /**
   * 根据实体条件检索list
   * @author 刘镝
   */
    public MovingOutInfo queryMovingOutDataList(MovingInfo entity){
      String sql = " select a.*,b.user_nm as deal_user_name,case when approval_state='6' then '已完成' else '审核中' end as approval_state_name"
      		+ " from moving_out_info a left join sys_user_info b on a.deal_user_id = b.user_num "
      		+ " where 1=1 and moutNo='"+entity.getMovingNo()+"'";
      return this.queryObject(sql,MovingOutInfo.class);
    }
    
    /**
     * 根据实体条件检索list
     * @author 刘镝
     */
      public List<MovingOutInfo> queryMovingInfoList(MovingOutInfo entity){
        String sql = "select * from moving_out_info where 1=1 and moutNo='"+entity.getMoutNo()+"'";
        return this.queryForList(sql,MovingOutInfo.class);
      }
      
	/**
	 * 查询返回entity(首行)
	 * @author 刘镝
	 */
  public MovingOutInfo queryMovingOutInfoEO(MovingOutInfo entity) {
    List<MovingOutInfo> list = queryMovingOutInfoList(entity);
    if(list.size() > 0){
      return list.get(0);
    }else{
      return new MovingOutInfo();
    }
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveMovingOutInfo(MovingOutInfo entity) {
    if(entity.getId()==null)
    entity.setId(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateMovingOutInfo(MovingOutInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }


/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteMovingOutInfo(MovingOutInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public MovingOutInfo getMovingOutInfoById(String id) {
    return this.getEntityById(id, MovingOutInfo.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<MovingOutInfo> queryDataList(MovingOutInfo entity, int pageIndex, int pageSize) {
    String sql = "select t.* from moving_out_info t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getId())){
    //  sql = sql + " and id like '%" + entity.getId() + "%'";
    //}
    //sql = sql + " order by id desc";
    return getPage(sql, null, MovingOutInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public List<MovingOutInfo> queryDataList(MovingOutInfo entity){
    String sql = "select * from moving_out_info where 1=1 ";
    return this.queryForList(sql,MovingOutInfo.class);
  }

/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkMovingOutInfoEO(MovingOutInfo entity) {
    String sql = "";
    MovingOutInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getId())){
        sql = "select count(*) from moving_out_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from moving_out_info t where t.id <> '" + entity.getId() +"'";
        eo.setId("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
