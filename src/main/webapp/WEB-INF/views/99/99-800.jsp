<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-200.js"></script>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="activityFixingPop210200" style="display: none ;">
		<div class="popup" style="width: 1100px;">
			<div class="phead">
				<span id="">업무일지</span>
				<a id="activityFixingPopCloseX10200" class="phead-closex">닫기</a>
			</div>
			<div class="diary">
						<h2>이마트 성수점</h2>
						<div class="dateweekbox">
							<ul class="dateweek">
								<li>
									<a href="#"> <span><i class="fa fa-angle-left"></i></span> </a>
								</li>
								<li>
									<a href="#" class="cell_active"><b>2015.01.22 ~ 2015.01.28</b></a>
								</li>
								<li>
									<a href="#"> <span><i class="fa fa-angle-right"></i></span> </a>
								</li>
							</ul>
						</div>
						<div class="charger_name">
							담당사원:김민정
						</div>
					</div>
					<div class="con">
						<div id="slideView" class="pop_accordion">
							<ul>
								<li >
									<a class="toogle-slide" data-target="exISLANDStatus" ><span><i class="fa fa-plus-square fa-lg"></i></span>행사매대현황</a>
									<a class="options" id="evnOptionOpenBtn" ><span><i class="fa fa-cog fa-lg"></i></span></a>
								</li>
								<li id="exISLANDStatus" class="aco-con" style="display: none;" >
									<div class="tbl_title">
										<span>1. 엔드/아일랜드 현황 </span>
									</div>
									<table class="tbl_row">
										<colgroup>
											<col style="width:14%">
											<col style="width:86%">
										</colgroup>
										<tbody>
											<tr>
												<th>주동선엔드</th>
												<td>
													<img src="/resources/images/thum_default.png" height="80px" >
												</td>
											</tr>
											<tr>
												<th>후동선엔드</th>
												<td>
													<img src="/resources/images/thum_default.png" height="80px" >
												</td>
											</tr>
											<tr>
												<th>아일랜드</th>
												<td>
													<img src="/resources/images/thum_default.png" height="80px" >
												</td>
											</tr>
										</tbody>
									</table>
									<div class="tbl_title">
										<span>2. 보조진열 </span>
									</div>
									<table class="tbl_row">
										<colgroup>
											<col style="width:14%">
											<col style="width:86%">
										</colgroup>
										<tbody>
											<tr>
												<th>평매대</th>
												<td>
													<img src="/resources/images/thum_default.png" height="80px" >
												</td>
											</tr>
											<tr>
												<th>기타</th>
												<td>
													<img src="/resources/images/thum_default.png" height="80px" >
												</td>
											</tr>
										</tbody>
									</table>
									<div class="tbl_title">
										<span>3. 추가매대</span>
									</div>
									<table class="tbl_row">
										<colgroup>
											<col style="width:14%">
											<col style="width:86%">
										</colgroup>
										<tbody>
											<tr>
												<th>기타매대</th>
												<td>
													<img src="/resources/images/thum_default.png" height="80px" >
												</td>
											</tr>
										</tbody>
									</table>
								</li>
								<li>
									<a class="toogle-slide" data-target="exPOGStatus" >
									<span><i class="fa fa-plus-square fa-lg"></i></span>
									POG 및 현재 진열 줄수
									</a>
									<a class="options" id="pogOptionOpenBtn" title="POG 및 현재 진열 줄수 옵션" ><span><i class="fa fa-cog fa-lg"></i></span></a>
								</li>
								<li  id="exPOGStatus"  class="aco-con" style="display: none;"  >
									<div class="tbl_title">
										<span>POG 최종 업데이트 :  2015-04-21 14:15</span>
									</div>
									<table class="tbl_col">
										<colgroup>
											<col width="10%" >
											<col span="10" width="6%" >
											<col span="2" width="7%" >
											<col width="16%" >
											
										</colgroup>
										<tr>
											<th rowspan="2" >구분</th>
											<th colspan="2" >DSF</th>
											<th colspan="2" >NY(KEL)</th>
											<th colspan="2" >LN</th>
											<th colspan="2" >AP</th>
											<th colspan="2" >PB 및 기타경쟁사</th>
											<th rowspan="2" >POG<br>진영률</th>
											<th rowspan="2" >실제<br>진영률</th>
											<th rowspan="2" >특이사항</th>
										</tr>
										<tr>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
										</tr>
										<tbody>
											<tr>
												<th>I/C</th>
												<td>42</td>
												<td>15</td>
												<td>12</td>
												<td>11</td>
												<td>2</td>
												<td>22</td>
												<td>17</td>
												<td>32</td>
												<td>12</td>
												<td>92</td>
												<td>53.8%</td>
												<td>53.8%</td>
												<td>없음</td>
											</tr>
										</tbody>
									</table>
								</li>
								<li>
									<a class="toogle-slide" data-target="exBIGStatus" ><span><i class="fa fa-plus-square fa-lg"></i></span>보조진열현황</a>
									<a class="options" id="bigOptionOpenBtn" title="매대진열현황 옵션" ><span><i class="fa fa-cog fa-lg"></i></span></a>
								</li>
								<li id="exBIGStatus" class="aco-con" style="display: none;" >
									<div class="tbl_title">
										<span>POG 최종 업데이트 :  2015-04-21 14:15</span>
									</div>
									<table class="tbl_col">
										<colgroup>
											<col width="10%" >
											<col span="10" width="6%" >
											<col span="2" width="7%" >
											<col width="16%" >
											
										</colgroup>
										<tr>
											<th rowspan="2" >구분</th>
											<th colspan="2" >DSF</th>
											<th colspan="2" >NY(KEL)</th>
											<th colspan="2" >LN</th>
											<th colspan="2" >AP</th>
											<th colspan="2" >PB 및 기타경쟁사</th>
											<th rowspan="2" >POG<br>진영률</th>
											<th rowspan="2" >실제<br>진영률</th>
											<th rowspan="2" >특이사항</th>
										</tr>
										<tr>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
										</tr>
										<tbody>
											<tr>
												<th>I/C</th>
												<td>42</td>
												<td>15</td>
												<td>12</td>
												<td>11</td>
												<td>2</td>
												<td>22</td>
												<td>17</td>
												<td>32</td>
												<td>12</td>
												<td>92</td>
												<td>53.8%</td>
												<td>53.8%</td>
												<td>없음</td>
											</tr>
										</tbody>
									</table>
								</li>
								<li>
									<a class="toogle-slide" data-target="exnPDStatus" ><span><i class="fa fa-plus-square fa-lg"></i></span>PD 매대현황</a>
									<a class="options"  ><span><i class="fa fa-cog fa-lg"></i></span></a>
								</li>
								<li id="exnPDStatus" class="aco-con" style="display: none ;" >
									<div class="tbl_title">
										<span>PD 최종 업데이트 :  2015-04-21 14:15</span>
									</div>
									<table class="tbl_col">
										<colgroup>
											<col width="10%" >
											<col span="10" width="6%" >
											<col span="2" width="7%" >
											<col width="16%" >
											
										</colgroup>
										<tr>
											<th rowspan="2" >구분</th>
											<th colspan="2" >DSF</th>
											<th colspan="2" >NY(KEL)</th>
											<th colspan="2" >LN</th>
											<th colspan="2" >AP</th>
											<th colspan="2" >PB 및 기타경쟁사</th>
											<th rowspan="2" >POG<br>진영률</th>
											<th rowspan="2" >실제<br>진영률</th>
											<th rowspan="2" >특이사항</th>
										</tr>
										<tr>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
										</tr>
										<tbody>
											<tr>
												<th>I/C</th>
												<td>42</td>
												<td>15</td>
												<td>12</td>
												<td>11</td>
												<td>2</td>
												<td>22</td>
												<td>17</td>
												<td>32</td>
												<td>12</td>
												<td>92</td>
												<td>53.8%</td>
												<td>53.8%</td>
												<td>없음</td>
											</tr>
										</tbody>
									</table>
								</li>
								<li>
									<a class="toogle-slide" data-target="exITEMStatus" ><span><i class="fa fa-plus-square fa-lg"></i></span>취급품목현황</a>
									<a class="options" ><span><i class="fa fa-cog fa-lg"></i></span></a>
								</li>
								<li  id="exITEMStatus" class="aco-con" style="display: none;"  >
									<div>
										<div class="tbl_title">
											<span>취급품목 현황 최종 업데이트 :  2015-04-21 14:15</span>
										</div>
										<table class="tbl_col">
											<colgroup>
												<col width="10%" >
												<col span="10" width="6%" >
												<col span="2" width="7%" >
												<col width="16%" >
												
											</colgroup>
											<tr>
												<th rowspan="2" >구분</th>
												<th colspan="2" >DSF</th>
												<th colspan="2" >NY(KEL)</th>
												<th colspan="2" >LN</th>
												<th colspan="2" >AP</th>
												<th colspan="2" >PB 및 기타경쟁사</th>
												<th rowspan="2" >POG<br>진영률</th>
												<th rowspan="2" >실제<br>진영률</th>
												<th rowspan="2" >특이사항</th>
											</tr>
											<tr>
												<th>POG</th>
												<th>현재</th>
												<th>POG</th>
												<th>현재</th>
												<th>POG</th>
												<th>현재</th>
												<th>POG</th>
												<th>현재</th>
												<th>POG</th>
												<th>현재</th>
											</tr>
											<tbody>
												<tr>
													<th>I/C</th>
													<td>42</td>
													<td>15</td>
													<td>12</td>
													<td>11</td>
													<td>2</td>
													<td>22</td>
													<td>17</td>
													<td>32</td>
													<td>12</td>
													<td>92</td>
													<td>53.8%</td>
													<td>53.8%</td>
													<td>없음</td>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
								<li>
									<a class="toogle-slide" data-target="exETCStatus" ><span><i class="fa fa-plus-square fa-lg"></i></span>시황 및 매장 특이사항</a>
									<a class="options" ><span><i class="fa fa-cog fa-lg"></i></span></a>
								</li>
								<li id="exETCStatus" class="aco-con" style="display: none;" >
									<div class="tbl_title">
										<span>시황및 매장특이사항 최종 업데이트 :  2015-04-21 14:15</span>
									</div>
									<table class="tbl_col">
										<colgroup>
											<col width="10%" >
											<col span="10" width="6%" >
											<col span="2" width="7%" >
											<col width="16%" >
											
										</colgroup>
										<tr>
											<th rowspan="2" >구분</th>
											<th colspan="2" >DSF</th>
											<th colspan="2" >NY(KEL)</th>
											<th colspan="2" >LN</th>
											<th colspan="2" >AP</th>
											<th colspan="2" >PB 및 기타경쟁사</th>
											<th rowspan="2" >POG<br>진영률</th>
											<th rowspan="2" >실제<br>진영률</th>
											<th rowspan="2" >특이사항</th>
										</tr>
										<tr>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
											<th>POG</th>
											<th>현재</th>
										</tr>
										<tbody>
											<tr>
												<th>I/C</th>
												<td>42</td>
												<td>15</td>
												<td>12</td>
												<td>11</td>
												<td>2</td>
												<td>22</td>
												<td>17</td>
												<td>32</td>
												<td>12</td>
												<td>92</td>
												<td>53.8%</td>
												<td>53.8%</td>
												<td>없음</td>
											</tr>
										</tbody>
									</table>
								</li>
								<li>
									<a class="toogle-slide" data-target="exWORKStatus" ><span><i class="fa fa-plus-square fa-lg"></i></span>근무계획</a>
									<a class="options" ><span><i class="fa fa-cog fa-lg"></i></span></a>
								</li>
								<li id="exWORKStatus" class="aco-con" style="display: none;" >
									<div class="tbl_title">
										<span>근무계획 최종 업데이트 :  2015-04-21 14:15</span>
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
												<td colspan="2">서부지점</td>
												<td rowspan="5" class="txt_center"><img src="/resources/images/thum_profile.png" /></td>
											</tr>
											<tr>
												<th>이름</th>
												<td colspan="2">전지현</td>
											</tr>
											<tr>
												<th>아이디</th>
												<td colspan="2">025019</td>
											</tr>
											<tr>
												<th>패스워드</th>
												<td colspan="2">
												<button class="white">
													패스워드 확인
												</button></td>
											</tr>
											<tr>
												<th>입사일</th>
												<td colspan="2">2012-04-01</td>
											</tr>
											<tr>
												<th>직책</th>
												<td>사원</td>
												<th>직무</th>
												<td>고정MD</td>
											</tr>
											<tr>
												<th>생년월일</th>
												<td>1981-08-07L</td>
												<th>성별</th>
												<td>여</td>
											</tr>
											<tr>
												<th>휴대전화</th>
												<td>010-1234-1234</td>
												<th>결혼기념일</th>
												<td>2010-06-02</td>
											</tr>
											<tr>
												<th>주소</th>
												<td colspan="3">우)123-123
												<br>
												서울시 강남구 논현로 우리집</td>
											</tr>
										</tbody>
									</table>
								</li>
							</ul>
						</div>

					</div>
			<div class="pfooter">
				<button type="button" class="red auth-insert auth-update" id="">
					저장
				</button>
				<button type="button" class="white" id="activityFixingPopClose10200">
					닫기
				</button>
			</div>
			
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어팝업 위에 팝업 행사매대 -->
	<div id="activityFixingEVNOptionPop210200" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">행사매대 설정</span>
				<a id="pogOptionCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_tab event">
					<ul>
						<li class="tab_on" data-group="pog" data-target="ent" ><a>비교업체 설정</a></li>
						<li  data-group="pog" data-target="prd"  ><a>품목 설정</a></li>
					</ul>
				</div>
				<div class="pog-group pog-ent-group" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:35%">
							<col style="width:15%">
							<col style="width:15%">
							<col style="width:15%">
						</colgroup>
						<tr>
							<th>업체약칭</th>
							<th>업체명</th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
						</tr>
						<tbody id="pogOptionEntTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray" id="entAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="entSaveRow10200">저장</button>
					</div>
				</div>
				<div class="pog-group pog-prd-group hide" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:35%">
							<col style="width:15%">
							<col style="width:15%">
							<col style="width:15%">
						</colgroup>
						<tr>
							<th>항목명</th>
							<th>정의</th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
						</tr>
						<tbody id="pogOptionPrdTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray" id="prdAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="prdSaveRow10200">
							저장
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<!-- 레이어팝업 위에 팝업 -->
	<div id="activityFixingPOGOptionPop210200" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">POG 및 현재 진열 줄수 설정</span>
				<a id="pogOptionCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_tab event">
					<ul>
						<li class="tab_on" data-group="pog" data-target="ent" ><a>비교업체 설정</a></li>
						<li  data-group="pog" data-target="prd"  ><a>품목 설정</a></li>
					</ul>
				</div>
				<div class="pog-group pog-ent-group" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:35%">
							<col style="width:15%">
							<col style="width:15%">
							<col style="width:15%">
						</colgroup>
						<tr>
							<th>업체약칭</th>
							<th>업체명</th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
						</tr>
						<tbody id="pogOptionEntTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray" id="entAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="entSaveRow10200">저장</button>
					</div>
				</div>
				<div class="pog-group pog-prd-group hide" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:35%">
							<col style="width:15%">
							<col style="width:15%">
							<col style="width:15%">
						</colgroup>
						<tr>
							<th>항목명</th>
							<th>정의</th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
						</tr>
						<tbody id="pogOptionPrdTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray" id="prdAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="prdSaveRow10200">
							저장
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<!-- 레이어팝업 위에 팝업 -->
	<div id="activityFixingBIGOptionPop210200" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">보조진열 설정</span>
				<a id="bigOptionCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_tab event">
					<ul>
						<li class="tab_on" data-group="big" data-target="ent" ><a>매대 설정</a></li>
						<li  data-group="big" data-target="prd"  ><a>품목 설정</a></li>
					</ul>
				</div>
				<div class="big-group big-ent-group" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:35%">
							<col style="width:15%">
							<col style="width:15%">
							<col style="width:15%">
						</colgroup>
						<tr>
							<th>업체약칭</th>
							<th>업체명</th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
						</tr>
						<tbody id="bigOptionEntTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray" id="bigEntAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="bigEntSaveRow10200">저장</button>
					</div>
				</div>
				<div class="big-group big-prd-group hide" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:35%">
							<col style="width:15%">
							<col style="width:15%">
							<col style="width:15%">
						</colgroup>
						<tr>
							<th>항목명</th>
							<th>정의</th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
						</tr>
						<tbody id="bigOptionPrdTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray" id="bigPrdAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="bigPrdSaveRow10200">
							저장
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>고정MD</h1>
			<p>
				<i class="fa fa-home"></i>
				<span class="clamp fa"></span>활동관리
				<span class="clamp fa"></span>고정MD
			</p>
		</div>
	</section>
	<section>

		<div class="searchbox">
							<div class="input_group clear">
								<span> <strong>항목</strong>
									<input class="input-basic" id="input-basic" type="text" placeholder="기본인풋박스" />
								</span>
								<span>
									<input class="input-icon" id="datatime" type="text" placeholder="날짜선택" />
									<label for="datatime"><i class="fa fa-calendar"></i></label> </span>
								<span>
									<input class="input-icon" id="id-user" type="text" placeholder="아이디/이름" />
									<label for="id-user"><i class="fa fa-user"></i></label> </span>
								<span>
									<input class="input-icon" id="storename" type="text" placeholder="거래처/매장명" />
									<label for="storename"><i class="fa fa-home"></i></label> </span>
							</div>
							<div class="btn_gup clear">
								<button class="red">
									조회
								</button>
								<button class="gray">
									초기화
								</button>
								<button class="skyblue p_memo auth-insert" >
									등록
								</button>
							</div>
						</div>
	</section>
	<section>
		<div class="searchbox_sub">
			<h2>2015-08-02 / 서부지점 고정MD</h2>
			<button class="gray">
				총원
			</button>
			<span>50명</span>
			<button class="hotpink">
				출근
			</button>
			<span>50명</span>
			<button class="hotpink">
				미출근
			</button>
			<span>50명</span>
			<button class="skyblue">
				퇴근
			</button>
			<span>50명</span>
			<button class="skyblue">
				미퇴근
			</button>
			<span>50명</span>
			<button class="brown">
				휴가
			</button>
			<span>50명</span>
		</div>
	</section>
	<section class="divide">
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="profilebox">
			<div>
				<img src="/resources/images/thum_profile.png">
			</div>
			<button class="hotpink lft">
				10:24
			</button>
			<button class="skyblue rht">
				미퇴근
			</button>
			<div class="profile_con">
				<ul>
					<li class="p_name">
						김여사원
					</li>
					<li class="p_phone">
						010-1234-1234
					</li>
					<li class="p_memo">
						<a>순방일지</a>
					</li>
				</ul>
			</div>
		</div>
	</section>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>