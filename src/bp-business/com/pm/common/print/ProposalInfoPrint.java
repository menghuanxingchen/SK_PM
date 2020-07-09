package com.pm.common.print;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.pm.persistence.proposal.dto.ProposalInfoDTO;

public class ProposalInfoPrint extends PrintBase {

	
	public ProposalInfoPrint(HttpServletRequest request){
		super(request);
	}
	/**
	 * 打印
	 * 
	 * @param listInputData
	 *            数据源
	 */
	public String mdPrint(List resList) {
		
		String outSheetName = "提案书信息";

		String filePath = this.getFilePath(outSheetName); 
		String outPutFilePath = this.getRealPath() + filePath;
		String tempPath = this.getRealPath() + TEMPLANTPATH + "proposalTemp.xls";

		FileOutputStream fileOutput = null;
		try {

			POIFSFileSystem poiInputFile = new POIFSFileSystem(
					new FileInputStream(tempPath));
			HSSFWorkbook hssInputExcelFile = new HSSFWorkbook(poiInputFile);
			HSSFSheet hssInputExcelSheet = hssInputExcelFile.getSheetAt(0);

			int endCol = 0;
			HSSFSheet hssOutputExcelSheet = hssInputExcelFile
					.createSheet(outSheetName);

			int startRow = 0;
			HSSFRow hssDetailStartRow = hssInputExcelSheet.getRow(startRow);
			int startCol = 0;
			endCol = hssDetailStartRow.getLastCellNum() - 1;
			String sheetName = hssInputExcelFile.getSheetName(0);

			this.mdRangeCopy(sheetName, outSheetName, 0, 0, startCol,
					endCol, 0, hssInputExcelFile);
			HSSFRow hssDetalSet = null;
			
			int readPlace = 0;
			
			int startMergeRow = 0;
			String oldNo = "";
			String newNo = "";
			CellRangeAddress cra= null;
			
			for(int i = 0; i < resList.size(); i++){
				//Map resMap = (Map) resList.get(i);
				ProposalInfoDTO proposalInfoDTO = (ProposalInfoDTO) resList.get(i);
				readPlace++;
				this.mdRangeCopy(sheetName, outSheetName, 1, 1, startCol,
						endCol, readPlace, hssInputExcelFile);
				hssDetalSet = hssOutputExcelSheet.getRow(readPlace);
				newNo = proposalInfoDTO.getNumber();//序号
				hssDetalSet.getCell(0).setCellValue(proposalInfoDTO.getNumber());//序号
				hssDetalSet.getCell(1).setCellValue(proposalInfoDTO.getProposal_num());//申请编号
				hssDetalSet.getCell(2).setCellValue(proposalInfoDTO.getProposal_type_nm());//内容分类
    			hssDetalSet.getCell(3).setCellValue(proposalInfoDTO.getProposal_detail());//提案内容
    			hssDetalSet.getCell(4).setCellValue(proposalInfoDTO.getAnticipate_result());//弊端改善
				hssDetalSet.getCell(5).setCellValue(proposalInfoDTO.getCreate_id_nm());//申请人
				hssDetalSet.getCell(6).setCellValue(proposalInfoDTO.getOperate_user_nm());//实施人
				hssDetalSet.getCell(7).setCellValue(proposalInfoDTO.getDeal_user_id_nm());//处理人
				hssDetalSet.getCell(8).setCellValue(proposalInfoDTO.getApproval_state_nm());//审批状态
				hssDetalSet.getCell(9).setCellValue(proposalInfoDTO.getDept_nm());//申请人部门
				hssDetalSet.getCell(10).setCellValue(proposalInfoDTO.getFactory_manager_approval_date());//全项完成日期
				hssDetalSet.getCell(11).setCellValue(proposalInfoDTO.getRequire_date());//分配日期
				/*newNo = (String)resMap.get("序号");
				hssDetalSet.getCell(0).setCellValue((String)resMap.get("序号"));
				hssDetalSet.getCell(1).setCellValue((String)resMap.get("项目名称"));
				hssDetalSet.getCell(2).setCellValue((String)resMap.get("内容"));
				hssDetalSet.getCell(3).setCellValue((String)resMap.get("负责人"));
				hssDetalSet.getCell(4).setCellValue((String)resMap.get("项目类型"));
				hssDetalSet.getCell(5).setCellValue((String)resMap.get("指示开始时间"));
				hssDetalSet.getCell(6).setCellValue((String)resMap.get("计划完成时间"));
				hssDetalSet.getCell(7).setCellValue((String)resMap.get("操作时间"));
				hssDetalSet.getCell(8).setCellValue((String)resMap.get("操作内容"));
				hssDetalSet.getCell(9).setCellValue((String)resMap.get("实际完成时间"));
				hssDetalSet.getCell(10).setCellValue((String)resMap.get("状态"));*/
				
				if(i==0){
					//oldNo = (String)resMap.get("");
					oldNo = (String)proposalInfoDTO.getNumber();//序号
					startMergeRow = readPlace;
				}else if(!oldNo.equals(newNo)){
					int endMergeRow = readPlace-1;

					//序号
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 0, 0);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//项目名称
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 1, 1);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//内容
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 2, 2);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//负责人
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 3, 3);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//项目类型
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 4, 4);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//指示开始时间
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 5, 5);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//计划完成时间
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 6, 6);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//实际完成时间
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 9, 9);
					hssOutputExcelSheet.addMergedRegion(cra);
					//状态
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 10, 10);
					hssOutputExcelSheet.addMergedRegion(cra); 
					
					startMergeRow = readPlace;
					oldNo = newNo;
				}
				
				if(i == resList.size() -1){
					int endMergeRow = readPlace;
					//序号
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 0, 0);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//项目名称
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 1, 1);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//内容
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 2, 2);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//负责人
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 3, 3);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//项目类型
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 4, 4);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//指示开始时间
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 5, 5);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//计划完成时间
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 6, 6);
					hssOutputExcelSheet.addMergedRegion(cra); 
					//实际完成时间
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 9, 9);
					hssOutputExcelSheet.addMergedRegion(cra);
					//状态
					cra=new CellRangeAddress(startMergeRow, endMergeRow, 10, 10);
					hssOutputExcelSheet.addMergedRegion(cra); 
				}

			}

			
			// 打印文件生成
			fileOutput = new FileOutputStream(outPutFilePath);
			
			// 打印设置
			//自动打印缩放
			this.printSet2(hssInputExcelSheet, hssOutputExcelSheet);
			hssOutputExcelSheet.setColumnBreak(endCol);
			
			hssInputExcelFile.removeSheetAt(0);
			hssInputExcelFile.setSheetName(0, outSheetName);
			hssInputExcelFile.setPrintArea(0, 0, endCol, 0, readPlace++);
			//hssInputExcelFile.removePrintArea(0);
			
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
		
		// 刪除文件
		// this.deleteFile(outPutFilePath);
		
		return filePath;
	}
}
