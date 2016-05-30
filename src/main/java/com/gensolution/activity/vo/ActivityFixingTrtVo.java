package com.gensolution.activity.vo;

import java.util.List;
import java.util.Map;

public class ActivityFixingTrtVo {
	private String pm_code;
	private String pm_code_nm;
    private String trtmnt_at;
	private String base_de;
	private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_de;
	private String updt_man;
	
	private String dft_innb;
	private String dftp_innb;
	private String em_no;
	private String cm_code;
	private String om_code;
	private String sm_code;
	
	private List<Map<String,String>> jsondata;
	private String params;
	
	public String getPm_code() {
		return pm_code;
	}
	public void setPm_code(String pm_code) {
		this.pm_code = pm_code;
	}
	public String getPm_code_nm() {
		return pm_code_nm;
	}
	public void setPm_code_nm(String pm_code_nm) {
		this.pm_code_nm = pm_code_nm;
	}
	public String getTrtmnt_at() {
		return trtmnt_at;
	}
	public void setTrtmnt_at(String trtmnt_at) {
		this.trtmnt_at = trtmnt_at;
	}
	public String getBase_de() {
		return base_de;
	}
	public void setBase_de(String base_de) {
		this.base_de = base_de;
	}
	public String getDelete_at() {
		return delete_at;
	}
	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}
	public String getRegist_man() {
		return regist_man;
	}
	public void setRegist_man(String regist_man) {
		this.regist_man = regist_man;
	}
	public String getRegist_de() {
		return regist_de;
	}
	public void setRegist_de(String regist_de) {
		this.regist_de = regist_de;
	}
	public String getUpdt_de() {
		return updt_de;
	}
	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}
	public String getUpdt_man() {
		return updt_man;
	}
	public void setUpdt_man(String updt_man) {
		this.updt_man = updt_man;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
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
	public String getDft_innb() {
		return dft_innb;
	}
	public void setDft_innb(String dft_innb) {
		this.dft_innb = dft_innb;
	}
	public String getDftp_innb() {
		return dftp_innb;
	}
	public void setDftp_innb(String dftp_innb) {
		this.dftp_innb = dftp_innb;
	}
	public List<Map<String, String>> getJsondata() {
		return jsondata;
	}
	public void setJsondata(List<Map<String, String>> jsondata) {
		this.jsondata = jsondata;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
}

