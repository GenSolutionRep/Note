package com.dasa.communication.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.dasa.communication.dao.NoticeMessageDAO;
import com.dasa.communication.vo.NoticeMessageVo;
import com.dasa.communication.vo.Notification;
import com.dasa.communication.vo.SendNotification;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;

public class NoticeMessageService extends SqlSessionDaoSupport  implements NoticeMessageDAO {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private SendNotification sendNotification;
	
	@Override
	public List<KeyValueVo> msgAutoComplate(Map<String,Object> map) throws SQLException {
		return getSqlSession().selectList("msgAutoComplate", map);
	}
	
	@Override
	public List<NoticeMessageVo> noticeMessageList(NaviVo naviVo) throws SQLException {
		return  getSqlSession().selectList("noticeMessageList", naviVo);
	}
	
	@Override
	public List<NoticeMessageVo> receiverdCnt(NaviVo naviVo) throws SQLException {
		return  getSqlSession().selectList("receiverdCnt", naviVo);
	}

	@Override
	public NaviVo noticeMessageListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("noticeMessageListCnt" ,naviVo));
		return naviVo;
	}

	@Override
	public String noticeMessageSave(NoticeMessageVo noticeMessageVo) throws SQLException {
		int cnt = 0;
		
		if(noticeMessageVo.getSaveType().equals("I")){
			//String  nm_innb = (String)getSqlSession().selectOne("noticeMessageNextVal");
			//noticeMessageVo.setNm_innb(nm_innb);
			//System.out.println("noticeMessageVo : " + noticeMessageVo.toString());
			
			cnt = getSqlSession().insert("noticeMessageMasterInsert",noticeMessageVo);
			
			List<Map<String, String>> omArr = noticeMessageVo.getOmArr();
			List<Map<String, String>> smArr = noticeMessageVo.getSmArr();
			List<Map<String, String>> emArr = noticeMessageVo.getEmArr();
			
			for (int i=0; i < omArr.size(); i++) {
				Map<String, String> omMap = omArr.get(i);
				Map<String, String> smMap = smArr.get(i);
				Map<String, String> emMap = emArr.get(i);
				
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("nm_innb", noticeMessageVo.getNm_innb());
				paramMap.put("om_code", omMap.get("om_code"));
				paramMap.put("sm_code", smMap.get("sm_code"));
				paramMap.put("em_code", emMap.get("em_code"));
				paramMap.put("regist_man", noticeMessageVo.getRegist_man());
				
				cnt = getSqlSession().insert("noticeMessageInsert",paramMap);
			}
		}
		return cnt > 0 ? noticeMessageVo.getNm_innb():"";
	}
	

	@Override
	public int noticeMessageFileSave(String bmInnb, int amNo) throws SQLException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bm_innb", bmInnb);
		map.put("am_no", amNo+"");		
		return getSqlSession().update("noticeAmNoUpdate", map);
	}

	@Override
	public List<NoticeMessageVo> noticeMessageView(String bmInnb) throws SQLException {
		return getSqlSession().selectList("noticeMessageView",bmInnb);
	}

	@Override
	public List<KeyValueVo> noticeOmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("noticeOmList", bmInnb);
	}

	@Override
	public List<KeyValueVo> noticeSmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("noticeSmList", bmInnb);
	}
	
	@Override
	public List<NoticeMessageVo> selectReceivedList(Map<String,Object> map) throws SQLException {
		return getSqlSession().selectList("selectReceivedList", map);
	}
	
	@Override
	public List<OrganizationVo> selectListByCp(String cm_code)throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationListByCp", cm_code);
	}
	
	@Override
	public List<OrganizationVo> organizationListSm(String cm_code)throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationListByBhf", cm_code);
	}
	
	@Override
	public List<OrganizationVo> selectDepthList(HashMap hashMap) throws SQLException {
		return getSqlSession().selectList("organization.selectDepthList", hashMap);
	}
	
	@Override
	public List<NoticeMessageVo> noticePopupOmlist(String om_code) throws SQLException {
		return getSqlSession().selectList("noticePopupOmlist",om_code);
	}
	
	@Override
	public int noticeMessagePush(String nm_innb) throws SQLException {
        //SendNotification sendNotification = new SendNotification();
		List<NoticeMessageVo> list = new ArrayList<NoticeMessageVo>();
		List<Notification> MsgNotiA = new ArrayList<Notification>();
		List<Notification> MsgNotiI = new ArrayList<Notification>();
		HashMap<String, String> map = new HashMap<String, String>();
		
		String auth_flag =  (String)session.getAttribute("auth_flag"); //권한코드
		String auth_Code = "";
		String result = "";
		
		if(auth_flag.equals("1")) {
			auth_Code = "0000000009";	
		}else if(auth_flag.equals("2")) {
			auth_Code = "0000000008";
		}else if(auth_flag.equals("4")) {
			auth_Code = "0000000007";
		}else if(auth_flag.equals("3")) {
			auth_Code = "0000000006";
		}
		map.put("auth_flag", auth_Code);
		map.put("nm_innb", nm_innb);
		
		list =getSqlSession().selectList("noticeMessagePushList", map);
		 
		for(NoticeMessageVo vo : list) {
			Notification notiA = new Notification();
			Notification notiI = new Notification();
			
			String em_push_id = vo.getEm_push_id();
			String em_device_type = vo.getEm_device_type();
			result = "[알림메시지] " + vo.getNm_massage();
			
//			System.out.println("em_device_type:" +em_device_type );
			
			if(em_device_type.equals("A")){
				notiA.setStringPropRegId(em_push_id);
				notiA.setDeviceType(em_device_type);
				MsgNotiA.add(notiA);
			}else if(em_device_type.equals("I")){
				notiI.setStringPropRegId(em_push_id);
				notiI.setDeviceType(em_device_type);
				
				//notiI.setRunMode("DEV");
				//notiI.setCertificatePath("C:\\DASA\\iphone_dev.p12");
				//notiI.setCertificatePath("C:\\DASA\\PAMS_APNS_T.p12");
				//notiI.setCertificatePassword("vertexid");
				
				MsgNotiI.add(notiI);
			}
			
		}
		
		try {
			sendNotification.sendPushMessage(MsgNotiA, MsgNotiI, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list.size();
	}
	
	//목록조회한 알림메세지 읽음처리
	public int changeReadY(NoticeMessageVo noticeMessageVo) throws SQLException {
		return getSqlSession().update("NoticeMessage.changeReadY", noticeMessageVo);
	}
	
}
