package com.vertexid.vo;

import java.util.List;
import java.util.Map;

public class OptionVo {
	
//	private String imp_code;
//	private String om_code;
//	private String sm_code;
//	private String imp_nm;
//	private String imp_define;
//	private String imp_sort_ordr;
//	
//	private String ime_code;
//	private String ime_nm;
//	private String ime_nike_nm;
//	private String ime_sort_ordr;
	
	private String code;
	private String nm;
	private String nike_nm;
	private String sort_ordr;
	private String default_at;
	private String use_at;
	private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	
	
	private List<Map<String,String>>  entArr;
	private List<Map<String,String>>  prdArr;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getNike_nm() {
		return nike_nm;
	}
	public void setNike_nm(String nike_nm) {
		this.nike_nm = nike_nm;
	}
	public String getSort_ordr() {
		return sort_ordr;
	}
	public void setSort_ordr(String sort_ordr) {
		this.sort_ordr = sort_ordr;
	}
	public String getDefault_at() {
		return default_at;
	}
	public void setDefault_at(String default_at) {
		this.default_at = default_at;
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
	public List<Map<String, String>> getEntArr() {
		return entArr;
	}
	public void setEntArr(List<Map<String, String>> entArr) {
		this.entArr = entArr;
	}
	public List<Map<String, String>> getPrdArr() {
		return prdArr;
	}
	public void setPrdArr(List<Map<String, String>> prdArr) {
		this.prdArr = prdArr;
	}
	@Override
	public String toString() {
		return "ActivityFixingPogOptionVo [code=" + code + ", nm=" + nm
				+ ", nike_nm=" + nike_nm + ", sort_ordr=" + sort_ordr
				+ ", default_at=" + default_at + ", use_at=" + use_at
				+ ", delete_at=" + delete_at + ", regist_man=" + regist_man
				+ ", regist_de=" + regist_de + ", updt_man=" + updt_man
				+ ", updt_de=" + updt_de + ", entArr=" + entArr + ", prdArr="
				+ prdArr + "]";
	}
	
	
}
