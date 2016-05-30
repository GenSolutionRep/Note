<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-600.js"></script>

</head>
<body>
<article class="content">

<table style="width: 100%;" >
<tr>

	<section>
		<div class="page_nav">
			<h1>메뉴관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>메뉴관리
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="btn_gup">
				<button class="skyblue" id="btnMenuInsert" >메뉴추가</button>
			</div>
		</div>
	</section>
<tr>
<colgroup>
	<col width="30%" >
	<col width="70%" >
</colgroup>
<tr>

	<td>
		<div id="baseMenuList" class="mtree-wrap" >
		</div>
	</td>
	<td>
		<div class="panel-body">
			<table summary="메뉴리스트" class="tbl_col">
				<caption>메뉴목록</caption>
				<colgroup>
					<col span="6">
				</colgroup>
				<thead>
					<tr>    			
						<th scope="col">메뉴코드</th>
						<th scope="col">코드명</th>
						<th scope="col">메뉴URL</th>
						<th scope="col">정렬순서</th>
						<th scope="col">사용여부</th>
						<th scope="col">수정</th>
					</tr>
				</thead>
				<tbody id="tbody99600">
				</tbody>
			</table>
		</div>
	</td>
</tr>
</table>
<!-- 레이어 팝업 시작 -->
<form id="frmMenu">
<input type="hidden" name="save_type_99600" id="save_type_99600"/>

<div id="menuPopSpace" >
	<div id="tempPop99600" style="display:none  ;">
		<div class="popup"  style="width: 800px;" >
				<div class="phead">
					<span id="">메뉴관리</span>
					<a id="tempCloseX99100" class="phead-closex" >닫기</a>
				</div>
				<div class="con">
					<div class="tbl_title" >
						<span>메뉴관리</span>
					</div>
					<table class="tbl_row">
						<colgroup >
							<col width="15%">
							<col width="85%">
						<col>
						</colgroup>
						<tr>
							<th>메뉴코드</th>
							<td>
								<!-- <input id="m_no" name="m_no" type="text" value="" disabled="disabled" maxlength="5"  > -->
								<input id="m_no" name="m_no" type="text" value="" maxlength="5" readonly="readonly" >
							</td>
						</tr>
						<tr>
							<th>상위메뉴</th>
							<td>
								<!-- 
								<select id="m_parent_no" name ="m_parent_no" style="margin-right: 6px;">
								</select> 
								-->
								<!-- <div id ="divMenuCombo">
									<select id="m_parent_no1" name ="m_parent_no1" style="margin-right: 6px;">
									</select> 
									<input type="hidden" name="m_parent_no" id="m_parent_no"/>
								</div> -->
								<input id="m_parent_no" name="m_parent_no" type="hidden" value="">
								<input id="m_parent_name" name="m_parent_name" type="text" value="" readonly="readonly" >
								* 설명필요.
								
							</td>
						</tr>
						<tr>
							<th>메뉴명</th>
							<td>
								<input id="m_nm" name="m_nm" type="text" maxlength="100" value="" >
							</td>
						</tr>
						<tr>
							<th>메뉴 url</th>
							<td>
								<input id="m_url" name="m_url" type="text" maxlength="100" value="" >
							</td>
						</tr>
						<tr>
							<th>정렬순서</th>
							<td>
								<input id="m_order" name="m_order" type="text" maxlength="100"  value=""  >
							</td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td>
								<input type="radio" name = "m_use_yn" id="pop_m_use_y" value="Y" checked='checked'>사용&nbsp;&nbsp;
								<input type="radio" name = "m_use_yn" id="pop_m_use_n" value="N">미사용
							</td>
						</tr>
						<tr>
							<th>메모</th>
							<td>
								<textarea id=m_note" name="m_note" cols="60" rows="6" maxlength="500">
								</textarea>
							</td>
						</tr>
					</table>
					<div> </div>
					<div class="pfooter">
		       			<button type='button' class="red" id="tempSave99600">저장</button>
		       			<button type='button' class="white" id="tempClose99600">닫기</button>
					</div>
				</div>
			</div>
			<div class="popup_overlay"></div>
		</div>
		
</div>
<!-- 레이어 팝업 종료 -->
</form>
</article>		
<!-- </body>
</html> -->
