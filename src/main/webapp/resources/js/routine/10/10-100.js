//var maxRndPlanListCnt10100 = 8;
//순방계획
var currBaseDate10100;
var currSysDate10100;
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	if(auth_flag != 4){
		$("#modActivityRndPlan10100").remove();
	}else{
		$("#modActivityRndPlan10100").show();
	}
	//검색일
	$("#searchDate10100").winCal(baseOptions);
	$("#selectStrDate10100").winCal({todayBtn:false});
	
	//DB SYSDATE
	var sysdate = getSysDate(2);
	currSysDate10100 = sysdate;
	
	//오늘날짜의 기준일자 설정
	currBaseDate10100 = sysdate.setBaseDate("first");
	
	//오늘날짜로 조회
	fnGetActivityRndPlanList10100(currBaseDate10100);
	
	//검색조건에 기준일자 등록
	$("#selectRndPlanBaseDate").val(currSysDate10100);
	//검색일 설정
	$("#searchDate10100").val(sysdate);
//	//검색일의 baseDate 설정
//	$("#thisBaseDate10100").val(currSysDate10100);
	fnGetRndPlanSearchComboBox("",1); // 소속가져오기
	// 이벤트 등록
	fnSetEventComponent();
});


// 해더 생성
function fnGetRndPlanHead(baseDate){
	
	var html = "<tr><th>일정</th>";
	
	var weeks = ["일","월","화","수","목","금","토"]; //목요일
	var hideMode = "hide";
	if($("#selectStrList10100").is(":visible")){
		hideMode ="";
	}
	for (var i = 0; i < 7 ; i++) {
		var now = new Date(baseDate); 
		now.setDate(now.getDate() + i);
		var nowWeek = now.getDay();
		var year= now.getFullYear();
		var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
		var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
		var planDe= year+"-"+mon+"-"+day;
		html += "<th>"+planDe+"("+weeks[nowWeek]+") ";
		html += "<button type='button' class='plan-mod-group "+hideMode+" plan-mod-btn' data-plan-de='"+planDe+"' >수정</button>";
		html += "</th>";
	}
	html += "</tr>";
	$("#activityRndPlanTHead10100").html(html);
	
}



/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	
	
	$("#searchTeam10100").change(function(){
		var om_code = $("#searchBhf10100").val();
		fnGetRndPlanEmComboBox(om_code,$(this).val());
	});
	$("#selectStrDate10100").change(function(){
//		var smArr = $("#rndPlanStrTb10100").find("input[type='checkbox']:checked'");
//		if(smArr.length == 0 ){
//			alert("등록된 매장이 없습니다.");
//			return;
//		}
		var planDate = $(this).val();			 // 선택한 버튼 위치
		var planBtn = $(".plan-mod-btn[data-plan-de='"+planDate+"']");
		
		var idx = planBtn.parent().index();			 // 선택한 버튼 위치
		var strArr = $("#activityRndPlanTBody10100 .row-group"+idx);	//수정창의 모든 매장 
		$("#rndPlanStrTb10100 input[type='checkbox']").prop("checked",false);	// 체크 해제
		
		// 있는것만 다시 체크
		for (var i = 0; i < strArr.length-1; i++) {
			var rowId = strArr.eq(i).attr("data-sm-code");
			if(rowId != "bigo"){
				$("#strCheck"+rowId).prop("checked",true);
			}
		}
		
	});
	$("#searchEmp10100").change(function(){
		var baseDate = $("#searchDate10100").val().setBaseDate("first");	
		fnGetActivityRndPlanList10100(baseDate);
		if($("#selectStrList10100").is(":visible")){
			fnGerRndPlanStrList();
		}
	});
	
	//편집 & 편집취소
	$("#modActivityRndPlan10100").click(function(){
		
		if($("#searchEmp10100").val()  == ""){
			alert("사원을 선택해주세요");
			$(this).html("일정편집");
			$("#selectStrList10100").hide();
			$(".plan-mod-group").hide();
			return ;
		}
		
		var thisTb = $("#activityRndPlanTBody10100");
		$("#activityRndPlanTBody10100 textarea").toggleClass("view-mode");
		if($("#selectStrList10100").is(":visible")){
			//일반모드
			$(this).html("일정편집");
//			$("#activityRndPlanTBody10100 textarea").prop("readonly",true);
			$("#selectStrList10100").hide();
			$(".plan-mod-group").hide();
			thisTb.find(".mode-btn").show();
			
		}else{
			//수정모드
			$(this).html("편집취소");
//			$("#activityRndPlanTBody10100 textarea").prop("readonly",false);
			fnGerRndPlanStrList();
			$("#selectStrDate10100").val("");
			$(".plan-mod-group").show();
			thisTb.find(".mode-btn").hide();
		}
		thisTb.find(".mod-view-group").show();
		thisTb.find(".mod-area").hide();
		
	});
	//검색일
	$("#searchDate10100").change(function(){
		var baseDate = $(this).val().setBaseDate("first");
		fnGetActivityRndPlanList10100(baseDate);
//		currBaseDate10100 = baseDate;
	});
	//오늘 버튼
	$("#searchTodayActivityRndPlan10100").click(function(){
		var baseDate = currSysDate10100.setBaseDate("first");	
		fnGetActivityRndPlanList10100(baseDate);
		$("#searchDate10100").val(currSysDate10100);
		if($("#selectStrList10100").is(":visible")){
			fnGerRndPlanStrList();
		}
	});
	//이전일
	$("#searchPrevWeekActivityRndPlan10100").click(function(){
		var baseDate = $("#searchDate10100").val().setBaseDate("prev");	
		fnGetActivityRndPlanList10100(baseDate);
		$("#searchDate10100").val(baseDate);
		
		if($("#selectStrList10100").is(":visible")){
			fnGerRndPlanStrList();
		}
		
//		currBaseDate10100 = baseDate;
	});
	//다음일
	$("#searchNextWeekActivityRndPlan10100").click(function(){
		var baseDate = $("#searchDate10100").val().setBaseDate("next");	
		fnGetActivityRndPlanList10100(baseDate);
		$("#searchDate10100").val(baseDate);
		if($("#selectStrList10100").is(":visible")){
			fnGerRndPlanStrList();
		}
//		currBaseDate10100 = baseDate;
	});
	
	// 저장 버튼
	$("#rndPlanStrSaveBtn10100").click(function(){
		
		if($("#searchEmp10100").val()  == ""){
			alert("사원을 선택해주세요");
			$(this).html("일정편집");
			$("#selectStrList10100").hide();
			$(".plan-mod-group").hide();
			return ;
		}
		
		var planDe = $("#selectStrDate10100").val();	
		var baseDe = currBaseDate10100;	
		var smArr = new Array();
		$("#rndPlanStrTb10100").find("input[type='checkbox']:checked'").each(function() {
			var smCode = $(this).val();
			var smName = $(this).data("sm-nm");
			smArr.push({
				"prdi_sm_code" : smCode,
				"prdi_sm_code_nm" : smName,
				"base_de" : baseDe.dateReplace(),
				"plan_de" : planDe.dateReplace()
			});
		});
		if(smArr.length == 0 ){
			alert("등록된 매장이 없습니다.");
			return;
		}
		if(planDe.length == 0 ){
			alert("날짜를 선택해주세요.");
			$("#selectStrDate10100").focus();
			return;
		}
		var saveData = {
				base_de : baseDe.dateReplace(),	
				plan_de : planDe.dateReplace(),	
				smArr 	: smArr	
//				prd_partclr_matter : $("#strEtc10100").val()	
		}
		$.ajax({
			url : "/rndplan/save",
			type : "POST",
		    data : saveData,
			dataType : "json",
			async :false,
			cache : false,
			success : function(data) {
	    		if (data != 0) {
	    			$("#rndPlanStrTb10100 input[type='checkbox']").prop("checked",false);	// 체크 해제
	    			fnGetActivityRndPlanList10100(baseDe);
//	    			$("#strEtc10100").val("");
	    			$("#selectStrDate10100").val("");
	    			alert("저장되었습니다.");
//	    			$("#workStatus10200").data("update-flag","N")
	    		} else {
	    			alert("저장에 실패하였습니다.");
	    		}
		    }
		});
	});
}

// 내 담당 매장 모두 조회
function fnGerRndPlanStrList(){
	$.ajax({
		url : "/rndplan/strList",
	    data:{em_no : $("#searchEmp10100").val()},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var html = "";
			for (var i = 0; i < data.length; i++) {
				var rndPlanStrVo  = data[i];
				html +='<tr>';
				html +='	<td>';
				html +='		<div class="inputCheck">';
				html +='			<input type="checkbox" value="'+rndPlanStrVo.sm_code+'" data-sm-nm="'+rndPlanStrVo.sm_nm+'"  id="strCheck'+rndPlanStrVo.sm_code+'">';
				html +='			<label for="strCheck'+rndPlanStrVo.sm_code+'"></label>';
				html +='		</div>';
				html +='	</td>';
				html +='	<td><div class="txt_left">'+rndPlanStrVo.sm_nm+'</div></td>';
				html +='</tr>';
			}
//			html +='<tr class="mid-h" >';
//			html +='	<td colspan="2"><textarea id="strEtc10100" ></textarea></td>';
//			html +='</tr>';
			
			$("#rndPlanStrTb10100").html(html);
			$("#selectStrList10100").show();
			var startDate = currBaseDate10100;	
			var endDate = startDate.setBaseDate("last");
			$("#selectStrDate10100").datetimepicker('setStartDate',startDate);
			$("#selectStrDate10100").datetimepicker('setEndDate',endDate);
	    }
	});
}


// 해더 아이디 조회
//function fnGetRndPlanStrDayList(baseDe){
//	$.ajax({
//		url : "/rndplan/dayList",
//		data:{
//			emNo : $("#searchEmp10100").val(),
//			baseDe : baseDe.dateReplace()
//		},
//		type : "POST",
//		dataType : "json",
//		async: false,
//		cache : false ,
//		success : function(data) {
//			for (var i = 0; i < data.length; i++) {
//				var rndPlanDayVo  = data[i];
//				$("#activityRndPlanTHead10100").find(".plan-mod-btn[data-plan-de='"+rndPlanDayVo.plan_de.dateFormat()+"']").val(rndPlanDayVo.prd_innb);
//			}
//		}
//	});
//}



//활동관리 순방계획 조회
function fnGetActivityRndPlanList10100(baseDate){
	var data = {
		  base_de : baseDate.dateReplace()
		, em_no : $("#searchEmp10100").val()
	};
	$.ajax({
		url : "/rndplan/list",
		type : "POST",
	    data : data,
		dataType : "json",
		async :true,
		global:true,
		cache : false,
		success : function(data) {
//			$("#activityRndPlanTBody10100").html("");
//			$("#activityRndPlanTHead10100").html("");
			$("#selectStrDate10100").val("");
			var len = data.rndPlanVoList.length;
			fnGetRndPlanHead(baseDate);
			
			//해더 수정버튼 
			$("#activityRndPlanTHead10100").find(".plan-mod-btn").click(function(){
				
				if($("#searchEmp10100").val()  == ""){
					alert("사원을 선택해주세요");
					$(this).html("일정편집");
					$("#selectStrList10100").hide();
					$(".plan-mod-group").hide();
					return ;
				}
				
				var idx = $(this).parent().index();			 // 선택한 버튼 위치
				var planDate = $(this).data("plan-de");		 //	계획일
				$("#selectStrDate10100").val(planDate);		 // 수정창에 등록
				var strArr = $("#activityRndPlanTBody10100 .row-group"+idx);	//수정창의 모든 매장 
				$("#rndPlanStrTb10100 input[type='checkbox']").prop("checked",false);	// 체크 해제
				// 있는것만 다시 체크
				for (var i = 0; i < strArr.length-1; i++) {
					var rowId = strArr.eq(i).attr("data-sm-code");
					if(rowId != "bigo"){
						$("#strCheck"+rowId).prop("checked",true);
//					}else{
//						$("#strEtc10100").val(strArr.eq(i).text()); //비고 등록						
					}
				}
			});
			var rowArr = ["1","2","3","4","5","6","7","8","9","10","비고","일정"];
			var dayArr = ["thw","fri","sat","sun","mon","tue","wed",];
			var html = "";
			var cnt = 0;
			for (var i = 0; i < rowArr.length; i++) {
				if(cnt < len ){
					var rndPlanVo = data.rndPlanVoList[cnt];
					var rn = rndPlanVo.rn;
					var classNm = rn > 10  ? "class='mid-h'": "";
					var updatMode = (rn ==11 ? true: false);
					if(rn == (i+1)){
						cnt++;
						if(updatMode){
							html +="<tr "+classNm+" >";
							html +="<td>"+rowArr[i]+"</td>";
							for (var f = 0; f < dayArr.length; f++) {
								html +="<td data-sm-code='"+rndPlanVo[dayArr[f]+"_cd"]+"' class='row-group1 enpre' >";
								html +="<span class='mod-view-group' >"+rndPlanVo[dayArr[f]+"_nm"]+"</span> <i class='fa fa-pencil mode-btn'></i>";
								html +="<div class='mod-area'>";
								html +="	<textarea>"+rndPlanVo[dayArr[f]+"_nm"]+"</textarea>";
								html +="	<div class='btn-area'>";
								html +="		<button  class='red save-btn' type='button' value='"+rndPlanVo[dayArr[f]+"_cd"]+"' >저장</button ><button  class='gay cancle-btn' type='button'>취소</button>";
								html +="	</div>";
								html +="</div>";
								html +="</td>";
							}
							html +="</tr>";
						}else{
							html +="<tr "+classNm+" >";
							html +="<td>"+rowArr[i]+"</td>";
							html +="<td data-sm-code='"+rndPlanVo.thw_cd+"' class='row-group1 enpre' >"+rndPlanVo.thw_nm+"</td>";
							html +="<td data-sm-code='"+rndPlanVo.fri_cd+"' class='row-group2 enpre' >"+rndPlanVo.fri_nm+"</td>";
							html +="<td data-sm-code='"+rndPlanVo.sat_cd+"' class='row-group3 enpre' >"+rndPlanVo.sat_nm+"</td>";
							html +="<td data-sm-code='"+rndPlanVo.sun_cd+"' class='row-group4 enpre' >"+rndPlanVo.sun_nm+"</td>";
							html +="<td data-sm-code='"+rndPlanVo.mon_cd+"' class='row-group5 enpre' >"+rndPlanVo.mon_nm+"</td>";
							html +="<td data-sm-code='"+rndPlanVo.tue_cd+"' class='row-group6 enpre' >"+rndPlanVo.tue_nm+"</td>";
							html +="<td data-sm-code='"+rndPlanVo.wed_cd+"' class='row-group7 enpre' >"+rndPlanVo.wed_nm+"</td>";
							html +="</tr>";
						}
					}else {
						html +="<tr>";
						html +="<td>"+rowArr[i]+"</td>";
						html +="<td data-sm-code='' class='row-group1' ></td>";
						html +="<td data-sm-code='' class='row-group2' ></td>";
						html +="<td data-sm-code='' class='row-group3' ></td>";
						html +="<td data-sm-code='' class='row-group4' ></td>";
						html +="<td data-sm-code='' class='row-group5' ></td>";
						html +="<td data-sm-code='' class='row-group6' ></td>";
						html +="<td data-sm-code='' class='row-group7' ></td>";
						html +="</tr>";
					}
				}else {
					var classNm ="";
					if(i > 9){
						classNm = "class='mid-h'";
					}
					var envFlag="";
					if(i > 10 ){
						envFlag = "evn";
						classNm = "class='sche-tr mid-h'";
					}
					html +="<tr "+classNm+" >";
					html +="<td >"+rowArr[i]+"</td>";
					html +="<td data-sm-code='"+envFlag+"' class='row-group1' ></td>";
					html +="<td data-sm-code='"+envFlag+"' class='row-group2' ></td>";
					html +="<td data-sm-code='"+envFlag+"' class='row-group3' ></td>";
					html +="<td data-sm-code='"+envFlag+"' class='row-group4' ></td>";
					html +="<td data-sm-code='"+envFlag+"' class='row-group5' ></td>";
					html +="<td data-sm-code='"+envFlag+"' class='row-group6' ></td>";
					html +="<td data-sm-code='"+envFlag+"' class='row-group7' ></td>";
					html +="</tr>";
				}
			}
			$("#activityRndPlanTBody10100").html(html);
			fnGetActivityRndPlanScheList10100(baseDate);
			$("#activityRndPlanTBody10100 .mode-btn").click(function(){
				$(this).next().show();
				$(this).prev().hide();
				$(this).hide();
			});
			$("#activityRndPlanTBody10100 .btn-area .cancle-btn").click(function(){
				$(this).parent().parent().hide();
				$(this).parent().parent().prevAll().show();
				
			});
			$("#activityRndPlanTBody10100 .btn-area .save-btn").click(function(){
//				alert("111");
				var el = $(this);
				var thisTd = el.parent().parent().parent();
				var matter = thisTd.find("textarea").val();
				var baseDe = currBaseDate10100;	
				var idx = thisTd.index();	
				var planDe = $("#activityRndPlanTHead10100 > tr > th").eq(idx).find(".plan-mod-btn").data("plan-de");
				var saveData = {
						base_de : baseDe.dateReplace(),	
						plan_de : planDe.dateReplace(),
						em_no : $("#searchEmp10100").val(),
						prd_partclr_matter : matter	
				}
				$.ajax({
					url : "/rndplan/mattersave",
					type : "POST",
				    data : saveData,
					dataType : "json",
//					async :false,
					global:true,
					cache : false,
					success : function(data) {
			    		if (data != 0) {
			    			alert("저장되었습니다.");
			    			thisTd.find("span").html(matter);
			    			el.parent().parent().hide();
			    			el.parent().parent().prevAll().show();
			    		} else {
			    			alert("저장에 실패하였습니다.");
			    		}
				    }
				});
			});
			if($("#selectStrList10100").is(":visible")){
				var startDate = baseDate;	
				var endDate = startDate.setBaseDate("last");	
				$("#selectStrDate10100").datetimepicker('setStartDate',startDate);
				$("#selectStrDate10100").datetimepicker('setEndDate',endDate);
			}
			currBaseDate10100 = baseDate;
		}
	});
	
}


function fnGetActivityRndPlanScheList10100(baseDate){
	var data = {
		  base_de : baseDate.dateReplace()
		, em_no : $("#searchEmp10100").val()
	};
	$.ajax({
		url : "/rndplan/schelist",
		type : "POST",
	    data : data,
		dataType : "json",
		async :false,
		cache : false,
		success : function(data) {
			
			var len = data.rndPlanScheVoList.length;
			var html = "<tr>";
			html += "<td>일정</td>";
			var baseDe = baseDate.dateReplace();
			for (var i = 0; i < len; i++) {
				var schVo = data.rndPlanScheVoList[i];
//				console.log("baseDe : " + baseDe);
				var startDe = schVo.bgnde;
//				console.log("startDe : " + startDe);
				startDe = startDe < baseDe ? baseDe: startDe;
				var endDe = schVo.endde;
//				console.log("endDe : " + endDe);
				var lastDate  = baseDate.setBaseDate("last").dateReplace();
				
//				console.log(baseDate+" ~ " + lastDate.dateFormat());
				
				endDe = endDe > lastDate ? lastDate: endDe;
				var adCnt  = (endDe - startDe.dateReplace())+1; // 회전수
				var nextDe = startDe.dateReplace().dateFormat();
				for (var f = 0; f < adCnt; f++) {
//					console.log("nextDe"+f+" : " + nextDe);
					
					var planBtn = $(".plan-mod-btn[data-plan-de='"+nextDe+"']");
					var idx = planBtn.parent().index();			 // 선택한 버튼 위치
					var schData = $("<div>"+schVo.me_sj+"</div>");
					schData.css({
						"width": "100%"
					    ,"padding": "5px"
					    ,"background": schVo.me_sj_color
					    ,"margin-bottom": "5px"
					    ,"color": "#fff"
						});
					$("#activityRndPlanTBody10100 > tr:last td").eq(idx).append(schData);
					nextDe = nextDe.setNextDate();
					
					
					
				}
				
			}
//			for (var i = 0; i < len; i++) {
//				var schVo = data.rndPlanScheVoList[i];
//				var startDe = schVo.bgnde;
//				startDe = startDe < baseDe ? baseDe: startDe;
//				var endDe = schVo.endde;
//				var adCnt  = (endDe - startDe)+1; // 회전수
//				var maxCnt = (7-(startDe-baseDe))+1;
//				console.log("startDe : " + startDe);
//				console.log("endDe : " + endDe);
//				console.log("adCnt : " + adCnt);
//				console.log("maxCnt : " + maxCnt);
//				var minusCnt = adCnt- maxCnt ;
//				minusCnt = minusCnt < 0 ? 0 : minusCnt;
//				var totalForLen = adCnt - (adCnt- maxCnt ); // 최대 회전수
//				console.log("totalForLen : " + totalForLen);
//				var nextDe = startDe;
//				for (var f = 0; f < totalForLen; f++) {
//					console.log("nextDe"+f+" : " + nextDe);
//					nextDe = nextDe.setNextDate();
//				}
//				html += "<td>일정</td>";
//				
//			}
		}
	});
}

//검색 콤보 박스
function fnGetRndPlanSearchComboBox(om_code ,om_orgnzt_se){
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
				$("#searchBhf10100").html(listHtml);
				$("#searchBhf10100").change(function(){
					fnGetRndPlanSearchComboBox($(this).val(), 2);
					fnGetRndPlanEmComboBox($(this).val(),"");
				});
				if(parseInt(data.auth_flag) > 1){
					fnGetRndPlanSearchComboBox($("#searchBhf10100").val() ,2);
					fnGetRndPlanEmComboBox($("#searchBhf10100").val() ,"");
				}
			}else if(om_orgnzt_se==2){
				$("#searchTeam10100").html(listHtml);
			}
	    }
	});
}
//검색 콤보 박스
function fnGetRndPlanEmComboBox(om_code,team_code){
	$.ajax({
		url : "/employee/empListByTeamCode",
		data:{"om_code":om_code,"team_code":team_code},
		type : "POST",
		dataType : "json",
		async: false,
		cache : false ,
		success : function(data) {
			var listHtml = "<option value='' >사원명</option>";
			if(auth_flag  >3 ){
				listHtml ="";
			}
			if( data.result.length > 0){
				for(var i=0; i<data.result.length; i++){
					var res = data.result[i];     
					listHtml += "<option value ='"+res.em_no+"'>"+ res.em_nm +"</option>";
				}
			}	
			$("#searchEmp10100").html(listHtml);
			
		}
	});
}




