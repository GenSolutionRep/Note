package com.gensolution.activity.vo;

import java.util.List;
import java.util.Map;

public class ActivityEvnMobileVo {
	private String main_innb;
	private String updt_de;
	
	private List<Map<String, Object>> evn_list;
	
	public String getUpdt_de() {
		return updt_de;
	}
	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}
	public String getMain_innb() {
		return main_innb;
	}
	public void setMain_innb(String main_innb) {
		this.main_innb = main_innb;
	}
	public List<Map<String, Object>> getEvn_list() {
		return evn_list;
	}
	public void setEvn_list(List<Map<String, Object>> evn_list) {
		this.evn_list = evn_list;
	}
}
