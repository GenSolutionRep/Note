package com.dasa.communication.service;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.communication.dao.NoticeDAO;
import com.dasa.communication.vo.NoticeVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;

public class NoticeService extends SqlSessionDaoSupport  implements NoticeDAO {

	@Override
	/*public List<KeyValueVo> martAutoComplate(String keyword) throws SQLException {
		return getSqlSession().selectList("Notice.martAutoComplate", keyword);
	}*/
	public List<KeyValueVo> martAutoComplate70100(Map<String,Object> map) throws SQLException {
		return getSqlSession().selectList("Notice.martAutoComplate70100", map);
	}
	
	public List<KeyValueVo> teamAutoComplate70100(Map<String,Object> map) throws SQLException {
		return getSqlSession().selectList("Notice.teamAutoComplate70100", map);
	}
	
	@Override
	public List<KeyValueVo> martAutoComplate_cp(String keyword) throws SQLException {
 
		return getSqlSession().selectList("Notice.martAutoComplate_cp", keyword);
	}
	
	@Override
	public List<NoticeVo> noticeList(NaviVo naviVo) throws SQLException {
		return  getSqlSession().selectList("noticeList", naviVo);
	}

	@Override
	public NaviVo noticeListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow((Integer)getSqlSession().selectOne("noticeListCnt" ,naviVo));
		return naviVo;
	}

	@Override
	public String noticeSave(NoticeVo noticeVo) throws SQLException {
		int cnt = 0;
//		System.out.println("noticeVo.getSaveType() : " + noticeVo.getSaveType());
		if(noticeVo.getSaveType().equals("I")){
			noticeVo.setBm_code("1");  //게시판 종류(공지사항, 업무지시)
			cnt = getSqlSession().insert("noticeInsert",noticeVo);
//			System.out.println(noticeVo.getBm_innb());
			if(cnt > 0 ){
				
				List<Map<String, String>> omArr = noticeVo.getOmArr();
				if (omArr == null) {
					System.out.println("전달받은 수신지점목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : noticeVo.getOmArr() ) {
							map.put("cm_innb", noticeVo.getBm_innb());
							map.put("cmmnc_se", "1");
							map.put("regist_man", noticeVo.getRegist_man());
							cnt = getSqlSession().insert("noticeOmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				/*List<Map<String, String>> smArr = noticeVo.getSmArr();
				if (smArr == null) {
					System.out.println("전달받은 수신매장목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : noticeVo.getSmArr() ) {
							map.put("cm_innb", noticeVo.getBm_innb());
							map.put("cmmnc_se", "2");
							map.put("regist_man", noticeVo.getRegist_man());
							cnt = getSqlSession().insert("noticeSmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}*/
				List<Map<String, String>> smArr = noticeVo.getSmArr();
				if (smArr == null) {
					System.out.println("전달받은 팀목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : noticeVo.getSmArr() ) {
							map.put("cm_innb", noticeVo.getBm_innb());
							map.put("cmmnc_se", "2");
							map.put("regist_man", noticeVo.getRegist_man());
							cnt = getSqlSession().insert("noticeSmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				List<Map<String, String>> emArr = noticeVo.getEmArr();
				if (emArr == null) {
					System.out.println("전달받은 사원목록이 없습니다.");
				}else {
					try{
						for(Map<String,String> map : noticeVo.getEmArr() ) {
							map.put("cm_innb", noticeVo.getBm_innb());
							map.put("cmmnc_se", "3");
							map.put("em_code", map.get("em_no"));
							map.put("regist_man", noticeVo.getRegist_man());
							cnt = getSqlSession().insert("noticeEmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				//System.out.println("noticeVo : " + noticeVo);
			}
			
		}else{
			cnt = getSqlSession().update("noticeUpdate",noticeVo);
			String bm_innb = noticeVo.getBm_innb();
			Map<String,String> delmap = new HashMap<String, String>();
			delmap.put("cm_innb", bm_innb);
			getSqlSession().delete("noticeOmDelete",delmap);
			if(cnt > 0 ){
				
				List<Map<String, String>> omArr = noticeVo.getOmArr();
				if (omArr == null) {
					System.out.println("전달받은 수신지점목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : noticeVo.getOmArr() ) {
							map.put("cm_innb", noticeVo.getBm_innb());
							map.put("cmmnc_se", "1");
							map.put("regist_man", noticeVo.getRegist_man());
							cnt = getSqlSession().insert("noticeOmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				List<Map<String, String>> smArr = noticeVo.getSmArr();
				if (smArr == null) {
					System.out.println("전달받은 수신매장목록이 없습니다.");
				} else {
					try{
						for(Map<String,String> map : noticeVo.getSmArr() ) {
							map.put("cm_innb", noticeVo.getBm_innb());
							map.put("cmmnc_se", "2");
							map.put("regist_man", noticeVo.getRegist_man());
							cnt = getSqlSession().insert("noticeSmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
				
				List<Map<String, String>> emArr = noticeVo.getEmArr();
				if (emArr == null) {
					System.out.println("전달받은 사원목록이 없습니다.");
				}else {
					try{
						for(Map<String,String> map : noticeVo.getEmArr() ) {
							map.put("cm_innb", noticeVo.getBm_innb());
							map.put("cmmnc_se", "3");
							map.put("em_code", map.get("em_no"));
							map.put("regist_man", noticeVo.getRegist_man());
							cnt = getSqlSession().insert("noticeEmInsert",map);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		return cnt > 0 ? noticeVo.getBm_innb():"";
	}

	@Override
	public int noticeFileSave(String bmInnb, int amNo) throws SQLException {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bm_innb", bmInnb);
		map.put("am_no", amNo+"");		
		return getSqlSession().update("noticeAmNoUpdate", map);
	}

	@Override
	public NoticeVo noticeView(String bmInnb) throws SQLException {
		return (NoticeVo)getSqlSession().selectOne("noticeView",bmInnb);
	}

	@Override
	public List<KeyValueVo> noticeOmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("noticeOmList", bmInnb);
	}

	@Override
	public int noticePopupDelete(HashMap map) throws SQLException {
		return getSqlSession().update("noticePopupDelete",map);
	}
	
	@Override
	public List<KeyValueVo> noticeSmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("noticeSmList", bmInnb);
	}
	
	@Override
	public List<KeyValueVo> noticeEmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("noticeEmList", bmInnb);
	}
	
	@Override
	public List<KeyValueVo> noticeTmList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("noticeTmList", bmInnb);
	}
	
	@Override
	public List<KeyValueVo> fixRoundList(String bmInnb) throws SQLException {
		return getSqlSession().selectList("fixRoundList", bmInnb);
	}
	
	@Override
	public List<OrganizationVo> selectListByCp(String cm_code)throws SQLException {
		return getSqlSession().selectList("organization.selectOrganizationListByCp", cm_code);
	}
	
	@Override
	public List<OrganizationVo> selectAuthDepthList(HashMap hashMap) {
//		System.out.println("auth");
		return getSqlSession().selectList("Notice.selectAuthDepthList", hashMap);
	}
}
