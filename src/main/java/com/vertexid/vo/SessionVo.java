package com.vertexid.vo;

/**
 * @Class명: SessionVo
 * @작성일: 2014. 10. 18
 * @작성자: 김진호
 * @설명: 매뉴얼 VO 
 */
public class SessionVo {
	private String user_id;				// 접속자 아이지
	private String user_nm;				// 접속자 이름
	private String dept_cd;				// 접속자 부서코드
	private String up_id;
	private String dept_nm;				// 접속자 부서명
	private String dept_list ;			// 접속자 상위부서 목록
	private String auth_gubun ;			// 접속자 권한
	private String auth_gubun_name ;	// 접속자 권한명
	private String system_date;			// 시스템 데이트
	private String dept_cd_list;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUp_id() {
		return up_id;
	}
	public void setUp_id(String up_id) {
		this.up_id = up_id;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getDept_list() {
		return dept_list;
	}
	public void setDept_list(String dept_list) {
		this.dept_list = dept_list;
	}
	public String getAuth_gubun() {
		return auth_gubun;
	}
	public void setAuth_gubun(String auth_gubun) {
		this.auth_gubun = auth_gubun;
	}
	public String getAuth_gubun_name() {
		return auth_gubun_name;
	}
	public void setAuth_gubun_name(String auth_gubun_name) {
		this.auth_gubun_name = auth_gubun_name;
	}
	public String getSystem_date() {
		return system_date;
	}
	public void setSystem_date(String system_date) {
		this.system_date = system_date;
	}
	@Override
	public String toString() {
		return "SessionVo [user_id=" + user_id + ", user_nm=" + user_nm
				+ ", dept_cd=" + dept_cd + ", dept_nm=" + dept_nm
				+ ", dept_list=" + dept_list + ", auth_gubun=" + auth_gubun
				+ ", auth_gubun_name=" + auth_gubun_name + ", system_date="
				+ system_date + ", dept_cd_list=" + dept_cd_list + "]";
	}
	public String getDept_cd_list() {
		return dept_cd_list;
	}
	public void setDept_cd_list(String dept_cd_list) {
		this.dept_cd_list = dept_cd_list;
	}
	
}
