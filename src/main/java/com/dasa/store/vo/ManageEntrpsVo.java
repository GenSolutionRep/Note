package com.dasa.store.vo;

/**
 * @Class명: ManageEntrpsVo
 * @작성일: 2015.09.30
 * @작성자: 최수영
 * @설명: 관리업체 오브젝트
 */
public class ManageEntrpsVo {
     
	private String me_code = "";   
	private String cg_code = "";   
	private String me_nm = "";     
	private String me_memo = "";   
	private String use_at = "";    
	private String delete_at = ""; 
	private String regist_man = ""; 
	private String regist_de = ""; 
	private String updt_man = "";  
	private String updt_de = "";
	
	private String autoYn = "";
	private String sm_cnt = "";
	
	public String getMe_code() {
		return me_code;
	}
	public void setMe_code(String me_code) {
		this.me_code = me_code;
	}
	public String getCg_code() {
		return cg_code;
	}
	public void setCg_code(String cg_code) {
		this.cg_code = cg_code;
	}
	public String getMe_nm() {
		return me_nm;
	}
	public void setMe_nm(String me_nm) {
		this.me_nm = me_nm;
	}
	public String getMe_memo() {
		return me_memo;
	}
	public void setMe_memo(String me_memo) {
		this.me_memo = me_memo;
	}
	public String getUse_at() {
		return use_at;
	}
	public void setUse_at(String use_at) {
		this.use_at = use_at;
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
	
	
	public String getAutoYn() {
		return autoYn;
	}
	public void setAutoYn(String autoYn) {
		this.autoYn = autoYn;
	}
	public String getSm_cnt() {
		return sm_cnt;
	}
	public void setSm_cnt(String sm_cnt) {
		this.sm_cnt = sm_cnt;
	}   
	
}
