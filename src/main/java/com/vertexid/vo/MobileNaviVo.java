package com.vertexid.vo;

/**
 * @파일명: MobileNaviVo.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 모바일 NaviVo
 */
public class MobileNaviVo extends NaviVo {

	private String kind;//종류
	private String cm_code;//회사코드
	private String cm_nm;//회사명
	private String om_code;//지점코드
	private String om_nm;//지점명
	private String cg_code;//고객그룹코드
	private String cg_nm;//고객그룹명
	private String me_code;//관리업체코드
	private String me_nm;//관리업체명
	private String sm_code;//매장코드
	private String sm_nm;//매장명
	private String em_nm;//사원명
	private String sm_odr;//차수
	private String sm_cvscafe_at;//CVS Cafe 여부
	private String sm_rprsntv_nm;//대표자명
	private String ad_date_from;//기안 조회시작일
	private String ad_date_to;//기안 조회종료일
	private String am_approver_em_no;//결재자
	private String is_work;//재직여부
	private String except_em_no;//조회제외대상 사원번호
	private String search_date_from;//검색시작일
	private String search_date_to;//검색종료일
	private String search_key;//검색구분
	private String search_value;//검색값
	private String yyyyMm;//년월
	private String t_days;//주요행사일

	public String getKind() {
		return kind;
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

	public String getEm_nm() {
		return em_nm;
	}

	public String getSm_odr() {
		return sm_odr;
	}

	public String getSm_cvscafe_at() {
		return sm_cvscafe_at;
	}

	public String getSm_rprsntv_nm() {
		return sm_rprsntv_nm;
	}

	public String getAd_date_from() {
		return ad_date_from;
	}

	public String getAd_date_to() {
		return ad_date_to;
	}

	public String getAm_approver_em_no() {
		return am_approver_em_no;
	}

	public String getIs_work() {
		return is_work;
	}

	public String getExcept_em_no() {
		return except_em_no;
	}

	public String getSearch_date_from() {
		return search_date_from;
	}

	public String getSearch_date_to() {
		return search_date_to;
	}

	public String getSearch_key() {
		return search_key;
	}

	public String getSearch_value() {
		return search_value;
	}

	public String getYyyyMm() {
		return yyyyMm;
	}

	public String getT_days() {
		return t_days;
	}

	public void setKind(String kind) {
		this.kind = kind;
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

	public void setEm_nm(String em_nm) {
		this.em_nm = em_nm;
	}

	public void setSm_odr(String sm_odr) {
		this.sm_odr = sm_odr;
	}

	public void setSm_cvscafe_at(String sm_cvscafe_at) {
		this.sm_cvscafe_at = sm_cvscafe_at;
	}

	public void setSm_rprsntv_nm(String sm_rprsntv_nm) {
		this.sm_rprsntv_nm = sm_rprsntv_nm;
	}

	public void setAd_date_from(String ad_date_from) {
		this.ad_date_from = ad_date_from;
	}

	public void setAd_date_to(String ad_date_to) {
		this.ad_date_to = ad_date_to;
	}

	public void setAm_approver_em_no(String am_approver_em_no) {
		this.am_approver_em_no = am_approver_em_no;
	}

	public void setIs_work(String is_work) {
		this.is_work = is_work;
	}

	public void setExcept_em_no(String except_em_no) {
		this.except_em_no = except_em_no;
	}

	public void setSearch_date_from(String search_date_from) {
		this.search_date_from = search_date_from;
	}

	public void setSearch_date_to(String search_date_to) {
		this.search_date_to = search_date_to;
	}

	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}

	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}

	public void setYyyyMm(String yyyyMm) {
		this.yyyyMm = yyyyMm;
	}

	public void setT_days(String t_days) {
		this.t_days = t_days;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NaviVo[");
		sb.append("params=" + super.getParams() + ",");
		sb.append("rowSize=" + super.getRowSize() + ",");
		sb.append("currPg=" + super.getCurrPg() + ",");
		sb.append("totRow=" + super.getTotRow() + ",");
		sb.append("fnName=" + super.getFnName() + ",");
		sb.append("start=" + super.getStart() + ",");
		sb.append("end=" + super.getEnd() + ",");
		sb.append("type=" + super.getType() + ",");
		sb.append("auth_flag=" + super.getAuth_flag() + ",");
		sb.append("em_no=" + super.getEm_no() + ",");
		sb.append("kind=" + kind + ",");
		sb.append("cm_code=" + cm_code + ",");
		sb.append("cm_nm=" + cm_nm + ",");
		sb.append("om_code=" + om_code + ",");
		sb.append("om_nm=" + om_nm + ",");
		sb.append("cg_code=" + cg_code + ",");
		sb.append("cg_nm=" + cg_nm + ",");
		sb.append("me_code=" + me_code + ",");
		sb.append("me_nm=" + me_nm + ",");
		sb.append("sm_code=" + sm_code + ",");
		sb.append("sm_nm=" + sm_nm + ",");
		sb.append("em_nm=" + em_nm + ",");
		sb.append("sm_odr=" + sm_odr + ",");
		sb.append("sm_cvscafe_at=" + sm_cvscafe_at + ",");
		sb.append("sm_rprsntv_nm=" + sm_rprsntv_nm + ",");
		sb.append("ad_date_from=" + ad_date_from + ",");
		sb.append("ad_date_to=" + ad_date_to + ",");
		sb.append("am_approver_em_no=" + am_approver_em_no + ",");
		sb.append("is_work=" + is_work + ",");
		sb.append("except_em_no=" + except_em_no + ",");
		sb.append("search_date_from=" + search_date_from + ",");
		sb.append("search_date_to=" + search_date_to + ",");
		sb.append("search_key=" + search_key + ",");
		sb.append("search_value=" + search_value + ",");
		sb.append("yyyyMm=" + yyyyMm + ",");
		sb.append("t_days=" + t_days);
		sb.append("]");
		return sb.toString();
	}

}
