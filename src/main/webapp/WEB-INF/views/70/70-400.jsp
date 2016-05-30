<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<%
String login_cp_cd = (String) request.getSession().getAttribute("login_cp_cd");
%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/70/70-400.js"></script>
</head>
<body>
<article class="content">
<input type="hidden" id="login_cp_cd_70400" name="login_cp_cd_99300" value="<%=login_cp_cd%>">
	<!-- 레이어 팝업 시작 -->
	<div id="noticeSavePop70400" style="display:  none ;">
		<input type="hidden" id="noticeSaveType70400" value="I" >
		<input type="hidden" id="noticeNmInnb70400" value="" >
		<input type="hidden" id="noticeMessageOmCode70400" value="" >
		<input type="hidden" id="noticeMessageCmCode70400" value="" >
		<input type="hidden" id="noticeMessageEmCode70400" value="" >
		<input type="hidden" id="noticeMessageAuthType70400" value="" >
		<div class="popup" style="width: 800px;">
			<div class="phead">
				<span id="noticeTitle70400">알림 메시지 전송</span>
				<a id="noticeSavePopCloseX70400" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<input type="hidden" name="om_orgnzt_se" id="om_orgnzt_se_70400" value=""/>
				<table cellspacing="0" border="1" summary="알림 메시지 전송" class="tbl_row">
					<caption>
						알림 메시지 전송
					</caption>
					<colgroup>
						<col style="width:15%;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>수신자</th>
							<td>
								<div class="input_group margintop clear">
									<span> 
										<strong>수신지점</strong>
										<select id="noticeOmCode70400" class="select-basic"></select> 
									</span>
									<span> 
										<strong>수신매장</strong>
										<select id="noticeSmCode70400" class="select-basic">
											<option value="">팀명</option>
										</select> 
									</span>
									<input id="om_parent_no_70400" name="om_parent_no_70400" type="hidden" value=""/>
									<input id="noticeCmCode70400" type="hidden" value=""/>
								</div>
								
								<div class="select_wrap sw_intable clear">
									<select class="withbtn" id="selectEmpList" multiple="true" size="9"></select>
									
									<div class="btn_way">
										<button class="btn" id="empP">
											<i class="fa fa-caret-right fa-2x"></i>
										</button>
										<button class="btn" id="empAP">
											<i class="fa fa-step-forward fa-lg"></i>
										</button>
										<button class="btn" id="empM">
											<i class="fa fa-caret-left fa-2x"></i>
										</button>
										<button class="btn" id="empAM">
											<i class="fa fa-step-backward fa-lg"></i>
										</button>
									</div>
									<select class="withbtn_bottom" id="selectEmpGL" multiple="true" size="9"></select>
								</div>
							</td>
						</tr>
						<tr>
							<th>메시지</th>
							<td>
								<textarea id="noticeMessageArea70400" name="" cols="60" rows="6" maxlength="500"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="pfooter">
				<button type="button"  class="red auth-insert auth-update" id="noticeSave70400">
					저장
				</button>
				<button type="button" class="white" id="noticeSavePopClose70400">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<div id="noticeViewPop70400" style="display: none  ;">
		<div class="popup" style="width: 600px;">
			<div class="phead">
				<span id="noticeTitle70400">알림메시지 수신자</span>
				<a id="noticeViewPopCloseX70400" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_row">
					<colgroup>
						<col width="30%"  >
						<col width="50%"  >
						<col width="20%"  >
					</colgroup>
					<thead>
						<tr>
							<th scope="col">지점</th>
							<th scope="col">근무유형</th>
							<th scope="col">이름</th>
						</tr>
					</thead>
					<tbody id="noticeViewPopTbody70400">
					</tbody>
				</table>
			</div>
			<div class="pfooter">
				<button type="button" class="white" id="noticeViewPopClose70400">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>알림 메시지</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>커뮤니케이션<span class="clamp fa"></span>알림 메시지
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="jijumOmCode70400" class="select-basic"></select>
					</span>
					<span>
						<input class="input-icon" id="jijumWord70400" type="text" placeholder="거래처/매장명">
						<label for="noticeWord2"><i class="fa fa-home"></i></label> 
						<input id="jijumWord70400Hidden" type="hidden" >
					</span>
				</div>
				<div class="input_group clear">
					<span> <strong>수신자</strong></span>
					<span>
						<input class="input-basic" id="receiver70400" type="text" >
					</span>
				</div>
				<div class="input_group clear">
					<span> 
					<select id="noticeKey70400">
						<!-- <option value="sj" >제목</option> -->
						<option value="cn" >내용</option>
					</select>  
				</span>
					<span>
						<input class="input-basic" id="noticeWord70400" type="text" placeholder="검색어">					 
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="noticeSearchBtn70400" >조회</button>
				<button class="gray" id="noticeSearchResat70400" >초기화</button>
				<button class="skyblue auth-insert auth-update" id="noticeOpen70400" >발신</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="공지사항 리스트" class="tbl_col">
			<caption>
				알림메시지목록
			</caption>
			<colgroup>
				<col width="5%"  >
				<col width="50%"  >
				<col width="7%"  >
				<col width="13%"  >
				<col width="10%"  >
				<col width="15%"  >
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">내용</th>
					<th scope="col">수신</th>
					<th scope="col">발신자</th>
					<th scope="col">수신자</th>
					<th scope="col">발신일</th>
				</tr>
			</thead>
			<tbody id="noticeTbody70400">
			</tbody>
		</table>
		<div id="noticeNavi70400"  class="paginate"> 
		</div>
	<!-- //UI Object -->
	</section>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
