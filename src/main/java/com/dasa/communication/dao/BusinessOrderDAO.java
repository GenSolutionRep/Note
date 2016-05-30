package com.dasa.communication.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.dasa.communication.vo.BusinessOrderVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;


public interface BusinessOrderDAO {

	public List<KeyValueVo> martAutoComplate(Map<String,Object> map) throws SQLException;
	
	public List<KeyValueVo> martAutoComplate_cp(Map<String,Object> map) throws SQLException;
	
	public List<KeyValueVo> emplAutoComplate(Map<String,Object> map) throws SQLException;

	//public List<BusinessOrderVo> noticeList(NaviVo naviVo) throws SQLException;

	//public NaviVo noticeListCnt(NaviVo naviVo) throws SQLException;

	@Transactional
	public String businessSave(BusinessOrderVo businessOrderVo) throws SQLException;

	public int noticeFileSave(String bmInnb, int seq) throws SQLException;

	public BusinessOrderVo businessView(String bmInnb) throws SQLException;
	
	//public BusinessOrderVo businessInserType(String bmInnb) throws SQLException;
	
	public BusinessOrderVo businessReceiverView(BusinessOrderVo businessOrderVo) throws SQLException;
	
	public int businessReceiverReply(BusinessOrderVo businessOrderVo) throws SQLException;
	
	public List<KeyValueVo> businessOmList(String bmInnb) throws SQLException;

	public List<KeyValueVo> businessSmList(String bmInnb) throws SQLException;
	
	public List<KeyValueVo> businessEmList(String bmInnb) throws SQLException;

	public List<KeyValueVo> businessTmList(String bmInnb) throws SQLException;

	public List<KeyValueVo> bizFixRoundList(String bmInnb) throws SQLException;
	
	public List<OrganizationVo> selectListByCp(String cm_code)  throws SQLException;

	public NaviVo businessPopupListCnt(NaviVo naviVo) throws SQLException;
	
	public NaviVo businessOrderListCnt(NaviVo naviVo) throws SQLException;
	
	public List<BusinessOrderVo> businessOrderList(NaviVo naviVo) throws SQLException;
	
	public int businessPopupDelete(HashMap hashMap) throws SQLException;
	
	public List<BusinessOrderVo> businessPopupList(NaviVo naviVo) throws SQLException;

	public int businessPush(String bm_innb) throws SQLException;
	
	public int businessPush2(String bm_innb, String em_no) throws SQLException;
}

