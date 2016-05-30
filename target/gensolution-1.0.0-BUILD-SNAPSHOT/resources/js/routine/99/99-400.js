/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#codePop99400").instancePopUp();
	
	//input 달력
	$("#searchToDate99400").winCal(baseOptions);	
	$("#searchFromDate99400").winCal(baseOptions);
	
	$("#cOrder").onlyNum();
	
	//트리생성
	fnMakeFtree();
	
	// 이벤트 등록
	fnSetEventComponent();
	
	//초기정보조회
	fnGetCodeList("");
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
	$("#codeOpen99400").click(function(){
		fnCodePopClear(); // 입력폼 초기화
		$("#codeTitle").text("공통코드 등록");
		$("#codePop99400").show();
	});
	
	// 닫기버튼
	$("#codePopClose99400, #codePopCloseX99400").click(function(){
		$("#codePop99400").hide();
	});
	// 저장버튼
	$("#codePopSave99400").click(function(){
		if(confirm("저장하시겠습니까?")){
			fnGetCodeSave();
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
function fnGetCodeList(cCode) {
	
	$.ajax({
		url : "/code/codeList",
		type : "POST",
		dataType : "json",
		data : {"c_code" : cCode},
		cache : false,
		success : function(data) {
			var html = '';
			if (data.result.length > 0) {
				for (var i = 0; i < data.result.length; i++) {
					var codeVo = data.result[i];
					if(i==0 && cCode!="")
						html += "<tr class ='cell_active'  style='font-weight: bold;'>";
					else
						html += "<tr>";
					//html+='<tr>';
					html+='	<td >'+(i + 1)+'</td>';
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
			$("#treeList99400").html(html);
			
			$(".not-del").click(function(){
				alert("자식코드가 존재하여 삭제가 불가능합니다.");
			});
			
			$(".code-del").click(function(){
				if(confirm("삭제 하시겠습니까?")){
					fnGetCodeDelete($(this).val());
				}
			});
			$(".code-mod").click(function(){
				fnGetCodeView($(this).val());
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
function fnGetCodeView(cCode) {
	$.ajax({
		url : "/code/codeView",
		type : "POST",
		dataType : "json",
		data : {"c_code" : cCode},
		cache : false,
		success : function(data) {
			$("#cCode").val(data.c_code);
			$("#cPrentCodeName").val(data.c_parent_code_name);
			$("#cName").val(data.c_name);
			$("#cDesc").val(data.c_desc);
			$("#cOrder").val(data.c_order);
			$("#cExt1").val(data.c_ext1);
			$("#cExt2").val(data.c_ext2);
			$("#cExt3").val(data.c_ext3);
			$("input:radio[name=cSystemCode]:input[value="+ data.c_is_system_code +"]").prop("checked", true);
			$("input:radio[name=cUse]:input[value="+ data.c_is_use +"]").prop("checked", true);
			$("#cPrentCodeName").html(data.c_parent_name_list);
			$("#cPrentCode").val(data.c_parent_code);
			$("#codeTitle").text("공통코드 수정");
			$("#codePop99400").show();
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
			,c_parent_code    : $("#cPrentCode").val()
			,c_code        	  : $("#cCode").val()
			,c_name        	  : $("#cName").val()
			,c_desc        	  : $("#cDesc").val()
			,c_order          : $("#cOrder").val()
			,c_ext1          : $("#cExt1").val()
			,c_ext2          : $("#cExt2").val()
			,c_ext3          : $("#cExt3").val()
			,c_is_system_code : $(':radio[name="cSystemCode"]:checked').val()
			,c_is_use		  : $(':radio[name="cUse"]:checked').val()
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
				fnGetCodeList($("#cPrentCode").val());	//코드목록 리로드
				fnMakeFtree(); 							// 트리 리로드
				$("#codePop99400").hide();
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
function fnGetCodeDelete(cCode) {
	
	$.ajax({
		url : "/code/codeDelete",
		data : {"c_code":cCode},
		type : "POST",
		dataType : "json",
		cache : false,
		success : function(data){
			if(data > 0 ){
				alert("삭제되었습니다.");
				fnGetCodeList($("#cPrentCode").val());	//코드목록 리로드
				fnMakeFtree(); 							// 트리 리로드
			}else{
				alert("삭제에 실패하였습니다.");
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
	$("#cCodeList").fTreeNew({
		url : "/code/codeTree",
		key:"c_code",
		val:"c_name",
		p_key:"c_parent_code",
		tagId:"codeTree",
		click:  function(data){
			console.log("key : " + data.key);
			console.log("val : " + data.val);
			console.log("p_key : " + data.p_key);
			fnGetCodeList(data.key);
			$("#selectedCodeName").text(data.val);	//선택코드명 > title
			$("#cPrentCodeName").val(data.val);		//선택코드명 > 등록창
			$("#cPrentCode").val(data.key);			//선택코드 > 등록창
		}
	});
}



/**
 * @함수명: fnCodePopClear
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 입력폼 초기화
 * @param 
 */
function fnCodePopClear(){
	$("#cCode").val("");
	$("#cName").val("");
	$("#cDesc").val("");
	$("#cOrder").val("");
	$("#cExt1").val("");
	$("#cExt2").val("");
	$("#cExt3").val("");
	$("input:radio[name=cSystemCode]:input[value=N]").prop("checked", true);
	$("input:radio[name=cUse]:input[value=Y").prop("checked", true);
//	$("#cPrentCodeName").val("");
//	$("#cPrentCode").val("");
	$("#codeTitle").text("공통코드 수정");
	$("#codePop99400").show();
}

