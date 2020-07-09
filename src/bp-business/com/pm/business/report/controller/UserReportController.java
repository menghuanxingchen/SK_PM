package com.pm.business.report.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.report.service.UserReportService;
import com.pm.persistence.report.dto.UserReportDto;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;

/**
 * user_report
 * @author fish
 * @date 2016.11.30
 *
 */
@Controller
@RequestMapping("/UserReportController")
public class UserReportController {
	
	@Resource
	private UserReportService userReportService;

	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/report/userreport/user_report";
	}
	
	/**
	 * 用户报表查询
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel UserReportDto userReportDto,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//列表查询（分页）
			Pagination<UserReportDto> userReportDtoList = userReportService.queryUserReportList(userReportDto, processor.getPageIndex(),processor.getPageSize());
			model.put("data", userReportDtoList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 用户报表详情
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryUserReportDetail.json")
	public @ResponseBody JSONMap<String,Object> queryUserReportDetail(@FormModel UserReportDto userReportDto,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//列表查询（分页）
			List<UserReportDto> userReportDtoList = userReportService.queryUserReportDetail(userReportDto);
			model.put("userReportDtoList", userReportDtoList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
}
