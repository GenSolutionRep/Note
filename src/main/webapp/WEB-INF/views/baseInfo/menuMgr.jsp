<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link href="/css/skin-xp/ui.fancytree.css" rel="stylesheet" type="text/css">
    <link href="/css/tree/treeview.min.css" rel="stylesheet" type="text/css">
    <link href="/css/popup/popup.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery/jquery.js" type="text/javascript"></script>
    <script src="/js/jquery/jquery-ui.custom.js" type="text/javascript"></script>
    <script src="/js/tree/jquery.fancytree.js" type="text/javascript"></script>
    <script src="/js/routine/baseInfo/menuMgr.js" type="text/javascript"></script>
  </head>

  <body class='clsBasic'>
    <div id="content-outer">
      <!-- Start Content -->
      <div id="content">
        <div class="navigation">
          <a href="/Main.jsp">Home</a> &gt; <a href="menuMgr.jsp">기준정보</a> &gt; 메뉴관리
        </div>
        <div id="page-heading"><span>메뉴관리</span></div>
        <table class="clsBasicTbl" id="content-table">
          <tr>
            <th rowspan="3" class="sized"><img src="/images/shared/side_shadowleft.jpg" class="clsSideShadow" alt="" /></th>
            <th class="topleft"></th>
            <td id="tbl-border-top">&nbsp;</td>
            <th class="topright"></th>
            <th rowspan="3" class="sized"><img src="/images/shared/side_shadowright.jpg" class="clsSideShadow" alt="" /></th>
          </tr>
          <tr>
            <td id="tbl-border-left"></td>
            <td>
              <div id="content-table-inner">
                <div id="table-content">
                  <table style="width: 99%; height: 768px; margin-left: auto; margin-right: auto;">
                    <tr>
                      <td style='vertical-align: top; padding-top: 5px; width: 150px;'>
                        <div id="tree" data-source="ajax" style='border: 1px solid #cccccc; height: 500px;'></div>
                      </td>
                      <td style="width: 20px;">&nbsp;</td>
                      <td style='vertical-align: top;'>
                        <div style="text-align: right; padding-right: 5px;">
                          <button type="button" class="btn btn-primary btn-sm" id='btn_add'>
                            <span class="glyphicon glyphicon-plus"></span> 등록
                          </button>
                        </div>
                        <!-- Table -->
                        <form id='formList' style='margin-top: 10px;'>
                          <table class="clsBasicTbl" id="product-table">
                            <colgroup>
                              <col style='width: 5%;'/>
                              <col style='width: 10%;'/>
                              <col style='width: 25%;'/>
                              <col style='width: 20%;'/>
                              <col style='width: 14%;'/>
                              <col style='width: 8%;'/>
                              <col style='width: 18%;'/>
                            </colgroup>
                            <thead>
                              <tr>
                                <th>No</th>
                                <th>메뉴일련번호</th>
                                <th>메뉴명</th>
                                <th>URL</th>
                                <th>순서</th>
                                <th>사용 여부</th>
                                <th>관리</th>
                              </tr>
                            </thead>
                            <tbody id='tbodyList'>
                              <tr>
                                <td class='clsTextAlignCenter' colspan='7' style='height: 300px;'>
                                  <img src='/css/tree/loading.gif' alt='로딩중' style="margin-top: 60px;"/>
                                </td>
                              </tr>
                            </tbody>
                          </table>
                        </form>
                      </td>
                    </tr>
                  </table>
                </div>
              </div>
            </td>
            <td id="tbl-border-right"></td>
            <td></td>
            <td></td>
          </tr>
          <tr>
            <th class="sized bottomleft"></th>
            <td id="tbl-border-bottom">&nbsp;</td>
            <th class="sized bottomright"></th>
          </tr>
        </table>
      </div>
    </div>

    <button class="btn btn-primary" id="btn_delete_popup" data-toggle="modal" data-target="#delete_popup" style="display: none;">Small modal</button>
    <div class="modal fade delete_popup" id="delete_popup" tabindex="-1">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title" id="myModalLabel">메뉴 삭제</h4>
          </div>
          <div class="modal-body">
            선택하신 메뉴를 삭제 하시겠습니까?
            <table class="table table-bordered" style="position: relative; top: 20px;">
              <tr>
                <td>메뉴일련번호</td>
                <td><div id="del_seq"></div></td>
              </tr>
              <tr>
                <td>메뉴명</td>
                <td><div id="del_name"></div></td>
              </tr>
            </table>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">취소</button>
            <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" onclick="deleteMenuComplete();">삭제</button>
          </div>
        </div>
      </div>
    </div>
    <div class="QTPopup">
      <div class="popupGrayBg"></div>
      <div class="QTPopupCntnr panel panel-primary" style="border-radius: 0; border-width: 0;">
        <div class="panel-heading" style="border-radius: 0;">
          <h3 class="panel-title" id="popup_title">메뉴 등록</h3>
        </div>
        <div class="panel-body" style="margin-left: 20px;">
          <form class="form-horizontal" name="frm_code" action="/CommCode?cmd=update" method="post">
            <input type="hidden" id="inputHiddenInputType" name="inputHiddenInputType" value="INSERT" />
            <input type="hidden" id ="p_cc_auto" name="p_cc_auto" value="1" />
            <input type="hidden" id="m_depth" name="m_depth" />
            <div class="form-group">
              <label class="col-sm-3 control-label">일련번호<span class="clsRequired">&nbsp;*</span></label>
              <div class="col-sm-8">
                <input type="number"
                       class="form-control"
                       id="m_seq"
                       name="m_seq"
                       disabled="disabled"
                       placeholder="일련번호는 자동 생성됩니다." />
              </div>
              <label class="checkbox-inline" style="display: none;">
                <input type="checkbox" id="cc_auto" name="cc_auto" value="" checked="checked" style="position: relative; bottom: 2px;"> 자동입력
              </label>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">메뉴명<span class="clsRequired">&nbsp;*</span></label>
              <div class="col-sm-8">
                <input type="text" class="form-control" id="m_name" name="m_name" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">URL<span class="clsRequired">&nbsp;*</span></label>
              <div class="col-sm-8">
                <input type="text" class="form-control" id="m_url" name="m_url" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">부모일련번호</label>
              <div class="col-sm-3" id="code_select1"></div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">순번</label>
              <div class="col-sm-8">
                <input type="number" class="form-control" id="m_order" name="m_order" />
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">사용여부<span class="clsRequired">&nbsp;*</span></label>
              <div class="col-sm-8">
                <label class="radio-inline">
                  <input type="radio" id="m_is_use1" name="m_is_use" value="Y" checked="checked" /> Y
                </label>
                <label class="radio-inline">
                  <input type="radio" id="m_is_use2" name="m_is_use" value="N" /> N
                </label>
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-3 control-label">메모</label>
              <div class="col-sm-8">
                <textarea rows="3" class="form-control" id="m_note" name="m_note"></textarea>
              </div>
            </div>
            <div class="form-group clsTextAlignCenter">
              <button type="button" id="btn_submit" name="btn_submit" class="btn btn-primary" style="font-size: 9pt;">확인</button>
              &nbsp;&nbsp;
              <button type="button" id="btn_cancel" name="btn_cancel" class="btn btn-default" style="font-size: 9pt;">취소</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>