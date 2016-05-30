/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */


$(document).ready(function(){
	
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#optionPop99500").instancePopUp();
	$("#optionTrtPop99500").instancePopUp();
	
	
	$("#cOrder").onlyNum();
	//트리생성
	fnMakeFtree();
	
	// 이벤트 등록
	fnSetEventComponent();
	
	$("#optionOrder").onlyNum();

});


/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	
	//등록버튼
	$("#optionOpen99500").click(function(){
		if($("#optionType").val() == ""){
			alert("업무일지 항목을 선택해주세요");
			return;
		}
		fnOptionPopClear(); // 입력폼 초기화
		$("#optionNickName").prop("readonly",false);
		$("#optionTitle").text("업무일지 항목 등록");
		if($("#optionType").val() =="0000000067" || $("#optionType").val() =="0000000072" ){
			$("#trtGroup99500").show();
			$("#optionName").prop("readonly",true);
			fnGetTrtComboDepthList("",1);
		}else{			
			$("#trtGroup99500").hide();
			$("#optionName").prop("readonly",false);
		}
		$("#optionPop99500").show();
	});
	
	$(".trt-change-group").change(function(){
		var depth = $(this).data("trt-depth");
//		if(depth == 5){
			if($(this).val() == ""){
				$("#optionName").val("");
				$("#optionParentCode").val("");
			}else{
				$("#optionName").val($(this).find("option:selected").text());
				$("#optionParentCode").val($(this).val());
			}
			
//		}else{
			fnGetTrtComboDepthList($(this).val(),depth+1);
//		}
//		if($(this).val()==""){
//			$("#pm_parent_no_99200").val("");
//			$("#pm_dp_99200").val("1");
//			$("#pm_parent_no_D2_99200, #pm_parent_no_D3_99200, #pm_parent_no_D4_99200").html(tempVal);
//		}
//		else{
//			$("#pm_parent_no_99200").val($(this).val());
//			$("#pm_dp_99200").val("2");
//			cboSelectDepthList_99200($(this).val(), 2);
//		}
	});
	
	
	$("#optionTrtOpen99500").click(function(){
		$("#optionTrtPop99500").show();
	});
	
	// 닫기버튼
	$("#optionPopClose99500, #optionPopCloseX99500").click(function(){
		$("#optionPop99500").hide();
	});
	// 저장버튼
	$("#optionPopSave99500").click(function(){
		fnSaveActivityOption();
	});
}

/**
 * @함수명: fnGetTrtComboDepthList
 * @작성일: 2015. 09. 24.
 * @작성자: 김진호
 * @설명: 콤보 Depth 
 * @param 
 */
function fnGetTrtComboDepthList(pm_code, pm_dp){
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
	    		listHtml = "<option value ='' > 선택 </option>";
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.pm_code+"'>" + res.pm_nm +"</option>";
	    		}
			}else{
				listHtml += "<option value =''> 선택 </option>";
			}	
			
			$(".trt-change-group[data-trt-depth='"+pm_dp+"']").html(listHtml);
			for (var i = 0; i < (5-pm_dp); i++) {
				$(".trt-change-group[data-trt-depth='"+(pm_dp+(i+1))+"']").html("<option value =''> 선택 </option>");
			}
	    }
	});
}

/**
 * @함수명: fnMakeFtree
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 트리 구조 생성및 이벤트
 * @param 
 */
function fnMakeFtree(){
	$("#optionCodeList").fTreeNew({
		url : "/option/tree",
		key:"oi_code",
		val:"oi_nm",
		p_key:"oi_type",
		tagId:"opTree",
		rootKey:"0000000020",
		rootVal : "업무일지항목" ,
//		allOpen : true,
		click:  function(data){
//			console.log("key : " + data.key)
//			console.log("val : " + data.val)
//			console.log("p_key : " + data.p_key)
//			console.log("depth : " + data.depth)
//			console.log("depth : " + data.add_flag)
//			fnGetCodeList(data.key);
			if(data.depth == 2 || data.add_flag == 'Y' ){
				fnGetSelectedOptionList(data.key);
				$("#optionOpen99500").show();	//선택코드명 > title
				$("#selectedOptionName").text(data.val);	//선택코드명 > title
				$("#optionTypeName").text(data.val);		//선택코드명 > 등록창
				$("#optionType").val(data.key);			//선택코드 > 등록창
			}else{
				$("#optionOpen99500").hide();	//선택코드명 > title
			}
		}
	});
}


//옵션가져오기 공통
function fnGetSelectedOptionList(code){
	$.ajax({
		url : "/option/list",
		type : "POST",
		data : {
			"optionCode":code
			},
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
			var html = "";
			if (data.optionList.length > 0) {
				var checked = "<i class='fa fa-check'></i>";
				for (var i = 0; i < data.optionList.length; i++) {
					var optionVo =  data.optionList[i];
					var code = optionVo.oi_code; 
					var useAt = optionVo.use_at;
					var defaultAt = optionVo.default_at;
//					var group = "op";
					html +='<tr>';
					html +='<td>'+(i+1)+'</td>';
					html +='<td>'+optionVo.oi_nick_nm+'</td>';
					html +='<td>'+optionVo.oi_nm+'</td>';
					html +='<td>'+optionVo.oi_sort_ordr+'</td>';
					html +='<td>'+(defaultAt=="Y"? checked:"")+"</td>";
//					html +='	<div class="inputCheck">';
//					html +='		<input type="checkbox" id="'+group+'DefaultCheckOld'+i+'" '+(defaultAt=="Y"? "checked":"")+' disabled  >';
//					html +='		<label for="'+group+'DefaultCheckOld'+i+'"></label>';
//					html +='	</div>';
//					html +='</td>';
					html +='<td>'+(useAt=="Y"? checked:"")+"</td>";
//					html +='<td>';
//					html +='	<div class="inputCheck">';
//					html +='		<input type="checkbox" id="'+group+'UseCheckOld'+i+'" '+(useAt=="Y"? "checked":"")+'  disabled  >';
//					html +='		<label for="'+group+'UseCheckOld'+i+'"></label>';
//					html +='	</div>';
//					html +='</td>';
					html +='<td>';
					html +='<button type="button" class="code-mod white" value="'+code+'" >수정</button> ';
					html +='</td>';
					html +='</tr>';
				}
			} else {
				html += '<tr><td colspan="7">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#treeList99500").html(html);	
			$(".code-mod").click(function(){
				if($("#optionType").val()=="0000000067"|| $("#optionType").val() =="0000000072" ){
					$("#trtGroup99500").show();
					$("#optionName").prop("readonly",true);
					fnGetTrtComboDepthList("",1);
				}else{			
					$("#trtGroup99500").hide();
					$("#optionName").prop("readonly",false);
				}
				fnGetOptionMode($(this).val());
			});
		}
	});
}


function fnGetOptionMode(code){
	$.ajax({
		url : "/option/view",
		type : "POST",
		dataType : "json",
		data : {
			"optionCode":code
			},
		cache : false,
		success : function(data) {
			$("#optionCode").val(data.oi_code);
			$("#optionTypeName").val(data.oi_type_name);
			$("#optionNickName").val(data.oi_nick_nm);
			$("#optionName").val(data.oi_nm);
			$("#optionOrder").val(data.oi_sort_ordr);
			$("#optionDefault"+ data.default_at).prop("checked", true);
			$("#optionUse"+ data.use_at).prop("checked", true);
			$("#optionTitle").text("업무일지항목 수정");
//			$("#optionNickName").prop("readonly",true);
			$("#optionPop99500").show();
		}
	});
}


//첫번째 옵션 저장
function fnSaveActivityOption(){
	var nickNm = $("#optionNickName");
	var name = $("#optionName");
	var order = $("#optionOrder");
	if(name.val() == ""){
		alert("항목명을 입력해주세요");
		name.focus();
		return;
	}else if((nickNm.val()).allTrim().length != nickNm.val().length){
		alert("약어에 공백을 넣을수 없습니다.");
		nickNm.focus();
		return;
	}else if(nickNm.val() == ""){
		alert("약어를 입력해주세요");
		nickNm.focus();
		return;
	}else if(order.val() == ""){
		alert("순번을 입력해주세요");
		order.focus();
		return;
	}
	if(!confirm("저장하시겠습니까?")){
		return;
	}
	var saveData = {
		"oi_code"		:$("#optionCode").val(),
		"oi_parn_code"	:$("#optionParentCode").val(),
		"oi_nm"			:$("#optionName").val(),
		"oi_nick_nm"	:$("#optionNickName").val(),
		"oi_sort_ordr"	:$("#optionOrder").val(),
		"default_at"	:$(':radio[name="optionDefault"]:checked').val(),
		"use_at"		:$(':radio[name="optionUse"]:checked').val(),
		"oi_type"		:$("#optionType").val()
	};
	$.ajax({
		url : "/option/save",
		data : saveData,
		type : "POST",		
		dataType : "json",
		cache : false,
		success : function(data) {
			if(data.cnt > 0){
				alert("저장 되었습니다.");
				fnMakeFtree();
				fnGetSelectedOptionList($("#optionType").val());
				$("#optionPop99500").hide();
			}else if(data.cnt == 0){
				alert($("#optionNickName").val() + "는사용 중인 약어입니다.");
			}else if(data.cnt < 0){
				alert($("#optionName").val() + "는 이미 등록된 제품입니다.");
			}else{
				alert("저장 실패");
			}
		}
	});
}








/**
 * @함수명: fnGetCodeList
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 코드목록 조회
 * @param 
 */
function fnGetCodeList(optionCode) {
	
	$.ajax({
		url : "/code/codeList",
		type : "POST",
		dataType : "json",
		data : {"c_code" : optionCode},
		cache : false,
		success : function(data) {
			var html = '';
			if (data.result.length > 0) {
				for (var i = 0; i < data.result.length; i++) {
					var codeVo = data.result[i];
					html+='<tr>';
					html+='	<td>'+(i + 1)+'</td>';
					html+='	<td>'+ codeVo.c_code + '</td>';
					html+='	<td class="txt_left">'+ codeVo.c_name + '</td>';
					html+='	<td>'+ codeVo.c_order + '</td>';
					html+='	<td>'+ codeVo.c_is_system_code + '</td>';
					html+='	<td>'+ codeVo.c_is_use + '</td>';
					html+='	<td>';
					html+='<button type="button" class="code-mod white" value="'+codeVo.c_code+'" >수정</button> ';
					if(codeVo.child_cnt > 0){
						html+='<button type="button" class="not-del white" >삭제</button>';
					}else{
						html+='<button type="button" class="code-del white" value="'+codeVo.c_code+'"  >삭제</button>';
					}
					html+='	</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="7">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#treeList99500").html(html);
			
			$(".not-del").click(function(){
				alert("자식코드가 존재하여 삭제가 불가능합니다.");
			});
			
			$(".code-del").click(function(){
				if(confirm("삭제 하시겠습니까?")){
					fnGetCodeDelete($(this).val());
				}
			});
			$(".code-mod").click(function(){
				fnGetTrtCodeView($(this).val());
			});
		}
	});
	
}


/**
 * @함수명: fnGetCodeList
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 코드목록 조회
 * @param 
 */
function fnGetTrtCodeView(optionCode) {
	$.ajax({
		url : "/code/codeView",
		type : "POST",
		dataType : "json",
		data : {"c_code" : optionCode},
		cache : false,
		success : function(data) {
			$("#optionCode").val(data.c_code);
			$("#optionTypeName").val(data.c_parent_code_name);
			$("#cName").val(data.c_name);
			$("#cDesc").val(data.c_desc);
			$("#cOrder").val(data.c_order);
			$("#cExt1").val(data.c_ext1);
			$("#cExt2").val(data.c_ext2);
			$("#cExt3").val(data.c_ext3);
			$("input:radio[name=cSystemCode]:input[value="+ data.c_is_system_code +"]").prop("checked", true);
			$("input:radio[name=optionUse]:input[value="+ data.c_is_use +"]").prop("checked", true);
			$("#optionTypeName").html(data.c_parent_name_list);
			$("#optionType").val(data.c_parent_code);
			$("#optionParentCode").val(data.oi_parn_code);
			$("#optionTitle").text("공통코드 수정");
			$("#optionPop99500").show();
		}
	});
}

/**
 * @함수명: fnGetCodeUpdate
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 코드목록 조회
 * @param 
 */
function fnGetCodeSave() {
	if($("#cName").val() == ""){
		alert("코드명을 입력해주세요");
		return;
	}
	var saveData = {
			 saveType    	  : $("#saveType").val()
			,c_parent_code    : $("#optionType").val()
			,c_code        	  : $("#optionCode").val()
			,c_name        	  : $("#cName").val()
			,c_desc        	  : $("#cDesc").val()
			,c_order          : $("#cOrder").val()
			,c_ext1          : $("#cExt1").val()
			,c_ext2          : $("#cExt2").val()
			,c_ext3          : $("#cExt3").val()
			,c_is_system_code : $(':radio[name="cSystemCode"]:checked').val()
			,c_is_use		  : $(':radio[name="optionUse"]:checked').val()
	}
	
	$.ajax({
		url : "/code/codeSave",
		data : saveData,
		type : "POST",
		dataType : "json",
		cache : false,
		global: true,
		success : function(data) {
			if(data > 0 ){
				alert("저장되었습니다.");
				fnGetCodeList($("#optionType").val());	//코드목록 리로드
				fnMakeFtree(); 							// 트리 리로드
				$("#optionPop99500").hide();
			}else{
				alert("저장에 실패하였습니다.");
			}
		}
	});
}
/**
 * @함수명: fnGetCodeUpdate
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 코드목록 조회
 * @param 
 */
function fnGetCodeDelete(optionCode) {
	
	$.ajax({
		url : "/code/codeDelete",
		data : {"c_code":optionCode},
		type : "POST",
		dataType : "json",
		cache : false,
		success : function(data){
			if(data > 0 ){
				alert("삭제되었습니다.");
				fnGetCodeList($("#optionType").val());	//코드목록 리로드
				fnMakeFtree(); 							// 트리 리로드
			}else{
				alert("삭제에 실패하였습니다.");
			}
		}
	});
}


/**
 * @함수명: fnOptionPopClear
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 입력폼 초기화
 * @param 
 */
function fnOptionPopClear(){
	$("#optionCode").val("");
	$("#optionParentCode").val("");
//	$("#optionTypeName").val("");
//	$("#optionType").val("");
	$("#optionCode").val("");
	$("#optionName").val("");
	$("#optionNickName").val("");
	$("#optionOrder").val("");
	$("#optionDefaultY").prop("checked", true);
	$("#optionUseY").prop("checked", true);
	$("#optionTitle").text("업무일지항목 수정");
//	$("#optionPop99500").show();
}

