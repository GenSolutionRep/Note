package com.dasa.analysis.vo;

import java.util.List;
import java.util.Map;


public class DisPlayVo {
	private String d_innb;
	private String d_name;
	private String fixing_code;
	private String rnd_code;
	private String fixing_child_code;
	private String rnd_child_code;
	
	private String oi_nm;
	private String dp_innb;
	private String dpi_innb;
	private String oi_nick_nm;
	private String oi_sort_ordr;
	private String collect_at;
	private String em_no;
	private String colunm;
	private String table_name;
	
	
	
	private String om_code;
	private String base_de;
	
	private List<Map<String,String>> prdArr;
	private List<String> columnArr;
	
	
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getOm_code() {
		return om_code;
	}
	public void setOm_code(String om_code) {
		this.om_code = om_code;
	}
	public String getBase_de() {
		return base_de;
	}
	public void setBase_de(String base_de) {
		this.base_de = base_de;
	}
	public List<String> getColumnArr() {
		return columnArr;
	}
	public void setColumnArr(List<String> columnArr) {
		this.columnArr = columnArr;
	}
	public String getColunm() {
		return colunm;
	}
	public void setColunm(String colunm) {
		this.colunm = colunm;
	}
	public String getDpi_innb() {
		return dpi_innb;
	}
	public void setDpi_innb(String dpi_innb) {
		this.dpi_innb = dpi_innb;
	}
	public String getEm_no() {
		return em_no;
	}
	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}
	public List<Map<String, String>> getPrdArr() {
		return prdArr;
	}
	public void setPrdArr(List<Map<String, String>> prdArr) {
		this.prdArr = prdArr;
	}
	public String getFixing_child_code() {
		return fixing_child_code;
	}
	public void setFixing_child_code(String fixing_child_code) {
		this.fixing_child_code = fixing_child_code;
	}
	public String getRnd_child_code() {
		return rnd_child_code;
	}
	public void setRnd_child_code(String rnd_child_code) {
		this.rnd_child_code = rnd_child_code;
	}
	public String getFixing_code() {
		return fixing_code;
	}
	public void setFixing_code(String fixing_code) {
		this.fixing_code = fixing_code;
	}
	public String getRnd_code() {
		return rnd_code;
	}
	public void setRnd_code(String rnd_code) {
		this.rnd_code = rnd_code;
	}
	public String getD_innb() {
		return d_innb;
	}
	public void setD_innb(String d_innb) {
		this.d_innb = d_innb;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getOi_nm() {
		return oi_nm;
	}
	public void setOi_nm(String oi_nm) {
		this.oi_nm = oi_nm;
	}
	public String getDp_innb() {
		return dp_innb;
	}
	public void setDp_innb(String dp_innb) {
		this.dp_innb = dp_innb;
	}
	public String getOi_nick_nm() {
		return oi_nick_nm;
	}
	public void setOi_nick_nm(String oi_nick_nm) {
		this.oi_nick_nm = oi_nick_nm;
	}
	public String getOi_sort_ordr() {
		return oi_sort_ordr;
	}
	public void setOi_sort_ordr(String oi_sort_ordr) {
		this.oi_sort_ordr = oi_sort_ordr;
	}
	public String getCollect_at() {
		return collect_at;
	}
	public void setCollect_at(String collect_at) {
		this.collect_at = collect_at;
	}
	@Override
	public String toString() {
		return "DisPlayVo [d_innb=" + d_innb + ", d_name=" + d_name
				+ ", fixing_code=" + fixing_code + ", rnd_code=" + rnd_code
				+ ", fixing_child_code=" + fixing_child_code
				+ ", rnd_child_code=" + rnd_child_code + ", oi_nm=" + oi_nm
				+ ", dp_innb=" + dp_innb + ", oi_nick_nm=" + oi_nick_nm
				+ ", oi_sort_ordr=" + oi_sort_ordr + ", collect_at="
				+ collect_at + ", prdArr=" + prdArr + "]";
	}
}
