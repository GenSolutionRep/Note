package com.dasa.communication.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.dasa.communication.dao.BusinessOrderDAO;
import com.dasa.communication.vo.BusinessOrderVo;
import com.dasa.communication.vo.Notification;
import com.dasa.communication.vo.SendNotification;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;

public class BusinessOrderService extends SqlSessionDaoSupport  implements BusinessOrderDAO {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private SendNotification sendNotification;
	
	@Override
	public List<OrganizationVo> selectListByCp(String cm_code)throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationListByCp", cm_code);
	}
	
	@Override
	public NaviVo businessOrderListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("businessOrderListCnt" ,naviVo));
		return naviVo;
	}
	
	@Override
	public NaviVo businessPopupListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("businessPopupListCnt" ,naviVo));
		return naviVo;
	}
	
	@Override
	public List<BusinessOrderVo> businessOrderList(NaviVo naviVo)throws SQLException {
		return getSqlSession().selectList("businessOrderList", naviVo);
	}

	@Override
	public List<BusinessOrderVo> businessPopupList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("businessPopupList",naviVo);
	}
	/*@Override
	public List<BusinessOrderVo> businessPopupList(NaviVo naviVo)throws SQLException {
		return getSqlSession().selectList("businessPopupList", naviVo);
	}*/
	
	@Override
	public List<KeyValueVo> martAutoComplate(Map<String,Object> map) throws SQLException {
		return getSqlSession().selectList("Business.martAutoComplate", map);
	}

	@Override
	public List<KeyValueVo> martAutoComplate_cp(Map<String,Object> map) throws SQLException {
		return getSqlSession().selectList("Business.martAutoComplate_cp", map);
	}
	
	@Override
	public List<KeyValueVo> emplAutoComplate(Map<String,Object> map) throws SQLException {
		return getSqlSession().selectList("Business.emplAutoComplate", map);
	}
	
	/*@Override
	public List<BusinessOrderVo> noticeList(NaviVo naviVo) throws SQLException {
		return  getSqlSession().selectList("Business.noticeList", naviVo);
	}*/

	/*public NaviVo noticeListCnt(NaviVo naviVo) throws SQLException {
	@Override
		naviVo.setTotRow((Integer)getSqlSession().selectOne("Business.noticeListCnt" ,naviVo));
		return naviVo;
	}*/

	@Override
	public String businessSave(BusinessOrderVo businessOrderVo) throws SQLException {
		int cnt = 0;
		if(businessOrderVo.getSaveType().equals("I")){
			businessOrderVo.setBm_code("2");  //게시판 종류(공지사항(1), 업무지시(2))
			cnt = getSqlSession().insert("businessInsert",businessOrderVo); //게시판 insert
			if(cnt > 0 ){
				List<Map<String, String>> omArr = businessOrderVo.getOmArr();
				if (omArr == null) {
					System.out.println("전달받은 수신지점목록이 없습니다.");
				}else {
					try{
						for(Map<String,String> map : businessOrderVo.getOmArr() ) {
							map.put("bm_innb", businessOrderVo.getBm_innb());
							map.put("bt_flag", "1");
							map.put("regist_man", businessOrderVo.getRegist_man());
							cnt = getSqlSession().insert("businessOmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				List<Map<String, String>> smArr = businessOrderVo.getSmArr();
				if (smArr == null) {
					System.out.println("전달받은 수신매장목록이 없습니다.");
				}else {
					try{
						for(Map<String,String> map : businessOrderVo.getSmArr() ) {
							map.put("bm_innb", businessOrderVo.getBm_innb());
							map.put("bt_flag", "2");
							map.put("regist_man", businessOrderVo.getRegist_man());
							cnt = getSqlSession().insert("businessSmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				List<Map<String, String>> emArr = businessOrderVo.getEmArr();
				if (emArr == null) {
					System.out.println("전달받은 사원목록이 없습니다.");
				}else {
					try{
						for(Map<String,String> map : businessOrderVo.getEmArr() ) {
							map.put("bm_innb", businessOrderVo.getBm_innb());
							map.put("bt_flag", "3");
							map.put("em_code", map.get("em_no"));
							map.put("regist_man", businessOrderVo.getRegist_man());
							cnt = getSqlSession().insert("businessEmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
//				System.out.println("businessOrderVo : " + businessOrderVo);
				cnt = getSqlSession().insert("businessInsertFinish",businessOrderVo); //업무지시 insert
			}
		}else{ 
			cnt = getSqlSession().update("businessUpdate",businessOrderVo);
//			String bm_innb = businessOrderVo.getBm_innb();
//			Map<String,String> delmap = new HashMap<String, String>();
//			delmap.put("cm_innb", bm_innb);
//			delmap.put("cmmnc_se", "2");
//			getSqlSession().delete("businessOmDelete",delmap);
//			getSqlSession().delete("businessSmDelete",delmap);
//			delmap.put("cm_innb", bm_innb);
//			getSqlSession().delete("businessOmDelete",delmap);
		
//			if(cnt > 0 ){
//				try{
//					for(Map<String,String> map : businessOrderVo.getOmArr() ) {
//						map.put("bm_innb", bm_innb);
//						map.put("bt_flag", "1");
//						map.put("regist_man", businessOrderVo.getRegist_man());
//						cnt = getSqlSession().insert("businessOmInsert",map);
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				
//				try{
//					for(Map<String,String> map : businessOrderVo.getSmArr() ) {
//						map.put("bm_innb", bm_innb);
//						map.put("bt_flag", "2");
//						map.put("regist_man", businessOrderVo.getRegist_man());
//						cnt = getSqlSession().insert("businessSmInsert",map);
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				
//				try{
//					for(Map<String,String> map : businessOrderVo.getEmArr() ) {
//						map.put("bm_innb", bm_innb);
//						map.put("bt_flag", "3");
//						map.put("em_code", map.get("em_no"));
//						map.put("regist_man", businessOrderVo.getRegist_man());
//						cnt = getSqlSession().insert("businessEmInsert",map);
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//			cnt = getSqlSession().insert("businessInsertFinish",businessOrderVo); //업무지시 insert
		}
		return cnt > 0 ? businessOrderVo.getBm_innb():"";
	}

	@Override
	public int noticeFileSave(String bmInnb, int amNo) throws SQLException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bm_innb", bmInnb);
		map.put("am_no", amNo+"");		
		return getSqlSession().update("businessFileSave", map);
	}

	@Override
	public BusinessOrderVo businessView(String bmInnb) throws SQLException {
		return (BusinessOrderVo)getSqlSession().selectOne("businessView",bmInnb);
	}
	
	/*@Override
	public BusinessOrderVo businessInserType(String bmInnb) throws SQLException{
		return (BusinessOrderVo)getSqlSession().selectOne("businessInsertType",bmInnb);
	}*/
	
	@Override
	public BusinessOrderVo businessReceiverView(BusinessOrderVo businessOrderVo) throws SQLException {
		return (BusinessOrderVo) getSqlSession().selectOne("businessReceiverView", businessOrderVo);
	}
	
	@Override
	public int businessReceiverReply(BusinessOrderVo businessOrderVo) throws SQLException {
		return getSqlSession().update("businessReceiverReply", businessOrderVo);
	}	
	
	@Override
	public List<KeyValueVo> businessOmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("businessOmList", bmInnb);
	}

	@Override
	public List<KeyValueVo> businessSmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("businessSmList", bmInnb);
	}
	
	@Override
	public int businessPopupDelete(HashMap map) throws SQLException {
		return getSqlSession().update("businessPopupDelete",map);
	}
	
	@Override
	public List<KeyValueVo> businessEmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("businessEmList", bmInnb);
	}
	
	@Override
	public List<KeyValueVo> businessTmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("businessTmList", bmInnb);
	}
	
	@Override
	public List<KeyValueVo> bizFixRoundList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("bizFixRoundList", bmInnb);
	}

	@Override
	public int businessPush(String bm_innb) throws SQLException {
		//SendNotification sendNotification = new SendNotification();
		List<BusinessOrderVo> list = new ArrayList<BusinessOrderVo>();
		List<Notification> bizNoti1 = new ArrayList<Notification>();
		List<Notification> bizNoti2 = new ArrayList<Notification>();
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
		map.put("bm_innb", bm_innb);
		
		list =getSqlSession().selectList("businessPushList", map);
		
		for(BusinessOrderVo vo : list) {
			Notification noti1 = new Notification();
			Notification noti2 = new Notification();
			
			String em_push_id = vo.getEm_push_id();
			String em_device_type = vo.getEm_device_type();
			result = "[업무지시] " + vo.getBm_sj();
			
//			System.out.println("em_device_type:" +em_device_type );
			
			if(em_device_type.equals("A")){
				noti1.setStringPropRegId(em_push_id);
				noti1.setDeviceType(em_device_type);
				bizNoti1.add(noti1);
			}else if(em_device_type.equals("I")){
				noti2.setStringPropRegId(em_push_id);
				noti2.setDeviceType(em_device_type);
				
				/*noti2.setRunMode("REAL");
				noti2.setCertificatePath("C:\\DASA\\iphone_rel.p12");
				noti2.setCertificatePassword("vertexid");*/
				
				bizNoti2.add(noti2);
			}
		}
		try {
			sendNotification.sendPushMessage(bizNoti1, bizNoti2, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.size();
	}
	
	@Override
	public int businessPush2(String bm_innb, String em_no) throws SQLException {
		//SendNotification sendNotification = new SendNotification();
		List<BusinessOrderVo> list = new ArrayList<BusinessOrderVo>();
		List<Notification> bizNoti1 = new ArrayList<Notification>();
		List<Notification> bizNoti2 = new ArrayList<Notification>();
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
		map.put("bm_innb", bm_innb);
		map.put("em_no", em_no);	
		
		list =getSqlSession().selectList("businessPushList2", map);
 
		for(BusinessOrderVo vo : list) {
			Notification noti1 = new Notification();
			Notification noti2 = new Notification();
			
			String em_push_id = vo.getEm_push_id();
			String em_device_type = vo.getEm_device_type();
			result = "[업무지시] " + vo.getBm_sj();
			
			if(em_device_type.equals("A")){
				noti1.setStringPropRegId(em_push_id);
				noti1.setDeviceType(em_device_type);
				bizNoti1.add(noti1);
			}else if(em_device_type.equals("I")){
				noti2.setStringPropRegId(em_push_id);
				noti2.setDeviceType(em_device_type);
				
				/*noti2.setRunMode("REAL");
				noti2.setCertificatePath("C:\\DASA\\iphone_rel.p12");
				noti2.setCertificatePassword("vertexid");*/
				
				bizNoti2.add(noti2);
			}
			 
		}
		
		try {
			sendNotification.sendPushMessage(bizNoti1, bizNoti2, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list.size();
	}
}
