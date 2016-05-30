<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/99/99-400.js"></script>
<script type="text/javascript">
		
</script>
<style type="text/css">


</style>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="codePop99400" style="display: none ;">
		<div class="popup" style="width: 600px;">
			<div class="phead">
				<span id="codeTitle">공통코드</span>
				<a id="codePopCloseX99400" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_row">
					<colgroup>
						<col style="width:20%">
						<col style="width:80%">
					</colgroup>
					<tbody>
						<tr>
							<th>부모코드</th>
							<td>
								<input id="cPrentCodeName" value="최상위코드" type="text" readonly >
								<input id="cPrentCode" value="" type="hidden" >
							</td>
						</tr>
						<tr>
							<th>코드</th>
							<td><input id="cCode" class="input-basic" type="text" placeholder="코드 자동 등록" disabled></td>
						</tr>
						<tr>
							<th>코드명</th>
							<td><input id="cName" type="text" placeholder="" maxlength="50" ></td>
						</tr>
						<tr>
							<th>코드설명</th>
							<td><textarea id="cDesc" rows="8" cols=""></textarea> </td>
						</tr>
						<tr>
							<th>순번</th>
							<td><input id="cOrder" max="999999"  type="text" placeholder="" maxlength="5" ></td>
						</tr>
						<tr>
							<th>기타1</th>
							<td><input id="cExt1" type="text" ></td>
						</tr>
						<tr>
							<th>기타2</th>
							<td><input id="cExt2" type="text"></td>
						</tr>
						<tr>
							<th>기타3</th>
							<td><input id="cExt3" type="text" ></td>
						</tr>
						<tr>
							<th>시스템 코드</th>
							<td>
								<div class="inputRadio">
									<input type="radio" value="Y" id="cSystemCodeY" name="cSystemCode"  />
									<label for="cSystemCodeY"></label>
								</div>Y 
								<div class="inputRadio">
									<input type="radio" value="N" id="cSystemCodeN" name="cSystemCode" checked />
									<label for="cSystemCodeN"></label>
								</div>N 
							</td>
						</tr>
						<tr>
							<th>사용여부</th>
							<td>
								<div class="inputRadio">
									<input type="radio" value="Y" id="cUseY" name="cUse" checked />
									<label for="cUseY"></label>
								</div>Y 
								
								<div class="inputRadio">
									<input type="radio" value="N" id="cUseN" name="cUse" />
									<label for="cUseN"></label>
								</div>N 
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="pfooter">
				<button type="button"  class="red auth-insert auth-update" id="codePopSave99400">
					저장
				</button>
				<button type="button" class="white" id="codePopClose99400">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>공통코드관리</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>기준정보<span class="clamp fa"></span>공통코드관리
			</p>
		</div>
	</section>
	
	<section>
	<!-- UI Object -->
		<table  style="width: 100% ; vertical-align: top; "  >
			<colgroup>
				<col style="width:25%">
				<col style="width:75%">
			</colgroup>
			<tbody>
				<tr>
					<td style=" vertical-align: top; ">
				        <div id='cCodeList' class="panel-body mtree-wrap" >
		            	</div>
					</td>
					<td  style=" vertical-align: top; " >
						<section>
							<div class="searchbox">
								<h4 id="selectedCodeName" >최상위코드</h4> 
								<div class="btn_gup">
									<button class="skyblue auth-insert" id="codeOpen99400" >등록</button>
								</div>
							</div>
						</section>
						<table summary="유형별 자산목록리스트" class="tbl_col">
							<caption>결재상신목록</caption>
							<colgroup>
								<col width="5%" >
								<col span="5"  >
								<col width="150px" >
							</colgroup>
							<thead>
								<tr>
									<th scope="col">No</th>
									<th scope="col">코드</th>
									<th scope="col">코드내용</th>
									<th scope="col">순서</th>
									<th scope="col">시스템코드</th>
									<th scope="col">사용여부</th>
									<th scope="col">관리</th>
								</tr>
							</thead>
							<tbody id="treeList99400" >
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
