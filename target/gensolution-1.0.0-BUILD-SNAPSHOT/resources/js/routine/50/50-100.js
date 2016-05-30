/**
 * @함수명: ready
 * @작성일: 2015. 09. 30
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#tempPop50101, #tempPop50102, #tempPop50103, #zipcdPop50103").instancePopUp();
	//$("#zipcdPop50103").dragable();

	
	fnSelectList_50101();
	
	//고객그룹 조회
	if($("#table50101 tbody tr").length > 0){
		$('#table50101 tbody tr:eq(0)').addClass('cell_active');
		
		$("#hTitle50102").text($("#table50101 tbody tr:eq(0) td:nth-child(2)").text() + " > 관리업체");
		fnSelectList_50102($("#table50101 tbody tr:eq(0) td:nth-child(1)").text());
	}
	
	if($("#table50102 tbody tr").length > 0){
		$('#table50102 tbody tr:eq(0)').addClass('cell_active');
		
		//관리업체
		$("#hTitle50103").text($("#table50102 tbody tr:eq(0) td:nth-child(2)").text() + " > 매장");
		fnSelectList_50103($("#table50102 tbody tr:eq(0) td:nth-child(1)").text());
	}
	
	if($("#table50103 tbody tr").length > 0){
		$('#table50103 tbody tr:eq(0)').addClass('cell_active');
	}
	
	// 이벤트 등록
	fnSetEventComponent();
	
	//사원명 AutoComplete
	fnMakeAutoComplete();
});




/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	
	/*$("#table50101 tr").click(function(){
		alert($("#table50101 tr td:eq(0)").text());
	});
	
*/
	/*
	$('#tbody50101 tr td').click(function(){
		var parents = $(this).parent().html();
		//var innerHtml = $(parents).html();
		alert("innerHtml : " + parents);
		/*
		var row = $(this).parent.get(0);
		var td = row.cells[0];
		var htmlOfTd = $(td).html();
		alert("htmlOfTd : " + htmlOfTd);
	});
	*/
	
    /*var tr = $('#table50101').find('tr');
    tr.bind('click', function(event) {
        var values = '';
        tr.removeClass('row-highlight');
        var tds = $(this).addClass('row-highlight').find('td');
        
        alert(tds(0).text)

        $.each(tds, function(index, item) {
            //values = values + 'td' + (index + 0) + ':' + item.innerHTML + '<br/>';
        	values = item.innerHTML ;
        });
        alert(values);
        

    });*/
	
	$("#table50101 tbody tr").click(function(){		
		$('#table50101 tbody').find('tr').removeClass('cell_active');
		$(this).addClass('cell_active');
		
		var idx = $("#table50101 tbody tr").index(this);
		
		//관리업체 타이틀 
		$("#hTitle50102").text($("#table50101 tbody tr:eq("+idx+") td:nth-child(2)").text() + " > 관리업체");
		fnSelectList_50102($("#table50101 tbody tr:eq("+idx+") td:nth-child(1)").text());
		
		if($("#table50102 tbody tr").length > 0){
			$('#table50102 tbody tr:eq(0)').addClass('cell_active');
			
			tableTr02Set();
			//매장 타이틀
			$("#hTitle50103").text($("#table50102 tbody tr:eq(0) td:nth-child(2)").text() + " > 매장");
			fnSelectList_50103($("#table50102 tbody tr:eq(0) td:nth-child(1)").text());
		}else{
			//매장 타이틀
			$("#hTitle50103").text(" > 매장");
			$("#tbody50103").html("");
		}
		
		if($("#table50103 tbody tr").length > 0){
			$('#table50103 tbody tr:eq(0)').addClass('cell_active');
		}
	});
	
	function tableTr02Set(){
		$("#table50102 tbody tr").click(function(){		
			$('#table50102 tbody').find('tr').removeClass('cell_active');
			$(this).addClass('cell_active');
			
			var idx = $("#table50102 tbody tr").index(this);
	
			tableTr03Set();

			//매장 타이틀 
			$("#hTitle50103").text($("#table50102 tbody tr:eq("+idx+") td:nth-child(2)").text() + " > 매장");
			fnSelectList_50103($("#table50102 tbody tr:eq("+idx+") td:nth-child(1)").text());
			
			if($("#table50103 tbody tr").length > 0){
				$('#table50103 tbody tr:eq(0)').addClass('cell_active');
			}
		});
	}
	
	function tableTr03Set(){
		$("#table50103 tbody tr").click(function(){		
			$('#table50103 tbody').find('tr').removeClass('cell_active');
			$(this).addClass('cell_active');
		});
	}


	//주소로 좌표 알아오기
	$('#getCoordinateBtn50103').click(function(){
		
		if($('#sm_zipcd_50103').val()==''){
			alert("주소를 검색하십시오");
			return;
		}
		if($('#sm_adres_50103').val()==''){
			alert("주소를 검색하십시오");
			return;
		}
		if($('#sm_etcadres_50103').val()==''){
			alert("주소를 검색하십시오");
			return;
		}
		
		var addrTxt = "";
		
		
		addrTxt = //$('#sm_zipcd_50103').val() + " " + 
				 $('#sm_adres_50103').val() + " "+
				 $('#sm_etcadres_50103').val() + $('#sm_dtadres_50103').val();
		addrTxt = addrTxt.replace("(","");
		addrTxt = addrTxt.replace(")","");
		
		alert(addrTxt);
		$.ajax({
			url : "http://maps.googleapis.com/maps/api/geocode/json",
			data:{"address": addrTxt, language:"ko", sensor:false},
		    type : "get",
		    dataType : "json",
		    cache : false,
		    async : false,
		    success : function(data) {
				if(data!=null&& data.results.length>0){
					$('#sm_la_50103').val(data.results[0].geometry.location.lat);
					$('#sm_lo_50103').val(data.results[0].geometry.location.lng);
				}
		    }
		});
	});
	
	//우편번호
	$('#addrSearchPopBtn50103').click(function(){
		$postOption.insertPostcode5 = "#sm_zipcd_50103";
		//주소
		$postOption.insertAddress = "#sm_adres_50103";
		//상세주소
		$postOption.insertExtraInfo = "#sm_etcadres_50103";
		//기타주소
		$postOption.insertDetails = "#sm_dtadres_50103";
					
		$("#zipcdPop50103").show();
	});
	
	$("#addrSearch50103").postcodify({
		controls : "#addrSearchControl50103" ,
		afterSelect : function(selectedEntry) { 
			$("#addrSearch50103").html("");
			$(".keyword").val("");
			$("#zipcdPop50103").hide();
		},
		hideOldAddresses : false ,
		mapLinkProvider : 'daum'
	});
	
	$('#zipcodePopCloseBtn50103').click(function(){
		$("#addrSearch50103").html("");
		$(".keyword").val("");
		$("#zipcdPop50103").hide();
	});
	
	
	
	$("#chkAutoYn_50101").click(function(){
		//코드 자동 등록 
		if($(this).prop("checked")){
			$("input:text[id=cg_code_50101]").attr("readonly", true);
			$("#cg_code_50101").val("");
			$("#autoYn_50101").val("Y");
		}else{
			$("input:text[id=cg_code_50101]").attr("readonly", false);
			$("#cg_code_50101").focus();
			$("#autoYn_50101").val("N");
		}
	});
	
	$("#chkAutoYn_50102").click(function(){
		//코드 자동 등록 
		if($(this).prop("checked")){
			$("input:text[id=me_code_50102]").attr("readonly", true);
			$("#me_code_50102").val("");
			$("#autoYn_50102").val("Y");
		}else{
			$("input:text[id=me_code_50102]").attr("readonly", false);
			$("#me_code_50102").focus();
			$("#autoYn_50102").val("N");
		}
	});
	
	$("#chk_sm_oper_at_50103").click(function(){
		//폐쇠여부
		if($(this).prop("checked")){
			$("#sm_oper_at_50103").val("N");
		}else{
			$("#sm_oper_at_50103").val("Y");
		}
	});
	
	
	
	//고객그룹 팝업 ==================================================
	$("#tempInsert50101").click(function(){
		$("#title1_50101, #title2_50101").text("고객그룹 등록");

		//팝업 컨트롤 초기화
		popControlInit_50101();

		$("#tempPop50101").show();
		$("#save_type_50101").val("INSERT");
		$("#cg_nm_50101").focus();
	});
	
	$("#tempClose50101, #tempCloseX50101").click(function(){
		$("#tempPop50101").hide();
	});

	//고객그룹 저장버튼
	$("#tempSave50101").click(function(){
		fnSave50101();
	});
	
	
	//관리업체 팝업===================================================
	$("#tempInsert50102").click(function(){
		$("#title1_50102, #title2_50102").text("관리업체 등록");
		
		//팝업 컨트롤 초기화
		popControlInit_50102();
		
		$("#save_type_50102").val("INSERT");
		$("#tempPop50102").show();
		$("#me_nm_50102").focus();
	});
	$("#tempClose50102, #tempCloseX50102").click(function(){
		$("#tempPop50102").hide();
	});
	
	//관리업체 저장버튼
	$("#tempSave50102").click(function(){
		fnSave50102();
	});
	
	
	//매장 팝업======================================================
	$("#tempInsert50103").click(function(){
		
		//팝업 컨트롤 초기화
		popControlInit_50103();
		
		$("#title1_50103, #title2_50103").text("매장 등록");
		$("#save_type_50103").val("INSERT");

		var optText ="<option>선택</option>";
		$("#me_code_combo_50103").html(optText);
		
		//관리지점 콤보 초기화
		cbo1_50103("001");
		//고객그룹 콤보 초기화
		cbo2_50103();
		
		//차수
		cboCommon_50103('0000000001', $('#sm_odr_50103'));
		
		$("#tempPop50103").show();
	});
	
	$("#tempClose50103, #tempCloseX50103").click(function(){
		$("#tempPop50103").hide();
	});
	
	//관리지점
	/*
	$("#om_code_combo_50103").change(function(){
		$("#cg_code_combo_50103, #me_code_combo_50103").html("<option>선택</option>");
		
		if($(this).val()==''){
			$("#em_nm_50103").val("");
		}else{
			cbo2_50103();			
		}
	});
	*/
	
	//담당사원유형
	$("input:radio[name=se_empl_ty]:").change(function(){
		$("#em_no_50103, #em_nm_50103").val("");
		$("#selectItem_em_nm_50103").html("");
	});
	
	//고객그룹
	$("#cg_code_combo_50103").change(function(){
		$("#me_code_combo_50103").html("<option>선택</option>");
		
		if($(this).val()==''){
			$("#em_nm_50103").val("");
		}else{
			cbo3_50103($(this).val());
		}
	});
	
	//관리업체
	$("#me_code_combo_50103").change(function(){
		if($(this).val()!=''){
			$("#em_nm_50103").val("");
		}
	});
	
	//매장 저장버튼
	$("#tempSave50103").click(function(){
		fnSave50103();
	});
	
}

/**
 * @함수명: cbo1_50103
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리지점 콤보 리스트
 * @param 
 */

function cbo1_50103(cm_code){
	$.ajax({
		url : "/organization/byCp/list",
		data:{"cm_code":cm_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
		var listHtml = "<option value='' selected='selected'>선택</option>";
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var organizationVo = data.result[i];     
        		listHtml += "<option value='"+organizationVo.om_code+"'>"+organizationVo.om_nm + "</option>";
        	}
		}else{
			
		}	
		$("#om_code_combo_50103").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: cbo2_50103
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 고객그룹명 콤보 리스트
 * @param 
 */

function cbo2_50103(){
	$.ajax({
		url : "/store/cstmrGroupList",
		//data:{"om_code":om_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "<option value='' selected='selected'>선택</option>";

		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var cstmrGroupVo = data.result[i];    
        		listHtml += "<option value='"+cstmrGroupVo.cg_code+"'>"+cstmrGroupVo.cg_nm + "</option>";
        	}
		}else{
			
		}	
		$("#cg_code_combo_50103").html(listHtml);
	    
	    }
	});
}
/**
 * @함수명: cbo3_50103
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리업체명
 * @param 
 */
function cbo3_50103(cg_code){
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "<option>선택</option>";
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var manageEntrpsVo = data.result[i];     
        		listHtml += "<option value='"+manageEntrpsVo.me_code+"'>"+manageEntrpsVo.me_nm + "</option>";
        	}
		}else{
			
		}	
		$("#me_code_combo_50103").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: cboChasu_50103
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 차수 콤보
 * @param 
 */
function cboCommon_50103(c_parent_code, obj){
	
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":"0000000010"},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var listHtml = ''; // "<option value='' selected='selected'>선택</option>";
			if(data!=null && data.length > 0){
				for(var i=0; i<data.length; i++){
					var codeVo = data[i];     
	        		listHtml += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
	        	}
			}else{
				
			}	
			obj.html(listHtml);
	    }
	});
}

/**
 * @함수명: chkSameCode_CstmrGroupCode
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 같은 고객그룹코드 코드 유무 확인
 * @param 
 */

function chkSameCode_CstmrGroupCode(cg_code){
	var res = true;
	$.ajax({
		url : "/store/checkCstmrGroupCode",
		data:{"cg_code":cg_code},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false,
	    success : function(data) {
	    	if(data!=null && data.result==null){
	    		res=true;
	    	}else{
				if(data.result.length > 0){
					var cstmrGroupVo = data.result[0];  
					if(cstmrGroupVo.cg_code!=null && cstmrGroupVo.cg_code!=''){
						alert(cstmrGroupVo.cg_code + " : " +cstmrGroupVo.cg_nm + "\n사용중인 코드입니다."); 
						$("#cg_code_50101").focus();
						res=false;
					}else
						res=true;
				}else
					res=true;
			}
	    }
	});
	return res;
}

	/**
	 * @함수명: chkSameCode_ManageEntrpsCode
	 * @작성일: 2015. 09. 30.
	 * @작성자: 최수영
	 * @설명: 같은 관리업체 코드 유무 확인
	 * @param 
	 */

	function chkSameCode_ManageEntrpsCode(me_code){
		var res = true;
		$.ajax({
			url : "/store/checkManageEntrpsCode",
			data:{"me_code":me_code},
		    type : "POST",
		    dataType : "json",
		    cache : false,
		    async: false,
		    success : function(data) {
		    	if(data!=null && data.result==null){
		    		res = true;
		    	}else{
					if(data.result.length > 0){
						var manageEntrpsVo = data.result[0];  
						if(manageEntrpsVo.me_code!=null && manageEntrpsVo.me_code!=''){
							alert(manageEntrpsVo.me_code + " : " +manageEntrpsVo.me_nm + "\n사용중인 코드입니다."); 
							$("#me_code_50102").focus();
							res=false;
						}else
							res=true;
					}else
						res=true;
				}
		    }
		});
		return res;
	}

		
/**
 * @함수명: fnSelectList_50101
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 고객그룹리스트 조회
 * @param 
 */

function fnSelectList_50101(){
	$.ajax({
		url : "/store/cstmrGroupList",
		//data:{"om_code":om_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "";

		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var cstmrGroupVo = data.result[i];     
				listHtml += "<tr>";
        		listHtml += "<td>" + cstmrGroupVo.cg_code + "</td>";
    			listHtml += "<td class='txt_left'><a href='#' onclick=\"fnSelectList_50102('"+cstmrGroupVo.cg_code+"')\";>"+cstmrGroupVo.cg_nm + "</a></td>";  
    			listHtml += "<td>" + cstmrGroupVo.me_cnt + "</td>";
    			listHtml += "<td>";
    			listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_50101('"+cstmrGroupVo.cg_code+"')\";>수정</button>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody50101").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: fnSelectList_50102
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리업체리스트 조회
 * @param 
 */

function fnSelectList_50102(cg_code){
	
	//고객그룹코드
	$("#cg_code_50102").val(cg_code);
	
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "";

		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var manageEntrpsVo = data.result[i];     
				listHtml += "<tr>";
        		listHtml += "<td>" + manageEntrpsVo.me_code + "</td>";
    			listHtml += "<td class='txt_left'><a href='#' onclick=\"fnSelectList_50103('"+manageEntrpsVo.me_code+"')\";>"+manageEntrpsVo.me_nm + "</a></td>";   
    			listHtml += "<td>" + manageEntrpsVo.sm_cnt + "</td>";
    			listHtml += "<td>";
    			listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_50102('"+manageEntrpsVo.me_code+"')\";>수정</button>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody50102").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: fnSelectList_50103
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리업체리스트 조회
 * @param 
 */

function fnSelectList_50103(me_code){
	//관리업체 코드
	$("#me_code_50103").val(me_code);
	
	$.ajax({
		url : "/store/storeList",
		data:{"me_code":me_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "";

		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var storeVo = data.result[i];     
				listHtml += "<tr>";
        		listHtml += "<td>" + storeVo.sm_code + "</td>";
    			listHtml += "<td class='txt_left'>" + storeVo.sm_nm + "</td>";
    			listHtml += "<td>" + storeVo.sm_oper_nm + "</td>";
    			listHtml += "<td>";
    			listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_50103('"+storeVo.sm_code+"')\";>수정</button>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody50103").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: fnUpdate_50101
 * @작성일: 2015. 09. 30
 * @작성자: 최수영
 * @설명: 고객그룹 update
 * @param 
 */
function fnUpdate_50101(cg_code){
	
	$("#title1_50101, #title2_50101").text("고객그룹 수정");
	$("#save_type_50101").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_50101();
	
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_50101").prop("disabled", true);

	//팝업 show
	$("#tempPop50101").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/store/cstmrGroupRow",
		data:{"cg_code":cg_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var cstmrGroupVo = data.result[0];    
				$("#cg_code_50101").val(cstmrGroupVo.cg_code);
				$("#cg_nm_50101").val(cstmrGroupVo.cg_nm);
				$("#cg_memo_50101").val(cstmrGroupVo.cm_memo);
				//$("input:radio[name=use_at]:input[value=" + cstmrGroupVo.use_at + "]").attr("checked", true);
				
				if(cstmrGroupVo.use_at=="Y")
					$("#use_at_y_50101").attr("checked", true);
				else
					$("#use_at_n_50101").attr("checked", true);
				
				$("#cg_nm_50101").focus();
			}else{
				
			}	
	    }
	});	
	
	$("#cg_nm").focus();
}

/**
 * @함수명: fnUpdate_50103
 * @작성일: 2015. 09. 30
 * @작성자: 최수영
 * @설명: 매장관리 update
 * @param 
 */
function fnUpdate_50103(sm_code){
	$("#title1_50103, #title2_50103").text("매장 수정");
	$("#save_type_50103").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_50103();

	//팝업 show
	$("#tempPop50103").show();
	
	//관리지점 콤보 초기화
	cbo1_50103("001");
	//고객그룹 콤보 초기화
	cbo2_50103();
	//차수
	cboCommon_50103('0000000001', $('#sm_odr_50103'));
	
	//해당 로우 조회
	$.ajax({
		url : "/store/storeRow",
		data:{"sm_code":sm_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var storeVo = data.result[0];    
				
				//사원 처리
				if(storeVo.em_no!=''){
					$("#selectItem_em_nm_50103").html("");
					var code = storeVo.em_no;
					var codeNm = storeVo.em_nm;
					var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
					var a = $('<a href="#"/>');
					a.click(function (){
						$(this).parent().remove();
						$("#em_no_50103").val("");
					});
					span.append(a);
					//$("#selectItem_em_nm_50103").append(span);
					$("#selectItem_em_nm_50103").html(span);
				}	
				
				$("#me_code_50102").val(storeVo.me_code);
				$("#me_nm_50102").val(storeVo.me_nm);
				$("#me_memo_50102").val(storeVo.me_memo);

				//관리지점
				$("#om_code_combo_50103").val(storeVo.om_code);
				//고객그룹
				$("#cg_code_combo_50103").val(storeVo.cg_code);
				
				//관리업체 콤보 초기화
				cbo3_50103(storeVo.cg_code);
				//관리업체
				$("#me_code_combo_50103").val(storeVo.me_code);
				
				//매장코드
				$("#sm_code_50103").val(storeVo.sm_code);
				//매장명
				$("#sm_nm_50103").val(storeVo.sm_nm);
				
				//운영여부(폐쇄:N)
				if(storeVo.sm_oper_at=="Y")
					$('input:checkbox[id=chk_sm_oper_at_50103]').attr("checked", false);
				else
					$('input:checkbox[id=chk_sm_oper_at_50103]').attr("checked", true);
				$('#sm_oper_at_50103').val(storeVo.sm_oper_at);
				
				//담당사원 유형
				if(storeVo.se_empl_ty=="0000000006")//고정
					$('input:radio[id=rdo_se_empl_ty_y_50103]').attr("checked", true);
				else //순회
					$('input:radio[id=rdo_se_empl_ty_n_99603]').attr("checked", true);
				
				//담당사원 코드
				$('#em_no_50103').val(storeVo.em_no);
				//담당사원명
				//$('#em_nm_50103').val(storeVo.em_nm);
				//차수
				$('#sm_odr_50103').val(storeVo.sm_odr);
				//사업자번호
				$('#sm_bizrno_50103').val(storeVo.sm_bizrno);
				//상호
				//$('#sm_cmpnm_50103').val(storeVo.sm_cmpnm);
				//대표자명
				$('#sm_rprsntv_nm_50103').val(storeVo.sm_rprsntv_nm);
				//지역
				$('#sm_area_50103').val(storeVo.sm_area);
				//우편번호
				$('#sm_zipcd_50103').val(storeVo.sm_zipcd);
				//주소
				$('#sm_adres_50103').val(storeVo.sm_adres);
				//상세주소
				$('#sm_dtadres_50103').val(storeVo.sm_dtadres);
				//기타주소
				$('#sm_etcadres_50103').val(storeVo.sm_etcadres);
				//위도/경도
				$('#sm_la_50103').val(storeVo.sm_la);
				$('#sm_lo_50103').val(storeVo.sm_lo);
				//웹사이트
				//$('#sm_site_se_50103').val(storeVo.sm_site_se);
				//$('#sm_site_50103').val(storeVo.sm_site);
				//대표전화
				$('#sm_tlphon_50103').val(storeVo.sm_tlphon);
				//팩스번호
				$('#sm_fxnum_50103').val(storeVo.sm_fxnum);
				//메모
				$('#cm_memo_50103').val(storeVo.cm_memo);
				
				
				$("#sm_nm_50103").focus();
			}else{
				
			}	
	    }
	});	
	
	$("#me_nm_50102").focus();
}

/**
 * @함수명: fnUpdate_50102
 * @작성일: 2015. 09. 30
 * @작성자: 최수영
 * @설명: 고객그룹 update
 * @param 
 */
function fnUpdate_50102(me_code){
	$("#title1_50102, #title2_50102").text("관리업체 수정");
	$("#save_type_50102").val("UPDATE");

	//팝업 컨트롤 초기화
	popControlInit_50102();
	
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_50102").prop("disabled", true);

	//팝업 show
	$("#tempPop50102").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/store/manageEntrpsRow",
		data:{"me_code":me_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var manageEntrpsVo = data.result[0];    
				$("#me_code_50102").val(manageEntrpsVo.me_code);
				$("#me_nm_50102").val(manageEntrpsVo.me_nm);
				$("#me_memo_50102").val(manageEntrpsVo.me_memo);
				
				if(manageEntrpsVo.use_at=="Y")
					$("#use_at_y_50102").attr("checked", true);
				else
					$("#use_at_n_50102").attr("checked", true);
				
				$("#me_nm_50102").focus();
			}else{
				
			}	
	    }
	});	
	
	$("#me_nm_50102").focus();
}

//고객그룹SAVE(50101)
function fnSave50101(){
	
	if($("#chkAutoYn_50101").prop("checked")==false){
		if($.trim($('#cg_code_50101').val()) == ''){
			alert("고객그룹코드를 입력하세요");
			$("#cg_code_50101").focus();
			return;
		}else if($('#cg_code_50101').val().length!=5){
			alert("고객그룹코드 자리수를 확인하세요");
			$("#cg_code_50101").focus();
			return;
		}
		
		//코드 확인
		if(!chkSameCode_CstmrGroupCode($('#cg_code_50101').val())){
			return;
		};
		
		$("#autoYn_50101").val("N");
	}else
		$("#autoYn_50101").val("Y");
	
	if($.trim($('#cg_nm_50101').val())==''){
		 alert("고객그룹명을 입력하세요");
		 $("#cg_nm_50101").focus();
		 return;
	}

	var vUrl = "";
	var vType = $('#save_type_50101').val();
	var param = $('#frm50101').serialize();
	
	//alert(param);
	
	 if(vType=="UPDATE")
		 vUrl = "/store/updateCstmrGroup";
	 else
		 vUrl = "/store/insertCstmrGroup";
	 
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
				
				fnSelectList_50101();
				//닫기
				$("#tempClose50101").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

//관리업체SAVE(50102)
function fnSave50102(){
	//코드 자동 등록 
	if($("#chkAutoYn_50102").prop("checked")==false){
		if($.trim($('#me_code_50102').val())==''){
			alert("관리업체 코드를 입력하세요");
			$("#me_code_50102").focus();
			return;
		}
		else if($('#me_code_50102').val().length!=5){
			alert("관리업체 코드 자리수를 확인하세요");
			$("#me_code_50102").focus();
			return;
		}
		
		//코드 확인
		if(!chkSameCode_ManageEntrpsCode($('#me_code_50102').val())){
			return;
		};
		
		$("#autoYn_50102").val("N");
	}
	else
		$("#autoYn_50102").val("Y");
	
	if($.trim($('#me_nm_50102').val()) == ''){
		alert("관리업체명을 입력하세요");
		$("#me_nm_50102").focus();
		return;
	}
	
	
	var vUrl = "";
	var vType = $('#save_type_50102').val();
	var param = $('#frm50102').serialize();
	
	//alert(param);
	 if(vType=="UPDATE")
		 vUrl = "/store/updateManageEntrps";
	 else
		 vUrl = "/store/insertManageEntrps";
	 
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
				fnSelectList_50102($("#cg_code_50102").val());
				
				//닫기
				$("#tempClose50102").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

//매장관리SAVE(50103)
function fnSave50103(){
	
	if($("#om_code_combo_50103").val()==''){
		alert("관리지점을 선택하세요");
		$("#om_code_combo_50103").focus();
		return;
	}
	if($("#cg_code_combo_50103").val()==''){
		alert("고객그룹을 선택하세요");
		$("#cg_code_combo_50103").focus();
		return;
	}
	if($("#me_code_combo_50103").val()==''){
		alert("관리업체 선택하세요");
		$("#me_code_combo_50103").focus();
		return;
	}
	if($("#sm_nm_50103").val()==''){
		alert("매장명을 입력하세요");
		$("#sm_nm_50103").focus();
		return;
	}
	
	//사원처리=============================================
	var emGroup = $("#selectItem_em_nm_50103 span");
	if(emGroup.length> 0){
		var em_no = emGroup.eq(0).attr("data-code");
		alert(em_no);
		$('#em_no_50103').val(em_no);
	}
	//==================================================
	
	var vUrl = "";
	var vType = $('#save_type_50103').val();
	var param = $('#frm50103').serialize();
	//alert(param);
	 if(vType=="UPDATE")
		 vUrl = "/store/updateStore";
	 else
		 vUrl = "/store/insertStore";
	 
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   url  : vUrl,
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
			if(data.dto[0].res_code=='-1' ){
				alert('저장되지 않았습니다.');
				return;
			}else if(data.dto[0].res_code=='0'){
				alert('저장 되었습니다.');
				fnSelectList_50103($("#me_code_50102").val());
				
				//닫기
				$("#tempClose50103").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

function popControlInit_50101(){
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_50101").prop("disabled", false);
	$("#chkAutoYn_50101").prop("checked", true);
	
	$("#cg_code_50101").onlyNum();
	$("#cg_code_50101").val("");
	$("#cg_nm_50101").val("");
	$("#cg_memo_50101").val("");
	$('input:radio[id=use_at_y_50101]').attr("checked", true);
	
	$("#cg_nm_50101").focus();
}

function popControlInit_50102(){
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_50102").prop("disabled", false);
	$("#chkAutoYn_50102").prop("checked", true);
	
	$("#me_code_50102").onlyNum();
	$("#me_code_50102").val("");
	$("#me_nm_50102").val("");
	$("#me_memo_50102").val("");
	$('input:radio[id=use_at_y_50102]').attr("checked", true);
	
	$("#me_nm_50102").focus();
}

function popControlInit_50103(){
	
	/*var optText ="<option>선택</option>";
	$("#om_code_combo_50103").html(optText);
	$("#cg_code_combo_50103").html(optText);
	$("#me_code_combo_50103").html(optText);*/
	
	//관리지점
	$("#om_code_combo_50103").val('');
	//고객그룹
	$("#cg_code_combo_50103").val('');
	//관리업체
	$("#me_code_combo_50103").val('');
	//매장명
	$("#sm_nm_50103").val('');
	
	//운영여부(폐쇄:N)
	$('input:checkbox[id=chk_sm_oper_at_50103]').attr("checked", false);
	$('#sm_oper_at_50103').val('Y');
	
	//담당사원 유형
	$('input:radio[id=rdo_se_empl_ty_y_50103]').attr("checked", true);
	
	//사원 autocomplete
	$("#selectItem_em_nm_50103").html("");
	//담당사원코드
	$('#em_no_50103').val('');
	//담당사원명
	$('#em_nm_50103').val('');
	//차수
	$('#sm_odr_50103').val("1");
	//사업자번호
	$('#sm_bizrno_50103').val('');
	//상호
	//$('#sm_cmpnm_50103').val('');
	//대표자명
	$('#sm_rprsntv_nm_50103').val('');
	//지역
	//$('#sm_area_50103').val(storeVo.sm_area);
	//우편번호
	$('#sm_zipcd_50103').val('');
	//주소
	$('#sm_adres_50103').val('');
	//상세주소
	$('#sm_dtadres_50103').val('');
	//기타주소
	$('#sm_etcadres_50103').val('');
	//위도/경도
	$('#sm_la_50103').val('');
	$('#sm_lo_50103').val('');
	//웹사이트
	//$('#sm_site_se_50103').val('http');
	//$('#sm_site_50103').val('');
	//대표전화
	$('#sm_tlphon_50103').val('');
	//팩스번호
	$('#sm_fxnum_50103').val('');
	//메모
	$('#cm_memo_50103').val('');
	
	
	$("#sm_nm_50103").focus();
}


function fnMakeAutoComplete(){
	
    $("#em_nm_50103").autocomplete({
    	serviceUrl:'/store/emplAutoComplate',
    	params: {se_empl_ty : $('input:radio[name="se_empl_ty"]:checked').val()},
        minChars:1,
        paramName: 'keyword',
        zIndex: 9999,
        noCache: true, 
        onSelect: function(result){ 
        	var code = result.key;
        	var codeNm = result.value;
	   		var appendCheck = $("#mart > span[data-code='"+code+"']").length;
	   		if(code != "" && appendCheck == 0){
	   			var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
	   			var a = $('<a href="#" />');
	   			a.click(function(){
	   				$(this).parent().remove();
	   				$("#em_no_50103").val("");
	   			});
	   			span.append(a);
	   			//$("#selectItem_em_nm_50103").append(span);
	   			$("#selectItem_em_nm_50103").html(span);
	   		}
	   		$(this).val("");
        }
    });
   
}
