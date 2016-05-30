<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<%-- <script type="text/javascript" src="<%=jsPath%>/routine/base/menu.js"></script> --%>
<script type="text/javascript">
		
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#temp2Pop").instancePopUp();
	$("#temp3Pop").instancePopUp();
	$("#searchToDate").winCal();	
	$("#searchFromDate").winCal();
	$("#temp2Open").click(function(){
		$("#temp2Pop").show();
	});
	$("#temp2Close, #temp2CloseX").click(function(){
		$("#temp2Pop").hide();
	});
	
	
	
	$('#addrSearchPopBtn').click(function(){
		$postOption.insertPostcode5 = "#pZip";
		$postOption.insertAddress = "#pAddr1";
		$postOption.insertDetails = "#pAddr2";
		$postOption.insertExtraInfo = "#pAddrExt";
		$("#temp3Pop").show();
	});
	
	$("#addrSearch").postcodify({
		controls : "#addrSearchControl" ,
		afterSelect : function(selectedEntry) { 
			$("#addrSearch").html("");
			$(".keyword").val("");
			$("#temp3Pop").hide();
		},
		hideOldAddresses : false
	});
	
	$('#zipcodePopCloseBtn').click(function(){
		$("#addrSearch").html("");
		$(".keyword").val("");
		$("#temp3Pop").hide();
	});
	
	
	
});

</script>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="temp3Pop" style="display: none ;">
	<div class="popup" style="width: 600px;">
					<div class="phead">
						<span id="">주소검색</span>
						<a id="zipcodePopCloseBtn" class="phead-closex">닫기</a>
					</div>

					<div class="con">
						<div class="input_group clear">
						<div  id="addrSearchControl"  ></div>
<!-- 								<span>  -->
<!-- 									<strong>주소검색</strong> -->
<!-- 									<input type="text" class="keyword" value="" id="postcodify_14441078345261391" placeholder="검색어는 3글자 이상 입력해 주십시오."> -->
<!-- 									<button type="button" class="red" id="postcodify_14441078345261391_button"> -->
<!-- 										검색 -->
<!-- 									</button> -->
<!-- 								</span> -->
						</div>
						<div class="">
						
<!-- 							<div id="addrSearch" class="postcodify_search_form postcode_search_form"> -->
							<div id="addrSearch" ></div>
<!-- 							</div> -->
						</div>
					</div>
				</div>
<!-- 		<div class="popup" style="width: 600px;"> -->
<!-- 			<div class="section"  >						 -->
<!-- 				<h1>주소검색<a id="zipcodePopCloseBtn" class="title-closex drag-not" >닫기</a></h1> -->
<!-- 				<div class="drag-not"> -->
<!-- 					<div class="comp_area zip-box" > -->
<!-- 						<div  id="addrSearchControl"  ></div> -->
<!-- 					</div> -->
<!-- 					<div class="fixdCol-warp green-check" > -->
<!-- 						<div id="addrSearch" ></div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="popup_overlay"></div>
	</div>
	<div id="temp2Pop" style="display: none ;">
		<div class="popup" style="width: 1100px;">
			<div class="phead">
				<span id="">업무일지</span>
				<a id="temp2CloseX" class="phead-closex">닫기</a>
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
						<div class="pop_accordion">
							<ul>
								<li class="aco-selectd">
									<a><span><i class="fa fa-minus-square fa-lg"></i></span>1</a>
								</li>
								<div class="aco-con">
									<div class="tbl_title">
										<span>1. 사원정보 </span>
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
												<div class="inputCheck">
													<input type="checkbox" value="None" id="inputCheck" name="check" checked />
													<label for="inputCheck"></label>
												</div>서부지점
												<form>
													<div class="inputRadio">
														<input type="radio" value="None" id="inputRadio1" name="radio" checked />
														<label for="inputRadio1"></label>
													</div>
													서부지점
													<div class="inputRadio">
														<input type="radio" value="None" id="inputRadio2" name="radio" />
														<label for="inputRadio2"></label>
													</div>
													서부지점
												</form></td>
												<td rowspan="5" class="txt_center"><img src="./images/thum_profile.png"></td>
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
												<td>1981-08-07</td>
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
												<br/>
												서울시 강남구 논현로 우리집</td>
											</tr>
										</tbody>
									</table>
									<div class="tbl_title second">
										<span>2. 인사기록 </span>
									</div>
									<table class="tbl_col">
										<colgroup>
											<col style="width:20%">
											<col style="width:20%">
											<col >
										</colgroup>
										<tbody>
											<tr>
												<th>발령일<a><i class="fa fa-sort"></i></a></th>
												<th>발령내용<a><i class="fa fa-sort-asc"></i></a></th>
												<th>특이사항<a><i class="fa fa-sort-desc"></i></a></th>
											</tr>
											<tr>
												<td> 2012-04-01 </td>
												<td class="txt_left">입사 </td>
												<td class="txt_left">
												<div class="input_group clear">
													<span>
														<input class="input-basic" disabled="disabled" value="Disneyland"/>
														<button class="disabled" disabled="disabled">
															Disneyland
														</button> </span>
													<span>
														<input class="input-basic"  value="Disneyland"/>
														<button class="white">
															Disneyland
														</button> </span>
												</div></td>
											</tr>
											<tr>
												<td> 2012-04-01 </td>
												<td class="txt_left">홈플러스 강서점 고정사원 발령 </td>
												<td class="txt_left">
												<div class="input_group clear">
													<span>
														<input type="text" disabled="disabled" value="Disneyland">
													</span>
													<span>
														<input class="input-basic"  value="Disneyland"/>
													</span>
												</div></td>
											</tr>
											<tr>
												<td> 2012-04-01 </td>
												<td class="txt_left">우수사원 표창 </td>
												<td class="txt_left">근태우수</td>
											</tr>
											<tr class="cell_active">
												<td> 2012-04-01 </td>
												<td class="txt_left">선택 tr class </td>
												<td class="txt_left">
												<div class="input_group clear">
													<span> <strong>지점선택</strong> </span>
													<span>
														<select class="select-basic">
															<option>선택</option>
															<option value="100">동부지점</option>
															<option value="200">서부지점</option>
															<option value="300">남부지점</option>
															<option value="400">북부지점</option>
														</select> </span>
												</div></td>
											</tr>
										</tbody>
									</table>
								</div>
								<li>
									<a><span><i class="fa fa-plus-square fa-lg"></i></span>2</a>
								</li>
								<li>
									<a><span><i class="fa fa-plus-square fa-lg"></i></span>3</a>
								</li>
							</ul>
						</div>

					</div>
			<div class="pfooter">
				<button type="button" class="red" id="">
					저장
				</button>
				<button type="button" class="white" id="temp2Close">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>임시 메인화면!!!!!!!!!!!!!!!!!!!</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>근태관리<span class="clamp fa"></span>순회MD<span class="clamp fa"></span>지점명
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
									<label for="id-user"><i class="fa fa-user"></i></i></label> </span>
								<span>
									<input class="input-icon" id="storename" type="text" placeholder="거래처/매장명" />
									<label for="storename"><i class="fa fa-home"></i></i></label> </span>
							</div>
							<div class="btn_gup clear">
								<button class="red">
									조회
								</button>
								<button class="gray">
									초기화
								</button>
								<button class="skyblue" id="addrSearchPopBtn" >
									주소검색
								</button>
								<button class="skyblue" id="temp2Open" >
									등록
								</button>
							</div>
						</div>
	</section>
	<section>
		<div class="searchbox_sub">
			<h2>2015-08-02 / 서부지점 순회MD</h2>
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
	</section>
</article>
</body>
</html>
