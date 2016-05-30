
var selectEmpGLIdArray = null;
var selectEmpListIdArray = null;
var selectEmpGLNameArray = null;
var selectEmpListNameArray = null;
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#authPop99700").instancePopUp();
	
	//input 달력
	//$("#searchToDate99700").winCal(baseOptions);	
	//$("#searchFromDate99700").winCal(baseOptions);
	
	// 이벤트 등록
	
	fnSetEventComponent();	
	//권한그룹 콤보
	fnGetAuthGroupList_99700();
	
	//지점 콤보
	fnGetSelectOmList_99700();
	
});



/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	
	//사원 검색
	$("#searchEmpBtn99700").click(function(){
		selectEmpList_99700();
	});
	
	//선택 완료
	$("#empSelectMove").click(function(){
		$( "#selectEmpListL option:selected" ).each(function() {
			$("#selectEmpListR").append($(this));
		});
	});
	//모두 선택
	$("#empSelectAllMove").click(function(){
		$( "#selectEmpListL option" ).each(function() {
			$("#selectEmpListR").append($(this));
		});
	});
	
	// 선택 취소
	$("#empSelectCancle").click(function(){
		$( "#selectEmpListR option:selected" ).each(function() {
			$("#selectEmpListL").append($(this));
	    });
	});
	//모두 취소
	$("#empSelectAllCancle").click(function(){
		$( "#selectEmpListR option" ).each(function() {
			$("#selectEmpListL").append($(this));
		});
	});
	//그룹 선택 변경
	$("#menuAuthGroup_99700").change(function(){
		fnGetMenuAuthList($(this).val());
		fnGetEmpAuthList($(this).val());
	});
	
	//그룹 선택 변경
	$("#authHeader99700 .inputCheck input[type='checkbox'] ").change(function(){
//		alert($(this).is(":checked"));
		var classNm = $(this).data("atuh-type");
		var checked = $(this).is(":checked");
		
		$("."+classNm+":input[type='checkbox']").prop("checked",checked);
	});
	
	//그룹저장
	$("#authGroupInsert99700").click(function(){
		popControlInit_99700();
		$("#authPop99700").show();
		$("#save_type_99700").val('INSERT');
		$("#ma_group_name_99700").focus();
		$("#title99700").text("메뉴권한그룹 등록");
	});
	//그룹수정
	$("#authGroupUpdate99700").click(function(){
		if($("#menuAuthGroup_99700").val()==""){
			alert("메뉴권한그룹을 선택 하십시오.");
			$('#menuAuthGroup_99700').focus();
			return;
		}
		popControlInit_99700();
		fnUpdateAuthGroup_99700($("#menuAuthGroup_99700").val());
		$("#save_type_99700").val('UPDATE');
		
		$("#title99700").text("메뉴권한그룹 수정");
	});
	//그룹조회
	$("#authSearch99700").click(function(){
		if($('#menuAuthGroup_99700').val()==''){
			alert('메뉴권한그룹을 선택하세요.');
			$('#menuAuthGroup_99700').focus();
			return;
		}
		
	});
	//메뉴그룹 저장
	$("#menuAuthSave99700").click(function(){
		fnSaveMenuAuthGroup99700("");
	});
	
	
	
	$("#tempAuthGroupClose99700, #tempCloseX99700").click(function(){
		$("#authPop99700").hide();
	});
	
	$("#tempAuthGroupSave99700").click(function(){
		fnSaveAuthGroup99700();
	});
	
	
}
function fnSaveMenuAuthGroup99700(){
	var authArr = [];
	var empArr = [];
	var ma_group_seq = $("#menuAuthGroup_99700").val();
	$(".auth-group").each(function() {
		var mNo = $(this).find(".mNo").val();
		var visi = $(this).find(".visi").is(":checked") ? "Y":"N";
		var inst = $(this).find(".inst").is(":checked") ? "Y":"N";
		var updt = $(this).find(".updt").is(":checked") ? "Y":"N";
		var del = $(this).find(".del").is(":checked") ? "Y":"N";
		var down = $(this).find(".down").is(":checked") ? "Y":"N";
		var etc = $(this).find(".etc").is(":checked") ? "Y":"N"; 
		authArr.push({
			"ma_group_seq":ma_group_seq,
			"m_no":mNo,
			"ma_visible" : visi,
			"ma_insert" : inst,
			"ma_update" : updt,
			"ma_del"  : del ,
			"ma_down" : down,
			"ma_etc"  : etc 
		});
//		console.log("visi : " + visi);
//		console.log("inst : " + inst);
//		console.log("updt : " + updt);
//		console.log("del  : " + del );
//		console.log("down : " + down);
//		console.log("etc  : " + etc );
	});
	
	$( "#selectEmpListR option" ).each(function() {
		empArr.push({
			"ma_group_seq":ma_group_seq,
			"em_no":$(this).val()
		});
//		console.log("em_no  : " + $(this).val() );
	});
	$.ajax({
		url : "/auth/save",
		data:{
			"ma_group_seq":ma_group_seq,
			"authArr":authArr,
			"empArr":empArr
		},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	if(data > 0){
	    		alert("저장 되었습니다.");
	    	}
//			var listHtml = "";
//			var empArray = json_obj['data'][0]['empArray'];
			
			//selectEmpGLIdArray = new Array();
			//selectEmpGLNameArray = new Array();
			
		    
	    }
	});
	
}
// 메뉴별 권한 목록 조회
function fnGetMenuAuthList(ma_group_seq) {
	$.ajax({
		url : "/auth/menuAuthList",
		data:{"ma_group_seq":ma_group_seq},
	    type : "POST",
	    dataType : "json",
	    global:true,
	    cache : false,
	    async : false,
	    success : function(data) {
	    	$("#tbody99700").html("");
			for(var i=0; i<data.result.length; i++){
				var html = "";
				var vo= data.result[i];     
				
				var depth 		= vo.m_depth;
				var m_nm 		= vo.m_nm;
				var m_no 		= vo.m_no;
				var m_parent_no = vo.m_parent_no;
				var ma_visible 	= vo.ma_visible;
		        var ma_insert  	= vo.ma_insert;        
		        var ma_update  	= vo.ma_update; 
		        var ma_del     	= vo.ma_del;    
		       	var ma_down    	= vo.ma_down;
		       	var ma_etc    	= vo.ma_etc;
		       	
//		       	alertmg += ","+m_seq;
				html += '<tr class="auth-group" data="'+m_no+'">';
				
				if(depth == 1){
					html += '<th colspan="3" class="align-l" >';
					html += m_nm;
					html += '<input type="hidden" class="mNo" value="'+m_no+'" >';
					html += '</th>';
				}else if(depth == 2){
					html += '<th></th>';
					html += '<th colspan="2" class="align-l">';
					html += m_nm;
					html += '<input type="hidden" class="mNo" value="'+m_no+'" >';
					html += '</th>';
				}else{
					html += '<th  colspan="2"  ></th>';
					html += '<th class="align-l">';
					html += m_nm;
					html += '<input type="hidden" class="mNo" value="'+m_no+'" >';
					html += '</th>';
				}
				
				html += '<td><div class="inputCheck"><input type="checkbox" class="visi" id="authCheckVisi'+(i)+'" '+(ma_visible == "Y" ? "checked":"")+' ><label for="authCheckVisi'+(i)+'"></label></div></td>';
				html += '<td><div class="inputCheck"><input type="checkbox" class="inst" id="authCheckInst'+(i)+'" '+(ma_insert == "Y" ? "checked":"")+' ><label for="authCheckInst'+(i)+'"></label></div></td>';
				html += '<td><div class="inputCheck"><input type="checkbox" class="updt" id="authCheckUpdt'+(i)+'" '+(ma_update == "Y" ? "checked":"")+' ><label for="authCheckUpdt'+(i)+'"></label></div></td>';
				html += '<td><div class="inputCheck"><input type="checkbox" class="del" id="authCheckDel'+(i)+'" '+(ma_del == "Y" ? "checked":"")+' ><label for="authCheckDel'+(i)+'"></label></div></td>';
				html += '<td><div class="inputCheck"><input type="checkbox" class="down" id="authCheckDown'+(i)+'" '+(ma_down == "Y" ? "checked":"")+' ><label for="authCheckDown'+(i)+'"></label></div></td>';
				html += '<td><div class="inputCheck"><input type="checkbox" class="etc" id="authCheckEtc'+(i)+'" '+(ma_etc == "Y" ? "checked":"")+' ><label for="authCheckEtc'+(i)+'"></label></div></td>';
				html += '</tr>';
				if(depth == 1){
					$("#tbody99700").append(html);
				}else{
					$(html).insertAfter($(".auth-group[data='"+m_parent_no+"']").last());
				}
			}
	    
	    }
	});
}

/**
 * @함수명: selectAuthEmpList_99700
 * @작성일: 2015. 10. 14.
 * @작성자: 최수영
 * @설명: 해당권한 그룹의 사원리스트
 * @param 
 */
function fnGetEmpAuthList(ma_group_seq){
	$.ajax({
		url : "/auth/authGroupEmpList",
		data:{"ma_group_seq":ma_group_seq},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var listHtml = "";
//			var empArray = json_obj['data'][0]['empArray'];
			
			//selectEmpGLIdArray = new Array();
			//selectEmpGLNameArray = new Array();
			
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var empVo = data.result[i];     
					var em_dty_nm = empVo.em_dty_nm;
					em_dty_nm = em_dty_nm == "" ? "" : "( "+em_dty_nm+" ) - ";
	        		listHtml += "<option value='"+empVo.em_no+"'>"+em_dty_nm+empVo.em_nm + "</option>";
	        		
	        		//selectEmpGLIdArray[i] = empArray[i]['eSeq'];
					//selectEmpGLNameArray[i] = empArray[i]['eName'];
	        	}
				
			}	
			$("#selectEmpListR").html(listHtml);
		    
	    }
	});
}

/**
 * @함수명: selectEmpList_99700
 * @작성일: 2015. 10. 14.
 * @작성자: 최수영
 * @설명: 사원조회
 * @param 
 */
function selectEmpList_99700(){
	$.ajax({
		url : "/auth/empList",
		data:{"om_code":$.trim($('#om_99700').val()), "em_nm":$.trim($('#em_nm_99700').val())},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var listHtml = $("<select/>");
				for(var i=0; i<data.result.length; i++){
					var empVo = data.result[i];
					var em_dty_nm = empVo.em_dty_nm;
					em_dty_nm = em_dty_nm == "" ? "" : "( "+em_dty_nm+" ) - ";
					var option = $("<option value='"+empVo.em_no+"'>"+em_dty_nm+empVo.em_nm + "</option>");
	        		listHtml.append(option);
	        	}
				$( "#selectEmpListR option" ).each(function() {
					listHtml.find("option[value='"+$(this).val()+"']").remove();
				});
				$("#selectEmpListL").html(listHtml.html());
	    }
	});
}

/**
 * @함수명: fnGetAuthGroup_99700
 * @작성일: 2015. 10. 14.
 * @작성자: 최수영
 * @설명: 권한그룹 콤보
 * @param 
 */
function fnGetAuthGroupList_99700(){
	$.ajax({
		url : "/auth/authGroupList",
		data:{"cm_code":""},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	//		var listHtml = "<option value='' selected='selected'>선택</option>";
			var listHtml = "";
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var authGroupVo = data.result[i];     
	        		listHtml += "<option value='"+authGroupVo.ma_group_seq+"'>"+authGroupVo.ma_group_name + "</option>";
	        	}
			}	
			$("#menuAuthGroup_99700").html(listHtml);
			fnGetMenuAuthList($("#menuAuthGroup_99700").val());
			fnGetEmpAuthList($("#menuAuthGroup_99700").val());
	    }
	});
}

/**
 * @함수명: fnGetSelectOmList_99700
 * @작성일: 2015. 10. 14.
 * @작성자: 최수영
 * @설명: 지점콤보
 * @param 
 */
function fnGetSelectOmList_99700(){
	$.ajax({
		url : "/organization/byCp/list",
		data:{"cm_code":""},
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
		$("#om_99700").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: fnSaveAuthGroup99700
 * @작성일: 2015. 10. 14.
 * @작성자: 최수영
 * @설명: 권한그룹 저장
 * @param 
 */

function fnSaveAuthGroup99700(){
	var vUrl = "/auth/saveAuthGroup";
	var param = $('#frm99700').serialize();

	 if($.trim($('#ma_group_name_99700').val()) == ''){
		 alert("메뉴권한 그룹명을 입력하세요.");
		 $("#ma_group_name_99700").focus();
		 return;
	 }
	 //alert(param);
	 
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   url  : vUrl,
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
			if(data.resultCnt> 0){
				alert('저장 되었습니다.');
				fnGetAuthGroupList_99700();
				//닫기
				$("#tempAuthGroupClose99700").click();
				
				return;
			}else{
				alert('저장되지 않았습니다.');
				alert(data.dto[0].res_msg);
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}


/**
 * @함수명: fnUpdateAuthGroup_99700
 * @작성일: 2015. 10. 14.
 * @작성자: 최수영
 * @설명: 권한그룹 정보 수정
 * @param 
 */
function fnUpdateAuthGroup_99700(ma_group_seq){
	$("#save_type_99700").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_99700();

	//팝업 show
	$("#authPop99700").show();
	
	//해당 로우 조회
	$.ajax({
		url : "/auth/authGroupRow",
		data:{"ma_group_seq": ma_group_seq},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var authGroupVo = data.result[0];    
				$("#ma_group_seq_99700").val(authGroupVo.ma_group_seq);
				$("#ma_group_name_99700").val(authGroupVo.ma_group_name);
				//$("input:radio[name=use_at]:input[value=" + organizationVo.use_at + "]").attr("checked", true);
				$("#ma_group_memo_99700").val(authGroupVo.ma_group_memo);

			}else{
				
			}	
	    }
	});	
	
	$("#ma_group_name_99700").focus();
}

function popControlInit_99700(){
	$("#ma_group_seq_99700").val('');
	$("#ma_group_name_99700").val('');
	$("#ma_group_memo_99700").val('');
}