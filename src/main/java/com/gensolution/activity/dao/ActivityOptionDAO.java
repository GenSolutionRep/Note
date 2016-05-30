package com.gensolution.activity.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gensolution.activity.vo.ActivityGridVo;
import com.gensolution.activity.vo.ActivityOptionTreeVo;
import com.gensolution.activity.vo.ActivityOptionVo;


public interface ActivityOptionDAO {


	public List<ActivityOptionVo> prdOptionList(String optionCode)throws SQLException;

	public List<ActivityOptionVo> entOptionList(String optionCode)throws SQLException;

	public int entOptionSave(ActivityOptionVo vo)throws SQLException;

	public int prdOptionSave(ActivityOptionVo vo)throws SQLException;

	public List<ActivityOptionVo> optionList(String optionCode)throws SQLException;

	public List<ActivityOptionTreeVo> optionTree(Map<String, String> map)throws SQLException;

	public ActivityOptionVo optionView(String optionCode)throws SQLException;

	public ActivityOptionVo optionSave(ActivityOptionVo vo)throws SQLException;

	public ActivityOptionVo optionCustomSave(ActivityOptionVo vo)throws SQLException;





}
