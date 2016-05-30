package com.dasa.employee.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * @파일명: EmployeeVo.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 Vo
 */
public class EmployeeVo {

	private String no;//페이징처리 NO
	private String em_no;//사원번호
	private String cm_code;//회사코드
	private String bhf_code;//지점코드
	private String bhf_nm;//지점명
	private String team_code;//팀코드
	private String team_nm;//팀명
	private String em_nm;//사원명
	private String em_id;//사원ID
	private String em_password;//비밀번호
	private String me_nm;//관리업체명
	private String sm_nm;//매장명
	private String em_ecny_de;//입사일
	private String em_ecny_de_nm;//입사일 FORMATTING
	private String em_retire_de;//퇴사일
	private String em_retire_de_nm;//퇴사일 FORMATTING
	private String em_rspofc_code;//직책코드
	private String em_rspofc_nm;//직책명
	private String em_dty_code;//직무코드
	private String em_dty_nm;//직무명
	private String em_ampm_at;//근무시간유형(A,P1,P2)
	private String em_ampm_at_nm;//근무시간 FORMATTING
	private String em_brthdy;//생년월일
	private String em_brthdy_nm;//생년월일 FORMATTING
	private String em_sexdstn;//성별(M,F)
	private String em_sexdstn_nm;//성별 FORMATTING
	private String em_mbtl_num;//휴대전화
	private String em_mbtl_open_at;//개인연락처 공개여부(Y,N)
	private String em_mbtl_open_at_nm;//개인연락처 공개여부 FORMATTING
	private String em_mrnry_de;//결혼기념일
	private String em_mrnry_de_nm;//결혼기념일 FORMATTING
	private String em_zipcd;//우편번호
	private String em_adres;//주소
	private String em_dtadres;//상세주소
	private String em_etcadres;//기타주소
	private String em_cldr;//달력유형
	private String em_cldr_nm;//달력유형명
	private int em_trans_fee;//기준교통비
	private String em_trans_fee_nm;//기준교통비 FORMATTING
	private String em_certify_at;//디바이스 인증여부(Y,N)
	private String em_certify_at_nm;//디바이스 인증여부 FORMATTING
	private int am_no;//첨부파일고유번호
	private MultipartFile[] files;//첨부파일
	private String image_url;//사진URL
	private String use_at;//재직여부(Y:재직,N:퇴사)
	private String use_at_nm;//재직여부 FORMATTING
	private String delete_at;//삭제여부
	private String before_password;//변경전 비밀번호
	private String after_password;//변경후 비밀번호
	private String check_id;//입력한 ID의 존재여부
	private String exist_em_no;//입력한 ID 존재시 사원번호
	private String check_timhdr;//입력한 팀의 팀장 존재여부
	private String timhdr_em_no;//입력한 팀의 팀장 존재시 팀장사원번호
	private String regist_man;//최초등록자
	private String regist_de;//최초등록일시
	private String updt_man;//최종수정자
	private String updt_de;//최종수정일시

	public String getNo() {
		return no;
	}

	public String getEm_no() {
		return em_no;
	}

	public String getCm_code() {
		return cm_code;
	}

	public String getBhf_code() {
		return bhf_code;
	}

	public String getBhf_nm() {
		return bhf_nm;
	}

	public String getTeam_code() {
		return team_code;
	}

	public String getTeam_nm() {
		return team_nm;
	}

	public String getEm_nm() {
		return em_nm;
	}

	public String getEm_id() {
		return em_id;
	}

	public String getEm_password() {
		return em_password;
	}

	public String getMe_nm() {
		return me_nm;
	}

	public String getSm_nm() {
		return sm_nm;
	}

	public String getEm_ecny_de() {
		return em_ecny_de;
	}

	public String getEm_ecny_de_nm() {
		return em_ecny_de_nm;
	}

	public String getEm_retire_de() {
		return em_retire_de;
	}

	public String getEm_retire_de_nm() {
		return em_retire_de_nm;
	}

	public String getEm_rspofc_code() {
		return em_rspofc_code;
	}

	public String getEm_rspofc_nm() {
		return em_rspofc_nm;
	}

	public String getEm_dty_code() {
		return em_dty_code;
	}

	public String getEm_dty_nm() {
		return em_dty_nm;
	}

	public String getEm_ampm_at() {
		return em_ampm_at;
	}

	public String getEm_ampm_at_nm() {
		return em_ampm_at_nm;
	}

	public String getEm_brthdy() {
		return em_brthdy;
	}

	public String getEm_brthdy_nm() {
		return em_brthdy_nm;
	}

	public String getEm_sexdstn() {
		return em_sexdstn;
	}

	public String getEm_sexdstn_nm() {
		return em_sexdstn_nm;
	}

	public String getEm_mbtl_num() {
		return em_mbtl_num;
	}

	public String getEm_mbtl_open_at() {
		return em_mbtl_open_at;
	}

	public String getEm_mbtl_open_at_nm() {
		return em_mbtl_open_at_nm;
	}

	public String getEm_mrnry_de() {
		return em_mrnry_de;
	}

	public String getEm_mrnry_de_nm() {
		return em_mrnry_de_nm;
	}

	public String getEm_zipcd() {
		return em_zipcd;
	}

	public String getEm_adres() {
		return em_adres;
	}

	public String getEm_dtadres() {
		return em_dtadres;
	}

	public String getEm_etcadres() {
		return em_etcadres;
	}

	public String getEm_cldr() {
		return em_cldr;
	}

	public String getEm_cldr_nm() {
		return em_cldr_nm;
	}

	public int getEm_trans_fee() {
		return em_trans_fee;
	}

	public String getEm_trans_fee_nm() {
		return em_trans_fee_nm;
	}

	public String getEm_certify_at() {
		return em_certify_at;
	}

	public String getEm_certify_at_nm() {
		return em_certify_at_nm;
	}

	public int getAm_no() {
		return am_no;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public String getImage_url() {
		return image_url;
	}

	public String getUse_at() {
		return use_at;
	}

	public String getUse_at_nm() {
		return use_at_nm;
	}

	public String getDelete_at() {
		return delete_at;
	}

	public String getBefore_password() {
		return before_password;
	}

	public String getAfter_password() {
		return after_password;
	}

	public String getCheck_id() {
		return check_id;
	}

	public String getExist_em_no() {
		return exist_em_no;
	}

	public String getCheck_timhdr() {
		return check_timhdr;
	}

	public String getTimhdr_em_no() {
		return timhdr_em_no;
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

	public void setNo(String no) {
		this.no = no;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}

	public void setBhf_code(String bhf_code) {
		this.bhf_code = bhf_code;
	}

	public void setBhf_nm(String bhf_nm) {
		this.bhf_nm = bhf_nm;
	}

	public void setTeam_code(String team_code) {
		this.team_code = team_code;
	}

	public void setTeam_nm(String team_nm) {
		this.team_nm = team_nm;
	}

	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}

	public void setEm_id(String em_id) {
		this.em_id = em_id;
	}

	public void setEm_password(String em_password) {
		this.em_password = em_password;
	}

	public void setMe_nm(String me_nm) {
		this.me_nm = me_nm;
	}

	public void setSm_nm(String sm_nm) {
		this.sm_nm = sm_nm;
	}

	public void setEm_ecny_de(String em_ecny_de) {
		this.em_ecny_de = em_ecny_de;
	}

	public void setEm_ecny_de_nm(String em_ecny_de_nm) {
		this.em_ecny_de_nm = em_ecny_de_nm;
	}

	public void setEm_retire_de(String em_retire_de) {
		this.em_retire_de = em_retire_de;
	}

	public void setEm_retire_de_nm(String em_retire_de_nm) {
		this.em_retire_de_nm = em_retire_de_nm;
	}

	public void setEm_rspofc_code(String em_rspofc_code) {
		this.em_rspofc_code = em_rspofc_code;
	}

	public void setEm_rspofc_nm(String em_rspofc_nm) {
		this.em_rspofc_nm = em_rspofc_nm;
	}

	public void setEm_dty_code(String em_dty_code) {
		this.em_dty_code = em_dty_code;
	}

	public void setEm_dty_nm(String em_dty_nm) {
		this.em_dty_nm = em_dty_nm;
	}

	public void setEm_ampm_at(String em_ampm_at) {
		this.em_ampm_at = em_ampm_at;
	}

	public void setEm_ampm_at_nm(String em_ampm_at_nm) {
		this.em_ampm_at_nm = em_ampm_at_nm;
	}

	public void setEm_brthdy(String em_brthdy) {
		this.em_brthdy = em_brthdy;
	}

	public void setEm_brthdy_nm(String em_brthdy_nm) {
		this.em_brthdy_nm = em_brthdy_nm;
	}

	public void setEm_sexdstn(String em_sexdstn) {
		this.em_sexdstn = em_sexdstn;
	}

	public void setEm_sexdstn_nm(String em_sexdstn_nm) {
		this.em_sexdstn_nm = em_sexdstn_nm;
	}

	public void setEm_mbtl_num(String em_mbtl_num) {
		this.em_mbtl_num = em_mbtl_num;
	}

	public void setEm_mbtl_open_at(String em_mbtl_open_at) {
		this.em_mbtl_open_at = em_mbtl_open_at;
	}

	public void setEm_mbtl_open_at_nm(String em_mbtl_open_at_nm) {
		this.em_mbtl_open_at_nm = em_mbtl_open_at_nm;
	}

	public void setEm_mrnry_de(String em_mrnry_de) {
		this.em_mrnry_de = em_mrnry_de;
	}

	public void setEm_mrnry_de_nm(String em_mrnry_de_nm) {
		this.em_mrnry_de_nm = em_mrnry_de_nm;
	}

	public void setEm_zipcd(String em_zipcd) {
		this.em_zipcd = em_zipcd;
	}

	public void setEm_adres(String em_adres) {
		this.em_adres = em_adres;
	}

	public void setEm_dtadres(String em_dtadres) {
		this.em_dtadres = em_dtadres;
	}

	public void setEm_etcadres(String em_etcadres) {
		this.em_etcadres = em_etcadres;
	}

	public void setEm_cldr(String em_cldr) {
		this.em_cldr = em_cldr;
	}

	public void setEm_cldr_nm(String em_cldr_nm) {
		this.em_cldr_nm = em_cldr_nm;
	}

	public void setEm_trans_fee(int em_trans_fee) {
		this.em_trans_fee = em_trans_fee;
	}

	public void setEm_trans_fee_nm(String em_trans_fee_nm) {
		this.em_trans_fee_nm = em_trans_fee_nm;
	}

	public void setEm_certify_at(String em_certify_at) {
		this.em_certify_at = em_certify_at;
	}

	public void setEm_certify_at_nm(String em_certify_at_nm) {
		this.em_certify_at_nm = em_certify_at_nm;
	}

	public void setAm_no(int am_no) {
		this.am_no = am_no;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}

	public void setUse_at_nm(String use_at_nm) {
		this.use_at_nm = use_at_nm;
	}

	public void setDelete_at(String delete_at) {
		this.delete_at = delete_at;
	}

	public void setBefore_password(String before_password) {
		this.before_password = before_password;
	}

	public void setAfter_password(String after_password) {
		this.after_password = after_password;
	}

	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}

	public void setExist_em_no(String exist_em_no) {
		this.exist_em_no = exist_em_no;
	}

	public void setCheck_timhdr(String check_timhdr) {
		this.check_timhdr = check_timhdr;
	}

	public void setTimhdr_em_no(String timhdr_em_no) {
		this.timhdr_em_no = timhdr_em_no;
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
		sb.append("EmployeeVo[");
		sb.append("no=" + no + ",");
		sb.append("em_no=" + em_no + ",");
		sb.append("cm_code=" + cm_code + ",");
		sb.append("bhf_code=" + bhf_code + ",");
		sb.append("bhf_nm=" + bhf_nm + ",");
		sb.append("team_code=" + team_code + ",");
		sb.append("team_nm=" + team_nm + ",");
		sb.append("em_nm=" + em_nm + ",");
		sb.append("em_id=" + em_id + ",");
		sb.append("em_password=" + em_password + ",");
		sb.append("me_nm=" + me_nm + ",");
		sb.append("sm_nm=" + sm_nm + ",");
		sb.append("em_ecny_de=" + em_ecny_de + ",");
		sb.append("em_ecny_de_nm=" + em_ecny_de_nm + ",");
		sb.append("em_retire_de=" + em_retire_de + ",");
		sb.append("em_retire_de_nm=" + em_retire_de_nm + ",");
		sb.append("em_rspofc_code=" + em_rspofc_code + ",");
		sb.append("em_rspofc_nm=" + em_rspofc_nm + ",");
		sb.append("em_dty_code=" + em_dty_code + ",");
		sb.append("em_dty_nm=" + em_dty_nm + ",");
		sb.append("em_ampm_at=" + em_ampm_at + ",");
		sb.append("em_ampm_at_nm=" + em_ampm_at_nm + ",");
		sb.append("em_brthdy=" + em_brthdy + ",");
		sb.append("em_brthdy_nm=" + em_brthdy_nm + ",");
		sb.append("em_sexdstn=" + em_sexdstn + ",");
		sb.append("em_sexdstn_nm=" + em_sexdstn_nm + ",");
		sb.append("em_mbtl_num=" + em_mbtl_num + ",");
		sb.append("em_mbtl_open_at=" + em_mbtl_open_at + ",");
		sb.append("em_mbtl_open_at_nm=" + em_mbtl_open_at_nm + ",");
		sb.append("em_mrnry_de=" + em_mrnry_de + ",");
		sb.append("em_mrnry_de_nm=" + em_mrnry_de_nm + ",");
		sb.append("em_zipcd=" + em_zipcd + ",");
		sb.append("em_adres=" + em_adres + ",");
		sb.append("em_dtadres=" + em_dtadres + ",");
		sb.append("em_etcadres=" + em_etcadres + ",");
		sb.append("em_cldr=" + em_cldr + ",");
		sb.append("em_cldr_nm=" + em_cldr_nm + ",");
		sb.append("em_trans_fee=" + em_trans_fee + ",");
		sb.append("em_trans_fee_nm=" + em_trans_fee_nm + ",");
		sb.append("em_certify_at=" + em_certify_at + ",");
		sb.append("em_certify_at_nm=" + em_certify_at_nm + ",");
		sb.append("am_no=" + am_no + ",");
		sb.append("image_url=" + image_url + ",");
		sb.append("use_at=" + use_at + ",");
		sb.append("use_at_nm=" + use_at_nm + ",");
		sb.append("delete_at=" + delete_at + ",");
		sb.append("before_password=" + before_password + ",");
		sb.append("after_password=" + after_password + ",");
		sb.append("check_id=" + check_id + ",");
		sb.append("exist_em_no=" + exist_em_no + ",");
		sb.append("check_timhdr=" + check_timhdr + ",");
		sb.append("timhdr_em_no=" + timhdr_em_no + ",");
		sb.append("regist_man=" + regist_man + ",");
		sb.append("regist_de=" + regist_de + ",");
		sb.append("updt_man=" + updt_man + ",");
		sb.append("updt_de=" + updt_de);
		sb.append("]");
		return sb.toString();
	}

}
