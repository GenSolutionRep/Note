package com.gensolution.activity.service;


import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gensolution.activity.dao.ActivityCvsDAO;
import com.gensolution.activity.vo.ActivityCvsItemVo;
import com.gensolution.activity.vo.ActivityCvsVo;
import com.vertexid.vo.NaviVo;

public class ActivityCvsService extends SqlSessionDaoSupport implements ActivityCvsDAO {


	@Override
	public NaviVo cvsListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("cvsListCnt" ,naviVo));
		return naviVo;
	}

	@Override
	public List<ActivityCvsVo> cvsList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("cvsList", naviVo);
	}
	@Override
	public List<ActivityCvsItemVo> cvsItemList(String drcInnb) throws SQLException {
		return getSqlSession().selectList("cvsItemList", drcInnb);
	}

	@Override
	public String cvsGetMatter(String drcInnb) throws SQLException {
		return  getSqlSession().selectOne("rndCvsMatter", drcInnb );
	}

}
