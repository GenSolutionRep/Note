
var pg_20200 = 1;
var pg_all_20200 = 1;
var arr20200;
/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	fnGetCollecItemList();
	
	$("#displayGroupSave20300").click(function(){
		var d_innb = $("#prdGroup20300").data("group-id");
		var checkGroupArr = $("#prdGroup20300").find(".check-group");
//		alert(d_innb);
//		alert(checkGroupArr.length);
		var prdArr = new Array();
		for (var i = 0; i < checkGroupArr.length; i++) {
			var checkGroup = checkGroupArr.eq(i);
			var isCheckd = checkGroup.is(":checked") == true ? "Y":"N";
			var nickNm = checkGroup.data("nick-nm");
			var dpInnb = checkGroup.val();
//			console.log("isCheckd : " + isCheckd);
//			console.log("nickNm : " + nickNm);
//			console.log("dpInnb : " + dpInnb);
			prdArr.push({
				"oi_nick_name"	:	nickNm,
				"collect_at"	:	isCheckd,
				"d_innb"		:	d_innb,
				"dp_innb"		:	dpInnb
			});
		}
		
		var saveData = {
				 "d_innb":d_innb
				,"prdArr":prdArr
		};
		$.ajax({
			url : "/display/prdsave"+d_innb,
			type : "POST",
		    data : saveData,
			dataType : "json",
			global:true,
			cache : false,
			success : function(data) {
	    		if (data != 0) {
	    			alert("저장되었습니다.");
	    			var selectRow = $('#displayTbody20300 .cell_active').find(".display-select");
					var fixingCode = selectRow.attr("data-fixing-code");
					var rndCode = selectRow.attr("data-rnd-code");
					var fixingChildCode = selectRow.attr("data-fixing-child-code");
					var rndChildCode = selectRow.attr("data-rnd-child-code");
					fnGetCollecPrdList(d_innb , fixingCode, rndCode, fixingChildCode, rndChildCode );
	    		} else {
	    			alert("저장에 실패하였습니다.");
	    		}
		    }
		});
	});
	$("#displayGroupItemSave20300").click(function(){
		var dpInnb = $("#prdItemGroup20300").data("group-id");
//		alert("dpInnb : " + dpInnb);
		var checkGroupItemArr = $("#prdItemGroup20300").find(".check-group");
		var prdArr = new Array();
		for (var i = 0; i < checkGroupItemArr.length; i++) {
			var checkGroup = checkGroupItemArr.eq(i);
			var isCheckd = checkGroup.is(":checked") == true ? "Y":"N";
			var nickNm = checkGroup.data("nick-nm");
			var dpiInnb = checkGroup.val();
//			console.log("isCheckd : " + isCheckd);
//			console.log("nickNm : " + nickNm);
//			console.log("dpiInnb : " + dpiInnb);
			prdArr.push({
				"oi_nick_name"	:	nickNm,
				"collect_at"	:	isCheckd,
				"dp_innb"		:	dpInnb,
				"dpi_innb"		:	dpiInnb
			});
		}
//		alert(dpInnb);
//		alert(checkGroupItemArr.length);
//		return;
		 
		var colunm = $("#prdGroup20300").find(".cell_active .nick-group").text();
		var d_innb = $("#prdGroup20300").data("group-id");
		var saveData = {
				"dp_innb":dpInnb
				,"d_innb":d_innb
				,"colunm":colunm
				,"prdArr":prdArr
		};
		$.ajax({
			url : "/display/prditemsave"+d_innb,
			type : "POST",
			data : saveData,
			dataType : "json",
			global:true,
			cache : false,
			success : function(data) {
				if (data != 0) {
					alert("저장되었습니다.");
					$('#displayPrdItemTbody20300').html("");
					
					var selectRow = $('#displayPrdTbody20300 .cell_active').find(".display-select");
					var fixingChildCode = selectRow.attr("data-fixing-child-code");
					var rndChildCode = selectRow.attr("data-rnd-child-code");
					fnGetCollecPrdItemList(dpInnb,fixingChildCode,rndChildCode);
					
				} else {
					alert("저장에 실패하였습니다.");
				}
			}
		});
	});
});
function fnGetCollecItemList(){
	$.ajax({
		url : "/display/list",
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	var html = "<ul>";
			if( data.length > 0){
	    		for(var i=0; i<data.length; i++){
	    			var dpVo = data[i];     
					html +='<li>';
					html +='	<div class="flex1">'+dpVo.d_name+'</div>';
					html +='	<div class="flex_100">	';
					html +='		<button type="button" class="white display-select" ';
					html +='			data-fixing-code="'+dpVo.fixing_code+'" ';
					html +='			data-rnd-code="'+dpVo.rnd_code+'" ';
					html +='			data-fixing-child-code="'+dpVo.fixing_child_code+'" ';
					html +='			data-rnd-child-code="'+dpVo.rnd_child_code+'" ';
					html +='			value="'+dpVo.d_innb+'">선택</button>';
					html +='	</div>';
					html +='</li>';
	    		}
			}
			html +='<ul>';
			$('#displayTbody20300').html(html);
			$('#displayTbody20300').find(".display-select").click(function(){
				$(this).parent().parent().parent().find("li").removeClass("cell_active");
				$(this).parent().parent().addClass("cell_active");
				var fixingCode = $(this).attr("data-fixing-code");
				var rndCode = $(this).attr("data-rnd-code");
				var fixingChildCode = $(this).attr("data-fixing-child-code");
				var rndChildCode = $(this).attr("data-rnd-child-code");
				fnGetCollecPrdList($(this).val(), fixingCode, rndCode, fixingChildCode, rndChildCode );
				$('#displayPrdItemTbody20300').html("");
			});
	    }
	});
}
function fnGetCollecPrdList(d_innb, fixingCode, rndCode, fixingChildCode, rndChildCode){
//	alert(fixingCode+"\n"+rndCode+"\n"+ fixingChildCode+"\n"+rndChildCode);
	$.ajax({
		url : "/display/prdlist",
		type : "POST",
		data : {
				"d_innb":d_innb,
				"fixing_code":fixingCode,
				"rnd_code":rndCode
			},
		dataType : "json",
//		async: false,
		global:true,
		cache : false ,
		success : function(data) {
			var html = "<ul data-group-id='"+d_innb+"' id='prdGroup20300' >";
			if( data.length > 0){
				for(var i=0; i<data.length; i++){
					var dpVo = data[i];     
					html +='<li>';
					html +='	<div class="flex2">'+dpVo.oi_nm+'</div>';
					html +='	<div class="flex2 nick-group">'+dpVo.oi_nick_nm+'</div>';
					html +='	<div class="flex1">'+dpVo.oi_sort_ordr+'</div>';
					html +='	<div class="flex1">';
					var collect_at = dpVo.collect_at;
					html +='		<div class="inputCheck" style="border-bottom: none;">';		
					html +='			<input type="checkbox" value="'+dpVo.dp_innb+'" data-nick-nm="'+dpVo.oi_nick_nm+'" class="check-group" id="prdCheck'+i+'" '+(collect_at == "Y" ? 'checked':'') + '>';		
					html +='			<label for="prdCheck'+i+'" style="margin-top: 9px;" ></label>';
					html +='		</div>';
					html +='	</div>';
					html +='	<div class="flex_100">';	
					if(collect_at == "Y" && fixingChildCode != ""){
						html +='		<button type="button" class="white display-select" ';
						html +='			data-fixing-child-code="'+fixingChildCode+'" ';
						html +='			data-rnd-child-code="'+rndChildCode+'" ';
						html +='			value="'+dpVo.dp_innb+'">선택</button>';
					}else{
						html +='		<button type="button" class="disabled" >선택</button>';
					}
					html +='	</div>';
					html +='</li>';
				}
			}
			
			html +='<ul>';
			$('#displayPrdTbody20300').html(html);
			$('#displayPrdTbody20300').find(".display-select").click(function(){
//				if($(this).val() == ""){
//					alert("신규 등록된 항목입니다.\n저장후 선택해주세요.");
//					return;
//				}
				$(this).parent().parent().parent().find("li").removeClass("cell_active");
				$(this).parent().parent().addClass("cell_active");
				var fixingChildCode = $(this).attr("data-fixing-child-code");
				var rndChildCode = $(this).attr("data-rnd-child-code");
				fnGetCollecPrdItemList($(this).val(),fixingChildCode,rndChildCode)
			});
		}
	});
}
function fnGetCollecPrdItemList(dp_innb, fixingChildCode, rndChildCode){
	$.ajax({
		url : "/display/prditemlist",
		type : "POST",
		data : {
			"dp_innb":dp_innb,
			"fixing_code":fixingChildCode,
			"rnd_code":rndChildCode
		},
		dataType : "json",
		global:true,
		cache : false ,
		success : function(data) {
			var html = "<ul data-group-id='"+dp_innb+"' id='prdItemGroup20300' >";
			if( data.length > 0){
				for(var i=0; i<data.length; i++){
					var dpVo = data[i];     
					html +='<li>';
					html +='	<div class="flex1">'+dpVo.oi_nm+'</div>';
					html +='	<div class="flex1">'+dpVo.oi_nick_nm+'</div>';
					html +='	<div class="flex1">'+dpVo.oi_sort_ordr+'</div>';
					html +='	<div class="flex1">';
					var collect_at = dpVo.collect_at;
					html +='		<div class="inputCheck" style="border-bottom: none;">';		
					html +='			<input type="checkbox" value="'+dpVo.dpi_innb+'" data-nick-nm="'+dpVo.oi_nick_nm+'" class="check-group"  id="prdItemCheck'+i+'" '+(collect_at == "Y" ? 'checked':'') + '>';		
					html +='			<label for="prdItemCheck'+i+'" style="margin-top: 9px;" ></label>';
					html +='		</div>';
					html +='	</div>';
//					html +='	<div class="flex_100">';	
//					html +='		<button type="button" class="white display-select" value="'+dpVo.dp_innb+'">선택</button>';
//					html +='	</div>';
					html +='</li>';
				}
			}
			
			html +='<ul>';
			$('#displayPrdItemTbody20300').html(html);
			$('#displayPrdItemTbody20300').find(".display-select").click(function(){
				$(this).parent().parent().parent().find("li").removeClass("cell_active");
				$(this).parent().parent().addClass("cell_active");
				alert($(this).val());
			});
		}
	});
}
/**
 * @함수명: fnMakeEventComponent_20200
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEvent_20300(){
	
}


