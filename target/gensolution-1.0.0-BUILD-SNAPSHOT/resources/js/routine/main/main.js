/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	fnMakeEventComponent();	// 버튼 이벤트 등록
//	fnGetTestList("파라메터 잘넘어갑니다...");
	
		
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
	$("#menuList").sTree({
		url:"/menu/list",
		root:true,
		key:"m_no",
		val:"m_nm",
		className:"mtree",
		data:{"user_id":"123"},
		click:  function(btn){
			console.log("data3 : " + btn.code)
		}
    });
//	menucall();
//	fnGetMenuList("1111111111");
//	fnGetMenuListNew("1111111111");
//	$sTree({
//		target : "menuList",
//		success:  function(data){
//			alert(data);
//		}
//    });
});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent(){
	// 팝업창 닫기
	$("#closeBtn").click(function(){
	});
	
	// 저장 버튼
	$("#saveBtn").click(function(){
	});
}


function fnGetMenuList(user_id){
	$.ajax({
		url : "/menu/list",
	    data:{"user_id":user_id},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(data) {
	    	
	    	var flag = true;
	    	var html = '<ul class="mtree transit">';
	    	for (var i = 0; i < data.result.length; i++) {
	    		if(data.result[i].m_depth == 1){
	    			html += "<li>";
	    			html += '<a href="#" >'+data.result[i].m_nm+'</a>';
	    			for (var f = 0; f < data.result.length; f++) {
	    				if(data.result[f].m_depth != 1 && data.result[i].m_no === data.result[f].m_parent_no){
	    					if(flag){
	    						html += "<ul>";
	    					}
	    					flag = false;
	    					html += '<li><a href="#">'+data.result[f].m_nm+'</a></li>';
	    	    		}
	    				
	    				if(!flag && f == data.result.length -1){
	    					html += "</ul>";
	    				}
	    			}
	    			flag = true;
	    			html += "</li>";
	    		}
    		
			}
	    	html += "</ul>";
	    	$("#menuList").html(html);
	    	setMtree();
	    }
	});
	
}


(function($){
$.sTree = function(option){
	alert("1");
	var op = $.extend({
		url: false,
		data: "9999",
		dataType: "0px",
		target: "0px",
		group: false,
		success : null
	}, option );
	
	
	$.ajax({
		url : "/menu/list",
		data:{"user_id":user_id},
		type : "POST",
		dataType : "json",
		success : op.fn
//		success : function(data) {
//			
//			var tempDepth = 1;
//			var html = '<ul class="mtree transit">';
//			for (var i = 0; i < data.result.length; i++) {
//				var depth = data.result[i].m_depth;
//				if(tempDepth == depth){
//					html += '<li><a href="#">'+data.result[i].m_nm+'</a>';
//					tempDepth = depth;
//				}else if(tempDepth < depth){
//					html += "<ul>";
//					html += '<li><a href="#">'+data.result[i].m_nm+'</a>';
//					tempDepth = depth;
//				}else{
//					var gap = tempDepth - depth;
//					for (var f = 0; f < gap; f++) {
//						html += "</li></ul>";
//					}
//					html += '<li><a href="#">'+data.result[i].m_nm+'</a>';
//					tempDepth = (tempDepth - gap);
//				}
//				
//			}
//			var gap2 = tempDepth -1;
//			for (var g = 0; g < gap2; g++) {
//				html += "</li></ul>";
//			}
//			html += "</li></ul>";
//			$("#menuList").html(html);
//			setMtree();
//		}
	});
	
};


})(jQuery);

function fnGetMenuListNew(user_id){
	$.ajax({
		url : "/menu/list",
		data:{"user_id":user_id},
		type : "POST",
		dataType : "json",
		success : function(data) {
			
			var tempDepth = 1;
			var html = '<ul class="mtree transit">';
			for (var i = 0; i < data.result.length; i++) {
				var depth = data.result[i].m_depth;
				if(tempDepth == depth){
					html += '<li><a href="#">'+data.result[i].m_nm+'</a>';
					tempDepth = depth;
				}else if(tempDepth < depth){
					html += "<ul>";
					html += '<li><a href="#">'+data.result[i].m_nm+'</a>';
					tempDepth = depth;
				}else{
					var gap = tempDepth - depth;
					for (var f = 0; f < gap; f++) {
						html += "</li></ul>";
					}
					html += '<li><a href="#">'+data.result[i].m_nm+'</a>';
					tempDepth = (tempDepth - gap);
				}
				
			}
			var gap2 = tempDepth -1;
			for (var g = 0; g < gap2; g++) {
				html += "</li></ul>";
			}
			html += "</li></ul>";
			$("#menuList").html(html);
			setMtree();
		}
	});
	
}




function fnGetTestList(param){
	$.ajax({
		url : "/data/test",
	    data:{"param":param},
	    type : "POST",
	    dataType : "json",
	    cache : false ,
	    success : function(result) {
			var html = "";
			for (var i=0; i <result.length ; i++){
				var corpno = result[i].corpno;
				var gubnno = result[i].gubnno;
				var gubnname = result[i].gubnname;
				var codelevel = result[i].codelevel;
				var highcode = result[i].highcode;
				var bigo = result[i].bigo;
				var delyn = result[i].delyn;
				var useyn = result[i].useyn;
				var regdate = result[i].regdate;
				var regemp = result[i].regemp;
				var editdate = result[i].editdate;
				var editemp = result[i].editemp;
				
				html += '<tr>';
				html += '<td>'+(i+1)+'</td>';
				html += '<td>'+corpno+'</td>';
				html += '<td>'+gubnno+'</td>';
				html += '<td>'+gubnname+'</td>';
				html += '<td>'+codelevel+'</td>';
				html += '<td>'+highcode+'</td>';
				html += '<td>'+bigo+'</td>';
				html += '<td>'+delyn+'</td>';
				html += '<td>'+useyn+'</td>';
				html += '<td>'+regdate+'</td>';
				html += '<td>'+regemp+'</td>';
				html += '<td>'+editdate+'</td>';
				html += '<td>'+editemp+'</td>';
				html += '</tr>';
			}
			$("#testTbody").html(html);
			
	    }
	});
}
