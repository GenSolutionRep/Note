package com.dasa.communication.vo;

import java.util.List;
import java.util.Map;

public class NoticeMessageVo {
	
	private String saveType;
	private String no;
	private String bm_innb;
	private String bm_code;
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
	
	private String nm_innb;
	private String nm_code;
	private String cm_code;
	private String nm_massage;
	private String nm_recptn_at;
	private String nmi_innb;
	private String om_code;
	private String sm_code;
	private String c_name;
	
	private String em_no;
	private String em_nm;
	private String em_id;
	private String om_nm;
	private String parent_no;
	private String om_cd;
	private String sm_cd;
	private String sm_nm;
	private String receive_yn;
	private String total_cnt;
	private String ai_cnt;
	private int am_no;
	
	private String em_dty_code;
	private String em_push_id;
	private String em_device_type;
	
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
	public String getNm_innb() {
		return nm_innb;
	}
	public void setNm_innb(String nm_innb) {
		this.nm_innb = nm_innb;
	}
	public String getNm_code() {
		return nm_code;
	}
	public void setNm_code(String nm_code) {
		this.nm_code = nm_code;
	}
	public String getNm_massage() {
		return nm_massage;
	}
	public void setNm_massage(String nm_massage) {
		this.nm_massage = nm_massage;
	}
	public String getNm_recptn_at() {
		return nm_recptn_at;
	}
	public void setNm_recptn_at(String nm_recptn_at) {
		this.nm_recptn_at = nm_recptn_at;
	}
	public String getNmi_innb() {
		return nmi_innb;
	}
	public void setNmi_innb(String nmi_innb) {
		this.nmi_innb = nmi_innb;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public String getCm_code() {
		return cm_code;
	}
	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}
	public String getEm_nm() {
		return em_nm;
	}
	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}
	public String getEm_id() {
		return em_id;
	}
	public void setEm_id(String em_id) {
		this.em_id = em_id;
	}
	public String getOm_nm() {
		return om_nm;
	}
	public void setOm_nm(String om_nm) {
		this.om_nm = om_nm;
	}
	public String getParent_no() {
		return parent_no;
	}
	public void setParent_no(String parent_no) {
		this.parent_no = parent_no;
	}
	public String getOm_cd() {
		return om_cd;
	}
	public void setOm_cd(String om_cd) {
		this.om_cd = om_cd;
	}
	public String getSm_cd() {
		return sm_cd;
	}
	public void setSm_cd(String sm_cd) {
		this.sm_cd = sm_cd;
	}
	public List<Map<String, String>> getOmArr() {
		return omArr;
	}
	public void setOmArr(List<Map<String, String>> omArr) {
		this.omArr = omArr;
	}
	public List<Map<String, String>> getEmArr() {
		return emArr;
	}
	public void setEmArr(List<Map<String, String>> emArr) {
		this.emArr = emArr;
	}
	public List<Map<String, String>> getSmArr() {
		return smArr;
	}
	public void setSmArr(List<Map<String, String>> smArr) {
		this.smArr = smArr;
	}
	public String getSm_nm() {
		return sm_nm;
	}
	public void setSm_nm(String sm_nm) {
		this.sm_nm = sm_nm;
	}
	public String getAi_cnt() {
		return ai_cnt;
	}
	public String getOm_code() {
		return om_code;
	}
	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}
	public String getSm_code() {
		return sm_code;
	}
	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}
	public void setAi_cnt(String ai_cnt) {
		this.ai_cnt = ai_cnt;
	}
	
	public String getReceive_yn() {
		return receive_yn;
	}
	public void setReceive_yn(String receive_yn) {
		this.receive_yn = receive_yn;
	}
	public String getTotal_cnt() {
		return total_cnt;
	}
	public void setTotal_cnt(String total_cnt) {
		this.total_cnt = total_cnt;
	}
	
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
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
	public String getEm_dty_code() {
		return em_dty_code;
	}
	public void setEm_dty_code(String em_dty_code) {
		this.em_dty_code = em_dty_code;
	}
	@Override
	public String toString() {
		return "NoticeMessageVo [saveType=" + saveType + ", no=" + no
				+ ", bm_innb=" + bm_innb + ", bm_code=" + bm_code
				+ ", bm_all_ntcat=" + bm_all_ntcat + ", bm_upend_at="
				+ bm_upend_at + ", bm_sj=" + bm_sj + ", bm_cn=" + bm_cn
				+ ", bm_regist_ip=" + bm_regist_ip + ", atchmnfl_innb="
				+ atchmnfl_innb + ", delete_at=" + delete_at + ", regist_man="
				+ regist_man + ", regist_de=" + regist_de + ", updt_man="
				+ updt_man + ", updt_de=" + updt_de + ", nm_innb=" + nm_innb
				+ ", nm_code=" + nm_code + ", cm_code=" + cm_code
				+ ", nm_massage=" + nm_massage + ", nm_recptn_at="
				+ nm_recptn_at + ", nmi_innb=" + nmi_innb + ", om_code="
				+ om_code + ", sm_code=" + sm_code + ", em_no=" + em_no
				+ ", em_nm=" + em_nm + ", em_id=" + em_id + ", om_nm=" + om_nm
				+ ", parent_no=" + parent_no + ", om_cd=" + om_cd + ", sm_cd="
				+ sm_cd + ", sm_nm=" + sm_nm + ",c_name=" + c_name + ",receive_yn=" 
				+ receive_yn + ", total_cnt=" + total_cnt + ", em_push_id=" + em_push_id +", ai_cnt=" + ai_cnt
				+ ", am_no=" + am_no +    ", omArr=" + omArr + ", smArr=" + smArr
				+ ", emArr=" + emArr + "]";
	}
}

