package com.gensolution.activity.vo;

import java.util.List;
import java.util.Map;

public class ActivityRndPlanDayVo {
	private String cm_code;
	private String om_code;
	private String prd_partclr_matter;
	private String base_de;
	private String plan_de;
	
	private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	private String em_no;
	
	private String prdi_sm_code;//'순방 매장 코드',
	private String prdi_sm_code_nm; // '순방 매장명',
	
	private List<Map<String,String>> smArr;
	
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	
	
	public List<Map<String, String>> getSmArr() {
		return smArr;
	}
	public void setSmArr(List<Map<String, String>> smArr) {
		this.smArr = smArr;
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
	public String getPrd_partclr_matter() {
		return prd_partclr_matter;
	}
	public void setPrd_partclr_matter(String prd_partclr_matter) {
		this.prd_partclr_matter = prd_partclr_matter;
	}
	public String getBase_de() {
		return base_de;
	}
	public void setBase_de(String base_de) {
		this.base_de = base_de;
	}
	public String getPlan_de() {
		return plan_de;
	}
	public void setPlan_de(String plan_de) {
		this.plan_de = plan_de;
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
	public String getUpdt_man() {
		return updt_man;
	}
	public void setUpdt_man(String updt_man) {
		this.updt_man = updt_man;
	}
	public String getUpdt_de() {
		return updt_de;
	}
	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}
	public String getPrdi_sm_code() {
		return prdi_sm_code;
	}
	public void setPrdi_sm_code(String prdi_sm_code) {
		this.prdi_sm_code = prdi_sm_code;
	}
	public String getPrdi_sm_code_nm() {
		return prdi_sm_code_nm;
	}
	public void setPrdi_sm_code_nm(String prdi_sm_code_nm) {
		this.prdi_sm_code_nm = prdi_sm_code_nm;
	}
	@Override
	public String toString() {
		return "ActivityRndPlanDayVo [cm_code=" + cm_code + ", om_code="
				+ om_code + ", prd_partclr_matter=" + prd_partclr_matter
				+ ", base_de=" + base_de + ", plan_de=" + plan_de
				+ ", delete_at=" + delete_at + ", regist_man=" + regist_man
				+ ", regist_de=" + regist_de + ", updt_man=" + updt_man
				+ ", updt_de=" + updt_de + ", em_no=" + em_no
				+ ", prdi_sm_code=" + prdi_sm_code + ", prdi_sm_code_nm="
				+ prdi_sm_code_nm + ", smArr=" + smArr + "]";
	}
	
}
