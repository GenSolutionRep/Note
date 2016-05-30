package com.vertexid.utill;

import java.io.File;
import java.io.FileOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelExport {
	
	  public ExcelExport(){}
	  
	  FileOutputStream fileOut = null;
	  
	  public String excelExportFile(JSONObject object, String fileName, String filePath) throws Exception{
		  JSONArray array = (JSONArray)object.get("data");
		  int rowCount = Integer.parseInt(object.get("rowCount").toString());
		  try{
		  HSSFWorkbook workbook = new HSSFWorkbook();
		  HSSFSheet sheet = workbook.createSheet("Sheet1");
		  
		  HSSFCellStyle cellStyle_head = workbook.createCellStyle();
		  cellStyle_head.setBorderRight(HSSFCellStyle.BORDER_THIN);   
		  cellStyle_head.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
		  cellStyle_head.setBorderTop(HSSFCellStyle.BORDER_THIN);   
		  cellStyle_head.setBorderBottom(HSSFCellStyle.BORDER_THIN);   
		  cellStyle_head.setAlignment((short)2);
	      
	      HSSFCellStyle cellStyle_body = workbook.createCellStyle();
	      cellStyle_body.setBorderRight(HSSFCellStyle.BORDER_THIN);   
	      cellStyle_body.setBorderLeft(HSSFCellStyle.BORDER_THIN);   
	      cellStyle_body.setBorderTop(HSSFCellStyle.BORDER_THIN);   
	      cellStyle_body.setBorderBottom(HSSFCellStyle.BORDER_THIN);   
	      
		  HSSFFont font = workbook.createFont();                                    //폰트 조정 인스턴스 생성   
		  font.setFontName("맑은 고딕");        
		  font.setFontHeightInPoints((short)9);
		  
		  cellStyle_head.setFont(font);
		  cellStyle_body.setFont(font);
		  
		  HSSFRow  row = null;
		  HSSFCell  cell = null;
		 
		  
		  if(array.size() > 0){
			  for(int i=0; i<1; i++){
				  JSONObject jobj = (JSONObject)object.get("title");
				  row = sheet.createRow((short)i);
				  for(int y = 0; y < rowCount; y++){
					  String n = "col"+ String.valueOf(y);
					  cell = row.createCell(y);
					  cell.setCellValue(jobj.get(n).toString());
					  cell.setCellStyle(cellStyle_head);
				  }
			  }
			  for(int i = 0; i < array.size(); i++){
				  JSONObject jobj = (JSONObject)array.get(i);
				  row = sheet.createRow((short)i+1);
				  for(int y = 0; y < rowCount; y++){
					  String n = "col"+ String.valueOf(y);
					  cell = row.createCell(y);
					  cell.setCellValue(jobj.get(n).toString());
					  cell.setCellStyle(cellStyle_body);
				  }
			  }
		  }
		  /**
		   * 디렉토리 생성 및 파일 생성
		   * 
		   **/
		  File fPath = new File(filePath);
		  if(!fPath.exists())	fPath.mkdirs();
		  fileOut = new FileOutputStream(filePath+"/"+fileName);
		  workbook.write(fileOut);
		  fileOut.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  } finally {
			  if(fileOut != null){
					try{
						fileOut.close();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
		  }
		  System.out.println("excelexpot===>"+fileName);
		  return fileName;
	  }
	  
	  public String excelExportFile2(JSONObject object, String fileName, String filePath) throws Exception
	  {
		  JSONArray array = (JSONArray)object.get("data");
		  int rowCount = Integer.parseInt(object.get("rowCount").toString());
		  try{
		  HSSFWorkbook workbook = new HSSFWorkbook();
		  HSSFSheet sheet = workbook.createSheet("Sheet1");
		  HSSFRow  row = null;
		  HSSFCell  cell = null;
		 
		  
		  if(array.size() > 0){
			  for(int i=0; i<1; i++){
				  JSONObject jobj = (JSONObject)object.get("title");
				  row = sheet.createRow((short)i);
				  for(int y = 0; y < rowCount; y++){
					  String n = "col"+ String.valueOf(y);
					  cell = row.createCell(y);
					  cell.setCellValue(jobj.get(n).toString());
				  }
			  }
			  for(int i=0; i<1; i++){
				  JSONObject jobj = (JSONObject)object.get("title2");
				  row = sheet.createRow((short)i+1);
				  for(int y = 0; y < rowCount; y++){
					  String n = "col"+ String.valueOf(y);
					  cell = row.createCell(y);
					  cell.setCellValue(jobj.get(n).toString());
				  }
			  }
			  for(int i = 0; i < array.size(); i++){
				  JSONObject jobj = (JSONObject)array.get(i);
				  row = sheet.createRow((short)i+2);
				  for(int y = 0; y < rowCount; y++){
					  String n = "col"+ String.valueOf(y);
					  cell = row.createCell(y);
					  cell.setCellValue(jobj.get(n).toString());
				  }
			  }
		  }
		  /**
		   * 디렉토리 생성 및 파일 생성
		   * 
		   **/
		  File fPath = new File(filePath);
		  if(!fPath.exists())	fPath.mkdirs();
		  fileOut = new FileOutputStream(filePath+"/"+fileName);
		  workbook.write(fileOut);
		  fileOut.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  } finally {
			  if(fileOut != null){
					try{
						fileOut.close();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
		  }
		  System.out.println("excelexpot===>"+fileName);
		  return fileName;
	  }
}
