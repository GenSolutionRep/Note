<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String type = (String) request.getAttribute("type");
%>
<script>
/**************************************************************************************************
 * @파일명: 60-100js.jsp
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 JS를 담고있는 JSP
**************************************************************************************************/
var request${type} = "${type}";//고정MD(FIX), 순회MD(RND), 팀장(TIMHDR), 관리자(MNGR)
var employeePg${type} = 1;

/**
 * @함수명: ready
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: DOM객체가 로드된 후에 실행
 */
$(document).ready(function() {
	fnMakeComponentEvent${type}();
	
	fnGetEmployeeList${type}(employeePg${type}, request${type});
});

/**
 * @함수명: fnMakeComponentEvent${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 컴포넌트 이벤트등록
 */
function fnMakeComponentEvent${type}() {
	//팝업창 등록
	$("#employeeSavePop${type}").instancePopUp();//팝업창 등록(사원정보 등록)
	$("#employeeViewPop${type}").instancePopUp();//팝업창 등록(사원정보 상세)
	$("#employeeAddressPop${type}").instancePopUp();//팝업창 등록(주소검색)
	$("#hrHistorySavePop${type}").instancePopUp();//팝업창(인사기록 등록)
	
	//팝업창열기 버튼
	//팝업창열기 버튼(사원정보 등록)
	$("#employeeSaveInsPopOpen${type}").click(function() {
		fnClearEmployeeSave${type}();
		
		$("#employeeSaveSubTitle${type}").text("사원 등록");
		$("#employeeSaveType${type}").val("INSERT");
		
		fnMakeComboBoxBhfTeam${type}("", 1, "bhf_nm${type}s");//콤보박스 생성(지점)
		fnMakeComboBoxRspofc${type}();//콤보박스 생성(직책)
		fnMakeComboBoxDty${type}(request${type});//콤보박스 생성(직무)
		fnMakeComboBoxCldr${type}();//콤보박스 생성(달력)

    	$("#em_password${type}s").prop("disabled", false);
    	$("#em_password${type}ss").prop("disabled", false);
    	$("#em_password${type}ss").show();
    	$("#em_password${type}sss").show();
    	
    	$("#em_dty_code${type}s").change();
    	
    	if (auth_flag <= 2) {
    		//슈퍼유저,관리자,팀장
    		$("#employeeStatusManage${type}s").show();
    		$("#employeeStatusManage2${type}s").show();
    	} else {
    		//고정MD,순회MD
    		$("#employeeStatusManage${type}s").hide();
    		$("#employeeStatusManage2${type}s").hide();
    	}
    	
    	$("#employeeSavePop${type}").show();
	});
	//팝업창열기 버튼(사원정보 수정)
	$("#employeeSaveUpdPopOpen${type}").click(function() {
		fnClearEmployeeSave${type}();
		
		$("#employeeSaveSubTitle${type}").text("사원 수정");
		$("#employeeSaveType${type}").val("UPDATE");
		
		fnMakeComboBoxBhfTeam${type}("", 1, "bhf_nm${type}s");//콤보박스 생성(지점)
		fnMakeComboBoxRspofc${type}();//콤보박스 생성(직책)
		fnMakeComboBoxDty${type}(request${type});//콤보박스 생성(직무)
		fnMakeComboBoxCldr${type}();//콤보박스 생성(달력)

		var em_no = $("#employeeEmployeeNo${type}").val();
		$.ajax({
			url : "/employee/getEmployeeView",
		    data : {"em_no" : em_no},
		    dataType : "json",
		    type : "POST",
		    async : false,
		    cache : false,
		    success : function(data) {
		    	var employeeVo = data.employeeVo;
		    	$("#bhf_nm${type}s").val(employeeVo.bhf_code);
		    	fnMakeComboBoxBhfTeam${type}(employeeVo.bhf_code, 2, "");//콤보박스 생성(팀)
		    	$("#team_nm${type}s").val(employeeVo.team_code);
		    	
		    	var img = $("<img id='em_image_url${type}s' class='photo-face' width='134px' height='142px'/>");
				if (employeeVo.image_url != null && employeeVo.image_url != "") {
					img.attr("src", employeeVo.image_url);
				} else {
					img.attr("src", "/resources/images/thum_profile.png");
				}
				$("#em_image${type}s").html(img);
				
		    	$("#employeeEmployeeAmNo${type}").val(employeeVo.am_no);
		    	$("#em_nm${type}s").val(employeeVo.em_nm);
		    	$("#em_id${type}s").val(employeeVo.em_id);
		    	$("#em_password${type}s").val(employeeVo.em_password);
		    	$("#em_password${type}s").prop("disabled", true);
		    	$("#em_password${type}ss").val(employeeVo.em_password);
		    	$("#em_password${type}ss").prop("disabled", true);
		    	$("#em_password${type}ss").hide();
		    	$("#em_password${type}sss").hide();
		    	$("#em_ecny_de${type}s").val(employeeVo.em_ecny_de_nm);
		    	$("#em_retire_de${type}s").val(employeeVo.em_retire_de_nm);
		    	$("#em_rspofc_code${type}s").val(employeeVo.em_rspofc_code);
		    	$("#em_dty_code${type}s").val(employeeVo.em_dty_code);
		    	$("#em_dty_code${type}s").change();
		    	if (employeeVo.em_ampm_at == "A") {
		    		$("input:radio[id=em_ampm_at${type}sA]").attr("checked", true);
		    	} else if (employeeVo.em_ampm_at == "P1") {
		    		$("input:radio[id=em_ampm_at${type}sP1]").attr("checked", true);
		    	} else if (employeeVo.em_ampm_at == "P2") {
		    		$("input:radio[id=em_ampm_at${type}sP2]").attr("checked", true);
		    	}
		    	$("#em_brthdy${type}s").val(employeeVo.em_brthdy_nm);
		    	$("#em_cldr${type}s").val(employeeVo.em_cldr);
		    	if (employeeVo.em_sexdstn == "F") {
		    		$("input:radio[id=em_sexdstn${type}sF]").attr("checked", true);
		    	} else {
		    		$("input:radio[id=em_sexdstn${type}sM]").attr("checked", true);
		    	}
		    	$("#em_mbtl_num${type}s").val(employeeVo.em_mbtl_num);
		    	$("#em_mrnry_de${type}s").val(employeeVo.em_mrnry_de_nm);
		    	$("#em_trans_fee${type}s").val(numberWithCommas(employeeVo.em_trans_fee));
		    	if (employeeVo.em_mbtl_open_at == "Y") {
		    		$("input:radio[id=em_mbtl_open_at${type}sY]").attr("checked", true);
		    	} else {
		    		$("input:radio[id=em_mbtl_open_at${type}sN]").attr("checked", true);
		    	}
		    	if (employeeVo.use_at == "Y") {
		    		$("input:radio[id=use_at${type}sY]").attr("checked", true);
		    	} else {
		    		$("input:radio[id=use_at${type}sN]").attr("checked", true);
		    	}
		    	if (employeeVo.em_certify_at == "Y") {
		    		$("input:radio[id=em_certify_at${type}sY]").attr("checked", true);
		    	} else {
		    		$("input:radio[id=em_certify_at${type}sN]").attr("checked", true);
		    	}
		    	$("#em_zipcd${type}s").val(employeeVo.em_zipcd);
		    	$("#em_adres${type}s").val(employeeVo.em_adres);
		    	$("#em_etcadres${type}s").val(employeeVo.em_etcadres);
		    	$("#em_dtadres${type}s").val(employeeVo.em_dtadres);
		    }
		});
		
    	if (auth_flag <= 2) {
    		//슈퍼유저,관리자,팀장
    		$("#employeeStatusManage${type}s").show();
    		$("#employeeStatusManage2${type}s").show();
    	} else {
    		//고정MD,순회MD
    		$("#employeeStatusManage${type}s").hide();
    		$("#employeeStatusManage2${type}s").hide();
    	}		
		
		$("#employeeSavePop${type}").addClass("pop-apn-pop");
		$("#employeeSavePop${type}").show();
	});
	//팝업창열기 버튼(인사기록 등록)
	$("#hrHistorySaveInsPopOpen${type}").click(function() {
		fnGetHrHistoryList2${type}();
	});
	
	//닫기 버튼
	$("#employeeSaveClose${type}, #employeeSaveCloseX${type}").click(function() {//닫기 버튼(사원정보 등록)
		$("#employeeSavePop${type}").removeClass("pop-apn-pop");
		$("#employeeSavePop${type}").hide();
	});
	$("#employeeViewClose${type}, #employeeViewCloseX${type}").click(function() {//닫기 버튼(사원정보 상세조회)
		fnGetEmployeeList${type}(employeePg${type}, request${type});
		$("#em_password${type}").hide();
		$("#employeePasswordChange${type}r").hide();
		$("#employeeViewPop${type}").hide();
	});
	$("#employeeAddressCloseX${type}").click(function() {//닫기 버튼(주소검색)
		$("#employeeAddressSearch${type}").html("");
		$(".keyword").val("");
		$("#employeeAddressPop${type}").hide();
	});
	$("#hrHistorySaveClose${type}, #hrHistorySaveCloseX${type}").click(function() {//닫기 버튼(인사기록 등록)
		fnGetHrHistoryList${type}($("#employeeEmployeeNo${type}").val());
		$("#hrHistorySavePop${type}").hide();
	});
	
	//조회 버튼(사원정보 조회)
	$("#employeeSearch${type}").click(function() {
		fnGetEmployeeList${type}(1, request${type});
	});
	
	//액셀다운로드 버튼
	$("#employeeExcelDown${type}").click(function() {
		fnGetEmployeeExcelDown${type}(request${type});
	});
	
	//저장 버튼
	$("#employeeSaveAction${type}").click(function() {//저장 버튼(사원정보 등록)
		fnSetEmployee${type}();
	});
	$("#employeeSavePasswordAction${type}").click(function() {//저장 버튼(사원정보 비밀번호변경)
		fnSetEmployeePassword${type}();
	});
	$("#hrHistorySaveAction${type}").click(function() {//저장 버튼(인사기록 등록)
		fnSetHrHistory${type}();
	});
	
	//추가 버튼(인사기록 행추가)
	$("#hrHistoryAddRow${type}").click(function() {
		fnHrHistoryAddRow${type}();
	});	
	
	//검색조건초기화 버튼(사원정보 조회)
	$("#employeeClearSearch${type}").click(function() {
		$("#employeeSearchBranch${type}").val(0);//검색콤보박스(지점)
		$("#employeeSearchName${type}").val("");//검색값(이름)
		$("#employeeSearchId${type}").val("");//검색값(ID)
		$("#employeeSearchKey${type}").val(0);//검색콤보박스(입사일,생일,결혼기념일)
		$("#employeeSearchValueFrom${type}").val("");//검색값(시작일)
		$("#employeeSearchValueTo${type}").val("");//검색값(종료일)
		$("input:checkbox[id=searchUseAt${type}]").attr("checked", false);//퇴사자
		fnGetEmployeeList${type}(1, request${type});;
	});
	
	//주소검색 버튼
	$("#employeeSearchAddress${type}").click(function() {
		$postOption.insertPostcode5 = "#em_zipcd${type}s";
		$postOption.insertAddress = "#em_adres${type}s";
		$postOption.insertExtraInfo = "#em_etcadres${type}s";
		$postOption.insertDetails = "#em_dtadres${type}s";
					
		$("#employeeAddressPop${type}").show();
	});
	$("#employeeAddressSearch${type}").postcodify( {
		controls : "#employeeAddressSearchControl${type}",
		afterSelect : function(selectedEntry) { 
			$("#employeeAddressSearch${type}").html("");
			$(".keyword").val("");
			$("#employeeAddressPop${type}").hide();
		},
		hideOldAddresses : false,
		mapLinkProvider : 'daum'
	});
	
	//달력함수
	$("#employeeSearchValueFrom${type}").winCal(baseOptions);//달력(검색 시작일)
	$("#employeeSearchValueTo${type}").winCal(baseOptions);//달력(검색 종료일)
	$("#em_ecny_de${type}s").winCal(baseOptions);//달력(입사일)
	$("#em_retire_de${type}s").winCal(baseOptions);//달력(퇴사일)
	$("#em_brthdy${type}s").winCal( {language:'ko',format:"yyyy-mm-dd",startView:4,minView:2,todayBtn:0} );//달력(생년월일)
	$("#em_mrnry_de${type}s").winCal(baseOptions);//달력(결혼기념일)
	
	//TAB설정
	//사원정보TAB
	$("#employeeViewTab${type} li:eq(0)").click(function() {
		$("#employeeViewTab${type} li:eq(0)").addClass("tab_on");
		$("#employeeViewTab${type} li:eq(1)").removeClass("tab_on");
		$("#employeeViewTab${type} li:eq(2)").removeClass("tab_on");
		
		$("#employeeViewSubTableA${type}").show();
		$("#employeeViewSubTableB${type}").hide();
		$("#employeeViewSubTableC${type}").hide();
		
		$("#employeeSaveUpdPopOpen${type}s").show();//팝업창열기 버튼(사원정보 수정)
		$("#hrHistorySaveInsPopOpen${type}s").hide();//팝업창열기 버튼(인사기록 등록)
		
		var em_no = $("#employeeEmployeeNo${type}").val();
		
		//버튼 비활성화
		var view_flag = true;
		if (request${type} == "MNGR") {//사원관리>관리자>상세조회 화면에 들어왔을 때
			if (auth_flag > 1) {//관리자권한을 가지지 않은 로그인유저인 경우
				view_flag = false;
			}
		}
		if (request${type} == "TIMHDR") {//사원관리>팀장>상세조회 화면에 들어왔을 때
			if (auth_flag > 2) {//팀장권한을 가지지 않은 로그인유저인 경우
				view_flag = false;
			} else if (auth_flag == 2 && login_no != em_no) {//팀장권한을 가진 로그인유저의 사번이 상세조회하는 사번과 같지 않을 때
				$("#hrHistorySaveInsPopOpen${type}s").hide();//인사기록 등록버튼 비활성화
				view_flag = false;
			}
		}
		if (request${type} == "FIX") {//사원관리>고정MD>상세조회 화면에 들어왔을 때
			if (auth_flag == 4) {//순회MD권한을 가진 로그인유저인 경우
				view_flag = false;
			} else if (auth_flag == 3 && login_no != em_no) {//고정MD권한을 가진 로그인유저의 사번이 상세조회하는 사번과 같지 않을 때
				view_flag = false;
			}
		}
		if (request${type} == "RND") {//사원관리>순회MD>상세조회 화면에 들어왔을 때
			if (auth_flag == 3) {//고정MD권한을 가진 로그인유저인 경우
				view_flag = false;
			} else if (auth_flag == 4 && login_no != em_no) {//순회MD권한을 가진 로그인유저의 사번이 상세조회하는 사번과 같지 않을 때
				view_flag = false;
			}
		}
		
		if (view_flag == false) {
			$("#employeePasswordOpen${type}").hide();//비밀번호 보기버튼 비활성화
			$("#employeePasswordChange${type}").hide();//비밀번호 변경버튼 비활성화
			$("#employeeSaveUpdPopOpen${type}s").hide();//사원정보 수정버튼 비활성화
		}
		
	});
	//인사기록TAB
	$("#employeeViewTab${type} li:eq(1)").click(function() {
		$("#employeeViewTab${type} li:eq(0)").removeClass("tab_on");
		$("#employeeViewTab${type} li:eq(1)").addClass("tab_on");
		$("#employeeViewTab${type} li:eq(2)").removeClass("tab_on");
		
		$("#employeeViewSubTableA${type}").hide();
		$("#employeeViewSubTableB${type}").show();
		$("#employeeViewSubTableC${type}").hide();
		
		$("#employeeSaveUpdPopOpen${type}s").hide();//팝업창열기 버튼(사원정보 수정)
		$("#hrHistorySaveInsPopOpen${type}s").show();//팝업창열기 버튼(인사기록 등록)
		
		//인사기록 목록조회
		fnGetHrHistoryList${type}($("#employeeEmployeeNo${type}").val());
	});
	//근무매장TAB
	$("#employeeViewTab${type} li:eq(2)").click(function() {
		$("#employeeViewTab${type} li:eq(0)").removeClass("tab_on");
		$("#employeeViewTab${type} li:eq(1)").removeClass("tab_on");
		$("#employeeViewTab${type} li:eq(2)").addClass("tab_on");
		
		$("#employeeViewSubTableA${type}").hide();
		$("#employeeViewSubTableB${type}").hide();
		$("#employeeViewSubTableC${type}").show();
		
		$("#employeeSaveUpdPopOpen${type}s").hide();//팝업창열기 버튼(사원정보 수정)
		$("#hrHistorySaveInsPopOpen${type}s").hide();//팝업창열기 버튼(인사기록 등록)
		
		//근무매장 목록조회
		fnGetWorkingStoreList${type}($("#employeeEmployeeNo${type}").val());
	});
	//사원정보TAB 활성화
	$("#employeeViewTab${type} li:eq(0)").click();
	
	//초기화
	$("#mainTitle${type}").text($("#inputHiddenTitle").val());
	$("#subTitle${type}").text($("#inputHiddenTitle").val());
	$("#employeeSearchBranch${type}").html("<option value=''>지점</option>");
	$("#bhf_nm${type}s").html("<option value=''>지점</option>");
	$("#team_nm${type}s").html("<option value=''>팀</option>");
	$("#em_rspofc_code${type}s").html("<option value=''>직책</option>");
	$("#em_dty_code${type}s").html("<option value=''>직무</option>");
	$("#em_cldr${type}s").html("<option value=''>달력</option>");
	fnMakeComboBoxBhfTeam${type}("", 1, "employeeSearchBranch${type}");
	$("#bhf_nm${type}s").change(function() {
		$("#team_nm${type}s").html("<option value=''>팀</option>");
		if ($(this).val() != "") {
			fnMakeComboBoxBhfTeam${type}($(this).val(), 2, "");//콤보박스 생성(팀)
		}
	});
	$("#bhf_nm${type}s").click(function() {
		$("#team_nm${type}s").html("<option value=''>팀</option>");
		if ($(this).val() != "") {
			fnMakeComboBoxBhfTeam${type}($(this).val(), 2, "");//콤보박스 생성(팀)
		}
	});
	$("#em_dty_code${type}s").change(function() {
		if ($("#em_dty_code${type}s").val() == "0000000006") {//고정MD
			$("#em_ampm_at${type}ss").show();
		} else {
			$("#em_ampm_at${type}ss").hide();
		}
	});
	$("#employeePasswordOpen${type}").click(function() {
		$("#em_password${type}").show();
	});
	$("#employeePasswordChange${type}").click(function() {
		$("#em_password${type}r").val("");
		$("#em_password${type}rr").val("");
		$("#employeePasswordChange${type}r").show();
	});
	$("#em_image_preview${type}s").change(function() {
		addPhotoFile${type}(this);
	});
	$("#em_trans_fee${type}s").keyup(function() {
		var em_trans_fee = $.trim($("#em_trans_fee${type}s").val());
		em_trans_fee = em_trans_fee.replace(/,/g, "");
		if (em_trans_fee == "") {
			$("#em_trans_fee${type}s").focus();
			return;
		}
		if (!checkNumber(em_trans_fee)) {
			alert("기준교통비는 숫자만 입력가능합니다.");
			$("#em_trans_fee${type}s").val("");
			$("#em_trans_fee${type}s").focus();
			return;
		}
		em_trans_fee = parseInt(em_trans_fee, 10);
		$("#em_trans_fee${type}s").val(numberWithCommas(em_trans_fee));
	});
}

/**
 * @함수명: fnGetEmployeeList${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 목록조회
 */
function fnGetEmployeeList${type}(currPg, type) {
	employeePg${type} = currPg;//현재페이지 리로드
	var fnName = "fnGetEmployeeList${type}";//페이징처리 함수명
	var em_dty_code = "";
	if (type == "FIX") {
		em_dty_code = "0000000006";//고정MD
	} else if (type =="RND") {
		em_dty_code = "0000000007";//순회MD
	} else if (type == "TIMHDR") {
		em_dty_code = "0000000008";//팀장
	} else if (type == "MNGR") {
		em_dty_code = "0000000009";//관리자
	}
	
	var searchBranch = $.trim($("#employeeSearchBranch${type}").val());//검색콤보박스(지점)
	var searchName = $.trim($("#employeeSearchName${type}").val());//검색값(이름)
	var searchId = $.trim($("#employeeSearchId${type}").val());//검색값(ID)
	var searchKey = $.trim($("#employeeSearchKey${type}").val());//검색콤보박스(입사일,생일,결혼기념일)
	var searchValueFrom = $.trim($("#employeeSearchValueFrom${type}").val());//검색값(시작일)
	var searchValueTo = $.trim($("#employeeSearchValueTo${type}").val());//검색값(종료일)
	var searchUseAt;
	var searchUseAt${type} = $("input:checkbox[id=searchUseAt${type}]").attr("checked");//퇴사자
	if (searchUseAt${type} == "checked") {
		searchUseAt = "RETIRE";
	} else {
		searchUseAt = "WORK";
	}
	
	var params = {
			em_dty_code : em_dty_code,
			searchCompany : login_cp_cd,
			searchBranch : searchBranch,
			searchStoreCode : "",
			searchStoreName : "",
			searchName : searchName,
			searchId : searchId,
			searchKey : searchKey,
			searchValueFrom : searchValueFrom,
			searchValueTo : searchValueTo,
			searchUseAt : searchUseAt,
			exceptEmployeeNo : ""
	};
	
	var rowSize = 16;//표시할 행수
	var rowSizePerLine = 8;//화면의 1라인에 표시할 행수
	
	$.ajax({
		url : "/employee/getEmployeeList",
	    data : {
	    	fnName : fnName,
	    	params : params,
	    	rowSize : rowSize,
	    	currPg : currPg,
	    	type : type
	    },
	    dataType : "json",
	    type : "POST",
	    async : true,
	    cache : false,
	    success : function(data) {
	    	var html = "";
			if (data.employeeList.length > 0) {
				for (var i = 0; i < data.employeeList.length; i++) {
					var employeeVo = data.employeeList[i];
					html += '<div class="profilebox">';
					html += '  <div style="max-width:134px; height:142px; position: relative;overflow: hidden;" >';
					if (employeeVo.image_url != null && employeeVo.image_url != "") {
						html += '    <img src="' + employeeVo.image_url + '">';
					} else {
						html += '    <img src="/resources/images/thum_profile.png">';
					}
					html += '  </div>';
					html += '  <div class="profile_con">';
					html += '    <ul>';
					html += '      <li class="p_name"><a href="#" class="employeeView" data-em_no="' + employeeVo.em_no + '">' + employeeVo.em_nm + '</a></li>';
					html += '      <li class="p_phone"><a href="#" class="employeeView" data-em_no="' + employeeVo.em_no + '">' + employeeVo.em_mbtl_num + '</a></li>';
					if (type == "FIX") {
						html += '  <li class="p_memo"><a href="#" class="employeeView" data-em_no="' + employeeVo.em_no + '">' + employeeVo.me_nm + employeeVo.sm_nm + employeeVo.em_ampm_at_nm + '</a></li>';
					}
					html += '    </ul>';
					html += '  </div>';
					html += '</div>';
					
					/*
					if ( (i+1)%rowSizePerLine == 0 && (i+1)!=data.employeeList.length ) {
						html += '<br/>';
					}
					*/
	        	}
			} else {
				html += '<div class="none-data"><i class="fa fa-info-circle"></i><span>조회된 데이터가 없습니다.</span></div>';
			}
			
			$("#employeeList${type}").html(html);
			
			$("#employeeList${type}").find("img").error(function() {
		        $(this).attr("src", "/resources/images/thum_profile.png");
		    });
			
			$("#employeeList${type}").find(".employeeView").click(function() {
				fnClearEmployeeView${type}();//사원관리 상세조회 초기화
				fnGetEmployeeView${type}($(this).attr("data-em_no"));
			});
			$("#employeeNavi${type}").html(data.navi);
	    }
	});
}

/**
 * @함수명: fnGetEmployeeView${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 상세조회
 */
function fnGetEmployeeView${type}(em_no) {
	$.ajax({
		url : "/employee/getEmployeeView",
	    data : {"em_no" : em_no},
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	var employeeVo = data.employeeVo;
	    	$("#employeeEmployeeNo${type}").val(em_no);
	    	$("#bhf_nm${type}").text(employeeVo.bhf_nm + " (" + employeeVo.team_nm + ")");
	    	
	    	var img = $("<img id='em_image_url${type}' class='photo-face' width='134px' height='142px'/>");
			if (employeeVo.image_url != null && employeeVo.image_url != "") {
				img.attr("src", employeeVo.image_url);
			} else {
				img.attr("src", "/resources/images/thum_profile.png");
			}
			$("#em_image${type}").html(img);
			
	    	$("#em_nm${type}").text(employeeVo.em_nm);
	    	$("#em_id${type}").text(employeeVo.em_id);
	    	$("#em_password${type}").text(employeeVo.em_password);
	    	$("#em_ecny_de_nm${type}").text(employeeVo.em_ecny_de_nm);
	    	if (employeeVo.use_at == "Y") {
	    		$("#em_retire_de_nm${type}").text("");
	    	} else {
	    		$("#em_retire_de_nm${type}").text(employeeVo.em_retire_de_nm);
	    	}
	    	$("#em_rspofc_nm${type}").text(employeeVo.em_rspofc_nm);
	    	$("#em_dty_nm${type}").text(employeeVo.em_dty_nm + " " + employeeVo.em_ampm_at_nm);
	    	$("#em_brthdy_nm${type}").text(employeeVo.em_brthdy_nm);
	    	$("#em_cldr_nm${type}").text(" (" + employeeVo.em_cldr_nm + ")");
	    	$("#em_sexdstn_nm${type}").text(employeeVo.em_sexdstn_nm);
	    	$("#em_mbtl_num${type}").text(employeeVo.em_mbtl_num);
	    	$("#em_mrnry_de_nm${type}").text(employeeVo.em_mrnry_de_nm);
	    	$("#em_trans_fee${type}").text(numberWithCommas(employeeVo.em_trans_fee));
	    	$("#em_mbtl_open_at_nm${type}").text(employeeVo.em_mbtl_open_at_nm);
	    	$("#use_at_nm${type}").text(employeeVo.use_at_nm);
	    	$("#em_certify_at_nm${type}").text(employeeVo.em_certify_at_nm);
	    	$("#em_zipcd${type}").text(employeeVo.em_zipcd);
	    	$("#em_adres${type}").text(employeeVo.em_adres + " " + employeeVo.em_dtadres);
	    }
	});
	
	if (auth_flag <= 2) {
		//슈퍼유저,관리자,팀장
		$("#employeeStatusManage${type}").show();
	} else {
		//고정MD,순회MD
		$("#employeeStatusManage${type}").hide();
	}	
	
	$("#employeeViewPop${type}").show();
	$("#employeeViewTab${type} li:eq(0)").click();//사원정보TAB 활성화
}

/**
 * @함수명: fnGetHrHistoryList${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 인사기록 목록조회
 */
function fnGetHrHistoryList${type}(em_no) {
	$.ajax({
		url : "/employee/getHrHistoryList",
	    data : {"em_no" : em_no},
	    dataType : "json",
	    type : "POST",
	    async : true,
	    cache : false,
	    success : function(data) {
	    	var html = "";
			if (data.hrHistoryList.length > 0) {
				for (var i = 0; i < data.hrHistoryList.length; i++) {
					var hrHistoryVo = data.hrHistoryList[i];
					html += "<tr>";
					html += "	<td class='txt_center'>" + hrHistoryVo.hm_gnfd_de_nm + "</td>";
					html += "	<td class='txt_center'>" + hrHistoryVo.hm_gnfd_cn + "</td>";
					html += "	<td class='txt_center'>" + hrHistoryVo.hm_partclr_matter + "</td>";
					html += "</tr>";					
	        	}
			} else {
				html += '<tr><td colspan="3">조회된 데이터가 없습니다.</td></tr>';
			}
			
			$("#hrHistoryList${type}").html(html);
	    }
	});
}

/**
 * @함수명: fnGetHrHistoryList2${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 인사기록 목록조회2
 */
function fnGetHrHistoryList2${type}() {
	var em_no = $("#employeeEmployeeNo${type}").val();
	$.ajax({
		url : "/employee/getHrHistoryList",
	    data : {"em_no" : em_no},
	    dataType : "json",
	    type : "POST",
	    async : true,
	    cache : false,
	    success : function(data) {
	    	var html = "";
			if (data.hrHistoryList.length > 0) {
				for (var i = 0; i < data.hrHistoryList.length; i++) {
					var hrHistoryVo = data.hrHistoryList[i];
					html += "<tr class='hrHistory-group' data-hm_innb='" + hrHistoryVo.hm_innb + "'>";
					html += "	<td class='txt_center'><div class='input_group clear'><span><input type='text' class='hrHistory-hm_gnfd_de_nm input-basic' value='" + hrHistoryVo.hm_gnfd_de_nm + "' readonly='readonly' placeholder='발령일'/></span></div></td>";
					html += "	<td class='txt_center'><div class='input_group clear'><span><input type='text' class='hrHistory-hm_gnfd_cn input-basic' value='" + hrHistoryVo.hm_gnfd_cn + "' maxlength='100' placeholder='발령내용'/></span></div></td>";
					html += "	<td class='txt_center'><div class='input_group clear'><span><input type='text' class='hrHistory-hm_partclr_matter input-basic' value='" + hrHistoryVo.hm_partclr_matter + "' maxlength='100' placeholder='특이사항'/></span></div></td>";
					html += "	<td class='txt_center'><div class='btn_gup clear'><button type='button' class='hrHistory-hm_innb skyblue' data-hm_innb='" + hrHistoryVo.hm_innb + "'>삭제</button></div></td>";
					html += "</tr>";					
	        	}
			}
			$("#hrHistoryList${type}s").html(html);
			$("#hrHistoryList${type}s").find(".hrHistory-hm_gnfd_de_nm").winCal(baseOptions);
			$("#hrHistoryList${type}s").find(".hrHistory-hm_innb").click(function() {
				if (confirm("삭제하시겠습니까?")) {
					$(this).parent().parent().parent().remove();
				}
			});
	    }
	});
	
	$("#hrHistorySavePop${type}").show();
}

/**
 * @함수명: fnGetWorkingStoreList${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 근무매장 목록조회
 */
function fnGetWorkingStoreList${type}(em_no) {
	$.ajax({
		url : "/employee/getWorkingStoreList",
	    data : {"em_no" : em_no},
	    dataType : "json",
	    type : "POST",
	    async : true,
	    cache : false,
	    success : function(data) {
	    	var html = "";
			if (data.workingStoreList.length > 0) {
				for (var i = 0; i < data.workingStoreList.length; i++) {
					var workingStoreVo = data.workingStoreList[i];
					html += "<tr>";
					html += "	<td class='txt_center'>" + workingStoreVo.cg_nm + "</td>";
					html += "	<td class='txt_center'>" + workingStoreVo.me_nm + "</td>";
					html += "	<td class='txt_center'>" + workingStoreVo.sm_nm + "</td>";
					html += "	<td class='txt_center'>" + workingStoreVo.gnfd_de + "</td>";
					html += "</tr>";					
	        	}
			} else {
				html += '<tr><td colspan="4">조회된 데이터가 없습니다.</td></tr>';
			}
			
			$("#workingStoreList${type}").html(html);
	    }
	});
}

/**
 * @함수명: fnSetEmployee${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 사원등록
 */
function fnSetEmployee${type}() {
	var file_length = $("#em_image_preview${type}s")[0].files.length;
	if (file_length == 1) {
		fnSetEmployeeImage${type}();
	}
	var am_no = $.trim($("#employeeEmployeeAmNo${type}").val());
	if (am_no == "") {
		am_no = 0;
	} else {
		am_no = parseInt(am_no, 10);
	}
	var em_no = $.trim($("#employeeEmployeeNo${type}").val());
	var bhf_code = $.trim($("#bhf_nm${type}s").val());
	if (bhf_code == "") {
		alert("지점을 선택하세요.");
		$("#bhf_nm${type}s").focus();
		return;
	}
	var team_code = $.trim($("#team_nm${type}s").val());
	if (team_code == "") {
		alert("팀을 선택하세요.");
		$("#team_nm${type}s").focus();
		return;
	} 
	var em_nm = $.trim($("#em_nm${type}s").val());
	if (em_nm == "") {
		alert("이름을 입력하세요.");
		$("#em_nm${type}s").focus();
		return;
	} 
	var em_id = $.trim($("#em_id${type}s").val());
	if (em_id == "") {
		alert("아이디를 입력하세요.");
		$("#em_id${type}s").focus();
		return;
	} 
	var em_password = $.trim($("#em_password${type}s").val());
	if (em_password == "") {
		alert("비밀번호를 입력하세요.");
		$("#em_password${type}s").focus();
		return;
	} 
	var em_passwordss = $.trim($("#em_password${type}ss").val());
	if (em_passwordss == "") {
		alert("(비밀번호 확인)을 입력하세요.");
		$("#em_password${type}ss").focus();
		return;
	} 
	if (em_password != em_passwordss) {
		alert("입력하신 두개의 비밀번호가 일치하지 않습니다.");
		$("#em_password${type}s").val("");
		$("#em_password${type}ss").val("");
		return;
	} 
	var em_ecny_de = $.trim($("#em_ecny_de${type}s").val());
	if (em_ecny_de == "") {
		alert("입사일을 선택하세요.");
		$("#em_ecny_de${type}s").focus();
		return;
	}
	var em_rspofc_code = $.trim($("#em_rspofc_code${type}s").val());
	if (em_rspofc_code == "") {
		alert("직책을 선택하세요.");
		$("#em_rspofc_code${type}s").focus();
		return;
	} 
	var em_dty_code = $.trim($("#em_dty_code${type}s").val());
	if (em_dty_code == "") {
		alert("직무를 선택하세요.");
		$("#em_dty_code${type}s").focus();
		return;
	}
	var em_ampm_at;
	var em_ampm_at${type}sA = $("input:radio[id=em_ampm_at${type}sA]").attr("checked");
	if (em_ampm_at${type}sA == "checked") {
		em_ampm_at = "A";
	}
	var em_ampm_at${type}sP1 = $("input:radio[id=em_ampm_at${type}sP1]").attr("checked");
	if (em_ampm_at${type}sP1 == "checked") {
		em_ampm_at = "P1";
	}
	var em_ampm_at${type}sP2 = $("input:radio[id=em_ampm_at${type}sP2]").attr("checked");
	if (em_ampm_at${type}sP2 == "checked") {
		em_ampm_at = "P2";
	}
	if (em_dty_code != "0000000006") {//고정MD
		em_ampm_at = "";
	}
	var em_brthdy = $.trim($("#em_brthdy${type}s").val());
	/*if (em_brthdy == "") {
		alert("생년월일을 선택하세요.");
		$("#em_brthdy${type}s").focus();
		return;
	}*/
	var em_cldr = $.trim($("#em_cldr${type}s").val());
	/*if (em_cldr == "") {
		alert("달력을 선택하세요.");
		$("#em_cldr${type}s").focus();
		return;
	}*/
	var em_sexdstn;
	var em_sexdstn${type}sF = $("input:radio[id=em_sexdstn${type}sF]").attr("checked");
	if (em_sexdstn${type}sF == "checked") {
		em_sexdstn = "F";
	} else {
		em_sexdstn = "M";
	}
	var em_mbtl_num = $.trim($("#em_mbtl_num${type}s").val());
	/*if (em_mbtl_num == "") {
		alert("휴대전화를 입력하세요.");
		$("#em_mbtl_num${type}s").focus();
		return;
	}*/
	var em_trans_fee = $.trim($("#em_trans_fee${type}s").val());
	/*if (em_trans_fee == "") {
		alert("기준교통비를 입력하세요.");
		$("#em_trans_fee${type}s").focus();
		return;
	}*/
	if (em_trans_fee == "") {
		em_trans_fee = 0;
	} else {
		em_trans_fee = em_trans_fee.replace(/,/g, "");
		em_trans_fee = parseInt(em_trans_fee, 10);
	}
	var em_mbtl_open_at;
	var em_mbtl_open_at${type}sY = $("input:radio[id=em_mbtl_open_at${type}sY]").attr("checked");
	if (em_mbtl_open_at${type}sY == "checked") {
		em_mbtl_open_at = "Y";
	} else {
		em_mbtl_open_at = "N";
	}
	var use_at;
	var use_at${type}sY = $("input:radio[id=use_at${type}sY]").attr("checked");
	if (use_at${type}sY == "checked") {
		use_at = "Y";
	} else {
		use_at = "N";
	}
	var em_certify_at;
	var em_certify_at${type}sY = $("input:radio[id=em_certify_at${type}sY]").attr("checked");
	if (em_certify_at${type}sY == "checked") {
		em_certify_at = "Y";
	} else {
		em_certify_at = "N";
	}
	var em_mrnry_de = $.trim($("#em_mrnry_de${type}s").val());
	var em_zipcd = $.trim($("#em_zipcd${type}s").val());
	/*if (em_zipcd == "") {
		alert("우편번호를 입력하세요.");
		$("#em_zipcd${type}s").focus();
		return;
	}*/
	var em_adres = $.trim($("#em_adres${type}s").val());
	/*if (em_adres == "") {
		alert("주소를 입력하세요.");
		$("#em_adres${type}s").focus();
		return;
	}*/
	var em_etcadres = $.trim($("#em_etcadres${type}s").val());
	/*if (em_etcadres == "") {
		alert("기타주소를 입력하세요.");
		$("#em_etcadres${type}s").focus();
		return;
	}*/
	var em_dtadres = $.trim($("#em_dtadres${type}s").val());
	/*if (em_dtadres == "") {
		alert("상세주소를 입력하세요.");
		$("#em_dtadres${type}s").focus();
		return;
	}*/
	//입사일과 생년월일 값비교
	if (em_ecny_de != "" && em_brthdy != "") {
		var ecny = parseInt(em_ecny_de.replace(/-/g, ""), 10);
		var brthdy = parseInt(em_brthdy.replace(/-/g, ""), 10);
		if (ecny <= brthdy) {
			alert("올바른 생년월일을 선택하세요.");
			$("#em_brthdy${type}s").focus();
			return;
		}
	}
	//휴대전화 유효성검사
	if (em_mbtl_num != "") {
		var pattern = /^([0]{1}[1]{1}[0-9]{1})-?([1-9]{1}[0-9]{2,3})-?([0-9]{4})$/;
		if (pattern.exec(em_mbtl_num)) {
			if (RegExp.$1 == "010" || RegExp.$1 == "011" || RegExp.$1 == "016" || RegExp.$1 == "017" || RegExp.$1 == "018" || RegExp.$1 == "019") {
				em_mbtl_num = RegExp.$1 + "-" + RegExp.$2 + "-" + RegExp.$3;
			} else {
				alert("올바르지 않는 전화번호 형식입니다.");
				$("#em_mbtl_num${type}s").focus();
				return;
			}
		} else {
			alert("올바르지 않는 전화번호 형식입니다.");
			$("#em_mbtl_num${type}s").focus();
			return;
		}
	}
	var em_retire_de;
	if (use_at == "Y") {//재직자
		em_retire_de = "";
	} else {//퇴사자
		em_retire_de = $.trim($("#em_retire_de${type}s").val());
		if (em_retire_de == "") {
			alert("퇴사일을 선택하세요.");
			$("#em_retire_de${type}s").focus();
			return;
		}
	}
	
	var data = {
			em_no : em_no,
			bhf_code : bhf_code,
			team_code : team_code,
			em_nm : em_nm,
			em_id : em_id,
			em_password : em_password,
			em_ecny_de : em_ecny_de,
			em_retire_de : em_retire_de,
			em_rspofc_code : em_rspofc_code,
			em_dty_code : em_dty_code,
			em_ampm_at : em_ampm_at,
			em_brthdy : em_brthdy,
			em_cldr :em_cldr,
			em_sexdstn : em_sexdstn,
			em_mbtl_num : em_mbtl_num,
			em_trans_fee : em_trans_fee,
			em_mbtl_open_at : em_mbtl_open_at,
			use_at : use_at,
			em_certify_at : em_certify_at,
			em_mrnry_de : em_mrnry_de,
			em_zipcd : em_zipcd,
			em_adres : em_adres,
			em_etcadres : em_etcadres,
			em_dtadres : em_dtadres,
			am_no : am_no
	};
	
	var saveType = $("#employeeSaveType${type}").val();
	var url;
	if (saveType == "INSERT") {
		url = "/employee/insertEmployee"; 
	} else if (saveType == "UPDATE") {
		url = "/employee/updateEmployee";
	}
	
	$.ajax({
		url : url,
	    data : data,
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	var result = data.result;
	    	if (saveType == "INSERT") {
	    		if (result != null && result == "-1") {
	    			alert("입력한 ID가 이미 존재합니다.\n다른 ID를 입력하세요.");
	    		} else if (result != null && result == "-2") {
	    			alert("입력한 팀의 팀장이 이미 존재합니다.\n팀장은 1명만 가능합니다.");
	    		} else if (result != null && result.length > "0") {
		    		$("#employeeSavePop${type}").hide();
		    		fnGetEmployeeList${type}(1, request${type});
		    	} else {
		    		alert("사원 등록에 실패했습니다.");
		    	}
	    	} else if (saveType == "UPDATE") {
	    		if (result != null && result == "-1") {
	    			alert("입력한 ID가 이미 존재합니다.\n다른 ID를 입력하세요.");
	    		} else if (result != null && result == "-2") {
	    			alert("입력한 팀의 팀장이 이미 존재합니다.\n팀장은 1명만 가능합니다.");
	    		} else if (result != null && result.length > "0") {
		    		$("#employeeSavePop${type}").removeClass("pop-apn-pop");
		    		$("#employeeSavePop${type}").hide();
		    		fnClearEmployeeView${type}();
					fnGetEmployeeView${type}(em_no);
		    	} else {
		    		alert("사원 수정에 실패했습니다.");
		    	}
	    	}
	    }
	});
}

/**
 * @함수명: fnSetEmployeeImage${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 프로필사진 등록
 */
function fnSetEmployeeImage${type}() {
	var file = $("#em_image_preview${type}s")[0].files[0];
	var am_no = $.trim($("#employeeEmployeeAmNo${type}").val());
	if (am_no == "") {
		am_no = 0;
	} else {
		am_no = parseInt(am_no, 10);
	}
	
	var formData = new FormData();
	formData.append("files", file);
	formData.append("am_no", am_no);
	
	$.ajax({
        url : "/employee/setProfilePicture",
		data : formData,
		dataType : "json",
		type : "POST",
		async : false,
		cache: false,
        contentType: false,
        processData: false,
		success : function(data) {
	    	var result = data.result;
			if (result != null && result.length > "0") {
				//alert("[am_no=" + result + "]프로필 사진을 등록하였습니다.");
				$("#employeeEmployeeAmNo${type}").val(result);
			}
		}
	}); 
}

/**
 * @함수명: fnSetEmployeePassword${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 비밀번호변경
 */
function fnSetEmployeePassword${type}() {
	var em_no = $.trim($("#employeeEmployeeNo${type}").val());
	
	var em_password = $.trim($("#em_password${type}r").val());
	if (em_password == "") {
		alert("비밀번호를 입력하세요.");
		$("#em_password${type}r").focus();
		return;
	} 
	var em_passwordrr = $.trim($("#em_password${type}rr").val());
	if (em_passwordrr == "") {
		alert("(비밀번호 확인)을 입력하세요.");
		$("#em_password${type}rr").focus();
		return;
	} 
	if (em_password != em_passwordrr) {
		alert("입력하신 두개의 비밀번호가 일치하지 않습니다.");
		$("#em_password${type}r").val("");
		$("#em_password${type}rr").val("");
		return;
	}
	
	$.ajax({
		url : "/employee/changePassword",
	    data : {
			em_no : em_no,
			em_password : em_password
	    },
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	var result = data.result;
			if (result != null && result.length > "0") {
				alert("비밀번호를 변경하였습니다.");
				$("#em_password${type}").text(em_password);
				$("#em_password${type}").hide();
				$("#employeePasswordChange${type}r").hide();
			}
	    }
	});
}

/**
 * @함수명: fnMakeComboBoxBhfTeam${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 콤보박스 생성(지점,팀)
 */
function fnMakeComboBoxBhfTeam${type}(om_code, om_orgnzt_se, htmlTagId) {
    $.ajax({
        url : "/organization/auth",
        data:{
            om_code : om_code,
            om_orgnzt_se : om_orgnzt_se
        },
        dataType : "json",
        type : "POST",
        async: false,
        cache : false ,
        success : function(data) {
            var html = "";
            if (data.result.length > 0) {
                for (var i = 0; i < data.result.length; i++) {
                    var organizationVo = data.result[i];
                    html += "<option value ='" + organizationVo.om_code + "'>" + organizationVo.om_nm + "</option>";
                }
            }

            if (om_orgnzt_se == 1) {
                //지점인 경우
                if (htmlTagId == "bhf_nm${type}s") {
                    $("#bhf_nm${type}s").html(html);
                } else if (htmlTagId == "employeeSearchBranch${type}") {
                    $("#employeeSearchBranch${type}").html(html);
                }
            } else if (om_orgnzt_se == 2) {
                //팀인 경우
                $("#team_nm${type}s").html(html);
            }
        }
    });
}

/**
 * @함수명: fnMakeComboBoxRspofc${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 콤보박스 생성(직책)
 */
function fnMakeComboBoxRspofc${type}() {
	$.ajax({
		url : "/code/codeComboBox",
	    data : {"c_parent_code" : "0000000001"},
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	var html = "<option value='' selected='selected'>직책</option>";
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var codeVo = data[i];     
					html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
	        	}
			}
			
			$("#em_rspofc_code${type}s").html(html);
	    }
	});
}

/**
 * @함수명: fnMakeComboBoxDty${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 콤보박스 생성(직무)
 */
function fnMakeComboBoxDty${type}(request${type}) {
	var saveType = $("#employeeSaveType${type}").val();
	
	$.ajax({
		url : "/code/codeComboBox",
	    data : {"c_parent_code" : "0000000002"},
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	var html = "";
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var codeVo = data[i];
					
					if (saveType == "INSERT") {//사원정보 등록
						
						if (request${type} == "FIX" && codeVo.c_code == "0000000006") {
							//사원관리>고정MD 화면에 들어왔을 때
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						} else if (request${type} == "RND" && codeVo.c_code == "0000000007") {
							//사원관리>순회MD 화면에 들어왔을 때
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						} else if (request${type} == "TIMHDR" && codeVo.c_code == "0000000008") {
							//사원관리>팀장 화면에 들어왔을 때
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						} else if (request${type} == "MNGR" && codeVo.c_code == "0000000009") {
							//사원관리>관리자 화면에 들어왔을 때
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						}
						
					} else if (saveType == "UPDATE") {//사원정보 수정
						
						if (request${type} == "FIX" && auth_flag == 3 && codeVo.c_code == "0000000006") {
							//사원관리>고정MD 화면에 들어왔을 때, 고정MD권한을 가진 로그인유저인 경우
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						}
						if (request${type} == "FIX" && auth_flag <= 2 && (codeVo.c_code == "0000000006" || codeVo.c_code == "0000000007" || codeVo.c_code == "0000000008")) {
							//사원관리>고정MD 화면에 들어왔을 때, 팀장이상권한을 가진 로그인유저인 경우
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						}
						if (request${type} == "RND" && auth_flag == 4 && codeVo.c_code == "0000000007") {
							//사원관리>순회MD 화면에 들어왔을 때, 순회MD권한을 가진 로그인유저인 경우
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						}
						if (request${type} == "RND" && auth_flag <= 2 && (codeVo.c_code == "0000000006" || codeVo.c_code == "0000000007" || codeVo.c_code == "0000000008")) {
							//사원관리>순회MD 화면에 들어왔을 때, 팀장이상권한을 가진 로그인유저인 경우
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						}
						if (request${type} == "TIMHDR" && auth_flag <= 2 && (codeVo.c_code == "0000000006" || codeVo.c_code == "0000000007" || codeVo.c_code == "0000000008")) {
							//사원관리>팀장 화면에 들어왔을 때, 팀장이상권한을 가진 로그인유저인 경우
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						}
						if (request${type} == "MNGR" && auth_flag <= 1 && codeVo.c_code == "0000000009") {
							//사원관리>관리자 화면에 들어왔을 때, 관리자이상권한을 가진 로그인유저인 경우
							html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
						}
						
					}
	        	}
			}
			
			$("#em_dty_code${type}s").html(html);
	    }
	});
}

/**
 * @함수명: fnMakeComboBoxCldr${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 콤보박스 생성(달력)
 */
function fnMakeComboBoxCldr${type}() {
	$.ajax({
		url : "/code/codeComboBox",
	    data : {"c_parent_code" : "0000000003"},
	    dataType : "json",
	    type : "POST",
	    async : false,
	    cache : false,
	    success : function(data) {
	    	var html = "";
			if (data.length > 0) {
				for (var i = 0; i < data.length; i++) {
					var codeVo = data[i];     
					html += "<option value='"+codeVo.c_code+"'>"+codeVo.c_name + "</option>";
	        	}
			}
			
			$("#em_cldr${type}s").html(html);
	    }
	});
}

/**
 * @함수명: fnClearEmployeeView${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 상세조회 초기화
 */
function fnClearEmployeeView${type}() {
	$("#bhf_nm${type}").text("");
	$("#em_nm${type}").text("");
	$("#em_id${type}").text("");
	$("#em_password${type}").text("");
	$("#em_password${type}r").val("");
	$("#em_password${type}rr").val("");
	$("#em_ecny_de_nm${type}").text("");
	$("#em_retire_de_nm${type}").text("");
	$("#em_rspofc_nm${type}").text("");
	$("#em_dty_nm${type}").text("");
	$("#em_brthdy_nm${type}").text("");
	$("#em_sexdstn_nm${type}").text("");
	$("#em_mbtl_num${type}").text("");
	$("#em_mrnry_de_nm${type}").text("");
	$("#em_trans_fee${type}").text("");
	$("#em_mbtl_open_at_nm${type}").text("");
	$("#use_at_nm${type}").text("");
	$("#em_zipcd${type}").text("");
	$("#em_adres${type}").text("");
	$("#employeePasswordOpen${type}").show();//비밀번호 보기버튼 활성화
	$("#employeePasswordChange${type}").show();//비밀번호 변경버튼 활성화
	$("#employeeSaveUpdPopOpen${type}s").show();//사원정보 수정버튼 활성화
	$("#hrHistorySaveInsPopOpen${type}s").show();//인사기록 등록버튼 활성화
}

/**
 * @함수명: fnClearEmployeeSave${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 등록 초기화
 */
function fnClearEmployeeSave${type}() {
	$("#bhf_nm${type}s").val("");
	$("#team_nm${type}s").val("");
	$("#employeeEmployeeAmNo${type}").val("");
	$("#em_nm${type}s").val("");
	$("#em_id${type}s").val("");
	$("#em_password${type}s").val("");
	$("#em_password${type}ss").val("");
	$("#em_ecny_de${type}s").val("");
	$("#em_retire_de${type}s").val("");
	$("#em_rspofc_code${type}s").val("");
	$("#em_dty_code${type}s").val("");
	$("#em_ampm_at${type}ss").hide();
	$("input:radio[id=em_ampm_at${type}sA]").attr("checked", true);
	$("#em_brthdy${type}s").val("");
	$("input:radio[id=em_sexdstn${type}sF]").attr("checked", true);
	$("#em_mbtl_num${type}s").val("");
	$("#em_mrnry_de${type}s").val("");
	$("#em_trans_fee${type}s").val("");
	$("input:radio[id=em_mbtl_open_at${type}sY]").attr("checked", true);
	$("input:radio[id=use_at${type}sY]").attr("checked", true);
	$("input:radio[id=em_certify_at${type}sN]").attr("checked", true);
	$("#em_zipcd${type}s").val("");
	$("#em_adres${type}s").val("");
	$("#em_etcadres${type}s").val("");
	$("#em_dtadres${type}s").val("");
}

/**
 * @함수명: fnSetHrHistory${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 인사기록 등록
 */
function fnSetHrHistory${type}() {
	var em_no = $("#employeeEmployeeNo${type}").val();
	var hrHistoryGroup = $(".hrHistory-group");
	var hrHistoryArr = new Array();
	for (var i = 0; i < hrHistoryGroup.length; i++) {
		var hrHistoryVo = hrHistoryGroup.eq(i);
		var hm_innb = hrHistoryVo.attr("data-hm_innb");
		var hm_gnfd_de_nm = hrHistoryVo.find(".hrHistory-hm_gnfd_de_nm").val();
		var hm_gnfd_cn = hrHistoryVo.find(".hrHistory-hm_gnfd_cn").val();
		var hm_partclr_matter = hrHistoryVo.find(".hrHistory-hm_partclr_matter").val();
		
		if (hm_gnfd_de_nm == "") {
			alert("발령일을 선택하세요.");
			return;
		}
		if (hm_gnfd_cn == "") {
			alert("발령내용을 입력하세요.");
			$("#hrHistoryList${type}s tr:eq("+ i + ") td:nth-child(2) input:text").focus();
			return;
		}
		
		hrHistoryArr.push({
			hm_innb : hm_innb,
			em_no : em_no,
			hm_gnfd_de_nm : hm_gnfd_de_nm,
			hm_gnfd_cn : hm_gnfd_cn,
			hm_partclr_matter : hm_partclr_matter
		});
	}
	
	$.ajaxSettings.traditional = false;
	$.ajax({
		url : "/hrHistory/setHrHistory",
		data : {
			em_no : em_no,
			hrHistoryArr : hrHistoryArr
		},
	    dataType : "json",
	    type : "POST",
	    async : true,
	    cache : false,
	    global : true,
	    success : function(data) {
	    	var result = data.result;
    		if (result != null && result.length > "0") {
    			alert("저장되었습니다.");
    			fnGetHrHistoryList2${type}();
    		} else {
    			alert("저장에 실패하였습니다.");
    		}
	    }
	});
}

/**
 * @함수명: fnHrHistoryAddRow${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 인사기록 행추가
 */
function fnHrHistoryAddRow${type}() {
	var length = $("#hrHistoryList${type}s tr").length + 1;
  	var html = "";
	html += "<tr class='hrHistory-group' data-hm_innb=''>";
	html += "	<td class='txt_center'><div class='input_group clear'><span><input type='text' class='hrHistory-hm_gnfd_de_nm input-basic' value='' readonly='readonly' placeholder='발령일'/></span></div></td>";
	html += "	<td class='txt_center'><div class='input_group clear'><span><input type='text' class='hrHistory-hm_gnfd_cn input-basic' value='' maxlength='13' placeholder='발령내용'/></span></div></td>";
	html += "	<td class='txt_center'><div class='input_group clear'><span><input type='text' class='hrHistory-hm_partclr_matter input-basic' value='' maxlength='13' placeholder='특이사항'/></span></div></td>";
	html += "	<td class='txt_center'><div class='btn_gup clear'><button type='button' id='hrHistory-hm_innb${type}" + length + "' class='skyblue' data-hm_innb=''>삭제</button></div></td>";
	html += "</tr>";
	$("#hrHistoryList${type}s").append(html);
	$("#hrHistoryList${type}s").find(".hrHistory-hm_gnfd_de_nm").winCal(baseOptions);
	$("#hrHistory-hm_innb${type}" + length).click(function() {
		if (confirm("삭제하시겠습니까?")) {
			$(this).parent().parent().parent().remove();
		}
	});
}

/**
 * @함수명: addPhotoFile${type}
 * @작성일: 2015.10.01
 * @작성자: 김진호
 * @설명: 사진등록
 */
function addPhotoFile${type}(addfile) {
	if (browserCheck()) {
		var fileNm = addfile.files[0].name;
		if (checkingFileSize(addfile.files[0]) && checkingImages(fileNm)) {
			photoFilePrev${type}(addfile);
		}
	}
}

/**
 * @함수명: photoFilePrev${type}
 * @작성일: 2015.10.01
 * @작성자: 김진호
 * @설명: 사진등록후 미리보기
 */
function photoFilePrev${type}(addfile) {
	var imgFile = addfile.files[0];
	var formData = new FormData();
	formData.append('imgFile', imgFile);
	$.ajax({
        url : "/photo/prev",
		data : formData,
		type : "POST",
		dataType : "json",
		cache: false,
        contentType: false,
        processData: false,
		success : function(data) {
            var img = $("<img id='em_image_url${type}s' class='photo-face' width='134px' height='142px'/>");
			var date = new Date();
   	    	var time = date.getTime();
			img.attr("src", data.src + "?rnd=" + time);
			$("#em_image${type}s").html(img);
		}
	}); 
}

/**
 * @함수명: fnGetEmployeeExcelDown${type}
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 엑셀다운로드
 */
function fnGetEmployeeExcelDown${type}(type) {
	var em_dty_code = "";
	if (type == "FIX") {
		em_dty_code = "0000000006";//고정MD
	} else if (type =="RND") {
		em_dty_code = "0000000007";//순회MD
	} else if (type == "TIMHDR") {
		em_dty_code = "0000000008";//팀장
	} else if (type == "MNGR") {
		em_dty_code = "0000000009";//관리자
	}
	
	var searchBranch = $.trim($("#employeeSearchBranch${type}").val());//검색콤보박스(지점)
	var searchName = $.trim($("#employeeSearchName${type}").val());//검색값(이름)
	var searchId = $.trim($("#employeeSearchId${type}").val());//검색값(ID)
	var searchKey = $.trim($("#employeeSearchKey${type}").val());//검색콤보박스(입사일,생일,결혼기념일)
	var searchValueFrom = $.trim($("#employeeSearchValueFrom${type}").val());//검색값(시작일)
	var searchValueTo = $.trim($("#employeeSearchValueTo${type}").val());//검색값(종료일)
	var searchUseAt;
	var searchUseAt${type} = $("input:checkbox[id=searchUseAt${type}]").attr("checked");//퇴사자
	if (searchUseAt${type} == "checked") {
		searchUseAt = "RETIRE";
	} else {
		searchUseAt = "WORK";
	}
	
	var params = "";
	params += "?em_dty_code=" + em_dty_code;
	params += "&searchCompany=" + login_cp_cd;
	params += "&searchBranch=" + searchBranch;
	params += "&searchStoreCode=" + "";
	params += "&searchStoreName=" + "";
	params += "&searchName=" + searchName;
	params += "&searchId=" + searchId;
	params += "&searchKey=" + searchKey;
	params += "&searchValueFrom=" + searchValueFrom;
	params += "&searchValueTo=" + searchValueTo;
	params += "&searchUseAt=" + searchUseAt;
	params += "&exceptEmployeeNo=" + "";
	
	location.href = "/employee/getEmployeeExcelDown" + params;
}
</script>
