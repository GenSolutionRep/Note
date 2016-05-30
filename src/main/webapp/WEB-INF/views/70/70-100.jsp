<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	<jsp:include page="../inc/auth.jsp" flush='true' />
    <script type="text/javascript" src="<%=jsPath%>/routine/70/70-100.js"></script>
  </head>

  <body>
    <article class="content">
      <!-- 레이어 팝업 시작 -->
      <div id="noticeSavePop70100" style="display: none ;">
        <input type="hidden" id="noticeSaveType70100" value="I">
        <input type="hidden" id="noticeAuthType70100">
        <input type="hidden" id="noticeModifyType70100">
        <input type="hidden" id="noticeCmCode70100">
         <input type="hidden" id="noticeTmCode70100">
        <input type="hidden" id="noticeEmCode70100">
        <input type="hidden" id="noticeBmInnb">
        <input type="hidden" id="noticeFixRoundItem" >
        <div class="popup" style="width: 800px;">
          <div class="phead">
            <span id="noticeTitle">공지사항 등록</span>
            <a id="noticeSavePopCloseX70100" class="phead-closex">닫기</a>
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
                      <span>
                        <!--
                        <select id="noticeOmCode" class="select-basic">
                          <option>선택</option>
                          <option value="00001">동부지점</option>
                          <option value="00002">서부지점</option>
                          <option value="00003">남부지점</option>                    
                          <option value="00004">북부지점</option>
                          <option value="00006">본사</option>
                        </select>
                        -->
                        <select id="noticeOmCode" class="select-basic"></select> 
                      </span>
                      <div id="selectOmItem" class="select-item"></div>
                    </div>
                  </td>
                </tr>
               <!--  <tr>
                  <th>수신매장</th>
                  <td>
                    <div class="input_group clear">
                      <span>
                        <input id="noticeSmCode" class="input-basic" type="text" />
                      </span>
                      <div id="selectSmItem" class="select-item"></div>
                    </div>
                  </td>
                </tr> -->
                <tr>
                <th>팀명</th>
                  <td>
                    <div class="input_group clear">
                      <span>
                        <input id="noticeTeamCode" class="input-basic" type="text" />
                      </span>
                      <div id="selectTeamItem" class="select-item"></div>
                    </div>
                  </td>
                </tr>
                 <tr>
                  <th>[고정  / 순회]</th>
                  <td>
                    <div class="input_group clear">
                      <span>
                        <select id="noticeFixRound" class="select-basic">
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
							<span id="noticeEmNoSpan">
								<input id="noticeEmNo70100" class="input-basic" type="text"  >
							</span>
							<div id="selectEmItem" class="select-item" ></div>
						</div>
					</td>
				</tr>
                <tr>
                  <th>제목</th>
                  <td>
                    <input id="noticeBmSj" type="text" style="width: 100%;" />
                  </td>
                </tr>
                <tr>
                  <th>내용</th>
                  <td><textarea id="noticeBmCn" rows="15" cols="" style="width: 100%;"></textarea></td>
                </tr>
                <tr>
                  <th>첨부파일</th>
                  <td style="padding: 0;">
                    <input type="hidden" id="noticeFileAmNo" value="0" />
                    <div id="noticeFile"></div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="pfooter">
            <button type="button" class="red auth-insert auth-update" id="noticeSave">저장</button>
            <button type="button" class="white" id="noticeSavePopClose70100">닫기</button>
            <button type="button" class="white" id="noticepCloseRelod70100" style="display:none">닫기</button>
          </div>
        </div>
        <div class="popup_overlay"></div>
      </div>
      <div id="noticeViewPop70100" style="display: none;">
        <div class="popup" style="width: 800px;">
          <div class="phead">
            <span id="noticeTitle">공지사항 보기</span>
            <a id="noticeViewPopCloseX70100" class="phead-closex">닫기</a>
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
                      <div id="selectOmViewItem" class="select-item"></div>
                    </div>
                  </td>
                </tr>
               <!--  <tr>
                  <th>수신매장</th>
                  <td>
                    <div class="input_group clear">
                      <div id="selectSmViewItem" class="select-item"></div>
                    </div>
                  </td>
                </tr> -->
                <tr>
                  <th>팀명</th>
                  <td>
                    <div class="input_group clear">
                      <div id="selectTmViewItem" class="select-item"></div>
                    </div>
                  </td>
                </tr> 
                <tr>
					<th>[고정  / 순회]</th>
					<td>
						<div class="input_group clear">
	                      <span>
	                        <select id="fixRoundViewItem" class="select-basic">
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
							<div id="selectEmViewItem" class="select-item" ></div>
						</div>
					</td>
				</tr>
                <tr>
                  <th>제목</th>
                  <td id="noticeViewBmSj"></td>
                </tr>
                <tr>
                  <th>내용</th>
                  <td id="noticeViewBmCn" class="content-td"></td>
                </tr>
                <tr>
                  <th>첨부파일</th>
                  <td colspan="3" class="attach-view">
                    <div class="tx-attach-div auth-down">
                      <div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0;">
                        <div class="tx-attach-box-inner">
                          <ul id="noticeFileViewList" class="tx-attach-list"></ul>
                        </div>
                      </div>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="pfooter">
            <button type="button" class="red auth-update" id="noticeMod70100">수정</button>
            <button type="button"  class="gray auth-del" id="noticeDel70100">삭제</button>
            <button type="button" class="white" id="noticeViewPopClose70100">닫기</button>
          </div>
        </div>
        <div class="popup_overlay"></div>
      </div>
      <!-- 레이어 팝업 종료 -->
      <section>
        <div class="page_nav">
          <h1>공지사항</h1>
          <p>
            <i class="fa fa-home"></i><span class="clamp fa"></span>커뮤니케이션<span class="clamp fa"></span>공지사항
          </p>
        </div>
      </section>
      <section>
        <div class="searchbox">
          <div class="input_group clear">
            <span> <strong>지점명</strong></span>
            <span> 
              <!--
              <select id="">
                <option value="sj">선택</option>
              </select>
              -->
              <select id="jijumOmCode70100" class="select-basic"></select>
            </span>
            <span>
              <!-- <input class="input-icon" id="jijumWord70100" type="text" placeholder="거래처/매장명"> -->
              <input class="input-icon" id="teamWord70100" type="text" placeholder="팀명">
              <label for="noticeWord2"><i class="fa fa-home"></i></label> 
              <input type="hidden" id="teamCode70100">
            </span>
          </div>
          <div class="input_group clear">
            <span> 
              <select id="noticeKey70100">
                <option value="sj">제목</option>
                <option value="cn">내용</option>
              </select>
            </span>
            <span>
              <input class="input-basic" id="noticeWord70100" type="text" placeholder="검색어">           
            </span>
          </div>
          <div class="btn_gup">
            <button class="red" id="noticeSearchBtn">조회</button>
            <button class="gray" id="noticeSearchResat">초기화</button>
            <button class="skyblue auth-insert" id="noticeOpen70100">등록</button>
          </div>
        </div>
      </section>
      <section>
      <!-- UI Object -->
      <table summary="공지사항 리스트" class="tbl_col">
        <caption>공지사항 목록</caption>
        <colgroup>
          <col width="5%">
          <col width="60%">
          <col width="10%">
          <col width="10%">
          <col width="15%">
        </colgroup>
        <thead>
          <tr>
            <th scope="col">No</th>
            <th scope="col">제목</th>
            <th scope="col">첨부</th>
            <th scope="col">작성자</th>
            <th scope="col">등록일자</th>
          </tr>
        </thead>
        <tbody id="noticeTbody">
          <!--
          <tr>
            <td>10</td>
            <td class="txt_left">서부지점 공지</td>
            <td><i class="fa fa-times"></i></td>
            <td>김태희</td>
            <td>2015-08-29</td>
          </tr>
          -->
        </tbody>
      </table>
      <div id="noticeNavi" class="paginate"></div>
      <!-- //UI Object -->
      </section>
    </article>
  <jsp:include page="../inc/auth.jsp" flush='true' />
  </body>
</html>