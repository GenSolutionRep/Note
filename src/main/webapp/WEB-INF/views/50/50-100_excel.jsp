<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="application/vnd.ms-excel;charset=euc-kr" pageEncoding="euc-kr"%>
<%
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy_MM_dd");
	String today = formatter.format(new java.util.Date());
	String file_name = "storeList_" + today;
	String ExcelName  = new String(file_name.getBytes("8859_1"),"euc-kr");

	response.setHeader("Content-Disposition", "attachment; filename="+ExcelName+".xls"); 
    response.setHeader("Content-Description", "JSP Generated Data");
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<table border='1'>
		<thead>
			<tr>
				<th colspan='11'>매장현황</th>
			</tr>
			<tr>
				<th>No.</th>
				<th>고객그룹</th>
				<th>관리업체</th>
				<th>매장명</th>
				<th>담당사원</th>
				<th>유형</th>
				<th>차수</th>
				<th>대표전화</th>
				<th>팩스번호</th>
				<th>cvs</th>
				<th>운영상태</th>
			</tr>
		</thead>
		<tbody id='tbodyExcel50100'>
			<c:set var="cnt" value="0" />
			<c:forEach var="storeList" items="${ajax.result}">
				<c:set var="cnt" value="${cnt+1}" />
				<tr>
					<td>${cnt}</td>
					<td>${storeList.cg_nm}</td>
					<td>${storeList.me_nm}</td>
					<td>${storeList.sm_nm}</td>
					<td>${storeList.em_nm}</td>
					<td>${storeList.se_empl_ty_nm}</td>
					<td>${storeList.sm_odr_nm}</td>
					<td>${storeList.sm_tlphon}</td>
					<td>${storeList.sm_fxnum}</td>
					<td>${storeList.sm_cvscafe_at}</td>
					<td>${storeList.sm_oper_nm}</td>
				</tr>
			</c:forEach>
				<tr>
					<td>총 매장</td>
					<td colspan = '10' class='txt_left'>${cnt}</td>
				</tr>
		</tbody>
	</table>
</body>
</html>

