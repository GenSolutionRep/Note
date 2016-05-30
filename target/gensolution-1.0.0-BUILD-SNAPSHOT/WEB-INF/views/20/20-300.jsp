<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>

<!DOCTYPE html> 
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-300.js"></script>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>수집항목관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>분석통계<span class="clamp fa">
				</span>수집항목관리<span class="clamp fa" id="selectedBanchName"></span>
			</p>
		</div>
	</section>
	<section>
		<div class="divide_3"  >
			<ul>
				<li style="width: 20%;">
					<div class="searchbox">
						<h4 id=hTitle1>통계 항목</h4> 
<!-- 						<div class="btn_gup"> -->
<!-- 						</div> -->
					</div>
				</li>
				<li  style="width: 40%;" >
					<div class="searchbox">
						<h4 id="hTitle2">수집 그룹</h4> 
						<div class="btn_gup">
							<button class="red" id="displayGroupSave20300" >저장</button>
						</div>
					</div>
				</li>
				<li  style="width: 40%;" >
					<div class="searchbox">
						<h4 id="hTitle3">수집 항목</h4> 
						<div class="btn_gup">
							<button class="red" id="displayGroupItemSave20300" >저장</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="divide_3">
			<ul>
				<!-- 통계항목리스트 -->
				<li style="width: 20%;">
					<div class="divide_3_thead" id="table1">
						<div class="flex2">통계명</div>	
						<div class="flex_100">관리</div>	
						<div class="flex_17"></div>	
					</div>
					<div class="divide_3_tbody" style="height:495px" id="displayTbody20300">
					</div>
				</li>
				<!-- 관리업체리스트 -->
				<li style="width: 40%;" >
					<div class="divide_3_thead" id="table2" >
						<div class="flex2">그룹명</div>	
						<div class="flex2">약어</div>	
						<div class="flex1">순번</div>	
						<div class="flex1">수집여부</div>	
						<div class="flex_100">선택</div>	
						<div class="flex_17"></div>	
					</div>
					<div class="divide_3_tbody" style="height:495px" id="displayPrdTbody20300">
					</div>
				</li>
				<!-- 매장리스트 -->
				<li style="width: 40%;" >
					<div class="divide_3_thead" id="table3" >
						<div class="flex1">항목명</div>	
						<div class="flex1">약어</div>	
						<div class="flex1">순번</div>	
						<div class="flex1">수집여부</div>	
<!-- 						<div class="flex_100">관리</div>	 -->
						<div class="flex_17"></div>	
					</div>
					<div class="divide_3_tbody" style="height:495px" id="displayPrdItemTbody20300">
					</div>
				</li>
			</ul>
		</div>
	</section>
	<div id="companyPopSpace" >
		<!-- 고객그룹 -->
		<div id="tempPop1" style="display:none  ;">
			<div class="popup"  style="width: 600px;" >
			
					<div class="phead">
						<span id="title1_1">고객그룹 등록</span>
						<a id="tempCloseX1" class="phead-closex" >닫기</a>
					</div>
					<div class="con">
						<div class="tbl_title" >
							<span id="title2_1">고객그룹 등록</span>
						</div>
						<input type="hidden" name="save_type_1" id="save_type_1"/>
						<form id="frm1">
						<table class="tbl_row">
							<colgroup >
								<col width="15%">
								<col width="85%">
							<col>
							</colgroup>
							<tr>
								<th>코드</th>
								<td>
									<input class="input-basic"id="cg_code_1" name="cg_code" type="text" class="input-basic" value="" maxlength="5" placeholder="" readonly="readonly" >
									&nbsp;
									<div class="inputCheck">
										<input id="chkAutoYn_1" name="chkAutoYn_1"  type="checkbox" checked="checked">
										<label for="chkAutoYn_1"></label>
									</div>코드 자동 등록
									<input type="hidden" name="autoYn" id="autoYn_1" value="Y"/>
								</td>
							</tr>
							<tr>
								<th>고객그룹명</th>
								<td>
									<input id="cg_nm_1" name="cg_nm" type="text" placeholder="" maxlength="50" value="" >
								</td>
							</tr>
							<tr>
								<th>사용여부</th>
								<td>
									<div class="inputRadio">
										<input type="radio" name="use_at" id="use_at_y_1" value="Y" checked='checked'>
										<label for="use_at_y_1"></label>
									</div>사용
									<div class="inputRadio">
										<input type="radio" name="use_at" id="use_at_n_1" value="N">
										<label for="use_at_n_1"></label>
									</div>미사용
								</td>
							</tr>
							<tr>
								<th>메모</th>
								<td>
									<textarea id="cg_memo_1" name="cg_memo" cols="60" rows="6" maxlength="250">
									</textarea>
								</td>
							</tr>
						</table>
						</form>
						</div>
					<div class="pfooter">
		       			<button type='button' class="red" id="tempSave1">저장</button>
		       			<button type='button' class="white" id="tempClose1">닫기</button>
					</div>
				</div>
				<div class="popup_overlay"></div>
		</div>
		<!-- 관리업체 -->
			
	</div>
	<!-- 매장현황 -->
</article>
</body>
</html>
