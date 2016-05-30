<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-200.js"></script>
</head>
<body>

<article class="content">

<section>
	<div class="page_nav">
		<h1>품목관리</h1>
		<p>
			<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>품목관리
		</p>
	</div>
</section>
<section>
<table style="width: 100%; vertical-align: top; " >
	<colgroup>
		<col style="width: 520px;" >
		<col>
	</colgroup>
	<tr>
		<td style=" vertical-align: top; ">
			<div id="baseList_99200" class="panel-body mtree-wrap" >
			</div>
		</td>
		<td style=" vertical-align: top; ">
			<section>
			<div class="searchbox">
				<h4 id="selectedCodeName_99200" >최상위코드</h4> 
				<div class="btn_gup">
					<button class="skyblue auth-insert" id="tempInsert99200" >코드추가</button>
				</div>
			</div>
			</section>
			
			<table summary="품목리스트" class="tbl_col">
				<caption>품목목록</caption>
				<colgroup>
					<col width="15%" >
					<col width="30%" >
					<col width="10%" >
					<col width="10%" >
					<col width="25%" >
				</colgroup>
				<thead>
					<tr>    			
						<th scope="col">코드</th>
						<th scope="col">코드명</th>
						<th scope="col">순서</th>
						<th scope="col">사용여부</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody id="tbody99200">
				</tbody>
			</table>
		</td>
	</tr>
</table>
</section>
<!-- 레이어 팝업 시작 -->
<div id="popSpace99200" >
	<div id="tempPop99200" style="display:none  ;">
		<div class="popup"  style="width: 600px;" >
				<div class="phead">
					<span id="">품목관리</span>
					<a id="tempCloseX99200" class="phead-closex" >닫기</a>
				</div>
				<div class="con">
					<div class="tbl_title" >
						<span>품목관리</span>
					</div>
					<form id="frm99200">
					<input type="hidden" name="save_type_99200" id="save_type_99200"/>
					<input type="hidden" name="pm_dp" id="pm_dp_99200" value=""/>
					<table class="tbl_row">
						<colgroup >
							<col width="15%">
							<col width="85%">
						<col>
						</colgroup>
						<tr>
							<th>제품군</th>
							<td>
								<select id="pm_parent_no_D1_99200"  name ="pm_parent_no_D1_99200" class="select-basic" style="margin-right: 6px;">
								</select> 
							</td>
						</tr>
						<tr>
							<th>자재그룹</th>
							<td>
								<select id="pm_parent_no_D2_99200"  name ="pm_parent_no_D2_99200" class="select-basic" style="margin-right: 6px;">
								</select> 
							</td>
						</tr>
						<tr>
							<th>제품계층1</th>
							<td>
								<select id="pm_parent_no_D3_99200"  name ="pm_parent_no_D3_99200" class="select-basic" style="margin-right: 6px; width: 300px;">
								</select> 
							</td>
						</tr>
						<tr>
							<th>제품계층2</th>
							<td>
								<select id="pm_parent_no_D4_99200"  name ="pm_parent_no_D4_99200" class="select-basic" style="margin-right: 6px; width: 300px;">
								</select>
								<input id="pm_parent_no_99200" name="pm_parent_no" type="hidden" value=""/> 
							</td>
						</tr>
						<tr>
							<th>코드</th>
							<td>
								<!-- <input id="m_no" name="m_no" type="text" value="" disabled="disabled" maxlength="5"  > -->
								<input id="pm_code_99200" name="pm_code" class="input-basic" type="text" value="" maxlength="10" placeholder="" >
							</td>
						</tr>
						<tr>
							<th>코드명</th>
							<td>
								<input id=pm_nm_99200 name="pm_nm" type="text" maxlength="100" placeholder="" value="" >
							</td>
						</tr>
						<tr>
							<th>순서</th>
							<td>
								<input id="pm_sort_ordr_99200" name="pm_sort_ordr" class="input-basic" type="text" maxlength="10"  value=""  >
							</td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td>
								<div class="inputRadio">
										<input type="radio" name = "use_at" id="pop_use_at_y_99200" value="Y" checked='checked'>
										<label for="pop_use_at_y_99200"></label>
								</div>사용
								<div class="inputRadio">
										<input type="radio" name = "use_at" id="pop_use_at_n_99200" value="N">
										<label for="pop_use_at_n_99200"></label>
								</div>미사용
							</td>
						</tr>
						<tr>
							<th>메모</th>
							<td>
								<textarea id="pm_memo_99200" name="pm_memo" cols="60" rows="6" maxlength="500">
								</textarea>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<div class="pfooter">
	       			<button type='button' class="red auth-update" id="tempSave99200">저장</button>
	       			<button type='button' class="white" id="tempClose99200">닫기</button>
				</div>
			</div>
		</div>
		
</div>
<!-- 레이어 팝업 종료 -->

</article>	
<jsp:include page="../inc/auth.jsp" flush='true' />	
</body>
</html>
