package com.dasa.analysis.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.analysis.vo.AttandanceVo;
import com.gensolution.analysis.dao.AttandanceDao;
import com.vertexid.vo.NaviVo;

public class AttandanceService extends SqlSessionDaoSupport implements AttandanceDao {
	@Override
	public NaviVo selectListCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("attandance.selectListCount", naviVo) );
		return naviVo;
	}
	
	@Override
	public List<AttandanceVo> selectList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("attandance.selectList", naviVo);
	}
	
	@Override
	public List<AttandanceVo> selectAllList(AttandanceVo vo) throws SQLException {
		return getSqlSession().selectList("attandance.selectAllList", vo);
	}
	
}
