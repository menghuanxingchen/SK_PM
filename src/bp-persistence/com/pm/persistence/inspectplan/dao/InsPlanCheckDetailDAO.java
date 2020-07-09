package com.pm.persistence.inspectplan.dao;

import java.sql.Array;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MySQLConnection;
import com.pm.persistence.inspectplan.entity.InsPlanCheckDetail;
import com.pm.persistence.inspectplan.entity.InsPlanMachineInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表ins_plan_check_detail 的DAO类
 * @author 刘镝
 */
@Component("InsPlanCheckDetailDAO")
public class InsPlanCheckDetailDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* FROM ins_plan_check_detail T WHERE 1=1 ";
  private JDBCHelper makeSearch(InsPlanCheckDetail entiy, String sql) {
     JDBCHelper jdbcHelper = new JDBCHelper(sql);
     if (entiy != null) {
              jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
              jdbcHelper.putParam(JDBCHelper.AND,"T. plan_machine_id", JDBCHelper.EQ, entiy.getPlan_machine_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_project_id", JDBCHelper.EQ, entiy.getCheck_project_id());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_detail", JDBCHelper.EQ, entiy.getCheck_detail());
              jdbcHelper.putParam(JDBCHelper.AND,"T. check_detail2", JDBCHelper.EQ, entiy.getCheck_detail2());
              jdbcHelper.putParam(JDBCHelper.AND,"T. operate_date", JDBCHelper.EQ, entiy.getOperate_date());
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
  public Pagination<InsPlanCheckDetail> queryInsPlanCheckDetailList(InsPlanCheckDetail entity, int pageIndex, int pageSize) {
    JDBCHelper helper = makeSearch(entity, QueryTableSql);
    return getPage(helper.getSql(), helper.getParam(), InsPlanCheckDetail.class, pageIndex, pageSize);
  }
  /**
   * 没有分页得到entity List
   * @author sunaibo
   */
 public List<InsPlanCheckDetail> queryInsPlanCheckList(InsPlanCheckDetail entity) {
   JDBCHelper helper = makeSearch(entity, QueryTableSql);
   return this.queryForList(helper.getSql(),helper.getParam(),InsPlanCheckDetail.class);
 }
/**
 * 保存实体对象entity
 * @author 刘镝
 */
  public int saveInsPlanCheckDetail(InsPlanCheckDetail entity) {
    int flag =  this.saveNew(entity);
    return flag;
  }

/**
 * 修改实体对象entity
 * @author 刘镝
 */
  public int updateInsPlanCheckDetail(InsPlanCheckDetail entity) {
    int flag =  this.saveUpdate(entity);
    return flag;
  }

/**
 * 删除实体对象entity
 * @author 刘镝
 */
  public int deleteInsPlanCheckDetail(InsPlanCheckDetail entity) {
    int flag =  this.saveRemove(entity);
    return flag;
  }

/**
 * 根据实体对象Id返回entity
 * @author 刘镝
 */
  public InsPlanCheckDetail getInsPlanCheckDetailById(String id) {
    return this.getEntityById(id, InsPlanCheckDetail.class);
  }
  
  
  /**
   * 巡检计划检查项目详情List
   * @author sunaibo
   * @throws SQLException 
   */
 public List<String[]> getInsPlanCheckDetailList(String pro) throws SQLException {
	 /*Connection conn = null;
	 CallableStatement comm = null;
	 String commStr = "";
	 String dbUrl = "jdbc:mysql://10.120.251.42:3306/pm";
	 //String dbUrl = "jdbc:mysql://localhost:3306/pm";
	 String theUser = "root";
	 String thePw = "root";
	 //String thePw = "user";
	 try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 conn = (Connection) DriverManager.getConnection(dbUrl, theUser,thePw);
	 commStr = "call ins_plan_check_detial_pro_all('"+pro+"')";
	 comm = (CallableStatement) ((java.sql.Connection) conn).prepareCall(commStr);
	 ResultSet rs = comm.executeQuery();
	 List<String[]> resList = new ArrayList<String[]>();
	 int columnCount =rs.getMetaData().getColumnCount();//得到结果集的列数	
	 while (rs != null && rs.next()) {
		 String[] resArr= new String[columnCount];
		 for(int j=0;j<columnCount;j++){
			 resArr[j]=rs.getString(j+1);
		 }
		 resList.add(resArr);
	 }
	 rs.close();
	 comm.close();
	 conn.close();*/	 
	 
	 String sql ="call ins_plan_check_detial_pro_all('"+pro+"');";
	  List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
	  List<String[]> datalist = new ArrayList<String[]>();
	  for(int i=0;i<list.size();i++){
		  String[] st = new String[list.get(i).size()];
		  for(int m=0;m<list.get(i).size();m++){
			  st[m] = (String) list.get(i).values().toArray()[m];
		  }
		  datalist.add(st);
	  }
	  return datalist;
	 
	}
 /**
  * 没有分页得到entity List
  * @author sunaibo
  */
public List<InsPlanCheckDetail> queryInsPlanCheckListMob(InsPlanCheckDetail entity) {
	String sql = "SELECT t.*,d.check_project_nm FROM ins_plan_check_detail t,checkitem_info d WHERE t.check_project_id = d.check_project_id  ";
    JDBCHelper helper = makeSearch(entity, sql);
  return this.queryForList(helper.getSql(),helper.getParam(),InsPlanCheckDetail.class);
}
}
