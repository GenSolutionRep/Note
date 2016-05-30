package com.vertexid.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.vertexid.dao.AttachDAO;
import com.vertexid.utill.CommonUtil;
import com.vertexid.vo.AttachVo;



public class AttachService extends SqlSessionDaoSupport implements AttachDAO{
	
	private int attachMainInsert(String userId) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("regist_man", userId);
		if(getSqlSession().insert("attachMainInsert",map) > 0){
			return Integer.parseInt(map.get("am_no"));
		}
		return -1;
	}
	
	@Override
	public int attachInsertMulti(List<AttachVo> voList,String userId,int amNo)  throws SQLException {
		int cnt = 0 ;
		int am_no = 0;
		if(voList.size() > 0 ){
			am_no = (amNo == 0 ? attachMainInsert(userId) :amNo);
			if(am_no > 0){
				for (AttachVo vo : voList) {
					vo.setAm_no(am_no);
					cnt += getSqlSession().insert("attachItemInsert",vo);
				}
			}
		}
		try {
			System.out.println("["+CommonUtil.getCurrentDateTime()+"]첨부파일등록(" + cnt + "개) em_no : "+userId+", am_no :"+am_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt > 0 ? am_no : -1;
	}
	
	//2015.10.29 by zzz2613
	@Override
	public int attachInsertMulti(List<AttachVo> voList, String userId, int amNo, String type) throws SQLException {
		int cnt = 0;
		int am_no = 0;
		if(voList.size() > 0){
			am_no = (amNo == 0 ? attachMainInsert(userId) : amNo);
			if (am_no > 0) {
				if (type.equals("OVERWRITE")) {
					int iCnt = getSqlSession().update("attachMainDelete", am_no);
					System.out.println("[type=OVERWRITE] 기존 첨부파일 " + iCnt + "개를 삭제처리 하였습니다.");
				}
				for (AttachVo vo : voList) {
					vo.setAm_no(am_no);
					cnt += getSqlSession().insert("attachItemInsert", vo);
				}
				try {
					System.out.println("["+CommonUtil.getCurrentDateTime()+"][type=" + type + "]첨부파일등록(" + cnt + "개) em_no : "+userId+", am_no :"+am_no);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return cnt > 0 ? am_no : -1;
	}	
	
	@Override
	public AttachVo attachItem(int ai_no) throws SQLException {
		return (AttachVo)getSqlSession().selectOne("attachItem",ai_no);
	}
	
	//2015.12.03 by zzz2613
	@Override
	public AttachVo attachItem_apk(int ai_no) throws SQLException {
		return (AttachVo)getSqlSession().selectOne("attachItem_apk",ai_no);
	}
	
	@Override
	public List<AttachVo> attachList(int am_no)  throws SQLException {
		return getSqlSession().selectList("attachList",am_no);
	}
	@Override
	public int attachDelete(int ai_no)  throws SQLException {
		return getSqlSession().delete("attachDelete",ai_no);
	}
}
