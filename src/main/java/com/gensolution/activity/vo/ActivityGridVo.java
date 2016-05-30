package com.gensolution.activity.vo;

import java.util.List;
import java.util.Map;

public class ActivityGridVo {
	
	private String base_de;
	private String em_no;
	private String sm_code;
	
	private String column_code;
	private String column_key;
	private String column_table;
	private String body_code;
	private String updt_de;
	private String partclr_matter;
	private String updateFlag;
	
	private List<Map<String,String>>  columnArr;
	private List<Map<String,String>>  bodyArr;

	
	
	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getPartclr_matter() {
		return partclr_matter;
	}

	public void setPartclr_matter(String partclr_matter) {
		this.partclr_matter = partclr_matter;
	}

	public String getUpdt_de() {
		return updt_de;
	}

	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}

	public String getSm_code() {
		return sm_code;
	}

	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}

	public String getBase_de() {
		return base_de;
	}

	public void setBase_de(String base_de) {
		this.base_de = base_de;
	}

	public String getEm_no() {
		return em_no;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public List<Map<String, String>> getColumnArr() {
		return columnArr;
	}

	public void setColumnArr(List<Map<String, String>> columnArr) {
		this.columnArr = columnArr;
	}

	public List<Map<String, String>> getBodyArr() {
		return bodyArr;
	}

	public void setBodyArr(List<Map<String, String>> bodyArr) {
		this.bodyArr = bodyArr;
	}

	public String getColumn_code() {
		return column_code;
	}

	public void setColumn_code(String column_code) {
		this.column_code = column_code;
	}

	public String getBody_code() {
		return body_code;
	}

	public void setBody_code(String body_code) {
		this.body_code = body_code;
	}

	public String getColumn_key() {
		return column_key;
	}

	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}

	public String getColumn_table() {
		return column_table;
	}

	public void setColumn_table(String column_table) {
		this.column_table = column_table;
	}
	
	
}
