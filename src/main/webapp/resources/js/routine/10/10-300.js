var options10300;
var currBaseDate10300 = "";
var currSysDate10300 = "";
var trtRndComboOption;
var trtRndPrdJoin;
var currRndAttendLvffcAt= "";
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	$.get('/option/rnd-options.json',function(data){
		options10300 = eval(data);
	});
	trtRndComboOption = fnGetTrtFirstComboOptions("",1);
	trtRndPrdJoin =fnGetProdComboOptionsAll(); // 모든 정보

	$("#activityRndPop210300").instancePopUp();			//아코디언 팝업
	$("#activityRndPreView10300").instancePopUp();
	$("#activityRndAtendImgView10300").instancePopUp();
	$("#activityRndOptionPop10300").instancePopUp();	//공통옵션 팝업
	$("#activityTrtOptionPop10300").instancePopUp();//취급옵션 팝업
	
	$("#activityRndAtendImgViewCloseXBtn").click(function(event){
		$("#activityRndAtendImgView10300").hide();
	});
	
//	$("#searchToDate").winCal(baseOptions);	
//	$("#searchFromDate").winCal(baseOptions);
	
	fnGetRndSearchComboBox("",1); // 소속가져오기
	//근태일자 선택
	//DB SYSDATE
	var sysdate = getSysDate(2);
	currSysDate10300 = sysdate;
	//기본날짜 설정
	$("#searchDate10300").val(sysdate);
	$("#searchDate10300").winCal(baseOptions);
//	$("#searchDate10300").val("2015-10-23");
//	currBaseDate10300 = sysdate.setBaseDate("first");
	currBaseDate10300 = sysdate;// 오늘일자 기준일자
	//검색조건에 기준일자 등록
	$("#selectRndBaseDate").val(currBaseDate10300);
//	$("#selectRndBaseDate").val("2015-10-23");
	fnSetEventComponent();
	
	if(auth_flag >1){
		fnGetActivityRndInfo10300(1);
	}
	if(auth_flag >2){
		$("#searchBox10300").hide();
	}
	
	//매장지도보기 by zzz2613
	$("#viewStoreMap").click(function() {
		var smArray = new Array();
		var cnt = 0;
		var smList = $("#rndTab10300 ul").find("li");
		for(var i = 0; i < smList.length; i++) {
			var smVo = smList.eq(i);
			var sm_la = smVo.attr("data-sm-la");
			var sm_lo = smVo.attr("data-sm-lo");
			var sm_nm = smVo.attr("data-sm-nm");
			
			if (typeof sm_la != "undefined" && typeof sm_lo != "undefined" && typeof sm_nm != "undefined" && sm_la != "" && sm_lo != "" && sm_nm != "") {
				smArray[cnt++] = new Array(sm_la, sm_lo, sm_nm);
			}
		}
		
		if (cnt > 0) {
			$("#storeMapPop").show();
			fnViewStoreMap(smArray);
		} else {
			alert("순회매장이 존재하지 않습니다.");
			return;
		}
	});
});

//검색 콤보 박스
function fnGetRndSearchComboBox(om_code ,om_orgnzt_se){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
	    		}
			}	
			if(om_orgnzt_se==1){
				$("#searchBhf10300").html(listHtml);
				$("#searchBhf10300").change(function(){
					fnGetRndSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetRndSearchComboBox($("#searchBhf10300").val() ,2);
					fnGetActivityRndInfo10300(1);// 조회
				}
			}else if(om_orgnzt_se==2){
				$("#searchTeam10300").html(listHtml);
			}
	    }
	});
}


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
			$(".tbl_tab.event.option-tab ul li").removeClass("tab_on");
			$("."+groupId+"-group").hide();
			
			$(this).addClass("tab_on");
			$("."+groupId+"-"+target+"-group").show();
		}
	});
	
	$("#activityRndPreViewCloseXBtn").click(function(){
		$("#activityRndPreView10300").hide();
	});
	
	//업무일지 닫기
	$("#activityRndPopClose10300, #activityRndPopCloseX10300").click(function(){
			$("#activityRndPop210300").hide();
	});
	$("#activityRndPreViewCloseXBtn").click(function(){
		$("#activityRndPreView10300").hide();
	});
	$("#rndExcelDownLoad10300").click(function(){
		var base_de = $("#selectRndBaseDate").val();
		var sm_code = $("#rndTab10300 .tab_on").attr("data-sm-code");
		var em_no = $("#emNo10300").val();
		location.href='/rnd/excel?base_de='+base_de.dateReplace()+"&sm_code="+sm_code+"&em_no="+em_no;
	});
	//초기화
	$("#clearActivityRnd10300").click(function(){
		$("#searchTeam10300").val(0);
		$("#searchBhf10300").val(0);
		$("#searchEmpNm10300").val("");
		$("#searchDate10300").val(currSysDate10300);
		if($("#searchBhf10300").val() != ""){
			fnGetActivityRndInfo10300(1);
		}else{
			$("#rndUserListBody").html('<div class="none-data" style=""><i class="fa fa-info-circle"style=""></i><span>내용을 검색해 주세요.</span></div>');
		}
	});
	
	//이전업무일지
	$("#rndDiaryPrev").click(function(){
			var baseDate =  $("#selectRndBaseDate").val().setPrevDate();
			var emNo = $("#emNo10300").val();
			fnGetSmTab(emNo,baseDate);
			$("#selectRndBaseDate").val(baseDate);
			$("#rndDiaryDate").html(baseDate);
			
			var tabOn = $("#rndTab10300 .tab_on")
			
			var cvsAt =  tabOn.data("cvs-at");
			if(cvsAt == "N"){
				$("#cvsView10300").hide();
				$("#slideView10300").show();
				
				var menuArr = ["curr","big","odd","pd","trt"];
				for (var i = 0; i < menuArr.length; i++) {
					if($("#"+menuArr[i]+"Status10300").is(":visible")){
						fnGetActivityRndData(menuArr[i]);
					}
				}
			}else{
				$("#slideView10300").hide();
				fnGetRndTabCvsItemView(tabOn.data("drc-innb"));
			}
			
			
//			var menuArr = ["curr","big","odd","trt"];
//			for (var i = 0; i < menuArr.length; i++) {
//				if($("#"+menuArr[i]+"Status10300").is(":visible")){
//					fnGetActivityRndData(menuArr[i]);
//				}
//			}
			
			
			$("#rndDiaryNext").removeClass("last-base-date");
	});
	//다음업무일지
	$("#rndDiaryNext").click(function(){
			if(currBaseDate10300 == $("#selectRndBaseDate").val()){
				alert("마지막 데이터 입니다.");
				return;
			}
			var baseDate =  $("#selectRndBaseDate").val().setNextDate();
			var emNo = $("#emNo10300").val();
			fnGetSmTab(emNo,baseDate);
			$("#selectRndBaseDate").val(baseDate);
			$("#rndDiaryDate").html(baseDate);
			
			
			var tabOn = $("#rndTab10300 .tab_on")
			
			var cvsAt =  tabOn.data("cvs-at");
			if(cvsAt == "N"){
				$("#cvsView10300").hide();
				$("#slideView10300").show();
				
				var menuArr = ["curr","big","odd","pd","trt"];
				for (var i = 0; i < menuArr.length; i++) {
					if($("#"+menuArr[i]+"Status10300").is(":visible")){
						fnGetActivityRndData(menuArr[i]);
					}
				}
			}else{
				$("#slideView10300").hide();
				fnGetRndTabCvsItemView(tabOn.data("drc-innb"));
			}
			
//			var menuArr = ["curr","big","odd","trt"];
//			for (var i = 0; i < menuArr.length; i++) {
//				if($("#"+menuArr[i]+"Status10300").is(":visible")){
//					fnGetActivityRndData(menuArr[i]);
//				}
//			}
			
			
			if(currBaseDate10300 == $("#selectRndBaseDate").val()){
				$(this).addClass("last-base-date");
			}else{
				$(this).removeClass("last-base-date");
			}
	});
	
	
	//아코디언 펼치치 / 닫기
	$("#slideView10300").find(".toogle-slide").click(function(){
		var $this = $(this);
		var type = $this.find("i").data("type");
		if(typeof type == "undefined" ){
			alert("준비중입니다.");
			return;
		}
		$this.parent().toggleClass("aco-selectd");
		//열려있는 항목만 조회
		if(!$("#"+type+"Status10300").is(":visible")){
			fnGetActivityRndData(type); // 일지관리 항목 조회 통합
		}
		//+,- 아이콘 변경
		$("#"+type+"Status10300").slideToggle(function(){
			$this.find("i").toggleClass("fa-minus-square");
		});
		
	});
	
	
	
	
	
	
	//지점목록 가져오기
//	fnMakeComboBoxBhf10300("001", "searchBhf10300");
//	cboSelectDepthList_10300("001", "searchBhf10300", 1);
	
	//활동관리 고정MD 조회
	$("#searchActivityRnd10300").click(function(){
		fnGetActivityRndInfo10300(1);
//		fnGetActivityRndView10300();
//		fnGetActivityRndList10300(1, "");
	});
	
	
	//공통 옵션창 열기
	$("#slideView10300 a.options.comm-group").click(function(){
		
		$("#optionLeftTab10300").trigger("click");			//1번텝 활성화
		$("#activityRndOptionPop10300").show();
		var type = $(this).data("type");
		$("#optionLeftAddRow10300").data("type",type);	//1번텝 행추가버튼
		$("#optionRightAddRow10300").data("type",type); //2번텝 행추가버튼
		$("#optionLeftSaveBtn10300").data("type",type);		//1번텝 저장버튼
		$("#optionRightSaveBtn10300").data("type",type);		//2번텝 저장버튼
		fnGetRndOptionList(options10300[type+"1"]);		//1번텝	조회
		fnGetRndOptionList(options10300[type+"2"]);		//2번텝 조회
	});	

	//공통 옵션창 닫기
	$("#activityRndOptionCloseXBtn").click(function(){
		$("#activityRndOptionPop10300").hide();
	});
	//공통 옵션창 행추가
	$("#optionLeftAddRow10300").click(function(){
		var type = $(this).data("type");
		fnMakeRndOptionAppend(options10300[type+"1"]);
	});
	$("#optionRightAddRow10300").click(function(){
		var type = $(this).data("type");
		fnMakeRndOptionAppend(options10300[type+"2"]);
	});
	//공통 옵션창 저장
	$("#optionLeftSaveBtn10300").click(function(){
		var type = $(this).data("type");
		fnSaveActivityOption10300(options10300[type+"1"]);
	});
	$("#optionRightSaveBtn10300").click(function(){
		var type = $(this).data("type");
		fnSaveActivityOption10300(options10300[type+"2"]);
	});
	
	
	
	
	//취급품목관리 행추가
	$("#trtStnAddRow10300").click(function(){
		fnMakeTrtOptionAppend(options10300["trt1"]);
	});
	//취급품목관리 옵션창 닫기
	$("#trtOptionCloseXBtn10300").click(function(){
		$("#activityTrtOptionPop10300").hide();
	});
	//취급품목관리 옵션창열기
	$("#trtOptionOpenBtn10300").click(function(){
		$("#activityTrtOptionPop10300").show();
		fnGetTrtOptionList10300(options10300["trt1"]);
	});	
	//취급품목관리 저장
	$("#trtStnSaveRow10300").click(function(){
		fnSaveActivityOption10300(options10300["trt1"]);
	});
	
	$("#trtTbody10300 .trt-change-group").change(function(){
		var thisCode = $(this).val();
		var thisCodeNm = $(this).find("option:selected").text();
		if(thisCode == ""){
			thisCodeNm ="";
		}
		$(this).parent().parent().find(".nm").attr("data-parent-code",thisCode);
		$(this).parent().parent().find(".nm").val(thisCodeNm);
		fnGetRndTrtComboDepthList($(this));
	});
	
	$("#rndPrevAtendCheck").change(function(){
		if($(this).is(":checked")){
			if(confirm("프로필사진을 변경하시겠습니까?")){
				var aiPath =$(this).attr("data-ai_path");
				var emNo =$(this).attr("data-em_no");
				var amNo =$(this).attr("data-am_no")
				fnSaveActivityRndEmpAiNo( emNo, amNo, aiPath);
			}
		}
	});
	
	
}

/**
 * @함수명: fnGetRndTrtComboDepthList
 * @작성일: 2015. 09. 24.
 * @작성자: 김진호
 * @설명: 콤보 Depth 
 * @param 
 */
function fnGetRndTrtComboDepthList(el){
	var pm_dp = el.data("trt-depth")+1;
	if(pm_dp > 5){
		return;
	}
	$.ajax({
		url : "/product/depthList",
	    data:{"pm_code":el.val(), "pm_dp": pm_dp},
	    type : "POST",
	    dataType : "json", 
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if(data.result!=null && data.result.length > 0){
	    		listHtml = "<option value ='' > 선택 </option>";
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.pm_code+"'>" + res.pm_nm +"</option>";
	    		}
			}else{
				listHtml += "<option value =''> 선택 </option>";
			}	
			
			el.parent().parent().find(".trt-change-group[data-trt-depth='"+pm_dp+"']").html(listHtml);
			for (var i = 0; i < (5-pm_dp); i++) {
				el.parent().parent().find(".trt-change-group[data-trt-depth='"+(pm_dp+(i+1))+"']").html("<option value=''>선택</option>");
			}
	    }
	});
}

// 콤보박스 생성
function fnGetTrtComboOptions10300(pmCode,cut,type){
	var parenCode = pmCode.substring(0,cut);
	var currCode = pmCode.substring(0,cut+2);
	var pm_dp = parenCode.length/2;
	var json = {};
	var arr ={};
	if(pmCode.length < parseInt(cut) && type == "options"){
		return "<option value ='' > 선택 </option>";
	}else if(pmCode.length < parseInt(cut)){
		return "";
	}
	if(parenCode == ""){
		arr = trtRndPrdJoin;
	}else{
		json = trtRndPrdJoin[parenCode.substring(0,2)];
		for (var i = 1; i < pm_dp; i++) {
			var str = ((i+1)*2);
//			console.log("str : "+str);
			json = json.child[parenCode.substring(0,str)];
		}
		arr = json.child;
	}
	if(type == "options"){
		var listHtml = "<option value ='' > 선택 </option>";
		$.each(arr, function(i,result){
			var pm_nm = result.pm_nm;
			var pm_code = result.pm_code;
//			console.log(pm_nm+" : "+pm_code);
			listHtml += "<option value ='"+pm_code+"' "+(pm_code == currCode ? "selected":"")+" >" + pm_nm +"</option>";
		});  
		return listHtml;
	}else{
		if(arr[currCode] !== undefined ){
			return arr[currCode].pm_nm
		}else{
			return "";
		}
	}
}

// key:value형(하이어라키 구조) JSON 한번에 모두 가져오기
function fnGetProdComboOptionsAll(){
	var json = {};
	$.ajax({
		url : "/product/listall",
//		data:{"pm_code":pm_code, "pm_dp": pm_dp},
		type : "POST",
		dataType : "json", 
		async: false,
		cache : false ,
		success : function(jsonData) {
//			console.log(jsonData);
			json = jsonData;
//			alert(json["11"].child["1103"].child["110301"].child["11030102"].child["1103010219"].pm_nm);
		}
	});
	return json;
}


function fnGetTrtFirstComboOptions(pm_code, pm_dp){
	var listHtml = "";
	$.ajax({
		url : "/product/depthList",
		data:{"pm_code":pm_code, "pm_dp": pm_dp},
		type : "POST",
		dataType : "json", 
		async: false,
		cache : false ,
		success : function(data) {
			if(data.result!=null && data.result.length > 0){
				listHtml = "<option value ='' > 선택 </option>";
				for(var i=0; i<data.result.length; i++){
					var res = data.result[i];     
					listHtml += "<option value ='"+res.pm_code+"'>" + res.pm_nm +"</option>";
				}
			}
		}
	});
	return listHtml;
}

//공통 옵션 저장
function fnSaveActivityOption10300(options){
	var oi_type = options.code;
	var group = options.groupId;
	
	if(group =="evn-type" ){
		oi_type = $("#optionIoType10300").val();
	}
	var optionAddGroup = $("."+group+"-add-group");
	var optionArr = new Array();
	for (var i = 0; i < optionAddGroup.length; i++) {
		var optionVo = optionAddGroup.eq(i);
		var oi_code = optionVo.attr("data-code");
		var oi_nm= optionVo.find(".nm").val();
		var oi_nick_nm= optionVo.find(".nick-nm").val();
		var oi_sort_ordr= optionVo.find(".sort").val();
		var use_at = optionVo.find(".use").is(":checked") == true ?"Y":"N";
		var oi_parn_code = "";
		if(group =="trt-stn" ){
			if(oi_nm ==""){
				alert(options.nmText+" 을 선택해주세요");
				optionVo.find(".nm").focus();
				return;
			}
			oi_parn_code = optionVo.find(".nm").attr("data-parent-code");
			if(oi_parn_code ==""){
				alert(options.nmText+" 을 선택해주세요");
				optionVo.find(".nm").focus();
				return;
			}
			oi_nm = optionVo.find(".nm").val();
//			oi_parn_code = oi_nm;
//			oi_nm = optionVo.find(".nm").children("option:selected").text();
		}
		if(oi_code == "" && (oi_nick_nm.allTrim()).length != oi_nick_nm.length ){
			alert("공백은 입력할수 없습니다.");
			optionVo.find(".nick-nm").focus();
			return;
		}else if(oi_code == "" && (oi_nick_nm.allTrim()) == ""){
			alert(options.nickNmText+" 을 입력해주세요");
			optionVo.find(".nick-nm").focus();
			return;
		}else if(oi_nm =="" && group !="trt-stn" ){
			alert(options.nmText+" 을 입력해주세요");
			optionVo.find(".nm").focus();
			return;
		}else if(oi_sort_ordr ==""  ){
			alert("순서를 입력해주세요");
			optionVo.find(".sort").focus();
			return;
		} 
		optionArr.push({
			"oi_code"		:oi_code,
			"oi_nm"			:oi_nm,
			"oi_nick_nm"	:oi_nick_nm,
			"oi_sort_ordr"	:oi_sort_ordr,
			"oi_parn_code"	:oi_parn_code,
			"use_at"		:use_at,
			"oi_type"		:oi_type
		});
	}	
	$.ajax({
		url : "/option/customSave",
		data:{"optionArr":optionArr},
		type : "POST",		
		dataType : "json",
		cache : false,
		success : function(data) {
			if(data.cnt > 0){
				if(data.usingArr.length > 0 ){
					var useText = "";
					for (var i = 0; i < data.usingArr.length; i++) {
						var usingVo = data.usingArr[i];
						if(i > 0) {
							useText += ", ";
						}
						useText += usingVo.oi_nick_nm;
//						$("input.nick-nm[value='"+usingVo.oi_nick_nm+"']").css("background-color","yellow")
//						alert(usingVo.oi_nick_nm);
//						alert("111 : "+$(".nick-nm[value='"+usingVo.oi_nick_nm+"']").val());
					}
					alert("저장되었습니다.\n"+useText + "는사용 중인 약어입니다.");
//				}else{
				}
				alert("저장되었습니다.");
				if(options.groupId =="trt-stn"){
					fnGetTrtFixingOptionList(options);
				}else{
					fnGetRndOptionList(options);	
				}
//				if(options.groupId =="evn-stn"){
//					fnGetEvnOptionList(options,"");
//				}else if(options.groupId =="evn-type"){
//					fnGetEvnOptionList(options,oi_type);
//				}else if(options.groupId =="trt-stn"){
//					fnGetTrtOptionList10300(options);
//				}else{
//					fnGetRndOptionList(options);	
//				}
			}else{
				alert("저장에 실패하였습니다.");
			}
		}
	});
}


//옵션가져오기 공통
function fnGetRndOptionList(options){
	
	$("#optionIoType10300").val(options.code);
	$("#"+options.target+"NickNmText10300").text(options.nickNmText);
	$("#"+options.target+"NmText10300").text(options.nmText);
	$.ajax({
		url : "/option/list",
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
				var code = optionVo.oi_code; 
				var useAt = optionVo.use_at;
				var defaultAt = optionVo.default_at;
				var group = options.groupId;
				if(defaultAt == "Y"){ 
					var checked = "";
					checked = "<i class='fa fa-check'></i>"
					html +='<tr>';
					html +='<td>'+optionVo.oi_nick_nm+'</td>';
					html +='<td>'+optionVo.oi_nm+'</td>';
					html +='<td>'+optionVo.oi_sort_ordr+'</td>';
					html +='<td>';
					html +=checked;
					html +='</td>';
					html +='<td>';
					if(useAt == "Y"){
						html +=checked;
					}
					html +='</td>';
					html +='<td>';
					html +='<button class="disabled"  disabled >삭제</button>'; 
					html +='</td>';
					html +='</tr>';
				}else{
					html +='<tr class="'+group+'-add-group" data-code="'+code+'" >';
					html +='<td>'+optionVo.oi_nick_nm+'</td>';
//					html +='<td><input type="text" class="nick-nm" value="'+optionVo.oi_nick_nm+'" ></td>';
					html +='<td><input type="text" class="nm"  value="'+optionVo.oi_nm+'"  ></td>';
					html +='<td><input type="text" class="sort"  maxlength="5" value="'+optionVo.oi_sort_ordr+'"  ></td>';
					html +='<td></td>';
					html +='<td>';
					html +='	<div class="inputCheck">';
					html +='		<input type="checkbox" class="use"  id="'+group+'UseCheckOld'+i+'" '+(useAt=="Y"? "checked":"")+'    >';
					html +='		<label for="'+group+'UseCheckOld'+i+'"></label>';
					html +='	</div>';
					html +='</td>';
					html +='<td>';
					html +='<button class="disabled"  disabled >삭제</button>'; 
					html +='</td>';
					html +='</tr>';
				}
//				prdAppendId++;
			}
			$("#"+options.target+"Tbody10300").html(html);	
			$("#"+options.target+"Tbody10300").find(".sort").onlyNum();	
			$("#"+options.target+"Tab10300").html(options.tabName);	
			$("#optionTitle10300").html(options.title);	
			$("#optionPrantCode10300").html(options.code);	
			
			$("#"+options.target+"AddRow10300").val(options.code);
		}
	});
}


//옵션가져오기 취급품목
function fnGetTrtOptionList10300(options){	
	var group = options.groupId;
	var code = options.code;
	$("#optionIoType10300").val(code);
	$.ajax({
		url : "/option/list",
		type : "POST",
		data : {
			"optionCode":code
		},
		dataType : "json",
//		async :false,
		cache : false,
		success : function(data) {
			var html = "";
			for (var i = 0; i < data.optionList.length; i++) {
				var optionVo =  data.optionList[i];
				var code = optionVo.oi_code; 
				var oi_parn_code = optionVo.oi_parn_code; 
				var useAt = optionVo.use_at;
				var defaultAt = optionVo.default_at;
//				console.log("oi_parn_code : " + oi_parn_code);
				if(defaultAt == "Y"){ 
					var checked = "";
					checked = "<i class='fa fa-check'></i>"
					html +='<tr>';
					html +='<td>'+fnGetTrtComboOptions10300(oi_parn_code,0,"")+'</td>';
					html +='<td>'+fnGetTrtComboOptions10300(oi_parn_code,2,"")+'</td>';
					html +='<td>'+fnGetTrtComboOptions10300(oi_parn_code,4,"")+'</td>';
					html +='<td>'+fnGetTrtComboOptions10300(oi_parn_code,6,"")+'</td>';
					html +='<td>'+fnGetTrtComboOptions10300(oi_parn_code,8,"")+'</td>';
					html +='<td>'+optionVo.oi_nm+'</td>';
					html +='<td>'+optionVo.oi_nick_nm+'</td>';
					html +='<td>'+optionVo.oi_sort_ordr+'</td>';
					html +='<td>';
					html +=checked;
					html +='</td>';
					html +='<td>'+(useAt == "Y" ? checked :"")+'</td>';
					html +='<td><button class="disabled"  disabled >삭제</button></td>';
					html +='</tr>';
				}else{
					html +='<tr class="'+group+'-add-group" data-code="'+code+'" >';
					html +='<td>';
					html +='<select class="select-basic trt-change-group" data-trt-depth="1" >';
					html +=fnGetTrtComboOptions10300(oi_parn_code,0,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select class="select-basic trt-change-group" data-trt-depth="2" >';
					html +=fnGetTrtComboOptions10300(oi_parn_code,2,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select class="select-basic trt-change-group" data-trt-depth="3" >';
					html +=fnGetTrtComboOptions10300(oi_parn_code,4,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select id="trtComboDept3" class="select-basic trt-change-group" data-trt-depth="4" >';
					html +=fnGetTrtComboOptions10300(oi_parn_code,6,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select class="select-basic trt-change-group" data-trt-depth="5" >';
					html +=fnGetTrtComboOptions10300(oi_parn_code,8,"options");
					html +='</select>';
					html +='</td>';
					html +='<td><input type="text" class="nm" data-parent-code="'+oi_parn_code+'"  value="'+optionVo.oi_nm+'"  readonly ></td>';
//					html +='<td><input type="text" class="nick-nm"  value="'+optionVo.oi_nick_nm+'"  ></td>';
					html +='<td>'+optionVo.oi_nick_nm+'</td>';
					html +='<td><input type="text" class="sort"  maxlength="5"  value="'+optionVo.oi_sort_ordr+'"  ></td>';
					html +='<td></td>';
					html +='<td>';
					html +='	<div class="inputCheck">';
					html +='		<input type="checkbox" class="use"  id="'+group+'UseCheckOld'+i+'" '+(useAt=="Y"? "checked":"")+'    >';
					html +='		<label for="'+group+'UseCheckOld'+i+'"></label>';
					html +='	</div>';
					html +='</td>';
					html +='<td>';
					html +='<button class="disabled" >삭제</button>';
					html +='</td>';
					html +='</tr>';
				}
			}
			$("#"+options.target+"Tbody10300").html(html);	
			$("#"+options.target+"Tbody10300").find(".sort").onlyNum();
			$("#"+options.target+"Tab10300").html(options.tabName);	
			$("#optionTitle10300").html(options.title);	
			$("#optionPrantCode10300").html(options.code);	
			$("#"+options.target+"AddRow10300").val(options.code);
			$("#"+options.target+"Tbody10300 .trt-change-group").change(function(){
				var thisCode = $(this).val();
				var thisCodeNm = $(this).find("option:selected").text();
				if(thisCode == ""){
					thisCodeNm ="";
				}
				$(this).parent().parent().find(".nm").attr("data-parent-code",thisCode);
				$(this).parent().parent().find(".nm").val(thisCodeNm);
				fnGetRndTrtComboDepthList($(this));
			});
			
		}
	});
}

//행추가 공통
function fnMakeRndOptionAppend(options){
	var maxCnt = options.maxCnt
	var rowSize = $("#"+options.target+"Tbody10300"+" > tr").length;
	if((rowSize > maxCnt -1) && maxCnt > 0){
		alert("이미 최대갯수 입니다.");
		return;
	}
	var group = options.groupId;
	var html ="";
	html +='<tr class="'+group+'-add-group"  data-code="" >';
	html +='<td><input type="text" class="nick-nm" ></td>';
	html +='<td><input type="text" class="nm" ></td>';
	html +='<td><input type="text" class="sort" ></td>';
	html +='<td></td>';
	html +='<td>';
	html +='	<div class="inputCheck">';
	html +='		<input type="checkbox" class="use"  value="None" id="optionUseCheck'+rowSize+'" >';
	html +='		<label for="optionUseCheck'+rowSize+'"></label>';
	html +='	</div>';
	html +='</td>';
	html +='<td>';
	html +='<button class="brown del-row" id="" >삭제</button>';
	html +='</td>';
	html +='</tr>';
	$("#"+options.target+"Tbody10300").append(html);	
	$("#"+options.target+"Tbody10300").find(".sort").onlyNum();
	$("#"+options.target+"Tbody10300 > tr").last().find(".del-row").click(function(){
		$(this).parent().parent().remove();
	});	
}
////행추가 행사매대
//function fnMakeEvnOptionAppend(options){
//	var maxCnt = options.maxCnt
//	var rowSize = $("#"+options.target+"Tbody10300"+" > tr").length;
//	if((rowSize > maxCnt -1) && maxCnt > 0){
//		alert("이미 최대갯수 입니다.");
//		return;
//	}
//	var group = options.groupId;
//	var html ="";
//	html +='<tr class="'+group+'-add-group" data-code="" >';
//	html +='<td><input type="text" class="nm" ></td>';
//	html +='<td><input type="text" class="sort" ></td>';
//	html +='<td></td>';
//	html +='<td>';
//	html +='	<div class="inputCheck">';
//	html +='		<input type="checkbox" class="use"  value="None" id="optionUseCheck'+rowSize+'" >';
//	html +='		<label for="optionUseCheck'+rowSize+'"></label>';
//	html +='	</div>';
//	html +='</td>';
//	html +='<td>';
//	html +='<button class="brown del-row" id="" >삭제</button>';
//	html +='</td>';
//	if(group == "evn-stn"){
//		html +='<td>';
//		html +='<button class="disabled" id="" >선택</button>';
//		html +='</td>';
//	}
//	html
//	html +='</tr>';
//	$("#"+options.target+"Tbody10300").append(html);	
//	$("#"+options.target+"Tbody10300").find(".sort").onlyNum();
//	$("#"+options.target+"Tbody10300 > tr").last().find(".del-row").click(function(){
//		$(this).parent().parent().remove();
//	});	
//}
//행추가 취급품목
function fnMakeTrtOptionAppend(options){
	var maxCnt = options.maxCnt
	var rowSize = $("#"+options.target+"Tbody10300"+" > tr").length;
	if((rowSize > maxCnt -1) && maxCnt > 0){
		alert("이미 최대갯수 입니다.");
		return;
	}
	var group = options.groupId;
	var html ="";
	html +='<tr class="'+group+'-add-group" data-code="" >';
	html +='<td>';
	html +='<select class="select-basic trt-change-group" data-trt-depth="1" >';
	html +=trtRndComboOption;
	html +='</select>';
	html +='</td>';
	html +='<td>';
	html +='<select class="select-basic trt-change-group" data-trt-depth="2" >';
	html +='<option value="">선택</option>';
	html +='</select>';
	html +='</td>';
	html +='<td>';
	html +='<select class="select-basic trt-change-group" data-trt-depth="3" >';
	html +='<option value="">선택</option>';
	html +='</select>';
	html +='</td>';
	html +='<td>';
	html +='<select class="select-basic trt-change-group" data-trt-depth="4" >';
	html +='<option value="">선택</option>';
	html +='</select>';
	html +='</td>';
	html +='<td>';
	html +='<select class="select-basic trt-change-group" data-trt-depth="5" >';
	html +='<option value="">선택</option>';
	html +='</select>';
	html +='</td>';
	html +='<td><input type="text" class="nm" data-parent-code="" readonly ></td>';
	html +='<td><input type="text" class="nick-nm" ></td>';
	html +='<td><input type="text" class="sort" ></td>';
	html +='<td></td>';
	html +='<td>';
	html +='	<div class="inputCheck">';
	html +='		<input type="checkbox" class="use"  value="None" id="optionUseCheck'+rowSize+'" >';
	html +='		<label for="optionUseCheck'+rowSize+'"></label>';
	html +='	</div>';
	html +='</td>';
	html +='<td>';
	html +='<button class="brown del-row" id="" >삭제</button>';
	html +='</td>';
	html +='</tr>';
	$("#"+options.target+"Tbody10300").append(html);	
	$("#"+options.target+"Tbody10300").find(".sort").onlyNum();
	$("#"+options.target+"Tbody10300 > tr").last().find(".del-row").click(function(){
		$(this).parent().parent().remove();
	});	
	$("#"+options.target+"Tbody10300 .trt-change-group").change(function(){
		var thisCode = $(this).val();
		var thisCodeNm = $(this).find("option:selected").text();
		if(thisCode == ""){
			thisCodeNm ="";
		}
		$(this).parent().parent().find(".nm").attr("data-parent-code",thisCode);
		$(this).parent().parent().find(".nm").val(thisCodeNm);
		fnGetRndTrtComboDepthList($(this));
	});
}


function fnGetActivityRndData(type){
	var base_de = $("#selectRndBaseDate").val();
	var sm_code = $("#rndTab10300 .tab_on").attr("data-sm-code");
//	alert(sm_code);
	$.ajax({
		url : "/rnd/"+type+"list",
		type : "POST",
		data : {
			"base_de": base_de.dateReplace(),
			"sm_code": sm_code,
			"em_no":$("#emNo10300").val()
		},
		dataType : "json",
		global:true,
		cache : false,
		success : function(data) {
			switch(type){
				case "curr": fnMakeRndSingleGrid(data,type); break;	//POG 현황
				case "big": fnMakeRndSingleGrid(data,type); break;	//보조진열현황
				case "pd": fnMakeRndSingleGrid(data,type); break;			//시황및 득이사항
				case "odd": fnMakeRndOddList(data); break;			//시황및 득이사항
				case "trt": fnMakeRndTrtList(data); break;			//취급품목
				default : alert("준비중입니다.");
			}
		}
	});
}
//취급품목
function fnMakeRndTrtList(trtList){
	var colHtml = "<tr><th>구분</th>";
	var rowHtml = "<tr><td>취급여부</td>";
	if(trtList.length > 0 ){
		$("#trtUpdateDate10300").html("최종 업데이트 : "+trtList[0].updt_de);
		for (var i = 0; i < trtList.length; i++) {
			var trtVo = trtList[i];
			var checked = "";
			if(trtVo.trtmnt_at == "Y"){
				checked = "<i class='fa fa-check'></i>"
			}
			colHtml += "<th>"+trtVo.pm_code_nm+"</th>";
			rowHtml += "<td>"+checked+"</td>";
			
		}
		colHtml += "</tr>";
		rowHtml += "</tr>";
	}else{
		rowHtml="<tr><td>내용이 없습니다.</td></tr>";
		colHtml="";
		$("#trtUpdateDate10300").html("");
//		colHtml="<tr><td>내용이 없습니다.</td></tr>";
//		rowHtml="";
	}
	$("#trtThead10300").html(colHtml);
	$("#trtTbody10300").html(rowHtml);
	
}

// Odd 생성
function fnMakeRndOddList(data){
	
	var ourImgTd ='<th rowspan="2" >당사</th>';
	var ourEtcTd ='';
	var comImgTd ='<th rowspan="2" >경쟁사</th>';
	var comEtcTd ='';
	var ourCnt =5;
	var comCnt =5;
	var oddLen =data.oddList.length;
//	alert(oddLen);
	for (var i = 0; i < oddLen; i++) {
		var oddVo = data.oddList[i];
		if(i == 0){
			$("#oddUpdateDate10300").text("최종 업데이트 : "+oddVo.updt_de);
		}
		if(oddVo.drop_flag == "1"){
			ourImgTd +='<td><img width="160px" src="'+oddVo.drop_img_url+'" class="odd-img" ></td>';
			ourEtcTd +='<td class="enpre">'+oddVo.drop_partclr_matter+'</td>';
			ourCnt --;
		}else if(oddVo.drop_flag == "2"){
			comImgTd +='<td><img width="160px" src="'+oddVo.drop_img_url+'" class="odd-img" ></td>';
			comEtcTd +='<td class="enpre">'+oddVo.drop_partclr_matter+'</td>';
			comCnt--;
		}
	}//alert(oddLen);
	if(oddLen > 0){
		for (var i = 0; i < ourCnt; i++) {
			ourImgTd +='<td></td>';
			ourEtcTd +='<td></td>';
		}
		for (var i = 0; i < comCnt; i++) {
			comImgTd +='<td></td>';
			comEtcTd +='<td></td>';
		}
	}else{
//		ourImgTd = "<td colspan='6' >내용이 없습니다.</td>";
		ourImgTd = "";
		comImgTd = "";
		comEtcTd = "<td colspan='6' >내용이 없습니다.</td>";
		$("#oddUpdateDate10300").html("");
	}
	$("#odd_up_date").html(data.odd_up_date);
	$("#oddOurImgTr10300").html(ourImgTd);
	$("#oddOurEtcTr10300").html(ourEtcTd);
	$("#oddComImgTr10300").html(comImgTd);
	$("#oddComEtcTr10300").html(comEtcTd);
	
	$("#oddOurImgTr10300").find(".odd-img").click(function(){
		var src = $(this).attr("src");
		$("#activityRndPreView10300").show();
		$("#rndPrevImg").attr("src",src);
	});
	$("#oddComImgTr10300").find(".odd-img").click(function(){
		var src = $(this).attr("src");
		$("#activityRndPreView10300").show();
		$("#rndPrevImg").attr("src",src);
	});
}
// 그리드 생성
function fnMakeRndSingleGrid(data,type){
	var headCol = "";
	var bodyRows = ""; 
	var columns = [] ; 	
	var columnArr = data.columnArr;
	var bodyArr = data.bodyArr;
	headCol +='<tr>';
	var headLen = columnArr.length;
	headCol +='<th>구분</th>';
	for (var i = 0; i < headLen; i++) {
		var columnNm = columnArr[i].oi_code_nm;
		var columnCd = columnArr[i].oi_code;
		headCol +='	<th>'+columnNm+'</th>';
		columns.push(columnCd);
	}
	if(type == "curr"){
		headCol +='<th >특이사항</th>';
	}
	headCol +='</tr>';
	if(headLen == 0 || data.updt_de === undefined || data.updt_de == "" || bodyArr.length == 0){
		headCol="<tr><td>내용이 없습니다.</td></tr>";
		$("#"+type+"UpdateDate10300").text("");
	}else{
		$("#"+type+"UpdateDate10300").text("최종 업데이트 : "+data.updt_de);
		for (var i = 0; i < bodyArr.length; i++) {
			bodyRows +='<tr>';
			bodyRows +='<td>'+bodyArr[i].oi_code_nm+'</td>';
			
			for (var f = 0; f < columns.length; f++) {
				var column = columns[f];
				bodyRows +='<td>'+bodyArr[i][column]+'</td>';
			}
			if(type == "curr"){
				var etc = bodyArr[i].partclr_matter;
				bodyRows +='<td class="enpre">'+etc+'</td>';
			}
			bodyRows +='</tr>';
		}
		if(headLen > 0 && type != "curr"){
			bodyRows +="<tr>";
			bodyRows +="<td>특이사항</td>";
			bodyRows +="<td class='enpre' colspan='"+headLen+"'>"+data.partclr_matter+"</td>";
			bodyRows +="</tr>";
		}
	}
	
	$("#"+type+"Thead10300").html(headCol);
	$("#"+type+"Tbody10300").html(bodyRows);
}















function fnGetActivityRndInfo10300(currPg){
	if(!fnActivityRndValidation10300()){
		return;
	}
	
	fnGetActivityRndView10300();
	fnGetActivityRndList10300(currPg);
}

//활동관리 고정MD 총원 조회
function fnGetActivityRndView10300(){
	var	retireYn = "NO";
	if($("#retireYn10300").is(":checked")){
		retireYn = "YES"
	}
	var params = {
//		searchStoreNm : $("#searchStoreNm10300").val()
		 searchEmpNm : $("#searchEmpNm10300").val()
		 , retireYn : retireYn
		, searchBhf : $("#searchBhf10300").val()
		, searchTeam : $("#searchTeam10300").val()
		, searchDate : $("#searchDate10300").val().replace(/\-/g,"")
	};
	
	$.ajax({
		url : "/rnd/view",
		type : "POST",
	    data : params,
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
			if(data.activityRndVo != null){
				$("#dmDt10300").html($("#searchDate10300").val());
				$("#omNm10300").html($("#searchBhf10300 option:selected").text());
				$("#totCnt10300").html(data.activityRndVo.dm_tot_cnt+"명");
				$("#dmAttendYCnt10300").html(data.activityRndVo.dm_attend_y_cnt+"명");
				$("#dmAttendNCnt10300").html(data.activityRndVo.dm_attend_n_cnt+"명");
				$("#dmLvffcYCnt10300").html(data.activityRndVo.dm_lvffc_y_cnt+"명");
				$("#dmLvffcNCnt10300").html(data.activityRndVo.dm_lvffc_n_cnt+"명");
				$("#dmVcatnYCnt10300").html(data.activityRndVo.dm_vcatn_y_cnt+"명");
			}else{
				$("#dmDt10300").html("");
				$("#omNm10300").html("");
				$("#totCnt10300").html("");
				$("#dmAttendYCnt10300").html("");
				$("#dmAttendNCnt10300").html("");
				$("#dmLvffcYCnt10300").html("");
				$("#dmLvffcNCnt10300").html("");
				$("#dmVcatnYCnt10300").html("");
			}
		}
	});
}

//활동관리 고정MD 조회
//attendLvffcAt ("" : 총원, "AY" : 출근, "AN" : 미출근, "LY" : 퇴근, "LN" : 미퇴근, "VY" : 휴가)
function fnGetActivityRndList10300(currPg){
	var fnName = "fnGetActivityRndList10300";//페이징처리 함수명
	$("#selectRndBaseDate").val($("#searchDate10300").val());
	
	var	retireYn = "NO";
	if($("#retireYn10300").is(":checked")){
		retireYn = "YES"
	}
	var params = {
//		searchStoreNm : $("#searchStoreNm10300").val()
		  searchEmpNm : $("#searchEmpNm10300").val()
		, retireYn : retireYn
		, searchBhf : $("#searchBhf10300").val()
		, searchTeam : $("#searchTeam10300").val()
		, searchDate : $("#searchDate10300").val().replace(/\-/g,"")
		, searchAttendLvffcAt : currRndAttendLvffcAt
	};
	currRndAttendLvffcAt = "";
	var rowSize = 16;//표시할 행수
//	var rowSizePerLine = 8;//화면의 1라인에 표시할 행수
	
	$.ajax({
		url : "/rnd/list",
		type : "POST",
	    data : {
	    	fnName : fnName,
	    	params : params,
	    	rowSize : rowSize,
	    	currPg : currPg
	    },
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
			var html = "";
			if(data.activityRndList.length > 0){
				for (var i = 0; i < data.activityRndList.length; i++) {
					var rndVo = data.activityRndList[i]; 
					html += '<div class="profilebox">';
					html += '	<div style="max-width:134px; height:142px; position: relative;overflow: hidden;" >';
					if(rndVo.emp_ai_path == ""){
						html += '<img id="profileImage10300'+rndVo.em_no+'"  src="/resources/images/thum_profile.png">';
					}else{
						html += '<img id="profileImage10300'+rndVo.em_no+'"  src="'+rndVo.emp_ai_path+'">';						
					}
					html += '	</div>';
					if(rndVo.dm_attend_de == ""){
						html += '	<button class="hotpink lft">미출근</button>';
					}else{
//						html += '<div id="screenshot103001'+i+'" class="popup screenshot10300" style="margin-top:0">';
//						html += '	<div class="phead txt_left"><span>촬영일시 : '+rndVo.dm_attend_de+'</span><a id="" class="phead-closex" data-type="1" data-idx="'+i+'">닫기</a></div>';
//						html += '	<span style="float:right;padding: 6px 8px 6px 0;font-size: small;">프로필 사진 적용</span>';
//						html += '	<div style="float:right;margin: 8px 10px;" class="inputCheck">';
//						html += '	<input type="checkbox" id="empImage103001'+i+'" />';
//						html += '	<label for="empImage103001'+i+'"></label>';
//						html += '	</div>';
//						html += '	<div><img id="preview103001'+i+'" src="'+rndVo.dm_at_path+'" alt="url preview" /></div>';
//						html += '</div>';
						
						html += '	<button class="skyblue lft img-view" data-type="1" data-at-date="'+rndVo.dm_attend_de+'" data-em_nm="'+rndVo.em_nm+'" data-em_no="'+rndVo.em_no+'" data-am_no="'+rndVo.dm_at_am_no+'" data-ai_path="'+rndVo.dm_at_path+'">';
						html += '		'+rndVo.dm_attend_de;
						html += '	</button>';
						
					}
					if(rndVo.dm_lvffc_de == ""){
						html += '	<button class="hotpink rht">미퇴근</button>';
					}else{
						html += '	<button class="skyblue rht img-view" data-type="2" data-at-date="'+rndVo.dm_lvffc_de+'" data-em_nm="'+rndVo.em_nm+'"  data-em_no="'+rndVo.em_no+'" data-am_no="'+rndVo.dm_lv_am_no+'" data-ai_path="'+rndVo.dm_lv_path+'">';
//						html += '		'+rndVo.dm_lvffc_de;
						html += '		퇴근';
						html += '	</button>';
//						html += '<div id="screenshot103002'+i+'" class="popup screenshot10300" style="margin-top:0">';
//						html += '	<div class="phead txt_left" style="margin-top:0"><span>촬영일시 : '+rndVo.dm_lvffc_de+'</span><a id="" class="phead-closex" data-type="2" data-idx="'+i+'">닫기</a></div>';
//						html += '	<span style="float:right;padding: 6px 8px 6px 0;font-size: small;">프로필 사진 적용</span>';
//						html += '	<div style="float:right;margin: 8px 10px;" class="inputCheck">';
//						html += '	<input type="checkbox" id="empImage103002'+i+'" />';
//						html += '	<label for="empImage103002'+i+'"></label>';
//						html += '	</div>';
//						
//						html += '	<div><img id="preview103002'+i+'" src="'+rndVo.dm_lv_path+'" alt="url preview" /></div>';
//						html += '</div>';
					}
					html += '	<div class="profile_con">';
					html += '		<ul>';
					html += '			<li class="p_name">'+rndVo.em_nm+'</li>';
					html += '			<li class="p_phone">'+rndVo.em_mbtl_num+'</li>';
					html += '			<li class="p_memo" data-em_no="'+rndVo.em_no+'"  data-em_nm="'+rndVo.em_nm+'" >순방일지';
					html += '			</li>';
					html += '		</ul>';
					html += '	</div>';
					html += '</div>';
					
				}
//				for (var i = 0; i < data.activityRndList.length; i++) {
//					var rndVo = data.activityRndList[i]; 
//					html += '<div class="profilebox">';
//					html += '	<div style="max-width:134px; height:142px; position: relative;overflow: hidden;" >';
//					if(rndVo.emp_ai_path == ""){
//						html += '<img id="profileImage10300'+i+'" src="/resources/images/thum_profile.png">';
//					}else{
//						html += '<img id="profileImage10300'+i+'" src="'+rndVo.emp_ai_path+'">';						
//					}
//					html += '	</div>';
//					if(rndVo.dm_attend_de == ""){
//						html += '	<button class="hotpink lft">미출근</button>';
//					}else{
//						html += '<div id="screenshot103001'+i+'" class="popup screenshot10300" style="margin-top:0">';
//						html += '	<div class="phead txt_left"><span>촬영일시 : '+rndVo.dm_attend_de+'</span><a id="" class="phead-closex" data-type="1" data-idx="'+i+'">닫기</a></div>';
//						html += '	<span style="float:right;padding: 6px 8px 6px 0;font-size: small;">프로필 사진 적용</span>';
//						html += '	<div style="float:right;margin: 8px 10px;" class="inputCheck">';
//						html += '	<input type="checkbox" id="empImage103001'+i+'" />';
//						html += '	<label for="empImage103001'+i+'"></label>';
//						html += '	</div>';
//						html += '	<div><img id="preview103001'+i+'" src="'+rndVo.dm_at_path+'" alt="url preview" /></div>';
//						html += '</div>';
//						
//						html += '	<button class="hotpink lft dmAttentDe" data-type="1" data-idx="'+i+'" data-em_nm="'+rndVo.em_nm+'" data-em_no="'+rndVo.em_no+'" data-am_no="'+rndVo.dm_at_am_no+'" data-ai_path="'+rndVo.dm_at_path+'">';
//						html += '		'+rndVo.dm_attend_de;
//						html += '	</button>';
//						
//					}
//					if(rndVo.dm_lvffc_de == ""){
//						html += '	<button class="skyblue rht">미퇴근</button>';
//					}else{
//						html += '	<button class="skyblue rht dmLvffcDe" data-type="2" data-idx="'+i+'" data-em_nm="'+rndVo.em_nm+'"  data-em_no="'+rndVo.em_no+'" data-am_no="'+rndVo.dm_lv_am_no+'" data-ai_path="'+rndVo.dm_lv_path+'">';
//						html += '		'+rndVo.dm_lvffc_de;
//						html += '	</button>';
//						html += '<div id="screenshot103002'+i+'" class="popup screenshot10300" style="margin-top:0">';
//						html += '	<div class="phead txt_left" style="margin-top:0"><span>촬영일시 : '+rndVo.dm_lvffc_de+'</span><a id="" class="phead-closex" data-type="2" data-idx="'+i+'">닫기</a></div>';
//						html += '	<span style="float:right;padding: 6px 8px 6px 0;font-size: small;">프로필 사진 적용</span>';
//						html += '	<div style="float:right;margin: 8px 10px;" class="inputCheck">';
//						html += '	<input type="checkbox" id="empImage103002'+i+'" />';
//						html += '	<label for="empImage103002'+i+'"></label>';
//						html += '	</div>';
//						
//						html += '	<div><img id="preview103002'+i+'" src="'+rndVo.dm_lv_path+'" alt="url preview" /></div>';
//						html += '</div>';
//					}
//					html += '	<div class="profile_con">';
//					html += '		<ul>';
//					html += '			<li class="p_name">'+rndVo.em_nm+'</li>';
//					html += '			<li class="p_phone">'+rndVo.em_mbtl_num+'</li>';
//					html += '			<li class="p_memo" data-em_no="'+rndVo.em_no+'"  data-em_nm="'+rndVo.em_nm+'" >순방일지';
//					html += '			</li>';
//					html += '		</ul>';
//					html += '	</div>';
//					html += '</div>';
//					
//				}
			}else{
				html += '<div class="none-data" style=""><i class="fa fa-info-circle"style=""></i><span>내용을 검색해 주세요.</span></div>';
			}
			
			$("#rndUserListBody").html(html);
			
//			$("#rndUserListBody").find("img").load(function() {
//		        var height = $(this).height();
//		        var width = $(this).width();
//		        console.log("height : " + height);
//		        console.log("width : " + width);
//		        if(height > width){
//		        	console.log("height ");
//		        	$(this).css("height","100%");
//		        	$(this).css("width","auto");
//		        }else{
//		        	console.log("width ");
//		        	$(this).css("height","auto");
//		        	$(this).css("width","100%");
//		        }
//		    }).error(function() {
//		        $(this).attr("src", "/resources/images/thum_profile.png");
//		    });
			$("#rndUserListBody img").error(function() {
		        $(this).attr("src", "/resources/images/thum_profile.png");
		        
		    });
			
			$("#navi10300").html(data.navi);
			
			//업무일지 열기
			$("#rndUserListBody .p_memo").click(function(){
				var baseDe =  $("#selectRndBaseDate").val();
				var em_no = $(this).attr("data-em_no");
				$("#rndDiaryDate").html(baseDe);
				$("#emNo10300").val(em_no);
				$("#rnd_name").text($(this).data("em_nm"));
				fnGetSmTab(em_no,baseDe);
				if(currBaseDate10300 == baseDe){
					$("#rndDiaryNext").addClass("last-base-date");
				}else{
					$("#rndDiaryNext").removeClass("last-base-date");
				}
				var tabOn = $("#rndTab10300 .tab_on")
				
				$("#activityRndPop210300").show();
				var cvsAt =  tabOn.data("cvs-at");
				if(cvsAt == "N"){
					$("#cvsView10300").hide();
					$("#slideView10300").show();
					
					var menuArr = ["curr","big","odd","pd","trt"];
					for (var i = 0; i < menuArr.length; i++) {
						if($("#"+menuArr[i]+"Status10300").is(":visible")){
							fnGetActivityRndData(menuArr[i]);
						}
					}
				}else{
					$("#slideView10300").hide();
					fnGetRndTabCvsItemView(tabOn.data("drc-innb"));
				}
				
				
			});
			
			//총원, 출근, 미출근, 퇴근, 미퇴근, 휴가 목록
			$(".attendLvffcAt10300").off().click(function(){
				currRndAttendLvffcAt = $(this).attr("data-attend_lvffc_at");
				fnGetActivityRndList10300(1);
			});
			
			//출근 이미지 출력
			$("#rndUserListBody .img-view").off().click(function(event){
				var aiPath =$(this).attr("data-ai_path");
				var attend_de =$(this).attr("data-at-date");
				var emNo =$(this).attr("data-em_no");
				var amNo =$(this).attr("data-am_no")
				
				$("#rndPrevAtendDe").text(attend_de);
				
				$("#rndPrevAtendCheck").data("ai_path",aiPath);
				$("#rndPrevAtendCheck").data("em_no",emNo);
				$("#rndPrevAtendCheck").data("am_no",amNo);
				
    			$("#rndPrevAtendImg").attr("src",aiPath);
    			
    			$("#activityRndAtendImgView10300").show();
			});

			
			//퇴근 이미지 출력
//			$("#rndUserListBody .dmLvffcDe").off().click(function(event){
//				fnGetDmAttentLvffcImage10300($(this).data("type"), $(this).data("idx"), $(this).offset().left, $(this).offset().top, $(this).attr("data-em_no"), $(this).attr("data-am_no"), $(this).attr("data-ai_path"));
//			});
			
			//닫기 적용
//			$("#rndUserListBody .phead-closex").click(function(){
//				$("#screenshot10300"+$(this).data("type")+$(this).data("idx")).hide();
//			});
		}
	});
}

function fnGetSmTab(emNo,baseDe){
	$.ajax({
		url : "/rnd/smList",
	    data:{
	    	em_no : emNo ,
	    	base_de : baseDe.dateReplace()
    	},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var html = "<ul>";
	    	var classNm="tab_on";
	    	if(data.length > 0 ){
	    		for (var i = 0; i < data.length; i++) {
	    			var rndPlanStrVo  = data[i];
	    			var imgUrl = rndPlanStrVo.sm_img_url;
	    			var cvsAt = rndPlanStrVo.sm_cvscafe_at;
	    			html +="<li";
	    			html +=" class='"+classNm+"'";
	    			html +=" data-sm-code='"+rndPlanStrVo.sm_code+"'";
	    			html +=" data-sm-nm='"+rndPlanStrVo.sm_nm+"'";
	    			html +=" data-sm-la='"+rndPlanStrVo.sm_la+"'";
	    			html +=" data-sm-lo='"+rndPlanStrVo.sm_lo+"'";
	    			html +=" data-cvs-at='"+cvsAt+"'";
	    			html +=" data-drc-innb='"+rndPlanStrVo.drc_innb+"'";
	    			html +=" >";
	    			//				html +="<i class='fa fa-female'></i>";
	    			if(i == 0){
	    				classNm='';
	    			}
	    			if(imgUrl != ""){
//	    				html +="<a id='' ><span><img src='/resources/images/report_work.png' style='width:100%;height:100%;'></span>"+rndPlanStrVo.sm_nm+"</a>";
	    				html +="<a id='' ><span><img src='"+imgUrl+"'  style='width:20px;height:20px;' ></span>"+rndPlanStrVo.sm_nm+"</a>";
	    			}else{
	    				html +="<a id='' >"+rndPlanStrVo.sm_nm+"</a>";
	    			}
	    			html +="</li>";
	    		}
	    		html +="</ul>";
	    		$("#rndTab10300").html(html);
	    		
	    		$("#rndTab10300 img").error(function() {
			        $(this).attr("src", "/resources/images/report_work_defult.png");
			    });
	    		$("#rndTab10300").find("img").click(function(){
	    			var src = $(this).attr("src");
	    			$("#activityRndPreView10300").show();
	    			$("#rndPrevImg").attr("src",src);
	    		});
	    		$("#rndTab10300 li").click(function(){
	    			if(!$(this).hasClass("tab_on")){
	    				var cvsAt =  $(this).data("cvs-at");
	    				if(cvsAt == "N"){
	    					$("#cvsView10300").hide();
	    					$("#slideView10300").show();
	    					$("#rndTab10300 li").removeClass("tab_on");
	    					$(this).addClass("tab_on");
	    					
	    					var menuArr = ["curr","big","odd","pd","trt"];
	    					for (var i = 0; i < menuArr.length; i++) {
	    						if($("#"+menuArr[i]+"Status10300").is(":visible")){
	    							fnGetActivityRndData(menuArr[i]);
	    						}
	    					}
	    				}else{
	    					$("#rndTab10300 li").removeClass("tab_on");
	    					$(this).addClass("tab_on");
	    					$("#slideView10300").hide();
	    					fnGetRndTabCvsItemView($(this).data("drc-innb"));
	    				}
	    			}
//				alert($(this).attr("data-sm-code"));
	    		});
	    	}else{
	    		$("#cvsView10300").hide();
	    		$("#slideView10300").hide();
	    		$("#rndTab10300").html('<div style="text-align: center;">순회매장이 존재하지 않습니다.</div>');
	    	}
	    }
	});
}

function fnGetRndTabCvsItemView(drcInnb){
	$.ajax({
		url : "/cvs/itemlist",
		type : "POST",
		dataType : "json",
		data:{"drcInnb":drcInnb},
		cache : false,
		success : function(data) {
			var itemList = data.cvsVoList;
			var itemMatter = data.matter;
			var html = '';
			var itemLen = itemList.length;
			var groupCnt = 0;
			var rcciStatus = ["-","양호","보통","불량"]
			var rcciColor = ["#0B81FF","#0B81FF","#4c4c4c","#FF6369"]
			if(itemLen > 0){
				for (var i = 0; i < itemLen; i++) {
					var itemVo = itemList[i];
					html+='<tr>';
					if(groupCnt <= i){
						groupCnt += parseInt(itemVo.child_cnt);
//						console.log("groupCnt :" + groupCnt);
//						console.log("child_cnt " +itemVo.child_cnt);
						html+='	<th rowspan="'+itemVo.child_cnt+'" >'+itemVo.drcc_c_code_nm+'</th>';
					}
					html+='	<td class="align-l">'+itemVo.drcci_c_code_nm+'</td>';
					html+='	<td style="color:'+rcciColor[itemVo.drcci_state]+'" >'+rcciStatus[itemVo.drcci_state]+'</td>';
					html+='	<td class="align-l">'+itemVo.drcci_c_desc+'</td>';
					html+='</tr>';
				}
				
				$("#cvsItemTfoot10300").html("<tr><th>특이사항</th><td colspan='3' class='align-l'>"+itemMatter+"</td></tr>");
			}else{
				html= "<td colspan='4' >내용이 없습니다.</td>";
			}
			$("#cvsItemTbody10300").html(html);
			$("#cvsView10300").show();
		}
	});
}


var maxWidth = 300; // Max width for the image
var maxHeight = 190;    // Max height for the image
var ratio = 0;  // Used for aspect ratio

var pageX = 0;
var pageY = 0;

function fnGetDmAttentLvffcImage10300(type, idx, offsetX, offsetY, emNo, amNo, aiPath){
	$("#screenshot10300"+type+idx).show();
		
	var width = $("#preview10300"+type+idx).width();
	var height = $("#preview10300"+type+idx).height();
	pageX = offsetX;
	pageY = offsetY;
	  
	if(width > maxWidth){
	  ratio = maxWidth / width;   // get ratio for scaling image
	  $("#preview10300"+type+idx).css("width", maxWidth); // Set new width
	  $("#preview10300"+type+idx).css("height", maxHeight);  // Scale height based on ratio
	  height = height * ratio;    // Reset height to match scaled image
	  width = width * ratio;    // Reset width to match scaled image
	}
	
//	$("#screenshot10300").css("top",(pageY+10)+"px").css("left",(pageX - width)+"px").fadeIn("fast");
//	$("#screenshot10300").css("top",pageY+"px").css("left",pageX+"px").fadeIn("fast");
	//$("#screenshot10300").css("top",pageY-height+"px").css("left",pageX-width+"px").fadeIn("fast");
	
	$("#screenshot10300"+type+idx).css("top",offsetY-25+"px").css("left",offsetX-130+"px").fadeIn("fast");
	
	$("#empImage10300"+type+idx).off().change(function(){
		if($(this).is(":checked")){
			fnSaveActivityRndEmpAiNo(type, idx, emNo, amNo, aiPath);
		}
	});
}

//활동관리 고정MD 프로필 사진 출퇴근시 이미지로 변경.
function fnSaveActivityRndEmpAiNo(emNo, amNo, aiPath){
	var data = {
		em_no : emNo
		, am_no : amNo
	};
	
	$.ajax({
		url : "/rnd/rndSaveEmpAiNo",
		type : "POST",
	    data : data,
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
	    	var result = data.result;
    		if (result != null && result.length != 0) {
    			alert("저장되었습니다.");
    			$("#profileImage10300"+emNo).attr("src", aiPath);
    		} else {
    			alert("저장에 실패하였습니다.");
    		}
	    }
	});
}

function fnActivityRndValidation10300(){
	if($("#searchBhf10300").val() == "" || $("#searchBhf10300").val() == null){
		alert("지점을 선택하세요.");
		return false;
	}
	
	if($("#searchDate10300").val() == "" || $("#searchDate10300").val() == null){
		alert("날짜를 선택하세요.");
		return false;
	}
	
	return true;
}
