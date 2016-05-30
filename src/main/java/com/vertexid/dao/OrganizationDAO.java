package com.vertexid.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vertexid.vo.MenuVo;
import com.vertexid.vo.OrganizationVo;

public interface OrganizationDAO {
	
	/**
	 * 트리 리스트
	 * @param String om_code
	 * @return List<AuthorityMenuVo>
	 */
	public List<OrganizationVo> getTreeList(String cm_code)  throws SQLException;
	
	/**
	 * 해당 노드 & 하위 메뉴 List
	 * @param String om_code
	 * @return List<OrganizationVo>
	 */
	public List<OrganizationVo> selectList(HashMap<String, String> map)  throws SQLException;
	
	/**
	 * nodeOrganizationListByCp
	 * @param String om_code
	 * @return List<OrganizationVo>
	 */
	public List<OrganizationVo> selectListByCp(String cm_code)  throws SQLException;
	
	/**
	 * nodeOrganizationListByBhf
	 * @param String bhf_code
	 * @return List<OrganizationVo>
	 */
	public List<OrganizationVo> selectListByBhf(String cm_code, String bhf_code)  throws SQLException;
	
	
	/**
	 * 해당 조직의 row data
	 * @param String om_code
	 * @return List<OrganizationVo>
	 */
	public OrganizationVo selectRow(String om_code)  throws SQLException;
	
	/**
	 * 해당 트리의 첫 node의 조직코드 조회
	 * @return List<OrganizationVo>
	 */
	public String selectFirstTree()  throws SQLException;
	
	/**
	 * 상위코드 정보 가져오기
	 * @param String user_id
	 * @return List<OrganizationVo>
	 */
	public OrganizationVo getParentInfo(String om_code)  throws SQLException;
	
	/**
	 * 콤보를 위한 상위메뉴 리스트
	 * @return List<OrganizationVo>
	 */

	public List<OrganizationVo> selectParentList()  throws SQLException;
	
	/**
	 * 하위 콤보를 위한 리스트
	 * @return List<OrganizationVo>
	 */

	public List<OrganizationVo> selectChildList(String om_code)  throws SQLException;
	
	/**
	 * (콤보)지점 리스트
	 * @return List<OrganizationVo>
	 */

	public List<OrganizationVo> selectBranchList(String cm_code)  throws SQLException;
	
	/**
	 * (콤보)depthList
	 * @return List<OrganizationVo>
	 */

	public List<OrganizationVo> selectDepthList(HashMap hashMap)  throws SQLException;
	
	/**
	 * save(프로시저)
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	@Transactional
	public void saveOrganization(OrganizationVo dto)  throws SQLException;
	
	/**
	 * 해당 조직 Insert
	 * @param OrganizationVo organizationVo
	 * @return String
	 */
	public int insertOrganization(HashMap hashMap)  throws SQLException;
	
	/**
	 * 해당 조직 Update
	 * @param OrganizationVo organizationVo
	 * @return String
	 */
	public int updateOrganization(HashMap hashMap)  throws SQLException;

	/**
	 * 해당 조직 Delete
	 * @param HashMap hashMap
	 * @return String
	 */
	public int deleteOrganization(HashMap hashMap)  throws SQLException;

	/**
	 * (콤보)depthList
	 * @param HashMap hashMap
	 * @return String
	 */
	public List<OrganizationVo> selectAuthDepthList(HashMap hashMap);

}
