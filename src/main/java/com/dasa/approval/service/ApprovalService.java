package com.dasa.approval.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.dasa.approval.dao.ApprovalDao;
import com.dasa.approval.vo.ApprovalVo;
import com.dasa.communication.vo.BusinessOrderVo;
import com.dasa.communication.vo.Notification;
import com.dasa.communication.vo.SendNotification;
import com.vertexid.vo.NaviVo;

public class ApprovalService extends SqlSessionDaoSupport implements ApprovalDao {
	
	@Autowired
	private SendNotification sendNotification;
	
	@Override
	public NaviVo selectApprovalListCount(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("approval.selectApprovalListCount", naviVo) );
		return naviVo;
	}
	
	@Override
	public List<ApprovalVo> selectApprovalList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("approval.selectApprovalList", naviVo);
	}
	
	@Override
	public List<ApprovalVo> selectRejectList(String am_code) throws SQLException {
		return getSqlSession().selectList("approval.selectRejectList", am_code);
	}
	
	@Override
	public ApprovalVo selectApprovalRow(ApprovalVo vo) throws SQLException {
		return getSqlSession().selectOne("approval.selectApprovalRow", vo);
		
	}
	
	@Override
	public int getCheckCount(ApprovalVo vo) throws SQLException {
		int checkCount = (Integer) getSqlSession().selectOne("approval.dupCheckCount", vo);
		return checkCount;
	}
	
	@Override
	public void saveApproval(ApprovalVo p_vo) throws SQLException {
		getSqlSession().update("approval.SP_SAVE_30100", p_vo);

		System.out.println("p_vo.getAm_code() : " + p_vo.getAm_code());
		System.out.println("p_vo.getRes_code() : " + p_vo.getRes_code());
		System.out.println("p_vo.getRes_am_code() : " + p_vo.getRes_am_code());
		System.out.println("p_vo.getFlag() : " + p_vo.getFlag());
		System.out.println("p_vo.getRes_msg : " + p_vo.getRes_msg());
		
		
		if(p_vo.getRes_code().equals("0") && (p_vo.getFlag().equals("INSERT") || p_vo.getFlag().equals("UPDATE")) ){ //상신
			ApprovalVo resVo = new ApprovalVo();
			
			
			List<Notification> bizNoti1 = new ArrayList<Notification>();
			List<Notification> bizNoti2 = new ArrayList<Notification>();
			
			Notification noti1 = new Notification();
			Notification noti2 = new Notification();
			
		 	resVo = getSqlSession().selectOne("approval.selectPushList", p_vo);
		 	
			if(resVo !=null && resVo.getEm_push_id().trim() != ""){
				String em_push_id = resVo.getEm_push_id();
				String em_device_type = resVo.getEm_device_type();
				String subject = "[전자결재] " + resVo.getEm_nm() + " 상신" ;
				
				System.out.println("em_device_type:" +em_device_type );
				
				if(em_device_type.equals("A")){
					System.out.println("em_push_id:" +em_push_id );
					System.out.println("em_device_type:" +em_device_type );
					System.out.println("vo.getEm_nm:" +resVo.getEm_nm() );
					System.out.println("subject:" +subject);
					noti1.setStringPropRegId(em_push_id);
					noti1.setDeviceType(em_device_type);
					bizNoti1.add(noti1);
				}else if(em_device_type.equals("I")){
					
					noti2.setStringPropRegId(em_push_id);
					noti2.setDeviceType(em_device_type);
					
					noti2.setRunMode("REAL");
					noti2.setCertificatePath("C:\\DASA\\iphone_rel.p12");
					noti2.setCertificatePassword("vertexid");
					
					bizNoti2.add(noti2);
				}
				
				try {
					sendNotification.sendPushMessage(bizNoti1, bizNoti2, subject);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 	}
		}
	}
	
	@Override
	public int amNoUpdate(ApprovalVo vo) throws SQLException {
		return getSqlSession().update("approval.amNoUpdate", vo);
	}
}
