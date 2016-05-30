<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="application/vnd.ms-excel;charset=euc-kr" pageEncoding="euc-kr"%>
<%
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
	String today = formatter.format(new java.util.Date());
	String file_name = "attandance_" + today;
	String ExcelName  = new String(file_name.getBytes("8859_1"),"euc-kr");

	response.setHeader("Content-Disposition", "attachment; filename="+ExcelName+".xls"); 
    response.setHeader("Content-Description", "JSP Generated Data");
    
	String linePath = request.getRealPath("/") + "/resources/images/line.JPG";
	//String linePath = "D:\\workspace-sts-dasa\\DASA\\src\\main\\webapp\\resources\\images\\line.JPG"; 

%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	console.log(<%=linePath%>);
</script>
<script type="text/javascript">
$(document).ready(function(){
	
	var week = new Array('일', '월', '화', '수', '목', '금', '토');
	
	var y = parseInt(${ajax.yyyy});
	var m = parseInt(${ajax.mm});
	var d ='';
    var $tds = $('#tbl_excel_20200 thead tr:eq(0)').find('th');
    
    for (var i = 5; i < $tds.length-15; i++){
	 d = $tds.eq(i).text();
	 day = new Date(y + '-' + m + '-' + d).getDay();
	 dayLabel = week[day];
	 $('#tbl_excel_20200 thead tr:eq(1) th:nth-child('+ (i-4) +')').text("gg");
	 
    }
});
</script>

</head>
<body>
	<table  border='1' id='tbl_excel_20200' style="font-size:15px;">
		<thead >
			<tr>
				<th colspan=${ajax.last_day+5+1} style="text-align:left; font-size: 25px;">사원근무실적 (${ajax.yyyy}년 ${ajax.mm}월)</th>
				<th colspan=10 style="height: 130px;">
					<%-- <div style="height: 130px; width: 500px; float: right; overflow: hidden; vertical-align :middle;">
				    	<img src=<%=linePath%> height=117  width=497 border=3>
					</div> --%>
				</th>
			</tr>
			<tr>
				<td colspan=${ajax.last_day+20} style="text-align:left;">
				<c:choose>
					<c:when test="${ajax.bhf_name=='선택'}">
						지점  : 전체
					</c:when>
					<c:otherwise>
						지점  : ${ajax.bhf_name}
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr style="">
					<th scope="col" rowspan="2" style="text-align:center;"> </th>
					<th scope="col" rowspan="2" style="text-align:center;">지점</th>
					<th scope="col" rowspan="2" style="text-align:center;">사번</th>
					<th scope="col" rowspan="2" style="text-align:center;">성명</th>
					<th scope="col" rowspan="2" style="text-align:center;">입사일</th>
					<th scope="col" rowspan="2" style="text-align:center;">직무</th>
					
					<th scope="col" style="text-align:center;">16</th>
					<th scope="col" style="text-align:center;">17</th>
					<th scope="col" style="text-align:center;">18</th>
					<th scope="col" style="text-align:center;">19</th>
					<th scope="col" style="text-align:center;">20</th>
					<th scope="col" style="text-align:center;">21</th>
					<th scope="col" style="text-align:center;">22</th>
					<th scope="col" style="text-align:center;">23</th>
					<th scope="col" style="text-align:center;">24</th>
					<th scope="col" style="text-align:center;">25</th>
					<th scope="col" style="text-align:center;">26</th>
					<th scope="col" style="text-align:center;">27</th>
					<c:if test="${ajax.last_day >=28}">
					<th scope="col" id='d28_20200' style="text-align:center;">28</th>
					</c:if>
					<c:if test="${ajax.last_day >=29}">
					<th scope="col" id='d29_20200' style="text-align:center;">29</th>
					</c:if>
					<c:if test="${ajax.last_day >=30}">
					<th scope="col" id='d30_20200' style="text-align:center;">30</th>
					</c:if>
					<c:if test="${ajax.last_day >=31}">
					<th scope="col" id='d31_20200' style="text-align:center;">31</th>
					</c:if>
					<th scope="col" style="text-align:center;">01</th>
					<th scope="col" style="text-align:center;">02</th>
					<th scope="col" style="text-align:center;">03</th>
					<th scope="col" style="text-align:center;">04</th>
					<th scope="col" style="text-align:center;">05</th>
					<th scope="col" style="text-align:center;">06</th>
					<th scope="col" style="text-align:center;">07</th>
					<th scope="col" style="text-align:center;">08</th>
					<th scope="col" style="text-align:center;">09</th>
					<th scope="col" style="text-align:center;">10</th>
					<th scope="col" style="text-align:center;">11</th>
					<th scope="col" style="text-align:center;">12</th>
					<th scope="col" style="text-align:center;">13</th>
					<th scope="col" style="text-align:center;">14</th>
					<th scope="col" style="text-align:center;">15</th>
					
					<th scope="col" rowspan="2" style="text-align:center; width:70px;">기준근무 일수</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">휴일 일수</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">평일 근무</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">휴일 근무</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">시간외 근무</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">실근무 일수</th>
					<%--<th scope="col" rowspan="2" style="text-align:center; width:40px; display: none;">기본 시간</th>--%>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">결근 일수</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">연차 휴가</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">하계 휴가</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">경조 휴가</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">교육</th>
					<th scope="col" rowspan="2" style="text-align:center; width:40px;">병가</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">기준 교통비</th>
					<th scope="col" rowspan="2" style="text-align:center; width:55px;">지급 교통비</th>
				</tr>
				<tr>
				<!-- 				
					<th scope="col">NO.</th>
					<th scope="col">지점</th>
					<th scope="col">사번</th>
					<th scope="col">이름</th>
					<th scope="col">입사일</th>
					<th scope="col">직무</th> 
				-->
					
					<c:set var="week" value="${iDayOfWeek_16}" />
 					<c:forEach var="d" begin="1" end="${ajax.last_day}" step="1">
 						<c:choose>		
	 						<c:when test="d eq 28 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 29 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 30 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 31 and d > ${ajax.last_day}"></c:when>
							<c:otherwise>
								<th scope="col" style="text-align:center;">					
						     	<c:choose>
							    	<c:when test="${week eq 1}">일</c:when>
							    	<c:when test="${week eq 2}">월</c:when>
							    	<c:when test="${week eq 3}">화</c:when>
							    	<c:when test="${week eq 4}">수</c:when>
							    	<c:when test="${week eq 5}">목</c:when>
							    	<c:when test="${week eq 6}">금</c:when>
							    	<c:when test="${week eq 7}">토</c:when>
							    </c:choose>
								</th>
							</c:otherwise>
						</c:choose>
						<c:choose>
						    <c:when test="${week eq 7}">
							    	<c:set var="week" value="1" />
						    </c:when>
					    	<c:otherwise>
						    	<c:set var="week" value="${week+1}" />
						    </c:otherwise>
				    	</c:choose>  
					</c:forEach>
					<%-- <c:set var="week" value="${iDayOfWeek_16}" />
 					<c:forEach var="d" begin="16" end="${ajax.last_day}" step="1">
 						<c:choose>		
	 						<c:when test="d eq 28 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 29 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 30 and d > ${ajax.last_day}"></c:when>
							<c:when test="d eq 31 and d > ${ajax.last_day}"></c:when>
							<c:otherwise>
								<th scope="col" style="text-align:center;">					
						     	<c:choose>
							    	<c:when test="${week eq 1}">일</c:when>
							    	<c:when test="${week eq 2}">월</c:when>
							    	<c:when test="${week eq 3}">화</c:when>
							    	<c:when test="${week eq 4}">수</c:when>
							    	<c:when test="${week eq 5}">목</c:when>
							    	<c:when test="${week eq 6}">금</c:when>
							    	<c:when test="${week eq 7}">토</c:when>
							    </c:choose>
								</th>
							</c:otherwise>
						</c:choose>
						<c:choose>
						    <c:when test="${week eq 7}">
							    	<c:set var="week" value="1" />
						    </c:when>
					    	<c:otherwise>
						    	<c:set var="week" value="${week+1}" />
						    </c:otherwise>
				    	</c:choose>  
					</c:forEach>

					<c:set var="week" value="${iDayOfWeek_01}" />
 					<c:forEach var="d" begin="1" end="15" step="1">
 						<th scope="col" style="text-align:center;">
				     	<c:choose>
					    	<c:when test="${week eq 1}">일</c:when>
					    	<c:when test="${week eq 2}">월</c:when>
					    	<c:when test="${week eq 3}">화</c:when>
					    	<c:when test="${week eq 4}">수</c:when>
					    	<c:when test="${week eq 5}">목</c:when>
					    	<c:when test="${week eq 6}">금</c:when>
					    	<c:when test="${week eq 7}">토</c:when>
					    </c:choose>
						</th>
						<c:choose>
						    <c:when test="${week eq 7}">
							    	<c:set var="week" value="1" />
						    </c:when>
					    	<c:otherwise>
						    	<c:set var="week" value="${week+1}" />
						    </c:otherwise>
				    	</c:choose> 
					</c:forEach> --%>

					<!-- <th scope="col" rowspan="2">기준근무일수</th>
					<th scope="col" rowspan="2">휴일일수</th>
					<th scope="col" rowspan="2">평일근무</th>
					<th scope="col" rowspan="2">휴일근무</th>
					<th scope="col" rowspan="2">시간외근무</th>
					<th scope="col" rowspan="2">실근무일수</th>
					<%--<th scope="col" rowspan="2" style="display: none;">기본시간</th>--%>
					<th scope="col" rowspan="2">결근일수</th>
					<th scope="col" rowspan="2">연차휴가</th>
					<th scope="col" rowspan="2">하계휴가</th>
					<th scope="col" rowspan="2">경조휴가</th>
					<th scope="col" rowspan="2">교육</th>
					<th scope="col" rowspan="2">병가</th>
					<th scope="col" rowspan="2">기준교통비</th>
					<th scope="col" rowspan="2">지급교통비</th> -->
				</tr>
		</thead>
		<tbody id='tbodyExcel20200' >
			<c:set var="cnt" value="0" />
			
			<c:set var="holiday_work" value="0" />
			<c:set var="overtime_work" value="0" />
			<c:set var="actual_work" value="0" />
			<c:set var="absent" value="0" />
			<c:set var="annual_leave" value="0" />
			<c:set var="summer_leave" value="0" />
			<c:set var="cc_leave" value="0" />
			<c:set var="education" value="0" />
			<c:set var="sick_leave" value="0" />
			<c:set var="trans_fee" value="0" />
			<c:set var="t_trans_fee" value="0" />
			
			<c:forEach var="list" items="${ajax.result}">
				<c:set var="cnt" value="${cnt+1}" />
				<c:set var="holiday_work" value="${holiday_work + list.ea_holidays_work}" />
				<c:set var="overtime_work" value="${overtime_work + list.ea_work_overtime}" />
				<c:set var="actual_work" value="${actual_work + list.ea_actual_work_days}" />
				<c:set var="absent" value="${absent + list.ea_absent_days}" />
				<c:set var="annual_leave" value="${annual_leave + list.ea_annual_leave}" />
				<c:set var="summer_leave" value="${summer_leave + list.ea_summer_holidays}" />
 			    <c:set var="cc_leave" value="${cc_leave + list.ea_cc_leave}" /> 			   
				<c:set var="education" value="${education + list.ea_education}" />
				<c:set var="sick_leave" value="${sick_leave + list.ea_sick_leave}" />
				<%-- <c:set var="t_trans_fee" value="${fn:replace(list.ea_trans_fee, ',', '')}" /> --%>
				<c:set var="t_trans_fee" value="${fn:trim(fn:replace(list.ea_trans_fee, ',', ''))}"/>
				<fmt:parseNumber type="number" var="tt_trans_fee">${t_trans_fee}</fmt:parseNumber>
				<c:set var="trans_fee" value="${trans_fee + tt_trans_fee}" />


					
				<tr>
					<td style="text-align:center;">${cnt}</td>
					<td style="text-align:center;">${list.bhf_name}</td>
					<td style="text-align:center;">${list.ea_em_no}</td>
					<td style="text-align:left;">${list.ea_em_nm}</td>
					<td style="text-align:center;">${list.em_ecny_de}</td>
					<td style="text-align:left;">${list.dty_name}</td>
					<td style="text-align:center;">${list.ea_day_16}</td>
					<td style="text-align:center;">${list.ea_day_17}</td>
					<td style="text-align:center;">${list.ea_day_18}</td>
					<td style="text-align:center;">${list.ea_day_19}</td>
					<td style="text-align:center;">${list.ea_day_20}</td>
					<td style="text-align:center;">${list.ea_day_21}</td>
					<td style="text-align:center;">${list.ea_day_22}</td>
					<td style="text-align:center;">${list.ea_day_23}</td>
					<td style="text-align:center;">${list.ea_day_24}</td>
					<td style="text-align:center;">${list.ea_day_25}</td>
					<td style="text-align:center;">${list.ea_day_26}</td>
					<td style="text-align:center;">${list.ea_day_27}</td>
					<c:if test="${ajax.last_day >=28}">
					<td style="text-align:center;">${list.ea_day_28}</td>
					</c:if>
					<c:if test="${ajax.last_day >=29}">
					<td style="text-align:center;">${list.ea_day_29}</td>
					</c:if>
					<c:if test="${ajax.last_day >=30}">
					<td style="text-align:center;">${list.ea_day_30}</td>
					</c:if>
					<c:if test="${ajax.last_day >=31}">
					<td style="text-align:center;">${list.ea_day_31}</td>
					</c:if>
					<td style="text-align:center;">${list.ea_day_01}</td>
					<td style="text-align:center;">${list.ea_day_02}</td>
					<td style="text-align:center;">${list.ea_day_03}</td>
					<td style="text-align:center;">${list.ea_day_04}</td>
					<td style="text-align:center;">${list.ea_day_05}</td>
					<td style="text-align:center;">${list.ea_day_06}</td>
					<td style="text-align:center;">${list.ea_day_07}</td>
					<td style="text-align:center;">${list.ea_day_08}</td>
					<td style="text-align:center;">${list.ea_day_09}</td>
					<td style="text-align:center;">${list.ea_day_10}</td>
					<td style="text-align:center;">${list.ea_day_11}</td>
					<td style="text-align:center;">${list.ea_day_12}</td>
					<td style="text-align:center;">${list.ea_day_13}</td>
					<td style="text-align:center;">${list.ea_day_14}</td>
					<td style="text-align:center;">${list.ea_day_15}</td>
					
					<td style="text-align:right;">${list.ea_std_work_days}</td>
					<td style="text-align:right;">${list.ea_holidays}</td>
					<td style="text-align:right;">${list.ea_weekdays_work}</td>
					<td style="text-align:right;">${list.ea_holidays_work}</td>
					<td style="text-align:right;">${list.ea_work_overtime}</td>
					<td style="text-align:right;">${list.ea_actual_work_days}</td>
					<%--<td style="text-align:right; display: none;">${list.ea_base_time}</td>--%>
					<td style="text-align:right;">${list.ea_absent_days}</td>
					<td style="text-align:right;">${list.ea_annual_leave}</td>
					<td style="text-align:right;">${list.ea_summer_holidays}</td>
					<td style="text-align:right;">${list.ea_cc_leave}</td>
					<td style="text-align:right;">${list.ea_education}</td>
					<td style="text-align:right;">${list.ea_sick_leave}</td>
					<td style="text-align:right;">${list.ea_std_trans_fee}</td>
					<td style="text-align:right;">${list.ea_trans_fee}</td>
					
				</tr>
			</c:forEach>
				<tr>
					<td>합계</td>
					<td style="text-align:left;" colspan = ${ajax.last_day+6-1} class='txt_left'>${cnt}</td>
					<td style="text-align:right;" class='txt_right'></td><%-- 기준근무일수--%>
					<td style="text-align:right;" class='txt_right'></td><%-- 휴일일수--%>
					<td style="text-align:right;" class='txt_right'></td><%-- 평일근무--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${holiday_work}</fmt:formatNumber></td><%-- 휴일근무--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${overtime_work}</fmt:formatNumber></td><%-- 시간외근무--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${actual_work}</fmt:formatNumber></td><%-- 실근무일수--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${absent}</fmt:formatNumber></td><%-- 결근일수--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${annual_leave}</fmt:formatNumber></td><%-- 연차--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${summer_leave}</fmt:formatNumber></td><%-- 하계--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${cc_leave}</fmt:formatNumber></td><%-- 경조--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${education}</fmt:formatNumber></td><%-- 교육--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${sick_leave}</fmt:formatNumber></td><%-- 병가--%>
					<td style="text-align:right;" class='txt_right'></td><%-- 기준교통비--%>
					<td style="text-align:right;" class='txt_right'><fmt:formatNumber type="number" pattern="###,###,##0">${trans_fee}</fmt:formatNumber></td><%-- 지금교통비--%>			
				</tr>
		</tbody>
	</table>
</body>
</html>

