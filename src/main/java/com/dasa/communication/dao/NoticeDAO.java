package com.dasa.communication.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.communication.vo.NoticeVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;


public interface NoticeDAO {

	//public List<KeyValueVo> martAutoComplate(String keyword) throws SQLException;
	public List<KeyValueVo> martAutoComplate70100(Map<String,Object> map) throws SQLException;
	
	public List<KeyValueVo> teamAutoComplate70100(Map<String,Object> map) throws SQLException;
	
	public List<KeyValueVo> martAutoComplate_cp(String keyword) throws SQLException;

	public List<NoticeVo> noticeList(NaviVo naviVo)throws SQLException;

	public NaviVo noticeListCnt(NaviVo naviVo) throws SQLException;

	@Transactional
	public String noticeSave(NoticeVo noticeVo)throws SQLException;

	public int noticeFileSave(String bmInnb, int seq)throws SQLException;

	public NoticeVo noticeView(String bmInnb)throws SQLException;

	public int noticePopupDelete(HashMap hashMap) throws SQLException;
	
	public List<KeyValueVo> noticeOmList(String bmInnb)throws SQLException;

	public List<KeyValueVo> noticeSmList(String bmInnb)throws SQLException;
	
	public List<KeyValueVo> noticeEmList(String bmInnb)throws SQLException;
	
	public List<KeyValueVo> noticeTmList(String bmInnb)throws SQLException;

	public List<KeyValueVo> fixRoundList(String bmInnb) throws SQLException;
	
	public List<OrganizationVo> selectListByCp(String cm_code)  throws SQLException;
	
	public List<OrganizationVo> selectAuthDepthList(HashMap hashMap);
}
