//var data1 = new google.visualization.DataTable();
//var data2 = new google.visualization.DataTable();
//var data3 = new google.visualization.DataTable();

var chart1 = new google.visualization.PieChart(document.getElementById('chart_10500_1'));
var chart2 = new google.visualization.PieChart(document.getElementById('chart_10500_2'));
var chart3 = new google.visualization.PieChart(document.getElementById('chart_10500_3'));
/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	/* // Load the Visualization API and the piechart package.
    google.load('visualization', '1.0', {'packages':['corechart']}); */
	
    // Set a callback to run when the Google Visualization API is loaded.
	
	$("#noticeViewPop10500, #tempViewPop10500").instancePopUp();

	//지점콤보
	cbo1_10500();
	
	fnMakeEventComponent_10500();	// 버튼 이벤트 등록
	
	//금일정보
	$("#title10500").text(fnGetToday10500());
	
	//공지사항
	fnGetNoticeList_10500(1);
	
	//주요행사
	fnGetEventList_10500();
	
	//reload
	//setTimeout("fnReload_10500()", 10000); // 1000ms(1초)가 경과하면 fnReload_10500() 함수를 실행합니다.
	fnChart_10500();
//	google.setOnLoadCallback(fnChart_10500);
	
});

/**
 * @함수명: cbo1_20200
 * @작성일: 2015. 11. 40.
 * @작성자: 최수영
 * @설명: 지점 콤보 리스트
 * @param 
 */
function cbo1_10500(){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": "1"},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var listHtml = "";//"<option value='' selected='selected'>전체</option>";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"' data-om_innb ='"+res.om_code+"' >"+ res.om_nm +"</option>";
	    		}
			}

			if(auth_flag==1)
				$('#om_code_10500').css("display", "");
			else
				$('#om_code_10500').css("display", "none");
			
			$('#om_code_10500').html(listHtml);
			$("#noticeTmCode10500").val(data.tm_code);	//팀코드
	    }
	});
}

function fnReload_10500(){
	
	//차트
	//google.setOnLoadCallback(fnChart_10500);
	fnChart_10500();
	
	//공지사항
	fnGetNoticeList_10500(1);
	
	//주요행사
	fnGetEventList_10500();
}

function fnChart_10500() {
	fnGetChat1();
	fnGetChat2();
	fnGetChat3();
	/*
	// Create the data table.
	  var data = new google.visualization.DataTable();
	  data.addColumn('string', 'Topping');
	  data.addColumn('number', 'Slices');
	  data.addRows([
	    ['Mushrooms', 3],
	    ['Onions', 1],
	    ['Olives', 1],
	    ['Zucchini', 1],
	    ['Pepperoni', 2]
	  ]);

	  // Set chart options
	  var options = {'title':'금일 근태 현황',
	                 'width':600,
	                 'height':400,
	                 'colors':  ["#F7819F", "#FE9A2E", "#059661", "#AC58FA", "#2E64FE"]
	                 };

	  // Instantiate and draw our chart, passing in some options.
	  var chart = new google.visualization.PieChart(document.getElementById('chart_10500_1'));
	  chart.draw(data, options);
  
  chart = new google.visualization.PieChart(document.getElementById('chart_10500_2'));
  chart.draw(data, options);
  
  chart = new google.visualization.PieChart(document.getElementById('chart_10500_3'));
  chart.draw(data, options);
  
  */
}

function fnGetChat1(){
	
	var name0 = "";
	var name1 = "";
	var name2 = "";
	var cnt0 = 0;
	var cnt1 = 0;
	var cnt2 = 0;
	//alert("a11");
	$.ajax({
		url : "/dash/chart/selectEmp",
		type : "POST",
		dataType : "json",
		data:{"om_code" : $("#om_code_10500").val()},
		cache : false,
		async : true,
		global : true,
		success : function(data) {
			var data1 = new google.visualization.DataTable(); 
			if (data.result.length > 0){
				name0 = data.result[0].c_name.replace('여사원','').replace('MD','');
				name1 = data.result[1].c_name.replace('여사원','').replace('MD','');
				
				if(data.result.length>2)
					name2 = data.result[2].c_name.replace('여사원','').replace('MD','');
				else{
					name2 = '팀장';
				}
				
				cnt0 = parseInt(data.result[0].cnt);
				cnt1 = parseInt(data.result[1].cnt);
				if(data.result.length>2)
					cnt2 = parseInt(data.result[2].cnt);
				
				  data1.addColumn('string', '직무');
				  data1.addColumn('number', '직원수');
				  data1.addRows([
				    [name0, cnt0],
				    [name1, cnt1],
				    [name2, cnt2]
				  ]);

			}else{

//				var data1 = new google.visualization.DataTable();
				  data1.addColumn('string', '직무');
				  data1.addColumn('number', '직원수');
				  data1.addRows([
				    ["고정", 0],
				    ["순회", 0], 
				    ["팀장", 0]
				  ]);
			}

			  // Set chart options
			  var options = {'title':'직무별 직원통계',
			                 'width':600,
			                 'height':400,
			                 'colors':  ["#678ceb", "#bcd800", "#ff992f"],
			                 'fontSize':15,
			                 'fontName': 'Gothic',
			                 'titleTextStyle':{fontSize: 20},
			  				 'sliceVisibilityThreshold':0,
			  				 'pieHole': 0.2,
			  				 /*'legend': 'none',*/
			  				 'pieSliceText': 'value'
			                 };

//			  var chart = new google.visualization.PieChart(document.getElementById('chart_10500_1'));
			  chart1.draw(data1, options);
		}
	});
}

function fnGetChat2(){
	if(auth_flag=='4'){
		$('#chart_10500_2').css('visibility','hidden');
		return;
	}
	
	var name0 = "";
	var name1 = ""; 
	var name2 = "";
	var name3 = "";
	var name4 = "";
	var cnt0 = 0;
	var cnt1 = 0;
	var cnt2 = 0;
	var cnt3 = 0;
	var cnt4 = 0;
	
	$.ajax({
		url : "/dash/chart/fix",
		type : "POST",
		dataType : "json",
		data:{"om_code" : $("#om_code_10500").val()},
		cache : false,
		async : true,
		global : true,
		success : function(data) {
			var data2 = new google.visualization.DataTable();
			if (data.result.length > 0 ) {

				cnt0 = parseInt(data.result[0].a_go);
				cnt1 = parseInt(data.result[0].a_leave);
				cnt2 = parseInt(data.result[0].a_yet);
				cnt3 = parseInt(data.result[0].a_vacation);
				cnt4 = parseInt(data.result[0].a_education);

				  data2.addColumn('string', '출퇴근 구분');
				  data2.addColumn('number', '카운트');
				  data2.addRows([
				    ["출근" , cnt0],
				    ["퇴근" , cnt1],
				    ["미출근", cnt2],
				    ["휴가", cnt3],
				    ["교육", cnt4]
				  ]);

				  // Set chart options
				  var options = {'title':'금일 고정MD 근태 현황',
						  		 'titleTextStyle':{fontSize: 20},
				                 'width':600,
				                 'height':400,
				                 'fontSize':15,
				                 'fontName': 'Gothic',
			                	 'sliceVisibilityThreshold':0,
			                	 'pieHole': 0.2,
				                 'colors':  ["#FD4769", "#31B6BB", "#E6003C", "#D3B08C", "#FFCC00"],
				                 /*'legend': 'none',*/
				                 'pieSliceText': 'value'
				                 };

//				  var chart = new google.visualization.PieChart(document.getElementById('chart_10500_2'));
				  chart2.draw(data2, options);
			}else{
				$('#chart_10500_2').css('visibility','hidden');
			}
		}
	});
}

function fnGetChat3(){ 
	if(auth_flag=='3'){
		$('#chart_10500_3').css('visibility','hidden');
		return;
	}
	
	var name0 = "";
	var name1 = "";
	var name2 = "";
	var name3 = "";
	var name4 = "";
	var cnt0 = 0;
	var cnt1 = 0;
	var cnt2 = 0;
	var cnt3 = 0;
	var cnt4 = 0;
	
	$.ajax({
		url : "/dash/chart/rnd",
		type : "POST",
		dataType : "json",
		data:{"om_code" : $("#om_code_10500").val()},
		cache : false,
		async : true,
		global : true,
		success : function(data) {
			var data3 = new google.visualization.DataTable();
			if (data.result.length > 0 ) {
				
				
				cnt0 = parseInt(data.result[0].a_go);
				cnt1 = parseInt(data.result[0].a_leave);
				cnt2 = parseInt(data.result[0].a_yet);
				cnt3 = parseInt(data.result[0].a_vacation);
				cnt4 = parseInt(data.result[0].a_education);
			//}	
				  data3.addColumn('string', '출퇴근 구분');
				  data3.addColumn('number', '카운트');
				  data3.addRows([
				    ["출근" , cnt0],
				    ["완료" , cnt1],
				    ["미출근", cnt2],
				    ["휴가", cnt3],
				    ["교육", cnt4]
				  ]);

				  // Set chart options
				  var options = {'title':'금일 순회MD 근태 현황',
				                 'width':600,
				                 'height':400,
				                 'fontSize':15,
				                 'fontName': 'Gothic',
				                 'titleTextStyle':{fontSize: 20},
				                 'sliceVisibilityThreshold':0,
				                 'pieHole': 0.2,
				                 'colors':  ["#FD4769", "#31B6BB", "#E6003C", "#D3B08C", "#FFCC00"],
				                 'pieSliceText': 'value'
				                 };

//				  var chart = new google.visualization.PieChart(document.getElementById('chart_10500_3'));
				  chart3.draw(data3, options);
			}else{
				$('#chart_10500_3').css('visibility','hidden');
			}
		}
	});
}


function viewPopupInit_10500(){
	//타이틀
	$("#subject_title_10500").text("");
	//제목
	$("#view_me_Sj_10500").text("");
	//일정
	$("#view_period_10500").text("");
	//내용
	$("#view_me_cn_10500").text("");
	//등록자
	$("#view_updt_man_name_10500").text("");
	//첨부파일
	$("#selectOmViewItem_10501").html("");
	$("#selectSmViewItem_10501").html("");
	$("#fileViewList_10500").html("");
}
/**
 * @함수명: fnView_10500
 * @작성일: 2015. 11. 10.
 * @작성자: 최수영
 * @설명: fnView_10500
 * @param 
 */
function fnViewEvent_10500(me_innb){
	//초기화
	viewPopupInit_10500();
	
	
	//해당 로우 조회
	$.ajax({
		global : true,
		url : "/event/row",
		data : {"me_innb": me_innb, "eventAuth" : login_dty_cd, "flag" : "v"},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    		res = data.vo;
	    		
				//타이틀
				$("#subject_title_10500").text("주요행사");
				//제목
				$("#view_me_Sj_10500").text(res.me_sj);
				//일정
				var year = res.schdul_bgnde.substring(0, 4);	
				var month = res.schdul_bgnde.substring(4, 6);
				var day = res.schdul_bgnde.substring(6, 8);
				var hhmm = "(" + (res.schdul_bgnde.substring(8, 10)) +":"+(res.schdul_bgnde.substring(10, 12)) + ")" ;
				var tmp_period = year +"-"+ month +"-"+day +" " + hhmm + "  ~  ";
				
				year = res.schdul_endde.substring(0, 4);	
				month = res.schdul_endde.substring(4, 6);
				day = res.schdul_endde.substring(6, 8);
				hhmm = "(" + res.schdul_endde.substring(8, 10) +":"+res.schdul_bgnde.substring(10, 12) + ")" ;
				tmp_period += year +"-"+ month +"-" + day +" " + hhmm;
				
				//등록구분
				if(res.me_holiday_at=='Y')
					$("#view_chk_me_holiday_at_10500").text("휴일");
				else
					$("#view_chk_me_holiday_at_10500").text("주요행사");
				
				$("#view_period_10500").text(tmp_period);
				//내용
				$("#view_me_cn_10500").text(res.me_cn);
				//등록자
				year = res.updt_de.substring(0, 4);	
				month = res.updt_de.substring(4, 6);
				day = res.updt_de.substring(6, 8);
				
				var tmp_dt= "("+ year +"-"+ month +"-" + day+ " " + res.updt_de.substring(8, 10) +":"+res.updt_de.substring(10, 12) +")";
				$("#view_updt_man_name_10500").text(res.updt_man_name +" "+ tmp_dt);
				
				for (var i = 0; i < data.omList.length; i++) {
					var om = data.omList[i];
					var span = $('<span>'+om.value+'</span>');
					$("#selectOmViewItem_10501").append(span);
				}
				
				for (var i = 0; i < data.smList.length; i++) {
					var sm = data.smList[i];
					var span = $('<span>'+sm.value+'</span>');
					$("#selectSmViewItem_10501").append(span);
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
						$("#fileViewList_10500").append(html);
					}
				}
				
				$(".noti-file-down").click(function(){
					location.href = "/file/down?ai_no="+$(this).data("ai_no");
				});
	    }
	});	
	$("#tempViewPop10500").show();
}

function fnGetEventList_10500(){
	$.ajax({
		url : "/event/dashList",
		type : "POST",
		dataType : "json",
		data:{"om_code" : $("#om_code_10500").val()},
		cache : false,
		acync : false,
		success : function(data) {
			var html = '';
			if (data.result.length > 0) {
				for (var i = 0; i < data.result.length; i++) {
					var eventVo = data.result[i];
					html+='<tr>';
					html+='<td>' + (i+1) + '</td>';
					html+='<td class="txt_left">';
					html+='	<a class="event-view" data-me_innb="'+eventVo.me_innb+'" href="#">'+eventVo.me_sj+'</a>';
					html+='</td>';
					
					var year = eventVo.schdul_bgnde.substring(2, 4);	
					var month = eventVo.schdul_bgnde.substring(4, 6);
					var day = eventVo.schdul_bgnde.substring(6, 8);
					var hhmm = "(" + (eventVo.schdul_bgnde.substring(8, 10)) +":"+(eventVo.schdul_bgnde.substring(10, 12)) + ")" ;
					var hhmm2 = "(" + (eventVo.schdul_endde.substring(8, 10)) +":"+(eventVo.schdul_endde.substring(10, 12)) + ")" ;
					var tmp_period1 = year +"-"+ month +"-"+day;
					
					if(hhmm=="(00:00)" && hhmm==hhmm2){
						hhmm ="";
						hhmm2 ="";
					}
					if(eventVo.ai_cnt > 0 ){
   						html+='<td><i class="fa fa-paperclip  fa-lg"></i></td>';
   					}else{
   						html+='<td><i  style="color:#EAEAEA;" class="fa fa-times"></i></td>';
   					}
					
					if(eventVo.me_allday_at == 'Y'){
						//alert("1:" + tmp_period);
						html+='<td>' + tmp_period1 + '</td>';
					}else{
						//html+='<td>'+eventVo.schdul_bgnde.substring(1,8)+" ~ "+eventVo.schdul_endde.substring(1,8)+'</td>';
						//alert("2:" + tmp_period);
						//일정
						tmp_period1 = year +"-"+ month +"-"+day +" " + hhmm ;
						year = eventVo.schdul_endde.substring(2, 4);	
						month = eventVo.schdul_endde.substring(4, 6);
						day = eventVo.schdul_endde.substring(6, 8);
						//hhmm = "(" + eventVo.schdul_endde.substring(8, 10) +":"+eventVo.schdul_endde.substring(10, 12) + ")" ;
						tmp_period2 = year +"-"+ month +"-" + day +" " + hhmm2;
						
						if(tmp_period1==tmp_period2){
							tmp_period = tmp_period1;
						}else{
							tmp_period = tmp_period1 + "  ~  " +  tmp_period2;
						}
						
						html+='<td>'+ tmp_period+'</td>';
						
					}
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="4">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#tbody_event_10500").html(html);
			
			$("#tbody_event_10500").find(".event-view").click(function(){
				viewPopupInit_10500();
				fnViewEvent_10500($(this).data("me_innb"));
				
			});
		}
	});
}

function fnGetNoticeList_10500(currPg){
	noticePg = currPg;	// 등록후 현 페이지 리로드
	
	var jijumOmCode = "";	
	var auth_code = "";	
	var team_val  = $("#noticeTmCode10500").val();   //팀코드값
	/*
	if(auth_flag !="1")		//관리자		
		jijumOmCode = login_bhf_cd;
		
	if(auth_flag =="2") {			//팀장
		auth_code = "'0000000008','0000000007','0000000006'";	
	}else if(auth_flag =="4") {	//순회
		auth_code = "'0000000007'" ;	
	}else if(auth_flag =="3") {	//고정
		auth_code = "'0000000006'" ;	
	}
	alert(auth_flag);
	var params = {
			"auth_flag"        : auth_flag,
			"cm_code"          : login_cp_cd,
			"em_no"            : "",
			"jijumOmCode"      : jijumOmCode,
			"jijumWord"        : "",
			"key" 		       : "",
			"word" 		       : "",
			"search_date_from" : "",
			"search_date_to"   : "",
			"auth_code"       : auth_code
		}; 
	*/
	
	var params = {
			"auth_flag"        : auth_flag,
			"cm_code"          : login_cp_cd,
			"em_no"            : login_no,
			"jijumOmCode"      : jijumOmCode,
			"jijumWord"        : "",
			"key" 		       : "",
			"word" 		       : "",
			"search_date_from" : "",
			"search_date_to"   : "",
			"team_val"         : team_val
		}; 
	
	var rowSize = 5;				// 표시할 로우수
	var fnName = "fnGetNoticeList_10500";//페이징처리 function명
	$.ajax({
		url : "/notice/list",
		type : "POST",
		dataType : "json",
		data:{"fnName":fnName
			, "params":params
			, "rowSize":rowSize
			, "currPg":currPg
			, "dash_flag" : "YY"
		},
		cache : false,
		success : function(data) {
			var html = '';
			if (data.noticeVoList.length > 0) {
				for (var i = 0; i < data.noticeVoList.length; i++) {
					var noticeVo = data.noticeVoList[i];
					html+='<tr>';
					/*html+='<td>' + (parseInt(data.firstNo)-i) + '</td>';*/
					html+='<td>' + (i+1) + '</td>';
					html+='<td class="txt_left">';
					html+='	<a class="notice-view" data-bm_innb="'+noticeVo.bm_innb+'" data-dty_code="'+noticeVo.em_dty_code +'" href="#">'+noticeVo.bm_sj+'</a>';
					html+='</td>';
					
					if(noticeVo.ai_cnt > 0 ){
   						html+='<td><i class="fa fa-paperclip  fa-lg"></i></td>';
   					}else{
   						html+='<td><i  style="color:#EAEAEA;" class="fa fa-times"></i></td>';
   					}
					
					
					html+='<td>'+noticeVo.regist_man+'</td>';
					html+='<td>'+noticeVo.regist_de+'</td>';
					html+='</tr>';
				}
			} else {
				html += '<tr><td colspan="5">조회된 데이터가 없습니다.</td></tr>';
			}
			$("#tbody_noti_10500").html(html);
			$("#tbody_noti_10500").find(".notice-view").click(function(){
				fnSetNoticePopClean_10500();
				fnViewNotice_10500($(this).data("bm_innb"), $(this).attr("data-dty_code"));
			});
			
			/*$("#noti_10500_Navi").html(data.navi);*/
		}
	});
}

function fnSetNoticePopClean_10500(){
	$("#noticeFileItemList_10500").html("");
	$("#noticeFileViewList_10500").html("");
	
	$("#selectSmItem_10500").html("");
	$("#selectOmItem_10500").html("");
	
	$("#selectSmViewItem_10501").html("");
	$("#selectOmViewItem_10501").html("");
	
	$("#noticeBmSj_10500").html("");
	$("#noticeBmCn_10500").html("");
	
	$("#noticeViewBmSj_10500").html("");
	$("#noticeViewBmCn_10500").html("");
}

//상세보기
function fnViewNotice_10500(bmInnb, dty_code){
	/*var noticeAuth = dty_code;*/
	 
	$.ajax({
		url : "/notice/view",
		data:{"bmInnb" : bmInnb, "authCode" : auth_flag},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) { 
			var noticeVo = data.noticeVo;
			$("#noticeViewBmSj_10500").text(noticeVo.bm_sj);
			$("#noticeViewBmCn_10500").text(noticeVo.bm_cn);
			/*$("#noticeMod_10500").val(noticeVo.bm_innb);*/
			$("#fixRoundViewItem_10501").attr("disabled", "disabled");   
			
			$("#selectOmViewItem_10501").html("");
			for (var i = 0; i < data.noticeOmList.length; i++) {
				var noticeOm = data.noticeOmList[i];
				var span = $('<span>'+noticeOm.value+'</span>');
				$("#selectOmViewItem_10501").append(span);
			}
		 
			/*$("#selectSmViewItem_10501").html("");
			for (var i = 0; i < data.noticeSmList.length; i++) {
				var noticeSm = data.noticeSmList[i];
				var span = $('<span>'+noticeSm.value+'</span>');
				$("#selectSmViewItem_10501").append(span);
			}*/
			
			$("#selectTmViewItem_10501").html("");
			for (var i = 0; i < data.noticeTmList.length; i++) {
				var noticeTm = data.noticeTmList[i];
				var span = $('<span>'+noticeTm.value+'</span>');
				$("#selectTmViewItem_10501").append(span);
			}
			$("#fixRoundViewItem_10501").html("");
			for (var i = 0; i < data.fixRoundList.length; i++) {
				var fixRound = data.fixRoundList[i];
				var span = $('<option>'+fixRound.value+'</option>');
				$("#fixRoundViewItem_10501").append(span);
			}
			
			$("#selectEmViewItem_10501").html("");
			for (var i = 0; i < data.noticeEmList.length; i++) {
				var noticeEm = data.noticeEmList[i];
				var span = $('<span>'+noticeEm.value+'</span>');
 				$("#selectEmViewItem_10501").append(span);
			}
			
			$("#noticeFileViewList_10500").html("");
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
				$("#noticeFileViewList_10500").append(html);
			}
			$(".noti-file-down").click(function(){
				location.href = "/file/down?ai_no="+$(this).data("ai_no");
			});
			$("#noticeViewPop10500").show();
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
function fnMakeEventComponent_10500(){
	
	
	$("#btnJuso").click(function(){
		var address = "/10/10-600";
		   location.href = address;
	});
	
	/*//바로가기
	$("#goNoti_10500").click(function(){
		 var address = "/70/70-100";
		 location.href = address;
	});
	
	//바로가기
	$("#goEvent_10500").click(function(){
		 var address = "/70/70-200";
		 location.href = address;
	});
	*/
	
	
	
	//조회버튼
	$("#btnSearch_10500").click(function(){
		fnReload_10500();
	});
	
	//팝업 닫기버튼
	$("#noticeViewPopClose10500, #noticeViewPopCloseX10500").click(function(){
		$("#noticeViewPop10500").hide();
	});
	
	$("#tempViewClose10500, #tempViewCloseX10500").click(function(){
		$("#tempViewPop10500").hide();
	});
}

function fnGetToday10500(){

	var now = new Date();
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
    
    var week = new Array('일', '월', '화', '수', '목', '금', '토');
    var chan_val = year + '년  ' + mon + '월  ' + day + '일  ' + ' (' +  week[now.getDay()]  + ')';
    
    return chan_val;
}


