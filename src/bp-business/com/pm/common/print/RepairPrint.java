package com.pm.common.print;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.repast.core.constant.PmConstant;

/**
 * 报表打印
 * @author 刘镝
 */
public class RepairPrint extends PrintBase {

	public RepairPrint(HttpServletRequest request) {
		super(request);
	}
	/**
	 * 打印
	 * 
	 * @param resultList 内容
	 * @param prePlanInfoList 计划名称
	 */
	public String mdPrint(Map<String, String> map审批流,Map<String, String> map维修单,List list报价内容,List list维修内容,List list报价预算,Double personmoney,Double materialmoney) {

		String outSheetName = "维修单报表";
		String filePath = this.getFilePath(outSheetName); 
		String outPutFilePath = this.getRealPath() + filePath;
		FileOutputStream fileOutput = null;
		try {
			//String outSheetName = "printSheet";
			POIFSFileSystem poiInputFile = new POIFSFileSystem(
					new FileInputStream(this.getTemPlatPath2()));
			HSSFWorkbook hssInputExcelFile = new HSSFWorkbook(poiInputFile);
			HSSFSheet hssInputExcelSheet = hssInputExcelFile.getSheetAt(0);
			HSSFSheet hssOutputExcelSheet = hssInputExcelFile
					.createSheet(outSheetName);
			String sheetName = hssInputExcelFile.getSheetName(0);

			int startRow = 0;
			HSSFRow hssDetailStartRow = hssInputExcelSheet.getRow(startRow + 3);
			int startCol = 0;
			int endCol = hssDetailStartRow.getLastCellNum() - 1;
			int readNos = 21;
			// 开始行get(0) 第一行
			// 开始列get(0) 第一列
			// 位置从0开始
			this.mdRangeCopy(sheetName, outSheetName, 0, readNos, startCol,
					endCol, 0, hssInputExcelFile);
			
			//审批流信息
			HSSFRow hssDetalSet = hssOutputExcelSheet.getRow(startRow + 4);
			hssDetalSet.getCell(1).setCellValue(map审批流.get("申请"));
			hssDetalSet.getCell(2).setCellValue(map审批流.get("初审"));
			hssDetalSet.getCell(3).setCellValue(map审批流.get("分配"));
			hssDetalSet.getCell(4).setCellValue(map审批流.get("报价"));
			hssDetalSet.getCell(5).setCellValue(map审批流.get("报价审批"));
			hssDetalSet.getCell(6).setCellValue(map审批流.get("维修"));
			hssDetalSet.getCell(7).setCellValue(map审批流.get("验证"));
			hssDetalSet.getCell(8).setCellValue(map审批流.get("Approval"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(startRow + 5);
			hssDetalSet.getCell(1).setCellValue(this.timeManage(map审批流.get("申请time")));
			hssDetalSet.getCell(2).setCellValue(this.timeManage(map审批流.get("初审time")));
			hssDetalSet.getCell(3).setCellValue(this.timeManage(map审批流.get("分配time")));
			hssDetalSet.getCell(4).setCellValue(this.timeManage(map审批流.get("报价time")));
			hssDetalSet.getCell(5).setCellValue(this.timeManage(map审批流.get("报价审批time")));
			hssDetalSet.getCell(6).setCellValue(this.timeManage(map审批流.get("维修time")));
			hssDetalSet.getCell(7).setCellValue(this.timeManage(map审批流.get("验证time")));
			hssDetalSet.getCell(8).setCellValue(this.timeManage(map审批流.get("Approvaltime")));			
			
			//维修单信息
			hssDetalSet = hssOutputExcelSheet.getRow(startRow);
			hssDetalSet.getCell(7).setCellValue(map维修单.get("申请编号"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(startRow + 7);
			hssDetalSet.getCell(2).setCellValue(map维修单.get("申请部门"));
			hssDetalSet.getCell(4).setCellValue(map维修单.get("维修地点"));
			hssDetalSet.getCell(7).setCellValue(this.timeManage(map维修单.get("计划完成日期")));
			
			//问题描述
			hssDetalSet = hssOutputExcelSheet.getRow(startRow + 9);
			hssDetalSet.getCell(2).setCellValue(map维修单.get("问题描述"));
			
			//备注
			hssDetalSet = hssOutputExcelSheet.getRow(startRow + 14);
			hssDetalSet.getCell(2).setCellValue(map维修单.get("备注"));
			
			
			//报价内容
			int readPlace = 21;
			// 金额
			DecimalFormat moneyFormat = new DecimalFormat("##0.00");
			
			Double moneyCount = Double.valueOf("0");

			//维修报价预算
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace-1);
			hssDetalSet.getCell(1).setCellValue("报价预算");
			for(int i = 0; i < list报价预算.size(); i++){
				readPlace++;
				Map<String, String> eozz报价预算 = (Map<String, String>) list报价预算	.get(i);
				// 列表
				this.mdRangeCopy(sheetName, outSheetName, readNos + 1,
						readNos + 1, startCol, endCol, readPlace,
						hssInputExcelFile);
				hssDetalSet = hssOutputExcelSheet.getRow(readPlace);

				if(null != eozz报价预算.get("报价种类") && !"".equals(eozz报价预算.get("报价种类")) && !"null".equals(eozz报价预算.get("报价种类"))){
					hssDetalSet.getCell(1).setCellValue(eozz报价预算.get("报价种类"));
					hssDetalSet.getCell(2).setCellValue(eozz报价预算.get("报价描述"));
					hssDetalSet.getCell(3).setCellValue(eozz报价预算.get("报价工时"));
					hssDetalSet.getCell(4).setCellValue(eozz报价预算.get("报价单价"));
					hssDetalSet.getCell(6).setCellValue(
							moneyFormat.format(this.tRound(eozz报价预算.get("报价工时")) * this.tRound(eozz报价预算.get("报价单价"))));
					moneyCount = moneyCount + this.tRound(eozz报价预算.get("报价工时")) * this.tRound(eozz报价预算.get("报价单价"));
				}
				
				//一次性费用 and 合计
				if(i == list报价预算.size() - 1){
					//税金
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 4,
							readNos + 4, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(2).setCellValue(moneyFormat.format(this.tRound(map维修单.get("税金"))));
					
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 5,
							readNos + 5, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(2).setCellValue(moneyFormat.format(this.tRound(map维修单.get("报价一次性费用"))));
					moneyCount = moneyCount + (this.tRound(map维修单.get("报价一次性费用")) + this.tRound(map维修单.get("税金")));
					
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 8,
							readNos + 8, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(3).setCellValue(moneyFormat.format(moneyCount));
				}
			}
			
			
			readPlace++;
			this.mdRangeCopy(sheetName, outSheetName, 31,
					31, startCol, endCol, readPlace,
					hssInputExcelFile);
			readPlace++;
			this.mdRangeCopy(sheetName, outSheetName, 31,
					31, startCol, endCol, readPlace,
					hssInputExcelFile);
			readPlace++;
			
			this.mdRangeCopy(sheetName, outSheetName, 20,
					21, startCol, endCol, readPlace,
					hssInputExcelFile);
			
			readPlace++;
			
			Double moneyCount2 = Double.valueOf("0");
			//维修报价list
			for(int i = 0; i < list报价内容.size(); i++){
				readPlace++;
				Map<String, String> eozz报价内容 = (Map<String, String>) list报价内容	.get(i);
				// 列表
				this.mdRangeCopy(sheetName, outSheetName, readNos + 1,
						readNos + 1, startCol, endCol, readPlace,
						hssInputExcelFile);
				hssDetalSet = hssOutputExcelSheet.getRow(readPlace);

				if(null != eozz报价内容.get("种类") && !"".equals(eozz报价内容.get("种类")) && !"null".equals(eozz报价内容.get("种类"))){
					hssDetalSet.getCell(1).setCellValue(eozz报价内容.get("种类"));
					hssDetalSet.getCell(2).setCellValue(eozz报价内容.get("描述"));
					hssDetalSet.getCell(3).setCellValue(eozz报价内容.get("工时"));
					hssDetalSet.getCell(4).setCellValue(eozz报价内容.get("单价"));
					hssDetalSet.getCell(6).setCellValue(
							moneyFormat.format(this.tRound(eozz报价内容.get("工时")) * this.tRound(eozz报价内容.get("单价"))));
					moneyCount2 = moneyCount2 + this.tRound(eozz报价内容.get("工时")) * this.tRound(eozz报价内容.get("单价"));
				}
				
				//一次性费用 and 合计
				if(i == list报价内容.size() - 1){

					//税金
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 4,
							readNos + 4, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(2).setCellValue(moneyFormat.format(this.tRound(map维修单.get("维修税金"))));
					
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 5,
							readNos + 5, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(2).setCellValue(moneyFormat.format(this.tRound(map维修单.get("一次性费用"))));
					moneyCount2 = moneyCount2 + (this.tRound(map维修单.get("一次性费用")) + this.tRound(map维修单.get("维修税金")));
					
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 6,
							readNos + 6, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(2).setCellValue(moneyFormat.format(personmoney));
					
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 7,
							readNos + 7, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(2).setCellValue(moneyFormat.format(materialmoney));
					
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, readNos + 8,
							readNos + 8, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					hssDetalSet.getCell(3).setCellValue(moneyFormat.format(moneyCount2));
				}
			}

			//报价结束 =》 维修开始
			this.mdRangeCopy(sheetName, outSheetName, readNos + 9, readNos + 12, startCol,
					endCol, readPlace + 1, hssInputExcelFile);
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 3);
			hssDetalSet.getCell(2).setCellValue(map维修单.get("维修结果"));
			
			readPlace = readPlace + 4;
			//维修内容
			if(list维修内容.size() == 0){
				readPlace++;
				this.mdRangeCopy(sheetName, outSheetName, readNos + 13,
						readNos + 13, startCol, endCol, readPlace,
						hssInputExcelFile);
			}else{
				for(int i = 0; i < list维修内容.size(); i++){
					readPlace++;
					Map<String, String> eozz维修内容 = (Map<String, String>) list维修内容	.get(i);
					// 列表
					this.mdRangeCopy(sheetName, outSheetName, readNos + 13,
							readNos + 13, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					
					hssDetalSet.getCell(1).setCellValue(i + 1 + "");
					hssDetalSet.getCell(2).setCellValue(eozz维修内容.get("设备名称"));
					hssDetalSet.getCell(3).setCellValue(eozz维修内容.get("停机时间"));
					hssDetalSet.getCell(4).setCellValue(eozz维修内容.get("维修内容"));
				}
			}
			
			//维修内容结束=》行尾
			this.mdRangeCopy(sheetName, outSheetName, readNos + 18, readNos + 20, startCol,
					endCol, readPlace + 1, hssInputExcelFile);
			
//			//copy 2
//			this.mdRangeCopy(outSheetName, outSheetName, 0, readPlace + 3, startCol,
//					endCol, readPlace + 4, hssInputExcelFile);
//			
//			//copy 3
//			this.mdRangeCopy(outSheetName, outSheetName, 0, readPlace + 3, startCol,
//					endCol, ((readPlace + 3)*2)+2, hssInputExcelFile);
			
//			//页号设定
//			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 2);
//			hssDetalSet.getCell(7).setCellValue("print 1:");
//			hssDetalSet = hssOutputExcelSheet.getRow((readPlace + 2)*2 + 1 + 1);
//			hssDetalSet.getCell(7).setCellValue("print 2:");
//			hssDetalSet = hssOutputExcelSheet.getRow((readPlace + 2)*3 + 2 + 2);
//			hssDetalSet.getCell(7).setCellValue("print 3:");
			
			//分页设置
//			hssOutputExcelSheet.setRowBreak(readPlace + 3);
//			hssOutputExcelSheet.setRowBreak((readPlace + 3)*2+1);
			
			// 图片设置
			this.picSet2(hssInputExcelFile, hssOutputExcelSheet,0);
//			this.picSet2(hssInputExcelFile, hssOutputExcelSheet,readPlace + 3 + 1);
//			this.picSet2(hssInputExcelFile, hssOutputExcelSheet,(readPlace + 3)*2 + 1 +1);

			// 打印设置
			this.printSet2(hssInputExcelSheet, hssOutputExcelSheet);
			
			//hssInputExcelFile.setPrintArea(1, 0, endCol, 0, 79);
			// 打印文件生成
			fileOutput = new FileOutputStream(outPutFilePath);
			hssInputExcelFile.removeSheetAt(0);
			hssInputExcelFile.setSheetName(0, sheetName);

			hssOutputExcelSheet.setColumnBreak(endCol);
			//hssInputExcelFile.setPrintArea(0, 0, endCol, 0, ((readPlace + 3)*3)+2);
			hssInputExcelFile.setPrintArea(0, 0, endCol, 0, readPlace + 3);
			hssInputExcelFile.writeProtectWorkbook(PmConstant.encrypt_password, PmConstant.encrypt_account);
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
