(function($) {
		$( document ).ajaxStart( function() {
//			$("html > body").css({position:"fixed"});
        	var ajaxScreen = $("<div id='ajaxLoding' />");
        	var height = $("#layerPopSpace").height();
        	var scrollTop = $("#topBody").scrollTop();
        	ajaxScreen.css({
        		position:"absolute",
        		left:"0px",
        		top:"0px",
        		width:"100%",
        		height:height+"px",
        		"z-index": "9999"
        	});
        	var view = $("<div />");
        	view.css({
        		position:"absolute",
        		left:$(document).width()/2 - 100/2,
        		top:(screen.availHeight/2 -100)+scrollTop ,
//        		width:100,
        		padding:20,
        		"border-radius": 10,
        		"background-color":"rgba(0,0,0,0.7)",
        		"text-align":"center",
        	});
//        	var ajaxIcon = $("<img src='/resources/images/ajax-loader-bar.gif'/>");
        	var ajaxIcon = $("<img src='/resources/images/712.GIF'/>");
        	view.append(ajaxIcon);
        	ajaxScreen.append(view);
        	$("html > body").append(ajaxScreen);
		});
        $.ajaxSetup({
        	global: false
        	,timeout: 30000
        	,complete:function(){
        		setTimeout(function(){
        			$("#ajaxLoding").remove();
        		}, 500);
        	}
        	,error : function(jqXHR, textStatus, errorThrown) {
        		if(jqXHR.status != ""){ 
        			if(jqXHR.status === 404 ) {
        				alert("일시적이 오류가 발생했습니다.\n담당자에게 문의하세요.");
        			}else if(jqXHR.status === 500 ) {
        					alert("서버 오류 또는 점검중입니다.\n담당자에게 문의하세요.");
//        					location.replace("/logout");
        			}else{
//        				location.replace("/logout");
        			}
        		}
        		setTimeout(function(){
        			$("#ajaxLoding").remove();
        		}, 500);
        		
            }
        });
})(jQuery);


function fnSetAjaxFileLoding(){
	var loaingText = $("<span id='fileSpan' >file uploading.....</span>");
	loaingText.css({
		position:"absolute",
		left:$(document).width()/2 - 100/2,
		top:screen.availHeight/2+30 ,
		width:100,
		"font-weight":"bold",
		"font-size":"20px",
		"text-align":"center",
	});
	$("#ajaxLoding").append(loaingText);
}

;(function($){
	$.fn.datetimepicker.dates['ko'] = {
		days: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"],
		daysShort: ["일", "월", "화", "수", "목", "금", "토", "일"],
		daysMin: ["일", "월", "화", "수", "목", "금", "토", "일"],
		months: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		monthsShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
		suffix: [],
		meridiem: ["오전", "오후"],
        today: "오늘",
	};
}(jQuery));
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
 * @함수명: comma
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: 3자리 콤마 찍기
 * @param :   
 */
Number.prototype.ncomma = function() {
	if (this == 0)
		return 0;
	 
	var reg = /(^[+-]?\d+)(\d{3})/;
	var n = (this + '');
	 
	while (reg.test(n))
		n = n.replace(reg, '$1' + ',' + '$2');
	 
	return n;
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
String.prototype.uncommazero = function(){
	var temp = this.replace(/\,/g,"");
	return  temp ==""? "0":temp; 
};
String.prototype.dateReplace = function(){
	var temp = this.replace(/\-/g,"");
	return  temp;
};
String.prototype.allTrim = function(){
	var temp = this.replace(/\ /g,"");
	return  temp;
};


/**
 * @함수명: read
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
jQuery.fn.ymdpicker = function(){
	$(this).datepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		buttonImage: "/resources/img/icon-date.png",
		buttonImageOnly: true,
		showOn: "button",
		showButtonPanel: true,
		closeText:"선택",
		buttonText: "Select date",
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		showMonthAfterYear: true,
		yearSuffix: '',
//        defaultDate : getLocalDate(-360),
		changeMonth: true,
		changeYear: true,
		yearRange: '2014:*'
//			,
//		onClose: function(dateText, inst) {  
//			var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
//			var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
//			$(this).val($.datepicker.formatDate('yy-mm-dd', new Date(year, month, 1)));
//		}
	
	}).focus(function () {
//      $(".ui-datepicker-calendar").hide();
//      $("#ui-datepicker-div").position({
//          my: "center top",
//          at: "center bottom",
//          of: $(this)
//      });
	});
	
};
jQuery.fn.yyyymmpicker = function(){
	$(this).datepicker({
    	dateFormat: 'yy-mm',
        prevText: '이전 달',
        nextText: '다음 달',
        buttonImage: "/resources/img/icon-date.png",
        buttonImageOnly: true,
        showOn: "button",
        showButtonPanel: true,
    	closeText:"선택",
        buttonText: "Select date",
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        showMonthAfterYear: true,
        yearSuffix: '',
//        defaultDate : getLocalDate(-360),
        changeMonth: true,
        changeYear: true,
        yearRange: '2014:*',
        onClose: function(dateText, inst) {  
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
            $(this).val($.datepicker.formatDate('yy-mm', new Date(year, month, 1)));
        }
        	
	}).focus(function () {
//      $(".ui-datepicker-calendar").hide();
//      $("#ui-datepicker-div").position({
//          my: "center top",
//          at: "center bottom",
//          of: $(this)
//      });
  });

};
jQuery.fn.selpicker = function(){
	$(this).datepicker({
		changeMonth: true,
        changeYear: true,
		showOn: "button",
	    buttonImage: "/resources/img/icon-date.png",
	    buttonImageOnly: true,
	    buttonText: "Select date",
    	dateFormat: 'yy-mm-dd',
    	showMonthAfterYear: true,
    	monthNames: ['1월','2월','3월','4월','5월','6월',
	                 '7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월',
	                 '7월','8월','9월','10월','11월','12월']
	});

};
jQuery.fn.onlyNum = function(){
	$(this).keydown(
		function() {
			if ((event.keyCode >= 48 && event.keyCode <= 57)
					|| event.keyCode == 9 || event.keyCode == 8
					|| (event.keyCode >= 96 && event.keyCode <= 105)
					|| (event.keyCode >= 37 && event.keyCode <= 40)
					|| (event.keyCode == 189 || event.keyCode == 109)) {
				event.returnValue = true;
			} else if (event.keyCode == 13) {
				event.keyCode = 9;
			} else {
				event.returnValue = false;
			}
		}
	).css('imeMode','disabled');
	
	if( $(this).attr("min") !== undefined ||  $(this).attr("max") !== undefined){
		$(this).focusout(function(e){
			var min = parseInt($(this).attr("min"));
			var max = parseInt($(this).attr("max"));
			var val = parseInt($(this).val());
//			var divView = $("<div style='position: absolute;height: 20px; background-color: #fff; border: 1px solid #ccc;' />");
			var divView = $("<div/>");
			divView.css({
				 "position": "absolute"
				,"height": "20px"
				,"background-color": "#fff"
				,"border": "1px solid #ccc"
				,"padding": "4px"
//				,"left": "-60px"
				,"top": "-24px"
				,"border-radius": "5px"
				,"box-shadow":"1px 1px rgba(0,0,0,0.6)"
				,"z-index": "99999"
			});
			if( $(this).attr("min") !== undefined &&  $(this).attr("max") !== undefined){
//				alert("둘다 있음 : "+$(this).val());
				if(val < min && val < max){
//					alert("범위 : "+ min+"~"+max);
					divView.append("<span>최소값 : "+ min+"</span>");
					$(this).parent().css({"position": "relative"});
					$(this).parent().append(divView);
					$(this).val(min);
				}else if(val > min && val > max){
					$(this).val(max);				
					divView.append("<span>최대값 : "+ max+"</span>");
					$(this).parent().css({"position": "relative"});
					$(this).parent().append(divView);
//					alert("범위 : "+ min+"~"+max);
				}
			}else if($(this).attr("min") !== undefined &&  $(this).attr("max") === undefined){
//				alert("민 있음 : "+$(this).val()+" min : " +min);
				if(val < min ){
//					alert("최소값 : "+ min);
					divView.append("<span>최소값 : "+ min+"</span>");
					$(this).parent().css({"position": "relative"});
					$(this).parent().append(divView);
					$(this).val(min);
				}
			}else if($(this).attr("max") !== undefined && $(this).attr("min") === undefined ){
//				alert("맥 있음 : "+$(this).val()+" max : " +max);
				if(val > max){
//					alert("최대값 : "+ max);
					divView.append("<span>최대값 : "+ max+"</span>");
					$(this).parent().css({"position": "relative"});
					$(this).parent().append(divView);
					$(this).val(max);
				}
			}
			divView.delay( 1000 ).fadeOut( "slow", function() {
				divView.remove();
			});
		});		
	}
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
/**
 * @함수명: setDateFormat
 * @작성일: 2014. 9. 29.
 * @작성자: 김진호
 * @설명: YYYY/MM/DD  >  YYYY-MM-DD
 * @param : YYYYMMDD
 *            seq
 */
String.prototype.dateFormat2 = function(){
	
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
 * @설명: YYYYMMDD  >  YYYY-MM-DD
 * @param : YYYYMMDD
 *            
 */
String.prototype.dateFormat = function(){
	
	var returnDate = "";
	if(this != ""){
		returnDate = this.substring(0, 4)+"-"+this.substring(4, 6)+"-"+this.substring(6, 8);
	}
    return  returnDate;
};



/**
 * @함수명: setYearMonthFormat
 * @작성일: 2014. 12. 31.
 * @작성자: 김진호
 * @설명: 년월 포멧
 * @param :
 *            seq
 */
String.prototype.setYearMonthFormat = function(){
	
	var returnDate = "";
	if(this != ""){
		returnDate = this.substring(0, 4)+"-"+this.substring(4, 6);
	}
    return  returnDate;
};


//파일 용량 체크
//function checkingFileSize(file){
//	var checkSize = 1024 * 1024 *10 *10000; //10MB
//	if(file.size  > checkSize){
//		alert("10M 이하의 파일만 등록 가능합니다.");
//		return false;
//	}else if(file.name.length  > 50){
//		alert("파일명이 50자 이하의 파일만 등록 가능합니다. ");
//		return false;
//	}else{
//	    return true;
//	}
//}
//이미지 체크
function checkingImages(fileName){
	var fileEx = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();	
	switch(fileEx){
	case 'gif': case 'jpg': case 'png':
		return true;
		break;
	default:
		alert("확장자가 gif, jpg, png 인 파일만 등록 가능합니다.");
	return false;
	break;
	}
}
//브라우저 ie 9이하 체크
//function browserCheck(){
//	var rv = 10; // Return value assumes failure.    
//	var agt = navigator.userAgent.toLowerCase();
//	if (agt.indexOf("msie") != -1){
//	    if (navigator.appName == 'Microsoft Internet Explorer') {        
//	         var ua = navigator.userAgent;        
//	         var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");        
//	         if (re.exec(ua) != null)            
//	             rv = parseFloat(RegExp.$1);    
//	        }    
//		
//	}
//	return rv <= 9 ? false : true;
//}

// 파일 용량 계산 ###.# 자리로 변경
//Number.prototype.fileSize = function() {
//	
//	var size = 0;
//	var unit = "Byte";
//	if(this < 999){
//		size = this;
//	}else if(this < 999999){ 
//		size = this/1024; //KB;
//		if((size%1) != 0){
//			size = size.toFixed(1);
//		}
//		unit = " KB";
//	}else if(this < 999999999){
//		size = this/(1024*1024); //MB;
//		if((size%1) != 0 ){
//			if( size < 100){
//				size = size.toFixed(1);
//			}else{
//				size = size.toFixed(0);				
//			};
//		}
//		unit = " MB";
//	}else if(this < 999999999999){
//		size = this/(1024*1024*1024);  //GB;		
//		if((size%1) != 0){
//			size = size.toFixed(2);
//		}
//		unit = " GB";
//	}else if(this < 999999999999999){
//		size = this/(1024*1024*1024*1024);		//TB;
//		if((size%1) != 0){
//			size = size.toFixed(2);
//		}
//		unit = " TB";
//	}
//    return size+unit;
//};



jQuery.fn.instancePopUp = function(){
	var id = $(this).attr("id");
	$("#"+id).remove();
	var pops = $(this);
	$("#layerPopSpace").append(pops);
};
jQuery.fn.enterSearch = function(funtionNm){
	$(this).keydown(
			function() {
				if (event.keyCode == 13){
					eval(funtionNm+"(1)");
				} 
			}
		)
};





jQuery.fn.asdTree = function(option){
//	alert("5555");
	var op = $.extend({
		url: "",
		data: {},
		key:"key",
		val:"val",
		dataType: "0px",
		root:false,
		group: false,
		click : function(data){}
	}, option );
//	op.success("fffff");
	var target = $(this);
	$.ajax({
		url : op.url,
		data : op.data,
		type : "POST",
		dataType : "json",
		success : function(data) {
			var tempDepth = 1;
			var html = '<ul class="mtree transit">';
			if(op.root) html += '<li><a class="tree-btn" href="#" data-key="1" ></a>';
			for (var i = 0; i < data.result.length; i++) {
				var depth = data.result[i].m_depth;
				if(tempDepth == depth){
					html += '<li><a href="#" class="tree-btn goog"  data-key="'+data.result[i][op.key]+'" >'+data.result[i][op.val]+'</a>';
					tempDepth = depth;
				}else if(tempDepth < depth){
					html += "<ul>";
					html += '<li><a href="#" class="tree-btn"  data-key="'+data.result[i][op.key]+'" >'+data.result[i][op.val]+'</a>';
					tempDepth = depth;
				}else{
					var gap = tempDepth - depth;
					for (var f = 0; f < gap; f++) {
						html += "</li></ul>";
					}
					html += '<li><a href="#" class="tree-btn"  data-key="'+data.result[i][op.key]+'" >'+data.result[i][op.val]+'</a>';
					tempDepth = (tempDepth - gap);
				}
				
			
			}
			var gap2 = tempDepth -1;
			for (var g = 0; g < gap2; g++) {
				html += "</li></ul>";
			}
			html += "</li></ul>";
			target.html(html);
			target.find("a.tree-btn").click(function(){
				var key = $(this).data("key");
//				alert(key);
				op.click({code:key});
			});
			setMtree();
		}
	});
//	op.success.call(this);
};






var streeDefault = {
		url: "",
		
		key:"key",
		val:"val",
		dataType: "0px",
		data: {},
		
		root : false,
		rootKey : "",
		rootVal : "root",
		
		allOpen : false,
		prarentClick : true,
		className : "mtree",
		click : function(data){},

		btnView : false,
		defaultButtonText: {
			allClose: "닫기",
			allOpen: "펼치기"
		},
		
	};


var $targetEl;

//메뉴관리 메뉴 트리
jQuery.fn.sTree = function(option){
	var op = $.extend(streeDefault, option );
	var target = $(this);
	$.ajax({
		url : op.url,
		data : op.data,
		type : "POST",
		dataType : "json",
		success : function(data) {
			var ul = $("<ul class='"+op.className+" transit' />");
			
			
			for (var i = 0; i < data.result.length; i++) {
				var depth = data.result[i].m_depth;
				var newli = $("<li/>");
				var newa = $("<a href='#' />");
				newa.data("key",data.result[i][op.key]);
				newa.click(function(){
					op.click({code: $(this).data("key")});
				});
				newa.text(data.result[i][op.val]);
				newli.append(newa);
				
				
				$targetEl =  ul;				
				
				for (var f = 0; f < depth-1 ; f++) {
					$targetEl = $targetEl.children("li:last-child");	
					if($targetEl.children("ul").length > 0){
						$targetEl = $targetEl.children("ul");
					}
				}
				
				if($targetEl.children("ul").length == 0){
					var newul = $("<ul/>");
					newul.append(newli);	
					$targetEl.append(newul);
				}else{
					$targetEl.append(newli);
				}
			}
			target.html(ul);
//			alert(target.attr("id"));
			setMtree(op.className,target.attr("id"));
		}
	});
};




//레프트 메뉴 트리 기초값
var $targetMenuEl;

var leftMenuDefault = {
		url: "",
		
		key:"key",
		val:"val",
		m_url:"",
		m_nm:"",
		val:"val",
		dataType: "0px",
		data: {},
		
		root : false,
		rootKey : "",
		rootVal : "root",
		
		allOpen : false,
		prarentClick : true,
		className : "aside_nav",
		click : function(data){},
		
		btnView : false,
		defaultButtonText: {
			allClose: "닫기",
			allOpen: "펼치기"
		},
		
};

//레프트 메뉴 트리
jQuery.fn.leftMenu = function(option){
	var op = $.extend(leftMenuDefault, option );
	var target = $(this);
	$.ajax({
		url : op.url,
		data : op.data,
		type : "POST",
		dataType : "json",
		success : function(data) {
			var $ul = $("<ul class='"+op.className+"' />");
			
			
			for (var i = 0; i < data.result.length; i++) {
				var depth = data.result[i].m_depth;
				var $newli = $("<li/>");
				var $newa = $("<a href='#' />");
				$newa.data("key",data.result[i][op.key]);
				$newa.data("m_url",data.result[i].m_url);
				$newa.data("m_nm",data.result[i][op.val]);
				
				$newa.click(function(){
					$(this).parent().toggleClass("selected");
					op.click({
						code : $(this).data("key"),
						node : $(this),
						m_url : $(this).data("m_url"),
						m_nm : $(this).data("m_nm")
					});
					$('#inputHiddenTitle').val($(this).data("m_nm"));
				});
				$newa.text(data.result[i][op.val]);
//				if(depth == 1){
//					$newa.append($("<i class='fa fa-caret-down' />"));
//				}
				$newli.append($newa);
				
				
				$targetMenuEl =  $ul;				
				
				for (var f = 0; f < depth-1 ; f++) {
					$targetMenuEl = $targetMenuEl.children("li:last-child");	
					if($targetMenuEl.children("ul").length > 0){
						$targetMenuEl = $targetMenuEl.children("ul");
					}
				}
				
				if($targetMenuEl.is( "li" ) && $targetMenuEl.children("ul").length == 0){
					$targetMenuEl.children("a").append($("<i class='fa fa-caret-right' />"));
					var $newul = $("<ul/>");
					$newul.append($newli);	
					$targetMenuEl.append($newul);
				}else{
					$targetMenuEl.append($newli);
				}
			}
			target.html($ul);
			if(!op.allOpen){
				$("."+op.className+" > li  ul").hide();
			}
		}
	});
};


////파일구조 트리 기초값
var $targetCodeEl;

var leftCodeDefault = {
		url: "",
		
		key:"key",
		val:"val",
		p_key:"p_key",
		data: {},
		
		root : true,
		rootKey : "",
		rootVal : "최상위코드",
		
		allOpen : false,
		prarentClick : true,
		tagId : "ftree",
		click : function(data){},
		
		btnView : false,
		defaultButtonText: {
			allClose: "닫기",
			allOpen: "펼치기"
		},
		
};



(function($){
	
	function fTreeMake(el,option){
		var that = this;
		var defaults = {
			url: "",
			
			key:"key",
			val:"val",
			p_key:"p_key",
			data: {},
			
			root : true,
			rootKey : "",
			rootVal : "최상위코드",
			
			allOpen : false,
			prarentClick : true,
			tagId : "ftree",
			click : function(data){},
			
			btnView : false,
			defaultButtonText: {
				allClose: "닫기",
				allOpen: "펼치기"
			}
        };
		var target = $(el);
		that.element = el;
        that.el = $(el);
        that.options = $.extend(defaults, option );
        var op = that.options;
    	$.ajax({
    		url : op.url,
    		data : op.data,
    		type : "POST",
    		dataType : "json",
    		success : function(data) {
    			var $ul = $("<ul id='"+op.tagId+"'  />");
    			if(op.root) {
    				var rootli = $('<li class="branch" data-top="Y" ><i class="fa fa-desktop"></i></li>');
    				var roota = $('<a href="#" data-key="" >'+op.rootVal+'</a>');
    				roota.data(op.key+"",op.rootKey);
    				roota.data(op.val+"",op.rootVal);
    				roota.data(op.p_key+"","");
    				roota.click(function(){
    					$("#"+op.tagId).find("li").removeClass("selected");
    					$(this).parent().toggleClass("selected");
    					op.click({
    						node : $(this),
    						key : $(this).data(op.key),
    						val : $(this).data(op.val),
    						p_key : $(this).data(op.p_key),
    						depth : "0"
    					});
    				});
    				rootli.append(roota);
    				$ul.append(rootli);
    			}
    			for (var i = 0; i < data.result.length; i++) {
    				var depth = data.result[i].depth;
    				var add_flag = data.result[i].add_flag;
    				var $newa = $("<a href='#' />");
    				var $newli = $("<li/>");
    				if (typeof add_flag != "undefined"){
    					$newli.data("add_flag",add_flag);
    				}
    				$newa.data(op.key+"",data.result[i][op.key]);
    				$newa.data(op.val+"",data.result[i][op.val]);
    				$newa.data(op.p_key+"",data.result[i][op.p_key]);
    				if (typeof add_flag != "undefined"){
    					$newa.data("add_flag",add_flag);
    				}
    				$newa.data("depth",depth);
    				
    				$newa.click(function(){
    					$("#"+op.tagId).find("li").removeClass("selected");
    					$(this).parent().toggleClass("selected");
    					var add_flag = "";
    					if (typeof add_flag != "undefined"){
    						add_flag = $(this).data("add_flag")
    					}
    					op.click({
    						node : $(this),
    						key : $(this).data(op.key),
    						val : $(this).data(op.val),
    						p_key : $(this).data(op.p_key),
    						add_flag : add_flag,
    						depth : $(this).data("depth")
    					});
    				});
    				$newa.text(data.result[i][op.val]);
//    				if(depth == 1){
//    					$newa.append($("<i class='fa fa-caret-down' />"));
//    				}
    				$newli.append($newa);
    				
    				
    				$targetCodeEl =  $ul;				
    				
    				for (var f = 0; f < depth-1 ; f++) {
    					$targetCodeEl = $targetCodeEl.children("li:last-child");	
    					if($targetCodeEl.children("ul").length > 0){
    						$targetCodeEl = $targetCodeEl.children("ul");
    					}
    				}
    				
    				if($targetCodeEl.is( "li" ) && $targetCodeEl.children("ul").length == 0){
//    					$targetCodeEl.children("a").append($("<i class='fa fa-caret-right' />"));
    					var $newul = $("<ul/>");
    					$newul.append($newli);	
    					$targetCodeEl.append($newul);
    				}else{
    					$targetCodeEl.append($newli);
    				}
    			}
    			target.html($ul);
    			if(!op.allOpen){
    				$('#'+op.tagId).treed({openedClass:'fa fa-folder-open', closedClass:'fa fa-folder'});
    				$("#"+op.tagId+" > li > ul li ").hide();
    			}else{
    				$('#'+op.tagId).treed({openedClass:'fa fa-folder', closedClass:'fa fa-folder-open'});
    				$("#"+op.tagId+" li ").show();
    			}
    			
    			
    		}
    	});
        
	}
	$.fn.fTree = function(option){
		return this.each(function () {
               var instance = new fTreeMake(this, option);
               $(this).data("ftree", instance);
        });
	};
	
//}(jQuery));
//
//(function($){
	
	function fTreeMakeNew(el,option){
		var that = this;
		var defaultss = {
				url: "",
				
				key:"key",
				val:"val",
				p_key:"p_key",
				data: {},
				
				root : true,
				rootKey : "",
				rootVal : "최상위코드",
				
				allOpen : false,
				prarentClick : true,
				tagId : "ftree",
				click : function(data){},
				
				btnView : false,
				defaultButtonText: {
					allClose: "닫기",
					allOpen: "펼치기"
				}
		};
		var target = $(el);
		that.element = el;
		that.el = $(el);
		that.options = $.extend(defaultss, option );
		var op = that.options;
		$.ajax({
			url : op.url,
			data : op.data,
			type : "POST",
			dataType : "json",
			success : function(data) {
				var $ul = $("<ul id='"+op.tagId+"'  />");
				if(op.root) {
					var rootli = $('<li class="branch" data-top="Y" ><i class="fa fa-desktop"></i></li>');
					var roota = $('<a href="#" data-key="" >'+op.rootVal+'</a>');
					roota.data(op.key+"",op.rootKey);
					roota.data(op.val+"",op.rootVal);
					roota.data(op.p_key+"","");
					roota.click(function(){
						$("#"+op.tagId).find("li").removeClass("selected");
						$(this).parent().toggleClass("selected");
						op.click({
							node : $(this),
							key : $(this).data(op.key),
							val : $(this).data(op.val),
							p_key : $(this).data(op.p_key),
							depth : "0"
						});
					});
					rootli.append(roota);
					$ul.append(rootli);
				}
				for (var i = 0; i < data.result.length; i++) {
					var depth = data.result[i].depth;
					var add_flag = data.result[i].add_flag;
					var $newa = $("<a href='#' />");
					var $newli = $("<li/>");
					if (typeof add_flag != "undefined"){
						$newli.data("add_flag",add_flag);
					}
					$newa.data(op.key+"",data.result[i][op.key]);
					$newa.data(op.val+"",data.result[i][op.val]);
					$newa.data(op.p_key+"",data.result[i][op.p_key]);
					if (typeof add_flag != "undefined"){
						$newa.data("add_flag",add_flag);
					}
					$newa.data("depth",depth);
					
					$newa.click(function(){
						$("#"+op.tagId).find("li").removeClass("selected");
						$(this).parent().toggleClass("selected");
						var add_flag = "";
						if (typeof add_flag != "undefined"){
							add_flag = $(this).data("add_flag")
						}
						op.click({
							node : $(this),
							key : $(this).data(op.key),
							val : $(this).data(op.val),
							p_key : $(this).data(op.p_key),
							add_flag : add_flag,
							depth : $(this).data("depth")
						});
					});
					$newa.text(data.result[i][op.val]);
					$newli.append($newa);
					
					$newli.addClass("mc"+data.result[i][op.key]);
    				if(depth == 1){
    					$ul.append($newli)
    				}else{
    					
//    					console.log("p_key : "+data.result[i][op.p_key]);
    					$targetCodeEl =  $ul.find(".mc"+data.result[i][op.p_key]);
//    					console.log("1 : "+$targetCodeEl);
//    					console.log("1 : "+$targetCodeEl.html());
//    					console.log("1 : "+$targetCodeEl.attr("class"));
//    					for (var f = 0; f < depth-1 ; f++) {
//    						$targetCodeEl = $targetCodeEl.children("li:last-child");	
//    						if($targetCodeEl.children("ul").length > 0){
//    							
//    							console.log($targetCodeEl.attr("class"));
//    							$targetCodeEl = $targetCodeEl.children("ul");
//    						}
//    					}
//    					console.log("2 : "+$targetCodeEl);
//    					console.log("2 : "+$targetCodeEl.html());
//    					console.log("2 : "+$targetCodeEl.attr("class"));
    					
    					if($targetCodeEl.is( "li" ) && $targetCodeEl.children("ul").length == 0){
    						var $newul = $("<ul/>");
    						$newul.append($newli);	
    						$targetCodeEl.append($newul);
    					}else{
    						$targetCodeEl.children("ul").append($newli);
    					}
    				}

				}
				target.html($ul);
				if(!op.allOpen){
					$('#'+op.tagId).treed({openedClass:'fa fa-folder-open', closedClass:'fa fa-folder'});
					$("#"+op.tagId+" > li > ul li ").hide();
				}else{
					$('#'+op.tagId).treed({openedClass:'fa fa-folder', closedClass:'fa fa-folder-open'});
					$("#"+op.tagId+" li ").show();
				}
				
				
			}
		});
		
	}
	$.fn.fTreeNew = function(option){
		return this.each(function () {
			var instance = new fTreeMakeNew(this, option);
			$(this).data("ftreeNew", instance);
		});
	};
	
}(jQuery));

//파일구조 트리
jQuery.fn.fTree2 = function(option){
	var op = $.extend(leftCodeDefault, option );
	var target = $(this);
	$.ajax({
		url : op.url,
		data : op.data,
		type : "POST",
		dataType : "json",
		success : function(data) {
			var $ul = $("<ul id='"+op.tagId+"'  />");
			
			if(op.root) {
				var rootli = $('<li class="branch" data-top="Y" ><i class="fa fa-desktop"></i></li>');
				var roota = $('<a href="#" data-key="" >'+op.rootVal+'</a>');
				roota.data(op.key+"",op.rootKey);
				roota.data(op.val+"",op.rootVal);
				roota.data(op.p_key+"","");
				roota.click(function(){
					$("#"+op.tagId).find("li").removeClass("selected");
					$(this).parent().toggleClass("selected");
					op.click({
						node : $(this),
						key : $(this).data(op.key),
						val : $(this).data(op.val),
						p_key : $(this).data(op.p_key),
						depth : "0"
					});
				});
				rootli.append(roota);
				$ul.append(rootli);
			}
			for (var i = 0; i < data.result.length; i++) {
				var depth = data.result[i].depth;
				var add_flag = data.result[i].add_flag;
				var $newa = $("<a href='#' />");
				var $newli = $("<li/>");
				if (typeof add_flag != "undefined"){
					$newli.data("add_flag",add_flag);
				}
				$newa.data(op.key+"",data.result[i][op.key]);
				$newa.data(op.val+"",data.result[i][op.val]);
				$newa.data(op.p_key+"",data.result[i][op.p_key]);
				if (typeof add_flag != "undefined"){
					$newa.data("add_flag",add_flag);
				}
				$newa.data("depth",depth);
				
				$newa.click(function(){
					$("#"+op.tagId).find("li").removeClass("selected");
					$(this).parent().toggleClass("selected");
					var add_flag = "";
					if (typeof add_flag != "undefined"){
						add_flag = $(this).data("add_flag")
					}
					op.click({
						node : $(this),
						key : $(this).data(op.key),
						val : $(this).data(op.val),
						p_key : $(this).data(op.p_key),
						add_flag : add_flag,
						depth : $(this).data("depth")
					});
				});
				$newa.text(data.result[i][op.val]);
//				if(depth == 1){
//					$newa.append($("<i class='fa fa-caret-down' />"));
//				}
				$newli.append($newa);
				
				
				$targetCodeEl =  $ul;				
				
				for (var f = 0; f < depth-1 ; f++) {
					$targetCodeEl = $targetCodeEl.children("li:last-child");	
					if($targetCodeEl.children("ul").length > 0){
						$targetCodeEl = $targetCodeEl.children("ul");
					}
				}
				
				if($targetCodeEl.is( "li" ) && $targetCodeEl.children("ul").length == 0){
//					$targetCodeEl.children("a").append($("<i class='fa fa-caret-right' />"));
					var $newul = $("<ul/>");
					$newul.append($newli);	
					$targetCodeEl.append($newul);
				}else{
					$targetCodeEl.append($newli);
				}
			}
			target.html($ul);
			if(!op.allOpen){
				$('#'+op.tagId).treed({openedClass:'fa fa-folder-open', closedClass:'fa fa-folder'});
				$("#"+op.tagId+" > li > ul li ").hide();
			}else{
				$('#'+op.tagId).treed({openedClass:'fa fa-folder', closedClass:'fa fa-folder-open'});
				$("#"+op.tagId+" li ").show();
			}
		}
	});
};



var dateOptions = {
		language:  'ko',
		format:"yyyy-mm-dd",
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
			
};

var baseOptions = {
		language:  'ko',
		format:"yyyy-mm-dd",
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0	
};

jQuery.fn.winCal = function(option){
	var op = $.extend(dateOptions, option );
	$(this).datetimepicker(op);
};



$.fn.extend({
    treed: function (o) {
      
      var openedClass = 'glyphicon-minus-sign';
      var closedClass = 'glyphicon-plus-sign';
      
      if (typeof o != 'undefined'){
        if (typeof o.openedClass != 'undefined'){
        openedClass = o.openedClass;
        }
        if (typeof o.closedClass != 'undefined'){
        closedClass = o.closedClass;
        }
      };
      
        //initialize each of the top levels
        var tree = $(this);
        tree.addClass("tree");
//        tree.find('li').has('ul').each(function () {
    	  tree.find('li').each(function () {
            var branch = $(this); //li with children ul
            if($(this).children("ul").length > 0 && $(this).data("top") != 'Y'){
//            	alert($(this).parent().hasClass("tree")+"," +$(this).has('ul'));
            	var folder = $("<i class='folder "+closedClass+"'></i>");
            	branch.prepend(folder);
//            	var addico = $("<i class='fa fa-plus-square'></i>");
//            	addico.css({"cursor":"","color":"#757575"});
//            	if($(this).data("add_flag") == "Y"){
//            		branch.prepend(addico);
//            	}
            	branch.addClass('branch');
            	folder.on('click', function (e) {
            		if (this == e.target) {
            			var icon = $(this).parent().children('i.folder:first');
            			icon.toggleClass(openedClass + " " + closedClass);
            			$(this).parent().children().children().slideToggle();
            		}
            	})
            	
            }else if( $(this).parent().hasClass("tree")&& $(this).data("top") != 'Y'){
            	var folder = $("<i class=' " + closedClass + "'></i>");
            	folder.css({"cursor":"not-allowed","color": "#8C8C8C"});
            	branch.addClass('branch');
            	branch.prepend(folder);
            }
            var addico = $("<i class='fa fa-plus-square'></i>");
            addico.css({"cursor":"","color":"#757575"});
            if($(this).data("add_flag") == "Y"){
            	branch.prepend(addico);
            }
//            branch.on('click', function (e) {
//            	if (this == e.target) {
//            		var icon = $(this).children('i:first');
//            		icon.toggleClass(openedClass + " " + closedClass);
//            		$(this).children().children().slideToggle();
//                    $(this).children().children().toggle();
//            	}
//            })
            branch.children().children().toggle();
        });
      tree.find('.branch .indicator').each(function(){
        $(this).on('click', function () {
            $(this).closest('li').click();
        });
      });
        tree.find('.branch>a').each(function () {
            $(this).on('click', function (e) {
                $(this).closest('li').click();
                e.preventDefault();
            });
        });
        tree.find('.branch>button').each(function () {
            $(this).on('click', function (e) {
                $(this).closest('li').click();
                e.preventDefault();
            });
        });
    }
});


$.fn.extend({
    filedrop: function (options) {
        var defaults = {
            callback : null
        };
        options =  $.extend(defaults, options);
        return this.each(function() {
            var files = [];
            var $this = $(this);

            // Stop default browser actions
            $this.bind('dragover', function(event) {
            	$this.css("background-color","#ccc");
            	event.stopPropagation();
            	event.preventDefault();
            });
//            $this.bind('dragout', function(event) {
//            	$this.css("background-color","#fff");
//            	event.stopPropagation();
//            	event.preventDefault();
//            });
            $this.bind('dragleave', function(event) {
            	$this.css("background-color","#fff");
                event.stopPropagation();
                event.preventDefault();
            });

            // Catch drop event
            $this.bind('drop', function(event) {
            	$this.css("background-color","#fff");
                // Stop default browser actions
                event.stopPropagation();
                event.preventDefault();

                // Get all files that are dropped
                files = event.originalEvent.target.files || event.originalEvent.dataTransfer.files;
//                alert(files[0].name+"\n"+files.length);	
//                alert(files[1].name+"\n"+files.length);	
                // Convert uploaded file to data URL and pass trought callback
                if(options.callback) {
                    for (var i = 0; i < files.length; i++) {
//                        var reader = new FileReader();
//                        reader.onload = function(event) {
                            options.callback(files[i]);
//                        }
//                        reader.readAsDataURL(files[0]);
                    }
                }
                return false;
            });
        });
    }
})
function getSysDate(flag){
	var sysdate = "";
	$.ajax({
		url : "/getSystemDate",
		data : {"flag":flag},
		type : "POST",
		async : false,
		dataType : "text",
		success : function(data) {
			sysdate = data;
		}
	});	
	return sysdate;
}

String.prototype.setBaseDate = function(flag){
	if(this ==""){
		return "";
	}
	var int = 0;
	var now = new Date(this);    
	var baseWeek = 4; //목요일
	
	switch(flag){
		case "first": int = 0; break;
		case "last": int = +6; break;
		case "next": int = +7; break;
		case "prev": int = -7; break;
		default :int = 0;
	}
    var nowWeek = now.getDay();
    if(nowWeek >= baseWeek) {
    	now.setDate(now.getDate() + (baseWeek-nowWeek)+int); 
    }else{
    	now.setDate(now.getDate() + (baseWeek-nowWeek)-7+int);
    }
    var year= now.getFullYear();
    var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
    var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
    
    return year + '-' + mon + '-' + day;
};


String.prototype.setNextDate = function(){
	if(this ==""){
		return "";
	}
	var now = new Date(this);    
	now.setDate(now.getDate() +1);
	
	var year= now.getFullYear();
	var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	return year + '-' + mon + '-' + day;
};
String.prototype.setPrevDate = function(){
	if(this ==""){
		return "";
	}
	var now = new Date(this);    
	now.setDate(now.getDate() -1);
	
	var year= now.getFullYear();
	var mon = (now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);
	var day = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	return year + '-' + mon + '-' + day;
};


String.prototype.addDay = function(){
	if(this ==""){
		return "";
	}
	var now = new Date(this);    
	var nowWeek = now.getDay();
	var weeks = ["일","월","화","수","목","금","토"]; //목요일
	return this+"("+weeks[nowWeek]+")";
};

/**
 * @함수명: fnCalDateRange
 * @작성일: 2015. 10. 20
 * @작성자: 최수영
 * @설명: 일자간 일수 계산
 * @param 
 */
function fnCalDateRange(dateFrom, dateTo)
{
    var FORMAT = "-";

    // FORMAT을 포함한 길이 체크
    if (dateFrom.length != 10 || dateTo.length != 10)
        return null;

    // FORMAT이 있는지 체크
    if (dateFrom.indexOf(FORMAT) < 0 || dateTo.indexOf(FORMAT) < 0)
        return null;

    // 년도, 월, 일로 분리
    var start_dt = dateFrom.split(FORMAT);
    var end_dt = dateTo.split(FORMAT);

    // 월 - 1(자바스크립트는 월이 0부터 시작하기 때문에...)
    // Number()를 이용하여 08, 09월을 10진수로 인식하게 함.
    start_dt[1] = (Number(start_dt[1]) - 1) + "";
    end_dt[1] = (Number(end_dt[1]) - 1) + "";

    var from_dt = new Date(start_dt[0], start_dt[1], start_dt[2]);
    var to_dt = new Date(end_dt[0], end_dt[1], end_dt[2]);

    var resultValue = (to_dt.getTime() - from_dt.getTime()) / 1000 / 60 / 60 / 24;
    return resultValue;
}

/**
 * @함수명: checkNumber
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 숫자 유효성 체크
 */
function checkNumber(value)
{
	var i;
	var str = new String(value);
	for(i=0;i<str.length;i++)
	{
		if ( !(parseInt(str.charAt(i)) >= 0 && parseInt(str.charAt(i)) < 10) )
		{
			return false;
		}
	}
	return true;
}

/**
 * @함수명: numberWithCommas
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 금액에 콤마(,) 붙이기
 */
function numberWithCommas(value) {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function checkingFileSize(file) {
	var checkSize = 1024 * 1024 * 10;//10MB
	if(file.size  > checkSize){
		alert("10M 이하의 파일만 등록 가능합니다.");
		return false;
	}else{
	    return true;
	}
}

function checkingImages(fileName) {
	var fileEx = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();	
	switch(fileEx){
	case 'gif': case 'jpg': case 'png':
		return true;
		break;
	default:
		alert("확장자가 gif, jpg, png 인 파일만 등록 가능합니다.");
		return false;
		break;
	};
}

function browserCheck(){
	var rv = 10; // Return value assumes failure.    
	var agt = navigator.userAgent.toLowerCase();
	if (agt.indexOf("msie") != -1) {
	    if (navigator.appName == 'Microsoft Internet Explorer') {        
	         var ua = navigator.userAgent;        
	         var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");        
	         if (re.exec(ua) != null)            
	             rv = parseFloat(RegExp.$1);    
	    }    
		
	}
	
	return rv <= 9 ? false : true;
}


function UtilGetComboBox(c_parent_code, target){
	
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":c_parent_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var listHtml = ''; // "<option value='' selected='selected'>선택</option>";
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
