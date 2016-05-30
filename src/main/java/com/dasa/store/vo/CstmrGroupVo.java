package com.dasa.store.vo;

/**
 * @Class명: CstmrGroupVo
 * @작성일: 2015.09.30
 * @작성자: 최수영
 * @설명: 고객그룹 오브젝트
 */
public class CstmrGroupVo {
       
	private String cg_code = "";       
	private String cg_nm = "";      
	private String cg_memo = "";     
	private String use_at = "";     
	private String delete_at = "";   
	private String regist_man = "";  
	private String regist_de = "";  
	private String updt_man = "";    
	private String updt_de = "";
	
	private String autoYn = "";
	private String me_cnt = "";
	
	
	public String getCg_code() {
		return cg_code;
	}
	public void setCg_code(String cg_code) {
		this.cg_code = cg_code;
	}
	public String getCg_nm() {
		return cg_nm;
	}
	public void setCg_nm(String cg_nm) {
		this.cg_nm = cg_nm;
	}
	public String getCg_memo() {
		return cg_memo;
	}
	public void setCg_memo(String cg_memo) {
		this.cg_memo = cg_memo;
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
	public String getMe_cnt() {
		return me_cnt;
	}
	public void setMe_cnt(String me_cnt) {
		this.me_cnt = me_cnt;
	}  
	
	
}
