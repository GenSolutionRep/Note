package com.vertexid.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.vertexid.vo.CompanyVo;
import com.vertexid.vo.MenuVo;

public interface CompanyDAO {
	

	/**
	 * 회사관리 리스트
	 * @return List<CompanyVo>
	 */
	public List<CompanyVo> selectCompanyList()  throws SQLException;
	
	/**
	 * 해당 회사의 row data
	 * @param String cm_code
	 * @return CompanyVo
	 */
	public CompanyVo selectCompanyRow(String cm_code)  throws SQLException;
	
	
	/**
	 * 회사정보 update
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int updateCompany(HashMap hashMap)  throws SQLException;
	
	/**
	 * 회사정보 delete
	 * @param HashMap hashMap
	 * @return int
	 */
	public int deleteCompany(HashMap hashMap)  throws SQLException;
	
	/**
	 * 회사정보 insert
	 * @param HashMap hashMapd
	 * @return int
	 */
	public int insertCompany(HashMap hashMap)  throws SQLException;
	
}
