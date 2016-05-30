
$(document).ready(function(){
	sc_GetScheduler();		//기본 이벤트 등록
	sc_EventComponent();	//기본 이벤트 등록
});

function sc_EventComponent(){
	
//	sc_datepicker(); // 달력 컨포넌트 등록
	
	//상세보기 창닫기
	$("#reserveViewCloseBtn").click(function(){
		$('#rmViewForm').hide();
	});
	//스케줄 선택 창닫기
	$("#choisClose").click(function(){
		$('#schChoiceForm').hide();
	});
	
	$("#choiceReserveBtn").click(function(){
		alert("예약선택");
		$('#schChoiceForm').hide();
	});
	$("#choiceMemoBtn").click(function(){
		alert("매모선택");
		$('#schChoiceForm').hide();
	});
	
	//등록 수정창 닫기
//	$("#reserveCloseBtn").click(function(){
//		$("#rmSaveForm").hide();
//	});
//	
//	//저장하기
//	$("#reserveSaveBtn").click(function(){
//		fnSet_ReserveSave($(this).val());
//	});
//	
//	//예약시 기존펫 선택 
//	$("#selectPet").change(function(){
//		var html= "";
//		if($(this).val() != ""){
//			var str = $(this).children("option:selected").text();
//			var selVal = $(this).val();
//			var selType = str.substring(1, str.lastIndexOf("]"));
//			var selName = str.substring(str.lastIndexOf(" "));
//			
//			var oldPet = $(".old-pet-item");
//			for (var i = 0; i < oldPet.length; i++) {
//				oldPet.eq(i).val()
//				if(selVal == oldPet.eq(i).val()){
//					alert("이미등록한 펫입니다.");
//					return;
//				}
//			}
//			
//			html += '<div>';
//			html += '<input type="hidden"class="old-pet-item" value="'+selVal+'" >';
//			html += '<span class="type-name" >'+selType+'</span>';
//			html += '<span>'+selName+'</span>';
//			html += '<a href="#" class="pet-del-btn" alt="취소">X</a>';
//			html += '</div>';
//			$("#petAddDiv").append(html);
//			fnSet_PetDelEvent();
//		}
//			
//	});
//	
//	//예약시 신규펫 등록 버튼
//	$("#newPetAddBtn").click(function(){
//		var newPetType = $("#autoPetType" ).val();;
//		var newPetTypeSeq = $("#newPetTypeSeq" ).val();;
//		var newPetName = $("#newPetName").val();
//		
//		if(newPetType == ""){
//			alert("자산유형을 선택해주세요.");
//			$("#autoPetType").focus();
//			return;
//		}else if(newPetName =="" ){
//			alert("자산이름을 입력해주세요.");
//			$("#newPetName" ).focus();
//			return;
//		} 		
//		var html= ""; 
//		
//		html += '<div>';
//		html += '<input type="hidden"class="new-pet-item" title="'+newPetName+'" value="'+newPetTypeSeq+'" >';
//		html += '<span class="type-name" >'+newPetType+'</span><span>'+newPetName+'</span>';
//		html += '<a href="#" class="pet-del-btn" alt="취소">X</a>';
//		html += '</div>';
//		$("#petAddDiv").append(html);
//		fnSet_PetDelEvent();
//		
//	});
//	
//	//신규 기존 고객 선택시 화면 토글
//	$("input[name='checkAdd']").change(function(){
//		if($(this).val() == "N"){
//			$("#newTr").show();
//			$("#oldTr").hide();
//			$("#selectPetDiv").hide();
//			$("#autoCustomer").attr("disabled",true);
//		}else{
//			$("#newTr").hide();
//			$("#oldTr").show();
//			$("#selectPetDiv").show();
//			$("#autoCustomer").attr("disabled",false);
//			
//		}
//	});
//	
//	// 호텔 미용 토글시 종료일자 숨기기
//	$("input[name='rType']").change(function(){
//		if($(this).val() == "H"){
//			$("#hotelDateDiv").show();
//			$("#beautyDateDiv").hide();
//		}else{
//			$("#hotelDateDiv").hide();
//			$("#beautyDateDiv").show();
//			
//		}
//	});
//	
//	make_AutoCustomer(); // 자동완성 컨포넌트 등록
	
}

function sc_GetScheduler(){
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		defaultDate: getLocalDate(0),
		lang: 'ko',
// 			buttonIcons: false, // show the prev/next text
		weekNumbers: false,
		selectable: true,
		selectHelper: true,
		select: function(start, end) {
			$("#schChoiceForm").show();
// 				var title = prompt('Event Title:');
// 				var eventData;
// 				if (title) {
// 					eventData = {
// 						title: title,
// 						start: start,
// 						end: end
// 					};
// 					alert(start);
// 					alert(end);
// 					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
// 				}
// 				$('#calendar').fullCalendar('unselect');
		},
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		events:"/admin/schedulerJson",
		timeFormat:'(H:mm)'
	});

}

//보유펫 검색
function fnGet_OldCustomPetCombo(c_seq){
	$.ajax({
		url : "/admin/reserveOldCustomPetCombo",
		type : "POST",
		data :{"c_seq":c_seq},
		dataType : "json",
		cache : false ,
		success : function(data) {
			var phtml ="<option value='' >선택</option>";
			for (var i = 0; i < data.result.length; i++) {
				phtml +="<option value='"+data.result[i].p_seq+"' >["+data.result[i].t_name+"] "+data.result[i].p_name+"</option>";
			}
			$('#selectPet').html(phtml);
			
		}
	});
}


// 예약 상세보기
function sc_ReserveRow(r_seq){
	$.ajax({
		url : "/admin/reserveRow",
	    type : "POST",
	    data :{"r_seq":r_seq},
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	var r_status_name ="<span class='status-y' >예약</span>"; 
	    	if(data.row.r_status=="Y"){
	    		$("#reserveCancleBtn").show();
	    		$("#reserveOkBtn").hide();
    		}else{
    			r_status_name ="<span class='status-n' >취소</span>";
	    		$("#reserveCancleBtn").hide();
	    		$("#reserveOkBtn").show();
	    	}
	    	$("#reserveCancleBtn").val(data.row.r_seq);
	    	$("#reserveOkBtn").val(data.row.r_seq);
	    	$('#rmViewForm').show();
	    	
	    	var r_type_name ="미용"; 
    		if(data.row.r_type =="H"){
    			r_type_name ="호텔";
    		}
    		
    		$("#reserveRStatusText").html(r_status_name);
    		$("#reserveRtypeText").html(r_type_name);
	    	$("#inputCNameViewText").html(data.row.c_name);
	    	$("#inputPhoneViewText").html(data.row.phone);
	    	$("#inputAddrViewText").html(data.row.address_detail);
	    	$("#inputEmailViewText").html(data.row.email);
	    	
	    	$("#reserveDateViewText").html(data.row.sdate+(data.row.edate == ""? "":" ~ "+data.row.edate));
	    	
	    	$("#inputMemoViewText").html(data.row.memo);
	    	$("#inputBigoViewText").html(data.row.bigo);

	    	
	    	
	    	var phtml ="";
	    	for (var i = 0; i < data.pet.length; i++) {
	    		phtml+="<div><span class='type-name' >"+data.pet[i].t_name+"</span><span> "+data.pet[i].p_name+"</span></div>"; 
			}
	    	$("#petViewDiv").html(phtml);
	    	
	    }
	});
}

// 입력수정창 초기화
function inputClear(){
	
	$("#reserveSaveBtn").val("");
	$("#typeHotel").prop("checked", false);
	$("#typeBeauty").prop("checked", false);
	$("#inputCNameText").html(""); 
	$("#inputPhoneText").html("");
	$("#inputAddrText").html("");
	$("#inputEmailText").html("");
	$("#inputCName").val("");
	$("#inputPhone").val("");
	$("#selectPet").html("<option value='' >없음</option>"); 
	$("#newPetTypeSeq").val("");
	$("#autoPetType").val("");
	$("#newPetName").val("");
	$("#petAddDiv").html("");
	$("#reserveBeautySdate").val("");
	$("#reserveHotelEdate").val("");
	$("#reserveHotelSdate").val("");
	$("#inputMemo").val("");
	$("#inputBigo").val("");
	
}



function sc_datepicker(){
	
	//검색 시작일
	$( "#searchSdate" ).dateType();
//	//검색 종료일
	$( "#searchEdate" ).dateType();
	// 미용 예약일
	$( "#reserveBeautySdate" ).dateTimeType();
	//예약 종료일
	$( "#reserveHotelEdate" ).dateType();
	//예약 시작일
	$( "#reserveHotelSdate" ).dateType();
}


function make_AutoCustomer(){
	
	$("#autoCustomer").keydown(function(){
		
		if(!event.keyCode != 8 && event.keyCode != 13 ){
			$("#inputCSeq").val("");
			$("#inputCName").val("");
			$("#inputPhone").val("");
		}
	});
	$("#autoCustomer").autocomplete({
		serviceUrl:'/admin/autoCustom',
		minChars:1,
		paramName: 'searchText',
		params :{"fnName":"1"},
		zIndex: 9999,
		noCache: true, 
		onSelect: function(suggestion){
			$("#inputCSeq").val(suggestion.c_seq);
			$("#inputCSeqText").text(suggestion.c_seq);
			$("#inputCNameText").text(suggestion.c_name);
			$("#inputPhoneText").text(suggestion.phone);
			$("#inputAddrText").text(suggestion.addr);
			$("#inputEmailText").text((suggestion.email =="@"? "":suggestion.email));
			$("#autoCustomer").val("");
			fnGet_OldCustomPetCombo(suggestion.c_seq);
		}
	});
	$("#autoPetType").autocomplete({
		serviceUrl:'/admin/autoPetType',
		minChars:1,
		paramName: 'searchText',
		params :{"fnName":"1"},
		zIndex: 9999,
		noCache: true, 
		onSelect: function(suggestion){
			$("#newPetTypeSeq").val(suggestion.t_type);
		}
	});
}




// 펫 추가부분 삭제
function fnSet_PetDelEvent(){
	$(".pet-del-btn").click(function(){
		$(this).parent().remove();
	});
}


// 예약 저장
function fnSet_ReserveSave(r_seq){
	var r_type = $("input[name='rType']:checked").val();
	var checkAdd = $("input[name='checkAdd']:checked").val();
	var c_seq = $("#inputCSeq").val(); 
	var c_name = $("#inputCName").val().trimAll(); 
	var phone = $("#inputPhone").val().trimAll();
	
	
	var sdate = $("#reserveHotelSdate").val();
	var edate = $("#reserveHotelEdate").val();
	if(r_type == "B"){
		sdate = $("#reserveBeautySdate").val();
		edate = "";
	}
	
	
	var memo = $("#inputMemo").val().trimFL();
	var bigo = $("#inputBigo").val();
//	
	var newPet = $(".new-pet-item");
	var oldPet = $(".old-pet-item");
	

	var newPetArray =  new Array();
	for (var i = 0; i < newPet.length; i++) {
		newPetArray.push({
			t_type : newPet.eq(i).val(),
			p_name : newPet.eq(i).attr("title")
		});
	}
	var oldPetArray =  new Array();
	for (var i = 0; i < oldPet.length; i++) {
		oldPetArray.push({
			p_seq : oldPet.eq(i).val()
		});
	}
	
	if(r_type === undefined){
		alert("예약 구분을 선택해주세요");
		$("#typeHotel").focus();
		return;
	}else if(c_name == "" && checkAdd == "N" ){
		alert("고객이름을 입력해주세요.");
		$("#inputCName").focus();
		return;
	}else if(phone == "" &&  checkAdd == "N" ){
		alert("전화번호를 입력해주세요");
		$("#inputPhone").focus();
		return;
	}else if(c_seq == "" && checkAdd == "O"){
		alert("고객을 검색 해주세요.");
		$("#autoCustomer").focus();
		return;
	}else if(sdate == "" && r_type == "B" ){
		alert("시작일자를 입력해주세요");
		$("#reserveBeautySdate").focus();
		return;
	}else if(sdate == ""  && r_type == "H"){
		alert("시작일자를 입력해주세요");
		$("#reserveHotelSdate").focus();
		return;
	}else if(edate == "" && r_type == "H" ){
		alert("종료일자를 입력해주세요");
		$("#reserveHotelEdate").focus();
		return;
	}else if(memo == "" ){
		alert("메모를 입력해주세요");
		$("#inputMemo").focus();
		return;
	}
	
	var url = "/admin/reserveUpdate";
	if(r_seq == ""){
		url = "/admin/reserveSave";
	}
	$.ajax({
		url : url,
	    type : "POST",
	    data :{ "r_seq":r_seq,
	    		"r_type":r_type,
	    		"checkAdd":checkAdd,
	    	 	"c_seq":c_seq,
	    	 	"c_name":c_name,
	    		"phone":phone,
	    		"sdate":sdate,
	    		"edate":edate,
	    		"memo":memo,
	    		"bigo":bigo,
	    		"newPetArray":newPetArray,
	    		"oldPetArray":oldPetArray
	    },
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	if(data.result > 0 ){
	    		alert("정상적으로 등록되었습니다.");
	    		$('#rmSaveForm').hide();
	    	}
	    }
	});
}