/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	tabsOnIndex["Home"]={"m_no" :'',"url" :"/10/100-500","title" :"Home"};
	$("#handle").click(function(){
		if($("#aside").width() == 0){
			$("#aside").animate({"width":"160"});
			$("#container").animate({"margin-left":"160"});
			$("#headerUser").animate({"right":"160px"});
		}else{
			$("#aside").animate({"width":"0"});
			$("#container").animate({"margin-left":"0"});
			$("#headerUser").animate({"right":"0px"});
		}
	});
	
	fnCreateTopMenu();		//상위 메뉴 생성
	
	fnMakeEventComponent();	// 버튼 이벤트 등록

	fnCreateLeftMenu("1"); // 왼쪽 메뉴 생성
	
//	$("#jsonTest").click(function() {
//	var popWidth = 1250;
//	var popHeight = 900;
//	var params  = 'width='+popWidth;
//	   params += ', height='+popHeight;
//	   params += ', top=50, left='+((screen.width-popWidth)/2);
//	   params += ', toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no';
//
//	  window.open('jsonTest',"", params);
//	});
		
	//매장지도보기 by zzz2613
	$("#storeMapPop").instancePopUp();
	$("#storeMapClose").click(function(){
		$("#storeMapPop").hide();
	});
	
});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent(){
	
	$("#logoutBtn").click(function() {
		check = false;
		location.replace("/logout");
	});
	
	// 팝업창 닫기
	$("#closeBtn").click(function(){
	});
	
	// 저장 버튼
	$("#saveBtn").click(function(){
	});
	
	/*$("#leftMenu").leftMenu({
		url:"/menu/list",
		root:false,
		key:"m_no",
		val:"m_nm",
		className:"aside_nav",
		data:{"user_id":"123", "m_use_yn" : "Y"},
		click:function(btn){
			if(btn.m_url != ""){
				tabAdds(btn.m_nm, btn.m_url);
				var tempLiList = $("#leftMenu li");
				
				for(var i=0; i<tempLiList.length; i++){
					var tempLi = tempLiList[i];
					
					if($(tempLi).children("ul").length == 0){
						$(tempLi).removeClass("selected");
					}
				}
	
				btn.node.parent("li").addClass("selected");
			}else{
				if(btn.node.next().is(":visible")){
					btn.node.next().slideUp(function(){
						btn.node.children("i").removeClass("up");
					});
				}else{
					btn.node.next().slideDown(function(){
						btn.node.children("i").addClass("up");
					});
				}
				$("li").removeClass("selected");
				btn.node.parent("li").addClass("selected");
			}
		},
    });*/
	
}


/*function fnCreateLeftMenuOld(m_no){
	$.ajax({
		url : "/menu/menuInit",
		data : {"m_no":$('#inputHiddenMenuValue').val()},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {	
			var tempDepth = 1;
			var html = '<ul class="aside_nav">';
			for (var i = 0; i < data.result.length; i++) {
				var depth = data.result[i].m_depth;
				if(tempDepth == depth){
					html += '<li><a href="#" class="home-left" data-m_url="'+data.result[i].m_url+'" data-m_nm="'+data.result[i].m_nm+'" >'+data.result[i].m_nm+'</a>';
					tempDepth = depth;
				}else if(tempDepth  < depth){
					html += "<ul>";
					html += '<li><a href="#" class="home-left" data-m_url="'+data.result[i].m_url+'" data-m_nm="'+data.result[i].m_nm+'" >'+data.result[i].m_nm+'</a>';
					tempDepth = depth;
				}else{
					var gap = tempDepth - depth;
					for (var f = 0; f < gap; f++) {
						html += "</li ></ul>";
					}
					html += '<li><a href="#" class="home-left" data-m_url="'+data.result[i].m_url+'" data-m_nm="'+data.result[i].m_nm+'" >'+data.result[i].m_nm+'</a>';
					tempDepth = (tempDepth - gap);
				}
				
			}
			html += "</li></ul>";
			
			$("#leftMenu").html(html);
			
			$("#leftMenu").find(".home-left").click(function(){
				fn_openPage($(this).data("m_nm"), $(this).data("m_url"));
			});
			
			fnMoveMainpage($('#inputHiddenMenuValue').val());
		}
	});
}*/

function fnCreateLeftMenu(m_no){
	$.ajax({
		url : "/menu/menuInit",
		data : {"m_no":$('#inputHiddenMenuValue').val()},
		type : "POST",
		dataType : "json",
		cache : false ,
		success : function(data) {	
			$("#leftMenu > ul.aside_nav").html("");
			var targetMenuEl;
			for (var i = 0; i < data.result.length; i++) {
				var html = '';
				var depth = data.result[i].m_depth;
				var sub = "";
				if(depth == 1){
					targetMenuEl = $("#leftMenu .aside_nav");
				}else{
					targetMenuEl = $("#leftMenu").find(".menuId"+data.result[i].m_parent_no);
					sub = "sub";
				}
				html += '<li class="menuId'+data.result[i].m_no+' '+sub+data.result[i].m_no+'" ><a href="#" class="home-left" data-m_url="'+data.result[i].m_url+'"  data-m_no="'+data.result[i].m_no+'"  data-m_nm="'+data.result[i].m_nm+'" >'+data.result[i].m_nm+'</a></li>';
				
				if(targetMenuEl.is( "li" ) &&  targetMenuEl.children("ul").length == 0){
					html = '<ul id="left_'+data.result[i].m_no+'" style=display:none>'+html+'</ul>';
					targetMenuEl.append(html);
				}else{
					if(depth == 1){
						var $html2 = $(html);
						$html2.find("a.home-left").append($("<i class='fa fa-caret-right' />"));
						targetMenuEl.append($html2);
					}else{
						if(targetMenuEl.find("ul li").length  == 1){
							targetMenuEl.children("a.home-left").append($("<i class='fa fa-caret-right' />"));
						}
						targetMenuEl.children("ul").append(html);
					}
				}
			}
			
			$("#left_45").css('display','block');
			
			$("#leftMenu").find(".home-left").click(function(){
				if($(this).data("m_url") != ""){
					fn_openPage($(this).data("m_nm"), $(this).data("m_url"), $(this).data("m_no"));
					var tempLiList = $("#leftMenu li");
					for(var i=0; i<tempLiList.length; i++){
						var tempLi = tempLiList[i];
						
						if($(tempLi).children("ul").length == 0){
							$(tempLi).removeClass("selected");
						}
					}
					$(this).parent("li").addClass("selected");
				}else{
//					showSub($(this).data("m_no"));
					if($(this).next().is(":visible")){
						$(this).next().slideUp(function(){
							$(this).children("i").removeClass("up");
						});
					}else{
						$(this).parent().nextAll().children("li ul").slideUp();
				        $(this).parent().prevAll().children("li ul").slideUp();
						$(this).next("ul").slideDown(function(){
							$(this).children("i").addClass("up");
						});
					}
					$("li").removeClass("selected");
					$(this).parent("li").addClass("selected");
				}
			});
			
		}
	});
}


function fnMoveMainpage(val) {
	$.ajax({
		url : "/menu/menuInit",
		data:{"m_no": val},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	var url = "";
	    	var depth = "";
	    	var nm = "";
	    	for (var i = 0; i < data.result.length; i++) {
	    		depth = data.result[i].m_depth;
	    		url = data.result[i].m_url;
	    		nm = data.result[i].m_nm;
	    		mno = data.result[i].m_no;
	    		if(url == null) continue;
	    		if((depth != '1') && (url != null) && (url != "")){
	    			break;
	    		}
	    		
	    	}
	    	$("#inputHiddenTitle").val(nm);
	    	tabAdds(nm, url,mno);
	    }
	}); 
}

function fnCreateTopMenu() {
	$.ajax({
		url : "/menu/topInit",
		data:{"m_use_yn": "Y"},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    var html = '<ul>';
		for(var i=0; i<data.result.length; i++){
			html += '<li><a href="#" class="home-view" data-m_no="'+data.result[i].m_no+'" >'+data.result[i].m_nm+'</a>';
    	}
		html += '</li></ul>';
		/*html += '<div id="headerUser"><a id="logoutBtn" href="#">('+data.login_nm+'님) 로그아웃</a></div>';
		if(auth_flag=='0'||auth_flag=='1'){
			html += '<div >';
			html += '	<select id="cm_code_change" style="width: 100px;"> ';
			html += '	</select> ';
			html += '</div>';
		}*/
		if(auth_flag=='0'){
			html += '<div id="headerUser">';
			
			html += '	<select id="cm_code_change" style="width: 150px; margin-left: 20px;"> ';
			html += '	</select> ';	
			html += '	<div style="';
			html += '    	position: initial;';
			html += '    	display: inline-block;';
			html += '    	padding: 0 10px;';
			html += '    	border-left: 0;';
			html += '    	width: 10px;';
			html += '    	border-right: 1px solid #DEDEDE;"';
			html += '	>';	
			html += '	</div>';
			html += '<a id="logoutBtn" href="#">('+data.login_nm+'님) 로그아웃</a></div>';
		}else{
			html += '<div id="headerUser"><a id="logoutBtn" href="#">('+data.login_nm+'님) 로그아웃</a></div>';
			/*html += '<div >';
			html += '	<select id="cm_code_change" style="width: 100px;"> ';
			html += '	</select> ';
			html += '</div>';*/
		}

		
		$("#topMenu").html(html);
		
		if(auth_flag=='0'){
			$.ajax({
				url : "/company/companyList",
				/*data:{"cm_code":cm_code},*/
			    type : "POST",
			    dataType : "json",
			    cache : false,
			    async : false,
			    success : function(data) {
					var listHtml = '';
					if(data!=null && data.result.length > 0){
						for(var i=0; i<data.result.length; i++){
							var cmVo = data.result[i];     
			        		listHtml += "<option value='"+cmVo.cm_code+"'>"+cmVo.cm_nm + "</option>";
			        	}
					}
					$("#cm_code_change").html(listHtml);
					//alert(login_cp_cd);
					$("#cm_code_change").val(login_cp_cd);
			    }
			});
			
			$("#cm_code_change").change(function(){
				$.ajax({
					url : "/login",
				    data : {
				    	em_no : login_no,
				    	em_password : '',
				    	auth_flag : auth_flag,
				    	cm_code : $(this).val()
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
			});
		}
		
		
		$("#topMenu").find(".home-view").click(function(){
			//fn_openMenu($(this).data("m_no"));
			//fn_display($(this).data("m_no"));
			showSub($(this).data("m_no"));
		});
		
	    }
	});
}

function showSub(id){
   if(!$("."+id).children("ul").is(":visible")){
	   $("."+id).nextAll().children("ul").slideUp();
	   $("."+id).prevAll().children("ul").slideUp();
      $("."+id).children("ul").slideDown();
   }else{
	   $("."+id).children("ul").slideUp();
   }
}

function fn_display(val) {
	if(val == '2'){							//활동관리
		$("#45").css('display','block');
		$("#61").css('display','none');
		$("#66").css('display','none');
		$("#40").css('display','none');
		$("#41").css('display','none');
		$("#17").css('display','none');
		$("#22").css('display','none');
	}else if(val=='5'){ 					//진열현황
		$("#45").css('display','none');
		$("#61").css('display','none');
		$("#66").css('display','none');
		$("#40").css('display','none');
		$("#41").css('display','none');
		$("#17").css('display','none');
		$("#22").css('display','none');
	}else if(val == '8') {					//전자결재
		$("#45").css('display','none');
		$("#61").css('display','none');
		$("#66").css('display','block');
		$("#9").css('display','block');
		$("#10").css('display','block');
		$("#40").css('display','none');
		$("#41").css('display','none');
		$("#17").css('display','none');
		$("#22").css('display','none');
	}else if(val == '11') {					//거래처관리
		//$("#").css('display','block');
	}else if(val == '46') {					//매장관리
		$("#45").css('display','none');
		$("#61").css('display','block');
		$("#66").css('display','none');
		$("#40").css('display','none');
		$("#41").css('display','none');
		$("#17").css('display','none');
		$("#22").css('display','none');
	}else if(val == '14') {					//사원관리
		$("#45").css('display','none');
		$("#61").css('display','none');
		$("#66").css('display','none');
		$("#40").css('display','block');
		$("#41").css('display','block');
		$("#17").css('display','none');
		$("#22").css('display','none');
	}else if(val == '16') {					//커뮤니케이션
		$("#45").css('display','none');
		$("#61").css('display','none');
		$("#66").css('display','none');
		$("#40").css('display','none');
		$("#41").css('display','none');
		$("#17").css('display','block');
		$("#22").css('display','none');
	}else if(val == '21') {					//기준정보
		$("#22").css('display','block');
		$("#45").css('display','none');
		$("#61").css('display','none');
		$("#66").css('display','none');
		$("#40").css('display','none');
		$("#41").css('display','none');
		$("#17").css('display','none');
	}	
	
	
}

// 탑 클릭
function fn_openMenu(val) {
	$('#inputHiddenMenuValue').val(val); 
	fnCreateLeftMenu(); // 레프트 메뉴 생성
	fnMoveMainpage($('#inputHiddenMenuValue').val());
}

//레프트 클릭
function fn_openPage(m_nm, m_url,m_no) {
	$("#inputHiddenTitle").val(m_nm);
	tabAdds(m_nm, m_url,m_no);
//	fnMoveMainpage($('#inputHiddenMenuValue').val());
}

/**
 * @함수명: fnViewStoreMap
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 매장지도보기
 */
function fnViewStoreMap(locations) {
	
    // 기본 지도 표시
    var map = new daum.maps.Map(document.getElementById("storeMap"), {
        center: new daum.maps.LatLng(locations[0][0], locations[0][1]),
        level: 8,
        mapTypeId: daum.maps.MapTypeId.ROADMAP
    });

    // 지도 콘트롤 표시
    var zoomControl = new daum.maps.ZoomControl();
    map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
    var mapTypeControl = new daum.maps.MapTypeControl();
    map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

    // 다중 마커와 인포윈도우 표시

    //위치 정보와 인포윈도우에 표시할 정도
    /*var locations = [[37.5008270, 127.0268110,'강남점'],
                     [37.4969260,127.0532730,'2번'],
                     [37.4959960,127.0290570,'3번'],
                     [37.4969260,127.0532730,'4번']];*/
    
    for(var i = 0; i < locations.length; i++) {
        // 다중 마커
        var marker = new daum.maps.Marker({
            position: new daum.maps.LatLng(locations[i][0], locations[i][1])
        });
        marker.setMap(map);

        //인포 윈도우
        daum.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
                var infowindow = new daum.maps.InfoWindow({
                    content: '<p style="margin:7px 22px 7px 12px;font:12px/1.5 sans-serif">' + locations[i][2] + '</p>',
                    removable : true
                });
              infowindow.open(map, marker);
            }
        })(marker, i));
    }
}
