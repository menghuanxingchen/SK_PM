package com.pm.business.machinemanager.controller;

import java.net.URLDecoder;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.pm.business.machinemanager.service.MachinePotInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.machinemanager.entity.MachinePotInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;

/**
 * 表machine_pot_info的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/MachinePotInfoController")
public class MachinePotInfoController {

    @Resource
    private MachinePotInfoService machinePotInfoService;
    @Resource
    private SysCodeInfoService sysCodeInfoService;

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
        return "web/basicinfo/machinemanager/machine_pot_info_list";
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
     * @param machinePotInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel MachinePotInfo machinePotInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
        	if("Mobile".equals(processor.getDeviceType())) {
        		if(!StringUtils.isNullOrEmpty(machinePotInfo.getPot_nm())){
        			machinePotInfo.setPot_nm(URLDecoder.decode(machinePotInfo.getPot_nm(), "UTF-8"));
        			}
        	}
            Pagination<MachinePotInfo> dataList = machinePotInfoService.queryMachinePotInfoList(machinePotInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<MachinePotInfo> convert = new UIDataConvert<MachinePotInfo>();
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
     * @param machinePotInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel MachinePotInfo machinePotInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            MachinePotInfo dataInfo = machinePotInfoService.getMachinePotInfoById(machinePotInfo.getPot_id());
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
     * @param machinePotInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel MachinePotInfo machinePotInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            machinePotInfoService.insertMachinePotInfo(machinePotInfo,processor);
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
     * @param machinePotInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel MachinePotInfo machinePotInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            machinePotInfoService.updMachinePotInfo(machinePotInfo,processor);
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
     * @param machinePotInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel MachinePotInfo machinePotInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            machinePotInfoService.cancelMachinePotInfo(machinePotInfo);
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
     * @param machinePotInfo
     * @param processor
     * @author 姜易平
     */
    @RequestMapping("/docheckDB.json")
    public @ResponseBody JSONMap<String,Object> docheckDB(@FormModel MachinePotInfo machinePotInfo,@FormModel Processor processor) {
    	JSONMap<String,Object> model = new JSONMap<String,Object>();
    	try{
    		model.put(SysConstant.OP_FLAG, machinePotInfoService.dbExistCheck(machinePotInfo));
    		model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
    	}catch(Exception e){
    		model.put(SysConstant.OP_FLAG, false);
    		model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
    		System.out.println(e.getMessage());
    	}
    	return model;
    }
}

