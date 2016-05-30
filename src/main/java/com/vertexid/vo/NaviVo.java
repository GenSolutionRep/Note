package com.vertexid.vo;

import java.util.Map;
/**
 * @Class명: NaviVo
 * @작성일: 2014. 10. 18
 * @작성자: 김진호
 * @설명: 네비게이션 VO
 */
public class NaviVo {
	private String fnName;//페이지이동 script function 명
	private int start;//페이지 글시작 로우넘
	private int end;//페이지 글종료 로우넘
	private int currPg;//현페이지
	private int rowSize = 0;//로우사이즈
	private int totRow;//총로우 사이즈
	private String type;//화면유형코드
	private int auth_flag;//권한코드
	private String em_no;//사원번호
	private String cm_code;//회사코드
	private String dash_flag; //대쉬보드 여부
	private Map<String, String> params;//공통 파라메터
	
	public String getDash_flag() {
		return dash_flag;
	}

	public void setDash_flag(String dash_flag) {
		this.dash_flag = dash_flag;
	}

	public String getFnName() {
		return fnName;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getCurrPg() {
		return currPg;
	}

	public int getRowSize() {
		return rowSize;
	}

	public int getTotRow() {
		return totRow;
	}

	public String getType() {
		return type;
	}

	public int getAuth_flag() {
		return auth_flag;
	}

	public String getEm_no() {
		return em_no;
	}

	public String getCm_code() {
		return cm_code;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setFnName(String fnName) {
		this.fnName = fnName;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setCurrPg(int currPg) {
		this.currPg = currPg;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public void setTotRow(int totRow) {
		this.totRow = totRow;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAuth_flag(int auth_flag) {
		this.auth_flag = auth_flag;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public void setCm_code(String cm_code) {
		this.cm_code = cm_code;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getFirstRowNo(){
		int no = totRow - ((currPg-1) * rowSize);
		return no > 0 ? no +"": "";
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NaviVo[");
        sb.append("fnName=" + fnName + ",");
        sb.append("start=" + start + ",");
        sb.append("end=" + end + ",");
        sb.append("currPg=" + currPg + ",");
        sb.append("rowSize=" + rowSize + ",");
        sb.append("totRow=" + totRow + ",");
        sb.append("type=" + type + ",");
        sb.append("auth_flag=" + auth_flag + ",");
        sb.append("em_no=" + em_no + ",");
        sb.append("cm_code=" + cm_code + ",");
        sb.append("params=" + params);
		sb.append("]");
		return sb.toString();
	}
	
//	ORACLE
//	public String getPagingStart() {
//		//this.params = Encoder.isoToUtfMap(this.params);
//		String sQuery = "";
//		sQuery += "SELECT PAGE2.*														\n";
//		sQuery += "FROM (																\n";
//		sQuery += "       SELECT ROWNUM RN, PAGE1.*										\n";
//		sQuery += "		 FROM ( 														\n";
//
//		sQuery += "SELECT NO_PAGE.* , ROW_NUMBER() OVER (ORDER BY NO_DESC DESC) NO		\n";
//		sQuery += "FROM (																\n";
//		sQuery += "       SELECT NO_DESC_PAGE.* ,ROWNUM NO_DESC							\n";
//		sQuery += "		 FROM ( 														\n";
//		return sQuery;
//	}

//	public String getPagingEnd() {
//		int end = currPg * rowSize;
//		int start = end - rowSize;
//		this.end = end;
//		this.start = start;
//		String eQuery = "";
//		eQuery += "		)NO_DESC_PAGE 													\n";
//		eQuery += ") NO_PAGE															\n";
//		eQuery += "ORDER BY NO_DESC														\n";
//
//		eQuery += "		) PAGE1 														\n";
//		eQuery += "		WHERE ROWNUM <= " + end + "										\n";
//		eQuery += ") PAGE2 																\n";
//		eQuery += "WHERE PAGE2.RN > " + start + "										\n";
//		return eQuery;
//	}
// MYBATIS
	
	public String getPagingStart(){
		return "";
	}
	
	public String getPagingEnd(){
		int end = currPg * rowSize;
		int start = end - rowSize;
		String eQuery="LIMIT "+start+","+rowSize;
		if(rowSize == 0 ){
			eQuery = "";
		}
		return eQuery;
	}

}
