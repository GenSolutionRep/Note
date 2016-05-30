package com.gensolution.activity.vo;

public class ActivityRndPrdListVo {
	
	 private String drap_innb;
     private String dra_innb;
     private String oi_code;
     private String oi_code_nm;
     private String drap_cur_rate;
     private String drap_partclr_matter;
     
	public String getDrap_innb() {
		return drap_innb;
	}
	public void setDrap_innb(String drap_innb) {
		this.drap_innb = drap_innb;
	}
	public String getDra_innb() {
		return dra_innb;
	}
	public void setDra_innb(String dra_innb) {
		this.dra_innb = dra_innb;
	}
	public String getOi_code() {
		return oi_code;
	}
	public void setOi_code(String oi_code) {
		this.oi_code = oi_code;
	}
	public String getOi_code_nm() {
		return oi_code_nm;
	}
	public void setOi_code_nm(String oi_code_nm) {
		this.oi_code_nm = oi_code_nm;
	}
	public String getDrap_cur_rate() {
		return drap_cur_rate;
	}
	public void setDrap_cur_rate(String drap_cur_rate) {
		this.drap_cur_rate = drap_cur_rate;
	}
	public String getDrap_partclr_matter() {
		return drap_partclr_matter;
	}
	public void setDrap_partclr_matter(String drap_partclr_matter) {
		this.drap_partclr_matter = drap_partclr_matter;
	}
	@Override
	public String toString() {
		return "ActivityRndPrdListVo [drap_innb=" + drap_innb + ", dra_innb="
				+ dra_innb + ", oi_code=" + oi_code + ", oi_code_nm="
				+ oi_code_nm + ", drap_cur_rate=" + drap_cur_rate
				+ ", drap_partclr_matter=" + drap_partclr_matter + "]";
	}
     
     
}
