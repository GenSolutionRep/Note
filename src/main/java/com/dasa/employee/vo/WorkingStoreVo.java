package com.dasa.employee.vo;

/**
 * @파일명: WorkingStoreVo.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 근무매장 Vo
 */
public class WorkingStoreVo {
	//field
	private String em_no;//사원번호
	private String cg_nm;//고객그룹명
	private String me_nm;//관리업체명
	private String sm_nm;//매장명
	private String gnfd_de;//발령일자

	//method
	public String getEm_no() {
		return em_no;
	}

	public String getCg_nm() {
		return cg_nm;
	}

	public String getMe_nm() {
		return me_nm;
	}

	public String getSm_nm() {
		return sm_nm;
	}

	public String getGnfd_de() {
		return gnfd_de;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public void setCg_nm(String cg_nm) {
		this.cg_nm = cg_nm;
	}

	public void setMe_nm(String me_nm) {
		this.me_nm = me_nm;
	}

	public void setSm_nm(String sm_nm) {
		this.sm_nm = sm_nm;
	}

	public void setGnfd_de(String gnfd_de) {
		this.gnfd_de = gnfd_de;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("WorkingStoreVo[");
		sb.append("em_no=" + em_no + ",");
		sb.append("cg_nm=" + cg_nm + ",");
		sb.append("me_nm=" + me_nm + ",");
		sb.append("sm_nm=" + sm_nm + ",");
		sb.append("gnfd_de=" + gnfd_de);
		sb.append("]");

		return sb.toString();
	}

}
