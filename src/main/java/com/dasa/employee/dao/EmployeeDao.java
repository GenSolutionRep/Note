package com.dasa.employee.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.dasa.employee.vo.EmployeeVo;
import com.dasa.employee.vo.HrHistoryVo;
import com.dasa.employee.vo.WorkingStoreVo;
import com.vertexid.vo.NaviVo;

/**
 * @파일명: EmployeeDao.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 Dao
 */
public interface EmployeeDao {

	//사원관리 전체행수조회
	public NaviVo getEmployeeListCnt(NaviVo naviVo) throws SQLException;

	//사원관리 목록조회
	public List<EmployeeVo> getEmployeeList(NaviVo naviVo) throws SQLException;

	//사원관리 상세조회
	public EmployeeVo getEmployeeView(String em_no) throws SQLException;

	//사원관리 목록조회(지점코드,사원명)
	public List<EmployeeVo> getEmployeeListByOmCode(Map<String, String> map) throws SQLException;
	
	//사원관리 목록조회(팀코드)
	public List<EmployeeVo> getEmployeeListByTeamCode(Map<String, String> map) throws SQLException;
	
	//사원관리 입력한 ID의 존재여부
	public EmployeeVo checkUserId(EmployeeVo employeeVo) throws SQLException;
	
	//사원관리 입력한 팀의 팀장 존재여부
	public EmployeeVo checkTeamHeader(EmployeeVo employeeVo) throws SQLException;

	//사원관리 등록(INSERT)
	public int insertEmployee(EmployeeVo employeeVo) throws SQLException;

	//사원관리 수정(UPDATE)
	public int updateEmployee(EmployeeVo employeeVo) throws SQLException;

	//사원관리 비밀번호변경
	public int changePassword(EmployeeVo employeeVo) throws SQLException;
	
	//사원관리 개인연락처 공개여부변경
	public int changeContactOpen(EmployeeVo employeeVo) throws SQLException;
	
	//사원관리 프로필 사진조회
	public EmployeeVo getProfilePicture(String em_no) throws SQLException;
	
	//사원관리 프로필사진 등록
	public int setProfilePicture(EmployeeVo employeeVo) throws SQLException;

	//인사기록 목록조회
	public List<HrHistoryVo> getHrHistoryList(String em_no) throws SQLException;

	//인사기록 등록
	public int setHrHistory(HrHistoryVo hrHistoryVo) throws SQLException;

	//근무매장 목록조회
	public List<WorkingStoreVo> getWorkingStoreList(String em_no) throws SQLException;
	
	//사원관리 엑셀다운로드
	public List<EmployeeVo> getEmployeeExcelDown(Map<String, String> map) throws SQLException;

}
