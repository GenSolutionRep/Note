var eventPg_70200 = 1;
var eventAuth_70200 = "";
var fileList_70200 = [];
var toggleFlag_70200 = "C"; //C:달력  
var arr_70200 = new Array();
var eventAuth = "";
var jfcalplugin;
/*alert("000007");*/

/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
//$(document).ready(function(){
	//레이어팝업
	$("#tempPop70200").instancePopUp();
	$("#tempViewPop70200").instancePopUp();
	
	initMyCal_70200();
	
	//버튼 이벤트 등록
	fnMakeEventComponent_70200();	

	//지점콤보 초기화
	cbo1_70200($("#om_code_70200"));

	//팝업 지점콤보 초기화
	cbo1_70200($("#pop_om_code_70200"));
	
	//플래그(시작시엔 달력)
	toggleFlag_70200 ="C";
	
	//*******************************************************
	
	var omGroup   = $("#pop_om_code_70200 > option");
	var omArray = new Array();
	for (var i = 0; i < omGroup.length; i++) {
		 var data = omGroup.eq(i).attr("data-om_innb");
		 if(data == undefined || data == "") continue;
		 //omArray[i] = omGroup.eq(i).attr("data-om_innb");
		 omArray.push(omGroup.eq(i).attr("data-om_innb"));
	}	
	//오토컴플릿(지점/매장)
	fnMakeAutoComplete70200(omArray);
	
	//*******************************************************
	
	if($("#om_code_70200").val()!=""){
		$("#om_code_70200").change();
	}
	
	//리스트 조회
	//fnSelectCompanyList_70200();
	
	//$("#dateSelect").winCal();
	
	$("#dateSelect").winCal(baseOptions);
	$("#dateFrom_popup70200").winCal(baseOptions);
	$("#dateTo_popup70200").winCal(baseOptions);
	
	//첨부파일
	$("#file_70200").file({
		fileList : fileList_70200
	});
	
	
	
//});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent_70200(){
	
	//일정목록
	$("#tempList70200").click(function(){
		
		if($.trim($("#om_code_70200").val())==''){
			alert("지점을 선택하세요");
			$("#om_code_70200").focus();
			return;
		}
		
		if(toggleFlag_70200=="C"){//현재 달력이면
			$(this).text("월력");
			$("#mycal").css("display", "none");
			$("#myScheduleList").css("display", "");
			toggleFlag_70200 ="S"; //스케쥴리스트
			fnScheduleList_70200();
		}else{
			$(this).text("일정목록");
			$("#mycal").css("display", "");
			$("#myScheduleList").css("display", "none");
			toggleFlag_70200 ="C"; //달력
			
			fnSelectList_70200();
		}
	});
	
	
	//등록구분
	$("#chk_me_holiday_at_N_70200").change(function(){	
		$('#pop_om_code_70200').prop('disabled',false);
		//매장
		$('#pop_sm_nm_70200').prop('disabled',false);

		//기간
		$('#hhFrom_popup70200').prop('disabled',false);
		$('#mmFrom_popup70200').prop('disabled',false);
		$('#hhTo_popup70200').prop('disabled',false);
		$('#mmTo_popup70200').prop('disabled',false);
		
		//첨부파일
		$('#file_70200List').prop('disabled',false); 
	});
	$("#chk_me_holiday_at_Y_70200").change(function(){	
		$('#pop_om_code_70200').val('');
		$("#selectItem_om_nm_70200").html("");
		$('#pop_om_code_70200').prop('disabled',true);
		//매장
		$('#pop_sm_nm_70200').val('');
		$("#selectItem_sm_nm_70200").html("");
		$('#pop_sm_nm_70200').prop('disabled',true);

		//기간
		//$('#dateFrom_popup70200').val('');
		$('#hhFrom_popup70200').val('00');
		$('#mmFrom_popup70200').val('00');
		$('#hhFrom_popup70200').prop('disabled',true);
		$('#mmFrom_popup70200').prop('disabled',true);
		//('#dateTo_popup70200').val('');
		$('#hhTo_popup70200').val('00');
		$('#mmTo_popup70200').val('00');
		$('#hhTo_popup70200').prop('disabled',true);
		$('#mmTo_popup70200').prop('disabled',true);
		
		//첨부파일
		$("#file_70200ItemList").html('');
		$('#am_no_70200').val('');
		$('#file_70200List').prop('disabled',true); 
	});
	
	//종일
	$("#chk_me_allday_at_70200").click(function(){
		if($(this).data("value")=="Y"){
			$(this).data("value","N");
		}else{
			$(this).data("value","Y");
			
			$('#hhFrom_popup70200, #mmFrom_popup70200').val('00');
			$('#hhTo_popup70200, #mmTo_popup70200').val('00');
		}
	});

	//스마트폰 알림
	$("#chk_me_ntcn_at_70200").click(function(){
		if($(this).data("value")=="Y"){
			$(this).data("value","N");
		}else{
			$(this).data("value","Y");
		}
	});
	
	
	//오늘
	$("#searchToday70200").click(function(){
		$("#dateSelect").val(fnGetToday70200());
		//$("#dateSelect").change();
		
		if($.trim($("#om_code_70200").val())==''){
			alert("지점을 선택하세요");
			$("#om_code_70200").focus();
			return;
		}
		
		if(toggleFlag_70200=="C"){//현재 달력이면)
			fnSelectList_70200();
		}else{
			fnScheduleList_70200();
		}
	});

	//지점 콤보 change 이벤트
	$("#om_code_70200").change(function(){
		if($.trim($("#om_code_70200").val())=='')
			return;
		
		if(toggleFlag_70200=="C"){//현재 달력이면
			fnSelectList_70200();
		}else{//스케쥴리스트면
			fnScheduleList_70200();
		}
	});
	
	//조회
	$("#tempSearch70200").click(function(){
		if($.trim($("#om_code_70200").val())==''){
			alert("지점을 선택하세요");
			$("#om_code_70200").focus();
			return;
		}
		
		fnSelectList_70200();
	});
	
	//이전버튼
	$("#searchPreMonth70200").click(function(){
		
		if($.trim($("#om_code_70200").val())==''){
			alert("지점을 선택하세요");
			$("#om_code_70200").focus();
			return;
		}
		
		jfcalplugin.showPreviousMonth("#mycal");
		// update the jqeury datepicker value
		var calDate = jfcalplugin.getCurrentDate("#mycal"); // returns Date object
		var cyear = calDate.getFullYear();
		/*var cmonth = calDate.getMonth();
		var cday = calDate.getDate();*/
		// Date month 0-based (0=January)
		var cmonth = ((calDate.getMonth() + 1) < 10 ? ('0'+ (calDate.getMonth() + 1)) : (calDate.getMonth() + 1)); //calDate.getMonth();  
		var cday = (calDate.getDate() < 10 ? '0'+ (calDate.getDate()) : calDate.getDate());// calDate.getDate();
		// jquery datepicker month starts at 1 (1=January) so we add 1
		//$("#dateSelect").datepicker("setDate", cyear + "-" + (cmonth +1) + "-" + cday);
		$("#dateSelect").val(cyear + "-" + cmonth + "-" + cday);
		
		if(toggleFlag_70200=="C"){//현재 달력이면
			fnSelectList_70200();
		}else{//스케쥴리스트면
			fnScheduleList_70200();
		}
		
		return false;
	});
	
	//다음버튼
	$("#searchNextMonth70200").click(function(){
		if($.trim($("#om_code_70200").val())==''){
			alert("지점을 선택하세요");
			$("#om_code_70200").focus();
			return;
		}
		
		jfcalplugin.showNextMonth("#mycal");
		// update the jqeury datepicker value
		var calDate = jfcalplugin.getCurrentDate("#mycal"); // returns Date object
		var cyear = calDate.getFullYear();
		/*var cmonth = calDate.getMonth();
		var cday = calDate.getDate();*/
		// Date month 0-based (0=January)
		var cmonth = ((calDate.getMonth() + 1) < 10 ? ('0'+ (calDate.getMonth() + 1)) : (calDate.getMonth() + 1)); //calDate.getMonth();  
		var cday = (calDate.getDate() < 10 ? '0'+ (calDate.getDate()) : calDate.getDate());// calDate.getDate();
		// jquery datepicker month starts at 1 (1=January) so we add 1
		/*$("#dateSelect").datepicker("setDate", cyear + "-" + (cmonth + 1)+ "-" + cday);*/
		$("#dateSelect").val(cyear + "-" + (cmonth) + "-" + cday);
		
	
		if(toggleFlag_70200=="C"){//현재 달력이면
			fnSelectList_70200();
		}else{//스케쥴리스트면
			fnScheduleList_70200();
		}
		return false;
	});
	
	//색상 ColorPicker
	$("#colorSelectorBackground").ColorPicker({
		
		color : "#426ff5",
		onShow : function(colpkr) {
			$(colpkr).css("z-index","10000");
			$(colpkr).fadeIn(500);
			return false;
		},
		onHide : function(colpkr) {
			$(colpkr).fadeOut(500);
			return false;
		},
		onChange : function(
				hsb,
				hex,
				rgb) {
			$("#colorSelectorBackground div").css("backgroundColor","#"+ hex);
			$("#colorBackground").val("#"+ hex);
		}
	});

	//등록 버튼
	$("#tempInsert70200").click(function(){
		$("#save_type_70200").val("INSERT");
			
		//팝업 컨트롤 초기화
		popControlInit_70200();

		//$("#pop_om_code_70200").change();
		
		//팝업안 일자 셋팅
		$('#dateFrom_popup70200').val($('#dateSelect').val());
		$('#dateTo_popup70200').val($('#dateSelect').val());
		
		$("html, body").animate({scrollTop: 0}, 0);
		
		$("#title_70200").text("주요행사 등록");
		$("#tempPop70200").show();
		
		$('#pop_me_sj_70200').focus();
	});
	
	//팝업 저장버튼
	$("#tempSave70200").click(function(){
		fnSave70200();
	});
	
	//팝업 닫기버튼
	$("#tempClose70200, #tempCloseX70200").click(function(){
		$("#tempPop70200").hide();
	});
	
	
	//보기팝업 수정버튼
	$("#tempViewUpdate70200").click(function(){
		fnUpdate_70200();
	});
	
	//보기팝업 삭제버튼
	$("#tempViewDelete70200").click(function(){
		fnDelete70200();
	});
	
	//보기팝업 닫기버튼
	$("#tempViewClose70200, #tempViewCloseX70200").click(function(){
		$("#tempViewPop70200").hide();
	});
}

function fnDelete70200(){
	if(!confirm("해당 행사를 삭제 하시겠습니까?")){
		return;
	}
	//alert("$('#me_innb_70200').val() : " + $('#me_innb_70200').val());
	
	$.ajax({
		   type : "POST",
		   cache : false,
		   url  : "/event/delete",
		   dataType :"json",
		   data : {me_innb: $('#me_innb_70200').val(), "eventAuth" : eventAuth, "flag" : "u"},
		   /*data : {
			   me_innb : $('#me_innb_70200').val()
		   },*/
		   success  : function(data){
			    if(data == null){
					alert("권한이 없습니다.");
					return;
				}
				if(data.resultCnt==0 ){
					alert('삭제되지 않았습니다.');
					return;
				}else if(data.resultCnt==1){
					alert('삭제 되었습니다.');
					
					fnSelectList_70200();
					
					//닫기
					$("#tempViewClose70200").click();
					return;
				}
		   },
		   error : function(XMLHttpRequest, textStatus, error){
			alert("서버 통신 중 에러가 발생했습니다.");
		   }
		});
}
/**
 * @함수명: cbo1_70200
 * @작성일: 2015. 11. 40.
 * @작성자: 최수영
 * @설명: 지점 콤보 리스트
 * @param 
 */
function cbo1_70200(obj){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": "1"},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var listHtml = "";
	    	/*if(auth_flag==2)
	    		listHtml = "<option value='' selected='selected'>지점</option>";*/
			if( data.result.length > 0){
				if(data.result.length==1 && obj!=$("#om_code_70200")){
					listHtml += "<option value ='' data-om_innb ='' >선택</option>";
					var res = data.result[0];     
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' selected>"+ res.om_nm +"</option>";
				}else{
		    		for(var i=0; i<data.result.length; i++){
		    			var res = data.result[i];     
		    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
		    		}
				}
			}
			$("#auth_type_70200").val(parseInt(data.auth_flag));
			eventAuth_70200 = $("#auth_type_70200").val();
			
			obj.html(listHtml);
	    }
	});
}

function fnGetToday70200(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
    
    var chan_val = year + '-' + mon + '-' + day;
    
    return chan_val;
}

/**
 * @함수명: fnSelectList_70200
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 리스트 조회
 * @param 
 */
function fnSelectList_70200(){
	if($.trim($("#om_code_70200").val())==''){
		alert("지점을 선택하세요");
		$("#om_code_70200").focus();
		return;
	}
	
	//initMyCal_70200();
	jfcalplugin.deleteAllAgendaItems("#mycal");
	//jfcalplugin.reInit();
	
	$.ajax({
		url : "/event/list",
	    data:{
	    		yearMm  : $("#dateSelect").val().replace('-', '').replace('-', '').substring(0,6),
	    		om_code : $("#om_code_70200").val()
	    	  },
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    asynch : false,
	    success : function(data) {
	    	var arr = new Array();
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var res = data.result[i];     
					
					var what = res.me_sj;
					var em_dty = res.em_dty_code;
					//alert(em_dty);
					//alert(what);
					//var startDate = $("#dateFrom_popup70200").val();
					//var startDtArray = startDate.split("-");
					var startYear = res.schdul_bgnde.substring(0, 4);
					// jquery datepicker months start at 1 (1=January)		
					var startMonth = res.schdul_bgnde.substring(4, 6);
					var startDay = res.schdul_bgnde.substring(6, 8);
					// strip any preceeding 0's		
					startMonth = startMonth.replace(/^[0]+/g,"");
					startDay = startDay.replace(/^[0]+/g,"");
					var startHour = res.schdul_bgnde.substring(8, 10);
					var startMin = res.schdul_bgnde.substring(10, 12);
					if (startHour == "0" || startHour == "00") {
						startHour = 0;
					}else{
						startHour = parseInt(startHour.replace(/^[0]+/g,""));
					}
					
					if (startMin == "0" || startMin == "00") {
						startMin = 0;
					} else {
						startMin = parseInt(startMin.replace(/^[0]+/g,""));
					}
	
					//var endDate = res.schdul_endde;
					//var endDtArray = endDate.split("-");
					var endYear = res.schdul_endde.substring(0, 4);
					// jquery datepicker months start at 1 (1=January)		
					var endMonth = res.schdul_endde.substring(4, 6);
					
					var endDay = res.schdul_endde.substring(6, 8);
					// strip any preceeding 0's		
					endMonth = endMonth.replace(/^[0]+/g,"");
	
					endDay = endDay.replace(/^[0]+/g,"");
					var endHour = res.schdul_endde.substring(8, 10);
					var endMin = res.schdul_endde.substring(10, 12);
					//var endMeridiem = jQuery.trim($("#endMeridiem").val());
					if (endHour == "0" || endHour == "00") {
						endHour = 0;
					}else{
						endHour = parseInt(endHour.replace(/^[0]+/g,""));
					}
					if (endMin == "0"|| endMin == "00") {
						endMin = 0;
					} else {
						endMin = parseInt(endMin.replace(/^[0]+/g,""));
					}

					var startDateObj = new Date(
							parseInt(startYear),
							parseInt(startMonth) - 1,
							parseInt(startDay),
							startHour,
							startMin,
							0, 0);
					var endDateObj = new Date(
							parseInt(endYear),
							parseInt(endMonth) - 1,
							parseInt(endDay),
							endHour,
							endMin, 0,
							0);
					
					// add new event to the calendar
					jfcalplugin.addAgendaItem(
									"#mycal",
									what,
									startDateObj,
									endDateObj,
									false,
									{
										fname : "Santa",
										lname : "Claus",
										leadReindeer : "Rudolph",
										myDate : new Date(),
										//myNum : 42
										myNum : 42
									},
									{
										backgroundColor :  res.me_sj_color,//$("#colorBackground").val(),
										foregroundColor : "#ffffff"
									});
					//AgendaId:me_innb 
					
					//alert(jfcalplugin.getThisAgendaId());
					arr[i+1] = res.me_innb;
	        		}
				}
				arr_70200 = arr;
				return;
		    }
	});
}

/**
 * @함수명: fnScheduleList_70200
 * @작성일: 2015. 09. 18.
 * @작성자: 최수영
 * @설명: 리스트 조회
 * @param 
 */
function fnScheduleList_70200(){
	$.ajax({
		global : true,
		url : "/event/scheduleList",
	    data:{
	    	    kind    : "",
	    	    auth_flag : -999,
	    	    em_no : "",
	    		yearMm  : $("#dateSelect").val().replace('-', '').replace('-', '').substring(0,6),
	    		om_code : $("#om_code_70200").val(),
	    		sm_nm : ""
	    	  },
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	var ihtml="";
	    	ihtml+="<tr>";
    		ihtml+="	<th>일자</th>";
			ihtml+="	<th>시간</th>";
			ihtml+="	<th>주요내용</th>";
			ihtml+="</tr>";
			
			var tmp_date ='';
			var tdArray = new Array();
			var iVal=cnt=0;
			if(data!=null && data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var res = data.result[i];    
					if (i!=0 && tmp_date != res.t_days){
						tdArray[iVal] = cnt++; 
						cnt=0;
						
						ihtml+="<tr >";
						ihtml+="	<td id='td1_"+i+"'>" + res.t_days + res.week_text + "</td>";
					}else{
						if(i==0){
							ihtml+="<tr >";
							ihtml+="	<td id='td1_"+i+"'>" + res.t_days + res.week_text + "</td>";
						}
						tdArray[iVal] = 0;
					}
					ihtml+="	<td id='td2_"+i+"'>" ;
					
					if(res.me_allday_at=='Y'){
						ihtml+="종일";
					}else{
						ihtml+=res.schdul_bgnde_hhmm + "~" + res.schdul_endde_hhmm;
					}
					ihtml+="	</td>";
					
					ihtml+="	<td class='txt_left'>";
					ihtml+='		<div style="float: left; margin-right: 10px;">';
					ihtml+="			<div style='background-color: "+res.me_sj_color+"; width: 12px; height: 12px; border: 1px solid #d1cdd1; border-radius :0px'></div>";
					ihtml+="		</div>";
					ihtml+=	res.me_sj;
					ihtml+="	</td>";
					
					ihtml+="</tr>";
					
					if (tmp_date != res.t_days)
						iVal = i;
					
					cnt++;
					tmp_date = res.t_days;
				}
			}else{
				ihtml+="<tr><td colspan=3>조회된 내용이 없습니다.</td></tr>";
			}
			$('#tbody_70200').html(ihtml);
			
			for(var i=0; i<tdArray.length+1; i++){
				if(tdArray[i] > 0){
					$('#td1_'+i).attr("rowspan", tdArray[i]);
				}else
					$('#td2_'+i).parent().addClass("top-none");
			}
	    }
	});
}

/**
 * @함수명: fnView_70200
 * @작성일: 2015. 11. 10.
 * @작성자: 최수영
 * @설명: fnView_70200
 * @param 
 */
function fnView_70200(){
	//초기화
	viewPopupInit_70200();
	
	//해당 로우 조회
	$.ajax({
		global : true,
		url : "/event/row",
		data : {me_innb: $('#me_innb_70200').val(), "eventAuth" : eventAuth, "flag" : "v"},
		//data : {me_innb: $('#me_innb_70200').val()},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    		res = data.vo;
	    		
	    		$("#tempViewUpdate70200").val(res.me_innb);
				$("#tempViewDelete70200").val(res.me_innb);
				
				//타이틀
				$("#subject_title_70200").text(res.me_sj);
				//제목
				$("#view_me_Sj_70200").text(res.me_sj);
				//일정
				var year = res.schdul_bgnde.substring(0, 4);	
				var month = res.schdul_bgnde.substring(4, 6);
				var day = res.schdul_bgnde.substring(6, 8);
				var hhmm = "(" + (res.schdul_bgnde.substring(8, 10)) +":"+(res.schdul_bgnde.substring(10, 12)) + ")" ;
				var tmp_period = year +"-"+ month +"-"+day +" " + hhmm + "  ~  ";
				//alert(res.em_dty_code);
				eventAuth = res.em_dty_code;
				year = res.schdul_endde.substring(0, 4);	
				month = res.schdul_endde.substring(4, 6);
				day = res.schdul_endde.substring(6, 8);
				hhmm = "(" + res.schdul_endde.substring(8, 10) +":"+res.schdul_bgnde.substring(10, 12) + ")" ;
				tmp_period += year +"-"+ month +"-" + day +" " + hhmm;
				
				//등록구분
				if(res.me_holiday_at=='Y')
					$("#view_chk_me_holiday_at_70200").text("휴일");
				else
					$("#view_chk_me_holiday_at_70200").text("주요행사");
				
				$("#view_period_70200").text(tmp_period);
				//내용
				$("#view_me_cn_70200").text(res.me_cn);
				//등록자
				year = res.updt_de.substring(0, 4);	
				month = res.updt_de.substring(4, 6);
				day = res.updt_de.substring(6, 8);
				
				var tmp_dt= "("+ year +"-"+ month +"-" + day+ " " + res.updt_de.substring(8, 10) +":"+res.updt_de.substring(10, 12) +")";
				$("#view_updt_man_name_70200").text(res.updt_man_name +" "+ tmp_dt);
				
				for (var i = 0; i < data.omList.length; i++) {
					var om = data.omList[i];
					var span = $('<span>'+om.value+'</span>');
					$("#selectOmViewItem_70200").append(span);
				}
				
				for (var i = 0; i < data.smList.length; i++) {
					var sm = data.smList[i];
					var span = $('<span>'+sm.value+'</span>');
					$("#selectSmViewItem_70200").append(span);
				}
				//alert("data.attachVoList : " + data.attachVoList);
				if(data.attachVoList!=undefined){
					for (var i = 0; i < data.attachVoList.length; i++) {
						var attachVo = data.attachVoList[i];
						var html = '<li class="add-file-li type-image tx-existed" id="old-file'+attachVo.ai_no+'" >';
						html += '<dl>';
						html += '<dt class="tx-name noti-file-down" data-ai_no="'+attachVo.ai_no+'"  ><i class="fa ext" data-ext="'+attachVo.ai_nm+'" ></i>';
						var size = attachVo.ai_size.fileSize();
						html += attachVo.ai_nm+' ('+size+')';
						html += '</dt><dd class="tx-button">';
						html += '</dd>';
						html += '</dl>';
						html += '</li>';
						$("#fileViewList_70200").append(html);
					}
				}
				
				$(".noti-file-down").click(function(){
					location.href = "/file/down?ai_no="+$(this).data("ai_no");
				});
	    }
	});	
	$("#tempViewPop70200").show();
}


function viewPopupInit_70200(){
	//타이틀
	$("#subject_title_70200").text("");
	//제목
	$("#view_me_Sj_70200").text("");
	//일정
	$("#view_period_70200").text("");
	//내용
	$("#view_me_cn_70200").text("");
	//등록자
	$("#view_updt_man_name_70200").text("");
	//첨부파일
	$("#selectOmViewItem_70200").html("");
	$("#selectSmViewItem_70200").html("");
	$("#fileViewList_70200").html("");
}

/**
 * @함수명: fnUpdate_70200
 * @작성일: 2015. 11. 10.
 * @작성자: 최수영
 * @설명: fnView_70200
 * @param 
 */
function fnUpdate_70200(){
	$("#save_type_70200").val("UPDATE");
	
	//팝업 컨트롤 초기화
	popControlInit_70200();

	//해당 로우 조회
	$.ajax({
		global : true,
		url : "/event/row",
		data : {me_innb: $('#me_innb_70200').val(), "eventAuth" : eventAuth, "flag" : "u"},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
		    	if(data == null){
					alert("권한이 없습니다.");
					return;
				}
	    		res = data.vo;
				//타이틀
				$("#title_70200").text("주요행사 수정");
				//번호
				//$("#me_innb_70200").val(res.cm_code);
				//제목
				$("#pop_me_sj_70200").val(res.me_sj);
				 
				$("#am_no_70200").val(res.atchmnfl_innb);
				
				//일정
				var year = res.schdul_bgnde.substring(0, 4);	
				var month = res.schdul_bgnde.substring(4, 6);
				var day = res.schdul_bgnde.substring(6, 8);
				var tmp_period = year +"-"+ month +"-" + day;
				$("#dateFrom_popup70200").val(tmp_period);
				$("#hhFrom_popup70200").val(res.schdul_bgnde.substring(8, 10));
				$("#mmFrom_popup70200").val(res.schdul_bgnde.substring(10, 12));
				
				year = res.schdul_endde.substring(0, 4);	
				month = res.schdul_endde.substring(4, 6);
				day = res.schdul_endde.substring(6, 8);
				hhmm = "(" + res.schdul_endde.substring(8, 10) +":"+res.schdul_bgnde.substring(10, 12) + ")" ;
				tmp_period = year +"-"+ month +"-" + day;
				
				$("#dateTo_popup70200").val(tmp_period);
				$("#hhTo_popup70200").val(res.schdul_endde.substring(8, 10));
				$("#mmTo_popup70200").val(res.schdul_endde.substring(10, 12));
				
				//등록구분
				if(res.me_holiday_at!=null && res.me_holiday_at=='Y')
					$('input:radio[id=chk_me_holiday_at_Y_70200]').prop("checked", true);
				else
					$('input:radio[id=chk_me_holiday_at_N_70200]').prop("checked", true);
				$('input:radio[name=me_holiday_at]').prop("disabled", true);
				
				//종일
				if(res.me_allday_at!=null && res.me_allday_at=='Y')
					$('input:checkbox[id=chk_me_allday_at_70200]').prop("checked", true);
				else
					$('input:checkbox[id=chk_me_allday_at_70200]').prop("checked", false);
					
				$("#chk_me_allday_at_70200").data('value', res.me_allday_at);
				
				//스마트폰알림
				if(res.me_ntcn_at!=null && res.me_ntcn_at=='Y')
					$('input:checkbox[id=chk_me_ntcn_at_70200]').prop("checked", true);
				else
					$('input:checkbox[id=chk_me_ntcn_at_70200]').prop("checked", false);
					
				$("#chk_me_ntcn_at_70200").data('value', res.me_ntcn_at);

				//색상
				$("#colorBackground").val(res.me_sj_color);
				$('#colorSelectorBackground div:eq(0)').css("background-color", res.me_sj_color);
				
				//내용
				$("#pop_me_cn_70200").val(res.me_cn);

				//사원 처리
				$("#selectItem_om_nm_70200").html("");
				for (var i = 0; i < data.omList.length; i++) {
					var om = data.omList[i];
					var span = $('<span data-code="'+om.key+'" >'+om.value+'</span>');
					var a = $('<a href="#"/>');
					a.click(function (){
						$(this).parent().remove();
					});
					span.append(a);
					$("#selectItem_om_nm_70200").append(span);
				}

				$("#selectItem_sm_nm_70200").html("");
				for (var i = 0; i < data.smList.length; i++) {
					var sm = data.smList[i];
					var span = $('<span data-code="'+sm.key+'" >'+sm.value+'</span>');
					var a = $('<a href="#"/>');
					a.click(function (){
						$(this).parent().remove();
					});
					span.append(a);
					$("#selectItem_sm_nm_70200").append(span);
				}
				
				$("#file_70200ItemList").html("");
				for (var i = 0; i < data.attachVoList.length; i++) {
					var attachVo = data.attachVoList[i];
					var html = '<li class="add-file-li type-image tx-existed" id="old-file'+attachVo.ai_no+'" >';
					html += '<dl>';
					html += '<dt class="tx-name"><i class="fa ext" data-ext="'+attachVo.ai_nm+'" ></i>';
					var size = attachVo.ai_size.fileSize();
					html += attachVo.ai_nm+' ('+size+')';
					html += '</dt><dd class="tx-button">';
					html += '<a class="tx-delete notice-old-del" data-ai_no="'+attachVo.ai_no+'" >삭제</a>';
					html += '</dd>';
					html += '</dl>';
					html += '</li>';
					/*if(i == 0){
						$("#am_no_70200").val(attachVo.ai_no);
					}*/
					$("#file_70200ItemList").append(html);
				}
				
				$(".notice-old-del").click(function(){
					if(confirm("저장없이 바로삭제됩니다.\n삭제하시겠습니까?")){
						fnFileDel70200($(this).data("ai_no"));
					}
				});
				
				$(".noti-file-down").click(function(){
					location.href = "/file/down?ai_no="+$(this).data("ai_no");
				});
				
				//팝업 show
				$("#tempViewPop70200").hide();
				$("#tempPop70200").show();
	    }
	});	
	return;
}

function fnFileDel70200(ai_no){
	$.ajax({
		url : "/file/delete",
		data : {"ai_no":ai_no},
		type : "POST",
		dataType : "json",
		cache: false,
		success : function(data) {
			if(data != "" ){
				alert("삭제되었습니다.");
				$("#old-file"+ai_no).remove();
			}else{
				alert("삭제오류!!");
			}
		}
	}); 
}
/**
 * @함수명: fnSave70200
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 회사코드 입력/삭제
 * @param 
 */
function fnSave70200(){
	
	var tmp1 = parseInt($("#dateFrom_popup70200").val().replace("-", "").replace("-", ""));
	var tmp2 = parseInt($("#dateTo_popup70200").val().replace("-", "").replace("-", ""));
	
	if(tmp1 > tmp2){
		alert("기간을 확인하세요");
		//$("#dateFrom_popup70200").focus();
		return;
	}
	
	if(tmp1 == tmp2){
		var time1 = parseInt(($("#hhFrom_popup70200").val()+$("#mmFrom_popup70200").val()));
		var time2 = parseInt(($("#hhTo_popup70200").val()+$("#mmTo_popup70200").val()));

		if(time1 > time2){
			alert("시간을 확인하세요");
			//$("#dateFrom_popup70200").focus();
			return;
		}
	}
	
	var omGroup = $("#selectItem_om_nm_70200 span");
	var smGroup = $("#selectItem_sm_nm_70200 span");
	var omArr = new Array();
	var smArr = new Array();

	for (var i = 0; i < omGroup.length; i++) {
		omArr.push({
			"om_code":omGroup.eq(i).attr("data-code")
		});
	}	
	
	for (var i = 0; i < smGroup.length; i++) {
		smArr.push({
			"sm_code":smGroup.eq(i).attr("data-code")
		});
	}	
	
	//alert($('input:radio[name="me_holiday_at"]:checked').val() + " "+ omGroup.length + " "+ (smGroup.length == 0));
	if($('input:radio[name="me_holiday_at"]:checked').val()=='N' 
		&& (omGroup.length == 0) && (smGroup.length == 0) && parseInt(auth_flag) > 1) {
		alert("수신지점 또는 수신매장을 입력해 주세요.");
		return;
	}
	
/*	if($('input:radio[name="chk_me_holiday_at_N_70200"]:checked').val()=='N' && parseInt(auth_flag) > 1){
		omArr.push({
			"om_code":login_bhf_cd
		});
	}
*/	
	
	//*****************************************************************************************
	if($.trim($('#pop_me_sj_70200').val()) == ''){
		alert("제목을 입력하세요.");
		$("#pop_me_sj_70200").focus();
		 return;
	}
	//*****************************************************************************************
	
	var tmp1 = parseInt($("#dateFrom_popup70200").val().replace("-", "").replace("-", ""));
	var tmp2 = parseInt($("#dateTo_popup70200").val().replace("-", "").replace("-", ""));
	
	if(tmp1 > tmp2){
		alert("기간을 확인하세요");
		//$("#dateFrom_popup70200").focus();
		return;
	}
	//*****************************************************************************************
	
	var me_innb = "";	
	var atchmnfl_innb = "";	
	var saveType =  $('#save_type_70200').val();
	
	if(saveType =="UPDATE"){
		me_innb = $("#me_innb_70200").val();
		atchmnfl_innb = $("#am_no_70200").val();
	}
	
	var meSj = $.trim($("#pop_me_sj_70200").val());
	var meCn = $.trim($("#pop_me_cn_70200").val());
	
	var dateFrom= $.trim($("#dateFrom_popup70200").val().replace('-','').replace('-',''))
					+ $.trim($("#hhFrom_popup70200").val()) 
					+ $.trim($("#mmFrom_popup70200").val()) ;
	var dateTo= $.trim($("#dateTo_popup70200").val().replace('-','').replace('-',''))
					+ $.trim($("#hhTo_popup70200").val()) 
					+ $.trim($("#mmTo_popup70200").val()) ;

	var allDayAt = $.trim($("#chk_me_allday_at_70200").data("value"));
	var meNtcnAt = $.trim($("#chk_me_ntcn_at_70200").data("value"));
	//alert(allDayAt);
	var color = $.trim($("#colorBackground").val());
	
	var holidayAt =$.trim($('input:radio[name="me_holiday_at"]:checked').val());

	
	//alert("saveType :" + saveType + "\n me_innb:" + me_innb+ "\n meSj:" + meSj+ "\n meCn:" + meCn+ "\n allDayAt:" + allDayAt+ "\n meNtcnAt:" + meNtcnAt+  "\n atchmnfl_innb:" + atchmnfl_innb);
	//return;
	var saveData = {
			 saveType : saveType  // SAVE 타입
			,me_innb  : me_innb    // PK
			,omArr    : omArr	  // 지점
			,smArr    : smArr	  // 매장
			,me_sj    : meSj      // 제목
			,me_cn    : meCn	  // 내용
			,schdul_bgnde   : dateFrom    // dateFrom 
			,schdul_endde   : dateTo      // dateTo
			,me_allday_at 	: allDayAt 	  // 종일
			,me_ntcn_at 	: meNtcnAt    // 스마트폰 알림
			,me_sj_color 	: color    // 알림색상
			,atchmnfl_innb 	: atchmnfl_innb    // 첨부파일마스터 no
			,me_holiday_at 	: holidayAt    // 휴일등록 여부
			
	};
	
	//SAVE
	var url = "/event/save";
	$.ajaxSettings.traditional = false;
	$.ajax({
		global:true,
		url : url,
		data:saveData,
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(me_innb) {
			if(me_innb != ""){
				fnSaveFile_70200(me_innb);
				var goPg = 1;
				if(saveType == "U"){
					goPg = eventPg_70200;
				}
				//fnGetList(goPg);
			}else{
				alert("저장실패.");
			}
		}
	}); 
}

function fnSaveFile_70200(me_innb){
	var am_no = $("#am_no_70200").val();
	var formData = new FormData();
	
	if(fileList_70200.length > 0) {
		for (var i = 0; i < fileList_70200.length; i++) {
			formData.append('files', fileList_70200[i]);
		}
	}else{
		alert("저장 되었습니다.");
		$("#tempPop70200").hide();
		fnSelectList_70200();
		//fnSetNoticePopClean();
		return;
	}
	formData.append('me_innb', me_innb);
	formData.append('am_no', am_no);
	$.ajax({
		url : "/event/saveFile",
		data : formData,
		type : "POST",
		dataType : "json",
		async : false,
		global: true,		
		cache: false,
        contentType: false,
        processData: false,
        beforeSend :function(){
			  fnSetAjaxFileLoding();
		},
		success : function(data) {
			if(data > 0 ){
				alert("저장 되었습니다.");
				$("#tempPop70200").hide();
				fnSelectList_70200();
				fileList_70200 = []; //추가되었습니다.
				fileId = 1;			 //추가되었습니다.
				$("#file_70200").file({fileList : fileList_70200});  //추가되었습니다.
			}else{
				alert("파일저장실패.");
			}
			
		}
	}); 
}

/**
 * @함수명: popControlInit_70200
 * @작성일: 2015. 09. 18
 * @작성자: 최수영
 * @설명: 팝업 컨트롤 초기화
 * @param 
 */
function popControlInit_70200(){
	
	//휴일
	$('input:radio[id=chk_me_holiday_at_N_70200]').prop("checked", true);
	$('input:radio[name=me_holiday_at]').prop("disabled", false);
	
	
	$('#chk_me_holiday_at_70200').data('value', 'N');
	//지점
	$('#pop_om_code_70200').val('');
	$("#selectItem_om_nm_70200").html("");
	$('#pop_om_code_70200').prop('disabled',false);
	//매장
	$('#pop_sm_nm_70200').val('');
	$("#selectItem_sm_nm_70200").html("");
	$('#pop_sm_nm_70200').prop('disabled',false);
	//제목
	$('#pop_me_sj_70200').val('');
	
	//기간
	//$('#dateFrom_popup70200').val('');
	$('#hhFrom_popup70200').val('00');
	$('#mmFrom_popup70200').val('00');
	$('#hhFrom_popup70200').prop('disabled',false);
	$('#mmFrom_popup70200').prop('disabled',false);
	//('#dateTo_popup70200').val('');
	$('#hhTo_popup70200').val('00');
	$('#mmTo_popup70200').val('00');
	$('#hhTo_popup70200').prop('disabled',false);
	$('#mmTo_popup70200').prop('disabled',false);
	//내용
	$('#pop_me_cn_70200').val('');
	
	//색상
	$("#colorBackground").val("#426ff5");
	$('#colorSelectorBackground div:eq(0)').css("background-color", "#426ff5");
	
	//종일
	$('input:checkbox[id=chk_me_allday_at_70200]').prop("checked", false);
	$('#chk_me_allday_at_70200').data('value', 'N');
	
	//스마트폰 알림
	$('input:checkbox[id=chk_me_ntcn_at_70200]').prop("checked", false);
	$('#chk_me_ntcn_at_70200').data('value', 'N');

	
	//첨부파일
	$("#file_70200ItemList").html('');
	$('#am_no_70200').val('');
	$('#file_70200List').prop('disabled',false);
	$('#pop_me_sj_70200').focus();

}

//오토컴플릿
function fnMakeAutoComplete70200(omArray){
	var om_code = String(omArray);
	 
	//지점 콤보박스
	$("#pop_om_code_70200").change(function(){
		var code = $(this).val();
		var codeNm = $(this).children("option:selected").text();
		var appendCheck = $("#selectItem > span[data-code='"+code+"']").length;
		if(code != "" && appendCheck == 0){
			var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
			var a = $('<a href="#"/>');
			a.click(function (){
				$(this).parent().remove();
			});
			
			if(validation(code)){
				span.append(a);
				$("#selectItem_om_nm_70200").append(span);
			}
		}
		$(this).val(0);
	});
	
	//중복체크
	function validation(code) {
		var omGroup = $("#selectItem_om_nm_70200 span");
		
		for (var i = 0; i < omGroup.length; i++) {
			if(code == omGroup.eq(i).attr("data-code")) {
				alert("이미 선택한 지점입니다.");
				return false;
			}
		}
		return true;
	}
    
    $("#pop_sm_nm_70200").autocomplete({
    	serviceUrl:'/70/martList',
        minChars:1,
        paramName: 'keyword',
        zIndex: 9999,
        params:{
        	om_code : om_code
        },
        noCache: true, 
        onSelect: function(result){ 
        	var code = result.key;
        	var codeNm = result.value;
	   		var appendCheck = $("#mart > span[data-code='"+code+"']").length;
	   		
	   		if(code != "" && appendCheck == 0){
	   			var span = $('<span data-code="'+code+'" >'+codeNm+'</span>');
	   			var a = $('<a href="#" />');
	   			a.click(function (){
	   				$(this).parent().remove();
 	   			});
	   			if(validationMaket(code)){
					span.append(a);
					$("#selectItem_sm_nm_70200").append(span);
				}
	   		}
	   		$(this).val("");
        }
    });
    
    function validationMaket(code) {
    	var smGroup = $("#selectItem_sm_nm_70200 span");
    	
    	for (var i = 0; i < smGroup.length; i++) {
    		if(code == smGroup.eq(i).attr("data-code")) {
    			alert("이미 선택한 매장입니다.");
    			return false;
    		}
    	}
    	
    	return true;
    }
}

function initMyCal_70200(){

	var clickDate = "";
	var clickAgendaItem = "";

	//var jfcalplugin = $("#mycal")
	jfcalplugin = $("#mycal")
			.jFrontierCal(
					{
						date : new Date(),
						dayClickCallback : myDayClickHandler,
						agendaClickCallback : myAgendaClickHandler,
						agendaDropCallback : myAgendaDropHandler,
						agendaMouseoverCallback : myAgendaMouseoverHandler,
						applyAgendaTooltipCallback : myApplyTooltip,
						agendaDragStartCallback : myAgendaDragStart,
						agendaDragStopCallback : myAgendaDragStop,
						dragAndDropEnabled : true
					}).data("plugin");

	/**
	 * Do something when dragging starts on agenda div
	 */
	function myAgendaDragStart(eventObj, divElm, agendaItem) {
		//alert("myAgendaDragStart");
		// destroy our qtip tooltip
		if (divElm.data("qtip")) {
			divElm.qtip("destroy");
		}
		
	}
	;
	/**
	 * Do something when dragging stops on agenda div
	 */
	function myAgendaDragStop(eventObj, divElm,	agendaItem) {
		//alert("myAgendaDragStop");
		//alert("drag stop");
	};

	/**
	 * Custom tooltip - use any tooltip library you want to display the agenda data.
	 * for this example we use qTip - http://craigsworks.com/projects/qtip/
	 *
	 * @param divElm - jquery object for agenda div element
	 * @param agendaItem - javascript object containing agenda data.
	 */
	function myApplyTooltip(divElm, agendaItem) {
		
		//alert("myApplyTooltip");
		// Destroy currrent tooltip if present
		
		
		if (divElm.data("qtip")) {
			divElm.qtip("destroy");
		}

		var displayData = "";

		var title = agendaItem.title;
		var startDate = agendaItem.startDate;
		var endDate = agendaItem.endDate;
		var allDay = agendaItem.allDay;
		var data = agendaItem.data;
		/*
		displayData += "<br><b>" + title
				+ "</b><br><br>";
		if (allDay) {
			displayData += "(All day event)<br><br>";
		} else {
			displayData += "<b>Starts:</b> "
					+ startDate + "<br>"
					+ "<b>Ends:</b> " + endDate
					+ "<br><br>";
		}
		for ( var propertyName in data) {
			displayData += "<b>" + propertyName
					+ ":</b> " + data[propertyName]
					+ "<br>";
		}
		*/
		
		displayData += "<b>" + title;
		// use the user specified colors from the agenda item.
		var backgroundColor = agendaItem.displayProp.backgroundColor;
		var foregroundColor = agendaItem.displayProp.foregroundColor;
		var myStyle = {
			border : {
				width : 1,
				radius : 10
			},
			/*width : title.length * 20,*/
			padding : 15,
			textAlign : "left",
			tip : true,
			name : "dark" // other style properties are inherited from dark theme		
		};
		if (backgroundColor != null && backgroundColor != "") {
			myStyle["backgroundColor"] = backgroundColor;
		}
		if (foregroundColor != null && foregroundColor != "") {
			myStyle["color"] = foregroundColor;
		}
		// apply tooltip
		/*divElm.qtip({
			content : displayData,
			position : {
				corner : {
					tooltip : "bottomMiddle",
					target : "topMiddle"
				},
				adjust : {
					mouse : true,
					x : 0,
					y : -15
				},
				target : "mouse"
			},
			show : {
				when : {
					event : 'mouseover'
				}
			},
			style : myStyle
		});*/
		

	}
	;

	/**
	 * Make the day cells roughly 3/4th as tall as they are wide. this makes our calendar wider than it is tall. 
	 */
	jfcalplugin.setAspectRatio("#mycal", 0.75);

	/**
	 * Called when user clicks day cell
	 * use reference to plugin object to add agenda item
	 */
	function myDayClickHandler(eventObj) {
		//alert("myDayClickHandler");
		
		// Get the Date of the day that was clicked from the event object
		var date = eventObj.data.calDayDate;
		
		// store date in our global js variable for access later
		clickDate = date.getFullYear() 
					+ "-" + ((date.getMonth() + 1) < 10 ? ('0'+ (date.getMonth() + 1)) : (date.getMonth() + 1))
					+ "-" + (date.getDate() < 10 ? ('0'+ date.getDate()) : date.getDate()) ;
		// open our add event dialog
		//$('#add-event-form').dialog('open');
		
		//팝업초기화
		popControlInit_70200();
		
		//팝업안 일자 셋팅
		$('#dateFrom_popup70200').val(clickDate);
		$('#dateTo_popup70200').val(clickDate);
		
		$("html, body").animate({scrollTop: 0}, 0);
		
		$('#save_type_70200').val("INSERT");
		$('#tempPop70200').show();
		
		$('#pop_me_sj_70200').focus();
	};
	
	/**
	 * Called when user clicks and agenda item
	 * use reference to plugin object to edit agenda item
	 */
	function myAgendaClickHandler(eventObj) {
		//alert("myAgendaClickHandler");
		// Get ID of the agenda item from the event object
		var agendaId = eventObj.data.agendaId;
		//alert(agendaId);
		//alert("agendaId:"+agendaId+", arr_70200[agendaId] :" + arr_70200[agendaId]);
		/*
		// pull agenda item from calendar
		var agendaItem = jfcalplugin.getAgendaItemById("#mycal", agendaId);
		clickAgendaItem = agendaItem;
		$("#display-event-form").dialog('open');
		*/
		$('#me_innb_70200').val(arr_70200[agendaId]);
		$("html, body").animate({scrollTop: 0}, 0);
		fnView_70200();
		
	};

	/**
	 * Called when user drops an agenda item into a day cell.
	 */
	function myAgendaDropHandler(eventObj) {
		//alert("myAgendaDropHandler");
		// Get ID of the agenda item from the event object
		var agendaId = eventObj.data.agendaId;
		// date agenda item was dropped onto
		var date = eventObj.data.calDayDate;
		// Pull agenda item from calendar
		var agendaItem = jfcalplugin.getAgendaItemById("#mycal", agendaId);
		/*alert("You dropped agenda item "
				+ agendaItem.title
				+ " onto "
				+ date.toString()
				+ ". Here is where you can make an AJAX call to update your database.");
		*/
		
		//var now = new Date();
	    var year= date.getFullYear();
	    var mon = (date.getMonth()+1)>9 ? ''+(date.getMonth()+1) : '0'+(date.getMonth()+1);
	    var day = date.getDate()>9 ? ''+date.getDate() : '0'+date.getDate();
	    
	    var chan_val = year + '' + mon + '' + day;
	    
	    //alert("chan_val : " + chan_val +", arr_70200[agendaId] : " + arr_70200[agendaId]);
	    
		//AgendaId:me_innb 
		$.ajax({
			   type : "POST",
			   cache : false,
			   url  : "/event/updatePeriod",
			   dataType :"json",
			   data : {
				   schdul_bgnde : chan_val,
				   me_innb : arr_70200[agendaId]
			   },
			   success  : function(data){
					if(data.resultCnt==0 ){
						//alert('저장되지 않았습니다.');
						return;
					}else if(data.resultCnt==1){
						//alert('저장 되었습니다.');
						//fnSelectList_70200();
						return;
					}
			   },
			   error : function(XMLHttpRequest, textStatus, error){
				alert("서버 통신 중 에러가 발생했습니다.");
			   }
			});
	};

	/**
	 * Called when a user mouses over an agenda item	
	 */
	function myAgendaMouseoverHandler(eventObj) {
		//alert("myAgendaMouseoverHandler");
		var agendaId = eventObj.data.agendaId;
		var agendaItem = jfcalplugin.getAgendaItemById("#mycal", agendaId);
		//alert("You moused over agenda item " + agendaItem.title + " at location (X=" + eventObj.pageX + ", Y=" + eventObj.pageY + ")");
	};
	
	/**
	 * Initialize jquery ui datepicker. set date format to yyyy-mm-dd for easy parsing
	 */
	/*$("#dateSelect").datepicker({
		showOtherMonths : true,
		selectOtherMonths : true,
		changeMonth : true,
		changeYear : true,
		showButtonPanel : true,
		dateFormat : 'yy-mm-dd'
	});
	 */
	/**
	 * Set datepicker to current date
	 */
	//$("#dateSelect").datepicker('setDate', new Date());
	$("#dateSelect").val(fnGetToday70200());
	/**
	 * Use reference to plugin object to a specific year/month
	 */
	$("#dateSelect")
			.bind(
					'change',
					function() {
						var selectedDate = $("#dateSelect").val();
						var dtArray = selectedDate.split("-");
						var year = dtArray[0];
						// jquery datepicker months start at 1 (1=January)		
						var month = dtArray[1];
						// strip any preceeding 0's		
						month = month.replace(/^[0]+/g,"");
						var day = dtArray[2];
						// plugin uses 0-based months so we subtrac 1
						jfcalplugin.showMonth("#mycal", year, parseInt(month - 1).toString());
					});

}

