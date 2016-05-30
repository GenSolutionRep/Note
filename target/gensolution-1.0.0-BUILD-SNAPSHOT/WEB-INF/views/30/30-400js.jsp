<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String typeCode = (String) request.getAttribute("typeCode");
System.out.println("typeCode=" + typeCode);
%>
<script>
/**************************************************************************************************
 * @파일명: 30-400js.jsp
 * @작성일: 2015.10.23
 * @작성자: 최수영
 * @설명: 결재수신함 JS를 담고있는 JSP
**************************************************************************************************/

var approvalfileList_30400_${typeCode} = [];
var approvalPg_30400_${typeCode} = 1;
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#tempReasonPop30400_${typeCode}, #tempPop30400_${typeCode}").instancePopUp();
	
	//input 달력
	$('#dateFrom_search30400_${typeCode}').winCal();
	$('#dateTo_search30400_${typeCode}').winCal();
	
	$('#dateFrom_popup30400_${typeCode}').winCal();
	$('#dateTo_popup30400_${typeCode}').winCal();
	$('#date_popup30400_${typeCode}').winCal();
	
	
	$("#tr1_30400_${typeCode}").css("display", "");
	$("#tr2_30400_${typeCode}").css("display", "none");
	
	
	if('${typeCode}'=='0000000063')
		$("#mainTitle30400_${typeCode}").text("결재대기");
	else if('${typeCode}'=='0000000064')
		$("#mainTitle30400_${typeCode}").text("결재완료");
	else if('${typeCode}'=='0000000065')
		$("#mainTitle30400_${typeCode}").text("결재반려");
	
	// 이벤트 등록
	fnSetEventComponent30400_${typeCode}();	
	
	//메인 초기화
	fnInitMain30400_${typeCode}();
	
	//첨부파일
	$("#approvalFile_30400_${typeCode}").file({
		fileList : approvalfileList_30400_${typeCode},
	});
});

/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent30400_${typeCode}(){
	
	/*//파일
	$('#noticeFileInfoList').filedrop({
	    callback : function(fileEncryptedData) {
	    	noticeDragFile(fileEncryptedData);
	    }
	});*/
	
	$("#ad_type_combo_30400_${typeCode}").change(function(){
		if($(this).val()=='0000000059' ||$(this).val()=='0000000390'){//평일연장근무
			$("#tr1_30400_${typeCode}").css("display", "none");
			$("#tr2_30400_${typeCode}").css("display", "");
			
			//근태일자
			var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
			$('#date_popup30400_${typeCode}').val(tmpDate);
			$('#hhFrom_popup30400_${typeCode}').val("00");
			$('#mmFrom_popup30400_${typeCode}').val("00");
			$('#hhTo_popup30400_${typeCode}').val("00");
			$('#mmTo_popup30400_${typeCode}').val("00");
			
		}else{
			$("#tr1_30400_${typeCode}").css("display", "");
			$("#tr2_30400_${typeCode}").css("display", "none");
			
			//근태일자
			var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
			$('#dateFrom_popup30400_${typeCode}').val(tmpDate);
			$('#dateTo_popup30400_${typeCode}').val(tmpDate);
		}
	});
	
	$('#dateFrom_popup30400_${typeCode}, #dateTo_popup30400_${typeCode}').change(function(){
		var date1 = $('#dateFrom_popup30400_${typeCode}').val().replace("-", "").replace("-", ""); 
		var date2 = $('#dateTo_popup30400_${typeCode}').val().replace("-", "").replace("-", "");
		
		if(parseInt(date1) > parseInt(date2)){
			alert("근태기간을 확인하십시오.");
			$('#dateTo_popup30400_${typeCode}').focus();
			$('#txtDays30400_${typeCode}').text('');
			return;
		}
		
		var txtDays = fnCalDateRange($('#dateFrom_popup30400_${typeCode}').val(), $('#dateTo_popup30400_${typeCode}').val());
		alert(txtDays);
		var tmpTxt = " ("+(txtDays+1)+"일)";
		
		$('#txtDays30400_${typeCode}').text((tmpTxt));
	});
	
	
	//조회
	$("#btnSearch30400_${typeCode}").click(function(){
		var date1 = $('#dateFrom_search30400_${typeCode}').val().replace("-", "").replace("-", ""); 
		var date2 = $('#dateTo_search30400_${typeCode}').val().replace("-", "").replace("-", "");
		
		if(parseInt(date1) > parseInt(date2)){
			alert("조회기간을 확인하십시오.");
			//$('#dateFrom_search30400_${typeCode}').focus();
			return;
		}
		
		fnSelectList_30400_${typeCode}("1", "");
	});
	
	//초기화
	$("#btnClear30400_${typeCode}").click(function(){ 
		fnInitMain30400_${typeCode}();
	});
	
	//삭제버튼
	$("#tempDelete30400_${typeCode}").click(function(){
		if(confirm("삭제 하시겠습니까?"))	
			fnSave30400_${typeCode}("DELETE");
	});
	
	//상신버튼
	$("#tempApproval30400_${typeCode}").click(function(){
		if(confirm("결재 처리하시겠습니까?"))
			fnSave30400_${typeCode}("APPROVAL");
	});
	
	//닫기
	$("#tempClose30400_${typeCode}, #tempCloseX30400_${typeCode}").click(function(){
		$("#tempPop30400_${typeCode}").hide();
	});
	
	
	//반려버튼(반려사유 팝업 띄우기)
	$("#tempReason30400_${typeCode}").click(function(){
		$("#tempReasonPop30400_${typeCode}").show();
		$("#ar_reason_30400_${typeCode}").focus();
		
	});
	
	//반려팝업 닫기 
	$("#tempReasonClose30400_${typeCode}").click(function(){
		$("#tempReasonPop30400_${typeCode}").hide();
	});
	
	//반려버튼(반려처리)
	$("#tempReject30400_${typeCode}").click(function(){
		if($.trim($("#ar_reason_30400_${typeCode}").val()).length==0){
			alert("반려사유를 입력하세요.");
			$("#ar_reason_30400_${typeCode}").focus();
			return;
		}
		
		//반려처리
		fnSave30400_${typeCode}("REJECT");
	});
	
	//출력버튼
	$("#tempPrint30400_${typeCode}").click(function(){
		//타이틀 
		$("#aTitle1_30400_${typeCode}").text("("+$("#txt_ad_type_combo_30400_${typeCode}").text()+")");
		$("#aTitle2_30400_${typeCode}").text($("#txt_ad_type_combo_30400_${typeCode}").text());
		
		//성명
		$("#aName_30400_${typeCode}").text($("#em_nm2_30400_${typeCode}").text());
		//근무지
		//$("#str_nm_30400_${typeCode}").text($("#aPlace_30400_${typeCode}").text());
		$("#aPlace_30400_${typeCode}").text($("#om_nm_30400_${typeCode}").text()); 
		//사유
		$("#aReason_30400_${typeCode}").text($("#txt_ad_reason_30400_${typeCode}").text());
		//기간
		$("#aPeriod_30400_${typeCode}").text($("#txt_date_popup30400_${typeCode}").text());
		//기안일
		$("#adate_30400_${typeCode}").text($("#am_approval_date_txt_30400_${typeCode}").text().replace("-",".").replace("-",".")+"."); 
		//신청자
		$("#aApplicant_30400_${typeCode}").text($("#em_nm2_30400_${typeCode}").text());
		
		$("#printArea30400_${typeCode}").show();		
		var win = window.open();
		win.document.open();
		win.document.write($("#printArea30400_${typeCode}").html());
		win.document.close();
		win.print();
		win.close();
		$("#printArea30400_${typeCode}").hide();
	});
	
}

function rowClick30400_${typeCode}(){
	$("#tbl_30400_${typeCode} tbody tr").click(function(){
		if($("#tbl_30400_${typeCode} tbody td").length > 1){
			var idx = $(this).index();
			var amCode = $("#tbl_30400_${typeCode} tbody tr:eq("+idx+") td:nth-child(2)").text();
			var amStatus = $("#tbl_30400_${typeCode} tbody tr:eq("+idx+") td:nth-child(9)").text();
			
			$("#tbl_30400_${typeCode} tbody").find("tr").removeClass('cell_active');
			$("#tbl_30400_${typeCode} tbody tr:eq("+idx+")").addClass('cell_active');
			
			//팝업초기화
			fnInitPopup30400_${typeCode}();
			
			$("#tblRead_30400_${typeCode}").css("display", "");
				$("#tempApproval30400_${typeCode}").css("display", "");
				$("#tempReason30400_${typeCode}").css("display", "");

			fnSelectRow_30400_${typeCode}(amCode);
			
			$("#tempPop30400_${typeCode}").show();
		}
	});
	
}

/**
 * @함수명: cbo_om_code_30400_${typeCode}
 * @작성일: 2015. 09. 30.
 * @작성자: 최수영
 * @설명: 지점 콤보 리스트
 * @param 
 */
function cbo_om_code_30400_${typeCode}(){
	$.ajax({
		url : "/organization/auth",
	    data:{"om_code":"", "om_orgnzt_se": "1"},
	    type : "POST",
	    dataType : "json",
	    async: false,
	    cache : false ,
	    success : function(data) {
	    	//var listHtml = "<option value='' selected='selected'>선택</option>";
	    	var listHtml = "";
			if( data.result.length > 0){
	    		for(var i=0; i<data.result.length; i++){
	    			var res = data.result[i];     
	    			listHtml += "<option value ='"+res.om_code+"'>"+ res.om_nm +"</option>";
	    		}
			}	
			$("#cbo_om_code30400_${typeCode}").html(listHtml);
	    }
	});
}

/**
 * @함수명: fnSelectList_30400_${typeCode}
 * @작성일: 2015. 10. 18.
 * @작성자: 최수영
 * @설명: 상신 리스트 조회
 * @param 
 */

function fnSelectList_30400_${typeCode}(currPg, type){
	approvalPg_30400_${typeCode} = currPg;//현재페이지 리로드
	var fnName = "fnSelectList_30400_${typeCode}";//페이징처리 함수명
	var rowSize = 15;//표시할 행수
	var rowSizePerLine = 1;//화면의 1라인에 표시할 행수
	
	var params = {
					flag : $('#cboType30400_${typeCode}').val(),
					ad_date_from : $('#dateFrom_search30400_${typeCode}').val().replace('-','').replace('-',''),
					ad_date_to : $('#dateTo_search30400_${typeCode}').val().replace('-','').replace('-',''),
					om_code : $('#cbo_om_code30400_${typeCode}').val(),
					em_no : '',
					em_nm : $.trim($('#em_nm_search30400_${typeCode}').val()),
					am_approver_em_no : login_no,
					am_status: '${typeCode}',
					auth_flag: auth_flag
				};
	
	$.ajax({
		url : "/approval/list",
		data:{
			  //하단 페이징 처리
			  "fnName" : fnName,
			  "params" : params,
			  "rowSize" : rowSize,
			  "currPg" : currPg,
			  },
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
	    	
		var listHtml = "";

		if(data!=null && data.result.length > 0){
			for(var i=0; i<data.result.length; i++){
				var vo = data.result[i];   
				
				listHtml += "<tr>";
				listHtml += "<td>" + (parseInt(data.firstNo)-i) + "</td>";
				listHtml += "<td>" + vo.am_code + "</td>";
        		listHtml += "<td class='txt_center'>" + vo.om_nm + "</td>";
    			listHtml += "<td class='txt_center'>" + vo.em_nm + "</td>";
    			listHtml += "<td>" + vo.ad_type_nm+ "</td>";
    			listHtml += "<td>" ;
    			listHtml += vo.ad_date_from;
    			if(vo.ad_type !='0000000059' && vo.ad_type !='0000000390')
    				listHtml += " ~ " + vo.ad_date_to;	
    			else{
    				var tmpTxt = "(" + vo.ad_date_from_hhmm.substring(0,2) + ":" + vo.ad_date_from_hhmm.substring(2,4) + " ~ ";
    				tmpTxt += vo.ad_date_to_hhmm.substring(0,2)+ ":" +vo.ad_date_to_hhmm.substring(2,4);
    				listHtml += " " + tmpTxt + ")";
    			}
    			listHtml += "</td>";
    			
    			if(vo.attach_cnt > 0)
    				listHtml += "<td><i class='fa fa-paperclip fa-lg'></i></td>";
    			else
    				listHtml += "<td><i class='fa fa-times'></i></td>";
    			listHtml += "<td>" + vo.am_approval_date + "</td>";
    			
    			if(vo.am_status=='0000000063')//상신
    				listHtml += "<td>결재대기</td>";
    			else
    				listHtml += "<td>" + vo.am_status_nm + "</td>";
    			listHtml += "</tr>";
    			//tmp= tmp-1;
        	}
		}else{
			listHtml += "<tr>";
			listHtml += "<td colspan='9'>조회된 내용이 없습니다</td>";
			listHtml += "</tr>";
		}	
		$("#tbody30400_${typeCode}").html(listHtml);
		//$("#approvalList30400_${typeCode}").html(listHtml);
		$("#approvalNavi30400_${typeCode}").html(data.navi);
		rowClick30400_${typeCode}();
	    }
	});
}

/**
 * @함수명: fnSelectRow_30400_${typeCode}
 * @작성일: 2015. 10. 18.
 * @작성자: 최수영
 * @설명: 상신 리스트 조회
 * @param 
 */

function fnSelectRow_30400_${typeCode}(am_code){
	
	//반려이력
	$.ajax({
		url : "/approval/rejectHistoryList",
		data:{"am_code":am_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async: false,
	    success : function(data) {
	    	var strHtml ="";
	    	
			if(data!=null && data.result.length > 0){
				strHtml+="<tr><th colspan='2'>반려 이력</th></tr>";
				
				for (var i = 0; i < data.result.length; i++) {
					var vo = data.result[i];   
					strHtml+="<tr>";
					strHtml+="	<td>"+ vo.updt_de +"</td>";
					strHtml+="	<td class='txt_left'>"+ vo.ar_reason+"</td>";
					strHtml+="</tr>";
				}
				$("#rejectHistory30400_${typeCode}").html(strHtml);
			}
	    }
	});
	
	
	//=======================================================================
	var am_status="";
	$.ajax({
		url : "/approval/approvalRow",
		data:{"am_code":am_code, "am_status": am_status},
	    type : "POST",
	    dataType : "json",
	    //global:true,
	    async: false,
	    cache : false,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var vo = data.result[0];   
				
				$("#am_code_30400_${typeCode}").val(vo.am_code);
				
				$("#em_no_30400_${typeCode}").val(vo.em_no);
				$("#em_nm1_30400_${typeCode}").text(vo.em_nm);
				$("#em_nm2_30400_${typeCode}").text(vo.em_nm);
				$("#om_code_30400_${typeCode}").val(vo.om_code);
				$("#om_nm_30400_${typeCode}").text(vo.om_nm);
				$("#em_dty_code_30400_${typeCode}").val(vo.em_dty_code);
				$("#em_dty_nm_30400_${typeCode}").text(vo.em_dty_nm);
				$("#am_approver_em_no_30400_${typeCode}").val(vo.am_approver_em_no);
				$("#am_approver_em_nm_30400_${typeCode}").text(vo.am_approver_em_nm);
				$("#am_approval_date_30400_${typeCode}").text(vo.am_approval_date);
				$("#am_approval_date_txt_30400_${typeCode}").text(vo.am_approval_date);
				$("#am_no_30400_${typeCode}").text(vo.am_no);
				
				
				//결재완료면
				if(vo.am_status=='0000000064')
					$("#txt_updt_de_30400_${typeCode}").text(vo.updt_de);
				
				//read=======================================================
				$("#txt_ad_type_combo_30400_${typeCode}").text(vo.ad_type_nm);	
				$("#fileViewList_30400_${typeCode}").html("");
				
				//alert(vo.am_no);
				//alert(data.attachVoList.length);
				var html ="";
				for (var i = 0; i < data.attachVoList.length; i++) {
					var attachVo = data.attachVoList[i];
					html += '<li class="add-file-li type-image tx-existed" id="old-file'+attachVo.ai_no+'" >';
					html += '<dl>';
					html += '<dt class="tx-name noti-file-down" data-ai_no="'+attachVo.ai_no+'"  ><i class="fa ext" data-ext="'+attachVo.ai_nm+'" ></i>';
					var size = attachVo.ai_size.fileSize();
					html += attachVo.ai_nm+' ('+size+')';
					html += '</dt><dd class="tx-button">';
					html += '</dd>';
					html += '</dl>';
					html += '</li>';
					//$("#fileViewList_30400_${typeCode}").append(html);
				}
				$("#fileViewList_30400_${typeCode}").html(html);
				//==============================================================
				$(".noti-file-down").click(function(){
					location.href = "/file/down?ai_no="+$(this).data("ai_no");
				});
				
				
				var tmpTxt="";
				if(vo.ad_type=="0000000059" || vo.ad_type=="0000000390"){//휴일연장근무
					tmpTxt += vo.ad_date_from + " ";
					tmpTxt += vo.ad_date_from_hhmm.substring(0,2) + ":" + vo.ad_date_from_hhmm.substring(2,4) + " ~ ";
					tmpTxt += vo.ad_date_to_hhmm.substring(0,2)+ ":" +vo.ad_date_to_hhmm.substring(2,4);
					
				}else{
					tmpTxt += vo.ad_date_from + " ~ " + vo.ad_date_to;
					
					var txtDays = fnCalDateRange(vo.ad_date_from, vo.ad_date_to);
					tmpTxt += " ("+(txtDays+1)+"일)";
				}
				$("#txt_date_popup30400_${typeCode}").text(tmpTxt);
				
				$("#txt_ad_reason_30400_${typeCode}").text(vo.ad_reason);				
				
				
				//버튼처리
				if(vo.am_status=='0000000063'){//상신
					if(parseInt(auth_flag) ==1) {
						$("#tempApproval30400_${typeCode}").css("display", "none");
						$("#tempReason30400_${typeCode}").css("display", "none");
						$("#tempPrint30400_${typeCode}").css("display", "");
						$("#tempDelete30400_${typeCode}").css("display", "none");
					}else{
						$("#tempApproval30400_${typeCode}").css("display", "");
						$("#tempReason30400_${typeCode}").css("display", "");
						$("#tempPrint30400_${typeCode}").css("display", "");
						$("#tempDelete30400_${typeCode}").css("display", "none");
					}
				}else if(vo.am_status=='0000000064'){ //결제완료
					$("#tempApproval30400_${typeCode}").css("display", "none");
					$("#tempReason30400_${typeCode}").css("display", "none");
					$("#tempPrint30400_${typeCode}").css("display", "");
					
					if(parseInt(auth_flag) ==1)
						$("#tempDelete30400_${typeCode}").css("display", "");
					else
						$("#tempDelete30400_${typeCode}").css("display", "none");
				}else if(vo.am_status=='0000000065'){ //반려
					$("#tempApproval30400_${typeCode}").css("display", "none");
					$("#tempReason30400_${typeCode}").css("display", "none");
					$("#tempPrint30400_${typeCode}").css("display", "none");
					$("#tempDelete30400_${typeCode}").css("display", "none");
				}
					
			}
	    }
	});
}

/**
 * @함수명: fnSelectRow_30400_${typeCode}
 * @작성일: 2015. 10. 18.
 * @작성자: 최수영
 * @설명: 상신 리스트 조회
 * @param 
 */

function fnSelectBaseInfo_30400_${typeCode}(){
	$.ajax({
		url : "/approval/approvalBaseInfo",
		//data:{"am_code":am_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var vo = data.result[0];   
				$("#em_no_30400_${typeCode}").val(vo.em_no);
				$("#em_nm1_30400_${typeCode}").text(vo.em_nm);
				$("#em_nm2_30400_${typeCode}").text(vo.em_nm);
				$("#om_code_30400_${typeCode}").val(vo.om_code);
				$("#om_nm_30400_${typeCode}").text(vo.om_nm);
				$("#em_dty_code_30400_${typeCode}").val(vo.em_dty_code);
				$("#em_dty_nm_30400_${typeCode}").text(vo.em_dty_nm);
				$("#am_approver_em_no_30400_${typeCode}").val(vo.am_approver_em_no);
				$("#am_approver_em_nm_30400_${typeCode}").text(vo.am_approver_em_nm);
			}
	    }
	});
}

function fnSave30400_${typeCode}(flag){
	$("#save_type_30400_${typeCode}").val(flag);
	
	var param = "";
	param+="flag=" + $("#save_type_30400_${typeCode}").val();
	param+="&am_code=" + $("#am_code_30400_${typeCode}").val();
	param+="&ar_reason=" + $("#ar_reason_30400_${typeCode}").val();
	param+="&em_no=" + $("#em_no_30400_${typeCode}").val();
	param+="&om_code=" + $("#om_code_30400_${typeCode}").val();
	param+="&am_approver_em_no=" + $("#am_approver_em_no_30400_${typeCode}").val();
	//alert(param);
	 
	$.ajax({
	   type : "POST",
	   async : true,
	   cache : false,
	   url  : "/approval/save",
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
			if(data.dto[0].res_code=='-1' ){
				if(flag=="APPROVAL")
					alert('결재처리에 실패하였습니다.');
				else if(flag=="REJECT")
					alert('반려처리에 실패하였습니다.');
				else if(flag=="DELETE")
					alert('삭제처리에 실패하였습니다.');
			}else if(data.dto[0].res_code=='0'){
				if(flag=="APPROVAL")
					alert('결재처리 되었습니다.');
				else if(flag=="REJECT"){
					alert('반려처리 되었습니다.');
					//반려팝업창 닫기
					$("#tempReasonClose30400_${typeCode}").click();
				}else if(flag=="DELETE"){
					alert('삭제처리 되었습니다.');
				}
				//닫기
				$("#tempClose30400_${typeCode}").click();
				fnSelectList_30400_${typeCode}(approvalPg_30400_${typeCode}, "");
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

/**
 * @함수명: cboType30400_${typeCode}
 * @작성일: 2015. 10. 19.
 * @작성자: 최수영
 * @설명: 결재유형 콤보 리스트
 * @param 
 */

function cboType30400_${typeCode}(){
	$.ajax({
		url : "/code/codeComboBox",
		data:{"c_parent_code":"0000000058"},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
	    success : function(data) {
		var listHtml = "<option value='' selected='selected'>선택</option>";
		if(data!=null && data.length > 0){
			for(var i=0; i<data.length; i++){
				var vo = data[i];     
        		listHtml += "<option value='"+vo.c_code+"'>"+vo.c_name + "</option>";
        	}
		}else{
			
		}	
		$("#ad_type_combo_30400_${typeCode}").html(listHtml);
	    
	    }
	});
}
/**
 * @함수명: fnInitMain30400_${typeCode}
 * @작성일: 2015. 10. 19
 * @작성자: 
 * @설명: 메인화면 Init.
 * @param 
 */
function fnInitMain30400_${typeCode}(){
	//근태일/상신일
	$('#cboType30400_${typeCode}').val('A');
	
	//일자 셋팅
	var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
	tmpDate=tmpDate.substring(0,8)+"16";
	$('#dateFrom_search30400_${typeCode}').val(tmpDate);
	
	var today = tmpDate.replace('-','').replace('-','');
	var user_date = new Date(today.substring(0,4),today.substring(4,6),today.substring(6,8));
	var year = user_date.getFullYear();
	var month = user_date.getMonth() + 1;
	var date = user_date.getDate() - 1; 
	if(month<10) month = "0" + month; 
	if(date<10) date = "0" + date; 
	var result = year + "-" + month + "-" + date; 
	
	$('#dateTo_search30400_${typeCode}').val(result);
	
	//지점코드 콤보
	cbo_om_code_30400_${typeCode}();
	
	//상신자
	$('#em_nm_search30400_${typeCode}').val('');
	
	//조회
	fnSelectList_30400_${typeCode}("1", "");
	
}

/**
 * @함수명: fnInitPopup30400_${typeCode}
 * @작성일: 2015. 10. 19
 * @작성자: 
 * @설명: 팝업 컨트롤 init.
 * @param 
 */
function fnInitPopup30400_${typeCode}(){	
	var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
	
	$("#txt_ad_type_combo_30400_${typeCode}").text('');	
	$("#txt_date_popup30400_${typeCode}").text('');
	$("#txt_ad_reason_30400_${typeCode}").text('');
	$("#txt_updt_de_30400_${typeCode}").text('');
	
	$("#fileViewList_30400_${typeCode}").html("");
	
	//반려처리시 반려사유
	$("#rejectHistory30400_${typeCode}").html("");
	
	$("#ar_reason_30400_${typeCode}").val("");
	
	
}



</script>
