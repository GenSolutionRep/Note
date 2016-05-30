package com.vertexid.utill;

/**
 * @파일명: Constant.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 상수선언
 */
public class Constant {

	public static final int SUCCESS_CD = 1;
	public static final String SUCCESS_MSG = "성공";

	public static final int FAIL_CD = 0;
	public static final String FAIL_MSG = "실패";

	public static final int INVALID_PARAM_CD = -1;
	public static final String INVALID_PARAM_MSG = "요청한 파라미터가 올바르지 않습니다.";

	public static final int NO_RESULT_CD = -2;
	public static final String NO_RESULT_MSG = "조회된 결과가 없습니다.";

	public static final int CHANGED_PHONE_CD = -3;
	public static final String CHANGED_PHONE_MSG = "사용중인 휴대전화가 변경되었습니다.\n관리자에게 문의해 주세요.";

	public static final int LOGIN_FAILED_CD = -4;
	public static final String LOGIN_FAILED_MSG = "로그인 실패했습니다.";

	public static final int PW_CHANGE_FAILED_CD = -5;
	public static final String PW_CHANGE_FAILED_MSG = "비밀번호 변경작업이 실패했습니다.";

	public static final int CONTACT_OPEN_CHANGE_FAILED_CD = -6;
	public static final String CONTACT_OPEN_CHANGE_FAILED_MSG = "개인연락처 공개여부 변경작업이 실패했습니다.";

	public static final int PROFILE_PICTURE_FAILED_CD = -7;
	public static final String PROFILE_PICTURE_FAILED_MSG = "프로필사진 등록작업이 실패했습니다.";

	public static final int FILE_UPLOAD_FAILED_CD = -8;
	public static final String FILE_UPLOAD_FAILED_MSG = "파일업로드 작업이 실패했습니다.";

	public static final int APPROVAL_FAILED_CD = -9;
	public static final String APPROVAL_FAILED_MSG = "전자결재 작업이 실패했습니다.";

	public static final int MENU_LIST_FAILED_CD = -10;
	public static final String MENU_LIST_FAILED_MSG = "메뉴 조회작업이 실패했습니다.";

	public static final int COMMUNITY_LIST_FAILED_CD = -11;
	public static final String COMMUNITY_LIST_FAILED_MSG = "커뮤니케이션 조회작업이 실패했습니다.";

	public static final int RND_PLAN_FAILED_CD = -12;
	public static final String RND_PLAN_FAILED_MSG = "순방계획 등록작업이 실패했습니다.";

	public static final int APP_UPDATE_FAILED_CD = -13;
	public static final String APP_UPDATE_FAILED_MSG = "현재 사용하시는 앱의 최신버전을 다운로드 받으세요.";

	public static final int MENU_NOTICE_CNT_FAILED_CD = -14;
	public static final String MENU_NOTICE_CNT_FAILED_MSG = "메뉴 알림개수 조회작업이 실패했습니다.";

	public static final int DEVICE_ERROR_FAILED_CD = -15;
	public static final String DEVICE_ERROR_FAILED_MSG = "디바이스오류 등록작업이 실패했습니다.";

}
