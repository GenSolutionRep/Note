package com.dasa.store.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.approval.vo.ApprovalVo;
import com.dasa.store.dao.StoreDAO;
import com.dasa.store.vo.CstmrGroupVo;
import com.dasa.store.vo.EmplManageVo;
import com.dasa.store.vo.ManageEntrpsVo;
import com.dasa.store.vo.StoreVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;


public class StoreService extends SqlSessionDaoSupport  implements StoreDAO {
	
	/*고객그룹 관리**********************************************************************/
	@Override
	public CstmrGroupVo checkCstmrGroupCode(String cg_code) throws SQLException {
		return getSqlSession().selectOne("store.chkSameCode_CstmrGroupCode", cg_code);
	}
	
	@Override
	public List<CstmrGroupVo> selectCstmrGroupList(String om_code) throws SQLException {
		return getSqlSession().selectList("store.selectCstmrGroupList");
	}
	@Override
	public CstmrGroupVo selectCstmrGroupRow(String cg_code) throws SQLException {
		return getSqlSession().selectOne("store.selectCstmrGroupRow", cg_code);
	}
	
	@Override
	public int updateCstmrGroup(HashMap hashMap) throws SQLException {
		 return getSqlSession().update("store.updateCstmrGroup", hashMap);
	}
	
	@Override
	public int deleteCstmrGroup(HashMap hashMap) throws SQLException {
		return getSqlSession().update("store.deleteCstmrGroup", hashMap);
	}
	
	@Override
	public int insertCstmrGroup(HashMap hashMap) throws SQLException {
		return getSqlSession().insert("store.insertCstmrGroup", hashMap);
	}
	
	/*관리업체 관리**********************************************************************/
	@Override
	public ManageEntrpsVo checkManageEntrpsCode(HashMap hashMap) throws SQLException {
		return getSqlSession().selectOne("store.chkSameCode_ManageEntrpsCode", hashMap);
	}
	@Override
	public List<ManageEntrpsVo> selectManageEntrpsList(HashMap hashMap) throws SQLException {
		return getSqlSession().selectList("store.selectManageEntrpsList", hashMap);
	}
	@Override
	public ManageEntrpsVo selectManageEntrpsRow(HashMap hashMap) throws SQLException {
		return getSqlSession().selectOne("store.selectManageEntrpsRow", hashMap);
	}
	
	@Override
	public int updateManageEntrps(HashMap hashMap) throws SQLException {
		 return getSqlSession().update("store.updateManageEntrps", hashMap);
	}
	
	@Override
	public int deleteManageEntrps(HashMap hashMap) throws SQLException {
		return getSqlSession().update("store.deleteManageEntrps", hashMap);
	}
	
	@Override
	public int insertManageEntrps(HashMap hashMap) throws SQLException {
		return getSqlSession().insert("store.insertManageEntrps", hashMap);
	}
	
	/*매장관리**********************************************************************/
	@Override
	public List<StoreVo> selectStoreList(HashMap map) throws SQLException {
		return getSqlSession().selectList("store.selectStoreList", map);
	}
	@Override
	public StoreVo selectStoreRow(String cm_code) throws SQLException {
		return getSqlSession().selectOne("store.selectStoreRow", cm_code);
	}
	
	
	@Override
	public void saveStore(StoreVo storeDto) throws SQLException {
		 getSqlSession().update("store.sp_save_50100", storeDto);
	}
	
	@Override
	public int updateStore(HashMap hashMap) throws SQLException {
		return getSqlSession().update("store.updateStore", hashMap);
	}
	
	@Override
	public int deleteStore(HashMap hashMap) throws SQLException {
		return getSqlSession().update("store.deleteStore", hashMap);
	}
	
	@Override
	public int insertStore(HashMap hashMap) throws SQLException {
		return getSqlSession().insert("store.insertStore", hashMap);
	}
	
	@Override
	public List<EmplManageVo> selectEmpList(HashMap hashMap){
		return getSqlSession().selectList("store.selectManageEntrpsList", hashMap);
	}
	
	@Override
	public List<KeyValueVo> emplAutoComplate(HashMap hashMap) throws SQLException {
		return getSqlSession().selectList("store.emplAutoComplate", hashMap);
	}

	/*매장현황 조회***************************************************************************/
	
	@Override
	public NaviVo selectStoreAllCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("store.selectStoreAllCount", naviVo) );
		return naviVo;
	}
	
	@Override
	public List<StoreVo> selectStoreAllList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("store.selectStoreAllList", naviVo);
	}
	
	@Override
	public List<StoreVo> selectStoreAllExcelList(StoreVo vo) throws SQLException {
		return getSqlSession().selectList("store.selectStoreAllExcelList", vo);
	}
	
	@Override
	public List<StoreVo> selectJuso() throws SQLException {
		return getSqlSession().selectList("store.selectJuso");
	}
	
	@Override
	public int saveJuso(StoreVo storeDto) throws SQLException {
		return getSqlSession().update("store.saveJuso", storeDto);
	}
}
