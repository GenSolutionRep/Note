
var pg_10600 = 1;
var pg_all_10600 = 1;
var arr10600;
/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	
	fnMakeEvent_10600();	// 버튼 이벤트 등록
		
	//지점콤보
	cbo1_10600();
	
	//초기화
	controlInit_10600();

	//리스트 조회
	fnSelectList_10600(1);
	
	$("#yy_mm_10600").winCal(
			{format: "yyyy-mm", 
			startView: 3, 
			minView: 3, 
			maxView: 3});
	
});

/**
 * @함수명: fnMakeEventComponent_10600
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEvent_10600(){
	
	//조회
	$("#btnSearch_10600").click(function(){
		fnSelectList_10600(1);
	});
	
	//초기화
	$("#btnClear_10600").click(function(){
		controlInit_10600();
	});
	
	//엑셀
	$("#btnExcel_10600").click(function(){
		//alert("반영 예정입니다.");
		//return;
		
		var params ="";
		
		var y = parseInt($("#yy_mm_10600").val().substring(0,4));
		var m = parseInt($("#yy_mm_10600").val().substring(5,7));
		//var lastDay = (new Date(y,m,"")).getDate();
		
		var user_date = new Date(y,m, 1);
		var date = user_date.setDate(user_date.getDate()-1); 
		var month = user_date.getMonth();
		var year = user_date.getFullYear();
		if(month<10) month = "0" + month; 
		if(month=='00') {month = '12'; year = year-1; }
		if(date<10) date = "0" + date; 
		var lastDay = new Date(year,month, "").getDate();

		params="bhf_code=" + $.trim($("#om_code_10600").val());
		//params+="&bhf_name=" + encodeURI(encodeURIComponent($.trim($('#om_code_10600 option:selected').text())));
		params+="&pre_yy=" + year;
		params+="&pre_mm=" + month;
		params+="&yy=" + $('#yy_mm_10600').val().substring(0,4);
		params+="&mm=" + $("#yy_mm_10600").val().substring(5,7);
		params+="&yyyyMm=" + $('#yy_mm_10600').val().replace('-','');
		params+="&pre_yyyyMm=" + year + "" + month;
		params+="&em_id=" + $('#em_no_10600').val();
		params+="&em_nm=" + encodeURI(encodeURIComponent($('#em_nm_10600').val()));
		params+="&last_day=" + lastDay;
		
		//alert(params);
		var address = "/activity/teamleader/excelExport?" + params;
		location.href = address;
	});
	
	//인쇄
	$("#btnPrint_10600").click(function(){
	});
	
}

/**
 * @함수명: cbo1_10600
 * @작성일: 2015. 11. 40.
 * @작성자: 최수영
 * @설명: 지점 콤보 리스트
 * @param 
 */
function cbo1_10600(){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": "1"},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var listHtml = ""; //"<option value='' selected='selected'>선택</option>";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
	    		}
			}

			$('#om_code_10600').html(listHtml);
	    }
	});
}

function fnSelectList_10600(currPg){
	//header
	getInputDayLabel_10600();
	
	var y = parseInt($("#yy_mm_10600").val().substring(0,4));
	var m = parseInt($("#yy_mm_10600").val().substring(5,7));
	
	
	var user_date = new Date(y,m, 1);
	var date = user_date.setDate(user_date.getDate()-1); 
	var month = user_date.getMonth();
	var year = user_date.getFullYear();
	if(month<10) month = "0" + month; 
	if(date<10) date = "0" + date; 

	var lastDay = (new Date(year,month,"")).getDate();
	
	
	pg_10600 = currPg;//현재페이지 리로드
	var fnName = "fnSelectList_10600";//페이징처리 함수명
	var rowSize = 20;//표시할 행수	
	var params = {
				em_id: $.trim($("#em_no_10600").val()),
		    	bhf_code: $.trim($("#om_code_10600").val()),
		    	em_nm: $.trim($("#em_nm_10600").val()),
		    	yyyyMm: $.trim($("#yy_mm_10600").val().substring(0,4)) + $.trim($("#yy_mm_10600").val().substring(5,7)),
		    	pre_yyyyMm: ((month==0?(year-1):(year))+"") + ((month==0?12:month)+"")
			};
    	
	$.ajax({
		url : "/activity/teamleader",
		data:{
			  //하단 페이징 처리
			  "fnName" : fnName,
			  "params" : params,
			  "rowSize" : rowSize,
			  "currPg" : currPg,
			  },
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var l_listHtml ="";
			var r_listHtml ="";
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var vo = data.result[i];     
					
					l_listHtml += "<tr style='height :29px;'>";
					/*listHtml += "	 <td data-value"+vo.no+"='"+vo.sm_code+"'>"+(parseInt(data.firstNo)-i)+"</td>";
					listHtml += "	 <td class='txt_left'>"+vo.cg_nm +"</td>";*/
					
					//listHtml += "	<td class='txt_center' data-code=''>"+(((currPg*rowSize)-rowSize)+i+1)+"</td>";
					l_listHtml += "	<td class='txt_center' data-code=''>"+(parseInt(data.firstNo)-i)+"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.em_id +"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.em_nm +"</td>";	
					l_listHtml += "</tr>";
					
					r_listHtml += "<tr class='child-td-pink' id="+vo.em_no+" style='height: 29px;'>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_16+"'>"+ vo.day_16 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_17+"'>"+ vo.day_17 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_18+"'>"+ vo.day_18 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_19+"'>"+ vo.day_19 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_20+"'>"+ vo.day_20 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_21+"'>"+ vo.day_21 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_22+"'>"+ vo.day_22 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_23+"'>"+ vo.day_23 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_24+"'>"+ vo.day_24 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_25+"'>"+ vo.day_25 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_26+"'>"+ vo.day_26 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_27+"'>"+ vo.day_27 +"</td>";
					if (lastDay >= 28)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_28+"'>"+ vo.day_28 +"</td>";
					if (lastDay >= 29)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_29+"'>"+ vo.day_29 +"</td>";
					if (lastDay >= 30)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_30+"'>"+ vo.day_30 +"</td>";
					if (lastDay >= 31)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_31+"'>"+ vo.day_31 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_01+"'>"+ vo.day_01 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_02+"'>"+ vo.day_02 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_03+"'>"+ vo.day_03 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_04+"'>"+ vo.day_04 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_05+"'>"+ vo.day_05 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_06+"'>"+ vo.day_06 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_07+"'>"+ vo.day_07 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_08+"'>"+ vo.day_08 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_09+"'>"+ vo.day_09 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_10+"'>"+ vo.day_10 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_11+"'>"+ vo.day_11 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_12+"'>"+ vo.day_12 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_13+"'>"+ vo.day_13 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_14+"'>"+ vo.day_14 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.day_15+"'>"+ vo.day_15 +"</td>";					
					r_listHtml += "</tr>";
	        	}
			}else{
				l_listHtml += "<tr>";
				l_listHtml += "	<td class = 'txt_center' colspan='"+ (3) + "' >조회된 내용이 없습니다.</td>";
				l_listHtml += "</tr>";
				
				r_listHtml += "<tr>";
				r_listHtml += "	<td class = 'txt_center' colspan='"+ (lastDay+15) + "' >조회된 내용이 없습니다.</td>";
				r_listHtml += "</tr>";
			}	
			
			$("#tbody10600_left").html(l_listHtml);
			$("#tbody10600_right").html(r_listHtml);
			$("#tbody10600_right").find("td[data-code$='퇴근']").each(function(){
				var text = $(this).text().split(" ");
				$(this).html("<i style='color:#2f878a;'>"+text[0]+"</i> <i style='color:#E63E5D;'>"+text[1]+"<i>");
			});
			
			$("#navi10600").html(data.navi);
			
			//색상 set
			setColor_10600();
			
			//header
			headerControl_10600();
		    
			//fnAddTbodyClickEvent();
	    }
	});
}

/**
 * @함수명: getCommonCode_106005
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 공통코드
 * @param 
 */
function setColor_10600(){
	
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":"0000000058"},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			if(data!=null && data.length > 0){
				arr10600 = new Array(data.length);
				for(var i=0; i<data.length; i++){
					var codeVo = data[i];     
					arr10600[i] = new Array(codeVo.c_ext1, codeVo.c_ext2);
				}
			}
	    }
	});
	
	//색상set
	$('#tbody10600_right tr').each(function(index) {
		 var $tds = $(this).find('td');
		 for (var i = 0; i < $tds.length; i++){
			 var tmp = $tds.eq(i).data("code");
			 var tmp2 = $tds.eq(i).text();
			 if(tmp!=''){
				 for (var j = 0; j < arr10600.length; j++) {
					if(tmp.substring(0,2)==arr10600[j][0]){
						$tds.eq(i).css("background-color", arr10600[j][1]);
					}
				 }
			 }
			 /*else{
				 $tds.eq(i).css("background-color",'#ffffff');
			 }*/
			 if(tmp2=='휴'){
				 $tds.eq(i).css("background-color",'#f5f7f9');
			 }
		 }
	});
}
/**
 * @함수명: fnSelectList_10600
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 리스트 조회
 * @param 
 */
function fnSelectListA_10600(){
	$.ajax({
		url : "/activity/teamleader",
	    data:{em_no: $("#em_no").val(),
	    	bhf_code: $("#om_code_10600").val(),
	    	em_nm: $("#em_nm_10600").val(),
	    	yy: $("#yy_mm_10600").val().substring(0,4),
	    	mm: $("#yy_mm_10600").val().substring(5,7)},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	
		var listHtml = "";
		
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var res = data.result[i];     
				listHtml += "<tr>";
        		listHtml += "<td>" + res.cm_code + "</td>";
    			listHtml += "<td class='txt_left'>" + res.cm_nm + "</td>";
    			listHtml += "<td>" + res.cm_sort_ordr + "</td>";
    			listHtml += "<td>" + res.use_at + "</td>";
    			listHtml += "<td>";
    			listHtml += "</td>";
    			listHtml += "</tr>";
        	}
		}else{
			
		}	
		$("#tbody10600").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: popControlInit_10600
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 팝업 컨트롤 초기화
 * @param 
 */
function controlInit_10600(){
	
	$('#om_code_10600').val('');
	$('#em_nm_10600').val('');
	$('#em_no_10600').val('');
	$('#yy_mm_10600').val(fnGetToday10600());
	
	//headerControl_10600();
	
	$('#em_nm_10600').focus();
	
	fnSelectList_10600(1);
}

function headerControl_10600(){
	var y = parseInt($("#yy_mm_10600").val().substring(0,4));
	var m = parseInt($("#yy_mm_10600").val().substring(5,7));
	var user_date = new Date(y,m, 1);
	
	var date = user_date.setDate(user_date.getDate()-1); 
	var month = user_date.getMonth();
	var year = user_date.getFullYear();
	
	var lastDay = (new Date(year,month,"")).getDate();
	
	if (lastDay < 28) $("#d28_10600, #w28_10600").css("display", 'none'); else $("#d28_10600, #w28_10600").css("display", '');
	if (lastDay < 29) $("#d29_10600, #w29_10600").css("display", 'none'); else $("#d29_10600, #w29_10600").css("display", '');
	if (lastDay < 30) $("#d30_10600, #w30_10600").css("display", 'none'); else $("#d30_10600, #w30_10600").css("display", '');
	if (lastDay < 31) $("#d31_10600, #w31_10600").css("display", 'none'); else $("#d31_10600, #w31_10600").css("display", '');
	
	getInputDayLabel_10600();
}

function getInputDayLabel_10600() {
	
	var week = new Array('일', '월', '화', '수', '목', '금', '토');
	
	var y = parseInt($("#yy_mm_10600").val().substring(0,4));
	var m = parseInt($("#yy_mm_10600").val().substring(5,7));
	var d ='';
	
	var user_date = new Date(y,m, 1);
	var date = user_date.setDate(user_date.getDate()-1); 
	var month = user_date.getMonth();
	var year = user_date.getFullYear();
	//alert("year :" + year + " month :" + month);
	
	if(month<10) month = "0" + month; 
	if(date<10) date = "0" + date; 
	var result = year + "-" + month + "-" + date; 
	
    var $tds = $('#tbl10600_right thead tr:eq(0)').find('th');
    
    for (var i = 0; i < $tds.length; i++){
    	
	 d = $tds.eq(i).text();
	 
	 if(i < $tds.length-15)
		 day = new Date((month==0?(year-1):(year)) + '-' + (month==0?12:month) + '-' + d).getDay();
	 else 
		 day = new Date(y + '-' + m + '-' + d).getDay();
	 dayLabel = week[day];
	 $('#tbl10600_right thead tr:eq(1) th:nth-child('+ (i+1) +')').text(dayLabel);
	 if(day==0)//일
		 $('#tbl10600_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','red');
	 else if(day==6)//토
		 $('#tbl10600_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','blue');
	 else
		 $('#tbl10600_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','#4c4c4c');
	 
    }

}

function fnGetToday10600(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    //var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
    
    var chan_val = year + '-' + mon;
    
    return chan_val;
}

