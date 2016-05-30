package com.vertexid.vo;

import java.util.List;
import java.util.Map;

public class AuthVo {
	
	private String m_cm_code;
	private String ma_group_seq;
	private String m_seq;
	private String m_no;
	private String m_nm;
	private String m_parent_no;
	private String ma_visible; 
	private String ma_insert;	 
	private String ma_update;	 
	private String ma_del;		 
	private String ma_down;	 
	private String ma_etc;		 
	private String ma_in_id;	 
	private String ma_in_date;	 
	private String ma_up_id;	 
	private String ma_up_date;
	private String m_depth;
	
	private String login_id;	 
	
	private List<Map<String,String>> authArr;
	private List<Map<String,String>> empArr;
	
	
	
	public String getM_cm_code() {
		return m_cm_code;
	}
	public void setM_cm_code(String m_cm_code) {
		this.m_cm_code = m_cm_code;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getM_parent_no() {
		return m_parent_no;
	}
	public void setM_parent_no(String m_parent_no) {
		this.m_parent_no = m_parent_no;
	}
	public String getM_depth() {
		return m_depth;
	}
	public void setM_depth(String m_depth) {
		this.m_depth = m_depth;
	}
	public String getM_nm() {
		return m_nm;
	}
	public void setM_nm(String m_nm) {
		this.m_nm = m_nm;
	}
	public String getMa_group_seq() {
		return ma_group_seq;
	}
	public void setMa_group_seq(String ma_group_seq) {
		this.ma_group_seq = ma_group_seq;
	}
	public String getM_seq() {
		return m_seq;
	}
	public void setM_seq(String m_seq) {
		this.m_seq = m_seq;
	}
	public String getM_no() {
		return m_no;
	}
	public void setM_no(String m_no) {
		this.m_no = m_no;
	}
	public String getMa_visible() {
		return ma_visible;
	}
	public void setMa_visible(String ma_visible) {
		this.ma_visible = ma_visible;
	}
	public String getMa_insert() {
		return ma_insert;
	}
	public void setMa_insert(String ma_insert) {
		this.ma_insert = ma_insert;
	}
	public String getMa_update() {
		return ma_update;
	}
	public void setMa_update(String ma_update) {
		this.ma_update = ma_update;
	}
	public String getMa_del() {
		return ma_del;
	}
	public void setMa_del(String ma_del) {
		this.ma_del = ma_del;
	}
	public String getMa_down() {
		return ma_down;
	}
	public void setMa_down(String ma_down) {
		this.ma_down = ma_down;
	}
	public String getMa_etc() {
		return ma_etc;
	}
	public void setMa_etc(String ma_etc) {
		this.ma_etc = ma_etc;
	}
	public String getMa_in_id() {
		return ma_in_id;
	}
	public void setMa_in_id(String ma_in_id) {
		this.ma_in_id = ma_in_id;
	}
	public String getMa_in_date() {
		return ma_in_date;
	}
	public void setMa_in_date(String ma_in_date) {
		this.ma_in_date = ma_in_date;
	}
	public String getMa_up_id() {
		return ma_up_id;
	}
	public void setMa_up_id(String ma_up_id) {
		this.ma_up_id = ma_up_id;
	}
	public String getMa_up_date() {
		return ma_up_date;
	}
	public void setMa_up_date(String ma_up_date) {
		this.ma_up_date = ma_up_date;
	}
	public List<Map<String, String>> getAuthArr() {
		return authArr;
	}
	public void setAuthArr(List<Map<String, String>> authArr) {
		this.authArr = authArr;
	}
	public List<Map<String, String>> getEmpArr() {
		return empArr;
	}
	public void setEmpArr(List<Map<String, String>> empArr) {
		this.empArr = empArr;
	}
	@Override
	public String toString() {
		return "AuthVo [ma_group_seq=" + ma_group_seq + ", m_seq=" + m_seq
				+ ", m_no=" + m_no + ", m_nm=" + m_nm + ", m_parent_no="
				+ m_parent_no + ", ma_visible=" + ma_visible + ", ma_insert="
				+ ma_insert + ", ma_update=" + ma_update + ", ma_del=" + ma_del
				+ ", ma_down=" + ma_down + ", ma_etc=" + ma_etc + ", ma_in_id="
				+ ma_in_id + ", ma_in_date=" + ma_in_date + ", ma_up_id="
				+ ma_up_id + ", ma_up_date=" + ma_up_date + ", m_depth="
				+ m_depth + ", authArr=" + authArr + ", empArr=" + empArr + "]";
	}
	
	

}
