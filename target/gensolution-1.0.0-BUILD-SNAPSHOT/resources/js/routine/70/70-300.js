
var noticefileList70300 = [];
var businessPg = 1;
var businessAuth = "";
var businessEmCode = "";
var biz_auth_code = "";	
var biz_authCode = "";
var omArray = new Array();
//var om_code = "";
//var sm_code = "";

/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 김종현
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#businessSavePop70300").instancePopUp();
	$("#businessViewPop70300").instancePopUp();
	
	//input 달력
	$("#searchToDate70300").winCal(baseOptions);	
	$("#searchFromDate70300").winCal(baseOptions);
	
	//fnGetJijumOmList("001");
	//fnMakeAutoCompleteBusiness(); //팝업화면 매장명 자동검색
	//fnMakeAutoCompleteEmpl(); //팝업화면 사원명 자동검색
	//fnMakeAutoComplete_cp();  //메인화면 지점명 자동검색	

	// 이벤트 등록
	fnSetEventComponent();	
	
	fnGetNoticeList70300(businessPg);
	
	
	$("#businessFile").file({fileList : noticefileList70300, maxcnt : 5}); 
});




/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	fnComboBox_70300("",1); 			//메인 콤보박스 생성(권한)
	fnGetMsgSearchComboBox_70300("",1); //팝업 콤보박스 생성(권한)
	
	//등록 버튼 눌렀을때
	$("#businessOpen70300").click(function(){
		$("#businessSavePop70300").show();
		$("#businessOmCodeSpan").show();
		$("#businessSmCodeSpan").show();
		$("#businessEmNoSpan").show();
		$("#selectOmItem70300").show();
		$("#selectSmItem70300").show();
		$("#selectEmItem70300").show();
		$("#bizTeamCode").show();
		fnSetNoticePopClean();
		clean70300();
	});

	
	//저장
	$("#businessSave70300").click(function(){
		fnSaveNotice70300();
	});
	$("#businessSearchBtn70300").click(function(){
		fnGetNoticeList70300("1");
	});
	$("#businessSearchResat70300").click(function(){
		$("#jijumOmCode70300").val("");//지점구분
		$("#teamWord70300").val("");   //지점명
		$("#teamCode70300").val("");   //팀명
		$("#noticeKey70300").val(0);   //검색구분
		$("#noticeWord70300").val(""); //검색어
		
		fnGetNoticeList70300("1");
	});
	//닫기
	$("#businessSavePopClose70300, #businessSavePopCloseX70300").click(function(){
		$("#businessSavePop70300").hide();
	});
	//닫기
	$("#businessViewPopClose70300, #businessViewPopCloseX70300").click(function(){
		$("#businessViewPop70300").hide();
	});
	//수정
	$("#businessMod70300").click(function(){
		fnModiBusiness($(this).val());
	});
	//삭제
	$("#businessDel70300").click(function(){
		fnDelBusiness($(this).val());
	});
	//파일
	$('#businessFileInfoList').filedrop({
	    callback : function(fileEncryptedData) {
	    	noticeDragFile(fileEncryptedData);
	    }
	});
	//팝업화면 지점 콤보박스
	$("#businessOmCode").change(function(){
		var code = $(this).val();
		var codeNm = $(this).children("option:selected").text();
		var appendCheck = $("#selectItem > span[data-code='"+code+"']").length;
		if(code != "" && appendCheck == 0){
			var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
			var a = $('<a href="#"/>');
			a.click(function (){
			$(this).parent().remove();
				/*
				var omGroupSpan = $("#selectOmItem70300 span");
				var omArraySpan = new Array();
				for (var i = 0; i < omGroupSpan.length; i++) {
					 var dataSpan = omGroupSpan.eq(i).attr("data-code");
					 if(dataSpan == undefined || dataSpan == "") continue;
					 omArraySpan.push(omGroupSpan.eq(i).attr("data-code"));
				}
				
				if(omArraySpan.length > 0) {
					
					fnMakeAutoComplete70300(2,omArraySpan); //수신매장콤보박스 생성
					fnMakeAutoCompleteEmpl(2,omArraySpan); //수신매장콤보박스 생성
					
				}else if(omArraySpan.length == 0){
					var omGroup     = $("#businessOmCode > option");
					var omArray 	= new Array();
					for (var i = 0; i < omGroup.length; i++) {
						 var data = omGroup.eq(i).attr("data-om_innb");
						 if(data == undefined || data == "") continue;
						 omArray.push(omGroup.eq(i).attr("data-om_innb"));
					}
					
					fnMakeAutoComplete70300(1,omArray);	//수신매장콤보박스 생성
					fnMakeAutoCompleteEmpl(1,omArray); //사원 자동생성
				}
				*/ 

			});
			if(validation(code)){
				span.append(a);
				$("#selectOmItem70300").append(span);	//메인화면 지점
			}
		}
		$(this).val(0);
		
		/*var omGroupSpan = $("#selectOmItem70300 span");
		var omArraySpan = new Array();
		for (var i = 0; i < omGroupSpan.length; i++) {
			 var dataSpan = omGroupSpan.eq(i).attr("data-code");
			 if(dataSpan == undefined || dataSpan == "") continue;
			 omArraySpan.push(omGroupSpan.eq(i).attr("data-code"));
		}
		
		if(omArraySpan.length > 0) {
			fnMakeAutoComplete70300(2,omArraySpan); //수신매장콤보박스 생성
			fnMakeAutoCompleteEmpl(2,omArraySpan); //수신매장콤보박스 생성
		}*/ 
			
	});
	
	//고정/순회 콤보박스
	$("#bizFixRound").change(function(){
		var code = $(this).val();
		$("#bizFixRoundItem").val(code);
		//alert($("#bizFixRoundItem").val());
		fnMakeAutoCompleteEmpl(3,omArray);
		
	});
}

//팝업 삭제버튼
function fnDelBusiness(bmInnb) {
	if(!confirm("삭제하시겠습니까?"))return;
	$.ajax({
		global : true,
		url : "/business/popupDelete",
		//data:{"bmInnb" : bmInnb},
		data:{"bmInnb" : bmInnb, "authCode" : biz_authCode},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {
			if(data == null){
				alert("권한이 없습니다.");
				return;
			}
			if(data != "" ){
				alert("삭제되었습니다.");
				$("#businessViewPop70300").hide();
				fnGetNoticeList70300(1);
				fnSetNoticePopClean();
				return;
			}else{
				alert("삭제오류!!");
			}
		}
	});
}

//중복체크
function validation(code) {

	var omGroup = $("#selectOmItem70300 span");
	
	for (var i = 0; i < omGroup.length; i++) {
		if(code == omGroup.eq(i).attr("data-code")) {
			alert("중복된 수신지점이 존재합니다.");
			return false;
		}
	}
	
	return true;
}


function clean70300() {
	$("#jijumOmCode70300").val("");
	$("#teamWord70300").val("");
	$("#noticeWord70300").val("");
	$("#teamCode70300").val("");
}

//업무지시 수정
function fnModiBusiness(bmInnb){
	$("#businessSaveType70300").val("U");
	$("#businessBmInnb").val(bmInnb);
	$("#businessViewTitle").text("업무지시 수정");
	$.ajax({
		global : true,
		url : "/business/modify",
		data:{"bmInnb" : bmInnb, "authCode" : biz_authCode},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) { 
			if(data == null){
				alert("권한이 없습니다.");
				return;
			}
			var businessVo = data.businessOrderVo;
			
			$("#businessOmCodeSpan").hide();
			$("#businessSmCodeSpan").hide();
			$("#businessEmNoSpan").hide();
			$("#bizTeamCode").hide();
			$("#bizFixRound").attr("disabled","disabled");
			
			$("#businessBmSj").val(businessVo.bm_sj);
			$("#businessBmCn").val(businessVo.bm_cn);
			$("#businessFileAmNo").val(businessVo.am_no);  // 추가되었습니다.
			 
			$("#selectOmItem70300").html("");
			for (var i = 0; i < data.businessOmList.length; i++) {
				var businessOm =  data.businessOmList[i];
				var code = businessOm.key;
				var codeNm = businessOm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				$("#selectOmItem70300").append(span);
			}
			/*$("#selectSmItem70300").html("");
			for (var i = 0; i < data.businessSmList.length; i++) {
				var businessSm = data.businessSmList[i];
				var code = businessSm.key;
				var codeNm = businessSm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				$("#selectSmItem70300").append(span);
			}*/
			$("#selectTeamItem70300").html("");
			for (var i = 0; i < data.businessTmList.length; i++) {
				var businessTm = data.businessTmList[i];
				var code = businessTm.key;
				var codeNm = businessTm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				$("#selectTeamItem70300").append(span);
			}
			$("#bizFixRound").html("");
			for (var i = 0; i < data.bizFixRoundList.length; i++) {
				var businessFix = data.bizFixRoundList[i];
				var span = $('<option>'+businessFix.value+'</option>');
				$("#bizFixRound").append(span);
			}
			$("#selectEmViewItem70300").html("");
			for (var i = 0; i < data.businessEmList.length; i++) {
				var businessEm = data.businessEmList[i];
				var code = businessEm.key;
				var codeNm = businessEm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				$("#selectEmItem70300").append(span);
			}
			$("#businessFileItemList").html("");
			for (var i = 0; i < data.attachVoList.length; i++) {
				var attachVo = data.attachVoList[i];
				var html = '<li class="add-file-li type-image tx-existed" id="biz-file'+attachVo.ai_no+'" >';
				html += '<dl>';
				html += '<dt class="tx-name"><i class="fa ext" data-ext="'+attachVo.ai_nm+'" ></i>';
				var size = attachVo.ai_size.fileSize();
				html += attachVo.ai_nm+' ('+size+')';
				html += '</dt><dd class="tx-button">';
				html += '<a class="tx-delete notice-old-del" data-ai_no="'+attachVo.ai_no+'" >삭제</a>';
				html += '</dd>';
				html += '</dl>';
				html += '</li>';
				$("#businessFileItemList").append(html);
			}
			$(".notice-old-del").click(function(){
				if(confirm("저장없이 바로삭제됩니다.\n삭제하시겠습니까?")){
					fnNoticeOldFileDel($(this).data("ai_no"));
				}
			});
			$(".noti-file-down").click(function(){
				location.href = "/file/down?ai_no="+$(this).data("ai_no");
			});
			
			if(businessVo.insert_Type == "om") {
				$("#selectOmItem70300").show();
				$("#selectSmItem70300").hide();
				$("#selectEmItem70300").hide();
			}else if(businessVo.insert_Type == "sm") {
				$("#selectOmItem70300").hide();
				$("#selectSmItem70300").show();
				$("#selectEmItem70300").hide();
			}else if(businessVo.insert_Type == "em") {
				$("#selectOmItem70300").hide();
				$("#selectSmItem70300").hide();
				$("#selectEmItem70300").show();
			}else {
				$("#selectOmItem70300").show();
				$("#selectSmItem70300").show();
				$("#selectEmItem70300").show();
			} 
			
			$("#businessViewPop70300").hide();
			$("#businessSavePop70300").show();
		}
	});
}

function fnNoticeOldFileDel(ai_no){
	$.ajax({
		url : "/file/delete",
		data : {"ai_no":ai_no},
		type : "POST",
		dataType : "json",
		cache: false,
		success : function(data) {
			if(data != "" ){
				alert("삭제되었습니다.");
				$("#biz-file"+ai_no).remove();
			}else{
				alert("삭제오류!!");
			}
		}
	}); 
}

 
function fnGetNoticeList70300(currPg){
	businessPg = currPg;	// 등록후 현 페이지 리로드
	
	var jijumOmCode = $("#jijumOmCode70300").val();		//지점구분
	var jijumCmCode = $("#businessCmCode70300").val();	//cm_code
	var jijumWord   = "";								//지점명
	var noticeKey   = $("#noticeKey70300").val();		//검색구분
	var noticeWord  = $("#noticeWord70300").val();		//검색어
	var teamName    = $("#teamWord70300").val();        //팀코드
	var teamCode    = $("#teamCode70300").val();        //팀코드
	var team_val    = $("#businessTmCode70300").val();  //팀코드값
	 
	if(teamName == "") {
		teamCode = "";
	}
	 
	var params = {
		"auth_flag"        : "",
		"cm_code"          : jijumCmCode,
		"em_no"            : businessEmCode,
		"jijumOmCode"      : jijumOmCode,
		"jijumWord"        : jijumWord,
		"em_nm"            : "",
		"key" 		       : noticeKey,
		"word" 		       : noticeWord,
		"search_date_from" : "",
		"search_date_to"   : "",
		"type"   		   : "WEB",
		"teamWord"         : teamCode,
		"team_val"         : team_val
	}; 
	var rowSize = 15;					 // 표시할 로우수
	var fnName = "fnGetNoticeList70300"; // 페이징처리 function명
	$.ajax({
		url : "/business/list",
		type : "POST",
		dataType : "json",
		data:{"fnName":fnName
			, "params":params
			, "rowSize":rowSize
			, "currPg":currPg
		},
		cache : false,
		success : function(data) {
			var html = '';
			if (data.businessVoList.length > 0) {
				for (var i = 0; i < data.businessVoList.length; i++) {
					var businessVo = data.businessVoList[i];
					html+='<tr>';
					html+='<td>' + (parseInt(data.firstNo)-i) + '</td>';
					html+='<td class="txt_left">';
					html+='	<a class="notice-view" data-bm_innb="'+businessVo.bm_innb+'" data-em_no="'+businessVo.em_no+'" data-dty_code="'+businessVo.em_dty_code +'" href="#">'+businessVo.bm_sj+'</a>';
					html+='</td>';
					html+='<td>'+businessVo.receive_yn+'/'+businessVo.total_cnt+'</td>';
					html+='<td>'+businessVo.regist_man+'</td>';
					html+='<td>'+businessVo.regist_de+'</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="5">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#noticeTbody70300").html(html);
			$("#noticeTbody70300").find(".notice-view").click(function(){
				fnSetNoticePopClean();
				fnViewBusiness($(this).data("bm_innb"), $(this).attr("data-dty_code"));
			});
			
			$("#noticeNavi70300").html(data.navi);
		}
	});
}

//상세보기 답변리스트
function fnViewPopupTbody(businessPg){
	var bmInnb = $("#businessBmInnb").val();
	var currPg  = businessPg;
	var fnName  = "fnViewPopupTbody"; 	// 페이징처리 function명
	var rowSize = 5;					// 표시할 로우수
	var params = {
			"bmInnb"  :  bmInnb
		}; 
	$.ajax({
		url : "/business/popupList",
		type : "POST",
		dataType : "json",
		data:{"fnName":fnName
			, "params":params
			, "rowSize":rowSize
			, "currPg":currPg
		},
		cache : false,
		success : function(data) {
			var html = '';
			if (data.businessVoPopupList.length > 0) {
				for (var i = 0; i < data.businessVoPopupList.length; i++) {
					var businessVoPopup = data.businessVoPopupList[i];
					html+='<tr>';
					html+='<td>'+(parseInt(data.firstNo)-i)+'</td>';
					html+='<td>'+businessVoPopup.em_nm+'</td>';
					html+='<td>'+businessVoPopup.br_answer+'</td>';
					html+='<td>'+businessVoPopup.br_receive_date+'</td>';
					
					if(businessVoPopup.em_push_id == null || businessVoPopup.em_push_id == ""){
						html+='<td>';
						html+='	<button type="button" class="disabled" >재전송';
						html+='</td>';
					}else{
						if(businessVoPopup.br_receive_date == null || businessVoPopup.br_receive_date == "") {
							html+='<td>';
							html+='	<button type="button" class="notice-view"  data-em_no="'+businessVoPopup.em_no+'"  data-bm_innb="'+businessVoPopup.bm_innb+'" >재전송';
							html+='</td>';
						}else{
							html+='<td>';
							//html+='	<button type="button" class="notice-view"  data-em_no="'+businessVoPopup.em_no+'"  data-bm_innb="'+businessVoPopup.bm_innb+'" >재전송';
							html+='</td>';
						}
					}
				}
			} else {
				html += '<tr><td colspan="4">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#noticePopupTbody70300").html(html);
			
			$("#noticePopupTbody70300").find(".notice-view").click(function(){
				fnResend($(this).attr("data-bm_innb"), $(this).attr("data-em_no"));
			});
			
			$("#popNavi70300").html(data.navi);
			//$("#popNavi70300").html("=====");
		}
	});
}

//상세보기 재전송 버튼
function fnResend(bm_innb, em_no) {
	$.ajax({
		url : "/business/push",
		data : {"bm_innb":bm_innb,
			    "em_no"  :em_no 
	    },
		type : "POST",
		dataType : "json",
		cache: false,
		success : function(data) {
			if(data != "" ){
				alert("푸시성공.");
			}else{
				alert("푸시오류!!");
			}
		}
	}); 
}

//상세보기
function fnViewBusiness(bmInnb, dty_code){
	biz_authCode = dty_code;
	
	$.ajax({
		url : "/business/view",
		data:{"bmInnb" : bmInnb},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) { 
			$("#businessBmInnb").val(bmInnb);
			
			var businessVo = data.businessOrderVo;
			 
			//alert(businessVo.insert_Type);
			 
			$("#businessViewBmSj").text(businessVo.bm_sj);
			$("#businessViewBmCn").text(businessVo.bm_cn);
			$("#businessMod70300").val(businessVo.bm_innb);
			$("#businessDel70300").val(businessVo.bm_innb);
			$("#businessViewTitle").text("업무지시 보기");
			$("#bizFixRoundViewItem70300").attr("disabled","disabled");
			
			
			$("#selectOmViewItem70300").html("");
			for (var i = 0; i < data.businessOmList.length; i++) {
				var businessOm = data.businessOmList[i];
				var span = $('<span>'+businessOm.value+'</span>');
				$("#selectOmViewItem70300").append(span);
			}
			/*$("#selectSmViewItem70300").html("");
			for (var i = 0; i < data.businessSmList.length; i++) {
				var businessSm = data.businessSmList[i];
				var span = $('<span>'+businessSm.value+'</span>');
				$("#selectSmViewItem70300").append(span);
			}*/
			$("#selectTmViewItem70300").html("");
			for (var i = 0; i < data.businessTmList.length; i++) {
				var businessTm = data.businessTmList[i];
				var span = $('<span>'+businessTm.value+'</span>');
				$("#selectTmViewItem70300").append(span);
			}
			$("#bizFixRoundViewItem70300").html("");
			for (var i = 0; i < data.bizFixRoundList.length; i++) {
				var businessFix = data.bizFixRoundList[i];
				var span = $('<option>'+businessFix.value+'</option>');
				$("#bizFixRoundViewItem70300").append(span);
			}
			$("#selectEmViewItem70300").html("");
			for (var i = 0; i < data.businessEmList.length; i++) {
				var businessEm = data.businessEmList[i];
				var span = $('<span>'+businessEm.value+'</span>');
				$("#selectEmViewItem70300").append(span);
			}
			
			$("#businessFileViewList").html("");
			for (var i = 0; i < data.attachVoList.length; i++) {
				var attachVo = data.attachVoList[i];
				var html = '<li class="add-file-li type-image tx-existed" id="old-file'+attachVo.ai_no+'" >';
				html += '<dl>';
				html += '<dt class="tx-name noti-file-down" data-ai_no="'+attachVo.ai_no+'"  ><i class="fa ext" data-ext="'+attachVo.ai_nm+'" ></i>';
				var size = attachVo.ai_size.fileSize();
				html += attachVo.ai_nm+' ('+size+')';
				html += '</dt><dd class="tx-button">';
				html += '</dd>';
				html += '</dl>';
				html += '</li>';
				$("#businessFileViewList").append(html);
			}
			$(".noti-file-down").click(function(){
				location.href = "/file/down?ai_no="+$(this).data("ai_no");
			});
			
			fnViewPopupTbody(1); //팝업 답변 목록 출력
			
			if(businessVo.insert_Type == "om") {
				$("#selectOmViewItem70300").show();
				$("#selectSmViewItem70300").hide();
				$("#selectEmViewItem70300").hide();
			}else if(businessVo.insert_Type == "sm") {
				$("#selectOmViewItem70300").hide();
				$("#selectSmViewItem70300").show();
				$("#selectEmViewItem70300").hide();
			}else if(businessVo.insert_Type == "em") {
				$("#selectOmViewItem70300").hide();
				$("#selectSmViewItem70300").hide();
				$("#selectEmViewItem70300").show();
			}else {
				$("#selectOmViewItem70300").show();
				$("#selectSmViewItem70300").show();
				$("#selectEmViewItem70300").show();
			} 
			
			$("#businessViewPop70300").show();
		}
	});
}

//저장
function fnSaveNotice70300(){
	var bmSj = $.trim($("#businessBmSj").val());
	if(bmSj == ""){
		alert("제목을 입력해주세요.");
		$("#businessBmSj").focus();
		return;
	}
	var bmCn = $.trim($("#businessBmCn").val());
	if(bmCn == ""){
		alert("내용을 입력해주세요.");
		$("#businessBmCn").focus();
		return;
	}
	var omGroup = $("#selectOmItem70300 span");
	//var smGroup = $("#selectSmItem70300 span");
	var smGroup = $("#selectTeamItem70300 span");
	var emGroup = $("#selectEmItem70300 span");
	
	var omArr = new Array();
	var smArr = new Array();
	var emArr = new Array();
	
	var insert_Type = "";
	
	for (var i = 0; i < omGroup.length; i++) {
		omArr.push({
			"om_code":omGroup.eq(i).attr("data-code")
		});
	}	
		
	/*for (var i = 0; i < smGroup.length; i++) {
		smArr.push({
			"sm_code":smGroup.eq(i).attr("data-code")
		   ,"om_code":smGroup.eq(i).attr("data-om-code")
		});
	} */	
	for (var i = 0; i < smGroup.length; i++) {
		smArr.push({
			"tm_code":smGroup.eq(i).attr("data-code")
		   ,"om_code":smGroup.eq(i).attr("data-om-code")
		});
	} 
	
	for (var i = 0; i < emGroup.length; i++) {
		emArr.push({
			 "em_no"  :emGroup.eq(i).attr("data-code")
		    ,"om_code":emGroup.eq(i).attr("data-om-code")
			//,"sm_code":emGroup.eq(i).attr("data-sm-code")
		});
	}
 
	//if((omGroup.length == 0)) {
	if((omGroup.length == 0) && (smGroup.length == 0) && (emGroup.length == 0)) {
		var omGroupN   = $("#jijumOmCode70300 > option");
		for (var i = 0; i < omGroupN.length; i++) {
			 var data = omGroupN.eq(i).attr("data-om_innb");
			 if(data == undefined || data == "") continue;
			 omArr.push({
				 "om_code":omGroupN.eq(i).attr("data-om_innb") 
			 });
		}
	}
	
	/*if((omGroup.length == 0) && (smGroup.length == 0) && (emGroup.length == 0)) {
		//alert("지점이나 매장 또는 사원을 입력하세요");
		if(!confirm("전체에게 알리시겠습니까?")) return;
	}*/
	if(businessAuth == 1) {
		if((omGroup.length == 0) && (smGroup.length == 0) && (emGroup.length == 0)) {
			if(!confirm("전체에게 알리시겠습니까?")) return;
		}
	}else {
		if((omGroup.length == 0) && (smGroup.length == 0) && (emGroup.length == 0)) {
			alert("지점이나 팀 또는 사원을 입력하세요");
			return;
		}
	}
	
	var bmInnb = "";	
	var saveType =  $("#businessSaveType70300").val();
	var fixRnd = $("#bizFixRound").val(); //0000000007 순회, 0000000006 고정
	
	if(saveType =="U"){
		bmInnb = $("#businessBmInnb").val();
	}
	 
	if(omArr.length > 0 && smArr.length == 0 && emArr.length == 0) {
		insert_Type = 'om';
	}else if(omArr.length == 0 && smArr.length > 0 && emArr.length == 0) {
		insert_Type = 'sm';
	}else if(omArr.length == 0 && smArr.length == 0 && emArr.length > 0) {
		insert_Type = 'em';
	}else {
		insert_Type = 'etc';
	}  
	 
	var saveData = {
			 saveType : saveType
			,insert_Type : insert_Type
			,bm_innb  : bmInnb
			,bm_sj    : bmSj
			,bm_cn    : bmCn
			,fixRnd   : fixRnd
			,omArr    : omArr
			,smArr    : smArr
			,emArr    : emArr
	}
	
	// 수정
	var url = ""; 
	if(saveType == "I") {
		url = "/business/save1";
	}else if(saveType == "U") {
		url = "/business/save2";
	}
	
	$.ajaxSettings.traditional = false;
	$.ajax({
		global: true,	
		url : url,
		data:saveData,
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(bmInnb) {
			if(bmInnb != ""){
				fnSaveNoticeFile70300(bmInnb);
				var goPg = 1;
				if(saveType == "U"){
					goPg = businessPg;
				}
				fnGetNoticeList70300(goPg);
			}else{
				alert("저장실패.");
			}
		}
	}); 
	
}

//파일 저장
function fnSaveNoticeFile70300(bmInnb){
	var amNo = $("#businessFileAmNo").val();
	var formData = new FormData();
	if(noticefileList70300.length > 0) {
		for (var i = 0; i < noticefileList70300.length; i++) {
			formData.append('files', noticefileList70300[i]);
		}
	}else{
		alert("정상적으로 등록되었습니다.");
		$("#businessSavePop70300").hide();
		fnSetNoticePopClean();
		return;
	}
	
	formData.append('bmInnb', bmInnb);
	formData.append('amNo', amNo);
	 
	$.ajax({
		url : "/businessfile/save",
		data : formData,
		type : "POST",
		dataType : "json",
		async : false,
		global: true,		
		cache: false,
        contentType: false,
        processData: false,
        beforeSend :function(){
			  fnSetAjaxFileLoding();
		},
		success : function(data) {
			if(data > 0 ){
				alert("정상적으로 등록되었습니다.");
				$("#businessSavePop70300").hide();
				fnSetNoticePopClean();
				noticefileList70300 = []; //추가되었습니다.
				fileId = 1;			 //추가되었습니다.
				$("#businessFile").file({fileList : noticefileList70300});  //추가되었습니다.
			}else{
				alert("파일저장실패.");
			}
			
		}
	}); 
}

function fnSetNoticePopClean(){
	$("#businessFileItemList").empty();
	$("#businessFileViewList").empty();
	$("#selectSmItem70300").empty();
	$("#selectOmItem70300").empty();
	$("#selectEmItem70300").empty();
	$("#selectTeamItem70300").empty();
	$("#selectSmViewItem7030").empty();
	$("#selectOmViewItem7030").empty();
	$("#selectEmViewItem7030").empty();
	$("#businessBmSj").val("");
	$("#businessBmCn").val("");
	$("#businessViewBmSj").val("");
	$("#businessViewBmCn").val("");
	$("#businessSmCode").val("");
	$("#bizTeamCode").val("");
	$("#teamCode70300").val("");
	$("#businessEmNo70300").val("");
	$("#businessFileAmNo").val("");
	$("#businessSaveType70300").val("I");
	
	$("#bizFixRound").removeAttr('disabled');
}

function fnGetOmCodes(){
	
	var omGroup = $("#selectOmItem70300 span");
	
	var prams ="";
	for (var i = 0; i < omGroup.length; i++) {
		if(i > 0) prams += ",";
		prams += "'"+omGroup.eq(i).attr("data-code")+"'";
	}	
	return prams;	
}

//메인 선택박스 생성
function fnComboBox_70300(om_code ,om_orgnzt_se){
	$.ajax({
		url : "/notice/auth",
	    data:{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			//var listHtml = "";
	    	var listHtml = "<option value='' selected='selected'>전체</option>";
	    	if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];   
	    			if(res.om_nm == '지점') continue;
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
	    		}
			}	
 
	    	$("#businessEmCode70300").val(data.em_code);
	    	$("#businessCmCode70300").val(data.cm_code);
	    	$("#businessAuthType70300").val(parseInt(data.auth_flag));
	    	$("#jijumOmCode70300").html(listHtml);
	    	
	    	businessEmCode = $("#businessEmCode70300").val();
	    	businessAuth = $("#businessAuthType70300").val();
	    	if(businessAuth > 2) {
	    		$("#teamWord70300").attr("disabled", "disabled");
	    	}
	    }
	});
	
	var omGroup   = $("#jijumOmCode70300 > option");
	var omArray = new Array();
	for (var i = 0; i < omGroup.length; i++) {
		 var data = omGroup.eq(i).attr("data-om_innb");
		 if(data == undefined || data == "") continue;
		 omArray.push(omGroup.eq(i).attr("data-om_innb"));
	}
	 
	fnMakeAutoComplete_cp(omArray);	//수신매장콤보박스 생성
}

function validationMaket(code) {
	var smGroup = $("#selectSmItem70300 span");
	
	for (var i = 0; i < smGroup.length; i++) {
		if(code == smGroup.eq(i).attr("data-code")) {
			alert("중복된 수신매장이 존재합니다.");
			return false;
		}
	}
	
	return true;
}

//메인화면 팀명 자동완성
function fnMakeAutoComplete_cp(omArray){
	om_code = String(omArray);
	
    $("#teamWord70300").autocomplete({
    	//serviceUrl:'/business/martList_cp',
    	serviceUrl:'/70/martList',
        minChars:1,
        paramName: 'keyword',
        zIndex: 9999,
        params:{
        	om_code : om_code
        },
        noCache: true, 
        onSelect: function(result){ 
        	var code = result.key;
        	var codeNm = result.value;
        	var omCode = result.om_code;
	   		var appendCheck = $("#mart > span[data-code='"+code+"']").length;
	   		if(code != "" && appendCheck == 0){
	   			$("#teamCode70300").val(code);
	   			var span = $('<span data-code="'+code+'" data-om-code="'+omCode+'">'+codeNm+'</span>');
	   			var a = $('<a href="#" />');
	   			a.click(function (){
	   				$(this).parent().remove();
	   			});
	   			span.append(a);
	   			$("#teamWord70300").append(span);
	   		}
        }
    });
} 
 

//팝업 지점 선택박스 생성
function fnGetMsgSearchComboBox_70300(om_code ,om_orgnzt_se){
	$.ajax({
		url : "/notice/auth",
	    data:{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			//var listHtml = "";
	    	var listHtml = "<option value='' selected='selected'>선택</option>";
	    	if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];   
	    			if(res.om_nm == '지점') continue;
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
	    		}
			}	
	    	
	    	$("#businessCmCode70300").val(data.cm_code);
	    	$("#businessTmCode70300").val(data.tm_code);
	    	//$("#businessAuthType70300").val(parseInt(data.auth_flag));
	    	$("#businessOmCode").html(listHtml);
	    }
	});
	
	var omGroup   = $("#businessOmCode > option");
	for (var i = 0; i < omGroup.length; i++) {
		 var data = omGroup.eq(i).attr("data-om_innb");
		 if(data == undefined || data == "") continue;
		 omArray.push(omGroup.eq(i).attr("data-om_innb"));
	}
	//fnMakeAutoComplete70300(1,omArray);	//수신매장콤보박스 생성
	fnMakeAutoComplete70300(omArray);	//수신매장콤보박스 생성
	fnMakeAutoCompleteEmpl(3,omArray); //사원 자동생성
}

//팝업 매장 자동생성
/*function fnMakeAutoComplete70300(val,omArray){
	//var auth_flag = $("#businessAuthType70300").val(); // 1:관리자, 2:팀장
	var cm_code = $("#businessCmCode70300").val(); 	   // cm_code
	var om_code = "";
	
	if(val == "1") {
		om_code = String(omArray);	
	}
	else if(val == "2"){
		om_code = String(omArray);
	}
	 
	$("#businessSmCode").autocomplete({
    	serviceUrl:'/business/martList',
        minChars:1,
        paramName: 'keyword',
        zIndex: 9999,
        params:{
        	om_code : om_code,
         // auth_flag : auth_flag,
            cm_code : cm_code
        },
        noCache: true, 
        onSelect: function(result){ 
        	var code = result.key;
        	var codeNm = result.value;
        	var omCode = result.om_code;
	   		var appendCheck = $("#mart > span[data-code='"+code+"']").length;
	   		 
	   		if(code != "" && appendCheck == 0){
	   			var span = $('<span data-code="'+code+'" data-om-code="'+omCode+'" >'+codeNm+'</span>');
	   			var a = $('<a href="#" />');
	   			a.click(function (){
	   				$(this).parent().remove();
	   				var smGroupSpan = $("#selectSmItem70300 span");
	   				//var omArray = new Array();
	   				var smArray = new Array();
					for (var i = 0; i < smGroupSpan.length; i++) {
						 var dataSpan = smGroupSpan.eq(i).attr("data-code");
						 if(dataSpan == undefined || dataSpan == "") continue;
						 smArray.push(smGroupSpan.eq(i).attr("data-code"));
					}
					//fnMakeAutoCompleteEmpl(3,"",smArray); //수신매장콤보박스 생성
					
	   			});
	   			if(validationMaket(code)){
					span.append(a);
					$("#selectSmItem70300").append(span);
					
					var smGroupSpan = $("#selectSmItem70300 span");
					var smArray = new Array();
					for (var i = 0; i < smGroupSpan.length; i++) {
						 var dataSpan = smGroupSpan.eq(i).attr("data-code");
						 if(dataSpan == undefined || dataSpan == "") continue;
						 smArray.push(smGroupSpan.eq(i).attr("data-code"));
					}
					//fnMakeAutoCompleteEmpl(3,"",smArray); //수신매장콤보박스 생성
				}
	   		}
	   		$(this).val("");
        }
    });
}*/

//팀명 자동생성
function fnMakeAutoComplete70300(omArray){
	var om_code = String(omArray);	
	 
	$("#bizTeamCode").autocomplete({
    	//serviceUrl:'/business/martList',
		serviceUrl:'/70/martList',
        minChars:1,
        paramName: 'keyword',
        zIndex: 9999,
        params:{
        	om_code : om_code
        },
        noCache: true, 
        onSelect: function(result){ 
        	var code = result.key;
        	var codeNm = result.value;
        	var omCode = result.om_code;
	   		var appendCheck = $("#mart > span[data-code='"+code+"']").length;
	   		 
	   		if(code != "" && appendCheck == 0){
	   			var span = $('<span data-code="'+code+'" data-om-code="'+omCode+'" >'+codeNm+'</span>');
	   			var a = $('<a href="#" />');	   			
	   			a.click(function (){
	   				$(this).parent().remove();	   								
	   			});	   			
	   			if(validationTeam(code)){
					span.append(a);
					$("#selectTeamItem70300").append(span);					
				}
	   		}
	   		$(this).val("");
        }
    });
}

function validationTeam(code) {
	var smGroup = $("#selectTeamItem70300 span");
	
	for (var i = 0; i < smGroup.length; i++) {
		if(code == smGroup.eq(i).attr("data-code")) {
			alert("중복된 팀이 존재합니다.");
			return false;
		}
	}
	return true;
}

//팝업화면 사원명 자동완성
function fnMakeAutoCompleteEmpl(val,omArray){
	var cm_code = $("#businessCmCode70300").val(); //cm_code
	var fix_gbn = $("#bizFixRoundItem").val();  
	var om_code = "";
	 
	if(val == "3") {
		om_code = String(omArray);	
	} 
     
    $("#businessEmNo70300").autocomplete({
    	serviceUrl:'/business/empList',
        minChars:1,
        paramName: 'keyword',
        zIndex: 9999,
        params:{
          om_code   : om_code,
          cm_code   : cm_code,
          fix_gbn   : fix_gbn
         },
        noCache: true, 
        onSelect: function(result){ 
        	var code = result.key;
        	var codeNm = result.value;
        	var omCode = result.om_code;
        	var smCode = result.sm_code;
	   		var appendCheck = $("#mart > span[data-code='"+code+"']").length;
	   		if(code != "" && appendCheck == 0){
	   			var span = $('<span data-code="'+code+'" data-om-code="' + omCode + '" data-sm-code="' + smCode + '" >'+codeNm+'</span>');
	   			var a = $('<a href="#" />');
	   			a.click(function (){
	   				$(this).parent().remove();
	   			});
	   			if(validationEmpl(code)){
					span.append(a);
					$("#selectEmItem70300").append(span);
				}
	   		}
	   		$(this).val("");
        }
    });
}

function validationEmpl(code) {
	var emGroup = $("#selectEmItem70300 span");
	
	for (var i = 0; i < emGroup.length; i++) {
		if(code == emGroup.eq(i).attr("data-code")) {
			alert("중복된 사원명이 존재합니다.");
			return false;
		}
	}
	
	return true;
}