// option11 // 행사매대현황코드
// option12 // 행사매대현황코드
// option21 // POG 및 현재 진열현황
// option22 // POG 및 현재 진열현황
// option31 // 보조진열현황
// option32 // 보조진열현황
// option41 // PD 매대현황
// option42 // PD 매대현황

var options10200=null;

/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	$.get('/fixing/options.json',function(data){
		options10200 = eval(data);
		console.log(options10200);
	});
//	console.log(options10200);
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#activityFixingPop210200").instancePopUp();
	$("#activityFixingEVNOptionPop210200").instancePopUp();
	$("#activityFixingPOGOptionPop210200").instancePopUp();
	$("#activityFixingBIGOptionPop210200").instancePopUp();
	$("#searchToDate").winCal(baseOptions);	
	$("#searchFromDate").winCal(baseOptions);
	
	// 이벤트 등록
	fnSetEventComponent();	
});




/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	//텝버튼
	$(".tbl_tab.event ul li").click(function(){
		if(!$(this).hasClass("tab_on")){
			var groupId =  $(this).data("group");
			var target = $(this).data("target");
			$(".tbl_tab.event ul li").removeClass("tab_on");
			$("."+groupId+"-group").hide();
			
			$(this).addClass("tab_on");
			$("."+groupId+"-"+target+"-group").show();
		}
	});
	
	
	//업무일지 열기
	$(".p_memo").click(function(){
		$("#activityFixingPop210200").show();
	});
	
	//업무일지 닫기
	$("#activityFixingPopClose10200, #activityFixingPopCloseX10200").click(function(){
		$("#activityFixingPop210200").hide();
	});
	
	
	
	//POG및 현제 진열 줄수 옵션창 열기
	$("#evnOptionOpenBtn").click(function(){
		$("#activityFixingEVNOptionPop210200").show();
		fnGetOptionList(options10200["option11"]);
		fnGetOptionList(options10200["option12"]);
	});
	
	//POG및 현제 진열 줄수 옵션창 열기
	$("#pogOptionOpenBtn").click(function(){
		$("#activityFixingPOGOptionPop210200").show();
		fnGetOptionList(options10200["option21"]);
		fnGetOptionList(options10200["option22"]);
	});
	
	//보조진열현황 옵션창 열기
	$("#bigOptionOpenBtn").click(function(){
		$("#activityFixingBIGOptionPop210200").show();
		fnGetOptionList(options10200["option31"]);
		fnGetOptionList(options10200["option32"]);
	});
	
	
	
	//POG및 현제 진열 줄수 옵션창 닫기
	$("#pogOptionCloseXBtn").click(function(){
		$("#activityFixingPOGOptionPop210200").hide();
	});
	//보조진열현황 옵션창창 닫기
	$("#bigOptionCloseXBtn").click(function(){
		$("#activityFixingBIGOptionPop210200").hide();
	});
	
	
	
	//아코디언 펼치치 / 닫기
	$("#slideView").find(".toogle-slide").click(function(){
		var $this = $(this);
		var target = $this.data("target");
		$this.parent().toggleClass("aco-selectd");
		$("#"+target).slideToggle(function(){
			$this.find("i").toggleClass("fa-minus-square");
		});
			
	});
	
	
	//POG및 현제 진열 줄수  옵션창 비교업체 행추가
	$("#entAddRow10200").click(function(){
		fnMakeOptionAppend(options10200["option21"]);
	});
	//POG및 현제 진열 줄수  옵션창 품목 행추가
	$("#prdAddRow10200").click(function(){
		fnMakeOptionAppend(options10200["option22"]);
	});
	
	//보조진열현황  옵션창 비교업체 행추가
	$("#bigEntAddRow10200").click(function(){
		fnMakeOptionAppend(options10200["option31"]);
	});
	//보조진열현황  옵션창 품목 행추가
	$("#bigPrdAddRow10200").click(function(){
		fnMakeOptionAppend(options10200["option32"]);
	});
	
	
	
	
	
	
	//POG및 현제 진열 줄수  옵션창 비교업체 저장
	$("#entSaveRow10200").click(function(){
		fnSaveEntOption(options10200["option21"]);
	});
	//POG및 현제 진열 줄수  옵션창 품목 저장
	$("#prdSaveRow10200").click(function(){
		fnSavePrdOption(options10200["option22"]);
	});
	//보조진열현황  옵션창 비교업체 저장
	$("#bigEntSaveRow10200").click(function(){
		fnSaveEntOption(options10200["option31"]);
	});
	//보조진열현황  옵션창 품목 저장
	$("#bigPrdSaveRow10200").click(function(){
		fnSavePrdOption(options10200["option32"]);
	});
}

//옵션가져오기 공통
function fnGetOptionList(options){
	$.ajax({
		url : "/fixing/pogOptionList",
		type : "POST",
		data : {
			"optionCode":options.code
			},
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
			var html = "";
			for (var i = 0; i < data.optionList.length; i++) {
				var optionVo =  data.optionList[i];
				var code = optionVo.code; 
				var useAt = optionVo.use_at;
				var defaultAt = optionVo.default_at;
				var group = options.groupId;
				if(code == ""){
					html +='<tr>';
					html +='<td>'+optionVo.nike_nm+'</td>';
					html +='<td>'+optionVo.nm+'</td>';
					html +='<td>'+optionVo.sort_ordr+'</td>';
					html +='<td>';
					html +='	<div class="inputCheck">';
					html +='		<input type="checkbox" id="'+group+'DefaultCheckOld'+i+'" '+(defaultAt=="Y"? "checked":"")+' disabled  >';
					html +='		<label for="'+group+'DefaultCheckOld'+i+'"></label>';
					html +='	</div>';
					html +='</td>';
					html +='<td>';
					html +='	<div class="inputCheck">';
					html +='		<input type="checkbox" id="'+group+'UseCheckOld'+i+'" '+(useAt=="Y"? "checked":"")+'  disabled  >';
					html +='		<label for="'+group+'UseCheckOld'+i+'"></label>';
					html +='	</div>';
					html +='</td>';
					html +='</tr>';
				}else{
					html +='<tr class="'+group+'-add-group" data-code="'+code+'" >';
					html +='<td><input type="text" class="nike-nm" value="'+optionVo.nike_nm+'" ></td>';
					html +='<td><input type="text" class="nm"  value="'+optionVo.nm+'"  ></td>';
					html +='<td><input type="text" class="sort"  value="'+optionVo.sort_ordr+'"  ></td>';
					html +='<td>';
					html +='	<div class="inputCheck">';
					html +='		<input type="checkbox" class="default" id="'+group+'DefaultCheckOld'+i+'" disabled  >';
					html +='		<label for="'+group+'tDefaultCheckOld'+i+'"></label>';
					html +='	</div>';
					html +='</td>';
					html +='<td>';
					html +='	<div class="inputCheck">';
					html +='		<input type="checkbox" class="use"  id="'+group+'UseCheckOld'+i+'" '+(useAt=="Y"? "checked":"")+'    >';
					html +='		<label for="'+group+'UseCheckOld'+i+'"></label>';
					html +='	</div>';
					html +='</td>';
					html +='</tr>';
				}
//				prdAppendId++;
			}
			$("#"+options.target).html(html);	
		}
	});
}

//행추가 공통
function fnMakeOptionAppend(options){
	var maxCnt = options.maxCnt
	var rowSize = $("#"+options.target +" > tr").length;
	var group = options.groupId;
	alert(rowSize);
	if((rowSize > maxCnt -1) && maxCnt > 0){
		alert("이미 최대갯수 입니다.");
		return;
	}
	var html ="";
	html +='<tr class="'+group+'-add-group"  data-code="" >';
	html +='<td><input type="text" class="nike-nm" ></td>';
	html +='<td><input type="text" class="nm" ></td>';
	html +='<td><input type="text" class="sort" ></td>';
	html +='<td>';
	html +='	<div class="inputCheck">';
	html +='		<input type="checkbox" class="default"  value="None" id="'+group+'DefaultCheck'+rowSize+'" disabled >';
	html +='		<label for="entDefaultCheck'+rowSize+'"></label>';
	html +='	</div>';
	html +='</td>';
	html +='<td>';
	html +='	<div class="inputCheck">';
	html +='		<input type="checkbox" class="use"  value="None" id="'+group+'UseCheck'+rowSize+'" >';
	html +='		<label for="'+group+'UseCheck'+rowSize+'"></label>';
	html +='	</div>';
	html +='</td>';
	html +='</tr>';
	$("#"+options.target ).append(html);	
}

//첫번째 옵션 저장
function fnSaveEntOption(options){
	var group = options.groupId;
	var entAddGroup = $("."+group+"-add-group");
	var entArr = new Array();
	for (var i = 0; i < entAddGroup.length; i++) {
		var optionVo = entAddGroup.eq(i);
		var ime_code = optionVo.attr("data-code");
		var ime_nm= optionVo.find(".nm").val();
		var ime_nike_nm= optionVo.find(".nike-nm").val();
		var ime_sort_ordr= optionVo.find(".sort").val();
		var default_at= optionVo.find(".default").is(":checked") == true ?"Y":"N";
		var use_at = optionVo.find(".use").is(":checked") == true ?"Y":"N";
		entArr.push({
			"ime_code"		:ime_code,
			"ime_nm"		:ime_nm,
			"ime_nike_nm"	:ime_nike_nm,
			"ime_sort_ordr"	:ime_sort_ordr,
			"default_at"	:default_at,
			"use_at"		:use_at,
			"ime_type"		:options.code
		});
	}	
	$.ajax({
		url : "/fixing/entOptionSave",
		data:{"entArr":entArr},
		type : "POST",		
		dataType : "json",
		cache : false,
		success : function(data) {
			if(data > 0){
				alert("저장되었습니다.");
				fnGetOptionList(options);
			}else{
				alert("저장에 실패하였습니다.");
			}
		}
	});
}

//두번제 옵션 저장
function fnSavePrdOption(options){
	var group = options.groupId;
	var prdAddGroup = $("."+group+"-add-group");
	var prdArr = new Array();	
	for (var i = 0; i < prdAddGroup.length; i++) {
		var optionVo = prdAddGroup.eq(i);
		var imp_code = optionVo.attr("data-code");
		var imp_nm= optionVo.find(".nm").val();
		var imp_nike_nm= optionVo.find(".nike-nm").val();
		var imp_sort_ordr= optionVo.find(".sort").val();
		var default_at= optionVo.find(".default").is(":checked") == true ?"Y":"N";
		var use_at = optionVo.find(".use").is(":checked") == true ?"Y":"N";
		prdArr.push({
			"imp_code"		:imp_code,
			"imp_nm"		:imp_nm,
			"imp_nike_nm"	:imp_nike_nm,
			"imp_sort_ordr"	:imp_sort_ordr,
			"default_at"	:default_at,
			"use_at"		:use_at,
			"imp_type"		:options.code
		});
	}	
	$.ajax({
		url : "/fixing/prdOptionSave",
		data:{"prdArr":prdArr},
		type : "POST",		
		dataType : "json",
		global: true,
		cache : false,
		success : function(data) {
			if(data > 0){
				alert("저장되었습니다.");
				fnGetOptionList(options);
			}else{
				alert("저장에 실패하였습니다.");
			}
		}
	});
}























