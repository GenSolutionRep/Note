/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */

/*
jQuery.fn.userFunction = function(options){
	var op = $.extend({
		fn1 : "fn1",
		fn2 : function(){}
	}, options);
	
	
	$.ajax({
		url : "/code/codeTree",
		data:{"c_code":""},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {
			op.fn2(data);
		}
	});
	
	
}
*/


$(document).ready(function(){
	
	//fnMakeEventComponent();	// 버튼 이벤트 등록
//	fnGetTestList("파라메터 잘넘어갑니다...");
	
		/*
	$(".form_date").datetimepicker({
		language:	'ko',
				weekStart: 1,
				todayBtn:	1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
		});
//	fnGetMenuList("1111111111");
*/
	//$("#btn_add").userFunction({fn1:"asd",fn2:function(ccc){alert(ccc)}});
	
	
	
	
//	$("#cCodeList").fTree({
//		url:"/code/codeTree",
//		root:true,
//		key:"c_code",
//		val:"c_name",
//		className:"tree",
//		data:{"user_id":"123"},
//		click:  function(btn){
//			alert("1111111111");
//			console.log("data3 : " + btn.code)
//		}
//    });
//	fnGetTreeList("");
	fnGetSelectCodeList("");
//
//	fnGetCodeRowByParentCode("", "");
	
	$("#btn_add").click(function(){
		
	});
});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent(){
	// 팝업창 닫기
	$("#closeBtn").click(function(){
	});
	
	// 저장 버튼
	$("#saveBtn").click(function(){
	});
}

function abcd(c_code){
	alert(c_code);
}

function fnGetTreeList(c_code){
	$.ajax({
		url : "/code/codeTree",
		data:{"c_code":c_code},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {
			
			/*
			var tempDepth = 1;
			var html = '<ul class="mtree transit"><li><a href="#" onclick="fnGetSelectCodeList(\'\');">최상위코드</a><ul>';
//			var html = '<ul class="mtree transit">';
			for (var i = 0; i < data.result.length; i++) {
				var depth = data.result[i].c_level;
				if(tempDepth == depth){
					html += '<li><a href="#" onclick="fnGetSelectCodeList(\'' + data.result[i].c_code + '\');">'+data.result[i].c_name+'</a>';
					tempDepth = depth;
				}else if(tempDepth < depth){
					html += "<ul>";
					html += '<li><a href="#" onclick="fnGetSelectCodeList(\'' + data.result[i].c_code + '\');">'+data.result[i].c_name+'</a>';
					tempDepth = depth;
				}else{
					var gap = tempDepth - depth;
					for (var f = 0; f < gap; f++) {
						html += "</li></ul>";
					}
					html += '<li><a href="#" onclick="fnGetSelectCodeList(\'' + data.result[i].c_code + '\');">'+data.result[i].c_name+'</a>';
					tempDepth = (tempDepth - gap);
				}
				
			}
			
			var gap2 = tempDepth - 1;
			
			for(var g=0; g<tempDepth-1; g++){
				html += "</li></ul>";
			}
			
			html += "</li></ul></li></ul>";
//			html += "</li></ul>";
			$("#menuList").html(html);
			*/
			alert(1);
			var topUl = $("<ul>", {class:"mtree transit"});
			
			var val = null;

			var innerUl = topUl;
			var innerLi = innerLi = $("<li>", {});
			
			for (var i = 0; i < data.result.length; i++) {
				val = data.result[i].c_code;
				var depth = data.result[i].c_level;
				var nextDepth = 1;
				
				if(i+1 < data.result.length){
					nextDepth = data.result[i+1].c_level;
				}else{
					innerLi = $("<li>", {});
				}
				
				var innerA = $("<a>"
					, {
						href:"#"
						, click:function(e){
							alert(val);
						}
						, text:data.result[i].c_name
					});
				
				innerUl.append(innerLi);
				innerLi.append(innerA);
				
				if(i+1 < data.result.length){
					if(depth == nextDepth){
						innerLi = $("<li>", {});
					}else if(depth < nextDepth){
						var innerInnerLi = $("<li>", {});
						
						innerUl = $("<ul>", {});
						innerLi.append(innerUl);
						innerUl.append(innerInnerLi);
						
						innerLi = innerInnerLi;
					}else if(depth > nextDepth){
						for(var j=0; j<depth-nextDepth; j++){
							innerLi = $(innerUl).parent();
							innerUl = $(innerLi).parent();
						}
						
						innerLi = $("<li>", {});
					}
					
				}
			}
			$("#cCodeList").html(topUl);
			alert($("#cCodeList").html());
			
			setMtree();
		}
	});
	
}

function fnGetSelectCodeList(c_code)
{
	try{
		$.ajax(
							 {
								url: "/code/codeList"
							, type: "POST"
							, dataType : "json"
							, data: {"c_code":c_code}
							, cache: false // 캐시 사용 안함
								, beforeSend: function(xhr)
															{
																xhr.overrideMimeType("text/plain; charset=utf-8");
															}
							 }
				).done(
						function(data)
						{
							//var json_obj = $.parseJSON(data);
							var html_text = '';
							if(data.result.length > 0){
								for (var i=0; i<data.result.length; i++){
									var c_code = data.result[i].c_code;
									var c_name = data.result[i].c_name;
									var c_desc = data.result[i].c_desc;
									var c_parent_code = data.result[i].c_parent_code;
									var c_is_system_code = data.result[i].c_is_system_code;
									var c_is_use = data.result[i].c_is_use;
									var c_order = data.result[i].c_order;
									var child_cnt = data.result[i].child_cnt;
									//var col_tmp_order = data.result[i].col_tmp_order;
									
									html_text += '<tr>';
									html_text += '<td style="width:30px;text-align:center">'+(i+1)+'</td>';
									html_text += '<td style="width:120px;text-align: center;">'+c_code+'</td>';
									html_text += '<td style="width:140px;text-align: center;">'+c_name+'</td>';
									html_text += '<td style="width:30px;text-align: center;">'+c_order+'</td>';
									html_text += '<td style="width:60px;text-align: center;">'+c_is_system_code+'</td>';
									html_text += '<td style="width:60px;text-align: center;">'+c_is_use+'</td>';
									html_text += '<td class="auth-group" style="width:120px;text-align: center;">';
									html_text += '<button type="button" class="textbnt" value="수정" onclick="fnUpdateCode(\''+c_code+'\');">';
										html_text += '<span>';
										html_text += '<i class="fa fa-pencil-square-o"></i>수정';
										html_text += '</span>';
										html_text += '</button>';
										html_text += '<button type="button" class="textbnt" value="삭제" onclick="fnDeleteCode(\''+c_code+'\',\''+child_cnt+'\');">';
										html_text += '<span>';
										html_text += '<i class="fa fa-trash-o"></i>삭제';
										html_text += '</span>';
										html_text += '</button>';
									html_text += '</td>';
									html_text += '</tr>';
								}
							}else{
								html_text += '<tr><td colspan="7">조회된 데이터가 없습니다.</td></tr>';
							}
							$("#tbodyList").html(html_text);
							//auth();
							$("#c_parent_code").val(c_code);
						});
	}catch (e){
			alert(e.message);
	}
}

function fnUpdateCode(c_code)
{
	try{
		$.ajax(
							 {
								url: "/code/codeRow"
							, type: "POST"
							, dataType : "json"
							, data: {"c_code":c_code}
							, cache: false // 캐시 사용 안함
								, beforeSend: function(xhr)
															{
																xhr.overrideMimeType("text/plain; charset=utf-8");
															}
							 }
				).done(
						function(data)
						{
							
							
							
							
							
							$("#c_code").val(data.c_code);
								$("#c_name").val(data.c_name);
									$("#c_desc").val(data.c_desc);
									$("#c_order").val(data.c_order);
									$("input:radio[name=c_is_system_code]:input[value=" + data.c_is_system_code + "]").attr("checked", true);
									$("input:radio[name=c_is_use]:input[value=" + data.c_is_use + "]").attr("checked", true);
									$("#parent_name_list").html(data.c_parent_name_list);
									$("#c_parent_code").val(data.c_parent_code);
									/*
									var depth = data.c_depth;
									
									if(depth == 1){
										//fnGetCodeRowByParentCode("", "");
										$("#c_parent_code1").val(data.c_code);
									}else if(depth == 2){
										$("#c_parent_code1").val(data.c_parent_code);
									}
									*/
									
									/*
									if (depth == 2) {
										$("#cc_parent_code1").val(firstCode);
										make_depth_select(1, firstCode, 0, child_cnt);		
									} else if (depth == 3) {
										$("#cc_parent_code1").val(firstCode);
										make_depth_select(1, firstCode, parent_code, child_cnt);
									}
									
									if (child_cnt > 0) {
										$("#cc_parent_code1").attr("disabled", true);
										$("#cc_parent_code2").attr("disabled", true);
									} else {
										$("#cc_parent_code1").attr("disabled", false);
										$("#cc_parent_code2").attr("disabled", false);
									}
									*/
							
							
							
							/*
							//var json_obj = $.parseJSON(data);
							var html_text = '';
							if(data.result.length > 0){
								for (var i=0; i<data.result.length; i++){
									var c_code = data.result[i].c_code;
									var c_name = data.result[i].c_name;
									var c_desc = data.result[i].c_desc;
									var c_parent_code = data.result[i].c_parent_code;
									var c_depth = data.result[i].c_depth;
									var c_is_system_code = data.result[i].c_is_system_code;
									var c_is_use = data.result[i].c_is_use;
									var c_order = data.result[i].c_order;
									var child_cnt = data.result[i].child_cnt;
									//var col_tmp_order = data.result[i].col_tmp_order;
									
									html_text += '<tr>';
									html_text += '<td style="width:30px;text-align:center">'+(i+1)+'</td>';
									html_text += '<td style="width:120px;text-align: center;">'+c_code+'</td>';
									html_text += '<td style="width:140px;text-align: center;">'+c_name+'</td>';
									html_text += '<td style="width:30px;text-align: center;">'+c_order+'</td>';
									html_text += '<td style="width:60px;text-align: center;">'+c_is_system_code+'</td>';
									html_text += '<td style="width:60px;text-align: center;">'+c_is_use+'</td>';
									html_text += '<td class="auth-group" style="width:120px;text-align: center;">';
									html_text += '<button type="button" class="textbnt" value="수정" onclick="fnUpdateCode(\''+c_code+'\',\''+child_cnt+'\');">';
										html_text += '<span>';
										html_text += '<i class="fa fa-pencil-square-o"></i>수정';
										html_text += '</span>';
										html_text += '</button>';
										html_text += '<button type="button" class="textbnt" value="삭제" onclick="fnDeleteCode(\''+c_code+'\',\''+child_cnt+'\');">';
										html_text += '<span>';
										html_text += '<i class="fa fa-trash-o"></i>삭제';
										html_text += '</span>';
										html_text += '</button>';
									html_text += '</td>';
									html_text += '</tr>';
								}
							}else{
								html_text += '<tr><td colspan="7">조회된 데이터가 없습니다.</td></tr>';
							}
							$("#tbodyList").html(html_text);
							*/
							
							$(".QTPopup").animate({
											 width : 'show'
										 }, 'slow');
						});
	}catch (e){
			alert(e.message);
	}
}

function fnDeleteCode(c_code, child_cnt){
	if(child_cnt > 0){
		alert("하위코드가 있는 코드는 삭제 할 수 없습니다.");
		return;
	}
	
	if (!confirm("삭제 하시겠습니까?")){
		return;
	}
	
	var params =	{"c_code":c_code};
	$.ajax(
						 {
							url: "/code/deleteCode"
						, type: "POST"
						, data: params
						, cache: false // 캐시 사용 안함
							, beforeSend: function(xhr)
							{
								xhr.overrideMimeType("text/plain; charset=utf-8");
							}
						 }
			).done(
					function(strResult){
						if(strResult > 0){
							alert("삭제 성공하였습니다.");
							fnGetSelectCodeList("");
							fnGetTreeList("");
						}else{
							alert("삭제 실패하였습니다.");
						}
					});
}

function fnGetCodeRowByParentCode(c_code, c_parent_code, child_cnt) {
	try{
		$.ajax(
							 {
								url: "/code/codeRowByParentCode"
							, type: "POST"
							, dataType : "json"
							, data: {"c_parent_code":c_parent_code}
							, cache: false // 캐시 사용 안함
								, beforeSend: function(xhr)
															{
																xhr.overrideMimeType("text/plain; charset=utf-8");
															}
							 }
				).done(
						function(data)
						{
							var html = "";
							
							/*
							if(c_depth == 1){
								html = "";
								
								$("#code_select2").hide();
								$("#code_select3").hide();
								html += "<option value=''>최상위코드</option>";
								
								for(var i=0; i<data.result.length; i++){
									html += "<option value='" + data.result[i].c_code + "'>" + data.result[i].c_name + "</option>";
								}
								
								$("#c_parent_code1").html(html);
							}else  if(c_depth == 2){
								html = "";
								
								$("#code_select2").show();
								$("#code_select3").hide();
								html += "<option value=''>최상위코드</option>";
								
								for(var i=0; i<data.result.length; i++){
									html += "<option value='" + data.result[i].c_code + "'>" + data.result[i].c_name + "</option>";
								}
								
								$("#c_parent_code2").html(html);
							}
							*/
							
							

							
							/*
							
							$("#c_code").val(data.c_code);
								$("#c_name").val(data.c_name);
									$("#c_desc").val(data.c_desc);
									$("#c_order").val(data.c_order);
									$("input:radio[name=c_is_system_code]:input[value=" + data.c_is_system_code + "]").attr("checked", true);
									$("input:radio[name=c_is_use]:input[value=" + data.c_is_use + "]").attr("checked", true);
									
									var depth = data.c_depth;
									if (depth == 2) {
										$("#cc_parent_code1").val(firstCode);
										make_depth_select(1, firstCode, 0, child_cnt);		
									} else if (depth == 3) {
										$("#cc_parent_code1").val(firstCode);
										make_depth_select(1, firstCode, parent_code, child_cnt);
									}
									
									if (child_cnt > 0) {
										$("#cc_parent_code1").attr("disabled", true);
										$("#cc_parent_code2").attr("disabled", true);
									} else {
										$("#cc_parent_code1").attr("disabled", false);
										$("#cc_parent_code2").attr("disabled", false);
									}
									*/
							
							
							
							/*
							//var json_obj = $.parseJSON(data);
							var html_text = '';
							if(data.result.length > 0){
								for (var i=0; i<data.result.length; i++){
									var c_code = data.result[i].c_code;
									var c_name = data.result[i].c_name;
									var c_desc = data.result[i].c_desc;
									var c_parent_code = data.result[i].c_parent_code;
									var c_depth = data.result[i].c_depth;
									var c_is_system_code = data.result[i].c_is_system_code;
									var c_is_use = data.result[i].c_is_use;
									var c_order = data.result[i].c_order;
									var child_cnt = data.result[i].child_cnt;
									//var col_tmp_order = data.result[i].col_tmp_order;
									
									html_text += '<tr>';
									html_text += '<td style="width:30px;text-align:center">'+(i+1)+'</td>';
									html_text += '<td style="width:120px;text-align: center;">'+c_code+'</td>';
									html_text += '<td style="width:140px;text-align: center;">'+c_name+'</td>';
									html_text += '<td style="width:30px;text-align: center;">'+c_order+'</td>';
									html_text += '<td style="width:60px;text-align: center;">'+c_is_system_code+'</td>';
									html_text += '<td style="width:60px;text-align: center;">'+c_is_use+'</td>';
									html_text += '<td class="auth-group" style="width:120px;text-align: center;">';
									html_text += '<button type="button" class="textbnt" value="수정" onclick="fnUpdateCode(\''+c_code+'\',\''+child_cnt+'\');">';
										html_text += '<span>';
										html_text += '<i class="fa fa-pencil-square-o"></i>수정';
										html_text += '</span>';
										html_text += '</button>';
										html_text += '<button type="button" class="textbnt" value="삭제" onclick="fnDeleteCode(\''+c_code+'\',\''+child_cnt+'\');">';
										html_text += '<span>';
										html_text += '<i class="fa fa-trash-o"></i>삭제';
										html_text += '</span>';
										html_text += '</button>';
									html_text += '</td>';
									html_text += '</tr>';
								}
							}else{
								html_text += '<tr><td colspan="7">조회된 데이터가 없습니다.</td></tr>';
							}
							$("#tbodyList").html(html_text);
							*/
							
							$(".QTPopup").animate({
											 width : 'show'
										 }, 'slow');
						});
	}catch (e){
			alert(e.message);
	}
	
	
	
	
	
	
}