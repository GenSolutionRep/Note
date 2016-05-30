package com.gensolution.activity.vo;

import org.springframework.web.multipart.MultipartFile;

public class ActivityRndMobileAttendVo {
	private String plan_de;
	private String base_de;
	private String cm_code;
	private String om_code;
	private String sm_code;
	private String em_no;
	private String am_no;
	private String prdi_sm_code;
	private MultipartFile file;
	
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
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getAm_no() {
		return am_no;
	}
	public void setAm_no(String am_no) {
		this.am_no = am_no;
	}
	public String getPrdi_sm_code() {
		return prdi_sm_code;
	}
	public void setPrdi_sm_code(String prdi_sm_code) {
		this.prdi_sm_code = prdi_sm_code;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
	
}
