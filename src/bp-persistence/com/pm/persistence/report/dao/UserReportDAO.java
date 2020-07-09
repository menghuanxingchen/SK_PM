package com.pm.persistence.report.dao;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.report.dto.UserReportDto;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;

@Component("UserReportDAO")
public class UserReportDAO extends BaseDao {
	  private JDBCHelper makeSearch(UserReportDto entiy, String sql) {
	     JDBCHelper jdbcHelper = new JDBCHelper(sql);
	     if (entiy != null) {
	              jdbcHelper.putParam(JDBCHelper.AND,"T. type", JDBCHelper.EQ, entiy.getType());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.LIKE, entiy.getUpdate_id());
	              jdbcHelper.putParam(JDBCHelper.AND,"T. user_nm", JDBCHelper.LIKE, entiy.getUser_nm());
	      }
	    jdbcHelper.putOrder(" T.type ,T. all_cnt desc ");
	    
	      return jdbcHelper;
	  }
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	  public Pagination<UserReportDto> queryUserReportList(UserReportDto entity, int pageIndex, int pageSize) {
		  String sql = "select T.* FROM(select '巡检' as typeNm,'ins' as type,COUNT(*) as all_cnt,a.update_id ,(SELECT user_nm from sys_user_info where user_num = a.update_id) as user_nm, "
		  		+ " (select count(*) from ins_plan_info b where b.ins_plan_id = s.ins_plan_id and substring(b.operate_time,1,10)<=  s.end_date) as r_cnt, "
		  		+ " (select count(*) from ins_plan_info c where c.ins_plan_id = s.ins_plan_id and c.operate_time >  s.end_date) as l_cnt "
		  		+ " from ins_plan_info  a,report_ins_next_date_view s where  a.ins_plan_id = s.ins_plan_id and a.operate_time <> '' and a.update_id <> '' ";
		  		if(!StringUtils.isNullOrEmpty(entity.getStart_date())){
		  			sql +=  " and a.ins_plan_date > '"+entity.getStart_date() +"' ";
		  		}
		  		if(!StringUtils.isNullOrEmpty(entity.getEnd_date())){
		  			sql +=  " and a.ins_plan_date < '"+entity.getEnd_date()+"' ";
		  		}
		  		sql +=  " group BY a.update_id"
	  			  		+ " UNION"
	  			  		+ " select '保养' as type,'pre' as type, COUNT(*) as all_cnt,a.update_id,(SELECT user_nm from sys_user_info where user_num = a.update_id) as user_nm,"
	  			  		+ " (select count(*) from pre_plan_info b where b.pre_plan_id = s.pre_plan_id and substring(b.operate_date,1,10)  <=  s.end_date) as r_cnt,"
	  			  		+ " (select count(*) from pre_plan_info c where c.pre_plan_id = s.pre_plan_id and c.operate_date >  s.end_date) as l_c"
	  			  		+ " from pre_plan_info  a,report_pre_next_date_view s where  a.pre_plan_id = s.pre_plan_id and a.operate_date <> '' and a.update_id <> '' ";
		  		if(!StringUtils.isNullOrEmpty(entity.getStart_date())){
		  			sql +=  " and a.pre_plan_date > '"+entity.getStart_date()+"' ";
		  		}
		  		if(!StringUtils.isNullOrEmpty(entity.getEnd_date())){
		  			sql +=  " and a.pre_plan_date < '"+entity.getEnd_date()+"' ";
		  			  		
		  		}
		  		sql += " group BY a.update_id) T WHERE 1= 1 ";
	    JDBCHelper helper = makeSearch(entity, sql);
	    System.out.println(helper.getSql()+"____"+helper.getParam());
	    return getPage(helper.getSql(), helper.getParam(), UserReportDto.class, pageIndex, pageSize);
	  }
}
