package com.gensolution.activity.vo;

public class ActivityRndPrdItemListVo {
	
	private String drapi_innb;
    private String drap_innb;
    private String dra_innb;
    private String oi_code;
    private String oi_code_nm;
    private String drapi_cur_value;
    
	public String getDrapi_innb() {
		return drapi_innb;
	}
	public void setDrapi_innb(String drapi_innb) {
		this.drapi_innb = drapi_innb;
	}
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
	public String getDrapi_cur_value() {
		return drapi_cur_value;
	}
	public void setDrapi_cur_value(String drapi_cur_value) {
		this.drapi_cur_value = drapi_cur_value;
	}
	@Override
	public String toString() {
		return "ActivityRndPrdItemListVo [drapi_innb=" + drapi_innb
				+ ", drap_innb=" + drap_innb + ", dra_innb=" + dra_innb
				+ ", oi_code=" + oi_code + ", oi_code_nm=" + oi_code_nm
				+ ", drapi_cur_value=" + drapi_cur_value + "]";
	}

}
