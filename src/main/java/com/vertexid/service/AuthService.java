package com.vertexid.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.employee.vo.EmployeeVo;
import com.vertexid.dao.AuthDAO;
import com.vertexid.vo.AuthGroupEmpVo;
import com.vertexid.vo.AuthGroupVo;
import com.vertexid.vo.AuthVo;

public class AuthService extends SqlSessionDaoSupport implements AuthDAO {
	
	@Override
	public List<AuthGroupVo> selectAuthGroupList() throws SQLException {
		return getSqlSession().selectList("auth.selectAuthGroupList");
	}
	
	@Override
	public AuthGroupVo selectAuthGroupRow(String ma_group_seq) throws SQLException {
		return getSqlSession().selectOne("auth.selectAuthGroupRow", ma_group_seq);
	}
	
	@Override
	public int insertAuthGroup(AuthGroupVo vo) throws SQLException {
		return getSqlSession().insert("auth.insertAuthGroup", vo);
	}
	
	@Override
	public int updateAuthGroup(AuthGroupVo vo) throws SQLException {
		return getSqlSession().update("auth.updateAuthGroup", vo);
	}
	
	@Override
	public int deleteAuthGroup(AuthGroupVo vo) throws SQLException {
		return getSqlSession().update("auth.deleteAuthGroup", vo);
	}
	
	//============================================================================
	
	@Override
	public List<AuthGroupEmpVo> selectAuthGroupEmpList(HashMap<String, String> map)	throws SQLException {
		return getSqlSession().selectList("auth.selectAuthGroupEmpList", map);
	}
	
	@Override
	public int insertAuthGroupEmp(AuthGroupEmpVo vo) throws SQLException {
		return getSqlSession().insert("auth.insertAuthGroupEmp", vo);
	}
	
	@Override
	public int updateAuthGroupEmp(AuthGroupEmpVo vo) throws SQLException {
		return getSqlSession().update("auth.updateAuthGroupEmp", vo);
	}
	
	@Override
	public int deleteAuthGroupEmp(AuthGroupEmpVo vo) throws SQLException {
		return getSqlSession().update("auth.deleteAuthGroupEmp", vo);
	}
	
	//============================================================================
	
	@Override
	public List<AuthVo> selectAuthList(AuthVo vo) throws SQLException {
		return getSqlSession().selectList("auth.selectAuthList", vo);
		
	}
	
	@Override
	public int insertAuth(AuthVo vo) throws SQLException {
		return getSqlSession().insert("auth.insertAuth", vo);
	}
	
	@Override
	public int deleteAuth(AuthVo vo) throws SQLException {
		return getSqlSession().delete("auth.deleteAuth", vo);
	}

	//권한 그룹 저장
	@Override
	public int authSave(AuthVo vo) throws SQLException {
		int cnt = 0;
		for (Map<String,String > map : vo.getAuthArr()) {
			map.put("login_id", vo.getLogin_id());
			cnt = getSqlSession().insert("authSave",map);
		}
		if(vo.getEmpArr() != null && vo.getEmpArr().size() != 0){
			
			cnt = getSqlSession().delete("authEmpDelete",vo.getMa_group_seq());
			for (Map<String,String > map : vo.getEmpArr()) {
				map.put("login_id", vo.getLogin_id());
				cnt = getSqlSession().insert("authEmpSave",map);
			}
		}
		return cnt;
	}

	@Override
	public List<EmployeeVo> authEmpList(Map<String, String> map)throws SQLException {
		return getSqlSession().selectList("authEmpList", map);
	}

	@Override
	public AuthVo thisMenuAuth(Map<String, String> map) throws SQLException {
		return getSqlSession().selectOne("thisMenuAuth", map);
	}
}
