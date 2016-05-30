package com.gensolution.activity.dao;

import java.sql.SQLException;
import java.util.List;

import com.gensolution.activity.vo.ActivityCvsItemVo;
import com.gensolution.activity.vo.ActivityCvsVo;
import com.vertexid.vo.NaviVo;

public interface ActivityCvsDAO {


	public NaviVo cvsListCnt(NaviVo naviVo) throws SQLException;

	public List<ActivityCvsVo> cvsList(NaviVo naviVo)throws SQLException;

	public List<ActivityCvsItemVo> cvsItemList(String drcInnb) throws SQLException;

	public String cvsGetMatter(String drcInnb) throws SQLException;
}
