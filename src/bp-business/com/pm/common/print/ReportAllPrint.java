package com.pm.common.print;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.Region;

import com.pm.persistence.inspectplan.entity.InsPlanInfo;
import com.pm.persistence.preventplan.entity.PrePlanInfo;
import com.pm.persistence.report.dto.ReportAllDto;

/**
 * 报表打印
 * @author 刘镝
 */
public class ReportAllPrint extends PrintBase {
	
	public ReportAllPrint(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 打印
	 * 
	 * @param resultList 内容
	 * @param reportPorpList 预见性维护（没有则null）
	 * @param planInfoList 计划名称
	 */
	public String mdPrint(List resultList,List reportPorpList,List planInfoList,ReportAllDto report) {

		if (resultList.size() == 0) {
			return "";
		}
		
		String outSheetName = report.getMachineTypeNm() + report.getPlanTypeNm() + REPORTNAME;
		String filePath = this.getFilePath(outSheetName); 
		String outPutFilePath = this.getRealPath() + filePath;
		FileOutputStream fileOutput = null;
		try {
			//String outSheetName = "printSheet";
			POIFSFileSystem poiInputFile = new POIFSFileSystem(
					new FileInputStream(this.getTemPlatPath()));
			HSSFWorkbook hssInputExcelFile = new HSSFWorkbook(poiInputFile);
			HSSFSheet hssInputExcelSheet = hssInputExcelFile.getSheetAt(0);
			HSSFSheet hssOutputExcelSheet = hssInputExcelFile
					.createSheet(outSheetName);
			String sheetName = hssInputExcelFile.getSheetName(0);

			//业务处理开始
			tableRowBase = new HashMap<String,String>();
			tableColBase = new HashMap<String,String>();
			
			//最大列
			int maxCol = 0;
			//结束列
			int endCol = 0;
			//位置
			int position = 2;
			
			for(int i = 0; i < resultList.size(); i++){
				position = position + 2;
				List<String[]> printList = this.listNoAdd((List<String[]>) resultList.get(i),1);
				List<String[]> printreportPorpList = null;
				try{
					printreportPorpList = this.listNoAdd((List<String[]>)reportPorpList.get(i),2);
				}catch(Exception e){
					printreportPorpList = null;
				}

				for(int j = 0; j < printList.size(); j++){
					String[] resultRow = printList.get(j);
					endCol = resultRow.length;
					maxCol = maxCol < endCol?endCol:maxCol;
					
					if(j == 0){
						//标题设定
						this.mdRangeCopy(sheetName, outSheetName, 6, 6, 0,
								endCol-1, position-1, hssInputExcelFile);
						this.mdRangeCopy(sheetName, outSheetName, 10, 10, 0,
								maxCol-1, position-2, hssInputExcelFile);
						//单元格合并 ：四个参数分别是：起始行，起始列，结束行，结束列   
						hssOutputExcelSheet.addMergedRegion(new Region((short)(position-1), (short)0, (short)(position-1),
								(short)(endCol-1)));
						
						HSSFRow hssDetalSet = hssOutputExcelSheet.getRow(position-1);
						try{
							if(planInfoList.get(i) instanceof PrePlanInfo){
								PrePlanInfo preEo = (PrePlanInfo)planInfoList.get(i);
								String titleVal = preEo.getPre_plan_nm() + " ( " + preEo.getPre_plan_date() + "  1次/" + preEo.getPre_plan_cycle_nm() + ")";
							    //标题赋值
							    hssDetalSet.getCell(0).setCellValue(titleVal);
							}
							else if(planInfoList.get(i) instanceof InsPlanInfo){
								InsPlanInfo preEo = (InsPlanInfo)planInfoList.get(i);
								String titleVal = preEo.getIns_plan_nm() + " ( " + preEo.getIns_plan_date() + "  1次/" + preEo.getIns_plan_cycle_name() + ")";
							    //标题赋值
							    hssDetalSet.getCell(0).setCellValue(titleVal);
							}
							else{
								String titleVal = "无计划名" + " (1次/" + "无周期" + ")";
								//标题赋值
							    hssDetalSet.getCell(0).setCellValue(titleVal);
							}
						}catch(Exception e){
							String titleVal = "计划名" + " (1次/" + "周期名" + ")";
							//标题赋值
						    hssDetalSet.getCell(0).setCellValue(titleVal);
						}
					}
					
					// 模板copy
					// 开始行get(0) 第一行
					// 开始列get(0) 第一列
					// 位置从0开始
					if(j == 0){
						this.mdRangeCopy(sheetName, outSheetName, 1, 1, 0,
								endCol-1, position, hssInputExcelFile);
					}else{
						this.mdRangeCopy(sheetName, outSheetName, 4, 4, 0,
								endCol-1, position, hssInputExcelFile);	
					}
					//遍历列
					for(int k = 0; k < endCol; k++){
						//单元格内容
						String val = resultRow[k];
						//单元格赋值
						this.getStringHandle(val,hssOutputExcelSheet,position,k);
					}
					
					position = position + 1;
				}
				
				//存在预见性维护的场合
				if(null != printreportPorpList){
					for(int j = 0; j < printreportPorpList.size(); j++){
						String[] resultRow = printreportPorpList.get(j);
						//endCol = resultRow.length;
						
						// 模板copy
						this.mdRangeCopy(sheetName, outSheetName, 4, 4, 0,
									endCol-1, position, hssInputExcelFile);	
						//遍历列
						for(int k = 0; k < endCol; k++){
							try{
								//单元格内容
								String val = resultRow[k];
								//单元格赋值
								this.getStringHandle(val,hssOutputExcelSheet,position,k);
							}catch(Exception e){
								HSSFRow hssDetalSet = hssOutputExcelSheet.getRow(position);
								hssDetalSet.getCell(k).setCellValue("");
							}
						}
						
						position = position + 1;
					}
				}
				
				
			}
			
			
			//header设定
			this.mdRangeCopy(sheetName, outSheetName, 13, 13, 0,
					maxCol-1, 0, hssInputExcelFile);
			this.mdRangeCopy(sheetName, outSheetName, 8, 8, 0,
					maxCol-1, 1, hssInputExcelFile);
			//header单元格合并 ：四个参数分别是：起始行，起始列，结束行，结束列   
			hssOutputExcelSheet.addMergedRegion(new Region((short)(0), (short)(0), (short)(0),
					(short)(maxCol-1)));
			hssOutputExcelSheet.addMergedRegion(new Region((short)(1), (short)(0), (short)(1),
					(short)(maxCol-1)));
			//PM完成率
			HSSFRow hssDetalSet = hssOutputExcelSheet.getRow(0);
			hssDetalSet.getCell(0).setCellValue("PM完成率 ：" + report.getPm_rate() + "   ");
		    //header赋值
			hssDetalSet = hssOutputExcelSheet.getRow(1);
			String headerVal = report.getMachineTypeNm() + report.getPlanTypeNm() + "计划 ( " + 
		    report.getStart_date().replace("-","") + " ~ " + report.getEnd_date().replace("-","") + " )";
		    hssDetalSet.getCell(0).setCellValue(headerVal);
		    
		    //sheet名设置
			Date d = new Date();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
			sheetName = outSheetName + sdf.format(d);
		    
			// 图片设置
			this.picSet(hssInputExcelFile, hssOutputExcelSheet);
			
			// 打印设置
			this.printSet(hssInputExcelSheet, hssOutputExcelSheet);

			// 打印文件生成
			fileOutput = new FileOutputStream(outPutFilePath);
			hssInputExcelFile.removeSheetAt(0);
			hssInputExcelFile.setSheetName(0, sheetName);
			// hssOutputExcelSheet.removeRowBreak(37);
			hssInputExcelFile.setPrintArea(0, 0, maxCol-1, 0, position - 1);
			hssInputExcelFile.write(fileOutput);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileOutput != null) {
				try {
					fileOutput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// 打印执行
		//this.print(outPutFilePath);
		
		//刪除文件
		//this.deleteFile(outPutFilePath);
		
		return filePath;
	}
}
