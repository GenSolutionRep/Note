package com.gensolution.activity.vo;

import java.util.List;
import java.util.Map;

public class ActivityGridMobileVo {
	
	private String main_innb;
	private String updt_de;
	private String partclr_matter;
	
	private List<Map<String,String>> columnArr;
	private List<Map<String,String>> bodyArr;

	
	
	public String getUpdt_de() {
		return updt_de;
	}

	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}

	public String getPartclr_matter() {
		return partclr_matter;
	}

	public void setPartclr_matter(String partclr_matter) {
		this.partclr_matter = partclr_matter;
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

	public String getMain_innb() {
		return main_innb;
	}

	public void setMain_innb(String main_innb) {
		this.main_innb = main_innb;
	}

	
	
}
