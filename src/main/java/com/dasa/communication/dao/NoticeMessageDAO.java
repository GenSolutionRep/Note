package com.dasa.communication.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.communication.vo.NoticeMessageVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;


public interface NoticeMessageDAO {

	public List<KeyValueVo> msgAutoComplate(Map<String,Object> map) throws SQLException;
	
	public List<NoticeMessageVo> noticeMessageList(NaviVo naviVo)throws SQLException; 

	public List<NoticeMessageVo> receiverdCnt(NaviVo naviVo)throws SQLException; 
	
	public NaviVo noticeMessageListCnt(NaviVo naviVo) throws SQLException;

	@Transactional
	public String noticeMessageSave(NoticeMessageVo noticeMessageVo)throws SQLException;

	public int noticeMessageFileSave(String bmInnb, int seq)throws SQLException;

	public List<NoticeMessageVo> noticeMessageView(String nmInnb)throws SQLException;

	public List<KeyValueVo> noticeOmList(String bmInnb)throws SQLException;

	public List<KeyValueVo> noticeSmList(String bmInnb)throws SQLException;

	public List<NoticeMessageVo> selectReceivedList(Map<String,Object> map)throws SQLException;
	
	public List<OrganizationVo> selectListByCp(String cm_code)  throws SQLException;
	
	public List<OrganizationVo> organizationListSm(String cm_code)  throws SQLException;
	
	public List<OrganizationVo> selectDepthList(HashMap hashMap)  throws SQLException;
	
	public List<NoticeMessageVo> noticePopupOmlist(String om_code) throws SQLException;
	
	public int noticeMessagePush(String nm_innb)throws SQLException;
	
	//목록조회한 알림메세지 읽음처리
	public int changeReadY(NoticeMessageVo noticeMessageVo) throws SQLException;
}
