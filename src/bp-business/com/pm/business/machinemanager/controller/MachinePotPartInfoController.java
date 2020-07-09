package com.pm.business.machinemanager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.pm.business.machinemanager.service.MachinePotPartInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.machinemanager.entity.MachinePotInfo;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.UIDataConvert;

/**
 * 表machine_pot_part_info的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/MachinePotPartInfoController")
public class MachinePotPartInfoController {

    @Resource
    private MachinePotPartInfoService machinePotPartInfoService;
    @Resource
    private SysCodeInfoService sysCodeInfoService;

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
        return "web/basicinfo/machinemanager/machine_pot_part_info_list";
    }
    
    @RequestMapping("/selectPotPop")
    public String selectPotPop() {
    	return "web/basicinfo/machinemanager/machine_pot_add_POP";
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
     * @param machinePotPartInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
            Pagination<MachinePotPartInfo> dataList = machinePotPartInfoService.queryMachinePotPartInfoList(machinePotPartInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<MachinePotPartInfo> convert = new UIDataConvert<MachinePotPartInfo>();
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
     * 根据设备ID查询所属部件列表
     * @param machinePotPartInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/queryDataListByPotId.json")
    public @ResponseBody JSONMap<String,Object> queryDataListByPotId(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
    	JSONMap<String,Object> model = new JSONMap<String,Object>();
    	try{
    		//部件列表
    		List<MachinePotPartInfo> dataList = machinePotPartInfoService.queryMachinePotPartInfoEmergentList(machinePotPartInfo);
    		
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
     * @param machinePotPartInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            MachinePotPartInfo dataInfo = machinePotPartInfoService.getMachinePotPartInfoById(machinePotPartInfo.getPot_part_id());
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
     * @param machinePotPartInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            machinePotPartInfoService.insertMachinePotPartInfo(machinePotPartInfo,processor);
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
     * @param machinePotPartInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            machinePotPartInfoService.updMachinePotPartInfo(machinePotPartInfo,processor);
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
     * @param machinePotPartInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            machinePotPartInfoService.cancelMachinePotPartInfo(machinePotPartInfo);
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
     * DB检查是否有重复
     * @param machinePotPartInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/docheckDB.json")
    public @ResponseBody JSONMap<String,Object> docheckDB(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
    	JSONMap<String,Object> model = new JSONMap<String,Object>();
    	try{
    		model.put(SysConstant.OP_FLAG, machinePotPartInfoService.dbExistCheck(machinePotPartInfo));
    		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
    	}catch(Exception e){
    		model.put(SysConstant.OP_FLAG, false);
    		model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
    		System.out.println(e.getMessage());
    	}
    	return model;
    }
}

