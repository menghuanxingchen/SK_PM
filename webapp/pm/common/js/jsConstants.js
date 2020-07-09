var Class = (function() {  
  // Private static attributes.
  var constants = {//定义了两个常量
    PLAN_STATE: '02', //计划状态——02,待确认
    PLAN_STATE_CHECKED: '03', //计划状态——02,已确认
    PLAN_CYCLE_HALFDAY: 'halfday', //计划周期-半天
    MACH_TYPE_CODE: 'machTypeCode', //设备类别
    APPROVAL_STATE_DONE: '9', //维修单完成
    PAY_Y: 'Y', //维修单已结算
    PAY_N: 'N', //维修单未结算
    PAGE_FLAG: 'payedpage', //已结算页面flag
    UNPAYEDPAGE_FLAG: 'unpayedpage', //未结算页面flag
    PRE_TYPE: 'preplan', //保养分类
    EDITTYPE_UPDATE: 'U', //编辑分类 update
    EDITTYPE_ADD: 'A' ,//编辑分类 add 新增
    PROPTYPE_PRE: '1' ,//编辑分类 add 新增
    PROPTYPE_REPAIR: '2', //编辑分类 add 新增
    SUPPLIER_GROUP: "supplier_group",//供应商code_group_value
    MATERIAL_UNIT_GROUP: "material_unit_group",//供应商code_group_value
    EXPENSES_Y: "Y",//人员分配时维修类别默认是有费用
    PROPOSAL_CONFIRM_WAIT: "4",//待确认
    PROPOSAL_FACTORY_MANAGER_CONFIRM_WAIT: "5",//厂长待确认
    
    USER_PRICE_WITH_TAX: "52",//人工费含税31.8元/小时  2019/01/04修改
    MEAL_PRICE_WITH_TAX: "12",//餐费餐补 含税     12.72元/次
    TRANS_FLAG: "1",//维修报价时材料费分类是合同固定运费的情况
    TOOL_USE_FLAG: "4",//维修报价时材料费分类是工具使用的情况
//    MEAL_FLAG: "3",//维修报价时材料费分类是餐补（含税）的情况
//    OVERTIME_FLAG: "7",//维修报价时材料费分类是加班（含税）的情况
	MATERIAL_FLAG: "2",//维修报价时材料费分类是材料费的情况
    YDYY_FLAG: "6",//维修报价时材料费分类是一单一议用车运费的情况
    PERSON_EXPENSES: "8",//维修报价时材料费分类是人工费（合同内）的情况
    CAR_TG_FLAG: "97.41",//面包(塘沽)往返  		元/往返		75  2019/01/04修改
    CAR_SL_FLAG: "194.82",//面包(市里)往返  		元/往返		180 2019/01/04修改
    HC_TG_FLAG: "194.82",//货车小于2吨（塘沽） 		元/往返		185 2019/01/04修改
    HC_SL_FLAG: "389.65",//货车小于2吨（市里） 		元/往返		350 2019/01/04修改
    MT_SY_FLAG: "146.12",//码头收油收船车辆往返		元/往返		80  2019/01/04修改
    GM_CY_FLAG: "97.41",//购买柴油用车				元/往返		75
    MB_DLDT_FLAG: "80",//面包车东丽单趟 80元
	CAR_TG: "1",//面包(塘沽)往返  				元/往返		75
    CAR_SL: "2",//面包(市里)往返  				元/往返		180
    HC_TG: "3",//货车小于2吨（塘沽） 				元/往返		185
    HC_SL: "4",//货车小于2吨（市里） 				元/往返		350
    MT_SY: "5",//码头收油收船车辆往返				元/往返		80
    GM_CY: "6",//购买柴油用车					元/往返		75
    MB_DLDT: "7",//面包车东丽单趟 80元
    
    PERSON_HOUR_EXPENSES: "1",//小时工费
    MEAL_SUPPLY_EXPENSES: "2",//餐补
    OVERTIME_HOUR_EXPENSES: "3",//加班工时费
    OFFER_EXPENSES_TYPE_MATERIAL:"2",//材料费
    PLAN_LIST: "planList",//保养计划列表分类
    
	DHJ1:"1",//1	电焊机小于4.1小时
	DHJ2:"2",//2	电焊机大于4.1小时
	QHJ1 :"3",//3	气焊机小于4.1小时
	QHJ2:"4",//4	气焊机大于4.1小时
	DG1:"5",//5	电镐小于4.1小时
	DG2:"6",//6	电镐大于4.1小时
	DC1:"7",//7	电锤小于4.1小时
	DC2:"8",//8	电锤大于4.1小时
	DZ1:"9",//9	电钻小于4.1小时
	DZ2:"10",//10	电钻大于4.1小时
	JMJ1:"11",//11	角磨机小于4.1小时
	JMJ2:"12",//12	角磨机大于4.1小时
	FG1:"13",//13	风镐小于4.1小时
	FG2:"14",//14	风镐大于4.1小时
	WCJ1:"15",//15	无齿锯小于4.1小时
	WCJ2:"16",//16	无齿锯大于4.1小时
	YQ:"17",//17	氧气
	YQue:"18",//18	乙炔
	
    DHJ_IN_4HOUR:"9.74",//电焊机	10	元/小时	60	元/天
	QHJ_IN_4HOUR:"9.74",//气焊机	10	元/小时	60	元/天
	DG_IN_4HOUR:"3.89",//电镐	3	元/小时	30	元/天   2019/01/04修改
	DC_IN_4HOUR:"3.89",//电锤	3	元/小时	30	元/天   2019/01/04修改
	DZ_IN_4HOUR:"3.89",//电钻	3	元/小时	30	元/天   2019/01/04修改
	JMJ_IN_4HOUR:"3.89",//角磨机	3	元/小时	30	元/天  2019/01/04修改
	FG_IN_4HOUR:"9.74",//风镐	10	元/小时	60	元/天
	WCJ_IN_4HOUR:"9.74",//无齿锯	3	元/小时	30	元/天  2019/01/04修改
	YQ_IN_4HOUR:"91.18",//氧气	93.6	元/瓶		
	YQue_IN_4HOUR:"180.08",//乙炔	184.86	元/瓶		
	
	DHJ_OVER_4HOUR:"60",//电焊机	10	元/小时	60	元/天
	QHJ_OVER_4HOUR:"60",//气焊机	10	元/小时	60	元/天
	DG_OVER_4HOUR:"30",//电镐	3	元/小时	30	元/天
	DC_OVER_4HOUR:"30",//电锤	3	元/小时	30	元/天
	DZ_OVER_4HOUR:"30",//电钻	3	元/小时	30	元/天
	JMJ_OVER_4HOUR:"30",//角磨机	3	元/小时	30	元/天
	FG_OVER_4HOUR:"60",//风镐	10	元/小时	60	元/天
	WCJ_OVER_4HOUR:"30"//无齿锯	3	元/小时	30	元/天	

  }
  var con={};
  // 定义了一个静态方法
  con.getConstant=function(name){//获取常量的方法
    return constants[name];
  }
  return con
})();