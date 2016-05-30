<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<script type="text/javascript" src="<%=jsPath%>/routine/baseInfo/company.js"></script>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>회사관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>회사관리
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<!-- <div class="input_gup">
				<select style="width:108px">
					<option value="">근태일</option><option value="">기안일</option>
				</select>
				<input type="text" class="input_databox" size="10" placeholder="날짜 입력">
				<span class="wave"></span>
				<input type="text" class="input_databox" size="10" placeholder="날짜 입력">
			</div> -->
			<div class="btn_gup">
				<button class="red" id="btnCmpnySearch" >조회</button>
				<button class="skyblue" id="btnCmpnyInsert" >등록</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="회사관리 리스트" class="tbl_col">
			<caption>회사목록</caption>
				<colgroup>
					<col span="5">
				</colgroup>
			<thead>
				<tr>
					<th scope="col">회사코드</th>
					<th scope="col">회사명</th>
					<th scope="col">순서</th>
					<th scope="col">사용 여부</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody id="cmpnyTbody">
			</tbody>
		</table>
	<!-- //UI Object -->
	</section>

	<form id="frmCompany">
	<input type="hidden" name="save_type" id="save_type"/>
	<div id="companyPopSpace" >
		<div id="companyPop" style="display:none  ;">
			<div class="popup"  style="width: 800px;" >
					<div class="phead">
						<span id="">회사관리</span>
						<a id="textCmpnyPopupClose" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<div class="tbl_title" >
							<span>회사관리</span>
						</div>
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="85%">
							<col>
							</colgroup>
							<tr>
								<th>회사코드</th>
								<td>
									<input id="cmpnyPop_cmpny_code" name="cmpny_code" type="text" value="" maxlength="3" readonly="readonly" >
								</td>
							</tr>
							<tr>
								<th>회사명</th>
								<td>
									<input id="cmpnyPop_cmpny_nm" name="cmpny_nm" type="text" maxlength="50" value="" >
								</td>
							</tr>
							<tr>
								<th>사용여부</th>
								<td>
									<input type="radio" name="use_at" id="cmpnyPop_use_at_y" value="Y" checked='checked'>사용&nbsp;&nbsp;
									<input type="radio" name="use_at" id="cmpnyPop_use_at_n" value="N">미사용
								</td>
							</tr>
							<tr>
								<th>순서</th>
								<td>
									<input id="cmpnyPop_sort_ordr" name="sort_ordr" type="text" maxlength="5"  value=""  >
								</td>
							</tr>
							<tr>
								<th>메모</th>
								<td>
									<textarea id="cmpnyPop_memo" name="memo" cols="60" rows="6" maxlength="250">
									</textarea>
								</td>
							</tr>
						</table>
						<div class="pfooter">
			       			<button type='button' class="red" id="btnCmpnyPopSave">저장</button>
			       			<button type='button' class="white" id="btnCmpnyPopClose">닫기</button>
						</div>
					</div>
				</div>
				<div class="popup_overlay"></div>
			</div>
	</div>
	</form>

</article>
</body>
</html>
