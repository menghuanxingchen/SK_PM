package com.pm.common.print;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class ProposalPrint extends PrintBase {

	public ProposalPrint(HttpServletRequest request) {
		super(request);
	}
	/**
	 * 打印
	 * 
	 * @param resultList 内容
	 * @param prePlanInfoList 计划名称
	 */
	public String mdPrint(Map<String, String> map审批流,Map<String, String> map提案书,Map<String, String> map实施结果) {

		String outSheetName = "提案书报表";
		String filePath = this.getFilePath(outSheetName); 
		String outPutFilePath = this.getRealPath() + filePath;
		FileOutputStream fileOutput = null;
		try {
			//String outSheetName = "printSheet";
			POIFSFileSystem poiInputFile = new POIFSFileSystem(
					new FileInputStream(this.getTemPlatPathForProposal()));
			HSSFWorkbook hssInputExcelFile = new HSSFWorkbook(poiInputFile);
			HSSFSheet hssInputExcelSheet = hssInputExcelFile.getSheetAt(0);
			HSSFSheet hssOutputExcelSheet = hssInputExcelFile
					.createSheet(outSheetName);
			String sheetName = hssInputExcelFile.getSheetName(0);

			int startRow = 0;
			HSSFRow hssDetailStartRow = hssInputExcelSheet.getRow(startRow + 3);
			int startCol = 0;
			int endCol = hssDetailStartRow.getLastCellNum() - 1;
			int readNos = 25;
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
			hssDetalSet.getCell(4).setCellValue(map审批流.get("实施"));
			hssDetalSet.getCell(5).setCellValue(map审批流.get("验证"));
			hssDetalSet.getCell(6).setCellValue(map审批流.get("Approval"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(startRow + 5);
			hssDetalSet.getCell(1).setCellValue(this.timeManage(map审批流.get("申请time")));
			hssDetalSet.getCell(2).setCellValue(this.timeManage(map审批流.get("初审time")));
			hssDetalSet.getCell(3).setCellValue(this.timeManage(map审批流.get("分配time")));
			hssDetalSet.getCell(4).setCellValue(this.timeManage(map审批流.get("实施time")));
			hssDetalSet.getCell(5).setCellValue(this.timeManage(map审批流.get("验证time")));
			hssDetalSet.getCell(6).setCellValue(this.timeManage(map审批流.get("Approvaltime")));			
			
			//提案书编号
			hssDetalSet = hssOutputExcelSheet.getRow(startRow);
			hssDetalSet.getCell(5).setCellValue(map提案书.get("申请编号"));
			
			//提案内容
			hssDetalSet = hssOutputExcelSheet.getRow(startRow + 7);
			hssDetalSet.getCell(2).setCellValue(map提案书.get("提案内容"));
			
			int readPlace = 12;
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
			hssDetalSet.getCell(1).setCellValue("分类: "+map提案书.get("内容分类"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 1);
			hssDetalSet.getCell(3).setCellValue(map提案书.get("效果预期"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 2);
			hssDetalSet.getCell(3).setCellValue(map提案书.get("费用人力投入"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 3);
			hssDetalSet.getCell(3).setCellValue(map提案书.get("比较结论"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 6);
			hssDetalSet.getCell(2).setCellValue(map实施结果.get("实施内容"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 7);
			hssDetalSet.getCell(2).setCellValue(map实施结果.get("实施费用"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 8);
			hssDetalSet.getCell(2).setCellValue(map实施结果.get("改善效果"));
			
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 11);
			hssDetalSet.getCell(2).setCellValue(map实施结果.get("意见"));
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
			hssInputExcelFile.setPrintArea(0, 0, endCol, 0, readPlace + 14);
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
