var mouse_x =0;
var mouse_y =0;
var myTypeItem="";

var timeOption = ["오전 07:00","오전 07:30", "오전 08:00","오전 08:30",
				  "오전 09:00","오전 09:30", "오전 10:00","오전 10:30",
				  "오전 11:00","오전 11:30", "오후 12:00","오후 12:30",
				  "오후 01:00","오후 01:30", "오후 02:00","오후 02:30",
				  "오후 03:00","오후 03:30", "오후 04:00","오후 04:30",
				  "오후 05:00","오후 05:30", "오후 06:00","오후 06:30",
				  "오후 07:00","오후 07:30", "오후 08:00","오후 08:30",
				  "오후 09:00","오후 09:30", "오후 10:00","오후 10:30",
				  "오후 11:00","오후 11:30", "오전 12:00","오전 12:30",
				  "오전 01:00","오전 01:30", "오전 02:00","오전 02:30",
				  "오전 03:00","오전 03:30", "오전 04:00","오전 04:30",
				  "오전 05:00","오전 05:30", "오전 06:00","오전 06:30"
                  ];
var timeOptionVal = [ "07:00","07:30", "08:00","08:30",
					  "09:00","09:30", "10:00","10:30",
					  "11:00","11:30", "12:00","12:30",
					  "13:00","13:30", "14:00","14:30",
					  "15:00","15:30", "16:00","16:30",
					  "17:00","17:30", "18:00","18:30",
					  "19:00","19:30", "20:00","20:30",
					  "21:00","21:30", "22:00","22:30",
					  "23:00","23:30", "00:00","00:30",
					  "01:00","01:30", "02:00","02:30",
					  "03:00","03:30", "04:00","04:30",
					  "05:00","05:30", "06:00","06:30"
	                  ];

$(document).ready(function(){
    $(document).mousemove(function(e){
    	mouse_x = e.pageX; 
    	mouse_y = e.pageY; 
  	});
	
	fn_GetScheduler();		//기본 스케줄
	
	/*
	$(".form_date").datetimepicker({
		language:  'ko',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
    */
});
function fn_GetColorChooser(){
	var colorData = ["#A39AFF","#5484ed","#a4bdfc","#46d6db","#7ae7bf","#51b749","#fbd75b","#ffb878","#ff887c","#dc2127","#dbadff","#e1e1e1"];
	var colorDiv = $("<div id='colorDiv' />");
//	alert(colorData.length );
	for (var i = 0 ;  i  < colorData.length ; i++){
		var colorItem = $("<div class='col-item' id='"+colorData[i]+"' style='background-color:"+colorData[i]+"' />");
		colorItem.click(function(){
//			alert($(this).attr("id"));
			$(".col-item").removeClass("color-selected");
			$(this).addClass("color-selected");
		});
		colorDiv.append(colorItem);
	}
	return colorDiv;
}

function fn_GetScheduler(){
	
	var calendarArray = [
	         			{
	        				title: 'All Day Event',
	        				start: '2014-09-01'
	        			},
	        			{
	        				title: 'Long Event',
	        				start: '2014-09-07',
	        				end: '2014-09-10'
	        			},
	        			{
	        				id: 999,
	        				title: 'Repeating Event',
	        				start: '2014-09-09T16:00:00'
	        			},
	        			{
	        				id: 999,
	        				title: 'Repeating Event',
	        				start: '2014-09-16T16:00:00'
	        			},
	        			{
	        				title: 'Conference',
	        				start: '2014-09-11',
	        				end: '2014-09-13'
	        			},
	        			{
	        				title: 'Meeting',
	        				start: '2014-09-12T10:30:00',
	        				end: '2014-09-12T12:30:00'
	        			},
	        			{
	        				title: 'Lunch',
	        				start: '2014-09-12T12:00:00'
	        			},
	        			{
	        				title: 'Meeting',
	        				start: '2014-09-12T14:30:00'
	        			},
	        			{
	        				title: 'Happy Hour',
	        				start: '2014-09-12T17:30:00'
	        			},
	        			{
	        				title: 'Dinner',
	        				start: '2014-09-12T20:00:00'
	        			},
	        			{
	        				title: 'Birthday Party',
	        				start: '2014-09-13T07:00:00'
	        			},
	        			{
	        				title: 'Click for Google',
	        				url: 'http://google.com/',
	        				start: '2014-09-28'
	        			}
	        		];
	
	/*
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		defaultDate: "2015-09-08",
//		defaultDate: "2015-01-12",
		lang: 'ko',
// 			buttonIcons: false, // show the prev/next text
		//weekNumbers: false,
//		selectable: true,
//		selectHelper: true,
//		select: function(start, end) {
//			$("#spop").remove();
//			$("#schChoiceForm").show();
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
//		},
		eventClick: function(calEvent, jsEvent, view) {
			
			var start = calEvent.start.format();
	        var end = calEvent.end.format();

			$("#spop").remove();
			var div =$("<div id='spop' />");
	        var spopContent =$("<div class='arrow_box' ></div>");
	        
	        var h1 =$("<h1 />");
	        var span =$("<span />");
	        span.text(calEvent.title);
	        var closeBtn = $("<a href='#' />");
	        closeBtn.text("닫기0");
	        closeBtn.click(function(){
	        	$("#spop").remove();
	        });
	        var table = $("<table class='table_wrap spop-table' />");
	        var tr1 = $("<tr />");	        
	        var td1 = $("<td />");
	        td1.text(start+" ~ "+end);
	        
	        var tr2 = $("<tr />");
	        var td2 = $("<td />");
	        var sclrollDiv = $("<div />");
	        sclrollDiv.text("내용");

	        td2.append(sclrollDiv);
	        
	        var buttonDiv = $("<div class='ta_center spop-button' />");
	        
	        var delBtn = $("<button type='button' class='roundbtn' />");
	        var modiBtn = $("<button type='button' class='roundbtn green'>");
	        delBtn.text("삭제");
	        modiBtn.text("수정");
	        
	        
	        delBtn.click(function(){
	        	if(confirm("삭제하시겠습니까?")){
	        		
	        	}
	        });
	        modiBtn.click(function(){
	        	alert("수정");
	        });
	        
	        h1.append(span);
	        h1.append(closeBtn);
	        spopContent.append(h1);
	        
	        tr1.append(td1);
	        tr2.append(td2);
	        table.append(tr1);
	        table.append(tr2);
	        
	        buttonDiv.append(delBtn);
	        buttonDiv.append(modiBtn);
	        
	        spopContent.append(table);
	        spopContent.append(buttonDiv);
	        
	        div.append(spopContent);
	        div.css({"top":jsEvent.pageY-(242),"left":jsEvent.pageX-250});
	        $("body").append(div);

	    },
		events:{url:"/schedule/salesSchedulerJson",
				data : function() {
					return {"d_dept_cd" : $("#inputDepCodeList").val()};
			 	}
		},
		timeFormat:'(H:mm)'
	});
	*/

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		lang: 'ko',
		
		defaultDate: '2014-09-07',
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		selectable: true,
		selectHelper: true,
		select: function(pstart, pend) {
			
			
			var start = pstart.format();
	        var end = pend.format();
//
			$("#spop").remove();
			
//	        
	        var h1 =$("<h1 />");
	        var span =$("<span />");
	        var closeBtn = $("<a href='#' />");

	        span.text("일정 등록");
	        closeBtn.text("닫기");
	        closeBtn.click(function(){
	        	$("#spop").remove();
	        });
	        h1.append(span);
	        h1.append(closeBtn);
	        
	        
	        
	        var tr0 = $("<tr />");	        
	        var td0 = $("<td />");
	        
	        var inputTitle = $("<input type='text' id='scheduleTitle' placeholder='제목입력' />");
	        inputTitle.css("width","99%");
	        td0.append(inputTitle);	        
	        
	        var tr1 = $("<tr />");	        
	        var td1 = $("<td />");	 
	        
	        //var inputStart = $("<input class='form_date' type='text' class='sch-date' />");
	        var inputStart = $("<input class='form_date' type='text' data-date-format='yyyy-mm-dd' readonly='readonly' placeholder='날짜입력' class='sch-date' />");
	        //inputStart.datepicker();
	        var inputEnd = $("<input class='form_date' type='text' data-date-format='yyyy-mm-dd' readonly='readonly' placeholder='날짜입력' class='sch-date' />");
	        //inputEnd.datepicker();
	        
	        
	        

	        var inputStartTime = $("<select class='sch-time' />");
	        var inputEndTime = $("<select class='sch-time' />");
	        inputStartTime.hide();
	        inputEndTime.hide();
	        for(var i =0 ; i < timeOption.length ; i++){
	        	var option = "<option value='"+timeOptionVal[i]+"' >"+timeOption[i]+"</option>";
	        	inputStartTime.append(option);
	        	inputEndTime.append(option);
	        }
	        
	        var alldayRadio = $("<input name='sche_type' type='radio' id='alldaySch' value='A' checked />");
	        var alldayRadioLabel = $("<label for='alldaySch' >종일</label>");
	        var timeRadio = $("<input name='sche_type' type='radio' id='timeSch' value='T' />");
	        var timeRadioLabel = $("<label for='timeSch' >시간</label>");
			alldayRadio.change(function(){
				if($(this).is(":checked")){
					$(".sch-time").hide();
				}
			});
			timeRadio.change(function(){
				if($(this).is(":checked")){
					$(".sch-time").show();
				}
			});

	        inputStart.val(start);
	        inputEnd.val(end);	        
	        td1.append(inputStart);
	        td1.append(inputStartTime);
	        td1.append(" ~ ");
	        td1.append(inputEnd);
	        td1.append(inputEndTime);
	        td1.append(alldayRadio);
	        td1.append(alldayRadioLabel);
	        td1.append(timeRadio);
	        td1.append(timeRadioLabel);
	        
	        
	        var tr2 = $("<tr />");
	        var td2 = $("<td />");
	        
	        var selectType = $("<select />");
	        selectType.css("float", "left");
	        selectType.change(function(){	        	
	        	$("#selectItemColor").css("background-color",$(this).children("option:selected").attr("id"));
	        });	        
	        selectType.append(myTypeItem);
	        
	        td2.append("<div class='item-col' style='float:left;line-height: 18px;' >일정 구분</div>");
	        td2.append(selectType);
	        td2.append("<div id='selectItemColor' />");
	        td2.append(fn_GetColorChooser());
	        
	        
	        var tr3 = $("<tr />");
	        var td3 = $("<td />");
	        
	        var sclrollDiv = $("<div class='area'  />");
	        var contentTextarea = $("<textarea />");
	        contentTextarea.css({"width":"100%","height":"100%"});
	        sclrollDiv.append(contentTextarea);
	        td3.append(sclrollDiv);
	        
	        var buttonDiv = $("<div class='ta_center spop-button' />");	        
	        var delBtn = $("<button type='button' class='roundbtn' />");
	        var modiBtn = $("<button type='button' class='roundbtn green'>");
	        delBtn.text("삭제");
	        modiBtn.text("수정");
	        delBtn.click(function(){
	        	if(confirm("삭제하시겠습니까?")){
	        	}
	        });
	        modiBtn.click(function(){
	        	alert("수정");
	        });
	        buttonDiv.append(delBtn);
	        buttonDiv.append(modiBtn);
	        
	        
	        
	        var table = $("<table class='table_wrap spop-table' />");
	        var divPop =$("<div id='spop' class='mysch' />");
	        var spopContent =$("<div class='arrow_box' ></div>");
	        
	        
	        tr0.append(td0);
	        tr1.append(td1);
	        tr2.append(td2);
	        tr3.append(td3);
	        table.append(tr0);
	        table.append(tr1);
	        table.append(tr2);
	        table.append(tr3);
	        

	        
	        spopContent.append(h1);
	        spopContent.append(table);
	        spopContent.append(buttonDiv);
	        
	        
	        divPop.append(spopContent);
	        
	        
	        var pop_x = ( mouse_x - 250 ) ;
	        var pop_y = ( mouse_y - 325 );
	        divPop.css({"top":pop_y,"left":pop_x,"width":"700px"});
	        $("body").append(divPop);
	        
	        
	        $(".form_date").datetimepicker({
	    		language:  'ko',
	            weekStart: 1,
	            todayBtn:  1,
	    		autoclose: 1,
	    		todayHighlight: 1,
	    		startView: 2,
	    		minView: 2,
	    		forceParse: 0
	        });

			
	        /*
	    	$(".form_date").datetimepicker({
	    		language:  'ko',
	            weekStart: 1,
	            todayBtn:  1,
	    		autoclose: 1,
	    		todayHighlight: 1,
	    		startView: 2,
	    		minView: 2,
	    		forceParse: 0
	        });
	    	*/
			
//		
//			var title = prompt('Event Title:');
//			var eventData;
//			if (title) {
//				eventData = {
//					title: title,
//					start: start,
//					end: end
//				};
//				alert(start);
//				alert(end);
//				$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
//			}
//			$('#calendar').fullCalendar('unselect');
	        
	        
//	        $("#alldaySch").change(function(){
//	        	alert("111");
//	    		if($(this).is(":checked").val()=="A"){
//	    			alert($(this).val() +" 올데이선택");
//	    		}else{
//	    			alert($(this).val() +" 타임선택");
//	    		}
//	    	});
	        

		},
		eventClick: function(calEvent, jsEvent, view) {
			var start = calEvent.start.format();
	        var end = calEvent.end.format();

			$("#spop").remove();
			var div =$("<div id='spop' />");
	        var spopContent =$("<div class='arrow_box' ></div>");
	        
	        var h1 =$("<h1 />");
	        var span =$("<span />");
	        span.text(calEvent.title);
	        var closeBtn = $("<a href='#' />");
	        closeBtn.text("닫기0");
	        closeBtn.click(function(){
	        	$("#spop").remove();
	        });
	        var table = $("<table class='table_wrap spop-table' />");
	        var tr1 = $("<tr />");	        
	        var td1 = $("<td />");
	        td1.text(start+" ~ "+end);
	        
	        var tr2 = $("<tr />");
	        var td2 = $("<td />");
	        var sclrollDiv = $("<div />");
	        sclrollDiv.text("내용");

	        td2.append(sclrollDiv);
	        
	        var buttonDiv = $("<div class='ta_center spop-button' />");
	        
	        var delBtn = $("<button type='button' class='roundbtn' />");
	        var modiBtn = $("<button type='button' class='roundbtn green'>");
	        delBtn.text("삭제");
	        modiBtn.text("수정");
	        
	        
	        delBtn.click(function(){
	        	if(confirm("삭제하시겠습니까?")){
	        		
	        	}
	        });
	        modiBtn.click(function(){
	        	alert("수정");
	        });
	        
	        h1.append(span);
	        h1.append(closeBtn);
	        spopContent.append(h1);
	        
	        tr1.append(td1);
	        tr2.append(td2);
	        table.append(tr1);
	        table.append(tr2);
	        
	        buttonDiv.append(delBtn);
	        buttonDiv.append(modiBtn);
	        
	        spopContent.append(table);
	        spopContent.append(buttonDiv);
	        
	        div.append(spopContent);
	        //div.css({"top":jsEvent.pageY-(242),"left":jsEvent.pageX-250});
	        div.css({"top":jsEvent.pageY-(300),"left":jsEvent.pageX-410});
	        $("#content").append(div);

	    },
		events: [
			{
				title: 'All Day Event',
				start: '2014-09-01',
				end: '2014-09-03',
				allDay:true
					
			},
			{
				title: 'All2222 Day Event',
				start: '2014-09-04T16:00:00',
				end: '2014-09-06T01:00:00'
					
			}/*,
			{
				title: 'Long Event',
				start: '2014-09-07',
				end: '2014-09-10'
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: '2014-09-09T16:00:00'
			},
			{
				title: 'Conference',
				start: '2014-09-11',
				end: '2014-09-13'
			},
			{
				title: 'Meeting',
				start: '2014-09-12T10:30:00',
				end: '2014-09-13T08:30:00'
			},
			{
				title: 'Lunch',
				start: '2014-09-12T12:00:00'
			},
			{
				title: 'Meeting',
				start: '2014-09-12T14:30:00'
			},
			{
				title: 'Happy Hour',
				start: '2014-09-12T17:30:00'
			},
			{
				title: 'Dinner',
				start: '2014-09-12T20:00:00'
			},
			{
				title: 'Birthday Party',
				start: '2014-09-13T07:00:00'
			},
			{
				title: 'Click for Google',
				url: 'http://google.com/',
				start: '2014-09-28'
			}*/
		]
	});

}

function getLocalDate(mon){
	var now = new Date();
	now.setMonth(now.getMonth() + mon);
	var year= now.getFullYear();
	var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	          
	return year + '-' + mon + '-' + day;
}