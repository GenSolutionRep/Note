package com.vertexid.vo;

import java.util.List;
import java.util.Map;

/**
 * @Class명: ProductVo
 * @작성일: 2015.10.06
 * @작성자: 최수영
 * @설명: 품목관리 오브젝트
 */
public class ProductJsonVo {
       
	private String pm_code;
	private String pm_parent_no;
	private String pm_nm;		
	private String pm_dp;		
	private Map<String,ProductJsonVo> child;
	
	public String getPm_code() {
		return pm_code;
	}
	public void setPm_code(String pm_code) {
		this.pm_code = pm_code;
	}
	public String getPm_parent_no() {
		return pm_parent_no;
	}
	public void setPm_parent_no(String pm_parent_no) {
		this.pm_parent_no = pm_parent_no;
	}
	public String getPm_nm() {
		return pm_nm;
	}
	public void setPm_nm(String pm_nm) {
		this.pm_nm = pm_nm;
	}
	public String getPm_dp() {
		return pm_dp;
	}
	public void setPm_dp(String pm_dp) {
		this.pm_dp = pm_dp;
	}
	public Map<String, ProductJsonVo> getChild() {
		return child;
	}
	public void setChild(Map<String, ProductJsonVo> child) {
		this.child = child;
	}
	
}
