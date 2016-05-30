package com.dasa.communication.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dasa.communication.vo.MainEventTargetVo;
import com.dasa.communication.vo.MainEventVo;
import com.vertexid.vo.KeyValueVo;


public interface MainEventDAO {

	public List<MainEventVo> selectList(HashMap<String, String> map)throws SQLException;
	
	public MainEventVo selectRow(String me_innb) throws SQLException;
	
	public List<MainEventVo> selectScheduleList(MainEventVo vo)throws SQLException;

	public int insertMainEvent(MainEventVo vo)throws SQLException;
	
	public int updateMainEvent(MainEventVo vo)throws SQLException;
	
	public int updateEventPeriod(MainEventVo vo)throws SQLException;
	
	public int deleteMainEvent(MainEventVo vo)throws SQLException;
	
	public String saveMainEvent(MainEventVo vo)throws SQLException;
	
	public int saveFile(String me_innb, int seq) throws SQLException;
	
	public List<MainEventVo> selectTargetList(MainEventTargetVo vo)throws SQLException;
	
	public MainEventVo selectTargetRow(String me_innb) throws SQLException;

	public int insertTarget(MainEventTargetVo vo)throws SQLException;
	
	public int updateTarget(MainEventTargetVo vo)throws SQLException;

	public int deleteTarget(MainEventTargetVo vo)throws SQLException;
	
	
	public List<KeyValueVo> autoComplate_store(Map<String, String> map) throws SQLException; 

	public List<KeyValueVo> autoComplate_bhf(Map<String, String> map) throws SQLException;
	
	
	public List<KeyValueVo> omList(String me_innb) throws SQLException;

	public List<KeyValueVo> smList(String me_innb) throws SQLException;
	
	public int eventPush(String flag, String me_innb, String me_sj) throws SQLException;
	
	public List<MainEventVo> eventDashList(HashMap<String, String> map) throws SQLException;
}
