package com.vertexid.vo;

import java.util.List;
import java.util.Map;

import com.gensolution.activity.vo.ActivityFixingEvnVo;
import com.gensolution.activity.vo.ActivityFixingTrtVo;
import com.gensolution.activity.vo.ActivityRndOddVo;
import com.gensolution.activity.vo.ActivityRndTrtVo;

public class ExcelVo {
	private String fileName;
	private String sheetName;
	
	private String[] sheetNames;
	private String[] matters;
	
	private List<Map<String, String>> columnList; 
	private List<Map<String, String>> columnSecondList; 
	private List<Map<String, String>> bodyList ;
	private List<Map<String,String>>  sumList;
	
	
	private List<ActivityFixingEvnVo> fixingEvnColumnList;
	private List<ActivityFixingEvnVo> fixingEvnBodyList;
	
	private List<ActivityRndTrtVo> rndTrtList;	
	private List<ActivityRndOddVo> rndOddList;
	
	private List<ActivityFixingTrtVo> fixingTrtList;
	
	private List<List<Map<String, String>>> columnLists; 
	private List<List<Map<String, String>>> bodyLists ;
	
	
	
	public List<ActivityFixingTrtVo> getFixingTrtList() {
		return fixingTrtList;
	}
	public void setFixingTrtList(List<ActivityFixingTrtVo> fixingTrtList) {
		this.fixingTrtList = fixingTrtList;
	}
	public List<ActivityFixingEvnVo> getFixingEvnColumnList() {
		return fixingEvnColumnList;
	}
	public void setFixingEvnColumnList(List<ActivityFixingEvnVo> fixingEvnColumnList) {
		this.fixingEvnColumnList = fixingEvnColumnList;
	}
	public List<ActivityFixingEvnVo> getFixingEvnBodyList() {
		return fixingEvnBodyList;
	}
	public void setFixingEvnBodyList(List<ActivityFixingEvnVo> fixingEvnBodyList) {
		this.fixingEvnBodyList = fixingEvnBodyList;
	}
	public List<ActivityRndTrtVo> getRndTrtList() {
		return rndTrtList;
	}
	public void setRndTrtList(List<ActivityRndTrtVo> rndTrtList) {
		this.rndTrtList = rndTrtList;
	}
	public List<ActivityRndOddVo> getRndOddList() {
		return rndOddList;
	}
	public void setRndOddList(List<ActivityRndOddVo> rndOddList) {
		this.rndOddList = rndOddList;
	}
	public List<List<Map<String, String>>> getColumnLists() {
		return columnLists;
	}
	public void setColumnLists(List<List<Map<String, String>>> columnLists) {
		this.columnLists = columnLists;
	}
	public List<List<Map<String, String>>> getBodyLists() {
		return bodyLists;
	}
	public void setBodyLists(List<List<Map<String, String>>> bodyLists) {
		this.bodyLists = bodyLists;
	}
	public String[] getSheetNames() {
		return sheetNames;
	}
	public void setSheetNames(String[] sheetNames) {
		this.sheetNames = sheetNames;
	}
	public List<Map<String, String>> getColumnSecondList() {
		return columnSecondList;
	}
	public void setColumnSecondList(List<Map<String, String>> columnSecondList) {
		this.columnSecondList = columnSecondList;
	}
	public String getFileName() {
		if(fileName == null || fileName.equals("")){
			this.fileName = "엑셀다운로드.xlsx";
		}
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSheetName() {
		if(sheetName == null || sheetName.equals("")){
			this.sheetName = "Sheet1";
		}
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public List<Map<String, String>> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<Map<String, String>> columnList) {
		this.columnList = columnList;
	}
	public List<Map<String, String>> getBodyList() {
		return bodyList;
	}
	public void setBodyList(List<Map<String, String>> bodyList) {
		this.bodyList = bodyList;
	}
	public List<Map<String, String>> getSumList() {
		return sumList;
	}
	public void setSumList(List<Map<String, String>> sumList) {
		this.sumList = sumList;
	}
	public String[] getMatters() {
		return matters;
	}
	public void setMatters(String[] matters) {
		this.matters = matters;
	}
	
}
