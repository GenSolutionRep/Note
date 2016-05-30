function checkAuth(m_no){
	$.ajax({
		url : "/auth/thisMenuAuth",
		data:{"m_no":m_no},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
			var authArr = ["auth-insert","auth-update","auth-del","auth-down","auth-etc"];     
	        var ma_insert  	= data.ma_insert;        
	        var ma_update  	= data.ma_update; 
	        var ma_del     	= data.ma_del;    
	       	var ma_down    	= data.ma_down;
	       	var ma_etc    	= data.ma_etc;
	       	console.log("등록 : "+ma_insert +", 수정 : "+ ma_update +", 삭제 : "+ ma_del +", 다운 : "+ ma_down +", 기타 : "+ ma_etc);
	       	var authVal = {
	       		"auth-insert" : ma_insert,
	       		"auth-update" : ma_update,
	       		"auth-del" : ma_del,
	       		"auth-down" : ma_down,
	       		"auth-etc" : ma_etc
       		};
	       	var tagetTab = $("#tabs .ui-tabs-panel:visible");
//	       	$("#tabs .ui-tabs-panel:visible").html()
//	       	alert(tagetTab.html());
	       	for (var i = 0; i < authArr.length; i++) {
	       		if(authVal[authArr[i]] == "N"){
	       			console.log("삭제 : "+authArr[i]);
	       			tagetTab.find("."+authArr[i]).remove();
	       		}
			}
		       	
	    
	    }
	});
}