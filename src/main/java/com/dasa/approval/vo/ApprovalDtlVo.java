package com.dasa.approval.vo;

public class ApprovalDtlVo {
	
	private String am_code ="";           
	private String ad_seq ="";            
	private String ad_type ="";           
	private String ad_date_from ="";       
	private String ad_date_from_hhmm =""; 
	private String ad_date_to ="";        
	private String ad_date_to_hhmm ="";   
	private String ad_reason ="";         
	private String am_no ="";             
	private String delete_at ="";         
	private String regist_man ="";        
	private String regist_de ="";         
	private String updt_man ="";          
	private String updt_de  ="";    
	
	//Mst 컬럼
	private String em_no = "";                  
	private String am_approver_em_no = "";     
	private String am_status = "";
	private String am_approver_date = ""; 
	
	//REJECT 컬럼
	private String  ar_seq = "";                 
	private String  ar_em_no = "";                  
	private String  ar_reason = "";        
	
	//out parameter
	private String res_code="";
    private String res_msg="";
	
	public String getAm_code() {
		return am_code;
	}
	public void setAm_code(String am_code) {
		this.am_code = am_code;
	}
	public String getAd_seq() {
		return ad_seq;
	}
	public void setAd_seq(String ad_seq) {
		this.ad_seq = ad_seq;
	}
	public String getAd_type() {
		return ad_type;
	}
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	public String getAd_date_from() {
		return ad_date_from;
	}
	public void setAd_date_from(String ad_date_from) {
		this.ad_date_from = ad_date_from;
	}
	public String getAd_date_from_hhmm() {
		return ad_date_from_hhmm;
	}
	public void setAd_date_from_hhmm(String ad_date_from_hhmm) {
		this.ad_date_from_hhmm = ad_date_from_hhmm;
	}
	public String getAd_date_to() {
		return ad_date_to;
	}
	public void setAd_date_to(String ad_date_to) {
		this.ad_date_to = ad_date_to;
	}
	public String getAd_date_to_hhmm() {
		return ad_date_to_hhmm;
	}
	public void setAd_date_to_hhmm(String ad_date_to_hhmm) {
		this.ad_date_to_hhmm = ad_date_to_hhmm;
	}
	public String getAd_reason() {
		return ad_reason;
	}
	public void setAd_reason(String ad_reason) {
		this.ad_reason = ad_reason;
	}
	public String getAm_no() {
		return am_no;
	}
	public void setAm_no(String am_no) {
		this.am_no = am_no;
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
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getAm_approver_em_no() {
		return am_approver_em_no;
	}
	public void setAm_approver_em_no(String am_approver_em_no) {
		this.am_approver_em_no = am_approver_em_no;
	}
	public String getAm_status() {
		return am_status;
	}
	public void setAm_status(String am_status) {
		this.am_status = am_status;
	}
	public String getAm_approver_date() {
		return am_approver_date;
	}
	public void setAm_approver_date(String am_approver_date) {
		this.am_approver_date = am_approver_date;
	}    

}
