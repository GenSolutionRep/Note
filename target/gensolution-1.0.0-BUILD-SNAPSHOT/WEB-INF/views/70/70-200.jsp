<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/70/70-200.js"></script>
</head>
<body>
<article class="content">
<div id='allDiv70200'>
	<section>
		<div class="page_nav">
			<h1>주요행사</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>커뮤니케이션<span class="clamp fa"></span>주요행사
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">
				<button type="button" id="searchToday70200" class="red">
					오늘
				</button>
				<input type="text" id="dateSelect" size="10" style="width: 90px;" readonly="readonly"/>
				<button type="button" id="searchPreMonth70200" class="red">
					◀
				</button>
				<button type="button" id="searchNextMonth70200" class="red">
					▶
				</button>
				<span>
					<!-- <strong>지점</strong> -->
					<select class="select-basic"  id="om_code_70200"  name ="om_code_70200" style="width: 120px">
					</select> 
				</span>
			</div>
			<div class="btn_gup">
				<button class="skyblue" id="tempSearch70200" style="display: none;" >조회</button>
				<button class="write" id="tempList70200" >일정목록</button>
				<button class="red auth-insert" id="tempInsert70200" >일정등록</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<div id="mycal"></div>
		<div id="myScheduleList" style="display: none;">
			<table class="tbl_col tbl_col_white">
				<colgroup>
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:60%">
				</colgroup>
				<tbody id="tbody_70200">
				</tbody>
			</table>
		</div>
	<!-- //UI Object -->
	</section>
	
	<!-- 레이어팝업 show -->
	<div id="mainEventPopSpace" >
		<div id="tempPop70200" style="display:none  ;">
			<div class="popup"  style="width: 850px;" >
			
					<div class="phead">
						<span id="title_70200">주요행사 등록</span>
						<a id="tempCloseX70200" class="phead-closex" >닫기</a>
					</div>
					<div class="con" id="popSave_70200">
						<!-- div class="tbl_title" >
							<span>주요행사 등록</span>
						</div> -->
						<form id="frm70200">
						<input type="hidden" id="me_innb_70200" name="me_innb">
						<input type="hidden" id="save_type_70200" name="save_type">
						<input type="hidden" id="auth_type_70200" name="auth_type">
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="85%">
							<col>
							</colgroup>
							<tr>
								<th>등록구분</th>
								<td>
									<div class="inputRadio">
										<input type="radio" name = "me_holiday_at" id="chk_me_holiday_at_N_70200" value="N" checked="checked">
										<label for="chk_me_holiday_at_N_70200"></label>
									</div>주요행사	
									<div class="inputRadio">
										<input type="radio" name = "me_holiday_at" id="chk_me_holiday_at_Y_70200" value="Y">
										<label for="chk_me_holiday_at_Y_70200"></label>
									</div>휴일	
								</td>
							</tr>
							<tr>
								<th>지점</th>
								<td>
									<!-- <input id="pop_om_nm_70200" name="om_nm" class="input-basic" type="text" value="" maxlength="20"  >
									<div id="selectItem_om_nm_70200" class="select-item"></div>
									-->
									<span>
										<select class="select-basic"  id="pop_om_code_70200"  name ="om_code" style="width: 120px">
										</select>
									</span> 
									<div id="selectItem_om_nm_70200" class="select-item"></div>
								</td>
							</tr>
							<tr>
								<th>매장</th>
								<td>
									<input id="pop_sm_nm_70200" name="sm_nm" class="input-basic" type="text" placeholder="" maxlength="50" value="" >
									<div id="selectItem_sm_nm_70200" class="select-item"></div>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td>
									<div style="float: left;">
									<input id="pop_me_sj_70200" name="me_sj" type="text" style="width: 620px;" maxlength="50" value="" >
									</div>
									<!-- 색상 end -->
									<div id="colorSelectorBackground" style="float: left; margin-left: 10px;">
										<div style="background-color: #426ff5; width: 25px; height: 25px; border: 1px solid #d1cdd1; border-radius :15px"></div>
									</div>
									<input type="hidden" id="colorBackground" value="#426ff5">
									<!-- 색상  end -->
								</td>
							</tr>
							<tr>
								<th>일정</th>
								<td>
									<input type="text" id="dateFrom_popup70200" size="10" style="width: 90px;" readonly="readonly">
									<select id="hhFrom_popup70200" style="width: 50px;">
										<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option>
										<option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option>
										<option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option>
										<option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option>
										<option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
									</select>:<select id="mmFrom_popup70200" style="width: 50px;">
										<option value="00">00</option><option value="05">05</option>
										<option value="10">10</option><option value="15">15</option>
										<option value="20">20</option><option value="25">25</option>
										<option value="30">30</option><option value="35">35</option>
										<option value="40">40</option><option value="45">45</option>
										<option value="50">50</option><option value="55">55</option>
									</select>
									<span class="wave"></span>
									<input type="text" id="dateTo_popup70200" size="10" style="width: 90px;" readonly="readonly">								
									<select id="hhTo_popup70200" style="width: 50px;">
										<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option>
										<option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option>
										<option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option>
										<option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option>
										<option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
									</select>:<select id="mmTo_popup70200" style="width: 50px;">
										<option value="00">00</option><option value="05">05</option>
										<option value="10">10</option><option value="15">15</option>
										<option value="20">20</option><option value="25">25</option>
										<option value="30">30</option><option value="35">35</option>
										<option value="40">40</option><option value="45">45</option>
										<option value="50">50</option><option value="55">55</option>
									</select>
									<div class="inputCheck" style="margin-left: 15px">
										<input id="chk_me_allday_at_70200" name="me_allday_at"  type="checkbox" data-value="N" >
										<label for="chk_me_allday_at_70200"></label>
									</div>종일
									<div class="inputCheck" style="margin-left: 15px">
										<input id="chk_me_ntcn_at_70200" name="me_ntcn_at"  type="checkbox" data-value="N">
										<label for="chk_me_ntcn_at_70200"></label>
									</div>스마트폰 알림
								</td>
								
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<textarea id="pop_me_cn_70200" name="me_cn" cols="60" rows="10" maxlength="250">
									</textarea>
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td style="padding: 0;" >
									<input type="hidden" id="am_no_70200" value=""  >
									<div id="file_70200" ></div>
								</td>
							</tr>
						</table>
						</form>
					</div>
					<div class="pfooter">
		       			<button type='button' class="red auth-insert auth-update" id="tempSave70200">저장</button>
		       			<button type='button' class="white" id="tempClose70200">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
			</div>
			<div id="tempViewPop70200" style="display:none  ;">
			<div class="popup"  style="width: 850px;" >
			
					<div class="phead">
						<span id="subject_title_70200">주요행사 보기</span>
						<a id="tempViewCloseX70200" class="phead-closex" >닫기</a>
					</div>
					<div class="con" id="popView_70200">
						<!-- div class="tbl_title" >
							<span>주요행사 등록</span>
						</div> -->
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="85%">
							<col>
							</colgroup>
							<tr>
								<th>등록구분 </th>
								<td id="view_chk_me_holiday_at_70200">
								</td>
							</tr>
							<tr>
								<th>지점</th>
								<td>
									<div class="input_group clear">
									<div id="selectOmViewItem_70200" class="select-item" >
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th>매장</th>
								<td>
									<div class="input_group clear">
										<div id="selectSmViewItem_70200" class="select-item" ></div>
									</div>
								</td>
								
							</tr>
							<tr>
								<th>제목</th>
								<td id="view_me_Sj_70200">
								</td>
							</tr>
							<tr>
								<th>일정</th>
								<td id="view_period_70200">
								</td>
							</tr>
							<tr>
								<th>등록자</th>
								<td id="view_updt_man_name_70200">
								</td>
							</tr>
							<tr>
								<th>내용</th>
								<td id="view_me_cn_70200" class="content-td" >
								</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3" class="attach-view" >
								<div class="tx-attach-div">
									<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">
										<div class="tx-attach-box-inner">
											<ul id="fileViewList_70200" class="tx-attach-list">
											</ul>
										</div>
									</div>
								</div>
							</tr>
						</table>
					</div>
					<div class="pfooter">
		       			<button type='button' class="red auth-update" id="tempViewUpdate70200">수정</button>
		       			<button type='button' class="gray auth-del" id="tempViewDelete70200">삭제</button>
		       			<button type='button' class="white" id="tempViewClose70200">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
			</div>
	</div>
</div>	
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
