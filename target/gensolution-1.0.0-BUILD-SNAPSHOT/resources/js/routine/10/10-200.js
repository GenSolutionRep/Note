var options10200;
var currBaseDate10200 = "";
var currSysDate10200 = "";
var trtFixingComboOption;
var trtFixingPrdJoin;
var currFixingAttendLvffcAt ="";
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
//	fnGetTrtComboOptions("1103010220");
	$.get('/option/fixing-options.json',function(data){
		options10200 = eval(data);
	});
	trtFixingComboOption = fnGetTrtFirstComboOptions("",1);
	trtFixingPrdJoin =fnGetProdComboOptionsAll(); // 모든 정보
//	console.log(trtFixingPrdJoin);
//	alert(trtFixingPrdJoin["11"].pm_nm);
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#activityFixingPop210200").instancePopUp();
	$("#activityFixingPreView210200").instancePopUp();
	$("#activityFixingAtendImgView10300").instancePopUp();
	$("#activityFixingOptionPop10200").instancePopUp();
	$("#activityFixingEVNOptionPop210200").instancePopUp();
	$("#activityFixingTRTOptionPop210200").instancePopUp();
//	$("#activityFixingPOGOptionPop210200").instancePopUp();
//	$("#activityFixingBIGOptionPop210200").instancePopUp();
//	$("#searchToDate10200").winCal(baseOptions);	
//	$("#searchFromDate10200").winCal(baseOptions);
	
	$("#activityFixingAtendImgViewCloseXBtn").click(function(event){
		$("#activityFixingAtendImgView10300").hide();
	});
	
	fnGetFixSearchComboBox("",1); // 소속가져오기
	//근태일자 선택
	$("#searchDate10200").winCal(baseOptions);
	//DB SYSDATE
	var sysdate = getSysDate(2);
	currSysDate10200 = sysdate;
	//기본날짜 설정
	$("#searchDate10200").val(sysdate);
//	$("#searchDate10200").val("2015-10-14");
	//오늘날짜의 기준일자 설정
	currBaseDate10200 = sysdate.setBaseDate("first");
	//검색조건에 기준일자 등록
	$("#selectBaseDate").val(currBaseDate10200);
	fnSetEventComponent();
	
	
	if(auth_flag >1){
		fnGetActivityFixingInfo10200(1);
	}
	if(auth_flag >2){
		$("#searchBox10200").hide();
	}
	
});

function workModiCheck(){
	if($("#workStatus10200").data("update-flag") == "Y"){
		if(confirm("근무계획이 저장되지 않았습니다.\n계속 진행하시겠습니까?")){
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}
//검색 콤보 박스
function fnGetFixSearchComboBox(om_code ,om_orgnzt_se){
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
				$("#searchBhf10200").html(listHtml);
				$("#searchBhf10200").change(function(){
					fnGetFixSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetFixSearchComboBox($("#searchBhf10200").val() ,2);
//					fnGetActivityFixingInfo10200(1, "");// 조회
				}
			}else if(om_orgnzt_se==2){
				$("#searchTeam10200").html(listHtml);
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
			$(".tbl_tab.event ul li").removeClass("tab_on");
			$("."+groupId+"-group").hide();
			
			$(this).addClass("tab_on");
			$("."+groupId+"-"+target+"-group").show();
		}
	});
	$("#activityFixingPreViewCloseXBtn").click(function(){
		$("#activityFixingPreView210200").hide();
	});
	
	
	//업무일지 닫기
	$("#activityFixingPopClose10200, #activityFixingPopCloseX10200").click(function(){
		if(workModiCheck()){
			$("#activityFixingPop210200").hide();
		}
	});
	$("#rndExcelDownLoad10200").click(function(){
		var base_de = $("#selectBaseDate").val();
		var em_no = $("#emNo10200").val();
		location.href='/fixing/excel?base_de='+base_de.dateReplace()+"&em_no="+em_no;
	});
	//초기화
	$("#clearActivityFixing10200").click(function(){
		$("#searchStoreNm10200").val("");
		$("#searchTeam10200").val(0);
		$("#searchBhf10200").val(0);
		$("#searchEmpNm10200").val("");
		$("#searchDate10200").val(currSysDate10200);
		if($("#searchBhf10200").val() != ""){
			fnGetActivityFixingInfo10200(1);
		}else{
			$("#fixingUserListBody").html('<div class="none-data" style=""><i class="fa fa-info-circle"style=""></i><span>내용을 검색해 주세요.</span></div>');
		}
	});
	
	//이전업무일지
	$("#diaryPrev").click(function(){
		if(workModiCheck()){
			var baseDate =  $("#selectBaseDate").val().setBaseDate("prev");
			var prevFirst = baseDate;
			var prevLast = baseDate.setBaseDate("last");
			$("#selectBaseDate").val(baseDate);
			$("#diaryDate").html(prevFirst+" ~ "+prevLast);
			var menuArr = ["evn","pog","big","pd","odd","trt","work"];
			for (var i = 0; i < menuArr.length; i++) {
				if($("#"+menuArr[i]+"Status10200").is(":visible")){
					fnGetActivityData(menuArr[i]);
				}
			}
			$("#diaryNext").removeClass("last-base-date");
		}
	});
	//다음업무일지
	$("#diaryNext").click(function(){
		if(workModiCheck()){
			if(currBaseDate10200 == $("#selectBaseDate").val()){
				alert("마지막 데이터 입니다.");
				return;
			}
			var baseDate =  $("#selectBaseDate").val().setBaseDate("next");
			var nextFirst = baseDate;
			var nextLast = baseDate.setBaseDate("last");
			$("#selectBaseDate").val(baseDate);
			$("#diaryDate").html(nextFirst+" ~ "+nextLast);
			var menuArr = ["evn","pog","big","pd","odd","trt","work"];
			for (var i = 0; i < menuArr.length; i++) {
				if($("#"+menuArr[i]+"Status10200").is(":visible")){
					fnGetActivityData(menuArr[i]);
				}
			}
			if(currBaseDate10200 == $("#selectBaseDate").val()){
				$(this).addClass("last-base-date");
			}else{
				$(this).removeClass("last-base-date");
			}
		}
	});
	// 근무계획 저장
	$("#workSaveBtn10200").click(function(){
		var nextBaseDate = $("#selectBaseDate").val().setBaseDate("next").dateReplace();
		var workForm = $("#workTbody10200");
		var dfwp_thw_at  =  workForm.find(":radio[name='work_thw']:checked").val();
		var dfwp_fri_at  =  workForm.find(":radio[name='work_fri']:checked").val();
		var dfwp_sat_at  =  workForm.find(":radio[name='work_sat']:checked").val();
		var dfwp_sun_at  =  workForm.find(":radio[name='work_sun']:checked").val();
		var dfwp_mon_at  =  workForm.find(":radio[name='work_mon']:checked").val();
		var dfwp_tue_at  =  workForm.find(":radio[name='work_tue']:checked").val();
		var dfwp_wed_at  =  workForm.find(":radio[name='work_wed']:checked").val();
		var workData = {
				 "dfwp_innb":$("#dfwpInnb10200").val()
				,"base_de":nextBaseDate
				,"em_no":$("#emNo10200").val()
				,"saveType":$("#workStatus10200").data("save-type")
				,"dfwp_thw_at":dfwp_thw_at
				,"dfwp_fri_at":dfwp_fri_at
				,"dfwp_sat_at":dfwp_sat_at
				,"dfwp_sun_at":dfwp_sun_at
				,"dfwp_mon_at":dfwp_mon_at
				,"dfwp_tue_at":dfwp_tue_at
				,"dfwp_wed_at":dfwp_wed_at
		};
		$.ajax({
			url : "/fixing/workSave",
			type : "POST",
		    data : workData,
			dataType : "json",
			async :false,
			cache : false,
			success : function(data) {
	    		if (data != 0) {
	    			alert("저장되었습니다.");
	    			$("#workStatus10200").data("update-flag","N")
	    		} else {
	    			alert("저장에 실패하였습니다.");
	    		}
		    }
		});
	});
	
	
	//아코디언 펼치치 / 닫기
	$("#slideView").find(".toogle-slide").click(function(){
		var $this = $(this);
		var type = $this.find("i").data("type");
//		var target = $this.data("target");
		if(typeof type == "undefined" ){
			alert("준비중입니다.");
			return;
		}
		if(type =="work" && $("#"+type+"Status10200").is(":visible") ){
			if(!workModiCheck()){
				return;
			} 
		}
		$this.parent().toggleClass("aco-selectd");
		//열려있는 항목만 조회
		if(!$("#"+type+"Status10200").is(":visible")){
			fnGetActivityData(type); // 일지관리 항목 조회 통합
		}
		//+,- 아이콘 변경
		$("#"+type+"Status10200").slideToggle(function(){
			$this.find("i").toggleClass("fa-minus-square");
		});
		
	});
	
	
	
	
	
	
	//지점목록 가져오기
//	fnMakeComboBoxBhf10200("001", "searchBhf10200");
//	cboSelectDepthList_10200("001", "searchBhf10200", 1);
	
	//활동관리 고정MD 조회
	$("#searchActivityFixing10200").click(function(){
		fnGetActivityFixingInfo10200(1);
//		fnGetActivityFixingView10200();
//		fnGetActivityFixingList10200(1, "");
	});
	
	
	//공통 옵션창 열기
	$("#slideView a.options.comm-group").click(function(){
		
		$("#optionLeftTab10200").trigger("click");			//1번텝 활성화
		$("#activityFixingOptionPop10200").show();
		var type = $(this).data("type");
		$("#optionLeftAddRow10200").data("type",type);	//1번텝 행추가버튼
		$("#optionRightAddRow10200").data("type",type); //2번텝 행추가버튼
		$("#optionLeftSaveBtn10200").data("type",type);		//1번텝 저장버튼
		$("#optionRightSaveBtn10200").data("type",type);		//2번텝 저장버튼
		fnGetOptionList(options10200[type+"1"]);		//1번텝	조회
		fnGetOptionList(options10200[type+"2"]);		//2번텝 조회
	});	

	//공통 옵션창 닫기
	$("#activityFixingOptionCloseXBtn").click(function(){
		$("#activityFixingOptionPop10200").hide();
	});
	//공통 옵션창 행추가
	$("#optionLeftAddRow10200").click(function(){
		var type = $(this).data("type");
		fnMakeOptionAppend(options10200[type+"1"]);
	});
	$("#optionRightAddRow10200").click(function(){
		var type = $(this).data("type");
		fnMakeOptionAppend(options10200[type+"2"]);
	});
	//공통 옵션창 저장
	$("#optionLeftSaveBtn10200").click(function(){
		var type = $(this).data("type");
		fnSaveActivityOption10200(options10200[type+"1"]);
	});
	$("#optionRightSaveBtn10200").click(function(){
		var type = $(this).data("type");
		fnSaveActivityOption10200(options10200[type+"2"]);
	});
	
	
	
	//행사매대 행추가
	$("#evnStnAddRow10200").click(function(){
		fnMakeEvnOptionAppend(options10200["evn1"]);
	});
	$("#evnTypeAddRow10200").click(function(){
		fnMakeEvnOptionAppend(options10200["evn2"]);
	});
	//행사매대 옵션창 닫기
	$("#evnOptionCloseXBtn").click(function(){
		$("#activityFixingEVNOptionPop210200").hide();
	});
	//행사매대 옵션창열기
	$("#evnOptionOpenBtn").click(function(){
		$("#activityFixingEVNOptionPop210200").show();
		fnGetEvnOptionList(options10200["evn1"],"");		//1번텝	조회
		fnGetEvnOptionList(options10200["evn2"],"");		//2번텝 조회
	});	
	//행사매대옵션창 저장
	$("#evnStnSaveRow10200").click(function(){
		fnSaveActivityOption10200(options10200["evn1"]);
	});
	$("#evnTypeSaveRow10200").click(function(){
		fnSaveActivityOption10200(options10200["evn2"]);
	});
	
	
	//취급품목관리 행추가
	$("#trtStnAddRow10200").click(function(){
		fnMakeFixingTrtOptionAppend(options10200["trt1"]);
	});
	//취급품목관리 옵션창 닫기
	$("#trtOptionCloseXBtn10200").click(function(){
		$("#activityFixingTRTOptionPop210200").hide();
	});
	//취급품목관리 옵션창열기
	$("#trtOptionOpenBtn10200").click(function(){
		$("#activityFixingTRTOptionPop210200").show();
		fnGetTrtFixingOptionList(options10200["trt1"]);
	});	
	//취급품목관리 저장
	$("#trtStnSaveRow10200").click(function(){
		fnSaveActivityOption10200(options10200["trt1"]);
	});
	$("#fixingPrevAtendCheck").change(function(){
		if($(this).is(":checked")){
			if(confirm("프로필사진을 변경하시겠습니까?")){
				var aiPath =$(this).attr("data-ai_path");
				var emNo =$(this).attr("data-em_no");
				var amNo =$(this).attr("data-am_no")
				fnSaveActivityFixingEmpAiNo( emNo, amNo, aiPath);
			}
		}
	});
	$("#trtTbody10200 .trt-change-group").change(function(){
//		var depth = $(this).data("trt-depth");
//		$(this).parent().parent().find(".nm").val($(this).find("option:selected").text());
		var thisCode = $(this).val();
		var thisCodeNm = $(this).find("option:selected").text();
		if(thisCode == ""){
			thisCodeNm ="";
		}
		$(this).parent().parent().find(".nm").attr("data-parent-code",thisCode);
		$(this).parent().parent().find(".nm").val(thisCodeNm);
		fnGetFixingTrtComboDepthList($(this));
//		if($(this).val()==""){
//			$("#pm_parent_no_99200").val("");
//			$("#pm_dp_99200").val("1");
//			$("#pm_parent_no_D2_99200, #pm_parent_no_D3_99200, #pm_parent_no_D4_99200").html(tempVal);
//		}
//		else{
//			$("#pm_parent_no_99200").val($(this).val());
//			$("#pm_dp_99200").val("2");
//			cboSelectDepthList_99200($(this).val(), 2);
//		}
	});
	
}

/**
 * @함수명: fnGetFixingTrtComboDepthList
 * @작성일: 2015. 09. 24.
 * @작성자: 김진호
 * @설명: 콤보 Depth 
 * @param 
 */
function fnGetFixingTrtComboDepthList(el){
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
function fnGetTrtComboOptions(pmCode,cut,type){
	var parenCode = pmCode.substring(0,cut);
	var currCode = pmCode.substring(0,cut+2);
	var pm_dp = parenCode.length/2;
	var json = {};
	var arr ={};
//	console.log("parenCode : " + parenCode);
//	console.log("currCode : " + currCode);
//	console.log("pmCode : " + pmCode);
//	console.log("pmCode.length : " + pmCode.length);
//	console.log("cut+2 : " + (cut+2));
	if(pmCode.length < parseInt(cut) && type == "options"){
		return "<option value ='' > 선택 </option>";
	}else if(pmCode.length < parseInt(cut)){
		return "";
	}
//	console.log("pm_code : " + parenCode);
//	console.log("pm_dp : "+pm_dp);
//	console.log("trtFixingPrdJoin : "+trtFixingPrdJoin);
	if(parenCode == ""){
		arr = trtFixingPrdJoin;
	}else{
		json = trtFixingPrdJoin[parenCode.substring(0,2)];
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
function fnSaveActivityOption10200(options){
	var oi_type = options.code;
	var group = options.groupId;
	
	if(group =="evn-type" ){
		oi_type = $("#optionIoType10200").val();
	}
	var optionAddGroup = $("."+group+"-add-group");
	var optionArr = new Array();
	if(optionAddGroup.length < 1){
		alert("행을 추가해주세요");
		return;
	}
	for (var i = 0; i < optionAddGroup.length; i++) {
		var optionVo = optionAddGroup.eq(i);
		var oi_code = optionVo.attr("data-code");
		var oi_nm = optionVo.find(".nm").val();
		var oi_nick_nm = optionVo.find(".nick-nm").val();
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
//			alert("oi_parn_code : " + oi_parn_code);
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
					alert(useText + "는사용 중인 약어입니다.");
				}else{
					alert("저장되었습니다.");
				}
				if(options.groupId =="evn-stn"){
					fnGetEvnOptionList(options,"");
				}else if(options.groupId =="evn-type"){
					fnGetEvnOptionList(options,oi_type);
				}else if(options.groupId =="trt-stn"){
					fnGetTrtFixingOptionList(options);
				}else{
					fnGetOptionList(options);	
				}
			}else{
				alert("저장에 실패하였습니다.");
			}
		}
	});
}


//옵션가져오기 공통
function fnGetOptionList(options){
	
	$("#optionIoType10200").val(options.code);
	$("#"+options.target+"NickNmText10200").text(options.nickNmText);
	$("#"+options.target+"NmText10200").text(options.nmText);
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
//					html +='<td><input type="text" class="nick-nm" value="'+optionVo.oi_nick_nm+'" ></td>';
					html +='<td>'+optionVo.oi_nick_nm+'</td>';
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
			$("#"+options.target+"Tbody10200").html(html);	
			$("#"+options.target+"Tbody10200").find(".sort").onlyNum();	
			$("#"+options.target+"Tab10200").html(options.tabName);	
			$("#optionTitle10200").html(options.title);	
			$("#optionPrantCode10200").html(options.code);	
			
			$("#"+options.target+"AddRow10200").val(options.code);
		}
	});
}
//옵션가져오기 행사매대
function fnGetEvnOptionList(options,pran_code){	
	var group = options.groupId;
	var code = options.code;
	if(pran_code != ""){
		code =pran_code
	}
	$("#optionIoType10200").val(code);
	$.ajax({
		url : "/option/list",
		type : "POST",
		data : {
			"optionCode":code
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
					if(group == "evn-stn"){
						html +='<td>';
						html +='<button class="skyblue select-row" value="'+code+'" >선택</button>';
						html +='</td>';
					}
					html +='</tr>';
				}else{
					html +='<tr class="'+group+'-add-group" data-code="'+code+'" >';
//					html +='<td><input type="text" class="nick-nm"  value="'+optionVo.oi_nick_nm+'"  ></td>';
					html +='<td>'+optionVo.oi_nick_nm+'</td>';
					html +='<td><input type="text" class="nm"  value="'+optionVo.oi_nm+'"  ></td>';
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
					if(group == "evn-stn"){
						html +='<td>';
						html +='<button class="skyblue select-row" value="'+code+'" >선택</button>';
						html +='</td>';
					}
					html +='</tr>';
				}
			}
			$("#"+options.target+"Tbody10200").html(html);	
			$("#"+options.target+"Tbody10200").find(".sort").onlyNum();
			$("#"+options.target+"Tab10200").html(options.tabName);	
			$("#optionTitle10200").html(options.title);	
			$("#optionPrantCode10200").html(options.code);	
			$("#"+options.target+"AddRow10200").val(options.code);
			if(group == "evn-stn"){
				$("#"+options.target+"Tbody10200 tr:first-child").addClass("cell_active");
				$("#"+options.target+"Tbody10200 > tr").find(".select-row").click(function(){
					$("#"+options.target+"Tbody10200 tr").removeClass("cell_active");
					fnGetEvnOptionList(options10200["evn2"],$(this).val());
					$(this).parent().parent().addClass("cell_active");
				});
			}
			
		}
	});
}


//옵션가져오기 취급품목
function fnGetTrtFixingOptionList(options){	
	var group = options.groupId;
	var code = options.code;
	$("#optionIoType10200").val(code);
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
				if(defaultAt == "Y"){ 
					var checked = "";
					checked = "<i class='fa fa-check'></i>"
					html +='<tr>';
					html +='<td>'+fnGetTrtComboOptions(oi_parn_code,0)+'</td>';
					html +='<td>'+fnGetTrtComboOptions(oi_parn_code,2)+'</td>';
					html +='<td>'+fnGetTrtComboOptions(oi_parn_code,4)+'</td>';
					html +='<td>'+fnGetTrtComboOptions(oi_parn_code,6)+'</td>';
					html +='<td>'+fnGetTrtComboOptions(oi_parn_code,8)+'</td>';
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
					html +=fnGetTrtComboOptions(oi_parn_code,0,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select class="select-basic trt-change-group" data-trt-depth="2" >';
					html +=fnGetTrtComboOptions(oi_parn_code,2,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select class="select-basic trt-change-group" data-trt-depth="3" >';
					html +=fnGetTrtComboOptions(oi_parn_code,4,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select id="trtComboDept3" class="select-basic trt-change-group" data-trt-depth="4" >';
					html +=fnGetTrtComboOptions(oi_parn_code,6,"options");
					html +='</select>';
					html +='</td>';
					html +='<td>';
					html +='<select class="select-basic trt-change-group" data-trt-depth="5" >';
					html +=fnGetTrtComboOptions(oi_parn_code,8,"options");
					html +='</select>';
					html +='</td>';
					html +='<td><input type="text" class="nm" data-parent-code="'+oi_parn_code+'"  value="'+optionVo.oi_nm+'"  ></td>';
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
			$("#"+options.target+"Tbody10200").html(html);	
			$("#"+options.target+"Tbody10200").find(".sort").onlyNum();
			$("#"+options.target+"Tab10200").html(options.tabName);	
			$("#optionTitle10200").html(options.title);	
			$("#optionPrantCode10200").html(options.code);	
			$("#"+options.target+"AddRow10200").val(options.code);
			$("#"+options.target+"Tbody10200 .trt-change-group").change(function(){
				var thisCode = $(this).val();
				var thisCodeNm = $(this).find("option:selected").text();
				if(thisCode == ""){
					thisCodeNm ="";
				}
				$(this).parent().parent().find(".nm").attr("data-parent-code",thisCode);
				$(this).parent().parent().find(".nm").val(thisCodeNm);
				fnGetFixingTrtComboDepthList($(this));
			});
			
		}
	});
}

//행추가 공통
function fnMakeOptionAppend(options){
	var maxCnt = options.maxCnt
	var rowSize = $("#"+options.target+"Tbody10200"+" > tr").length;
	if((rowSize > maxCnt -1) && maxCnt > 0){
		alert("이미 최대갯수 입니다.");
		return;
	}
	var group = options.groupId;
	var html ="";
	html +='<tr class="'+group+'-add-group"  data-code="" >';
	html +='<td><input type="text" class="nick-nm" value="" ></td>';
	html +='<td><input type="text" class="nm" value="" ></td>';
	html +='<td><input type="text" class="sort" value="" ></td>';
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
	$("#"+options.target+"Tbody10200").append(html);	
	$("#"+options.target+"Tbody10200").find(".sort").onlyNum();
	$("#"+options.target+"Tbody10200 > tr").last().find(".del-row").click(function(){
		$(this).parent().parent().remove();
	});	
}
//행추가 행사매대
function fnMakeEvnOptionAppend(options){
	var maxCnt = options.maxCnt
	var rowSize = $("#"+options.target+"Tbody10200"+" > tr").length;
	if((rowSize > maxCnt -1) && maxCnt > 0){
		alert("이미 최대갯수 입니다.");
		return;
	}
	var group = options.groupId;
	var html ="";
	html +='<tr class="'+group+'-add-group" data-code="" >';
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
	if(group == "evn-stn"){
		html +='<td>';
		html +='<button class="disabled" id="" >선택</button>';
		html +='</td>';
	}
	html
	html +='</tr>';
	$("#"+options.target+"Tbody10200").append(html);	
	$("#"+options.target+"Tbody10200").find(".sort").onlyNum();
	$("#"+options.target+"Tbody10200 > tr").last().find(".del-row").click(function(){
		$(this).parent().parent().remove();
	});	
}
//행추가 취급품목
function fnMakeFixingTrtOptionAppend(options){
	var maxCnt = options.maxCnt
	var rowSize = $("#"+options.target+"Tbody10200"+" > tr").length;
	if((rowSize > maxCnt -1) && maxCnt > 0){
		alert("이미 최대갯수 입니다.");
		return;
	}
	var group = options.groupId;
	var html ="";
	html +='<tr class="'+group+'-add-group" data-code="" >';
	html +='<td>';
	html +='<select class="select-basic trt-change-group" data-trt-depth="1" >';
	html +=trtFixingComboOption;
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
	html +='<td><input type="text" class="nm" data-parent-code="" ></td>';
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
	$("#"+options.target+"Tbody10200").append(html);	
	$("#"+options.target+"Tbody10200").find(".sort").onlyNum();
	$("#"+options.target+"Tbody10200 > tr").last().find(".del-row").click(function(){
		$(this).parent().parent().remove();
	});	
	$("#"+options.target+"Tbody10200 .trt-change-group").change(function(){
//		$(this).parent().parent().find(".nm").val($(this).find("option:selected").text());
		var thisCode = $(this).val();
		var thisCodeNm = $(this).find("option:selected").text();
		if(thisCode == ""){
			thisCodeNm ="";
		}
		$(this).parent().parent().find(".nm").attr("data-parent-code",thisCode);
		$(this).parent().parent().find(".nm").val(thisCodeNm);
		fnGetFixingTrtComboDepthList($(this));
	});
}


function fnGetActivityData(type){
	var base_de = $("#selectBaseDate").val();
	base_de = (type != "work" ? base_de:base_de.setBaseDate("next"));
	$.ajax({
		url : "/fixing/"+type+"list",
		type : "POST",
		data : {
			"base_de": base_de.dateReplace()
			,"em_no":$("#emNo10200").val()
		},
		dataType : "json",
		global:true,
//		async :false,
		cache : false,
		success : function(data) {
			switch(type){
				case "evn": fnMakeFixingEvnList(data,type); break;	//행사매대
				case "pog": fnMakeDoubleGrid(data,type); break;	//POG 현황
				case "big": fnMakeFixingSingleGrid(data,type); break;	//보조진열현황
				case "pd": fnMakeFixingSingleGrid(data,type); break;	//PD매대현황
				case "odd": fnMakeFixingOddList(data); break;			//시황및 득이사항
				case "trt": fnMakeFixingTrtList(data); break;			//취급품목
				case "work": fnMakeFixingWorkList(data); break;		//근무계획
				default : alert("준비중입니다.");
			}
		}
	});
}
function fnMakeFixingTrtList(trtList){
	var colHtml = "<tr><th>구분</th>";
	var rowHtml = "<tr><td>취급여부</td>";
	if(trtList.length > 0 ){
		$("#trtUpdateDate10200").html("최종 업데이트 : "+trtList[0].updt_de);
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
		$("#trtUpdateDate10200").html("");
//		colHtml="<tr><td>내용이 없습니다.</td></tr>";
//		rowHtml="";
	}
	$("#trtThead10200").html(colHtml);
	$("#trtTbody10200").html(rowHtml);
	
}
function fnMakeFixingWorkList(workVo){
	$("#workStatus10200").data("update-flag","N");
	var html ="";
	var rows = ["오전","오후1","오후2","휴무"];
	if(workVo){
		var nextBaseDate = currBaseDate10200.setBaseDate("next"); // 다음주 기준일
		var workBaseDate = workVo.base_de.dateFormat();			  // 넘어온데이터 기준일
		$("#workUpdateDate10200").html("* "+workBaseDate.addDay() +" ~ "+workBaseDate.setBaseDate("last").addDay()); //기간
		
		$("#workThead10200").html(fnGetWeekHead(workBaseDate));
		//내가 작성하고 데이터기준일자와 다음주 계획일자랑 동일할때
		if(nextBaseDate == workBaseDate && workVo.dfwp_innb != ""){
			$("#workStatus10200").data("save-type","UPDATE");
			$("#dfwpInnb10200").val(workVo.dfwp_innb);
			for (var i = 0; i < rows.length; i++) {
				html += '<tr>';	
				html += '<th>'+rows[i]+'</th>';
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_thw'+i+'" name="work_thw" '+(workVo.dfwp_thw_at == i+1 ? "checked":"")+' />';
				html += '<label for="work_thw'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_fri'+i+'" name="work_fri" '+(workVo.dfwp_fri_at == i+1 ? "checked":"")+' />';
				html += '<label for="work_fri'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_sat'+i+'" name="work_sat" '+(workVo.dfwp_sat_at == i+1 ? "checked":"")+' />';
				html += '<label for="work_sat'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_sun'+i+'" name="work_sun" '+(workVo.dfwp_sun_at == i+1 ? "checked":"")+' />';
				html += '<label for="work_sun'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_mon'+i+'" name="work_mon" '+(workVo.dfwp_mon_at == i+1 ? "checked":"")+' />';
				html += '<label for="work_mon'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_tue'+i+'" name="work_tue" '+(workVo.dfwp_tue_at == i+1 ? "checked":"")+' />';
				html += '<label for="work_tue'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_wed'+i+'" name="work_wed" '+(workVo.dfwp_wed_at == i+1 ? "checked":"")+' />';
				html += '<label for="work_wed'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += "</tr>";
				$("#workSaveBtnDiv10200").show();
			}
		}else{//이미 작성한계획이거나 다른사람이 로그인
			var checked = "<i class='fa fa-check'></i>";
			$("#dfwpInnb10200").val("");
			for (var i = 0; i < rows.length; i++) {
				html += '<tr>';	
				html += '<td>'+rows[i]+'</td>';
				html += '<td>'+(workVo.dfwp_thw_at == i+1 ? checked :"")+'</td>';	
				html += '<td>'+(workVo.dfwp_fri_at == i+1 ? checked :"")+'</td>';	
				html += '<td>'+(workVo.dfwp_sat_at == i+1 ? checked :"")+'</td>';	
				html += '<td>'+(workVo.dfwp_sun_at == i+1 ? checked :"")+'</td>';	
				html += '<td>'+(workVo.dfwp_mon_at == i+1 ? checked :"")+'</td>';	
				html += '<td>'+(workVo.dfwp_tue_at == i+1 ? checked :"")+'</td>';	
				html += '<td>'+(workVo.dfwp_wed_at == i+1 ? checked :"")+'</td>';	
				html += "</tr>";
				$("#workSaveBtnDiv10200").hide();
			}
		}
			
	}else{ // 없으면 신규 등록
		var nextBaseDate = $("#selectBaseDate").val().setBaseDate("next");	// 다음주 기준일
		var workBaseDate = currBaseDate10200.setBaseDate("next");			// 데이터 기준일
		$("#workUpdateDate10200").html("* "+nextBaseDate.addDay() +" ~ "+nextBaseDate.setBaseDate("last").addDay()); //기간
		
		$("#workThead10200").html(fnGetWeekHead(nextBaseDate));
		
		// 본인 계획이고 오늘 기준으로 다음주 계획이 없을경우 신규등록 
		if(nextBaseDate == workBaseDate &&  $("#emNo10200").val() ==  login_no){ 
			$("#workStatus10200").data("save-type","INSERT");
			$("#dfwpInnb10200").val("");
			for (var i = 0; i < rows.length; i++) {
				html += '<tr>';	
				html += '<th>'+rows[i]+'</th>';
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_thw'+i+'" name="work_thw" />';
				html += '<label for="work_thw'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_fri'+i+'" name="work_fri"  />';
				html += '<label for="work_fri'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_sat'+i+'" name="work_sat"  />';
				html += '<label for="work_sat'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_sun'+i+'" name="work_sun"  />';
				html += '<label for="work_sun'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_mon'+i+'" name="work_mon"  />';
				html += '<label for="work_mon'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_tue'+i+'" name="work_tue"  />';
				html += '<label for="work_tue'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += '<td>';	
				html += '<div class="inputCheck_Radio">';
				html += '	<input type="radio" value="'+(i+1)+'" id="work_wed'+i+'" name="work_wed"  />';
				html += '<label for="work_wed'+i+'"></label>';
				html += '</div>';
				html += '</td>';	
				html += "</tr>";
			}
			$("#workSaveBtnDiv10200").show();
		}else{ //다른사람 로그인
			$("#dfwpInnb10200").val("");
			html += "<tr><td colspan='8' >내용이 없습니다.</td></tr>";
			$("#workSaveBtnDiv10200").hide();
		}
		
		
	}
	$("#workTbody10200").html(html);
	$("#workTbody10200").find("input[type='radio']").change(function(){
		$("#workStatus10200").data("update-flag","Y");
	});
	
}

function fnGetWeekHead(baseDate){
	
	var html = "<tr><th></th>";
	
	var weeks = ["일","월","화","수","목","금","토"]; //목요일
	for (var i = 0; i < 7 ; i++) {
		var now = new Date(baseDate); 
		now.setDate(now.getDate() + i);
		var nowWeek = now.getDay();
		var year= now.getFullYear();
		var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
		var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
		
		html += "<th>"+year+'-'+mon+'-'+ day+"("+weeks[nowWeek]+")</th>";
	}
	html += "</tr>";
	return html;
}
// Evn 생성
function fnMakeFixingEvnList(data){
	
	var evnColumn ='';
	var evnLen =data.evnList.length;
	var evnColumnLen =data.evnColumnList.length;

	for (var i = 0; i < evnColumnLen; i++) {
		var evnColVo = data.evnColumnList[i];
		evnColumn +='<div class="tbl_title">';
		evnColumn +='<span>'+(i+1)+'. '+evnColVo.oi_code_nm+' </span>';
		evnColumn +='<div style="float: right;">최종 업데이트 : <span>'+evnColVo.updt_de+'</span></div>';
		evnColumn +='</div>';
		evnColumn +='<table class="tbl_row">';
		evnColumn +='<colgroup>';
		evnColumn +='<col style="width:14%">';
		evnColumn +='<col style="width:86%">';
		evnColumn +='</colgroup>';
		evnColumn +='<tbody id="evn'+evnColVo.oi_code+'" >';
		evnColumn +='</tbody>';
		evnColumn +='</table>';
	}
	if(evnColumnLen == 0){
		evnColumn += "<table  class='tbl_row center' ><tr><td>내용이 없습니다.</td></tr></table>";
	}
	$("#evnStatus10200").html(evnColumn);
	for (var i = 0; i < evnLen; i++) {
		var evnBody ='';
		var evnVo = data.evnList[i];
		evnBody +='<tr>';
		evnBody +='<th>'+evnVo.oi_code_nm+'</th>';
		evnBody +='<td class="posi-relec" >';
		var imgArr = evnVo.img_url_arr.split('|');
		for (var f = 0; f < imgArr.length; f++) {
			evnBody +='<img src="'+imgArr[f]+'" class="fixing-img" >';
//			evnBody +='<div class="over-view-evn"><img src="'+imgArr[f]+'"></div>';
		} 
		evnBody +='</td>';
		evnBody +='</tr>';
		$("#evn"+evnVo.dfes_oi_code).append(evnBody);
	}
	$("#evnStatus10200").find(".fixing-img").click(function(){
		var src = $(this).attr("src");
		$("#activityFixingPreView210200").show();
		$("#fixingPrevImg").attr("src",src);
		
	});
}
// Odd 생성
function fnMakeFixingOddList(data){
	
	var ourImgTd ='<th rowspan="2" >당사</th>';
	var ourEtcTd ='';
	var comImgTd ='<th rowspan="2" >경쟁사</th>';
	var comEtcTd ='';
	var ourCnt =5;
	var comCnt =5;
	var oddLen =data.oddList.length;
	for (var i = 0; i < oddLen; i++) {
		var oddVo = data.oddList[i];
		if(i == 0){
			$("#oddUpdateDate10200").text("최종 업데이트 : "+oddVo.updt_de);
		}
		if(oddVo.dfop_flag == "1"){
			ourImgTd +='<td><img width="160px" src="'+oddVo.dfop_img_url+'" class="odd-img" ></td>';
			ourEtcTd +='<td class="enpre">'+oddVo.dfop_partclr_matter+'</td>';
			ourCnt --;
		}else if(oddVo.dfop_flag == "2"){
			comImgTd +='<td><img width="160px" src="'+oddVo.dfop_img_url+'" class="odd-img" ></td>';
			comEtcTd +='<td class="enpre">'+oddVo.dfop_partclr_matter+'</td>';
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
		$("#oddUpdateDate10200").html("");
	}
	$("#odd_up_date").html(data.odd_up_date);
	$("#oddOurImgTr10200").html(ourImgTd);
	$("#oddOurEtcTr10200").html(ourEtcTd);
	$("#oddComImgTr10200").html(comImgTd);
	$("#oddComEtcTr10200").html(comEtcTd);
	$("#oddOurImgTr10200").find(".odd-img").click(function(){
		var src = $(this).attr("src");
		$("#activityFixingPreView210200").show();
		$("#fixingPrevImg").attr("src",src);
	});
	$("#oddOurImgTr10200").find(".odd-img").click(function(){
		var src = $(this).attr("src");
		$("#activityFixingPreView210200").show();
		$("#fixingPrevImg").attr("src",src);
	});
}
// Pog그리드 생성
function fnMakeDoubleGrid(data,type){
	var headColFirst = "";
	var headColLast = "";
	
	var columns = new Array(); 	
	var columnArr = data.columnArr;
	headColFirst +='<tr>';
	headColLast +='<tr>';
	var headLen =columnArr.length;
	var tlen = headLen * 2;
	var cols="";
	cols+='<col width="6%" >';
	cols+='<col span="'+tlen+'" width="*" >';
	cols+='<col span="2" width="6%" >';
	cols+='<col width="16%" >';
	$("#pogColgroup").html(cols);
	headColFirst +='	<th rowspan="2" >구분</th>';
	for (var i = 0; i < headLen; i++) {
		var columnNm = columnArr[i].oi_code_nm;
		var columnCd = columnArr[i].oi_code;
		headColFirst +='	<th colspan="2" >'+columnNm+'</th>';
		
		headColLast +='	<th>POG</th>';
		headColLast +='	<th>현재</th>';
		
		columns.push("pog_"+columnCd);
		columns.push("cur_"+columnCd);
	}
	headColFirst +='	<th rowspan="2" >POG<br>진열률</th>';
	headColFirst +='	<th rowspan="2" >실제<br>진열률</th>';
	headColFirst +='	<th rowspan="2" >특이사항</th>';
	headColFirst +='</tr>';
	headColLast +='</tr>';
	if(headLen == 0){
		headColFirst="<tr><td>내용이 없습니다.</td></tr>";
		headColLast="";
		$("#pogUpdateDate10200").html("");
	}
	$("#pogThead10200").html(headColFirst + headColLast);
	
	
	
	if(headLen > 0){
		var bodyRows = ""; 
		var bodyArr = data.bodyArr;
		for (var i = 0; i < bodyArr.length; i++) {
			if(i == 0){
				$("#pogUpdateDate10200").text("최종 업데이트 : "+bodyArr[i].updt_de);
			}
			bodyRows +='<tr>';
			bodyRows +='<td>'+bodyArr[i].oi_code_nm+'</td>';
			
			for (var f = 0; f < columns.length; f++) {
				var column = columns[f];
				bodyRows +='<td>'+bodyArr[i][column]+'</td>';
			}
			
			bodyRows +='<td>'+bodyArr[i].dfap_pog_rate+'</td>';
			bodyRows +='<td>'+bodyArr[i].dfap_cur_rate+'</td>';
			bodyRows +='<td class="enpre">'+bodyArr[i].partclr_matter+'</td>';
			bodyRows +='</tr>';
		}
		$("#pogTbody10200").html(bodyRows);
	}else{
		$("#pogTbody10200").html("");
	}
}
// 그리드 생성
function fnMakeFixingSingleGrid(data,type){
	var headCol = "";
	
	var columns = [] ; 	
	var columnArr = data.columnArr;
	headCol +='<tr>';
	var headLen = columnArr.length;
	headCol +='<th>구분</th>';
	for (var i = 0; i < headLen; i++) {
		var columnNm = columnArr[i].oi_code_nm;
		var columnCd = columnArr[i].oi_code;
		headCol +='	<th>'+columnNm+'</th>';
		columns.push(columnCd);
	}
//	headCol +='<th class="etc-group" >특이사항</th>';
	headCol +='</tr>';
	if(headLen == 0){
		headCol="<tr><td>내용이 없습니다.</td></tr>";
		$("#"+type+"UpdateDate10200").html("");
	}
	$("#"+type+"Thead10200").html(headCol);
	
	var bodyRows = ""; 
	var bodyArr = data.bodyArr;
	if(data.updt_de != ""){
		$("#"+type+"UpdateDate10200").text("최종 업데이트 : "+data.updt_de);
	}
	for (var i = 0; i < bodyArr.length; i++) {
		bodyRows +='<tr>';
		bodyRows +='<td>'+bodyArr[i].oi_code_nm+'</td>';
		
		for (var f = 0; f < columns.length; f++) {
			var column = columns[f];
			bodyRows +='<td>'+bodyArr[i][column]+'</td>';
		}
//		var etc = bodyArr[i].partclr_matter;
//		if(etc){
//			bodyRows +='<td>'+etc+'</td>';
//		}else{
//			$("#"+type+"Thead10200").find(".etc-group").remove();
//		}
		bodyRows +='</tr>';
	}
	if(headLen > 0 ){
		bodyRows +="<tr>";
		bodyRows +="<td>특이사항</td>";
		bodyRows +="<td class='enpre' colspan='"+headLen+"'>"+data.partclr_matter+"</td>";
		bodyRows +="</tr>";
	}
	$("#"+type+"Tbody10200").html(bodyRows);
}
















/**
 * @함수명: fnMakeComboBoxBhf10200
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 콤보박스 생성(지점)
 */
/*
function fnMakeComboBoxBhf10200(cm_code, htmlTagId) {
	$.ajax({
		url : "/organization/byCp/list",
	    data : {"cm_code" : cm_code},
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	var html = "<option value='' selected='selected'>선택</option>";
			if (data.result.length > 0) {
				for (var i = 0; i < data.result.length; i++) {
					var organizationVo = data.result[i];
					html += "<option value='"+organizationVo.om_code+"'>"+organizationVo.om_nm+"</option>";
	        	}
			}
			
			//$("#searchBhf10200").html(html);
			$("#"+htmlTagId).html(html);
			
			if(htmlTagId == "searchBhf10200"){
				$("#searchBhf10200").change(function(){
					fnMakeComboBoxBhf10200($(this).val(), "searchTeam10200");
				});
			}
	    }
	});
}
*/

/**
 * @함수명: cboSelectDepthList_99300
 * @작성일: 2015. 09. 24.
 * @작성자: 
 * @설명: 콤보 Depth 
 * @param 
 */
function cboSelectDepthList_10200(cm_code, om_code, om_orgnzt_se){
	$.ajax({
		url : "/organization/depthList",
	    data:{"cm_code":cm_code, "om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					if(data!=null && data.result.length > 0){
						if(om_orgnzt_se == 1){
							listHtml = "<option value =''>지점</option>";
						}else if(om_orgnzt_se == 2){
							listHtml = "<option value =''>팀명</option>";
						}
						
			    		for(var i=0; i<data.result.length; i++){
			    			var res = data.result[i];     
			    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
			    		}
			    	}else{
			    		
			    	}
	        	}	
			}else{
				if(om_orgnzt_se == 1){
					listHtml = "<option value =''>지점</option>";
				}else if(om_orgnzt_se == 2){
					listHtml = "<option value =''>팀명</option>";
				}
			}	
			
			if(om_orgnzt_se==1){
				$("#searchBhf10200").html(listHtml);
				
				$("#searchBhf10200").change(function(){
					cboSelectDepthList_10200(cm_code, $(this).val(), 2);
				});
			}else if(om_orgnzt_se==2){
				$("#searchTeam10200").html(listHtml);
			}
	    }
	});
}

function fnGetActivityFixingInfo10200(currPg){
	if(!fnActivityFixingValidation10200()){
		return;
	}
	
	fnGetActivityFixingView10200();
	fnGetActivityFixingList10200(currPg);
}

//활동관리 고정MD 총원 조회
function fnGetActivityFixingView10200(){
	var	retireYn = "NO";
	if($("#retireYn10200").is(":checked")){
		retireYn = "YES"
	}
	var params = {
		searchStoreNm : $("#searchStoreNm10200").val()
		, retireYn : retireYn
		, searchEmpNm : $("#searchEmpNm10200").val()
		, searchBhf : $("#searchBhf10200").val()
		, searchTeam : $("#searchTeam10200").val()
		, searchDate : $("#searchDate10200").val().replace(/\-/g,"")
	};
	
	$.ajax({
		url : "/fixing/getActivityFixingView",
		type : "POST",
	    data : params,
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
			if(data.activityFixingVo != null){
				$("#dmDt10200").html($("#searchDate10200").val());
				$("#omNm10200").html($("#searchBhf10200 option:selected").text());
				$("#totCnt10200").html(data.activityFixingVo.dm_tot_cnt+"명");
				$("#dmAttendYCnt10200").html(data.activityFixingVo.dm_attend_y_cnt+"명");
				$("#dmAttendNCnt10200").html(data.activityFixingVo.dm_attend_n_cnt+"명");
				$("#dmLvffcYCnt10200").html(data.activityFixingVo.dm_lvffc_y_cnt+"명");
				$("#dmLvffcNCnt10200").html(data.activityFixingVo.dm_lvffc_n_cnt+"명");
				$("#dmVcatnYCnt10200").html(data.activityFixingVo.dm_vcatn_y_cnt+"명");
			}else{
				$("#dmDt10200").html("");
				$("#omNm10200").html("");
				$("#totCnt10200").html("");
				$("#dmAttendYCnt10200").html("");
				$("#dmAttendNCnt10200").html("");
				$("#dmLvffcYCnt10200").html("");
				$("#dmLvffcNCnt10200").html("");
				$("#dmVcatnYCnt10200").html("");
			}
		}
	});
}

//활동관리 고정MD 조회
//attendLvffcAt ("" : 총원, "AY" : 출근, "AN" : 미출근, "LY" : 퇴근, "LN" : 미퇴근, "VY" : 휴가)
function fnGetActivityFixingList10200(currPg){
	var fnName = "fnGetActivityFixingList10200";//페이징처리 함수명
//	$("#selectBaseDate").val($("#searchDate10200").val());
	var	retireYn = "NO";
	if($("#retireYn10200").is(":checked")){
		retireYn = "YES"
	}
	var params = {
		searchStoreNm : $("#searchStoreNm10200").val()
		, retireYn : retireYn
		, searchEmpNm : $("#searchEmpNm10200").val()
		, searchBhf : $("#searchBhf10200").val()
		, searchTeam : $("#searchTeam10200").val()
		, searchDate : $("#searchDate10200").val().replace(/\-/g,"")
		, searchAttendLvffcAt : currFixingAttendLvffcAt
	};
	currFixingAttendLvffcAt = "";
	var rowSize = 16;//표시할 행수
//	var rowSizePerLine = 8;//화면의 1라인에 표시할 행수
	
	$.ajax({
		url : "/fixing/getActivityFixingList",
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
			if(data.activityFixingList.length > 0){
				for (var i = 0; i < data.activityFixingList.length; i++) {
					var fixVo = data.activityFixingList[i];
					html += '<div class="profilebox">';
					html += '	<div style="max-width:134px; height:142px; position: relative;overflow: hidden;" >';
					if(fixVo.emp_ai_path == ""){
						html += '<img id="profileImage10200'+i+'" src="/resources/images/thum_profile.png"  >';
					}else{
						html += '<img id="profileImage10200'+i+'" src="'+fixVo.emp_ai_path+'" >';						
					}
					html += '	</div>';
					if(fixVo.dm_attend_de == ""){
						html += '	<button class="hotpink lft">미출근</button>';
					}else{
//						html += '<div id="screenshot102001'+i+'" class="popup screenshot10200" style="margin-top:0;">';
//						html += '	<div class="phead txt_left"><span>촬영일시 : '+fixVo.dm_attend_de+'</span><a id="" class="phead-closex" data-type="1" data-idx="'+i+'">닫기</a></div>';
//						html += '	<span style="float:right;padding: 6px 8px 6px 0;font-size: small;">프로필 사진 적용</span>';
//						html += '	<div style="float:right;margin: 8px 10px;" class="inputCheck">';
//						html += '	<input type="checkbox" id="empImage102001'+i+'" />';
//						html += '	<label for="empImage102001'+i+'"></label>';
//						html += '	</div>';
//						html += '	<div><img id="preview102001'+i+'" src="'+fixVo.dm_at_path+'" alt="url preview" /></div>';
//						html += '</div>';
						
						html += '	<button class="skyblue lft img-view" data-type="1" data-at-date="'+fixVo.dm_attend_de+'" data-em_nm="'+fixVo.em_nm+'" data-em_no="'+fixVo.em_no+'" data-am_no="'+fixVo.dm_at_am_no+'" data-ai_path="'+fixVo.dm_at_path+'">';
						html += '		'+fixVo.dm_attend_de;
						html += '	</button>';
						
					}
					if(fixVo.dm_lvffc_de == ""){
						html += '	<button class="hotpink rht">미퇴근</button>';
					}else{
						html += '	<button class="skyblue rht img-view" data-type="2" data-at-date="'+fixVo.dm_lvffc_de+'"  data-em_nm="'+fixVo.em_nm+'"  data-em_no="'+fixVo.em_no+'" data-am_no="'+fixVo.dm_lv_am_no+'" data-ai_path="'+fixVo.dm_lv_path+'">';
						html += '		퇴근';
//						html += '		퇴근'+fixVo.dm_lvffc_de;
						html += '	</button>';
//						html += '<div id="screenshot102002'+i+'" class="popup screenshot10200" style="margin-top:0; ">';
//						html += '	<div class="phead txt_left" style="margin-top:0"><span>촬영일시 : '+fixVo.dm_lvffc_de+'</span><a id="" class="phead-closex" data-type="2" data-idx="'+i+'">닫기</a></div>';
//						html += '	<span style="float:right;padding: 6px 8px 6px 0;font-size: small;">프로필 사진 적용</span>';
//						html += '	<div style="float:right;margin: 8px 10px;" class="inputCheck">';
//						html += '	<input type="checkbox" id="empImage102002'+i+'" />';
//						html += '	<label for="empImage102002'+i+'"></label>';
//						html += '	</div>';
//						html += '	<div ><img id="preview102002'+i+'" src="'+fixVo.dm_lv_path+'" alt="url preview" class="preview" /></div>';
//						html += '</div>';
					}
					html += '	<div class="profile_con">';
					html += '		<ul>';
					html += '			<li class="p_name">'+fixVo.em_nm+'</li>';
					html += '			<li class="p_phone">'+fixVo.em_mbtl_num+'</li>';
					html += '			<li class="p_memo" data-ebsm_nm="'+fixVo.me_nm +' '+fixVo.sm_nm+'" data-em_no="'+fixVo.em_no+'"  data-em_nm="'+fixVo.em_nm+'" >';
					html += '			'+fixVo.me_nm +' > '+fixVo.sm_nm;
					html += '			</li>';
					html += '		</ul>';
					html += '	</div>';
					html += '</div>';
					
				}
			}else{
				html += '<div class="none-data" style=""><i class="fa fa-info-circle"style=""></i><span>내용을 검색해 주세요.</span></div>';
			}
			
			$("#fixingUserListBody").html(html);
			
//			 $("#fixingUserListBody").find("img").load(function() {
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
//		    });
			 
			$("#fixingUserListBody img").error(function() {
		        $(this).attr("src", "/resources/images/thum_profile.png");
		        
		    });
//			$("#fixingUserListBody img").css("width","100%");
			$("#navi10200").html(data.navi);
			
			//업무일지 열기
			$("#fixingUserListBody .p_memo").click(function(){
				var emsm_nm = $(this).data("ebsm_nm");
				$("#fixingEmSmNm10200").text(emsm_nm);
				var prevFirst =  $("#selectBaseDate").val().setBaseDate("first");
				var prevLast = prevFirst.setBaseDate("last");
				$("#diaryDate").html(prevFirst+" ~ "+prevLast);
				$("#emNo10200").val($(this).attr("data-em_no"));
				$("#fixsing_name").text($(this).data("em_nm"));
				if(currBaseDate10200 == prevFirst){
					$("#diaryNext").addClass("last-base-date");
				}else{
					$("#diaryNext").removeClass("last-base-date");
				}
				
				
//				var baseDate =  $("#selectBaseDate").val();
//				var baseLast = baseDate.setBaseDate("last");
//				$("#selectBaseDate").val(baseDate);
//				$("#diaryDate").html(baseDate+" ~ "+baseLast);
				if(currBaseDate10200 == $("#selectBaseDate").val()){
					$("#diaryNext").addClass("last-base-date");
				}else{
					$("#diaryNext").removeClass("last-base-date");
				}
				
				$("#activityFixingPop210200").show();
				var menuArr = ["evn","pog","big","pd","odd","trt","work"];
				for (var i = 0; i < menuArr.length; i++) {
					if($("#"+menuArr[i]+"Status10200").is(":visible")){
						fnGetActivityData(menuArr[i]);
					}
				}
			});
			
			//총원, 출근, 미출근, 퇴근, 미퇴근, 휴가 목록
			$(".attendLvffcAt10200").off().click(function(){
				currFixingAttendLvffcAt = $(this).attr("data-attend_lvffc_at");
				fnGetActivityFixingList10200(1);
			});
			//출근 이미지 출력
			$("#fixingUserListBody .img-view").off().click(function(event){
				var aiPath =$(this).attr("data-ai_path");
				var attend_de =$(this).attr("data-at-date");
				var emNo =$(this).attr("data-em_no");
				var amNo =$(this).attr("data-am_no")
				
				$("#fixingPrevAtendDe").text(attend_de);
				
				$("#fixingPrevAtendCheck").data("ai_path",aiPath);
				$("#fixingPrevAtendCheck").data("em_no",emNo);
				$("#fixingPrevAtendCheck").data("am_no",amNo);
				
    			$("#fixingPrevAtendImg").attr("src",aiPath);
    			
    			$("#activityFixingAtendImgView10300").show();
			});
//			//출근 이미지 출력
//			$("#fixingUserListBody .dmAttentDe").off().click(function(event){
//				fnGetDmAttentLvffcImage10200($(this).data("type"), $(this).data("idx"), $(this).offset().left, $(this).offset().top, $(this).attr("data-em_no"), $(this).attr("data-am_no"), $(this).attr("data-ai_path"));
//			});
//			
//			//퇴근 이미지 출력
//			$("#fixingUserListBody .dmLvffcDe").off().click(function(event){
//				fnGetDmAttentLvffcImage10200($(this).data("type"), $(this).data("idx"), $(this).offset().left, $(this).offset().top, $(this).attr("data-em_no"), $(this).attr("data-am_no"), $(this).attr("data-ai_path"));
//			});
//			
//			//닫기 적용
//			$("#fixingUserListBody .phead-closex").click(function(){
//				$("#screenshot10200"+$(this).data("type")+$(this).data("idx")).hide();
//			});
		}
	});
}


var maxWidth = 300; // Max width for the image
var maxHeight = 190;    // Max height for the image
var ratio = 0;  // Used for aspect ratio

var pageX = 0;
var pageY = 0;

function fnGetDmAttentLvffcImage10200(type, idx, offsetX, offsetY, emNo, amNo, aiPath){
	$("#screenshot10200"+type+idx).show();
		
	var width = $("#preview10200"+type+idx).width();
	var height = $("#preview10200"+type+idx).height();
	pageX = offsetX;
	pageY = offsetY;
	  
	if(width > maxWidth){
	  ratio = maxWidth / width;   // get ratio for scaling image
	  $("#preview10200"+type+idx).css("width", "100%"); // Set new width
	  $("#preview10200"+type+idx).css("max-height", "400px");  // Scale height based on ratio
	  height = height * ratio;    // Reset height to match scaled image
	  width = width * ratio;    // Reset width to match scaled image
	}
	
//	$("#screenshot10200").css("top",(pageY+10)+"px").css("left",(pageX - width)+"px").fadeIn("fast");
//	$("#screenshot10200").css("top",pageY+"px").css("left",pageX+"px").fadeIn("fast");
	//$("#screenshot10200").css("top",pageY-height+"px").css("left",pageX-width+"px").fadeIn("fast");
	
	$("#screenshot10200"+type+idx).css("top",offsetY-25+"px").css("left",offsetX-130+"px").fadeIn("fast");
	
//	$("#empImage10200"+type+idx).off().change(function(){
//		if($(this).is(":checked")){
//			fnSaveActivityFixingEmpAiNo(type, idx, emNo, amNo, aiPath);
//		}
//	});
}

//활동관리 고정MD 프로필 사진 출퇴근시 이미지로 변경.
function fnSaveActivityFixingEmpAiNo(type, idx, emNo, amNo, aiPath){
	var data = {
		em_no : emNo
		, am_no : amNo
	};
	
	$.ajax({
		url : "/fixing/saveActivityFixingEmpAiNo",
		type : "POST",
	    data : data,
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
	    	var result = data.result;
    		if (result != null && result.length != 0) {
    			alert("저장되었습니다.");
    			$("#profileImage10200"+idx).attr("src", aiPath);
    			$("#screenshot10200"+type+idx).hide();
    		} else {
    			alert("저장에 실패하였습니다.");
    		}
	    }
	});
}

function fnActivityFixingValidation10200(){
	if($("#searchBhf10200").val() == "" || $("#searchBhf10200").val() == null){
		alert("지점을 선택하세요.");
		return false;
	}
	
	if($("#searchDate10200").val() == "" || $("#searchDate10200").val() == null){
		alert("날짜를 선택하세요.");
		return false;
	}
	
	return true;
}


















