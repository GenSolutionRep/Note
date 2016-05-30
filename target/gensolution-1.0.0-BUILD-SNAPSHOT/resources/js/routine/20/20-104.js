/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
var displyArrPg = 1;
$(document).ready(function(){
	fnGetDisPlayArrSearchComboBox(); 
	fnGetDisPlayArrCstmrGroupList();
	fnGetDiaryArrItemList(displyArrPg);
	$("#diplySearchBtn20104").click(function(){
		fnGetDiaryArrItemList(1);
	});
	$("#searchEmName20104").enterSearch("fnGetDiaryArrItemList");
	
	$("#excelDownBtn20104").click(function(){
//		fnGetDisPlayTrtEntrpsList($(this).val());
		if(confirm("데이터가 많아 최대 5분까지 소요 될수 있습니다.\n계속 진행하시겠습니까? ")){
			location.href='/display/arrexcel';
		}
//		alert("반영 예정입니다.");
	});
	//배치
	$("#diplyBatchBtn20104").click(function(){
		if(confirm("데이터가 많아 최대 5분까지 소요될수 있습니다.\n계속진행하시겠습니까?")){
			fnGetBatchArr();
		}
	});
	$("#searchCgCode20104").change(function(){
		fnGetDisPlayArrEntrpsList($(this).val());
	});
	$("#diplySearchResetBtn20104").click(function(){
		$("#searchOmCode20104").val(""); // 지점
		$("#searchCgCode20104").val("");	//고객그룹
		$("#searchMeCode20104").val("");	//관리업체
		$("#searchEmName20104").val("");	//매장명
		$("#searchSmOdr20104").val("");	//차수
		$("#searchAddrCode120104").val("");		//시도
		$("#searchAddrCode220104").val("");		//구군
		fnGetDiaryArrItemList(1);
	});
	fnGetCommonCodeComboBox("0000000107",$("#searchAddrCode120104"));
	
	$("#searchAddrCode120104").change(function(){
		fnGetCommonCodeComboBox($(this).val(),$("#searchAddrCode220104"));
	});
});
function fnGetBatchArr(){
	
	$.ajax({
		url : "/display/bach_arr",
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
function fnGetCommonCodeComboBox(c_parent_code, target){
	
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
function fnGetDisPlayArrEntrpsList(cg_code){
	
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
		$("#searchMeCode20104").html(listHtml);
	    
	    }
	});
}
function fnGetDisPlayArrCstmrGroupList(){
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
			$("#searchCgCode20104").html(listHtml);
	    
	    }
	});
}
function fnGetDisPlayArrSearchComboBox(){
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
				$("#searchOmCode20104").html(listHtml);
	    }
	});
}
function fnGetDiaryArrItemList(currPg){
	
	displyArrPg = currPg;
	var om_code = $("#searchOmCode20104").val(); // 지점
	var cg_code = $("#searchCgCode20104").val();	//고객그룹
	var me_code = $("#searchMeCode20104").val();	//관리업체
//	var sm_nm = $("#searchSmName20104").val();	//매장명
	var em_nm = $("#searchEmName20104").val();	//매장명
	var sm_odr = $("#searchSmOdr20104").val();	//차수
	var sm_area1 = $("#searchAddrCode120104").val();		//시도
	var sm_area2 = $("#searchAddrCode220104").val();		//구군
//	var addr_code3 = $("#searchAddrCode3").val();		//읍면동
	var params = {
			"om_code"    : om_code   ,
			"cg_code"    : cg_code     ,
			"me_code"    : me_code     ,
//			"sm_nm"      : sm_nm     ,
			"em_nm"     : em_nm    ,
			"sm_odr"     : sm_odr    ,
			"sm_area1" : sm_area1,
			"sm_area2" : sm_area2
//			"addr_code3" : addr_code3
		};
	var rowSize = 20;//표시할 행수
	var fnName = "fnGetDiaryArrItemList";//페이징처리 함수명
	$.ajax({
		url : "/display/arrlist",
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
			headColFirst +='<tr id="headColFirstGgroup20104" >';
			headColSecond +='<tr id="headColSecondGroup20104" >';
			footCol +='<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">';
//			footCol +='<td colspan="5" style="font-weight:bold;text-align:center">합계</td>';
			
			
			var headLen =columnArr.length;
//			var tlen = headLen * 2;
			var cols="";
//			cols+='<col width="100px;" style="min-width: 100px;" >';
//			cols+='<col width="120px;" style="min-width: 120px;"  >';
//			cols+='<col width="135px;" style="min-width: 135px;"  >';
//			cols+='<col width="60px;" style="min-width: 60px;"  >';
//			cols+='<col width="45px;" style="min-width: 45px;"  >';
			cols+='<col span="'+headLen+'" width="*" >';
			
			$("#displayTable20104").css("width",((headLen*90))+"px");
			
			$("#arrColgroup20104").html(cols);
			
			var headColFixed ="";
			headColFixed +='<tr style="height: 58px;" >';
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
				
				
//				console.log("tempHeadCol : " + tempHeadCol+ " group_id : " + group_id);
				if(tempHeadCol != group_id){
					headColFirst +='	<th colspan="" class="parent-group" data-group-id="'+group_id+'" >'+group_nm+'</th>';
				}
				tempHeadCol = group_id;
				
				headColSecond +='	<th class="child-group'+group_id+'"  data-group-id="'+group_id+'" >';
				if(colunm_id == "rate"){
					headColSecond +="진열율";
				}else{
					headColSecond +=item_nm;
				}
				headColSecond +='</th>';
				columns.push(colunm_id+"_"+group_id+"_"+item_id);
			}
			
			headColFirst +='</tr>';
			headColSecond +='</tr>';
			
			
			
			$("#fixedThead20104").html(headColFixed);
			$("#arrThead20104").html(headColFirst + headColSecond);
			var firstGroupArr = $("#headColFirstGgroup20104 th.parent-group");
			
			for (var i = 0; i < firstGroupArr.length; i++) {
				var node =firstGroupArr.eq(i);
				var groupId = node.data("group-id");
//				console.log("groupId : " + groupId)
				var rowspan = $("#headColSecondGroup20104").find(".child-group"+groupId).length;
//				console.log("rowspan :" +  rowspan);
				node.attr("colspan",rowspan);
			}
			
			
			if(data.bodyArr.length > 0){
				var bodyRows = "<tr>"; 
				var bodyRowsFixed = "<tr>";  
				var bodyArr = data.bodyArr;
				var sumArr = data.sumArr;
				for (var i = 0; i < bodyArr.length; i++) {
					bodyRowsFixed +='<td  style="max-width: 90px;"  class="txt_left">'+bodyArr[i].cg_nm+'</td>';
					bodyRowsFixed +='<td  style="max-width: 120px;" class="txt_left">'+bodyArr[i].me_nm+'</td>';
					bodyRowsFixed +='<td  style="max-width: 135px;" class="txt_left">'+bodyArr[i].sm_nm+'</td>';
					bodyRowsFixed +='<td  style="max-width: 60px;" >'+bodyArr[i].em_nm+'</td>';
					bodyRowsFixed +='<td  style="max-width: 55px;" >'+bodyArr[i].sm_odr+'</td>';
					
					var tempGroupId = "";
					for (var f = 0; f < columns.length; f++) {
						var column = columns[f];
						var colunm_id = bodyArr[i][column+"_colunm_id"];
						var group_id = bodyArr[i][column+"_group_id"];
//						console.log("colunm_id : " + colunm_id);
						var entVal =bodyArr[i][column];
						if(entVal === undefined){
							entVal = "0";
						}
						if(tempGroupId != group_id){
							bodyRows +='<td class="txt_right main-group" data-rate-group="'+group_id+'" >'+entVal+'</td>';
						}else{
							if(colunm_id != "rate"){
								bodyRows +='<td class="txt_right group-target-'+group_id+'">'+entVal+'</td>';
							}else{
								bodyRows +='<td class="txt_right group-value-'+group_id+'">'+entVal+'</td>';
							}
						}
						if(i == 0){
							var sumVal =sumArr[i][column];
							if(sumVal === undefined){
								sumVal = "0";
							}
							if(tempGroupId != group_id){
								footCol +='<td class="txt_right main-group" data-rate-group="'+group_id+'" >'+(sumVal+"").comma()+'</td>';
							}else{
								if(colunm_id != "rate"){
									footCol +='<td class="txt_right group-target-'+group_id+'">'+(sumVal+"").comma()+'</td>';
								}else{
									footCol +='<td class="txt_right group-value-'+group_id+'">'+(sumVal+"").comma()+'</td>';
								}
							}
//							footCol +='<td class="txt_right">'+(sumArr[i][column]+"").comma()+'</td>';
						}
						
						tempGroupId = group_id;
					}
					
					bodyRows +='</tr>';
					bodyRowsFixed +='</tr>';
				}
				$("#fixedTbody20104").html(bodyRowsFixed);
				$("#arrTbody20104").html(bodyRows);
				$("#arrTfoot20104").html(footCol);
				var rateGroupArr = $("#displayTable20104").find(".main-group");
				for (var i = 0; i < rateGroupArr.length; i++) {
					var rateGroup = rateGroupArr.eq(i);
					var dsfVal = parseInt(rateGroup.text().uncomma());
					var group_id = rateGroup.data("rate-group");
					var outerSum = 0;
//					console.log("length : "+rateGroup.parent().find(".group-target-"+group_id).length);
					rateGroup.parent().find(".group-target-"+group_id).each(function(index) {
						outerSum += parseInt($(this).text().uncommazero());
//						console.log("$(this).text() : "+ $(this).text());
					});
					var groupSum = dsfVal + outerSum ;
					var rateVal =  0;
					if(groupSum > 0){
//						rateVal =  (dsfVal * 100) / groupSum;
//						if(rateVal % 1 != 0){
							rateVal =  parseFloat((dsfVal * 100) / groupSum).toFixed(1);
//						}
					}
					
					rateGroup.parent().find(".group-value-"+group_id).text(rateVal);
//					console.log("group_id : " + group_id+ " dsfVal : " + dsfVal+" outerSum : " + outerSum+" rateVal : " + parseFloat(rateVal).toFixed(1));
//					console.log(rateVal);
				}
			}else{
				$("#fixedTbody20104").html("<tr><td colspan='5' ></td></tr>");
				$("#arrTfoot20104").html("");
				$("#arrTbody20104").html("<tr><td colspan='"+(5+headLen)+"' >내용이 없습니다.</td></tr>");
			}
			$("#displayPageNavi20104").html(data.navi);
		}
	});
}


