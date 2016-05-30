package com.vertexid.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vertexid.vo.AttachVo;


public interface AttachDAO {
	
//	@Transactional("multi")
	public int attachInsertMulti(List<AttachVo> voList,String userId, int file_m_seq) throws SQLException;
	
	//2015.10.29 by zzz2613
//	@Transactional("multi")
	public int attachInsertMulti(List<AttachVo> voList,String userId, int file_m_seq, String type) throws SQLException;
	
	public AttachVo attachItem(int ai_no)throws SQLException;
	
	//2015.12.03 by zzz2613
	public AttachVo attachItem_apk(int ai_no)throws SQLException;

	public List<AttachVo> attachList(int am_no)throws SQLException;

	public int attachDelete(int fi_seq)throws SQLException;
	
}
