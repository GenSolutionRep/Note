package com.dasa.dashboard.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.dashboard.dao.DashDAO;
import com.dasa.dashboard.vo.DashVo;


public class DashService extends SqlSessionDaoSupport  implements DashDAO {

	@Override
	public List<DashVo> selectEmp(HashMap<String, String> map) throws SQLException {
		return getSqlSession().selectList("selectEmp", map);
	}
	@Override
	public DashVo selectFixAttdance(HashMap<String, String> map) throws SQLException {
		return getSqlSession().selectOne("selectFixAttdance", map);
	}
	@Override
	public DashVo selectRndAttdance(HashMap<String, String> map) throws SQLException {
		return getSqlSession().selectOne("selectRndAttdance", map);
	}
	
	
}
