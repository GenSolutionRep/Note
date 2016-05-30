package com.dasa.analysis.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.dasa.analysis.vo.DisPlayColunmVo;
import com.dasa.analysis.vo.DisPlayVo;
import com.dasa.analysis.vo.DisplayGridVo;
import com.gensolution.activity.vo.ActivityGridVo;
import com.gensolution.analysis.dao.DisPlayDao;
import com.vertexid.utill.CommonUtil;
import com.vertexid.vo.NaviVo;

public class DisPlayService  extends SqlSessionDaoSupport implements DisPlayDao{

	@Override
	public List<DisPlayVo> displayList() throws SQLException {
		return getSqlSession().selectList("displayList");
	}

	@Override
	public List<DisPlayVo> displayPrdList(DisPlayVo vo) throws SQLException {
		return getSqlSession().selectList("displayPrdList",vo);
	}

	@Override
	public List<DisPlayVo> displayPrdItemList(DisPlayVo vo) throws SQLException {
//		System.out.println("vo : " + vo.toString());
		return getSqlSession().selectList("displayPrdItemList",vo);
	}

	
/////////////////////////////////////////////////////////////사원별 진열률111111111111111111111111/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave1(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	@Override
	public int displayPrdItemSave1(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			map.put("colunm", vo.getColunm());
			cnt += getSqlSession().insert("displayPrdItemSave", map);
			if(map.get("collect_at").equals("Y") ){
				columnArr.add("ent_"+vo.getColunm()+"_"+map.get("oi_nick_name"));
			}
			
		}
		columnArr.add("rate_"+vo.getColunm()+"_DSF");
		for(String colunm : columnArr){
//			System.out.println("column : " + colunm);
			Map<String, String> columnVo = getSqlSession().selectOne("displayPrdItemColumnCheck1", colunm);
//			System.out.println("column : " + colunm);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				Map<String,String> map = new HashMap<String, String>();
				map.put("colunm", colunm);
				cnt += getSqlSession().insert("displayPrdItemColumnAdd1", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////사원별 진열률111111111111111111111111/////////////////////////////////////////////////////////////////////	
	
	
/////////////////////////////////////////////////////////////사원별 보조진열3333333333333333333333/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave3(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	
	@Override
	public int displayPrdItemSave3(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			map.put("colunm", vo.getColunm());
			cnt += getSqlSession().insert("displayPrdItemSave", map);
			if(map.get("collect_at").equals("Y") ){
				columnArr.add("ent_"+vo.getColunm()+"_"+map.get("oi_nick_name"));
			}
			
		}
		for(String colunm : columnArr){
//			System.out.println("column : " + colunm);
			Map<String, String> columnVo = getSqlSession().selectOne("displayPrdItemColumnCheck3", colunm);
//			System.out.println("column : " + colunm);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				Map<String,String> map = new HashMap<String, String>();
				map.put("colunm", colunm);
				cnt += getSqlSession().insert("displayPrdItemColumnAdd3", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////사원별 보조진열3333333333333333333333/////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/////////////////////////////////////////////////////////////관리업체별 진열률44444444444444444444/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave4(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	// 신규
	@Override
	public int displayPrdItemSave4(DisPlayVo vo) throws SQLException {
		String table = "tb_display_arr";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dpi_innb") == null || map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
				cnt = getSqlSession().insert("displayPrdItemSaveInsert", map);
//				System.out.println("dpi_innb : " + map.get("dpi_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdItemSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}
		}
//		columnArr.add("etc_"+vo.getDp_innb()+"_DSF");
		columnArr.add("rate_"+vo.getDp_innb()+"_DSF");
		for(String colunm : columnArr){
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////관리업체별 진열률44444444444444444444/////////////////////////////////////////////////////////////////////
	
	
	
/////////////////////////////////////////////////////////////관리업체별 보조진열현황666666666666666666666666/////////////////////////////////////////////////////////////////////
	@Override
	public int displayPrdSave6(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	
	@Override
	public int displayPrdItemSave6(DisPlayVo vo) throws SQLException {
		String table = "tb_display_big";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dpi_innb") == null || map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
				cnt = getSqlSession().insert("displayPrdItemSaveInsert", map);
//				System.out.println("dpi_innb : " + map.get("dpi_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdItemSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}
		}
		for(String colunm : columnArr){
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
			}
		}
//		System.out.println("cnt : " + cnt);
		return cnt;
	}
/////////////////////////////////////////////////////////////관리업체별 보조진열현황666666666666666666666666/////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////////////////////관리업체별 PD매대설치현황7777777777777777777777/////////////////////////////////////////////////////////////////////	
	
	@Override
	public int displayPrdSave7(DisPlayVo vo) throws SQLException {
		int cnt = 0;
		for(Map<String,String> map : vo.getPrdArr()){
			if(map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
			}
			map.put("em_no", vo.getEm_no());
			cnt = getSqlSession().insert("displayPrdSave", map);
		}
		return cnt;
	}
	
	@Override
	public int displayPrdItemSave7(DisPlayVo vo) throws SQLException {
		String table = "tb_display_pd";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dpi_innb") == null || map.get("dpi_innb").equals("")){
				map.put("dpi_innb",null);
				cnt = getSqlSession().insert("displayPrdItemSaveInsert", map);
//				System.out.println("dpi_innb : " + map.get("dpi_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdItemSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb")+"_"+map.get("dpi_innb"));
				}
			}
		}
		for(String colunm : columnArr){
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
//			System.out.println("columnVo : " + columnVo);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}
/////////////////////////////////////////////////////////////관리업체별 PD매대설치현황7777777777777777777777/////////////////////////////////////////////////////////////////////	

	
	// 취급품목
	@Override
	public int displayPrdSave5(DisPlayVo vo) throws SQLException {
		String table = "tb_display_trt";
		int cnt = 0;
		List< String> columnArr =  new ArrayList<String>();
		for(Map<String,String> map : vo.getPrdArr()){
			map.put("em_no", vo.getEm_no());
			
			if(map.get("dp_innb") == null || map.get("dp_innb").equals("")){
				map.put("dp_innb",null);
				cnt = getSqlSession().insert("displayPrdSaveInsert", map);
//				System.out.println("dp_innb : " + map.get("dp_innb"));
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb"));
				}
			}else{
				cnt = getSqlSession().insert("displayPrdSaveUpdate", map);
				if(map.get("collect_at").equals("Y") ){
					columnArr.add("ent_"+map.get("dp_innb"));
				}
			}
		}
		
		for(String colunm : columnArr){
//			System.out.println("column : " + colunm);
			Map<String,String> map = new HashMap<String, String>();
			map.put("colunm", colunm);
			map.put("table", table);
			Map<String, String> columnVo = getSqlSession().selectOne("displayColumnCheck", map);
			if(columnVo == null ){
				cnt += getSqlSession().insert("displayPrdItemColumnAdd", map);
//				System.out.println("cnt : " + cnt);
			}
		}
		return cnt;
	}

	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////신규 배치 ////////////////////////////////////////////////////////////////////
	@Override
	public int displayBatchArr(String type) throws SQLException ,ParseException{
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", type);
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		String tempGroupId = "";
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			String itemId = vo.getItem_id();
			String itemNm = vo.getItem_nm();
			
			if(!tempGroupId.equals(groupId)){
				Map<String,String> mapRate =  new HashMap<String, String>();
				mapRate.put("group_id", groupId);
				mapRate.put("item_id", "DSF");
				mapRate.put("group_nm", groupNm);
				mapRate.put("item_nm", "DSF");
				mapRate.put("colunm_id", "rate");
				columnArr.add(mapRate);
//				Map<String,String> mapEtc =  new HashMap<String, String>();
//				mapEtc.put("group_id", groupId);
//				mapEtc.put("item_id", "DSF");
//				mapEtc.put("group_nm", groupNm);
//				mapEtc.put("item_nm", "DSF");
//				mapEtc.put("colunm_id", "etc");
//				columnArr.add(mapEtc);
			}
			tempGroupId  = groupId;
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
		
		int delCnt = getSqlSession().delete("displayDeleteListArr", CommonUtil.getSysDe());
//		System.out.println("Arr 삭제 : " + delCnt);
		
		
		cnt +=  getSqlSession().insert("displayInsertListArr", vo);
//		List<String> omList = new ArrayList<String>();
//		omList = getSqlSession().selectList("displayOmList");
//		for (String om_code : omList) {
////			System.out.println("진열률 지점별 배치 om_code : " +om_code);
//			vo.setOm_code(om_code);
//			try {
//				cnt +=  getSqlSession().insert("displayInsertListArr", vo);
//			} catch (Exception e) {
//				cnt = 0;
//				e.printStackTrace();			
//			}
//		}
		
		return cnt;
	}

	@Override
	public int displayBatchPd(String type) throws SQLException,ParseException {
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", type);
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
//		String tempGroupId = "";
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			String itemId = vo.getItem_id();
			String itemNm = vo.getItem_nm();
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
		int delCnt = getSqlSession().delete("displayDeleteListPd", CommonUtil.getSysDe());
//		System.out.println("PD 삭제 : " + delCnt);
		
		cnt +=  getSqlSession().insert("displayInsertListPd", vo);
//		List<String> omList = new ArrayList<String>();
//		omList = getSqlSession().selectList("displayOmList");
//		for (String om_code : omList) {
////			System.out.println("PD매대 지점별 배치 om_code : " +om_code);
//			vo.setOm_code(om_code);
//			try {
//				cnt +=  getSqlSession().insert("displayInsertListPd", vo);
//			} catch (Exception e) {
//				cnt = 0;
//				e.printStackTrace();			
//			}
//		}
		
		return cnt;
	}

	@Override
	public int displayBatchBig(String type) throws SQLException,ParseException {
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", type);
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			String itemId = vo.getItem_id();
			String itemNm = vo.getItem_nm();
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
		int delCnt = getSqlSession().delete("displayDeleteListBig", CommonUtil.getSysDe());
//		System.out.println("Big 삭제 : " + delCnt);

		
		
		cnt +=  getSqlSession().insert("displayInsertListBig", vo);
//		List<String> omList = new ArrayList<String>();
//		omList = getSqlSession().selectList("displayOmList");
//		for (String om_code : omList) {
////			System.out.println("진열률 지점별 배치 om_code : " +om_code);
//			vo.setOm_code(om_code);
//			try {
//				cnt +=  getSqlSession().insert("displayInsertListBig", vo);
//			} catch (Exception e) {
//				cnt = 0;
//				e.printStackTrace();			
//			}
//		}
		
//		System.out.println("insertcnt : " + cnt );
		return cnt;
	}

	@Override
	public int displayBatchTrt(String type) throws SQLException,ParseException {
		int cnt = 1;
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayMainColumnList", type);
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		for (DisPlayColunmVo vo : displayList) {
			String groupId = vo.getGroup_id();
			String groupNm = vo.getGroup_nm();
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("group_nm", groupNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		
		DisPlayVo vo = new DisPlayVo();
		vo.setPrdArr(columnArr);
		vo.setD_innb(type);
		vo.setBase_de(CommonUtil.getSysDe());
		
//		System.out.println("omList : " + omList.size());
		
		int delCnt = getSqlSession().delete("displayDeleteListTrt", CommonUtil.getSysDe());
//		System.out.println("TRT 삭제 : " + delCnt);
		
		
		cnt +=  getSqlSession().insert("displayInsertListTrt", vo);
		
		return cnt;
	}

	@Override
	public DisplayGridVo displayArrList(NaviVo naviVo) throws SQLException {
		
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "4" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		String tempGroupId = "";
		String tempGroupNm= "";
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
//		}
//		for (DisPlayColunmVo colvo : displayList) {
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			if(i > 0  && !tempGroupId.equals(groupId)){
				Map<String,String> mapEtc =  new HashMap<String, String>();
				mapEtc.put("group_id", tempGroupId);
				mapEtc.put("item_id", "DSF");
				mapEtc.put("group_nm", tempGroupNm);
				mapEtc.put("item_nm", "DSF");
				mapEtc.put("colunm_id", "rate");
				columnArr.add(mapEtc);
			}
			tempGroupId  = groupId;
			tempGroupNm  = groupNm;
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
		Map<String,String> mapRate =  new HashMap<String, String>();
		mapRate.put("group_id", tempGroupId);
		mapRate.put("item_id", "DSF");
		mapRate.put("group_nm", tempGroupNm);
		mapRate.put("item_nm", "DSF");
		mapRate.put("colunm_id", "rate");
		columnArr.add(mapRate);
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayArrBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayArrSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}
	
	@Override
	public DisplayGridVo displayExcelArrList(NaviVo naviVo) throws SQLException {
		
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "4" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		String tempGroupId = "";
		String tempGroupNm= "";
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
//		}
//		for (DisPlayColunmVo colvo : displayList) {
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			if(i > 0  && !tempGroupId.equals(groupId)){
				Map<String,String> mapEtc =  new HashMap<String, String>();
				mapEtc.put("group_id", tempGroupId);
				mapEtc.put("item_id", "DSF");
				mapEtc.put("group_nm", tempGroupNm);
				mapEtc.put("item_nm", "DSF");
				mapEtc.put("colunm_id", "rate");
				mapEtc.put("cellId", "rate_"+groupId+"_DSF");
				mapEtc.put("cellName", "진열율");
				columnArr.add(mapEtc);
			}
			tempGroupId  = groupId;
			tempGroupNm  = groupNm;
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
		Map<String,String> mapRate =  new HashMap<String, String>();
		mapRate.put("group_id", tempGroupId);
		mapRate.put("item_id", "DSF");
		mapRate.put("group_nm", tempGroupNm);
		mapRate.put("item_nm", "DSF");
		mapRate.put("colunm_id", "rate");
		mapRate.put("cellId", "rate_"+tempGroupId+"_DSF");
		mapRate.put("cellName", "점유율");
		columnArr.add(mapRate);
//		System.out.println("columnArr : " + columnArr );
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayArrBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayArrSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}
	
	@Override
	public NaviVo displayArrListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayArrListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public NaviVo displayBigListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayBigListCnt", naviVo) );
		return naviVo;
	}

	
	@Override
	public DisplayGridVo displayBigList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "6" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayBigBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayBigSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public NaviVo displayPdListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayPdListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public DisplayGridVo displayPdList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "7" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		vo.setCm_code(naviVo.getCm_code());
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayPdBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayPdSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public NaviVo displayTrtListCnt(NaviVo naviVo) throws SQLException {
		naviVo.setTotRow( (Integer) getSqlSession().selectOne("displayTrtListCnt", naviVo) );
		return naviVo;
	}

	@Override
	public DisplayGridVo displayTrtList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayMainColumnList", "5" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		DisplayGridVo vo =  new DisplayGridVo();
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayTrtBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayTrtSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public DisplayGridVo displayExcelBigList(NaviVo naviVo) throws SQLException {
		
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "6" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayBigBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayBigSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public DisplayGridVo displayExcelPdList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayColumnList", "7" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayPdBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayPdSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

	@Override
	public DisplayGridVo displayExcelTrtList(NaviVo naviVo) throws SQLException {
		List<DisPlayColunmVo> displayList = getSqlSession().selectList("displayMainColumnList", "5" );
//		System.out.println("displayList : " + displayList );
		List< Map<String,String>> columnArr =  new ArrayList<Map<String,String>>();
		
		for (int i = 0; i < displayList.size(); i++) {
			DisPlayColunmVo colvo = displayList.get(i);
			
			String groupId = colvo.getGroup_id();
			String groupNm = colvo.getGroup_nm();
			String itemId = colvo.getItem_id();
			String itemNm = colvo.getItem_nm();
			
			
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("group_id", groupId);
			map.put("item_id", itemId);
			map.put("group_nm", groupNm);
			map.put("item_nm", itemNm);
			map.put("colunm_id", "ent");
			map.put("cellId", "ent_"+groupId+"_"+itemId);
			map.put("cellName", itemNm);
			columnArr.add(map);
		}
//		System.out.println("columnArr : " + columnArr );
		
		DisplayGridVo vo =  new DisplayGridVo(); 
		vo.setCm_code(naviVo.getCm_code());
		vo.setPagingEnd(naviVo.getPagingEnd());
		vo.setParams(naviVo.getParams());
		vo.setColumnArr(columnArr);
		List<Map<String, String>> mapBody = getSqlSession().selectList("displayTrtBodyList", vo );
		
		List<Map<String, String>> sumBody = getSqlSession().selectList("displayTrtSumList", vo );
		
//		System.out.println("mapBody : " + mapBody );
		vo.setBodyArr(mapBody);
		vo.setSumArr(sumBody);
		
		return vo;
	}

}
