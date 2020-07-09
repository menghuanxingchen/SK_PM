package com.pm.persistence.workout.dao;

import java.util.List;

import org.springframework.stereotype.Component;





import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.pm.persistence.workout.entity.RepairMaterialCostDetail;
import com.pm.persistence.workout.entity.WorkOutDetail;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表rapair_material_cost_detail 的DAO类
 * @author 刘镝
 */
@Component("RapairMaterialCostDetailDAO")
public class RepairMaterialCostDetailDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM rapair_material_cost_detail T WHERE 1=1 ";
  private static  String  DELETE_SQL = "delete from rapair_material_cost_detail where 1=1 ";
  private JDBCHelper makeSearch(RepairMaterialCostDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. rapair_material_cost_id", JDBCHelper.EQ, entiy.getRapair_material_cost_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. work_out_id", JDBCHelper.EQ, entiy.getWork_out_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. confirm_date", JDBCHelper.EQ, entiy.getConfirm_date());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cost_type", JDBCHelper.EQ, entiy.getCost_type());
              jdbcHelper.putParam(JDBCHelper.AND,"T. cost_nm", JDBCHelper.EQ, entiy.getCost_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. amount", JDBCHelper.EQ, entiy.getAmount());
              jdbcHelper.putParam(JDBCHelper.AND,"T. material_cost_detail", JDBCHelper.EQ, entiy.getMaterial_cost_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_code", JDBCHelper.EQ, entiy.getDept_code());
              jdbcHelper.putParam(JDBCHelper.AND,"T. dept_nm", JDBCHelper.EQ, entiy.getDept_nm());
              jdbcHelper.putParam(JDBCHelper.AND,"T. remark", JDBCHelper.EQ, entiy.getRemark());
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
  public Pagination<RepairMaterialCostDetail> queryRapairMaterialCostDetailList(RepairMaterialCostDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), RepairMaterialCostDetail.class, pageIndex, pageSize);
  }

/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveRapairMaterialCostDetail(RepairMaterialCostDetail entity) {
	  //System.out.println("insert start");
    if(entity.getRapair_material_cost_id()==null)
    entity.setRapair_material_cost_id(this.getUUId());
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateRapairMaterialCostDetail(RepairMaterialCostDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteRapairMaterialCostDetail(RepairMaterialCostDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

  /**
   * 删除实体对象entity
   * @author 刘镝
   */
    public int deleteRapairMaterialCostDetailById(String work_out_id ) {

  	String sql = DELETE_SQL+"AND work_out_id IN('"+work_out_id+"') ";
	return super.executeSQL(sql, new Object[]{});
    
    }
/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public RepairMaterialCostDetail getRapairMaterialCostDetailById(String id) {
    return this.getEntityById(id, RepairMaterialCostDetail.class);
  }
  
  /**
   * 根据实体对象Id返回entity 
   * @author SKCC
   */
    public  List<RepairMaterialCostDetail> getRepairCostDetailInfoById(String id ,String type) {
 
  	  String sql = "SELECT T.* FROM rapair_material_cost_detail T WHERE 1=1 and work_out_id ='"+id+"'" ;
  	  //System.out.println("getRepairCostDetailInfoById" +sql.toString());
        return this.queryForList(sql, RepairMaterialCostDetail.class);
        
    }
}
