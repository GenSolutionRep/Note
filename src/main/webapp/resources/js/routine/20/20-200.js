
var pg_20200 = 1;
var pg_all_20200 = 1;
var arr20200;
/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	
	fnMakeEvent_20200();	// 버튼 이벤트 등록
		
	//지점콤보
	cbo1_20200();
	
	//직무
	cboCommon_20200('0000000002', $('#dty_code_20200'));
	
	//초기화
	controlInit_20200();

	//리스트 조회
	fnSelectList_20200(1);
	
	$("#yy_mm_20200").winCal(
			{format: "yyyy-mm", 
			startView: 3, 
			minView: 3, 
			maxView: 3,
			autoclose: 1,
			forceParse: 1});
	
});

/**
 * @함수명: fnMakeEventComponent_20200
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEvent_20200(){
	
	//조회
	$("#btnSearch_20200").click(function(){
		fnSelectList_20200(1);
	});
	
	//초기화
	$("#btnClear_20200").click(function(){
		controlInit_20200();
	});
	
	//엑셀
	$("#btnExcel_20200").click(function(){
		var params ="";
		
		var y = parseInt($("#yy_mm_20200").val().substring(0,4));
		var m = parseInt($("#yy_mm_20200").val().substring(5,7));
		//var lastDay = (new Date(y,m,"")).getDate();
		
		var user_date = new Date(y,m, 1);
		var date = user_date.setDate(user_date.getDate()-1); 
		var month = user_date.getMonth();
		var year = user_date.getFullYear();
		if(month<10) month = "0" + month; 
		if(month=='00') {month = '12'; year = year-1; }
		if(date<10) date = "0" + date; 
		var lastDay = new Date(year,month, "").getDate();
			
		params = "bhf_code=" + $('#om_code_20200').val();
		params+="&dty_code=" + $('#dty_code_20200').val();
		params+="&ea_pre_yy=" + year;
		params+="&ea_pre_mm=" + month;
		params+="&ea_yy=" + $('#yy_mm_20200').val().substring(0,4);
		params+="&ea_mm=" + $("#yy_mm_20200").val().substring(5,7);
		params+="&ea_em_no=" + $.trim($("#em_no_20200").val()),
		params+="&ea_em_nm=" + encodeURI(encodeURIComponent($('#em_nm_20200').val()));
		params+="&bhf_name=" + encodeURI(encodeURIComponent($('#om_code_20200 option:selected').text()));
		params+="&last_day=" + lastDay;

		var address = "/attandance/excelExport?" + params;
		location.href = address;
	});
	
	//인쇄
	$("#btnPrint_20200").click(function(){
	});
	
}

/**
 * @함수명: cbo1_20200
 * @작성일: 2015. 11. 40.
 * @작성자: 최수영
 * @설명: 지점 콤보 리스트
 * @param 
 */
function cbo1_20200(){
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

			$('#om_code_20200').html(listHtml);
	    }
	});
}

function fnSelectList_20200(currPg){
	//header
	getInputDayLabel_20200();
	
	var y = parseInt($("#yy_mm_20200").val().substring(0,4));
	var m = parseInt($("#yy_mm_20200").val().substring(5,7));
	
	
	var user_date = new Date(y,m, 1);
	var date = user_date.setDate(user_date.getDate()-1); 
	var month = user_date.getMonth();
	var year = user_date.getFullYear();
	if(month<10) month = "0" + month; 
	if(month=='00') {month = '12'; year = year-1; }
	if(date<10) date = "0" + date; 
	
	var lastDay = (new Date(year,month,"")).getDate();
	
	pg_20200 = currPg;//현재페이지 리로드
	var fnName = "fnSelectList_20200";//페이징처리 함수명
	var rowSize = 20;//표시할 행수	
	var params = {
				cm_code: login_cp_cd,
				dty_code : $.trim($("#dty_code_20200").val()),
				ea_em_no: $.trim($("#em_no_20200").val()),
		    	bhf_code: $.trim($("#om_code_20200").val()),
		    	ea_em_nm: $.trim($("#em_nm_20200").val()),
		    	ea_pre_yy: year,
		    	ea_pre_mm: month,
		    	ea_yy: $.trim($("#yy_mm_20200").val().substring(0,4)),
		    	ea_mm: $.trim($("#yy_mm_20200").val().substring(5,7))
		    	
			};
    	
	$.ajax({
		url : "/attandance/list",
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
			if(data!=null && data.result.length > 0){
				var l_listHtml ="";
				var r_listHtml ="";
				for(var i=0; i<data.result.length; i++){
					var vo = data.result[i];     
					
					l_listHtml += "<tr id="+vo.ea_em_no+" style='height: 29px;'>";
					/*listHtml += "	 <td data-value"+vo.no+"='"+vo.sm_code+"'>"+(parseInt(data.firstNo)-i)+"</td>";
					listHtml += "	 <td class='txt_left'>"+vo.cg_nm +"</td>";*/
					
					//listHtml += "	<td class='txt_center' data-code=''>"+(((currPg*rowSize)-rowSize)+i+1)+"</td>";
					l_listHtml += "	<td class='txt_center' data-code=''>"+(i+1)+"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.bhf_name +"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.ea_em_no +"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.ea_em_nm +"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.em_ecny_de +"</td>";
					l_listHtml += "	<td data-code=''>"+ vo.dty_name.replace('여사원','').replace('MD','') +"</td>";		
					l_listHtml += "</tr>";
					
					r_listHtml += "<tr id="+vo.ea_em_no+" style='height: 29px;'>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_16+"'>"+ vo.ea_day_16 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_17+"'>"+ vo.ea_day_17 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_18+"'>"+ vo.ea_day_18 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_19+"'>"+ vo.ea_day_19 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_20+"'>"+ vo.ea_day_20 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_21+"'>"+ vo.ea_day_21 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_22+"'>"+ vo.ea_day_22 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_23+"'>"+ vo.ea_day_23 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_24+"'>"+ vo.ea_day_24 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_25+"'>"+ vo.ea_day_25 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_26+"'>"+ vo.ea_day_26 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_27+"'>"+ vo.ea_day_27 +"</td>";
					if (lastDay >= 28)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_28+"'>"+ vo.ea_day_28 +"</td>";
					if (lastDay >= 29)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_29+"'>"+ vo.ea_day_29 +"</td>";
					if (lastDay >= 30)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_30+"'>"+ vo.ea_day_30 +"</td>";
					if (lastDay >= 31)
						r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_31+"'>"+ vo.ea_day_31 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_01+"'>"+ vo.ea_day_01 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_02+"'>"+ vo.ea_day_02 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_03+"'>"+ vo.ea_day_03 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_04+"'>"+ vo.ea_day_04 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_05+"'>"+ vo.ea_day_05 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_06+"'>"+ vo.ea_day_06 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_07+"'>"+ vo.ea_day_07 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_08+"'>"+ vo.ea_day_08 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_09+"'>"+ vo.ea_day_09 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_10+"'>"+ vo.ea_day_10 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_11+"'>"+ vo.ea_day_11 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_12+"'>"+ vo.ea_day_12 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_13+"'>"+ vo.ea_day_13 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_14+"'>"+ vo.ea_day_14 +"</td>";
					r_listHtml += "	<td class='txt_center' data-code='"+ vo.ea_day_15+"'>"+ vo.ea_day_15 +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_std_work_days +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_holidays +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_weekdays_work +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_holidays_work +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_work_overtime +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_actual_work_days +"</td>";
					r_listHtml += "	<td class='txt_right' data-code='' style='display: none;'>"+ vo.ea_base_time +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_absent_days +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_annual_leave +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_summer_holidays +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_cc_leave +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_education +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_sick_leave +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_std_trans_fee +"</td>";
					r_listHtml += "	<td class='txt_right' data-code=''>"+ vo.ea_trans_fee +"</td>";
					r_listHtml += "</tr>";
	        	}
			}else{
				listHtml += "<tr>";
				listHtml += "	<td class = 'txt_center' colspan='"+ (6+lastDay+15) + "' >조회된 내용이 없습니다.</td>";
				listHtml += "</tr>";
			}	
			
			$("#tbody20200_left").html(l_listHtml);
			$("#tbody20200_right").html(r_listHtml);
			$("#navi20200").html(data.navi);
			
			//색상 set
			setColor_20200();
			
			//header
			headerControl_20200();
		    
			//fnAddTbodyClickEvent();
	    }
	});
}

/**
 * @함수명: getCommonCode_20200
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 공통코드
 * @param 
 */
function setColor_20200(){
	
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":"0000000058"},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			if(data!=null && data.length > 0){
				arr20200 = new Array(data.length);
				for(var i=0; i<data.length; i++){
					var codeVo = data[i];     
					arr20200[i] = new Array(codeVo.c_ext1, codeVo.c_ext2);
				}
			}
	    }
	});
	
	//색상set
	$('#tbody20200_right tr').each(function(index) {
		 var $tds = $(this).find('td');
		 for (var i = 0; i < $tds.length; i++){
			 var tmp = $tds.eq(i).data("code");
			 var tmp2 = $tds.eq(i).text();
			 if(tmp!=''){
				 for (var j = 0; j < arr20200.length; j++) {
					if(tmp.substring(0,2)==arr20200[j][0]){
						$tds.eq(i).css("background-color", arr20200[j][1]);
					}
				 }
			 }
			 /*else{
				 $tds.eq(i).css("background-color",'#ffffff');
			 }*/
			 if(tmp2=='휴무' || tmp2=='휴일'){
				 $tds.eq(i).css("background-color",'#cfd0d1');
			 }
		 }
	});
}
/**
 * @함수명: fnSelectList_20200
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 리스트 조회
 * @param 
 */
function fnSelectListA_20200(){
	$.ajax({
		url : "/attandance/list",
	    data:{ea_em_no: $("#ea_em_no").val(),
	    	bhf_code: $("#om_code_20200").val(),
	    	ea_em_nm: $("#em_nm_20200").val(),
	    	ea_yy: $("#yy_mm_20200").val().substring(0,4),
	    	ea_mm: $("#yy_mm_20200").val().substring(5,7)},
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
		$("#tbody20200").html(listHtml);
	    
	    }
	});
}

/**
 * @함수명: popControlInit_20200
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 팝업 컨트롤 초기화
 * @param 
 */
function controlInit_20200(){
	
	$('#om_code_20200').val('');
	$('#em_nm_20200').val('');
	$('#em_no_20200').val('');
	$('#yy_mm_20200').val(fnGetToday20200());
	
	//headerControl_20200();
	
	$('#em_nm_20200').focus();
	
	fnSelectList_20200(1);
}

function headerControl_20200(){
	var y = parseInt($("#yy_mm_20200").val().substring(0,4));
	var m = parseInt($("#yy_mm_20200").val().substring(5,7));
	var user_date = new Date(y,m, 1);
	
	var date = user_date.setDate(user_date.getDate()-1); 
	var month = user_date.getMonth();
	var year = user_date.getFullYear();
	
	var lastDay = (new Date(year,month,"")).getDate();
	
	if (lastDay < 28) $("#d28_20200, #w28_20200").css("display", 'none'); else $("#d28_20200, #w28_20200").css("display", '');
	if (lastDay < 29) $("#d29_20200, #w29_20200").css("display", 'none'); else $("#d29_20200, #w29_20200").css("display", '');
	if (lastDay < 30) $("#d30_20200, #w30_20200").css("display", 'none'); else $("#d30_20200, #w30_20200").css("display", '');
	if (lastDay < 31) $("#d31_20200, #w31_20200").css("display", 'none'); else $("#d31_20200, #w31_20200").css("display", '');
	
	getInputDayLabel_20200();
	
	//$('#tbody20200 tr td:nth-child(20)').hide();
	//$('#tbody20200 td:nth-child(21)').hide();

/*	$('#tbl20200 thead tr:eq(0) th:nth-child(21)').hide();
	$('#tbl20200 thead tr:eq(1) th:nth-child(21)').hide();
	*/
	/*
	if (lastDay < 28){
		$('#tbody20200 tr').each(function(index) {
			 var $tds = $(this).find('td');		 
			 $tds.eq(18).hide();
		});
	};
	if (lastDay < 29){
		$('#tbody20200 tr').each(function(index) {
			 var $tds = $(this).find('td');		 
			 $tds.eq(19).hide();
		});
	};
	if (lastDay < 30){
		$('#tbody20200 tr').each(function(index) {
			 var $tds = $(this).find('td');		 
			 $tds.eq(20).hide();
		});
	};
	if (lastDay < 31){
		$('#tbody20200 tr').each(function(index) {
			 var $tds = $(this).find('td');		 
			 alert('21');
			 $tds.eq(21).css("display","none");
		});
	};
	*/
	/*	
	$('#tbl20200 th:nth-child(18)').show(); $('#tbl20200 tbody td:nth-child(18)').show();
	$('#tbl20200 th:nth-child(19)').show(); $('#tbl20200 tbody td:nth-child(19)').show();
	$('#tbl20200 th:nth-child(20)').show(); $('#tbl20200 tbody td:nth-child(20)').show();
	$('#tbl20200 th:nth-child(21)').show(); $('#tbl20200 tbody td:nth-child(21)').show();

if (lastDay < 28) {$('#tbl20200 th:nth-child(18)').hide(); $('#tbl20200 tbody td:nth-child(18)').hide();}
	if (lastDay < 29) {$('#tbl20200 th:nth-child(19)').hide(); $('#tbl20200 tbody td:nth-child(19)').hide();}
	if (lastDay < 30) {$('#tbl20200 th:nth-child(20)').hide(); $('#tbl20200 tbody td:nth-child(20)').hide();}
	if (lastDay < 31) {$('#tbl20200 th:nth-child(21)').hide(); $('#tbody20200 td:nth-child(21)').hide();}*/

}

function getInputDayLabel_20200() {
	
	var week = new Array('일', '월', '화', '수', '목', '금', '토');
	
	var y = parseInt($("#yy_mm_20200").val().substring(0,4));
	var m = parseInt($("#yy_mm_20200").val().substring(5,7));
	var d ='';
	
	var user_date = new Date(y,m, 1);
	var date = user_date.setDate(user_date.getDate()-1); 
	var month = user_date.getMonth();
	var year = user_date.getFullYear();
	//alert("year :" + year + " month :" + month);
	
	if(month<10) month = "0" + month; 
	if(date<10) date = "0" + date; 
	var result = year + "-" + month + "-" + date; 
	
    var $tds = $('#tbl20200_right thead tr:eq(0)').find('th');
    
    for (var i = 0; i < $tds.length-15; i++){ 
	 d = $tds.eq(i).text();
	 if(i < $tds.length-15-15)
		 day = new Date((month==0?(year-1):(year)) + '-' + (month==0?12:month) + '-' + d).getDay();
	 else 
		 day = new Date(y + '-' + m + '-' + d).getDay();
	 dayLabel = week[day];
	 $('#tbl20200_right thead tr:eq(1) th:nth-child('+ (i+1) +')').text(dayLabel);
	 if(day==0)//일
		 $('#tbl20200_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','red');
	 else if(day==6)//토
		 $('#tbl20200_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','blue');
	 else 
		 $('#tbl20200_right thead tr:eq(1) th:nth-child('+ (i+1) +')').css('color','#4c4c4c');
    }

}

function fnGetToday20200(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    //var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
    
    var chan_val = year + '-' + mon;
    
    return chan_val;
}


/**
 * @함수명: cboCommon_20200
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 공통코드 콤보
 * @param 
 */
function cboCommon_20200(c_parent_code, obj){
	
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
			obj.html(listHtml);
	    }
	});
}
