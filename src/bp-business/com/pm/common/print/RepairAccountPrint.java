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

/**
 * 报表打印
 * @author 刘镝
 */
public class RepairAccountPrint extends PrintBase {
	public RepairAccountPrint(HttpServletRequest request){
		super(request);
	}
	/**
	 * 打印
	 * 
	 * @param resultList 内容
	 */
	public String mdPrint(Map<String,String> map维修费用明细, List list人工服务费明细, List list维修材料费) {

		
		String outSheetName = "外协维修费用明细";
		String filePath = this.getFilePath(outSheetName); 
		String outPutFilePath = this.getRealPath() + filePath;
		FileOutputStream fileOutput = null;
		try {
			//String outSheetName = "printSheet";
			POIFSFileSystem poiInputFile = new POIFSFileSystem(
					new FileInputStream(this.getTemPlatPath3()));
			HSSFWorkbook hssInputExcelFile = new HSSFWorkbook(poiInputFile);
			HSSFSheet hssInputExcelSheet = hssInputExcelFile.getSheetAt(0);
			HSSFSheet hssOutputExcelSheet = hssInputExcelFile
					.createSheet(outSheetName);
			String sheetName = hssInputExcelFile.getSheetName(0);

			int startRow = 0;
			HSSFRow hssDetailStartRow = hssInputExcelSheet.getRow(startRow + 3);
			int startCol = 0;
			int endCol = hssDetailStartRow.getLastCellNum() - 1;
			int readNos = 6;
			// 开始行get(0) 第一行
			// 开始列get(0) 第一列
			// 位置从0开始
			this.mdRangeCopy(sheetName, outSheetName, 0, readNos, startCol,
					endCol, 0, hssInputExcelFile);
			
			//Title
			HSSFRow hssDetalSet = hssOutputExcelSheet.getRow(startRow + 2);
			String Title = map维修费用明细.get("开始日期").replace("-", "/") + " - " + map维修费用明细.get("结束日期").replace("-", "/");
			hssDetalSet.getCell(0).setCellValue(Title + "  " + outSheetName);
			hssDetalSet = hssOutputExcelSheet.getRow(startRow + 3);
			String lc供应商 = "供应商：" + map维修费用明细.get("供应商");
			hssDetalSet.getCell(0).setCellValue(lc供应商);

			//报价内容
			int readPlace = 6;
			// 金额
//			DecimalFormat moneyFormat = new DecimalFormat("##0.00");
			DecimalFormat moneyFormat = new DecimalFormat(",##0.00");
			
			Double moneyCount = Double.valueOf("0");
			
			for(int i = 0; i < list人工服务费明细.size(); i++){
				readPlace++;
				Map<String, String> eozz人工服务费明细 = (Map<String, String>) list人工服务费明细	.get(i);
				// 列表
				this.mdRangeCopy(sheetName, outSheetName, readNos + 1,
						readNos + 1, startCol, endCol, readPlace,
						hssInputExcelFile);
				hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
				//hssDetalSet.getCell(0).setCellValue(eozz人工服务费明细.get("完成日期").substring(0,10).replace("-", "/"));
				hssDetalSet.getCell(0).setCellValue(eozz人工服务费明细.get("计划完成日期"));
				hssDetalSet.getCell(1).setCellValue(eozz人工服务费明细.get("人工时"));
				hssDetalSet.getCell(2).setCellValue(eozz人工服务费明细.get("单价"));
				String lc合计 = moneyFormat.format(this.tRound(eozz人工服务费明细.get("人工时")) * this.tRound(eozz人工服务费明细.get("单价")));
				moneyCount = moneyCount + this.tRound(eozz人工服务费明细.get("人工时")) * this.tRound(eozz人工服务费明细.get("单价"));
				hssDetalSet.getCell(3).setCellValue(lc合计);
				hssDetalSet.getCell(4).setCellValue(eozz人工服务费明细.get("项目名称"));
				hssDetalSet.getCell(5).setCellValue(eozz人工服务费明细.get("部门"));
				hssDetalSet.getCell(6).setCellValue(eozz人工服务费明细.get("备注"));
				hssDetalSet.getCell(7).setCellValue(eozz人工服务费明细.get("维修单号"));
				hssDetalSet.getCell(8).setCellValue(eozz人工服务费明细.get("结算日期"));
				hssDetalSet.getCell(9).setCellValue(eozz人工服务费明细.get("全项完成日期"));
				
				//合计
				if(i == list人工服务费明细.size() - 1){				
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, 18,
							21, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					//之前是sub(moneyCount, moneyCount/1.06)，改成moneyCount*0.06   20190909
					hssDetalSet.getCell(3).setCellValue(moneyFormat.format(moneyCount+moneyCount*0.06));
					hssDetalSet.getCell(8).setCellValue(moneyFormat.format(moneyCount*0.06));
				}
			}
			
			//材料费
			readPlace = readPlace + 3;
			Double moneyCount1 = Double.valueOf("0");
			Double repairtax = Double.valueOf("0");

			for(int i = 0; i < list维修材料费.size(); i++){
				readPlace++;
				Map<String, String> eozz维修材料费 = (Map<String, String>) list维修材料费.get(i);

				if(i == 0){
					repairtax = this.tRound(String.valueOf(eozz维修材料费.get("维修税金")));
				}
				// 列表
				this.mdRangeCopy(sheetName, outSheetName, 22,
						22, startCol, endCol, readPlace,
						hssInputExcelFile);
				hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
				//hssDetalSet.getCell(0).setCellValue(eozz维修材料费.get("完成日期").substring(0,10).replace("-", "/"));
				hssDetalSet.getCell(0).setCellValue(eozz维修材料费.get("计划完成日期"));
				hssDetalSet.getCell(1).setCellValue(eozz维修材料费.get("费用种类"));
				if(!"".equals(eozz维修材料费.get("费用种类")) && eozz维修材料费.get("费用种类")!= null){
					if(eozz维修材料费.get("费用种类").equals("一次性费用")){
						hssDetalSet.getCell(3).setCellValue(moneyFormat.format(this.tRound(eozz维修材料费.get("金额"))));
						moneyCount1 = moneyCount1 + this.tRound(eozz维修材料费.get("金额"));
					}else{
						String lc金额 = moneyFormat.format(this.tRound(eozz维修材料费.get("数量")) * this.tRound(eozz维修材料费.get("单价")));
						hssDetalSet.getCell(3).setCellValue(lc金额);
						moneyCount1 = moneyCount1 +(this.tRound(eozz维修材料费.get("数量")) * this.tRound(eozz维修材料费.get("单价")));
					}
				}
				hssDetalSet.getCell(4).setCellValue(eozz维修材料费.get("事由"));
				hssDetalSet.getCell(5).setCellValue(eozz维修材料费.get("部门"));
				hssDetalSet.getCell(6).setCellValue(eozz维修材料费.get("备注"));
				hssDetalSet.getCell(7).setCellValue(eozz维修材料费.get("维修单号"));
				hssDetalSet.getCell(8).setCellValue(eozz维修材料费.get("结算日期"));
				hssDetalSet.getCell(9).setCellValue(eozz维修材料费.get("全项完成日期"));

				//合计
				if(i == list维修材料费.size() - 1){
//					readPlace ++;
//					this.mdRangeCopy(sheetName, outSheetName, 23,
//							23, startCol, endCol, readPlace,
//							hssInputExcelFile);
//					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
//					hssDetalSet.getCell(3).setCellValue(moneyFormat.format(repairtax));
					
					readPlace ++;
					this.mdRangeCopy(sheetName, outSheetName, 53,
							53, startCol, endCol, readPlace,
							hssInputExcelFile);
					hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
					//hssDetalSet.getCell(3).setCellValue(moneyFormat.format(moneyCount1 + repairtax));
					hssDetalSet.getCell(3).setCellValue(moneyFormat.format(moneyCount1));
				}
			}
			
			//备注
			readPlace ++;
			this.mdRangeCopy(sheetName, outSheetName, 54,
					59, startCol, endCol, readPlace,
					hssInputExcelFile);
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
			hssDetalSet.getCell(1).setCellValue(map维修费用明细.get("备注"));
			hssDetalSet = hssOutputExcelSheet.getRow(readPlace + 2);
			
			//String lc费用合计 = "  三、"+Title+" 费用共计：" + "        ￥"+ moneyFormat.format(moneyCount +  repairtax + moneyCount1);
			String lc费用合计 = "  三、"+Title+" 费用共计：" + "        ￥"+ moneyFormat.format(moneyCount +  moneyCount1 + moneyCount*0.06);
			hssDetalSet.getCell(0).setCellValue(lc费用合计);
//			hssDetalSet.getCell(3).setCellValue(moneyFormat.format(moneyCount + moneyCount1));	
			

			// 图片设置
			this.picSet3(hssInputExcelFile, hssOutputExcelSheet);

			// 打印设置
			this.printSet(hssInputExcelSheet, hssOutputExcelSheet);
			
			//hssInputExcelFile.setPrintArea(1, 0, endCol, 0, 79);
			// 打印文件生成
			fileOutput = new FileOutputStream(outPutFilePath);
			hssInputExcelFile.removeSheetAt(0);
			hssInputExcelFile.setSheetName(0, outSheetName);

			hssOutputExcelSheet.setColumnBreak(endCol);
			hssInputExcelFile.setPrintArea(0, 0, endCol, 0, readPlace + 5);
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
