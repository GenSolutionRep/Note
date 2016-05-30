package com.dasa.mobile.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.mobile.dao.MobileDao;
import com.dasa.mobile.vo.MobileVo;
import com.vertexid.utill.CommonUtil;

/**
 * @파일명: MobileService.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 모바일 Service
 */
public class MobileService extends SqlSessionDaoSupport implements MobileDao {

	@Override
	public MobileVo getCurrentDate(MobileVo mobileVo) throws SQLException {
		return (MobileVo) getSqlSession().selectOne("mobile.getCurrentDate", mobileVo);
	}

	@Override
	public List<MobileVo> getCustomerGroupList() throws SQLException {
		return getSqlSession().selectList("mobile.getCustomerGroupList");
	}

	@Override
	public List<MobileVo> getManageEnterpriseListPerCustomerGroup(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getManageEnterpriseListPerCustomerGroup", mobileVo);
	}

	@Override
	public List<MobileVo> getStoreListPerManageEnterprise(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getStoreListPerManageEnterprise", mobileVo);
	}

	@Override
	public MobileVo getStoreViewOfFix(MobileVo mobileVo) throws SQLException {
		return (MobileVo) getSqlSession().selectOne("mobile.getStoreViewOfFix,Rnd", mobileVo);
	}

	@Override
	public List<MobileVo> getStoreListOfRnd(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getStoreViewOfFix,Rnd", mobileVo);
	}

	@Override
	public List<MobileVo> getStoreListOfTeamHeader(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getStoreListOfTeamHeader,Manager", mobileVo);
	}

	@Override
	public List<MobileVo> getStoreListOfManager(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getStoreListOfTeamHeader,Manager", mobileVo);
	}

	@Override
	public List<MobileVo> getStoreListPerBranch(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getStoreListPerBranch", mobileVo);
	}

	@Override
	public List<MobileVo> getBranchListPerCompany(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getBranchListPerCompany", mobileVo);
	}

	@Override
	public List<MobileVo> getEmployeeListPerBranch(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getEmployeeListPerBranch", mobileVo);
	}

	@Override
	public List<MobileVo> getEmployeeListPerStore(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getEmployeeListPerStore", mobileVo);
	}

	@Override
	public MobileVo getCommuteTime(MobileVo mobileVo) throws SQLException {
		return (MobileVo) getSqlSession().selectOne("mobile.getCommuteTime", mobileVo);
	}

	@Override
	public MobileVo setAttendingTime(MobileVo mobileVo) throws SQLException {
		getSqlSession().insert("mobile.setAttendingTime", mobileVo);
		return mobileVo;
	}

	@Override
	public MobileVo setLeavingTime(MobileVo mobileVo) throws SQLException {
		getSqlSession().update("mobile.setLeavingTime", mobileVo);
		return mobileVo;
	}
	
	@Override
	public List<MobileVo> getRndPlanList(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getRndPlanList", mobileVo);
	}
	
	@Override
	public int setRndPlan(MobileVo mobileVo) throws SQLException, Exception {
		int cnt = 0, cnt1 = 0, cnt2 = 0, cnt3 = 0, cnt4 = 0;
		
		cnt1 = getSqlSession().insert("actvityRndPlan.rndPlanSave", mobileVo);
		cnt2 = getSqlSession().insert("actvityRndPlan.rndPlanDaySave", mobileVo);
		cnt3 = getSqlSession().insert("actvityRndPlan.rndPlanDayItemDelete", mobileVo);
		
		List<Map<String, String>> mapList = CommonUtil.stringToList(mobileVo.getParams());
		for (int i = 0; i < mapList.size(); i++) {
			Map<String, String> map = mapList.get(i);
			map.put("cm_code", mobileVo.getCm_code());
			map.put("om_code", mobileVo.getOm_code());
			map.put("em_no", mobileVo.getEm_no());
			map.put("plan_de", mobileVo.getPlan_de());
			map.put("base_de", mobileVo.getBase_de());
			map.put("regist_man", mobileVo.getEm_no());
			map.put("updt_man", mobileVo.getEm_no());
			cnt4 += getSqlSession().insert("actvityRndPlan.rndPlanDayItemSave", map);
		}
		
		if (cnt1 > 0 && cnt2 > 0 && cnt3 >= 0 && cnt4 >= 0) {
			cnt = 1;
		}
		
		return cnt;
	}
	
	@Override
	public List<MobileVo> getFileInfo(MobileVo mobileVo) throws SQLException {
		return getSqlSession().selectList("mobile.getFileInfo", mobileVo);
	}
	
	@Override
	public int setDeviceError(MobileVo mobileVo) throws SQLException {
		return getSqlSession().insert("mobile.setDeviceError", mobileVo);
	}

}
