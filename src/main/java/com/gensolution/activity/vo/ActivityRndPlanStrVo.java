package com.gensolution.activity.vo;

public class ActivityRndPlanStrVo {
	private String sm_code;
	private String sm_nm;
	private String sm_la;
	private String sm_lo;
	private String sm_img_url;
	private String sm_cvscafe_at;
	private String sm_mod_at;
	private String drc_innb;
	public String getSm_code() {
		return sm_code;
	}
	
	public String getSm_mod_at() {
		return sm_mod_at;
	}

	public void setSm_mod_at(String sm_mod_at) {
		this.sm_mod_at = sm_mod_at;
	}

	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public String getSm_nm() {
		return sm_nm;
	}
	public void setSm_nm(String sm_nm) {
		this.sm_nm = sm_nm;
	}
	public String getSm_la() {
		return sm_la;
	}
	public void setSm_la(String sm_la) {
		this.sm_la = sm_la;
	}
	public String getSm_lo() {
		return sm_lo;
	}
	public void setSm_lo(String sm_lo) {
		this.sm_lo = sm_lo;
	}
	public String getSm_img_url() {
		return sm_img_url;
	}
	public void setSm_img_url(String sm_img_url) {
		this.sm_img_url = sm_img_url;
	}
	public String getSm_cvscafe_at() {
		return sm_cvscafe_at;
	}
	public void setSm_cvscafe_at(String sm_cvscafe_at) {
		this.sm_cvscafe_at = sm_cvscafe_at;
	}
	public String getDrc_innb() {
		return drc_innb;
	}
	public void setDrc_innb(String drc_innb) {
		this.drc_innb = drc_innb;
	}
	@Override
	public String toString() {
		return "ActivityRndPlanStrVo [sm_code=" + sm_code + ", sm_nm=" + sm_nm
				+ ", sm_la=" + sm_la + ", sm_lo=" + sm_lo + ", sm_img_url="
				+ sm_img_url + ", sm_cvscafe_at=" + sm_cvscafe_at
				+ ", drc_innb=" + drc_innb + "]";
	}
	
}
