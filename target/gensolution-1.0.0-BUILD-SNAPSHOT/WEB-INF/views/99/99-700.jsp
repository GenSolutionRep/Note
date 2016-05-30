<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-700.js"></script>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>권한관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>권한관리
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">
				<span> <strong>메뉴권한그룹</strong>
					<select class="select-basic" id="menuAuthGroup_99700"  name ="menuAuthGroup_99700" >
					</select>
				</span>
			</div>
			<div class="btn_gup clear">
				<button class="gray" id="authClear99700" style="display: none;">
					그룹초기화
				</button>
				<button class="gray auth-insert" id="authGroupInsert99700" class="auth-insert">
					그룹등록
				</button>
				<button class="gray auth-update" id="authGroupUpdate99700" class="auth-update">
					그룹수정
				</button>
				<button class="gray auth-update" id="menuAuthSave99700" class="auth-insert auth-update">
					메뉴권한 그룹저장
				</button>
				<button class="red auth-etc" id="authSearch99700" style="display: none;">
					조회
				</button>
			</div>
		</div>
	</section>
	<section>
		<div class="searchbox_sub">
			<div class="input_group clear">
				<span> <strong>지점</strong>
					<select class="select-basic" id="om_99700"  name ="om_99700" >
					</select> </span>
				<span>
					<input class="input-icon" id="em_nm_99700" name="em_nm_99700" type="text" placeholder="사원명">
					<label for="em_nm_99700"><i class="fa fa-user"></i></label> </span>
			</div>
			<button type="button" class="red" id="searchEmpBtn99700">
				조회
			</button>
		</div>
	</section>
	<section>
		<!-- 수정 clearFix 클래스추가 -->
		<div class="select_wrap clear">
			<select class="withbtn" id="selectEmpListL" multiple="multiple" size="9"></select>
			<div class="btn_way">
				<button class="btn" id="empSelectMove">
					<i class="fa fa-caret-right fa-2x"></i>
				</button>
				<button class="btn" id="empSelectAllMove">
					<i class="fa fa-step-forward fa-lg"></i>
				</button>
				<button class="btn" id="empSelectCancle">
					<i class="fa fa-caret-left fa-2x"></i>
				</button>
				<button class="btn" id="empSelectAllCancle">
					<i class="fa fa-step-backward fa-lg"></i>
				</button>
			</div>
			<select class="withbtn_bottom" id="selectEmpListR" multiple="multiple" size="9"></select>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="권한리스트" class="tbl_col">
			<caption>권한목록</caption>
				<colgroup >
					<col width="2%">
					<col width="2%">
					<col width="8%">
					<col span="5" width="*">
				</colgroup>
				<thead id="authHeader99700">
<!-- 					<tr style="height:45px;" > -->
<!-- 						<th colspan="3" scope="col">메뉴명</th> -->
<!-- 						<th scope="col"> -->
<!-- 						메뉴보기 -->
<!-- 						<div class="inputCheck"> -->
<!-- 							<input type="checkbox" id="authTypeVisible" data-atuh-type="visi" > -->
<!-- 							<label for="authTypeVisible"></label> -->
<!-- 						</div> -->
<!-- 						</th> -->
<!-- 						<th scope="col"> -->
<!-- 						등록 -->
<!-- 						<div class="inputCheck"> -->
<!-- 							<input type="checkbox" id="authTypeInsert" data-atuh-type="inst" > -->
<!-- 							<label for="authTypeInsert"></label> -->
<!-- 						</div> -->
<!-- 						</th> -->
<!-- 						<th scope="col"> -->
<!-- 						수정 -->
<!-- 						<div class="inputCheck"> -->
<!-- 							<input type="checkbox" id="authTypeUpdate" data-atuh-type="updt" > -->
<!-- 							<label for="authTypeUpdate"></label> -->
<!-- 						</div> -->
<!-- 						</th> -->
<!-- 						<th scope="col"> -->
<!-- 						삭제 -->
<!-- 						<div class="inputCheck"> -->
<!-- 							<input type="checkbox" id="authTypeDelete" data-atuh-type="del" > -->
<!-- 							<label for="authTypeDelete"></label> -->
<!-- 						</div> -->
<!-- 						</th> -->
<!-- 						<th scope="col"> -->
<!-- 						다운로드 -->
<!-- 						<div class="inputCheck"> -->
<!-- 							<input type="checkbox" id="authTypeDownload" data-atuh-type="down" > -->
<!-- 							<label for="authTypeDownload"></label> -->
<!-- 						</div> -->
<!-- 						</th> -->
<!-- 						<th scope="col"> -->
<!-- 						기타 -->
<!-- 						<div class="inputCheck"> -->
<!-- 							<input type="checkbox" id="authTypeEtc" data-atuh-type="etc" > -->
<!-- 							<label for="authTypeEtc"></label> -->
<!-- 						</div> -->
<!-- 						</th> -->
<!-- 					</tr> -->
					<tr>
						<th colspan="3" rowspan="2" scope="col" >메뉴명</th>
						<th scope="col">메뉴보기</th>
						<th scope="col">등록</th>
						<th scope="col">수정</th>
						<th scope="col">삭제</th>
						<th scope="col">다운로드</th>
						<th scope="col">기타</th>
					</tr>
					<tr class="unborder">
						<th scope="col">
						<div class="inputCheck">
							<input type="checkbox" id="authTypeVisible" data-atuh-type="visi" >
							<label for="authTypeVisible"></label>
						</div>
						</th>
						<th scope="col">
						<div class="inputCheck">
							<input type="checkbox" id="authTypeInsert" data-atuh-type="inst" >
							<label for="authTypeInsert"></label>
						</div>
						</th>
						<th scope="col">
						<div class="inputCheck">
							<input type="checkbox" id="authTypeUpdate" data-atuh-type="updt" >
							<label for="authTypeUpdate"></label>
						</div>
						</th>
						<th scope="col">
						<div class="inputCheck">
							<input type="checkbox" id="authTypeDelete" data-atuh-type="del" >
							<label for="authTypeDelete"></label>
						</div>
						</th>
						<th scope="col">
						<div class="inputCheck">
							<input type="checkbox" id="authTypeDownload" data-atuh-type="down" >
							<label for="authTypeDownload"></label>
						</div>
						</th>
						<th scope="col">
						<div class="inputCheck">
							<input type="checkbox" id="authTypeEtc" data-atuh-type="etc" >
							<label for="authTypeEtc"></label>
						</div>
						</th>
					</tr>
				</thead>
			<tbody id="tbody99700">
			</tbody>
		</table>
	<!-- //UI Object -->
	</section>

	<div id="authPopSpace" >
		<div id="authPop99700" style="display:none  ;">
			<div class="popup"  style="width: 600px;" >
			
					<div class="phead">
						<span id="title99700">메뉴권한그룹 등록</span>
						<a id="tempCloseX99700" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<div class="tbl_title" >
							<span>메뉴권한그룹</span>
						</div>
						<form id="frm99700">
						<table class="tbl_row">
							<colgroup >
								<col width="25%">
								<col width="75%">
							<col>
							</colgroup>
							<tr>
								<th>메뉴권한 그룹명</th>
								<td>
									<input type="hidden" name="flag" id="save_type_99700">
									<input type="hidden" id="ma_group_seq_99700" name="ma_group_seq" value="">
									<input id="ma_group_name_99700" name="ma_group_name" type="text" placeholder="" maxlength="50" value="" >
								</td>
							</tr>
							<tr>
								<th>메모</th>
								<td>
									<textarea id="ma_group_memo_99700" name="ma_group_memo" cols="60" rows="6" maxlength="250">
									</textarea>
								</td>
							</tr>
						</table>
						</form>
					</div>
					<div class="pfooter">
		       			<button type='button' class="red auth-insert auth-update" id="tempAuthGroupSave99700" class='auth-insert auth-update'>저장</button>
		       			<button type='button' class="white" id="tempAuthGroupClose99700">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
			</div>
	</div>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
