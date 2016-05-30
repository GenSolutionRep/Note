<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/70/70-300.js"></script>
</head>
<body>
<article class="content">
	<!-- 레이어 팝업 시작 -->
	<div id="businessSavePop70300" style="display:  none ;">
		<input type="hidden" id="businessSaveType70300" value="I" >
		<input type="hidden" id="businessCmCode70300" value="" >
		<input type="hidden" id="businessEmCode70300" value="" >
		<input type="hidden" id="businessTmCode70300" value="" >
		<input type="hidden" id="businessAuthType70300" value="" >
		<input type="hidden" id="businessBmInnb" value="" >
		<input type="hidden" id="bizFixRoundItem" >
		<div class="popup" style="width: 800px;">
			<div class="phead">
				<span id="businessTitle">업무지시 등록</span>
				<a id="businessSavePopCloseX70300" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_row">
					<colgroup>
						<col style="width:20%">
						<col style="width:80%">
					</colgroup>
					<tbody>
						<tr>
							<th>지점</th>
							<td>
								<div class="input_group clear">
									<span id="businessOmCodeSpan">
										<select id="businessOmCode" class="select-basic">
										</select> 
									</span>
									<div id="selectOmItem70300" class="select-item" >
									</div>
								</div>
							</td>
						</tr>
						<!-- <tr>
							<th>매장</th>
							<td>
								<div class="input_group clear">
									<span id="businessSmCodeSpan">
										<input id="businessSmCode" class="input-basic" type="text"  >
									</span>
									<div id="selectSmItem70300" class="select-item" ></div>
								</div>
							</td>
						</tr> -->
						<tr>
							<th>팀명</th>
							<td>
			                    <div class="input_group clear">
			                      	<span>
			                        	<input id="bizTeamCode" class="input-basic" type="text" />
			                    	</span>
			                    <div id="selectTeamItem70300" class="select-item"></div>
			                 	</div>
			                 </td>
						</tr>
						<tr>
							<th>[고정  / 순회]</th>
							<td>
								<div class="input_group clear">
			                      <span>
			                        <select id="bizFixRound" class="select-basic">
			                          <option value="">전체</option>
			                          <option value="0000000006">고정</option>
			                          <option value="0000000007">순회</option>
			                        </select>
			                      </span>
			                    </div>
							</td>
						</tr>
						<tr>
							<th>사원</th>
							<td>
								<div class="input_group clear">
									<span id="businessEmNoSpan">
										<input id="businessEmNo70300" class="input-basic" type="text"  >
									</span>
									<div id="selectEmItem70300" class="select-item" ></div>
								</div>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>
								<input id="businessBmSj" type="text" style="width: 100%" >
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea id="businessBmCn" rows="15" cols="" style="width: 100%;" ></textarea> </td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td style="padding: 0;" >
								<input type="hidden" id="businessFileAmNo" value="0"  >
								<div id="businessFile" ></div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="pfooter">
				<button type="button"  class="red auth-insert auth-update" id="businessSave70300">
					저장
				</button>
				<button type="button" class="white" id="businessSavePopClose70300">
					닫기
				</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	<div id="businessViewPop70300" style="display: none  ;">
		<div class="popup" style="width: 800px;">
			<div class="phead">
				<span id="businessViewTitle">업무지시 보기</span>
				<a id="businessViewPopCloseX70300" class="phead-closex">닫기</a>
			</div>
			<div class="con">
				<table class="tbl_row">
					<colgroup>
						<col style="width:20%">
						<col style="width:80%">
					</colgroup>
					<tbody>
						<tr>
							<th>지점</th>
							<td>
								<div class="input_group clear">
									<div id="selectOmViewItem70300" class="select-item" >
									</div>
								</div>
							</td>
						</tr>
						<!-- <tr>
							<th>매장</th>
							<td>
								<div class="input_group clear">
									<div id="selectSmViewItem70300" class="select-item" ></div>
								</div>
							</td>
						</tr> -->
						<tr>
							<th>팀명</th>
							<td>
								<div class="input_group clear">
									<div id="selectTmViewItem70300" class="select-item" ></div>
								</div>
							</td>
						</tr>
						<tr>
							<th>[고정  / 순회]</th>
							<td>
								<div class="input_group clear">
			                      <span>
			                        <select id="bizFixRoundViewItem70300" class="select-basic">
			                          <option value="">전체</option>
			                          <option value="0000000006">고정</option>
			                          <option value="0000000007">순회</option>
			                        </select>
			                      </span>
			                    </div>
							</td>
						</tr>
						<tr>
							<th>사원</th>
							<td>
								<div class="input_group clear">
									<div id="selectEmViewItem70300" class="select-item" ></div>
								</div>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td id="businessViewBmSj">
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td id="businessViewBmCn" class="content-td" style="height:200px"></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td colspan="3" class="attach-view" >
							<div class="tx-attach-div auth-down">
								<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">
									<div class="tx-attach-box-inner">
										<ul id="businessFileViewList" class="tx-attach-list">
										</ul>
									</div>
								</div>
							</div>
						</tr>
					</tbody>
				</table>
				
				<div style="margin-top: 20px;"></div>
				
				<table class="tbl_col">
					<caption>
						업무지시목록
					</caption>
					<colgroup>
						<col width="5%"  >
						<col width="10%"  >
						<col width="60%"  >
						<col width="13%"  >
						<col width="12%"  >
					</colgroup>
					<thead>
						<tr>
							<th scope="col">No</th>
							<th scope="col">수신자</th>
							<th scope="col">답변내용</th>
							<th scope="col">수신일</th>
							<th scope="col">재전송</th>
						</tr>
					</thead>
					<tbody id="noticePopupTbody70300" >
						<!-- <tr>
							<td>1</td>
							<td >아이유</td>
							<td>답변내용</td>
							<td>2015-08-29</td>
							<td><button type="button"  id="">재전송</button></td>
						</tr> -->
					</tbody>
				</table>
				<div id="popNavi70300" class="paginate"></div>
			</div>
			
			<div class="pfooter">
				<button type="button"  class="red auth-update" id="businessMod70300">수정</button>
				<button type="button"  class="gray auth-del" id="businessDel70300">삭제</button>
				<button type="button"  class="white" id="businessViewPopClose70300">닫기</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
	 
	<!-- 레이어 팝업 종료 -->
	<section>
		<div class="page_nav">
			<h1>업무지시</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"></span>커뮤니케이션<span class="clamp fa"></span>업무지시
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span> <strong>지점명</strong></span>
					<span> 
						<select id="jijumOmCode70300" class="select-basic"></select>
					</span>
					<span>
<!-- 					    <input class="input-icon" id="jijumWord70300" type="text" placeholder="팀명"> -->
						<input class="input-icon" id="teamWord70300" type="text" placeholder="팀명">
						<label for="noticeWord2"><i class="fa fa-home"></i></label> 
						<input type="hidden" id="teamCode70300">
					</span>
				</div>
				<div class="input_group clear">
					<span> 
					<select id="noticeKey70300">
						<option value="sj" >제목</option>
						<option value="cn" >내용</option>
					</select>
				</span>
					<span>
						<input class="input-basic" id="noticeWord70300" type="text" placeholder="검색어">					 
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="businessSearchBtn70300" >조회</button>
				<button class="gray" id="businessSearchResat70300" >초기화</button>
				<button class="skyblue auth-insert" id="businessOpen70300" >등록</button>
			</div>
		</div>
	</section>
	<section>
	<!-- UI Object -->
		<table summary="업무지시리스트" class="tbl_col">
			<caption>
				업무지시목록
			</caption>
			<colgroup>
				<col width="5%"  >
				<col width="60%"  >
				<col width="10%"  >
				<col width="10%"  >
				<col width="15%"  >
			</colgroup>
			<thead>
				<tr>
					<th scope="col">No</th>
					<th scope="col">제목</th>
					<th scope="col">답변</th>
					<th scope="col">작성자</th>
					<th scope="col">등록일자</th>
				</tr>
			</thead>
			<tbody id="noticeTbody70300" >
				<!-- <tr>
					<td>10</td>
					<td class="txt_left">서부지점 공지</td>
					<td>9/20</td>
					<td>김태희</td>
					<td>2015-08-29</td>
				</tr> -->
			</tbody>
		</table>
		<div id="noticeNavi70300"  class="paginate"> 
		</div>
	<!-- //UI Object -->
	</section>
</article>
<jsp:include page="../inc/auth.jsp" flush='true' />
</body>
</html>
