package com.vertexid.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.employee.vo.EmployeeVo;
import com.vertexid.vo.AuthGroupEmpVo;
import com.vertexid.vo.AuthGroupVo;
import com.vertexid.vo.AuthVo;

public interface AuthDAO {
	
	/**
	 * 권한 그룹 리스트
	 * @return List<AuthVo>
	 */
	public List<AuthGroupVo> selectAuthGroupList()  throws SQLException;
	
	/**
	 * 해당 권한그룹의 row data
	 * @param String ma_group_seq
	 * @return AuthVo
	 */
	public AuthGroupVo selectAuthGroupRow(String ma_group_seq)  throws SQLException;
	
	/**
	 * 권한그룹 마스터 insert
	 * @param AuthGroupVo vo
	 * @return int
	 */
	public int insertAuthGroup(AuthGroupVo vo)  throws SQLException;
	
	/**
	 * 권한그룹 마스터 update
	 * @param AuthGroupVo vo
	 * @return List<AuthorityMenuVo>
	 */
	public int updateAuthGroup(AuthGroupVo vo)  throws SQLException;
	
	/**
	 * 권한그룹 마스터 delete
	 * @param AuthGroupVo vo
	 * @return int
	 */
	public int deleteAuthGroup(AuthGroupVo vo)  throws SQLException;
	

	/**
	 * 권한그룹 사원 리스트
	 * @return List<AuthGroupEmpVo>
	 */
	public List<AuthGroupEmpVo> selectAuthGroupEmpList(HashMap<String, String> map)  throws SQLException;
	
	
	/**
	 * 권한그룹 사원  insert
	 * @param AuthGroupEmpVo vo
	 * @return int
	 */
	public int insertAuthGroupEmp(AuthGroupEmpVo vo)  throws SQLException;
	
	/**
	 * 권한그룹 사원  update
	 * @param AuthGroupEmpVo vo
	 * @return List<AuthorityMenuVo>
	 */
	public int updateAuthGroupEmp(AuthGroupEmpVo vo)  throws SQLException;
	
	/**
	 * 권한그룹 사원  delete
	 * @param AuthGroupEmpVo vo
	 * @return int
	 */
	public int deleteAuthGroupEmp(AuthGroupEmpVo vo)  throws SQLException;
	
	/**
	 * 메뉴권한 리스트
	 * @return List<AuthVo>
	 */
	public List<AuthVo> selectAuthList(AuthVo vo)  throws SQLException;
	
	/**
	 * 메뉴권한 insert
	 * @param AuthVo vo
	 * @return int
	 */
	public int insertAuth(AuthVo vo)  throws SQLException;
	
	/**
	 * 메뉴권한 delete
	 * @param AuthVo
	 * @return int
	 */
	public int deleteAuth(AuthVo vo)  throws SQLException;

	
	/**
	 * 메뉴권한 authSave
	 * @param AuthVo
	 * @return int
	 */
	@Transactional
	public int authSave(AuthVo vo) throws SQLException;

	public List<EmployeeVo> authEmpList(Map<String, String> map) throws SQLException;

	public AuthVo thisMenuAuth(Map<String, String> map) throws SQLException;

}
