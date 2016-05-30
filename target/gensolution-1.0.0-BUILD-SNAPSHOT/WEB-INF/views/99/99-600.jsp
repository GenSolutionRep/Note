<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-600.js"></script>
</head>
<body>
<article class="content">

<section>
	<div class="page_nav">
		<h1>메뉴관리</h1>
		<p>
			<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>메뉴관리
		</p>
	</div>
</section>

<table style="width: 100%; vertical-align: top;" >
	<colgroup>
		<col style="width: 280px;" >
		<col>
	</colgroup>
	<tbody>
	<tr>
		<td style="vertical-align: top;">
			<div id="baseMenuList_99600" class="panel-body mtree-wrap" >
			</div>
		</td>
		<td style="vertical-align: top;">
			<section>
			<div class="searchbox">
				<h4 id="selectedCodeName_99600" >최상위코드</h4> 
				<div class="btn_gup">
					<button class="skyblue auth-insert" id="tempInsert99600" >메뉴추가</button>
				</div>
			</div>
			</section>
			<table summary="메뉴리스트" class="tbl_col" id="table99600">
				<caption>메뉴목록</caption>
				<colgroup>
					<col width="5px" >
					<col width="25px" >
					<col width="25px" >
					<col width="10px" >
					<col width="10px" >
					<col width="20px" >
				</colgroup>
				<thead>
					<tr>    			
						<th scope="col">No.</th>
						<th scope="col">메뉴명</th>
						<th scope="col">URL</th>
						<th scope="col">순서</th>
						<th scope="col">사용여부</th>
						<th scope="col">수정</th>
					</tr>
				</thead>
				<tbody id="tbody99600">
				</tbody>
			</table>
		</td>
	</tr>
	</tbody>
</table>
<!-- 레이어 팝업 시작 -->
<div id="menuPopSpace" >
	<div id="tempPop99600" style="display:none  ;">
		<div class="popup"  style="width: 600px;" >
				<div class="phead">
					<span id="">메뉴관리</span>
					<a id="tempCloseX99600" class="phead-closex" >닫기</a>
				</div>
				<div class="con">
					<div class="tbl_title" >
						<span>메뉴관리</span>
					</div>
					<form id="frm99600">
					<input type="hidden" name="flag" id="save_type_99600"/>
					<input type="hidden" name="m_depth" id="m_depth_99600"/>
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
								<input id="m_no_99600" name="m_no" class="input-basic" type="text" value="" maxlength="5" placeholder="코드 자동 채번" readonly="readonly" >
							</td>
						</tr>
						<tr>
							<th>메뉴명</th>
							<td>
								<input id="m_nm_99600" name="m_nm" type="text" maxlength="100" placeholder="" value="" >
							</td>
						</tr>
						<tr>
							<th>URL</th>
							<td>
								<input id="m_url_99600" name="m_url" type="text" maxlength="100" value="" >
							</td>
						</tr>
						<tr>
							<th>부모메뉴</th>
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
								
								<!-- <input id="m_parent_no_99600" name="m_parent_no" type="hidden" value="">
								<input id="m_parent_name_99600" name="m_parent_name" type="text" value="" readonly="readonly" > -->
								
								<select id="m_parent_no_D1_99600" name ="m_parent_no_D1_99600" class="select-basic" style="margin-right: 6px;">
								</select> 
								<select id="m_parent_no_D2_99600" name ="m_parent_no_D2_99600" class="select-basic"style="margin-right: 6px;">
								</select> 
								<input id="m_parent_no_99600" name=m_parent_no type="hidden" value=""/>
							</td>
						</tr>
						<tr>
							<th>순서</th>
							<td>
								<input id="m_order_99600" name="m_order"  class="input-basic" type="text" maxlength="10"  value=""  >
							</td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td>
								<div class="inputRadio">
										<input type="radio" name = "m_use_yn" id="pop_m_use_y_99600" value="Y" checked='checked'>
										<label for="pop_m_use_y_99600"></label>
								</div>사용	
								
								<div class="inputRadio">
										<input type="radio" name = "m_use_yn" id="pop_m_use_n_99600" value="N">
										<label for="pop_m_use_n_99600"></label>
								</div>미사용	
							</td>
						</tr>
						<tr>
							<th>메모</th>
							<td>
								<textarea id="m_note_99600" name="m_note" cols="60" rows="6" maxlength="500">
								</textarea>
							</td>
						</tr>
					</table>
					</form>
					<div> </div>
					<div class="pfooter">
		       			<button type='button' class="red auth-insert auth-update" id="tempSave99600">저장</button>
		       			<button type='button' class="white" id="tempClose99600">닫기</button>
					</div>
				</div>
			</div>
			<div class="popup_overlay"></div>
		</div>
		
</div>
<!-- 레이어 팝업 종료 -->

</article>		
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
