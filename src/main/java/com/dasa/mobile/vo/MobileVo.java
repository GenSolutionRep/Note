package com.dasa.mobile.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * @파일명: MobileVo.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 모바일 Vo
 */
public class MobileVo {

	private String cg_code;//고객그룹코드
	private String cg_nm;//고객그룹명
	private String me_code;//관리업체코드
	private String me_nm;//관리업체명
	private String sm_code;//매장코드
	private String sm_nm;//매장명
	private String cm_code;//회사코드
	private String cm_nm;//회사명
	private String om_code;//지점코드
	private String om_nm;//지점명
	private String em_no;//사원번호
	private String em_id;//사원ID
	private String em_nm;//사원명
	private String em_dty_code;//직무코드
	private String em_type_nm;//사원유형(담당사원,보조사원)
	private String sm_la;//매장 위도
	private String sm_lo;//매장 경도
	private int am_no;//첨부파일 고유번호
	private int ai_no;//실제파일 고유번호
	private String ai_path;//실제파일 경로
	private String ai_nm;//실제파일 이름
	private String ai_size;//실제파일 크기
	private MultipartFile[] files;//첨부파일
	private String type;//유형
	private String flag;//구분
	private int auth_flag;//권한코드
	private String search_code;//검색코드
	private String search_nm;//검색명
	private String search_de;//검색날짜
	private String date_format;//날짜포맷
	private String curr_de;//현재일자
	private String base_de;//기준일자
	private String plan_de;//순방일자
	private String attending_time;//출근시간
	private String leaving_time;//퇴근시간
	private String attending_image_url;//출근사진URL
	private String leaving_image_url;//퇴근사진URL
	private String prd_partclr_matter;//순방비고
	private String prdi_sm_code;//순방매장코드
	private String prdi_sm_nm;//순방매장명
	private String prdi_em_nm;//순방사원명
	private String prdi_attend_de;//순방매장 출근시간
	private String prdi_attend_at;//순방매장 출근여부
	private String device_type;//디바이스 유형
	private String device_os_version;//디바이스 OS 버전
	private String device_app_version;//디바이스 APP 버전
	private String device_manufacture;//디바이스 제조사
	private String device_model;//디바이스 모델명
	private String device_la;//디바이스 위도
	private String device_lo;//디바이스 경도
	private String device_error_code;//디바이스 오류코드
	private String device_error_msg;//디바이스 오류메세지
	private String params;//JSONArray 문자열
	private String regist_man;//최초등록자
	private String regist_de;//최초등록일시
	private String updt_man;//최종수정자
	private String updt_de;//최종수정일시

	public String getCg_code() {
		return cg_code;
	}

	public String getCg_nm() {
		return cg_nm;
	}

	public String getMe_code() {
		return me_code;
	}

	public String getMe_nm() {
		return me_nm;
	}

	public String getSm_code() {
		return sm_code;
	}

	public String getSm_nm() {
		return sm_nm;
	}

	public String getCm_code() {
		return cm_code;
	}

	public String getCm_nm() {
		return cm_nm;
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

	public String getEm_id() {
		return em_id;
	}

	public String getEm_nm() {
		return em_nm;
	}

	public String getEm_dty_code() {
		return em_dty_code;
	}

	public String getEm_type_nm() {
		return em_type_nm;
	}

	public String getSm_la() {
		return sm_la;
	}

	public String getSm_lo() {
		return sm_lo;
	}

	public int getAm_no() {
		return am_no;
	}

	public int getAi_no() {
		return ai_no;
	}

	public String getAi_path() {
		return ai_path;
	}

	public String getAi_nm() {
		return ai_nm;
	}

	public String getAi_size() {
		return ai_size;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public String getType() {
		return type;
	}

	public String getFlag() {
		return flag;
	}

	public int getAuth_flag() {
		return auth_flag;
	}

	public String getSearch_code() {
		return search_code;
	}

	public String getSearch_nm() {
		return search_nm;
	}

	public String getSearch_de() {
		return search_de;
	}

	public String getDate_format() {
		return date_format;
	}

	public String getCurr_de() {
		return curr_de;
	}

	public String getBase_de() {
		return base_de;
	}

	public String getPlan_de() {
		return plan_de;
	}

	public String getAttending_time() {
		return attending_time;
	}

	public String getLeaving_time() {
		return leaving_time;
	}

	public String getAttending_image_url() {
		return attending_image_url;
	}

	public String getLeaving_image_url() {
		return leaving_image_url;
	}

	public String getPrd_partclr_matter() {
		return prd_partclr_matter;
	}

	public String getPrdi_sm_code() {
		return prdi_sm_code;
	}

	public String getPrdi_sm_nm() {
		return prdi_sm_nm;
	}

	public String getPrdi_em_nm() {
		return prdi_em_nm;
	}

	public String getPrdi_attend_de() {
		return prdi_attend_de;
	}

	public String getPrdi_attend_at() {
		return prdi_attend_at;
	}

	public String getDevice_type() {
		return device_type;
	}

	public String getDevice_os_version() {
		return device_os_version;
	}

	public String getDevice_app_version() {
		return device_app_version;
	}

	public String getDevice_manufacture() {
		return device_manufacture;
	}

	public String getDevice_model() {
		return device_model;
	}

	public String getDevice_la() {
		return device_la;
	}

	public String getDevice_lo() {
		return device_lo;
	}

	public String getDevice_error_code() {
		return device_error_code;
	}

	public String getDevice_error_msg() {
		return device_error_msg;
	}

	public String getParams() {
		return params;
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

	public void setCg_code(String cg_code) {
		this.cg_code = cg_code;
	}

	public void setCg_nm(String cg_nm) {
		this.cg_nm = cg_nm;
	}

	public void setMe_code(String me_code) {
		this.me_code = me_code;
	}

	public void setMe_nm(String me_nm) {
		this.me_nm = me_nm;
	}

	public void setSm_code(String sm_code) {
		this.sm_code = sm_code;
	}

	public void setSm_nm(String sm_nm) {
		this.sm_nm = sm_nm;
	}

	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}

	public void setCm_nm(String cm_nm) {
		this.cm_nm = cm_nm;
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

	public void setEm_id(String em_id) {
		this.em_id = em_id;
	}

	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}

	public void setEm_dty_code(String em_dty_code) {
		this.em_dty_code = em_dty_code;
	}

	public void setEm_type_nm(String em_type_nm) {
		this.em_type_nm = em_type_nm;
	}

	public void setSm_la(String sm_la) {
		this.sm_la = sm_la;
	}

	public void setSm_lo(String sm_lo) {
		this.sm_lo = sm_lo;
	}

	public void setAm_no(int am_no) {
		this.am_no = am_no;
	}

	public void setAi_no(int ai_no) {
		this.ai_no = ai_no;
	}

	public void setAi_path(String ai_path) {
		this.ai_path = ai_path;
	}

	public void setAi_nm(String ai_nm) {
		this.ai_nm = ai_nm;
	}

	public void setAi_size(String ai_size) {
		this.ai_size = ai_size;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setAuth_flag(int auth_flag) {
		this.auth_flag = auth_flag;
	}

	public void setSearch_code(String search_code) {
		this.search_code = search_code;
	}

	public void setSearch_nm(String search_nm) {
		this.search_nm = search_nm;
	}

	public void setSearch_de(String search_de) {
		this.search_de = search_de;
	}

	public void setDate_format(String date_format) {
		this.date_format = date_format;
	}

	public void setCurr_de(String curr_de) {
		this.curr_de = curr_de;
	}

	public void setBase_de(String base_de) {
		this.base_de = base_de;
	}

	public void setPlan_de(String plan_de) {
		this.plan_de = plan_de;
	}

	public void setAttending_time(String attending_time) {
		this.attending_time = attending_time;
	}

	public void setLeaving_time(String leaving_time) {
		this.leaving_time = leaving_time;
	}

	public void setAttending_image_url(String attending_image_url) {
		this.attending_image_url = attending_image_url;
	}

	public void setLeaving_image_url(String leaving_image_url) {
		this.leaving_image_url = leaving_image_url;
	}

	public void setPrd_partclr_matter(String prd_partclr_matter) {
		this.prd_partclr_matter = prd_partclr_matter;
	}

	public void setPrdi_sm_code(String prdi_sm_code) {
		this.prdi_sm_code = prdi_sm_code;
	}

	public void setPrdi_sm_nm(String prdi_sm_nm) {
		this.prdi_sm_nm = prdi_sm_nm;
	}

	public void setPrdi_em_nm(String prdi_em_nm) {
		this.prdi_em_nm = prdi_em_nm;
	}

	public void setPrdi_attend_de(String prdi_attend_de) {
		this.prdi_attend_de = prdi_attend_de;
	}

	public void setPrdi_attend_at(String prdi_attend_at) {
		this.prdi_attend_at = prdi_attend_at;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public void setDevice_os_version(String device_os_version) {
		this.device_os_version = device_os_version;
	}

	public void setDevice_app_version(String device_app_version) {
		this.device_app_version = device_app_version;
	}

	public void setDevice_manufacture(String device_manufacture) {
		this.device_manufacture = device_manufacture;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public void setDevice_la(String device_la) {
		this.device_la = device_la;
	}

	public void setDevice_lo(String device_lo) {
		this.device_lo = device_lo;
	}

	public void setDevice_error_code(String device_error_code) {
		this.device_error_code = device_error_code;
	}

	public void setDevice_error_msg(String device_error_msg) {
		this.device_error_msg = device_error_msg;
	}

	public void setParams(String params) {
		this.params = params;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("MobileVo[");
		sb.append("cg_code=" + cg_code + ",");
		sb.append("cg_nm=" + cg_nm + ",");
		sb.append("me_code=" + me_code + ",");
		sb.append("me_nm=" + me_nm + ",");
		sb.append("sm_code=" + sm_code + ",");
		sb.append("sm_nm=" + sm_nm + ",");
		sb.append("cm_code=" + cm_code + ",");
		sb.append("cm_nm=" + cm_nm + ",");
		sb.append("om_code=" + om_code + ",");
		sb.append("om_nm=" + om_nm + ",");
		sb.append("em_no=" + em_no + ",");
		sb.append("em_id=" + em_id + ",");
		sb.append("em_nm=" + em_nm + ",");
		sb.append("em_dty_code=" + em_dty_code + ",");
		sb.append("em_type_nm=" + em_type_nm + ",");
		sb.append("sm_la=" + sm_la + ",");
		sb.append("sm_lo=" + sm_lo + ",");
		sb.append("am_no=" + am_no + ",");
		sb.append("ai_no=" + ai_no + ",");
		sb.append("ai_path=" + ai_path + ",");
		sb.append("ai_nm=" + ai_nm + ",");
		sb.append("ai_size=" + ai_size + ",");
		sb.append("type=" + type + ",");
		sb.append("flag=" + flag + ",");
		sb.append("auth_flag=" + auth_flag + ",");
		sb.append("search_code=" + search_code + ",");
		sb.append("search_nm=" + search_nm + ",");
		sb.append("search_de=" + search_de + ",");
		sb.append("date_format=" + date_format + ",");
		sb.append("curr_de=" + curr_de + ",");
		sb.append("base_de=" + base_de + ",");
		sb.append("plan_de=" + plan_de + ",");
		sb.append("attending_time=" + attending_time + ",");
		sb.append("leaving_time=" + leaving_time + ",");
		sb.append("attending_image_url=" + attending_image_url + ",");
		sb.append("leaving_image_url=" + leaving_image_url + ",");
		sb.append("prd_partclr_matter=" + prd_partclr_matter + ",");
		sb.append("prdi_sm_code=" + prdi_sm_code + ",");
		sb.append("prdi_sm_nm=" + prdi_sm_nm + ",");
		sb.append("prdi_em_nm=" + prdi_em_nm + ",");
		sb.append("prdi_attend_de=" + prdi_attend_de + ",");
		sb.append("prdi_attend_at=" + prdi_attend_at + ",");
		sb.append("device_type=" + device_type + ",");
		sb.append("device_os_version=" + device_os_version + ",");
		sb.append("device_app_version=" + device_app_version + ",");
		sb.append("device_manufacture=" + device_manufacture + ",");
		sb.append("device_model=" + device_model + ",");
		sb.append("device_la=" + device_la + ",");
		sb.append("device_lo=" + device_lo + ",");
		sb.append("device_error_code=" + device_error_code + ",");
		sb.append("device_error_msg=" + device_error_msg + ",");
		sb.append("params=" + params + ",");
		sb.append("regist_man=" + regist_man + ",");
		sb.append("regist_de=" + regist_de + ",");
		sb.append("updt_man=" + updt_man + ",");
		sb.append("updt_de=" + updt_de);
		sb.append("]");
		return sb.toString();
	}

}
