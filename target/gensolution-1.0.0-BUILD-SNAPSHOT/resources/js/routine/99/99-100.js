/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	fnMakeEventComponent_99100();	// 버튼 이벤트 등록
	
	//리스트 조회
	fnSelectCompanyList_99100();
});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent_99100(){
	
	$("#tempPop99100").instancePopUp();
	
	//조회
	$("#tempSearch99100").click(function(){
		fnSelectCompanyList_99100();
	});
	
	//등록 버튼
	$("#tempInsert99100").click(function(){
		//팝업 컨트롤 초기화
		popControlInit_99100();

		$("#tempPop99100").show();
		$("#save_type_99110").val("INSERT");
		$('#cmpnyPop_cm_nm').focus();
	});
	
	//팝업 저장버튼
	$("#tempSave99100").click(function(){
		fnSave99100();
	});
	
	//팝업 닫기버튼
	$("#tempClose99100, #tempCloseX99100").click(function(){
		$("#tempPop99100").hide();
	});
}

/**
 * @함수명: fnSelectCompanyList_99100
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 리스트 조회
 * @param 
 */
function fnSelectCompanyList_99100(){
	$.ajax({
		url : "/company/companyList",
	    //data:{"param":$("#m_no").val()},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	
		var listHtml = "";
		
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var res = data.result[i];     
				listHtml += "<tr>";
        		listHtml += "<td>" + res.cm_code + "</td>";
    			listHtml += "<td class='txt_left'>" + res.cm_nm + "</td>";
    			listHtml += "<td>" + res.cm_sort_ordr + "</td>";
    			listHtml += "<td>" + res.use_at + "</td>";
    			listHtml += "<td>";
    			listHtml += "	<button type='button' class='white' onclick=\"fnCmpnyInfoUpdate_99100('"+res.cm_code+"')\";>수정</button>&nbsp;&nbsp;";
    			listHtml += "	<button type='button' class='white' onclick=\"fnDeleteCompany_99100('"+res.cm_code+"')\";>삭제</button>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody99100").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: fnCmpnyInfoUpdate_99100
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 메뉴코드 정보 수정
 * @param 
 */
function fnCmpnyInfoUpdate_99100(cm_code){
	$("#save_type_99110").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_99100();
	
	//팝업 show
	$("#tempPop99100").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/company/companyRow",
		data : {'cm_code': cm_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {

	    	if(data!=null && data.result.length > 0){
				var res = data.result[0];     		
				$("#cmpnyPop_cm_code").val(res.cm_code);
				$("#cmpnyPop_cm_nm").val(res.cm_nm);
				$("#cmpnyPop_cm_sort_ordr").val(res.cm_sort_ordr);
				$("input:radio[name=use_at]:input[value=" + res.use_at + "]").attr("checked", true);
				$("#cmpnyPop_cm_memo").val(res.cm_memo);
			}else{
			}	
	    }
	});	
	
	$('#cmpnyPop_cm_nm').focus();
}

/**
 * @함수명: fnSave99100
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 회사코드 입력/삭제
 * @param 
 */
function fnSave99100(){
	var vUrl = "";
	var vType = $('#save_type_99110').val();
	var param = $('#frm99100').serialize();
	
	 if($.trim($('#cmpnyPop_cm_nm').val()) == ''){
		 alert("회사명을 입력하세요");
		 $("#cmpnyPop_cm_nm").focus();
		 return;
	 }
	 /*
	 if($.trim($('#cmpnyPop_cm_sort_ordr').val()) == ''){
		 alert("순서를 입력하세요.");
		 $("#cmpnyPop_cm_sort_ordr").focus();
		 return;
	 }
	 */
	 if(vType=="UPDATE")
		 vUrl = "/company/updateCompany";
	 else
		 vUrl = "/company/insertCompany";
	 
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   global: true,
	   url  : vUrl,
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
			if(data.resultCnt==0 ){
				alert('저장되지 않았습니다.');
				return;
			}else if(data.resultCnt==1){
				alert('저장 되었습니다.');
				fnSelectCompanyList_99100();
				//$(SearchForm).attr({action:Url, method:'post'}).submit();
				
				//닫기
				$("#tempClose99100").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}
/**
 * @함수명: fnDeleteCompany_99100
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 회사코드 삭제
 * @param 
 */
function fnDeleteCompany_99100(cm_code){
	
	if(!confirm("해당 회사를 삭제 하시겠습니까?")){
		return;
	}
	
	$.ajax({
	   type : "POST",
	   global: true,
	   async : true,
	   cache : false,
	   url  : "/company/deleteCompany",
	   dataType :"json",
	   data  	 : {'cm_code': cm_code},
	   success  : function(data){
			if(data.resultCnt==0 ){
				alert('삭제되지 않았습니다.');
				return;
			}else if(data.resultCnt==1){
				alert('삭제 되었습니다.');
				
				//리스트 조회
				fnSelectCompanyList_99100();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

/**
 * @함수명: popControlInit_99100
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 팝업 컨트롤 초기화
 * @param 
 */
function popControlInit_99100(){
	
	$('#cmpnyPop_cm_code').val('');
	$('#cmpnyPop_cm_nm').val('');
	$('#cmpnyPop_cm_sort_ordr').val('');
	$("#cmpnyPop_cm_sort_ordr").onlyNum();
	$('#cmpnyPop_cm_memo').val('');
	$('input:radio[id=cmpnyPop_use_at_y]').attr("checked", true);
	
	$('#cmpnyPop_cm_nm').focus();

}

