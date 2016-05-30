package com.dasa.store.vo;

/**
 * @Class명: StrEmplVo
 * @작성일: 2015.09.30
 * @작성자: 최수영
 * @설명: 매장별 사원관리 오브젝트
 */
public class StrEmplVo {
       
	
	private String em_no = "";	
	private String em_name = "";
	private String sm_code = "";	
	private String om_code = "";	
	private String se_empl_ty = "";	
	private String delete_at  = "";	
	private String regist_man = "";	
	private String regist_de = "";	
	private String updt_man = "";	
	private String updt_de = "";
	
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getEm_name() {
		return em_name;
	}
	public void setEm_name(String em_name) {
		this.em_name = em_name;
	}
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public String getOm_code() {
		return om_code;
	}
	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}
	public String getSe_empl_ty() {
		return se_empl_ty;
	}
	public void setSe_empl_ty(String se_empl_ty) {
		this.se_empl_ty = se_empl_ty;
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
	
}
