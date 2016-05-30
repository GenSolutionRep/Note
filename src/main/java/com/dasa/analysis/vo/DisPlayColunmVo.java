package com.dasa.analysis.vo;


public class DisPlayColunmVo {
	private String item_id;
	private String group_id;
	private String item_nm;
	private String group_nm;
	
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public String getGroup_nm() {
		return group_nm;
	}
	public void setGroup_nm(String group_nm) {
		this.group_nm = group_nm;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	@Override
	public String toString() {
		return "DisPlayColunmVo [item_id=" + item_id + ", group_id=" + group_id
				+ "]";
	}
	
	
}
