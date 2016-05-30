
var v_nodeProductCode = "";

/**
 * @함수명: onLoad
 * @작성일: 2015. 10. 06.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	//이벤트 등록
	fnMakeEventCpmponent_99200();
	
	//트리
	fnMakeFtree99200();
	
	//최초조회
	fnSelectNodeList_99200("");
				
});

/**
 * @함수명: fnMakeFtree
 * @작성일: 2015. 10. 06.
 * @작성자: 
 * @설명: 트리 구조 생성및 이벤트
 * @param 
 */
function fnMakeFtree99200(){
	$("#baseList_99200").fTreeNew({
		url :"/product/treeList",
		key:"pm_code",
		val:"pm_nm",
		p_key:"pm_parent_no",
		tagId:"jTree",
		click:  function(data){
			if(data!=null){
				v_nodeProductCode = data.key;
				fnSelectNodeList_99200(data.key);
				$("#selectedCodeName_99200").text(data.val);	//선택코드명 > title
			}
		}
	});
}

/**
 * @함수명: fnMake_EventCpmponent
 * @작성일: 2015. 10. 06.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventCpmponent_99200(){
	
	$("#tempPop99200").instancePopUp();
	var tempVal = "<option value ='' selected=\"selected\"> 선택 </option>";
	
	$("#pm_parent_no_D1_99200").change(function(){
		if($(this).val()==""){
			$("#pm_parent_no_99200").val("");
			$("#pm_dp_99200").val("1");
			$("#pm_parent_no_D2_99200, #pm_parent_no_D3_99200, #pm_parent_no_D4_99200").html(tempVal);
		}
		else{
			$("#pm_parent_no_99200").val($(this).val());
			$("#pm_dp_99200").val("2");
			cboSelectDepthList_99200($(this).val(), 2);
			
			//코드 기본 셋팅
			$('#pm_code_99200').val($(this).val());
		}
	});
	
	$("#pm_parent_no_D2_99200").change(function(){
		if($(this).val()==""){
			$("#pm_parent_no_99200").val($("#pm_parent_no_D1_99200").val());
			$("#pm_dp_99200").val("2");
			$("#pm_parent_no_D3_99200, #pm_parent_no_D4_99200").html(tempVal);
		}
		else{
			$("#pm_parent_no_99200").val($(this).val());
			$("#pm_dp_99200").val("3");
			cboSelectDepthList_99200($(this).val(), 3);
			
			//코드 기본 셋팅
			$('#pm_code_99200').val($(this).val());
		}
	});
	
	$("#pm_parent_no_D3_99200").change(function(){
		if($(this).val()==""){
			$("#pm_parent_no_99200").val($("#pm_parent_no_D2_99200").val());
			$("#pm_dp_99200").val("3");
			$("#pm_parent_no_D4_99200").html(tempVal);
		}
		else{
			$("#pm_parent_no_99200").val($(this).val());
			$("#pm_dp_99200").val("4");
			cboSelectDepthList_99200($(this).val(), 4);
			
			//코드 기본 셋팅
			$('#pm_code_99200').val($(this).val());
		}
	});
	
	$("#pm_parent_no_D4_99200").change(function(){
		if($(this).val()==""){
			$("#pm_parent_no_99200").val($("#pm_parent_no_D3_99200").val());
			$("#pm_dp_99200").val("4");
		}
		else{
			$("#pm_parent_no_99200").val($(this).val());
			$("#pm_dp_99200").val("5");
			
			//코드 기본 셋팅
			$('#pm_code_99200').val($(this).val());
		}
	});
	
	//품목 추가 버튼
	$("#tempInsert99200").click(function(){
		//팝업 컨트롤 초기화
		popControlInit_99200();
		
		if(v_nodeProductCode==''){
			//콤보1
			cboSelectDepthList_99200('', 1);
			$("#pm_parent_no_D1_99200").val("");
			$("#pm_dp_99200").val("1");
		}else{
			//입력시 트리 클릭에의한 코드 기본셋팅
			fnBaseInsertSetting_99200(v_nodeProductCode);
		}

		$("#tempPop99200").show();
		$("#save_type_99200").val("INSERT");
		$("#pm_code_99200").focus();
	});
	
	//팝업 저장버튼
	$("#tempSave99200").click(function(){
		fnSave99200();
	});
	
	//팝업 닫기버튼
	$("#tempClose99200, #tempCloseX99200").click(function(){
		$("#tempPop99200").hide();
	});
}

/**
 * @함수명: fnBaseInsertSetting_99200
 * @작성일: 2015. 10. 28.
 * @작성자: 최수영
 * @설명: 트리 클릭에 의한 기본 셋팅
 * @param 
 */
function fnBaseInsertSetting_99200(nodeCode){
	if(nodeCode.length==0|| nodeCode.length==10){
		//콤보1
		cboSelectDepthList_99200('', 1);
		$("#pm_parent_no_D1_99200").val("");
		$("#pm_dp_99200").val("1");
		return;
	}else if(nodeCode.length==2){
		var code1=nodeCode.substr(0,2);
		//1
		cboSelectDepthList_99200("", 1);
		$("#pm_parent_no_D1_99200").val(code1);
		//2
		cboSelectDepthList_99200(code1, 2);
		
		$("#pm_dp_99200").val("2");
		$("#pm_parent_no_99200").val($("#pm_parent_no_D1_99200").val());
	}else if(nodeCode.length==4){
		var code1=nodeCode.substr(0,2);
		var code2=nodeCode.substr(0,4);
		//1
		cboSelectDepthList_99200("", 1);
		$("#pm_parent_no_D1_99200").val(code1);
		//2
		cboSelectDepthList_99200(code1, 2);
		$("#pm_parent_no_D2_99200").val(code2);
		//3
		cboSelectDepthList_99200(code2, 3);
		
		$("#pm_dp_99200").val("3");
		$("#pm_parent_no_99200").val($("#pm_parent_no_D2_99200").val());
	}else if(nodeCode.length==6){
		var code1=nodeCode.substr(0,2);
		var code2=nodeCode.substr(0,4);
		var code3=nodeCode.substr(0,6);
		//1
		cboSelectDepthList_99200("", 1);
		$("#pm_parent_no_D1_99200").val(code1);
		//2
		cboSelectDepthList_99200(code1, 2);
		$("#pm_parent_no_D2_99200").val(code2);
		//3
		cboSelectDepthList_99200(code2, 3);
		$("#pm_parent_no_D3_99200").val(code3);
		//4
		cboSelectDepthList_99200(code3, 4);
		
		$("#pm_dp_99200").val("4");
		$("#pm_parent_no_99200").val($("#pm_parent_no_D3_99200").val());
	}else if(nodeCode.length==8){
		var code1=nodeCode.substr(0,2);
		var code2=nodeCode.substr(0,4);
		var code3=nodeCode.substr(0,6);
		var code4=nodeCode.substr(0,8);
		//1
		cboSelectDepthList_99200("", 1);
		$("#pm_parent_no_D1_99200").val(code1);
		//2
		cboSelectDepthList_99200(code1, 2);
		$("#pm_parent_no_D2_99200").val(code2);
		//3
		cboSelectDepthList_99200(code2, 3);
		$("#pm_parent_no_D3_99200").val(code3);
		//4
		cboSelectDepthList_99200(code3, 4);
		$("#pm_parent_no_D4_99200").val(code4);
		
		$("#pm_dp_99200").val("5");
		$("#pm_parent_no_99200").val($("#pm_parent_no_D4_99200").val());
	}
	//코드 기본 셋팅
	$('#pm_code_99200').val(nodeCode);	
}

/**
 * @함수명: fnSelectNodeList_99200
 * @작성일: 2015. 10. 06.
 * @작성자: 최수영
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */

function fnSelectNodeList_99200(pm_code){
	$.ajax({
		url : "/product/list",
		data:{"pm_code":pm_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "";

		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var productVo = data.result[i];     
				
				if(i==0 && pm_code!='')
					listHtml += "<tr class ='cell_active'  style='font-weight: bold;'>";
				else
					listHtml += "<tr>";
				
        		listHtml += "<td class='txt_left'>" + productVo.pm_code + "</td>";
    			listHtml += "<td class='txt_left'>" + productVo.pm_nm + "</td>";
    			listHtml += "<td>" + productVo.pm_sort_ordr + "</td>";
    			listHtml += "<td>" + productVo.use_at + "</td>";
    			listHtml += "<td>";
    			listHtml += "	<button type='button' class='white' onclick=\"fnUpdate_99200('"+productVo.pm_code+"')\";>수정</button>&nbsp;&nbsp;";
    			listHtml += "	<button type='button' class='white' onclick=\"fnDelete_99200('"+productVo.pm_code+"')\";>삭제</button>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody99200").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: cboSelectDepthList_99200
 * @작성일: 2015. 09. 24.
 * @작성자: 최수영
 * @설명: 콤보 Depth 
 * @param 
 */
function cboSelectDepthList_99200(pm_code, pm_dp){
	$.ajax({
		url : "/product/depthList",
	    data:{"pm_code":pm_code, "pm_dp": pm_dp},
	    type : "POST",
	    dataType : "json", 
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if(data.result!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
			    		listHtml = "<option value ='' selected=\"selected\"> 선택 </option>";
			    		for(var i=0; i<data.result.length; i++){
			    			var res = data.result[i];     
			    			listHtml += "<option value ='"+res.pm_code+"'>" + res.pm_code + " : " + res.pm_nm +"</option>";
			    		}
	        	}	
			}else{
				listHtml += "<option value =''> 선택 </option>";
			}	
			if(pm_dp=='1')
				$("#pm_parent_no_D1_99200").html(listHtml);
			else if(pm_dp=='2')
				$("#pm_parent_no_D2_99200").html(listHtml);
			else if(pm_dp=='3')
				$("#pm_parent_no_D3_99200").html(listHtml);
			else if(pm_dp=='4')
				$("#pm_parent_no_D4_99200").html(listHtml);
	    }
	});
}

/**
 * @함수명: fnUpdate_99200
 * @작성일: 2015. 10. 06.
 * @작성자: 최수영
 * @설명: 품목코드 정보 수정
 * @param 
 */
function fnUpdate_99200(pm_code){
	$("#save_type_99200").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_99200();

	//팝업 show
	$("#tempPop99200").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/product/productRow",
		data:{"pm_code":pm_code},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var productVo = data.result[0];    
				var listHtml ="<option value ='' selected=\"selected\"> 선택 </option>";
			
				var code1, code2, code3, code4, code5;
				var tmpCode = productVo.pm_code;
				var tmpLength = tmpCode.length;
				if(tmpLength==2){
					//code1=tmpCode;
					cboSelectDepthList_99200("", 1);
					//$("#pm_parent_no_D1_99200").val(code1);
				}else if(tmpLength==4){
					code1=tmpCode.substr(0,2);
					code2=tmpCode.substr(0,4);
					//1
					cboSelectDepthList_99200("", 1);
					$("#pm_parent_no_D1_99200").val(code1);
				
				}else if(tmpLength==6){
					code1=tmpCode.substr(0,2);
					code2=tmpCode.substr(0,4);
					code3=tmpCode.substr(0,6);
					//1
					cboSelectDepthList_99200("", 1);
					$("#pm_parent_no_D1_99200").val(code1);
					//2
					cboSelectDepthList_99200(code1, 2);
					$("#pm_parent_no_D2_99200").val(code2);
				

				}else if(tmpLength==8){
					code1=tmpCode.substr(0,2);
					code2=tmpCode.substr(0,4);
					code3=tmpCode.substr(0,6);
					code4=tmpCode.substr(0,8);

					//1
					cboSelectDepthList_99200("", 1);
					$("#pm_parent_no_D1_99200").val(code1);
					//2
					cboSelectDepthList_99200(code1, 2);
					$("#pm_parent_no_D2_99200").val(code2);
					//3
					cboSelectDepthList_99200(code2, 3);
					$("#pm_parent_no_D3_99200").val(code3);
					
				}else if(tmpLength==10){
					code1=tmpCode.substr(0,2);
					code2=tmpCode.substr(0,4);
					code3=tmpCode.substr(0,6);
					code4=tmpCode.substr(0,8);
					code5=tmpCode.substr(0,10);

					//1
					cboSelectDepthList_99200("", 1);
					$("#pm_parent_no_D1_99200").val(code1);
					//2
					cboSelectDepthList_99200(code1, 2);
					$("#pm_parent_no_D2_99200").val(code2);
					//3
					cboSelectDepthList_99200(code2, 3);
					$("#pm_parent_no_D3_99200").val(code3);
					//4
					cboSelectDepthList_99200(code3, 4);
					$("#pm_parent_no_D4_99200").val(code4);
				}
				
				$("#pm_code_99200").prop("readonly", true);
				$("#pm_parent_no_D1_99200, #pm_parent_no_D2_99200, #pm_parent_no_D3_99200, #pm_parent_no_D4_99200").prop("disabled", true);
				$("#pm_code_99200").val(productVo.pm_code);
				$("#pm_parent_no_99200").val(productVo.pm_parent_no);
				$("#pm_nm_99200").val(productVo.pm_nm);
				$("#pm_dp_99200").val(productVo.pm_dp);
				$("#pm_sort_ordr_99200").val(productVo.pm_sort_ordr);
				$("input:radio[name=use_at]:input[value=" + productVo.use_at + "]").prop("checked", true);
				$("#pm_memo_99200").val(productVo.pm_memo);
				
			}else{
				
			}	
	    }
	});	
	$("#pm_nm").focus();
}

/**
 * @함수명: fnDelete_99200
 * @작성일: 2015. 09. 23
 * @작성자: 최수영
 * @설명: 품목코드 삭제
 * @param 
 */
function fnDelete_99200(pm_code){
	
	if(!confirm("해당 품목를 삭제 하시겠습니까?")){
		return;
	}
	
	$.ajax({
	   type : "POST",
	   global: true,
	   async : true,
	   cache : false,
	   url  : "/product/deleteProduct",
	   dataType :"json",
	   data  	 : {'pm_code': pm_code},
	   success  : function(data){
			if(data.resultCnt==0 ){
				alert('삭제되지 않았습니다.');
				return;
			}else if(data.resultCnt>=1){
				alert('삭제 되었습니다.');
				
				//리스트 조회
				fnSelectNodeList_99200(v_nodeProductCode);
				$("#leftMenu").leftMenu();
				fnMakeFtree99200();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

/**
 * @함수명: fnSave99200
 * @작성일: 2015. 10. 06.
 * @작성자: 최수영
 * @설명: 품목정보 저장
 * @param 
 */
function fnSave99200(){
	var vUrl = "";
	
	var param = $('#frm99200').serialize();

	if($.trim($('#pm_code_99200').val()) == ''){
		 alert("코드를 입력하세요.");
		 $("#pm_code_99200").focus();
			 return;
	}
	
	var dp = $("#pm_dp_99200").val();
	var code = $.trim($("#pm_code_99200").val());
	var parCode =  $.trim($("#pm_parent_no_99200").val());

	if($.trim($("#pm_code_99200").val()).length != (dp*2)){
		alert("코드 길이를 확인하세요");
		$("#pm_code_99200").focus();
		return;
	}

	//alert(dp);
	//alert("parCode : " + parCode + ", code.substr(0,(dp*2)-2) : " + code.substr(0,(dp*2)-2));
	if(code.substr(0,(dp*2)-2)!=parCode){
		alert("코드체계를 확인하세요");
		$("#pm_code_99200").focus();
		return;
	}

	 if($.trim($('#pm_nm_99200').val()) == ''){
		 alert("코드명을 입력하세요.");
		 $("#pm_nm_99200").focus();
		 return;
	 }

	//코드 확인
	 var vType = $('#save_type_99200').val();
	 if(vType=="INSERT")
	 if(!chkSameCode_prodcutCode($('#pm_code_99200').val())){
		 return;
	 };
	 
	 vType = $('#save_type_99200').val();
	 if(vType=="UPDATE")
		 vUrl = "/product/updateProduct";
	 else
		 vUrl = "/product/insertProduct";
		 
	 /*
	 if(vType=="UPDATE")
		 vUrl = "/product/updateProduct";
	 else{
		 vUrl = "/product/insertProduct";
		 
		 //코드 확인
		 if(!chkSameCode_prodcutCode($('#pm_code_99200').val())){
			 return;
		 };
	 }
	 */
	 
	$.ajax({
	   type : "POST",
	   global: true,
	   async : false,
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
				
				//alert("v_nodeProductCode : " + v_nodeProductCode);
				fnSelectNodeList_99200(v_nodeProductCode);
				
				fnMakeFtree99200();
				
				//닫기
				$("#tempClose99200").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
	
	//fnMakeFtree99200();
}

/**
 * @함수명: chkSameCode_prodcutCode
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 코드 유효성 확인
 * @param 
 */

function chkSameCode_prodcutCode(pm_code){
	var res = true;
	$.ajax({
		url : "/product/checkProductCode",
		data:{"pm_code":pm_code},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false,
	    success : function(data) {
	    	if(data!=null && data.result==null){
	    		res=true;
	    	}else{
				if(data.result.length > 0){
					var vo = data.result[0];  
					if(vo.pm_code!=null && vo.pm_code!='' && vo.delete_at=='N'){
						//alert(vo.pm_code + " : " +vo.pm_nm + "\n사용중인 코드입니다."); 
						$("#pm_code_99200").focus();
						res=false;
					}else if(vo.pm_code!=null && vo.pm_code!='' && vo.delete_at=='Y'){
						$("#pm_code_99200").focus();
						$("#save_type_99200").val("UPDATE");
						//alert("aa : " + $("#save_type_99200").val());
						res=true;
					}else{
						res=true;
						$("#save_type_99200").val("INSERT");
					}
				}else{
					res=true;
					$("#save_type_99200").val("INSERT");
				}
			}
	    }
		
	});
	return res;
}

/**
 * @함수명: fnFirstNodeSelect_99200
 * @작성일: 2015. 10. 06.
 * @작성자: 최수영
 * @설명: 메뉴코드 정보 수정
 * @param 
 */
function fnFirstNodeSelect_99200(){
	var firstNodeCode="";
	$.ajax({
		url : "/product/firstTreeProduct",
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
	
	fnSelectNodeList_99200(firstNodeCode);
}

/**
 * @함수명: popControlInit_99200
 * @작성일: 2015. 10. 06.
 * @작성자: 최수영
 * @설명: 팝업 컨트롤 초기화
 * @param 
 */
function popControlInit_99200(){
	
	$("#pm_code_99200").onlyNum();
	$("#pm_sort_ordr_99200").onlyNum();
	
	$('#pm_code_99200').val('');
	$("#pm_code_99200").prop("readonly", false);
	$('#pm_nm_99200').val('');
	$('#pm_parent_no_99200').val('');
	$('#pm_dp_99200').val("");
	$('#pm_sort_ordr_99200').val('');
	$('#pm_memo_99200').val('');
	$('input:radio[id="pop_use_at_y_99200"]').prop("checked", true);
	
	var tempVal = "<option value ='' selected=\"selected\"> 선택 </option>";
	$("#pm_parent_no_D1_99200, #pm_parent_no_D2_99200, #pm_parent_no_D3_99200, #pm_parent_no_D4_99200").html(tempVal);
	$("#pm_parent_no_D1_99200, #pm_parent_no_D2_99200, #pm_parent_no_D3_99200, #pm_parent_no_D4_99200").prop("disabled", false);
	
	$("#pm_code_99200").focus();
}


