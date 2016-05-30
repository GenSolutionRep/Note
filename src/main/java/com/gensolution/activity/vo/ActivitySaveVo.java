package com.gensolution.activity.vo;

public class ActivitySaveVo {
	private String base_de;
	private String em_no;
	private String cm_code;
	private String om_code;
	private String sm_code;
	private String partclr_matter;
	private String main_innb;
	
	private String paramArr1;
	private String paramArr2;
	private String paramArr3;
	
	public String getBase_de() {
		return base_de;
	}
	public void setBase_de(String base_de) {
		this.base_de = base_de;
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
	public String getParamArr1() {
		return paramArr1;
	}
	public void setParamArr1(String paramArr1) {
		this.paramArr1 = paramArr1;
	}
	public String getParamArr2() {
		return paramArr2;
	}
	public void setParamArr2(String paramArr2) {
		this.paramArr2 = paramArr2;
	}
	public String getParamArr3() {
		return paramArr3;
	}
	public void setParamArr3(String paramArr3) {
		this.paramArr3 = paramArr3;
	}
	public String getPartclr_matter() {
		return partclr_matter;
	}
	public void setPartclr_matter(String partclr_matter) {
		this.partclr_matter = partclr_matter;
	}
	public String getMain_innb() {
		return main_innb;
	}
	public void setMain_innb(String main_innb) {
		this.main_innb = main_innb;
	}
	@Override
	public String toString() {
		return "ActivitySaveVo [base_de=" + base_de + ", em_no=" + em_no
				+ ", cm_code=" + cm_code + ", om_code=" + om_code
				+ ", sm_code=" + sm_code + ", partclr_matter=" + partclr_matter
				+ ", main_innb=" + main_innb + ", paramArr1=" + paramArr1
				+ ", paramArr2=" + paramArr2 + ", paramArr3=" + paramArr3 + "]";
	}
	
}
