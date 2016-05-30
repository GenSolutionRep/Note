<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <script type="text/javascript" src="<%=jsPath%>/routine/30/30-400.js"></script> --%>
<jsp:include page="../inc/auth.jsp" flush='true' />
<%@ include file="30-400js.jsp"%>
</head>
<body>
<article class="content">
<!-- 출력 시작 -->
<div id="printArea30400_${typeCode}" style="display: none;">
	<table class="" style="border: none; text-align: center; font-family: Gothic; width: 100%; margin-top: 30px;">
		<colgroup>
			<col style="width:30%">
			<col style="width:70%">
		</colgroup>
		<tbody>
			<tr>
				<th colspan="2" style="height: 50px; text-align: center;">
					<div ><u><h1>허&nbsp;&nbsp;가&nbsp;&nbsp;원</h1></u></div>
					<div style=""><h3><span id="aTitle1_30400_${typeCode}"></span></h3></div>
				</th>
			</tr>
			<tr>
				<th colspan="2" style="height: 80px; text-align: center; vertical-align: middle;">
					아래와 같이 <span id="aTitle2_30400_${typeCode}"></span>(을/를) 신청하오니 재가하여 주시기 바랍니다.
				</th>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; " >1. 성&nbsp;&nbsp;&nbsp;명 :</th>
				<td style="text-align: left;">
					<span id="aName_30400_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">2. 근무지 :</th>
				<td style="text-align: left;">
					<span id="aPlace_30400_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">3. 사&nbsp;&nbsp;&nbsp;유  :</th>
				<td style="text-align: left;">
					<span id="aReason_30400_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">4. 기&nbsp;&nbsp;&nbsp;간  :</th>
				<td style="text-align: left;">
					<span id="aPeriod_30400_${typeCode}"></span>
				</td>
			</tr>
			<tr>
				<th style="height: 60px; text-align: center; ">5. 대리인 : </th>
				<td style="text-align: left;">
					<%-- <span id="aRepresentative_30400_${typeCode}"></span> --%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(인)
				</td>
			</tr>
			<tr>
				<th colspan="2" style="height: 90px; text-align: center; vertical-align: bottom;">
					<span id="adate_30400_${typeCode}">2015. 05. 01.</span>
				</th>
			</tr>
			<tr>
				<th colspan="2" style="height: 80px; text-align: center; vertical-align: middle;">
					신청자 : &nbsp;&nbsp;&nbsp;<span id="aApplicant_30400_${typeCode}"></span>&nbsp;&nbsp;&nbsp;(인)
				</th>
			</tr>
			<tr style="height: 120px;">
				<td colspan="2" style="text-align: center;">
					<c:choose>
						<c:when test="${login_cp_cd==001}">
							<table class='line-box' style="border: 1px solid #000; margin-left: auto; margin-right: auto; border-collapse : collapse">
								<tr height="30px;" style=" border: 1px solid #000;">
									<th rowspan="2" width="30px;">결<br><br>재 </th>
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
	<!-- 반려사유 팝업 시작 -->
	<div id="tempReasonPop30400_${typeCode}" class="pop-apn-pop"  style="display: none ; z-index: 8888;">
		<div class="popup" style="width: 400px;">
			<div class="phead">
				<span id="">결재반려 사유</span>
			</div>
			<div class="con">
				<div class="tbl_title" >
					<span id=''>결재반려 사유</span>
				</div>
				<textarea id="ar_reason_30400_${typeCode}" name="ar_reason"  rows="6" maxlength="300"></textarea>
			</div>
			<div class="pfooter">
				<button type="button" class="red auth-insert auth-update" id="tempReject30400_${typeCode}">
					반려
				</button>
				<button type="button" class="white" id="tempReasonClose30400_${typeCode}">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 반려사유 팝업 종료 -->
	
	<!-- 레이어 팝업 시작 -->
	
	<div id="tempPop30400_${typeCode}" style="display: none ;  z-index: 7777;">
		<div class="popup" style="width: 600px;">
			<div class="phead">
				<span id="">결재수신</span>
				<a id="tempCloseX30400_${typeCode}" class="phead-closex">닫기</a>
			</div>
			
			<div class="con">
			<form id="frm30400_${typeCode}">
			<input type="hidden" id="save_type_30400_${typeCode}" name="flag">
			<input type="hidden" id="am_code_30400_${typeCode}" name="am_code">
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
										<input type="hidden" id="em_no_30400_${typeCode}" name="em_no" style="width: 90px;">
										<span id="em_nm1_30400_${typeCode}"></span>
									</td>
								</tr>
								<tr>
									<th>지점명</th>
									<td class="txt_center">
										<input type="hidden" id="om_code_30400_${typeCode}" name="om_code" style="width: 90px;">
										<span id="om_nm_30400_${typeCode}"></span>
									</td>
								</tr>
								<tr>
									<th>직무</th>
									<td class="txt_center">
										<input type="hidden" id="em_dty_code_30400_${typeCode}" name="em_dty_code" style="width: 90px;">
										<span id="em_dty_nm_30400_${typeCode}"></span>
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
										<span id="em_nm2_30400_${typeCode}">하하하</span>
									</td>
									<!--팀장-->
									<td rowspan="2">
										<input type="hidden" id="am_approver_em_no_30400_${typeCode}" name="am_approver_em_no" style="width: 90px;">
										<span id="am_approver_em_nm_30400_${typeCode}"></span>
									</td>
								</tr>
								<tr>
								</tr>
								<tr>
									<td>
										<input type="hidden" id="am_approval_date_30400_${typeCode}" name="am_approval_date" style="width: 90px;" >
										<span id="am_approval_date_txt_30400_${typeCode}"></span>
									</td>
									<td>
										<span id="txt_updt_de_30400_${typeCode}"></span>
									</td>
								</tr>
						</table>
					</td>
				</tr>
			</table>
			<div class="margintop"></div>
			</form>
			<div id="tblRead_30400_${typeCode}" style="display:  ;">
				<table class="tbl_col tbl_col_white">
					<colgroup>
						<col style="width:30%">
						<col style="width:70%">
					</colgroup>
					<tbody id="rejectHistory30400_${typeCode}">
					</tbody>
				</table>
				<div class="margintop"></div>
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
								<span id="txt_ad_type_combo_30400_${typeCode}"></span>
							</td>
						</tr>
						<tr>
							<th>근태일자</th>
							<td class="txt_left">
								<span id="txt_date_popup30400_${typeCode}"></span>
							</td>
						</tr>
						<tr>
							<th>사유</th>
							<td class="txt_left"  style="height: 100px; vertical-align: top;"><span id="txt_ad_reason_30400_${typeCode}"></span>  
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3" class="attach-view" >
							<div class="tx-attach-div auth-down">
								<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">
									<div class="tx-attach-box-inner">
										<ul id="fileViewList_30400_${typeCode}" class="tx-attach-list">
										</ul>
									</div>
								</div>
							</div>
						</tr>
						<!-- <tr>
							<th>반려사유</th>
							<td class="txt_left"  style="height: 100px; vertical-align: top;"><span id="txt_ar_reason_30400_${typeCode}"></span>  
							</td>
						</tr> -->
					</tbody>
				</table>
			</div>
			</div>
			<div class="pfooter">
				<button type="button" class="white" id="tempPrint30400_${typeCode}" style="display: none;">
					출력
				</button>
				<button type="button" class="skyblue auth-insert auth-update" id="tempApproval30400_${typeCode}">
					결재
				</button>
				<button type="button" class="red auth-insert auth-update" id="tempReason30400_${typeCode}">
					반려
				</button>
				<button type="button" class="red auth-insert auth-update" id="tempDelete30400_${typeCode}">
					삭제
				</button>
				<button type="button" class="white" id="tempClose30400_${typeCode}">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1 id="mainTitle30400_${typeCode}">결재대기</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa">
				</span>전자결재<span class="clamp fa">
				</span>결재수신함<span class="clamp fa"></span>결재대기
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
			<div class="input_group clear">
				<select class="select-basic" id="cboType30400_${typeCode}" style="width:80px">
					<option value="A" selected="selected">근태일</option>
					<option value="B">기안일</option>
				</select>
				<input type="text" class="input-basic" id="dateFrom_search30400_${typeCode}" size="10" style="width: 90px;" placeholder="" readonly="readonly">
				<span class="wave"></span>
				<input type="text" class="input-basic" id="dateTo_search30400_${typeCode}" size="10" style="width: 90px;" placeholder="" readonly="readonly">
				
				<span style="margin-left: 15px">
					<strong>지점명</strong>
					<select class="select-basic" id="cbo_om_code30400_${typeCode}" style="width:100px">
					</select>
				</span>
				<span style="margin-left: 15px">
					<strong>상신자</strong>
					<input type="text" class="input-basic" id="em_nm_search30400_${typeCode}" size="10" style="width: 90px;">
				</span>
			</div>
			<div class="btn_gup clear">
				<button type="button" class="red" id="btnSearch30400_${typeCode}" >
					조회
				</button>
				<button type="button" class="navy" id="btnClear30400_${typeCode}" >
					초기화
				</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="결재수신목록" class="tbl_col" id="tbl_30400_${typeCode}">
			<caption>
				결재수신목록
			</caption>
			<colgroup>
				<col span="9">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
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
			<tbody id='tbody30400_${typeCode}'>
			</tbody>
		</table>
	<!-- //UI Object -->
	</section>
	<!-- <section id="approvalList30400_${typeCode}"/> -->
	<section id="approvalNavi30400_${typeCode}" class="paginate"/>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
