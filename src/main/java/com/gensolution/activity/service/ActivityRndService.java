package com.gensolution.activity.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.gensolution.activity.dao.ActivityRndDAO;
import com.gensolution.activity.vo.ActivityEmVo;
import com.gensolution.activity.vo.ActivityFixingOddVo;
import com.gensolution.activity.vo.ActivityGridMobileVo;
import com.gensolution.activity.vo.ActivityGridVo;
import com.gensolution.activity.vo.ActivityOddMobileVo;
import com.gensolution.activity.vo.ActivityRndMobileAttendVo;
import com.gensolution.activity.vo.ActivityRndOddVo;
import com.gensolution.activity.vo.ActivityRndPlanStrVo;
import com.gensolution.activity.vo.ActivityRndTrtVo;
import com.gensolution.activity.vo.ActivitySaveVo;
import com.gensolution.activity.vo.ActivityTestVo;
import com.gensolution.activity.vo.ActivityTrtMobileVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.vo.NaviVo;

public class ActivityRndService extends SqlSessionDaoSupport implements ActivityRndDAO {
	
	
	@Autowired
	private AttachManager attachManager;
	

	@Override
	public ActivityEmVo rndView(ActivityEmVo activityFixingVo) throws SQLException {
		return (ActivityEmVo)getSqlSession().selectOne("rndView", activityFixingVo);
	}

	@Override
	public NaviVo rndListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("rndListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public List<ActivityEmVo> rndList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("rndList", naviVo);
	}

	@Override
	public String rndSaveEmpAiNo(ActivityEmVo activityFixingVo) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().insert("rndSaveEmpAiNo", activityFixingVo);
		return cnt > 0 ? Integer.toString(cnt) : "";
	}
	

	@Override
	public ActivityGridVo rndBiglist(ActivityGridVo vo) throws SQLException {
		String baseDate =vo.getBase_de(); 
		String sysDate = CommonUtil.getSysDe(); 
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("rndBigColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("rndBiglist", vo );
		vo.setBodyArr(mapBody);
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("rndBigInfo", vo );
		if(agmVo != null){
			vo.setUpdt_de(agmVo.getUpdt_de());
			vo.setPartclr_matter(agmVo.getPartclr_matter());
		}
		return vo;
	}

	@Override
	public List<ActivityRndOddVo> rndOddlist(Map<String,String> map) throws SQLException {
		return getSqlSession().selectList("rndOddlist", map );
	}

	@Override
	public List<ActivityRndTrtVo> rndTrtlist(Map<String, String> map)throws SQLException {
		return getSqlSession().selectList("rndTrtlist", map );
	}
	
	//RND
	@Override
	public List<ActivityRndPlanStrVo> rndSmList(Map<String, String> map) {
		return getSqlSession().selectList("rndSmList", map );
	} 
	
	//현재 진열줄수
	@Override
	public ActivityGridVo rndCurrList(ActivityGridVo vo) {
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("rndCurrColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("rndCurrlist", vo );
		vo.setBodyArr(mapBody);
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("rndCurrInfo", vo );
		if(agmVo != null){
			vo.setUpdt_de(agmVo.getUpdt_de());
			vo.setPartclr_matter(agmVo.getPartclr_matter());
		}
		return vo;
	}
	//Mobilde 현재 진열줄수
	@Override
	public JSONObject rndMobileCurrList(ActivityGridVo vo) throws Exception {
		
//		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
//		String sysDate = CommonUtil.getSysBaseDe(); 
		String baseDate =vo.getBase_de(); 
		String sysDate = CommonUtil.getSysDe(); 
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		List<Map<String, Object>> prdlist = getSqlSession().selectList("rndCurrPrdList", vo );
		List<Map<String, String>> itemlist = getSqlSession().selectList("rndCurrPrdItemList", vo );
		
		
//		String baseDate =vo.getBase_de(); 
//		String sysDate = CommonUtil.getSysDe(); 
		vo.setColumn_code("0000000069");
		vo.setBody_code("0000000068"); 
		System.out.println("Mobile:/rnd/arrlist]"+"조회시간4["+CommonUtil.getCurrentDateTime()+"]");
		if(baseDate.equals(sysDate)){
			int cnt = getSqlSession().selectOne("rndCurrCnt", vo );
			if(cnt == 0){
				prdlist = getSqlSession().selectList("tempRndCurrPrdList", vo );
				itemlist = getSqlSession().selectList("tempRndCurrPrdItemList", vo );
			}
		}
		JSONObject body = new JSONObject();
		String innb = "";
		if(prdlist != null && prdlist.size() > 0 && prdlist.get(0).get("dra_innb") !=null ){
			innb =prdlist.get(0).get("dra_innb")+"";
		}
		body.put("main_innb", innb );
		System.out.println("Mobile:/rnd/arrlist]"+"조회시간5["+CommonUtil.getCurrentDateTime()+"]");
		return CommonUtil.makeMobileRespons(prdlist,itemlist,"curr_list","oi_code","item","drap_oi_code",body);
	}
	
	//Mobile CVS순회 업무일지 by zzz2613
	@Override
	public JSONObject getCvsList(ActivityGridVo vo) throws Exception {
		List<Map<String, Object>> cvsList = getSqlSession().selectList("getCvsList", vo);
		List<Map<String, String>> cvsItemList = getSqlSession().selectList("getCvsItemList", vo);


		String baseDate =vo.getBase_de(); 
		String sysDate = CommonUtil.getSysDe(); 
		vo.setColumn_code("0000000055");
		JSONObject body = new JSONObject();
		
		if(baseDate.equals(sysDate)){
			if(cvsList.size() == 0){
				cvsList = getSqlSession().selectList("tempGetCvsList", vo );
				cvsItemList = getSqlSession().selectList("tempGetCvsItemList", vo );
				body.put("main_innb", "");
				body.put("partclr_matter", "" );
			}
		}
//		String innb = "";
//		if(cvsList != null && cvsList.size() > 0 && cvsList.get(0).get("drc_innb") !=null ){
//			innb =cvsList.get(0).get("drc_innb")+"";
//		}
		
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("rndCvsInfo", vo );
		if(agmVo != null){
//			vo.setUpdt_de(agmVo.getUpdt_de());
//			vo.setPartclr_matter(agmVo.getPartclr_matter());
			body.put("main_innb", agmVo.getMain_innb());
			body.put("partclr_matter", agmVo.getPartclr_matter() );
		}
		
		
		
		return CommonUtil.makeMobileRespons(cvsList,cvsItemList,"cvs_list","drcc_c_code","cvs_item","drcc_c_code",body);
	}
	
	@Override
	public int rndMobileCvsSave(ActivitySaveVo vo) throws SQLException,Exception {
		int cnt = 0;
//		System.out.println("vo 전체 : " + vo.toString());
		List<Map<String, Object>> columnArr = CommonUtil.stringToObjList(vo.getParamArr1());
		// 마스터 저장
		
		
		
		String base_de = vo.getBase_de();
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String drc_innb = vo.getMain_innb();
		String drc_partclr_matter  = vo.getPartclr_matter();
		if(drc_innb == null ||drc_innb.equals("")){
			drc_innb = null;
		}
		
		Map<String, String> saveData = new HashMap<String, String>();
		saveData.put("base_de", base_de);
		saveData.put("em_no", em_no);
		saveData.put("cm_code", cm_code);
		saveData.put("om_code", om_code);
		saveData.put("sm_code", sm_code);
		saveData.put("drc_innb", drc_innb);		
		saveData.put("drc_partclr_matter", drc_partclr_matter);		
		
		cnt = getSqlSession().update("rndMoblieCvsSave", saveData );
		if(cnt > 0){
			for (Map<String, Object> columnMap : columnArr) {
//				System.out.println("POG Prd : " + columnMap);
				
				Map<String, String> prdData = new HashMap<String, String>();
				prdData.put("base_de", base_de);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				String drcc_innb =(String)columnMap.get("drcc_innb");
				if(drcc_innb == null || drcc_innb.equals("")){
					drcc_innb = null;
				}
				prdData.put("drcc_innb", drcc_innb);
				prdData.put("drc_innb", saveData.get("drc_innb"));				
				prdData.put("drcc_c_code", (String)columnMap.get("drcc_c_code"));
				prdData.put("drcc_c_code_nm", (String)columnMap.get("drcc_c_code_nm"));
//				System.out.println("PRD 저장 데이터 : " + prdData);
				cnt = getSqlSession().update("rndMoblieCvsCheckSave", prdData );
				
				
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> itemArr = (List<Map<String, Object>>) columnMap.get("item");
				for (Map<String, Object> itemMap : itemArr) {
//					System.out.println("POG item data : " + itemMap);
					
					Map<String, String> itemData = new HashMap<String, String>();
					itemData.put("base_de", base_de);
					itemData.put("em_no", em_no);
					itemData.put("cm_code", cm_code);
					itemData.put("om_code", om_code);
					itemData.put("sm_code", sm_code);
					
					
					String drcci_innb = (String)itemMap.get("drcci_innb");
					if(drcci_innb == null || drcci_innb.equals("")){
						drcci_innb = null;
					}
					
					itemData.put("drcci_innb", drcci_innb);
					itemData.put("drcc_innb", prdData.get("drcc_innb"));
					itemData.put("drc_innb", saveData.get("drc_innb"));				
					itemData.put("drcci_c_code", (String)itemMap.get("drcci_c_code"));
					itemData.put("drcci_c_code_nm", (String)itemMap.get("drcci_c_code_nm"));
					itemData.put("drcci_c_desc", (String)itemMap.get("drcci_c_desc"));
					
					
					// 현제 진열줄수
					String drcci_state = (String)itemMap.get("drcci_state");
					if(drcci_state == null || drcci_state.equals("")){
						drcci_state = null;
					}
					itemData.put("drcci_state", drcci_state);
					
					
//					System.out.println("ITEM 저장 데이터 : " + itemData);
					cnt = getSqlSession().update("rndMoblieCvsCheckItemSave", itemData );
					
				}
			}
		}
		return cnt;
	}
	
	
	//Mobilde 보조진열현황
	@Override
	public ActivityGridMobileVo rndMobileBiglist(ActivityGridVo vo)throws SQLException,Exception{
		
		String baseDate = vo.getBase_de(); 
		String sysDate = CommonUtil.getSysDe(); 
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		
		
		
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("rndBigColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("rndBiglist", vo );
		vo.setBodyArr(mapBody);
		
		
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("rndBigInfo", vo );
		if(agmVo != null){
			agmVo.setColumnArr(mapColumn);
			agmVo.setBodyArr(mapBody);
		}else if(baseDate.equals(sysDate)){
			System.out.println("보조진열 데이터 없음 !!!! TEMP 불러오기");
			agmVo = new ActivityGridMobileVo();
			vo.setColumn_code("0000000070");
			vo.setColumn_key("drbp");
			vo.setColumn_table("tb_diary_rnd_big");
			vo.setBody_code("0000000071");
			List<Map<String, String>> tempColumn =  getSqlSession().selectList("tempColunmlist", vo );
			vo.setColumnArr(tempColumn);
			List<Map<String, String>> tempBody = getSqlSession().selectList("tempBodylist", vo );
			agmVo.setColumnArr(tempColumn);
			agmVo.setBodyArr(tempBody);
		}
		return agmVo;
	}
	//Mobilde 취급품목현황
	@Override 
	public ActivityTrtMobileVo rndMobileTrtlist(Map<String, String> map)throws SQLException ,Exception{
		
		
		String baseDate = map.get("base_de"); 
		String sysDate = CommonUtil.getSysDe(); 
		if(baseDate.equals(sysDate) ){
			map.put("updateFlag", "true");
		}else{
			map.put("updateFlag", "false");
		}
		
		
		List<Map<String, String>> trt_list = getSqlSession().selectList("rndMobileTrtlist", map );
		ActivityRndTrtVo infoVo = getSqlSession().selectOne("rndMobileTrtInfo", map );
		ActivityTrtMobileVo vo =  new ActivityTrtMobileVo();
		int cnt = getSqlSession().selectOne("rndTrtlistCnt", map );
		
		if(trt_list != null && cnt > 0 ){
			vo.setTrt_list(trt_list);
			String innb = "";
			if(infoVo !=null ){
				vo.setUpdt_de(infoVo.getUpdt_de());
				innb = infoVo.getDrt_innb();
			}
			vo.setMain_innb(innb);
		}else if(baseDate.equals(sysDate)){
			trt_list = getSqlSession().selectList("m_tempRndTrtlist", map );
			vo.setTrt_list(trt_list);
		}else{
			vo = null;
		}
		return vo;
	}
	//Mobile 시황및 특이사항
	@Override
	public ActivityOddMobileVo rndMobileOddlist(Map<String, String> map)throws SQLException ,Exception{
		List<Map<String, String>> odd_list = getSqlSession().selectList("rndMobileOddList", map );
//		String updt_de = getSqlSession().selectOne("rndMobileOddInfo", map );
		ActivityRndOddVo infoVo = getSqlSession().selectOne("rndMobileOddInfo", map );
		ActivityOddMobileVo vo =  new ActivityOddMobileVo();
		String baseDate = map.get("base_de"); 
		String sysDate = CommonUtil.getSysDe(); 
		if(odd_list != null && odd_list.size() > 0 ){
			vo.setOdd_list(odd_list);
			String innb = "";
			if(infoVo !=null ){
				innb = infoVo.getDro_innb();
			}
			vo.setMain_innb(innb);
		}else if(baseDate.equals(sysDate)){
			vo.setOdd_list(null);
		}else{
			vo = null;
		}
		return vo;
	}

	@Override
	public int rndSaveTest(ActivityTestVo vo) throws SQLException,Exception {
		int cnt = 0;
		List<Map<String, String>> mapList = CommonUtil.stringToList(vo.getParams());
		// 마스터 저장
		
		for (Map<String, String> map : mapList) {
			map.put("cm_code", vo.getSm_code()); // 세션 정보들
			map.put("em_no", vo.getEm_no());	 // 세션 정보들 등등
			
			// 차일드 저장
		}
		
		return cnt;
	}

	@Override
	public int rndMobileTrtsave(ActivitySaveVo vo)throws SQLException, Exception {
		int cnt = 0;
		List<Map<String, String>> listMap = CommonUtil.stringToList(vo.getParamArr1());
		
		
		String base_de = vo.getBase_de();
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String drt_innb = vo.getMain_innb();
		if(drt_innb == null || drt_innb.equals("")){
			drt_innb = null;
		}
		
		Map<String, String> saveData = new HashMap<String, String>();
		saveData.put("base_de", base_de);
		saveData.put("em_no", em_no);
		saveData.put("cm_code", cm_code);
		saveData.put("om_code", om_code);
		saveData.put("sm_code", sm_code);
		saveData.put("drt_innb", drt_innb);		
		
//		System.out.println("MAIN 저장 데이터 : " + saveData);
		
		cnt = getSqlSession().insert("m_rndTrtsave",saveData);
		if(cnt > 0 ){
			for(Map<String,String> prdData : listMap){
				prdData.put("base_de", base_de);
				String drtp_innb = prdData.get("drtp_innb");
				if(drtp_innb == null || drtp_innb.equals("")){
					drtp_innb = null;
				}
				prdData.put("drtp_innb", drtp_innb);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				String new_innb = saveData.get("drt_innb");
				if(new_innb.equals("0") && !drt_innb.equals("")){
					prdData.put("drt_innb", drt_innb);
				}else{
					prdData.put("drt_innb", new_innb);		
				}
				
//				prdData.put("drt_innb", saveData.get("drt_innb"));		
				cnt = getSqlSession().insert("m_rndTrtPrdlstsave", prdData);
			}
		}
		return cnt;
	}

	@Override
	public int rndMobileCurrSave(ActivitySaveVo vo) throws SQLException,Exception {
		int cnt =1;
		List<Map<String, Object>> columnArr = CommonUtil.stringToObjList(vo.getParamArr1());
		// 마스터 저장
		
		
		
		String base_de = vo.getBase_de();
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String dra_innb = vo.getMain_innb();
		
		if(dra_innb == null ||dra_innb.equals("")){
			dra_innb = null;
		}
		
		Map<String, String> saveData = new HashMap<String, String>();
		saveData.put("base_de", base_de);
		saveData.put("em_no", em_no);
		saveData.put("cm_code", cm_code);
		saveData.put("om_code", om_code);
		saveData.put("sm_code", sm_code);
		saveData.put("dra_innb", dra_innb);		
		
		cnt = getSqlSession().update("rndMoblieCurrSave", saveData );
		if(cnt > 0 ){
			for (Map<String, Object> columnMap : columnArr) {
//				System.out.println("POG Prd : " + columnMap);
				
				Map<String, String> prdData = new HashMap<String, String>();
				prdData.put("base_de", base_de);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				String drap_innb =(String)columnMap.get("drap_innb");
				if(drap_innb == null || drap_innb.equals("")){
					drap_innb = null;
				}
				prdData.put("drap_innb", drap_innb);
				prdData.put("dra_innb", saveData.get("dra_innb"));				
				prdData.put("drap_oi_code", (String)columnMap.get("oi_code"));
				prdData.put("drap_oi_code_nm", (String)columnMap.get("oi_code_nm"));
				prdData.put("drap_cur_rate", (String)columnMap.get("drap_cur_rate"));
				prdData.put("drap_partclr_matter",(String)columnMap.get("drap_partclr_matter") );
				
//				System.out.println("PRD 저장 데이터 : " + prdData);
				cnt = getSqlSession().update("rndMoblieCurrPrdSave", prdData );
				
				
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> itemArr = (List<Map<String, Object>>) columnMap.get("item");
				for (Map<String, Object> itemMap : itemArr) {
//					System.out.println("POG item data : " + itemMap);
					
					Map<String, String> itemData = new HashMap<String, String>();
					itemData.put("base_de", base_de);
					itemData.put("em_no", em_no);
					itemData.put("cm_code", cm_code);
					itemData.put("om_code", om_code);
					itemData.put("sm_code", sm_code);
					
					
					String drapi_innb = (String)itemMap.get("drapi_innb");
					if(drapi_innb == null || drapi_innb.equals("")){
						drapi_innb = null;
					}
					
					itemData.put("drapi_innb", drapi_innb);
					itemData.put("drap_innb", prdData.get("drap_innb"));
					itemData.put("dra_innb", saveData.get("dra_innb"));				
					itemData.put("drap_oi_code", (String)columnMap.get("oi_code"));			// 부모OI 코드
					itemData.put("drap_oi_code_nm", (String)columnMap.get("oi_code_nm"));	// 부모OI 코드명
					itemData.put("oi_code", (String)itemMap.get("oi_code"));
					itemData.put("oi_code_nm", (String)itemMap.get("oi_code_nm"));
					
					
					// 현제 진열줄수
					String drapi_cur_value = (String)itemMap.get("drapi_cur_value");
					if(drapi_cur_value == null || drapi_cur_value.equals("")){
						drapi_cur_value = null;
					}
					itemData.put("drapi_cur_value", drapi_cur_value);
					
					
//					System.out.println("ITEM 저장 데이터 : " + itemData);
					cnt = getSqlSession().update("rndMoblieCurrPrdItemSave", itemData );
					
				}
			}
		}
		return cnt;
	}

	@Override
	public int rndMobileBigSave(ActivitySaveVo vo) throws SQLException,Exception {
		int cnt = 0;
		List<Map<String, String>> columnArr = CommonUtil.stringToList(vo.getParamArr1());
		List<Map<String, String>> bodyArr = CommonUtil.stringToList(vo.getParamArr2());
		
		String base_de = vo.getBase_de();
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String partclr_matter =  vo.getPartclr_matter();
		String main_innb = vo.getMain_innb();
		if(main_innb == null || main_innb.equals("")){
			main_innb = null;
		}
		
		Map<String, String> saveData = new HashMap<String, String>();
		saveData.put("base_de", base_de);
		saveData.put("em_no", em_no);
		saveData.put("cm_code", cm_code);
		saveData.put("om_code", om_code);
		saveData.put("sm_code", sm_code);
		saveData.put("drb_innb", main_innb);		
		saveData.put("partclr_matter", partclr_matter);
		
//		System.out.println("MAIN 저장 데이터 : " + saveData);
		
		cnt = getSqlSession().update("rndMoblieBigSave", saveData );
		
		String new_innb = saveData.get("drb_innb");
		String drb_innb = "";//saveData.get("dfb_innb");
		if(new_innb.equals("0") && !main_innb.equals("")){
			drb_innb = main_innb;
		}else{
			drb_innb = new_innb;
		}
		
		if(cnt > 0 ){
			if(saveData.get("drb_innb") != null && !saveData.get("drb_innb").equals("") ){
				for (Map<String, String> bodyMap : bodyArr) {
					
					Map<String, String> prdData = new HashMap<String, String>();
					prdData.put("base_de", base_de);
					prdData.put("em_no", em_no);
					prdData.put("cm_code", cm_code);
					prdData.put("om_code", om_code);
					prdData.put("sm_code", sm_code);
					prdData.put("drb_innb", drb_innb);				
					prdData.put("partclr_matter", partclr_matter);
					String drbp_innb = bodyMap.get("drbp_innb");
					if(drbp_innb == null || drbp_innb.equals("")){
						drbp_innb = null;
					}
					prdData.put("drbp_innb", drbp_innb);
					prdData.put("drbp_oi_code", bodyMap.get("oi_code"));
					prdData.put("drbp_oi_code_nm", bodyMap.get("oi_code_nm"));
					
					
					
					
//					System.out.println("PRD 저장 데이터 : " + prdData);
					cnt = getSqlSession().update("rndMoblieBigPrdSave", prdData );
					
					
					String new_drbp_innb = prdData.get("drbp_innb");
					String last_drbp_innb = "";
					if(drbp_innb != null && !drbp_innb.equals("")){
						last_drbp_innb = drbp_innb;
					}else{
						last_drbp_innb = new_drbp_innb;
					}
					
					if(prdData.get("drbp_innb") != null && !prdData.get("drbp_innb").equals("") ){
						for (Map<String, String> columnMap : columnArr) {
							Map<String, String> itemData = new HashMap<String, String>();
							itemData.put("base_de", base_de);
							itemData.put("em_no", em_no);
							itemData.put("cm_code", cm_code);
							itemData.put("om_code", om_code);
							itemData.put("sm_code", sm_code);
							itemData.put("drb_innb", drb_innb);				
							itemData.put("partclr_matter", partclr_matter);
							
							String drbpi_cur_value = bodyMap.get(columnMap.get("oi_code"));
							if(drbpi_cur_value == null || drbpi_cur_value.equals("")){
								drbpi_cur_value = null;
							}
							itemData.put("drbpi_cur_value", drbpi_cur_value);
							String drbpi_innb = bodyMap.get(columnMap.get("oi_code")+"_innb");
							if(drbpi_innb == null ||drbpi_innb.equals("")){
								drbpi_innb = null;
							}
							itemData.put("drbpi_innb", drbpi_innb);
							itemData.put("drbp_innb", last_drbp_innb);
							itemData.put("drbp_oi_code", bodyMap.get("oi_code"));
							itemData.put("drbp_oi_code_nm", bodyMap.get("oi_code_nm"));
							itemData.put("oi_code", columnMap.get("oi_code"));
							itemData.put("oi_code_nm", columnMap.get("oi_code_nm"));
//							System.out.println("ITEM 저장 데이터 : " + itemData);
							cnt = getSqlSession().update("rndMoblieBigPrdItemSave", itemData );
						}
					}
				}
			}
		}
		
		return cnt;
	}

	@Override
	public int rndMobileOddsave(ActivityRndOddVo vo) throws SQLException,Exception {
		vo.setDro_innb(vo.getMain_innb());
		Map<String,String> mainMap =  new HashMap<String, String>();
		mainMap.put("dro_innb", vo.getMain_innb());
		mainMap.put("cm_code", vo.getCm_code());
		mainMap.put("om_code", vo.getOm_code());
		mainMap.put("sm_code", vo.getSm_code());
		mainMap.put("base_de", vo.getBase_de());
		mainMap.put("em_no", vo.getEm_no());	
		
		int cnt= getSqlSession().insert("rndMobileOddsave",mainMap);
		List<Map<String, String>> listMap = CommonUtil.stringToList(vo.getParamArr1());
		MultipartFile[] files = vo.getFiles();
		if(cnt > 0){
			for(int i=0; i<listMap.size();i++){
				Map<String,String> map = listMap.get(i);
				if(cnt > 0){
					String memo = map.get("drop_partclr_matter");
					map.put("drop_partclr_matter",  memo );
					if(mainMap.get("dro_innb").equals("0")){
						map.put("dro_innb", vo.getMain_innb());
					}else{
						map.put("dro_innb", mainMap.get("dro_innb"));
					}
					map.put("cm_code", vo.getCm_code());
					map.put("em_no", vo.getEm_no());	
					map.put("sm_code", vo.getSm_code());
					map.put("om_code", vo.getOm_code());
					map.put("base_de", vo.getBase_de());
					try {
						if(files[i].getSize() > 0 ){
							String imgUrl = attachManager.uploadImageFile(files[i]);
//							System.out.println(imgUrl);
							map.put("drop_img_url", imgUrl);
						}
					} catch (Exception e) {
						System.out.println("파일저장실패");
					}
					cnt = getSqlSession().insert("rndMobileOddPrdsave", map);
				}
			}
		}
		return cnt;
	}
	// 순회 출근
	@Override
	public int rndMobileAttending(ActivityRndMobileAttendVo vo)throws SQLException ,Exception {
		vo.setBase_de(CommonUtil.getBaseDe(vo.getPlan_de())); 
		int cnt = 0;
		int atenOkCnt = getSqlSession().selectOne("rndMobileAttendingOkCnt",vo); // 출근 매장 수
		if(atenOkCnt == 0){
			cnt = getSqlSession().insert("rndMobileAttending",vo);
			cnt = getSqlSession().insert("rndMobileAttendingManager",vo);
		}else{
			cnt = getSqlSession().insert("rndMobileAttending",vo);
			int atenNotCnt = getSqlSession().selectOne("rndMobileAttendingNotCnt",vo); //미출근 매장수
			if(atenNotCnt == 0){
				cnt = getSqlSession().insert("rndMobileLeavingManager",vo);
			}
		}
		
		return cnt;
	}

	@Override
	public int rndMobileOddDel(Map<String,String> map ) throws SQLException {
		return getSqlSession().update("rndMobileOddDel",map);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ActivityGridVo rndPdlist(ActivityGridVo vo) throws SQLException, Exception {
		
		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("rndPdColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("rndPdlist", vo );
		vo.setBodyArr(mapBody);
		Map<String,String> map = new HashMap<String, String>();
		map.put("base_de", vo.getBase_de() );
		map.put("em_no", vo.getEm_no() );
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("rndPdInfo", vo );
		if(agmVo != null){
			vo.setUpdt_de(agmVo.getUpdt_de());
			vo.setPartclr_matter(agmVo.getPartclr_matter());
		}
		return vo;
	}

	@Override
	public ActivityGridMobileVo rndMobilePdlist(ActivityGridVo vo) throws SQLException, Exception {
		
		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		List<Map<String, String>> mapColumn = new ArrayList<Map<String,String>>();
		mapColumn =  getSqlSession().selectList("rndPdColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = new ArrayList<Map<String,String>>();
		mapBody = getSqlSession().selectList("rndPdlist", vo );
		vo.setBodyArr(mapBody);
//		
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("rndPdInfo", vo );
		
		
		if(agmVo != null){
			agmVo.setColumnArr(mapColumn);
			agmVo.setBodyArr(mapBody);
		}else if(baseDate.equals(sysDate)){
			agmVo = new ActivityGridMobileVo();
			vo.setColumn_code("0000000375");
			vo.setColumn_key("drpp");
			vo.setColumn_table("tb_diary_rnd_pd");
			vo.setBody_code("0000000376");
			List<Map<String, String>> tempColumn =  getSqlSession().selectList("tempColunmlist", vo );
			vo.setColumnArr(tempColumn);
			List<Map<String, String>> tempBody = getSqlSession().selectList("tempBodylist", vo );
			agmVo.setColumnArr(tempColumn);
			agmVo.setBodyArr(tempBody);
		}
		return agmVo;
	}

	@Override
	public int rndMobilePdSave(ActivitySaveVo vo) throws SQLException,Exception {
		int cnt = 0;
		List<Map<String, String>> columnArr = CommonUtil.stringToList(vo.getParamArr1());
		List<Map<String, String>> bodyArr = CommonUtil.stringToList(vo.getParamArr2());
		
		String base_de = vo.getBase_de();
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String partclr_matter =  vo.getPartclr_matter();
		String main_innb = vo.getMain_innb();
		
		if(main_innb == null ||main_innb.equals("")){
			main_innb = null;
		}
		
		
		Map<String, String> saveData = new HashMap<String, String>();
		saveData.put("base_de", base_de);
		saveData.put("em_no", em_no);
		saveData.put("cm_code", cm_code);
		saveData.put("om_code", om_code);
		saveData.put("sm_code", sm_code);
		saveData.put("drp_innb", main_innb);		
		saveData.put("partclr_matter", partclr_matter);
		
//		System.out.println("PD MAIN 저장 데이터 : " + saveData);
		
		cnt = getSqlSession().update("rndMobliePdSave", saveData );
		
		String new_innb = saveData.get("drp_innb");
		String drp_innb = "";//saveData.get("drb_innb");
		if(new_innb.equals("0") && !main_innb.equals("")){
			drp_innb = main_innb;
		}else{
			drp_innb = new_innb;
		}
		
		if(cnt > 0 && saveData.get("drp_innb") != null && !saveData.get("drp_innb").equals("") ){
			for (Map<String, String> bodyMap : bodyArr) {
				
				Map<String, String> prdData = new HashMap<String, String>();
				prdData.put("base_de", base_de);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				prdData.put("drp_innb", drp_innb);				
				prdData.put("partclr_matter", partclr_matter);
				
				String drpp_innb = bodyMap.get("drpp_innb");
				if(drpp_innb == null || drpp_innb.equals("")){
					drpp_innb = null;
				}
				prdData.put("drpp_innb", drpp_innb);
				prdData.put("drpp_oi_code", bodyMap.get("oi_code"));
				prdData.put("drpp_oi_code_nm", bodyMap.get("oi_code_nm"));
				
				
				
				
//				System.out.println("PRD 저장 데이터 : " + prdData);
				cnt = getSqlSession().update("rndMobliePdPrdSave", prdData );
				
				String new_drpp_innb = prdData.get("drpp_innb");
				String last_drpp_innb = "";
				if(drpp_innb != null && !drpp_innb.equals("")){
					last_drpp_innb = drpp_innb;
				}else{
					last_drpp_innb = new_drpp_innb;
				}
				
				if(prdData.get("drpp_innb") != null && !prdData.get("drpp_innb").equals("") ){
					for (Map<String, String> columnMap : columnArr) {
						Map<String, String> itemData = new HashMap<String, String>();
						itemData.put("base_de", base_de);
						itemData.put("em_no", em_no);
						itemData.put("cm_code", cm_code);
						itemData.put("om_code", om_code);
						itemData.put("sm_code", sm_code);
						itemData.put("drp_innb", drp_innb);				
						//				itemData.put("drp_innb", drp_innb);				
						itemData.put("partclr_matter", partclr_matter);
						
						
						String drppi_cur_value = bodyMap.get(columnMap.get("oi_code"));
						if(drppi_cur_value.equals("")){
							drppi_cur_value = null;
						}
						itemData.put("drppi_cur_value", drppi_cur_value);
						
						String drppi_innb = bodyMap.get(columnMap.get("oi_code")+"_innb");
						if(drppi_innb == null || drppi_innb.equals("")){
							drppi_innb = null;
						}
						itemData.put("drppi_innb", drppi_innb);
						itemData.put("drpp_innb", last_drpp_innb);
						itemData.put("drpp_oi_code", bodyMap.get("oi_code"));
						itemData.put("drpp_oi_code_nm", bodyMap.get("oi_code_nm"));
						itemData.put("oi_code", columnMap.get("oi_code"));
						itemData.put("oi_code_nm", columnMap.get("oi_code_nm"));
//						System.out.println("PRD ITEM 저장 데이터 : " + itemData);
						cnt = getSqlSession().update("rndMobliePdPrdItemSave", itemData );
					}
				}
			}
		}
		
		return cnt;
	}


}
