/**
 * @함수명: trimFL
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 압뒤공백제거
 * @param :   
 */
String.prototype.trimFL = function(){
	var temp = this.replace(/(^\s*)|(\s*$)/, '');
    return  temp;
};

/**
 * @함수명: trimAll
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 모든공백제거
 * @param :   
 */
String.prototype.trimAll = function(){
	var temp = this.replace(/ /g,"");
	return  temp;
};
String.prototype.toggleAmPm = function(){
	var temp = this;
	
	if(temp.indexOf("AM") > 0 || temp.indexOf("PM") > 0){
		temp = temp.replace(/AM/gi,"오전");
		temp = temp.replace(/PM/gi,"오후");
	}else if(temp.indexOf("오전") > 0 ){
		temp = temp.replace(/오전/g,"AM");
	}else if( temp.indexOf("오후") > 0){
		temp = temp.replace(/오후/g,"PM");
	}
	return  temp;
};

/**
 * @함수명: comma
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 3자리 콤마 찍기
 * @param :   
 */
String.prototype.comma = function(){
	var temp = (this =="") ? "":(parseInt(this)+"");
	var zeroDel = temp.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    return  zeroDel;
};

/**
 * @함수명: uncomma
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 콤마제거
 * @param : seq 
 */
String.prototype.uncomma = function(){
	var temp = this.replace(/\,/g,"");
    return  temp;
};


/**
 * @함수명: textMoney
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 금액을 한글로 변경
 * @param String
 */
Number.prototype.textMoney = function() {
    if (this == 0) return '영';
    var phonemic = ['','일','이','삼','사','오','육','칠','팔','구'];
    var unit = ['','','십','백','천','만','십만','백만','천만','억','십억','백억','천억','조','십조','백조'];
    var ret = '';
    var part = new Array();
    for (var x=0; x<String(this).length; x++) part[x] = String(this).substring(x,x+1);
    for (var i=0, cnt = String(this).length; cnt > 0; --cnt,++i) {
        p = phonemic[part[i]];
        p+= (p) ? (cnt>4 && phonemic[part[i+1]]) ? unit[cnt].substring(0,1) : unit[cnt] : '';
        ret+= p;
    }
    return ret;
};


/**
 * @함수명: getLocalDate
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 오늘날짜 localDate 
 * @param mon (-1 :  1달전 , 4 : 4달후) 
 */
function getLocalDate(mon){
	var now = new Date();
	now.setMonth(now.getMonth() + mon);
	var year= now.getFullYear();
	var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	          
	return year + '-' + mon + '-' + day;
}

/**
 * @함수명: setDateFormat
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: yyyy-mm-dd 변경
 * @param :
 */
String.prototype.setDateFormat = function(){
	
	var returnDate = "";
	if(this != ""){
		var dateArray = this.split("/");
		returnDate = dateArray[2]+"-"+dateArray[0]+"-"+dateArray[1];
	}
    return  returnDate;
};




/**
 * @함수명: setDateFormat
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 숫자만+ 백스페이스
 * @param :
 */
jQuery.fn.dateType = function(){
	$(this).datepicker({
		dateFormat: 'yy-mm-dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
	    showMonthAfterYear: true,
	    yearSuffix: '년'
	});
};
jQuery.fn.dateTimeType = function(){
	$(this).datetimepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일','월','화','수','목','금','토'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		hourMin: 1,
		timeFormat: 'TTh:mm',
		yearSuffix: '년',
		currentText:"오늘",
		closeText:"확인",
		timeText:"시간",		
		minuteText:"분",
		stepMinute: 10,
		hourText:"시",
		
		altField: "#"+$(this).attr("id")+"F",
		altFieldTimeOnly: false,
		altFormat: "yy/mm/dd",
		altTimeFormat: "H:m",
		altSeparator: " "
			
	});
};
jQuery.fn.dateSelectType = function(){
	$(this).datepicker({
    	dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        showMonthAfterYear: true,
        yearSuffix: '',
        defaultDate : getLocalDate(-360),
        changeMonth: true,
        changeYear: true,
        yearRange: 'c-60:c+30'
        	
        	
        	
	});
};




/**
 * @함수명: setDateFormat
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 숫자만+ 백스페이스
 * @param :
 */
jQuery.fn.onlyNumber = function(){
	$(this).keydown(function(e){
		fn_Number($(this),e);
	}).keyup(function(e){
		fn_Number($(this),e);
	}).css('imeMode','disabled');
};

function fn_Number(obj, e) {
	if (e.which == '229' || e.which == '197' && $.browser.opera) {
		setInterval(function() {
			obj.trigger('keyup');
		}, 100);
	}

	if (!(e.which && (e.which > 47 && e.which < 58) || e.which == 8
			|| e.which == 9 || e.which == 0 || (e.ctrlKey && e.which == 86))) {
		e.preventDefault();
	}

	var value = obj.val().match(/[^0-9]/g);

	if (value != null) {
		obj.val(obj.val().replace(/[^0-9]/g, ''));
	}

}
function openWindowPopUp(url,popName) {
	var popWidth = 1250;
	var popHeight = 600;
	var params  = 'width='+popWidth;
	   params += ', height='+popHeight;
	   params += ', top=50, left='+((screen.width-popWidth)/2);
	   params += ', toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no';

	   newwin=window.open(url,popName, params);
	   newwin.focus();
}
function showRequireDialog(){
	$("#spop").remove();
	
    var h1 =$("<h1 />");
    var span =$("<span />");
    var closeBtn = $("<a href='#' />");

    span.text("요구사항 전송");
    closeBtn.text("닫기");
    closeBtn.click(function(){
    	$("#spop").remove();
    });
    h1.append(span);
    h1.append(closeBtn);
    
    
    
    var tr0 = $("<tr />");	        
    var td0 = $("<td />");
    td0.append("<div  style='line-height: 18px;' >ID : "+$("#pId").val()+"<br>");
    td0.append("화면명 : "+$("#pTitle").val()+"</div>");
    
    
    var tr1 = $("<tr />");	        
    var td1 = $("<td />");	 
//
    var inputTitle = $("<input type='text' id='rName' placeholder='작성자' /> ");
    inputTitle.css("width","20%");
    td1.append(inputTitle);	        
    var inputTell = $(" <input type='text' id='rTell' placeholder='회신필요시 전화번호 등 연락가능 정보 입력해주세요' />");
    inputTell.css({"width":"75%","margin-left":"10px"});
    td1.append(inputTell);	        
    
    
    var tr2 = $("<tr />");
    var td2 = $("<td />");
    
    var selectType = $("<select id='rType' />");
    selectType.css("float", "left");
    selectType.append("<option value=''>선택</option>");
    selectType.append("<option value='개발'>개발</option>");
    selectType.append("<option value='에러'>에러</option>");
    selectType.append("<option value='수정'>수정</option>");
    selectType.append("<option value='문의'>문의</option>");
    selectType.append("<option value='신규'>신규</option>");
    selectType.append("<option value='기타'>기타</option>");
    td2.append("<div class='item-col' style='float:left;line-height: 18px;' >구분</div>");
    td2.append(selectType);
    
    
    var tr3 = $("<tr />");
    var td3 = $("<td />");
    
    var sclrollDiv = $("<div class='area'  />");
    var contentTextarea = $("<textarea id='rContent' placeholder='요구사항을 입력해주세요.'  />");
    contentTextarea.css({"width":"99%","height":"100%"});
    sclrollDiv.append(contentTextarea);
    td3.append(sclrollDiv);
    
    var buttonDiv = $("<div class='ta_center spop-button' />");	        
    var cancelBtn = $("<button type='button' class='roundbtn' />");
    cancelBtn.css("margin-left","10px");
    var sendBtn = $("<button type='button' class='roundbtn green'>");
    cancelBtn.text("취소");
    sendBtn.text("전송");
    cancelBtn.click(function(){
    	$("#spop").remove();
    });
    sendBtn.click(function(){
    	fnSaveRequire();
    });
    buttonDiv.append(sendBtn);
//    buttonDiv.append(cancelBtn);
    
    
    
    var table = $("<table class='table_wrap spop-table' />");
    var divPop =$("<div id='spop' class='mysch' />");
    var spopContent =$("<div class='arrow_box' ></div>");
    
    
    tr0.append(td0);
    tr1.append(td1);
    tr2.append(td2);
    tr3.append(td3);
    table.append(tr0);
    table.append(tr2);
    table.append(tr1);
    table.append(tr3);
    
    spopContent.append(h1);
    spopContent.append(table);
    spopContent.append(buttonDiv);
    
    
    divPop.append(spopContent);
    $("body").append(divPop);
}

function showRequireUpdateDialog(rSeq){
		$.ajax({
			url : "/proto/requireView",
		    type : "POST",
		    data :{"r_seq":rSeq},
		    dataType : "json",
		    cache : false ,
		    success : function(data) {
		    	var rSeq = data.r_seq;
		    	var rId = data.r_id;
		    	var rTitle = data.r_title;
		    	
		    	$("#rSeq").val(rSeq);
		    	$("#rId").val(rId);
		    	$("#rTitle").val(rTitle);
		    	var rType = data.r_type;
		    	var rName = data.r_name;
		    	var rTell = data.r_tell;
		    	var rContent = data.r_content;
		    	
		    	$("#spop").remove();
		    	
		        var h1 =$("<h1 />");
		        var span =$("<span />");
		        var closeBtn = $("<a href='#' />");

		        span.text("요구사항 전송");
		        closeBtn.text("닫기");
		        closeBtn.click(function(){
		        	$("#spop").remove();
		        });
		        h1.append(span);
		        h1.append(closeBtn);
		        
		        
		        
		        var tr0 = $("<tr />");	        
		        var td0 = $("<td />");
		        td0.append("<div  style='line-height: 18px;' >ID : "+rId+"<br>");
		        td0.append("화면명 : "+rTitle+"</div>");
		        
		        
		        var tr1 = $("<tr />");	        
		        var td1 = $("<td />");	 
		    //
		        var inputTitle = $("<span>"+rName+"</span>");
		        inputTitle.css("width","20%");
		        td1.append(inputTitle);	        
		        var inputTell = $(" <input type='text' id='rTell'  value='"+rTell+"' placeholder='회신필요시 전화번호 등 연락가능 정보 입력해주세요' />");
		        inputTell.css({"width":"75%","margin-left":"10px"});
		        td1.append(inputTell);	        
		        
		        
		        var tr2 = $("<tr />");
		        var td2 = $("<td />");
		        
		        var selectType = $("<select id='rType' />");
		        selectType.css("float", "left");
		        selectType.append("<option value=''>선택</option>");
		        selectType.append("<option value='개발' "+(rType =="개발"? "selected":"")+" >개발</option>");
		        selectType.append("<option value='에러' "+(rType =="에러"? "selected":"")+" >에러</option>");
		        selectType.append("<option value='수정' "+(rType =="수정"? "selected":"")+" >수정</option>");
		        selectType.append("<option value='문의' "+(rType =="문의"? "selected":"")+" >문의</option>");
		        selectType.append("<option value='신규' "+(rType =="신규"? "selected":"")+" >신규</option>");
		        selectType.append("<option value='기타' "+(rType =="기타"? "selected":"")+" >기타</option>");
		        td2.append("<div class='item-col' style='float:left;line-height: 18px;' >구분</div>");
		        td2.append(selectType);
		        
		        
		        var tr3 = $("<tr />");
		        var td3 = $("<td />");
		        
		        var sclrollDiv = $("<div class='area'  />");
		        var contentTextarea = $("<textarea id='rContent' placeholder='요구사항을 입력해주세요.'>"+rContent+"</textarea>");
		        contentTextarea.css({"width":"99%","height":"100%"});
		        sclrollDiv.append(contentTextarea);
		        td3.append(sclrollDiv);
		        
		        var buttonDiv = $("<div class='ta_center spop-button' />");	        
		        var cancelBtn = $("<button type='button' class='roundbtn' />");
		        cancelBtn.css("margin-left","10px");
		        var sendBtn = $("<button type='button' class='roundbtn green'>");
		        cancelBtn.text("취소");
		        sendBtn.text("수정");
		        cancelBtn.click(function(){
		        	$("#spop").remove();
		        });
		        sendBtn.click(function(){
		        	fnUpdateRequire();
		        });
		        buttonDiv.append(sendBtn);
//		        buttonDiv.append(cancelBtn);
		        
		        
		        
		        var table = $("<table class='table_wrap spop-table' />");
		        var divPop =$("<div id='spop' class='mysch' />");
		        var spopContent =$("<div class='arrow_box' ></div>");
		        
		        
		        tr0.append(td0);
		        tr1.append(td1);
		        tr2.append(td2);
		        tr3.append(td3);
		        table.append(tr0);
		        table.append(tr2);
		        table.append(tr1);
		        table.append(tr3);
		        
		        spopContent.append(h1);
		        spopContent.append(table);
		        spopContent.append(buttonDiv);
		        
		        
		        divPop.append(spopContent);
		        $("body").append(divPop);
		    }
		});
	
	
}


function fnSaveRequire(){
	if(!confirm("전송 하시겠습니까?"))return;
	
	var rId = $("#pId").val();
	var rTitle = $("#pTitle").val();
	var rTell = $("#rTell").val();
	var rName = $("#rName").val();
	var rType = $("#rType").val();
	var rContent = $("#rContent").val();
	var params =  { 
			"r_id" : rId,
			"r_title" : rTitle,
			"r_tell" : rTell,
			"r_name" : rName,
			"r_type" : rType,
			"r_content" : rContent
    };
	$.ajax({
		url : "/proto/requireSave",
	    type : "POST",
	    data :params,
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	if(data > 0){
	    		alert("전송되었습니다.");
	    		$("#spop").remove();
	    	}else{
	    		alert("전송에 실패 하였습니다.\n다시 시도해주세요.");
	    	}
	    }
	});
}
function fnUpdateRequire(){
	if(!confirm("수정 하시겠습니까?"))return;
	
	var rSeq = $("#rSeq").val();
	var rId = $("#rId").val();
	var rTitle = $("#rTitle").val();
	var rTell = $("#rTell").val();
	var rName = $("#rName").val();
	var rType = $("#rType").val();
	var rContent = $("#rContent").val();
	alert(rId);
	var params =  { 
			"r_seq" : rSeq,
			"r_id" : rId,
			"r_title" : rTitle,
			"r_tell" : rTell,
			"r_name" : rName,
			"r_type" : rType,
			"r_content" : rContent
	};
	$.ajax({
		url : "/proto/requireUpdate",
		type : "POST",
		data :params,
		dataType : "json",
		cache : false ,
		success : function(data) {
			if(data > 0){
				alert("수정 되었습니다.");
				$("#spop").remove();
				fnListRequire();
			}else{
				alert("수정에 실패 하였습니다.\n다시 시도해주세요.");
			}
		}
	});
}
function updateRequireStep(rSeq,rValue,fild){
	var params =  { 
			"r_seq" : rSeq,
			"r_fild" : fild,
			"r_value" : rValue
	};
	$.ajax({
		url : "/proto/requireUpdateStep",
		type : "POST",
		data :params,
		dataType : "json",
		cache : false ,
		success : function(data) {
			var text = (fild  == "r_is_del" ? "삭제":"수정");
			var text2 = (rValue  == "N" ? "가 취소 ":"");
			if(data > 0){
				alert(text+text2+" 되었습니다.");
			}else{
				alert(text+"에 실패 하였습니다.\n다시 시도해주세요.");
			}
		}
	});
}