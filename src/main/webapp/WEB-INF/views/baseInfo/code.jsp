<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<script type="text/javascript" src="<%=jsPath%>/routine/baseInfo/code.js"></script>
<style type="text/css">

.mtree-wrap {
	height: 100%;
	overflow-y: scroll;
	width: 232px;
	font-size: 12px;
	border: 1px solid #eaeaea;
	text-align: left;
}

.tree {
	padding-left: 14px;
	overflow: auto;
	position: relative;
}

.tree-solid-line {
	padding-left: 12px;
}

.tree.tree-no-line {
	padding-left: 0;
}

.tree:before {
	width: 1px;
	z-index: 1;
	display: block;
	content: "";
	position: absolute;
	top: -21px;
	bottom: 16px;
	left: 0;
	border-left: 1px dotted #666;
}

.tree.tree-solid-line:before {
	border-left: 1px solid #999;
}

.tree.tree-no-line:before {
	display: none;
}

.tree .tree-folder {
	width: auto;
	min-height: 20px;
	cursor: pointer;
}

.tree .tree-folder .tree-folder-header {
	position: relative;
	min-height: 20px;
	line-height: 20px;
	min-width: 100px;
}

.tree .tree-folder .tree-folder-header:hover {
	background-color: #F1F2F7;
	border-radius: 4px;
	-webkit-border-radius: 4px;
}

.tree .tree-folder .tree-folder-header .tree-folder-name, .tree .tree-item .tree-item-name {
	display: inline;
	z-index: 2;
}

.tree .tree-folder img {
	margin-left: 4px;
}

.tree .tree-folder .tree-folder-header .tree-folder-name {
	margin-left: 2px;
}

.tree .tree-folder .tree-folder-content {
	margin-left: 23px;
	position: relative
}

.tree .tree-folder .tree-folder-content:before {
	display: inline-block;
	content: "";
	position: absolute;
	width: 1px;
	z-index: 1;
	top: -9px;
	bottom: 16px;
	left: -12px;
	border-left: 1px dotted #666;
}

.tree.tree-solid-line .tree-folder .tree-folder-content:before {
	border-left: 1px solid #999;
}

.tree.tree-no-line .tree-folder .tree-folder-content:before {
	display: none;
}

.tree .tree-item {
	position: relative;
	min-height: 20px;
	line-height: 20px;
	min-width: 100px;
	cursor: pointer
}

.tree .tree-item:hover {
	background-color: #F1F2F7;
	border-radius: 4px;
	-webkit-border-radius: 4px;
}

.tree .tree-item .tree-item-name {
	margin-left: 2px;
}

.tree .tree-folder, .tree .tree-item {
	position: relative;
}

.tree .tree-folder:before, .tree .tree-item:before {
	display: inline-block;
	content: "";
	position: absolute;
	top: 14px;
	left: -13px;
	width: 18px;
	height: 0;
	border-top: 1px dotted #666;
	z-index: 1;
}

.tree.tree-solid-line .tree-folder:before, .tree.tree-solid-line .tree-item:before {
	border-top: 1px solid #999;
}

.tree.tree-no-line .tree-folder:before, .tree.tree-no-line .tree-item:before {
	display: none;
}

.tree .tree-selected {
	background-color: #F1F2F7;
	color: #6398b0;
	border-radius: 4px;
	-webkit-border-radius: 4px;
}

.tree .tree-selected:hover {
	background-color: #e1e1e1;
	border-radius: 4px;
	-webkit-border-radius: 4px;
}

.tree .tree-item, .tree .tree-folder {
	border: 1px solid #FFF
}

.tree .tree-item, .tree .tree-folder .tree-folder-header {
	margin: 0;
	padding: 4px 5px 6px 5px;
	color: #333;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
}

.tree .tree-item .tree-item-name > i, .tree .tree-folder .tree-folder-header > i {
	color: #666;
}

.tree .tree-item.tree-selected > i, .tree .tree-item .tree-item-name > i {
	margin-left: -2px;
	width: 14px;
	display: inline-block;
	text-align: center;
	margin-right: 1px;
	color: #666;
}

.tree.tree-plus-minus .tree-folder-header .fa-folder-open:before {
	height: 16px;
	width: 16px;
	line-height: 16px;
	vertical-align: middle;
	display: inline-block;
	background: url("../img/tree-icons.png") no-repeat;
	background-position: 0 -23px;
	content: "";
}

.tree.tree-plus-minus .tree-folder-header .fa-folder:before {
	height: 16px;
	width: 16px;
	line-height: 16px;
	vertical-align: middle;
	display: inline-block;
	background: url("../img/tree-icons.png") no-repeat;
	background-position: 0 0px;
	content: "";
}

.ie .tree.tree-plus-minus .tree-folder-header .fa-folder:before, .ie .tree.tree-plus-minus .tree-folder-header .fa-folder-open:before {
	margin-top: -5px;
}

.tree.tree-plus-minus .tree-folder-name {
	margin-left: 0px !important;
}

.tree .tree-actions {
	display: none;
	position: absolute;
	margin-top: 1px;
	right: 4px;
}

.tree .tree-item:hover .tree-actions, .tree .tree-folder-header:hover .tree-actions {
	display: inline-block;
}

.tree .tree-actions > i {
	font-weight: 300;
	padding: 1px 3px;
	text-align: center;
	font-size: 14px;
	color: #999;
	margin-right: 6px;
	margin-top: 0px;
	display: inline-block;
}

.tree .tree-actions > i:hover {
	color: #666;
}
.panel-body {
	padding: 15px;
}
.panel-body:before {
	display: table;
	content: " ";
}
.panel-body:after {
	clear: both;
}
</style>
</head>
<body>

<section>
	<table style="width: 100% ;margin-top: 50px; "  >
		<colgroup>
			<col width="30%">
			<col width="70%">
		</colgroup>
		<tbody>
			<tr>
				<td>
				<div class="panel-body mtree-wrap">
					<div id="cCodeList" class="tree">
						<div class="tree-folder" style="display: block;">
							<div class="tree-folder-header">
								<i class="fa fa-folder-open"></i>
								<div class="tree-folder-name">
									Test tree 1 <div class="tree-actions"></div>
								</div>
							</div>
							<div class="tree-folder-content" style="display: block;">
								<div class="tree-folder" style="display: block;">
									<div class="tree-folder-header">
										<i class="fa fa-folder-open"></i>
										<div class="tree-folder-name">
											Test tree 1 <div class="tree-actions"></div>
										</div>
									</div>
									<div class="tree-folder-content" style="display: block;">
										<div class="tree-folder" style="display: block;">
											<div class="tree-folder-header">
												<i class="fa fa-folder"></i>
												<div class="tree-folder-name">
													Test tree 1 <div class="tree-actions"></div>
												</div>
											</div>
											<div class="tree-folder-content" style="display: none;">
												<div class="tree-folder" style="display: block;">
													<div class="tree-folder-header">
														<i class="fa fa-folder-open"></i>
														<div class="tree-folder-name">
															Test tree 1 <div class="tree-actions"></div>
														</div>
													</div>

													<div class="tree-loader" style="display: none;"><img src="images/input-spinner.gif">
													</div>
												</div>
												<div class="tree-folder" style="display: block;">
													<div class="tree-folder-header">
														<i class="fa fa-folder"></i>
														<div class="tree-folder-name">
															Test tree 2 <div class="tree-actions"></div>
														</div>
													</div>
													<div class="tree-folder-content"></div>
													<div class="tree-loader" style="display: none;"><img src="images/input-spinner.gif">
													</div>
												</div>
											</div>
											<div class="tree-loader" style="display: none;"><img src="images/input-spinner.gif">
											</div>
										</div>
										<div class="tree-folder" style="display: block;">
											<div class="tree-folder-header">
												<i class="fa fa-folder"></i>
												<div class="tree-folder-name">
													Test tree 2 <div class="tree-actions"></div>
												</div>
											</div>

											<div class="tree-loader" style="display: none;"><img src="images/input-spinner.gif">
											</div>
										</div>
									</div>
									<div class="tree-loader" style="display: none;"><img src="images/input-spinner.gif">
									</div>
								</div>
								<div class="tree-folder" style="display: block;">
									<div class="tree-folder-header">
										<i class="fa fa-folder"></i>
										<div class="tree-folder-name">
											Test tree 2 <div class="tree-actions"></div>
										</div>
									</div>

									<div class="tree-loader" style="display: none;"><img src="images/input-spinner.gif">
									</div>
								</div>
							</div>
						</div>
						<div class="tree-folder" style="display: block;">
							<div class="tree-folder-header">
								<i class="fa fa-folder"></i>
								<div class="tree-folder-name">
									Test tree 2 <div class="tree-actions"></div>
								</div>
							</div>
							<div class="tree-folder-content"></div>
						</div>
					</div>
				</div></td>
				<td>
				<div class="panel-body">
					<table class="tbl_type">
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
						<tbody id="tbodyList">
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
				</div></td>
			</tr>
		</tbody>
	</table>
</section>
<%-- 
<table>
<colgroup>
	<col width="30%" >
	<col width="70%" >
</colgroup>
<tr>
	<td>
		<div id="cCodeList" class="mtree-wrap" >
		</div>
	</td>
	<td>
		<div>
			<input class="form_date" type="text" data-date-format="yyyy-mm-dd" readonly placeholder="날짜입력" >
			<button type="button" id='btn_add'>
				<span class="glyphicon glyphicon-plus"></span> 등록
			</button>
		</div>
		
		<table class="clsBasicTbl" id="product-table">
			<colgroup>
				<col style='width: 10%;'/>
				<col style='width: 20%;'/>
													<col style='width: 22%;'/>
													<col style='width: 14%;'/>
													<col style='width: 8%;'/>
													<col style='width: 8%;'/>
													<col style='width: 18%;'/>
												</colgroup>
												<thead>
													<tr>
														<th class="table-header-repeat clsTextAlignCenter">No</th>
														<th class="table-header-repeat clsTextAlignCenter">코드</th>
														<th class="table-header-repeat clsTextAlignCenter">코드 내용</th>
														<th class="table-header-repeat clsTextAlignCenter">순서</th>
														<th class="table-header-repeat clsTextAlignCenter">시스템 코드</th>
														<th class="table-header-repeat clsTextAlignCenter">사용 여부</th>
														<th class="table-header-repeat clsTextAlignCenter list-auth">관리</th>
													</tr>
												</thead>
												<tbody id='tbodyList'>
													<tr>
														<td class='clsTextAlignCenter' colspan='7' style='height: 300px;'>
															<img src='/images/common/loader.gif' alt='로딩중' style="margin-top: 60px;"/>
														</td>
													</tr>
												</tbody>
											</table>
		
		
		
		
		
		
		
		
		

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

</table> --%>
</body>
</html>
