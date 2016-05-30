
var v_nodeMenuCode = "";

/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	fnMakeEventComponent_99600();	// 버튼 이벤트 등록
		
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
	/*
	$("#baseMenuList_99600").sTree({
		url:"/menu/list",
		//root:true,
		key:"m_no",
		val:"m_nm",
		//allOpen : true,
		className:"mtree",
		data:{"user_id":"123"},
		click:  function(data){
			//console.log("data3 : " + data.code);
			//$("#selectedCodeName_99600").text(data.m_nm);	//선택코드명 > title
			v_nodeMenuCode=data.code;
			fnSelectNodeMenuList_99600(data.code);
		}
    });
	*/
	fnMakeFtree99600();
	
	fnSelectNodeMenuList_99600('');
	
});

/**
 * @함수명: fnMakeFtree
 * @작성일: 2015. 09. 24
 * @작성자: 
 * @설명: 트리 구조 생성및 이벤트
 * @param 
 */
function fnMakeFtree99600(){
	/*
	$("#baseMenuList_99600").sTree({
		url:"/menu/list",
		//root:true,
		key:"m_no",
		val:"m_nm",
		//allOpen : true,
		className:"mtree",
		data:{"user_id":"123"},
		click:  function(data){
			//console.log("data3 : " + data.code);
			//$("#selectedCodeName_99600").text(data.m_nm);	//선택코드명 > title
			v_nodeMenuCode=data.code;
			fnSelectNodeMenuList_99600(data.code);
		}
    });
	*/
	$("#baseMenuList_99600").fTree({
		url :"/menu/list",
		key:"m_no",
		val:"m_nm",
		data:{"user_id":"", "m_use_yn":"A"},
		p_key:"m_parent_no",
		tagId:"menuMTree",
		click:  function(data){
			v_nodeMenuCode = data.key;
			fnSelectNodeMenuList_99600(data.key);
			$("#selectedCodeName_99600").text(data.val);	//선택코드명 > title
		}
	});
}

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent_99600(){
	
	$("#tempPop99600").instancePopUp();
	
	$("#m_parent_no_D1_99600").change(function(){
		$("#m_parent_no_99600").val($(this).val());
		$("#m_depth_99600").val("2");
		cboSelectDepthList_99600($(this).val(), 2);
	});	
	
	$("#m_parent_no_D2_99600").change(function(){
		if($(this).val()==""){
			$("#m_parent_no_99600").val($("#m_parent_no_D1_99600").val());
			$("#m_depth_99600").val("2");
		}else{
			$("#m_parent_no_99600").val($(this).val());
			$("#m_depth_99600").val("3");
		}
		//alert('2 : ' + $("#m_parent_no_99600").val());
	});
	
	//메뉴 추가 버튼
	$("#tempInsert99600").click(function(){
		//alert(v_nodeMenuCode);
		
		//팝업 컨트롤 초기화
		popControlInit_99600();
		
		//회사
		cboSelectDepthList_99600('', 1);
		$("#m_parent_no_D1_99600").val("");
		$("#m_depth_99600").val("1");
		
		var listHtml = "<option value =''>선택</option>";
		$("#m_parent_no_D2_99600").html(listHtml);
		$("#tempPop99600").show();
		$("#save_type_99600").val("INSERT");
		$("#m_nm_99600").focus();
		
	});
	
	//팝업 저장버튼
	$("#tempSave99600").click(function(){
		fnSaveMenu_99600();
	});
	
	//팝업 닫기버튼
	$("#tempClose99600, #tempCloseX99600").click(function(){
		$("#tempPop99600").hide();
	});
}

/**
 * @함수명: cboSelectDepthList_99600
 * @작성일: 2015. 09. 24.
 * @작성자: 최수영
 * @설명: 콤보 Depth 
 * @param 
 */
function cboSelectDepthList_99600(m_no,  m_depth){
	$.ajax({
		url : "/menu/depthList",
	    data:{"m_no":m_no,"m_depth": m_depth},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					if(data!=null && data.result.length > 0){
			    		listHtml = "<option value ='' selected=\"selected\">상위메뉴 선택</option>";
			    		for(var i=0; i<data.result.length; i++){
			    			var res = data.result[i];     
			    			listHtml += "<option value ='"+res.m_no+"'>"+ res.m_nm +"</option>";
			    		}
			    	}else{
			    		
			    	}
	        	}	
			}else{
				listHtml += "<option value =''>선택</option>";
			}	
			
			if(m_depth==1)
				$("#m_parent_no_D1_99600").html(listHtml);
			else if(m_depth==2)
				$("#m_parent_no_D2_99600").html(listHtml);
	    }
	});
}
/**
 * @함수명: fnSelectNodeMenuList
 * @작성일: 2015. 09. 16.
 * @작성자: 최수영
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */

function fnSelectNodeMenuList_99600(m_no){
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
				var menuVo = data.result[i];   
	
				if(i==0 && m_no!='')
					menuListHtml += "<tr class ='cell_active'  style='font-weight: bold;'>";
				else
					menuListHtml += "<tr>";
				
        		//menuListHtml += "<td>" + menuVo.m_no + "</td>";
    			menuListHtml += "<td>" + (i+1) + "</td>";
    			menuListHtml += "<td class='txt_left'>" + menuVo.m_nm + "</td>";
    			menuListHtml += "<td>" + menuVo.m_url + "</td>";
    			menuListHtml += "<td>" + menuVo.m_order + "</td>";
    			if(menuVo.m_use_yn=='Y')
    				menuListHtml += "<td style='color: blue;'>" + menuVo.m_use_yn + "</td>";
    			else
    				menuListHtml += "<td style='color: red;'>" + menuVo.m_use_yn + "</td>";
    			menuListHtml += "<td>";
    			menuListHtml += "	<button type='button' class = 'white' onclick=\"fnMenuInfoUpdate_99600('"+menuVo.m_no+"')\";>수정</button>";
    			menuListHtml += "</td>";
    			menuListHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody99600").html(menuListHtml);
	    
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
function fnMenuInfoUpdate_99600(m_no){
	$("#save_type_99600").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_99600();

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
				var menuVo = data.result[0];     
				var listHtml ="<option value ='' selected=\"selected\">선택</option>";
				
				if(menuVo.m_depth=="1") {
					//$("#m_parent_no_D1_99600, #m_parent_no_D2_99600").attr("disabled", true);
					cboSelectDepthList_99600("", 1);
					$("#m_parent_no_D2_99600").html(listHtml);
				}else if(menuVo.m_depth=="2") {
					cboSelectDepthList_99600("", 1);
					$("#m_parent_no_D1_99600").val(menuVo.m_parent_no);
					cboSelectDepthList_99600(menuVo.m_parent_no, 2);
					$("#m_parent_no_D2_99600").val("");
				}else if(menuVo.m_depth=="3") {
					cboSelectDepthList_99600("", 1);
					$("#m_parent_no_D1_99600").val(menuVo.up_depth_code);
					cboSelectDepthList_99600(menuVo.up_depth_code, 2);
					$("#m_parent_no_D2_99600").val(menuVo.m_parent_no);
				}
				$("#m_parent_no_99600").val(menuVo.m_parent_no);
				$("#m_no_99600").val(menuVo.m_no);
				$("#m_nm_99600").val(menuVo.m_nm);
				$("#m_url_99600").val(menuVo.m_url);
				$("input:radio[name=m_use_yn]:input[value=" + menuVo.m_use_yn + "]").attr("checked", true);
				$("#m_order_99600").val(menuVo.m_order);
				$("#m_note_99600").val(menuVo.m_note);
				$("#m_depth_99600").val(menuVo.m_depth);
			}else{
				
			}	
	    }
	});	
	
	$("#m_nm").focus();
}	$(".tempPop99600").scrollTop(0);

function fnSaveMenu_99600(){
	var vUrl = "";
	var vType = $('#save_type_99600').val();
	var param = $('#frm99600').serialize();
	
	 if($.trim($('#m_nm_99600').val()) == ''){
		 alert("메뉴명을 입력하세요");
		 $("#m_nm_99600").focus();
		 return;
	 }
	 
	 /*
	 if($.trim($('#m_order_99600').val()) == ''){
		 alert("정렬순서를 입력하세요.");
		 $("#m_order_99600").focus();
		 return;
	 }
	 */
	 
	 /*
	 if(vType=="UPDATE")
		 vUrl = "/menu/updateMenu";
	 else
		 vUrl = "/menu/insertMenu";
	  */
	 vUrl = "/menu/saveMenu";
	 
	 
	$.ajax({
	   type : "POST",
	   async : false,
	   cache : false,
	   global: true,
	   url  : vUrl,
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
		   	if(data.dto[0].res_code==-1){
				alert('저장되지 않았습니다.');
				alert(data.dto[0].res_msg);
			}if(data.dto[0].res_code==0){
				alert('저장 되었습니다.');
				fnSelectNodeMenuList_99600(v_nodeMenuCode);
				
				//닫기
				$("#tempClose99600").click();
			}
			$("#leftMenu").leftMenu();
			fnMakeFtree99600();
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
	
	//$("#baseMenuList_99600").sTree();
	
}


/**
 * @함수명: fnMenuFirstNodeSelect_99600
 * @작성일: 2015. 09. 16.
 * @작성자: 최수영
 * @설명: 메뉴코드 정보 수정
 * @param 
 */
function fnMenuFirstNodeSelect_99600(){
	var firstNodeCode;
	$.ajax({
		url : "/menu/firstTreeMenu",
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
	fnSelectNodeMenuList_99600(firstNodeCode);
}

/**
 * @함수명: comboInit
 * @작성일: 2014. 09. 16.
 * @작성자: 최수영
 * @설명: 팝업 콤보 초기화
 * @param 
 */

/**
 * @함수명: menuPopControlInit
 * @작성일: 2014. 09. 16.
 * @작성자: 최수영
 * @설명: 메뉴팝업 컨트롤 초기화
 * @param 
 */
function popControlInit_99600(){
	$('#m_no_99600').val('');
	$('#m_nm_99600').val('');
	$('#m_order_99600').val('');
	$("#m_order_99600").onlyNum();
	$('#m_url_99600').val('');
	$('#m_note_99600').val('');
	$('#m_depth_99600').val('');
	$("#m_parent_no_D1_99600, #m_parent_no_D2_99600").attr("disabled", false);
	$('input:radio[id=pop_m_use_y_99600]').attr("checked", true);
	
	$("#m_nm_99600").focus();
}



