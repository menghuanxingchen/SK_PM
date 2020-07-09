package com.repast.core.constant;

public class PmConstant {

	/*
	 * 是否状态
	 */
	public static String STATE_YN  = "state_yn_group";//行政设备
	public static String STATE_Y = "Y";
	public static String STATE_N = "N";
	
	/*
	 * 维修类别
	 */
	public static String REPAIR_CATEGORY  = "repair_category_group";
	public static String EXPENSES_Y 	  = "Y";//有费用
	public static String EXPENSES_N 	  = "N";//无费用
	
	/*
	 * 部门
	 */
	public static String DEPART_GROUP          = "depart_group";
	public static String ADMIN_EQUIP_DEPART    = "1";//品质安全科
	public static String PRODUCE_DEPART        = "2";//生产
	public static String PRODUCE_MANAGE_DEPART = "3";//生产支援
	
	/*
	 * 下属部门
	 */
	//行政设备
	public static String ADMIN_EQUIP_DEPART_GROUP  = "admin_equip_depart_group";
	public static String ELECTRICAL_DEPART         = "1";//电仪
	public static String MECHANICAL_DEPART         = "2";//机械
	public static String SECURITY_DEPART           = "3";//安全
	public static String ADMIN_EQUIP_DEPART_SUB    = "4";//行政设备
	public static String FINANCE_DEPART            = "5";//财务
	public static String ADMIN_EQUIP_DEPART_OTHER  = "6";//其他
	//生产
	public static String PRODUCE_DEPART_GROUP      = "produce_depart_group";
	public static String FILLING_DEPART        	   = "1";//灌装
	public static String PRODUCE_DEPART_sub 	   = "2";//生产
	public static String MIX_DEPART 			   = "3";//调配
	//生产管理
	public static String PRODUCE_MANAGE_DEPART_GROUP   = "produce_manage_depart_group";
	public static String WAREHOUSE_DEPART        	   = "1";//仓库
	public static String LABORATORY_DEPART 	           = "2";//实验室
	public static String PRODUCE_MANAGE_DEPART_SUB 	   = "3";//生产管理
	public static String PRODUCE_MANAGE_DEPART_OTHER   = "4";//其他
	
	/*
	 * 生产线
	 */
	public static String LINE_PRODUNCT          = "line";
	
	//财务
//	public static String FINANCE_DEPART_GROUP          = "finance_depart_group";
//	public static String FINANCE_SUB_DEPART        	   = "1";//财务
	//设备类型
	/*public static String DEPART_GROUP_SUB4           = "depart_group_sub4";
	public static String DEPART_GROUP_SUB4001        	 = "001";//设备类型1
	public static String DEPART_GROUP_SUB4002	         = "002";*///设备类型2
	//区域
	public static String AREACODE           = "areacode";
	public static String AREACODE01        	 = "1";//1.V308区域；
	public static String AREACODE02	         = "2";//2.灌装线；
	public static String AREACODE03	         = "3";//3.调配车间室内及屋檐；
	public static String AREACODE04	         = "4";//4.控制室；
	public static String AREACODE05          = "5";//5.调配罐区室外及附属；
	public static String AREACODE06	         = "6";//6.办公楼；
	public static String AREACODE07	         = "7";//7.成品仓库；
	public static String AREACODE08	         = "8";//8.空压机房；
	public static String AREACODE09	         = "9";//9.卧罐及附属；
	public static String AREACODE10	         = "10";//10.消防泵房隔油池及附属；
	public static String AREACODE11	         = "11";//11.不确定及其它；
	
	/*
	 * 职位
	 */
	public static String POSITION_GROUP           = "position_group";
	public static String FACTORY_MANAGER          = "1";//厂长
	public static String SECTION_CHIEF            = "2";//科长
	public static String FACTORY_STAFF            = "3";//职员
	public static String ADMIN_STAFF              = "4";//管理员
	
	/*
	 * 审批状态
	 */
	public static String APPROVE_GROUP           			= "approve_group";
	public static String APPROVE_WAIT          				= "1";//待审批
	public static String DISTRIBUT_WAIT            			= "2";//待分配
	public static String OFFER_WAIT              			= "3";//待报价
	public static String FACTORY_MANAGER_APPROVE_WAIT       = "4";//厂长待审批
	public static String REPAIR_WAIT       					= "5";//待维修
	public static String CONFIRM_WAIT           			= "6";//待确认
	public static String FACTORY_MANAGER_CONFIRM_WAIT       = "7";//厂长待确认
	public static String REJECT          					= "8";//驳回
	public static String DONE            					= "9";//完成
	
	/*
	 * 审批线
	 */
	public static String DRAFT       					= "1";//起草
	public static String APPROVE          				= "2";//审批
	public static String DISTRIBUT            			= "3";//分配
	public static String FACTORY_MANAGER_APPROVE        = "4";//厂长审批
	public static String CONFIRM           				= "5";//确认
	public static String FACTORY_MANAGER_CONFIRM        = "6";//厂长确认
	public static String REAPIR							= "7";//维修   不在审批流内 判断用
	
	/*
	 * 搬入搬出审批状态
	 */
	public static String MOVINGAPPROVE_GROUP           		= "moving_approve_group";
	public static String MOVINGAPPROVE_WAIT          		= "1";//待审批
	public static String MOVINGKZAPPROVE_WAIT               = "2";//科长待审批
	public static String MOVINGCZAPPROVE_WAIT               = "3";//厂长待审批
	public static String MOVINGCONFIRM_WAIT           		= "4";//待确认
	public static String MOVINGREJECT          				= "5";//驳回
	public static String MOVINGDONE            				= "6";//完成


	/*
	 * 审批线
	 */
	public static String MOVINGDRAFT       					= "1";//起草
	public static String MOVINGAPPROVE          			= "2";//审批
	public static String MOVINGFACTORY_MANAGER_APPROVE      = "3";//厂长审批
	public static String MOVINGCONFIRM           			= "4";//确认
	public static String MOVINSTEPGDONE           			= "6";//完成

	
	/*
	 * 审批状态
	 */
	public static String APPROVE_STATE       			= "A";//审批
	public static String REJECT_STATE           		= "R";//驳回
	public static String CONFIRM_STATE             		= "C";//确认
	
	/*
	 * 计划周期
	 */
	
	public static String CYCLE_GROUP      			= "cycleCode";//周期group_code
	public static String CYCLE_HALFDAY      		= "halfday";//半天
	public static String CYCLE_TWOYEAR      		= "twoyears";//2年
	public static String CYCLE_THREEYEAR      		= "threeyears";//3年
	public static String CYCLE_FIVEYEAR      		= "fiveyears";//5年
	public static String CYCLE_ELEVENYEAR      	= "elevenyears";//11年
	public static String CYCLE_SIXYEAR      		= "sixyears";//6年
	public static String CYCLE_TWELVEYEAR      		= "twelveyears";//12年
	public static String CYCLE_TWOMONTH      		= "twomonth";//2个月
	public static String CYCLE_TWODAY     		= "twoday";//2天
	public static String CYCLE_BANMONTH     		= "banmonth";//半个月
	/*
	 * 报价供应商
	 */
	public static String OFFER_SUPPLIER_GROUP       	= "offer_supplier_group";
	
	/*
	 * 报价费用类型
	 */
	public static String OFFER_EXPENSES_TYPE_GROUP       = "offer_expenses_type_group";
	public static String PERSON_EXPENSES_TYPE       	= "8";
	public static String TRANS_FLAG       			 	= "1";//维修报价时材料费分类是合同固定运费的情况
	public static String TOOL_USE_FLAG       			 = "4";//维修报价时材料费分类是工具使用的情况
	public static String YDYY_FLAG       			 	= "6";//维修报价时材料费分类是一单一议用车运费的情况
	public static String MATERIAL_FLAG       			 = "2";//维修报价时材料费分类是材料费的情况
	
	/*
	 * 设备类别
	 */
	
	public static String MACH_TYPE_GROUP      			= "machTypeCode";//设备类别group code：机械、电仪、安全、其他
	/*
	 * 设备类别 SUB
	 */
	public static String machTypeSubCode_01      			= "machTypeSubCode_01";//设备类别 电仪
	public static String machTypeSubCode_02      			= "machTypeSubCode_02";//机械
	public static String machTypeSubCode_03      			= "machTypeSubCode_03";//安全
	public static String machTypeSubCode_04      			= "machTypeSubCode_04";//其他
	/*
	 * 设备类型
	 */
	
	public static String MACH_SPECIES_GROUP      	 = "machSpeciesCode";//设备类型group code：
	public static String MACH_SPECIES_001        	 = "001";//设备类型1
	public static String MACH_SPECIES_002	         = "002";//设备类型2
	
	/**
	 * 巡查保养分类
	 */
	public static String PRE_INS_TYPE = "preInsType"; //巡查保养分类group code
	public static String PRE_TYPE = "preplan"; //保养分类
	public static String INS_TYPE = "insPlan"; //巡查分类
	
	/*
	 * 排序
	 */
	public static String ORDER_GROUP      			= "orderCode";//排序类型group code
	public static String[][] ORDER_PER_PLAN_GROUP   = {{" pre_plan_date desc ","计划时间降序"},{" pre_plan_date asc ","计划时间升序"},{" pre_plan_group desc ","计划组"}};//时间升序,时间降序,计划组
	public static String[][] ORDER_INSPECT_PLAN_GROUP   = {{" ins_plan_date desc   ","计划时间降序"},{"ins_plan_date asc ","计划时间升序"},{" ins_plan_group desc ","计划组"}};//时间升序,时间降序,计划组
	public static String[][] ORDER_USER_GROUP   = {{" user_num ","用户工号"},{" dept_code ","部门"},{" sub_dept_code ","职能部门"},{" position_id ","职位"}};//用户工号，部门，下属部门，职位
	
	
	/*
	 * 修改区分
	 */
	public static String UPDATE_FLAG              = "update_flag";	
	public static String UPDATE_FLAG_1      			= "1";//单个修改
	public static String UPDATE_FLAG_0           		= "0";//批量修改
	public static String UPDATE_FLAG_2           		= "2";//批量删除
	
	/*
	 * 计划状态
	 */
	public static String PLAN_STATE 			= "planState"; //计划状态group code
	public static String PLAN_STATE_01			= "01"; //未处理
	public static String PLAN_STATE_02			= "02"; //待确认
	public static String PLAN_STATE_03 			= "03"; //已确认
	/*
	 * 工作分类
	 */
	public static String job_classification          = "job_classification";
	/*
	 * 报价录入时 单位
	 */
	public static String OFFER_UNIT              = "offer_unit";	
	/*
	 * 维修内容
	 */
	public static String REPAIR_DETAIL_GROUP     = "repair_detail_group";	
	
	/*
	 * 初始密码
	 */
	public static String PASSWORD_INIT     = "111111";	
	/*
	 * 维修类型
	 */
	public static String MAINTAINTYPE     = "maintaintype";
	
	/*
	 * 页面区分
	 */
	public static String PAGE_FLAG		    =  "payedpage";
	public static String UNPAYEDPAGE_FLAG	=  "unpayedpage";
	/*
	 * 检查项目状态  正常Y  异常N
	 */
	public static String CHECKITEM_STATE  = "checkitem_state";//行政设备
	public static String CHECKITEM_STATE_Y = "Y";//正常
	public static String CHECKITEM_STATE_N = "N";//异常
	
	/*
	 * 编辑类型（修改或新增）
	 */
	public static String EDITTYPE_UPDATE = "U";
	public static String EDITTYPE_ADD = "A";
	
	/*
	 * 维修相关页面排序区分
	 */
	public static String DEAL_ORDER   = "1";//按照分配要求完成日期
	public static String CREATE_ORDER = "2";//按照申请日期
	public static String PAYED_ORDER  = "3";//按照结算日期
	public static String REQUIRE_TIME_ORDER  = "4";//按照计划完成日期
	public static String DONE_TIME_ORDER  = "5";//按照计划完成日期
	/*
	 * 供应商group
	 */
	public static String SUPPLIER_GROUP   = "supplier_group";//按照分配要求完成日期
	
	/*
	 *PM完成率 
	 */
	public static String PM_RATE   = "100";//按照分配要求完成日期
	/*
	 *供应商 
	 */
	public static String SUPPLIER_NAME   = "天津市正强盛机械有限公司";
	/*
	 *邮件用语
	 */
	public static String MAIL_APPROVE   = " 您好 ,您有一个维修单需要审批，谢谢！  链接地址    http://114.113.147.106:8888/PM/";
	public static String MAIL_REJECT  = " 您好 ,您有一个维修单被驳回，请确认。谢谢！ 链接地址    http://114.113.147.106:8888/PM/ ";
	public static String MAIL_DEAL   = " 您好 ,您有一个维修单需要处理，谢谢！  链接地址    http://114.113.147.106:8888/PM/";
	public static String MAIL_CONFIRM  = " 您好 ,您有一个维修单需要确认，谢谢！  链接地址    http://114.113.147.106:8888/PM/ ";
	public static String MAIL_FACTORY_MANAGER_ALARAM  = " 您好 ,现有一个维修单进行了人员分配，谢谢！  链接地址    http://114.113.147.106:8888/PM/ ";
	
	/*
	 * 报价运费对应的规格
	 */
	public static String TRANSPORT_SPEC       	= "transport_spec";
	
	/*
	 * 维修单列表页面 查询条件 人员区分
	 */
	public static String USER_CHOOSE       	= "user_choose";
	public static String CREATE_USER       	= "1";
	public static String REPAIR_USER       	= "2";
	
	/*
	 * 查询保养列表
	 */
	public static String PLAN_LIST       	= "planList";
	public static String PLAN_TYPE_LIST       	= "planTypeList";
	/*
	 * 出勤数据统计 
	 */	
	public static String WORKOUT_ATTENDANCE ="1";//出勤数据
	public static String WORKOUT_MACHINE ="2";//机械种类
	/*
	 * 报价合同实用工具对应的规格
	 */
	public static String TOOL_USE_SPEC       	= "tool_use_spec";
	/*
	 * 报价人工费对应的规格
	 */
	public static String PERSON_EXPENSES       					= "person_expenses";
	public static String PERSON_HOUR_EXPENSES       	 		 = "1";
	public static String MEAL_SUPPLY_EXPENSES       			 = "2";
	public static String OVERTIME_HOUR_EXPENSES       			 = "3";
	
	/*
	 * 人工费和餐费单价
	 */
	public static String USER_PRICE_WITH_TAX       			 = "52";
	public static String MEAL_PRICE_WITH_TAX       			 = "12";
	
	/*
	 *项目状态 项目类型
	 * */
	public static String PROJECT_TYPE   = "projectType";
	public static String PROJECT_STATE   = "projectState";
	/*
	 *项目状态 项目类型
	 * */
	public static String PROJECT_TYPE_BIG   = "1";
	public static String PROJECT_TYPE_SMALL   = "2";
	
	public static String POSITION_CHECK   = "厂长 和 科长级别 才能确认";
	public static String MEETING_DOC  = "会议纪要";
	

	/*
	 *灌装机对应类别 电仪 “1”   灌装机‘7’
	 **/
	public static String MACHINE_SPECIECE_1  = "1";
	public static String MACHINE_TYPE_7   = "7";
	
	/*
	 * 项目计划管理-项目计划类型
	 */
	public static String PROJECT_PLAN_TYPE = "projectPlanType";
	/*
	 * 项目计划管理-项目计划状态
	 */
	public static String PROJECT_PLAN_STATE = "projectPlanState";
	/*
	 * 项目计划管理-大项目阶段
	 */
	public static String PROJECT_STEP = "bigProStep";
	/*
	 * 提案书内容分类
	 */
	public static String PROPOSAL_ITEM = "proposal_item";
	/*
	 * 提案书审批状态
	 */
	public static String PROPOSAL_APPROVE_STATE = "proposal_approve_state";
	
	/*
	 * 提案书审批状态详情
	 */
	public static String PROPOSAL_APPROVE_WAIT          				= "1";//待审批
	public static String PROPOSAL_DISTRIBUT_WAIT            			= "2";//待分配
	public static String PROPOSAL_OPERATE_WAIT       					= "3";//待实施
	public static String PROPOSAL_CONFIRM_WAIT           				= "4";//待确认
	public static String PROPOSAL_FACTORY_MANAGER_CONFIRM_WAIT       	= "5";//厂长待确认
	public static String PROPOSAL_REJECT          						= "6";//驳回
	public static String PROPOSAL_DONE            						= "7";//完成
	
	/*
	 * 提案书审批线
	 */
	public static String PROPOSAL_DRAFT       					= "1";//起草
	public static String PROPOSAL_APPROVE          				= "2";//审批
	public static String PROPOSAL_DISTRIBUT            			= "3";//分配
	public static String PROPOSAL_CONFIRM           			= "4";//确认
	public static String PROPOSAL_FACTORY_MANAGER_CONFIRM       = "5";//厂长确认
	public static String PROPOSAL_OPERATE						= "6";//实施   不在审批流内 判断用
	/*
	 * 人员分配新增维修类型
	 */
	public static String REPAIR_CLASSIFY = "repair_classify";
	
	/*
	 * 导出文件的用户名和密码
	 */
	public static String encrypt_account = "admin";
	public static String encrypt_password = "111111";

	public static String JOB_TYPE = "job_type";
	public static String COMPONTNTS = "components_03";
	
	/*
	 * sqm角色
	 */
	public static String SQM_ROLE = "sqm_role";//sqm角色
	public static String INSPECTOR = "1";//检测员
	public static String LINE_LEATER = "2";//线长
	public static String SPOT_CHECK = "3";//抽检
	public static String SQM_OTHER = "4";//其他
	
}
