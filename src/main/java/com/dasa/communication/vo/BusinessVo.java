package com.dasa.communication.vo;

import java.util.List;
import java.util.Map;

public class BusinessVo {
	
	private String ba_innb;
	private String bm_innb;
	private String em_no;
	private String ba_answer;
	private String ba_recptn_de;
	private String delete_at;
	private String regist_man;
	private String resist_de;
	private String updt_man;
	private String updt_de;
	 
	private List<Map<String,String>> emArr;
	
	 
	public String getBa_innb() {
		return ba_innb;
	}


	public void setBa_innb(String ba_innb) {
		this.ba_innb = ba_innb;
	}


	public String getBm_innb() {
		return bm_innb;
	}


	public void setBm_innb(String bm_innb) {
		this.bm_innb = bm_innb;
	}


	public String getEm_no() {
		return em_no;
	}


	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}


	public String getBa_answer() {
		return ba_answer;
	}


	public void setBa_answer(String ba_answer) {
		this.ba_answer = ba_answer;
	}


	public String getBa_recptn_de() {
		return ba_recptn_de;
	}


	public void setBa_recptn_de(String ba_recptn_de) {
		this.ba_recptn_de = ba_recptn_de;
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


	public String getResist_de() {
		return resist_de;
	}


	public void setResist_de(String resist_de) {
		this.resist_de = resist_de;
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


	public List<Map<String, String>> getEmArr() {
		return emArr;
	}


	public void setEmArr(List<Map<String, String>> emArr) {
		this.emArr = emArr;
	}


	@Override
	public String toString() {
		return "BusinessOrderVo [ba_innb=" + ba_innb + ", bm_innb="
				+ bm_innb + ", em_no=" + em_no + ", ba_answer="
				+ ba_answer + ", ba_recptn_de=" + ba_recptn_de + ", delete_at="
				+ delete_at + ", regist_man=" + regist_man + ", regist_de="
				+ resist_de + ", updt_man=" + updt_man + ", updt_de=" + updt_de
				+ ", emArr=" + emArr + "]";
	}
	
}

