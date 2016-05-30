package com.gensolution.activity.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gensolution.activity.dao.ActivityFixingDAO;
import com.gensolution.activity.vo.ActivityEmVo;
import com.gensolution.activity.vo.ActivityEvnMobileSaveVo;
import com.gensolution.activity.vo.ActivityEvnMobileVo;
import com.gensolution.activity.vo.ActivityFixingEvnVo;
import com.gensolution.activity.vo.ActivityFixingOddVo;
import com.gensolution.activity.vo.ActivityFixingPogOptionVo;
import com.gensolution.activity.vo.ActivityFixingTrtVo;
import com.gensolution.activity.vo.ActivityFixingWorkVo;
import com.gensolution.activity.vo.ActivityGridMobileVo;
import com.gensolution.activity.vo.ActivityGridVo;
import com.gensolution.activity.vo.ActivityOddMobileVo;
import com.gensolution.activity.vo.ActivitySaveVo;
import com.gensolution.activity.vo.ActivityTrtMobileVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Encoder;
import com.vertexid.vo.NaviVo;

public class ActivityFixingService extends SqlSessionDaoSupport implements ActivityFixingDAO {
	
	@Autowired
	private AttachManager attachManager;

	@Override
	public List<ActivityFixingPogOptionVo> entOptionList(String optionCode)throws SQLException {
		return getSqlSession().selectList("entOptionList", optionCode);
	}

	@Override
	public List<ActivityFixingPogOptionVo> prdOptionList(String optionCode)throws SQLException {
		return getSqlSession().selectList("prdOptionList" ,optionCode);
	}

	@Override
	public int entOptionSave(ActivityFixingPogOptionVo vo) throws SQLException {
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
	public int prdOptionSave(ActivityFixingPogOptionVo vo) throws SQLException {
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
	public List<ActivityFixingPogOptionVo> fullOptionList(String optionCode)throws SQLException {
		return getSqlSession().selectList("fullOptionList", optionCode);
	}

	@Override
	public ActivityEmVo getActivityFixingView(ActivityEmVo activityFixingVo) throws SQLException {
		return (ActivityEmVo)getSqlSession().selectOne("getActivityFixingView", activityFixingVo);
	}

	@Override
	public NaviVo getActivityFixingListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("getActivityFixingListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public List<ActivityEmVo> getActivityFixingList(NaviVo naviVo) throws SQLException {
		return getSqlSession().selectList("getActivityFixingList", naviVo);
	}

	@Override
	public String saveActivityFixingEmpAmNo(ActivityEmVo activityFixingVo) throws SQLException {
		int cnt = 0;
		cnt = getSqlSession().insert("saveActivityFixingEmpAiNo", activityFixingVo);
		return cnt > 0 ? Integer.toString(cnt) : "";
	}
	
	@Override
	public ActivityGridVo optionPoglist(ActivityGridVo vo) throws SQLException {
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("fixingPogColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("fixingPoglist", vo );
		vo.setBodyArr(mapBody);
		return vo;
	}

	@Override
	public ActivityGridVo optionBiglist(ActivityGridVo vo) throws SQLException ,Exception {
		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		
		
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("fixingBigColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("fixingBiglist", vo );
		vo.setBodyArr(mapBody);
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("fixingBigInfo", vo );
		if(agmVo != null){
			vo.setUpdt_de(agmVo.getUpdt_de());
			vo.setPartclr_matter(agmVo.getPartclr_matter());
		}
		return vo;
	}
	@Override
	public ActivityGridVo optionBigInfo(ActivityGridVo vo) throws SQLException {
		return getSqlSession().selectOne("fixingBigInfo", vo );
	}
	@Override
	public ActivityGridVo optionPdlist(ActivityGridVo vo) throws SQLException ,Exception{
		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("fixingPdColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("fixingPdlist", vo );
		vo.setBodyArr(mapBody);
		Map<String,String> map = new HashMap<String, String>();
		map.put("base_de", vo.getBase_de() );
		map.put("em_no", vo.getEm_no() );
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("fixingPdInfo", vo );
		if(agmVo != null){
			vo.setUpdt_de(agmVo.getUpdt_de());
			vo.setPartclr_matter(agmVo.getPartclr_matter());
		}
		return vo;
	}

	@Override
	public List<ActivityFixingOddVo> optionOddlist(Map<String,String> map) throws SQLException {
		return getSqlSession().selectList("fixingOddlist", map );
	}
	
	@Override
	public ActivityOddMobileVo m_optionOddlist(Map<String, String> map) throws SQLException ,Exception {
		List<Map<String, String>> odd_list = getSqlSession().selectList("m_fixingOddlist", map );
		ActivityFixingOddVo infoVo = getSqlSession().selectOne("m_fixingOddlistInfo", map );
		ActivityOddMobileVo vo =  new ActivityOddMobileVo();
		String baseDate = CommonUtil.getBaseDe(map.get("base_de")); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		
		if(odd_list != null && odd_list.size() > 0 ){
			vo.setUpdt_de(infoVo.getUpdt_de());
			if(odd_list.size() == 1 && odd_list.get(0) == null){
				vo.setOdd_list(null);
			}else{
				vo.setOdd_list(odd_list);
			}
			String innb = "";
			if(infoVo !=null ){
				innb = infoVo.getDfo_innb();
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
	public int m_optionOddsave(ActivityFixingOddVo vo) throws SQLException,Exception {

		vo.setDfo_innb(vo.getMain_innb());
		Map<String,String> mainMap =  new HashMap<String, String>();
		mainMap.put("dfo_innb", vo.getMain_innb());
		mainMap.put("cm_code", vo.getCm_code());
		mainMap.put("om_code", vo.getOm_code());
		mainMap.put("sm_code", vo.getSm_code());
		mainMap.put("base_de", vo.getBase_de());
		mainMap.put("em_no", vo.getEm_no());	
		
		int cnt= getSqlSession().insert("m_optionOddsave",mainMap);
		if(cnt > 0){
			List<Map<String, String>> listMap = CommonUtil.stringToList(vo.getParamArr1());
			MultipartFile[] files = vo.getFiles();
			for(int i=0; i<listMap.size();i++){
				Map<String,String> map = listMap.get(i);
				if(cnt > 0){
					String memo = map.get("dfop_partclr_matter");
					map.put("dfop_partclr_matter",  memo );
					if(mainMap.get("dfo_innb").equals("0")){
						map.put("dfo_innb", vo.getMain_innb());
					}else{
						map.put("dfo_innb", mainMap.get("dfo_innb"));
					}
					map.put("cm_code", vo.getCm_code());
					map.put("em_no", vo.getEm_no());	
					map.put("sm_code", vo.getSm_code());
					map.put("om_code", vo.getOm_code());
					map.put("base_de", vo.getBase_de());
					try {
						if(files != null && files[i].getSize() > 0 ){
							String imgUrl = attachManager.uploadImageFile(files[i]);
							map.put("dfop_img_url", imgUrl);
						}
					} catch (Exception e) {
						System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:m_optionOddsave()]  : 시황및 파일저장실패 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
					}
					cnt = getSqlSession().insert("m_optionOddPrdlistsave", map);
				}
			}
			
		}
		return cnt;
	}

	@Override
	public List<ActivityFixingEvnVo> optionEvnColumnlist(Map<String, String> map)throws SQLException {
		return getSqlSession().selectList("fixingEvnColunmlist", map );
	}
	@Override
	public List<ActivityFixingEvnVo> optionEvnlist(Map<String, String> map)throws SQLException {
		return getSqlSession().selectList("fixingEvnlist", map );
	}

	@Override
	public ActivityFixingWorkVo fixingWorklist(Map<String, String> map)throws SQLException {
		return getSqlSession().selectOne("fixingWorklist", map );
	}
	
	@Override
	public ActivityFixingWorkVo m_fixingWorklist(Map<String, String> map)throws SQLException {
		return getSqlSession().selectOne("m_fixingWorklist", map );
	}

	@Override
	public int fixingWorkSave(ActivityFixingWorkVo vo) {
		int cnt = 0;
		if(vo.getSaveType().equals("UPDATE")){
			cnt =getSqlSession().insert("fixingWorkUpdate", vo );
		}else if(vo.getSaveType().equals("INSERT")){
			cnt =getSqlSession().insert("fixingWorkInsert", vo );
		}
		return cnt ;
	};

	@Override
	public int m_fixingWorkSave(ActivityFixingWorkVo vo) throws SQLException {
		return getSqlSession().insert("m_fixingWorkSave", vo );
	}
	
	@Override
	public List<ActivityFixingTrtVo> fixingTrtlist(Map<String, String> map)throws SQLException {
		return getSqlSession().selectList("fixingTrtlist", map );
	}

	@Override
	public ActivityTrtMobileVo m_fixingTrtlist(Map<String, String> map)throws SQLException , Exception {
		
		String baseDate = CommonUtil.getBaseDe(map.get("base_de")); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		if(baseDate.equals(sysDate) ){
			map.put("updateFlag", "true");
		}else{
			map.put("updateFlag", "false");
		}
		
		List<Map<String, String>> trt_list = getSqlSession().selectList("m_fixingTrtlist", map );
		ActivityFixingTrtVo infoVo = getSqlSession().selectOne("m_fixingTrtlistInfo", map );
		ActivityTrtMobileVo vo =  new ActivityTrtMobileVo();
		
		if(trt_list != null && trt_list.size() > 0 ){
			vo.setTrt_list(trt_list);
			String innb = "";
			if(infoVo !=null ){
				vo.setUpdt_de(infoVo.getUpdt_de());
				innb = infoVo.getDft_innb();
			}
			vo.setMain_innb(innb);
		}else if(baseDate.equals(sysDate)){
			trt_list = getSqlSession().selectList("m_tempFixingTrtlist", map );
			vo.setTrt_list(trt_list);
		}else{
			vo = null;
		}
		return vo;
	}

	@Override
	public int m_fixingTrtsave(ActivitySaveVo vo)throws SQLException, Exception {
		int cnt = 0;
		List<Map<String, String>> listMap = CommonUtil.stringToList(vo.getParamArr1());
		
		String base_de = CommonUtil.getBaseDe(vo.getBase_de());
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String dft_innb = vo.getMain_innb();
		if(dft_innb == null || dft_innb.equals("")){
			dft_innb = null;
		}
		
		Map<String, String> saveData = new HashMap<String, String>();
		saveData.put("base_de", base_de);
		saveData.put("em_no", em_no);
		saveData.put("cm_code", cm_code);
		saveData.put("om_code", om_code);
		saveData.put("sm_code", sm_code);
		saveData.put("dft_innb", dft_innb);		
		
		
		cnt = getSqlSession().insert("m_fixingTrtsave",saveData);
		if(cnt > 0 ){
			
			for(Map<String,String> prdData : listMap){
				String dftp_innb = prdData.get("dftp_innb");
				if(dftp_innb == null || dftp_innb.equals("")){
					dftp_innb = null;
				}
				prdData.put("dftp_innb", dftp_innb);
				prdData.put("base_de", base_de);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				String new_innb = saveData.get("dft_innb");
				if(new_innb.equals("0") && !dft_innb.equals("")){
					prdData.put("dft_innb", dft_innb);
				}else{
					prdData.put("dft_innb", new_innb);		
				}
				cnt = getSqlSession().insert("m_fixingTrtPrdlstsave", prdData);
			}
		}
		return cnt;
	}
	//모바일 POG및 현재 진열줄수
	@Override
	public JSONObject fixingMPoglist(ActivityGridVo vo) throws SQLException,Exception {

		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}

		List<Map<String, Object>> prdlist = getSqlSession().selectList("fixingPogPrdList", vo );
		List<Map<String, String>> Itemlist = getSqlSession().selectList("fixingPogPrdItemList", vo );
		
		vo.setColumn_code("0000000023");
		vo.setBody_code("0000000022");
		if(baseDate.equals(sysDate)){
			int cnt = getSqlSession().selectOne("fixingPogCnt", vo );
			if(cnt== 0){
				prdlist = getSqlSession().selectList("tempFixingPogPrdList", vo );
				Itemlist = getSqlSession().selectList("tempFixingPogPrdItemList", vo );
			}
		}
		JSONObject body = new JSONObject();
		String innb = "";
		if(prdlist != null && prdlist.size() > 0 && prdlist.get(0).get("dfa_innb") !=null ){
			innb =prdlist.get(0).get("dfa_innb")+"";
		}
		body.put("main_innb", innb );
		return CommonUtil.makeMobileRespons(prdlist,Itemlist,"pog_list","oi_code","item","dfap_oi_code",body);
	}

	@Override
	public ActivityGridMobileVo fixingMobileBiglist(ActivityGridVo vo) throws SQLException, Exception {
		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		
		List<Map<String, String>> mapColumn =  getSqlSession().selectList("fixingBigColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = getSqlSession().selectList("fixingBiglist", vo );
		vo.setBodyArr(mapBody);
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("fixingBigInfo", vo );
		if(agmVo != null ){
			agmVo.setColumnArr(mapColumn);
			agmVo.setBodyArr(mapBody);
		}else if(baseDate.equals(sysDate)){
			agmVo = new ActivityGridMobileVo();
			vo.setColumn_code("0000000036");
			vo.setColumn_key("dfbp");
			vo.setColumn_table("tb_diary_fix_big");
			vo.setBody_code("0000000037");
			List<Map<String, String>> tempColumn =  getSqlSession().selectList("tempColunmlist", vo );
			vo.setColumnArr(tempColumn);
			List<Map<String, String>> tempBody = getSqlSession().selectList("tempBodylist", vo );
			agmVo.setColumnArr(tempColumn);
			agmVo.setBodyArr(tempBody);
		}
		return agmVo;
	}
	//모바일 PD매대 리스트
	@Override
	public ActivityGridMobileVo fixingMobilePdlist(ActivityGridVo vo) throws SQLException, Exception  {
		String baseDate = CommonUtil.getBaseDe(vo.getBase_de()); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		if(baseDate.equals(sysDate) ){
			vo.setUpdateFlag("true");
		}else{
			vo.setUpdateFlag("false");
		}
		List<Map<String, String>> mapColumn = new ArrayList<Map<String,String>>();
		mapColumn =  getSqlSession().selectList("fixingPdColunmlist", vo );
		vo.setColumnArr(mapColumn);
		List<Map<String, String>> mapBody = new ArrayList<Map<String,String>>();
		mapBody = getSqlSession().selectList("fixingPdlist", vo );
		vo.setBodyArr(mapBody);
//		
		ActivityGridMobileVo agmVo = getSqlSession().selectOne("fixingPdInfo", vo );
		if(agmVo != null){
			agmVo.setColumnArr(mapColumn);
			agmVo.setBodyArr(mapBody);
		}else if(baseDate.equals(sysDate)){
			agmVo = new ActivityGridMobileVo();
			vo.setColumn_code("0000000038");
			vo.setColumn_key("dfpp");
			vo.setColumn_table("tb_diary_fix_pd");
			vo.setBody_code("0000000039");
			List<Map<String, String>> tempColumn =  getSqlSession().selectList("tempColunmlist", vo );
			vo.setColumnArr(tempColumn);
			List<Map<String, String>> tempBody = getSqlSession().selectList("tempBodylist", vo );
			agmVo.setColumnArr(tempColumn);
			agmVo.setBodyArr(tempBody);
		}
		return agmVo;
	}

	//모바일 행사매대 목록
	@Override
	public ModelAndView fixingMobileEvnlist(Map<String, String> map) throws SQLException, Exception  {
		String baseDate = CommonUtil.getBaseDe(map.get("base_de")); 
		String sysDate = CommonUtil.getSysBaseDe(); 
		
		if(baseDate.equals(sysDate) ){
			map.put("updateFlag", "true");
		}else{
			map.put("updateFlag", "false");
		}
		System.out.println("[모바일 행사매대 목록 코드 위치 ActivityFixingService.java line: 481] 하드코딩 : 0000000034");
		String oiType = "0000000034";
		
		List<Map<String, Object>> evnlist = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> stndlist = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> itemlist = new ArrayList<Map<String,Object>>();
		ActivityEvnMobileVo evnVo  =  new ActivityEvnMobileVo();
		map.put("oi_type", oiType);
		evnlist = getSqlSession().selectList("fixingMobileEvnlist", map );
		int evnlistCnt = getSqlSession().selectOne("fixingMobileEvnlistCnt", map );
		if(baseDate.equals(sysDate) && evnlistCnt == 0){
			evnlist = getSqlSession().selectList("tempFixingMobileEvnlist", oiType );
			stndlist = getSqlSession().selectList("tempFixingMobileEvnStndlist", oiType );
		}else{
			stndlist = getSqlSession().selectList("fixingMobileEvnStndlist", map );
			
			itemlist = getSqlSession().selectList("fixingMobileEvnStndItemlist", map );
			stndlist  = CommonUtil.makeMobileChildSet(stndlist,itemlist,"oi_code","oi_code","item");
			
			evnVo = getSqlSession().selectOne("fixingMobileEvnInfo", map );
		}
		
		evnlist  = CommonUtil.makeMobileChildSet(evnlist,stndlist,"oi_code","dfes_oi_code","stnd_list");
		
		if(evnVo != null ){
			evnVo.setEvn_list(evnlist);
		}
		return CommonUtil.makeMobileRespons(evnVo,"evn_list");
		
		
	}

	
	//모바일 보조진열 저장
	@Override
	public int fixingMobileBigSave(ActivitySaveVo vo) throws SQLException, Exception {
		int cnt = 0;
		List<Map<String, String>> columnArr = CommonUtil.stringToList(vo.getParamArr1());
		List<Map<String, String>> bodyArr = CommonUtil.stringToList(vo.getParamArr2());
		
		String base_de = CommonUtil.getBaseDe(vo.getBase_de());
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String partclr_matter =  vo.getPartclr_matter();
		String main_innb = vo.getMain_innb();
		if(main_innb.equals("")){
			main_innb = null;
		}
		
		Map<String, String> saveData = new HashMap<String, String>();
		saveData.put("base_de", base_de);
		saveData.put("em_no", em_no);
		saveData.put("cm_code", cm_code);
		saveData.put("om_code", om_code);
		saveData.put("sm_code", sm_code);
		saveData.put("dfb_innb", main_innb);		
		saveData.put("partclr_matter", partclr_matter);
		
//		System.out.println("MAIN 저장 데이터 : " + saveData);
		
		cnt = getSqlSession().update("fixingMoblieBigSave", saveData );
		
		
		String new_innb = saveData.get("dfb_innb");
		String dfb_innb = "";//saveData.get("dfb_innb");
		if(new_innb.equals("0") && !main_innb.equals("")){
			dfb_innb = main_innb;
		}else{
			dfb_innb = new_innb;
		}
		
		if(cnt > 0 && saveData.get("dfb_innb") != null && !saveData.get("dfb_innb").equals("") ){
			for (Map<String, String> bodyMap : bodyArr) {
				
				Map<String, String> prdData = new HashMap<String, String>();
				prdData.put("base_de", base_de);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				
				
				
				prdData.put("dfb_innb", dfb_innb);				
				prdData.put("partclr_matter", partclr_matter);
				String dfbp_innb = bodyMap.get("dfbp_innb");
				if(dfbp_innb.equals("")){
					dfbp_innb = null;
				}
//				System.out.println("dfbp_innb"+ dfbp_innb);
				prdData.put("dfbp_innb", dfbp_innb);
				prdData.put("dfbp_oi_code", bodyMap.get("oi_code"));
				prdData.put("dfbp_oi_code_nm", bodyMap.get("oi_code_nm"));
				
				
				
				
				cnt = getSqlSession().update("fixingMoblieBigPrdSave", prdData );
				
				
				String new_dfbp_innb = prdData.get("dfbp_innb");
				String last_dfbp_innb = "";
				if(dfbp_innb != null && !dfbp_innb.equals("")){
					last_dfbp_innb = dfbp_innb;
				}else{
					last_dfbp_innb = new_dfbp_innb;
				}
				if(prdData.get("dfbp_innb") != null && !prdData.get("dfbp_innb").equals("") ){
					for (Map<String, String> columnMap : columnArr) {
						Map<String, String> itemData = new HashMap<String, String>();
						itemData.put("base_de", base_de);
						itemData.put("em_no", em_no);
						itemData.put("cm_code", cm_code);
						itemData.put("om_code", om_code);
						itemData.put("sm_code", sm_code);
						itemData.put("dfb_innb", dfb_innb);				
//				itemData.put("dfb_innb", saveData.get("dfb_innb"));				
						itemData.put("partclr_matter", partclr_matter);
						
						String dfbpi_cur_value = bodyMap.get(columnMap.get("oi_code"));
						if(dfbpi_cur_value.equals("")){
							dfbpi_cur_value = null;
						}
						itemData.put("dfbpi_cur_value", dfbpi_cur_value);
						String dfbpi_innb = bodyMap.get(columnMap.get("oi_code")+"_innb");
						if(dfbpi_innb.equals("")){
							dfbpi_innb = null;
						}
						itemData.put("dfbpi_innb", dfbpi_innb);
						itemData.put("dfbp_innb", last_dfbp_innb);
						itemData.put("dfbp_oi_code", bodyMap.get("oi_code"));
						itemData.put("dfbp_oi_code_nm", bodyMap.get("oi_code_nm"));
						itemData.put("oi_code", columnMap.get("oi_code"));
						itemData.put("oi_code_nm", columnMap.get("oi_code_nm"));
						cnt = getSqlSession().update("fixingMoblieBigPrdItemSave", itemData );
					}
				}
			}
			
		}
		
		return cnt;
	}

	// PD매대 현황 저장
	@Override
	public int fixingMobilePdSave(ActivitySaveVo vo) throws SQLException, Exception{
//		System.out.println("\n#################\nfixing PD매대 저장 : " + vo.toString()+"\n#################\n");
		int cnt = 0;
		List<Map<String, String>> columnArr = CommonUtil.stringToList(vo.getParamArr1());
		List<Map<String, String>> bodyArr = CommonUtil.stringToList(vo.getParamArr2());
		
		String base_de = CommonUtil.getBaseDe(vo.getBase_de());
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
		saveData.put("dfp_innb", main_innb);		
		saveData.put("partclr_matter", partclr_matter);
		
//		System.out.println("PD MAIN 저장 데이터 : " + saveData);
		
		cnt = getSqlSession().update("fixingMobliePdSave", saveData );
		
		String new_innb = saveData.get("dfp_innb");
		String dfp_innb = "";//saveData.get("dfb_innb");
		if(new_innb.equals("0") && !main_innb.equals("")){
			dfp_innb = main_innb;
		}else{
			dfp_innb = new_innb;
		}
		
		if(cnt > 0 && saveData.get("dfp_innb") != null && !saveData.get("dfp_innb").equals("") ){
			for (Map<String, String> bodyMap : bodyArr) {
				
				Map<String, String> prdData = new HashMap<String, String>();
				prdData.put("base_de", base_de);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				prdData.put("dfp_innb", dfp_innb);				
				prdData.put("partclr_matter", partclr_matter);
				
				String dfpp_innb = bodyMap.get("dfpp_innb");
				if(dfpp_innb == null || dfpp_innb.equals("")){
					dfpp_innb = null;
				}
				prdData.put("dfpp_innb", dfpp_innb);
				prdData.put("dfpp_oi_code", bodyMap.get("oi_code"));
				prdData.put("dfpp_oi_code_nm", bodyMap.get("oi_code_nm"));
				
				
				
				
//				System.out.println("PRD 저장 데이터 : " + prdData);
				cnt = getSqlSession().update("fixingMobliePdPrdSave", prdData );
				
				String new_dfpp_innb = prdData.get("dfpp_innb");
				String last_dfpp_innb = "";
				if(dfpp_innb != null && !dfpp_innb.equals("")){
					last_dfpp_innb = dfpp_innb;
				}else{
					last_dfpp_innb = new_dfpp_innb;
				}
				
				if(prdData.get("dfpp_innb") != null && !prdData.get("dfpp_innb").equals("") ){
					for (Map<String, String> columnMap : columnArr) {
						Map<String, String> itemData = new HashMap<String, String>();
						itemData.put("base_de", base_de);
						itemData.put("em_no", em_no);
						itemData.put("cm_code", cm_code);
						itemData.put("om_code", om_code);
						itemData.put("sm_code", sm_code);
						itemData.put("dfp_innb", dfp_innb);				
						//				itemData.put("dfp_innb", dfp_innb);				
						itemData.put("partclr_matter", partclr_matter);
						
						
						String dfppi_cur_value = bodyMap.get(columnMap.get("oi_code"));
						if(dfppi_cur_value.equals("")){
							dfppi_cur_value = null;
						}
						itemData.put("dfppi_cur_value", dfppi_cur_value);
						
						String dfppi_innb = bodyMap.get(columnMap.get("oi_code")+"_innb");
						if(dfppi_innb == null || dfppi_innb.equals("")){
							dfppi_innb = null;
						}
						itemData.put("dfppi_innb", dfppi_innb);
						itemData.put("dfpp_innb", last_dfpp_innb);
						itemData.put("dfpp_oi_code", bodyMap.get("oi_code"));
						itemData.put("dfpp_oi_code_nm", bodyMap.get("oi_code_nm"));
						itemData.put("oi_code", columnMap.get("oi_code"));
						itemData.put("oi_code_nm", columnMap.get("oi_code_nm"));
//						System.out.println("PRD ITEM 저장 데이터 : " + itemData);
						cnt = getSqlSession().update("fixingMobliePdPrdItemSave", itemData );
					}
				}
			}
		}
		
		return cnt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int fixingMobilePogSave(ActivitySaveVo vo) throws SQLException, Exception {
		int cnt =1;
//		System.out.println("\n#################\nfixing POG 저장 : " + vo.toString()+"\n#################\n");
		List<Map<String, Object>> columnArr = CommonUtil.stringToObjList(vo.getParamArr1());
		// 마스터 저장
		
		String base_de = CommonUtil.getBaseDe(vo.getBase_de());
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
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
		saveData.put("dfa_innb", main_innb);		
		
		cnt = getSqlSession().update("fixingMobliePogSave", saveData );
		
		String new_innb = saveData.get("dfa_innb");
		String dfa_innb = "";//saveData.get("dfb_innb");
		
		if(new_innb.equals("0") && !main_innb.equals("")){
			dfa_innb = main_innb;
		}else{
			dfa_innb = new_innb;
		}
		if(cnt  > 0 ){
			for (Map<String, Object> columnMap : columnArr) {
//				System.out.println("POG Prd : " + columnMap);
				
				Map<String, String> prdData = new HashMap<String, String>();
				prdData.put("base_de", base_de);
				prdData.put("em_no", em_no);
				prdData.put("cm_code", cm_code);
				prdData.put("om_code", om_code);
				prdData.put("sm_code", sm_code);
				String dfap_innb =(String)columnMap.get("dfap_innb");
				if(dfap_innb == null || dfap_innb.equals("")){
					dfap_innb = null;
				}
				prdData.put("dfap_innb", dfap_innb);
				prdData.put("dfa_innb", dfa_innb);				
				prdData.put("dfap_oi_code", (String)columnMap.get("oi_code"));
				prdData.put("dfap_oi_code_nm", (String)columnMap.get("oi_code_nm"));
				prdData.put("dfap_pog_rate", (String)columnMap.get("dfap_pog_rate"));
				prdData.put("dfap_cur_rate", (String)columnMap.get("dfap_cur_rate"));
				prdData.put("dfap_partclr_matter",(String)columnMap.get("dfap_partclr_matter") );
				
//				System.out.println("PRD 저장 데이터 : " + prdData);
				cnt = getSqlSession().update("fixingMobliePogPrdSave", prdData );
				
				String new_dfap_innb = prdData.get("dfap_innb");
				String last_dfap_innb = "";
				if(dfap_innb != null && !dfap_innb.equals("")){
					last_dfap_innb = dfap_innb;
				}else{
					last_dfap_innb = new_dfap_innb;
				}
				
				
				List<Map<String, Object>> itemArr = (List<Map<String, Object>>) columnMap.get("item");
				for (Map<String, Object> itemMap : itemArr) {
//					System.out.println("POG item data : " + itemMap);
					
					Map<String, String> itemData = new HashMap<String, String>();
					itemData.put("base_de", base_de);
					itemData.put("em_no", em_no);
					itemData.put("cm_code", cm_code);
					itemData.put("om_code", om_code);
					itemData.put("sm_code", sm_code);
					
					
					String dfapi_innb = (String)itemMap.get("dfapi_innb");
					if(dfapi_innb == null || dfapi_innb.equals("")){
						dfapi_innb = null;
					}
					
					itemData.put("dfapi_innb", dfapi_innb);
					itemData.put("dfap_innb", last_dfap_innb);
					itemData.put("dfa_innb", dfa_innb);				
					itemData.put("dfap_oi_code", (String)columnMap.get("oi_code"));			// 부모OI 코드
					itemData.put("dfap_oi_code_nm", (String)columnMap.get("oi_code_nm"));	// 부모OI 코드명
					itemData.put("oi_code", (String)itemMap.get("oi_code"));
					itemData.put("oi_code_nm", (String)itemMap.get("oi_code_nm"));
					
					
					//POG 진열줄수
					String dfapi_pog_value = (String)itemMap.get("dfapi_pog_value");
					if(dfapi_pog_value == null ||dfapi_pog_value.equals("")){
						dfapi_pog_value = null;
					}
					itemData.put("dfapi_pog_value", dfapi_pog_value);
					// 현제 진열줄수
					String dfapi_cur_value = (String)itemMap.get("dfapi_cur_value");
					if(dfapi_cur_value == null ||dfapi_cur_value.equals("")){
						dfapi_cur_value = null;
					}
					itemData.put("dfapi_cur_value", dfapi_cur_value);
					
//					System.out.println("ITEM 저장 데이터 : " + itemData);
					cnt = getSqlSession().update("fixingMobliePogPrdItemSave", itemData );
					
				}
			}
		}
		return cnt;
	}

	@Override
	public int fixingMobileEvnSave(ActivityEvnMobileSaveVo vo) throws SQLException, Exception {
//		System.out.println("\n#################\nfixing 행사매대 저장 : " + vo.toString()+"\n#################\n");
		String main_innb =  vo.getMain_innb(); 
		vo.setDfe_innb(main_innb); 
		int cnt = getSqlSession().insert("fixingMobileEvnSave",vo);
		String dfe_innb = vo.getDfe_innb();
		
		if( main_innb != null && dfe_innb.equals("0")){
			vo.setDfe_innb(main_innb);
		}
		if(cnt > 0 ){
			String dfes_innb = vo.getDfes_innb();
			cnt = getSqlSession().insert("fixingMobileEvnStndSave",vo);
			String new_dfes_innb = vo.getDfes_innb();
			if(new_dfes_innb.equals("0")){
				vo.setDfes_innb(dfes_innb);
			}
			MultipartFile files = vo.getFile();
			vo.setDfesi_img_url(attachManager.uploadImageFile(files));
			
			cnt = getSqlSession().insert("fixingMobileEvnStndItemSave",vo);
		}
		return cnt;
	}

	@Override
	public int fixingMobileEvnDel(ActivityEvnMobileSaveVo vo)throws SQLException, Exception {
		return getSqlSession().insert("fixingMobileEvnDel",vo);
	}

	@Override
	public int fixingMobileOddDel(Map<String, String> map) throws SQLException {
		return getSqlSession().update("fixingMobileOddDel",map);
	}

	
}
