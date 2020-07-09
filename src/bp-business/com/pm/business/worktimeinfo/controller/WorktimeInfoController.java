package com.pm.business.worktimeinfo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.worktimeinfo.service.WorktimeInfoService;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.pm.persistence.worktimeinfo.entity.WorktimeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.util.DateUtil;

/**
 * 表worktime_info的Controller类
 * @author 刘镝
 */
@Controller
@Component("taskJob")
@RequestMapping("/WorktimeInfoController")
public class WorktimeInfoController {

    @Resource
    private WorktimeInfoService worktimeInfoService;
    @Resource
    private SysCodeInfoService sysCodeInfoService;

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
        return "web/workhour/worktime_info_list";
    }

    /**
     * 下拉列表查询
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/beforeDataList.json")
    public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            SysCodeInfo entity =new SysCodeInfo();
            //entity.setCode_group_value(BusConstant.SELECTID);
            //List<SysCodeInfo> selectList = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(entity);
            //model.put("selectList", selectList);

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
     * 信息查询
     * @param worktimeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel WorktimeInfo worktimeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
            Pagination<WorktimeInfo> dataList = worktimeInfoService.queryWorktimeInfoList(worktimeInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<WorktimeInfo> convert = new UIDataConvert<WorktimeInfo>();
            //IConvert position_nm = new SqlMapConvertImpl("entity_select_id", "entity_select_name", "sys_code_info", "code_nm","code_value", " and code_group_value='"+BusConstant.SELECTID+"' ");
            //convert.addConvert(position_nm);
            //convert.format(dataList);

            model.put("data", dataList);
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
     * 修改、更新前数据取得
     * @param worktimeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel WorktimeInfo worktimeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            WorktimeInfo dataInfo = worktimeInfoService.getWorktimeInfoById(worktimeInfo.getWorkhour_id());
            model.put("dataInfo", dataInfo);

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
     * 保存信息
     * @param worktimeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel WorktimeInfo worktimeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            worktimeInfoService.insertWorktimeInfo(worktimeInfo);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }

    /**
     * 修改信息
     * @param worktimeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel("worktimeInfo") List<WorktimeInfo> worktimeInfoList,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            worktimeInfoService.delWorktimeInfoList(worktimeInfoList);
            for(WorktimeInfo e:worktimeInfoList){
            	e.setUpdate_id(processor.getCurrentUserId());
            	e.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
            }
            worktimeInfoService.saveWorktimeInfoList(worktimeInfoList);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }

    /**
     * 作废信息
     * @param worktimeInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel WorktimeInfo worktimeInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            worktimeInfoService.cancelWorktimeInfo(worktimeInfo);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }
    @Scheduled(cron="0 10 0 1 * ? ") 
//    @Scheduled(cron="45 * * * * ? ")
    public void timeadd(){
    	 try {
    		 System.out.println("开始新增");
	    	
	    	List<WorktimeInfo> worktimeInfoList = new ArrayList<WorktimeInfo>();
	    	String[] week={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
			Calendar date = Calendar.getInstance();
	    	String yearNow = String.valueOf(date.get(Calendar.YEAR));
	    	String monthNow = String.valueOf(date.get(Calendar.MONTH));
	        int year = Integer.parseInt(yearNow);
	        int month = Integer.parseInt(monthNow)+1;
	        if(month==12){
	        	year++;
	        	month=0;
	        }
	        int monthshow = month+1;
	        if(monthshow==13){
	        	monthshow=1;
	        }
	    	 Calendar c = Calendar.getInstance(); //获取Calendar实例
	         c.set(Calendar.YEAR, year); //设置年
	         c.set(Calendar.MONTH, month); //设置月
	         c.set(Calendar.DAY_OF_MONTH, 1); //设置月开始第一天日期
	         int end = c.getActualMaximum(Calendar.DAY_OF_MONTH); //获得月末日期
	         for (int i=1; i<=end; i++) {
	        	 WorktimeInfo worktimeInfo = new WorktimeInfo();
	        	 if(monthshow<10){
	        		 worktimeInfo.setDate_group(year+"-0"+monthshow);
	        	 }else{
	        		 worktimeInfo.setDate_group(year+"-"+monthshow);
	        	 }
	        	 worktimeInfo.setDays(String.valueOf(c.get(Calendar.DATE)));
	        	 worktimeInfo.setWeek(String.valueOf(week[c.get(Calendar.DAY_OF_WEEK)-1]));
	        	 worktimeInfo.setWork_hour("8");
	        	 worktimeInfo.setUpdate_id("sys");
				 worktimeInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
				 worktimeInfoList.add(worktimeInfo);
	             c.add(Calendar.DAY_OF_MONTH, 1);
	         }
	         worktimeInfoService.saveWorktimeInfoList(worktimeInfoList);
	         System.out.println("新增完成");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 
        
    }
}

