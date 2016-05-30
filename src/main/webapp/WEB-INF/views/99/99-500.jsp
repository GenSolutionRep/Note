<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-500.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">


</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="optionPop99500" style="display: none ;">
		<div class="popup" style="width: 600px;">
			<div class="phead">
				<span id="optionTitle">업무일지항목</span>
				<a id="optionPopCloseX99500" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_row">
					<colgroup>
						<col style="width:20%">
						<col style="width:80%">
					</colgroup>
					<tr>
						<th>구분</th>
						<td>
							<span id="optionTypeName" ></span>
						</td>
					</tr>
					<tr>
						<th>코드</th>
						<td><input id="optionCode" class="input-basic" type="text" placeholder="코드 자동 등록" disabled></td>
					</tr>
					<tbody id="trtGroup99500" class="hide" >
						<tr>
							<th>제품군</th>
							<td>
								<select id="trtComboDept1"  class="select-basic trt-change-group" data-trt-depth="1" >
								</select> 
							</td>
						</tr>
						<tr>
							<th>자재그룹</th>
							<td>
								<select id="trtComboDept2"  class="select-basic trt-change-group" data-trt-depth="2" >
								</select> 
							</td>
						</tr>
						<tr>
							<th>제품계층1</th>
							<td>
								<select id="trtComboDept3" class="select-basic trt-change-group" data-trt-depth="3" >
								</select> 
							</td>
						</tr>
						<tr>
							<th>제품계층2</th>
							<td>
								<select id="trtComboDept4" class="select-basic trt-change-group" data-trt-depth="4" >
								</select>
							</td>
						</tr>
						<tr>
							<th>제품계층3</th>
							<td>
								<select id="trtComboDept5" class="select-basic trt-change-group" data-trt-depth="5" >
								</select>
							</td>
						</tr>
					</tbody>
					<tr>
						<th>항목명</th>
						<td>
							<input id="optionParentCode" type="hidden">
							<input id="optionName" type="text" placeholder="" maxlength="50" >
						</td>
					</tr>
					<tr>
						<th>약어</th>
						<td><input id="optionNickName" type="text" placeholder="" maxlength="50" ></td>
					</tr>
					<tr>
						<th>순번</th>
						<td><input id="optionOrder" max="999999"  type="text" placeholder="" maxlength="5" ></td>
					</tr>
					<tr>
						<th>기본여부</th>
						<td>
							<div class="inputRadio">
								<input type="radio" value="Y" id="optionDefaultY" name="optionDefault"  />
								<label for="optionDefaultY"></label>
							</div>Y 
							<div class="inputRadio">
								<input type="radio" value="N" id="optionDefaultN" name="optionDefault" checked />
								<label for="optionDefaultN"></label>
							</div>N 
						</td>
					</tr>
					<tr>
						<th>사용여부</th>
						<td>
							<div class="inputRadio">
								<input type="radio" value="Y" id="optionUseY" name="optionUse" checked />
								<label for="optionUseY"></label>
							</div>Y 
							
							<div class="inputRadio">
								<input type="radio" value="N" id="optionUseN" name="optionUse" />
								<label for="optionUseN"></label>
							</div>N 
						</td>
					</tr>
				</table>
			</div>
			<div class="pfooter">
				<button type="button"  class="red  auth-insert auth-update" id="optionPopSave99500">
					저장
				</button>
				<button type="button" class="white" id="optionPopClose99500">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>업무일지관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>활동관리<span class="clamp fa"></span>업무일지관리
			</p>
		</div>
	</section>
	
	<section>
		<input id="optionType" value="" type="hidden" >
	<!-- UI Object -->
		<table  style="width: 100% ; vertical-align: top; "  >
			<colgroup>
				<col width="25%">
				<col width="75%">
			</colgroup>
			<tbody>
				<tr>
					<td style=" vertical-align: top; ">
				        <div id='optionCodeList' class="panel-body mtree-wrap" >
		            	</div>
					</td>
					<td  style=" vertical-align: top; " >
						<section>
							<div class="searchbox" style="height: 54px;" >
								<h4 id="selectedOptionName" ></h4> 
								<div class="btn_gup">
									<button class="skyblue auth-insert" id="optionOpen99500" >등록</button>
								</div>
							</div>
						</section>
						<table summary="유형별 자산목록리스트" class="tbl_col">
							<caption>결재상신목록</caption>
							<colgroup>
								<col width="5%" >
								<col width="16%"  >
								<col width="22%"  >
								<col width="14%"  >
								<col width="14%"  >
								<col width="14%"  >
								<col width="15%" >
							</colgroup>
							<thead>
								<tr>
									<th scope="col">No</th>
<!-- 									<th scope="col">코드</th> -->
									<th scope="col">약어</th>
									<th scope="col">항목명</th>
									<th scope="col">순서</th>
									<th scope="col">기본여부</th>
									<th scope="col">사용여부</th>
									<th scope="col">관리</th>
								</tr>
							</thead>
							<tbody id="treeList99500" >
								<tr><td colspan="8">조회된 데이터가 없습니다.</td></tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</section>
	<!-- //UI Object -->
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
