package com.pm.business.workout.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.workout.service.WorkoutInfoService;
import com.pm.persistence.repair.dto.RepairOrderInfoDTO;
import com.pm.persistence.workout.entity.WorkOutDetail;
import com.pm.persistence.workout.entity.WorkOutInfo; 
import com.pm.persistence.workout.entity.RepairMaterialCostDetail; 
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.util.DateUtil;

@Controller
@RequestMapping("/WorkoutInfoController")
public class WorkoutInfoController {

	@Resource
	private WorkoutInfoService WorkoutInfoService;
	
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/account/workout_list";
	}

	/**
	 * 出勤统计列表查询 1
	 * @param WorkOutInfo
	 * @param processor
	 * @author SKCC
	 * @return
	 */
	@RequestMapping("/queryWorkoutDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel ("workoutInfoList") WorkOutInfo WorkOutInfo,@FormModel ("pay_yn")String pay_yn,@FormModel ("repairOrderInfo")RepairOrderInfoDTO repairOrderInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//列表查询（分页）
			//System.out.println("queryWorkoutDataList strat");
			String payYn = (pay_yn==null)?"":pay_yn;
			if(repairOrderInfo.getStart_dt() != null && !repairOrderInfo.getStart_dt().equals("")){
				
				WorkOutInfo.setStart_dt(repairOrderInfo.getStart_dt());
				WorkOutInfo.setEnd_dt(repairOrderInfo.getEnd_dt());
				
				if(!"".equals(payYn))
					WorkOutInfo.setPay_yn(payYn);
			}			
			Pagination<WorkOutInfo> workoutInfoList = WorkoutInfoService.queryWorkoutInfoList(WorkOutInfo, processor.getPageIndex(), processor.getPageSize());
			
			List<WorkOutInfo> list = workoutInfoList.getRecords();
			for(WorkOutInfo item:list){
				if(PmConstant.FINANCE_DEPART.equals(processor.getSubdept())){
					item.setFlag("7");
				} 
				//20170320 修改  当前月份的前2个月的数据 不能编辑 当月和前一个月可以编辑 下个月可以编辑
				int currentMonth =Integer.parseInt(DateUtil.smartFormat(new Date()).substring(5,7));//当前月
                int currentYear = Integer.parseInt(DateUtil.smartFormat(new Date()).substring(0,4));//当前年
                int itemMm =Integer.parseInt(item.getWork_out_ym().substring(5,7));//维修月份
                int itemYy =Integer.parseInt(item.getWork_out_ym().substring(0,4));//维修年份
                int monthsub = (currentYear-itemYy)*12+(currentMonth-itemMm);
                
                if (monthsub > 1 ){
                	item.setEditYnFlag("N");
                	item.setDeleteYnFlag("N");
                }else{
                	item.setEditYnFlag("Y");
                	item.setDeleteYnFlag("Y");
                }
				/*
				if(PmConstant.ADMIN_STAFF.equals(processor.getAuth()) || processor.getCurrentUserId().equals(item.getCreate_id())){
					item.setEditYnFlag("Y");
				}else{
					item.setEditYnFlag("N");
				}
				*/
			}
			workoutInfoList.setRecords(list);

			UIDataConvert<WorkOutInfo> convert = new UIDataConvert<WorkOutInfo>();
			convert.format(workoutInfoList);
			model.put("data", workoutInfoList);
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
	 * 查询详细信息
	 * @param WorkOutInfo
	 * @param processor
	 * @author SKCC
	 * @return
	 */
	@RequestMapping("/WorkOutInfoDetailEntity.json") 
	public @ResponseBody JSONMap<String,Object> workOutInfoDetailEntity(@FormModel WorkOutInfo WorkOutInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//根据计划id获得出勤实体
			WorkOutInfo workOutInfo = WorkoutInfoService.getWorkoutInfoById(WorkOutInfo.getWork_out_id());
			model.put("workOutInfo", workOutInfo);
			//根据计划id获得出勤实体
			List<WorkOutDetail> workOutDetailInfo = WorkoutInfoService.getWorkoutDetailInfoById(WorkOutInfo.getWork_out_id(),PmConstant.WORKOUT_ATTENDANCE);
			model.put("workOutDetailInfo", workOutDetailInfo);
		 
			List<WorkOutDetail> workOutDetailMachInfo = WorkoutInfoService.getWorkoutDetailInfoById(WorkOutInfo.getWork_out_id(),PmConstant.WORKOUT_MACHINE);
			model.put("workOutDetailMachInfo", workOutDetailMachInfo);
			
			List<RepairMaterialCostDetail> repairMaterialCostInfo = WorkoutInfoService.getRepairCostDetailInfoById(WorkOutInfo.getWork_out_id(),PmConstant.WORKOUT_MACHINE);
			model.put("repairMaterialCostInfo", repairMaterialCostInfo);
			
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
	 * 新加查询模板详细信息
	 * @param WorkOutInfo
	 * @param processor
	 * @author SKCC
	 * @return
	 */
	@RequestMapping("/WorkOutInfoDetailNewEntity.json")
	public @ResponseBody JSONMap<String,Object> workOutInfoDetailNewEntity(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//根据计划id获得出勤实体
			List<WorkOutDetail> workOutDetailInfo = WorkoutInfoService.getWorkoutDetailInfoById("0",PmConstant.WORKOUT_ATTENDANCE);
			model.put("workOutDetailInfo", workOutDetailInfo);
		 
			List<WorkOutDetail> workOutDetailMachInfo = WorkoutInfoService.getWorkoutDetailInfoById("0",PmConstant.WORKOUT_MACHINE);
			model.put("workOutDetailMachInfo", workOutDetailMachInfo);
			
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
	 * 修改项目管理信息
	 * @param ItemInfo
	 * @return
	 * @author SKCC
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel("workOutInfo") WorkOutInfo workOutInfo,@FormModel("workOutDetailInfo") List<WorkOutDetail> workOutDetail,@FormModel("workOutDetailMachInfo") List<WorkOutDetail> workOutDetailMach,@FormModel("rapairMaterialCostDetail") List<RepairMaterialCostDetail> rapairMaterialCostDetail ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			workOutInfo.setUpdate_id(processor.getCurrentUserId());
			workOutInfo.setUpdate_time(new Date());
			
			int saveInfo = WorkoutInfoService.updateWorkOutInfo(workOutInfo);

			if(saveInfo > 0){
				WorkoutInfoService.updateWorkOutDtlInfo(workOutDetail, processor);
				WorkoutInfoService.updateWorkOutDtlInfo(workOutDetailMach, processor);
				
				WorkoutInfoService.updateRepairCostDtlInfo(rapairMaterialCostDetail, processor,workOutInfo.getWork_out_id());
			}

			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	

	/**
	 * 修改项目管理信息
	 * @param workOutInfo
	 * @return
	 * @author SKCC
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel("workOutInfo") WorkOutInfo workOutInfo,@FormModel("workOutDetailInfo") List<WorkOutDetail> workOutDetail,@FormModel("rapairMaterialCostDetail") List<RepairMaterialCostDetail> rapairMaterialCostDetail,@FormModel("workOutDetailMachInfo") List<WorkOutDetail> workOutDetailMach,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			workOutInfo.setCreate_id(processor.getCurrentUserId());
			workOutInfo.setCreate_time(new Date());
			workOutInfo.setPay_yn("N");
			//System.out.println("name:"+workOutInfo.getWork_out_nm());
			//System.out.println("年月："+workOutInfo.getWork_out_ym());
	 
			int saveInfo = WorkoutInfoService.saveWorkOutInfo(workOutInfo);
			//System.out.println("saveInfo"+saveInfo);
			//System.out.println("workOUtid:"+workOutInfo.getWork_out_id());
			//System.out.println("getRemark："+rapairMaterialCostDetail.get(0).getRemark());
 
			if(saveInfo == 1){
	 
				int saveDetail = WorkoutInfoService.addWorkOutDtlInfo(workOutDetail, processor,workOutInfo.getWork_out_id());
				int saveDetailMach = WorkoutInfoService.addWorkOutDtlInfo(workOutDetailMach, processor,workOutInfo.getWork_out_id());
				WorkoutInfoService.addRepailMaterialCostDtlInfo(rapairMaterialCostDetail, processor, workOutInfo.getWork_out_id());
			}
			  
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 删除出勤统计表
	 * @param workOutInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel("workOutInfo") WorkOutInfo workOutInfo ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			//统计表主表信息删除
			WorkoutInfoService.deleteWorkOutInfo(workOutInfo);
			//统计表附表 信息删除
			WorkoutInfoService.deleteWorkOutDetailInfo(workOutInfo.getWork_out_id());
			//材料费用表内容删除
			WorkoutInfoService.deleteRepairCostDetailInfo(workOutInfo.getWork_out_id());

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
	 * 修改结算状态
	 * @param workOutInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateForPayYn.json")
	public @ResponseBody JSONMap<String,Object> updateForPayYn(@FormModel("workOutInfo") WorkOutInfo workOutInfo ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//System.out.println("id:"+workOutInfo.getWork_out_id());
			workOutInfo.setPay_yn("Y");
			workOutInfo.setUpdate_id(processor.getCurrentUserId());
			workOutInfo.setUpdate_time(new Date());
			workOutInfo.setPay_time(workOutInfo.getPay_time());
			WorkoutInfoService.updateWorkOutInfo(workOutInfo);
			
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
