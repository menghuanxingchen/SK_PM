package com.repast.core.codelist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.Option;
import com.repast.core.uiview.Select;


@Controller
@RequestMapping("/transcodeController")
public class TranscodeController {
	
	@Resource
	private CodeListService codeListService;
	
	/**
	 * 转换codelist数据
	 * @param kind
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("transcodeJson.json")
	public String transcodeJson(@RequestParam String kind,Model model,HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		Map<String, Map<String, String>> codelistjson = CodelistManager.getInstance().getCodeList(kind);
		model.addAttribute("codeData",codelistjson);
		return "jsonView";
	}
	
	/**
	 * 转换codelist
	 * @param kind
	 * @return
	 */
	@RequestMapping("selectTable.json")
	public @ResponseBody JSONMap<String,Object> selectTable(@RequestParam String kind) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
		//	List<Option> options = uiService.queryParameterListByTable(selectConfig);
		//	Select<Option> select = new Select<Option>();
		//	select.setOptions(options);
			
			Map<String, Map<String, String>> codelistjson = CodelistManager.getInstance().getCodeList(kind);
			
			if(codelistjson.containsKey(kind)){
				List<Option> options = new ArrayList();
				Map<String, String> valMap = codelistjson.get(kind);
				for(String key:valMap.keySet()){
					Option option = new Option();
					option.setText(valMap.get(key));
					option.setValue(key);;
					options.add(option);
				}
				model.put(SysConstant.OP_FLAG, true);
				Select<Option> select = new Select<Option>();
				select.setOptions(options);
				model.put("data", select);
			}else{
				model.put(SysConstant.OP_FLAG,false);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	
	
	@RequestMapping("batchSelectTable.json")
	public @ResponseBody JSONMap<String,Object> batchSelectTable(@RequestParam String kinds,@RequestParam String objIDs) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{

			String[] kindsArray = kinds.split(",");
			String[] objIDsArray = objIDs.split(",");
			
			for(int i=0;i<kindsArray.length;i++)
			{
				Select<Option> select = new Select<Option>();
				String kind = kindsArray[i];
				Map<String, Map<String, String>> codelistjson = CodelistManager.getInstance().getCodeList(kind);
				if(codelistjson.containsKey(kind)){
					List<Option> options = new ArrayList<Option>();
					Map<String, String> valMap = codelistjson.get(kind);
					for(String key:valMap.keySet()){
						Option option = new Option();
						option.setText(valMap.get(key));
						option.setValue(key);;
						options.add(option);
					}
					select.setOptions(options);
					model.put(objIDsArray[i], select);
				}else{
					model.put(objIDsArray[i], select);
				}
			}
				model.put(SysConstant.OP_FLAG, true);
				
			
			
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	
	/**
	 * 转身到代码表管理的列表页面
	 * @return
	 */
	@RequestMapping("/forwardSysCodeList")
	public String forwardSysLogOperationList() 
	{
		return "/sys/syscodelist/syscodelist_list";
	}
	
	/**
	 * 查询codelist中的数据,按codekind进行分组
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySysCodeList.json")
	public @ResponseBody JSONMap<String,Object> querySysCodeList(@FormModel SysCodelist sysCodelist,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<SysCodelist> list = codeListService.querySysCodeList(sysCodelist, processor.getPageIndex(),processor.getPageSize());
			model.put("data", list);
			model.put(SysConstant.OP_FLAG, true);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 查询指定codekind下的数据
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySysCodeListByCodeKind.json")
	public @ResponseBody JSONMap<String,Object> querySysCodeListByCodeKind(@FormModel SysCodelist sysCodelist,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<SysCodelist> list = codeListService.querySysCodeListByCodeKind(sysCodelist, processor.getPageIndex(),processor.getPageSize());
			model.put("data", list);
			model.put(SysConstant.OP_FLAG, true);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 保存codelist表中的数据
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	@RequestMapping("/saveSysCodeListByCode.json")
	public @ResponseBody JSONMap<String,Object> saveSysCodeListByCode(@FormModel SysCodelist sysCodelist,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//判断数据是否重复
			int checkCnt = codeListService.checkSysCodeListByCodeExists(sysCodelist);
			if(checkCnt>0)
			{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, "无法保存，代码已存在，请检查数据！");
			}else{
				codeListService.saveSysCodeListByCode(sysCodelist, processor);
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 读取指定id的codelist对象
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	@RequestMapping("/loadSysCodeListById.json")
	public @ResponseBody JSONMap<String,Object> loadSysCodeListById(@FormModel SysCodelist sysCodelist,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			    sysCodelist = codeListService.loadSysCodeListById(sysCodelist, processor);
				model.put("sysCodelist", sysCodelist);
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
	 * 保存codelist对象
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	@RequestMapping("/saveUpdateSysCodeListById.json")
	public @ResponseBody JSONMap<String,Object> saveUpdateSysCodeListById(@FormModel SysCodelist sysCodelist,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			    sysCodelist = codeListService.saveUpdateSysCodeListById(sysCodelist, processor);
			    
				model.put("sysCodelist", sysCodelist);
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
	 * 删除指定id的codelist对象
	 * @param sysCodelist
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteSysCodeListById.json")
	public @ResponseBody JSONMap<String,Object> deleteSysCodeListById(@FormModel SysCodelist sysCodelist,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			    codeListService.deleteSysCodeListById(sysCodelist, processor);
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
