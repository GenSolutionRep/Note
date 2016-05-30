
//var noticefileList = [];
var noticeMsgPg = 1;
var v_cp_code_70400 ="";
var msgAuth = "";
var msgEmCode7400 = "";
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#noticeSavePop70400").instancePopUp();
	$("#noticeViewPop70400").instancePopUp();
	
	//input 달력
	$("#searchToDate70400").winCal(baseOptions);	
	$("#searchFromDate70400").winCal(baseOptions);
	
	v_cp_code_70400 = $("#login_cp_cd_70400").val();
	 
	fnSetEventComponent70400();	// 이벤트 등록
	
	fnGetNoticeList70400(noticeMsgPg); //리스트 조회
 
});




/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent70400(){
	fnComboBox_70400("",1); 			//메인 콤보박스 생성 
	fnGetMsgSearchComboBox_70400("",1); //팝업 콤보박스 생성
	
	//발신 버튼 눌렀을때
	$("#noticeOpen70400").click(function(){
		$("#noticeSavePop70400").show();
		fnSetNoticePopClean70400();
		areaReceiverdView();
		clean70400();
	});
	
	//저장
	$("#noticeSave70400").click(function(){
		fnSaveNotice70400();
	});
	//조회
	$("#noticeSearchBtn70400").click(function(){
		fnGetNoticeList70400("1");
	});
	//초기화
	$("#noticeSearchResat70400").click(function(){
		$("#jijumOmCode70400").val("");//지점구분
		$("#jijumWord70400").val("");  //지점명
		$("#receiver70400").val("");   //수신자
		$("#noticeKey70400").val(0);   //검색구분
		$("#noticeWord70400").val(""); //검색어
		
		fnGetNoticeList70400("1");
	});
	//닫기
	$("#noticeSavePopClose70400, #noticeSavePopCloseX70400").click(function(){
		$("#noticeSavePop70400").hide();
	});
	//닫기
	$("#noticeViewPopClose70400, #noticeViewPopCloseX70400").click(function(){
		$("#noticeViewPop70400").hide();
	});
	//수정
	$("#noticeMod70400").click(function(){
		fnModiNotice($(this).val());
	});
	//파일
	$('#noticeFileInfoList').filedrop({
	    callback : function(fileEncryptedData) {
	    	noticeDragFile(fileEncryptedData);
	    }
	});
	
	//단건 왼쪽에서 오른쪽으로
	$("#empP").click(function(){
		fnMoveLtoR($(this).val());
	});
	//다건 왼쪽에서 오른쪽으로
	$("#empAP").click(function(){
		fnMoveLtoRall($(this).val());
	});
	//단건 오른쪽에서 왼쪽으로
	$("#empM").click(function(){
		fnMoveRtoL($(this).val());
	});
	//다건 오른쪽에서 왼쪽으로
	$("#empAM").click(function(){
		fnMoveRtoLall($(this).val());
	});
	
	//팝업화면 수신지점 체인지
	$("#noticeSmCode70400").change(function(){
		areaReceiverdView();
	});
	
}

//팝업 선택박스 생성
function fnGetMsgSearchComboBox_70400(om_code ,om_orgnzt_se){
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
				$("#noticeOmCode70400").html(listHtml);
				$("#noticeOmCode70400").change(function(){
					//fnGetMsgSearchComboBox_70400($(this).val(), 2);
					fnMakeAutoComplete70400($(this).val());		//수신매장콤보박스 생성
					$("#om_parent_no_70400").val($(this).val());
					$("#noticeCmCode70400").val(data.cm_code);
					$("#om_orgnzt_se_70400").val("2");
					areaReceiverdView();
				});
				if(parseInt(data.auth_flag) > 1){ // auth_flag (1:관리자, 2:팀장, 3:사원)  
					fnGetMsgSearchComboBox_70400($("#noticeOmCode70400").val() ,2);
					$("#om_parent_no_70400").val($("#noticeOmCode70400").val());
					$("#noticeCmCode70400").val(data.cm_code);
				}
			}else if(om_orgnzt_se==2){
				if(parseInt(data.auth_flag) == 2){ // auth_flag (2:팀장, 3:사원)  
					$("#om_parent_no_70400").val($("#noticeOmCode70400").val());
					$("#noticeCmCode70400").val(data.cm_code);	
					fnMakeAutoComplete70400($("#noticeOmCode70400").val());		//수신매장콤보박스 생성
					areaReceiverdView(data.om_code);
				}				
			}
	    }
	});
}

//수신자 리스트 목록
function areaReceiverdView() { 
	var om_cd      = $("#noticeOmCode70400").val();	//지점명
	var sm_cd      = $("#noticeSmCode70400").val();	//수신매장
	var parent_no  = "";// 
	 
	if(msgAuth =="2") {			//팀장
		parent_no = $("#noticeMessageOmCode70400").val();
	}else {
		parent_no = $("#om_parent_no_70400").val();
	}
	 
	var params = {
			"om_cd" : om_cd,
			"sm_cd" : sm_cd,
			"parent_no" : parent_no 
		}; 
	$.ajax({
		url : "/noticeMessage/receivedList",
		data:params,
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if(data.receiverdVoList !=null && data.receiverdVoList.length > 0){
				for(var i=0; i<data.receiverdVoList.length; i++){
					var NoticeMessageVo = data.receiverdVoList[i];

					var omCode = NoticeMessageVo.om_cd;
					var smCode = NoticeMessageVo.sm_code;
					var emCode = NoticeMessageVo.em_no;
					/*alert(omCode);
					alert(smCode);
					alert(emCode);*/
					
	    			listHtml += "<option value ="+emCode+" id=receiver"+emCode+" data-om_innb='"+omCode+"'  data-sm_innb='"+smCode+"' class=notice-view >"+ NoticeMessageVo.em_nm +"</option>";
				}	
				
			}else{
				 
			}	
			
			$("#selectEmpList").html(listHtml);
			
	    }
	});
}

function fnComboBox_70400(om_code ,om_orgnzt_se){
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
	    	
	    	$("#noticeMessageOmCode70400").val(data.om_code);
	    	$("#noticeMessageCmCode70400").val(data.cm_code);
	    	$("#noticeMessageEmCode70400").val(data.em_code);
	    	$("#noticeMessageAuthType70400").val(parseInt(data.auth_flag));
	    	$("#jijumOmCode70400").html(listHtml);
	    	msgAuth = $("#noticeMessageAuthType70400").val();
	    	msgEmCode7400 = $("#noticeMessageEmCode70400").val();
	    }
	});
	
	var omGroup   = $("#jijumOmCode70400 > option");
	var omArray = new Array();
	for (var i = 0; i < omGroup.length; i++) {
		 var data = omGroup.eq(i).attr("data-om_innb");
		 if(data == undefined || data == "") continue;
		 omArray.push(omGroup.eq(i).attr("data-om_innb"));
	}
	 
	fnMakeAutoComplete_cp(omArray);	//수신매장콤보박스 생성
}

//메인화면 매장명 자동완성
function fnMakeAutoComplete_cp(omArray){
	om_code = String(omArray);
	
    $("#jijumWord70400").autocomplete({
    	serviceUrl:'/noticeMessage/martList_cp',
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
	   		var appendCheck = $("#mart > span[data-code='"+code+"']").length;
	   		if(code != "" && appendCheck == 0){
	   			var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
	   			var a = $('<a href="#" />');
	   			a.click(function (){
	   				$(this).parent().remove();
	   			});
	   			$("#jijumWord70400Hidden").val(code);
	   			span.append(a);
	   			$("#jijumWord70400").append(span);
	   		}
        }
    });
}

//버튼 눌렀을때 단건 사용자 왼->오 이동
function fnMoveLtoR() {
	var selectVar = String($('#selectEmpList').val());

	if(selectVar == null) {
		alert("수신자를 선택해 주세요.");
		return;
	}
	 
	var arr = selectVar.split(',');
	var selectlen = arr.length; 
	
	for(var i=0; i < selectlen; i++) {
		if(validation(arr[i],'R')){
			$('#selectEmpGL').prepend($('#receiver'+arr[i]));
		}
	}
}


//버튼 눌렀을때 단건 사용자 오->왼 이동
function fnMoveRtoL() {
	var selectVar = String($('#selectEmpGL').val());

	if(selectVar == null) {
		alert("수신자를 선택해 주세요.");
		return;
	}
	 
	var arr = selectVar.split(',');
	var selectlen = arr.length; 
	
	for(var i=0; i < selectlen; i++) {
		if(validation(arr[i],'L')){
			$('#selectEmpList').prepend($('#receiver'+arr[i]));
		}
	}
	
}

//버튼 눌렀을때 전체 사용자 왼->오 이동
function fnMoveLtoRall() {
	$("#selectEmpList > option").attr("selected", "selected");
	var selectVar = String($('#selectEmpList').val());
	
	var arr = selectVar.split(',');
	var selectlen = arr.length; 
	
	for(var i=0; i < selectlen; i++) {
		if(validation(arr[i],'R')){
			$('#selectEmpGL').prepend($('#receiver'+arr[i]));
		}
	}
	$('#selectEmpList').html('');
}

//버튼 눌렀을때 전체 사용자 오->왼 이동
function fnMoveRtoLall() {
	$("#selectEmpGL > option").attr("selected", "selected");
	var selectVar = String($('#selectEmpGL').val());
	 
	var arr = selectVar.split(',');
	var selectlen = arr.length; 
	
	for(var i=0; i < selectlen; i++) {
		if(validation(arr[i],'L')){
			$('#selectEmpList').prepend($('#receiver'+arr[i]));
		}
	}
}

//사원 중복체크
function validation(code,val) {
	var empRleng = $("#selectEmpGL").find('.notice-view').size();		//오른쪽    수신자 목록
	var empLleng = $("#selectEmpList").find('.notice-view').size();		//왼쪽 수신자 목록
	if(val == 'R'){   //오른쪽으로 넘길때
		for(var i=1; i<empRleng+1; i++) {
			if(code == $('#selectEmpGL option:nth-child('+i+')').val()){
				return false;
			}
		}
	}else if(val == 'L') {  //왼쪽으로 넘길때
		for(var i=1; i<empLleng+1; i++) {
			if(code == $('#selectEmpList option:nth-child('+i +')').val()){
				$("#selectEmpGL").find("option").each(function() {
					 if(this.value == code) {
						 $(this).remove();
					 }
				});
				return false;
			}
		}
	}
	
	return true;
}


//조회
function fnGetNoticeList70400(currPg){
	noticeMsgPg = currPg;	// 등록후 현 페이지 리로드
	var jijumOmCode70400 = $("#jijumOmCode70400").val();		//지점구분
	var jijumWord70400   = $("#jijumWord70400Hidden").val();	//지점명
	var receiver70400	 = $("#receiver70400").val();			//수신자
	var noticeKey70400   = $("#noticeKey70400").val();			//검색구분
	var noticeWord70400  = $("#noticeWord70400").val();			//검색어
	var msg_auth_code 	 = "";
    var jijumCmCode70400 = $("#noticeMessageCmCode70400").val(); //cm_code;
      
	if($("#jijumWord70400").val() == "" ) {
		jijumWord70400 = "";
	}
 
	/*if(msgAuth =="2") {			//팀장
		msg_auth_code = "'0000000008','0000000007','0000000006'";	
	}else if(msgAuth =="4") {	//순회
		msg_auth_code = "'0000000007'" ;	
	}else if(msgAuth =="3") {	//고정
		msg_auth_code = "'0000000006'" ;	
	}*/
	
	var params = {
		"auth_flag"        : msgAuth,
		"cm_code"          : jijumCmCode70400,
		"em_no"            : msgEmCode7400,
		"jijumOmCode70400" : jijumOmCode70400,
		"jijumWord70400"   : jijumWord70400,
		"receiver70400"    : receiver70400,
		"key70400"   	   : noticeKey70400,
		"word70400"  	   : noticeWord70400,
		"search_date_from" : "",
		"search_date_to"   : "",
		"type"   		   : "WEB"
	}; 
	var rowSize = 15;				// 표시할 로우수
	var fnName = "fnGetNoticeList70400";//페이징처리 function명
	$.ajax({
		url : "/noticeMessage/list",
		type : "POST",
		dataType : "json",
		data:{
			  "fnName":fnName
			, "params":params
			, "rowSize":rowSize
			, "currPg":currPg
		},
		cache : false,
		success : function(data) {
			var html = '';
			if (data.noticeMessageVoList.length > 0) {
				for (var i = 0; i < data.noticeMessageVoList.length; i++) {
					var noticeMessageVoList = data.noticeMessageVoList[i];
					var count = "";
					var receiveYn = "";
					
					for (var j = 0; j < data.receiverdVoList.length; j++) {
						var receiverdVoList = data.receiverdVoList[j];
						if(receiverdVoList.nm_innb == noticeMessageVoList.nm_innb) {
							count = receiverdVoList.no;
							receiveYn = receiverdVoList.receive_yn;
						}
					}
					 
					html+='<tr>';
					html+='<td>' + (parseInt(data.firstNo)-i) + '</td>';
					html+='<td class="txt_left" data-dty_code="'+noticeMessageVoList.em_dty_code +'">'+noticeMessageVoList.nm_massage+'</td>';
					html+='<td>'+ receiveYn +'/'+ count +'</td>';
					html+='<td>'+noticeMessageVoList.regist_man+'</td>';
					html+='<td>';
					html+='    <a class="notice-message" data-nm_Innb="'+noticeMessageVoList.nm_innb+'" href="#"><button type="button"  id="noticeDetail70400">보기</a>';
					html+='</td>';
					html+='<td>'+noticeMessageVoList.regist_de+'</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="6">조회된 데이터가 없습니다.</td></tr>';
			}
			
			$("#noticeTbody70400").html(html);
			
			$("#noticeTbody70400").find(".notice-message").click(function(){
				fnSetNoticePopClean70400();
				fnViewNoticeMessage($(this).data("nm_innb"));
			});
			$("#noticeNavi70400").html(data.navi);
		}
	});
}

// 상세보기
function fnViewNoticeMessage(nmInnb){
	$.ajax({
		url : "/noticeMessage/view",
		data:{"nmInnb" : nmInnb},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {
			var html = '';
			for (var i = 0; i < data.noticeMessageVoList.length; i++) {
				var noticeMessageVoList = data.noticeMessageVoList[i];
				html+='<tr>';
				html+='<td>'+noticeMessageVoList.om_nm+'</td>';
				html+='<td>'+noticeMessageVoList.c_name+'</td>';
				html+='<td>'+noticeMessageVoList.em_nm+'</td>';
				html+='</tr>';
			}
			$("#noticeViewPopTbody70400").html(html);
			$("#noticeViewPop70400").show();
		}
	});
	
}

//팝업 저장버튼
function fnSaveNotice70400(){
	$("#selectEmpGL > option").attr("selected", "selected");
	var em_no 	  = String($('#selectEmpGL').val());
	//var om_code	  = String($("#noticeOmCode70400").val());
	//var sm_code	  = String($("#noticeSmCode70400").val());
	var cm_code	  = String($("#noticeCmCode70400").val());
	var nm_massage= String($("#noticeMessageArea70400").val());
	var omArr 	  = new Array();
	var smArr = new Array();
	var omGroup   = $("#selectEmpGL > option");
	
	for (var i = 0; i < omGroup.length; i++) {
		omArr.push({
			"om_code":omGroup.eq(i).attr("data-om_innb")
		});
		
		smArr.push({
			"sm_code":omGroup.eq(i).attr("data-sm_innb")
		});
	}
 
	if(em_no == "" || em_no == 'null' || omGroup.length < 1){
		alert("수신자를 선택해 주세요.");
		$("#selectEmpGL").focus();
		return;
	}
	if(nm_massage == "" || nm_massage == null){
		alert("메시지를 입력해주세요.");
		$("#noticeMessageArea70400").focus();
		return;
	}
 
	var arr = em_no.split(',');
	var selectlen = arr.length; 
	var emArr = new Array();
	
	for (var i = 0; i < selectlen; i++) {
		emArr.push({
			"em_code":$('#receiver'+arr[i]).val()
		});
	}	
	
	var nmInnb = "";	
	var saveType =  $("#noticeSaveType70400").val();
	if(saveType =="U"){
		nmInnb = $("#noticeNmInnb70400").val();
	}
 
	var saveData = {
			 saveType  : saveType
			,nm_Innb   : nmInnb
			,cm_code   : cm_code
			,nm_massage: nm_massage
			,emArr     : emArr
			,omArr     : omArr
			,smArr     : smArr
	};
	
	var url = "/noticeMessage/save";
	$.ajaxSettings.traditional = false;
	$.ajax({
		global: true,	
		url : url,
		data:saveData,
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(nmInnb) {
			if(nmInnb != ""){
				fnSaveNoticePush();  
				var goPg = 1;
				if(saveType == "U"){
					goPg = noticeMsgPg;
				}
				fnGetNoticeList70400(goPg);
			}else{
				alert("error");
			}
		}
	});
	
}

function fnSaveNoticePush(){
	alert("정상적으로 등록되었습니다.");
	$("#noticeSavePop70400").hide();
}

function fnSetNoticePopClean70400(){
	$("#noticeOmCode70400").val('');		//수신매장
	$('#om_parent_no_70400').val('');		//수신매장
	$("#noticeSmCode70400").val('');		//수신지점
	$("#selectEmpList").val('');			//수신자 왼쪽
	$("#selectEmpGL").html('');				//수신자 오른쪽
	$("#noticeMessageArea70400").val('');   //메시지 내용
	
	$("#noticeSaveType70400").val("I");
}

function clean70400() {
	$("#jijumOmCode70400").val("");
	$("#jijumWord70400").val("");
	$("#receiver70400").val("");
	$("#noticeWord70400").val("");
	$("#jijumWord70400Hidden").val("");
}

//팝업화면 지점명 셀렉트 박스
function fnMakeAutoComplete70400(om_code){
	$.ajax({
		url : "/noticePopupOmlist/list",
		data:{"om_code":om_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
		var listHtml = "<option value='' selected='selected'>선택</option>";
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var noticeMessageVo = data.result[i];     
        		listHtml += "<option value='"+noticeMessageVo.sm_code+"'>"+noticeMessageVo.sm_nm + "</option>";
        	}
		}else{
			
		}	
		$("#noticeSmCode70400").html(listHtml);
	    	 
	    }
	});
}

