
var v_nodeOrganizationCode = "";
var v_cp_code_99300 ="";

/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	//이벤트 등록
	fnMakeEventComponent_99300();
	
	v_cp_code_99300 = $("#login_cp_cd_99300").val();
	
	//트리
	fnMakeFtree99300();
	
	//최초조회
	fnSelectNodeList_99300("");
				
});

/**
 * @함수명: fnMakeFtree
 * @작성일: 2015. 09. 24
 * @작성자: 
 * @설명: 트리 구조 생성및 이벤트
 * @param 
 */
function fnMakeFtree99300(){
	$("#baseList_99300").fTree({
		url : "/organization/treeList",
		key:"om_code",
		val:"om_nm",
		p_key:"om_parent_no",
		tagId:"pTree",
		click:  function(data){
			v_nodeOrganizationCode = data.key;
			fnSelectNodeList_99300(data.key);
			$("#selectedCodeName_99300").text(data.val);	//선택코드명 > title
		}
	});
}

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 34.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent_99300(){
	
	$("#tempPop99300, #zipcdPop_99300").instancePopUp();
	
	$("#om_parent_no_D1_99300").change(function(){
		$("#om_parent_no_99300").val($(this).val());
		$("#om_orgnzt_se_99300").val("2");
		cboSelectDepthList_99300(v_cp_code_99300, $(this).val(), 2);
	});	
	
	$("#om_parent_no_D2_99300").change(function(){
		if($(this).val()==""){
			$("#om_parent_no_99300").val($("#om_parent_no_D1_99300").val());
			$("#om_orgnzt_se_99300").val("2");
		}
		else{
			$("#om_parent_no_99300").val($(this).val());
			$("#om_orgnzt_se_99300").val("3");
		}
		
		
	});
	
	//조직 추가 버튼
	$("#tempInsert99300").click(function(){
		//팝업 컨트롤 초기화
		popControlInit_99300();
		
		//회사 콤보 초기화
		cboSelectCompanyList_99300();
		$("#om_orgnzt_se_99300").val("1");
		
		//회사
		cboSelectDepthList_99300(v_cp_code_99300, '', 1);
		$("#om_parent_no_D1_99300").val("");
		$("#om_orgnzt_se_99300").val("1");
		
		var listHtml = "<option value =''> 선택 </option>";
		$("#om_parent_no_D2_99300").html(listHtml);
		
		$("#tempPop99300").show();
		$("#save_type_99300").val("INSERT");
		$("#om_nm_99300").focus();
	});
	
	//팝업 저장버튼
	$("#tempSave99300").click(function(){
		fnSave99300();
	});
	
	//팝업 닫기버튼
	$("#tempClose99300, #tempCloseX99300").click(function(){
		$("#tempPop99300").hide();
	});
	
	
	
	//주소로 좌표 알아오기
	$('#getCoordinateBtn_99300').click(function(){
		
		/* if($('#om_zipcd_99300').val()==''){
			alert("주소를 검색하십시오");
			return;
		} */
		if($('#om_adres_99300').val()==''){
			alert("주소를 검색하십시오");
			return;
		}
		/* if($('#om_etcadres_99300').val()==''){
			alert("주소를 검색하십시오");
			return;
		} */
		
		var addrTxt = "";
		
		
		addrTxt = //$('#om_zipcd_99300').val() + " " + 
				 $('#om_adres_99300').val();
				 //+ " "+ $('#om_etcadres_99300').val() + $('#om_dtadres_99300').val();
		addrTxt = addrTxt.replace("("," ");
		addrTxt = addrTxt.replace(")","");
		
		//alert(addrTxt);
		//Google
		/*
		$.ajax({
			url : "http://maps.googleapis.com/maps/api/geocode/json",
			data:{"address": addrTxt, language:"ko", sensor:false},
		    type : "get",
		    dataType : "json",
		    cache : false,
		    async : false,
		    success : function(data) {
				if(data!=null&& data.results.length>0){
					$('#om_la_99300').val(data.results[0].geometry.location.lat);
					$('#om_lo_99300').val(data.results[0].geometry.location.lng);
				}
		    }
		});
		*/
		//DAUM
		$.ajax({
			url : "https://apis.daum.net/local/geo/addr2coord",
			data:{output:"json", q: addrTxt, apikey: "3b154ec13945d98f6f1c52a9922d8373"},
		    type : "get",
		    dataType : "jsonp",
		    jsonp : "callback",
		    cache : false,
		    //async : false,
		    success : function(data) {
				if(data!=null&& data.channel.item.length>0){
					$('#om_la_99300').val(data.channel.item[0].lat);
					$('#om_lo_99300').val(data.channel.item[0].lng);
				}
		    }
		    ,error : function(jqXHR, textStatus, errorThrown) {
				alert("에러가 발생하였습니다.");
			}
		});
	});
	
	//우편번호
	$('#addrSearchPopBtn_99300').click(function(){
		$postOption.insertPostcode5 = "#om_zipcd_99300";
		//주소
		$postOption.insertAddress = "#om_adres_99300";
		//상세주소
		$postOption.insertExtraInfo = "#om_etcadres_99300";
		//기타주소
		$postOption.insertDetails = "#om_dtadres_99300";
					
		$("#zipcdPop_99300").show();
	});
	
	$("#addrSearch_99300").postcodify({
		controls : "#addrSearchControl_99300" ,
		afterSelect : function(selectedEntry) { 
			$("#addrSearch_99300").html("");
			$(".keyword").val("");
			$("#zipcdPop_99300").hide();
		},
		hideOldAddresses : false ,
		mapLinkProvider : 'daum'
	});
	
	$('#zipcodePopCloseBtn_99300').click(function(){
		$("#addrSearch_99300").html("");
		$(".keyword").val("");
		$("#zipcdPop_99300").hide();
	});
	
}

/**
 * @함수명: fnSelectNodeList_99300
 * @작성일: 2015. 09. 23.
 * @작성자: 최수영
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */

function fnSelectNodeList_99300(om_code){
	$.ajax({
		url : "/organization/list",
		data:{"om_code":om_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "";

		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var organizationVo = data.result[i];   
				
				if(i==0 && om_code!='')
					listHtml += "<tr class ='cell_active'  style='font-weight: bold;'>";
				else
					listHtml += "<tr>";
				
        		listHtml += "<td>" + organizationVo.om_code + "</td>";
    			listHtml += "<td class='txt_left'>" + organizationVo.om_nm + "</td>";
    			listHtml += "<td>" + organizationVo.om_sort_ordr + "</td>";
    			listHtml += "<td>" + organizationVo.use_at + "</td>";
    			listHtml += "<td>";
    			listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_99300('"+organizationVo.om_code+"')\";>수정</button>&nbsp;&nbsp;";
    			listHtml += "	<button type='button' class='white' onclick=\"fnDelete_99300('"+organizationVo.om_code+"')\";>삭제</button>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody99300").html(listHtml);
	    
	    }
	});
}


/**
 * @함수명: cboSelectCompanyList_99300
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 회사 콤보 조회
 * @param 
 */
function cboSelectCompanyList_99300(){
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
					if(data!=null && data.result.length > 0){
			    		listHtml += "<option value ='' selected=\"selected\">회사 선택</option>";
			    		for(var i=0; i<data.result.length; i++){
			    			var res = data.result[i];     
			    			
			    			listHtml += "<option value ='"+res.cm_code+"'>"+ res.cm_nm +"</option>";
			    		}
			    	}else{
			    		listHtml += "<option value =''> 선택 </option>";
			    	}
			    	
			    	$("#cm_code_99300").html(listHtml);
	        	}
			}else{
				
			}	
	    }
	});
}

/**
 * @함수명: cboSelectBranchList_99300
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 지점 콤보 조회(depth1)
 * @param 
 */
function cboSelectBranchList_99300(){
	$.ajax({
		url : "/organization/branchList",
	    data:{"cm_code":$("#cm_code_99300").val()},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					if(data!=null && data.result.length > 0){
			    		listHtml = "<option value ='' selected=\"selected\">지점 선택</option>";
			    		for(var i=0; i<data.result.length; i++){
			    			var res = data.result[i];     
			    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
			    		}
			    	}else{
			    		listHtml += "<option value =''>"+"없음"+"<option>";
			    	}
			    	$("#om_parent_no_99300").html(listHtml);
	        	}			}else{
				
			}	
	    }
	});
}


/**
 * @함수명: cboSelectDepthList_99300
 * @작성일: 2015. 09. 24.
 * @작성자: 최수영
 * @설명: 콤보 Depth 
 * @param 
 */
function cboSelectDepthList_99300(cm_code, om_code, om_orgnzt_se){
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
			    		listHtml = "<option value ='' selected=\"selected\">상위코드 선택</option>";
			    		for(var i=0; i<data.result.length; i++){
			    			var res = data.result[i];     
			    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
			    		}
			    	}else{
			    		
			    	}
	        	}	
			}else{
				listHtml += "<option value =''> 선택 </option>";
			}	
			
			if(om_orgnzt_se==1)
				$("#om_parent_no_D1_99300").html(listHtml);
			else if(om_orgnzt_se==2)
				$("#om_parent_no_D2_99300").html(listHtml);
	    }
	});
}

/**
 * @함수명: fnUpdate_99300
 * @작성일: 2015. 09. 23.
 * @작성자: 최수영
 * @설명: 조직코드 정보 수정
 * @param 
 */
function fnUpdate_99300(om_code){
	$("#save_type_99300").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_99300();
	
	//회사 콤보 초기화
	//cboSelectCompanyList_99300();
	
	//팝업 show
	$("#tempPop99300").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/organization/organizationRow",
		data:{"om_code":om_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var organizationVo = data.result[0];    
				var listHtml ="<option value ='' selected=\"selected\"> 선택 </option>";
				if(organizationVo.om_orgnzt_se=="1") {
					//$("#om_parent_no_D1_99300, #om_parent_no_D2_99300").attr("disabled", true);
					cboSelectDepthList_99300(organizationVo.cm_code, "", 1);
					$("#om_parent_no_D2_99300").html(listHtml);
				}else if(organizationVo.om_orgnzt_se=="2") {
					cboSelectDepthList_99300(organizationVo.cm_code, "", 1);
					$("#om_parent_no_D1_99300").val(organizationVo.om_parent_no);
					cboSelectDepthList_99300(organizationVo.cm_code, organizationVo.om_parent_no, 2);
					$("#om_parent_no_D2_99300").val("");			
				}else if(organizationVo.om_orgnzt_se=="3") {
					cboSelectDepthList_99300(organizationVo.cm_code, "", 1);
					$("#om_parent_no_D1_99300").val(organizationVo.up_depth_code);
					cboSelectDepthList_99300(organizationVo.cm_code, organizationVo.up_depth_code, 2);
					$("#om_parent_no_D2_99300").val(organizationVo.om_parent_no);
				}
				$("#om_code_99300").val(organizationVo.om_code);
				$("#cm_code_99300").val(organizationVo.cm_code);
				$("#om_parent_no_99300").val(organizationVo.om_parent_no);
				$("#om_nm_99300").val(organizationVo.om_nm);
				$("#om_orgnzt_se_99300").val(organizationVo.om_orgnzt_se);
				$("#om_sort_ordr_99300").val(organizationVo.om_sort_ordr);
				
				$("#om_zipcd_99300").val(organizationVo.om_zipcd);
				$("#om_adres_99300").val(organizationVo.om_adres);
				$("#om_dtadres_99300").val(organizationVo.om_dtadres);
				$("#om_etcadres_99300").val(organizationVo.om_etcadres);
				$("#om_la_99300").val(organizationVo.om_la);
				$("#om_lo_99300").val(organizationVo.om_lo);
				
				$("input:radio[name=use_at]:input[value=" + organizationVo.use_at + "]").attr("checked", true);
				$("#om_memo_99300").val(organizationVo.om_memo);

			}else{
				
			}	
	    }
	});	
	
	$("#om_nm").focus();
}

/**
 * @함수명: fnDelete_99300
 * @작성일: 2015. 09. 23
 * @작성자: 최수영
 * @설명: 조직코드 삭제
 * @param 
 */
function fnDelete_99300(om_code){
	
	if(!confirm("해당 조직를 삭제 하시겠습니까?")){
		return;
	}
	
	$.ajax({
		   type : "POST",
		   global: true,
		   async : true,
		   cache : false,
		   //url  : "/organization/deleteOrganization",
		   url  : "/organization/saveOrganization",
		   dataType :"json",
		   //data  	 : {'om_code': om_code},
		   data  	 : {'flag':'DELETE', 'om_code': om_code},
		   success  : function(data){
			   if(data.dto[0].res_code==-1){
					alert('삭제되지 않았습니다.');
					return;
				}else if(data.dto[0].res_code==0){
					alert('삭제 되었습니다.');
					
					//리스트 조회
					//fnSelectCompanyList_99100();
					fnSelectNodeList_99300(v_nodeOrganizationCode);
					fnMakeFtree99300();
					return;
				}
		   },
		   error : function(XMLHttpRequest, textStatus, error){
			alert("서버 통신 중 에러가 발생했습니다.");
		   }
		});
	/*
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   //url  : "/organization/deleteOrganization",
	   url  : "/organization/saveOrganization",
	   dataType :"json",
	   //data  	 : {'om_code': om_code},
	   data  	 : {'flag':'DELETE', 'om_code': om_code},
	   success  : function(data){
			if(data.resultCnt==0 ){
				alert('삭제되지 않았습니다.');
				return;
			}else if(data.resultCnt==1){
				alert('삭제 되었습니다.');
				
				//리스트 조회
				//fnSelectCompanyList_99100();
				fnSelectNodeList_99300(v_nodeOrganizationCode);
				fnMakeFtree99300();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
	*/
}

/**
 * @함수명: fnSave99300
 * @작성일: 2015. 09. 23.
 * @작성자: 최수영
 * @설명: 조직정보 저장
 * @param 
 */
function fnSave99300(){
	var vUrl = "";
	var vType = $('#save_type_99300').val();
	
	$('#cm_code_99300').val(login_cp_cd);
	var param = $('#frm99300').serialize();
	
	/*
	if($('#cm_code_99300').val() == ''){
		 alert("해당 조직이 속할 회사를 선택 하세요.");
		 $("#cm_code_99300").focus();
		 return;
	 }
	 */
	
	 if($.trim($('#om_nm_99300').val()) == ''){
		 alert("조직명을 입력하세요.");
		 $("#om_nm_99300").focus();
		 return;
	 }
	 
	 if($('#om_orgnzt_se_99300').val() == ''){
		 alert("조직구분을 선택하세요.");
		 $("#om_orgnzt_se_99300").focus();
		 return;
	 }
	 
	 if($('#om_orgnzt_se_99300').val()=='T' && $('#om_parent_no_99300').val()==''){
		 $("#om_parent_no_99300").focus();
		 alert("지점을 선택하세요.");
		 return;
	 }
	 /*
	 if(vType=="UPDATE")
		 vUrl = "/organization/updateOrganization";
	 else
		 vUrl = "/organization/insertOrganization";
	 */
	 vUrl="/organization/saveOrganization";
	 
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   url  : vUrl,
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
			if(data.dto[0].res_code==-1){
				alert('저장되지 않았습니다.');
				//alert(data.dto[0].res_msg);
				return;
			}else if(data.dto[0].res_code==0){
				alert('저장 되었습니다.');
				
				//alert("v_nodeOrganizationCode : " + v_nodeOrganizationCode);
				fnSelectNodeList_99300(v_nodeOrganizationCode);
				
				fnMakeFtree99300();
				//닫기
				$("#tempClose99300").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
	
	//$("#baseList_99300").fTree();
	//fnMakeFtree99300();
}


/**
 * @함수명: fnFirstNodeSelect_99300
 * @작성일: 2015. 09. 23.
 * @작성자: 최수영
 * @설명: 메뉴코드 정보 수정
 * @param 
 */
function fnFirstNodeSelect_99300(){
	var firstNodeCode="";
	$.ajax({
		url : "/organization/firstTreeOrganization",
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    async : false,
	    success : function(data) {	
	    	//alert(data.result);
			if(data!=null)
				firstNodeCode = data.result;
			else
				firstNodeCode = 0;
		}
	});	
	
	fnSelectNodeList_99300(firstNodeCode);
}

/**
 * @함수명: popControlInit_99300
 * @작성일: 2015. 09. 23.
 * @작성자: 최수영
 * @설명: 팝업 컨트롤 초기화
 * @param 
 */
function popControlInit_99300(){
	
	//회사코드 나중에 세션값으로!!
	$('#cm_code_99300').val(v_cp_code_99300);
	
	$('#om_code_99300').val('');
	$('#om_nm_99300').val('');
	$('#om_parent_no_99300').val('');
	$('#om_orgnzt_se_99300').val('');
	$('#om_sort_ordr_99300').val('');
	$("#om_sort_ordr_99300").onlyNum();
	$('#om_memo_99300').val('');
	$('#use_at_99300').val('Y');
	$('input:radio[id=pop_use_at_y_99300]').attr("checked", true);
	
	$("#om_parent_no_D1_99300, #om_parent_no_D2_99300").attr("disabled", false);
	
	var listHtml = "<option value =''> 선택 </option>";
	$("#om_parent_no_D2_99300").html(listHtml);
	
	
	$('#om_zipcd_99300').val('');
	$('#om_adres_99300').val('');
	$('#om_etcadres_99300').val('');
	$('#om_dtadres_99300').val('');
	$('#om_la_99300').val('');
	$('#om_lo_99300').val('');
	
	$("#om_nm_99300").focus();
}


