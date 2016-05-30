package com.gensolution.activity.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gensolution.activity.dao.ActivityRndPlanDAO;
import com.gensolution.activity.vo.ActivityRndPlanDayVo;
import com.gensolution.activity.vo.ActivityRndPlanScheVo;
import com.gensolution.activity.vo.ActivityRndPlanStrVo;
import com.gensolution.activity.vo.ActivityRndPlanVo;
import com.vertexid.utill.CommonUtil;

public class ActivityRndPlanService extends SqlSessionDaoSupport implements ActivityRndPlanDAO {

	@Override
	public List<ActivityRndPlanVo> getEmpListByTeam(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("getEmpListByTeam", map);
	}
 
	@Override
	public List<ActivityRndPlanVo> getActivityRndPlanHeaderList(
			Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("getActivityRndPlanHeaderList", map);
	}

	@Override
	public List<ActivityRndPlanVo> rndPlanList(HashMap<String, String> map)throws SQLException {
		return getSqlSession().selectList("rndPlanList", map);
	}

	@Override
	public List<ActivityRndPlanStrVo> rndPlanStrList(String emNo)throws SQLException {
		return getSqlSession().selectList("rndPlanStrList", emNo);
	}

	@Override
	public List<ActivityRndPlanStrVo> rndPlanStrSelectList(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("rndPlanStrSelectList", map);
	}

	@Override
	public List<ActivityRndPlanDayVo> rndPlanDayList(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("rndPlanDayList", map);
	}

	@Override
	public int rndPlanSave(ActivityRndPlanDayVo vo) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().insert("rndPlanSave", vo);
		cnt = getSqlSession().insert("rndPlanDaySave", vo);
		cnt = getSqlSession().insert("rndPlanDayItemDelete", vo);
		for (Map<String, String> map : vo.getSmArr()) {
			map.put("cm_code", vo.getCm_code());
			map.put("om_code", vo.getOm_code());
			map.put("regist_man", vo.getRegist_man());
			cnt = getSqlSession().insert("rndPlanDayItemSave", map);
		}
		return cnt;
	}

	@Override
	public int rndPlanMatterSave(ActivityRndPlanDayVo vo) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().insert("rndPlanSave", vo);
		cnt = getSqlSession().update("rndPlanMatterSave", vo);
		return cnt;
	}

	@Override
	public int rndMobilePlanSave(ActivityRndPlanDayVo vo) throws SQLException ,Exception{
		
		String emNo = vo.getEm_no();

		int cnt = 0;
		vo.setUpdt_man(emNo);
		vo.setRegist_man(emNo);
		cnt = getSqlSession().insert("rndPlanSave", vo);
		
		cnt = getSqlSession().insert("rndPlanDaySave", vo);
		
		
		Map<String, String> map =  new HashMap<String, String>();
		map.put("prdi_sm_code", vo.getPrdi_sm_code());
		map.put("plan_de", vo.getPlan_de());
		map.put("base_de", CommonUtil.getBaseDe(vo.getBase_de()));
		map.put("cm_code", vo.getCm_code());
		map.put("om_code", vo.getOm_code());
		map.put("regist_man", emNo);
		map.put("prdi_sm_code_nm", vo.getPrdi_sm_code_nm());
		
		cnt = getSqlSession().insert("rndPlanDayItemSave", map);
		return cnt;
	}

	@Override
	public List<ActivityRndPlanScheVo> rndPlanScheList(HashMap<String, String> map) throws SQLException {
		return  getSqlSession().selectList("rndPlanScheList", map);
	}

	@Override
	public int rndMobilePlanItemDelete(ActivityRndPlanDayVo vo)throws SQLException {
		int cnt = getSqlSession().delete("rndMobilePlanItemDelete", vo);
//		if(cnt > 0 ){
//			ActivityRndMobileAttendVo atVo = new ActivityRndMobileAttendVo();
//			atVo.setPlan_de(vo.getPlan_de());
//			atVo.setEm_no(vo.getEm_no());
//			int atenNotCnt = getSqlSession().selectOne("rndMobileAttendingNotCnt",atVo); //미출근 매장수
//			if(atenNotCnt == 0){
//				cnt = getSqlSession().insert("rndMobileLeavingManager",atVo);
//			}
//			cnt = getSqlSession().insert("rndMobilePlanItemDelete", vo);
//		}
		
//		ActivityRndMobileAttendVo atVo = new ActivityRndMobileAttendVo();
//		int atenOkCnt = getSqlSession().selectOne("rndMobileAttendingOkCnt",vo); // 출근 매장 수
//		
//		if(atenOkCnt == 0){
//			cnt = getSqlSession().insert("rndMobileAttending",atVo);
//			cnt = getSqlSession().insert("rndMobileAttendingManager",atVo);
//		}else{
//			cnt = getSqlSession().insert("rndMobileAttending",atVo);
//			if(atenNotCnt == 0){
//				cnt = getSqlSession().insert("rndMobileLeavingManager",atVo);
//			}
//		}
		
		return cnt;
	}

}
