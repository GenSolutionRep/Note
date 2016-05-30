package com.dasa.employee.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.employee.dao.EmployeeDao;
import com.dasa.employee.vo.EmployeeVo;
import com.dasa.employee.vo.HrHistoryVo;
import com.dasa.employee.vo.WorkingStoreVo;
import com.vertexid.vo.NaviVo;

/**
 * @파일명: EmployeeService.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 Service
 */
public class EmployeeService extends SqlSessionDaoSupport implements EmployeeDao {

	@Override
	public NaviVo getEmployeeListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer) getSqlSession().selectOne("employee.getEmployeeListCnt", naviVo));
		return naviVo;
	}

	@Override
	public List<EmployeeVo> getEmployeeList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("employee.getEmployeeList", naviVo);
	}

	@Override
	public EmployeeVo getEmployeeView(String em_no) throws SQLException {
		return (EmployeeVo) getSqlSession().selectOne("employee.getEmployeeView", em_no);
	}

	@Override
	public List<EmployeeVo> getEmployeeListByOmCode(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("employee.getEmployeeListByOmCode", map);
	}
	@Override
	public List<EmployeeVo> getEmployeeListByTeamCode(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("employee.getEmployeeListByTeamCode", map);
	}
	
	@Override
	public EmployeeVo checkUserId(EmployeeVo employeeVo) throws SQLException {
		return (EmployeeVo) getSqlSession().selectOne("employee.checkUserId", employeeVo);
	}
	
	@Override
	public EmployeeVo checkTeamHeader(EmployeeVo employeeVo) throws SQLException {
		return (EmployeeVo) getSqlSession().selectOne("employee.checkTeamHeader", employeeVo);
	}

	@Override
	public int insertEmployee(EmployeeVo employeeVo) throws SQLException {
		employeeVo.setEm_no((String) getSqlSession().selectOne("employee.getEmployeeNextVal"));
		return getSqlSession().insert("employee.insertEmployee", employeeVo);
	}

	@Override
	public int updateEmployee(EmployeeVo employeeVo) throws SQLException {
		return getSqlSession().update("employee.updateEmployee", employeeVo);
	}

	@Override
	public int changePassword(EmployeeVo employeeVo) throws SQLException {
		return getSqlSession().update("employee.changePassword", employeeVo);
	}
	
	@Override
	public int changeContactOpen(EmployeeVo employeeVo) throws SQLException {
		return getSqlSession().update("employee.changeContactOpen", employeeVo);
	}
	
	@Override
	public EmployeeVo getProfilePicture(String em_no) throws SQLException {
		return (EmployeeVo) getSqlSession().selectOne("employee.getProfilePicture", em_no);
	}	
	
	@Override
	public int setProfilePicture(EmployeeVo employeeVo) throws SQLException {
		return getSqlSession().update("employee.setProfilePicture", employeeVo);
	}

	@Override
	public List<HrHistoryVo> getHrHistoryList(String em_no) throws SQLException {
		return getSqlSession().selectList("employee.getHrHistoryList", em_no);
	}

	@Override
	public int setHrHistory(HrHistoryVo hrHistoryVo) throws SQLException {
		int cnt = 0, cnt1 = 0, cnt2 = 0, cnt3 = 0;
		
		cnt1 = getSqlSession().update("employee.deleteHrHistory", hrHistoryVo);
		
		for (Map<String, String> map : hrHistoryVo.getHrHistoryArr()) {
			String hm_innb = map.get("hm_innb");
			if (hm_innb == null || hm_innb.equals("")) {
				map.put("regist_man", hrHistoryVo.getUpdt_man());
				map.put("updt_man", hrHistoryVo.getUpdt_man());
				cnt2 += getSqlSession().insert("employee.insertHrHistory", map);
			} else {
				map.put("updt_man", hrHistoryVo.getUpdt_man());
				cnt3 += getSqlSession().update("employee.updateHrHistory", map);
			}
		}
		
		if (cnt1 >= 0 && (cnt2+cnt3) > 0) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public List<WorkingStoreVo> getWorkingStoreList(String em_no) throws SQLException {
		return getSqlSession().selectList("employee.getWorkingStoreList", em_no);
	}
	
	@Override
	public List<EmployeeVo> getEmployeeExcelDown(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("employee.getEmployeeExcelDown", map);
	}
	
}
