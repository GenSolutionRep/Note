package com.dasa.communication.vo;

import java.util.List;
import java.util.Map;

public class BusinessOrderVo {

	private String type;
	private String saveType;
	private String no;
	private String bm_innb;
	private String bt_innb;
	private String bm_code;
	private String cm_code;
	private String bm_all_ntcat;
	private String bm_upend_at;
	private String bm_sj;
	private String bm_cn;
	private String bm_regist_ip;
	private String atchmnfl_innb;
	private String delete_at;
	private String ba_answer;
	private String receive_yn;
	private String total_cnt;
	private String regist_man;
	private String regist_de;
	private String updt_man;
	private String updt_de;
	private String em_no;
	private String om_code;
	private String sm_code;
	private String tm_code;
	private String ai_cnt;
	private int am_no;
	private String em_nm;
	private String br_answer;
	private String br_receive_date;
	private String em_push_id;
	private String em_device_type;
	private String em_dty_code;
	private String sender_em_no;
	private String receiver_em_no;
	private String biz_auth_code;
	private int auth_flag;
	private String auth_Code;
	private String insert_Type;
	private String fixRnd;
	
	private List<Map<String, String>> omArr;
	private List<Map<String, String>> smArr;
	private List<Map<String, String>> emArr;

	public String getType() {
		return type;
	}

	public String getSaveType() {
		return saveType;
	}

	public String getNo() {
		return no;
	}

	public String getBm_innb() {
		return bm_innb;
	}

	public String getBt_innb() {
		return bt_innb;
	}

	public String getBm_code() {
		return bm_code;
	}

	public String getCm_code() {
		return cm_code;
	}

	public String getBm_all_ntcat() {
		return bm_all_ntcat;
	}

	public String getBm_upend_at() {
		return bm_upend_at;
	}

	public String getBm_sj() {
		return bm_sj;
	}

	public String getBm_cn() {
		return bm_cn;
	}

	public String getBm_regist_ip() {
		return bm_regist_ip;
	}

	public String getAtchmnfl_innb() {
		return atchmnfl_innb;
	}

	public String getDelete_at() {
		return delete_at;
	}

	public String getBa_answer() {
		return ba_answer;
	}

	public String getReceive_yn() {
		return receive_yn;
	}

	public String getTotal_cnt() {
		return total_cnt;
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

	public String getEm_no() {
		return em_no;
	}

	public String getOm_code() {
		return om_code;
	}

	public String getSm_code() {
		return sm_code;
	}

	public String getAi_cnt() {
		return ai_cnt;
	}

	public int getAm_no() {
		return am_no;
	}

	public String getEm_nm() {
		return em_nm;
	}

	public String getBr_answer() {
		return br_answer;
	}

	public String getBr_receive_date() {
		return br_receive_date;
	}

	public String getEm_push_id() {
		return em_push_id;
	}

	public String getEm_dty_code() {
		return em_dty_code;
	}

	public String getSender_em_no() {
		return sender_em_no;
	}

	public String getReceiver_em_no() {
		return receiver_em_no;
	}

	public String getBiz_auth_code() {
		return biz_auth_code;
	}

	public int getAuth_flag() {
		return auth_flag;
	}

	public List<Map<String, String>> getOmArr() {
		return omArr;
	}

	public List<Map<String, String>> getSmArr() {
		return smArr;
	}

	public List<Map<String, String>> getEmArr() {
		return emArr;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setBm_innb(String bm_innb) {
		this.bm_innb = bm_innb;
	}

	public void setBt_innb(String bt_innb) {
		this.bt_innb = bt_innb;
	}

	public void setBm_code(String bm_code) {
		this.bm_code = bm_code;
	}

	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}

	public void setBm_all_ntcat(String bm_all_ntcat) {
		this.bm_all_ntcat = bm_all_ntcat;
	}

	public void setBm_upend_at(String bm_upend_at) {
		this.bm_upend_at = bm_upend_at;
	}

	public void setBm_sj(String bm_sj) {
		this.bm_sj = bm_sj;
	}

	public void setBm_cn(String bm_cn) {
		this.bm_cn = bm_cn;
	}

	public void setBm_regist_ip(String bm_regist_ip) {
		this.bm_regist_ip = bm_regist_ip;
	}

	public void setAtchmnfl_innb(String atchmnfl_innb) {
		this.atchmnfl_innb = atchmnfl_innb;
	}

	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}

	public void setBa_answer(String ba_answer) {
		this.ba_answer = ba_answer;
	}

	public void setReceive_yn(String receive_yn) {
		this.receive_yn = receive_yn;
	}

	public void setTotal_cnt(String total_cnt) {
		this.total_cnt = total_cnt;
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

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}

	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}

	public void setAi_cnt(String ai_cnt) {
		this.ai_cnt = ai_cnt;
	}

	public void setAm_no(int am_no) {
		this.am_no = am_no;
	}

	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}

	public void setBr_answer(String br_answer) {
		this.br_answer = br_answer;
	}

	public void setBr_receive_date(String br_receive_date) {
		this.br_receive_date = br_receive_date;
	}

	public void setEm_push_id(String em_push_id) {
		this.em_push_id = em_push_id;
	}

	public void setEm_dty_code(String em_dty_code) {
		this.em_dty_code = em_dty_code;
	}

	public void setSender_em_no(String sender_em_no) {
		this.sender_em_no = sender_em_no;
	}

	public void setReceiver_em_no(String receiver_em_no) {
		this.receiver_em_no = receiver_em_no;
	}

	public void setBiz_auth_code(String biz_auth_code) {
		this.biz_auth_code = biz_auth_code;
	}

	public String getEm_device_type() {
		return em_device_type;
	}

	public void setEm_device_type(String em_device_type) {
		this.em_device_type = em_device_type;
	}

	public void setAuth_flag(int auth_flag) {
		this.auth_flag = auth_flag;
	}

	public String getAuth_Code() {
		return auth_Code;
	}

	public void setAuth_Code(String auth_Code) {
		this.auth_Code = auth_Code;
	}


	public String getInsert_Type() {
		return insert_Type;
	}

	public String getFixRnd() {
		return fixRnd;
	}

	public String getTm_code() {
		return tm_code;
	}

	public void setTm_code(String tm_code) {
		this.tm_code = tm_code;
	}

	public void setFixRnd(String fixRnd) {
		this.fixRnd = fixRnd;
	}

	public void setInsert_Type(String insert_Type) {
		this.insert_Type = insert_Type;
	}

	public void setOmArr(List<Map<String, String>> omArr) {
		this.omArr = omArr;
	}

	public void setSmArr(List<Map<String, String>> smArr) {
		this.smArr = smArr;
	}

	public void setEmArr(List<Map<String, String>> emArr) {
		this.emArr = emArr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BusinessOrderVo[");
		sb.append("type=" + type + ",");
		sb.append("saveType=" + saveType + ",");
		sb.append("no=" + no + ",");
		sb.append("bm_innb=" + bm_innb + ",");
		sb.append("bt_innb=" + bt_innb + ",");
		sb.append("bm_code=" + bm_code + ",");
		sb.append("cm_code=" + cm_code + ",");
		sb.append("bm_all_ntcat=" + bm_all_ntcat + ",");
		sb.append("bm_upend_at=" + bm_upend_at + ",");
		sb.append("bm_sj=" + bm_sj + ",");
		sb.append("bm_cn=" + bm_cn + ",");
		sb.append("bm_regist_ip=" + bm_regist_ip + ",");
		sb.append("atchmnfl_innb=" + atchmnfl_innb + ",");
		sb.append("delete_at=" + delete_at + ",");
		sb.append("ba_answer=" + ba_answer + ",");
		sb.append("receive_yn=" + receive_yn + ",");
		sb.append("total_cnt=" + total_cnt + ",");
		sb.append("regist_man=" + regist_man + ",");
		sb.append("regist_de=" + regist_de + ",");
		sb.append("updt_man=" + updt_man + ",");
		sb.append("updt_de=" + updt_de + ",");
		sb.append("em_no=" + em_no + ",");
		sb.append("om_code=" + om_code + ",");
		sb.append("sm_code=" + sm_code + ",");
		sb.append("ai_cnt=" + ai_cnt + ",");
		sb.append("am_no=" + am_no + ",");
		sb.append("em_nm=" + em_nm + ",");
		sb.append("biz_auth_code=" + biz_auth_code + ",");
		sb.append("br_answer=" + br_answer + ",");
		sb.append("br_receive_date=" + br_receive_date + ",");
		sb.append("em_push_id=" + em_push_id + ",");
		sb.append("em_dty_code=" + em_dty_code + ",");
		sb.append("sender_em_no=" + sender_em_no + ",");
		sb.append("receiver_em_no=" + receiver_em_no + ",");
		sb.append("auth_flag=" + auth_flag + ",");
		sb.append("fixRnd=" + fixRnd + ",");
		sb.append("tm_code=" + tm_code + ",");
		sb.append("insert_Type=" + insert_Type + ",");
		sb.append("omArr=" + omArr + ",");
		sb.append("smArr=" + smArr + ",");
		sb.append("emArr=" + emArr);
		sb.append("]");
		return sb.toString();
	}

}
