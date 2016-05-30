package com.gensolution.activity.vo;

import java.util.List;
import java.util.Map;

public class ActivityOddMobileVo {
	private String main_innb;
	private String updt_de;
	private List<Map<String, String>> odd_list;
	
	public String getUpdt_de() {
		return updt_de;
	}
	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}
	
	public List<Map<String, String>> getOdd_list() {
		return odd_list;
	}
	public void setOdd_list(List<Map<String, String>> odd_list) {
		this.odd_list = odd_list;
	}
	public String getMain_innb() {
		return main_innb;
	}
	public void setMain_innb(String main_innb) {
		this.main_innb = main_innb;
	}
	
}
