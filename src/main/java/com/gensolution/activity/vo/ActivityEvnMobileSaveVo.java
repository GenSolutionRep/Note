package com.gensolution.activity.vo;

import org.springframework.web.multipart.MultipartFile;

public class ActivityEvnMobileSaveVo {
	private String base_de;
	private String main_innb;
	private String dfesi_innb;
	private String dfes_innb;
	private String dfe_innb;
	private String cm_code;
	private String om_code;
	private String sm_code;
	private String oi_code;
	private String oi_code_nm;
	private String em_no;
	private String dfes_oi_code;
	private String dfes_oi_code_nm;
	private String dfesi_img_url;
	
	private MultipartFile file;
	
	
	
	public String getDfesi_img_url() {
		return dfesi_img_url;
	}
	public void setDfesi_img_url(String dfesi_img_url) {
		this.dfesi_img_url = dfesi_img_url;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getBase_de() {
		return base_de;
	}
	public void setBase_de(String base_de) {
		this.base_de = base_de;
	}
	public String getMain_innb() {
		return main_innb;
	}
	public void setMain_innb(String main_innb) {
		this.main_innb = main_innb;
	}
	public String getDfesi_innb() {
		if(dfesi_innb == null || dfesi_innb.equals("")){
			dfesi_innb = null;
		}
		return dfesi_innb;
	}
	public void setDfesi_innb(String dfesi_innb) {
		this.dfesi_innb = dfesi_innb;
	}
	public String getDfes_innb() {
		if(dfes_innb == null || dfes_innb.equals("")){
			dfes_innb = null;
		}
		return dfes_innb;
	}
	public void setDfes_innb(String dfes_innb) {
		this.dfes_innb = dfes_innb;
	}
	public String getDfe_innb() {
		if(dfe_innb == null || dfe_innb.equals("")){
			dfe_innb = null;
		}
		return dfe_innb;
	}
	public void setDfe_innb(String dfe_innb) {
		this.dfe_innb = dfe_innb;
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
	public String getOi_code() {
		return oi_code;
	}
	public void setOi_code(String oi_code) {
		this.oi_code = oi_code;
	}
	public String getOi_code_nm() {
		return oi_code_nm;
	}
	public void setOi_code_nm(String oi_code_nm) {
		this.oi_code_nm = oi_code_nm;
	}
	public String getDfes_oi_code() {
		return dfes_oi_code;
	}
	public void setDfes_oi_code(String dfes_oi_code) {
		this.dfes_oi_code = dfes_oi_code;
	}
	public String getDfes_oi_code_nm() {
		return dfes_oi_code_nm;
	}
	public void setDfes_oi_code_nm(String dfes_oi_code_nm) {
		this.dfes_oi_code_nm = dfes_oi_code_nm;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "ActivityEvnMobileSaveVo [base_de=" + base_de + ", main_innb="
				+ main_innb + ", dfesi_innb=" + dfesi_innb + ", dfes_innb="
				+ dfes_innb + ", dfe_innb=" + dfe_innb + ", cm_code=" + cm_code
				+ ", om_code=" + om_code + ", sm_code=" + sm_code
				+ ", oi_code=" + oi_code + ", oi_code_nm=" + oi_code_nm
				+ ", em_no=" + em_no + ", dfes_oi_code=" + dfes_oi_code
				+ ", dfes_oi_code_nm=" + dfes_oi_code_nm + ", dfesi_img_url="
				+ dfesi_img_url + ", file=" + file + "]";
	}
	
}
