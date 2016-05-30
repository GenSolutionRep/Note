<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=jsPath%>/routine/80/80-100.js"></script>
<script type="text/javascript">
		
$(document).ready(function(){
	
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#tempPop80100").instancePopUp();
	
	$("#tempOpen80100").click(function(){
		$("#tempPop80100").show();
	});
	$("#tempClose80100, #tempCloseX80100").click(function(){
		$("#tempPop80100").hide();
	});
});

</script>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="tempPop80100" style="display: none ;">
		<div class="popup" style="width: 800px;">
			<div class="phead">
				<span id="">학생 수정</span>
				<a id="tempPopCloseX80100" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<div class="tbl_tab">
					<ul>
						<li class="tab_on"><a>사원정보</a></li>
						<li><a>인사기록</a></li>
					</ul>
				</div>
				<div class="tbl_title">
					<span>1. 사원정보 </span>
				</div>
				<table class="tbl_row">
					<colgroup>
						<col style="width:14%">
						<col style="width:36%">
						<col style="width:14%">
						<col style="width:36%">
					</colgroup>
					<tbody>
						<tr>
							<th>지점</th>
							<td colspan="2">서부지점</td>
							<td rowspan="5" class="txt_center"><img src="./images/thum_profile.png"></td>
						</tr>
						<tr>
							<th>이름</th>
							<td colspan="2">전지현</td>
						</tr>
						<tr>
							<th>아이디</th>
							<td colspan="2">025019</td>
						</tr>
						<tr>
							<th>패스워드</th>
							<td colspan="2">
							<button class="white">
								패스워드 확인
							</button></td>
						</tr>
						<tr>
							<th>입사일</th>
							<td colspan="2">2012-04-01</td>
						</tr>
						<tr>
							<th>직책</th>
							<td>사원</td>
							<th>직무</th>
							<td>고정MD</td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td>1981-08-07L</td>
							<th>성별</th>
							<td>여</td>
						</tr>
						<tr>
							<th>휴대전화</th>
							<td>010-1234-1234</td>
							<th>결혼기념일</th>
							<td>2010-06-02</td>
						</tr>
						<tr>
							<th>주소</th>
							<td colspan="3">우)123-123
							<br>
							서울시 강남구 논현로 우리집</td>
						</tr>
					</tbody>
				</table>
				<div class="tbl_title second">
					<span>2. 인사기록 </span>
				</div>
				<table class="tbl_col">
					<colgroup>
						<col style="width:20%">
						<col style="width:40%">
						<col style="width:40%">
					</colgroup>
					<tbody>
						<tr>
							<th>발령일<a><i class="fa fa-sort"></i></a></th>
							<th>발령내용<a><i class="fa fa-sort-asc"></i></a></th>
							<th>특이사항<a><i class="fa fa-sort-desc"></i></a></th>
						</tr>
						<tr>
							<td> 2012-04-01 </td>
							<td class="txt_left">입사 </td>
							<td class="txt_left"><input><button class="white">test</button></td>
						</tr>
						<tr>
							<td> 2012-04-01 </td>
							<td class="txt_left">홈플러스 강서점 고정사원 발령 </td>
							<td class="txt_left"><input></td>
						</tr>
						<tr>
							<td> 2012-04-01 </td>
							<td class="txt_left">우수사원 표창 </td>
							<td class="txt_left">근태우수 </td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="pfooter">
				<button type="button" class="red" id="">
					저장
				</button>
				<button type="button" class="white" id="tempClose80100">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<!-- 레이어 팝업 종료 -->
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
				<button class="skyblue" id="tempOpen80100" >결재상신</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="유형별 자산목록리스트" class="tbl_col">
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
