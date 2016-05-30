package com.dasa.employee.vo;

import java.util.List;
import java.util.Map;

/**
 * @파일명: HrHistoryVo.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 인사기록 Vo
 */
public class HrHistoryVo {
	//field
	private String hm_innb;//인사고유번호
	private String em_no;//사원번호
	private String hm_gnfd_de;//발령일
	private String hm_gnfd_de_nm;//발령일 FORMATTING
	private String hm_gnfd_cn;//발령내용
	private String hm_partclr_matter;//특이사항
	private String delete_at;//삭제여부
	private String regist_man;//최초등록자
	private String regist_de;//최초등록일시
	private String updt_man;//최종수정자
	private String updt_de;//최종수정일시
	private List<Map<String, String>> hrHistoryArr;

	//method
	public String getHm_innb() {
		return hm_innb;
	}

	public String getEm_no() {
		return em_no;
	}

	public String getHm_gnfd_de() {
		return hm_gnfd_de;
	}

	public String getHm_gnfd_de_nm() {
		return hm_gnfd_de_nm;
	}

	public String getHm_gnfd_cn() {
		return hm_gnfd_cn;
	}

	public String getHm_partclr_matter() {
		return hm_partclr_matter;
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

	public List<Map<String, String>> getHrHistoryArr() {
		return hrHistoryArr;
	}

	public void setHm_innb(String hm_innb) {
		this.hm_innb = hm_innb;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public void setHm_gnfd_de(String hm_gnfd_de) {
		this.hm_gnfd_de = hm_gnfd_de;
	}

	public void setHm_gnfd_de_nm(String hm_gnfd_de_nm) {
		this.hm_gnfd_de_nm = hm_gnfd_de_nm;
	}

	public void setHm_gnfd_cn(String hm_gnfd_cn) {
		this.hm_gnfd_cn = hm_gnfd_cn;
	}

	public void setHm_partclr_matter(String hm_partclr_matter) {
		this.hm_partclr_matter = hm_partclr_matter;
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

	public void setHrHistoryArr(List<Map<String, String>> hrHistoryArr) {
		this.hrHistoryArr = hrHistoryArr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HrHistoryVo[");
		sb.append("hm_innb=" + hm_innb + ",");
		sb.append("em_no=" + em_no + ",");
		sb.append("hm_gnfd_de=" + hm_gnfd_de + ",");
		sb.append("hm_gnfd_de_nm=" + hm_gnfd_de_nm + ",");
		sb.append("hm_gnfd_cn=" + hm_gnfd_cn + ",");
		sb.append("hm_partclr_matter=" + hm_partclr_matter + ",");
		sb.append("delete_at=" + delete_at + ",");
		sb.append("regist_man=" + regist_man + ",");
		sb.append("regist_de=" + regist_de + ",");
		sb.append("updt_man=" + updt_man + ",");
		sb.append("updt_de=" + updt_de + ",");
		sb.append("hrHistoryArr=" + hrHistoryArr);
		sb.append("]");

		return sb.toString();
	}

}
