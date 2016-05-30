<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>m_interface</title>
<link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/slides.min.jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#interfaceList").find(".interfaceView").click(function() {
        var server = $.trim($("#server").val());
        var url = $(this).attr("data-url");
        var uri = server + url;
        window.open(uri);
    });
});
</script>
<style>
.cursor { cursor:pointer; }
</style>
</head>
<body>
<article id="interfaceList">
<br/>
<div class="input_group clear">
    <select id="server" class="select-basic">
        <option value="http://localhost:18080">로컬서버</option>
        <option value="http://dasa.vertexid.com:8080">개발서버</option>
        <option value="http://www.dspams.co.kr">운영서버</option>
    </select>
</div><br/><br/><br/>
<div class="interfaceView cursor" data-url="/m_getCurrentDate?flag=2">
    [00-000] 서버의 현재일자 조회
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_changePassword?em_no=00001&before_password=15fd70d6fcc8c10451b672907b4f5f50&after_password=15fd70d6fcc8c10451b672907b4f5f50">
    [00-001] 비밀번호 변경
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_changeContactOpen?em_no=00001&em_mbtl_open_at=N">
    [00-002] 개인연락처 공개여부 변경
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_setProfilePicture?em_no=00001&am_no=84">
    [00-003] 프로필 사진등록
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_getProfilePicture?em_no=00001">
    [00-004] 프로필 사진조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getCGList">
    [00-010] 고객그룹 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getMEListPerCG?cg_code=00001">
    [00-011] 고객그룹별 관리업체 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getStoreListPerME?cg_code=00001&me_code=00002">
    [00-012] 관리업체별 매장 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getStoreViewOfFIX?em_no=00004">
    [00-013] 고정MD의 매장 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getStoreListOfRND?em_no=00095&search_nm=c">
    [00-014] 순회MD의 매장 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getStoreListOfTIMHDR?om_code=00001&search_nm=a">
    [00-015] MD팀장의 매장 조회(관리업체명+매장명)
</div><br/>
<div class="interfaceView cursor" data-url="/m_getStoreListOfMNGR?search_nm=a">
    [00-016] 관리자의 매장 조회(관리업체명+매장명)
</div><br/>
<div class="interfaceView cursor" data-url="/m_getStoreListPerBranch?om_code=00001&search_nm=a">
    [00-017] 지점별 매장 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getBranchListPerCompany?cm_code=001">
    [00-020] 회사별 지점 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getEmployeeListPerBranch?om_code=00001&type=RND&search_nm=1">
    [00-021] 지점별 사원조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getEmployeeListPerStore?sm_code=01014&type=RND&search_nm=1">
    [00-022] 매장별 사원조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getAppType">
    [00-030] 결재유형 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getAppStatus">
    [00-031] 결재상태 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getOrder">
    [00-032] 차수 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getFileInfo?am_no=380">
    [00-040] 첨부파일 정보조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_fileUpload">
    [00-041] 첨부파일 업로드
</div><br/>
<div class="interfaceView cursor" data-url="/m_fileDownload?ai_no=795">
    [00-042] 첨부파일 다운로드
</div><br/>
<div class="interfaceView cursor" data-url="/m_fileDownload_apk?ai_no=798">
    [00-043] APK파일 다운로드
</div><br/>
<div class="interfaceView cursor" data-url="/m_setDeviceError?om_code=00001&sm_code=01014&em_no=00009&device_type=A&device_os_version=4.1.4&device_app_version=105&device_manufacture=LG&device_model=SCH-400&device_la=13.12343454564633434&device_lo=45.34694856045039323&device_error_code=1&device_error_msg=java.lang.NumberFormatException">
	[00-044] 디바이스오류 등록
</div><br/>
<div class="interfaceView cursor" data-url="/m_checkAppUpdate?em_device_type=A&em_device_version=104">
    [00-045] 디바이스 APP 업데이트 확인
</div><br/>
<div class="interfaceView cursor" data-url="/m_checkLogin?em_id=teama&em_password=15fd70d6fcc8c10451b672907b4f5f50&em_unique_id=123456&em_device_type=A&em_device_version=104">
    [02-010] 로그인 체크
</div><br/>
<div class="interfaceView cursor" data-url="/m_login?em_id=teama&em_password=15fd70d6fcc8c10451b672907b4f5f50&em_unique_id=123456&em_push_id=5678&em_device_type=A">
    [02-011] 로그인
</div><br/>
<div class="interfaceView cursor" data-url="/m_getMenuList?&auth_flag=1">
    [03-010] 메인화면 메뉴 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getCommunityList?auth_flag=2&cm_code=001&om_code=00001&em_no=00009&kind=MAIN&rowSize=1&currPg=1">
    [03-011] 메인화면 커뮤니케이션 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getMenuNoticeCnt?auth_flag=3&cm_code=001&em_no=00004">
	[03-012] 메인화면 메뉴 알림개수 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_setCommuteTime?type=ATTEND&em_no=00004&om_code=00001&curr_de=2015-11-11&am_no=10">
    [10-011] 고정MD 출퇴근 등록
</div><br/>
<div class="interfaceView cursor" data-url="/m_getCommuteTime?em_no=00004&search_de=2015-11-11">
    [10-012] 고정MD 출퇴근 조회
</div><br/>
<div class="interfaceView cursor" data-url="/m_getRndPlanList?em_no=00095&plan_de=2015-12-07&base_de=2015-12-03">
    [20-010] 순회MD 순방계획 조회
</div><br/>
<div class="interfaceView cursor" data-url='/m_setRndPlan?cm_code=001&om_code=00001&em_no=00095&plan_de=2015-11-25&base_de=2015-11-19&prd_partclr_matter=TEST&params=[{"prdi_sm_code":"00111","prdi_sm_code_nm":"Store111"},{"prdi_sm_code":"00222","prdi_sm_code_nm":"Store222"}]'>
    [20-020] 순회MD 순방계획 등록
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_getEmployeeList?type=FIX&rowSize=20&currPg=1&om_code=05234&sm_code=&sm_nm=&is_work=WORK&except_em_no=00001">
    [30-010] 사원현황(고정MD)
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_getEmployeeList?type=RND&rowSize=20&currPg=1&om_code=05233&sm_code=&sm_nm=&is_work=WORK&except_em_no=00001">
    [30-020] 사원현황(순회MD)
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_getEmployeeList?type=TIMHDR&cm_code=001&rowSize=20&currPg=1">
    [30-021] 사원현황(MD팀장)
</div><br/>
<div class="interfaceView cursor" data-url="/employee/m_getEmployeeList?type=MNGR&cm_code=001&rowSize=20&currPg=1">
    [30-030] 사원현황(관리자)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_approvalList?em_no=00004&kind=SEND&type=APPLY&ad_date_from=2015-11-01&ad_date_to=2015-11-30&rowSize=1&currPg=1">
    [40-010] 전자결재(상신함-결재상신)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_approvalList?am_approver_em_no=00093&kind=RECEIVE&type=APPLY&ad_date_from=2015-11-01&ad_date_to=2015-11-30&rowSize=1&currPg=1">
    [40-011] 전자결재(수신함-결재대기)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_approvalList?em_no=00002&kind=SEND&type=APPROVAL&ad_date_from=2015-11-01&ad_date_to=2015-11-30&rowSize=1&currPg=1">
    [40-100] 전자결재(상신함-결재완료)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_approvalList?am_approver_em_no=00009&kind=RECEIVE&type=APPROVAL&ad_date_from=2015-11-01&ad_date_to=2015-11-30&rowSize=1&currPg=1">
    [40-101] 전자결재(수신함-결재완료)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_approvalList?em_no=00002&kind=SEND&type=REJECT&ad_date_from=2015-11-01&ad_date_to=2015-11-30&rowSize=1&currPg=1">
    [40-200] 전자결재(상신함-결재반려)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_approvalList?am_approver_em_no=00009&kind=RECEIVE&type=REJECT&ad_date_from=2015-11-01&ad_date_to=2015-11-30&rowSize=1&currPg=1">
    [40-201] 전자결재(수신함-결재반려)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_approvalRow?am_code=1511030006">
    [40-030] 전자결재(결재상세)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_saveApproval?kind=SEND&type=APPLY&om_code=00001&em_no=00091&am_approver_em_no=00093&ad_type=0000000059&ad_date_from=2015-10-28&ad_date_from_hhmm=01:01&ad_date_to=2015-10-28&ad_date_to_hhmm=02:02&ad_reason=TEST&am_no=86">
    [40-020] 전자결재(결재신청-상신)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_saveApproval?kind=RECEIVE&type=APPROVAL&am_code=1511020026&em_no=00091&am_approver_em_no=00093">
    [40-031] 전자결재(결재처리-결재)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_saveApproval?kind=RECEIVE&type=REJECT&am_code=1511020026&em_no=00091&am_approver_em_no=00093&ar_reason=NO!!!">
    [40-032] 전자결재(결재처리-반려)
</div><br/>
<div class="interfaceView cursor" data-url="/approval/m_saveApproval?kind=SEND&type=UPDATE&am_code=1511030006&om_code=00001&em_no=00091&am_approver_em_no=00093&ad_date_from=2015-10-28&ad_date_from_hhmm=01:01&ad_date_to=2015-10-28&ad_date_to_hhmm=02:02&ad_reason=RE-TRY&am_no=86">
    [40-033] 전자결재(결재재신청-재상신)
</div><br/>
<div class="interfaceView cursor" data-url="/notice/m_noticeList?rowSize=20&currPg=1&cm_code=001&auth_flag=2&em_no=00009&search_date_from=2015-11-01&search_date_to=2015-11-30&search_value=1">
    [50-010] 공지사항 목록조회
</div><br/>
<div class="interfaceView cursor" data-url="/notice/m_noticeView?bm_innb=310">
    [50-011] 공지사항 상세조회
</div><br/>
<div class="interfaceView cursor" data-url="/event/m_scheduleList?kind=LIST&auth_flag=2&em_no=00001&yyyyMm=201511&om_code=00001&sm_nm=1">
    [50-020] 주요행사 목록조회
</div><br/>
<div class="interfaceView cursor" data-url="/event/m_scheduleList?kind=VIEW&auth_flag=2&em_no=00009&yyyyMm=201511&om_code=00001&t_days=2015-11-10">
    [50-021] 주요행사 상세조회
</div><br/>
<div class="interfaceView cursor" data-url="/business/m_businessList?rowSize=20&currPg=1&cm_code=001&auth_flag=2&em_no=00009&om_code=&em_nm=&search_date_from=2015-11-01&search_date_to=2015-12-31">
    [50-030] 업무지시 목록조회
</div><br/>
<div class="interfaceView cursor" data-url="/business/m_businessReceiverView?auth_flag=3&em_no=00004&bm_innb=368">
    [50-031] 업무지시 상세조회
</div><br/>
<div class="interfaceView cursor" data-url="/business/m_businessReceiverReply?bm_innb=333&em_no=00091&br_answer=Test123">
    [50-032] 업무지시 답변등록
</div><br/>
<div class="interfaceView cursor" data-url="/noticeMessage/m_noticeList?rowSize=20&currPg=1&cm_code=001&auth_flag=2&em_no=00009&search_date_from=2015-11-01&search_date_to=2015-12-31&search_value=">
    [50-040] 알림메세지 목록조회
</div><br/>
<div class="interfaceView cursor" data-url="/store/m_storeAllList?rowSize=20&currPg=1&om_code=00001&em_nm=1&sm_odr=0000000011&sm_cvscafe_at=N&sm_rprsntv_nm=1&cg_nm=2&me_nm=1&sm_nm=A">
    [60-010] 매장정보 목록조회
</div><br/>
<div class="interfaceView cursor" data-url="/store/m_storeRow?sm_code=01004">
    [60-020] 매장정보 상세조회
</div><br/>
</article>
</body>
</html>
