<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-200.js"></script>
<style type="text/css">
.fixed-table td{
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}
.fixed-table{
border-bottom: 0; width:390px ;   max-width: 390px; position: absolute; background-color: #fff ;
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
			<h1>사원근무실적</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>분석통계<span class="clamp fa"></span>사원근무실적
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">								
				<span style="margin-left: 12px">
					<select class="select-basic"  id="om_code_20200"  name ="om_code_20200" style="width: 120px">
					</select> 
				</span>
				<span style="margin-left: 12px">
					<strong>연월</strong>
					<input type="text" class="input-basic" id="yy_mm_20200" size="20" style="width: 70px;" readonly="readonly" >
				</span>
				<span style="margin-left: 12px">
					<strong>직무</strong>
					<select id="dty_code_20200"  name ="dty_code_20200" style="width: 100px;" ></select> 
				</span>
				<span style="margin-left: 12px">
					<strong>사원명</strong>
					<input type="text" class="input-basic" id="em_nm_20200" size="20" style="width: 90px;" >
				</span>
				<span style="margin-left: 12px">
					<strong>사번</strong>
					<input type="text" class="input-basic" id="em_no_20200" size="20" style="width: 90px;" >
				</span>

			</div>
			<div class="btn_gup clear">
				<button type="button" class="red" id="btnSearch_20200" >
					조회
				</button>
				<button type="button" class="navy" id="btnClear_20200" >
					초기화
				</button>
				<button type="button" class="skyblue auth-down" id="btnExcel_20200">
					<i class="fa fa-file-excel-o fa-lg"></i> 엑셀
				</button>
				<button type="button" class="skyblue" id="btnPrint_20200" style="display: none;">
					인쇄
				</button>
			</div>
		</div>
	</section>
	<section>
	<div style="overflow: auto;">
	<!-- UI Object -->
		<table summary="left헤더" class="tbl_row fixed-table"  id ="tbl20200_left" style="width: 390px;">
			<caption>좌측 리스트</caption>
				<colgroup >
					<col style="width: 30px;" >
					<col style="width: 80px;" >
					<col style="width: 80px;">
					<col style="width: 70px;">
					<col style="width: 80px;">
					<col style="width: 50px;"><!-- <col style="width: 1.8%;"> -->
				</colgroup>
			<thead>
				<tr style="height: 54.5px;">
					<th scope="col" rowspan="2" style="text-align:center;"></th>
					<th scope="col" rowspan="2" style="text-align:center;">지점</th>
					<th scope="col" rowspan="2" style="text-align:center;">사번</th>
					<th scope="col" rowspan="2" style="text-align:center;">이름</th>
					<th scope="col" rowspan="2" style="text-align:center;">입사일</th>
					<th scope="col" rowspan="2" style="text-align:center;">직무</th>
				</tr>
				<tr>
				</tr>
			</thead>
			<tbody id="tbody20200_left">
			</tbody>
		</table>
		
		<table summary="사원근무실적 리스트" class="tbl_row"  id ="tbl20200_right" style="width: 2898px !important;  margin-left: 390px;">
			<caption>사원근무실적 리스트</caption>
				<colgroup >
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 60px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					<col style="width: 63px;">
					
				</colgroup>
			<thead>
				<tr style="">
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
					<th scope="col" id='d28_20200' style="text-align:center;">28</th>
					<th scope="col" id='d29_20200' style="text-align:center;">29</th>
					<th scope="col" id='d30_20200' style="text-align:center;">30</th>
					<th scope="col" id='d31_20200' style="text-align:center;">31</th>
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
					
					<th scope="col" rowspan="2" style="text-align:center;">기준근무일수</th>
					<th scope="col" rowspan="2" style="text-align:center;">휴일일수</th>
					<th scope="col" rowspan="2" style="text-align:center;">평일근무</th>
					<th scope="col" rowspan="2" style="text-align:center;">휴일근무</th>
					<th scope="col" rowspan="2" style="text-align:center;">시간외근무</th>
					<th scope="col" rowspan="2" style="text-align:center;">실근무일수</th>
					<th scope="col" rowspan="2" style="text-align:center; display: none;" >기본시간</th>
					<th scope="col" rowspan="2" style="text-align:center;">결근일수</th>
					<th scope="col" rowspan="2" style="text-align:center;">연차휴가</th>
					<th scope="col" rowspan="2" style="text-align:center;">하계휴가</th>
					<th scope="col" rowspan="2" style="text-align:center;">경조휴가</th>
					<th scope="col" rowspan="2" style="text-align:center;">교육</th>
					<th scope="col" rowspan="2" style="text-align:center;">병가</th>
					<th scope="col" rowspan="2" style="text-align:center;">기준교통비</th>
					<th scope="col" rowspan="2" style="text-align:center;">지급교통비</th>
				</tr>
				<tr>
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
					<th scope="col" style="text-align:center;" id='w28_20200'>28</th>
					<th scope="col" style="text-align:center;" id='w29_20200'>29</th>
					<th scope="col" style="text-align:center;" id='w30_20200'>30</th>
					<th scope="col" style="text-align:center;" id='w31_20200'>31</th>
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
			<tbody id="tbody20200_right">
			</tbody>
		</table>
	<!-- //UI Object -->
	</div>
	</section>
	<section id="navi20200" class="paginate"/>


</article>
</body>
</html>
