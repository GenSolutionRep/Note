package com.dasa.login.vo;

/**
 * @파일명: LoginVo.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 로그인 Vo
 */
public class LoginVo {

	private String cm_code;//회사코드
	private String cm_nm;//회사명
	private String bhf_code;//지점코드
	private String bhf_nm;//지점명
	private String team_code;//팀코드
	private String team_nm;//팀명
	private String timhdr_em_no;//소속팀장사원번호
	private String timhdr_em_nm;//소속팀장명
	private String timhdr_om_la;//소속팀장 지점 위도
	private String timhdr_om_lo;//소속팀장 지점 경도
	private String em_no;//사원번호
	private String em_nm;//사원명
	private String em_id;//사원ID
	private String em_password;//비밀번호
	private String em_rspofc_code;//직책코드
	private String em_rspofc_nm;//직책명
	private String em_dty_code;//직무코드
	private String em_dty_nm;//직무명
	private String em_unique_id;//디바이스 고유번호
	private String em_push_id;//디바이스 PUSH ID
	private String em_device_type;//디바이스 유형
	private int em_device_version;//디바이스 버전
	private String em_mbtl_open_at;//개인연락처 공개여부
	private String sys_de;//시스템날짜
	private String date_format;//날짜포맷
	private String flag;//구분
	private int auth_flag;//권한코드
	private String check_id;//사원ID 존재여부
	private String check_password;//비밀번호 확인여부
	private String check_unique_id;//디바이스 고유번호 존재여부

	public String getCm_code() {
		return cm_code;
	}

	public String getCm_nm() {
		return cm_nm;
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

	public String getTimhdr_em_no() {
		return timhdr_em_no;
	}

	public String getTimhdr_em_nm() {
		return timhdr_em_nm;
	}

	public String getTimhdr_om_la() {
		return timhdr_om_la;
	}

	public String getTimhdr_om_lo() {
		return timhdr_om_lo;
	}

	public String getEm_no() {
		return em_no;
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

	public String getEm_unique_id() {
		return em_unique_id;
	}

	public String getEm_push_id() {
		return em_push_id;
	}

	public String getEm_device_type() {
		return em_device_type;
	}

	public int getEm_device_version() {
		return em_device_version;
	}

	public String getEm_mbtl_open_at() {
		return em_mbtl_open_at;
	}

	public String getSys_de() {
		return sys_de;
	}

	public String getDate_format() {
		return date_format;
	}

	public String getFlag() {
		return flag;
	}

	public int getAuth_flag() {
		return auth_flag;
	}

	public String getCheck_id() {
		return check_id;
	}

	public String getCheck_password() {
		return check_password;
	}

	public String getCheck_unique_id() {
		return check_unique_id;
	}

	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}

	public void setCm_nm(String cm_nm) {
		this.cm_nm = cm_nm;
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

	public void setTimhdr_em_no(String timhdr_em_no) {
		this.timhdr_em_no = timhdr_em_no;
	}

	public void setTimhdr_em_nm(String timhdr_em_nm) {
		this.timhdr_em_nm = timhdr_em_nm;
	}

	public void setTimhdr_om_la(String timhdr_om_la) {
		this.timhdr_om_la = timhdr_om_la;
	}

	public void setTimhdr_om_lo(String timhdr_om_lo) {
		this.timhdr_om_lo = timhdr_om_lo;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
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

	public void setEm_unique_id(String em_unique_id) {
		this.em_unique_id = em_unique_id;
	}

	public void setEm_push_id(String em_push_id) {
		this.em_push_id = em_push_id;
	}

	public void setEm_device_type(String em_device_type) {
		this.em_device_type = em_device_type;
	}

	public void setEm_device_version(int em_device_version) {
		this.em_device_version = em_device_version;
	}

	public void setEm_mbtl_open_at(String em_mbtl_open_at) {
		this.em_mbtl_open_at = em_mbtl_open_at;
	}

	public void setSys_de(String sys_de) {
		this.sys_de = sys_de;
	}

	public void setDate_format(String date_format) {
		this.date_format = date_format;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setAuth_flag(int auth_flag) {
		this.auth_flag = auth_flag;
	}

	public void setCheck_id(String check_id) {
		this.check_id = check_id;
	}

	public void setCheck_password(String check_password) {
		this.check_password = check_password;
	}

	public void setCheck_unique_id(String check_unique_id) {
		this.check_unique_id = check_unique_id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("LoginVo[");
		sb.append("cm_code=" + cm_code + ",");
		sb.append("cm_nm=" + cm_nm + ",");
		sb.append("bhf_code=" + bhf_code + ",");
		sb.append("bhf_nm=" + bhf_nm + ",");
		sb.append("team_code=" + team_code + ",");
		sb.append("team_nm=" + team_nm + ",");
		sb.append("timhdr_em_no=" + timhdr_em_no + ",");
		sb.append("timhdr_em_nm=" + timhdr_em_nm + ",");
		sb.append("timhdr_om_la=" + timhdr_om_la + ",");
		sb.append("timhdr_om_lo=" + timhdr_om_lo + ",");
		sb.append("em_no=" + em_no + ",");
		sb.append("em_nm=" + em_nm + ",");
		sb.append("em_id=" + em_id + ",");
		sb.append("em_password=" + em_password + ",");
		sb.append("em_rspofc_code=" + em_rspofc_code + ",");
		sb.append("em_rspofc_nm=" + em_rspofc_nm + ",");
		sb.append("em_dty_code=" + em_dty_code + ",");
		sb.append("em_dty_nm=" + em_dty_nm + ",");
		sb.append("em_unique_id=" + em_unique_id + ",");
		sb.append("em_push_id=" + em_push_id + ",");
		sb.append("em_device_type=" + em_device_type + ",");
		sb.append("em_device_version=" + em_device_version + ",");
		sb.append("em_mbtl_open_at=" + em_mbtl_open_at + ",");
		sb.append("sys_de=" + sys_de + ",");
		sb.append("date_format=" + date_format + ",");
		sb.append("flag=" + flag + ",");
		sb.append("auth_flag=" + auth_flag + ",");
		sb.append("check_id=" + check_id + ",");
		sb.append("check_password=" + check_password + ",");
		sb.append("check_unique_id=" + check_unique_id);
		sb.append("]");
		return sb.toString();
	}

}
