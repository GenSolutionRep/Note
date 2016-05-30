package com.dasa.store.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.store.dao.StoreDAO;
import com.dasa.store.vo.CstmrGroupVo;
import com.dasa.store.vo.EmplManageVo;
import com.dasa.store.vo.ManageEntrpsVo;
import com.dasa.store.vo.StoreVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.Encoder;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.MobileNaviVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ StoreController.java
 * @ 2015. 9. 18.
 * @
 * @ 프로그램명: StoreController
 */
@Controller
public class StoreController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private StoreDAO dao;
	
	private ModelAndView mv;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	private JSONObject response;
	private String VAL_OM_CODE;
	
	private StoreVo storeVo;
	private EmplManageVo emplManageVo;
	private CstmrGroupVo cstmrGroupVo;
	private ManageEntrpsVo manageEntrpsVo;
	private List<StoreVo> storeList;
	private List<EmplManageVo> emplManageList;
	private List<CstmrGroupVo> cstmrGroupList;
	private List<ManageEntrpsVo> manageEntrpsList;
	private List<KeyValueVo> autoCompList;
	
	/**
	 * @메서드명: menuPg
	 * @작성일: 2014. 9. 30
	 * @작성자: 김진호
	 * @설명: 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/50/50-100/{typeCode}")
	public ModelAndView menuPg(@PathVariable("typeCode") String typeCode) throws Exception {
		VAL_OM_CODE = typeCode;
		mv = new ModelAndView();
		mv.addObject("typeCode", typeCode);
		mv.setViewName("/50/50-100");
		return mv;
	}
	
	/*고객그룹 ********************************************************************************/
	
	/**
	 * @메서드명: checkCstmrGroupCode
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 매장관리
	 */
	@RequestMapping("/store/checkCstmrGroupCode") 
	public ModelAndView checkCstmrGroupCode(@RequestParam("cg_code") String cg_code) throws Exception{
		cstmrGroupVo =  dao.checkCstmrGroupCode(cg_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(cstmrGroupVo);
		
		if(cstmrGroupVo==null)
			jsonObject.put("result", null);
		else
			jsonObject.put("result", jsonArray);
		
		//System.out.println(jsonObject);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	/**
	 * @메서드명: checkManageEntrpsCode
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 매장관리
	 */
	@RequestMapping("/store/checkManageEntrpsCode") 
	public ModelAndView checkManageEntrpsCode(	@RequestParam("cg_code") String cg_code,
												@RequestParam("me_code") String me_code) throws Exception{
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cg_code", cg_code);
		map.put("me_code", me_code);
		manageEntrpsVo =  dao.checkManageEntrpsCode(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(manageEntrpsVo);
		
		if(manageEntrpsVo==null)
			jsonObject.put("result", "");
		else
			jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	/**
	 * @메서드명: cstmrGroupList
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 매장관리
	 */
	@RequestMapping("/store/cstmrGroupList") 
	public ModelAndView cstmrGroupList() throws Exception{
		cstmrGroupList =  dao.selectCstmrGroupList("");
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(cstmrGroupList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	/**
	 * @메서드명: cstmrGroupList
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 매장관리
	 */
	@RequestMapping("/ds_store/cstmrGroupList") 
	public ModelAndView ds_cstmrGroupList() throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + " Web&Mobile:/ds_store/cstmrGroupList] 동서식품 모바일 : 매장목록 조회 ");
		CommonUtil.setSessionVo(session);
		cstmrGroupList =  dao.selectCstmrGroupList("");

		List<Map<String, String>> dsCstmrGroupList = new ArrayList<Map<String, String>>();
		if(cstmrGroupList !=  null){
			for (CstmrGroupVo cVo: cstmrGroupList) {
				Map<String, String> mapVo =  new HashMap<String, String>();
				mapVo.put("cg_code", cVo.getCg_code());
				mapVo.put("cg_nm", cVo.getCg_nm());
				dsCstmrGroupList.add(mapVo);
			}
		}
		
		jsonArray = JSONArray.fromObject(dsCstmrGroupList);
		jsonObject = new JSONObject();
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: cstmrGroupRow
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 매장관리
	 */
	@RequestMapping("/store/cstmrGroupRow") 
	public ModelAndView cstmrGroupRow(@RequestParam("cg_code") String cg_code) throws Exception{
		cstmrGroupVo =  dao.selectCstmrGroupRow(cg_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(cstmrGroupVo);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	/**
	 * @메서드명: updateCstmrGroup
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 고객그룹 update
	 */
	@RequestMapping("/store/updateCstmrGroup")
	public ModelAndView updateCstmrGroup(CstmrGroupVo p_cstmrGroupVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("cg_code", p_cstmrGroupVo.getCg_code().trim());   
		map.put("cg_nm",p_cstmrGroupVo.getCg_nm().trim());	     
		map.put("cg_memo",p_cstmrGroupVo.getCg_memo().trim());   
		map.put("use_at", p_cstmrGroupVo.getUse_at());   
		map.put("updt_man", login_no);
		
		int resultCnt = dao.updateCstmrGroup(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: deleteCstmrGroup
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 고객그룹 delete
	 */
	@RequestMapping("/store/deleteCstmrGroup")
	public ModelAndView deleteCstmrGroup(String cg_code) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sm_code", cg_code);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);

		int resultCnt = dao.updateCstmrGroup(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: insertCstmrGroup
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 고객그룹 insert
	 */
	@RequestMapping("/store/insertCstmrGroup")
	public ModelAndView insertCstmrGroup(CstmrGroupVo p_cstmrGroupVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//System.out.println("AutoYn : " + p_cstmrGroupVo.getAutoYn());
		map.put("autoYn", p_cstmrGroupVo.getAutoYn());   
		map.put("cg_code", p_cstmrGroupVo.getCg_code());   
		map.put("cg_nm",p_cstmrGroupVo.getCg_nm().trim());	     
		map.put("cg_memo",p_cstmrGroupVo.getCg_memo().trim());   
		map.put("use_at", p_cstmrGroupVo.getUse_at());   
		map.put("delete_at", 'N');
		map.put("regist_man", login_no);

		int resultCnt = dao.insertCstmrGroup(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/*관리업체********************************************************************************/
	
	/**
	 * @메서드명: manageEntrpsList
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 관리업체 조회
	 */
	@RequestMapping("/store/manageEntrpsList") 
	public ModelAndView manageEntrpsList(@RequestParam("cg_code") String cg_code, 
										@RequestParam("om_code") String om_code) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cg_code", cg_code);
		map.put("om_code", om_code);
		manageEntrpsList =  dao.selectManageEntrpsList(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(manageEntrpsList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	/**
	 * @메서드명: manageEntrpsList
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 관리업체 조회
	 */
	@RequestMapping("/ds_store/manageEntrpsList") 
	public ModelAndView ds_manageEntrpsList(@RequestParam("cg_code") String cg_code, @RequestParam("om_code") String om_code) throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + " WEB :/ds_store/manageEntrpsList] 동서식품 웹 : 관리업체 조회 cg_code : "+ cg_code+", om_code"+ om_code);
		CommonUtil.setSessionVo(session);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cg_code", cg_code);
		map.put("om_code", om_code);
		manageEntrpsList =  dao.selectManageEntrpsList(map);
		
		List<Map<String, String>> dsManageEntrpsList = new ArrayList<Map<String, String>>();
		if(manageEntrpsList !=  null){
			for (ManageEntrpsVo cVo: manageEntrpsList) {
				Map<String, String> mapVo =  new HashMap<String, String>();
				mapVo.put("me_code", cVo.getMe_code());
				mapVo.put("me_nm", cVo.getMe_nm());
				dsManageEntrpsList.add(mapVo);
			}
		}
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(dsManageEntrpsList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: cstmrGroupRow
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 관리업체 row
	 */
	@RequestMapping("/store/manageEntrpsRow") 
	public ModelAndView manageEntrpsRow(@RequestParam("cg_code") String cg_code,
										@RequestParam("me_code") String me_code) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cg_code", cg_code);
		map.put("me_code", me_code);
		manageEntrpsVo =  dao.selectManageEntrpsRow(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(manageEntrpsVo);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	/**
	 * @메서드명: updateCstmrGroup
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 고객그룹 update
	 */
	@RequestMapping("/store/updateManageEntrps")
	public ModelAndView updateManageEntrps(ManageEntrpsVo p_manageEntrpsVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("me_code", p_manageEntrpsVo.getMe_code());  
		map.put("cg_code", p_manageEntrpsVo.getCg_code());
		map.put("me_nm", p_manageEntrpsVo.getMe_nm().trim());
		map.put("me_memo", p_manageEntrpsVo.getMe_memo().trim());
		map.put("use_at", p_manageEntrpsVo.getUse_at());
		map.put("delete_at", p_manageEntrpsVo.getDelete_at());
		map.put("updt_man", login_no);
		
		int resultCnt = dao.updateManageEntrps(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: deleteCstmrGroup
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 고객그룹 delete
	 */
	@RequestMapping("/store/deleteManageEntrps")
	public ModelAndView deleteManageEntrps(String me_code) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("me_code", me_code);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);

		int resultCnt = dao.updateManageEntrps(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: insertCstmrGroup
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 고객그룹 insert
	 */
	@RequestMapping("/store/insertManageEntrps")
	public ModelAndView insertManageEntrps(ManageEntrpsVo p_manageEntrpsVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("autoYn", p_manageEntrpsVo.getAutoYn()); 
		map.put("me_code", p_manageEntrpsVo.getMe_code());  
		map.put("cg_code", p_manageEntrpsVo.getCg_code());
		map.put("me_nm", p_manageEntrpsVo.getMe_nm().trim());
		map.put("me_memo", p_manageEntrpsVo.getMe_memo().trim());
		map.put("use_at", p_manageEntrpsVo.getUse_at());
		map.put("delete_at", 'N');
		map.put("regist_man", login_no);

		int resultCnt = dao.insertManageEntrps(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/*매장관리 ********************************************************************************/
	/**
	 * @메서드명: storeList
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 매장관리
	 */
	@RequestMapping("/store/storeList") 
	public ModelAndView storeList(@RequestParam("cg_code") String cg_code, 
								  @RequestParam("me_code") String me_code,
								  @RequestParam("om_code") String om_code) throws Exception {
		
		HashMap map =  new HashMap();
		//map.put("om_code",VAL_OM_CODE);
		map.put("om_code",om_code);
		map.put("cg_code",cg_code); 
		map.put("me_code",me_code); 
		
		storeList =  dao.selectStoreList(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(storeList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	/**
	 * @메서드명: storeRow
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 해당 매장관리 row 조회
	 */
	@RequestMapping("/store/storeRow") 
	public ModelAndView storeRow(@RequestParam("sm_code") String sm_code) throws Exception {
		storeVo = dao.selectStoreRow(sm_code);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(storeVo);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
    /**
     * @메소드명: m_storeRow
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /store/m_storeRow
     */
	@RequestMapping(value = "/store/m_storeRow", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_storeRow(@ModelAttribute("vo") StoreVo reqStoreVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/store/m_storeRow");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String sm_code = reqStoreVo.getSm_code();
		System.out.println("sm_code=" + sm_code);
		
		//Check Required Parameter
		if (StringCheck.isNull(sm_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		StoreVo resStoreVo = dao.selectStoreRow(sm_code);
		System.out.println("resStoreVo=" + resStoreVo);
		
		if (resStoreVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_store = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("om_nm", resStoreVo.getOm_nm());
			bodyVo.put("cg_nm", resStoreVo.getCg_nm());
			bodyVo.put("me_nm", resStoreVo.getMe_nm());
			bodyVo.put("sm_nm", resStoreVo.getSm_nm());
			bodyVo.put("sm_odr_nm", resStoreVo.getSm_odr_nm());
			bodyVo.put("em_nm", resStoreVo.getEm_nm());
			bodyVo.put("sm_bizrno", resStoreVo.getSm_bizrno());
			bodyVo.put("sm_cvscafe_at", resStoreVo.getSm_cvscafe_at());
			bodyVo.put("sm_rprsntv_nm", resStoreVo.getSm_rprsntv_nm());
			bodyVo.put("sm_tlphon", resStoreVo.getSm_tlphon());
			bodyVo.put("sm_fxnum", resStoreVo.getSm_fxnum());
			bodyVo.put("sm_adres", resStoreVo.getSm_adres());
			bodyVo.put("sm_dtadres", resStoreVo.getSm_dtadres());
			bodyVo.put("sm_etcadres", resStoreVo.getSm_etcadres());
			body_store.add(bodyVo);
			body.put("store", body_store);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}	
	
	/**
	 * @메서드명: insertStore
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 매장관리 매장 insert
	 */
	@RequestMapping("/store/insertStore")
	public ModelAndView insertStore(StoreVo p_storeVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");		
		StoreVo dto = new StoreVo();
		
		dto.setFlag("INSERT");
		dto.setSm_code(p_storeVo.getSm_code());
		dto.setOm_code(p_storeVo.getOm_code());
		dto.setCg_code(p_storeVo.getCg_code());
		dto.setMe_code(p_storeVo.getMe_code());
		dto.setSm_nm(p_storeVo.getSm_nm().trim());
		dto.setSm_oper_at(p_storeVo.getSm_oper_at());
		dto.setSe_empl_ty(p_storeVo.getSe_empl_ty());
		dto.setEm_no(p_storeVo.getEm_no());
		dto.setSm_odr(p_storeVo.getSm_odr());
		dto.setSm_bizrno(p_storeVo.getSm_bizrno().trim());
		dto.setSm_cmpnm(p_storeVo.getSm_cmpnm().trim());
		dto.setSm_rprsntv_nm(p_storeVo.getSm_rprsntv_nm().trim());
		dto.setSm_zipcd(p_storeVo.getSm_zipcd().trim());
		//dto.setSm_area(p_storeVo.getSm_area().trim());
		dto.setSm_adres(p_storeVo.getSm_adres().trim());
		dto.setSm_dtadres(p_storeVo.getSm_dtadres().trim());
		dto.setSm_etcadres(p_storeVo.getSm_etcadres().trim());
		dto.setSm_la(p_storeVo.getSm_la().trim());
		dto.setSm_lo(p_storeVo.getSm_lo().trim());
		dto.setSm_site_se(p_storeVo.getSm_site_se());
		dto.setSm_site(p_storeVo.getSm_site().trim());
		dto.setSm_tlphon(p_storeVo.getSm_tlphon().trim());
		dto.setSm_fxnum(p_storeVo.getSm_fxnum().trim());
		dto.setSm_memo(p_storeVo.getSm_memo().trim());
		dto.setSm_cvscafe_at(p_storeVo.getSm_cvscafe_at().trim());
		dto.setEm_no_sub(p_storeVo.getEm_no_sub());
		dto.setSm_area1(p_storeVo.getSm_area1());
		dto.setSm_area2(p_storeVo.getSm_area2());
		
		dto.setUse_at("Y");
		dto.setDelete_at("N");
		dto.setRegist_man(login_no);
		dto.setUpdt_man(login_no);

		dao.saveStore(dto);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(dto);
		
		jsonObject.put("dto", jsonArray);
		
		//System.out.println(jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: updateStore
	 * @작성일: 2015. 9. 18
	 * @작성자: 최수영
	 * @설명: 매장관리 insert
	 */
	@RequestMapping("/store/updateStore")
	public ModelAndView updateStore(StoreVo p_storeVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");		
		StoreVo dto = new StoreVo();
		
	/*	System.out.println("p_storeVo.getSm_code() :" + p_storeVo.getSm_code());
		System.out.println("p_storeVo.getOm_code() :" + p_storeVo.getOm_code());
		System.out.println("p_storeVo.getCg_code() :" + p_storeVo.getCg_code());
		System.out.println("p_storeVo.getMe_code() :" + p_storeVo.getMe_code());
		System.out.println("p_storeVo.getSm_nm() :" + p_storeVo.getSm_nm());
		System.out.println("p_storeVo.getSm_oper_at() :" + p_storeVo.getSm_oper_at());
		System.out.println("p_storeVo.getSe_empl_ty() :" + p_storeVo.getSe_empl_ty());
		System.out.println("p_storeVo.getEm_no() :" + p_storeVo.getEm_no());
		System.out.println("p_storeVo.getSm_odr() :" + p_storeVo.getSm_odr());
		System.out.println("p_storeVo.getSm_bizrno() :" + p_storeVo.getSm_bizrno());
		System.out.println("p_storeVo.getSm_cmpnm() :" + p_storeVo.getSm_cmpnm());
		System.out.println("p_storeVo.getSm_rprsntv_nm() :" + p_storeVo.getSm_rprsntv_nm());
		System.out.println("p_storeVo.getSm_zipcd() :" + p_storeVo.getSm_zipcd());
		System.out.println("p_storeVo.getSm_area() :" + p_storeVo.getSm_area());
		System.out.println("p_storeVo.getSm_adres() :" + p_storeVo.getSm_adres());
		System.out.println("p_storeVo.getSm_dtadres() :" + p_storeVo.getSm_dtadres());
		System.out.println("p_storeVo.getSm_etcadres() :" + p_storeVo.getSm_etcadres());
		System.out.println("p_storeVo.getSm_tlphon() :" + p_storeVo.getSm_tlphon());
		System.out.println("p_storeVo.getSm_fxnum() :" + p_storeVo.getSm_fxnum());
		System.out.println("p_storeVo.getSm_memo() :" + p_storeVo.getSm_memo());
		System.out.println("p_storeVo.getSm_tlphon() :" + p_storeVo.getSm_tlphon());
		System.out.println("p_storeVo.getSm_fxnum() :" + p_storeVo.getSm_fxnum());
		System.out.println("p_storeVo.getSm_memo() :" + p_storeVo.getSm_memo());
		System.out.println("p_storeVo.getSm_tlphon() :" + p_storeVo.getSm_tlphon());
		System.out.println("p_storeVo.getSm_cvscafe_at() :" + p_storeVo.getSm_cvscafe_at());
		System.out.println("p_storeVo.getEm_no_sub() :" + p_storeVo.getEm_no_sub());
		*/
		dto.setFlag("UPDATE");
		dto.setSm_code(p_storeVo.getSm_code());
		dto.setOm_code(p_storeVo.getOm_code());
		dto.setCg_code(p_storeVo.getCg_code());
		dto.setMe_code(p_storeVo.getMe_code());
		dto.setSm_nm(p_storeVo.getSm_nm().trim());
		dto.setSm_oper_at(p_storeVo.getSm_oper_at());
		dto.setSe_empl_ty(p_storeVo.getSe_empl_ty());
		dto.setEm_no(p_storeVo.getEm_no());
		dto.setSm_odr(p_storeVo.getSm_odr());
		dto.setSm_bizrno(p_storeVo.getSm_bizrno().trim());
		dto.setSm_cmpnm(p_storeVo.getSm_cmpnm().trim());
		dto.setSm_rprsntv_nm(p_storeVo.getSm_rprsntv_nm());
		dto.setSm_zipcd(p_storeVo.getSm_zipcd());
		dto.setSm_area(p_storeVo.getSm_area());
		dto.setSm_adres(p_storeVo.getSm_adres().trim());
		dto.setSm_dtadres(p_storeVo.getSm_dtadres().trim());
		dto.setSm_etcadres(p_storeVo.getSm_etcadres().trim());
		dto.setSm_la(p_storeVo.getSm_la().trim());
		dto.setSm_lo(p_storeVo.getSm_lo().trim());
		dto.setSm_site_se(p_storeVo.getSm_site_se());
		dto.setSm_site(p_storeVo.getSm_site().trim());
		dto.setSm_tlphon(p_storeVo.getSm_tlphon().trim());
		dto.setSm_fxnum(p_storeVo.getSm_fxnum().trim());
		dto.setSm_memo(p_storeVo.getSm_memo().trim());
		dto.setSm_cvscafe_at(p_storeVo.getSm_cvscafe_at().trim());
		dto.setEm_no_sub(p_storeVo.getEm_no_sub());
		dto.setSm_sap_code(p_storeVo.getSm_sap_code());
		dto.setSm_area1(p_storeVo.getSm_area1());
		dto.setSm_area2(p_storeVo.getSm_area2());
		dto.setUse_at("Y");
		dto.setDelete_at("N");
		dto.setRegist_man(login_no);
		dto.setUpdt_man(login_no);
		
		//System.out.println("p_storeVo.getSm_cvscafe_at() : " + p_storeVo.getSm_cvscafe_at());
		
		dao.saveStore(dto);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(dto);
		
		jsonObject.put("dto", jsonArray);
		
		//System.out.println(jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: deleteStore
	 * @작성일: 2015. 9. 18
	 * @작성자: 최수영
	 * @설명: 매장관리 delete
	 */
	@RequestMapping("/store/deleteStore")
	public ModelAndView deleteStore(String sm_code) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sm_code", sm_code);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);
		
		int resultCnt = dao.deleteStore(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: selectEmpList
	 * @작성일: 2015. 9. 30
	 * @작성자: 최수영
	 * @설명: 사원조회
	 */
	@RequestMapping("/store/empList") 
	public ModelAndView selectEmpList(String om_code, String em_dty_code) throws Exception{
		HashMap<String, String> map =  new HashMap<String, String>();
		
		map.put("om_code",  om_code);
		map.put("em_dty_code",  em_dty_code);
		emplManageList =  dao.selectEmpList(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(emplManageList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	@RequestMapping("/store/emplAutoComplate") 
	public ModelAndView emplAutoComplate(@RequestParam("keyword") String keyword
										,@RequestParam("se_empl_ty") String se_empl_ty
										,@RequestParam("bhf_code") String bhf_code
										) throws Exception {
		//System.out.println(Encoder.isoToUtf(keyword));
		//autoCompList =  dao.martAutoComplate(Encoder.isoToUtf(keyword));
		//System.out.println("se_empl_ty : " + se_empl_ty);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
//		map.put("keyword", Encoder.isoToUtf(keyword));
		map.put("se_empl_ty", se_empl_ty);
		map.put("bhf_code", bhf_code);
		autoCompList =  dao.emplAutoComplate(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(autoCompList);
		
		jsonObject.put("suggestions", jsonArray);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}
	
	/*매장현황 조회*********************************************************************/
	
	/**
	 * @메서드명: storeAllList
	 * @작성일: 2015. 10. 18
	 * @작성자: 최수영
	 * @설명: /store/storeAllList
	 */
	@RequestMapping("/store/storeAllList") 
	public ModelAndView storeAllList(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		String cm_code = (String) session.getAttribute("login_cp_cd");
		naviVo.setCm_code(cm_code);
		naviVo = dao.selectStoreAllCount(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		storeList =  dao.selectStoreAllList(naviVo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(storeList);
		//System.out.println(jsonArray);
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("navi",navi);
		jsonObject.put("firstNo", naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
    /**
     * @메소드명: m_storeAllList
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /store/m_storeAllList
     */
	@RequestMapping(value = "/store/m_storeAllList", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_storeAllList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/store/m_storeAllList");
        CommonUtil.setSessionVo(session);
        
		//Required Parameter
		int rowSize = reqMobileNaviVo.getRowSize();
		int currPg = reqMobileNaviVo.getCurrPg();
		System.out.println("rowSize=" + rowSize);
		System.out.println("currPg=" + currPg);
		
		//Check Required Parameter
		if (rowSize == 0 || currPg == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String om_code = reqMobileNaviVo.getOm_code();
		String em_nm = reqMobileNaviVo.getEm_nm();
		String sm_odr = reqMobileNaviVo.getSm_odr();
		String sm_cvscafe_at = reqMobileNaviVo.getSm_cvscafe_at();
		String sm_rprsntv_nm = reqMobileNaviVo.getSm_rprsntv_nm();
		String cg_nm = reqMobileNaviVo.getCg_nm();
		String me_nm = reqMobileNaviVo.getMe_nm();
		String sm_nm = reqMobileNaviVo.getSm_nm();
		System.out.println("om_code=" + om_code);
		System.out.println("em_nm=" + em_nm);
		System.out.println("sm_odr=" + sm_odr);
		System.out.println("sm_cvscafe_at=" + sm_cvscafe_at);
		System.out.println("sm_rprsntv_nm=" + sm_rprsntv_nm);
		System.out.println("cg_nm=" + cg_nm);
		System.out.println("me_nm=" + me_nm);
		System.out.println("sm_nm=" + sm_nm);
		
		Map<String, String> params = new HashMap<String, String>();
		String cm_code = (String) session.getAttribute("login_cp_cd");
		params.put("cm_code", cm_code);
		params.put("om_code", om_code);
		params.put("em_nm", em_nm);
		params.put("sm_odr", sm_odr);
		params.put("sm_cvscafe_at", sm_cvscafe_at);
		params.put("sm_rprsntv_nm", sm_rprsntv_nm);
		params.put("cg_nm", cg_nm);
		params.put("me_nm", me_nm);
		params.put("sm_nm", sm_nm);
		params.put("se_empl_ty", "");
		reqMobileNaviVo.setParams(params);
		
		reqMobileNaviVo = (MobileNaviVo) dao.selectStoreAllCount(reqMobileNaviVo);
		
		storeList =  dao.selectStoreAllList(reqMobileNaviVo);
		//System.out.println("storeList=" + storeList);
		
		if (storeList != null && storeList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_store = new JSONArray();

			//Response Body
			for (int i = 0; i < storeList.size(); i++) {
				StoreVo storeVo = storeList.get(i);
				if (storeVo.getSm_oper_at().equals("Y")) {//운영중인 매장인 경우
					JSONObject bodyVo = new JSONObject();
					bodyVo.put("sm_code", storeVo.getSm_code());
					bodyVo.put("sm_nm", storeVo.getSm_nm());
					bodyVo.put("em_nm", storeVo.getEm_nm());
					bodyVo.put("sm_odr_nm", storeVo.getSm_odr_nm());
					bodyVo.put("sm_tlphon", storeVo.getSm_tlphon());
					body_store.add(bodyVo);
				}
			}
			body.put("rowSize", rowSize);
			body.put("currPg", currPg);
			body.put("totRow", reqMobileNaviVo.getTotRow());
			body.put("store", body_store);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}	
	
	/**
	 * @메서드명: storeAllExcelList2
	 * @작성일: 2015. 10. 18
	 * @작성자: 최수영
	 * @설명: /store/excelExport2
	 */
	@RequestMapping("/store/excelExport2") 
	public ModelAndView storeAllExcelList2(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		storeList =  dao.selectStoreAllList(naviVo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(storeList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		//System.out.println(jsonObject.toString());
		mv.setViewName("/50/50-100_excel");
			
		return mv;
	}
	
	/**
	 * @메서드명: storeAllExcelList
	 * @작성일: 2015. 10. 18
	 * @작성자: 최수영
	 * @설명: /store/excelExport
	 */
	@RequestMapping("/store/excelExport") 
	public ModelAndView storeAllExcelList(StoreVo p_vo) throws Exception {
		String cm_code = (String) session.getAttribute("login_cp_cd");
		p_vo.setCm_code(cm_code);
		//System.out.println("**excel om_code : " + p_vo.getOm_code());
		
		storeList =  dao.selectStoreAllExcelList(p_vo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(storeList);
		
		jsonObject.put("result", jsonArray);
		
		//mv = new ModelAndView(new JSONView());
		mv = new ModelAndView();
		mv.addObject("ajax", jsonObject);
		//System.out.println(jsonObject.toString());
		mv.setViewName("/50/50-100_excel");
			
		return mv;
	}
	
	
	//위도경도 자동
	@RequestMapping("/store/juso") 
	public ModelAndView selectJuso(StoreVo p_vo) throws Exception {
		storeList =  dao.selectJuso();

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(storeList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		//System.out.println(jsonObject.toString());
			
		return mv;
	}

	@RequestMapping("/store/saveJuso")
	public ModelAndView saveJuso(StoreVo p_vo) throws Exception {
		//String sm_lo = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sm_code", p_vo.getSm_code());
		map.put("sm_la", p_vo.getSm_la());
		map.put("sm_lo", p_vo.getSm_lo());
		int resultCnt = dao.saveJuso(p_vo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	
	
}


