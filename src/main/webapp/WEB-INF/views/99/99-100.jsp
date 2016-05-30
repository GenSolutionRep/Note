<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-100.js"></script>
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
				<!-- <button class="red" id="tempSearch99100" >조회</button> -->
				<button class="skyblue auth-insert" id="tempInsert99100" >등록</button>
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
			<tbody id="tbody99100">
			</tbody>
		</table>
	<!-- //UI Object -->
	</section>

	<div id="companyPopSpace" >
		<div id="tempPop99100" style="display:none  ;">
			<div class="popup"  style="width: 600px;" >
			
					<div class="phead">
						<span id="">회사관리</span>
						<a id="tempCloseX99100" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<div class="tbl_title" >
							<span>회사관리</span>
						</div>
						<form id="frm99100">
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="85%">
							<col>
							</colgroup>
							<tr>
								<th>회사코드</th>
								<td>
									<input id="cmpnyPop_cm_code" name="cm_code" class="input-basic" type="text" value="" maxlength="3" placeholder="코드 자동 등록" readonly="readonly" >
								</td>
							</tr>
							<tr>
								<th>회사명</th>
								<td>
									<input id="cmpnyPop_cm_nm" name="cm_nm" type="text" placeholder="" maxlength="50" value="" >
								</td>
							</tr>
							<tr>
								<th>사용여부</th>
								<td>
									<div class="inputRadio">
										<input type="radio" name="use_at" id="cmpnyPop_use_at_y" value="Y" checked='checked'>
										<label for="cmpnyPop_use_at_y"></label>
									</div>사용
									<div class="inputRadio">
										<input type="radio" name="use_at" id="cmpnyPop_use_at_n" value="N" checked='checked'>
										<label for="cmpnyPop_use_at_n"></label>
									</div>미사용
								</td>
							</tr>
							<tr>
								<th>순서</th>
								<td>
									<input id="cmpnyPop_cm_sort_ordr" name="cm_sort_ordr" class="input-basic" type="text" maxlength="5"  value=""  >
								</td>
							</tr>
							<tr>
								<th>메모</th>
								<td>
									<textarea id="cmpnyPop_cm_memo" name="cm_memo" cols="60" rows="6" maxlength="250">
									</textarea>
								</td>
								<input type="hidden" name="save_type_99110" id="save_type_99110"/>
							</tr>
						</table>
						</form>
					</div>
					<div class="pfooter">
		       			<button type='button' class="red auth-update" id="tempSave99100">저장</button>
		       			<button type='button' class="white" id="tempClose99100">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
			</div>
	</div>
	

</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
