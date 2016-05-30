package com.gensolution.activity.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.gensolution.activity.dao.ActivityOptionDAO;
import com.gensolution.activity.vo.ActivityOptionTreeVo;
import com.gensolution.activity.vo.ActivityOptionVo;

public class ActivityOptionService extends SqlSessionDaoSupport implements ActivityOptionDAO {

	@Override
	public List<ActivityOptionVo> entOptionList(String optionCode)throws SQLException {
		return getSqlSession().selectList("entOptionList", optionCode);
	}

	@Override
	public List<ActivityOptionVo> prdOptionList(String optionCode)throws SQLException {
		return getSqlSession().selectList("prdOptionList" ,optionCode);
	}

	@Override
	public int entOptionSave(ActivityOptionVo vo) throws SQLException {
		int cnt = 0;
		for (Map<String,String> map : vo.getEntArr()) {
			map.put("regist_man", vo.getRegist_man());
			String imeCode = map.get("ime_code");
			if(imeCode == null || imeCode.equals("")){
				cnt =getSqlSession().insert("entOptionSave",map);
			}else{
				cnt =getSqlSession().insert("entOptionUpdate",map);
			}
		}
		return cnt;
	}

	@Override
	public int prdOptionSave(ActivityOptionVo vo) throws SQLException {
		int cnt = 0;
		for (Map<String,String> map : vo.getPrdArr()) {
			map.put("regist_man", vo.getRegist_man());
			String imeCode = map.get("imp_code");
			if(imeCode == null || imeCode.equals("")){
				cnt =getSqlSession().insert("prdOptionSave",map);
			}else{
				cnt =getSqlSession().insert("prdOptionUpdate",map);
			}
		}
		return cnt;
	}

	@Override
	public List<ActivityOptionVo> optionList(String optionCode)throws SQLException {
		return getSqlSession().selectList("optionList", optionCode);
	}

	@Override
	public List<ActivityOptionTreeVo> optionTree(Map<String, String> map) throws SQLException {
		return getSqlSession().selectList("optionTree",map);
	}

	@Override
	public ActivityOptionVo optionView(String optionCode) throws SQLException {
		return  getSqlSession().selectOne("optionView",optionCode);
	}

	@Override
	public ActivityOptionVo optionSave(ActivityOptionVo vo) throws SQLException {
		int cnt = 0;
		String oiCode = vo.getOi_code();
		String oiType = vo.getOi_type();
		Map<String,String> map = new HashMap<String, String>();
		map.put("oi_nick_nm" , vo.getOi_nick_nm());
		map.put("oi_type" , oiType);
		map.put("oi_code" , oiCode);
		
		// 등록된 닉네임 확인
		int checkCnt =getSqlSession().selectOne("optionUsingCheck",map);
		
		// 등록된 품목 확인
		int checkTrtCnt = 0;
		if(oiType.equals("0000000072") || oiType.equals("0000000067") ){
			map.put("oi_parn_code" , vo.getOi_parn_code());
			checkTrtCnt = getSqlSession().selectOne("optionTrtUsingCheck",map); 
		}
	
		if(checkCnt > 0){
			cnt = 0;
		}else if(checkTrtCnt > 0){
			cnt = -1;
		}else{
			if(oiCode == null || oiCode.equals("")){
				cnt =getSqlSession().insert("optionSave",vo);
			}else{
				cnt =getSqlSession().insert("optionUpdate",vo);
			}
		}
		vo.setCnt(cnt);
		return vo;
	}

	@Override
	public ActivityOptionVo optionCustomSave(ActivityOptionVo vo) throws SQLException {
		
		int cnt = 0;
		List<Map<String,String>>  usingArr =  new ArrayList<Map<String,String>>();
		for (Map<String,String> map : vo.getOptionArr()) {
			
			String oiCode = map.get("oi_code");
			String oiType = map.get("oi_type");
			
			int checkCnt =getSqlSession().selectOne("optionUsingCheck",map);
			map.put("regist_man", vo.getRegist_man());
			map.put("default_at", "N");
			
			// 등록된 품목 확인
			int checkTrtCnt = 0;
			if(oiType.equals("0000000072") || oiType.equals("0000000067") ){
				map.put("oi_parn_code" , map.get("oi_parn_code"));
				checkTrtCnt = getSqlSession().selectOne("optionTrtUsingCheck",map); 
			}
			
//			String oiCode = map.get("oi_code");
				if(checkCnt > 0){
					map.put("err_type", "0");
					usingArr.add(map);
				}else if(checkTrtCnt > 0){
					map.put("err_type", "1");
					usingArr.add(map);
				}else{
					if(oiCode == null || oiCode.equals("")){
						cnt +=getSqlSession().insert("optionCustomInsert",map);
					}else{
						cnt += getSqlSession().insert("optionCustomUpdate",map);
					}
				}
		}
		vo.setCnt(cnt);
		vo.setOptionArr(usingArr);
		return vo;
	}


}
