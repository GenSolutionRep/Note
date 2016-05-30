<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>

<!DOCTYPE html> 
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<%@ include file="50-100js.jsp"%>
<style type="text/css">
#navi50100${typeCode} > ul {
    margin: 0;
    margin-bottom: 18px;
}
</style>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>매장관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa">
				</span>매장관리<span class="clamp fa" id="selectedBanchName"></span>
				<button type='button' class="white" id="btnJuso_${typeCode}" style="display: none;" >주소 위도경도 처리</button>
				<h1 id='proc_${typeCode}'></h1>
			</p>
		</div>
	</section>
	<!-- 
	<section>
		<div class="searchbox">
			<div class="input_gup">
				<select style="width:108px">
					<option value="">근태일</option><option value="">기안일</option>
				</select>
				<input type="text" class="input_databox" size="10" placeholder="날짜 입력">
				<span class="wave"></span>
				<input type="text" class="input_databox" size="10" placeholder="날짜 입력">
			</div>
			<div class="btn_gup">
				<button class="red" id="tempSearch99100" >조회</button>
				<button class="skyblue" id="tempInsert99100" >등록</button>
			</div>
		</div>
	</section>
 	-->
	<section>
		<div class="divide_3">
			<ul>
				<li>
					<div class="searchbox">
						<h4 id=hTitle${typeCode}1>고객그룹</h4> 
						<div class="btn_gup">
							<button class="red" id="tempInsert${typeCode}1" >등록</button>
						</div>
					</div>
				</li>
				<li>
					<div class="searchbox">
						<h4 id="hTitle${typeCode}2">관리업체</h4> 
						<div class="btn_gup">
							<button class="red" id="tempInsert${typeCode}2" >등록</button>
						</div>
					</div>
				</li>
				<li>
					<div class="searchbox">
						<h4 id="hTitle${typeCode}3">매장</h4> 
						<div class="btn_gup">
							<button class="skyblue" id="tempSearch${typeCode}3" >전체목록</button>
							<button class="red" id="tempInsert${typeCode}3" >등록</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="divide_3">
			<ul>
				<!-- 고객그룹리스트 -->
				<li>
					<div class="divide_3_thead" id="table${typeCode}1">
						<div class="flex1">코드</div>						
						<div class="flex2">고객그룹명</div>	
						<div class="flex1">업체수</div>
						<div class="flex1">사용여부</div>			
						<div class="flex_100">관리</div>	
						<div class="flex_17"></div>	
					</div>
					<div class="divide_3_tbody" style="height:495px" id="tbody${typeCode}1">
					</div>
				</li>
				<!-- 관리업체리스트 -->
				<li>
					<div class="divide_3_thead" id="table${typeCode}2" summary="관리업체리스트">
						<div class="flex1">코드</div>	
						<div class="flex2">관리업체명</div>	
						<div class="flex1">매장수</div>	
						<div class="flex1">사용여부</div>	
						<div class="flex_100">관리</div>	
						<div class="flex_17"></div>	
					</div>
					<div class="divide_3_tbody" style="height:495px" id="tbody${typeCode}2">
					</div>
				</li>
				<!-- 매장리스트 -->
				<li>
					<div class="divide_3_thead" id="table${typeCode}3" summary="매장리스트">
						<div class="flex1">코드</div>	
						<div class="flex2">매장명</div>	
						<div class="flex1">상태</div>	
						<div class="flex_100">관리</div>	
						<div class="flex_17"></div>	
					</div>
					<div class="divide_3_tbody" style="height:495px" id="tbody${typeCode}3">
					</div>
				</li>
			</ul>
		</div>
	</section>
	
	<!-- 
	<section>
	<table style="width: 100%; vertical-align: top; ">
	<colgroup>
		<col style="width: 30%;" >
		<col style="width: 30%;" >
		<col style="width: 30%;" >
	</colgroup>
	<tr>
		<td style=" vertical-align: top; padding-right: 16px;">
			
		</td>
		<td style=" vertical-align: top;  padding-right: 16px;">
			<section>
			
			</section>
			<table id="table${typeCode}2" summary="관리업체리스트" class="tbl_col">
				<caption>관리업체리스트</caption>
					<colgroup>
						<col style="width: 20%;" >
						<col style="width: 40%;" >
						<col style="width: 20%;" >
						<col style="width: 20%;" >
					</colgroup>
				<thead>
					<tr>
						<th scope="col">코드</th>
						<th scope="col">관리업체명</th>
						<th scope="col">매장수</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody id="tbody${typeCode}2">
				</tbody>
			</table>
		</td>
		<td style=" vertical-align: top;  padding-right: 16px;">
			<section>
			
			</section>
			<table id="table${typeCode}3" summary="매장리스트" class="tbl_col">
				<caption>매장리스트</caption>
					<colgroup>
						<col style="width: 20%;" >
						<col style="width: 40%;" >
						<col style="width: 20%;" >
						<col style="width: 20%;" >
					</colgroup>
				<thead>
					<tr>
						<th scope="col">코드</th>
						<th scope="col">매장명</th>
						<th scope="col">상태</th>
						<th scope="col">관리</th>
					</tr>
				</thead>
				<tbody id="tbody${typeCode}3">
				</tbody>
			</table>
		</td>
	</tr>
	</table>
	</section>
 	-->
	<div id="companyPopSpace" >
		<!-- 고객그룹 -->
		<div id="tempPop${typeCode}1" style="display:none  ;">
			<div class="popup"  style="width: 600px;" >
			
					<div class="phead">
						<span id="title1_${typeCode}1">고객그룹 등록</span>
						<a id="tempCloseX${typeCode}1" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<div class="tbl_title" >
							<span id="title2_${typeCode}1">고객그룹 등록</span>
						</div>
						<input type="hidden" name="save_type_${typeCode}1" id="save_type_${typeCode}1"/>
						<form id="frm${typeCode}1">
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="85%">
							<col>
							</colgroup>
							<tr>
								<th>코드</th>
								<td>
									<input class="input-basic"id="cg_code_${typeCode}1" name="cg_code" type="text" class="input-basic" value="" maxlength="5" placeholder="" readonly="readonly" >
									&nbsp;
									<div class="inputCheck">
										<input id="chkAutoYn_${typeCode}1" name="chkAutoYn_${typeCode}1"  type="checkbox" checked="checked">
										<label for="chkAutoYn_${typeCode}1"></label>
									</div>코드 자동 등록
									<input type="hidden" name="autoYn" id="autoYn_${typeCode}1" value="Y"/>
								</td>
							</tr>
							<tr>
								<th>고객그룹명</th>
								<td>
									<input id="cg_nm_${typeCode}1" name="cg_nm" type="text" placeholder="" maxlength="50" value="" >
								</td>
							</tr>
							<tr>
								<th>사용여부</th>
								<td>
									<div class="inputRadio">
										<input type="radio" name="use_at" id="use_at_y_${typeCode}1" value="Y" checked='checked'>
										<label for="use_at_y_${typeCode}1"></label>
									</div>사용
									<div class="inputRadio">
										<input type="radio" name="use_at" id="use_at_n_${typeCode}1" value="N">
										<label for="use_at_n_${typeCode}1"></label>
									</div>미사용
								</td>
							</tr>
							<tr>
								<th>메모</th>
								<td>
									<textarea id="cg_memo_${typeCode}1" name="cg_memo" cols="60" rows="6" maxlength="250">
									</textarea>
								</td>
							</tr>
						</table>
						</form>
						</div>
					<div class="pfooter">
		       			<button type='button' class="red" id="tempSave${typeCode}1">저장</button>
		       			<button type='button' class="white" id="tempClose${typeCode}1">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
		</div>
		<!-- 관리업체 -->
		<div id="tempPop${typeCode}2" style="display:none  ;">
			<div class="popup"  style="width: 600px;" >
			
					<div class="phead">
						<span id="title1_${typeCode}2">관리업체 등록</span>
						<a id="tempCloseX${typeCode}2" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<div class="tbl_title" >
							<span id="title2_${typeCode}2">관리업체 등록</span>
						</div>
						<input type="hidden" name="save_type_${typeCode}2" id="save_type_${typeCode}2"/>
						<form id="frm${typeCode}2">
						<input type="hidden" name="cg_code" id="cg_code_${typeCode}2"/>
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="85%">
							<col>
							</colgroup>
							<tr>
								<th>코드</th>
								<td>
									<input id="me_code_${typeCode}2" name="me_code" class="input-basic" type="text" value="" maxlength="5" placeholder="" readonly="readonly" >
									&nbsp;
									<div class="inputCheck">
										<input id="chkAutoYn_${typeCode}2" name="chkAutoYn_${typeCode}2"  type="checkbox" checked="checked">
										<label for="chkAutoYn_${typeCode}2"></label>
									</div>코드 자동 등록
									
									<input type="hidden" name="autoYn" id="autoYn_${typeCode}2" value="Y"/>
								</td>
							</tr>
							<tr>
								<th>고객그룹명</th>
								<td>
									<input id="me_nm_${typeCode}2" name="me_nm" type="text" placeholder="" maxlength="50" value="" >
								</td>
							</tr>
							<tr>
								<th>사용여부</th>
								<td>
									<div class="inputRadio">
										<input type="radio" name="use_at" id="use_at_y_${typeCode}2" value="Y" checked='checked'>
										<label for="use_at_y_${typeCode}2"></label>
									</div>사용
									<div class="inputRadio">
										<input type="radio" name="use_at" id="use_at_n_${typeCode}2" value="N">
										<label for="use_at_n_${typeCode}2"></label>
									</div>미사용
								</td>
							</tr>
							<tr>
								<th>메모</th>
								<td>
									<textarea id="cm_memo_${typeCode}2" name="me_memo" cols="60" rows="6" maxlength="250">
									</textarea>
								</td>
							</tr>
						</table>
						</form>
						</div>
					<div class="pfooter">
		       			<button type='button' class="red" id="tempSave${typeCode}2">저장</button>
		       			<button type='button' class="white" id="tempClose${typeCode}2">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
		</div>
		<!-- 레이어 팝업 시작 -->
 		<!-- <div id="zipcdPop${typeCode}3" style="display: none ;">
			<div class="popup" style="width: 600px;">
				<div class="section"  >						
					<h1>주소검색<a id="zipcodePopCloseBtn${typeCode}3" class="title-closex drag-not" >닫기</a></h1>
					<div class="drag-not">
						<div class="comp_area zip-box" >
							<div  id="addrSearchControl${typeCode}3"  ></div>
						</div>
						<div class="fixdCol-warp green-check" >
							<div id="addrSearch${typeCode}3" ></div>
						</div>
					</div>
				</div>
			</div>
			<div class="popup_overlay"></div>
		</div> -->
		<!-- 매장관리 -->
		<div id="zipcdPop${typeCode}3" class="pop-apn-pop" style="display: none;"> 
			<div class="popup"   style="width: 600px; z-index: 1002" >
				<div class="phead">
					<span>주소검색</span>
					<a id="zipcodePopCloseBtn${typeCode}3" class="phead-closex" >닫기</a>
				</div>
				<div class="con"  >						
					<div class="drag-not">
						<div class="comp_area zip-box" >
							<div  id="addrSearchControl${typeCode}3"  ></div>
						</div>
						<div class="fixdCol-warp green-check" >
							<div id="addrSearch${typeCode}3" ></div>
						</div>
					</div>
				</div>
			</div>
			<div class="popup_overlay"></div>
		</div>
		<div id="tempPop${typeCode}3" class="pop-apn-pop" style="display:none;">
			<div class="popup"  style="width: 700px;" >
			
					<div class="phead">
						<span id="title1_${typeCode}3">매장등록</span>
						<a id="tempCloseX${typeCode}3" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<div class="tbl_title" >
							<span id='title2_${typeCode}3'>매장등록</span>
						</div>
						<input type="hidden" name="save_type_${typeCode}3" id="save_type_${typeCode}3"/>
						<form id="frm${typeCode}3">
						<input type="hidden" id="sm_code_${typeCode}3" name="sm_code" maxlength="10" value="" >
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="33%">
								<col width="15%">
								<col width="37%">
							<col>
							</colgroup>
							<tr>
								<th>관리지점</th>
								<td>
									<select id="om_code_combo_${typeCode}3" name ="om_code" style="margin-right: 6px;">
									</select> 
									<!-- <input id="om_code_${typeCode}3" name="om_code" type="hidden" value=""/> -->
								</td>
								<th>고객그룹명</th>
								<td>
									<select id="cg_code_combo_${typeCode}3" name ="cg_code" style="margin-right: 6px;">
									</select> 
									<!-- <input id="cg_code_${typeCode}3" name="cg_code" type="hidden" value=""/> -->
								</td>
							</tr>
							<tr>
								<th>관리업체명</th>
								<td>
									<select id="me_code_combo_${typeCode}3" name ="me_code" style="margin-right: 6px;">
									</select> 
									<!-- <input id="me_code_${typeCode}3" name="me_code" type="hidden" value=""/> -->
								</td>
								<th>매장명</th>
								<td>
									<div class='input_group'>
										<span>
											<input id="sm_nm_${typeCode}3" name="sm_nm" type="text" placeholder="" maxlength="50" value="" style="width: 150px">
											<div class="inputCheck">
												<input id="chk_sm_oper_at_${typeCode}3" name="chk_sm_oper_at_${typeCode}3"  type="checkbox" >
												<label for="chk_sm_oper_at_${typeCode}3"></label>
											</div>폐쇄
										</span>
									</div>
									<input id="sm_oper_at_${typeCode}3" name="sm_oper_at" type="hidden" value="Y"/>
								</td>
							</tr>
							<tr>
								<th>담당사원 유형</th>
								<td>
									<div class="inputRadio">
										<input type="radio" name = "se_empl_ty" id="rdo_se_empl_ty_y_${typeCode}3" value="0000000006" checked='checked'>
										<label for="rdo_se_empl_ty_y_${typeCode}3"></label>
									</div>고정	
									<div class="inputRadio">
										<input type="radio" name = "se_empl_ty" id="rdo_se_empl_ty_n_${typeCode}3" value="0000000007">
										<label for="rdo_se_empl_ty_n_${typeCode}3"></label>
									</div>순회	
								</td>
								<th>차수</th>
								<td>
									<select id="sm_odr_${typeCode}3" name ="sm_odr" style="margin-right: 6px; width: 70px;">
									</select> 
								</td>
							</tr>
							<tr>
								<th>담당사원</th>
								<td>
									<div class="input_group clear">
										<span>
											<input id="em_nm_${typeCode}3" name="em_nm" type="text" placeholder="" maxlength="50" value="" style="width: 100px" >
										</span>
										<div id="selectItem_em_nm_${typeCode}3" class="select-item"></div>
										<input type="hidden" id="em_no_${typeCode}3" name="em_no" value=""/>
									</div>
								</td>
								<th>사업자번호</th>
								<td>
									<input id="sm_bizrno_${typeCode}3" name="sm_bizrno" type="text" maxlength="10" value="" >
								</td>
							</tr>
							<tr>
								<th>보조사원</th>
								<td>
									<div class="input_group clear">
										<span>
											<input id="em_nm_sub_${typeCode}3" name="em_nm_sub" type="text" placeholder="" maxlength="50" value="" style="width: 100px" >
										</span>
										<div id="selectItem_em_nm_sub_${typeCode}3" class="select-item"></div>
										<input type="hidden" id="em_no_sub_${typeCode}3" name="em_no_sub" value=""/>
									</div>
								</td>
								<th>매장코드</th>
								<td>
									<input id="sm_sap_code_${typeCode}3" name="sm_sap_code" type="text" maxlength="6" value="" style="width: 70px;" >
								</td>
							</tr>
							<tr style="display: none;">
								<th>상호</th>
								<td>
									<input id="sm_cmpnm_${typeCode}3" name="sm_cmpnm" type="text" maxlength="30" value="" >
								</td>
							</tr>
							<tr>
								
								<th>CVS Cafe</th>
								<td>
									<div class="inputCheck">
										<input id="chk_sm_cvscafe_at_${typeCode}3" name="chk_sm_cvscafe_at_${typeCode}3" type="checkbox" value="None">
										<label for="chk_sm_cvscafe_at_${typeCode}3"></label>
									</div>CVS Cafe
									<input id="sm_cvscafe_at_${typeCode}3" name="sm_cvscafe_at" type="hidden" value="" >
								</td>
								<th>대표자명</th>
								<td>
									<input id="sm_rprsntv_nm_${typeCode}3" name="sm_rprsntv_nm" class="input-basic" type="text" maxlength="5" value="" >
								</td>
							</tr>
							<tr>
								<th>주소</th>
								<td class="txt_left" colspan=3>
									<!-- <select id="sm_area_${typeCode}3" name ="sm_area" style="margin-right: 6px;">
									</select>  -->
									<input id="sm_zipcd_${typeCode}3" name="sm_zipcd" class="input-basic" type="text" maxlength="5" value="" readonly="readonly" style="width: 100px;">
									<button type="button" id="addrSearchPopBtn${typeCode}3" class="white">주소검색</button>
									<input id="sm_adres_${typeCode}3" name="sm_adres" class="table_row_input" type="text" maxlength="100" value="" >
									<input id="sm_etcadres_${typeCode}3" name="sm_etcadres" class="table_row_input" type="text" maxlength="100" value="" >
									<input id="sm_dtadres_${typeCode}3" name="sm_dtadres" class="table_row_input" type="text" maxlength="100" value="" >
								</td>
							</tr>
							<tr>
								<th>위치좌표</th>
								<td colspan=3>
									<span>위도</span><input id="sm_la_${typeCode}3" name="sm_la" type="text" maxlength="30" value="" style="width: 145px;">
									&nbsp;&nbsp;
									<span>경도</span><input id="sm_lo_${typeCode}3" name="sm_lo" type="text" maxlength="30" value="" style="width: 145px;">
									<button type="button" id="getCoordinateBtn${typeCode}3" class="white" style="margin-left: 10px;">주소로 좌표 알아오기</button>
								</td>
							</tr>
							<tr>
								<th>권역</th>
								<td colspan=3>
									<div class="btn_gup">													
										<select id="sm_area1_combo_${typeCode}3" name ="sm_area1" style="margin-right: 6px;" class='input-basic'>
										</select> 
										<select id="sm_area2_combo_${typeCode}3" name ="sm_area2" style="margin-right: 6px;" class='input-basic'>
										</select> 
									</div>
								</td>
							</tr>
							<tr style="display: none;">
								<th>웹사이트</th>
								<td colspan=3>
									<select id="sm_site_se_${typeCode}3" name ="sm_site_se" style="margin-right: 0px; width: 70px;">
										<option value='1' selected="selected">http</option>
										<option value='2'>https</option>
									</select> 
									<span>://</span><input id="sm_site_${typeCode}3" name="sm_site" class="input-basic" type="text" maxlength="50" value="" style="width: 300px;">
								</td>
							</tr>
							<tr>
								<th>대표전화</th>
								<td colspan=3>
		 							<input id="sm_tlphon_${typeCode}3" name="sm_tlphon" class='input-basic' type="text" maxlength="20" value="" >
								</td>
							</tr>
							<tr>
								<th>팩스번호</th>
								<td colspan=3>
									<input id="sm_fxnum_${typeCode}3" name="sm_fxnum" class='input-basic'  type="text" maxlength="20" value="" >
								</td>
							</tr>
							<tr>
								<th>메모</th>
								<td colspan=3>
									<textarea id="sm_memo_${typeCode}3" name="sm_memo" cols="60" rows="3" maxlength="300"></textarea>
								</td>
							</tr>
						</table>
						</form>
						</div>
					<div class="pfooter">
		       			<button type='button' class="red" id="tempSave${typeCode}3">저장</button>
		       			<button type='button' class="white" id="tempClose${typeCode}3">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
		</div>
		<!-- 레이어 팝업 시작 -->
<!-- 		<div id="zipcdPop${typeCode}3" style="display: none ;"> -->
<!-- 			<div class="popup" style="width: 600px;"> -->
<!-- 				<div class="section"  >						 -->
<!-- 					<h1>주소검색<a id="zipcodePopCloseBtn${typeCode}3" class="title-closex drag-not" >닫기</a></h1> -->
<!-- 					<div class="drag-not"> -->
<!-- 						<div class="comp_area zip-box" > -->
<!-- 							<div  id="addrSearchControl${typeCode}3"  ></div> -->
<!-- 						</div> -->
<!-- 						<div class="fixdCol-warp green-check" > -->
<!-- 							<div id="addrSearch${typeCode}3" ></div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="popup_overlay"></div> -->
<!-- 		</div> -->
			
	</div>
	<!-- 매장현황 -->
		<div id="tempPop${typeCode}4" style="display:none  ;">
			<div class="popup"  style="width: 1300px;" >
			
					<div class="phead">
						<span id="title1_${typeCode}4">매장현황</span>
						<a id="tempCloseX${typeCode}4" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<%-- <div class="tbl_title" >
							<span id="title2_${typeCode}4">매장현황</span>
						</div> --%>
						<div class="searchbox">
							<div class="input_group clear">								
								<span style="margin-left: 15px">
									<strong>고객그룹</strong>
									<input type="text" class="input-basic" id="cg_nm_search_${typeCode}4" size="20" style="width: 90px;" >
								</span>
								<span style="margin-left: 15px">
									<strong>관리업체</strong>
									<input type="text" class="input-basic" id="me_nm_search_${typeCode}4" size="20" style="width: 90px;" >
								</span>
								<span style="margin-left: 15px">
									<strong>매장명</strong>
									<input type="text" class="input-basic" id="sm_nm_search_${typeCode}4" size="20" style="width: 90px;" >
								</span>
								<span style="margin-left: 15px">
									<strong>사원명</strong>
									<input type="text" class="input-basic" id="em_nm_search_${typeCode}4" size="20" style="width: 90px;" >
								</span>
								<span style="margin-left: 15px">
									<strong>관리유형</strong>
									<select class="select-basic" id="se_empl_ty_search_${typeCode}4" style="width:80px">
										<option value=""  selected="selected">선택</option>
										<option value="0000000006">고정</option>
										<option value="0000000007">순회</option>
									</select>
								</span>
								<span style="margin-left: 15px">
									<strong>차수</strong>
									<select class="select-basic" id="sm_odr_search_${typeCode}4" style="width:80px">
									</select>
								</span>
								<div class="inputCheck" style="margin-left: 15px">
									<input id="chk_search_sm_oper_at_${typeCode}4" name="chk_search_sm_oper_at_${typeCode}4"  type="checkbox" data-value="" >
									<label for="chk_search_sm_oper_at_${typeCode}4"></label>
								</div>cvs
							</div>
							<div class="btn_gup clear">
								<button type="button" class="red" id="btnSearch_${typeCode}4" >
									조회
								</button>
								<button type="button" class="navy" id="btnClear_${typeCode}4" >
									초기화
								</button>
								<button type="button" class="skyblue auth-down" id="btnExcel_${typeCode}4">
									<i class="fa fa-file-excel-o fa-lg"></i> 엑셀
								</button>
							</div>
						</div>
						
						
						<input type="hidden" name="save_type_${typeCode}4" id="save_type_${typeCode}4"/>
						<form id="frm${typeCode}4">
						<table class="tbl_col" id="table${typeCode}4" >
							<colgroup>
								<col width="5%">
								<col width="10%">
								<col width="10%">
								<col width="15%">
								<col width="10%">
								<col width="10%">
								<col width="5%">
								<col width="10%">
								<col width="10%">
								<col width="5%">
								<col width="10%">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">No</th>
									<th scope="col">고객그룹</th>
									<th scope="col">관리업체</th>
									<th scope="col">매장명</th>
									<th scope="col">담당사원</th>
									<th scope="col">유형</th>
									<th scope="col">차수</th>
									<th scope="col">대표전화</th>
									<th scope="col">팩스번호</th>
									<th scope="col">cvs</th>
									<th scope="col">상태</th>
								</tr>
							</thead>
							<tbody id='tbody${typeCode}4'>
							</tbody>
						</table>
						</form>
						</div>
						<section id="navi50100${typeCode}" class="paginate" style="background-color: #fff;" > 
						</section>
					<div class="pfooter">
		       			<%-- <button type='button' class="red" id="tempSave${typeCode}4">저장</button> --%>
		       			<button type='button' class="white" id="tempClose${typeCode}4">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
		</div>
</article>
</body>
</html>
