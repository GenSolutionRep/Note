<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String typeCode = (String) request.getAttribute("typeCode");
System.out.println("typeCode=" + typeCode);
%>
<script>
/**************************************************************************************************
 * @파일명: 50-100js.jsp
 * @작성일: 2015.10.01
 * @작성자: 최수영
 * @설명: 매장관리 JS를 담고있는 JSP
**************************************************************************************************/

/**
 * @함수명: ready
 * @작성일: 2015. 09. 30
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
 var v_om_code_${typeCode}='';
 var storePg_${typeCode} = 1;
 var storeAllPg_${typeCode} = 1;
 
 //다음 좌표 배치용
//  var arr_type_code = [];
 var numCnt=0;
 
$(document).ready(function(){
	
	v_om_code_${typeCode} = '${typeCode}';
	
	//팝업창등록(필수)
	$("#tempPop${typeCode}1, #tempPop${typeCode}2, #tempPop${typeCode}3, #zipcdPop${typeCode}3, #tempPop${typeCode}4").instancePopUp();
	//$("#zipcdPop${typeCode}3").dragable();

	
	fnSelectList_${typeCode}1();
	
	//고객그룹 조회
	if($("#ul${typeCode}1 li").length > 0){
		$('#ul${typeCode}1 li:eq(0)').addClass('cell_active');
		
		//$("#hTitle${typeCode}2").text($("#table${typeCode}1 ul li:eq(0) div:nth-child(2)").text() + " > 관리업체");
		//fnSelectList_${typeCode}2($("#table${typeCode}1 ul li:eq(0) div:nth-child(1)").text());
		fnSelectList_${typeCode}2($("#ul${typeCode}1 li:eq(0) div:eq(0)").text());
	}
	
	if($("#ul${typeCode}2 li").length > 0){
		$('#ul${typeCode}2 li:eq(0)').addClass('cell_active');
		
		//관리업체
		//$("#hTitle${typeCode}3").text($("#table${typeCode}2 ul li:eq(0) div:nth-child(2)").text() + " > 매장");
		fnSelectList_${typeCode}3($("#ul${typeCode}1 li:eq(0) div:eq(0)").text(),
								  $("#ul${typeCode}2 li:eq(0) div:eq(0)").text());
	}
	
	if($("#ul${typeCode}3 li").length > 0){
		$('#ul${typeCode}3 li:eq(0)').addClass('cell_active');
	}
	
	// 이벤트 등록
	fnSetEventComponent50100();
	
	//사원명 AutoComplete
	fnMakeAutoComplete_50100();
	
	if(v_om_code_${typeCode}=='05262'){
		$('#btnJuso_${typeCode}').css('display', '');
	}
});




/**
 * @함수명: fnSetEventComponent50100
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent50100(){
	 fnJuso_${typeCode}();
	 
	 $("#btnExcel_${typeCode}4").click(function(){
		 var params ="";
		 params="om_code=" + v_om_code_${typeCode};
		 params+="&cg_nm=" + $('#cg_nm_search_${typeCode}4').val();
		 params+="&me_nm=" + $('#me_nm_search_${typeCode}4').val();
		 params+="&sm_nm=" + $('#sm_nm_search_${typeCode}4').val();
		 params+="&em_nm=" + $('#em_nm_search_${typeCode}4').val();
		 params+="&se_empl_ty=" + $('#se_empl_ty_search_${typeCode}4').val();
		 params+="&sm_odr=" + $('#sm_odr_search_${typeCode}4').val();
		 params+="&sm_cvscafe_at=" + $('#chk_search_sm_oper_at_${typeCode}4').data("value");
		 
		 var address = "/store/excelExport?" + params;
		  location.href = address;
		   
		   /* $.ajax({
			   	global : true,
				url : address,
				//data:{"cm_code":cm_code},
			    type : "GET",
			    //dataType : "json",
			    cache : false,
			    async : false,
			    success : function(data){
			    	alert("다운로드 완료하였습니다.");
			    }
			}); */
		   
		   
		   
	 });

	/*
	$('#tbody${typeCode}1 tr td').click(function(){
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
	
    /*var tr = $('#table${typeCode}1').find('tr');
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
    
	$("#ul${typeCode}1 li").click(function(){		
		$('#ul${typeCode}1').find('li').removeClass('cell_active');
		$(this).addClass('cell_active');
		
		var idx = $("#ul${typeCode}1 li").index(this);
		
		//관리업체 타이틀 
		//$("#hTitle${typeCode}2").text($("#ul${typeCode}1 tbody tr:eq("+idx+") td:nth-child(2)").text() + " > 관리업체");
		fnSelectList_${typeCode}2($("#ul${typeCode}1 li:eq("+idx+") div:eq(0)").text());
		
		if($("#ul${typeCode}2 li").length > 0){
			$('#ul${typeCode}2 li:eq(0)').addClass('cell_active');
			
			tableTr02Set(idx);
			tableTr03Set();
			
			//매장 타이틀
			//$("#hTitle${typeCode}3").text($("#table${typeCode}2 tbody tr:eq(0) td:nth-child(2)").text() + " > 매장");
			fnSelectList_${typeCode}3($("#ul${typeCode}1 li:eq("+idx+") div:eq(0)").text(), 
									  $("#ul${typeCode}2 li:eq(0) div:eq(0)").text());
		}else{
			//매장 타이틀
			//$("#hTitle${typeCode}3").text(" > 매장");
			$("#tbody${typeCode}3").html("");
		}
		
		if($("#ul${typeCode}3 li").length > 0){
			$('#ul${typeCode}3 li(0)').addClass('cell_active');
		}
	});
	
	function tableTr02Set(idx_1){
		$("#ul${typeCode}2 li").click(function(){	
			
			$('#ul${typeCode}2').find('li').removeClass('cell_active');
			$(this).addClass('cell_active');
			var idx1 = $("#ul${typeCode}2 li").index(this);
	
			tableTr03Set();
			
			//매장 타이틀 
			//$("#hTitle${typeCode}3").text($("#table${typeCode}2 tbody tr:eq("+idx+") td:nth-child(2)").text() + " > 매장");
			fnSelectList_${typeCode}3(  $("#ul${typeCode}1 li:eq("+idx_1+") div:eq(0)").text(),
										$("#ul${typeCode}2 li:eq("+idx1+") div:eq(0)").text());
			
			if($("#ul${typeCode}3 li").length > 0){
				$('#ul${typeCode}3 li:eq(0)').addClass('cell_active');
			}
		});
	}
	
	$("#ul${typeCode}3 li").click(function(){		
		$('#ul${typeCode}3').find('li').removeClass('cell_active');
		$(this).addClass('cell_active');
	});
	
	function tableTr03Set(){
		$("#ul${typeCode}3 li").click(function(){		
			$('#ul${typeCode}3').find('li').removeClass('cell_active');
			$(this).addClass('cell_active');
		});
	}


	//주소로 좌표 알아오기
	$('#getCoordinateBtn${typeCode}3').click(function(){
		
		/* if($('#sm_zipcd_${typeCode}3').val()==''){
			alert("주소를 검색하십시오");
			return;
		} */
		if($('#sm_adres_${typeCode}3').val()==''){
			alert("주소를 검색하십시오");
			return;
		}
		/* if($('#sm_etcadres_${typeCode}3').val()==''){
			alert("주소를 검색하십시오");
			return;
		} */
		
		var addrTxt = "";
		
		
		addrTxt = //$('#sm_zipcd_${typeCode}3').val() + " " + 
				 $('#sm_adres_${typeCode}3').val() + " "+ $('#sm_etcadres_${typeCode}3').val();//+ " " + $('#sm_dtadres_${typeCode}3').val();
		addrTxt = $.trim(addrTxt);
		/* addrTxt = addrTxt.replace("("," ");
		addrTxt = addrTxt.replace(")",""); */
		
		/*
		//Google
		//alert(addrTxt);
		$.ajax({
			url : "http://maps.googleapis.com/maps/api/geocode/json",
			data:{"address": addrTxt, language:"ko", sensor:false},
		    type : "get",
		    dataType : "json",
		    cache : false,
		    async : false,
		    success : function(data) {
				if(data!=null&& data.results.length>0){
					$('#sm_la_${typeCode}3').val(data.results[0].geometry.location.lat);
					$('#sm_lo_${typeCode}3').val(data.results[0].geometry.location.lng);
				}
		    }
		});
		*/
		
		//DAUM***************************************************************
		$.ajax({
			url : "https://apis.daum.net/local/geo/addr2coord",
			data:{output:"json", q: addrTxt, apikey: "6620dde9d3a1dde771f78dff247387d6"},
		    type : "get",
		    dataType : "jsonp",
		    jsonp : "callback",
		    cache : false,
		    //async : false,
		    success : function(data) {
				 if(data!=null&& data.channel.item.length>0){
					$('#sm_la_${typeCode}3').val(data.channel.item[0].lat);
					$('#sm_lo_${typeCode}3').val(data.channel.item[0].lng);
				} 
				
		    }
		    ,error : function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.status);
			}
		});				
		
	});
	
	//우편번호
	$('#addrSearchPopBtn${typeCode}3').click(function(){
		$postOption.insertPostcode5 = "#sm_zipcd_${typeCode}3";
		//주소
		$postOption.insertAddress = "#sm_adres_${typeCode}3";
		//상세주소
		$postOption.insertExtraInfo = "#sm_etcadres_${typeCode}3";
		//기타주소
		$postOption.insertDetails = "#sm_dtadres_${typeCode}3";
					
		$("#zipcdPop${typeCode}3").show();
	});
	
	$("#addrSearch${typeCode}3").postcodify({
		controls : "#addrSearchControl${typeCode}3" ,
		afterSelect : function(selectedEntry) { 
			$("#addrSearch${typeCode}3").html("");
			$(".keyword").val("");
			$("#zipcdPop${typeCode}3").hide();
		},
		hideOldAddresses : false ,
		mapLinkProvider : 'daum'
	});
	
	$('#zipcodePopCloseBtn${typeCode}3').click(function(){
		$("#addrSearch${typeCode}3").html("");
		$(".keyword").val("");
		$("#zipcdPop${typeCode}3").hide();
	});
	
	
	
	$("#chkAutoYn_${typeCode}1").click(function(){
		//코드 자동 등록 
		if($(this).prop("checked")){
			$("input:text[id=cg_code_${typeCode}1]").attr("readonly", true);
			$("#cg_code_${typeCode}1").val("");
			$("#autoYn_${typeCode}1").val("Y");
		}else{
			$("input:text[id=cg_code_${typeCode}1]").attr("readonly", false);
			$("#cg_code_${typeCode}1").focus();
			$("#autoYn_${typeCode}1").val("N");
		}
	});
	
	$("#chkAutoYn_${typeCode}2").click(function(){
		//코드 자동 등록 
		if($(this).prop("checked")){
			$("input:text[id=me_code_${typeCode}2]").attr("readonly", true);
			$("#me_code_${typeCode}2").val("");
			$("#autoYn_${typeCode}2").val("Y");
		}else{
			$("input:text[id=me_code_${typeCode}2]").attr("readonly", false);
			$("#me_code_${typeCode}2").focus();
			$("#autoYn_${typeCode}2").val("N");
		}
	});
	
	$("#chk_sm_oper_at_${typeCode}3").click(function(){
		//폐쇠여부
		if($(this).prop("checked")){
			$("#sm_oper_at_${typeCode}3").val("N");
		}else{
			$("#sm_oper_at_${typeCode}3").val("Y");
		}
	});
	
	//cvs cafe 여부
	$("#chk_sm_cvscafe_at_${typeCode}3").click(function(){
		if($(this).prop("checked")){
			$("#sm_cvscafe_at_${typeCode}3").val("Y");
		}else{
			$("#sm_cvscafe_at_${typeCode}3").val("N");
		}
	});
	
	//고객그룹 팝업 ==================================================
	$("#tempInsert${typeCode}1").click(function(){
		$("#title1_${typeCode}1, #title2_${typeCode}1").text("고객그룹 등록");

		//팝업 컨트롤 초기화
		popControlInit_${typeCode}1();

		$("#tempPop${typeCode}1").show();
		$("#save_type_${typeCode}1").val("INSERT");
		$("#cg_nm_${typeCode}1").focus();
	});
	
	$("#tempClose${typeCode}1, #tempCloseX${typeCode}1").click(function(){
		$("#tempPop${typeCode}1").hide();
	});

	//고객그룹 저장버튼
	$("#tempSave${typeCode}1").click(function(){
		fnSave${typeCode}1();
	});
	
	
	//관리업체 팝업===================================================
	$("#tempInsert${typeCode}2").click(function(){
		$("#title1_${typeCode}2, #title2_${typeCode}2").text("관리업체 등록");
		
		//팝업 컨트롤 초기화
		popControlInit_${typeCode}2();
		
		$("#save_type_${typeCode}2").val("INSERT");
		$("#tempPop${typeCode}2").show();
		$("#me_nm_${typeCode}2").focus();
	});
	$("#tempClose${typeCode}2, #tempCloseX${typeCode}2").click(function(){
		$("#tempPop${typeCode}2").hide();
	});
	
	//관리업체 저장버튼
	$("#tempSave${typeCode}2").click(function(){
		fnSave${typeCode}2();
	});
	
	
	//매장 팝업======================================================
	$("#tempInsert${typeCode}3").click(function(){
		
		//팝업 컨트롤 초기화
		popControlInit_${typeCode}3();
				
		$("#title1_${typeCode}3, #title2_${typeCode}3").text("매장 등록");
		$("#save_type_${typeCode}3").val("INSERT");

		var optText = "<option value=''>선택</option>";
		$("#me_code_combo_${typeCode}3").html(optText);
		
		//관리지점 콤보 초기화
		cbo1_${typeCode}3("001");
		
		//관리지점 값 set
		$('#om_code_combo_${typeCode}3').val('${typeCode}');
		
		//고객그룹 콤보 초기화
		cbo2_${typeCode}3();
		
		//차수
		cboCommon_${typeCode}3('0000000010', $('#sm_odr_${typeCode}3'));
		
		//권역1
		cboCommon_${typeCode}3('0000000107', $('#sm_area1_combo_${typeCode}3'));
		
		//권역2
		$("#sm_area2_combo_${typeCode}3").html(optText); //<option>선택</option>;
		
		//자동완성
		fnMakeAutoComplete_50100();
		
		$("#tempPop${typeCode}3").show();
	});
	
	$("#tempClose${typeCode}3, #tempCloseX${typeCode}3").click(function(){
		$("#tempPop${typeCode}3").hide();
	});
	
	//관리지점
	/*
	$("#om_code_combo_${typeCode}3").change(function(){
		$("#cg_code_combo_${typeCode}3, #me_code_combo_${typeCode}3").html("<option>선택</option>");
		
		if($(this).val()==''){
			$("#em_nm_${typeCode}3").val("");
		}else{
			cbo2_${typeCode}3();			
		}
	});
	*/
	
	//담당사원유형
	$("input:radio[name=se_empl_ty]:").change(function(){
		$("#em_no_${typeCode}3, #em_nm_${typeCode}3").val("");
		$("#selectItem_em_nm_${typeCode}3").html("");
		
		$("#em_no_sub_${typeCode}3, #em_nm_sub_${typeCode}3").val("");
		$("#selectItem_em_nm_sub_${typeCode}3").html("");
		
    	if($(this).val()=='0000000006'){
    		$("input:text[id=em_nm_sub_${typeCode}3]").prop("readonly", false);
    	}else{
    		$("input:text[id=em_nm_sub_${typeCode}3]").prop("readonly", true);
    	}

   		fnMakeAutoComplete_50100();
	});
	
	//고객그룹
	$("#cg_code_combo_${typeCode}3").change(function(){
		$("#me_code_combo_${typeCode}3").html("<option>선택</option>");
		
		if($(this).val()==''){
			$("#em_nm_${typeCode}3").val("");
		}else{
			cbo3_${typeCode}3($(this).val());
		}
	});
	
	//관리업체
	$("#me_code_combo_${typeCode}3").change(function(){
		if($(this).val()!=''){
			$("#em_nm_${typeCode}3").val("");
		}
	});
	
	//매장 저장버튼
	$("#tempSave${typeCode}3").click(function(){
		fnSave${typeCode}3();
	});
	
	//매장 전체목록 show
	$("#tempSearch${typeCode}3").click(function(){
		//초기화
		fnInitPopupStoreAll_${typeCode}4();
		
		$("#tempPop${typeCode}4").show();
		
		//조회
		fnSelectList_${typeCode}4(1);
	});
	
	//매장 전체목록 조회 **************************************************************
	$("#btnSearch_${typeCode}4").click(function(){
		//조회
		fnSelectList_${typeCode}4(1);
	});

	$("#btnClear_${typeCode}4").click(function(){
		//초기화
		fnInitPopupStoreAll_${typeCode}4();
		//조회
		fnSelectList_${typeCode}4(1);
	});
	
	
	//매장전체목록 닫기
	$("#tempClose${typeCode}4, #tempCloseX${typeCode}4").click(function(){
		$("#tempPop${typeCode}4").hide();
	});
	
	
	//cvs cafe 여부
	$("#chk_search_sm_oper_at_${typeCode}4").click(function(){
		if($(this).prop("checked")){
			$(this).data("value", "Y");
		}else{
			$(this).data("value", "N");
		}
	});
	
	//권역콤보1
	$("#sm_area1_combo_${typeCode}3").change(function(){
		if($(this).val()==''){
			$("#sm_area2_combo_${typeCode}3").html("<option value=''>선택</option>");
		}else{
			//권역2
			cboCommon_${typeCode}3($(this).val(), $('#sm_area2_combo_${typeCode}3'));
		}
	});
}

/**
 * @함수명: cbo1_${typeCode}3
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리지점 콤보 리스트
 * @param 
 */

function cbo1_${typeCode}3(cm_code){
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
		$("#om_code_combo_${typeCode}3").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: cbo2_${typeCode}3
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 고객그룹명 콤보 리스트
 * @param 
 */

function cbo2_${typeCode}3(){
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
		$("#cg_code_combo_${typeCode}3").html(listHtml);
	    
	    }
	});
}
/**
 * @함수명: cbo3_${typeCode}3
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리업체명
 * @param 
 */
function cbo3_${typeCode}3(cg_code){
	
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code, "om_code": v_om_code_${typeCode}},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "<option value='' selected='selected'>선택</option>";
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var manageEntrpsVo = data.result[i];     
        		listHtml += "<option value='"+manageEntrpsVo.me_code+"'>"+manageEntrpsVo.me_nm + "</option>";
        	}
		}else{
			
		}	
		$("#me_code_combo_${typeCode}3").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: cboChasu_${typeCode}3
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 공통코드 콤보
 * @param 
 */
function cboCommon_${typeCode}3(c_parent_code, obj){
	
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":c_parent_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var listHtml = "<option value='' selected='selected'>선택</option>";
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
						$("#cg_code_${typeCode}1").focus();
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

	function chkSameCode_ManageEntrpsCode(cg_code, me_code){
		var res = true;
		$.ajax({
			url : "/store/checkManageEntrpsCode",
			data:{"cg_code":cg_code, "me_code":me_code},
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
							$("#me_code_${typeCode}2").focus();
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
 * @함수명: fnSelectList_${typeCode}1
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 고객그룹리스트 조회
 * @param 
 */

function fnSelectList_${typeCode}1(){
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
				listHtml += "<ul id='ul${typeCode}1'>";
				for(var i=0; i<data.result.length; i++){
					var cstmrGroupVo = data.result[i];  
	    			listHtml += "<li>";
	    			listHtml += "<div class='flex1'>"+ cstmrGroupVo.cg_code +"</div>";
	    			listHtml += "<div class='flex2 txt_left'><a href='#' onclick=\"fnSelectList_${typeCode}2('"+cstmrGroupVo.cg_code+"')\";>"+cstmrGroupVo.cg_nm + "</a></div>";
	    			listHtml += "<div class='flex1'>"+ cstmrGroupVo.me_cnt +"</div>";
	    			listHtml += "<div class='flex1'>"+ cstmrGroupVo.use_at +"</div>";
	    			listHtml += "<div class='flex_100'>";
	    			listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_${typeCode}1('"+cstmrGroupVo.cg_code+"')\";>수정</button>";
	    			listHtml += "</div>";
	    			listHtml += "</li>";
	        	}
				listHtml += "</ul>";
			}else{
				
			}	
			$("#tbody${typeCode}1").html(listHtml);
			
	    }
	});
}

/**
 * @함수명: fnSelectList_${typeCode}2
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리업체리스트 조회
 * @param 
 */

function fnSelectList_${typeCode}2(cg_code){
	
	//고객그룹코드
	$("#cg_code_${typeCode}2").val(cg_code);
	
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code, "om_code": v_om_code_${typeCode}},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "";

		if(data!=null && data.result.length > 0){
			listHtml += "<ul id='ul${typeCode}2'>";
			for(var i=0; i<data.result.length; i++){
				var manageEntrpsVo = data.result[i];  
    			listHtml += "<li>";
    			listHtml += "<div class='flex1'>"+ manageEntrpsVo.me_code +"</div>";
    			listHtml += "<div class='flex2 txt_left'><a href='#' onclick=\"fnSelectList_${typeCode}3('"+manageEntrpsVo.cg_code+"','"+manageEntrpsVo.me_code+"')\";>"+manageEntrpsVo.me_nm + "</a></div>";
    			listHtml += "<div class='flex1'>"+ manageEntrpsVo.sm_cnt +"</div>";
    			listHtml += "<div class='flex1'>"+ manageEntrpsVo.use_at +"</div>";
    			listHtml += "<div class='flex_100'>";
    			listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_${typeCode}2('"+manageEntrpsVo.cg_code+"','"+manageEntrpsVo.me_code+"')\";>수정</button>";
    			listHtml += "</div>";
    			listHtml += "</li>";
        	}
			listHtml += "</ul>";
		}else{
			
		}	
		$("#tbody${typeCode}2").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: fnSelectList_${typeCode}3
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 관리업체리스트 조회
 * @param 
 */

function fnSelectList_${typeCode}3(cg_code, me_code){
	//alert(me_code);
	//관리업체 코드
	$("#me_code_${typeCode}3").val(me_code);
	
	$.ajax({
		url : "/store/storeList",
		data:{"cg_code":cg_code, "me_code":me_code, "om_code": v_om_code_${typeCode}},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
			var listHtml = "<ul id='ul${typeCode}3'>";
			
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var storeVo = data.result[i];     
					listHtml += "<li>";
					listHtml += "<div class='flex1'>"+ storeVo.sm_sap_code +"</div>";
					listHtml += "<div class='flex2 txt_left'>" + storeVo.sm_nm + "</div>";
					listHtml += "<div class='flex1'>" + storeVo.sm_oper_nm + "</div>";
					listHtml += "<div class='flex_100'>";
					listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_${typeCode}3('"+storeVo.sm_code+"')\";>수정</button>";
					listHtml += "</div>";
					listHtml += "</li>";
	        	}
				listHtml += "</ul>";
			}else{
				
			}	
			
			$("#tbody${typeCode}3").html(listHtml);
	    }
	});
}

/**
 * @함수명: fnUpdate_${typeCode}1
 * @작성일: 2015. 09. 30
 * @작성자: 최수영
 * @설명: 고객그룹 update
 * @param 
 */
function fnUpdate_${typeCode}1(cg_code){
	
	$("#title1_${typeCode}1, #title2_${typeCode}1").text("고객그룹 수정");
	$("#save_type_${typeCode}1").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_${typeCode}1();
	
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_${typeCode}1").prop("disabled", true);

	//팝업 show
	$("#tempPop${typeCode}1").show();
	
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
				$("#cg_code_${typeCode}1").val(cstmrGroupVo.cg_code);
				$("#cg_nm_${typeCode}1").val(cstmrGroupVo.cg_nm);
				$("#cg_memo_${typeCode}1").val(cstmrGroupVo.cm_memo);
				//$("input:radio[name=use_at]:input[value=" + cstmrGroupVo.use_at + "]").attr("checked", true);
				
				if(cstmrGroupVo.use_at=="Y")
					$("#use_at_y_${typeCode}1").attr("checked", true);
				else
					$("#use_at_n_${typeCode}1").attr("checked", true);
				
				$("#cg_nm_${typeCode}1").focus();
			}else{
				
			}	
	    }
	});	
	
	$("#cg_nm").focus();
}

/**
 * @함수명: fnUpdate_${typeCode}3
 * @작성일: 2015. 09. 30
 * @작성자: 최수영
 * @설명: 매장관리 update
 * @param 
 */
function fnUpdate_${typeCode}3(sm_code){
	$("#title1_${typeCode}3, #title2_${typeCode}3").text("매장 수정");
	$("#save_type_${typeCode}3").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_${typeCode}3();

	//팝업 show
	$("#tempPop${typeCode}3").show();
	
	//관리지점 콤보 초기화
	cbo1_${typeCode}3("001");
	//고객그룹 콤보 초기화
	cbo2_${typeCode}3();
	//차수
	cboCommon_${typeCode}3('0000000010', $('#sm_odr_${typeCode}3'));
	
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
					$("#selectItem_em_nm_${typeCode}3").html("");
					var code = storeVo.em_no;
					var codeNm = storeVo.em_nm;
					var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
					var a = $('<a href="#"/>');
					a.click(function (){
						$(this).parent().remove();
						$("#em_no_${typeCode}3").val("");
					});
					span.append(a);
					//$("#selectItem_em_nm_${typeCode}3").append(span);
					$("#selectItem_em_nm_${typeCode}3").html(span);
				}	
				
				//보조사원 처리
				if(storeVo.em_no_sub!=''){
					$("#selectItem_em_nm_sub_${typeCode}3").html("");
					var code = storeVo.em_no_sub;
					var codeNm = storeVo.em_nm_sub;
					var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
					var a = $('<a href="#"/>');
					a.click(function (){
						$(this).parent().remove();
						$("#em_no_sub_${typeCode}3").val("");
					});
					span.append(a);
					//$("#selectItem_em_nm_${typeCode}3").append(span);
					$("#selectItem_em_nm_sub_${typeCode}3").html(span);
				}
				//매장SAP코드
				$("#sm_sap_code_${typeCode}3").val(storeVo.sm_sap_code);
				$("#me_code_${typeCode}2").val(storeVo.me_code);
				$("#me_nm_${typeCode}2").val(storeVo.me_nm);
				$("#me_memo_${typeCode}2").val(storeVo.me_memo);

				//관리지점
				$("#om_code_combo_${typeCode}3").val(storeVo.om_code);
				//고객그룹
				$("#cg_code_combo_${typeCode}3").val(storeVo.cg_code);
				
				//관리업체 콤보 초기화
				cbo3_${typeCode}3(storeVo.cg_code);
				//관리업체
				$("#me_code_combo_${typeCode}3").val(storeVo.me_code);
				
				//매장코드
				$("#sm_code_${typeCode}3").val(storeVo.sm_code);
				//매장명
				$("#sm_nm_${typeCode}3").val(storeVo.sm_nm);
				
				//운영여부(폐쇄:N)
				if(storeVo.sm_oper_at=="Y")
					$('input:checkbox[id=chk_sm_oper_at_${typeCode}3]').attr("checked", false);
				else
					$('input:checkbox[id=chk_sm_oper_at_${typeCode}3]').attr("checked", true);
				$('#sm_oper_at_${typeCode}3').val(storeVo.sm_oper_at);
				
				//담당사원 유형
				if(storeVo.se_empl_ty=='' || storeVo.se_empl_ty=="0000000006")//고정
					$('input:radio[id=rdo_se_empl_ty_y_${typeCode}3]').attr("checked", true);
				else //순회
					$('input:radio[id=rdo_se_empl_ty_n_${typeCode}3]').attr("checked", true);
				
				//cvs cafe 여부
				if(storeVo.sm_cvscafe_at=="Y")
					$('input:checkbox[id=chk_sm_cvscafe_at_${typeCode}3]').prop("checked", true);
				else
					$('input:checkbox[id=chk_sm_cvscafe_at_${typeCode}3]').prop("checked", false);
				$('#sm_cvscafe_at_${typeCode}3').val(storeVo.sm_cvscafe_at);
				
				
				
				//담당사원 코드
				$('#em_no_${typeCode}3').val(storeVo.em_no);
				
				//보조사원 코드
				$('#em_no_sub_${typeCode}3').val(storeVo.em_no_sub);
				
				//담당사원명
				//$('#em_nm_${typeCode}3').val(storeVo.em_nm);
				//차수
				$('#sm_odr_${typeCode}3').val(storeVo.sm_odr);
				//사업자번호
				$('#sm_bizrno_${typeCode}3').val(storeVo.sm_bizrno);
				//상호
				//$('#sm_cmpnm_${typeCode}3').val(storeVo.sm_cmpnm);
				//대표자명
				$('#sm_rprsntv_nm_${typeCode}3').val(storeVo.sm_rprsntv_nm);
				//지역
				$('#sm_area_${typeCode}3').val(storeVo.sm_area);
				//우편번호
				$('#sm_zipcd_${typeCode}3').val(storeVo.sm_zipcd);
				//주소
				$('#sm_adres_${typeCode}3').val(storeVo.sm_adres);
				//상세주소
				$('#sm_dtadres_${typeCode}3').val(storeVo.sm_dtadres);
				//기타주소
				$('#sm_etcadres_${typeCode}3').val(storeVo.sm_etcadres);
				//위도/경도
				$('#sm_la_${typeCode}3').val(storeVo.sm_la);
				$('#sm_lo_${typeCode}3').val(storeVo.sm_lo);
				
				//권역1
				cboCommon_${typeCode}3("0000000107", $('#sm_area1_combo_${typeCode}3'));
				$('#sm_area1_combo_${typeCode}3').val(storeVo.sm_area1);
				
				//권역2
				cboCommon_${typeCode}3(storeVo.sm_area1, $('#sm_area2_combo_${typeCode}3'));
				$('#sm_area2_combo_${typeCode}3').val(storeVo.sm_area2);
				
				/* if(storeVo.sm_area2==''){
					$("#sm_area2_combo_${typeCode}3").html("<option value=''>선택</option>");
				}else{
					cboCommon_${typeCode}3(storeVo.sm_area1, $('#sm_area2_combo_${typeCode}3'));
				} */
				
				//웹사이트
				//$('#sm_site_se_${typeCode}3').val(storeVo.sm_site_se);
				//$('#sm_site_${typeCode}3').val(storeVo.sm_site);
				//대표전화
				$('#sm_tlphon_${typeCode}3').val(storeVo.sm_tlphon);
				//팩스번호
				$('#sm_fxnum_${typeCode}3').val(storeVo.sm_fxnum);
				//메모
				$('#sm_memo_${typeCode}3').val(storeVo.sm_memo);
				
				//자동완성
				fnMakeAutoComplete_50100();
				
				$("#sm_nm_${typeCode}3").focus();
			}else{
				
			}	
	    }
	});	
	
	$("#me_nm_${typeCode}2").focus();
}

/**
 * @함수명: fnUpdate_${typeCode}2
 * @작성일: 2015. 09. 30
 * @작성자: 최수영
 * @설명: 고객그룹 update
 * @param 
 */
function fnUpdate_${typeCode}2(cg_code, me_code){
	$("#title1_${typeCode}2, #title2_${typeCode}2").text("관리업체 수정");
	$("#save_type_${typeCode}2").val("UPDATE");

	//팝업 컨트롤 초기화
	popControlInit_${typeCode}2();
	
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_${typeCode}2").prop("disabled", true);

	//팝업 show
	$("#tempPop${typeCode}2").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/store/manageEntrpsRow",
		data:{"cg_code":cg_code, "me_code":me_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var manageEntrpsVo = data.result[0];    
				$("#me_code_${typeCode}2").val(manageEntrpsVo.me_code);
				$("#me_nm_${typeCode}2").val(manageEntrpsVo.me_nm);
				$("#me_memo_${typeCode}2").val(manageEntrpsVo.me_memo);
				
				if(manageEntrpsVo.use_at=="Y")
					$("#use_at_y_${typeCode}2").attr("checked", true);
				else
					$("#use_at_n_${typeCode}2").attr("checked", true);
				
				$("#me_nm_${typeCode}2").focus();
			}else{
				
			}	
	    }
	});	
	
	$("#me_nm_${typeCode}2").focus();
}

//고객그룹SAVE(${typeCode}1)
function fnSave${typeCode}1(){
	
	if($("#chkAutoYn_${typeCode}1").prop("checked")==false){
		if($.trim($('#cg_code_${typeCode}1').val()) == ''){
			alert("고객그룹코드를 입력하세요");
			$("#cg_code_${typeCode}1").focus();
			return;
		}else if($.trim($('#cg_code_${typeCode}1').val()).length>5){
			alert("고객그룹코드 자리수를 확인하세요");
			$("#cg_code_${typeCode}1").focus();
			return;
		}
		
		//코드 확인
		if(!chkSameCode_CstmrGroupCode($('#cg_code_${typeCode}1').val())){
			return;
		};
		
		$("#autoYn_${typeCode}1").val("N");
	}else
		$("#autoYn_${typeCode}1").val("Y");
	
	if($.trim($('#cg_nm_${typeCode}1').val())==''){
		 alert("고객그룹명을 입력하세요");
		 $("#cg_nm_${typeCode}1").focus();
		 return;
	}
	
	var vUrl = "";
	var vType = $('#save_type_${typeCode}1').val();
	var param = $('#frm${typeCode}1').serialize();

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
				
				fnSelectList_${typeCode}1();
				//닫기
				$("#tempClose${typeCode}1").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

//관리업체SAVE(${typeCode}2)
function fnSave${typeCode}2(){
	//코드 자동 등록 
	if($("#chkAutoYn_${typeCode}2").prop("checked")==false){
		if($.trim($('#me_code_${typeCode}2').val())==''){
			alert("관리업체 코드를 입력하세요");
			$("#me_code_${typeCode}2").focus();
			return;
		}
		else if($('#me_code_${typeCode}2').val().length!=5){
			alert("관리업체 코드 자리수를 확인하세요");
			$("#me_code_${typeCode}2").focus();
			return;
		}
		
		//코드 확인
		if(!chkSameCode_ManageEntrpsCode($('#cg_code_${typeCode}2').val(), 
										$('#me_code_${typeCode}2').val())){
			return;
		};
		
		$("#autoYn_${typeCode}2").val("N");
	}
	else
		$("#autoYn_${typeCode}2").val("Y");
	
	if($.trim($('#me_nm_${typeCode}2').val()) == ''){
		alert("관리업체명을 입력하세요");
		$("#me_nm_${typeCode}2").focus();
		return;
	}
	
	
	var vUrl = "";
	var vType = $('#save_type_${typeCode}2').val();
	var param = $('#frm${typeCode}2').serialize();
	
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
				fnSelectList_${typeCode}2($("#cg_code_${typeCode}2").val());
				
				//닫기
				$("#tempClose${typeCode}2").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

//매장관리SAVE(${typeCode}3)
function fnSave${typeCode}3(){
	
	if($("#om_code_combo_${typeCode}3").val()==''){
		alert("관리지점을 선택하세요");
		$("#om_code_combo_${typeCode}3").focus();
		return;
	}
	if($("#cg_code_combo_${typeCode}3").val()==''){
		alert("고객그룹을 선택하세요");
		$("#cg_code_combo_${typeCode}3").focus();
		return;
	}
	if($("#me_code_combo_${typeCode}3").val()==''){
		alert("관리업체 선택하세요");
		$("#me_code_combo_${typeCode}3").focus();
		return;
	}
	if($("#sm_nm_${typeCode}3").val()==''){
		alert("매장명을 입력하세요");
		$("#sm_nm_${typeCode}3").focus();
		return;
	}
	
	var em_no ="";
	var em_no_sub ="";
	//사원처리=============================================
	var emGroup = $("#selectItem_em_nm_${typeCode}3 span");
	if(emGroup.length> 0){
		var em_no = emGroup.eq(0).attr("data-code");
		//alert(em_no);
		$('#em_no_${typeCode}3').val(em_no);
	}
	//==================================================
	
	//보조사원처리==========================================
	var emSubGroup = $("#selectItem_em_nm_sub_${typeCode}3 span");
	if(emSubGroup.length> 0){
		var em_no_sub = emSubGroup.eq(0).attr("data-code");
		//alert(em_no_sub);
		$('#em_no_sub_${typeCode}3').val(em_no_sub);
	}
	//==================================================
		
	if(em_no!='' && em_no==em_no_sub){
		alert("담당사원과 보조사원이 같습니다 확인하세요.");
		return;
	}
	
	var vUrl = "";
	var vType = $('#save_type_${typeCode}3').val();
	var param = $('#frm${typeCode}3').serialize();
	
	param +='&om_code=' + $('#om_code_combo_${typeCode}3').val();
	
	$('#om_code_combo_${typeCode}3').prop("disabled", true);
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
				fnSelectList_${typeCode}3($("#cg_code_combo_${typeCode}3").val(), $("#me_code_${typeCode}2").val());
				$("#btnSearch_${typeCode}4").click();
				
				//닫기
				$("#tempClose${typeCode}3").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

function popControlInit_${typeCode}1(){
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_${typeCode}1").prop("disabled", false);
	$("#chkAutoYn_${typeCode}1").prop("checked", true);
	
	$("#cg_code_${typeCode}1").val("");
	$("#cg_code_${typeCode}1").onlyNum();
	
	$("#cg_nm_${typeCode}1").val("");
	$("#cg_memo_${typeCode}1").val("");
	$('input:radio[id=use_at_y_${typeCode}1]').attr("checked", true);
	
	$("#cg_nm_${typeCode}1").focus();
}

function popControlInit_${typeCode}2(){
	//코드자동등록 체크박스 disabled
	$("#chkAutoYn_${typeCode}2").prop("disabled", false);
	$("#chkAutoYn_${typeCode}2").prop("checked", true);
	
	$("#me_code_${typeCode}2").val("");
	$("#me_code_${typeCode}2").onlyNum();
	
	$("#me_nm_${typeCode}2").val("");
	$("#me_memo_${typeCode}2").val("");
	$('input:radio[id=use_at_y_${typeCode}2]').attr("checked", true);
	
	$("#me_nm_${typeCode}2").focus();
}

function popControlInit_${typeCode}3(){
	
	/*var optText ="<option>선택</option>";
	$("#om_code_combo_${typeCode}3").html(optText);
	$("#cg_code_combo_${typeCode}3").html(optText);
	$("#me_code_combo_${typeCode}3").html(optText);*/
	
	$('#om_code_combo_${typeCode}3').prop("disabled", true);
	//관리지점
	$("#om_code_combo_${typeCode}3").val('');
	//고객그룹
	$("#cg_code_combo_${typeCode}3").val('');
	//관리업체
	$("#me_code_combo_${typeCode}3").val('');
	//매장명
	$("#sm_nm_${typeCode}3").val('');
	
	//운영여부(폐쇄:N)
	$('input:checkbox[id=chk_sm_oper_at_${typeCode}3]').attr("checked", false);
	$('#sm_oper_at_${typeCode}3').val('Y');
	
	//담당사원 유형
	$('input:radio[id=rdo_se_empl_ty_y_${typeCode}3]').attr("checked", true);
	
	//cvs cafe 여부
	$('input:checkbox[id=chk_sm_cvscafe_at_${typeCode}3]').prop("checked", false);
	$('#sm_cvscafe_at_${typeCode}3').val('N');
	//사원 autocomplete
	$("#selectItem_em_nm_${typeCode}3").html("");
	//보조사원 autocomplete
	$("#selectItem_em_nm_sub_${typeCode}3").html("");
	//담당사원코드
	$('#em_no_${typeCode}3').val('');
	//담당사원명
	$('#em_nm_${typeCode}3').val('');
	//보조사원명
	$('#em_nm_sub_${typeCode}3').val('');
	//매장sap코드
	$('#sm_sap_code_${typeCode}3').val('');
	$("#sm_sap_code_${typeCode}3").onlyNum();
	//차수
	$('#sm_odr_${typeCode}3').val("1");
	//사업자번호
	$('#sm_bizrno_${typeCode}3').val('');
	//상호
	//$('#sm_cmpnm_${typeCode}3').val('');
	//대표자명
	$('#sm_rprsntv_nm_${typeCode}3').val('');
	//지역
	//$('#sm_area_${typeCode}3').val(storeVo.sm_area);
	//우편번호
	$('#sm_zipcd_${typeCode}3').val('');
	//주소
	$('#sm_adres_${typeCode}3').val('');
	//상세주소
	$('#sm_dtadres_${typeCode}3').val('');
	//기타주소
	$('#sm_etcadres_${typeCode}3').val('');
	//위도/경도
	$('#sm_la_${typeCode}3').val('');
	$('#sm_lo_${typeCode}3').val('');
	//웹사이트
	//$('#sm_site_se_${typeCode}3').val('http');
	//$('#sm_site_${typeCode}3').val('');
	//대표전화
	$('#sm_tlphon_${typeCode}3').val('');
	//팩스번호
	$('#sm_fxnum_${typeCode}3').val('');
	//메모
	$('#sm_memo_${typeCode}3').val('');
	
	
	$("#sm_nm_${typeCode}3").focus();
}


function fnMakeAutoComplete_50100(){
	var empl_ty = "";
	
	if($('input:radio[id="rdo_se_empl_ty_y_${typeCode}3"]:checked').val()!= undefined){
		empl_ty = $('#rdo_se_empl_ty_y_${typeCode}3').val(); 
	}else{
		empl_ty = $('#rdo_se_empl_ty_n_${typeCode}3').val();
	}
	
	//alert(empl_ty);
	//if($('input:radio[name="se_empl_ty"]:checked').val())
    $("#em_nm_${typeCode}3").autocomplete({
    	serviceUrl:'/store/emplAutoComplate',
    	params: {se_empl_ty : empl_ty,
    			bhf_code : $('#om_code_combo_${typeCode}3').val()},
    	//params: {se_empl_ty : '0000000006'},
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
	   				$("#em_no_${typeCode}3").val("");
	   			});
	   			span.append(a);
	   			//$("#selectItem_em_nm_${typeCode}3").append(span);
	   			$("#selectItem_em_nm_${typeCode}3").html(span);
	   		}
	   		$(this).val("");
        }
    });
    
    $("#em_nm_sub_${typeCode}3").autocomplete({
    	serviceUrl:'/store/emplAutoComplate',
    	params: {se_empl_ty : empl_ty, //$('input:radio[name="se_empl_ty"]:checked').val(),
    			bhf_code : $('#om_code_combo_${typeCode}3').val()},
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
	   				$("#em_no_sub_${typeCode}3").val("");
	   			});
	   			span.append(a);
	   			//$("#selectItem_em_nm_${typeCode}3").append(span);
	   			$("#selectItem_em_nm_sub_${typeCode}3").html(span);
	   		}
	   		$(this).val("");
        }
    });
   
}

function fnSelectList_${typeCode}4(currPg){
	storePgAll_${typeCode} = currPg;//현재페이지 리로드
	var fnName = "fnSelectList_${typeCode}4";//페이징처리 함수명
	var rowSize = 10;//표시할 행수
	var params = {
					om_code : v_om_code_${typeCode},
					cg_nm : $.trim($('#cg_nm_search_${typeCode}4').val()),					
					me_nm : $.trim($('#me_nm_search_${typeCode}4').val()),
					sm_nm : $.trim($('#sm_nm_search_${typeCode}4').val()),
					em_nm : $.trim($('#em_nm_search_${typeCode}4').val()),
					se_empl_ty : $('#se_empl_ty_search_${typeCode}4').val(),
					sm_odr : $('#sm_odr_search_${typeCode}4').val(),
					sm_cvscafe_at :$('#chk_search_sm_oper_at_${typeCode}4').data("value"),
					sm_rprsntv_nm :""
				};
	
	$.ajax({
		url : "/store/storeAllList",
		data:{
			  //하단 페이징 처리
			  "fnName" : fnName,
			  "params" : params,
			  "rowSize" : rowSize,
			  "currPg" : currPg,
			  },
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var listHtml ="";
				for(var i=0; i<data.result.length; i++){
					var storeVo = data.result[i];     
					
					listHtml += "<tr>";
					//listHtml += "	<td>"+storeVo.no+"</td>";
					listHtml += "	<td data-value"+storeVo.no+"='"+storeVo.sm_code+"'>"+(parseInt(data.firstNo)-i)+"</td>";
					listHtml += "	<td class='txt_left'>"+storeVo.cg_nm +"</td>";
					listHtml += "	<td class='txt_left'>"+storeVo.me_nm +"</td>";
					listHtml += "	<td class='txt_left'>"+storeVo.sm_nm +"</td>";
					listHtml += "	<td class='txt_left'>"+storeVo.em_nm +"</td>";
					listHtml += "	<td>"+storeVo.se_empl_ty_nm +"</td>";
					listHtml += "	<td>"+storeVo.sm_odr_nm +"</td>";
					listHtml += "	<td class='txt_left'>"+storeVo.sm_tlphon +"</td>";
					listHtml += "	<td class='txt_left'>"+storeVo.sm_fxnum +"</td>";
					/* listHtml += "	<td>"+storeVo.sm_oper_at +"</td>"; */
					if (storeVo.sm_cvscafe_at=='Y')
						listHtml += "	<td><i class='fa fa-check'></i></td>";
					else
						listHtml += "	<td></td>";
							
					listHtml += "	<td>"+storeVo.sm_oper_nm +"</td>";
					listHtml += "</tr>";
	        	}
			}else{
				listHtml += "<tr>";
				listHtml += "	<td colspan='11'>조회된 내용이 없습니다.</td>";
				listHtml += "</tr>";
			}	
			
			$("#navi50100${typeCode}").html(data.navi);
			$("#tbody${typeCode}4").html(listHtml);
		    
			//
			fnAddTbodyClickEvent();
	    }
	});
}

function fnAddTbodyClickEvent(){
	$('#tbody${typeCode}4 tr').click(function(){
		$('#tbody${typeCode}4').find('tr').removeClass('cell_active');
		$(this).addClass('cell_active');
	
		var idx = $("#table${typeCode}4 tbody tr").index(this);
		var no=$("#table${typeCode}4 tbody tr:eq("+idx+") td:nth-child(1)").text();
		var smCode = $("#table${typeCode}4 tbody tr:eq("+idx+") td:nth-child(1)").attr("data-value") + "";
		fnUpdate_${typeCode}3(smCode);
	});
}

//매장전체목록 팝업 초기화
function fnInitPopupStoreAll_${typeCode}4(){
	$('#cg_nm_search_${typeCode}4').val(''); 
	$('#me_nm_search_${typeCode}4').val(''); 
	$('#sm_nm_search_${typeCode}4').val(''); 
	$('#em_nm_search_${typeCode}4').val(''); 

	//차수
	cboCommon_${typeCode}3('0000000010', $('#sm_odr_search_${typeCode}4'));
	
	$('#se_empl_ty_${typeCode}4').val('0000000006');
	$('#sm_odr_search_{typeCode}4').val('0000000011');
	
	$('#chk_search_sm_oper_at_${typeCode}4').prop('checked', false); 
	$('#chk_search_sm_oper_at_${typeCode}4').data('value', 'N'); 
	
	
}
var arr_type_code = [];

function fnJuso_${typeCode}(){
	$('#btnJuso_${typeCode}').click(function(){
		numCnt=0;
		alert("주소 위도 경도 처리 시작합니다.");
		$.ajax({
			url : "/store/juso",
			//data:{"om_code":om_code},
		    type : "POST",
		    dataType : "json",
		    cache : false,
		    async : false,
		    success : function(data) {
				arr_type_code = [];
		    	//alert("111");
		    	for (var i = 0; i < data.result.length; i++) {
					var vo = data.result[i];
					var sm_code= vo.sm_code;
					var sm_la="";
					var sm_lo="";
					var addrTxt = "";
					addrTxt = vo.sm_adres+" "+vo.sm_etcadres; // + vo.sm_dtadres;
					addrTxt = addrTxt.replace("(","");
					addrTxt = addrTxt.replace(")","");
				
// 					var arrTest = {
// 							"sm_code" : sm_code
// 							,"addrTxt":addrTxt
// 					}
					
					arr_type_code.push({
							"sm_code" : sm_code
							,"addrTxt":addrTxt
					});
		    	}
		    }
		});
 			alert(arr_type_code.length);
 			console.log("sm_code : " + arr_type_code[0].sm_code + " addrTxt : "+  arr_type_code[0].addrTxt);
 			juapyo(arr_type_code[0].sm_code, arr_type_code[0].addrTxt);
	});
}


function juapyo(sm_code, addrTxt){
	console.log("** sm_code :" + sm_code +", addrTxt :" + addrTxt+ ", cnt : " + numCnt);
	
	var sm_la;
	var sm_lo;
	$.ajax({
		url : "https://apis.daum.net/local/geo/addr2coord",
		data:{"output":"json","q":addrTxt,"apikey":"6620dde9d3a1dde771f78dff247387d6"},      // //3b154ec13945d98f6f1c52a9922d8373
	    type : "get",
	    dataType : "jsonp",
	    jsonp : "callback",
	    cache : false,
	    //async : false,
	    success : function(data){
			if(data!=null&& data.channel.item.length>0){
				sm_la = data.channel.item[0].lat;
				sm_lo = data.channel.item[0].lng;
			} 
			
			if(sm_la != ""){
				$.ajax({
					url : "/store/saveJuso",
					data:{"sm_code":sm_code,"sm_la":sm_la,"sm_lo":sm_lo},
				    type : "POST",
				    dataType : "json",
				    cache : false,
				    async : false,
				    success : function(data) {
						//alert("성공");
				    }
				});
			}
			
    		if( numCnt < arr_type_code.length-1){
    			numCnt++;
    			juapyo(arr_type_code[numCnt].sm_code, arr_type_code[numCnt].addrTxt);
    		}
	    }
	    ,error : function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status + " a " +jqXHR.responseText);
		}
	});
}


</script>