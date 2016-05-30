<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String typeCode = (String) request.getAttribute("typeCode");
System.out.println("typeCode=" + typeCode);
%>
<script>
/**************************************************************************************************
 * @파일명: 30-100js.jsp
 * @작성일: 2015.10.23
 * @작성자: 최수영
 * @설명: 결재상신함 JS를 담고있는 JSP
**************************************************************************************************/

var approvalfileList_30100_${typeCode} = [];
var approvalPg_30100_${typeCode} = 1;
/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#tempPop30100_${typeCode}").instancePopUp();
	
	//input 달력
	$('#dateFrom_search30100_${typeCode}').winCal(baseOptions);
	$('#dateTo_search30100_${typeCode}').winCal(baseOptions);
	
	$('#dateFrom_popup30100_${typeCode}').winCal(baseOptions);
	$('#dateTo_popup30100_${typeCode}').winCal(baseOptions);
	$('#date_popup30100_${typeCode}').winCal(baseOptions);
	
	
	$("#tr1_30100_${typeCode}").css("display", "");
	$("#tr2_30100_${typeCode}").css("display", "none");
	
	if('${typeCode}'=='0000000063')
		$("#mainTitle30100_${typeCode}").text("결재상신");
	else if('${typeCode}'=='0000000064')
		$("#mainTitle30100_${typeCode}").text("결재완료");
	else if('${typeCode}'=='0000000065')
		$("#mainTitle30100_${typeCode}").text("결재반려");
	
	// 이벤트 등록
	fnSetEventComponent30100_${typeCode}();	
	
	//메인 초기화
	fnInitMain30100_${typeCode}();
	
	//첨부파일
	$("#approvalFile_30100_${typeCode}").file({
		fileList : approvalfileList_30100_${typeCode},
	});
	
	if('${typeCode}' !='0000000063' || parseInt(auth_flag) == 1){
		$("#tempSend30100_${typeCode}").css('display','none');
	}
});

/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent30100_${typeCode}(){
	
	//파일
	$('#noticeFileInfoList').filedrop({
	    callback : function(fileEncryptedData) {
	    	noticeDragFile(fileEncryptedData);
	    }
	});
	
	$("#ad_type_combo_30100_${typeCode}").change(function(){
		if($(this).val()=='0000000059' || $(this).val()=='0000000390'){//평일연장근무 or 휴일연장근무
			$("#tr1_30100_${typeCode}").css("display", "none");
			$("#tr2_30100_${typeCode}").css("display", "");
			
			//근태일자
			var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
			$('#date_popup30100_${typeCode}').val(tmpDate);
			$('#hhFrom_popup30100_${typeCode}').val("00");
			$('#mmFrom_popup30100_${typeCode}').val("00");
			$('#hhTo_popup30100_${typeCode}').val("00");
			$('#mmTo_popup30100_${typeCode}').val("00");
			
		}else{
			$("#tr1_30100_${typeCode}").css("display", "");
			$("#tr2_30100_${typeCode}").css("display", "none");
			
			//근태일자
			var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
			
			$('#dateFrom_popup30100_${typeCode}').val(tmpDate);
			$('#dateTo_popup30100_${typeCode}').val(tmpDate);
		}
	});
	
	$('#dateFrom_popup30100_${typeCode}, #dateTo_popup30100_${typeCode}').change(function(){
		var date1 = $('#dateFrom_popup30100_${typeCode}').val().replace("-", "").replace("-", ""); 
		var date2 = $('#dateTo_popup30100_${typeCode}').val().replace("-", "").replace("-", "");
		
		if(parseInt(date1) > parseInt(date2)){
			alert("근태기간을 확인하십시오.");
			$('#dateTo_popup30100_${typeCode}').focus();
			$('#txtDays30100_${typeCode}').text('');
			return;
		}
		var txtDays = fnCalDateRange($('#dateFrom_popup30100_${typeCode}').val(), $('#dateTo_popup30100_${typeCode}').val());
		var tmpTxt = " ("+(txtDays+1)+"일)";
		
		$('#txtDays30100_${typeCode}').text((tmpTxt));
	});
	
	
	//조회
	$("#btnSearch30100_${typeCode}").click(function(){
		var date1 = $('#dateFrom_search30100_${typeCode}').val().replace("-", "").replace("-", ""); 
		var date2 = $('#dateTo_search30100_${typeCode}').val().replace("-", "").replace("-", "");
		
		if(parseInt(date1) > parseInt(date2)){
			alert("조회기간을 확인하십시오.");
			//$('#dateFrom_search30100_${typeCode}').focus();
			return;
		}
		
		fnSelectList_30100_${typeCode}("1", "");
	});
	
	//초기화
	$("#btnClear30100_${typeCode}").click(function(){ 
		fnInitMain30100_${typeCode}();
	});
	
	//결재상신
	$("#tempSend30100_${typeCode}").click(function(){
		 
		//팝업 초기화
		fnInitPopup30100_${typeCode}();
		fnSelectBaseInfo_30100_${typeCode}();
		
		$("#tblWrite_30100_${typeCode}").css("display", "");
		$("#tblRead_30100_${typeCode}").css("display", "none");
		
		$("#tempSave30100_${typeCode}").css("display", "");
		$("#tempDelete30100_${typeCode}").css("display", "none");
		$("#tempPrint30100_${typeCode}").css("display", "none");
		
		
		$("#save_type_30100_${typeCode}").val("INSERT");
		$("#tempPop30100_${typeCode}").show();
	});
	
	//상신버튼
	$("#tempPrint30100_${typeCode}").click(function(){
		//타이틀 
		$("#aTitle1_30100_${typeCode}").text("("+$("#txt_ad_type_combo_30100_${typeCode}").text()+")");
		$("#aTitle2_30100_${typeCode}").text($("#txt_ad_type_combo_30100_${typeCode}").text());
		
		//성명
		$("#aName_30100_${typeCode}").text($("#em_nm2_30100_${typeCode}").text());
		//근무지
		//$("#str_nm_30100_${typeCode}").text($("#aPlace_30100_${typeCode}").text());
		$("#aPlace_30100_${typeCode}").text($("#om_nm_30100_${typeCode}").text()); 
		//사유
		$("#aReason_30100_${typeCode}").text($("#txt_ad_reason_30100_${typeCode}").text());
		//기간
		$("#aPeriod_30100_${typeCode}").text($("#txt_date_popup30100_${typeCode}").text());
		//기안일
		$("#adate_30100_${typeCode}").text($("#am_approval_date_txt_30100_${typeCode}").text().replace("-",".").replace("-",".")+"."); 
		//신청자
		$("#aApplicant_30100_${typeCode}").text($("#em_nm2_30100_${typeCode}").text());

		
		/* $.ajax({
			url : "/approval/approvalRow",
			data:{"am_code":am_code_30100_${typeCode}.val(), "am_status": "${typeCode}"},
		    type : "POST",
		    dataType : "json",
		    //global:true,
		    cache : false,
		    async: false,
		    success : function(data) {
				if(data!=null && data.result.length > 0){
					var vo = data.result[0];   
					
					//타이틀 
					$("#aTitle1_30100_${typeCode}").text(vo.am_status_nm);
					$("#aTitle2_30100_${typeCode}").text(vo.am_status_nm);
					
					//성명
					$("#aName_30100_${typeCode}").text(vo.em_nm);
					//사유
					$("#aReason_30100_${typeCode}").text(vo.ad_reason);
					//기간
					var tmpTxt="";
					if(vo.ad_type=="0000000059" || vo.ad_type=="0000000060"){//휴일연장근무
						tmpTxt += vo.ad_date_from + " ";
						tmpTxt += vo.ad_date_from_hhmm.substring(0,2) + ":" + vo.ad_date_from_hhmm.substring(2,4) + " ~ ";
						tmpTxt += vo.ad_date_to_hhmm.substring(0,2)+ ":" +vo.ad_date_to_hhmm.substring(2,4);
						
					}else{
						tmpTxt += vo.ad_date_from + " ~ " + vo.ad_date_to;
						
						var txtDays = fnCalDateRange(vo.ad_date_from, vo.ad_date_to);
						tmpTxt += " ("+(txtDays+1)+"일)";
					}
					$("#aPeriod_30100_${typeCode}").text(tmpTxt);
					
					$("#adate_30100_${typeCode}").text(vo.am_approval_date);
					
					//신청자
					$("#aApplicant_30100_${typeCode}").text("신청자 : &nbsp;&nbsp;&nbsp;" +  vo.em_nm + "&nbsp;&nbsp;&nbsp;(인)");
				}
		    }
		}); */
		
		$("#printArea30100_${typeCode}").show();		
		var win = window.open();
		win.document.open();
		win.document.write($("#printArea30100_${typeCode}").html());
		win.document.close();
		win.print();
		win.close();
		$("#printArea30100_${typeCode}").hide();
	});
	
	//상신버튼
	$("#tempDelete30100_${typeCode}").click(function(){
		$("#save_type_30100_${typeCode}").val("DELETE");
		fnSave30100_${typeCode}();
	});
	
	//상신버튼
	$("#tempSave30100_${typeCode}").click(function(){
		fnSave30100_${typeCode}();
	});
	
	//닫기
	$("#tempClose30100_${typeCode}, #tempCloseX30100_${typeCode}").click(function(){
		$("#tempPop30100_${typeCode}").hide();
	});
}

function rowClick30100_${typeCode}(){
	$("#tbl_30100_${typeCode} tbody tr").click(function(){
		if($("#tbl_30100_${typeCode} tbody td").length > 1){
			var idx = $(this).index();
			var amCode = $("#tbl_30100_${typeCode} tbody tr:eq("+idx+") td:nth-child(2)").text();
			var amStatus = $("#tbl_30100_${typeCode} tbody tr:eq("+idx+") td:nth-child(9)").text();
			
			$("#tbl_30100_${typeCode} tbody").find("tr").removeClass('cell_active');
			$("#tbl_30100_${typeCode} tbody tr:eq("+idx+")").addClass('cell_active');
			
			fnInitPopup30100_${typeCode}();
			//alert(amCode);
			
			$("#save_type_30100_${typeCode}").val("UPDATE");
			
			if(amStatus!='반려'){
				$("#tblWrite_30100_${typeCode}").css("display", "none");
				$("#tblRead_30100_${typeCode}").css("display", "");
				$("#tempPrint30100_${typeCode}").css("display", "");
			}else{
				$("#tblWrite_30100_${typeCode}").css("display", "");
				$("#tblRead_30100_${typeCode}").css("display", "none");
				$("#tempPrint30100_${typeCode}").css("display", "none");
			}
					
			
			$("#tempSave30100_${typeCode}").css("display", "none");
			
			if(parseInt(auth_flag) == 1){
				$("#tempDelete30100_${typeCode}").css("display", "none");
			}else{
				$("#tempDelete30100_${typeCode}").css("display", "");
			}
			
			fnSelectRow_30100_${typeCode}(amCode);
			$("#tempPop30100_${typeCode}").show();
		}
	});
	
}

/**
 * @함수명: fnSelectList_30100_${typeCode}
 * @작성일: 2015. 10. 18.
 * @작성자: 최수영
 * @설명: 상신 리스트 조회
 * @param 
 */

function fnSelectList_30100_${typeCode}(currPg, type){
	approvalPg_30100_${typeCode} = currPg;//현재페이지 리로드
	var fnName = "fnSelectList_30100_${typeCode}";//페이징처리 함수명
	var rowSize = 15;//표시할 행수
	var rowSizePerLine = 1;//화면의 1라인에 표시할 행수
	
	var params = {
					flag : $('#cboType30100_${typeCode}').val(),
					ad_date_from : $('#dateFrom_search30100_${typeCode}').val().replace('-','').replace('-',''),
					ad_date_to : $('#dateTo_search30100_${typeCode}').val().replace('-','').replace('-',''),
					om_code : '',
					em_no : login_no,
					em_nm : '',
					am_approver_em_no : '',
					am_status: '${typeCode}'
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
    			if(vo.am_status =='0000000063') //상신
    				listHtml += "<td>결재중</td>";
    			else
    				listHtml += "<td>" + vo.am_status_nm + "</td>";
    			
    			listHtml += "</tr>";
        	}
		}else{
			listHtml += "<tr>";
			listHtml += "<td colspan='9'>조회된 내용이 없습니다</td>";
			listHtml += "</tr>";
		}	
		$("#tbody30100_${typeCode}").html(listHtml);
		//$("#approvalList30100_${typeCode}").html(listHtml);
		$("#approvalNavi30100_${typeCode}").html(data.navi);
		rowClick30100_${typeCode}();
	    }
	});
}

/**
 * @함수명: fnSelectRow_30100_${typeCode}
 * @작성일: 2015. 10. 18.
 * @작성자: 최수영
 * @설명: 상신 리스트 조회
 * @param 
 */

function fnSelectRow_30100_${typeCode}(am_code){
	
	//반려이력
	$.ajax({
		url : "/approval/rejectHistoryList",
		data:{"am_code":am_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    async : false,
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
				$("#rejectHistory30100_${typeCode}").html(strHtml);
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
	    cache : false,
	    async: false,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var vo = data.result[0];   
				
				$("#str_nm_30100_${typeCode}").val(vo.str_nm);
				$("#am_code_30100_${typeCode}").val(vo.am_code);
				
				$("#em_no_30100_${typeCode}").val(vo.em_no);
				$("#em_nm1_30100_${typeCode}").text(vo.em_nm);
				$("#em_nm2_30100_${typeCode}").text(vo.em_nm);
				$("#om_code_30100_${typeCode}").val(vo.om_code);
				$("#om_nm_30100_${typeCode}").text(vo.om_nm);
				$("#em_dty_code_30100_${typeCode}").val(vo.em_dty_code);
				$("#em_dty_nm_30100_${typeCode}").text(vo.em_dty_nm);
				$("#am_approver_em_no_30100_${typeCode}").val(vo.am_approver_em_no);
				$("#am_approver_em_nm_30100_${typeCode}").text(vo.am_approver_em_nm);
				$("#am_approval_date_30100_${typeCode}").text(vo.am_approval_date);
				$('#am_approval_date_txt_30100_${typeCode}').text(vo.am_approval_date);
				$("#am_no_30100_${typeCode}").text(vo.am_no);
				
				
				//결재완료면
				if(vo.am_status=='0000000064')
					$("#txt_updt_de_30100_${typeCode}").text(vo.updt_de);
				
				$("#ad_reason_30100_${typeCode}").val(vo.ad_reason);
				$("#ad_type_combo_30100_${typeCode}").val(vo.ad_type);	
				$("#ad_type_combo_30100_${typeCode}").prop('disabled',true);
				
				$("#dateFrom_popup30100_${typeCode}").val(vo.ad_date_from);
				$("#dateTo_popup30100_${typeCode}").val(vo.ad_date_to);	
				
				$("#date_popup30100_${typeCode}").val(vo.ad_date_from);
				
				$("#hhFrom_popup30100_${typeCode}").val(vo.ad_date_from_hhmm.substring(0,2));
				$("#mmFrom_popup30100_${typeCode}").val(vo.ad_date_from_hhmm.substring(2,4));
				$("#hhTo_popup30100_${typeCode}").val(vo.ad_date_to_hhmm.substring(0,2));
				$("#mmTo_popup30100_${typeCode}").val(vo.ad_date_to_hhmm.substring(2,4));
				
				if(vo.ad_type=='0000000059' || vo.ad_type=='0000000390'){//평일연장근무
					$("#tr1_30100_${typeCode}").css("display", "none");
					$("#tr2_30100_${typeCode}").css("display", "");					
				}else{
					$("#tr1_30100_${typeCode}").css("display", "");
					$("#tr2_30100_${typeCode}").css("display", "none");
				}
				
				//read=======================================================
				$("#txt_ad_type_combo_30100_${typeCode}").text(vo.ad_type_nm);	
				$("#fileViewList_30100_${typeCode}").html("");
				
				//alert(vo.am_no);
				//alert(data.attachVoList.length);
				var html="";
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
					$("#fileViewList_30100_${typeCode}").append(html);
				}
				//$("#fileViewList_30100_${typeCode}").html(html);
				
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
				$("#txt_date_popup30100_${typeCode}").text(tmpTxt);
				
				$("#txt_ad_reason_30100_${typeCode}").text(vo.ad_reason);
				
				//버튼처리
				if(vo.am_status=='0000000063'){//상신
					$("#tempSave30100_${typeCode}").css("display", "none");
					if(parseInt(auth_flag)==1){
						$("#tempDelete30100_${typeCode}").css("display", "none");
					}else{
						$("#tempDelete30100_${typeCode}").css("display", "");
					}
				}else if(vo.am_status=='0000000064'){ //결제완료
					$("#tempSave30100_${typeCode}").css("display", "none");
					$("#tempDelete30100_${typeCode}").css("display", "none");
				}else if(vo.am_status=='0000000065'){ //반려
					if(parseInt(auth_flag)==1){
						$("#tempSave30100_${typeCode}").css("display", "none");
						$("#tempDelete30100_${typeCode}").css("display", "none");
					}else{
						$("#tempSave30100_${typeCode}").css("display", "");
						$("#tempDelete30100_${typeCode}").css("display", "");
					}
					
				}
					
			}
	    }
	});
}

/**
 * @함수명: fnSelectRow_30100_${typeCode}
 * @작성일: 2015. 10. 18.
 * @작성자: 최수영
 * @설명: 상신 리스트 조회
 * @param 
 */

function fnSelectBaseInfo_30100_${typeCode}(){
	$.ajax({
		url : "/approval/approvalBaseInfo",
		//data:{"am_code":am_code},
	    type : "POST",
	    dataType : "json",
	    cache : false,
	    success : function(data) {
			if(data!=null && data.result.length > 0){
				var vo = data.result[0];   
				$("#em_no_30100_${typeCode}").val(vo.em_no);
				$("#em_nm1_30100_${typeCode}").text(vo.em_nm);
				$("#em_nm2_30100_${typeCode}").text(vo.em_nm);
				$("#om_code_30100_${typeCode}").val(vo.om_code);
				$("#om_nm_30100_${typeCode}").text(vo.om_nm);
				$("#em_dty_code_30100_${typeCode}").val(vo.em_dty_code);
				$("#em_dty_nm_30100_${typeCode}").text(vo.em_dty_nm);
				$("#am_approver_em_no_30100_${typeCode}").val(vo.am_approver_em_no);
				$("#am_approver_em_nm_30100_${typeCode}").text(vo.am_approver_em_nm);
			}
	    }
	});
}

function fnSave30100_${typeCode}(){
	if($("#save_type_30100_${typeCode}").val()!='DELETE'){
		if($("#ad_type_combo_30100_${typeCode}").val()==''){
			alert("결재유형을 선택하세요");
			$("#ad_type_combo_30100_${typeCode}").focus();
			return;
		}
		
		
		if($("#ad_type_combo_30100_${typeCode}").val()=='0000000059' || $("#ad_type_combo_30100_${typeCode}").val()=='0000000390' ){
			var tmp1 = parseInt($("#hhFrom_popup30100_${typeCode}").val()+$("#mmFrom_popup30100_${typeCode}").val());
			var tmp2 = parseInt($("#hhTo_popup30100_${typeCode}").val()+$("#mmTo_popup30100_${typeCode}").val());
			
			if(tmp1 > tmp2){
				alert("근태시간을 확인하세요");
				return;
			}
			
		}else{
			var tmp1 = parseInt($("#dateFrom_popup30100_${typeCode}").val().replace("-", "").replace("-", ""));
			var tmp2 = parseInt($("#dateTo_popup30100_${typeCode}").val().replace("-", "").replace("-", ""));
			
			if(tmp1 > tmp2){
				alert("근태기간을 확인하세요");
				$("#dateFrom_popup30100_${typeCode}").focus();
				return;
			}
		}
		
		if($.trim($("#ad_reason_30100_${typeCode}").val())==''){
			alert("사유를 입력하세요.");
			$("#ad_reason_30100_${typeCode}").focus();
			return;
		}
	}
	var param = $('#frm30100_${typeCode}').serialize();
	if($("#ad_type_combo_30100_${typeCode}").val()=='0000000059' ||$("#ad_type_combo_30100_${typeCode}").val()=='0000000390'  ){
		param+="&ad_date_from="+$("#date_popup30100_${typeCode}").val();
		param+="&ad_date_to="+$("#date_popup30100_${typeCode}").val();
		param+="&ad_date_from_hhmm="+$("#hhFrom_popup30100_${typeCode}").val()+$("#mmFrom_popup30100_${typeCode}").val();
		param+="&ad_date_to_hhmm="+$("#hhTo_popup30100_${typeCode}").val()+$("#mmTo_popup30100_${typeCode}").val();
	}else{
		param+="&ad_date_from="+$("#dateFrom_popup30100_${typeCode}").val();
		param+="&ad_date_to="+$("#dateTo_popup30100_${typeCode}").val();
		param+="&ad_date_from_hhmm=0000";
		param+="&ad_date_to_hhmm=0000";
	}
	
	//alert(param);

	$.ajax({
	   global :true,
	   type : "POST",
	   async : true,
	   cache : false,
	   url  : "/approval/save",
	   dataType :"json",
	   data  	 : param,
	   success  : function(data){
			if(data.dto[0].res_code=='-1' ){
				if($("#save_type_30100_${typeCode}").val()!='DELETE')
					alert('처리되지 않았습니다.');
				else
					alert('삭제되지 않았습니다.');
				return;
			}else if(data.dto[0].res_code=='0'){
				if($("#save_type_30100_${typeCode}").val()!='DELETE'){
					if(data.dto[0].res_am_code != ""){
						fnSaveFile30100_${typeCode}(data.dto[0].res_am_code);
					}else{
						alert("저장실패.");
					}
					//alert('저장 되었습니다.');
				}else
					alert('삭제 되었습니다.');
				
				fnSelectList_30100_${typeCode}(approvalPg_30100_${typeCode}, "");
				
				//닫기
				$("#tempClose30100_${typeCode}").click();
				return;
			}else if(data.dto[0].res_code=='-2' ){ //중복 체크
				alert('해당 기간에 상신건이 존재합니다.\n확인 하세요.');
				return;
			}
	   },
	   error : function(XMLHttpRequest, textStatus, error){
		alert("서버 통신 중 에러가 발생했습니다.");
	   }
	});
}

function fnSaveFile30100_${typeCode}(am_code){
	//alert("am_code : " + am_code);
	
	var am_no = $("#am_no_30100_${typeCode}").val();
	//alert("am_no : " + am_no);
	var formData = new FormData();
	
	if(approvalfileList_30100_${typeCode}.length > 0) {
		for (var i = 0; i < approvalfileList_30100_${typeCode}.length; i++) {
			formData.append('files', approvalfileList_30100_${typeCode}[i]);
		}
	}else{
		alert("상신완료 되었습니다.");
		$("#tempClose30100_${typeCode}").click();
		fnSelectList_30100_${typeCode}(approvalPg_30100_${typeCode}, "");
		fnInitPopup30100_${typeCode}();
		return;
	}
	formData.append('am_code', am_code);
	formData.append('am_no', am_no);
	$.ajax({
		url : "/approval/saveFiles",
		data : formData,
		type : "POST",
		dataType : "json",
		async : false,
		global: true,		
		cache: false,
        contentType: false,
        processData: false,
        beforeSend :function(){
			  fnSetAjaxFileLoding();
		},
		success : function(data) {
			//alert("data" + data);
			if(data > 0 ){
				$("#tempClose30100_${typeCode}").click();
				fnSelectList_30100_${typeCode}(approvalPg_30100_${typeCode}, "");
				fnInitPopup30100_${typeCode}();
			
				approvalfileList_30100_${typeCode} = []; //새로 추가
				fileId = 1;	//새로 추가
				$("#approvalFile_30100_${typeCode}").file({fileList : approvalfileList_30100_${typeCode}});  //새로 추가
				
				alert("상신완료 되었습니다.");
			}else{
				alert("파일저장실패.");
			}
			
		}
	}); 
}

/**
 * @함수명: cboType30100_${typeCode}
 * @작성일: 2015. 10. 19.
 * @작성자: 최수영
 * @설명: 결재유형 콤보 리스트
 * @param 
 */

function cboType30100_${typeCode}(){
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
		$("#ad_type_combo_30100_${typeCode}").html(listHtml);
	    
	    }
	});
}
/**
 * @함수명: fnInitMain30100_${typeCode}
 * @작성일: 2015. 10. 19
 * @작성자: 
 * @설명: 메인화면 Init.
 * @param 
 */
function fnInitMain30100_${typeCode}(){
	
	var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
	tmpDate=tmpDate.substring(0,8)+"16";
	$('#dateFrom_search30100_${typeCode}').val(tmpDate);
	
	var today = tmpDate.replace('-','').replace('-','');
	var user_date = new Date(today.substring(0,4),today.substring(4,6),today.substring(6,8));
	var year = user_date.getFullYear();
	var month = user_date.getMonth() + 1;
	var date = user_date.getDate() - 1; 
	if(month<10) month = "0" + month; 
	if(date<10) date = "0" + date; 
	var result = year + "-" + month + "-" + date; 
	
	$('#dateTo_search30100_${typeCode}').val(result);
	
	//$('#tbody30100_${typeCode}').html('');
	
	fnSelectList_30100_${typeCode}("1", "");
	
}

/**
 * @함수명: fnInitPopup30100_${typeCode}
 * @작성일: 2015. 10. 19
 * @작성자: 
 * @설명: 팝업 컨트롤 init.
 * @param 
 */
function fnInitPopup30100_${typeCode}(){
	//결재유형
	cboType30100_${typeCode}();
	
	var tmpDate=$.datepicker.formatDate($.datepicker.ATOM, new Date());
	//상신일자
	$('#am_approval_date_30100_${typeCode}').val(tmpDate.replace('-','').replace('-',''));
	$('#am_approval_date_txt_30100_${typeCode}').text(tmpDate);
	
	$('#txt_updt_de_30100_${typeCode}').text('');
	
	//근태일자
	$('#dateFrom_popup30100_${typeCode}').val(tmpDate);
	$('#dateTo_popup30100_${typeCode}').val(tmpDate);
	$('#date_popup30100_${typeCode}').val(tmpDate);
	$('#txtDays30100_${typeCode}').text("(1일)");
	
	//결재유형
	$('#ad_type_combo_30100_${typeCode}').val("");
	$("#ad_type_combo_30100_${typeCode}").prop('disabled',false);
	$("#tr1_30100_${typeCode}").css("display", "");
	$("#tr2_30100_${typeCode}").css("display", "none");
	
	//사유
	$('#ad_reason_30100_${typeCode}').val("");
	
	//첨부파일번호
	$('#am_no_30100_${typeCode}').val("");
	
	
	$("#fileViewList_30100_${typeCode}").html("");
	
	//반려이력
	$("#rejectHistory30100_${typeCode}").html('');
	
	//반려이력
	$("#approvalFile_30100_${typeCode}ItemList").html('');
	
	
	
}

</script>
