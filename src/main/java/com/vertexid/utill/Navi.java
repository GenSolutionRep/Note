package com.vertexid.utill;

import com.vertexid.vo.NaviVo;



/**
 * @Class명: Navi
 * @작성일: 2014. 10. 2
 * @작성자: 김진호
 * @설명: 페이지 네비게이션
 */
public class Navi {
	
	private int rowSize = 10;      //로우사이즈
	private int pageSize = 10;     //페이지 수
	private int currentPage;       //현재페이지
	private int totalRowCnt;       //총 로우수
	private String fnName;         //페이징처리 script 함수명
	private String type;
	
	/**
	 *@인자값
	 *<pre>String FnNm,	(펑션명)
	 *int totRow,	(전체로우)
	 *int currPg,	(현페이지번호)
	 *int viewRowSize	(리스트수)
	 *</pre>
	*/	
	public Navi (String FnNm,int totRow, int currPg, int viewRowSize) {
		this.setFnName(FnNm);
		this.setTotalRowCnt(totRow);
		this.setCurrentPage(currPg);
		this.setRowSize(viewRowSize);
	}
	/**
	 *@인자값
	 *<pre>NaviVo vo		(NaviVo)
	 *int totRow	(전체로우)  
	*/	
	public Navi (NaviVo vo, int totRow) {
		this.setFnName(vo.getFnName());
		this.setTotalRowCnt(totRow);
		this.setCurrentPage(vo.getCurrPg());
		this.setRowSize(vo.getRowSize());
		this.setType(vo.getType());
	}
	/**
	 * @인자값 NaviVo vo 		(NaviVo 클래스)
	 */	
	public Navi (NaviVo vo) {
		this.setFnName(vo.getFnName());
		this.setTotalRowCnt(vo.getTotRow());
		this.setCurrentPage(vo.getCurrPg());
		this.setRowSize(vo.getRowSize());	
		this.setType(vo.getType());
	}
	/**
	 *@설명 
	 *<pre>인자값 String FnNm,	(펑션명)
	 *int totRow,	(전체로우) 
	 *int currPg,	(현페이지번호) 
	 *화면 출력 Default Row  10줄
	 *</pre>
	*/	
	public Navi (String FnNm,int totRow, int currPg) {
		this.setFnName(FnNm);
		this.setTotalRowCnt(totRow);
		this.setCurrentPage(currPg);		
	}
	
	/**
	 * @메서드명: getPageNavi()
	 * @설명: paging 처리 Html 리턴
	 * @return String
	*/	
	public String getPageNavi() {
		
		int totalPageCnt = ((totalRowCnt + rowSize - 1) / rowSize);
		boolean nowFirst = (currentPage <= pageSize);
		boolean nowEnd = (totalPageCnt - 1) / pageSize * pageSize < currentPage;
		
		
		StringBuffer sbHtml = new StringBuffer();
		if(totalRowCnt > 0){
			int iStartPage = (currentPage - 1) / pageSize * pageSize + 1;
			int iEndPage = iStartPage + pageSize - 1;
			if(iEndPage > totalPageCnt)
				iEndPage = totalPageCnt;
			
			
			sbHtml.append("<ul class='pagination'>\n");
			if (nowFirst) {
				sbHtml.append("  <li><a href='#'> <span><i class='fa fa-angle-double-left'></i></span> </a></li>\n");
			} else {
				if (StringCheck.isNull(type)) {
					sbHtml.append("  <li><a href='#'  onclick='javascript:"+fnName+"("+ (iEndPage - 10) + ")'> <span><i class='fa fa-angle-double-left'></i></span> </a></li>\n");
				} else {
					sbHtml.append("  <li><a href='#'  onclick='javascript:"+fnName+"("+ (iEndPage - 10) + "," +  "\"" + type + "\")'> <span><i class='fa fa-angle-double-left'></i></span> </a></li>\n");
				}
				
				
				
			}
			
			for (int i = iStartPage; i <= iEndPage; i++) {
				if (currentPage == i) {
					sbHtml.append("     <li><a  class='page_on'>"+ i +"</a></li>\n");
				} else {
					if (StringCheck.isNull(type)) {
						sbHtml.append("     <li><a href='#' onclick='javascript:"+fnName+"("+ i + ")'> " + i + " </a></li> \n");
					} else {
						sbHtml.append("     <li><a href='#' onclick='javascript:"+fnName+"("+ i + "," +  "\"" + type + "\")'> " + i + " </a></li> \n");
					}
					
				}
			}		
			if (nowEnd) {
				sbHtml.append("   <li><a href='#'> <span><i class='fa fa-angle-double-right'></i></span> </a></li>\n");
			} else {
				if (StringCheck.isNull(type)) {
					sbHtml.append("   <li><a href='#'  onclick='javascript:"+fnName+"("+ (iStartPage + 10) + ")'> <span><i class='fa fa-angle-double-right'></i></span> </a></li>\n");
				} else {
					sbHtml.append("   <li><a href='#'  onclick='javascript:"+fnName+"("+ (iStartPage + 10) + "," +  "\"" + type + "\")'> <span><i class='fa fa-angle-double-right'></i></span> </a></li>\n");
				}
			}
			sbHtml.append("</ul>\n");
		}
		return sbHtml.toString();

	}
	
	public String getFnName() {
		return fnName;
	}
	public void setFnName(String fnName) {
		this.fnName = fnName;
	}
	public int getRowSize() {
		return rowSize;
	}
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRowCnt() {
		return totalRowCnt;
	}
	public void setTotalRowCnt(int totalRowCnt) {
		this.totalRowCnt = totalRowCnt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
}
