package com.vertexid.vo;

import java.util.List;
import java.util.Map;

public class JsonVo {
	
	
	
	private String params;
	private String om_code;
	private String sm_code;
	
	private Map<String,Map<String,String>> map;
	private List<Map<String,String>> arr;
	
	
	
	
	
	
	
	
	
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getOm_code() {
		return om_code;
	}
	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public Map<String, Map<String, String>> getMap() {
		return map;
	}
	public void setMap(Map<String, Map<String, String>> map) {
		this.map = map;
	}
	public List<Map<String, String>> getArr() {
		return arr;
	}
	public void setArr(List<Map<String, String>> arr) {
		this.arr = arr;
	}
	@Override
	public String toString() {
		return "JsonVo [params=" + params + ", om_code=" + om_code
				+ ", sm_code=" + sm_code + ", map=" + map + ", arr=" + arr
				+ "]";
	}
	
	
}
