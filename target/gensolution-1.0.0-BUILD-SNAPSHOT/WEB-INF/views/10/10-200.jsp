<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-200.js"></script>
<style type="text/css">
.screenshot10200{
	position:absolute;
	border:1px solid #ccc;
	background:#797979;
	padding:5px;
 	display:none;
	color:#fff;
}
.tbl_row.center td ,.tbl_row.center th{
	text-align: center;
}
.trt-change-group{
 width: 90%;
}
</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="activityFixingPop210200" style="display: none ;">
		<input type="hidden" id="emNo10200" />
		<input type="hidden" id="dfwpInnb10200" />
		<div class="popup" style="width: 1100px;">
			<div class="phead">
				<span id="">업무일지</span>
				<a id="activityFixingPopCloseX10200" class="phead-closex">닫기</a>
			</div>
			<div class="diary">
				<h2 id="fixingEmSmNm10200" >이마트 성수점</h2>
				<div class="dateweekbox">
					<input type="hidden" id="selectBaseDate" value="" >
					<ul class="dateweek">
						<li>
							<a id="diaryPrev" ><span><i class="fa fa-angle-left"></i></span> </a>
						</li>
						<li>
							<a class="cell_active"><b id="diaryDate" ></b></a>
						</li>
						<li>
							<a id="diaryNext" > <span><i class="fa fa-angle-right"></i></span> </a>
						</li>
					</ul>
				</div>
				<div class="charger_name">
					담당사원 : <span id="fixsing_name" ></span> &nbsp;
					<button class="skyblue" id="rndExcelDownLoad10200" ><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
				</div>
			</div>
			<div class="con">
				<div id="slideView" class="pop_accordion">
					<ul>
						<li >
							<a class="toogle-slide" data-target="exISLANDStatus" >
								<span><i data-type="evn" class="fa fa-plus-square fa-lg"></i></span>
								행사매대현황
							</a>
							<a class="options" data-type="evn" id="evnOptionOpenBtn"  title="행사매대현황 옵션"  ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li id="evnStatus10200" class="aco-con" style="display: none;" >
						</li>
						<li>
							<a class="toogle-slide" data-target="exPOGStatus" >
							<span><i  data-type="pog" class="fa fa-plus-square fa-lg"></i></span>
							POG 및 현재 진열 줄수
							</a>
							<a class="options comm-group"  data-type="pog" id="pogOptionOpenBtn" title="POG 및 현재 진열 줄수 옵션" ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li  id="pogStatus10200"  class="aco-con" style="display: none;"  >
							<div class="tbl_title align_r">
								<span id="pogUpdateDate10200" ></span>
							</div>
							<table class="tbl_col">
								<colgroup id="pogColgroup" >
								</colgroup>
								<thead id="pogThead10200" >
								</thead>
								<tbody id="pogTbody10200"  >
								</tbody>
							</table>
						</li>
						<li>
							<a class="toogle-slide" data-target="exBIGStatus" >
							<span><i  data-type="big" class="fa fa-plus-square fa-lg"></i></span>
								보조진열현황
							</a>
							<a class="options comm-group"  data-type="big" id="bigOptionOpenBtn" title="매대진열현황 옵션" ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li id="bigStatus10200" class="aco-con" style="display: none;" >
							<div class="tbl_title align_r">
								<span id="bigUpdateDate10200" ></span>
							</div>
							<table class="tbl_col">
								<thead id="bigThead10200" >
								</thead>
								<tbody id="bigTbody10200"  >
								</tbody>
							</table>
						</li>
						<li>
							<a class="toogle-slide" data-target="exnPDStatus" >
								<span><i data-type="pd" class="fa fa-plus-square fa-lg"></i></span>
								PD 매대현황
							</a>
							<a class="options comm-group"  data-type="pd" id="pdOptionOpenBtn" ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li id="pdStatus10200" class="aco-con" style="display: none ;" >
							<div class="tbl_title align_r">
								<span id="pdUpdateDate10200" ></span>
							</div>
							<table class="tbl_col">
								<thead id="pdThead10200" >
								</thead>
								<tbody id="pdTbody10200"  >
								</tbody>
							</table>
						</li>
						<li>
							<a class="toogle-slide" data-target="exITEMStatus" >
								<span><i  data-type="trt" class="fa fa-plus-square fa-lg"></i></span>
								취급품목현황
							</a>
							<a class="options"  data-type="trt" id="trtOptionOpenBtn10200"  ><span><i class="fa fa-cog fa-lg"></i></span></a>
						</li>
						<li  id="trtStatus10200" class="aco-con" style="display: none;"  >
							<div style="overflow-x: auto;">
								<div class="tbl_title align_r">
									<span id="trtUpdateDate10200" ></span>
								</div>
								<table class="tbl_col">
<!-- 									<colgroup> -->
<!-- 										<col width="10%" > -->
<!-- 										<col span="10" width="6%" > -->
<!-- 										<col span="2" width="7%" > -->
<!-- 										<col width="16%" > -->
										
<!-- 									</colgroup> -->
									<thead id="trtThead10200" >
									</thead>
									<tbody id="trtTbody10200"  >
									</tbody>
								</table>
							</div>
						</li>
						<li>
							<a class="toogle-slide" data-target="exETCStatus" >
								<span><i data-type="odd" class="fa fa-plus-square fa-lg"></i></span>
								시황 및 매장 특이사항
							</a>
<!-- 							<a class="options" ><span><i class="fa fa-cog fa-lg"></i></span></a> -->
						</li>
						<li id="oddStatus10200" class="aco-con" style="display: none;" >
							<div class="tbl_title align_r">
								<span id="oddUpdateDate10200" ></span>
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
									<tr id="oddOurImgTr10200" ></tr>
									<tr id="oddOurEtcTr10200"></tr>
									<tr id="oddComImgTr10200" ></tr>
									<tr id="oddComEtcTr10200"></tr>
								</tbody>
							</table>
						</li>
						<li>
							<a class="toogle-slide" data-target="exWORKStatus" >
								<span><i data-type="work" class="fa fa-plus-square fa-lg"></i></span>
								근무계획
							</a>
<!-- 							<a class="options" ><span><i class="fa fa-cog fa-lg"></i></span></a> -->
						</li>
						<li id="workStatus10200" data-update-flag="N" class="aco-con" style="display: none;" >
							<div class="tbl_title">
								<span id="workUpdateDate10200" ></span>
							</div>
							<table class="tbl_row center" id="workTable10200" >
								<colgroup>
									<col width="9%" >
									<col width="13%" >
									<col width="13%" >
									<col width="13%" >
									<col width="13%" >
									<col width="13%" >
									<col width="13%" >
									<col width="13%" >
								</colgroup>
								<thead id="workThead10200" >
								</thead>
								<tbody id="workTbody10200"  >
								</tbody>
							</table>
							<div class="pfooter inner" id="workSaveBtnDiv10200">
								<button type="button" class="red auth-insert auth-update" id="workSaveBtn10200">
									저장
								</button>
							</div>
						</li>
					</ul>
				</div>

			</div>
			
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어팝업 위에 팝업 행사매대 -->
	<div id="activityFixingEVNOptionPop210200" class="pop-apn-pop" style="display: none ;" >
		<input type="hidden" id="optionIoType10200"  value="" >
		<div class="popup" style="width: 1200px" >
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
								<button class="gray auth-insert" id="evnStnAddRow10200" style="float: right;" >행추가</button>
							</div>
							<table class="tbl_col pink">
								<colgroup>
									<col style="width:16%">
									<col style="width:20%">
									<col style="width:14%">
									<col style="width:14%">
									<col style="width:14%">
									<col style="width:16%">
									<col style="width:16%">
								</colgroup>
								<tr>
									<th>약어</th>
									<th>매대명</th>
									<th>순서</th>
									<th>기본</th>
									<th>사용</th>
									<th>삭제</th>
									<th>선택</th>
								</tr>
								<tbody id="evnOptionStnTbody10200" >
								</tbody>
							</table>
							<div class="pfooter">
<!-- 								<button class="gray" id="entAddRow10200" >행추가</button> -->
								<button type="button" class="red auth-insert auth-update" id="evnStnSaveRow10200">저장</button>
							</div>
						</td>
						<td style="vertical-align: top; padding: 0 5px;" >
<!-- 							<div class="tbl_title"> -->
<!-- 								<span>1. 사원정보 </span> -->
<!-- 							</div> -->
							<div class="tbl_title" style="text-align: left; line-height: 34px; margin-bottom: 0;">
								<span>2. 매대유형 설정 </span>
								<button class="gray auth-insert" id="evnTypeAddRow10200" style="float: right;" >행추가</button>
							</div>
							<table class="tbl_col ">
								<colgroup>
									<col style="width:18%">
									<col style="width:25%">
									<col style="width:15%">
									<col style="width:14%">
									<col style="width:14%">
									<col style="width:14%">
								</colgroup>
								<tr>
									<th>약어</th>
									<th>매대 유형</th>
									<th>순서</th>
									<th>기본</th>
									<th>사용</th>
									<th>삭제</th>
								</tr>
								<tbody id="evnOptionTypeTbody10200" >
								</tbody>
							</table>
							<div class="pfooter">
								<button type="button" class="red auth-insert auth-update" id="evnTypeSaveRow10200">
									저장
								</button>
							</div>
						</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td colspan="2"	> -->
<!-- 							<button type="button" class="red" id="prdSaveRowAll10200">저장</button> -->
<!-- 						</td> -->
<!-- 					</tr> -->
				</table>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	
	<!--미리보기미리보기미리보기미리보기미리보기미리보기미리보기미리보기미리보기미리보기미리보기미리보기 레이어팝업 위에 팝업 -->
	<div id="activityFixingPreView210200" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
				<span id="">사진보기</span>
				<a id="activityFixingPreViewCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="option-group option-left-group" style="overflow: hidden;" >
					<div style="text-align: center;">
						<img src="" id="fixingPrevImg" >
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<div id="activityFixingAtendImgView10300" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<div class="phead">
					<div class="phead txt_left" style="margin-top:0">촬영일시 : 
						<span id="fixingPrevAtendDe"></span>
						<a id="activityFixingAtendImgViewCloseXBtn" class="phead-closex" >닫기</a>
					</div>
					<span style="float:right;margin-right: 5px; color: #000 ">프로필 사진 적용</span>
					<div style="float:right;margin: 8px 10px;" class="inputCheck">
						<input type="checkbox" id="fixingPrevAtendCheck" data-ai_path="" data-am_no="" data-em_no="" />
						<label for="fixingPrevAtendCheck"></label>
					</div>
			</div>
			<div class="con">
				<div class="option-group option-left-group" style="overflow: hidden;" >
					<div style="text-align: center;" >
						<img src="" id="fixingPrevAtendImg"  style="  max-width: 100%;" >
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<div id="activityFixingOptionPop10200" class="pop-apn-pop" style="display: none ;" >
		<div class="popup" style="width: 600px" >
			<input type="hidden" id="optionPrantCode10200" >
			<div class="phead">
				<span id="optionTitle10200"></span>
				<a id="activityFixingOptionCloseXBtn" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_tab event">
					<ul>
						<li class="tab_on" data-group="option" data-target="left" ><a id="optionLeftTab10200" ></a></li>
						<li  data-group="option" data-target="right"  ><a id="optionRightTab10200"></a></li>
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
							<th id="optionLeftNickNmText10200" ></th>
							<th id="optionLeftNmText10200" ></th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
							<th>삭제</th>
						</tr>
						<tbody id="optionLeftTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray auth-insert" id="optionLeftAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="optionLeftSaveBtn10200">저장</button>
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
							<th id="optionRightNickNmText10200" ></th>
							<th id="optionRightNmText10200" ></th>
							<th>순서</th>
							<th>기본</th>
							<th>사용</th>
							<th>삭제</th>
						</tr>
						<tbody id="optionRightTbody10200" >
						</tbody>
					</table>
					<div class="pfooter">
						<button class="gray auth-insert" id="optionRightAddRow10200" >행추가</button>
						<button type="button" class="red auth-insert auth-update" id="optionRightSaveBtn10200">저장</button>
					</div>
				</div>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<!-- 레이어팝업 위에 팝업 -->
	<div id="activityFixingTRTOptionPop210200" class="pop-apn-pop" style="display: none ;" >
		<input type="hidden" id="optionIoType10200"  value="" >
		<div class="popup" style="width: 1300px" >
			<div class="phead">
				<span id="">취급품목 설정</span>
				<a id="trtOptionCloseXBtn10200" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_title" style="text-align: left; line-height: 34px; margin-bottom: 0;">
					<button class="gray auth-insert" id="trtStnAddRow10200" style="float: right;" >행추가</button>
				</div>
				<table class="tbl_col pink">
					<colgroup>
						<col style="width:7%">
						<col style="width:8%">
						<col style="width:9%">
						<col style="width:10%">
						<col style="width:14%">
						<col style="width:14%">
						<col style="width:8%">
						<col style="width:6%">
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
					<tbody id="trtOptionStnTbody10200" >
					</tbody>
				</table>
				<div class="pfooter">
<!-- 								<button class="gray" id="entAddRow10200" >행추가</button> -->
					<button type="button" class="red auth-insert auth-update" id="trtStnSaveRow10200">저장</button>
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
				<span>
					<input id="searchStoreNm10200" type="text" class="input-icon" maxlength="10" placeholder="매장">
					<label for="inputSearchStoreNm10200"><i class="fa fa-user"></i></label>
					<input type="hidden" id="searchStore10200" />
				</span>
				<span>
					<input id="searchEmpNm10200" type="text" class="input-icon" maxlength="10" placeholder="이름">
					<label for="searchEmpNm10200"><i class="fa fa-user"></i></label>
				</span>
				<span>
					<strong>소속</strong><select id="searchBhf10200" class="select-basic"><option value="" selected="selected">지점</option></select>
					<select id="searchTeam10200" class="select-basic"><option value="" selected="selected">팀명</option></select>
				</span>
				<span>
					<input class="input-icon" id="searchDate10200" type="text" readonly="readonly" placeholder="날짜선택" value="" />
					<label for="searchDate10200"><i class="fa fa-calendar"></i></label>
				</span>
				<span>
					<span class="inputCheck">
						<input type="checkbox" id="retireYn10200" style="margin-top: 4px;">
						<label for="retireYn10200"  style="margin-top: 4px;" ></label>
					</span>
					<strong>퇴사자</strong>
				</span>
			</div>
			<div class="btn_gup clear">
				<button id="searchActivityFixing10200" class="red">
					조회
				</button>
				<button id="clearActivityFixing10200" class="gray">
					초기화
				</button>
			</div>
		</div>
	</section>
	<section>
		<div class="searchbox_sub" id="searchBox10200" >
			<h2 id="dmDt10200"></h2><h2> / </h2><h2 id="omNm10200"></h2><h2>고정MD</h2>
			<button data-attend_lvffc_at="" class="gray attendLvffcAt10200">
				총원
			</button>
			<span id="totCnt10200"></span>
			<button data-attend_lvffc_at="AY" class="skyblue attendLvffcAt10200">
				출근
			</button>
			<span id="dmAttendYCnt10200"></span>
			<button data-attend_lvffc_at="AN" class="hotpink attendLvffcAt10200">
				미출근
			</button>
			<span id="dmAttendNCnt10200"></span>
			<button data-attend_lvffc_at="LY" class="skyblue attendLvffcAt10200">
				퇴근
			</button>
			<span id="dmLvffcYCnt10200"></span>
			<button data-attend_lvffc_at="LN" class="hotpink attendLvffcAt10200">
				미퇴근
			</button>
			<span id="dmLvffcNCnt10200"></span>
			<button data-attend_lvffc_at="VY" class="brown attendLvffcAt10200">
				휴가
			</button>
			<span id="dmVcatnYCnt10200"></span>
		</div>
	</section>
	<section class="divide" id="fixingUserListBody" >
			<div class="none-data" style="">
					<i class="fa fa-info-circle"style=""></i>
					<span>내용을 검색해 주세요.</span>
			</div>
		</section>
	<section id="navi10200" class="paginate">
	</section>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>