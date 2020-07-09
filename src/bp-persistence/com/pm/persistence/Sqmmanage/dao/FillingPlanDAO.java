package com.pm.persistence.Sqmmanage.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.repast.core.system.BaseDao;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.JDBCHelper;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表 的DAO类
 * @author 刘镝
 */ 
@Component("FillingPlanDAO")
public class FillingPlanDAO extends BaseDao {
	public final static String QueryTableSql = "SELECT (SELECT count(scf.id) from  sqm_complaint_feedback scf where scf.cf_type = '1' AND scf.product_pack = T.CAPA) AS complaint_num,"
			+ "T.* ,s.code_nm as FILL_STATE_NAME "
			+ "from filling_plan_info T left join sys_code_info s on t.FILL_STATE = s.code_value and s.code_group_value='filling_plan_state' where 1=1 ";

	public final static String QueryTableFroFillingPlanSql = "SELECT T.* from filling_plan_info T where 1=1";

	private JDBCHelper makeSearch(FillingPlanInfo entiy, String sql) {
		JDBCHelper jdbcHelper = new JDBCHelper(sql);
		if (entiy != null) {
			jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. CNFYMD", JDBCHelper.EQ, entiy.getCNFYMD());
			jdbcHelper.putParam(JDBCHelper.AND,"T. VSL", JDBCHelper.EQ, entiy.getVSL());
			jdbcHelper.putParam(JDBCHelper.AND,"T. TURN", JDBCHelper.EQ, entiy.getTURN());
			jdbcHelper.putParam(JDBCHelper.AND,"T. PROD", JDBCHelper.EQ, entiy.getPROD());
			jdbcHelper.putParam(JDBCHelper.AND,"T. PRODNAME", JDBCHelper.LIKE, entiy.getPRODNAME());
			jdbcHelper.putParam(JDBCHelper.AND,"T. BLNDQTY", JDBCHelper.EQ, entiy.getBLNDQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LOTNO", JDBCHelper.EQ, entiy.getLOTNO());
			jdbcHelper.putParam(JDBCHelper.AND,"T. PKG", JDBCHelper.EQ, entiy.getPKG());
			jdbcHelper.putParam(JDBCHelper.AND,"T. HTANK", JDBCHelper.EQ, entiy.getHTANK());
			jdbcHelper.putParam(JDBCHelper.AND,"T. MOVEQTY", JDBCHelper.EQ, entiy.getMOVEQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. MARGINQTY", JDBCHelper.EQ, entiy.getMARGINQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. MGUBUNNM", JDBCHelper.EQ, entiy.getMGUBUNNM());
			jdbcHelper.putParam(JDBCHelper.AND,"T. DENSITY", JDBCHelper.EQ, entiy.getDENSITY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. COMPOUNDID", JDBCHelper.EQ, entiy.getCOMPOUNDID());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LINEID", JDBCHelper.EQ, entiy.getLINEID());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LINENAME", JDBCHelper.EQ, entiy.getLINENAME());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LABELSTATE", JDBCHelper.EQ, entiy.getLABELSTATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. CHRGQTY", JDBCHelper.EQ, entiy.getCHRGQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. CAPA", JDBCHelper.EQ, entiy.getCAPA());
			jdbcHelper.putParam(JDBCHelper.AND,"T. FILL_STATE", JDBCHelper.EQ, entiy.getFILL_STATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. FILL_STDATE", JDBCHelper.EQ, entiy.getFILL_STDATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. FILL_EDDATE", JDBCHelper.EQ, entiy.getFILL_EDDATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. user_yn", JDBCHelper.EQ, entiy.getUser_yn());
			jdbcHelper.putParam(JDBCHelper.AND,"T. fault_user_id", JDBCHelper.EQ, entiy.getFault_user_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. fault_time", JDBCHelper.EQ, entiy.getFault_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. over_plan_user", JDBCHelper.EQ, entiy.getOver_plan_user());
			jdbcHelper.putParam(JDBCHelper.AND,"T. fourSYn", JDBCHelper.EQ, entiy.getFourSYn());

		}
		return jdbcHelper;
	}

	
	private JDBCHelper makeSearch2(FillingPlanInfo entiy, String sql) {
		JDBCHelper jdbcHelper = new JDBCHelper(sql);
		if (entiy != null) {
			jdbcHelper.putParam(JDBCHelper.AND,"T. id", JDBCHelper.EQ, entiy.getId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. CNFYMD", JDBCHelper.GTQ, entiy.getCNFYMD());
			jdbcHelper.putParam(JDBCHelper.AND,"T. CNFYMD", JDBCHelper.LTQ, entiy.getENDCNFYMD());
			jdbcHelper.putParam(JDBCHelper.AND,"T. VSL", JDBCHelper.EQ, entiy.getVSL());
			jdbcHelper.putParam(JDBCHelper.AND,"T. TURN", JDBCHelper.EQ, entiy.getTURN());
			jdbcHelper.putParam(JDBCHelper.AND,"T. PROD", JDBCHelper.EQ, entiy.getPROD());
			jdbcHelper.putParam(JDBCHelper.AND,"T. PRODNAME", JDBCHelper.LIKE, entiy.getPRODNAME());
			jdbcHelper.putParam(JDBCHelper.AND,"T. BLNDQTY", JDBCHelper.EQ, entiy.getBLNDQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LOTNO", JDBCHelper.EQ, entiy.getLOTNO());
			jdbcHelper.putParam(JDBCHelper.AND,"T. PKG", JDBCHelper.EQ, entiy.getPKG());
			jdbcHelper.putParam(JDBCHelper.AND,"T. HTANK", JDBCHelper.EQ, entiy.getHTANK());
			jdbcHelper.putParam(JDBCHelper.AND,"T. MOVEQTY", JDBCHelper.EQ, entiy.getMOVEQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. MARGINQTY", JDBCHelper.EQ, entiy.getMARGINQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. MGUBUNNM", JDBCHelper.EQ, entiy.getMGUBUNNM());
			jdbcHelper.putParam(JDBCHelper.AND,"T. DENSITY", JDBCHelper.EQ, entiy.getDENSITY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. COMPOUNDID", JDBCHelper.EQ, entiy.getCOMPOUNDID());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LINEID", JDBCHelper.EQ, entiy.getLINEID());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LINENAME", JDBCHelper.EQ, entiy.getLINENAME());
			jdbcHelper.putParam(JDBCHelper.AND,"T. LABELSTATE", JDBCHelper.EQ, entiy.getLABELSTATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. CHRGQTY", JDBCHelper.EQ, entiy.getCHRGQTY());
			jdbcHelper.putParam(JDBCHelper.AND,"T. CAPA", JDBCHelper.EQ, entiy.getCAPA());
			jdbcHelper.putParam(JDBCHelper.AND,"T. FILL_STATE", JDBCHelper.EQ, entiy.getFILL_STATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. FILL_STDATE", JDBCHelper.EQ, entiy.getFILL_STDATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. FILL_EDDATE", JDBCHelper.EQ, entiy.getFILL_EDDATE());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_id", JDBCHelper.EQ, entiy.getCreate_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. create_time", JDBCHelper.EQ, entiy.getCreate_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. update_id", JDBCHelper.EQ, entiy.getUpdate_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. update_time", JDBCHelper.EQ, entiy.getUpdate_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. user_yn", JDBCHelper.EQ, entiy.getUser_yn());
			jdbcHelper.putParam(JDBCHelper.AND,"T. fault_user_id", JDBCHelper.EQ, entiy.getFault_user_id());
			jdbcHelper.putParam(JDBCHelper.AND,"T. fault_time", JDBCHelper.EQ, entiy.getFault_time());
			jdbcHelper.putParam(JDBCHelper.AND,"T. equipmentId", JDBCHelper.EQ, entiy.getEquipmentId());
			jdbcHelper.putParam(JDBCHelper.AND,"T. over_plan_user", JDBCHelper.EQ, entiy.getOver_plan_user());
			jdbcHelper.putParam(JDBCHelper.AND,"T. fourSYn", JDBCHelper.EQ, entiy.getFourSYn());
		}
		return jdbcHelper;
	}

	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<FillingPlanInfo> interfaceFillingPlanDataList(FillingPlanInfo entity, int pageIndex, int pageSize) {
		JDBCHelper helper = makeSearch2(entity, QueryTableSql);
		helper.setDefaultOrderBy("T. CNFYMD DESC");
		return getPage(helper.getSql(), helper.getParam(), FillingPlanInfo.class, pageIndex, pageSize);
	}


	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	public Pagination<FillingPlanInfo> queryLineProductInfoList(FillingPlanInfo entity, int pageIndex, int pageSize) {
		String sql="SELECT (SELECT count(scf.id) from  sqm_complaint_feedback scf where scf.cf_type = '1' AND scf.product_pack = T.CAPA) AS complaint_num,T.* from filling_plan_info T where 1=1 ";
		if(!StringUtils.isNullOrEmpty(entity.getId())) {
			sql+=" and T.id='"+entity.getId()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getCNFYMD())) {
			sql+=" and T.CNFYMD='"+entity.getCNFYMD()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getPROD())) {
			sql+=" and T.PROD like '%"+entity.getPROD()+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getPRODNAME())) {
			sql+=" and T.PRODNAME like '%"+entity.getPRODNAME()+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getLINEID())) {
			sql+=" and T.LINEID='"+entity.getLINEID()+"' ";
		}
		if(entity.getFILL_STATE()!=null) {
			sql+=" and T.FILL_STATE="+entity.getFILL_STATE()+" ";
			switch (entity.getFILL_STATE()) {
			case 1://进行中
				sql+=" or (  T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE = 1 ";
				break;
			case 2://异常
				sql+=" or (  T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE = 2 ";
				break;
			case 3://完成
				sql+=" or (  T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and FILL_EDDATE like CONCAT(date_format(now(), '%Y-%m-%d'),'%') and  T.FILL_STATE = 3 ";
				break;
			case 4://故障
				sql+=" or (  T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE = 4 ";
				break;
			default:
				break;
			}
			if(!StringUtils.isNullOrEmpty(entity.getPROD())) {
				sql+=" and T.PROD like '%"+entity.getPROD()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getPRODNAME())) {
				sql+=" and T.PRODNAME like '%"+entity.getPRODNAME()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getLINEID())) {
				sql+=" and T.LINEID='"+entity.getLINEID()+"' ";
			}
		}else {
			sql+=" or (( T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE in (1,2,4)";
			if(!StringUtils.isNullOrEmpty(entity.getPROD())) {
				sql+=" and T.PROD like '%"+entity.getPROD()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getPRODNAME())) {
				sql+=" and T.PRODNAME like '%"+entity.getPRODNAME()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getLINEID())) {
				sql+=" and T.LINEID='"+entity.getLINEID()+"' ";
			}
			sql+= "  or FILL_EDDATE like CONCAT(date_format(now(), '%Y-%m-%d'),'%'))";
		}
		sql+="  order by CNFYMD,COMPOUNDID";
		return getPage(sql, null, FillingPlanInfo.class, pageIndex, pageSize);
	}

	/**
	 * 保存实体对象entity
	 * @author gao
	 */
	public int saveFillingPlanInfo(FillingPlanInfo entity) {

		int flag =  this.saveNew(entity);
		return flag;
	}
	
	/**
	 * 保存实体对象entity
	 * @author gao
	 */
	public int saveDensityInfo(FillingPlanInfo entity) {

		String sql = "update  filling_plan_info set DENSITY='"+entity.getDENSITY()+"',update_time='"+entity.getUpdate_time()+"'"
				+ ",LINEID='"+entity.getLINEID()+"',LINENAME='"+entity.getLINENAME()+"' "
				+ "where CNFYMD = '"+entity.getCNFYMD()+"' and PROD='"+entity.getPROD()+"' and PKG='"+entity.getPKG()+"'"
						+ "and LOTNO='"+entity.getLOTNO()+"' and VSL='"+entity.getVSL()+"' and FILL_STATE='0'";
		int flag =  this.executeSQL(sql);
		return flag;
	}

	/**
	 * 根据实体条件检索list
	 * @author 刘镝
	 */
	public List<FillingPlanInfo> queryMovingGoodsInfoList(FillingPlanInfo entity){
		String sql = "select * from filling_plan_info where 1=1 and CNFYMD='"+entity.getCNFYMD()+"'";
		return this.queryForList(sql,FillingPlanInfo.class);
	}


	/**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	public int deleteFillingPlanInfo(String NowDate) {
		String sql = "delete from filling_plan_info where CNFYMD >= '"+NowDate+"' and FILL_STATE='0'";
		int flag =  this.executeSQL(sql);
		return flag;
	}

	/**
	 * 根据实体对象Id返回entity
	 * @author 刘镝
	 */
	public FillingPlanInfo getFillingPlanInfoById(String id) {
		String sql = "SELECT (SELECT count(scf.id) from  sqm_complaint_feedback scf where scf.cf_type = '1' AND scf.product_pack = T.CAPA) AS complaint_num,T.* from filling_plan_info T where  T.id ='"+id+"'";
		return this.queryObject(sql, FillingPlanInfo.class);
	}

	/**
	 * 修改灌装计划状态及计划时间
	 * @author gao
	 */
	public int updateFillingPlanInfo(FillingPlanInfo entity) {
		String sql="update filling_plan_info set ";
		if(entity.getFILL_STATE()!=null) {
			sql=sql+" FILL_STATE = "+entity.getFILL_STATE();
		}
		if(!StringUtils.isNullOrEmpty(entity.getFILL_STDATE())) {
			sql=sql+", FILL_STDATE = '"+entity.getFILL_STDATE()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getFILL_EDDATE())) {
			sql=sql+", FILL_EDDATE = '"+entity.getFILL_EDDATE()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getStart_pack_code())) {
			sql=sql+", start_pack_code = '"+entity.getStart_pack_code()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getFourSYn())) {
			sql=sql+", fourSYn = '"+entity.getFourSYn()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getFault_user_id())) {
			sql=sql+", fault_user_id = '"+entity.getFault_user_id()+"' ";
		}else {
			sql=sql+", fault_user_id = null ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getFault_time())) {
			sql=sql+", fault_time = '"+entity.getFault_time()+"' ";
		}else {
			sql=sql+", fault_time = null ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getEquipmentId())) {
			sql=sql+", equipmentId = '"+entity.getEquipmentId()+"' ";
		}else {
			sql=sql+", equipmentId = null ";
		}
		/*      		if(!StringUtils.isNullOrEmpty(entity.getTestNum()+"")) {
      			sql=sql+", TURN = '"+entity.getTestNum()+"' ";
      		}*/
		sql=sql+" where  id = '"+entity.getId()+"'";
		return this.executeSQL(sql);
	}

	/**
	 * 结束计划
	 * @author 
	 */
	public int updateFillingPlanInfoOver(FillingPlanInfo entity) {
		String sql="update filling_plan_info set  ";
		if(entity.getFILL_STATE()!=null) {
			sql=sql+" FILL_STATE = "+entity.getFILL_STATE()+" ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getFILL_EDDATE())) {
			sql=sql+", FILL_EDDATE = '"+entity.getFILL_EDDATE()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getOver_plan_user())) {
			sql=sql+", over_plan_user = '"+entity.getOver_plan_user()+"' ";
		}
		/*      		if(!StringUtils.isNullOrEmpty(entity.getTestNum()+"")) {
      			sql=sql+", TURN = '"+entity.getTestNum()+"' ";
      		}*/
		sql=sql+" where  id = '"+entity.getId()+"'";
		return this.executeSQL(sql);
	}

	/**
	 * 查询灌装计划
	 * @author 刘镝
	 */
	public List<FillingPlanInfo> queryFillingPlanDataList(FillingPlanInfo entity) {
		String sql="SELECT (SELECT count(scf.id) from  sqm_complaint_feedback scf where scf.cf_type = '1' AND scf.product_pack = T.CAPA) AS complaint_num,T.* from filling_plan_info T where 1=1 ";
		
		sql+=" and T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) ";

		if(!StringUtils.isNullOrEmpty(entity.getId())) {
			sql+=" and T.id='"+entity.getId()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getCNFYMD())) {
			sql+=" and T.CNFYMD='"+entity.getCNFYMD()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getPROD())) {
			sql+=" and T.PROD like '%"+entity.getPROD()+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getPRODNAME())) {
			sql+=" and T.PRODNAME like '%"+entity.getPRODNAME()+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getLINEID())) {
			sql+=" and T.LINEID='"+entity.getLINEID()+"' ";
		}
		if(entity.getFILL_STATE()!=null) {
			sql+=" and T.FILL_STATE="+entity.getFILL_STATE()+" ";
		}
		sql+="  order by CNFYMD,COMPOUNDID";
		return queryForList(sql, FillingPlanInfo.class);
	}
	
	/**
	 * 查询昨日未完成的及今日的灌装计划
	 * @author 刘镝
	 * @throws Exception 
	 */
	public List<FillingPlanInfo> queryFillingPlanDataList2(FillingPlanInfo entity) throws Exception {
		String now = DateUtil.getNow(DateUtil.YMD);
		
		String sql="SELECT (SELECT count(scf.id) from  sqm_complaint_feedback scf where scf.cf_type = '1' AND scf.product_pack = T.CAPA) AS complaint_num,T.* from filling_plan_info T where 1=1 ";
		if(!StringUtils.isNullOrEmpty(entity.getId())) {
			sql+=" and T.id='"+entity.getId()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getCNFYMD())) {
			sql+=" and T.CNFYMD='"+entity.getCNFYMD()+"' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getPROD())) {
			sql+=" and T.PROD like '%"+entity.getPROD()+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getPRODNAME())) {
			sql+=" and T.PRODNAME like '%"+entity.getPRODNAME()+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(entity.getLINEID())) {
			sql+=" and T.LINEID='"+entity.getLINEID()+"' ";
		}
		if(entity.getFILL_STATE()!=null) {
			sql+=" and T.FILL_STATE="+entity.getFILL_STATE()+" ";
			if(now.equals(entity.getCNFYMD())) {
			switch (entity.getFILL_STATE()) {
			case 1://进行中
				sql+=" or (  ( T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE = 1 ";
				break;
			case 2://异常
				sql+=" or ( ( T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE = 2 ";
				break;
			case 3://完成
				sql+=" or ( ( T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and FILL_EDDATE like CONCAT(date_format(now(), '%Y-%m-%d'),'%') and  T.FILL_STATE = 3 ";
				break;
			case 4://故障
				sql+=" or ( ( T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE = 4 ";
				break;
			default:
				break;
			}

			if(!StringUtils.isNullOrEmpty(entity.getPROD())) {
				sql+=" and T.PROD like '%"+entity.getPROD()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getPRODNAME())) {
				sql+=" and T.PRODNAME like '%"+entity.getPRODNAME()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getLINEID())) {
				sql+=" and T.LINEID='"+entity.getLINEID()+"' ";
			}

			if(entity.getFILL_STATE()!=0)sql+= " )";
			   }
		}else {
			if(now.equals(entity.getCNFYMD())) {
			sql+=" or ((( T.CNFYMD >= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d')) and  T.FILL_STATE in (1,2,4)   or FILL_EDDATE like CONCAT(date_format(now(), '%Y-%m-%d'),'%'))";
			if(!StringUtils.isNullOrEmpty(entity.getPROD())) {
				sql+=" and T.PROD like '%"+entity.getPROD()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getPRODNAME())) {
				sql+=" and T.PRODNAME like '%"+entity.getPRODNAME()+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(entity.getLINEID())) {
				sql+=" and T.LINEID='"+entity.getLINEID()+"' ";
			}
			sql+= ")";
			}
		}
		sql+="  order by CNFYMD,COMPOUNDID";
		return queryForList(sql, FillingPlanInfo.class);
	}
	
	/**
	 * 查询上一次该生产线生产了哪个产品
	 * @author 刘镝
	 */
	public List<FillingPlanInfo> queryLatelyFillingPlanPROD(String id) {
		String sql="SELECT * from filling_plan_info where LINEID=(SELECT LINEID from filling_plan_info where id="+id+") and  FILL_STDATE <(SELECT FILL_STDATE from filling_plan_info where id="+id+") and fill_state!=0 ORDER BY FILL_STDATE DESC LIMIT 0,1";
		List<FillingPlanInfo> queryForList = queryForList(sql, FillingPlanInfo.class);
		String sql2="SELECT * from filling_plan_info where LINEID=(SELECT LINEID from filling_plan_info where id="+id+") and  fill_state!=0 and id !="+id+" ORDER BY FILL_STDATE DESC LIMIT 0,1 ";
		queryForList=queryForList(sql2, FillingPlanInfo.class);
		return queryForList;
	}
	
	/**
	 * 查询同生产线同一天(包括前一天到未来五天)进行中的灌装计划数量
	 * @author 刘镝
	 * @throws Exception 
	 */
	public int queryTestingYn(FillingPlanInfo entity) throws Exception {
		String sql="SELECT id from filling_plan_info WHERE id!="+entity.getId()+" and ((CNFYMD>= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1)) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d') ) and FILL_STATE in (1,2,4) and LINEID='"+entity.getLINEID()+"'";
 		return queryForList(sql, FillingPlanInfo.class).size();
	}
	
	/**
	 * 查询同生产线前一天到未来五天的灌装计划（未进行、进行中）
	 * @author 刘镝
	 * @throws Exception 
	 */
	public List<FillingPlanInfo> queryNotAndProgressPlanList(FillingPlanInfo entity) throws Exception {
		String sql="SELECT * from filling_plan_info WHERE ((CNFYMD>= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1)) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d') ) and FILL_STATE in (0,1) and LINEID='"+entity.getLINEID()+"'";
 		return queryForList(sql, FillingPlanInfo.class);
	}
	
	/**
	 * 查询同生产线前一天到未来五天的灌装计划（异常、故障）
	 * @author 刘镝
	 * @throws Exception 
	 */
	public List<FillingPlanInfo> queryFaultPlanList(FillingPlanInfo entity) throws Exception {
		String sql="SELECT * from filling_plan_info WHERE ((CNFYMD>= (SELECT  CNFYMD from  filling_plan_info WHERE CNFYMD < date_format(now(), '%Y%m%d') ORDER BY CNFYMD DESC LIMIT 0,1)) and CNFYMD <= date_format(DATE_ADD(now(), INTERVAL 5 DAY),'%Y%m%d') ) and FILL_STATE in (2,4) and LINEID='"+entity.getLINEID()+"'";
 		return queryForList(sql, FillingPlanInfo.class);
	}
	
	/**
	 * 获取未来 第 past 天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getFetureDateXG(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = (Date) calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String result = format.format(today);
		return result;
	}
}