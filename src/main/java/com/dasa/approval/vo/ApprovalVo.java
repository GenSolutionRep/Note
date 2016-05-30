package com.dasa.approval.vo;

public class ApprovalVo {

	private String flag = "";
	private String no;
	private String am_code = "";
	private String ad_seq = "";
	private String ad_type = "";
	private String ad_type_nm = "";
	private String ad_date_from = "";
	private String ad_date_from_hhmm = "";
	private String ad_date_to = "";
	private String ad_date_to_hhmm = "";
	private String ad_reason = "";
	private String am_no = "";
	private String delete_at = "";
	private String regist_man = "";
	private String regist_de = "";
	private String updt_man = "";
	private String updt_de = "";

	//Mst 컬럼
	private String om_code = "";
	private String om_nm = "";
	private String em_no = "";
	private String em_nm = "";
	private String am_approver_em_no = "";
	private String am_approver_em_nm = "";
	private String am_status = "";
	private String am_status_nm = "";
	private String am_approval_date = "";
	private String attach_cnt = "";
	private String em_dty_code = "";
	private String em_dty_nm = "";

	//REJECT 컬럼
	private String ar_seq = "";
	private String ar_em_no = "";
	private String ar_reason = "";
	private String reject_date = "";

	//out parameter
	private String res_am_code = "";
	private String res_code = "";
	private String res_msg = "";
	
	private String kind = "";//상신(SEND),수신(RECEIVE)
	private String type = "";//신청(APPLY),승인(APPROVAL),반려(REJECT)
	
	private String em_push_id = "";
	private String em_device_type = "";
	
	

	public String getEm_push_id() {
		return em_push_id;
	}

	public void setEm_push_id(String em_push_id) {
		this.em_push_id = em_push_id;
	}

	public String getEm_device_type() {
		return em_device_type;
	}

	public void setEm_device_type(String em_device_type) {
		this.em_device_type = em_device_type;
	}

	public String getFlag() {
		return flag;
	}

	public String getNo() {
		return no;
	}

	public String getAm_code() {
		return am_code;
	}

	public String getAd_seq() {
		return ad_seq;
	}

	public String getAd_type() {
		return ad_type;
	}

	public String getAd_type_nm() {
		return ad_type_nm;
	}

	public String getAd_date_from() {
		return ad_date_from;
	}

	public String getAd_date_from_hhmm() {
		return ad_date_from_hhmm;
	}

	public String getAd_date_to() {
		return ad_date_to;
	}

	public String getAd_date_to_hhmm() {
		return ad_date_to_hhmm;
	}

	public String getAd_reason() {
		return ad_reason;
	}

	public String getAm_no() {
		return am_no;
	}

	public String getDelete_at() {
		return delete_at;
	}

	public String getRegist_man() {
		return regist_man;
	}

	public String getRegist_de() {
		return regist_de;
	}

	public String getUpdt_man() {
		return updt_man;
	}

	public String getUpdt_de() {
		return updt_de;
	}

	public String getOm_code() {
		return om_code;
	}

	public String getOm_nm() {
		return om_nm;
	}

	public String getEm_no() {
		return em_no;
	}

	public String getEm_nm() {
		return em_nm;
	}

	public String getAm_approver_em_no() {
		return am_approver_em_no;
	}

	public String getAm_approver_em_nm() {
		return am_approver_em_nm;
	}

	public String getAm_status() {
		return am_status;
	}

	public String getAm_status_nm() {
		return am_status_nm;
	}

	public String getAm_approval_date() {
		return am_approval_date;
	}

	public String getAttach_cnt() {
		return attach_cnt;
	}

	public String getEm_dty_code() {
		return em_dty_code;
	}

	public String getEm_dty_nm() {
		return em_dty_nm;
	}

	public String getAr_seq() {
		return ar_seq;
	}

	public String getAr_em_no() {
		return ar_em_no;
	}

	public String getAr_reason() {
		return ar_reason;
	}

	public String getReject_date() {
		return reject_date;
	}

	public String getRes_am_code() {
		return res_am_code;
	}

	public String getRes_code() {
		return res_code;
	}

	public String getRes_msg() {
		return res_msg;
	}

	public String getKind() {
		return kind;
	}

	public String getType() {
		return type;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setAm_code(String am_code) {
		this.am_code = am_code;
	}

	public void setAd_seq(String ad_seq) {
		this.ad_seq = ad_seq;
	}

	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}

	public void setAd_type_nm(String ad_type_nm) {
		this.ad_type_nm = ad_type_nm;
	}

	public void setAd_date_from(String ad_date_from) {
		this.ad_date_from = ad_date_from;
	}

	public void setAd_date_from_hhmm(String ad_date_from_hhmm) {
		this.ad_date_from_hhmm = ad_date_from_hhmm;
	}

	public void setAd_date_to(String ad_date_to) {
		this.ad_date_to = ad_date_to;
	}

	public void setAd_date_to_hhmm(String ad_date_to_hhmm) {
		this.ad_date_to_hhmm = ad_date_to_hhmm;
	}

	public void setAd_reason(String ad_reason) {
		this.ad_reason = ad_reason;
	}

	public void setAm_no(String am_no) {
		this.am_no = am_no;
	}

	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}

	public void setRegist_man(String regist_man) {
		this.regist_man = regist_man;
	}

	public void setRegist_de(String regist_de) {
		this.regist_de = regist_de;
	}

	public void setUpdt_man(String updt_man) {
		this.updt_man = updt_man;
	}

	public void setUpdt_de(String updt_de) {
		this.updt_de = updt_de;
	}

	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}

	public void setOm_nm(String om_nm) {
		this.om_nm = om_nm;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}

	public void setAm_approver_em_no(String am_approver_em_no) {
		this.am_approver_em_no = am_approver_em_no;
	}

	public void setAm_approver_em_nm(String am_approver_em_nm) {
		this.am_approver_em_nm = am_approver_em_nm;
	}

	public void setAm_status(String am_status) {
		this.am_status = am_status;
	}

	public void setAm_status_nm(String am_status_nm) {
		this.am_status_nm = am_status_nm;
	}

	public void setAm_approval_date(String am_approval_date) {
		this.am_approval_date = am_approval_date;
	}

	public void setAttach_cnt(String attach_cnt) {
		this.attach_cnt = attach_cnt;
	}

	public void setEm_dty_code(String em_dty_code) {
		this.em_dty_code = em_dty_code;
	}

	public void setEm_dty_nm(String em_dty_nm) {
		this.em_dty_nm = em_dty_nm;
	}

	public void setAr_seq(String ar_seq) {
		this.ar_seq = ar_seq;
	}

	public void setAr_em_no(String ar_em_no) {
		this.ar_em_no = ar_em_no;
	}

	public void setAr_reason(String ar_reason) {
		this.ar_reason = ar_reason;
	}

	public void setReject_date(String reject_date) {
		this.reject_date = reject_date;
	}

	public void setRes_am_code(String res_am_code) {
		this.res_am_code = res_am_code;
	}

	public void setRes_code(String res_code) {
		this.res_code = res_code;
	}

	public void setRes_msg(String res_msg) {
		this.res_msg = res_msg;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ApprovalVo[");
		sb.append("flag=" + flag + ",");
		sb.append("no=" + no + ",");
		sb.append("am_code=" + am_code + ",");
		sb.append("ad_seq=" + ad_seq + ",");
		sb.append("ad_type=" + ad_type + ",");
		sb.append("ad_type_nm=" + ad_type_nm + ",");
		sb.append("ad_date_from=" + ad_date_from + ",");
		sb.append("ad_date_from_hhmm=" + ad_date_from_hhmm + ",");
		sb.append("ad_date_to=" + ad_date_to + ",");
		sb.append("ad_date_to_hhmm=" + ad_date_to_hhmm + ",");
		sb.append("ad_reason=" + ad_reason + ",");
		sb.append("am_no=" + am_no + ",");
		sb.append("delete_at=" + delete_at + ",");
		sb.append("regist_man=" + regist_man + ",");
		sb.append("regist_de=" + regist_de + ",");
		sb.append("updt_man=" + updt_man + ",");
		sb.append("updt_de=" + updt_de + ",");
		sb.append("om_code=" + om_code + ",");
		sb.append("om_nm=" + om_nm + ",");
		sb.append("em_no=" + em_no + ",");
		sb.append("em_nm=" + em_nm + ",");
		sb.append("am_approver_em_no=" + am_approver_em_no + ",");
		sb.append("am_approver_em_nm=" + am_approver_em_nm + ",");
		sb.append("am_status=" + am_status + ",");
		sb.append("am_status_nm=" + am_status_nm + ",");
		sb.append("am_approval_date=" + am_approval_date + ",");
		sb.append("attach_cnt=" + attach_cnt + ",");
		sb.append("em_dty_code=" + em_dty_code + ",");
		sb.append("em_dty_nm=" + em_dty_nm + ",");
		sb.append("ar_seq=" + ar_seq + ",");
		sb.append("ar_em_no=" + ar_em_no + ",");
		sb.append("ar_reason=" + ar_reason + ",");
		sb.append("reject_date=" + reject_date + ",");
		sb.append("res_am_code=" + res_am_code + ",");
		sb.append("res_code=" + res_code + ",");
		sb.append("res_msg=" + res_msg + ",");
		sb.append("kind=" + kind + ",");
		sb.append("type=" + type);
		sb.append("]");
		return sb.toString();
	}

}
