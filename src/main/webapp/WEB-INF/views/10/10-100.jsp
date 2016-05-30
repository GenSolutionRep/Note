<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-100.js"></script>
<style>
.noline {
	border-top: none;
	border-left: none;
	border-bottom: 1px solid #e5e5e5;
}

.noline td {
	border-right: none;
	border-top: none;
	border-bottom: 1px solid #e5e5e5;
}
.noline td div {
	word-break: break-all;
}

.tbl_rhtbox {
	margin-right: 16px;
}

.tbl_rhtbox_top {
	padding: 6px 10px;
	background-color: #f5f7f9;
	border: 1px solid #e6e7e8;
}

.tbl_rhtbox_con {
	height: 365px;
	padding: 0;
	overflow-x: auto;
	overflow-y: scroll;
	font-size: 12px;
	border: 1px solid #eaeaea;
	text-align: left;
}
.tbl_rhtbox_bottom {
	padding: 6px 10px;
	background-color: #f5f7f9;
	border: 1px solid #e6e7e8;
	text-align: center;
}
.plan-mod-btn{
  margin: 5px 0px 0px 0px;
}  
.sche-tr > td{
vertical-align: top;
text-align: left;
}
.sche-tr > td:FIRST-CHILD{
vertical-align: middle;
text-align: center;
}
/* .sche-tr > td > div{ */
/* color: #fff;  */
/* } */
</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>순방계획</h1>
			<p>
				<i class="fa fa-home"></i>
				<span class="clamp fa"></span>활동관리
				<span class="clamp fa"></span>순방계획
			</p>
		</div>
	</section>
	<section>

		<div class="searchbox">
			<div class="input_group clear">
				<button id="searchTodayActivityRndPlan10100" class="red">오늘</button>
				<button id="searchPrevWeekActivityRndPlan10100" class="red">◀</button>
				<button id="searchNextWeekActivityRndPlan10100" class="red">▶</button>
				<span>
					<input class="input-icon" id="searchDate10100" type="text" readonly="readonly" placeholder="날짜선택" />
					<label for="searchDate10100"><i class="fa fa-calendar"></i></label>
				</span>
				<span>
					<strong>소속</strong><select id="searchBhf10100" class="select-basic"><option value="" selected="selected">지점</option></select>
					<select id="searchTeam10100" class="select-basic"><option value="" >팀명</option></select>
					<select id="searchEmp10100" class="select-basic"><option value="" >사원명</option></select>
				</span>
			</div>
			<div class="btn_gup clear">
<!-- 				<button id="searchActivityRndPlan10100" class="red"> -->
<!-- 					조회 -->
<!-- 				</button> -->
<!-- 				<button id="clearActivityRndPlan10100" class="gray"> -->
<!-- 					초기화 -->
<!-- 				</button> -->
				<button id="modActivityRndPlan10100" class="gray auth-insert" style="display: none;" >
					일정편집
				</button>
			</div>
		</div>
	</section>
	<section>
	<table style="width: 100%" >
		<tr>
<!-- 			<td style="width:214px; text-align:left;"> -->
			<td  id="selectStrList10100" style="display: none; vertical-align: top;" >
<!-- 				<input type="hidden" id="selectPrInnb10100" > -->
<!-- 				<input type="hidden" id="selectPrdInnb10100" > -->
				<div class="tbl_rhtbox"style=" overflow: hidden;">
					<div class="input_group clear tbl_rhtbox_top">
						<span>
							<input class="input-icon" id="selectStrDate10100" type="text" readonly="readonly" placeholder="날짜선택">
							<label for="selectStrDate10100"><i class="fa fa-calendar"></i></label>
						</span>
					</div>
					<div class="tbl_rhtbox_con" id="">
						<table class="tbl_col tbl_col_white noline">
						<tbody id="rndPlanStrTb10100">
						</tbody>
<!-- 							<tr> -->
<!-- 								<td> -->
<!-- 									<div class="inputCheck"> -->
<!-- 										<input type="checkbox" value="None" id="inputCheck" name="check" checked> -->
<!-- 										<label for="inputCheck"></label> -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 								<td> -->
<!-- 									<div class="txt_left">E지점 -->
<!-- 									</div> -->
<!-- 								</td> -->
<!-- 							</tr> -->
						</table>
					</div>
					<div class="tbl_rhtbox_bottom">
						<button id="rndPlanStrSaveBtn10100" class="gray auth-insert auth-update" >저장</button>
					</div>
				</div>
			</td>
			<td style="vertical-align: top;    width: 100%;">
			<!-- UI Object -->
				<table summary="순환계획" class="tbl_col">
					<caption>
						순환계획
					</caption>
					<colgroup>
						<col width="9%">
						<col span="7" width="13%">
					</colgroup>
					<thead id="activityRndPlanTHead10100">
					</thead>
					<tbody id="activityRndPlanTBody10100">
					</tbody>
				</table>
			<!-- //UI Object -->
			</td>
		</tr>
	</table>

	</section>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
