package com.pm.persistence.repair.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pm.persistence.repair.dto.MaintAreaStatisDTO;
import com.pm.persistence.repair.dto.MaintResStatisDTO;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * 维修故障统计
 * Company:
 * 表repair_machine_detail 的DAO类
 * @author sunaibo
 */
@Component("MaintResStatisDAO")
public class MaintResStatisDAO extends BaseDao {
/**
 * 分页查询entity List
 * @author 刘镝
 */
  public Pagination<MaintResStatisDTO> queryStatisOrderTypeList(MaintResStatisDTO entity, int pageIndex, int pageSize) {
	  String sql=" select v.maintenance_id,v.maintenance_nm, v.repair_date,count(machine_id) as amt "
	  		+ " from maintresult_statistic_view v"
	  		+ " where 1=1 ";
	  		if(!"".equals(entity.getSt_date())&&entity.getSt_date()!=null){
	  			sql=sql+" and v.repair_date>='"+entity.getSt_date()+"'";
	  		}
	  		if(!"".equals(entity.getEn_date())&&entity.getEn_date()!=null){
	  			sql=sql+" and v.repair_date<='"+entity.getEn_date()+"'";
	  		}
	  		sql=sql+ " group by v.maintenance_id" ;
    return getPage(sql, null, MaintResStatisDTO.class, pageIndex, pageSize);
  }
  
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public Pagination<MaintResStatisDTO> queryPotPartStopReport(MaintResStatisDTO entity, int pageIndex, int pageSize) {
  	  String sql=" select a.pot_id,b.pot_nm,date_format(start_time,'%Y-%m') as start_time,COUNT(emergent_id) emergent_id "
  	  		+ ",round((UNIX_TIMESTAMP(a.end_time)-UNIX_TIMESTAMP(a.start_time))/60/60,2) hourNum  "
  	  		+ " from emergent_repair_info a LEFT JOIN machine_pot_info b on a.pot_id=b.pot_id"
  	  		+ " where 1=1 AND a.pot_id<>''";
  	  		if(!"".equals(entity.getSt_date())&&entity.getSt_date()!=null){
  	  			sql=sql+" and date_format(a.start_time,'%Y-%m')='"+entity.getSt_date()+"'";
  	  		}
  	  		if(!"".equals(entity.getSelPotId()) && entity.getSelPotId()!=null){
  	  			sql=sql+" and a.pot_id ='"+entity.getSelPotId()+"'";
  	  		}
  	  		sql=sql+ " group by date_format(start_time,'%Y-%m'),a.pot_id,b.pot_nm order by pot_id,start_time" ;
      return getPage(sql, null, MaintResStatisDTO.class, pageIndex, pageSize);
    }
  /**
   * 分页查询entity List
   * @author 刘镝
   */
    public Pagination<MaintResStatisDTO> queryStatisOrderMachineList(MaintResStatisDTO entity, int pageIndex, int pageSize) {
  	  String sql=" select v.machine_id,v.machine_nm,count(v.id) as re_amt"
  	  		+ " from maintresult_statistic_view v"
  	  		+ " where 1=1"
  	  		+ " and v.maintenance_id='"+entity.getMaintenance_id()+"'";
  	  		if(!"".equals(entity.getSt_date())&&entity.getSt_date()!=null){
  	  			sql=sql+" and v.repair_date>='"+entity.getSt_date()+"'";
  	  		}
  	  		if(!"".equals(entity.getEn_date())&&entity.getEn_date()!=null){
  	  			sql=sql+" and v.repair_date<='"+entity.getEn_date()+"'";
  	  		}
  	  		sql=sql+ " group by v.machine_id" ;
      return getPage(sql, null, MaintResStatisDTO.class, pageIndex, pageSize);
    }
    
    /**
     * 分页查询entity List
     * @author 刘镝
     */
      public Pagination<MaintResStatisDTO> queryRepOrderList(MaintResStatisDTO entity, int pageIndex, int pageSize) {
    	  String sql=" select v.*,u.dept_code "
    	  		+ " from maintresult_statistic_view v,sys_user_info u "
    	  		+ " where 1=1"
    	  		+ " and v.create_id=u.user_num "
    	  		+ " and v.maintenance_id='"+entity.getMaintenance_id()+"'"
    	  		+ " and v.machine_id='"+entity.getMachine_id()+"'";
    	  		if(!"".equals(entity.getSt_date())&&entity.getSt_date()!=null){
    	  			sql=sql+" and v.repair_date>='"+entity.getSt_date()+"'";
    	  		}
    	  		if(!"".equals(entity.getEn_date())&&entity.getEn_date()!=null){
    	  			sql=sql+" and v.repair_date<='"+entity.getEn_date()+"'";
    	  		}
        return getPage(sql, null, MaintResStatisDTO.class, pageIndex, pageSize);
      }
      /**
       * 分页查询entity List
       * @author 刘镝
       */
        public Pagination<MaintAreaStatisDTO> queryMaintAreaStatisList(MaintAreaStatisDTO entity, int pageIndex, int pageSize) {
      	  	String sql=" select v.code_value "
      	  				+ " ,v.code_nm as 'placename' "
						+ " ,sum(IF(v.sub_dept_code='1',1,0)) AS 'dept01' "//电仪
						+ " ,sum(IF(v.sub_dept_code='2',1,0)) AS 'dept02' "//机械
						+ " ,sum(IF(v.sub_dept_code='3',1,0)) AS 'dept03' "//安全
						+ " ,sum(IF(v.sub_dept_code='4',1,0)) AS 'dept04' "//其他
						+"  ,sum(v.tatal_all) AS 'total_all' "//金额
						+ " from ( "
						+ " select t.*,a.code_value,a.code_nm from( "
						+ " select * from maint_area_statistic_view "
						+ " where 1=1 ";
						if(!"".equals(entity.getSt_date())&&entity.getSt_date()!=null){
		      	  			sql=sql+" and create_time>='"+entity.getSt_date()+"' ";
		      	  		}
		      	  		if(!"".equals(entity.getEn_date())&&entity.getEn_date()!=null){
		      	  			sql=sql+" and create_time<='"+entity.getEn_date()+"' ";
		      	  		}
						
		      	  	sql=sql+ " ) t RIGHT JOIN sys_code_info a ON (t.repair_place=a.code_value) "
						+ " where  a.code_group_value='areacode' "
						+ " ) v "
						+ " group by v.code_value "
						+ " ORDER BY v.code_value*1 asc " ;
      	  	System.out.println(sql);
          return getPage(sql, null, MaintAreaStatisDTO.class, pageIndex, pageSize);
        }
      
}
