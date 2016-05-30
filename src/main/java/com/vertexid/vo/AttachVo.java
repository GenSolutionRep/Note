package com.vertexid.vo;


/**
 * @Class명: FileVo
 * @작성일: 2014. 10. 18
 * @작성자: 김진호
 * @설명: 파일 VO 
 */
public class AttachVo {
	private int am_no;				// 파일 마스터 번호
	private int ai_no;				// 파일 자식 번호
	private String ai_path;			// 파일 경로
	private String ai_nm;			// 파일명
	private Long ai_size;			// 파일명
	private String regist_man;			// 작성자
	private String regist_de;	// 수정일
	public int getAm_no() {
		return am_no;
	}
	public void setAm_no(int am_no) {
		this.am_no = am_no;
	}
	public int getAi_no() {
		return ai_no;
	}
	public void setAi_no(int ai_no) {
		this.ai_no = ai_no;
	}
	public String getAi_path() {
		return ai_path;
	}
	public void setAi_path(String ai_path) {
		this.ai_path = ai_path;
	}
	public String getAi_nm() {
		return ai_nm;
	}
	public void setAi_nm(String ai_nm) {
		this.ai_nm = ai_nm;
	}
	public Long getAi_size() {
		return ai_size;
	}
	public void setAi_size(Long ai_size) {
		this.ai_size = ai_size;
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
	@Override
	public String toString() {
		return "FileVo [am_no=" + am_no + ", ai_no=" + ai_no + ", ai_path="
				+ ai_path + ", ai_nm=" + ai_nm + ", ai_size=" + ai_size
				+ ", regist_man=" + regist_man + ", regist_de=" + regist_de
				+ "]";
	}
	
	
	
}
