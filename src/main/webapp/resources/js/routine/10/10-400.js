var cvsPg = 1;

/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event - CVS 순회
 * @param 
 */
$(document).ready(function(){
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#cvsPop10400").instancePopUp();
	
	$("#searchStartDate10200").winCal(baseOptions);
	$("#searchEndDate10200").winCal(baseOptions);
	
	fnGetCvsSearchComboBox("",1); // 소속가져오기
	
	// 이벤트 등록
	fnSetEventComponent();
	
	fnGetCvsList(cvsPg);
});


/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	// 닫기버튼
	$("#cvsPopCloseX10400, #cvsPopClose10400").click(function(){
		$("#cvsPop10400").hide();
	});
	$("#cvsSearchBtn").click(function(){
		fnGetCvsList("1");
	});
	$("#cvsSearchResetBtn").click(function(){
		$("#searchSmName70100").val("");	//검색어
		$("#searchEmName70100").val("");	//검색어
		$("#searchOmCode10400").val("");	//검색어
		$("#searchTmCode10400").html("<option value=''>팀명</option>");	//검색어
		$("#searchStartDate10200").val("");	//검색어
		$("#searchEndDate10200").val("");	//검색어
		fnGetCvsList("1");
	});
	
}



/**
 * @함수명: fnGetCodeList
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 코드목록 조회
 * @param 
 */
function fnGetCvsList(currPg) {
	var smNm  = $("#searchSmName70100").val();	//검색어
	var emNm  = $("#searchEmName70100").val();	//검색어
	var omCode  = $("#searchOmCode10400").val();	//검색어
	var tmCode  = $("#searchTmCode10400").val();	//검색어
	var startDate  = $("#searchStartDate10200").val();	//검색어
	var endDate  = $("#searchEndDate10200").val();	//검색어
	if(startDate.dateReplace() > endDate.dateReplace()){
		alert("시작일이 종료일보다 클수 없습니다.");
		 return;
	}
	var params = {
			"smNm"      : smNm,
			"emNm"      : emNm,
			"omCode" 	: omCode,
			"tmCode" 	: tmCode,
			"startDate" : startDate.dateReplace(),
			"endDate"   : endDate.dateReplace()
	}; 
	var rowSize = 15;			
	var fnName = "fnGetCvsList";
	$.ajax({
		url : "/cvs/list",
		type : "POST",
		dataType : "json",
		data:{"fnName":fnName
			, "params":params
			, "rowSize":rowSize
			, "currPg":currPg
		},
		cache : false,
		success : function(data) {
			var html = '';
			var cvsLen = data.cvsVoList.length;
			var fristNo = data.fristNo;
			
			if (cvsLen > 0) {
				for (var i = 0; i < cvsLen; i++) {
					var cvsVo = data.cvsVoList[i];
					var checked = "";
					if(cvsVo.cvs_status == "Y"){ 
						checked = "<i class='fa fa-check'></i>"
					}
					html+='<tr>';
					html+='	<td>'+(fristNo-i)+'</td>';
					html+='	<td>'+cvsVo.om_nm+'</td>';
					html+='	<td>'+cvsVo.em_nm+'</td>';
					if(cvsVo.drc_innb != ""){ 
							html+='	<td><a class="view-btn" data-drc-innb="'+cvsVo.drc_innb+'" >'+cvsVo.sm_nm+'</a></td>';
					}else{
						html+='	<td>'+cvsVo.sm_nm+'</td>';
					}
					html+='	<td>'+cvsVo.plan_de+'</td>';
					html+='	<td>'+checked+'</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="6">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#cvsTbody10400").html(html);
			$("#cvsTbody10400 a.view-btn").click(function(){
				fnGetCvsItemView($(this).data("drc-innb"));
			});
			$("#cvsNavi10400").html(data.navi);
		}
	});
}

function fnGetCvsItemView(drcInnb){
	$.ajax({
		url : "/cvs/itemlist",
		type : "POST",
		dataType : "json",
		data:{"drcInnb":drcInnb},
		cache : false,
		success : function(data) {
			var itemList = data.cvsVoList;
			var itemMatter = data.matter;
			var html = '';
			var itemLen = itemList.length;
			var groupCnt = 0;
			var rcciStatus = ["-","양호","보통","불량"]
			var rcciColor = ["#0B81FF","#0B81FF","#4c4c4c","#FF6369"]
			if(itemLen > 0){
				for (var i = 0; i < itemLen; i++) {
					var itemVo = itemList[i];
					html+='<tr>';
					if(groupCnt <= i){
						groupCnt += parseInt(itemVo.child_cnt);
						html+='	<th rowspan="'+itemVo.child_cnt+'" >'+itemVo.drcc_c_code_nm+'</th>';
					}
					html+='	<td class="align-l">'+itemVo.drcci_c_code_nm+'</td>';
					html+='	<td style="color:'+rcciColor[itemVo.drcci_state]+'" >'+rcciStatus[itemVo.drcci_state]+'</td>';
					html+='	<td class="align-l">'+itemVo.drcci_c_desc+'</td>';
					html+='</tr>';
				}
				
				$("#cvsItemTfoot10400").html("<tr><th>특이사항</th><td colspan='3' class='align-l'>"+itemMatter+"</td></tr>");
			}else{
				html= "<td colspan='4' >내용이 없습니다.</td>";
			}
			$("#cvsItemTbody10400").html(html);
			$("#cvsPop10400").show();
			
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
function fnGetCodeView(optionCode) {
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
			$("#optionTitle").text("공통코드 수정");
			$("#optionPop10400").show();
		}
	});
}

//검색 콤보 박스
function fnGetCvsSearchComboBox(om_code ,om_orgnzt_se){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":om_code, "om_orgnzt_se": om_orgnzt_se},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
			var listHtml = "";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
	    		}
			}	
			if(om_orgnzt_se==1){
				$("#searchOmCode10400").html(listHtml);
				$("#searchOmCode10400").change(function(){
					fnGetCvsSearchComboBox($(this).val(), 2);
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetCvsSearchComboBox($("#searchOmCode10400").val() ,2);
//					fnGetActivityFixingInfo10200(1, "");// 조회
				}
			}else if(om_orgnzt_se==2){
				$("#searchTmCode10400").html(listHtml);
			}
	    }
	});
}

