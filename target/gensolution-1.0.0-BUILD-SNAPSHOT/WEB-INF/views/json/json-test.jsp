<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript">
var $cloneTr;
$(document).ready(function(){
	$("#addArray").click(function(){
		addNewArray();
	});
	var defaultTr = $("#arrayTb tr:eq(0)");	
	setArrayRowEvent(defaultTr);
	addNewArrRowEvent(defaultTr.find(".row-add"));
	//기본행 복사하여 등록 클래스 제거	
	$cloneTr = defaultTr.clone();
	$cloneTr.removeClass();
	$("#sendJsonBtn").click(function(){
		sendJson(true);
	});
	$("#prevJsonBtn").click(function(){
		sendJson(false);
	});
	//선텍 히스토리 삭제
	$("#allHistory").click(function(){
		var key =$("#jurl").val();
		if(confirm(key+" 요청의 내용을 모두 삭제 하시겠습니까?")){
			var oldSaveData = {};
			try {
				oldSaveData = JSON.parse(localStorage.getItem("saveData"));
			} catch (e) {
				console.log("localStorage Error : " + e);
				
			}
			delete oldSaveData[key];
			localStorage.setItem("saveData",JSON.stringify(oldSaveData));
			setHistoryAutoComp();
			$("#jurl").val("");
		}
	});
	//정렬
	$("#addPrevSort").click(function(){
		var prevData = {};
		try {
			prevData = JSON.parse($("#prevData").val());
			$("#prevData").val(JSON.stringify(prevData, null, "\t"));
		} catch (e) {
			prevData = eval($("#prevData").val());
			$("#prevData").val(JSON.stringify(prevData, null, "\t"));
		}
	});
	//새로고침
	$("#allRefresh").click(function(){
		window.location.reload(true);
	});
	//변수값 초기화
	$("#addPramsReset").click(function(){
		$("#paramsTb").find("input").val("");
	});
	//변수값 초기화
	$("#addPramsReset").click(function(){
		$("#paramsTb").find("input").val("");
	});
	//배열값초기화
	$("#addArrayReset").click(function(){
		$("#arrayTb").find("input").val("");
	});
	//배열데이터 모두삭제
	$("#addArrayDelete").click(function(){
		$("#arrayTb").html("");
	});
	//파람 데이터 모두삭제
	$("#addPramsDelete").click(function(){
		$("#paramsTb").html("");
	});
	
	//요총겂 초기화
	$("#addPrevReset").click(function(){
		$("#prevData").val("");
	});
	//서버응답결과 초기화
	$("#addServerReset").click(function(){
		$("#returnData").val("");
	});
	//모두초기화
	$("#allReset").click(function(){
		$("#prevData").val("");
		$("#returnData").val("");
		$("#paramsTb").find("input").val("");
		$("#arrayTb").find("input").val("");
	});
	
	
	//파람복원
	$("#addPramsRecov").click(function(){
		$("#paramsTb").html($(localStorage.getItem("parmasHtml")));
		$("#paramsTb").find(".row-del").click(function(){
			$(this).parent().parent().remove();
		});
	});
	//배열복원
	$("#addArrayRecov").click(function(){
		$("#arrayTb").html($(localStorage.getItem("arrayHtml")));
		rowEventAllSet();
	});
	
	
	
	
	$("#addPrams").click(function(){
		html = "";
		html+='<tr class="row-group1" >';
		html+='	<td class="">';
		html+='		<input class="prams-key" type="text">';
		html+='	</td>';
		html+='	<td class="">';
		html+='		<input class="prams-value" type="text">';
		html+='	</td>';
		html+='	<td>';
		html+='		<button class="white row-del" ><i>-</i></button>';
		html+='	</td>';
		html+='</tr>';
		$("#paramsTb").append(html);
		
		$("#paramsTb").find(".row-del").click(function(){
			$(this).parent().parent().remove();
		});
	});
	$("#paramsTb").find("input.auto-save").keyup(function(){
		localStorage.setItem($(this).attr("id"), $(this).val());
	}).focusout(function(){
		localStorage.setItem($(this).attr("id"), $(this).val());
	});
	
	
// 	$("#jurl").keyup(function(){
// 		localStorage.setItem("jurl", $(this).val());
// 		if (event.keyCode == 13) {
// 			sendJson(true);
// 		}
// 	}).focusout(function(){
// 		localStorage.setItem("jurl", $(this).val());
// 	});
	
	$("#jurl").keyup(function(){
// 		alert(event.keyCode);
		if (event.keyCode == 38) {
			var target = $("#autoComp").find("li.selected");
// 			alert(target);
// 			alert(target.prev().hasClass("li"));
			if(target.length > 0){
				if(target.prev().hasClass("auto-url")){
					target.removeClass("selected");
					target.prev().addClass("selected");
				}
			}else{
				 $("#autoComp").find("li:first").addClass("selected");
			}
// 			$("#autoComp").find("li").next.removeClass("selected");
		}
		if (event.keyCode == 40) {
			if($("#autoComp").is(":visible")){
				var target = $("#autoComp").find("li.selected");
				if(target.length > 0){
					if(target.next().hasClass("auto-url")){
						target.removeClass("selected");
						target.next().addClass("selected");
					}
				}else{
					 $("#autoComp").find("li:first").addClass("selected");
				}
			}else{
				$("#autoComp").show();
			}
		}
		if (event.keyCode == 13) {
			if($("#autoComp").find("li.selected").length == 0){
				sendJson(true);
			}else{
				var seleUrl = $("#autoComp").find("li.selected").text();
				$(this).val(seleUrl);
				setHistoryData(seleUrl);
				$("#autoComp").find("li").removeClass("selected");
				$("#autoComp").hide();
			}
		}
	}).click(function(){
		if(!$("#autoComp").is(":visible")){
			$("#autoComp").show();
		}else{
			$("#autoComp").hide();
		}
	});
	$("#jurl").parent().mouseleave(function(){
		$("#autoComp").hide();
	});
	
	chashSetting();
	
});

function chashSetting(){
	
	var oldSaveData = {};
	try {
		oldSaveData = JSON.parse(localStorage.getItem("saveData"));
	} catch (e) {
		console.log("localStorage Error : " + e);
		return;
	}
	if(!oldSaveData){
		return;
	}
	var html ="";
// 	var firstFlag = true;
	for(var key in oldSaveData){
// 		firstUrl = key;
// 		if(firstFlag){
// 			firstFlag = false;
// 		}
		html ="<li class='auto-url'>"+key+"</li>"+html;
	}
	html ="<ul>"+html+"</ul>";
	$("#autoComp").html(html);
	var firstUrl = $("#autoComp").find(".auto-url:first").text();
	$("#jurl").val(firstUrl); 
	$("#paramsTb").html(oldSaveData[firstUrl].parmasHtml);
	$("#paramsTb").find(".row-del").click(function(){
		$(this).parent().parent().remove();
	});
	$("#arrayTb").html(oldSaveData[firstUrl].arrayHtml);
	
	$(".auto-url").click(function(){ 
		$("#jurl").val($(this).text());
		setHistoryData($(this).text());
		$("#autoComp").hide();
	});
	rowEventAllSet();
// 	$("#paramsTb").html($(localStorage.getItem("parmasHtml")));
// 	$("#paramsTb").find(".row-del").click(function(){
// 		$(this).parent().parent().remove();
// 	});
// 	$("#arrayTb").html($(localStorage.getItem("arrayHtml")));
// 	rowEventAllSet();
// 	var jurl = localStorage.getItem("jurl");
// 	if(jurl){
// 		$("#jurl").val(jurl); 
// 	}
}
function setHistoryAutoComp(){
	var oldSaveData = {};
	try {
		oldSaveData = JSON.parse(localStorage.getItem("saveData"));
	} catch (e) {
		console.log("localStorage Error : " + e);
		
	}
	var html ="<ul>";
	for(var key in oldSaveData){
		html +="<li class='auto-url'>"+key+"</li>";
	}
	html +="</ul>";
	$("#autoComp").html(html);
	$(".auto-url").click(function(){ 
		$("#jurl").val($(this).text());
		setHistoryData($(this).text());
		$("#autoComp").hide();
	});
	
}
function setHistoryData(key){
	var oldSaveData = {};
	try {
		oldSaveData = JSON.parse(localStorage.getItem("saveData"));
	} catch (e) {
		console.log("localStorage Error : " + e);
		
	}
	
	$("#paramsTb").html(oldSaveData[key].parmasHtml);
	$("#paramsTb").find(".row-del").click(function(){
		$(this).parent().parent().remove();
	});
	$("#arrayTb").html(oldSaveData[key].arrayHtml);
	
	$(".auto-url").click(function(){ 
		$("#jurl").val($(this).text());
		$("#autoComp").hide();
	});
	rowEventAllSet();
}
function rowEventAllSet(){
	var arrList = $("#arrayTb tr");
	for (var i = 0; i < arrList.length; i++) {
		var tr = arrList.eq(i);
		setArrayRowEvent(tr);
		addNewArrRowEvent(tr.find(".row-add"));
	}
}

function sendJson(send){
	$("#autoComp").hide();
	var jurl = $("#jurl").val();
	jurl = jurl == "" ? "/sendJson": jurl;
	var jtype = $("input[name='httpTpye']:checked").val();
	var atype = $("input[name='arrTpye']:checked").val();
	var dtype = $("input[name='dataTpye']:checked").val();
	var jsonData={};
	var arrayArr = $("#arrayTb").find(".array-name");
	var groupArr = [];
	var paramArr = $("#paramsTb tr");
	var pramHtml="";
	var praramkeyvalue="";
	var arraykeyvalue="";
	
	for (var i = 0; i < paramArr.length; i++) {
		var key = paramArr.eq(i).find(".prams-key").val();
		var value = paramArr.eq(i).find(".prams-value").val();
		console.log("key : " + key);
		console.log("value : " + value);
		praramkeyvalue += $.trim(key+value);
		if($.trim(key) !=""){
			jsonData[key] = value;
		}
		pramHtml+='<tr class="row-group1" >';
		pramHtml+='	<td class="">';
		pramHtml+='		<input class="prams-key" type="text" value="'+key+'" >';
		pramHtml+='	</td>';
		pramHtml+='	<td class="">';
		pramHtml+='		<input class="prams-value" type="text"  value="'+value+'" >';
		pramHtml+='	</td>';
		pramHtml+='	<td>';
		pramHtml+='		<button class="white row-del" ><i>-</i></button>';
		pramHtml+='	</td>';
		pramHtml+='</tr>';
		
	}
	if(atype == "arr"){
// 		var tempGroupId = "";
		for (var i = 0; i < arrayArr.length; i++) {
			var groupId = arrayArr.eq(i).val(); //.parent().parent().data("group");
// 			if(tempGroupId != groupId){
// 				groupArr.push(arrayArr.eq(i).val());
// 			}
			var eqFlag = true;
			for (var f = 0; f < groupArr.length; f++) {
				console.log(groupArr[f]+":"+groupId);
				if(groupArr[f] == groupId){
					eqFlag = false;
					break;
				}
			}
			if(eqFlag){
				if($.trim(arrayArr.eq(i).val()) !=""){
					groupArr.push(arrayArr.eq(i).val());
				}
			}
		}
// 		console.log("arr :"+groupArr);
		for (var x = 0; x < groupArr.length; x++) {
			var dataArr = [];
			var groupId = groupArr[x];
// 			console.log("groupId : "+groupId);
			var trArr =  $("#arrayTb").find(".array-name[data-group='"+groupId+"']");
			for (var i = 0; i < trArr.length; i++) {
				
				var clName = trArr.eq(i).parent().parent().attr("class");
				var classArr = $("tr."+clName);
				var arrayData = {};
				
				for (var f = 0; f < classArr.length; f++) {
					var objctVo = classArr.eq(f);
					var objKey = objctVo.find(".obj-key").val();
					var objValue = objctVo.find(".obj-value").val();
					if($.trim(objKey) !=""){
						arrayData[objKey] = objValue;
					}
				}
				dataArr.push(arrayData);
			}
			if($.trim(groupId) !=""){
				if($("#arrayToString").is(":checked")){
					jsonData[groupId] =JSON.stringify(dataArr);
				}else{
					jsonData[groupId] =dataArr;
				}	
			}
			
		}
	}else{
		var dataArr = {};
		for (var i = 0; i < arrayArr.length; i++) {
			var clName = arrayArr.eq(i).parent().parent().attr("class");
			var classArr = $("."+clName);
			var arrayData = {};
			var arrName = arrayArr.eq(i).val() ;
			for (var f = 0; f < classArr.length; f++) {
				var objctVo = classArr.eq(f);
				var objKey = objctVo.find(".obj-key").val();
				var objValue = objctVo.find(".obj-value").val();
				if($.trim(objKey) !=""){
					arrayData[objKey] = objValue;
				}
			}
			if($.trim(arrName) !=""){
				dataArr[arrName] = arrayData;
			}
		}
		jsonData ={"map":dataArr}
	}
	var sendData ={};
	if(dtype == "str"){
		var strparams = $("#stringParmaNm").val();
		var parmaName = $.trim(strparams) =="" ? "params":strparams;
		 sendData[parmaName] = JSON.stringify(jsonData);
		 var StringData = {};
		 StringData[parmaName] = jsonData;
		 if($("#innerHeaderPrev").is(":checked")){
			var resultData = {};
			resultData.header = {"result":1,"msg":"성공"};
			resultData.body = {"list":StringData};
			sendData = resultData;
		}
		$("#prevData").val(JSON.stringify(StringData, null, "\t"));
	}else{
		sendData = jsonData;
		if($("#innerHeaderPrev").is(":checked")){
			var resultData = {};
			resultData.header = {"result":1,"msg":"성공"};
			resultData.body = {"list":sendData};
			$("#prevData").val(JSON.stringify(resultData, null, "\t"));
		}else{
			$("#prevData").val(JSON.stringify(sendData, null, "\t"));
		}
	}
	var inputarr =  $("#arrayTb").find("input");
	for (var g = 0; g < inputarr.length; g++) {
		var value = inputarr.eq(g).val();
		inputarr.eq(g).attr("value",value);
		arraykeyvalue += $.trim(value);
	}
// 	if(jurl != ""){
// 		localStorage.setItem("jurl", $(this).val());
// 	}
// 	if(praramkeyvalue != ""){
// 		localStorage.setItem("parmasHtml", pramHtml);
// 	}
// 	if(arraykeyvalue != ""){
// 		localStorage.setItem("arrayHtml", $("#arrayTb").html());
// 	}
	
	if(jurl != ""){
		localStorage.setItem("jurl", jurl);
	}
	if(praramkeyvalue != ""){
		localStorage.setItem("parmasHtml", pramHtml);
	}
	if(arraykeyvalue != ""){
		localStorage.setItem("arrayHtml", $("#arrayTb").html());
	}
	var oldSaveData = {};
// 	var oldUrlArr = [];
	try {
		oldSaveData = JSON.parse(localStorage.getItem("saveData"));
	} catch (e) {
		console.log("localStorage Error : " + e);
		
	}
// 	for(var key in oldSaveData){
// 		console.log("key: " + key + " has value: " + oldSaveData[key].parmasHtml);
// 		console.log("key: " + key + " has value: " + oldSaveData[key].arrayHtml);
// 	}
// 	console.log("oldSaveData : " + JSON.stringify(oldSaveData));
// 	console.log("oldUrlArr : " + oldUrlArr);
	if(oldSaveData ){
		oldSaveData[""+jurl] = {"parmasHtml": pramHtml,"arrayHtml": $("#arrayTb").html()};
	}else{
		oldSaveData = {};
	}
	localStorage.setItem("saveData",JSON.stringify(oldSaveData));
	setHistoryAutoComp();
// 	localStorage.setItem("saveData",oldUrlArr);

	var fileFlag = $("#appendFiles").is(":checked");
	var formData =  new FormData();
	if(fileFlag && file[0].files[0].size > 0){
		var file = $("#filed1");
		formData.append('file', file[0].files[0]);
	}
	$.each(sendData, function(key, value){
		formData.append(key, value);
	});
	
// 	alert(sendData.toString()); 
// 	alert(formData.toString());
// 		var params = new URLSearchParams(formData);
// 		alert(params);
// 	return;
// 		$.ajaxSettings.traditional = false;
// 	}else{
// 		formData = {};
// 		formData = sendData;
// 	}
	if(send){
// 		alert( $("#paramsTb").html());
// 		localStorage.setItem("parmasHtml", $("#paramsTb").html());
		
		$.ajax({
			url : jurl,
			type : jtype,
			dataType : "json",
			data : formData,
			global: true,		
			cache: false,
	        contentType: false,
	        processData: false,
			success : function(data) {
// 				alert(data);
				var result =data; 
				if(result != ""){
					if($("#innerHeaderServer").is(":checked")){
						var resultData = {};
						resultData.header = {"result":1,"msg":"성공"};
						resultData.body = {"list":result};
						result = resultData;
					}
					$("#returnData").val(JSON.stringify(result, null, "\t"));
				}else{
					$("#returnData").val("결과가 없습니다.");
				}
			},
			complete:function(data){
				setTimeout(function(){
        			$("#ajaxLoding").remove();
        		}, 500);
				
				if(data.status == "404"){
					alert("잘못된 URL !!");
				}else if(data.status == "400"){
					alert("잘못된 인자값 !!");
				}else if(data.status != "200"){
					alert("Server error :"+ data.statusText );
				}
// 				alert("data"+JSON.stringify(data, null, "\t"))
			}
		});
	}
	
}


function addNewArray(){
	var newitem = $cloneTr.clone();
	var groupId = "1";
	if($("#arrayTb tr:last").length > 0){
		groupId = $("#arrayTb tr:last").attr("class").replace("row-group", "");
	}
	//클래스명 제거
	newitem.removeClass();
	//클래스명 생성
	newitem.addClass("row-group" + (parseInt(groupId) + 1));
	
	//각종 이벤트 추가
	setArrayRowEvent(newitem);
	
	
	newitem.find("td:eq(0)").attr("rowspan", "1");
	addNewArrRowEvent(newitem.find(".row-add"));
	
	
	$("#arrayTb").append(newitem);
}
function addNewArrRowEvent(obj){
	
	$(obj).click(function(){
		var clickedRow = $(this).parent().parent();
		var classNm = clickedRow.attr("class");
		// tr 복사해서 마지막에 추가
		var newrow = clickedRow.clone();
		newrow.find("td:eq(0)").remove();
		newrow.data("si-seq","");
		setArrayRowEvent(newrow);
		
		// 추가버튼 삭제
		newrow.find(".row-add").remove();
		
		var prantTr = $("#arrayTb ."+classNm+":visible" );
		var planend = prantTr.last().find(".plan-end").val();
		planend = planend == ""? "": parseInt(planend)+1;
		newrow.find(".clear").val("");
		newrow.find(".clear-text").html("");
		newrow.insertAfter(prantTr.last());
		
		reSizingRowspan(classNm);
	});
}

function reSizingRowspan(classNm){
	var rows =$("#arrayTb ." + classNm+":visible");
	var rowspan = rows.length;
	rows.first().children("td:eq(0)").attr("rowspan", rowspan);
}
function setArrayRowEvent(tr){
	tr.find(".array-name").keyup(function(){
		$(this).parent().parent().attr("data-group",$(this).val());
		$(this).attr("data-group",$(this).val());
	}).focusout(function(){
		$(this).parent().parent().attr("data-group",$(this).val());
		$(this).attr("data-group",$(this).val());
		
	});
	
	// 행삭제
	tr.find(".row-del").click(function(){
		var clickedRow = $(this).parent().parent();
		clickedRow.data("del-flag","Y");
		var classNm = clickedRow.attr("class");
		if (clickedRow.find("td:eq(0)").attr("rowspan")) {
			var friendRow = clickedRow.nextAll("."+classNm);
			var friendLen = friendRow.length;
			for (var i = 0; i < friendLen; i++) {
				if (friendRow.eq(i).hasClass(classNm)) {
					friendRow.eq(i).prepend(clickedRow.find("td:eq(0)"));
					friendRow.eq(i).find("td:last-child").prepend($(this).prev());
					i= friendLen;
					break;
				}
			}
		}
		clickedRow.remove();
		reSizingRowspan(classNm);
	});
}
(function($) {
	$( document ).ajaxStart( function() {
//		$("html > body").css({position:"fixed"});
    	var ajaxScreen = $("<div id='ajaxLoding' />");
    	var height = $("#layerPopSpace").height();
    	var scrollTop = $("#topBody").scrollTop();
    	ajaxScreen.css({
    		position:"absolute",
    		left:"0px",
    		top:"0px",
    		width:"100%",
    		height:height+"px",
    		"z-index": "9999"
    	});
    	var view = $("<div />");
    	view.css({
    		position:"absolute",
    		left:$(document).width()/2 - 100/2,
    		top:(screen.availHeight/2 -100)+scrollTop ,
//    		width:100,
    		padding:20,
    		"border-radius": 10,
    		"background-color":"rgba(0,0,0,0.7)",
    		"text-align":"center",
    	});
//    	var ajaxIcon = $("<img src='/resources/images/ajax-loader-bar.gif'/>");
    	var ajaxIcon = $("<img src='/resources/images/712.GIF'/>");
    	view.append(ajaxIcon);
    	ajaxScreen.append(view);
    	$("html > body").append(ajaxScreen);
	});
})(jQuery);
</script>
<style type="text/css">
.arraytb{
	margin:10px 0 10px 0; width: 100%; 
  border-collapse: 0;
  border-spacing: 0;
  border-bottom: 1px solid #ccc;
}
.arraytb th{
  border: 1px solid #ccc;
  border-bottom: none;
  border-right: none;
}
#arrayTb input, #paramsTb input,#filesTb input{
width: 100%;
}
#arrayTb td, #paramsTb td,#filesTb td{
  text-align: center;
  padding: 2px 3px 2px 3px;
  border: 1px solid #ccc;
  border-bottom: none;
  border-right: none;
}
#arrayTb td:last-child, #paramsTb td:last-child, #filesTb td:last-child{
  border-right: 1px solid #ccc;
}
.arraytb th:last-child{
  border-right: 1px solid #ccc;
}
#autoComp{
	position: absolute; background-color: #FFF; border: 1px solid #ccc; width: 495px;
	padding: 0;
	margin: 0;
 	display: none; 
}
#autoComp ul{
	padding: 0;
	margin: 0;
    background-color: #F5F5F5;
}
#autoComp li{
	line-height:20px;
	height:20px;
	list-style: none;
	padding: 5px;
	cursor: pointer;
}
#autoComp li:HOVER{
    background-color: #dfdfdf;
}
#autoComp li.selected{
background-color: #ccc;
}

</style>
</head>
<body>
<h1 style="text-align: center;margin: 0;height: 37px;" >JSON TEST</h1>
<table  style="width: 100%">
	<colgroup>
		<col width="200px" >
		<col width="90%" >
	</colgroup>
	<tr>
		<td>전송방식</td>
		<td>
			<input type="radio" id="post" value="POST" name="httpTpye" checked  ><label for="post" >POST</label>  
			<input type="radio" id="get" value="GET"  name="httpTpye"><label for="get" >GET</label>  
		</td>
	</tr>
	<tr>
		<td style="position: relative;" >URL</td>
		<td><input type="text" size="70" id="jurl"  value="/sendJson" > 
		<button type="button" id="prevJsonBtn" >미리보기</button> 
		<button type="button" id="sendJsonBtn" >전송</button>
		<button type="button" id="allReset" >아래초기화</button>
		<button type="button" id="allRefresh" >새로고침</button>
		<button type="button" id="allHistory" >히스토리 삭제</button>
			<div id="autoComp" >
				<ul >
				</ul>
			</div>
		</td>
	</tr>
	<tr>
		<td>배열유형</td>
		<td>
			<input type="radio" id="arrtype" value="arr" name="arrTpye" checked  ><label for="arrtype" >[ &nbsp;] / Array</label>  
			<input type="radio" id="keytype" value="key"  name="arrTpye"><label for="keytype" >{ &nbsp;} / Map</label>  
		</td>
	</tr>
	<tr>
		<td>데이터유형</td>
		<td>
			<input type="radio" id="objtype" value="obj" name="dataTpye" checked  ><label for="objtype" >객체</label>  
			<input type="radio" id="stringtype" value="str"  name="dataTpye"><label for="stringtype" >문자</label>  
			<input type="text"  id="stringParmaNm" value="" placeholder="String 변수명(기본 params)">
			* 최종 전송데이터 저장됨. 변수값없을경우 저장안됨
		</td>
	</tr>
	<tr>
		<td>Params</td>
		<td>
			<table class="arraytb" style="" >
				<colgroup >
					<col width="37%">
					<col width="38%">
					<col width="25%">
				</colgroup>
				<tr>
					<th>KEY</th>
					<th>VALUE</th>
					<th>
						<button class='white' id="addPrams" >파라메터추가</button> 
						<button class='white' id="addPramsReset" >리셋</button> 
						<button class='white' id="addPramsDelete" >삭제</button> 
						<button class='white' id="addPramsRecov" title="최근전송데이터 복원" >복원</button> 
					</th>
				</tr>
				<tbody id="paramsTb" >
					<tr >
						<td class="">
							<input id="pramk1" class="prams-key auto-save" type="text" value="om_code" >
						</td>
						<td class="">
							<input id="pramv1" class="prams-value auto-save" type="text" value="00001" >
						</td>
						<td>
							<button class="white row-del" ><i>-</i></button>
						</td>
					</tr>
					<tr>
						<td class="">
							<input id="pramk2" class="prams-key auto-save" type="text" value="sm_code" >
						</td>
						<td class="">
							<input id="pramv2" class="prams-value auto-save" type="text" value="00015" >
						</td>
						<td>
							<button class="white row-del" ><i>-</i></button>
						</td>
					</tr>
					<tr>
						<td class="">
							<input id="pramk3" class="prams-key auto-save" type="text" value="" >
						</td>
						<td class="">
							<input id="pramv3" class="prams-value auto-save" type="text" value="" >
						</td>
						<td>
							<button class="white row-del" ><i>-</i></button>
						</td>
					</tr>
					<tr>
						<td class="">
							<input id="pramk4" class="prams-key auto-save" type="text" value="" >
						</td>
						<td class="">
							<input id="pramv4" class="prams-value auto-save" type="text" value="" >
						</td>
						<td>
							<button class="white row-del" ><i>-</i></button>
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td>파일</td>
		<td>
			<table class="arraytb" style="" >
				<colgroup >
					<col width="37%">
					<col width="38%">
					<col width="25%">
				</colgroup>
				<tr>
					<th>파일(전송 <input type="checkbox" id="appendFiles" >)</th>
					<th>Key</th>
					<th>
						<button class='white' id="addFiles" >파일추가</button> 
						<button class='white' id="addFilesReset" >리셋</button> 
						<button class='white' id="addFilesDelete" >삭제</button>  
					</th>
				</tr>
				<tbody id="filesTb" >
					<tr >
						<td class="">
							<input id="filed1" class="files-data" type="file" value="" >
						</td>
						<td class="">
							<input id="filek1" class="files-key" type="text" value="file" >
						</td>
						<td>
							<button class="white row-del" ><i>-</i></button>
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td>배열데이터</td>
		<td>
			<table class="arraytb" style="" >
				<colgroup >
					<col width="25%">
					<col width="25%">
					<col width="25%">
					<col width="25%">
				</colgroup>
				<tr>
					<th>Array(String <input type="checkbox" id="arrayToString" >)</th>
					<th>KEY</th>
					<th>VALUE</th>
					<th>
						<button class='white' id="addArray" >배열추가</button>
						<button class='white' id="addArrayReset" >리셋</button> 
						<button class='white' id="addArrayDelete" >삭제</button> 
						<button class='white' id="addArrayRecov"  title="최근전송데이터 복원" >복원</button> 
					</th>
				</tr>
				<tbody id="arrayTb" >
					<tr class="row-group1" data-group="">
						<td rowspan="1" class="array-td1">
							<input class="array-name" type="text" value="">
						</td>
						<td><input class="obj-key" type="text" value=""></td>
						<td><input class="obj-value" type="text" value=""></td>
						<td>
							<button class="white row-add" ><i>+</i></button>
							<button class="white row-del" ><i>-</i></button>
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<td>결과</td>
		<td>
			<table  style="width: 100%" >
				<tr>
					<th >요청(해더,바디 생성 <input type="checkbox" id="innerHeaderPrev" >)
						<button class='white' id="addPrevReset" >리셋</button> 
						<button class='white' id="addPrevSort" >정렬</button> 
					</th>
					<th >서버 응답 결과(해더,바디 생성 <input type="checkbox" id="innerHeaderServer" >) 
						<button class='white' id="addServerReset" >리셋</button> 
					</th>
				</tr>
				<tr>
					<td ><textarea id="prevData" rows="30" cols="60" style="width: 100%" ></textarea> </td>
					<td ><textarea id="returnData" rows="30" cols="60" style="width: 100%"></textarea> </td>
				</tr>
			</table>
		</td>
	</tr>
<!-- 	<tr>		 -->
<!-- 		<td >미리보기</td> -->
<!-- 		<td ><textarea id="prevData" rows="8" cols="120"></textarea> </td> -->
<!-- 	</tr> -->
<!-- 	<tr>		 -->
<!-- 		<td >서버 응답 결과</td> -->
<!-- 		<td ><textarea id="returnData" rows="25" cols="120"></textarea> </td> -->
<!-- 	</tr> -->
	
</table>
</body>
</html>
