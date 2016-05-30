<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<%-- <jsp:include page="../inc/auth.jsp" flush='true' /> --%>
<script type="text/javascript" src="<%=jsPath%>/routine/10/10-500.js"></script>
</head>
<body>
<!-- 레이어 팝업 시작-->      
<div id="popSpace_10500" >
	<div id="tempViewPop10500" style="display:none  ;">
	<input type="hidden" id="noticeTmCode10500">
		<div class="popup"  style="width: 700px;" >
	
			<div class="phead">
				<span id="subject_title_10500">주요행사 보기</span>
				<a id="tempViewCloseX10500" class="phead-closex" >닫기</a>
			</div>
			<div class="con">
				<!-- div class="tbl_title" >
					<span>주요행사 등록</span>
				</div> -->
				<table class="tbl_row">
					<colgroup >
						<col width="20%">
						<col width="80%">
					<col>
					</colgroup>
					<tr>
						<th>등록구분 </th>
						<td id="view_chk_me_holiday_at_10500">
						</td>
					</tr>
					<tr>
						<th>지점</th>
						<td>
							<div class="input_group clear">
							<div id="selectOmViewItem_10500" class="select-item" >
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th>매장</th>
						<td>
							<div class="input_group clear">
								<div id="selectSmViewItem_10500" class="select-item" ></div>
							</div>
						</td>
						
					</tr>
					<tr>
						<th>제목</th>
						<td id="view_me_Sj_10500">
							주요행사
						</td>
					</tr>
					<tr>
						<th>일정</th>
						<td id="view_period_10500">
						</td>
					</tr>
					<tr>
						<th>등록자</th>
						<td id="view_updt_man_name_10500">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td id="view_me_cn_10500" class="content-td" >
						</td>
					</tr>
					<tr>
						<th>첨부</th>
						<td colspan="3" class="attach-view" >
						<div class="tx-attach-div">
							<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">
								<div class="tx-attach-box-inner">
									<ul id="fileViewList_10500" class="tx-attach-list">
									</ul>
								</div>
							</div>
						</div>
					</tr>
				</table>
			</div>
			<div class="pfooter">
       			<button type='button' class="white" id="tempViewClose10500">닫기</button>
			</div>
		</div>
		<div class="popup_overlay"></div>
	</div>
<!-- 레이어 팝업 종료 -->  
<!-- 레이어 팝업 시작-->      
	<div id="noticeViewPop10500" style="display: none;">
        <div class="popup" style="width: 700px;">
          <div class="phead">
            <span id="noticeTitle">공지사항 보기</span>
            <a id="noticeViewPopCloseX10500" class="phead-closex">닫기</a>
          </div>
          <div class="con">
            <table class="tbl_row">
              <colgroup>
                <col style="width: 20%;">
                <col style="width: 80%;">
              </colgroup>
              <tbody>
                <tr>
					<th>수신지점</th>
					<td>
						<div class="input_group clear">
							<div id="selectOmViewItem_10501" class="select-item" ></div>
						</div>
					</td>
				</tr>
                
                <!-- <tr>
                  <th>수신매장</th>
                  <td>
                    <div class="input_group clear">
                      <div id="selectSmViewItem_10501" class="select-item"></div>
                    </div>
                  </td>
                </tr> -->
                
                <tr>
                  <th>팀명</th>
                  <td>
                    <div class="input_group clear">
                      <div id="selectTmViewItem_10501" class="select-item"></div>
                    </div>
                  </td>
                </tr> 
                <tr>
					<th>[고정  / 순회]</th>
					<td>
						<div class="input_group clear">
	                      <span>
	                        <select id="fixRoundViewItem_10501" class="select-basic">
	                          <!-- <option value="">전체</option>
	                          <option value="0000000006">고정</option>
	                          <option value="0000000007">순회</option> -->
	                        </select>
	                      </span>
	                    </div>
					</td>
				</tr>
				
                <!--       공지사항에 추가          -->
                <tr>
					<th>사원</th>
					<td>
						<div class="input_group clear">
							<div id="selectEmViewItem_10501" class="select-item" ></div>
						</div>
					</td>
				</tr>
				<!--       공지사항에 추가                  -->
				
                <tr>
                  <th>제목</th>
                  <td id="noticeViewBmSj_10500"></td>
                </tr>
                <tr>
                  <th>내용</th>
                  <td id="noticeViewBmCn_10500" class="content-td"></td>
                </tr>
                <tr>
                  <th>첨부</th>
                  <td colspan="3" class="attach-view">
                    <div class="tx-attach-div auth-down">
                      <div id="tx_attach_box_10500" class="tx-attach-box" style="margin-left: 0;">
                        <div class="tx-attach-box-inner">
                          <ul id="noticeFileViewList_10500" class="tx-attach-list"></ul>
                        </div>
                      </div>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="pfooter">
            <button type="button" class="white" id="noticeViewPopClose10500">닫기</button>
          </div>
        </div>
        <div class="popup_overlay"></div>
      </div>
</div>      
<!-- 레이어 팝업 종료 -->    
<article class="content">
	<section>
		<div class="page_nav">
			<!-- <div style='border-radius: 5px; background-color: red; width: 280px; height: 30px; box-shadow:5px 5px #ccc;'>
				<h1 id='title10500' style="vertical-align: middle; margin-left: 70px; color: white;">Today</h1>
			</div> -->
			<div>
				<h1 id='title10500'>Today</h1>
			</div>
			<div style="float: right;">
				<span style="margin-right: 12px">
					<!-- <strong>지점</strong> -->
					<select class="select-basic"  id="om_code_10500"  name ="om_code_10500" style="width: 120px">
					</select> 
				</span>
				
	       		<button type='button' class="skyblue" id="btnSearch_10500">새로고침</button>
	       		<!-- <button type='button' class="white" id="btnJuso">주소 위도경도 처리</button> -->
			</div>
		</div>
	</section>
	<!-- 공지사항 / 주요행사 -->
	<div class="con">
		<div class="tbl_tab">
			<ul>
				<li class="tab_on"><a>주요현황</a></li>
				<!-- <li><a>활동현황</a></li> -->
			</ul>
		</div>
		<!-- 직무별 직원 통계 / 금일 고정 여사원 근태현황/ 금일 순회 여사원 근태 현황 -->
		<div>
			<div id= "chart_10500_1" style="float: left; width: 31%;">
			</div>
			
			<div id= "chart_10500_2" style="float: left;  margin-left:10px; width: 31%;">
			</div>
			
			<div id= "chart_10500_3" style="float: left; margin-left:10px; width: 31%">
			</div>
		
		</div>
		
		<div style="float: left; width: 48%;">
			<div class="tbl_title">
				<span style="font-size: 19px; font-weight: bold;">공지사항</span>
				<!-- <span style="float: right;"><a id="goNoti_10500">공지사항 바로가기</a></span> -->
			</div>
			
			<table class="tbl_col">
				<colgroup>
					<col style="width:10%">
					<col style="width:44%">
					<col style="width:10%">
					<col style="width:18%">
					<col style="width:18%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>첨부</th>
						<th>작성자</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody id="tbody_noti_10500">
				</tbody>
			</table>
			<div id="noti_10500_Navi" class="paginate"></div>
		</div>
		<div style="float: right; width: 48%;">
			<div class="tbl_title">
				<span style="font-size: 19px; font-weight: bold;">주요행사</span>
				<!-- <span style="float: right;"><a id="goEvent_10500">주요행사 바로가기</a></span> -->
			</div>
			<table class="tbl_col">
				<colgroup>
					<col style="width:10%">
					<col style="width:40%">
					<col style="width:10%">
					<col style="width:40%">
				</colgroup>
				<thead>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>첨부</th>
						<th>일정</th>
					</tr>
				</thead>
				<tbody id="tbody_event_10500">
				</tbody>
			</table>
		</div>
	</div>
</article>
<%-- <jsp:include page="../inc/auth.jsp" flush='true' /> --%>
</body>
</html>
