<%@ page language="java" contentType="application/vnd.ms-excel;charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ page import = "com.vertexid.utill.CommonUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String fileName = "employeeList-" + CommonUtil.getCurrentDate() + ".xls";
response.setHeader("Content-Disposition", "attachment; filename="+fileName);
response.setHeader("Content-Description", "JSP Generated Data");
%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th colspan="19">사원현황</th>
            </tr>
            <tr>
                <th>No</th>
                <th>지점명</th>
                <th>팀명</th>
                <th>이름</th>
                <th>아이디</th>
                <th>입사일</th>
                <th>퇴사일</th>
                <th>직책명</th>
                <th>직무명</th>
                <th>생년월일</th>
                <th>달력유형명</th>
                <th>성별</th>
                <th>휴대전화</th>
                <th>결혼기념일</th>
                <th>기준교통비</th> 
                <th>재직여부</th>
                <th>우편번호</th>
                <th>주소</th>
                <th>상세주소</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="cnt" value="0" />
            <c:forEach var="employeeList" items="${ajax.employeeList}">
                <c:set var="cnt" value="${cnt+1}" />
                <tr>
                    <td>${cnt}</td>
                    <td>${employeeList.bhf_nm}</td>
                    <td>${employeeList.team_nm}</td>
                    <td>${employeeList.em_nm}</td>
                    <td>${employeeList.em_id}</td>
                    <td>${employeeList.em_ecny_de}</td>
                    <td>${employeeList.em_retire_de}</td>
                    <td>${employeeList.em_rspofc_nm}</td>
                    <td>${employeeList.em_dty_nm}</td>
                    <td>${employeeList.em_brthdy_nm}</td>
                    <td>${employeeList.em_cldr_nm}</td>
                    <td>${employeeList.em_sexdstn_nm}</td>
                    <td>${employeeList.em_mbtl_num}</td>
                    <td>${employeeList.em_mrnry_de_nm}</td>
                    <td>${employeeList.em_trans_fee}</td>
                    <td>${employeeList.use_at_nm}</td>
                    <td>${employeeList.em_zipcd}</td>
                    <td>${employeeList.em_adres}</td>
                    <td>${employeeList.em_dtadres}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
