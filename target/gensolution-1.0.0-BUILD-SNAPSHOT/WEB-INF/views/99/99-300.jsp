<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<%
String login_cp_cd = (String) request.getSession().getAttribute("login_cp_cd");
%>

<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-300.js"></script>
</head>
<body>

<article class="content">
<input type="hidden" id="login_cp_cd_99300" name="login_cp_cd_99300" value="<%=login_cp_cd%>">
<section>
	<div class="page_nav">
		<h1>조직관리</h1>
		<p>
			<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>조직관리
		</p>
	</div>
</section>
<section>
<table style="width: 100%; vertical-align: top; " >
	<colgroup>
		<col style="width: 280px;" >
		<col>
	</colgroup>
	<tr>
		<td style=" vertical-align: top; ">
			<div id="baseList_99300" class="panel-body mtree-wrap" >
			</div>
		</td>
		<td style=" vertical-align: top; ">
			<section>
			<div class="searchbox">
				<h4 id="selectedCodeName_99300" >최상위코드</h4> 
				<div class="btn_gup">
					<button class="skyblue auth-insert" id="tempInsert99300" >조직추가</button>
				</div>
			</div>
			</section>
			
			<table summary="조직리스트" class="tbl_col">
				<caption>조직목록</caption>
				<colgroup>
					<col width="20%" >
					<col width="25%" >
					<col width="15%" >
					<col width="15%" >
					<col width="25%" >
				</colgroup>
				<thead>
					<tr>    			
						<th scope="col">조직코드</th>
						<th scope="col">조직명</th>
						<th scope="col">순서</th>
						<th scope="col">사용여부</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody id="tbody99300">
				</tbody>
			</table>
		</td>
	</tr>
</table>
</section>
<!-- 레이어 팝업 시작 -->
<div id="popSpace99300" >
	<div id="tempPop99300" class="pop-apn-pop"  style="display:none  ;">
		<div class="popup"  style="width: 600px;" >
				<div class="phead">
					<span id="">조직관리</span>
					<a id="tempCloseX99300" class="phead-closex" >닫기</a>
				</div>
				<div class="con">
					<div class="tbl_title" >
						<span>조직관리</span>
					</div>
					<form id="frm99300">
					<input type="hidden" name="flag" id="save_type_99300"/>
					<input type="hidden" name="om_orgnzt_se" id="om_orgnzt_se_99300" value=""/>
					<table class="tbl_row">
						<colgroup >
							<col width="15%">
							<col width="85%">
						<col>
						</colgroup>
						<tr>
							<th>조직코드</th>
							<td>
								<!-- <input id="m_no" name="m_no" type="text" value="" disabled="disabled" maxlength="5"  > -->
								<input id="om_code_99300" name="om_code" class="input-basic" type="text" value="" maxlength="5" placeholder="코드 자동 등록" readonly="readonly" >
							</td>
						</tr>
						<tr>
							<th>조직명</th>
							<td>
								<input id=om_nm_99300 name="om_nm" type="text" maxlength="100" placeholder="" value="" >
							</td>
						</tr>
						<tr id="tr_company_99300" style="display: none;">
							<th>소속회사</th>
							<td>
								<!-- 
								<select id="m_parent_no" name ="m_parent_no" style="margin-right: 6px;">
								</select> 
								-->
								<div id ="divCmCombo">
									<select id="cm_code_99300" name ="cm_code" style="margin-right: 6px;">
									</select> 
									<!-- <input type="hidden" name="cm_code" id="cm_code"/> -->
								</div>
							</td>
						</tr>
						<tr>
						<tr>
							<th>부모코드</th>
							<td>
								<select id="om_parent_no_D1_99300"  name ="om_parent_no_D1_99300" class="select-basic" style="margin-right: 6px;">
								</select> 
								&nbsp;
								<select id="om_parent_no_D2_99300" name ="om_parent_no_D2_99300" class="select-basic" style="margin-right: 6px;">
								</select> 
								<input id="om_parent_no_99300" name="om_parent_no" type="hidden" value=""/>
							</td>
						</tr>
						<tr>
							<th>순서</th>
							<td>
								<input id="om_sort_ordr_99300" name="om_sort_ordr" class="input-basic" type="text" maxlength="100"  value=""  >
							</td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td>
								<div class="inputRadio">
										<input type="radio" name = "use_at" id="pop_use_at_y_99300" value="Y" checked='checked'>
										<label for="pop_use_at_y_99300"></label>
								</div>사용
								<div class="inputRadio">
										<input type="radio" name = "use_at" id="pop_use_at_n_99300" value="N">
										<label for="pop_use_at_n_99300"></label>
								</div>미사용
							</td>
						</tr>
						<tr>
							<th>메모</th>
							<td>
								<textarea id="om_memo_99300" name="om_memo" cols="60" rows="6" maxlength="500">
								</textarea>
							</td>
						</tr>
						<tr>
							<th>주소</th>
							<td class="txt_left" colspan=3>
								<!-- <select id="om_area" name ="om_area" style="margin-right: 6px;">
								</select>  -->
								<input id="om_zipcd_99300" name="om_zipcd" class="input-basic" type="text" maxlength="5" value="" readonly="readonly" style="width: 100px;">
								<button type="button" id="addrSearchPopBtn_99300" class="white">주소검색</button>
								<input id="om_adres_99300" name="om_adres" class="table_row_input" type="text" maxlength="100" value="" readonly="readonly">
								<input id="om_etcadres_99300" name="om_etcadres" class="table_row_input" type="text" maxlength="100" value="" readonly="readonly">
								<input id="om_dtadres_99300" name="om_dtadres" class="table_row_input" type="text" maxlength="100" value="" >
							</td>
						</tr>
						<tr>
							<th>위치좌표</th>
							<td colspan=3>
								<span>위도</span><input id="om_la_99300" name="om_la" class ="input-basic" type="text" maxlength="30" value="" style="width: 145px;">
								&nbsp;
								<span>경도</span><input id="om_lo_99300" name="om_lo" class ="input-basic" type="text" maxlength="30" value="" style="width: 145px;">
								<button type="button" id="getCoordinateBtn_99300" class="white" style="margin-left: 15px;">위치좌표</button>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<div class="pfooter">
	       			<button type='button' class="red auth-update" id="tempSave99300">저장</button>
	       			<button type='button' class="white" id="tempClose99300">닫기</button>
				</div>
			</div>
		</div>
		
</div>
<!-- 레이어 팝업 종료 -->
<div id="zipcdPop_99300" class="pop-apn-pop" style="display: none;"> 
			<div class="popup"   style="width: 600px; z-index: 1002" >
				<div class="phead">
					<span>주소검색</span>
					<a id="zipcodePopCloseBtn_99300" class="phead-closex" >닫기</a>
				</div>
				<div class="con">						
					<div class="drag-not">
						<div class="comp_area zip-box" >
							<div  id="addrSearchControl_99300"  ></div>
						</div>
						<div class="fixdCol-warp green-check" >
							<div id="addrSearch_99300" ></div>
						</div>
					</div>
				</div>
			</div>
			<div class="popup_overlay"></div>
		</div>

</article>		
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
