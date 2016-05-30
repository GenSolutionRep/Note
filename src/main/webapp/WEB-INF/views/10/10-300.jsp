<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-300.js"></script>
<style type="text/css">
.screenshot10300 {
	position:absolute;
	border:1px solid #ccc;
	background:#797979;
	padding:5px;
 	display:none;
	color:#fff;
}
.tbl_row.center td ,.tbl_row.center th {
	text-align: center;
}
.trt-change-group {
 width: 90%;
}

.diary2 {
	    padding: 0 20px;
	    font-size: small;
	    background-color: #fff;
	    border-bottom: 1px solid #ddd;
	    height: 44px;
	    line-height: 44px;
	    clear: both;
	    text-align: left;
}
.diary2 .charger_name {float:left;margin-right:12px;}
.diary2 button {
	    float: right;
	    margin-top: 6px;
	    margin-right: 6px;
}
.diary2 button i{margin-right:6px;}
		
</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="activityRndPop210300" style="display: none ;">
		<input type="hidden" id="emNo10300" />
		<input type="hidden" id="dfwpInnb10300" />
		<div class="popup" style="width: 1100px;">
			<div class="phead">
				<span id="">업무일지</span>
				<a id="activityRndPopCloseX10300" class="phead-closex">닫기</a>
			</div>
				<div class="diary2">
					<div class="dateweekbox">
						<input type="hidden" id="selectRndBaseDate" value="" >
						<ul class="dateweek">
							<li>
								<a id="rndDiaryPrev" > <span><i class="fa fa-angle-left"></i></span> </a>
							</li>
							<li>
								<a href="#" class="cell_active"><b id="rndDiaryDate" >2015.01.22</b></a>
							</li>
							<li>
								<a id="rndDiaryNext" > <span><i class="fa fa-angle-right"></i></span> </a>
							</li>
						</ul>
					</div>
					<div class="charger_name" >
						담당사원 : <span id="rnd_name" >홍길동</span>
					</div>
					<button class="skyblue" id="rndExcelDownLoad10300" ><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
					<button type="button" class="red"  id="viewStoreMap" >
						<i class="fa fa-map-marker"></i>매장지도
					</button>
<!-- 					<button type="button" class="skyblue"> -->
<!-- 						<i class="fa fa-file-excel-o"></i>엑셀 다운로드 -->
<!-- 					</button> -->
				</div>
			<div class="con">
<!-- 				<div class="tbl_tab" id="rndTab10300"> -->
<!-- 					<ul> -->
<!-- 						<li class="tab_on" data-sm-code="01004" data-sm-nm="a학동점" data-sm-la="37.6074244" data-sm-lo="127.0153238"> -->
<!-- 							<a id=""><span><img src="images/report_work.png"></span>a학동점</a> -->
<!-- 						</li> -->
<!-- 						<li class="" data-sm-code="01010" data-sm-nm="A지점" data-sm-la="37.6069436" data-sm-lo="127.0156971"> -->
<!-- 							<a id=""><span><img src="images/report_work_defult.png"></span>A지점</a> -->
<!-- 						</li> -->
<!-- 						<li class="" data-sm-code="01012" data-sm-nm="C지점" data-sm-la="37.6133088" data-sm-lo="127.0144341"> -->
<!-- 							<a id=""><span><img src="images/report_work_defult.png"></span>C지점</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
				<div class="tbl_tab event" id="rndTab10300">
					<ul>
						<li class="tab_on" data-group="option" data-target="left" ><a id="" >마트마트</a></li>
						<li  data-group="option" data-target="right"  ><a id="">마트</a></li>
					</ul>
				</div>
				<div id="slideView10300" class="pop_accordion">
					<ul>
<!-- 						<li class="li_tab"  style="position: relative;" > -->
<!-- 							<div class="tbl_tab" id="rndTab10300"> -->
<!-- 								<ul> -->
<!-- 									<li class="tab_on" data-group="" data-target="" ><i class="fa fa-female"></i><a id="" >정현슈퍼</a></li> -->
<!-- 									<li  data-group="" data-target=""  ><i class="fa fa-female"></i><a id="">진호마트</a></li> -->
<!-- 								</ul> -->
<!-- 							</div> -->
<!-- 						</li> -->
						<li>
							<a class="toogle-slide" >
							<span><i  data-type="curr" class="fa fa-plus-square fa-lg"></i></span>
							현재 진열 줄수
							</a>
							<a class="options comm-group"  data-type="curr" id="currOptionOpenBtn" title="현재 진열 줄수 옵션" ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li  id="currStatus10300"  class="aco-con" style="display: none;"  >
							<div class="tbl_title align_r">
								<span id="currUpdateDate10300" ></span>
							</div>
							<table class="tbl_col">
								<colgroup>
									<col width="10%" >
									<col id="currColgroup" span="10" width="*" >
									<col span="2" width="7%" >
									<col width="16%" >
									
								</colgroup>
								<thead id="currThead10300" >
								</thead>
								<tbody id="currTbody10300"  >
								</tbody>
							</table>
						</li>
						<li>
							<a class="toogle-slide">
							<span><i  data-type="big" class="fa fa-plus-square fa-lg"></i></span>
								보조진열현황
							</a>
							<a class="options comm-group"  data-type="big" id="bigOptionOpenBtn10300" title="매대진열현황 옵션" ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li id="bigStatus10300" class="aco-con" style="display: none;" >
							<div class="tbl_title align_r">
								<span id="bigUpdateDate10300" ></span>
							</div>
							<table class="tbl_col">
								<thead id="bigThead10300" >
								</thead>
								<tbody id="bigTbody10300"  >
								</tbody>
							</table>
						</li>
						<li>
							<a class="toogle-slide">
								<span><i data-type="pd" class="fa fa-plus-square fa-lg"></i></span>
								PD 매대현황
							</a>
							<a class="options comm-group"  data-type="pd" id="pdOptionOpenBtn" ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li id="pdStatus10300" class="aco-con" style="display: none ;" >
							<div class="tbl_title align_r">
								<span id="pdUpdateDate10300" ></span>
							</div>
							<table class="tbl_col">
								<thead id="pdThead10300" >
								</thead>
								<tbody id="pdTbody10300"  >
								</tbody>
							</table>
						</li>
						<li>
							<a class="toogle-slide">
								<span><i  data-type="trt" class="fa fa-plus-square fa-lg"></i></span>
								취급품목현황
							</a>
							<a class="options"  data-type="trt" id="trtOptionOpenBtn10300"  ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li  id="trtStatus10300" class="aco-con" style="display: none;"  >
							<div style="overflow-x: auto;">
								<div class="tbl_title align_r">
									<span id="trtUpdateDate10300" ></span>
								</div>
								<table class="tbl_col">
<!-- 									<colgroup> -->
<!-- 										<col width="10%" > -->
<!-- 										<col span="10" width="6%" > -->
<!-- 										<col span="2" width="7%" > -->
<!-- 										<col width="16%" > -->
										
<!-- 									</colgroup> -->
									<thead id="trtThead10300" >
									</thead>
									<tbody id="trtTbody10300"  >
									</tbody>
								</table>
							</div>
						</li>
						<li>
							<a class="toogle-slide" >
								<span><i data-type="odd" class="fa fa-plus-square fa-lg"></i></span>
								시황 및 매장 특이사항
							</a>
<!-- 							<a class="options" ><span><i class="fa fa-cog fa-lg"></i></span></a> -->
						</li>
						<li id="oddStatus10300" class="aco-con" style="display: none;" >
							<div class="tbl_title align_r">
								<span id="oddUpdateDate10300" ></span>
							</div>
							<table class="tbl_col">
								<colgroup>
									<col width="10%" >
									<col width="18%" >
									<col width="18%" >
									<col width="18%" >
									<col width="18%" >
									<col width="18%" >
								</colgroup>
								<tbody>
									<tr id="oddOurImgTr10300" ></tr>
									<tr id="oddOurEtcTr10300"></tr>
									<tr id="oddComImgTr10300" ></tr>
									<tr id="oddComEtcTr10300"></tr>
								</tbody>
							</table>
						</li>
<!-- 						<li> -->
<!-- 							<a class="toogle-slide" data-target="exWORKStatus" > -->
<!-- 								<span><i data-type="work" class="fa fa-plus-square fa-lg"></i></span> -->
<!-- 								근무계획 -->
<!-- 							</a> -->
<!-- 						</li> -->
<!-- 						<li id="workStatus10300" data-update-flag="N" class="aco-con" style="display: none;" > -->
<!-- 							<div class="tbl_title"> -->
<!-- 								<span id="workUpdateDate10300" ></span> -->
<!-- 							</div> -->
<!-- 							<table class="tbl_row center" id="workTable10300" > -->
<!-- 								<colgroup> -->
<!-- 									<col width="9%" > -->
<!-- 									<col width="13%" > -->
<!-- 									<col width="13%" > -->
<!-- 									<col width="13%" > -->
<!-- 									<col width="13%" > -->
<!-- 									<col width="13%" > -->
<!-- 									<col width="13%" > -->
<!-- 									<col width="13%" > -->
<!-- 								</colgroup> -->
<!-- 								<thead id="workThead10300" > -->
<!-- 								</thead> -->
<!-- 								<tbody id="workTbody10300"  > -->
<!-- 								</tbody> -->
<!-- 							</table> -->
<!-- 							<div class="pfooter inner" id="workSaveBtnDiv10300"> -->
<!-- 								<button type="button" class="red" id="workSaveBtn10300"> -->
<!-- 									저장 -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 						</li> -->
					</ul>
				</div>
				<div id="cvsView10300" >
					<table class="tbl_col">
					<colgroup>
						<col width="20%">
						<col width="30%">
						<col width="10%">
						<col width="40%">
					</colgroup>
					<tr>
						<th>항목</th>
						<th>정검 사항</th>
						<th>점검 결과</th>
						<th>비고</th>
					</tr>
					<tbody id="cvsItemTbody10300" >
					</tbody>
					<tbody id="cvsItemTfoot10300" >
					</tbody>
				</table>
				</div>

			</div>
			
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	
	<!-- 레이어팝업 위에 팝업 행사매대 -->
	<div id="activityRndEVNOptionPop210300" class="pop-apn-pop" style="display: none ;" >
		<input type="hidden" id="optionIoType10300"  value="" >
		<div class="popup" style="width: 1100px" >
			<div class="phead">
				<span id="">행사매대 설정</span>
				<a id="evnOptionCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table style="width: 100%;" >
					<colgroup>
						<col style="width:55%">
						<col style="width:45%">
					</colgroup>
					<tr>
						<td style="vertical-align: top;     padding: 0px 5px;" >
							<div class="tbl_title" style="text-align: left; line-height: 34px; margin-bottom: 0;">
								<span>1. 매대 설정 </span>
								<button class="gray auth-insert" id="evnStnAddRow10300" style="float: right;" >행추가</button>
							</div>
							<table class="tbl_col pink">
								<colgroup>
									<col style="width:29%">
									<col style="width:15%">
									<col style="width:15%">
									<col style="width:15%">
									<col style="width:18%">
									<col style="width:18%">
								</colgroup>
								<tr>
									<th>매대명</th>
									<th>순서</th>
									<th>기본</th>
									<th>사용</th>
									<th>삭제</th>
									<th>선택</th>
								</tr>
								<tbody id="evnOptionStnTbody10300" >
								</tbody>
							</table>
							<div class="pfooter">
<!-- 								<button class="gray" id="entAddRow10300" >행추가</button> -->
								<button type="button" class="red auth-insert auth-update" id="evnStnSaveRow10300">저장</button>
							</div>
						</td>
						<td style="vertical-align: top; padding: 0 5px;" >
<!-- 							<div class="tbl_title"> -->
<!-- 								<span>1. 사원정보 </span> -->
<!-- 							</div> -->
							<div class="tbl_title" style="text-align: left; line-height: 34px; margin-bottom: 0;">
								<span>2. 매대유형 설정 </span>
								<button class="gray auth-insert" id="evnTypeAddRow10300" style="float: right;" >행추가</button>
							</div>
							<table class="tbl_col ">
								<colgroup>
									<col style="width:35%">
									<col style="width:20%">
									<col style="width:15%">
									<col style="width:15%">
									<col style="width:15%">
								</colgroup>
								<tr>
									<th>매대 유형</th>
									<th>순서</th>
									<th>기본</th>
									<th>사용</th>
									<th>삭제</th>
								</tr>
								<tbody id="evnOptionTypeTbody10300" >
								</tbody>
							</table>
							<div class="pfooter">
								<button type="button" class="red auth-insert auth-update" id="evnTypeSaveRow10300">
									저장
								</button>
							</div>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td colspan="2"	> -->
<!-- 							<button type="button" class="red" id="prdSaveRowAll10300">저장</button> -->
<!-- 						</td> -->
<!-- 					</tr> -->
				</table>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	
	<!--공통 옵션창 레이어팝업 위에 팝업 -->
	<div id="activityRndOptionPop10300" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<input type="hidden" id="optionPrantCode10300" >
			<div class="phead">
				<span id="optionTitle10300"></span>
				<a id="activityRndOptionCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_tab event option-tab">
					<ul>
						<li class="tab_on" data-group="option" data-target="left" ><a id="optionLeftTab10300" ></a></li>
						<li  data-group="option" data-target="right"  ><a id="optionRightTab10300"></a></li>
					</ul>
				</div>
				<div class="option-group option-left-group" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:30%">
							<col style="width:13%">
							<col style="width:13%">
							<col style="width:13%">
							<col style="width:11%">							
						</colgroup>
						<tr>
							<th id="optionLeftNickNmText10300" ></th>
							<th id="optionLeftNmText10300" ></th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
							<th>삭제</th>
						</tr>
						<tbody id="optionLeftTbody10300" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray auth-insert" id="optionLeftAddRow10300" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="optionLeftSaveBtn10300">저장</button>
					</div>
				</div>
				<div class="option-group option-right-group hide" >
					<table class="tbl_col">
						<colgroup>
							<col style="width:20%">
							<col style="width:30%">
							<col style="width:13%">
							<col style="width:13%">
							<col style="width:13%">
							<col style="width:11%">							
						</colgroup>
						<tr>
							<th id="optionRightNickNmText10300" ></th>
							<th id="optionRightNmText10300" ></th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
							<th>삭제</th>
						</tr>
						<tbody id="optionRightTbody10300" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray auth-insert" id="optionRightAddRow10300" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="optionRightSaveBtn10300">저장</button>
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
		<!-- 레이어 팝업 종료 -->
	<div id="activityRndPreView10300" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">사진보기</span>
				<a id="activityRndPreViewCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="option-group option-left-group" style="overflow: hidden;" >
					<div style="text-align: center;" >
						<img src="" id="rndPrevImg"  style="  max-width: 100%;" >
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<div id="activityRndAtendImgView10300" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
					<div class="phead txt_left" style="margin-top:0">촬영일시 : 
						<span id="rndPrevAtendDe"></span>
						<a id="activityRndAtendImgViewCloseXBtn" class="phead-closex" >닫기</a>
					</div>
					<span style="float:right;margin-right: 5px; color: #000 ">프로필 사진 적용</span>
					<div style="float:right;margin: 8px 10px;" class="inputCheck">
						<input type="checkbox" id="rndPrevAtendCheck" data-ai_path="" data-am_no="" data-em_no="" />
						<label for="rndPrevAtendCheck"></label>
					</div>
			</div>
			<div class="con">
				<div class="option-group option-left-group" style="overflow: hidden;" >
					<div style="text-align: center;" >
						<img src="" id="rndPrevAtendImg"  style="  max-width: 100%;" >
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	
	<!-- 레이어팝업 위에 팝업 -->
	<div id="activityTrtOptionPop10300" class="pop-apn-pop" style="display: none ;" >
		<input type="hidden" id="optionIoType10300"  value="" >
		<div class="popup" style="width: 1300px" >
			<div class="phead">
				<span id="">취급품목 설정</span>
				<a id="trtOptionCloseXBtn10300" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_title" style="text-align: left; line-height: 34px; margin-bottom: 0;">
					<button class="gray auth-insert" id="trtStnAddRow10300" style="float: right;" >행추가</button>
				</div>
				<table class="tbl_col pink">
					<colgroup>
						<col style="width:7%">
						<col style="width:8%">
						<col style="width:9%">
						<col style="width:10%">
						<col style="width:15%">
						<col style="width:15%">
						<col style="width:8%">
						<col style="width:4%">
						<col style="width:4%">
						<col style="width:4%">
						<col style="width:6%">
					</colgroup>
					<tr>
						<th>제품굼</th>
						<th>자재그룹</th>
						<th>계층1</th>
						<th>계층2</th>
						<th>계층3</th>
						<th>품목명</th>
						<th>약어</th>
						<th>순서</th>
						<th>기본</th>
						<th>사용</th>
						<th>삭제</th>
					</tr>
					<tbody id="trtOptionStnTbody10300" >
					</tbody>
				</table>
				<div class="pfooter">
<!-- 								<button class="gray" id="entAddRow10300" >행추가</button> -->
					<button type="button" class="red auth-insert auth-update" id="trtStnSaveRow10300"  >저장</button>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	
	<section>
		<div class="page_nav">
			<h1>순회MD</h1>
			<p>
				<i class="fa fa-home"></i>
				<span class="clamp fa"></span>활동관리
				<span class="clamp fa"></span>순회MD
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">
<!-- 				<span> -->
<!-- 					<input id="searchStoreNm10300" type="text" class="input-icon" maxlength="10" placeholder="매장"> -->
<!-- 					<label for="inputSearchStoreNm10300"><i class="fa fa-user"></i></label> -->
<!-- 					<input type="hidden" id="searchStore10300" /> -->
<!-- 				</span> -->
				<span>
					<input id="searchEmpNm10300" type="text" class="input-icon" maxlength="10" placeholder="이름" >
					<label for="searchEmpNm10300"><i class="fa fa-user"></i></label>
				</span>
				<span>
					<strong>소속</strong><select id="searchBhf10300" class="select-basic"><option value="" selected="selected">지점</option></select>
					<select id="searchTeam10300" class="select-basic"><option value="" selected="selected">팀명</option></select>
				</span>
				<span>
					<input class="input-icon" id="searchDate10300" type="text" readonly="readonly" placeholder="날짜선택" value="2015-10-14" />
					<label for="searchDate10300"><i class="fa fa-calendar"></i></label>
				</span>
				<span>
					<span class="inputCheck">
						<input type="checkbox" id="retireYn10300" style="margin-top: 4px;">
						<label for="retireYn10300"  style="margin-top: 4px;" ></label>
					</span>
					<strong>퇴사자</strong>
				</span>
			</div>
			<div class="btn_gup clear">
				<button id="searchActivityRnd10300" class="red">
					조회
				</button>
				<button id="clearActivityRnd10300" class="gray">
					초기화
				</button>
			</div>
		</div>
	</section>
	<section>
		<div class="searchbox_sub" id="searchBox10300" >
			<h2 id="dmDt10300"></h2><h2> / </h2><h2 id="omNm10300"></h2><h2>순회MD</h2>
			<button data-attend_lvffc_at="" class="gray attendLvffcAt10300">
				총원
			</button>
			<span id="totCnt10300"></span>
			<button data-attend_lvffc_at="AY" class="skyblue attendLvffcAt10300">
				출근
			</button>
			<span id="dmAttendYCnt10300"></span>
			<button data-attend_lvffc_at="AN" class="hotpink attendLvffcAt10300">
				미출근
			</button>
			<span id="dmAttendNCnt10300"></span>
			<button data-attend_lvffc_at="LY" class="skyblue attendLvffcAt10300">
				퇴근
			</button>
			<span id="dmLvffcYCnt10300"></span>
			<button data-attend_lvffc_at="LN" class="hotpink attendLvffcAt10300">
				미퇴근
			</button>
			<span id="dmLvffcNCnt10300"></span>
			<button data-attend_lvffc_at="VY" class="brown attendLvffcAt10300">
				휴가
			</button>
			<span id="dmVcatnYCnt10300"></span>
		</div>
	</section>
	<section class="divide" id="rndUserListBody" >
			<div class="none-data" style="">
					<i class="fa fa-info-circle"style=""></i>
					<span>내용을 검색해 주세요.</span>
			</div>
		</section>
	<section id="navi10300" class="paginate">
	</section>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>