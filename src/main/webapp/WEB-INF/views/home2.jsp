<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ include file="./inc/common.jsp"%>
<%
String loginName = (String) request.getSession().getAttribute("login_nm");
%>
<!DOCTYPE html>

<html lang="ko">

	<head>
		<title>PAMS</title>
		<link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<jsp:include page="./inc/header.jsp" flush='true' />
		<link rel="shortcut icon" href="파비콘 주소">
		<script type="text/javascript" src="<%=jsPath%>/home.js"></script>
		<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=3b154ec13945d98f6f1c52a9922d8373"></script>
		<script type="text/javascript" src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1.1','packages':['corechart']}]}"></script>
				<script type="text/javascript">
// 		$(window).on("beforeunload", function(){
// 	        if(check){
// 		        return "이 페이지를 벗어나면 모든 내용이초기화 됩니다.";
// 		    }
// 		});
		$(document).keydown(function(e){
			if(e.keyCode === 116){
				$("#layerPopSpace > div").hide();
	    		var tabName = $('.ui-state-default.ui-corner-top.stHasCloseBtn.ui-tabs-selected > a > span').text();
		    	if(tabName != "Home"){
		    		var target = $('#tabs').find('li.ui-tabs-selected');
// 		    		var index = target.index()-1;
// 		    		alert("텝위치 : "+index);
// 		    		alert("열린텝수 : "+tabsOnIndex.length);
		    		var tabMenuData = tabsOnIndex[tabName];
// 		    		alert("title : "+tabMenuData.title);
// 		    		alert("url : "+tabMenuData.url);
		    		target.find(".ui-icon.ui-icon-circle-close").trigger("click");
// 	    			tabsOnIndex.splice(index, 1);
					tabAdds(tabMenuData.title, tabMenuData.url);
// 					$('#tabs').find('li.ui-tabs-selected').insertAfter($('#tabs').find('.ui-state-default .ui-corner-top').eq(index));
		    	}else{
		    		  	google.setOnLoadCallback(fnChart_10500);
		    			
		    			//금일정보
		    			$("#title10500").text(fnGetToday10500());
		    			
		    			//공지사항
		    			fnGetNoticeList_10500(1);
		    			
		    			//주요행사
		    			fnGetEventList_10500();
		    	}
		      	return false;
			}else if((e.target.nodeName != "INPUT" && e.target.nodeName != "TEXTAREA")){       
		    	if(e.keyCode === 8){   
		        	return false;
		        }
		    }else{
		    }
		 });
		</script>	
	</head>

	<body id="topBody" >
		<input type='hidden' id='inputHiddenTitle'>	
		<input type='hidden' id='inputHiddenMenuValue' value="1">
		<div id="layerPopSpace" ></div>
		<div class="wrap">
			<aside id="aside">
				<h1 class="logo" ><a href="#" id="jsonTest"><img  src="<%=imgPath %>/pams_logo.png"></a></h1>
				<section>
					<h1 class="aside_tit">활동관리</h1>
					<nav id="leftMenu">
						<ul class="aside_nav"></ul>
					</nav>
				</section>
			</aside>
			<article class="container" id="container">
				<header>
					<nav id="topMenu">
						<ul>
							<!-- <li class="on">
								<a href="#" title="" onClick="fn_openMenu(2);">활동관리</a>
							</li>
							<li>
								<a href="#" title="" onClick="fn_openMenu(5);">진열현황</a>
							</li>
							<li>
								<a href="#" title="" onClick="fn_openMenu(8);">전자결재</a>
							</li>
							<li>
								<a href="#" title="" onClick="fn_openMenu(11);">거래처관리</a>
							</li>
							<li>
								<a href="#" title="" onClick="fn_openMenu(14);">사원관리</a>
							</li>
							<li>
								<a href="#" title="" onClick="fn_openMenu(16);">커뮤니케이션</a>
							</li>
							<li>
								<a href="#" title="" onClick="fn_openMenu(21);">기준정보</a>
							</li> -->
						</ul>
<%-- 						<div id="headerUser"><a id="logoutBtn" href="#">(<%=loginName%>님) 로그아웃</a></div> --%>
					</nav>
				</header>
				<article class="content" id="content">
					<span class="handle" id="handle">슬라이드 됩니다</span>
				<div id="tabs">
					<ul>
					<!-- <li><a href="/50/50-100/00001">Home</a></li> -->
 				    	<li><a href="/10/10-500"><span>Home</span></a></li>
 				    	<!-- <li><a href="/20/20-200">Home</a></li> -->
				  	</ul>
		    	</div>
				</article>
				<footer>
					<small>Copyright © 2015 (주)다사마케팅. All rights reserved.</small>
				</footer>
			</article>

		</div>
		<div id="quickTab" class="bottom-menu" >
		</div>
		
        <!-- 레이어 팝업 시작 (매장지도보기 by zzz2613) -->
        <div id="storeMapPop" class="pop-apn-pop" style="display:none;">
            <div class="popup" style="width: 1100px;">
                <div class="con" id="storeMap" style="padding:0; width:1100px; height:500px; border:solid blue;"></div>
                <div class="pfooter">
                    <button id="storeMapClose" type="button" class="white">닫기</button>
                </div>
            </div>
            <div class="popup_overlay"></div>
        </div>
        <!-- 레이어 팝업 종료 -->
			
	</body>
</html>


