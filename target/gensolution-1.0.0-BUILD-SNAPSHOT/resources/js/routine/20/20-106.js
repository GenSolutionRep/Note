/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
var displyBigPg = 1;
$(document).ready(function(){
	fnGetDisPlayBigSearchComboBox();
	fnGetDisPlayBigCstmrGroupList();
	fnGetDiaryBigItemList(displyBigPg);
	$("#diplySearchBtn20106").click(function(){
		fnGetDiaryBigItemList(1);
	});
	$("#searchEmName20106").enterSearch("fnGetDiaryBigItemList");
	
	$("#searchCgCode20106").change(function(){
		fnGetDisPlayBigEntrpsList($(this).val());
	});
	$("#excelDownBtn20106").click(function(){
//		fnGetDisPlayTrtEntrpsList($(this).val());
		if(confirm("데이터가 많아 최대 5분까지 소요 될수 있습니다.\n계속 진행하시겠습니까? ")){
			location.href='/display/bigexcel';
		}
//		alert("반영 예정입니다.");
	});
	$("#diplySearchResetBtn20106").click(function(){
		$("#searchOmCode20106").val(""); // 지점
		$("#searchCgCode20106").val("");	//고객그룹
		$("#searchMeCode20106").val("");	//관리업체
		$("#searchEmName20106").val("");	//매장명
		$("#searchSmOdr20106").val("");	//차수
		$("#searchAddrCode120106").val("");		//시도
		$("#searchAddrCode220106").val("");		//구군
		fnGetDiaryBigItemList(1);
	});
	fnGetCommonCodeComboBox20106("0000000107",$("#searchAddrCode120106"));
	$("#searchAddrCode120106").change(function(){
		fnGetCommonCodeComboBox20106($(this).val(),$("#searchAddrCode220106"));
	});
	$("#diplyBatchBtn20106").click(function(){
		if(confirm("데이터가 많아 최대 5분까지 소요될수 있습니다.\n계속진행하시겠습니까?")){
			fnGetBatchBig();
		}
	});
});
function fnGetBatchBig(){

	$.ajax({
		url : "/display/bach_big",
		type : "POST",
		dataType : "json",
		cache : false,
		global:true,
		success : function(data) {
			if(data < 1 ){
				alert("실패 하였습니다.");
			}else{
				alert("완료되었습니다.");
			}
		}
	});
}
function fnGetCommonCodeComboBox20106(c_parent_code, target){
	
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
			target.html(listHtml);
	    }
	});
}

function fnGetDisPlayBigEntrpsList(cg_code){
	
	$.ajax({
		url : "/store/manageEntrpsList",
		data:{"cg_code":cg_code, "om_code": ""},
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
				
			}	
		$("#searchMeCode20106").html(listHtml);
	    
	    }
	});
}
function fnGetDisPlayBigCstmrGroupList(){
	$.ajax({
		url : "/store/cstmrGroupList",
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
			}	
			$("#searchCgCode20106").html(listHtml);
	    
	    }
	});
}
function fnGetDisPlayBigSearchComboBox(){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": 1},
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
				$("#searchOmCode20106").html(listHtml);
	    }
	});
}
function fnGetDiaryBigItemList(currPg){
	
	displyBigPg = currPg;
	var om_code = $("#searchOmCode20106").val(); // 지점
	var cg_code = $("#searchCgCode20106").val();	//고객그룹
	var me_code = $("#searchMeCode20106").val();	//관리업체
	var em_nm = $("#searchEmName20106").val();	//매장명
//	var sm_nm = $("#searchSmName20106").val();	//매장명
	var sm_odr = $("#searchSmOdr20106").val();	//차수
	var sm_area1 = $("#searchAddrCode120106").val();		//시도
	var sm_area2 = $("#searchAddrCode220106").val();		//구군
//	var addr_code3 = $("#searchAddrCode3").val();		//읍면동
	var params = {
			"om_code"    : om_code   ,
			"cg_code"    : cg_code     ,
			"me_code"    : me_code     ,
//			"sm_nm"      : sm_nm     ,
			"em_nm"      : em_nm     ,
			"sm_odr"     : sm_odr    ,
			"sm_area1" : sm_area1,
			"sm_area2" : sm_area2
//			"addr_code3" : addr_code3
		};
	var rowSize = 20;//표시할 행수
	var fnName = "fnGetDiaryBigItemList";//페이징처리 함수명
	$.ajax({
		url : "/display/biglist",
		type : "POST",
		data : {
			fnName : fnName,
			params : params,
			rowSize : rowSize,
			currPg : currPg
		},
//		data :searchData ,
		dataType : "json",
		global:true,
		cache : false,
		success : function(data) {
			
			var headColFirst = "";
			var headColSecond = "";	 
			var footCol = "";	 
			
			var columns = new Array(); 	
			var columnArr = data.columnArr;
			headColFirst +='<tr id="headColFirstGgroup20106" >';
			headColSecond +='<tr id="headColSecondGroup20106" >';
			footCol +='<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">';
			
			
			var headLen =columnArr.length;
//			var tlen = headLen * 2;
			var cols="";
			cols+='<col span="'+headLen+'" width="*" >';
			
			$("#displayTable20106").css("width",((headLen*83))+"px");
			$("#bigColgroup20106").html(cols);
			
			var headColFixed ="";
			headColFixed +='<tr style="height: 58px;">';
			headColFixed +='	<th rowspan="2" style="max-width: 90px;" >고객그룹</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 120px;" >관리업체</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 135px;" >매장명</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 60px;">사원명</th>';
			headColFixed +='	<th rowspan="2" style="max-width: 55px;">차수</th>';
			headColFixed +='</tr>';
			
			
			var tempHeadCol ="";
			for (var i = 0; i < headLen; i++) {
				var colunm_id = columnArr[i].colunm_id;
				var group_id = columnArr[i].group_id;
				var group_nm = columnArr[i].group_nm;
				var item_id = columnArr[i].item_id;
				var item_nm = columnArr[i].item_nm;
				
				
				if(tempHeadCol != group_id){
					headColFirst +='	<th colspan="" class="parent-group" data-group-id="'+group_id+'" >'+group_nm+'</th>';
				}
				tempHeadCol = group_id;
				
				headColSecond +='	<th class="child-group'+group_id+'"  data-group-id="'+group_id+'" >'+item_nm+'</th>';
				columns.push(colunm_id+"_"+group_id+"_"+item_id);
			}
			
			headColFirst +='</tr>';
			headColSecond +='</tr>';
			
			
			$("#fixedThead20106").html(headColFixed);
			$("#bigThead20106").html(headColFirst + headColSecond);
			var firstGroupArr = $("#headColFirstGgroup20106 th.parent-group");
			
			for (var i = 0; i < firstGroupArr.length; i++) {
				var node =firstGroupArr.eq(i);
				var groupId = node.data("group-id");
				var rowspan = $("#headColSecondGroup20106").find(".child-group"+groupId).length;
				node.attr("colspan",rowspan);
			}
			
			if(data.bodyArr.length > 0){
				var bodyRows = "<tr>"; 
				var bodyRowsFixed = "<tr>"; 
				var bodyArr = data.bodyArr;
				var sumArr = data.sumArr;
				
				
				for (var i = 0; i < bodyArr.length; i++) {
					bodyRowsFixed +='<td class="txt_left">'+bodyArr[i].cg_nm+'</td>';
					bodyRowsFixed +='<td class="txt_left">'+bodyArr[i].me_nm+'</td>';
					bodyRowsFixed +='<td class="txt_left">'+bodyArr[i].sm_nm+'</td>';
					bodyRowsFixed +='<td >'+bodyArr[i].em_nm+'</td>';
					bodyRowsFixed +='<td >'+bodyArr[i].sm_odr+'</td>';
					
					
					for (var f = 0; f < columns.length; f++) {
						var column = columns[f];
						var entVal =bodyArr[i][column];
						if(entVal === undefined){
							entVal = "0";
						}
						bodyRows +='<td class="txt_right" >'+entVal+'</td>';
						if(i == 0){
							var sumVal =sumArr[i][column];
							if(sumVal === undefined){
								sumVal = "0";
							}
							footCol +='<td class="txt_right">'+(sumVal+"").comma()+'</td>';
						}
					}
					bodyRows +='</tr>';
					bodyRowsFixed +='</tr>';
				}
				$("#fixedTbody20106").html(bodyRowsFixed);
				$("#bigTbody20106").html(bodyRows);
				$("#bigTfoot20106").html(footCol);
//				var rateGroupArr = $("#displayTable20106").find(".main-group");
			}else{
				$("#bigTfoot20106").html("");
				$("#fixedTbody20106").html("<tr><td colspan='5' ></td></tr>");
				$("#bigTbody20106").html("<tr><td colspan='"+(5+headLen)+"' >내용이 없습니다.</td></tr>");
			}
			$("#displayPageNavi20106").html(data.navi);
		}
	});
}


