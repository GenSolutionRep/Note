
var v_nodeMenuCode = "";

$(window).load(function(){
	alert("11");
	fnMenuFirstNodeSelect();
});


/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	fnMakeEventComponent();	// 버튼 이벤트 등록
//	fnGetTestList("파라메터 잘넘어갑니다...");
		
	$(".form_date").datetimepicker({
		language:  'ko',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$("#baseMenuList").sTree({
		url:"/menu/list",
		root:true,
		key:"m_no",
		val:"m_nm",
		allOpen : true,
		className:"mtree",
		//data:{"user_id":"123"},
		click:  function(data){
			//console.log("data3 : " + data.code);
			v_nodeMenuCode=data.code;
			fnSelectNodeMenuList(data.code);
		}
    });
				
});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent(){
	//메뉴 추가 버튼
	$("#btnMenuInsert").click(function(){
		//콤보초기화
		//comboInit();
		
		//팝업 컨트롤 초기화
		menuPopControlInit();
		
		if(v_nodeMenuCode != ""){
			//선택한 메뉴의 상위 메뉴 코드를 가져온다
			$.ajax({
				url : "/menu/menuParent",
				data:{"m_no":v_nodeMenuCode},
			    type : "POST",
			    dataType : "json",
			    cache : false ,
			    success : function(data) {
			    	if(data!=null){
				    	var res = data.result[0];
				    	if(res.m_parent_code==null){
					    	$('#m_parent_no').val('');
					    	$('#m_parent_name').val('최상위 메뉴');
				    	}else{
				    		$('#m_parent_no').val(res.m_parent_no);
					    	$('#m_parent_name').val(res.m_parent_name);
				    	}
			    	}
			    }
			});
		}else{
			$('#m_parent_no').val('');
			$('#m_parent_name').val('최상위 메뉴');
		}
				
		$("#tempPop99600").show();
		$("#save_type").val("INSERT");
	});
	
	//팝업 텍스트 닫기 텍스트 클릭
	$("#tempCloseX99600").click(function(){
		$("tempPop99600").hide();
	});
	
	//팝업 저장버튼
	$("#btnMenuPopSave").click(function(){
		fnSaveMenu();
	});
	
	//팝업 닫기버튼
	$("#btnMenuPopClose").click(function(){
		$("tempPop99600").hide();
	});
	
	/*
	$("#m_parent_no1").change(function() {
		var listHtml = "";
		alert()
		$.ajax({
			url : "/menu/menuParentList",
		    type : "POST",
		    data:{"m_no": $("#m_parent_no1").val()},
		    dataType : "json",
		    cache : false ,
		    async : false,
		    success : function(data) {	
		    	if(data!=null && data.result.length > 0){
		    		listHtml += "<select id='m_parent_no"+res.m_no+"' name ='m_parent_no"+res.m_no+"' style='margin-right: 6px;'>";
		    		listHtml += "<option value ='' selected=\"selected\">상위메뉴 선택</option>";
		    		for(var i=0; i<data.result.length; i++){
		    			var res = data.result[i];     
		    			listHtml += "<option value ='"+res.m_no+"'>"+ res.m_nm +"</option>";
		    		}
		    		listHtml += "</select>";
		    	}else{
		    		listHtml += "";
		    	}
		    	
		    	$("#divMenuCombo").html($("#divMenuCombo").html()+listHtml);
			}
		});	
        //fnGetSelectReferenceEmp($("#selectCd option:selected").val());
    });

	*/
}

/**
 * @함수명: fnSelectNodeMenuList
 * @작성일: 2015. 09. 16.
 * @작성자: 최수영
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */

function fnSelectNodeMenuList(m_no){
	$.ajax({
		url : "/menu/menuList",
	    //data:{"param":$("#m_no").val()},
		data:{"m_no":m_no},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	
		var menuListHtml = "";
		
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var res = data.result[i];     
				menuListHtml += "<tr>";
        		menuListHtml += "<td>" + res.m_no + "</td>";
    			menuListHtml += "<td>" + res.m_nm + "</td>";
    			menuListHtml += "<td>" + res.m_url + "</td>";
    			menuListHtml += "<td>" + res.m_order + "</td>";
    			menuListHtml += "<td>" + res.m_use_yn + "</td>";
    			menuListHtml += "<td>";
    			menuListHtml += "	<button type='button' onclick=\"fnMenuInfoUpdate('"+res.m_no+"')\"; <span class='glyphicon glyphicon-edit'></span>수정</button>&nbsp;&nbsp;";
    			menuListHtml += "</td>";
    			menuListHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#menuTbody").html(menuListHtml);
	    
	    }
	});
}

/**
 * @함수명: fnMenuInfoUpdate
 * @작성일: 2015. 09. 16.
 * @작성자: 최수영
 * @설명: 메뉴코드 정보 수정
 * @param 
 */
function fnMenuInfoUpdate(m_no){
	$("#save_type").val("UPDATE");
	
	//팝업 컨트롤 초기화
	menuPopControlInit();
	
	//콤보초기화
	//comboInit();
	
	//팝업 show
	$("#tempPop99600").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/menu/menuRow",
		data:{"m_no":m_no},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var res = data.result[0];     

				$("#m_no").val(res.m_no);
				$("#m_nm").val(res.m_nm);
				$("#m_parent_no").val(res.m_parent_no);
				$("#m_parent_name").val(res.m_parent_name);
				$("#m_url").val(res.m_url);
				$("input:radio[name=m_use_yn]:input[value=" + res.m_use_yn + "]").attr("checked", true);
				$("#m_order").val(res.m_order);
				$("#m_note").val(res.m_note);
			}else{
				
			}	
	    }
	});	
}

function fnSaveMenu(){
	var vUrl = "";
	var vType = $('#save_type').val();
	var param = $('#frmMenu').serialize();
	
	 if($.trim($('#m_nm').val()) == ''){
		 alert("메뉴명을 입력하세요");
		 $("#m_nm").focus();
		 return;
	 }
	 
	 if($.trim($('#m_order').val()) == ''){
		 alert("정렬순서를 입력하세요.");
		 $("#m_order").focus();
		 return;
	 }
	 
	 if(vType=="UPDATE")
		 vUrl = "/menu/updateMenu";
	 else
		 vUrl = "/menu/insertMenu";
	 
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
				fnSelectNodeMenuList(v_nodeMenuCode);
				//$(SearchForm).attr({action:Url, method:'post'}).submit();
				
				//닫기
				$("#btnMenuPopClose").click();
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
	
	$("#baseMenuList").sTree();
}


/**
 * @함수명: fnMenuFirstNodeSelect
 * @작성일: 2015. 09. 16.
 * @작성자: 최수영
 * @설명: 메뉴코드 정보 수정
 * @param 
 */
function fnMenuFirstNodeSelect(){
	var firstNodeCode;
	$.ajax({
		url : "/menu/firstTreeMenu",
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    async : false,
	    success : function(data) {	
	    	alert(data.result);
			if(data!=null)
				firstNodeCode = data.result;
			else
				firstNodeCode = 0;
		}
	});	
	
	fnSelectNodeMenuList(firstNodeCode);
}

/**
 * @함수명: comboInit
 * @작성일: 2014. 09. 16.
 * @작성자: 최수영
 * @설명: 팝업 콤보 초기화
 * @param 
 */
/*
function comboInit(){
	var listHtml = "";

	/*
	$.ajax({
		url : "/menu/menuParentList",
	    type : "POST",
	    
	    dataType : "json",
	    cache : false ,
	    async : false,
	    success : function(data) {	
	    	
	    	if(data!=null && data.result.length > 0){
	    		listHtml += "<option value ='' selected=\"selected\">상위메뉴 선택</option>";
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.m_no+"'>"+ res.m_nm +"</option>";
	    		}
	    	}else{
	    		listHtml += "<option value =''>"+"없음"+"<option>";
	    	}
	    	
	    	$("#m_parent_no").html(listHtml);
		}
	});	
	
}
*/

/**
 * @함수명: menuPopControlInit
 * @작성일: 2014. 09. 16.
 * @작성자: 최수영
 * @설명: 메뉴팝업 컨트롤 초기화
 * @param 
 */
function menuPopControlInit(){
	$('#m_no').val('');
	$('#m_nm').val('');
	$('#m_parent_no').val('');
	$('#m_parent_name').val('');
	$('#m_order').val('');
	$('#m_url').val('');
	$('#m_note').val('');
	$('#m_use_yn').val('Y');
	$('input:radio[id=pop_m_use_y]').attr("checked", true);
}


