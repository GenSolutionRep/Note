package com.dasa.communication.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.dasa.communication.dao.MainEventDAO;
import com.dasa.communication.vo.BusinessOrderVo;
import com.dasa.communication.vo.MainEventTargetVo;
import com.dasa.communication.vo.MainEventVo;
import com.dasa.communication.vo.Notification;
import com.dasa.communication.vo.SendNotification;
import com.vertexid.vo.KeyValueVo;


public class MainEventService extends SqlSessionDaoSupport  implements MainEventDAO {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private SendNotification sendNotification;
	
	@Override
	public List<MainEventVo> selectList(HashMap<String, String> map) throws SQLException {
		return getSqlSession().selectList("event.selectList", map);
	}
	
	@Override
	public List<MainEventVo> selectScheduleList(MainEventVo vo) throws SQLException {
		return getSqlSession().selectList("event.selectScheduleList", vo);
	}
	
	@Override
	public MainEventVo selectRow(String me_innb) throws SQLException {
		return getSqlSession().selectOne("event.selectRow", me_innb);
	}
	
	@Override
	public int insertMainEvent(MainEventVo vo) throws SQLException {
		return getSqlSession().insert("event.insertEvent", vo);
	}
	
	@Override
	public int updateEventPeriod(MainEventVo vo) throws SQLException {
		int period = getSqlSession().selectOne("event.getPeriod", vo);
		vo.setPeriod(period);
//		System.out.println("period" + period);
		return getSqlSession().update("event.updateEventPeriod", vo);
	}

	@Override
	public int updateMainEvent(MainEventVo vo) throws SQLException {
		return getSqlSession().update("event.updateEvent", vo);
	}
	
	@Override
	public int deleteMainEvent(MainEventVo vo) throws SQLException {
		return getSqlSession().update("event.deleteEvent", vo);
	}
	
	@Override
	public int saveFile(String me_innb, int amNo) throws SQLException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("me_innb", me_innb);
		map.put("am_no", amNo+"");		
		return getSqlSession().update("event.updateAmNo", map);
	}
	
	@Override
	public String saveMainEvent(MainEventVo vo) throws SQLException {
		int cnt = 0;
//		System.out.println("vo.getSaveType() : " + vo.getSaveType());
		if(vo.getSaveType().equals("INSERT")){
			cnt = getSqlSession().insert("event.insertEvent",vo);
			
			if(cnt > 0 ){
				List<Map<String, String>> omArr = vo.getOmArr();
				if (omArr == null) {
					System.out.println("전달받은 지점목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : vo.getOmArr() ) {
							map.put("me_innb", vo.getMe_innb());
							map.put("mt_flag", "1");
							map.put("regist_man", vo.getRegist_man());
							cnt = getSqlSession().insert("event.insertTarget",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				List<Map<String, String>> smArr = vo.getSmArr();
				if (smArr == null) {
					System.out.println("전달받은 매장목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : vo.getSmArr() ) {
							map.put("me_innb", vo.getMe_innb());
							map.put("mt_flag", "2");
							map.put("regist_man", vo.getRegist_man());
							cnt = getSqlSession().insert("event.insertTarget",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
//				System.out.println("******************************* vo.getMe_ntcn_at() : " + vo.getMe_ntcn_at());
				
				//push
				if(vo.getMe_ntcn_at().equals("Y")){
					if(omArr==null && smArr==null)
						eventPush("A", vo.getMe_innb(), vo.getMe_sj());
					else{
						eventPush("", vo.getMe_innb(), vo.getMe_sj());
					}
				}
			}
		}else{
			cnt = getSqlSession().update("event.updateEvent",vo);
			String me_innb = vo.getMe_innb();
			Map<String,String> delmap = new HashMap<String, String>();
			delmap.put("me_innb", me_innb);
			getSqlSession().delete("deleteTarget",delmap);
			
			if(cnt > 0 ){
				
				List<Map<String, String>> omArr = vo.getOmArr();
				if (omArr == null) {
					System.out.println("전달받은 지점목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : vo.getOmArr() ) {
							map.put("me_innb", vo.getMe_innb());
							map.put("mt_flag", "1");
							map.put("regist_man", vo.getRegist_man());
							cnt = getSqlSession().insert("event.insertTarget",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				List<Map<String, String>> smArr = vo.getSmArr();
				if (smArr == null) {
					System.out.println("전달받은 매장목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : vo.getSmArr() ) {
							map.put("me_innb", vo.getMe_innb());
							map.put("mt_flag", "2");
							map.put("regist_man", vo.getRegist_man());
							cnt = getSqlSession().insert("event.insertTarget",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}		
				//push
				if(vo.getMe_ntcn_at().equals("Y")){
					if(omArr==null && smArr==null)
						eventPush("A", vo.getMe_innb(), vo.getMe_sj());
					else{
						eventPush("", vo.getMe_innb(), vo.getMe_sj());
					}
				}
			}
			
//			System.out.println("******************************* vo.getMe_ntcn_at() : " + vo.getMe_ntcn_at());
			
		}
		return cnt > 0 ? vo.getMe_innb():"";
	}
	
	@Override
	public List<MainEventVo> selectTargetList(MainEventTargetVo vo) throws SQLException {
		return getSqlSession().selectList("event.selectTargetList", vo);
	}
	
	@Override
	public MainEventVo selectTargetRow(String me_innb) throws SQLException {
		return getSqlSession().selectOne("event.selectTargetRow", me_innb);
	}
	
	@Override
	public int insertTarget(MainEventTargetVo vo) throws SQLException {
		return getSqlSession().insert("event.insertTarget", vo);
	}

	@Override
	public int updateTarget(MainEventTargetVo vo) throws SQLException {
		return getSqlSession().update("event.updateTarget", vo);
	}
	
	@Override
	public int deleteTarget(MainEventTargetVo vo) throws SQLException {
		return getSqlSession().update("event.deleteTarget", vo);
	}
	
	@Override
	public List<KeyValueVo> autoComplate_store(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("event.autoComplate_store", map);
	}
	
	@Override
	public List<KeyValueVo> autoComplate_bhf(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("event.autoComplate_bhf", map);
	}
	
	@Override
	public List<KeyValueVo> omList(String me_innb) throws SQLException {
		return getSqlSession().selectList("event.omList", me_innb);
	}

	@Override
	public List<KeyValueVo> smList(String me_innb) throws SQLException {
		return getSqlSession().selectList("event.smList", me_innb);
	}
	
	@Override
	public int eventPush(String flag, String me_innb, String me_sj) throws SQLException {
		List<MainEventVo> list = new ArrayList<MainEventVo>();
		
		String em_push_id = null;
		String em_device_type = null;
		String result = "[주요행사] " + me_sj;
		
		if(flag.equals("A"))
			list =getSqlSession().selectList("eventAllPushList", me_innb);
		else
			list =getSqlSession().selectList("eventPushList", me_innb);
		
		List<Notification> android = new ArrayList<Notification>();
		List<Notification> ios = new ArrayList<Notification>();
		
		for(MainEventVo vo : list) {
			em_push_id = vo.getEm_push_id();
			em_device_type = vo.getEm_device_type();
			 
			//System.out.println("* flag / em_push_id / em_device_type / result : " + flag +", "+ em_push_id+", " + em_device_type +", " + result );
			
			Notification noti = new Notification();
			noti.setStringPropRegId(em_push_id);
			noti.setDeviceType(em_device_type);
			
			if(em_device_type.equals("A"))
				android.add(noti);
			else
				ios.add(noti);
		}
		
		try {
			sendNotification.sendPushMessage(android, ios, result);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return list != null ? 0 : list.size();
	}
	
	@Override
	public List<MainEventVo> eventDashList(HashMap<String, String> map) throws SQLException {
		return getSqlSession().selectList("event.eventDashList", map);
	}
}
