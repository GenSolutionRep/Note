package com.dasa.mobile.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.mobile.vo.MobileVo;

/**
 * @파일명: MobileDao.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 모바일 Dao
 */
public interface MobileDao {

	//서버의 현재일자 조회
	public MobileVo getCurrentDate(MobileVo mobileVo) throws SQLException;

	//고객그룹 조회
	public List<MobileVo> getCustomerGroupList() throws SQLException;

	//고객그룹별 관리업체 조회
	public List<MobileVo> getManageEnterpriseListPerCustomerGroup(MobileVo mobileVo) throws SQLException;

	//관리업체별 매장 조회
	public List<MobileVo> getStoreListPerManageEnterprise(MobileVo mobileVo) throws SQLException;

	//고정여사원의 매장 조회
	public MobileVo getStoreViewOfFix(MobileVo mobileVo) throws SQLException;

	//순회여사원의 매장 조회
	public List<MobileVo> getStoreListOfRnd(MobileVo mobileVo) throws SQLException;

	//팀장의 매장 조회
	public List<MobileVo> getStoreListOfTeamHeader(MobileVo mobileVo) throws SQLException;

	//관리자의 매장 조회
	public List<MobileVo> getStoreListOfManager(MobileVo mobileVo) throws SQLException;

	//지점별 매장 조회
	public List<MobileVo> getStoreListPerBranch(MobileVo mobileVo) throws SQLException;

	//회사별 지점 조회
	public List<MobileVo> getBranchListPerCompany(MobileVo mobileVo) throws SQLException;

	//지점별 사원 조회
	public List<MobileVo> getEmployeeListPerBranch(MobileVo mobileVo) throws SQLException;

	//매장별 사원 조회
	public List<MobileVo> getEmployeeListPerStore(MobileVo mobileVo) throws SQLException;

	//고정여사원 출퇴근시간 조회
	public MobileVo getCommuteTime(MobileVo mobileVo) throws SQLException;

	//고정여사원 출근시간 등록
	public MobileVo setAttendingTime(MobileVo mobileVo) throws SQLException;

	//고정여사원 퇴근시간 등록
	public MobileVo setLeavingTime(MobileVo mobileVo) throws SQLException;

	//순회여사원 순방계획 조회
	public List<MobileVo> getRndPlanList(MobileVo mobileVo) throws SQLException;

	//순회여사원 순방계획 등록
	@Transactional
	public int setRndPlan(MobileVo mobileVo) throws SQLException, Exception;

	//첨부파일 정보 조회
	public List<MobileVo> getFileInfo(MobileVo mobileVo) throws SQLException;

	//디바이스오류 등록
	public int setDeviceError(MobileVo mobileVo) throws SQLException;

}
