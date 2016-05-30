package com.dasa.communication.vo;

import java.util.List;
import java.util.Map;

public class NoticeVo {
	
	private String saveType;
	private String no;
	private String bm_innb;
	private String bm_code;
	private String cm_code;
	private String tm_code;
	private String bm_all_ntcat;
	private String bm_upend_at;
	private String bm_sj;
	private String bm_cn;
	private String bm_regist_ip;
	private String atchmnfl_innb;
	private String delete_at;
	private String regist_man;   
	private String regist_de;
	private String updt_man;
	private String updt_de;
	private String em_dty_code;
	private String insert_Type;
	private String em_no;
	private String ai_cnt;
	private int am_no;
	private String auth_code;
	private String fixRnd;
	
	private List<Map<String,String>> omArr;
	private List<Map<String,String>> smArr;
	private List<Map<String,String>> emArr;
	
	public int getAm_no() {
		return am_no;
	}
	public void setAm_no(int am_no) {
		this.am_no = am_no;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getBm_innb() {
		return bm_innb;
	}
	public void setBm_innb(String bm_innb) {
		this.bm_innb = bm_innb;
	}
	public String getBm_code() {
		return bm_code;
	}
	public void setBm_code(String bm_code) {
		this.bm_code = bm_code;
	}
	public String getBm_all_ntcat() {
		return bm_all_ntcat;
	}
	public void setBm_all_ntcat(String bm_all_ntcat) {
		this.bm_all_ntcat = bm_all_ntcat;
	}
	public String getBm_upend_at() {
		return bm_upend_at;
	}
	public void setBm_upend_at(String bm_upend_at) {
		this.bm_upend_at = bm_upend_at;
	}
	public String getBm_sj() {
		return bm_sj;
	}
	public void setBm_sj(String bm_sj) {
		this.bm_sj = bm_sj;
	}
	public String getBm_cn() {
		return bm_cn;
	}
	public String getTm_code() {
		return tm_code;
	}
	public void setTm_code(String tm_code) {
		this.tm_code = tm_code;
	}
	public void setBm_cn(String bm_cn) {
		this.bm_cn = bm_cn;
	}
	public String getBm_regist_ip() {
		return bm_regist_ip;
	}
	public void setBm_regist_ip(String bm_regist_ip) {
		this.bm_regist_ip = bm_regist_ip;
	}
	public String getAtchmnfl_innb() {
		return atchmnfl_innb;
	}
	public void setAtchmnfl_innb(String atchmnfl_innb) {
		this.atchmnfl_innb = atchmnfl_innb;
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
	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}
	public List<Map<String, String>> getOmArr() {
		return omArr;
	}
	public void setOmArr(List<Map<String, String>> omArr) {
		this.omArr = omArr;
	}
	public List<Map<String, String>> getSmArr() {
		return smArr;
	}
	public void setSmArr(List<Map<String, String>> smArr) {
		this.smArr = smArr;
	}
	public String getAi_cnt() {
		return ai_cnt;
	}
	public String getAuth_code() {
		return auth_code;
	}
	public String getInsert_Type() {
		return insert_Type;
	}
	public String getFixRnd() {
		return fixRnd;
	}
	public void setFixRnd(String fixRnd) {
		this.fixRnd = fixRnd;
	}
	public void setInsert_Type(String insert_Type) {
		this.insert_Type = insert_Type;
	}
	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}
	public void setAi_cnt(String ai_cnt) {
		this.ai_cnt = ai_cnt;
	}
	public String getEm_dty_code() {
		return em_dty_code;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public void setEm_dty_code(String em_dty_code) {
		this.em_dty_code = em_dty_code;
	}
	
	public List<Map<String, String>> getEmArr() {
		return emArr;
	}
	public void setEmArr(List<Map<String, String>> emArr) {
		this.emArr = emArr;
	}
	
	@Override
	public String toString() {
		return "NoticeVo [saveType=" + saveType + ", no=" + no + ", bm_innb=" + bm_innb + ", bm_code=" + bm_code
				+ ", cm_code=" + cm_code + ", tm_code=" + tm_code + ", bm_all_ntcat=" + bm_all_ntcat + ", bm_upend_at="
				+ bm_upend_at + ", bm_sj=" + bm_sj + ", bm_cn=" + bm_cn + ", bm_regist_ip=" + bm_regist_ip
				+ ", atchmnfl_innb=" + atchmnfl_innb + ", delete_at=" + delete_at + ", regist_man=" + regist_man
				+ ", regist_de=" + regist_de + ", updt_man=" + updt_man + ", updt_de=" + updt_de + ", em_dty_code="
				+ em_dty_code + ", insert_Type=" + insert_Type + ", em_no=" + em_no + ", ai_cnt=" + ai_cnt + ", am_no="
				+ am_no + ", auth_code=" + auth_code + ", fixRnd=" + fixRnd + ", omArr=" + omArr + ", smArr=" + smArr
				+ ", emArr=" + emArr + "]";
	}
}

