package com.vertexid.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.vertexid.dao.CompanyDAO;
import com.vertexid.vo.CompanyVo;


public class CompanyService extends SqlSessionDaoSupport  implements CompanyDAO {

	@Override
	public List<CompanyVo> selectCompanyList() throws SQLException {
		return getSqlSession().selectList("selectCompanyList");
	}
	@Override
	public CompanyVo selectCompanyRow(String cm_code) throws SQLException {
		return getSqlSession().selectOne("selectCompanyRow", cm_code);
	}
	
	@Override
	public int updateCompany(HashMap hashMap) throws SQLException {
		 return getSqlSession().update("updateCompany", hashMap);
	}
	
	@Override
	public int deleteCompany(HashMap hashMap) throws SQLException {
		return getSqlSession().update("deleteCompany", hashMap);
	}
	
	@Override
	public int insertCompany(HashMap hashMap) throws SQLException {
		return getSqlSession().insert("insertCompany", hashMap);
	}
	
	
}
