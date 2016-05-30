package com.vertexid.vo;

/**
 * @Class명: CompanyVo
 * @작성일: 2015.09.18
 * @작성자: 최수영
 * @설명: 회사관리 오브젝트
 */
public class CompanyVo {
       
	private String cm_code;
	private String cm_nm;
	private String use_at;
	private String cm_sort_ordr;
	private String cm_memo;
	private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	
	public String getUse_at() {
		return use_at;
	}
	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}
	public String getCm_nm() {
		return cm_nm;
	}
	public void setCm_nm(String cm_nm) {
		this.cm_nm = cm_nm;
	}
	public String getCm_sort_ordr() {
		return cm_sort_ordr;
	}
	public void setCm_sort_ordr(String cm_sort_ordr) {
		this.cm_sort_ordr = cm_sort_ordr;
	}
	public String getCm_memo() {
		return cm_memo;
	}
	public void setCm_memo(String cm_memo) {
		this.cm_memo = cm_memo;
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
}
