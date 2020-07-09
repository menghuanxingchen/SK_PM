package com.pm.business.emergentRepair.controller;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.pm.business.emergentRepair.service.EmergentRepairInfoService;
import com.pm.business.machinemanager.service.MachinePotPartInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.emergentRepair.entity.EmergentRepairInfo;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;
import com.repast.core.util.DateUtil;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 表emergent_repair_info的Controller类
 * @author 刘镝
 */
@Controller
@RequestMapping("/EmergentRepairInfoController")
public class EmergentRepairInfoController {

    @Resource
    private EmergentRepairInfoService emergentRepairInfoService;
    @Resource
    private SysCodeInfoService sysCodeInfoService;
    @Resource
    private MachinePotPartInfoService machinePotPartInfoService;

    @RequestMapping("/defaultJsp")
    public String tablemanagePage() {
        return "web/emergentrepair/emergent_repair_info_list";
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
            entity.setCode_group_value(PmConstant.JOB_TYPE);
            List<SysCodeInfo> selectList = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(entity);
            model.put("selectList", selectList);
            entity.setCode_group_value(PmConstant.COMPONTNTS);
            List<SysCodeInfo> potSonPartList = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(entity);
            model.put("potSonPartList", potSonPartList);

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
     * 子部门下拉列表查询联动
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/getSonPotList.json")
    public @ResponseBody JSONMap<String,Object> getSonPotList(@FormModel("subGroupCode") String subGroupCode,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            SysCodeInfo entity =new SysCodeInfo();
            
            entity.setSub_code_group_value(subGroupCode);
            List<SysCodeInfo> potSonPartList = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(entity);
            model.put("potSonPartList", potSonPartList);

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
     * @param emergentRepairInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryDataList.json")
    public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            //列表查询（分页）
        	if("Mobile".equals(processor.getDeviceType())) {
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_content())){
        			emergentRepairInfo.setRepair_content(URLDecoder.decode(emergentRepairInfo.getRepair_content(), "UTF-8"));
        			}
        	}
            Pagination<EmergentRepairInfo> dataList = emergentRepairInfoService.queryEmergentRepairInfoList2(emergentRepairInfo, processor.getPageIndex(), processor.getPageSize());

            //列表中code转化name
            UIDataConvert<EmergentRepairInfo> convert = new UIDataConvert<EmergentRepairInfo>();
            IConvert position_nm = new SqlMapConvertImpl("job_type", "job_type_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.JOB_TYPE+"' ");
            convert.addConvert(position_nm);
            position_nm = new SqlMapConvertImpl("pot_id", "pot_id_nm", "machine_pot_info", "pot_nm","pot_id", "");
            convert.addConvert(position_nm);
            position_nm = new SqlMapConvertImpl("pot_part_id", "pot_part_id_nm", "machine_pot_part_info", "pot_part_nm","pot_part_id", "");
            convert.addConvert(position_nm);
            position_nm = new SqlMapConvertImpl("pot_son_part_id", "pot_son_part_id_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.COMPONTNTS+"' ");
            convert.addConvert(position_nm);
            IConvert create_name = new SqlMapConvertImpl("create_id", "create_name", "sys_user_info", "user_nm","user_num", "");
			convert.addConvert(create_name);
            convert.format(dataList);

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
     * @param emergentRepairInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/queryBeforeUpdate.json")
    public @ResponseBody JSONMap<String,Object> queryBeforeUpdate(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            EmergentRepairInfo dataInfo = emergentRepairInfoService.getEmergentRepairInfoById(emergentRepairInfo.getEmergent_id());
            UIDataConvert<EmergentRepairInfo> convert = new UIDataConvert<EmergentRepairInfo>();
            IConvert position_nm = new SqlMapConvertImpl("job_type", "job_type_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.JOB_TYPE+"' ");
            convert.addConvert(position_nm);
            position_nm = new SqlMapConvertImpl("pot_id", "pot_id_nm", "machine_pot_info", "pot_nm","pot_id", "");
            convert.addConvert(position_nm);
            position_nm = new SqlMapConvertImpl("pot_part_id", "pot_part_id_nm", "machine_pot_part_info", "pot_part_nm","pot_part_id", "");
            convert.addConvert(position_nm);
            position_nm = new SqlMapConvertImpl("pot_son_part_id", "pot_son_part_id_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.COMPONTNTS+"' ");
            convert.addConvert(position_nm);
            convert.format(dataInfo);
            model.put("dataInfo", dataInfo);
            SysCodeInfo entity =new SysCodeInfo();
            entity.setCode_group_value(PmConstant.JOB_TYPE);
            List<SysCodeInfo> selectList = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(entity);
            model.put("selectList", selectList);
            entity.setCode_group_value(null);
            entity.setSub_code_group_value(dataInfo.getPot_part_id());
            List<SysCodeInfo> potSonPartList = sysCodeInfoService.querySysCodeInfoListNoPageWithoutCode(entity);
            model.put("potSonPartList", potSonPartList);
            
            
            
            MachinePotPartInfo mppInfo = new MachinePotPartInfo();
            mppInfo.setPot_id(dataInfo.getPot_id());
            List<MachinePotPartInfo> dataList =   machinePotPartInfoService.queryMachinePotPartInfoEmergentList(mppInfo);
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
     * 保存信息
     * @param emergentRepairInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/addEntityData.json")
    public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	emergentRepairInfo.setCreate_id(processor.getCurrentUserId());
        	if("Mobile".equals(processor.getDeviceType())) {
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_content())){
        			emergentRepairInfo.setRepair_content(URLDecoder.decode(emergentRepairInfo.getRepair_content(), "UTF-8"));
        			}
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_person())){
        			emergentRepairInfo.setRepair_person(URLDecoder.decode(emergentRepairInfo.getRepair_person(), "UTF-8"));
        			}
        	}
            emergentRepairInfoService.insertEmergentRepairInfo(emergentRepairInfo);
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
     * @param emergentRepairInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updEntityData.json")
    public @ResponseBody JSONMap<String,Object> updEntityData(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	if("Mobile".equals(processor.getDeviceType())) {
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_content())){
        			emergentRepairInfo.setRepair_content(URLDecoder.decode(emergentRepairInfo.getRepair_content(), "UTF-8"));
        			}
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_person())){
        			emergentRepairInfo.setRepair_person(URLDecoder.decode(emergentRepairInfo.getRepair_person(), "UTF-8"));
        			}
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_remark())){
        			emergentRepairInfo.setRepair_remark(URLDecoder.decode(emergentRepairInfo.getRepair_remark(), "UTF-8"));
        			}
        	}
            emergentRepairInfoService.updEmergentRepairInfo(emergentRepairInfo);
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
     * 维修反馈
     * @param emergentRepairInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/updEntityDataFeedback.json")
    public @ResponseBody JSONMap<String,Object> updEntityDataFeedback(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
        	if("Mobile".equals(processor.getDeviceType())) {
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_content())){
        			emergentRepairInfo.setRepair_content(URLDecoder.decode(emergentRepairInfo.getRepair_content(), "UTF-8"));
        			}
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_person())){
        			emergentRepairInfo.setRepair_person(URLDecoder.decode(emergentRepairInfo.getRepair_person(), "UTF-8"));
        			}
        		if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getRepair_remark())){
        			emergentRepairInfo.setRepair_remark(URLDecoder.decode(emergentRepairInfo.getRepair_remark(), "UTF-8"));
        			}
        	}
        	if(StringUtils.isNullOrEmpty(emergentRepairInfo.getSqm_relieve_yn())&&StringUtils.isNullOrEmpty(emergentRepairInfo.getEnd_time())) {
        		emergentRepairInfo.setEnd_time(DateUtil.getNow(DateUtil.Y_M_D_HM));
        	}
        	if(!StringUtils.isNullOrEmpty(emergentRepairInfo.getStart_time())&&!StringUtils.isNullOrEmpty(emergentRepairInfo.getEnd_time())) {
        		long differenceMin = getTimeDifferenceMin(emergentRepairInfo.getStart_time(),emergentRepairInfo.getEnd_time());
        		emergentRepairInfo.setTotal_time(differenceMin+"");
        		emergentRepairInfo.setEmergent_hours(differenceMin+"");
        	}
            emergentRepairInfoService.updEmergentRepairInfo(emergentRepairInfo);
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
     * @param emergentRepairInfo
     * @param processor
     * @author 刘镝
     */
    @RequestMapping("/cancelEntityData.json")
    public @ResponseBody JSONMap<String,Object> cancelEntityData(@FormModel EmergentRepairInfo emergentRepairInfo,@FormModel Processor processor) {
        JSONMap<String,Object> model = new JSONMap<String,Object>();
        try{
            emergentRepairInfoService.cancelEmergentRepairInfo(emergentRepairInfo);
            model.put(SysConstant.OP_FLAG, true);
            model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
        }catch(Exception e){
            model.put(SysConstant.OP_FLAG, false);
            model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
            System.out.println(e.getMessage());
        }
        return model;
    }
    public static long getTimeDifferenceMin(String startT,String endT) {
    	SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
    	Date begin = null;
    	Date end = null;
		try {
			begin = dfs.parse(startT);
			end = dfs.parse(endT);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒 
    	long min=between/60;
    	return min;
	}
}

