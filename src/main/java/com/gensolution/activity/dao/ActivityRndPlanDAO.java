package com.gensolution.activity.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gensolution.activity.vo.ActivityRndPlanDayVo;
import com.gensolution.activity.vo.ActivityRndPlanScheVo;
import com.gensolution.activity.vo.ActivityRndPlanStrVo;
import com.gensolution.activity.vo.ActivityRndPlanVo;

public interface ActivityRndPlanDAO {
	public List<ActivityRndPlanVo> getEmpListByTeam(Map<String, String> map)throws SQLException;
	
	public List<ActivityRndPlanVo> getActivityRndPlanHeaderList(Map<String, String> map)throws SQLException;

	public List<ActivityRndPlanVo> rndPlanList(HashMap<String, String> map)throws SQLException;

	public List<ActivityRndPlanStrVo> rndPlanStrList(String emNo)throws SQLException;

	public List<ActivityRndPlanStrVo> rndPlanStrSelectList(Map<String, String> map)throws SQLException;

	public List<ActivityRndPlanDayVo> rndPlanDayList(Map<String, String> map)throws SQLException;

	public int rndPlanSave(ActivityRndPlanDayVo vo)throws SQLException;

	public int rndPlanMatterSave(ActivityRndPlanDayVo vo)throws SQLException;

	public int rndMobilePlanSave(ActivityRndPlanDayVo vo)throws SQLException ,Exception;

	public List<ActivityRndPlanScheVo> rndPlanScheList(HashMap<String, String> map)throws SQLException;

	public int rndMobilePlanItemDelete(ActivityRndPlanDayVo vo)throws SQLException;
}
