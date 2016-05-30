package com.gensolution.activity.dao;

import java.sql.SQLException;
import java.util.List;

import com.dasa.analysis.vo.AttandanceVo;
import com.gensolution.activity.vo.ActivityTeamleaderVo;
import com.vertexid.vo.NaviVo;

public interface ActivityTeamleaderDAO {

	public NaviVo selectListCount(NaviVo naviVo) throws SQLException;

	public List<ActivityTeamleaderVo> selectList(NaviVo naviVo) throws SQLException;
	
	public List<ActivityTeamleaderVo> selectExcelList(ActivityTeamleaderVo vo) throws SQLException;

}
