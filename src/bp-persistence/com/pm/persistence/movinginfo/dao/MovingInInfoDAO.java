package com.pm.persistence.movinginfo.dao;

import org.springframework.stereotype.Component;


import java.util.List;
import org.springframework.stereotype.Component;
import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.movinginfo.dto.MovingInfo;
import com.pm.persistence.movinginfo.entity.MovingGoodsInfo;
import com.pm.persistence.movinginfo.entity.MovingInInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表moving_in_info 的DAO类
 * @author 刘镝
 */
@Component("MovingInInfoDAO")
public class MovingInInfoDAO extends BaseDao {
  public final static String QueryTableSql = "select t.* from moving_in_info t where 1=1 ";

/**
 * 检索绑定
 * @author 刘镝
 */
  private JDBCHelper makeSearch(MovingInInfo entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minNo", JDBCHelper.EQ, entiy.getMinNo());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minName", JDBCHelper.EQ, entiy.getMinName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minCompany", JDBCHelper.EQ, entiy.getMinCompany());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minCarno", JDBCHelper.EQ, entiy.getMinCarno());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minPhone", JDBCHelper.EQ, entiy.getMinPhone());
              jdbcHelper.putParam(JDBCHelper.AND,"T. skContactsNo", JDBCHelper.EQ, entiy.getSkContactsNo());
              jdbcHelper.putParam(JDBCHelper.AND,"T. skContactsName", JDBCHelper.EQ, entiy.getSkContactsName());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minTime", JDBCHelper.EQ, entiy.getMinTime());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minGoalType", JDBCHelper.EQ, entiy.getMinGoalType());
              jdbcHelper.putParam(JDBCHelper.AND,"T. minGoalContent", JDBCHelper.EQ, entiy.getMinGoalContent());
              jdbcHelper.putParam(JDBCHelper.AND,"T. moutYN", JDBCHelper.EQ, entiy.getMoutYN());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
              jdbcHelper.putParam(JDBCHelper.AND,"T. user_yn", JDBCHelper.EQ, entiy.getUser_yn());
      }
      return jdbcHelper;
  }
  
  /**
   * 检索绑定
   * @author 刘镝
   */
    private JDBCHelper makeSearch2(MovingInfo entiy, String sql) {
       JDBCHelper jdbcHelper = new JDBCHelper(sql);
       if (entiy != null) {
                jdbcHelper.putParam(JDBCHelper.AND,"T. movingType", JDBCHelper.EQ, entiy.getMovingType());
                jdbcHelper.putParam(JDBCHelper.AND,"T. approval_state", JDBCHelper.EQ, entiy.getApproval_state());
                jdbcHelper.putParam(JDBCHelper.AND,"T. deal_user_id", JDBCHelper.EQ, entiy.getDeal_user_id());
                jdbcHelper.putParam(JDBCHelper.AND,"T. minName", JDBCHelper.LIKE, entiy.getMovingPerson());
                jdbcHelper.putParam(JDBCHelper.AND,"T. minNo", JDBCHelper.LIKE, entiy.getMovingNo());
                jdbcHelper.putParam(JDBCHelper.AND,"T. minCarno", JDBCHelper.LIKE, entiy.getMovingCarno());
                jdbcHelper.putParam(JDBCHelper.AND,"T. skApplyName", JDBCHelper.LIKE, entiy.getSkApplyName());
                jdbcHelper.putParam(JDBCHelper.AND,"T. minCompany", JDBCHelper.LIKE, entiy.getMovingCompany());
              //  jdbcHelper.putParam(JDBCHelper.AND,"STR_TO_DATE(T. minTime, '%Y-%m-%d')", JDBCHelper.GTQ, entiy.getMovingStdate());
              //  jdbcHelper.putParam(JDBCHelper.AND,"STR_TO_DATE(T. minTime, '%Y-%m-%d')", JDBCHelper.LTQ, entiy.getMovingEddate());
               
        }
        return jdbcHelper;
    }
    
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<MovingInfo> queryDataList(MovingInfo entity, int pageIndex, int pageSize) {
		String tableSql="select ID, movingType,minNo as movingNo,t.deal_user_id,s.user_nm AS deal_user_name,approval_state,"
				+ "case when approval_state = '6' then '已完成' when approval_state = '5' then '驳回' else '审核中' end approval_state_name,"
				+ "minName as movingPerson,minCompany as movingCompany,minCarno as movingCarno,"
				+ "minPhone as movingPhone,minTime as movingTime,moutYN,minGoalType as movingGoalType,minGoalContent AS movingGoalContent,skContactsNo,skApplyName,p.cntApprove,t.create_id "
				+ "from (SELECT ID,'搬入证' as movingType,minNo,deal_user_id,approval_state,minName,minCompany,minCarno,minPhone,minTime,"
				+ "case when b.cnt > 0 then 'N' else moutYN end moutYN,minGoalType,minGoalContent,skContactsNo ,'' as skApplyName,a.create_id "
				+ "FROM moving_in_info a "
				+ "LEFT JOIN (select moutNo,count(*) cnt from moving_out_info  GROUP BY moutNo)b on REPLACE(a.minNo,'R','') = REPLACE(b.moutNo,'C','') "
				+" where a.minTime<=date_add('"+entity.getMovingEddate()+"',interval 1 day)  and a.minTime>= '"+entity.getMovingStdate()+"'"
				+ "union all select id,'搬出证' as movingType,moutNo,deal_user_id,approval_state,moutName,moutCompany,moutCarno,moutPhone,moutTime, "
				+ "'N' moutYN,moutGoalType,moutGoalContent,'' as skContactsNo,skApplyName,create_id from moving_out_info "
				+ " where moutTime<=date_add('"+entity.getMovingEddate()+"',interval 1 day) and moutTime>= '"+entity.getMovingStdate()+"' ) t "
				+ "LEFT JOIN sys_user_info S ON t.deal_user_id = s.user_num "
				+ " left join(select COUNT(approve_id) cntApprove,proposal_num from  \r\n" + 
				"          proposal_approve_info where approve_yn='Y' group by  proposal_num )P on t.minNo = p.proposal_num  "
				+ "where 1=1 ";
	  JDBCHelper helper = makeSearch2(entity, tableSql);
	  helper.setDefaultOrderBy("t.minTime desc,right(minNo,16),movingType desc");
	  return getPage(helper.getSql(), helper.getParam(), MovingInfo.class, pageIndex, pageSize);
	}



   /**
   * 保存实体对象entity
   * @author 刘镝
   */
  public int saveMovingInApplyInfo(MovingInInfo entity) {
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
     * 删除实体对象entity
     * @author 刘镝
     */
    public int deleteMovingGoodsInfo(MovingGoodsInfo entity) {
      int flag =  this.saveRemove(entity);
      return flag;
    }
    
  /**
   * 修改实体对象entity
   * @author 刘镝
   */
  public int updateMovingInInfo(MovingInInfo entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }


  /**
   * 删除实体对象entity
   * @author 刘镝
   */
  public int deleteMovingInInfo(MovingInInfo entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public MovingInInfo getMovingInInfoById(String id) {
    return this.getEntityById(id, MovingInInfo.class);
  }

/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<MovingInInfo> queryDataList(MovingInInfo entity, int pageIndex, int pageSize) {
    String sql = "select t.* from moving_in_info t where 1=1 ";
    //if(!StringUtils.isNullOrEmpty(entity.getId())){
    //  sql = sql + " and id like '%" + entity.getId() + "%'";
    //}
    //sql = sql + " order by id desc";
    return getPage(sql, null, MovingInInfo.class, pageIndex, pageSize);
  }

/**
 * 根据实体条件检索list
 * @author 刘镝
 */
  public MovingInInfo queryMovingInDataList(MovingInfo entity){
    String sql = "select a.*,b.user_nm as deal_user_name,case when approval_state='6' then '已完成' else '审核中' end as approval_state_name "
      		+ " from moving_in_info a left join sys_user_info b on a.deal_user_id = b.user_num "
      		+ " where 1=1 and minNo='"+entity.getMovingNo()+"'";
    return this.queryObject(sql,MovingInInfo.class);
  }

  /**
   * 根据实体条件检索list
   * @author 刘镝
   */
	public List<MovingGoodsInfo> queryMovingGoodsInfoList(MovingInfo entity){
	  String sql = "select * from moving_goods_info where 1=1 and minNo='"+entity.getMovingNo()+"'";
	  return this.queryForList(sql,MovingGoodsInfo.class);
	}
	    
	/**
	 * 删除
	 * @author 刘镝
	 */
	  public int deleteMovingInfoData(MovingInfo entity){
	    String sql1 = "delete from moving_in_info where 1=1 and minNo='"+entity.getMovingNo()+"'";
	    String sql2 = "delete from moving_out_info where 1=1 and moutNo='"+entity.getMovingNo()+"'";
	    int ret1 = this.executeSQL(sql1);
	    int ret2 = this.executeSQL(sql2);
	    return ret1 + ret2;
	  }
	
	/**
	 * 根据实体条件检索list
	 * @author 刘镝
	 */
	  public List<MovingInInfo> queryMovingInfoList(MovingInInfo entity){
	    String sql = "select * from moving_in_info where 1=1 and minNo='"+entity.getMinNo()+"'";
    return this.queryForList(sql,MovingInInfo.class);
  }
      
/**
 * DB存在check
 * @author 刘镝
 */
  public Boolean checkMovingInInfoEO(MovingInInfo entity) {
    String sql = "";
    MovingInInfo eo = entity.clone();
    if(StringUtils.isNullOrEmpty(entity.getId())){
        sql = "select count(*) from moving_in_info t where 1 = 1 ";
    }else{
        sql = "select count(*) from moving_in_info t where t.id <> '" + entity.getId() +"'";
        eo.setId("");
    }

    JDBCHelper helper = makeSearch(eo, sql);
    if(this.queryForInt(helper.getSql(), helper.getParam()) > 0){
        return true;
    }
    return false;
  }

}
