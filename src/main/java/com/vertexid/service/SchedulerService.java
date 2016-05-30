package com.vertexid.service;

import java.sql.SQLException;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.analysis.vo.AttandanceVo;
import com.vertexid.dao.SchedulerDAO;

public class SchedulerService extends SqlSessionDaoSupport  implements SchedulerDAO {
	
	@Override
	public void sp_summary_emp_attandance(AttandanceVo vo) throws SQLException {
		 getSqlSession().update("attandance.sp_summary_emp_attandance", vo);
		 return;
	}
	
}
