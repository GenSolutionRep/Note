package com.gensolution.analysis.dao;

import java.sql.SQLException;
import java.util.List;

import com.dasa.analysis.vo.AttandanceVo;
import com.vertexid.vo.NaviVo;

public interface AttandanceDao {
	
	/**
	 * 사원근무실적
	 * @return List<Attandance>
	 */
	public NaviVo selectListCount(NaviVo naviVo) throws SQLException;
	
	public List<AttandanceVo> selectList(NaviVo naviVo) throws SQLException;
	
	public List<AttandanceVo> selectAllList(AttandanceVo vo) throws SQLException;
	
}
