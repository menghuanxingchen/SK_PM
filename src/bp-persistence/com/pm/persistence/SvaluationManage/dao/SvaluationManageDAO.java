package com.pm.persistence.SvaluationManage.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.repair.entity.ApproveInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
import com.repast.persistence.entity.SysUserInfo;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表 的DAO类
 * @author 刘镝
 */
@Component("SvaluationManageDAO")
public class SvaluationManageDAO extends BaseDao {
  public final static String QueryTableSql = "SELECT T.* from evaluation_manage_info T "
  		+ "left join sys_user_info S on T.user_num = S.user_num  WHERE 1=1 ";
	  
  /**
   * 妫�绱㈢粦瀹�
   * @author 鍒橀暆
   */
    private JDBCHelper makeSearch(EvaluationManageInfo entiy, String sql) {
       JDBCHelper jdbcHelper = new JDBCHelper(sql);
       if (entiy != null) {
                jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
                jdbcHelper.putParam(JDBCHelper.AND,"T. user_num", JDBCHelper.EQ, entiy.getUser_num());
                jdbcHelper.putParam(JDBCHelper.AND,"T. user_nm", JDBCHelper.EQ, entiy.getUser_nm());
                jdbcHelper.putParam(JDBCHelper.AND,"T. year_date", JDBCHelper.EQ, entiy.getYear_date());
                jdbcHelper.putParam(JDBCHelper.AND,"T. date_annual", JDBCHelper.EQ, entiy.getDate_annual());
                jdbcHelper.putParam(JDBCHelper.AND,"T. test_goal_num", JDBCHelper.EQ, entiy.getTest_goal_num());
                jdbcHelper.putParam(JDBCHelper.AND,"T. modify_num", JDBCHelper.EQ, entiy.getModify_num());
                jdbcHelper.putParam(JDBCHelper.AND,"T. error_rate", JDBCHelper.EQ, entiy.getError_rate());
                jdbcHelper.putParam(JDBCHelper.AND,"T. average_score", JDBCHelper.EQ, entiy.getAverage_score());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_one", JDBCHelper.EQ, entiy.getEvaluation_one());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_one_name", JDBCHelper.EQ, entiy.getEvaluation_one_name());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_one_num", JDBCHelper.EQ, entiy.getEvaluation_one_num());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_one_remark", JDBCHelper.EQ, entiy.getEvaluation_one_remark());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_two", JDBCHelper.EQ, entiy.getEvaluation_two());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_two_name", JDBCHelper.EQ, entiy.getEvaluation_two_name());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_two_num", JDBCHelper.EQ, entiy.getEvaluation_two_num());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_two_remark", JDBCHelper.EQ, entiy.getEvaluation_two_remark());
                jdbcHelper.putParam(JDBCHelper.AND,"T. evaluation_state", JDBCHelper.EQ, entiy.getEvaluation_state());
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
	  public Pagination<EvaluationManageInfo> queryEvaluationManageInfoList(EvaluationManageInfo entity, int pageIndex, int pageSize) {
	
			String sql = "select s.user_num,s.user_nm,'"+entity.getYear_date()+"' as year_date,'"+entity.getDate_annual()+ "' as date_annual,"
							+" case when '"+entity.getEvaluation_one()+"'='null' then '' else '"+entity.getEvaluation_one()+"' END  as evaluation_one,"
									+ "a.user_nm as evaluation_one_name,"
							+ " case when '"+entity.getEvaluation_two()+"'='null' then '' else '"+entity.getEvaluation_two()+"' END as evaluation_two ,"
							+ " b.user_nm as evaluation_two_name ,ifnull(r.test_num,0) test_goal_num,IFNULL(r.update_num,0) modify_num,"
							+ " case when ifnull(r.test_num,0)=0 then 0 else ROUND(IFNULL(r.update_num,0)/ifnull(r.test_num,0),2) *100 end error_rate, "
							+ " case when ifnull(r.test_num,0)=0 then 0 else ROUND((ifnull(r.test_num,0)*10 - IFNULL(r.update_num,0))/ifnull(r.test_num,0),2) END average_score"
							+ " from sys_user_info s left join sys_user_info a on '"+entity.getEvaluation_one()+"' = a.user_num "
							+ " left join sys_user_info b on '"+entity.getEvaluation_two()+"' =b.user_num "+
							
							" left JOIN (select sum(test_num) as test_num,sum(update_num) as update_num,left(create_time,4) as create_time,create_id from test_result) r "+
							" on r.create_time = '"+entity.getYear_date()+"' and r.create_id=s.user_num"
									
 
 							+ " where 1=1 and s.evaluatYn='Y'";
		    
		    return getPage(sql, null, EvaluationManageInfo.class, pageIndex, pageSize);
	  }

	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<EvaluationManageInfo> queryEvaluationSaveInfoList(EvaluationManageInfo entity, int pageIndex, int pageSize) {
		JDBCHelper helper = makeSearch(entity, QueryTableSql);
	    return getPage(helper.getSql(), helper.getParam(), EvaluationManageInfo.class, pageIndex, pageSize);
	}

	/**
	 * entity List
	 * @author 刘镝
	 */
	public List<EvaluationManageInfo> checkListValueData(EvaluationManageInfo entity) {
		String sql="select * from evaluation_manage_info "
				+ "where year_date='"+entity.getYear_date()+"' and date_annual='"+entity.getDate_annual()+"' "
				+ "and (evaluation_one_num is not null or evaluation_one_remark is not null "
				+ "or evaluation_two_num is not null or evaluation_two_remark is not null) ";
	    return queryForList(sql, EvaluationManageInfo.class);
	}
	
	/**
	 * 生成评价
	 * @author 刘镝
	 */
	public int saveEvaluationManageInfo(EvaluationManageInfo entity) {
	 
		String sql = "insert into evaluation_manage_info(user_num,user_nm,year_date,date_annual,test_goal_num,modify_num,error_rate,average_score,"
				+ "evaluation_one,evaluation_one_name,evaluation_two,evaluation_two_name,create_id,create_time)"
				
				+ "select s.user_num,s.user_nm,'"+entity.getYear_date()+"' as year_date,'"+entity.getDate_annual()+ "' as date_annual,"
						+ "ifnull(r.test_num,0) test_goal_num,IFNULL(r.update_num,0) modify_num,"
						+ " case when ifnull(r.test_num,0)=0 then 0 else ROUND(IFNULL(r.update_num,0)/ifnull(r.test_num,0),2) *100 end error_rate, "
						+ " case when ifnull(r.test_num,0)=0 then 0 else ROUND((ifnull(r.test_num,0)*10 - IFNULL(r.update_num,0))/ifnull(r.test_num,0),2) END average_score,"
			  
					+"'"+entity.getEvaluation_one()+"' as evaluation_one,"
							+ "a.user_nm as evaluation_one_name,"
					+ "'"+entity.getEvaluation_two()+"' as evaluation_two ,"
							+ "b.user_nm as evaluation_two_name, "
					+"'"+entity.getCreate_id()+"' as create_id,'"+entity.getCreate_time()+"' as create_time"
					+ " from sys_user_info s left join sys_user_info a on '"+entity.getEvaluation_one()+"' = a.user_num "
					+ " left join sys_user_info b on '"+entity.getEvaluation_two()+"' =b.user_num "
					
					+ " left JOIN (select sum(test_num) as test_num,sum(update_num) as update_num,left(create_time,4) as create_time,create_id from test_result) r "
					+ " on r.create_time = '"+entity.getYear_date()+"' and r.create_id=s.user_num"	
					+ " where 1=1 and s.evaluatYn='Y'";
  
	  return executeSQL(sql, null);
	}
	
	/**
	 * 结束评价
	 * @author 刘镝
	 */
	public int saveEndEvaluationManageInfo(EvaluationManageInfo entity) {
	 
		String sql = "update evaluation_manage_info set evaluation_state='2' "
				+ "where year_date='"+entity.getYear_date()+"' and date_annual='"+entity.getDate_annual()+"'";
	  
	  return executeSQL(sql, null);
	}
	
	/**
	 * 保存1
	 * @author 刘镝
	 */
	public int saveTemporaryEvaluationOne(EvaluationManageInfo entity) {
	 
		String sql = "update evaluation_manage_info set evaluation_one_num='"+entity.getEvaluation_one_num().trim()+"',evaluation_one_remark='"+entity.getEvaluation_one_remark()+"' "
				+ "where year_date='"+entity.getYear_date()+"' and date_annual='"+entity.getDate_annual()+"' and user_num='"+entity.getUser_num().trim()+"'";
	  
	  return executeSQL(sql, null);
	}
	
	/**
	 * 保存2
	 * @author 刘镝
	 */
	public int saveTemporaryEvaluationTwo(EvaluationManageInfo entity) {
	 
		String sql = "update evaluation_manage_info set evaluation_two_num='"+entity.getEvaluation_two_num().trim()+"',evaluation_two_remark='"+entity.getEvaluation_two_remark()+"' "
				+ "where year_date='"+entity.getYear_date()+"' and date_annual='"+entity.getDate_annual()+"' and user_num='"+entity.getUser_num().trim()+"'";
	  
	  return executeSQL(sql, null);
	}
}
