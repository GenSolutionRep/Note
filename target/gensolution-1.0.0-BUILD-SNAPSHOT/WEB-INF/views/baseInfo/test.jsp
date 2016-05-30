<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<%=jsPath%>/scheduler/baseInfo/code.js"></script>
<link href="<%=jsPath%>/scheduler/fullcalendar.css" rel="stylesheet" />
<link href="<%=jsPath%>/scheduler/fullcalendar.print.css" rel="stylesheet" media="print" />
<script src="<%=jsPath%>/scheduler/lib/moment.min.js"></script>
<script src="<%=jsPath%>/scheduler/lib/jquery.min.js"></script>
<script src="<%=jsPath%>/scheduler/fullcalendar.min.js"></script>
<script src="<%=jsPath%>/scheduler/lang-all.js"></script>
</head>
<body>

<table>
<colgroup>
	<col width="100%" >
</colgroup>
<tr>
	<td>
		
		<div id="calendar"></div>
		
		
		
		<!--
		<table>
			<thead>
				<tr>
					<th>필드1</th>
					<th>필드2</th>
					<th>필드3</th>
					<th>필드4</th>
					<th>필드5</th>
					<th>필드6</th>
					<th>필드7</th>
					<th>필드8</th>
				</tr>
			</thead>
			<tbody id="testTbody" >
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
			</tbody>
		</table>
		-->
	</td>
</tr>

	<!-- Code Add & Update Popup -->
	<div class="QTPopup" style="display:none">
		<div class="popupGrayBg"></div>
		<!-- <div class="QTPopupCntnr">	-->
		
		<div class="QTPopupCntnr panel panel-primary" style="border-radius:0px; border-width: 0px;">
					<div class="panel-heading" style="border-radius:0px;">
						<h3 class="panel-title" id="popup_title">공통 코드 등록</h3>
					</div>
					<div class="panel-body" style="margin-left: 20px;">
						<form class="form-horizontal" name="frm_code" action="/CommCode?cmd=update" method="post">
							<input type="hidden" id="c_type" name="c_type" value="INSERT">
							<input type="hidden" id ="p_c_auto" name="p_c_auto" value="1">
							<input type="hidden" id="c_depth" name="c_depth" value="">
							<input type="hidden" id="c_in_id" name="c_in_id" value="<%=session.getAttribute("e_id") %>">
							<input type="hidden" id="c_up_id" name="c_up_id" value="<%=session.getAttribute("e_id") %>">
							<input type="hidden" id="c_parent_code" name="c_parent_code" />
							<input type="hidden" id="c_parent_depth" name="c_parent_depth" />
				<div class="form-group">
						<label class="col-sm-3 control-label">코드<span style="color: red;">&nbsp;*</span></label>
						<div class="col-sm-8">
							<input type="number" class="form-control" id="c_code" name="c_code" placeholder="코드는 자동 생성됩니다." disabled="disabled">
						</div>
						<label class="checkbox-inline" style="display: none;">
						<input type="checkbox" id="c_auto" name="c_auto" value="" checked="checked" style="position: relative; bottom: 2px;"> 자동입력
					</label>
						 </div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">코드내용<span style="color: red;">&nbsp;*</span></label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="c_name" name="c_name" placeholder="코드내용을 입력하세요.">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">코드설명</label>
					<div class="col-sm-8">
						<textarea rows="3" class="form-control" id="c_desc" name="c_desc"></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">부모코드</label>
					<div id="parent_name_list"></div>
					<%--
					<div class="col-sm-3" id="code_select1">
 						<select class="form-control" id="c_parent_code1" onchange="javascript:fnGetCodeRowByParentCode(2, '', this.value);">
 							<option value="">최상위코드</option>
 						</select>
					</div>
					<div class="col-sm-3" id="code_select2">
 						<select class="form-control" id="c_parent_code2" onchange="javascript:fnGetCodeRowByParentCode(3, '', this.value);">
 							<option value="">최상위코드</option>
 						</select>
 					</div>
					<div class="col-sm-3" id="code_select3">
 						<select class="form-control" id="c_parent_code3">
 							<option value="">최상위코드</option>
 						</select>
 					</div>
 					--%>
 					
					<!-- 
					<div class="col-sm-3">
						<select class="form-control" name="c_parent_code">
						<option value="1">1</option>
						<option value="1">2</option>
						<option value="1">3</option>
						<option value="1">4</option>
						<option value="1">5</option>
					</select>
				</div>
				 -->
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">순번</label>
					<div class="col-sm-8">
						<input type="number" class="form-control" id="c_order" name="c_order" >
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">시스템 코드<span style="color: red;">&nbsp;*</span></label>
					<div class="col-sm-8">
						<label class="radio-inline">
						<input type="radio" id="c_is_system_code" name="c_is_system_code" value="Y"> Y
					</label>
					<label class="radio-inline">
						<input type="radio" name="c_is_system_code" value="N" checked="checked"> N
					</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">사용 여부<span style="color: red;">&nbsp;*</span></label>
					<div class="col-sm-8">
						<label class="radio-inline">
						<input type="radio" id="c_is_use" name="c_is_use" value="Y" checked="checked"> Y
					</label>
					<label class="radio-inline">
						<input type="radio" name="c_is_use" value="N"> N
					</label>
					</div>
				</div>
				
				<div class="form-group" style="text-align: center;">
					<button type="button" id="btn_submit" name="btn_submit" class="btn btn-primary" style="font-size: 9pt;">확인</button>
					&nbsp;&nbsp;
				<button type="button" id="btn_cancel" name="btn_cancel" class="btn btn-default" style="font-size: 9pt;">취소</button>
				</div>
			</form>
					</div>
				</div>
		<!-- </div> -->
	</div>

</table>
</body>
</html>
