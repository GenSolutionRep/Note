package com.vertexid.vo;

public class RequireVO {
	
	private String r_seq;
	private String r_id;
	private String r_title;
	private String r_tell;
	private String r_type;
	private String r_name;
	private String r_content;
	private String r_in_date;
	private String r_confirm;
	private String r_status;
	private String r_fild;
	private String r_value;
	private String r_is_del;
	
	public String getR_is_del() {
		return r_is_del;
	}
	public void setR_is_del(String r_is_del) {
		this.r_is_del = r_is_del;
	}
	public String getR_value() {
		return r_value;
	}
	public void setR_value(String r_value) {
		this.r_value = r_value;
	}
	public String getR_fild() {
		return r_fild;
	}
	public void setR_fild(String r_fild) {
		this.r_fild = r_fild;
	}
	public String getR_confirm() {
		return r_confirm;
	}
	public void setR_confirm(String r_confirm) {
		this.r_confirm = r_confirm;
	}
	public String getR_status() {
		return r_status;
	}
	public void setR_status(String r_status) {
		this.r_status = r_status;
	}
	public String getR_seq() {
		return r_seq;
	}
	public void setR_seq(String r_seq) {
		this.r_seq = r_seq;
	}
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public String getR_title() {
		return r_title;
	}
	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	public String getR_tell() {
		return r_tell;
	}
	public void setR_tell(String r_tell) {
		this.r_tell = r_tell;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getR_in_date() {
		return r_in_date;
	}
	public void setR_in_date(String r_in_date) {
		this.r_in_date = r_in_date;
	}

	@Override
	public String toString() {
		return "RequireVO [r_seq=" + r_seq + ", r_id=" + r_id + ", r_title="
				+ r_title + ", r_tell=" + r_tell + ", r_name=" + r_name
				+ ", r_type=" + r_type + ", r_content=" + r_content
				+ ", r_in_date=" + r_in_date + "]";
	}
}
