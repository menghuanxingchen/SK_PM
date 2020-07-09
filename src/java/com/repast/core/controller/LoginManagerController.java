package com.repast.core.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.service.LoginManagerService;
import com.repast.core.spring.auth.Author;
import com.repast.core.spring.auth.CheckType;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;
import com.repast.core.util.DateUtil;
import com.repast.persistence.entity.SysUserInfo;

@Controller
@RequestMapping("/loginManagerController")
public class LoginManagerController {
	@Resource
	private LoginManagerService loginManagerService;
	@Resource
	private SysUserInfoService sysUserInfoService;
	private static Logger logger = Logger.getLogger("logger");
	
	@Author(type=CheckType.NO_CHECK)
	@RequestMapping("/showLoginPage")
	public String showLoginPage(HttpServletRequest request,HttpServletResponse response){
		request.getSession().setMaxInactiveInterval(1);
		return "foodms/login/login";
	}
	/**
	 * 跳转到登录后首页
	 * @param request
	 * @param response
	 * @return
	 */
	@Author(type=CheckType.CHECK_LOGIN)
	@RequestMapping("/showManipage")
	public String showManipage(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String currentUserId = (String)request.getSession().getAttribute("user");
		String loginType = (String)request.getSession().getAttribute("loginType");
		try {
			logger.info("登陆信息：userid:"+currentUserId);
			logger.info("登陆信息：loginType:"+loginType);
			logger.info("登陆信息：loginDateTime:"+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("获取登陆信息异常！");
		}
		//设置当前登录到前台中
		request.getSession().setAttribute("sys_hidden_currentUserId", currentUserId);
		model.addAttribute("sys_hidden_currentUserId",currentUserId);
		if(!StringUtils.isNullOrEmpty(currentUserId)){
			SysUserInfo user = loginManagerService.getSysUserInfoById(currentUserId);
			if(user!=null){
				request.getSession().setAttribute("sys_hidden_currentUsername", user.getUser_nm());
				request.getSession().setAttribute("sys_hidden_dept", user.getDept_code());
				request.getSession().setAttribute("sys_hidden_dept_name", user.getDept_code_nm());
				request.getSession().setAttribute("sys_hidden_auth", user.getPosition_id());
				request.getSession().setAttribute("sys_hidden_subdept", user.getSub_dept_code());
				request.getSession().setAttribute("sys_hidden_Sqmrolecode", user.getSqm_role_code());
				request.getSession().setAttribute("id", user.getUser_id());
			}
		}
		//门卫只有搬入搬出一个权限
		if("PC".equals(loginType) && "lg00999".equals(currentUserId)){
			return "web/movinginfo/moving_list";
		}else{
			if("PC".equals(loginType)){
				return "web/repair/repair_order_list";
			}else if("MBMOVING".equals(loginType)){//搬入搬出
				request.getSession().setMaxInactiveInterval(0);
				return "mobile/main/index_moving";
			}else if("UNIAPP".equals(loginType)){//从手机端uni-app
				request.getSession().setMaxInactiveInterval(0);
				
				return "mobile/main/index_uniapp";
			}
			else if("TEMPORARY".equals(loginType)){//临时人员扫描搬入
				request.getSession().setMaxInactiveInterval(0);
				return "mobile/m_moving_info/m_moving_in";
			}
			else if("PLAN".equals(loginType)){//保养计划
				request.getSession().setMaxInactiveInterval(0);
				return "mobile/m_preplaninfo/m_plan_list";
			}
			else if("INSPECTPLAN".equals(loginType)){//巡检计划
				request.getSession().setMaxInactiveInterval(0);
				return "mobile/m_inspectplan/m_inspectplan_list";
			}
			else if("REPAIR".equals(loginType)){//维修计划
				request.getSession().setMaxInactiveInterval(0);
				return "mobile/m_repair/m_repair_list";
			}
			else{
				if("lg00999".equals(currentUserId)){
					return "mobile/main/index_uniappMen";
				}else{
					return "mobile/main/index_m";
				}
				
			}
		}
	}
	
	
	/**
	 * 跳转到登录后首页
	 * @param request
	 * @param response
	 * @return
	 */
	@Author(type=CheckType.NO_CHECK)
	@RequestMapping("/showManipageUniapp")
	public String showManipageUniapp(@FormModel SysUserInfo sysUserInfo,HttpServletRequest request,
			HttpServletResponse response,Model model){
		String currentUserId = sysUserInfo.getUser_num().toLowerCase();
		
		try {
			logger.info("登陆信息：userid:"+currentUserId);
			
			logger.info("登陆信息：loginDateTime:"+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("获取登陆信息异常！");
		}
		//设置当前登录到前台中
		request.getSession().setAttribute("sys_hidden_currentUserId", currentUserId);
		model.addAttribute("sys_hidden_currentUserId",currentUserId);
		if(!StringUtils.isNullOrEmpty(currentUserId)){
			SysUserInfo user = loginManagerService.getSysUserInfoById(currentUserId);
			if(user!=null){
				request.getSession().setAttribute("sys_hidden_currentUsername", user.getUser_nm());
				request.getSession().setAttribute("sys_hidden_dept", user.getDept_code());
				request.getSession().setAttribute("sys_hidden_dept_name", user.getDept_code_nm());
				request.getSession().setAttribute("sys_hidden_auth", user.getPosition_id());
				request.getSession().setAttribute("sys_hidden_subdept", user.getSub_dept_code());
				request.getSession().setAttribute("id", user.getUser_id());
				request.getSession().setAttribute("user", user.getUser_num().toLowerCase());
			}
		}
		
		//门卫只有搬入搬出一个权限
		request.getSession().setMaxInactiveInterval(0);
		if("lg00999".equals(currentUserId)){//门卫的菜单
			return "mobile/main/index_uniappMen";
		}else{
			
			return "mobile/main/index_uniapp";
		}
		
	}
	
	/**
	 * 校验用户民／密码
	 * @param request
	 * @param response
	 * @return
	 */
	@Author(type=CheckType.NO_CHECK)
	@RequestMapping("/checkSysUserInfo.json")
	public @ResponseBody JSONMap<String,Object> checkSysUserInfo(@FormModel SysUserInfo sysUserInfo,HttpServletRequest request,
			HttpServletResponse response,@FormModel Processor processor){
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			if(processor!=null){
				SysUserInfo sysUserDetail = loginManagerService.getLoginInfo(sysUserInfo);
				String pwd=sysUserInfo.getPwd();
				if(sysUserDetail !=null){
					request.getSession().setAttribute(SysConstant.SESSION_LOGIN, SysConstant.SESSION_LOGIN_PASS);
					request.getSession().setAttribute("user", sysUserDetail.getUser_num());
					request.getSession().setAttribute("password", sysUserDetail.getPwd());
					request.getSession().setAttribute("loginType", sysUserInfo.getLoginType());
					request.getSession().setAttribute("sys_hidden_currentUsername", sysUserInfo.getUser_nm());
					request.getSession().setAttribute("sys_hidden_dept", sysUserInfo.getDept_code());
					request.getSession().setAttribute("registrationID", sysUserInfo.getRegistrationID());
					//更新推送ID字段
					if(!StringUtils.isNullOrEmpty(sysUserInfo.getRegistrationID()) ) {
						if(sysUserInfo.getLoginType().equals("UNIAPP")){
							sysUserDetail.setPmregistrationID(sysUserInfo.getRegistrationID().trim().replaceAll("\"", ""));
						}else{
							sysUserDetail.setRegistrationID(sysUserInfo.getRegistrationID().trim().replaceAll("\"", ""));
						}
						
						sysUserDetail.setPwd(sysUserInfo.getPwd());
						sysUserInfoService.updateSysUserInfo(sysUserDetail);
					}
					sysUserDetail.setPwd(pwd);
					//列表查询（分页）
					UIDataConvert<SysUserInfo> convert = new UIDataConvert<SysUserInfo>();
					//列表中部门code name转化
					IConvert dept = new SqlMapConvertImpl("dept_code", "dept_code_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.DEPART_GROUP+"' ");
					convert.addConvert(dept);
					//列表中职位code name转化
					IConvert position = new SqlMapConvertImpl("position_id", "position_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.POSITION_GROUP+"' ");
					convert.addConvert(position);
					//列表中职位code name转化
					IConvert sub_dept = new SqlMapConvertImpl("sub_dept_code", "sub_dept_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.PRODUCE_MANAGE_DEPART_GROUP+"' ");
					convert.addConvert(sub_dept);
					//列表中sqm职位code name转化
					IConvert sqm_role = new SqlMapConvertImpl("sqm_role_code", "sqm_role_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.SQM_ROLE+"' ");
					convert.addConvert(sqm_role);	
					convert.format(sysUserDetail);
					model.put("sysUserinfo", sysUserDetail);
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, "登录成功");
				}else{
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, "登录失败");
				}
		}
	}catch(Exception e){
		model.put(SysConstant.OP_FLAG, false);
	}
	 return model;
	}
	
	@RequestMapping("/initSession")
	private @ResponseBody String initSession(HttpServletRequest request){
		Enumeration em = request.getSession().getAttributeNames();
		  while(em.hasMoreElements()){
		   request.getSession().removeAttribute(em.nextElement().toString());
		  }
		  request.getSession().setMaxInactiveInterval(1);
		return "foodms/login/login";
	}

}