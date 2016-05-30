package com.gensolution.activity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gensolution.activity.dao.ActivityFixingDAO;
import com.gensolution.activity.vo.ActivityEmVo;
import com.gensolution.activity.vo.ActivityEvnMobileSaveVo;
import com.gensolution.activity.vo.ActivityFixingEvnVo;
import com.gensolution.activity.vo.ActivityFixingOddVo;
import com.gensolution.activity.vo.ActivityFixingTrtVo;
import com.gensolution.activity.vo.ActivityFixingWorkVo;
import com.gensolution.activity.vo.ActivityGridMobileVo;
import com.gensolution.activity.vo.ActivityGridVo;
import com.gensolution.activity.vo.ActivityOddMobileVo;
import com.gensolution.activity.vo.ActivityRndOddVo;
import com.gensolution.activity.vo.ActivityRndTrtVo;
import com.gensolution.activity.vo.ActivitySaveVo;
import com.gensolution.activity.vo.ActivityTrtMobileVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.ExcelManager;
//import com.vertexid.utill.CryptoUtil;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.ExcelVo;
import com.vertexid.vo.NaviVo;

@Controller
public class ActivityFixingController {
	private ModelAndView mv;			//모델엔뷰
	
	private JSONObject jObj;				//json obj
	private JSONObject jVo;
	private JSONObject response;
	private JSONArray jArr;				//json 배열
	
	
	private ActivityEmVo activityFixingVo;
	private ActivityFixingWorkVo workVo;
	private ActivityOddMobileVo oddVo;
	private ActivityTrtMobileVo fixTrtMVo;
	
	@Autowired
	private AttachManager attachManager;
	
	private List<ActivityEmVo> fixingVoList;
	private List<ActivityFixingOddVo> fixingOddVoList;
	private List<ActivityFixingEvnVo> fixingEvnVoList;
	private List<ActivityFixingTrtVo> fixingTrtVoList;
	
	@Autowired
	private ExcelManager em;						// 파일관리자
	
	@Autowired
	private ActivityFixingDAO dao;
	
	@Autowired
	private HttpSession session;
//	
//	@Autowired
//	private AttachManager am;						// 파일관리자
	
	@RequestMapping("/10/10-200") 
	public String codePg(){
		return "10/10-200";
	}
	
	
	
	//근무계획 아코디언
	@RequestMapping("/fixing/workSave") 
	public ModelAndView fixingWorkSave(@ModelAttribute("vo") ActivityFixingWorkVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/fixing/workSave]  : 근무계획 저장 em_no : "+vo.getEm_no() +",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		String cm_code = (String)session.getAttribute("login_cp_cd");
		String om_code = (String)session.getAttribute("login_bhf_cd");
		String login_no = (String)session.getAttribute("login_no");
		vo.setCm_code(cm_code);
		vo.setOm_code(om_code);
		vo.setRegist_man(login_no);
		vo.setUpdt_man(login_no);
		int cnt =  dao.fixingWorkSave(vo);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt );
		return mv;
	}
	
	//work 아코디언
	@RequestMapping("/fixing/m_workSave") 
	public ModelAndView m_fixingWorkSave(@ModelAttribute("vo") ActivityFixingWorkVo vo) throws Exception{ 
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_workSave]  : 근무계획 저장 em_no : "+vo.getEm_no() +",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String dfwp_innb = vo.getDfwp_innb();
		String em_no = vo.getEm_no();
		String cm_code = vo.getCm_code();
		String om_code = vo.getOm_code();
		String sm_code = vo.getSm_code();
		String base_de = vo.getBase_de();
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(cm_code) || StringCheck.isNull(om_code)
				||StringCheck.isNull(sm_code) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}

		vo.setDfwp_innb(dfwp_innb==""?"0":dfwp_innb);
		vo.setRegist_man(em_no);
		vo.setUpdt_man(em_no);
		
		int cnt =  dao.m_fixingWorkSave(vo);
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	//POG 아코디언
	@RequestMapping("/fixing/poglist") 
	public ModelAndView fixingPoglist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/fixing/poglist]  : POG 및 현제 진열줄수 조회  base_de : "+vo.getBase_de());
		ActivityGridVo result =  dao.optionPoglist(vo);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
	//모바일 POG아코디언
	@RequestMapping("/fixing/m_poglist") 
	public ModelAndView fixingMPdlist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_poglist]  : POG 및 현제 진열줄수 조회 em_no : "+vo.getEm_no() +",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getBase_de())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		response =  dao.fixingMPoglist(vo);
		return CommonUtil.setModelAndView(response);
	}
	
	
	@RequestMapping("/fixing/m_pogsave") 
	public ModelAndView fixingMobilePogSave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_pogsave]  : POG 및 현제 진열줄수 저장 em_no : "+vo.getEm_no() +", base_de : "+vo.getBase_de());
		CommonUtil.setSessionVo(session);
		
		if (  StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| StringCheck.isNull(vo.getParamArr1())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt =  dao.fixingMobilePogSave(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	
	//Big 아코디언
	@RequestMapping("/fixing/biglist") 
	public ModelAndView fixingBiglist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/fixing/biglist]  : 보조진열 현황 조회   base_de : "+vo.getBase_de());
		
		ActivityGridVo result =  dao.optionBiglist(vo);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
	//모바일 Big 아코디언
	@RequestMapping("/fixing/m_biglist") 
	public ModelAndView fixingMobileBiglist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_biglist]  : 보조진열 현황 조회   base_de : "+vo.getBase_de());
		CommonUtil.setSessionVo(session);
		
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getBase_de())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		ActivityGridMobileVo bigVo =  dao.fixingMobileBiglist(vo);
		
		
		return CommonUtil.makeMobileRespons(bigVo,"big_list");
	}
	
	@RequestMapping("/fixing/m_bigsave") 
	public ModelAndView fixingMobileBigSave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_bigsave]  : 보조진열 저장 em_no : "+vo.getEm_no() +",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		if ( StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| (StringCheck.isNull(vo.getParamArr1()) && StringCheck.isNull(vo.getParamArr2()))
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt =  dao.fixingMobileBigSave(vo);
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	
	//Pd아코디언
	@RequestMapping("/fixing/pdlist") 
	public ModelAndView fixingPdlist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/fixing/pdlist]  : PD 매대 조회  base_de : "+vo.getBase_de());
		ActivityGridVo result =  dao.optionPdlist(vo);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(result));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
	//모바일Pd아코디언
	@RequestMapping("/fixing/m_pdlist") 
	public ModelAndView fixingMobilePdlist(@ModelAttribute("vo") ActivityGridVo vo ) throws Exception{
		
		CommonUtil.setSessionVo(session);
		
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getBase_de())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		ActivityGridMobileVo pdVo =  dao.fixingMobilePdlist(vo);
		
		return CommonUtil.makeMobileRespons(pdVo,"pd_list");
	}
	
	//모바일Pd매대 현황 저징
	@RequestMapping("/fixing/m_pdsave") 
	public ModelAndView fixingMobilePdSave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_pdsave]  : PD매대 저장 em_no : "+vo.getEm_no() +",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		if (  StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				|| (StringCheck.isNull(vo.getParamArr1()) && StringCheck.isNull(vo.getParamArr2()))
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt =  dao.fixingMobilePdSave(vo);
		
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}

	//odd 아코디언
	@RequestMapping("/fixing/oddlist") 
	public ModelAndView fixingOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		jObj = new JSONObject();
		fixingOddVoList =  dao.optionOddlist(map);
		jArr = JSONArray.fromObject(fixingOddVoList);
		jObj.put("oddList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	//모바일 - odd 아코디언
	@RequestMapping("/fixing/m_oddlist") 
	public ModelAndView m_fixingOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_oddlist]  : 시황및 특이사항조회 em_no : "+em_no+",  base_de : "+base_de);
		CommonUtil.setSessionVo(session);
		
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		oddVo =  dao.m_optionOddlist(map);
		
		return CommonUtil.makeMobileRespons(oddVo,"body_fixing");
	}
	
	//모바일 - odd 아코디언 저장
	@RequestMapping("/fixing/m_oddsave") 
	public ModelAndView m_fixingOddsave(@ModelAttribute("vo") ActivityFixingOddVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_oddsave]  : 시황 저장 em_no : "+vo.getEm_no() +", base_de : "+vo.getBase_de());
		CommonUtil.setSessionVo(session);
		
		String em_no = vo.getEm_no();
		String base_de = vo.getBase_de();
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = 0;
		cnt =  dao.m_optionOddsave(vo);
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
		
	}
	
	//모바일 - odd 아코디언 삭제
	@RequestMapping("/fixing/m_odddel") 
	public ModelAndView fixingMobileOddDel(@RequestParam("dfop_innb") String dfop_innb) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_odddel]  : 시황 삭제 dfop_innb : "+dfop_innb);
		CommonUtil.setSessionVo(session);
		
		Map<String,String> map =  new HashMap<String, String>();
		map.put("dfop_innb", dfop_innb);
		
		//Check Required Parameter
		if (StringCheck.isNull(dfop_innb)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = 0;
		cnt =  dao.fixingMobileOddDel(map);
		
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
		
	}
	
	//evn 아코디언
	@RequestMapping("/fixing/evnlist") 
	public ModelAndView fixingEvnlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		jObj = new JSONObject();
		//해더정보
		fixingEvnVoList =  dao.optionEvnColumnlist(map);
		jArr = JSONArray.fromObject(fixingEvnVoList);
		jObj.put("evnColumnList", jArr);
		
		
		//로우정보
		fixingEvnVoList =  dao.optionEvnlist(map);
		jArr = JSONArray.fromObject(fixingEvnVoList);
		jObj.put("evnList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj );
		return mv;
	}
	//모바일evn 행사매대
	@RequestMapping("/fixing/m_evnlist") 
	public ModelAndView fixingMobileEvnlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
		CommonUtil.setSessionVo(session);
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);		
		
		CommonUtil.setSessionVo(session);
		if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		return dao.fixingMobileEvnlist(map);
	}
	//모바일evn 행사매대
	@RequestMapping("/fixing/m_evnsave") 
	public ModelAndView fixingMobileEvnSave(@ModelAttribute("vo") ActivityEvnMobileSaveVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_evnsave]  : 행사매대 저장 em_no : "+vo.getEm_no() +", base_de : "+vo.getBase_de() +", main_innb"+vo.getMain_innb());
		CommonUtil.setSessionVo(session);
		if (  StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())	
				|| StringCheck.isNull(vo.getOi_code())
				|| StringCheck.isNull(vo.getOi_code_nm())
				|| StringCheck.isNull(vo.getDfes_oi_code())				
				|| StringCheck.isNull(vo.getDfes_oi_code_nm())				
				|| (vo.getFile() == null || vo.getFile().getSize() == 0) 				
				) {

			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		int cnt =  dao.fixingMobileEvnSave(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	//모바일evn 행사매대
	@RequestMapping("/fixing/m_evndel") 
	public ModelAndView fixingMobileEvnDel(@ModelAttribute("vo") ActivityEvnMobileSaveVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_evndel]  : 행사매대 삭제 em_no : "+vo.getEm_no() +",  dfesi_innb : "+vo.getDfesi_innb());
		CommonUtil.setSessionVo(session);
		if (  StringCheck.isNull(vo.getDfesi_innb())
				|| StringCheck.isNull(vo.getEm_no())	 				
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		int cnt =  dao.fixingMobileEvnDel(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	//work 아코디언
	@RequestMapping("/fixing/worklist") 
	public ModelAndView fixingWorklist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("login_no", login_no);
		
		workVo =  dao.fixingWorklist(map);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(workVo));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jVo );
		return mv;
	}
	
	//work 아코디언
	@RequestMapping("/fixing/m_worklist") 
	public ModelAndView m_fixingWorklist(@ModelAttribute("vo") ActivityFixingWorkVo reqWorkVo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_worklist]  : 근무계획 조회 em_no : "+reqWorkVo.getEm_no() +",  base_de : "+reqWorkVo.getBase_de());
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String em_no = reqWorkVo.getEm_no();
		String sm_code = reqWorkVo.getSm_code();
		String base_de = reqWorkVo.getBase_de();
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(sm_code) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		 
		//Optional Parameter
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("login_no", em_no);
		map.put("sm_code", sm_code);
		
		workVo =  dao.m_fixingWorklist(map);
		
		return CommonUtil.makeMobileRespons(workVo,"body_fixing");
	}
	
	
	
	@RequestMapping("/fixing/trtlist") 
	public ModelAndView fixingTrtlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		map.put("login_no", login_no);
		
		fixingTrtVoList =  dao.fixingTrtlist(map);
		jArr = JSONArray.fromObject(fixingTrtVoList);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jArr );
		return mv;
	}
	
	@RequestMapping("/fixing/m_trtlist") 
	public ModelAndView m_fixingTrtlist(@ModelAttribute("vo") ActivityFixingTrtVo vo) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/fixing/m_trtlist]  : 취급품목 조회 em_no : "+vo.getEm_no() +",  base_de : "+vo.getBase_de()+",  sm_code : "+vo.getSm_code());
		CommonUtil.setSessionVo(session);
		
		//Check Required Parameter
		if (StringCheck.isNull(vo.getEm_no()) || StringCheck.isNull(vo.getSm_code()) || StringCheck.isNull(vo.getBase_de())) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		String login_no = (String)session.getAttribute("login_no");
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", vo.getBase_de());
		map.put("em_no", vo.getEm_no());
		map.put("sm_code", vo.getSm_code());
		map.put("login_no", login_no);
		
		fixTrtMVo =  dao.m_fixingTrtlist(map);
		
		return CommonUtil.makeMobileRespons(fixTrtMVo,"trt_list");
	}
	
	@RequestMapping("/fixing/m_trtsave") 
	public ModelAndView m_fixingTrtsave(@ModelAttribute("vo") ActivitySaveVo vo) throws Exception{
		CommonUtil.setSessionVo(session);
		if ( StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getSm_code())
				|| StringCheck.isNull(vo.getEm_no())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		int cnt =  dao.m_fixingTrtsave(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	@RequestMapping("/fixing/getActivityFixingView") 
	public ModelAndView getActivityFixingView(@ModelAttribute("vo") ActivityEmVo searchActivityFixingVo) throws Exception{
		activityFixingVo = dao.getActivityFixingView(searchActivityFixingVo);
		
		jObj = new JSONObject();
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(activityFixingVo));
		jObj.put("activityFixingVo", jVo);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/fixing/getActivityFixingList") 
	public ModelAndView getActivityFixingList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		naviVo = dao.getActivityFixingListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		String login_no = (String)session.getAttribute("login_no"); //권한코드
		naviVo.setAuth_flag(auth_flag);
		naviVo.setEm_no(login_no);
		fixingVoList = dao.getActivityFixingList(naviVo);
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(fixingVoList);
		jObj.put("activityFixingList", jArr);
		jObj.put("navi",navi);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/fixing/saveActivityFixingEmpAiNo") 
	public ModelAndView saveActivityFixingEmpAiNo(@ModelAttribute("vo") ActivityEmVo saveActivityFixingVo) throws Exception{
		String login_no = (String) session.getAttribute("login_no");//로그인 유저의 사원번호
		saveActivityFixingVo.setRegist_man(login_no);
		saveActivityFixingVo.setUpdt_man(login_no);
		String result = dao.saveActivityFixingEmpAmNo(saveActivityFixingVo);
		
		jObj = new JSONObject();
		jObj.put("result", result);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/fixing/excel") 
	@ResponseBody
	public FileSystemResource rndExcelDownload(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ,HttpServletResponse httpresponse) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Web:/fixing/excel]  : 고정 엑셀 다운");
		
		Map<String,String> map =  new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		
		List<List<Map<String, String>>> columnLists = new ArrayList<List<Map<String,String>>>(); 
		List<List<Map<String, String>>> bodyLists  = new ArrayList<List<Map<String,String>>>();
		
		ActivityGridVo vo = new ActivityGridVo();
		vo.setBase_de(base_de);
		vo.setEm_no(em_no);
		
		
		String[] sheets = {"POG및현재진열줄수","보조진열현황","PD매대현황","취급품목현황"};
//		String[] sheets = {"현재진열줄수","보조진열현황","PD매대현황"};
		
		String[] matters = {"","","",""};
		
		ExcelVo excelVo =  new ExcelVo();
		ActivityGridVo result = new ActivityGridVo();
		excelVo.setSheetNames(sheets);
		
		
//		List<ActivityFixingEvnVo> fixingEvnColumnList =  dao.optionEvnColumnlist(map);
//		List<ActivityFixingEvnVo> fixingEvnBodyList =  dao.optionEvnlist(map);
		
		
		result =  dao.optionPoglist(vo);
		columnLists.add(result.getColumnArr());
		bodyLists.add(result.getBodyArr());
		matters[0] = result.getPartclr_matter();
		
		
		result =  dao.optionBiglist(vo);
		columnLists.add(result.getColumnArr());
		bodyLists.add(result.getBodyArr());
		matters[1] = result.getPartclr_matter();
		
		result =  dao.optionPdlist(vo);
		columnLists.add(result.getColumnArr());
		bodyLists.add(result.getBodyArr());
		matters[2] = result.getPartclr_matter();
		
		List<ActivityFixingTrtVo> trtList=  dao.fixingTrtlist(map);
		matters[3] = result.getPartclr_matter();
		
//		List<ActivityRndOddVo> oddList= dao.rndOddlist(map);
		
		
//		excelVo.setFixingEvnColumnList(fixingEvnColumnList);
//		excelVo.setFixingEvnColumnList(fixingEvnBodyList);
		
		excelVo.setBodyLists(bodyLists);
		excelVo.setColumnLists(columnLists);
		excelVo.setFixingTrtList(trtList);
//		excelVo.setOddList(oddList);
		excelVo.setFileName("고정MD업무일지.xlsx");
		excelVo.setMatters(matters);
		
		return em.downloadFileFixing(excelVo,httpresponse);
	}
	/////////////////////동서식품
	
	//odd 아코디언
		@RequestMapping("/ds_fixing/oddlist") 
		public ModelAndView ds_fixingOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
			CommonUtil.setSessionVo(session);
			System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/ds_fixing/m_oddlist]  : 시황및 특이사항조회 em_no : "+em_no+",  base_de : "+base_de);
			Map<String,String> map =  new HashMap<String, String>();
			map.put("base_de", base_de);
			map.put("em_no", em_no);
			jObj = new JSONObject();
			fixingOddVoList =  dao.optionOddlist(map);
			jArr = JSONArray.fromObject(fixingOddVoList);
			jObj.put("oddList", jArr);
			
			mv = new ModelAndView(new JSONView());
			mv.addObject("ajax", jObj );
			return mv;
		}
		//모바일 - odd 아코디언
		@RequestMapping("/ds_fixing/m_oddlist") 
		public ModelAndView ds_m_fixingOddlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
			System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/ds_fixing/m_oddlist]  : 시황및 특이사항조회 em_no : "+em_no+",  base_de : "+base_de);
			CommonUtil.setSessionVo(session);
			
			Map<String,String> map =  new HashMap<String, String>();
			map.put("base_de", base_de);
			map.put("em_no", em_no);
			
			//Check Required Parameter
			if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
				response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
				return CommonUtil.setModelAndView(response);
			}
			oddVo =  dao.m_optionOddlist(map);
			
			return CommonUtil.makeMobileRespons(oddVo,"body_fixing");
		}
		
		//evn 아코디언
		@RequestMapping("/ds_fixing/evnlist") 
		public ModelAndView ds_fixingEvnlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
			CommonUtil.setSessionVo(session);
			System.out.println("["+CommonUtil.getCurrentDateTime() + "WEB :/ds_fixing/evnlist]  : 동서식품 행사매대 조회 em_no : "+em_no+",  base_de : "+base_de);
			Map<String,String> map =  new HashMap<String, String>();
			map.put("base_de", base_de);
			map.put("em_no", em_no);
			jObj = new JSONObject();
			//해더정보
			fixingEvnVoList =  dao.optionEvnColumnlist(map);
			jArr = JSONArray.fromObject(fixingEvnVoList);
			jObj.put("evnColumnList", jArr);
			
			
			//로우정보
			fixingEvnVoList =  dao.optionEvnlist(map);
			jArr = JSONArray.fromObject(fixingEvnVoList);
			jObj.put("evnList", jArr);
			
			mv = new ModelAndView(new JSONView());
			mv.addObject("ajax", jObj );
			return mv;
		}
		//모바일evn 행사매대
		@RequestMapping("/ds_fixing/m_evnlist") 
		public ModelAndView ds_fixingMobileEvnlist(@RequestParam("base_de") String base_de, @RequestParam("em_no") String em_no ) throws Exception{
			System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/ds_fixing/m_evnlist]  :동서식품 행사매대  조회 em_no : "+em_no+",  base_de : "+base_de);
			CommonUtil.setSessionVo(session);
			Map<String,String> map =  new HashMap<String, String>();
			map.put("base_de", base_de);
			map.put("em_no", em_no);		
			
			CommonUtil.setSessionVo(session);
			if (StringCheck.isNull(em_no) || StringCheck.isNull(base_de)) {
				response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
				return CommonUtil.setModelAndView(response);
			}
			
			return dao.fixingMobileEvnlist(map);
		}
}
