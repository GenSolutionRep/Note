package com.vertexid.dao;

import java.sql.SQLException;

import com.dasa.analysis.vo.AttandanceVo;

public interface SchedulerDAO {
	
	public void sp_summary_emp_attandance(AttandanceVo vo) throws SQLException;
}
