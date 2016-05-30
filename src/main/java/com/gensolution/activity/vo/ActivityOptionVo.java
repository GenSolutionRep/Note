package com.gensolution.activity.vo;

import java.util.List;
import java.util.Map;

public class ActivityOptionVo {
	
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
	
	private String oi_code;
	private String oi_parn_code;
	private String oi_nm;
	private String oi_nick_nm;
	private String oi_sort_ordr;
	private String oi_type;
	
	private String default_at;
	private String use_at;
	
	private String delete_at;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	private int cnt;
	
	private List<Map<String,String>>  optionArr;
	private List<Map<String,String>>  entArr;
	private List<Map<String,String>>  prdArr;
	
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getOi_parn_code() {
		return oi_parn_code;
	}
	public void setOi_parn_code(String oi_parn_code) {
		this.oi_parn_code = oi_parn_code;
	}
	public String getOi_type() {
		return oi_type;
	}
	public void setOi_type(String oi_type) {
		this.oi_type = oi_type;
	}
	public String getOi_code() {
		return oi_code;
	}
	public void setOi_code(String oi_code) {
		this.oi_code = oi_code;
	}
	public String getOi_nm() {
		return oi_nm;
	}
	public void setOi_nm(String oi_nm) {
		this.oi_nm = oi_nm;
	}
	public String getOi_nick_nm() {
		return oi_nick_nm;
	}
	public void setOi_nick_nm(String oi_nick_nm) {
		this.oi_nick_nm = oi_nick_nm;
	}
	public String getOi_sort_ordr() {
		return oi_sort_ordr;
	}
	public void setOi_sort_ordr(String oi_sort_ordr) {
		this.oi_sort_ordr = oi_sort_ordr;
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
	public List<Map<String, String>> getOptionArr() {
		return optionArr;
	}
	public void setOptionArr(List<Map<String, String>> optionArr) {
		this.optionArr = optionArr;
	}
	@Override
	public String toString() {
		return "ActivityOptionVo [oi_code=" + oi_code + ", oi_parn_code="
				+ oi_parn_code + ", oi_nm=" + oi_nm + ", oi_nick_nm="
				+ oi_nick_nm + ", oi_sort_ordr=" + oi_sort_ordr + ", oi_type="
				+ oi_type + ", default_at=" + default_at + ", use_at=" + use_at
				+ ", delete_at=" + delete_at + ", regist_man=" + regist_man
				+ ", regist_de=" + regist_de + ", updt_man=" + updt_man
				+ ", updt_de=" + updt_de + ", optionArr=" + optionArr
				+ ", entArr=" + entArr + ", prdArr=" + prdArr + "]";
	}
	
}
