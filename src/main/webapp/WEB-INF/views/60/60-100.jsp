<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<%--
    파일명 : 60-100.jsp
    작성일 : 2015.10.01
    작성자 : 김광욱
    설명 : 사원관리 JSP
--%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush="true" />
<%@ include file="60-100js.jsp"%>
</head>
<body>
<article class="content">
	<input type="hidden" id="employeeSaveType${type}"/>
	<input type="hidden" id="employeeEmployeeNo${type}"/>
	<input type="hidden" id="employeeEmployeeAmNo${type}"/>
	<input type="hidden" id="hrHistorySaveType${type}"/>
	<!-- 레이어 팝업 시작 -->
	<!-- 사원정보 등록 -->
	<div id="employeeSavePop${type}" style="display:none;">
		<div class="popup" style="width: 1100px;">
			<div class="phead">
				<span>사원관리</span>
				<a id="employeeSaveCloseX${type}" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_title">
					<span id="employeeSaveSubTitle${type}">사원등록</span>
				</div>
				<table class="tbl_row">
					<colgroup>
						<col style="width:14%">
						<col style="width:36%">
						<col style="width:14%">
						<col style="width:36%">
					</colgroup>
					<tbody>
						<tr>
							<th>지점</th>
							<td colspan="2">
								<div class="input_group clear">
									<span><select id="bhf_nm${type}s" class="select-basic"/></span>
									<span><select id="team_nm${type}s" class="select-basic"/></span>
								</div>
							</td>
							<td rowspan="3" class="txt_center" id="em_image${type}s"><img id="em_image_url${type}s" src="/resources/images/thum_profile.png"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td colspan="2">
								<div class="input_group clear">
									<span><input id="em_nm${type}s" type="text" class="input-basic" maxlength="10" placeholder=""/></span>
								</div>
							</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td colspan="2">
								<div class="input_group clear">
									<span><input id="em_id${type}s" type="text" class="input-basic" maxlength="10" placeholder=""/></span>
								</div>
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td colspan="2">
								<div class="input_group clear">
									<span><input id="em_password${type}s" type="password" class="input-basic" maxlength="15" placeholder=""/></span>
									<span id="em_password${type}sss">(비밀번호 확인)</span>
									<span><input id="em_password${type}ss" type="password" class="input-basic" maxlength="15" placeholder=""/></span>
								</div>
							</td>
							<td class="img-prev">
								<span><input type="file" id="em_image_preview${type}s"></span>
							</td>
						</tr>
						<tr>
							<th>입사일</th>
							<td>
								<div class="input_group clear">
									<span><input id="em_ecny_de${type}s" type="text" class="input-basic" readonly="readonly" placeholder="입사일"/></span>
								</div>
							</td>
							<th>퇴사일</th>
							<td>
								<div class="input_group clear" id="employeeStatusManage2${type}s" style="display:none;">
									<span><input id="em_retire_de${type}s" type="text" class="input-basic" readonly="readonly" placeholder="퇴사일"/></span>
								</div>
							</td>
						</tr>
						<tr>
							<th>직책</th>
							<td>
								<div class="input_group clear">
									<span><select id="em_rspofc_code${type}s" class="select-basic"/></span>
								</div>
							</td>
							<th>직무</th>
							<td>
								<div class="input_group clear">
									<span>
										<select id="em_dty_code${type}s" class="select-basic"/>
									</span>
									<span id="em_ampm_at${type}ss" style="display:none;">
										<div class="inputRadio">
											<input id="em_ampm_at${type}sA" name="em_ampm_at${type}s" type="radio" value="A" checked="checked">
											<label for="em_ampm_at${type}sA"></label>
										</div>오전
										<div class="inputRadio">
											<input id="em_ampm_at${type}sP1" name="em_ampm_at${type}s" type="radio" value="P1">
											<label for="em_ampm_at${type}sP1"></label>
										</div>오후1
										<div class="inputRadio">
											<input id="em_ampm_at${type}sP2" name="em_ampm_at${type}s" type="radio" value="P2">
											<label for="em_ampm_at${type}sP2"></label>
										</div>오후2
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td>
								<div class="input_group clear">
									<span><input id="em_brthdy${type}s" type="text" class="input-basic" readonly="readonly" placeholder="생년월일"/></span>
									<span><select id="em_cldr${type}s" class="select-basic" style="width:60px;"/></span>
								</div>
							</td>
							<th>성별</th>
							<td>
								<div class="inputRadio">
									<input id="em_sexdstn${type}sF" name="em_sexdstn${type}s" type="radio" value="F" checked="checked">
									<label for="em_sexdstn${type}sF"></label>
								</div>여
								<div class="inputRadio">
									<input id="em_sexdstn${type}sM" name="em_sexdstn${type}s" type="radio" value="M">
									<label for="em_sexdstn${type}sM"></label>
								</div>남
							</td>
						</tr>
						<tr>
							<th>휴대전화</th>
							<td>
								<div class="input_group clear">
									<span><input id="em_mbtl_num${type}s" type="text" class="input-basic" maxlength="13" placeholder=""/></span>
								</div>
							</td>
							<th>결혼기념일</th>
							<td>
								<div class="input_group clear">
									<span><input id="em_mrnry_de${type}s" type="text" class="input-basic" readonly="readonly" placeholder="결혼기념일"/></span>
								</div>
							</td>
						</tr>
						<tr>
							<th>기준교통비</th>
							<td>
								<div class="input_group clear">
									<span><input id="em_trans_fee${type}s" type="text" class="input-basic" maxlength="7" placeholder=""/></span>
								</div>
							</td>
							<th>개인연락처</th>
							<td>
								<div class="inputRadio">
									<input id="em_mbtl_open_at${type}sY" name="em_mbtl_open_at${type}s" type="radio" value="Y" checked="checked">
									<label for="em_mbtl_open_at${type}sY"></label>
								</div>공개
								<div class="inputRadio">
									<input id="em_mbtl_open_at${type}sN" name="em_mbtl_open_at${type}s" type="radio" value="N">
									<label for="em_mbtl_open_at${type}sN"></label>
								</div>비공개
							</td>
						</tr>
						
						<tr id="employeeStatusManage${type}s" style="display:none;">
							<th>재직여부</th>
							<td>
								<div class="inputRadio">
									<input id="use_at${type}sY" name="use_at${type}s" type="radio" value="Y" checked="checked">
									<label for="use_at${type}sY"></label>
								</div>재직
								<div class="inputRadio">
									<input id="use_at${type}sN" name="use_at${type}s" type="radio" value="N">
									<label for="use_at${type}sN"></label>
								</div>퇴사
							</td>
							<th>디바이스 인증</th>
							<td>
								<div class="inputRadio">
									<input id="em_certify_at${type}sN" name="em_certify_at${type}s" type="radio" value="N" checked="checked">
									<label for="em_certify_at${type}sN"></label>
								</div>비인증
								<div class="inputRadio">
									<input id="em_certify_at${type}sY" name="em_certify_at${type}s" type="radio" value="Y">
									<label for="em_certify_at${type}sY"></label>
								</div>인증
							</td>
						</tr>
						
						<tr>
							<th>주소</th>
							<td class="txt_left" colspan="3">
								<div class="input_group clear">
									<span><input id="em_zipcd${type}s" type="text" class="input-basic" style="margin-right:10px; width:100px;" maxlength="5" readonly="readonly" placeholder=""/></span>
									<span class="btn_gup clear"><button id="employeeSearchAddress${type}" type="button" class="gray">주소검색</button></span><br/>
									<input id="em_adres${type}s" type="text" class="table_row_input" maxlength="100" readonly="readonly" placeholder=""/>
									<input id="em_etcadres${type}s" type="text" class="table_row_input" maxlength="100" readonly="readonly" placeholder=""/>
									<input id="em_dtadres${type}s" type="text" class="table_row_input" maxlength="100" placeholder=""/>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="pfooter">
				<button id="employeeSaveAction${type}" type="button" class="red auth-update">저장</button>
				<button id="employeeSaveClose${type}" type="button" class="white">닫기</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 사원정보 상세 -->
	<div id="employeeViewPop${type}" style="display:none;">
		<div class="popup" style="width: 1100px;">
			<div class="phead">
				<span>사원관리</span>
				<a id="employeeViewCloseX${type}" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_tab">
					<ul id="employeeViewTab${type}">
						<li><a>사원정보</a></li>
						<li><a>인사기록</a></li>
						<li><a>근무매장</a></li>
					</ul>
				</div>
				<!-- 사원정보TAB -->
				<table id="employeeViewSubTableA${type}" class="tbl_row" style="display:none;">
					<colgroup>
						<col style="width:14%">
						<col style="width:36%">
						<col style="width:14%">
						<col style="width:36%">
					</colgroup>
					<tbody>
						<tr>
							<th>지점</th>
							<td id="bhf_nm${type}" colspan="2">지점</td>
							<td rowspan="4" class="txt_center" id="em_image${type}"> <img id="em_image_url${type}" src="/resources/images/thum_profile.png"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td id="em_nm${type}" colspan="2">이름</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td id="em_id${type}" colspan="2">아이디</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td colspan="2">
								<button id="employeePasswordOpen${type}" type="button" class="white">비밀번호 보기</button>
								<span id="em_password${type}" style="display:none;"></span>
								<button id="employeePasswordChange${type}" type="button" class="gray auth-update">변경</button>
								<span id="employeePasswordChange${type}r" class="input_group clear" style="display:none;">
									<input id="em_password${type}r" type="password" class="input-basic" maxlength="15" style="width:60px;" placeholder=""/>
									<span id="em_password${type}rrr">(비밀번호 확인)</span>
									<input id="em_password${type}rr" type="password" class="input-basic" maxlength="15" style="width:60px;" placeholder=""/>
									<button id="employeeSavePasswordAction${type}" type="button" class="red auth-update">전송</button>
								</span>
							</td>
						</tr>
						<tr>
							<th>입사일</th>
							<td id="em_ecny_de_nm${type}">입사일</td>
							<th>퇴사일</th>
							<td id="em_retire_de_nm${type}">퇴사일</td>
						</tr>
						<tr>
							<th>직책</th>
							<td id="em_rspofc_nm${type}">직책</td>
							<th>직무</th>
							<td id="em_dty_nm${type}">직무</td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td>
								<span id="em_brthdy_nm${type}">생년월일</span>
								<span id="em_cldr_nm${type}">달력</span>
							</td>
							<th>성별</th>
							<td id="em_sexdstn_nm${type}">성별</td>
						</tr>
						<tr>
							<th>휴대전화</th>
							<td id="em_mbtl_num${type}">휴대전화</td>
							<th>결혼기념일</th>
							<td id="em_mrnry_de_nm${type}">결혼기념일</td>
						</tr>
						<tr>
							<th>기준교통비</th>
							<td id="em_trans_fee${type}">기준교통비</td>
							<th>개인연락처</th>
							<td id="em_mbtl_open_at_nm${type}">개인연락처</td>
						</tr>
						<tr id="employeeStatusManage${type}" style="display:none;">
							<th>재직여부</th>
							<td id="use_at_nm${type}">재직여부</td>
							<th>디바이스 인증</th>
							<td id="em_certify_at_nm${type}">디바이스 인증</td>
						</tr>
						<tr>
							<th>주소</th>
							<td colspan="3">
								<span id="em_zipcd${type}">06107</span><br/>
								<span id="em_adres${type}">서울시 강남구 언주로 121길 13</span>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 인사기록TAB -->
				<table id="employeeViewSubTableB${type}" class="tbl_col" style="display:none;">
					<colgroup>
						<col style="width:20%">
						<col style="width:40%">
						<col style="width:40%">
					</colgroup>
					<tr>
						<th class='txt_center'>발령일</th>
						<th class='txt_center'>발령내용</th>
						<th class='txt_center'>특이사항</th>
					</tr>
					<tbody id="hrHistoryList${type}"/>
				</table>
				<!-- 근무매장TAB -->
				<table id="employeeViewSubTableC${type}" class="tbl_col" style="display:none;">
					<colgroup>
						<col style="width:25%">
						<col style="width:25%">
						<col style="width:25%">
						<col style="width:25%">
					</colgroup>
					<tr>
						<th class='txt_center'>고객그룹명</td>
						<th class='txt_center'>관리업체명</td>
						<th class='txt_center'>매장명</td>
						<th class='txt_center'>발령일자</td>
					</tr>
					<tbody id="workingStoreList${type}"/>
				</table>
			</div>
			<div class="pfooter">
				<span id="employeeSaveUpdPopOpen${type}s"><button id="employeeSaveUpdPopOpen${type}" type="button" class="red auth-update">수정</button></span>
				<span id="hrHistorySaveInsPopOpen${type}s"><button id="hrHistorySaveInsPopOpen${type}" type="button" class="red auth-insert">등록</button></span>
				<span><button id="employeeViewClose${type}" type="button" class="white">닫기</button></span>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 주소검색 -->
	<div id="employeeAddressPop${type}" class="pop-apn-pop" style="display:none;">
		<div class="popup" style="width:600px;">
			<div class="phead">
				<span>주소검색</span>
				<a id="employeeAddressCloseX${type}" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="drag-not">
					<div class="comp_area zip-box"><div id="employeeAddressSearchControl${type}"></div></div>
					<div class="fixdCol-warp green-check"><div id="employeeAddressSearch${type}"></div></div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 인사기록 등록 -->
	<div id="hrHistorySavePop${type}" class="pop-apn-pop" style="display:none;">
		<div class="popup" style="width: 1100px;">
			<div class="phead">
				<span>인사등록</span>
				<a id="hrHistorySaveCloseX${type}" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_row">
					<colgroup>
						<col style="width:20%">
						<col style="width:35%">
						<col style="width:35%">
						<col style="width:10%">
					</colgroup>
					<tr>
						<th class='txt_center'>발령일</th>
						<th class='txt_center'>발령내용</th>
						<th class='txt_center'>특이사항</th>
						<th class='txt_center'>삭제</th>
					</tr>
					<tbody id="hrHistoryList${type}s"/>
				</table>
			</div>
			<div class="pfooter">
				<button id="hrHistoryAddRow${type}" type="button" class="gray auth-insert">행추가</button>
				<button id="hrHistorySaveAction${type}" type="button" class="red auth-insert">저장</button>
				<button id="hrHistorySaveClose${type}" type="button" class="white">닫기</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1 id="mainTitle${type}">고정MD</h1>
			<p>
				<i class="fa fa-home"/><span class="clamp fa"></span>사원관리<span class="clamp fa"></span><span id="subTitle${type}">고정MD</span>
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">
				<span>
					<strong>지점명</strong><select id="employeeSearchBranch${type}" class="select-basic"/>
				</span>
				<span>
					<input id="employeeSearchName${type}" type="text" class="input-icon" maxlength="20" placeholder="이름">
					<label for="employeeSearchName${type}"><i class="fa fa-user"/></label>
				</span>
				<span>
					<input id="employeeSearchId${type}" type="text" class="input-icon" maxlength="20" placeholder="아이디">
					<label for="employeeSearchId${type}"><i class="fa fa-user"/></label>
				</span>
				<span> 
					<select id="employeeSearchKey${type}">
						<option value="em_ecny_de">입사일</option>
						<option value="em_brthdy">생일</option>
						<option value="em_mrnry_de">결혼기념일</option>
					</select>
				</span>
				<span><input id="employeeSearchValueFrom${type}" type="text" class="input-basic" readonly="readonly" placeholder="시작일"></span>
				~
				<span><input id="employeeSearchValueTo${type}" type="text" class="input-basic" readonly="readonly" placeholder="종료일"></span>
				<span>
					<span class="inputCheck">
						<input id="searchUseAt${type}" type="checkbox" style="margin-top:4px;">
						<label for="searchUseAt${type}" style="margin-top:4px;"></label>
					</span>
					<strong>퇴사자</strong>
				</span>
			</div>
			<div class="btn_gup clear">
				<button id="employeeSearch${type}" type="button" class="red">조회</button>
				<button id="employeeClearSearch${type}" type="button" class="gray">초기화</button>
				<button id="employeeExcelDown${type}" type="button" class="skyblue auth-down" ><i class="fa fa-file-excel-o fa-lg"/>엑셀</button>
				<button id="employeeSaveInsPopOpen${type}" type="button" class="brown auth-insert" >등록</button>
			</div>
		</div>
	</section>
	<section id="employeeList${type}"/>
	<section id="employeeNavi${type}" class="paginate"/>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
