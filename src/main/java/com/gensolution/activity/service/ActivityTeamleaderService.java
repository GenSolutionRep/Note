package com.gensolution.activity.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.analysis.vo.AttandanceVo;
import com.gensolution.activity.dao.ActivityFixingDAO;
import com.gensolution.activity.dao.ActivityTeamleaderDAO;
import com.gensolution.activity.vo.ActivityEmVo;
import com.gensolution.activity.vo.ActivityEvnMobileSaveVo;
import com.gensolution.activity.vo.ActivityEvnMobileVo;
import com.gensolution.activity.vo.ActivityFixingEvnVo;
import com.gensolution.activity.vo.ActivityFixingOddVo;
import com.gensolution.activity.vo.ActivityFixingPogOptionVo;
import com.gensolution.activity.vo.ActivityFixingTrtVo;
import com.gensolution.activity.vo.ActivityFixingWorkVo;
import com.gensolution.activity.vo.ActivityGridMobileVo;
import com.gensolution.activity.vo.ActivityGridVo;
import com.gensolution.activity.vo.ActivityOddMobileVo;
import com.gensolution.activity.vo.ActivitySaveVo;
import com.gensolution.activity.vo.ActivityTeamleaderVo;
import com.gensolution.activity.vo.ActivityTrtMobileVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Encoder;
import com.vertexid.vo.NaviVo;

public class ActivityTeamleaderService extends SqlSessionDaoSupport implements ActivityTeamleaderDAO {
	
	@Override
	public NaviVo selectListCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("teamleader.selectListCount", naviVo) );
		return naviVo;
	}
	
	@Override
	public List<ActivityTeamleaderVo> selectList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("teamleader.selectList", naviVo);
	}
	
	@Override
	public List<ActivityTeamleaderVo> selectExcelList(ActivityTeamleaderVo vo) throws SQLException {
		return getSqlSession().selectList("teamleader.selectExcelList", vo);
	}
}
