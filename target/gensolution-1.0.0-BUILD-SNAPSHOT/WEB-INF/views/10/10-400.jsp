<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-400.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">


</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="cvsPop10400" style="display:none ;">
		<div class="popup" style="width: 1000px;">
			<div class="phead">
				<span>체크리스트</span>
				<a id="cvsPopCloseX10400" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_col">
					<colgroup>
						<col width="20%">
						<col width="30%">
						<col width="10%">
						<col width="40%">
					</colgroup>
					<tr>
						<th>항목</th>
						<th>정검 사항</th>
						<th>점검 결과</th>
						<th>비고</th>
					</tr>
					<tbody id="cvsItemTbody10400" >
					</tbody>
					<tbody id="cvsItemTfoot10400" >
					</tbody>
				</table>
			</div>
			<div class="pfooter">
				<button type="button" class="white" id="cvsPopClose10400">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>CVS 순회</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>CVS 순회
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchSmName70100" type="text" placeholder="매장명">
						<label for="searchSmName70100"><i class="fa fa-home"></i></label> 
					</span>
					<span>
						<input class="input-icon" id="searchEmName70100" type="text" placeholder="이름">
						<label for="searchEmName70100"><i class="fa fa-user"></i></label> 
					</span>
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode10400" class="select-basic"></select>
					</span>
					<span> <strong>팀명</strong></span>
					<span> 
						<select id="searchTmCode10400" class="select-basic"><option value=''>팀명</option></select>
					</span>
				</div>
				<div class="input_group clear">
					<span>
						<input class="input-icon" id="searchStartDate10200" type="text" readonly="readonly" placeholder="날짜선택" width="130px" value="">
						<label for="searchStartDate10200"><i class="fa fa-calendar"></i></label>
					</span> ~
					<span>
						<input class="input-icon" id="searchEndDate10200" type="text" readonly="readonly" placeholder="날짜선택" value="">
						<label for="searchEndDate10200"><i class="fa fa-calendar"></i></label>
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="cvsSearchBtn" >조회</button>
				<button class="gray" id="cvsSearchResetBtn" >초기화</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="CVS 리스트" class="tbl_col">
			<caption>
				CVS 순회 목록
			</caption>
			<colgroup>
				<col width="5%"  >
				<col width="20%"  >
				<col width="20%"  >
				<col width="20%"  >
				<col width="20%"  >
				<col width="15%"  >
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">지점명</th>
					<th scope="col">사원명</th>
					<th scope="col">매장명</th>
					<th scope="col">순방일자</th>
					<th scope="col">완료</th>
				</tr>
			</thead>
			<tbody id="cvsTbody10400" >
			</tbody>
		</table>
		<div id="cvsNavi10400"  class="paginate"> 
		</div>
	</section>
	<!-- //UI Object -->
</article>
</body>
</html>
