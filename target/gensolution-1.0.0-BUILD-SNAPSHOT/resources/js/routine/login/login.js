/**************************************************************************************************
 * @파일명: login.js
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 로그인 JS
**************************************************************************************************/

/**
 * @함수명: ready
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: DOM객체가 로드된 후에 실행
 */
$(document).ready(function() {
//	innerPgChecking();
	//$('#tempPopLogin').instancePopUp();
	$("#layerPopSpace").append(tempPopLogin);
	
	fnMakeComponentEvent();
});

/**
 * @함수명: fnMakeComponentEvent
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 컴포넌트 이벤트등록
 */
function fnMakeComponentEvent() {
    $("#loginBtn").click(function() {
    	fnCheckLogin();
    });
    
    $("#loginBtn2").click(function() {
    	fnLogin();
    });
    
    $("#tempCloseLogin, #tempCloseXLogin").click(function() {
    	$("#tempPopLogin").hide();
    });
    
    
    $("#em_password").on("keydown", function(event) {
    	if (event.keyCode == 13) {
    		fnCheckLogin();
    	}
    });
    
    var userId = getCookie("userId");
    if (userId != "") {
    	$("#em_id").val(userId);
    	$("#checkSaveId").attr("checked", true);
    }
    
}
function innerPgChecking(){
}
/**
 * @함수명: fnCheckLogin
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 로그인 체크
 */
function fnCheckLogin() {
	var em_id = $.trim($("#em_id").val());
	if (em_id == "") {
		alert("아이디를 입력하세요.");
		$("#em_id").focus();
		return;
	} 
	var em_password = $.trim($("#em_password").val());
	if (em_password == "") {
		alert("비밀번호를 입력하세요.");
		$("#em_password").focus();
		return;
	}
	
	if ($("#checkSaveId").prop("checked")) {
		setCookie("userId", em_id, 7);
	} else {
		setCookie("userId", "", 7);
	}
	
	$.ajax({
		url : "/checkLogin",
	    data : {
	    	em_id : em_id,
	    	em_password : em_password
	    },
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    global : true,
	    success : function(data) {
	    	var loginVo = data.loginVo;
	    	if (loginVo.check_id == "N") {
	    		alert("존재하지 않는 아이디입니다.\n다시 입력해주세요.");
	    		$("#em_id").val("");
	    		$("#em_password").val("");
	    		$("#em_id").focus();
	    		return;
	    	} else if (loginVo.check_id == "Y" && loginVo.check_password == "N") {
	    		alert("비밀번호를 다시 확인해주세요.");
	    		$("#em_password").val("");
	    		$("#em_password").focus();
	    		return;
	    	} else if (loginVo.check_id == "Y" && loginVo.check_password == "Y") {
	    		$("#auth_flag_login").val(loginVo.auth_flag);
	    		//alert(loginVo.auth_flag);
	    		//if(loginVo.auth_flag=='0'){
	    		//alert(em_id);
	    		if(em_id=='superuser'){
	    			$("#auth_flag_login").val('0');
		    		cbo_cm_login();
		    		$("#tempPopLogin").show();
	    		}else{
	    		
		    		$("#em_id").val("");
		    		$("#em_password").val("");
		    		
		    		//alert("로그인이 정상적으로 이루어졌습니다.");
		    		$.ajax({
		    			url : "/login",
		    		    data : {
		    		    	em_id : em_id,
		    		    	em_password : em_password,
		    		    	auth_flag : loginVo.auth_flag,
		    		    	cm_code : ''
		    		    },
		    		    dataType : "json",
		    		    type : "POST",
		    		    async : false,
		    		    cache : false,
		    		    success : function(data) {
		    		    	if (data != null && data.loginVo.length != 0) {
		    		    		//로그인성공
		    		    		location.replace("/home");
		    		    	} else {
		    		    		//로그인실패
		    		    		location.replace("/");
		    		    	}
		    		    }
		    		});
	    		}
	    		
	    	}
	    }
	});
}


/**
 * @함수명: fnLogin
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 로그인 체크
 */
function fnLogin() {
	var em_id = $.trim($("#em_id").val());
	var em_password = $.trim($("#em_password").val());
	var auth_flag = $.trim($("#auth_flag_login").val());
	var cm_code = $.trim($("#cm_code_login").val());
	login_cp_cd = "${sessionScope.login_cp_cd}";
	//alert("${sessionScope.login_cp_cd}");
	
	if(cm_code == ""){
	      alert("회사를 선택해주세요.");
	      $("#cm_code_login").focus();
	      return;
	   }
	
	$("#em_id").val("");
	$("#em_password").val("");
	//alert("로그인이 정상적으로 이루어졌습니다.");
	$.ajax({
		url : "/login",
	    data : {
	    	em_id : em_id,
	    	em_password : em_password,
	    	auth_flag : auth_flag,
	    	cm_code : cm_code
	    },
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	if (data != null && data.loginVo.length != 0) {
	    		//로그인성공
	    		location.replace("/home");
	    	} else {
	    		//로그인실패
	    		location.replace("/");
	    	}
	    }
	});
}


function cbo_cm_login(){
	$.ajax({
		url : "/company/companyList",
		/*data:{"cm_code":cm_code},*/
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
		var listHtml = "<option value='' selected='selected'>선택</option>";
		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var cmVo = data.result[i];     
        		listHtml += "<option value='"+cmVo.cm_code+"'>"+cmVo.cm_nm + "</option>";
        	}
		}else{
			
		}	
		$("#cm_code_login").html(listHtml);
	    
	    }
	});
}

/**
 * 쿠키값
 * @param name
 * @return
 */
function getCookie( name )
{
  var nameOfCookie = name + "=";
  var x = 0;
  while ( x <= document.cookie.length )
  {
    var y = (x+nameOfCookie.length);
    if ( document.cookie.substring( x, y ) == nameOfCookie )
    {
      if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
        endOfCookie = document.cookie.length;
      return unescape( document.cookie.substring( y, endOfCookie ) );
    }
    x = document.cookie.indexOf( " ", x ) + 1;
    if ( x == 0 )
      break;
  }
  return "";
}

/**
 * 쿠키 값 세팅
 * @param name
 * @param value
 * @param addDays
 * @param addHours
 * @param addMinutes
 * @param addSeconds
 * @return
 */
function setCookie( name, value, addDays, addHours, addMinutes, addSeconds)
{
  var todayDate = new Date();
  todayDate.setDate   ( todayDate.getDate()     + (addDays || 0));
  todayDate.setHours  ( todayDate.getHours()    + (addHours || 0));
  todayDate.setMinutes( todayDate.getMinutes()  + (addMinutes || 0));
  todayDate.setSeconds( todayDate.getSeconds()  + (addSeconds || 0));
  document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}
