
var id = getRequestParameterValue("id");
var planNm = getRequestParameterValue("planNm");
var date = getRequestParameterValue("date");
/**
 * 预防性维护
 */
function goToMachineList(){
	var page = "/pm/mobile/m_preplaninfo/m_machine_list.jsp?id="+id;
	pageForward(page);
}
/**
 * 预见性维护
 */
function goToPropMachineList(){
	var page = "/pm/mobile/m_preplaninfo/prop_machine_list.jsp?id="+id+"&chk_type="+Class.getConstant('PROPTYPE_PRE');;
	pageForward(page);
}
/**
 * 大修项目
 */
function goToRepairMachineList(){
	var page = "/pm/mobile/m_preplaninfo/prop_machine_list.jsp?id="+id+"&chk_type="+Class.getConstant('PROPTYPE_REPAIR');;
	pageForward(page);
}
/**
 * 返回
 */
function goBack(){
	var page = "/pm/mobile/m_preplaninfo/m_plan_list.jsp";
	pageForward(page);
}