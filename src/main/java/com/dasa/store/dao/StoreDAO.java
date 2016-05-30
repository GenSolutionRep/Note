package com.dasa.store.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.approval.vo.ApprovalVo;
import com.dasa.store.vo.CstmrGroupVo;
import com.dasa.store.vo.EmplManageVo;
import com.dasa.store.vo.ManageEntrpsVo;
import com.dasa.store.vo.StoreVo;
import com.vertexid.vo.CompanyVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.MenuVo;
import com.vertexid.vo.NaviVo;

public interface StoreDAO {
	
	/*고객그룹***********************************************************************/
	/**
	 * 고객그룹 코드 체크 조회
	 * @return List<CstmrGroupVo>
	 */
	public CstmrGroupVo checkCstmrGroupCode(String cg_code)  throws SQLException;
	
	/**
	 * 고객그룹 조회
	 * @return List<CstmrGroupVo>
	 */
	public List<CstmrGroupVo> selectCstmrGroupList(String om_code)  throws SQLException;
	
	/**
	 * 고객그룹 row 조회
	 * @return List<CstmrGroupVo>
	 */
	public CstmrGroupVo selectCstmrGroupRow(String cg_code)  throws SQLException;
	
	/**
	 * 매장전체 리스트조회 카운트
	 * @return NaviVo naviVo
	 */
	public NaviVo selectStoreAllCount(NaviVo naviVo)  throws SQLException;
	
	/**
	 * 매장전체 리스트조회
	 * @return List<StoreVo>
	 */
	public List<StoreVo> selectStoreAllList(NaviVo naviVo)  throws SQLException;
	
	/**
	 * 매장전체 리스트조회
	 * @return List<StoreVo>
	 */
	public List<StoreVo> selectStoreAllExcelList(StoreVo vo)  throws SQLException;
	
	/**
	 * 고객그룹 insert
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int insertCstmrGroup(HashMap hashMap)  throws SQLException;
	
	/**
	 * 고객그룹 update
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int updateCstmrGroup(HashMap hashMap)  throws SQLException;
	
	/**
	 * 관객업체 delete
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int deleteCstmrGroup(HashMap hashMap)  throws SQLException;
	
	/*관리업체***********************************************************************/
	/**
	 * 관리업체 코드 체크 조회
	 * @return List<CstmrGroupVo>
	 */
	public ManageEntrpsVo checkManageEntrpsCode(HashMap hashMap)  throws SQLException;
	
	/**
	 * 관리업체 조회
	 * @return List<ManageEntrpsVo>
	 */
	public List<ManageEntrpsVo> selectManageEntrpsList(HashMap hashMap)  throws SQLException;
	
	/**
	 * 관리업체 row 조회
	 * @return List<StoreVo>
	 */
	public ManageEntrpsVo selectManageEntrpsRow(HashMap hashMap)  throws SQLException;
	
	/**
	 * 관리업체 insert
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int insertManageEntrps(HashMap hashMap)  throws SQLException;
	
	/**
	 * 관리업체 update
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int updateManageEntrps(HashMap hashMap)  throws SQLException;
	
	/**
	 * 관리업체 delete
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int deleteManageEntrps(HashMap hashMap)  throws SQLException;
	
	/*매장관리***********************************************************************/

	/**
	 * 매장관리 리스트
	 * @return List<StoreVo>
	 */
	public List<StoreVo> selectStoreList(HashMap map)  throws SQLException;
	
	/**
	 * 해당 매장의 row data
	 * @param String cm_code
	 * @return StoreVo
	 */
	public StoreVo selectStoreRow(String sm_code)  throws SQLException;
	
	
	/**
	 * 매장정보 save(프로시저)
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	@Transactional
	public void saveStore(StoreVo storeDto)  throws SQLException;
	
	/**
	 * 매장정보 update
	 * @param HashMap hashMap
	 * @return List<AuthorityMenuVo>
	 */
	public int updateStore(HashMap hashMap)  throws SQLException;
	
	/**
	 * 매장정보 delete
	 * @param HashMap hashMap
	 * @return int
	 */
	public int deleteStore(HashMap hashMap)  throws SQLException;
	
	/**
	 * 매장정보 insert
	 * @param HashMap hashMapd
	 * @return int
	 */
	public int insertStore(HashMap hashMap)  throws SQLException;

	/**
	 * 사원조회
	 * @return List<ManageEntrpsVo>
	 */
	public List<EmplManageVo> selectEmpList(HashMap hashMap)  throws SQLException;
	
	/**
	 * 자동완성
	 * @return List<KeyValueVo>
	 */
	public List<KeyValueVo> emplAutoComplate(HashMap hashMap) throws SQLException;
	

	public List<StoreVo> selectJuso()  throws SQLException;
	public int saveJuso(StoreVo storeDto)  throws SQLException;
}
