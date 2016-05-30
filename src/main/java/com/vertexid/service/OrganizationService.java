package com.vertexid.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.vertexid.dao.MenuDAO;
import com.vertexid.dao.OrganizationDAO;
import com.vertexid.vo.MenuVo;
import com.vertexid.vo.OrganizationVo;


public class OrganizationService extends SqlSessionDaoSupport  implements OrganizationDAO {
	
	@Override
	public List<OrganizationVo> getTreeList(String cm_code) throws SQLException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cm_code", cm_code);
		map.put("om_code", "");
		
		String isql_string = (String) getSqlSession().selectOne("organization.getSQL_organizationList", map);
		if(isql_string.trim().equals(""))
			return null;
		else{
			map.put("isql_string", isql_string);
			return getSqlSession().selectList("organization.organizationList", map);
		}
	}
	
	@Override
	public List<OrganizationVo> selectList(HashMap<String, String> map)	throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationList", map);
	}

	@Override
	public List<OrganizationVo> selectListByCp(String cm_code)throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationListByCp", cm_code);
	}
	
	@Override
	public List<OrganizationVo> selectListByBhf(String cm_code, String bhf_code)throws SQLException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cm_code", cm_code);
		map.put("om_code", bhf_code);
		
		String isql_string = (String) getSqlSession().selectOne("organization.getSQL_organizationList", map);
		map.put("isql_string", isql_string);
		return getSqlSession().selectList("organization.selectOrganizationListByBhf", map);
	}
	
	@Override
	public OrganizationVo selectRow(String om_code) throws SQLException {
		return getSqlSession().selectOne("organization.selectOrganizationRow", om_code);
	}

	@Override
	public OrganizationVo getParentInfo(String om_code) throws SQLException {
		return getSqlSession().selectOne("organization.getOrganizationParentInfo", om_code);
	}
	
	@Override
	public String selectFirstTree() throws SQLException {
		return getSqlSession().selectOne("organization.selectFirstTreeOrganization");
	}
	
	@Override
	public List<OrganizationVo> selectParentList() throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationParentList");
	}
	
	@Override
	public List<OrganizationVo> selectChildList(String m_no) throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationChildList");
	}
		
	@Override
	public List<OrganizationVo> selectBranchList(String cm_code) throws SQLException {
		return getSqlSession().selectList("organization.selectBranchList", cm_code);
	}
	
	@Override
	public List<OrganizationVo> selectDepthList(HashMap hashMap) throws SQLException {
		return getSqlSession().selectList("organization.selectDepthList", hashMap);
	}
	
	@Override
	public void saveOrganization(OrganizationVo dto) throws SQLException {
		getSqlSession().update("organization.SP_SAVE_ORGNZ_TO_MENU", dto);
	}
	
	@Override
	public int insertOrganization(HashMap hashMap) throws SQLException {
		return getSqlSession().insert("organization.insertOrganization", hashMap);
	}
	
	@Override
	public int updateOrganization(HashMap hashMap) throws SQLException {
		return getSqlSession().update("organization.updateOrganization", hashMap);
	}
	
	@Override
	public int deleteOrganization(HashMap hashMap) throws SQLException {
		return getSqlSession().update("organization.deleteOrganization", hashMap);
	}

	@Override
	public List<OrganizationVo> selectAuthDepthList(HashMap hashMap) {
//		System.out.println("auth");
		return getSqlSession().selectList("organization.selectAuthDepthList", hashMap);
	}
}
