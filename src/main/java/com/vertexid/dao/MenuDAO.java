package com.vertexid.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.store.vo.StoreVo;
import com.vertexid.vo.MenuVo;

public interface MenuDAO {
	
	/**
	 * 메뉴 목록
	 * @param String user_id
	 * @return List<AuthorityMenuVo>
	 */
	@Transactional
	public List<MenuVo> menuList(HashMap map)  throws SQLException;
	
	/**
	 * 해당 노드 & 하위 메뉴 List
	 * @param String user_id
	 * @return List<AuthorityMenuVo>
	 */
	public List<MenuVo> selectMenuList(String m_no, String cm_code)  throws SQLException;
	
	/**
	 * 해당 메뉴의 row data
	 * @param String user_id
	 * @return List<AuthorityMenuVo>
	 */
	public MenuVo selectMenuRow(String m_no)  throws SQLException;
	
	/**
	 * 해당 트리의 첫 node의 메뉴코드 조회
	 * @param String user_id
	 * @return List<AuthorityMenuVo>
	 */
	public String selectFirstTreeMenu()  throws SQLException;
	
	/**
	 * 상위코드 정보 가져오기
	 * @param String user_id
	 * @return List<AuthorityMenuVo>
	 */
	public MenuVo getParentInfo(String m_no)  throws SQLException;
	
	/**
	 * 콤보를 위한 상위메뉴 리스트
	 * @return List<AuthorityMenuVo>
	 */

	public List<MenuVo> selectMenuParentList()  throws SQLException;
	
	/**
	 * 하위 콤보를 위한 리스트
	 * @return List<AuthorityMenuVo>
	 */

	public List<MenuVo> selectMenuChildList(String m_no)  throws SQLException;
	
	/**
	 * 콤보 depth 리스트
	 * @return List<AuthorityMenuVo>
	 */

	public List<MenuVo> selectDepthList(HashMap hashMap)  throws SQLException;
	
	/**
	 * ave(프로시저)
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	@Transactional
	public void saveMenu(MenuVo menuDto)  throws SQLException;
	
	/**
	 * 해당 메뉴의 Insert
	 * @param MenuVo menuVo
	 * @return String
	 */
	public int InsertMenu(HashMap hashMap)  throws SQLException;
	
	/**
	 * 해당 메뉴의 Update
	 * @param MenuVo menuVo
	 * @return String
	 */
	public int UpdateMenu(HashMap hashMap)  throws SQLException;
	
	public List<MenuVo> selectMenuInit(HashMap<String, String> map) throws SQLException;
	
	public List<MenuVo> selectTopInit(HashMap<String, String> map) throws SQLException;
}
