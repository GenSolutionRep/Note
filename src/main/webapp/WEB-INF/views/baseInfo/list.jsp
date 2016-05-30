<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<script type="text/javascript" src="<%=jsPath%>/routine/base/menu.js"></script>
<script type="text/javascript">
		
$(document).ready(function(){
	$("#textOpen").click(function(){
		$("#testPop").show();
	});
	
	$("#textClose").click(function(){
		$("#testPop").hide();
	});

});

</script>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1>결재상신</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>전자결재<span class="clamp fa"></span>결재상신함
			</p>
		</div>
	</section>
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
				<button class="red">
					조회
				</button>
				<button class="navy">
					초기화
				</button>
				<button class="skyblue" id="textOpen" >결재상신</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table cellspacing="0" border="1" summary="유형별 자산목록리스트" class="tbl_type">
			<caption>
				결재상신목록
			</caption>
			<colgroup>
				<col span="9">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">관리번호</th>
					<th scope="col">지점명</th>
					<th scope="col">이름</th>
					<th scope="col">근태일자</th>
					<th scope="col">결재유형</th>
					<th scope="col">첨부</th>
					<th scope="col">기안일자</th>
					<th scope="col">결재상태</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>10</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-times"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>9</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-paperclip fa-lg"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>8</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-paperclip fa-lg"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>7</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-times"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>6</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-times"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>5</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-paperclip  fa-lg"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>4</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-times"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>3</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-times"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>2</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-times"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
				<tr>
					<td>1</td>
					<td>050820001</td>
					<td>서부</td>
					<td>김태희</td>
					<td>2015-08-29</td>
					<td>평일연장근무</td>
					<td><i class="fa fa-times"></i></td>
					<td>2015-08-20</td>
					<td>결재상신</td>
				</tr>
			</tbody>
		</table>
	<!-- //UI Object -->
	</section>
</article>
</body>
</html>
