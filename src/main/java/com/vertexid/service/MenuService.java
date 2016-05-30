package com.vertexid.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.vertexid.dao.MenuDAO;
import com.vertexid.vo.MenuVo;


public class MenuService extends SqlSessionDaoSupport  implements MenuDAO {
	
	@Override
	public List<MenuVo> menuList(HashMap map) throws SQLException {
		String isql_string = (String) getSqlSession().selectOne("menu.getSQL_menuList", map);
		map.put("isql_string", isql_string);
		return getSqlSession().selectList("menu.menuList", map);
	}
	
	@Override
	public List<MenuVo> selectMenuList(String m_no, String cm_code)	throws SQLException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("m_no", m_no);
		map.put("m_use_yn", "A");
		map.put("cm_code", cm_code);
		/*String isql_string = (String) getSqlSession().selectOne("menu.getSQL_menuList", map);
		
		map.put("isql_string", isql_string);*/
		return getSqlSession().selectList("menu.selectMenuList", map);
	}

	@Override
	public MenuVo selectMenuRow(String m_no) throws SQLException {
		return getSqlSession().selectOne("menu.selectMenuRow", m_no);
	}

	@Override
	public List<MenuVo> selectDepthList(HashMap hashMap) throws SQLException {
		return getSqlSession().selectList("menu.selectDepthList", hashMap);
	}
	
	@Override
	public MenuVo getParentInfo(String m_no) throws SQLException {
		return getSqlSession().selectOne("menu.getParentInfo", m_no);
	}
	
	@Override
	public String selectFirstTreeMenu() throws SQLException {
		return getSqlSession().selectOne("menu.selectFirstTreeMenu");
	}
	
	@Override
	public List<MenuVo> selectMenuParentList() throws SQLException {
		return getSqlSession().selectList("menu.selectMenuParentList");
	}
	
	@Override
	public List<MenuVo> selectMenuChildList(String m_no) throws SQLException {
		return getSqlSession().selectList("menu.selectMenuChildList");
	}
	
	@Override
	public void saveMenu(MenuVo menuDto) throws SQLException {
		getSqlSession().update("menu.SP_SAVE_MENU_TO_ORGNZ", menuDto);
	}
		
	@Override
	public int InsertMenu(HashMap hashMap) throws SQLException {
		return getSqlSession().insert("menu.insertMenu", hashMap);
	}
	
	@Override
	public int UpdateMenu(HashMap hashMap) throws SQLException {
		return getSqlSession().update("menu.updateMenu", hashMap);
	}
	
	@Override
	public List<MenuVo> selectMenuInit(HashMap<String, String> map) throws SQLException {
		return getSqlSession().selectList("menu.selectMenuInit", map);
	}
	
	@Override
	public List<MenuVo> selectTopInit(HashMap<String, String> map) throws SQLException {
		return getSqlSession().selectList("menu.selectTopInit", map);
	}
}
