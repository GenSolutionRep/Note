                     
var noticefileList = [];
var noticePg = 1;
var noticeAuth = "";
var auth_code = "";		
var noticeEmCode = "";
var authCode = "";
var omArray = new Array();
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#noticeSavePop70100").instancePopUp();
	$("#noticeViewPop70100").instancePopUp();
	
	//input 달력
	$("#searchToDate70100").winCal(baseOptions);	
	$("#searchFromDate70100").winCal(baseOptions);
	 
	//fnMakeAutoComplete_cp();

	// 이벤트 등록
	fnSetEventComponent();	
	
	fnGetNoticeList(noticePg);
	
	$("#noticeFile").file({fileList : noticefileList, maxcnt : 5});
});

/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	fnComboBox_70100("",1);
	fnGetMsgSearchComboBox_70100("",1); 
	
	//등록 버튼 눌렀을때
	$("#noticeOpen70100").click(function(){
		$("#noticeSavePop70100").show();
		$("#noticeSmCode").val('');
		$("#noticeTeamCode").val('');
		$("#noticeBmSj").val('');
		$("#noticeBmCn").val('');
		$("#noticeFileAmNo").val('');
		$("#noticeSavePopClose70100").show();
		$("#noticepCloseRelod70100").hide();
		fnSetNoticePopClean();
		clean70103();
	});
	
	//저장
	$("#noticeSave").click(function(){
		fnSaveNotice();
	});
	$("#noticeSearchBtn").click(function(){
		fnGetNoticeList("1");
	});
	$("#noticeSearchResat").click(function(){
		$("#jijumOmCode70100").val("");//지점구분
		$("#teamWord70100").val("");  //지점명
		$("#noticeKey70100").val(0);	  //검색구분
		$("#noticeWord70100").val(""); //검색어
		
		fnGetNoticeList("1");
	});
	//닫기
	$("#noticeSavePopClose70100, #noticeSavePopCloseX70100").click(function(){
		$("#noticeSavePop70100").hide();
	});
	//닫기
	$("#noticeViewPopClose70100, #noticeViewPopCloseX70100").click(function(){
		$("#noticeViewPop70100").hide();
	});
	//편집상태에서 그냥 닫을때 파일삭제된거  저장
	$("#noticepCloseRelod70100").click(function(){
		$("#noticeSavePop70100").hide();
		fnSetNoticePopClean();
		fnGetNoticeList("1");
	});
	//수정
	$("#noticeMod70100").click(function(){
		$("#noticeSavePopClose70100").hide();
		$("#noticepCloseRelod70100").show();
		fnModiNotice($(this).val());
	});
	//삭제
	$("#noticeDel70100").click(function(){
		fnDelNotice($(this).val());
	});
	//파일
	$('#noticeFileInfoList').filedrop({
	    callback : function(fileEncryptedData) {
	    	noticeDragFile(fileEncryptedData);
	    }
	});
	
	//지점 콤보박스
	$("#noticeOmCode").change(function(){
		var code = $(this).val();
		var codeNm = $(this).children("option:selected").text();
		var appendCheck = $("#selectItem > span[data-code='"+code+"']").length;
		if(code != "" && appendCheck == 0){
			var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
			var a = $('<a href="#"/>');
			a.click(function (){
				$(this).parent().remove();
			});
			
			if(validation(code)){
				span.append(a);
				$("#selectOmItem").append(span);
			}
		}
		$(this).val(0);
	});
	
	//고정/순회 콤보박스
	$("#noticeFixRound").change(function(){
		var code = $(this).val();
		$("#noticeFixRoundItem").val(code);
		//alert(fixOrRound);
		fnMakeAutoCompleteEmpl70100(3,omArray);
		
	});
}

//중복체크
function validation(code) {

	var omGroup = $("#selectOmItem span");
	
	for (var i = 0; i < omGroup.length; i++) {
		if(code == omGroup.eq(i).attr("data-code")) {
			alert("중복된 수신지점이 존재합니다.");
			return false;
		}
	}
	
	return true;
}

//공지사항 수정
function fnModiNotice(bmInnb){
	$("#noticeSaveType70100").val("U");
	$("#noticeBmInnb").val(bmInnb);
	$("#noticeTitle").text("공지사항 수정");
	 
	$.ajax({
		global : true,
		url : "/notice/modify",
		data:{"bmInnb" : bmInnb, "authCode" : authCode},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) { 
			if(data == null){
				alert("권한이 없습니다.");
				return;
			}
			var noticeVo = data.noticeVo;
			
			$("#noticeBmSj").val(noticeVo.bm_sj);
			$("#noticeBmCn").val(noticeVo.bm_cn);
			$("#noticeFileAmNo").val(noticeVo.am_no);  // 추가되었습니다.
			         
			fnGetMsgSearchComboBox_70100("",1); 
			
			$("#selectOmItem").html("");
			for (var i = 0; i < data.noticeOmList.length; i++) {
				var noticeOm = data.noticeOmList[i];
				var code = noticeOm.key;
				var codeNm = noticeOm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				var a = $('<a href="#"/>');
				a.click(function (){
					$(this).parent().remove();
				});
				span.append(a);
				$("#selectOmItem").append(span);
			}
			/*$("#selectSmItem").html("");
			for (var i = 0; i < data.noticeSmList.length; i++) {
				var noticeSm = data.noticeSmList[i];
				var code = noticeSm.key;
				var codeNm = noticeSm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				var a = $('<a href="#"/>');
				a.click(function (){
					$(this).parent().remove();
				});
				span.append(a);
				$("#selectSmItem").append(span);
			}*/
			$("#selectTeamItem").html("");
			for (var i = 0; i < data.noticeTmList.length; i++) {
				var noticeSm = data.noticeTmList[i];
				var code = noticeSm.key;
				var codeNm = noticeSm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				var a = $('<a href="#"/>');
				a.click(function (){
					$(this).parent().remove();
				});
				span.append(a);
				$("#selectTeamItem").append(span);
			}
			$("#selectEmItem").html("");
			for (var i = 0; i < data.noticeEmList.length; i++) {
				var noticeEm = data.noticeEmList[i];
				var code = noticeEm.key;
				var codeNm = noticeEm.value;
				var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
				var a = $('<a href="#"/>');
				a.click(function (){
					$(this).parent().remove();
				});
				span.append(a);
				$("#selectEmItem").append(span);
			}
			$("#noticeFileItemList").html("");
			for (var i = 0; i < data.attachVoList.length; i++) {
				var attachVo = data.attachVoList[i];
				var html = '<li class="add-file-li type-image tx-existed" id="old-file'+attachVo.ai_no+'" >';
				html += '<dl>';
				html += '<dt class="tx-name"><i class="fa ext" data-ext="'+attachVo.ai_nm+'" ></i>';
				var size = attachVo.ai_size.fileSize();
				html += attachVo.ai_nm+' ('+size+')';
				html += '</dt><dd class="tx-button">';
				html += '<a class="tx-delete notice-old-del" data-ai_no="'+attachVo.ai_no+'" >삭제</a>';
				html += '</dd>';
				html += '</dl>';
				html += '</li>';
				$("#noticeFileItemList").append(html);
			}
			$(".notice-old-del").click(function(){
				if(confirm("저장없이 바로삭제됩니다.\n삭제하시겠습니까?")){
					fnNoticeOldFileDel($(this).data("ai_no"));
				}
			});
			$(".noti-file-down").click(function(){
				location.href = "/file/down?ai_no="+$(this).data("ai_no");
			});
			
			
			$("#noticeViewPop70100").hide();
			$("#noticeSavePop70100").show();
		}
	});
}

//팝업 삭제버튼
function fnDelNotice(bmInnb) {
	if(!confirm("삭제하시겠습니까?"))return;
	
	$.ajax({
		global : true,
		url : "/notice/popupDelete",
		//data:{"bmInnb" : bmInnb},
		data:{"bmInnb" : bmInnb, "authCode" : authCode},
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
				$("#noticeViewPop70100").hide();
				fnGetNoticeList("1");
				fnSetNoticePopClean();
				return;
			}else{
				alert("삭제오류!!");
			}
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
				$("#old-file"+ai_no).remove();
			}else{
				alert("삭제오류!!");
			}
		}
	}); 
}

function fnGetNoticeList(currPg){
	noticePg = currPg;	// 등록후 현 페이지 리로드
	
	var jijumOmCode  = $("#jijumOmCode70100").val();	//지점구분
	var jijumWord    = "";								//지점명
	var noticeKey    = $("#noticeKey70100").val();		//검색구분
	var noticeWord   = $("#noticeWord70100").val();		//검색어
	var jijumCmCode  = $("#noticeCmCode70100").val();	//cm_code
	var teamName     = $("#teamWord70100").val();       //팀코드
	var teamCode     = $("#teamCode70100").val();       //팀코드
	var team_val     = $("#noticeTmCode70100").val();   //팀코드값
	var flag_val 	 = "";
	
	if(teamName == "") {
		teamCode = "";
	}
	
	/*if(noticeAuth =="4") {	//순회
		flag_val = "0000000007" ;	
	}else if(noticeAuth =="3") {	//고정
		flag_val = "0000000006" ;	
	}*/
	
	var params = {
		"auth_flag"        : noticeAuth,
		"cm_code"          : jijumCmCode,
		"em_no"            : noticeEmCode,
		"jijumOmCode"      : jijumOmCode,
		"jijumWord"        : jijumWord,
		"key" 		       : noticeKey,
		"word" 		       : noticeWord,
		"search_date_from" : "",
		"search_date_to"   : "",
		//"flag_val"         : flag_val,  //삭제예정
		"teamWord"         : teamCode,
		"team_val"         : team_val
	}; 
	var rowSize = 15;				// 표시할 로우수
	var fnName = "fnGetNoticeList";//페이징처리 function명
	$.ajax({
		url : "/notice/list",
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
			if (data.noticeVoList.length > 0) {
				for (var i = 0; i < data.noticeVoList.length; i++) {
					var noticeVo = data.noticeVoList[i];
					html+='<tr>';
					html+='<td>' + (parseInt(data.firstNo)-i) + '</td>';
					html+='<td class="txt_left">';
					html+='	<a class="notice-view" data-bm_innb="'+noticeVo.bm_innb+'"  data-dty_code="'+noticeVo.em_dty_code +'" href="#">'+noticeVo.bm_sj+'</a>';
					html+='</td>';
					
					if(noticeVo.ai_cnt > 0 ){
   						html+='<td><i class="fa fa-paperclip  fa-lg"></i></td>';
   					}else{
   						html+='<td><i  style="color:#EAEAEA;" class="fa fa-times"></i></td>';
   					}
					
					
					html+='<td>'+noticeVo.regist_man+'</td>';
					html+='<td>'+noticeVo.regist_de+'</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="5">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#noticeTbody").html(html);
			$("#noticeTbody").find(".notice-view").click(function(){
				fnSetNoticePopClean();
				fnViewNotice($(this).data("bm_innb"), $(this).attr("data-dty_code"));
			});
			
			$("#noticeNavi").html(data.navi);
		}
	});
}

// 상세보기
function fnViewNotice(bmInnb, dty_code){
	authCode = dty_code;
	 
	$.ajax({
		url : "/notice/view",
		data:{"bmInnb" : bmInnb, "authCode" : authCode},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) { 
			
			var noticeVo = data.noticeVo;
			$("#noticeViewBmSj").text(noticeVo.bm_sj);
			$("#noticeViewBmCn").text(noticeVo.bm_cn);
			$("#noticeMod70100").val(noticeVo.bm_innb);
			$("#noticeDel70100").val(noticeVo.bm_innb);
			$("#fixRoundViewItem").attr("disabled", "disabled");         
			
			$("#selectOmViewItem").html("");
			for (var i = 0; i < data.noticeOmList.length; i++) {
				var noticeOm = data.noticeOmList[i];
				var span = $('<span>'+noticeOm.value+'</span>');
				$("#selectOmViewItem").append(span);
			}
			/*$("#selectSmViewItem").html("");
			for (var i = 0; i < data.noticeSmList.length; i++) {
				var noticeSm = data.noticeSmList[i];
				var span = $('<span>'+noticeSm.value+'</span>');
				$("#selectSmViewItem").append(span);
			}*/
			$("#selectTmViewItem").html("");
			for (var i = 0; i < data.noticeTmList.length; i++) {
				var noticeTm = data.noticeTmList[i];
				var span = $('<span>'+noticeTm.value+'</span>');
				$("#selectTmViewItem").append(span);
			}
			$("#fixRoundViewItem").html("");
			for (var i = 0; i < data.fixRoundList.length; i++) {
				var fixRound = data.fixRoundList[i];
				var span = $('<option>'+fixRound.value+'</option>');
				$("#fixRoundViewItem").append(span);
			}
			$("#selectEmViewItem").html("");
			for (var i = 0; i < data.noticeEmList.length; i++) {
				var noticeEm = data.noticeEmList[i];
				var span = $('<span>'+noticeEm.value+'</span>');
				$("#selectEmViewItem").append(span);
			}
			$("#noticeFileViewList").html("");
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
				$("#noticeFileViewList").append(html);
			}
			$(".noti-file-down").click(function(){
				location.href = "/file/down?ai_no="+$(this).data("ai_no");
			});
			
			/*if(noticeVo.insert_Type == "om") {
				$("#selectOmViewItem").show();
				$("#selectSmViewItem").hide();
			}else if(noticeVo.insert_Type == "sm") {
				$("#selectOmViewItem").hide();
				$("#selectSmViewItem").show();
			}else { 
				$("#selectOmViewItem").show();
				$("#selectSmViewItem").show();
			} */
			
			$("#noticeViewPop70100").show();
		}
	});
}

//저장
function fnSaveNotice(){
	var bmSj = $.trim($("#noticeBmSj").val());
	if(bmSj == ""){
		alert("제목을 입력해주세요.");
		$("#noticeBmSj").focus();
		return;
	}
	var bmCn = $.trim($("#noticeBmCn").val());
	if(bmCn == ""){
		alert("내용을 입력해주세요.");
		$("#noticeBmCn").focus();
		return;
	}
	var omGroup = $("#selectOmItem span");
	//var smGroup = $("#selectSmItem span");
	var smGroup = $("#selectTeamItem span");
	var emGroup = $("#selectEmItem span");
	
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
	}*/	 
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
		var omGroupN   = $("#noticeOmCode > option");
		for (var i = 0; i < omGroupN.length; i++) {
			 var data = omGroupN.eq(i).attr("data-om_innb");
			 if(data == undefined || data == "") continue;
			 omArr.push({
				 "om_code": omGroupN.eq(i).attr("data-om_innb")
			});
		}
	}
	
	if(noticeAuth == 1) {
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
	var saveType =  $("#noticeSaveType70100").val();
	var fixRnd = $("#noticeFixRound").val(); //0000000007 순회, 0000000006 고정
	
	if(saveType =="U"){
		bmInnb = $("#noticeBmInnb").val();
	}
	
	if(omArr.length > 0 && smArr.length == 0 ) {
		insert_Type = 'om';
	}else if(omArr.length == 0 && smArr.length > 0) {
		insert_Type = 'sm';
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
	var url = "/notice/save";
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
				fnSaveNoticeFile(bmInnb);
				var goPg = 1;
				if(saveType == "U"){
					goPg = noticePg;
				}
				fnGetNoticeList(goPg);
			}else{
				alert("저장실패.");
			}
		}
	}); 
}

function fnSaveNoticeFile(bmInnb){
	var amNo = $("#noticeFileAmNo").val();
	var formData = new FormData();
	
	if(noticefileList.length > 0) {
		for (var i = 0; i < noticefileList.length; i++) {
			formData.append('files', noticefileList[i]);
		}
	}else{
		alert("정상적으로 등록되었습니다.");
		$("#noticeSavePop70100").hide();
		fnSetNoticePopClean();
		//fnGetNoticeList(1);
		return;
	}
	formData.append('bmInnb', bmInnb);
	formData.append('amNo', amNo);
	$.ajax({
		url : "/noticefile/save",
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
				$("#noticeSavePop70100").hide();
				fnSetNoticePopClean();
				noticefileList = []; //추가되었습니다.
				fileId = 1;			 //추가되었습니다.
				$("#noticeFile").file({fileList : noticefileList});  //추가되었습니다.
			}else{
				alert("파일저장실패.");
			}
			
		}
	}); 
}

function fnSetNoticePopClean(){
	$("#noticeFileItemList").html("");
	$("#noticeFileViewList").html("");
	
	$("#selectSmItem").html("");
	$("#selectOmItem").html("");
	$("#selectEmItem").html("");
	$("#selectTeamItem").html("");
	
	$("#selectSmViewItem").html("");
	$("#selectOmViewItem").html("");
	
	$("#noticeBmSj").html("");
	$("#noticeBmCn").html("");
	
	$("#noticeViewBmSj").html("");
	$("#noticeViewBmCn").html("");
	$("#noticeSaveType70100").val("I");
	$("#noticeFixRound").val("");
}

function clean70103() {
	$("#jijumOmCode70100").val("");
	$("#teamWord70100").val("");
	$("#noticeWord70100").val("");
	$("#teamCode70100").val("");
}


function validationMaket(code) {
	var smGroup = $("#selectSmItem span");
	
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
	var om_code = String(omArray);		 
	 
    $("#teamWord70100").autocomplete({
    	serviceUrl:'/70/martList',
    	//serviceUrl:'/organization/auth',
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
	   			$("#teamCode70100").val(code);
	   			var span = $('<span data-code="'+code+'" data-om-code="'+omCode+'" >'+codeNm+'</span>');
	   			var a = $('<a href="#" />');
	   			a.click(function (){
	   				$(this).parent().remove();
 	   			});
	   			span.append(a);
	   			$("#teamWord70100").append(span);
	   		}
        }
    });
}

//메인 선택박스 생성
function fnComboBox_70100(om_code, om_orgnzt_se) {
	$.ajax({
		url : "/notice/auth",
	    data:{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var listHtml = "<option value='' selected='selected'>전체</option>";
	    	if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			if(res.om_nm == '지점') continue;
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
	    		}
			}	
	    	 
	    	$("#noticeAuthType70100").val(parseInt(data.auth_flag));
	    	$("#noticeCmCode70100").val(data.cm_code);
	    	$("#noticeEmCode70100").val(data.em_code);
	    	$("#jijumOmCode70100").html(listHtml);
	    	noticeAuth = $("#noticeAuthType70100").val();
	    	noticeEmCode= $("#noticeEmCode70100").val();
	    	if(noticeAuth > 2) {
	    		$("#teamWord70100").attr("disabled", "disabled");
	    	}
	    }
	});
}

//팝업 선택박스 생성
function fnGetMsgSearchComboBox_70100(om_code ,om_orgnzt_se){
	$.ajax({
		url : "/notice/auth",
	    data:{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var listHtml = "<option value='' selected='selected'>선택</option>";
	    	if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			if(res.om_nm == '지점') continue;
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
	    		}
			}	
	    	 
	    	$("#noticeAuthType70100").val(parseInt(data.auth_flag));
	    	$("#noticeCmCode70100").val(data.cm_code);
	    	$("#noticeTmCode70100").val(data.tm_code);	//팀코드
	    	$("#noticeOmCode").html(listHtml);
	    }
	});
	
	var omGroup   = $("#noticeOmCode > option");
   
	omArray = new Array();
	for (var i = 0; i < omGroup.length; i++) {
		 var data = omGroup.eq(i).attr("data-om_innb");
		 if(data == undefined || data == "") continue;
		 omArray.push(omGroup.eq(i).attr("data-om_innb"));
	}
	
	fnMakeAutoComplete_cp(omArray);
	fnMakeAutoComplete70100(omArray);
	fnMakeAutoCompleteEmpl70100(3,omArray);
}

//팝업수신매장 자동생성
/*function fnMakeAutoComplete70100(omArray){
	var om_code = String(omArray);		 
	 
    $("#noticeSmCode").autocomplete({
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
	   			if(validationMaket(code)){
					span.append(a);
					$("#selectSmItem").append(span);
				}
	   		}
	   		$(this).val("");
        }
    });
}*/

//팀명 자동생성
function fnMakeAutoComplete70100(omArray){
	var om_code = String(omArray);		 
	 
    $("#noticeTeamCode").autocomplete({
    	serviceUrl:'/70/martList',
    	//serviceUrl:'/organization/auth',
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
					$("#selectTeamItem").append(span);
				}
	   		}
	   		$(this).val("");
        }
    });
}

function validationTeam(code) {
	var smGroup = $("#selectTeamItem span");
	
	for (var i = 0; i < smGroup.length; i++) {
		if(code == smGroup.eq(i).attr("data-code")) {
			alert("중복된 팀이 존재합니다.");
			return false;
		}
	}
	return true;
}

//팝업화면 사원명 자동완성
function fnMakeAutoCompleteEmpl70100(val,omArray){
	var cm_code = $("#noticeCmCode70100").val(); //cm_code
	var fix_gbn = $("#noticeFixRoundItem").val();  
	var om_code = "";
	
	if(val == "3") {
		om_code = String(omArray);	
	} 
 
    $("#noticeEmNo70100").autocomplete({
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
					$("#selectEmItem").append(span);
				}
	   		}
	   		$(this).val("");
        }
    });
}

function validationEmpl(code) {
	var emGroup = $("#selectEmItem span");
	
	for (var i = 0; i < emGroup.length; i++) {
		if(code == emGroup.eq(i).attr("data-code")) {
			alert("중복된 사원명이 존재합니다.");
			return false;
		}
	}
	
	return true;
}