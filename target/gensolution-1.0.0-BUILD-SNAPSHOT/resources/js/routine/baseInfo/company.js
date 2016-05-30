
/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	fnMakeEventComponent();	// 버튼 이벤트 등록
	
	//리스트 조회
	fnSelectCompanyList();
});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent(){
	
	//조회
	$("#btnCmpnySearch").click(function(){
		fnSelectCompanyList();
	});
	
	//등록 버튼
	$("#btnCmpnyInsert").click(function(){
		//팝업 컨트롤 초기화
		cmpnyPopControlInit();

		$("#companyPop").show();
		$("#save_type").val("INSERT");
	});
	
	//팝업 텍스트 닫기 텍스트 클릭
	$("#textCmpnyPopupClose").click(function(){
		$("#companyPop").hide();
	});
	
	//팝업 저장버튼
	$("#btnCmpnyPopSave").click(function(){
		fnSaveComany();
	});
	
	//팝업 닫기버튼
	$("#btnCmpnyPopClose").click(function(){
		$("#companyPop").hide();
	});
}

/**
 * @함수명: fnSelectCompanyList
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 리스트 조회
 * @param 
 */
function fnSelectCompanyList(){
	$.ajax({
		url : "/company/companyList",
	    //data:{"param":$("#m_no").val()},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	
		var listHtml = "";
		
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var res = data.result[i];     
				listHtml += "<tr>";
        		listHtml += "<td>" + res.cmpny_code + "</td>";
    			listHtml += "<td>" + res.cmpny_nm + "</td>";
    			listHtml += "<td>" + res.sort_ordr + "</td>";
    			listHtml += "<td>" + res.use_at + "</td>";
    			listHtml += "<td>";
    			listHtml += "	<button type='button' onclick=\"fnCmpnyInfoUpdate('"+res.cmpny_code+"')\"; <span class='glyphicon glyphicon-edit'></span>수정</button>&nbsp;&nbsp;";
    			listHtml += "	<button type='button' onclick=\"fnDeleteCompany('"+res.cmpny_code+"')\"; <span class='glyphicon glyphicon-remove'></span>삭제</button>&nbsp;&nbsp;";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#cmpnyTbody").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: fnCmpnyInfoUpdate
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 메뉴코드 정보 수정
 * @param 
 */
function fnCmpnyInfoUpdate(cmpny_code){
	$("#save_type").val("UPDATE");
	
	//팝업 컨트롤 초기화
	cmpnyPopControlInit();
	
	//팝업 show
	$("#companyPop").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/company/companyRow",
		data : {'cmpny_code': cmpny_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	
			if(data!=null && data.result.length > 0){
				var res = data.result[0];     		
				$("#cmpnyPop_cmpny_code").val(res.cmpny_code);
				$("#cmpnyPop_cmpny_nm").val(res.cmpny_nm);
				$("#cmpnyPop_sort_ordr").val(res.sort_ordr);
				$("input:radio[name=cmpnyPop_use_at]:input[value=" + res.use_at + "]").attr("checked", true);
				$("#cmpnyPop_memo").val(res.memo);
			}else{
				
			}	
	    }
	});	
}

/**
 * @함수명: fnSaveComany
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 회사코드 입력/삭제
 * @param 
 */
function fnSaveComany(){
	var vUrl = "";
	var vType = $('#save_type').val();
	var param = $('#frmCompany').serialize();
	
	 if($.trim($('#cmpnyPop_cmpny_nm').val()) == ''){
		 alert("회사명을 입력하세요");
		 $("#cmpnyPop_cmpny_nm").focus();
		 return;
	 }
	 
	 if($.trim($('#cmpnyPop_sort_ordr').val()) == ''){
		 alert("순서를 입력하세요.");
		 $("#cmpnyPop_sort_ordr").focus();
		 return;
	 }
	 
	 if(vType=="UPDATE")
		 vUrl = "/company/updateCompany";
	 else
		 vUrl = "/company/insertCompany";
	 
	 //alert(vUrl);
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   url  : vUrl,
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
			if(data.resultCnt==0 ){
				alert('저장되지 않았습니다.');
				return;
			}else if(data.resultCnt==1){
				alert('저장 되었습니다.');
				fnSelectCompanyList();
				//$(SearchForm).attr({action:Url, method:'post'}).submit();
				
				//닫기
				$("#btnCmpnyPopClose").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}
/**
 * @함수명: fnDeleteCompany
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 회사코드 삭제
 * @param 
 */
function fnDeleteCompany(cmpny_code){
	
	if(!confirm("해당 회사를 삭제 하시겠습니까?")){
		return;
	}
	
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   url  : "/company/deleteCompany",
	   dataType :"json",
	   data  	 : {'cmpny_code': cmpny_code},
	   success  : function(data){
			if(data.resultCnt==0 ){
				alert('삭제되지 않았습니다.');
				return;
			}else if(data.resultCnt==1){
				alert('삭제 되었습니다.');
				
				//리스트 조회
				fnSelectCompanyList();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

/**
 * @함수명: cmpnyPopControlInit
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 팝업 컨트롤 초기화
 * @param 
 */
function cmpnyPopControlInit(){
	$('#cmpnyPop_cmpny_code').val('');
	$('#cmpnyPop_cmpny_nm').val('');
	$('#cmpnyPop_sort_ordr').val('');
	$('#cmpnyPop_memo').val('');
	$('input:radio[id=cmpnyPop_use_at_y]').attr("checked", true);
	
	$('#cmpnyPop_cmpny_nm').focus();
}


