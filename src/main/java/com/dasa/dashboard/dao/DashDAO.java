package com.dasa.dashboard.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.dasa.dashboard.vo.DashVo;


public interface DashDAO {
	
	public List<DashVo> selectEmp(HashMap<String, String> map) throws SQLException;
	public DashVo selectFixAttdance(HashMap<String, String> map) throws SQLException;
	public DashVo selectRndAttdance(HashMap<String, String> map) throws SQLException;
}
