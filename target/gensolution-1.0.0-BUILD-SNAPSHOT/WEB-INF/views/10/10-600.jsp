<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-600.js"></script>
<style type="text/css">
.fixed-table td{
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}
.fixed-table{
border-bottom: 0; width:510px ;   max-width: 510px; position: absolute; background-color: #fff ;
}
.fixed-table tfoot > tr{
background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB
}
</style>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>MD팀장근태</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>MD팀장근태
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">								
				<span style="margin-left: 12px">
					<select class="select-basic"  id="om_code_10600"  name ="om_code_10600" style="width: 120px">
					</select> 
				</span>
				<span style="margin-left: 12px">
					<strong>연월</strong>
					<input type="text" class="input-basic" id="yy_mm_10600" size="20" style="width: 70px;" readonly="readonly" >
				</span>
				<span style="margin-left: 12px">
					<strong>사번</strong>
					<input type="text" class="input-basic" id="em_no_10600" size="20" style="width: 90px;" >
				</span>
				<span style="margin-left: 12px">
					<strong>사원명</strong>
					<input type="text" class="input-basic" id="em_nm_10600" size="20" style="width: 90px;" >
				</span>

			</div>
			<div class="btn_gup clear">
				<button type="button" class="red" id="btnSearch_10600" >
					조회
				</button>
				<button type="button" class="navy" id="btnClear_10600" >
					초기화
				</button>
				<button type="button" class="skyblue auth-down" id="btnExcel_10600">
					<i class="fa fa-file-excel-o fa-lg"></i> 엑셀
				</button>
				<button type="button" class="skyblue" id="btnPrint_10600" style="display: none;">
					인쇄
				</button>
			</div>
		</div>
	</section>
	<section>
	<div style="overflow: auto;">
	<!-- UI Object -->
		<table summary="MD팀장 출퇴근 리스트_left" class="tbl_row fixed-table"  id ="tbl10600_left" style="width: 195px;">
			<caption>MD팀장 출퇴근 리스트</caption>
				<colgroup >
					<col style="width: 50px;" >
					<col style="width: 75px;">
					<col style="width: 70px;">
				</colgroup>
			<thead>
				<tr style="height: 54.5px;">
					<th scope="col" rowspan="2" style="text-align:center;">No.</th>
					<th scope="col" rowspan="2" style="text-align:center;">사번</th>
					<th scope="col" rowspan="2" style="text-align:center;">성명</th>
				</tr>
				<tr>
				</tr>
			</thead>
			<tbody id="tbody10600_left">
			</tbody>
		</table>
		<table summary="MD팀장 출퇴근 리스트_right" class="tbl_row"  id ="tbl10600_right" style="width: 2900px;  margin-left: 195px;">
			<caption>MD팀장 출퇴근 리스트</caption>
				<colgroup >
					<!-- 
					<col style="width: 50px;" >
					<col style="width: 70px;">
					<col style="width: 75px;">
					 -->
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					<col style="width: 75px;">
					
					<col style="width: 75px;">
					
				</colgroup>
			<thead>
				<tr style="">
 					<!--<th scope="col" rowspan="2" style="text-align:center;"></th>
					<th scope="col" rowspan="2" style="text-align:center;">사번</th>
					<th scope="col" rowspan="2" style="text-align:center;">성명</th>
					 -->
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
					<th scope="col" id='d28_10600' style="text-align:center;">28</th>
					<th scope="col" id='d29_10600' style="text-align:center;">29</th>
					<th scope="col" id='d30_10600' style="text-align:center;">30</th>
					<th scope="col" id='d31_10600' style="text-align:center;">31</th>
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
				</tr>
				<tr>
<!-- 					<th scope="col">NO.</th>
					<th scope="col">사번</th>
					<th scope="col">성명</th>
					<th scope="col">입사일</th>
					<th scope="col">직무</th> -->
					
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;" id='w28_10600'>28</th>
					<th scope="col" style="text-align:center;" id='w29_10600'>29</th>
					<th scope="col" style="text-align:center;" id='w30_10600'>30</th>
					<th scope="col" style="text-align:center;" id='w31_10600'>31</th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
					<th scope="col" style="text-align:center;"></th>
				</tr>
			</thead>
			<tbody id="tbody10600_right">
			</tbody>
		</table>
	<!-- //UI Object -->
	</div>
	</section>
	<section id="navi10600" class="paginate"/>


</article>
</body>
</html>
