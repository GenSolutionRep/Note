package com.dasa.analysis.vo;

import java.util.List;
import java.util.Map;

public class DisplayGridVo {
	
//	private String d_innb;
	private String cm_code;
//	private String cg_nm;
//	private String me_nm;
//	private String sm_nm;
//	private String sm_odr;
//	private String addr_code1;
//	private String addr_code2;
//	private String addr_code3;
	
	private String pagingEnd;
	private Map<String, String> params;
	
	private List<Map<String,String>>  columnArr;
	private List<Map<String,String>>  bodyArr;
	private List<Map<String,String>>  sumArr;
	
	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}
	public String getPagingEnd() {
		return pagingEnd;
	}
	public void setPagingEnd(String pagingEnd) {
		this.pagingEnd = pagingEnd;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
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
	
	public List<Map<String, String>> getSumArr() {
		return sumArr;
	}
	public void setSumArr(List<Map<String, String>> sumArr) {
		this.sumArr = sumArr;
	}
	@Override
	public String toString() {
		return "DisplayGridVo [pagingEnd=" + pagingEnd + ", params=" + params
				+ ", columnArr=" + columnArr + ", bodyArr=" + bodyArr + "]";
	}
	
	
}
