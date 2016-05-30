<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <script type="text/javascript" src="<%=jsPath%>/routine/30/30-100.js"></script> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<%@ include file="30-100js.jsp"%>
<style type="text/css">
/* table tr td table.line-box tr td, table tr td table.line-box tr th{
	border: 1px solid #000;
} */
</style>
</head>
<body>

<article class="content">
<!-- 출력 시작 -->
<div id="printArea30100_${typeCode}" style="display: none;">
	<table class="" style="border: none; text-align: center; font-family: Gothic; width: 100%; margin-top: 30px;">
		<colgroup>
			<col style="width:30%">
			<col style="width:70%">
		</colgroup>
		<tbody>
			<tr>
				<th colspan="2" style="height: 50px; text-align: center;">
					<div ><u><h1>허&nbsp;&nbsp;가&nbsp;&nbsp;원</h1></u></div>
					<div style=""><h3><span id="aTitle1_30100_${typeCode}"></span></h3></div>
				</th>
			</tr>
			<tr>
				<th colspan="2" style="height: 80px; text-align: center; vertical-align: middle;">
					아래와 같이 <span id="aTitle2_30100_${typeCode}"></span>(을/를) 신청하오니 재가하여 주시기 바랍니다.
				</th>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; " >1. 성&nbsp;&nbsp;&nbsp;명 :</th>
				<td style="text-align: left;">
					<span id="aName_30100_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">2. 근무지 :</th>
				<td style="text-align: left;">
					<span id="aPlace_30100_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">3. 사&nbsp;&nbsp;&nbsp;유  :</th>
				<td style="text-align: left;">
					<span id="aReason_30100_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">4. 기&nbsp;&nbsp;&nbsp;간  :</th>
				<td style="text-align: left;">
					<span id="aPeriod_30100_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">5. 대리인 : </th>
				<td style="text-align: left;">
					<%-- <span id="aRepresentative_30100_${typeCode}"></span> --%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(인)
				</td>
			</tr>
			<tr>
				<th colspan="2" style="height: 90px; text-align: center; vertical-align: bottom;">
					<span id="adate_30100_${typeCode}">2015. 05. 01.</span>
				</th>
			</tr>
			<tr>
				<th colspan="2" style="height: 80px; text-align: center; vertical-align: middle;">
					신청자 : &nbsp;&nbsp;&nbsp;<span id="aApplicant_30100_${typeCode}"></span>&nbsp;&nbsp;&nbsp;(인)
				</th>
			</tr>
			<tr style="height: 120px;">
				<td colspan="2" style="text-align: center;">
					<c:choose>
						<c:when test="${login_cp_cd=='001'}">
							<table class='line-box' style="border: 1px solid #000; margin-left: auto; margin-right: auto; border-collapse : collapse">
								<tr height="30px;" style=" border: 1px solid #000;">
									<th rowspan="2" width="30px;">결<br>재 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">팀 장 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">과 장 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">차 장 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">부 장 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">부사장 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">사 장 </th>
								</tr>
								<tr height="80px;"  style="border-bottom: 1px solid #000;">
									<td style="border: 1px solid #000;"></td>
									<td style="border: 1px solid #000;"></td>						
									<td style="border: 1px solid #000;"></td>
									<td style="border: 1px solid #000;"></td>						
									<td style="border: 1px solid #000;"></td>
									<td style="border: 1px solid #000;"></td>						
								</tr>
							</table>
						</c:when>
						<c:otherwise>
							<table class='line-box' style="border: 1px solid #000; margin-left: auto; margin-right: auto; border-collapse : collapse">
								<tr height="30px;" style=" border: 1px solid #000;">
									<th rowspan="2" width="30px;">결<br>재 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">팀 장 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">부 장 </th>
									<th style="width: 70px; text-align: center; border: 1px solid #000;">전 무 </th>
								</tr>
								<tr height="80px;"  style="border-bottom: 1px solid #000;">
									<td style="border: 1px solid #000;"></td>
									<td style="border: 1px solid #000;"></td>						
									<td style="border: 1px solid #000;"></td>					
								</tr>
							</table>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="height: 120px; text-align: center; vertical-align: bottom;">
					<h2><span>${login_cp_nm} 대표이사 귀하</span></h2>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div style=" page-break-before:always;" ></div>
<!-- 출력 끝 -->
	<!-- 레이어 팝업 시작 -->	
	<div id="tempPop30100_${typeCode}" style="display: none ;">
		<div class="popup" style="width: 600px;">
			<div class="phead">
				<span id="">결재상신</span>
				<a id="tempCloseX30100_${typeCode}" class="phead-closex">닫기</a>
			</div>
			
			<div class="con">
			<form id="frm30100_${typeCode}">
			<input type="hidden" id="save_type_30100_${typeCode}" name="flag">
			<input type="hidden" id="am_code_30100_${typeCode}" name="am_code">
			<input type="hidden" id="str_nm_30100_${typeCode}" disabled="disabled">
			<table style="width: 100%;">
				<colgroup>
					<col style="width:35%">
					<col style="width:5%">
					<col style="width:60%">
				</colgroup>
				<tr>
					<td>
						<table class="tbl_col tbl_col_white">
							<colgroup>
								<col style="width:40%">
								<col style="width:60%">
							</colgroup>
							<tbody>
								<tr>
									<th colspan="2">사원정보</th>
								</tr>
								<tr>
									<th>이름</th>
									<td class="txt_center">
										<input type="hidden" id="em_no_30100_${typeCode}" name="em_no" style="width: 90px;">
										<span id="em_nm1_30100_${typeCode}"></span>
									</td>
								</tr>
								<tr>
									<th>지점명</th>
									<td class="txt_center">
										<input type="hidden" id="om_code_30100_${typeCode}" name="om_code" style="width: 90px;">
										<span id="om_nm_30100_${typeCode}"></span>
									</td>
								</tr>
								<tr>
									<th>직무</th>
									<td class="txt_center">
										<input type="hidden" id="em_dty_code_30100_${typeCode}" name="em_dty_code" style="width: 90px;">
										<span id="em_dty_nm_30100_${typeCode}"></span>
									</td>
								</tr>
						</table>
					</td>
					<td>
					</td>
					<td>
						<table class="tbl_col tbl_col_white">
							<colgroup>
								<col style="width:50%">
								<col style="width:50%">
							</colgroup>
							<tbody>
								<tr>
									<th>담당</th>
									<th>팀장</th>
								</tr>
								<tr>
									<!--담당-->
									<td rowspan="2" style="line-height: 3.4;">
										<span id="em_nm2_30100_${typeCode}">하하하</span>
									</td>
									<!--팀장-->
									<td rowspan="2">
										<input type="hidden" id="am_approver_em_no_30100_${typeCode}" name="am_approver_em_no" style="width: 90px;">
										<span id="am_approver_em_nm_30100_${typeCode}"></span>
									</td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td>
										<input type="hidden" id="am_approval_date_30100_${typeCode}" name="am_approval_date" style="width: 90px;" >
										<span id="am_approval_date_txt_30100_${typeCode}"></span>
									</td>
									<td>
										<span id="txt_updt_de_30100_${typeCode}"></span>
									</td>
								</tr>
						</table>
					</td>
				</tr>
			</table>
			<div class="margintop"></div>
			<table class="tbl_col tbl_col_white">
				<colgroup>
					<col style="width:30%">
					<col style="width:70%">
				</colgroup>
				<tbody id="rejectHistory30100_${typeCode}">
				</tbody>
			</table>
			<div class="margintop"></div>
			<div id="tblWrite_30100_${typeCode}" style="display: none ;">
				<table class="tbl_col tbl_col_white">
					<colgroup>
						<col style="width:30%">
						<col style="width:70%">
					</colgroup>
					<tbody>
						<tr>
							<th colspan="2">결재 내용</th>
						</tr>
						<tr>
							<th>결재유형</th>
							<td class="txt_left"><select id="ad_type_combo_30100_${typeCode}" name ="ad_type" style="width: 100px; margin-right: 6px;">
								</select> 
							</td>
						</tr>
						<tr id="tr1_30100_${typeCode}">
							<th>근태일자</th>
							<td class="txt_left"><input type="text" class="input-basic" id="dateFrom_popup30100_${typeCode}" size="10" style="width: 90px;" readonly="readonly">
								<span class="wave"></span>
								<input type="text" class="input-basic" id="dateTo_popup30100_${typeCode}" size="10" style="width: 90px;" readonly="readonly">
								<span id="txtDays30100_${typeCode}">(1일)</span>
							</td>
						</tr>
						<tr id="tr2_30100_${typeCode}">
							<th>근태일자</th>
							<td class="txt_left">
								<input type="text" class="input-basic" id="date_popup30100_${typeCode}" size="10" style="width: 90px; margin-right: 6px" readonly="readonly">
								<select id="hhFrom_popup30100_${typeCode}" style="width: 50px;">
									<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option>
									<option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option>
									<option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option>
									<option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option>
									<option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
								</select>		
								:						
								<select id="mmFrom_popup30100_${typeCode}" style="width: 50px;">
									<option value="00">00</option><option value="05">05</option>
									<option value="10">10</option><option value="15">15</option>
									<option value="20">20</option><option value="25">25</option>
									<option value="30">30</option><option value="35">35</option>
									<option value="40">40</option><option value="45">45</option>
									<option value="50">50</option><option value="55">55</option>
								</select>
								<span class="wave"></span>
								<select id="hhTo_popup30100_${typeCode}" style="width: 50px;">
									<option value="00">00</option><option value="01">01</option><option value="02">02</option><option value="03">03</option><option value="04">04</option>
									<option value="05">05</option><option value="06">06</option><option value="07">07</option><option value="08">08</option><option value="09">09</option>
									<option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option>
									<option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option>
									<option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option>
								</select>	
								:							
								<select id="mmTo_popup30100_${typeCode}" style="width: 50px;">
									<option value="00">00</option><option value="05">05</option>
									<option value="10">10</option><option value="15">15</option>
									<option value="20">20</option><option value="25">25</option>
									<option value="30">30</option><option value="35">35</option>
									<option value="40">40</option><option value="45">45</option>
									<option value="50">50</option><option value="55">55</option>
								</select>
							</td>
						</tr>
						<tr>
							<th >사유</th>
							<td><textarea id="ad_reason_30100_${typeCode}" name="ad_reason" cols="60" rows="4" maxlength="300"></textarea></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td style="padding: 0;" >
								<input type="hidden" id="am_no_30100_${typeCode}" value=""  >
								<div id="approvalFile_30100_${typeCode}"></div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			</form>
			<div id="tblRead_30100_${typeCode}" style="display:  ;">
				<!-- <table class="tbl_col tbl_col_white">
					<colgroup>
						<col style="width:30%">
						<col style="width:70%">
					</colgroup>
					<tbody id="rejectHistory30100_${typeCode}">
					</tbody>
				</table>
				<div class="margintop"></div> -->
				<table class="tbl_col tbl_col_white">
					<colgroup>
						<col style="width:30%">
						<col style="width:70%">
					</colgroup>
					<tbody>
						<tr>
							<th colspan="2">결재 내용</th>
						</tr>
						<tr>
							<th>결재유형</th>
							<td class="txt_left">
								<span id="txt_ad_type_combo_30100_${typeCode}"></span>
							</td>
						</tr>
						<tr>
							<th>근태일자</th>
							<td class="txt_left">
								<span id="txt_date_popup30100_${typeCode}"></span>
							</td>
						</tr>
						<tr>
							<th>사유</th>
							<td class="txt_left"  style="height: 100px; vertical-align: top;"><span id="txt_ad_reason_30100_${typeCode}"></span>  
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3" class="attach-view" >
							<div class="tx-attach-div  auth-down">
								<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">
									<div class="tx-attach-box-inner">
										<ul id="fileViewList_30100_${typeCode}" class="tx-attach-list">
										</ul>
									</div>
								</div>
							</div>
						</tr>
					</tbody>
				</table>
			</div>
			</div>
			
			<div class="pfooter">
				<button type="button" class="white" id="tempPrint30100_${typeCode}" style="display: none;">
					출력
				</button>
				<button type="button" class="skyblue auth-insert auth-update" id="tempSave30100_${typeCode}">
					상신
				</button>
				<button type="button" class="red auth-del" id="tempDelete30100_${typeCode}" style="display: none;">
					삭제
				</button>
				<button type="button" class="white" id="tempClose30100_${typeCode}">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1 id="mainTitle30100_${typeCode}">결재상신</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa">
				</span>전자결재<span class="clamp fa">
				</span>결재상신함<span class="clamp fa"></span>결재상신
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">
				<select class="select-basic" id="cboType30100_${typeCode}" style="width:80px">
					<option value="A" selected="selected">근태일</option>
					<option value="B">기안일</option>
				</select>
				<input type="text" class="input-basic" id="dateFrom_search30100_${typeCode}" size="10" style="width: 90px;" placeholder="" readonly="readonly">
				<span class="wave"></span>
				<input type="text" class="input-basic" id="dateTo_search30100_${typeCode}" size="10" style="width: 90px;" placeholder="" readonly="readonly">
			</div>
			<div class="btn_gup clear">
				<button type="button" class="red" id="btnSearch30100_${typeCode}" >
					조회
				</button>
				<button type="button" class="navy" id="btnClear30100_${typeCode}" >
					초기화
				</button>
				<button type="button" class="skyblue auth-insert" id="tempSend30100_${typeCode}" >결재상신</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="유형별 자산목록리스트" class="tbl_col" id="tbl_30100_${typeCode}">
			<caption>
				결재상신목록
			</caption>
			<colgroup>
				<col span="9">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No.</th>
					<th scope="col">관리번호</th>
					<th scope="col">지점명</th>
					<th scope="col">사원명</th>
					<th scope="col">결재유형</th>
					<th scope="col">근태일자</th>
					<th scope="col">첨부</th>
					<th scope="col">기안일자</th>
					<th scope="col">결재상태</th>
				</tr>
			</thead>
			<tbody id='tbody30100_${typeCode}'>
			</tbody>
		</table>
	<!-- //UI Object -->
	</section>
	<!-- <section id="approvalList30100_${typeCode}"/> -->
	<section id="approvalNavi30100_${typeCode}" class="paginate"/>
</article>
<div style=" page-break-after:always;" ></div>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
