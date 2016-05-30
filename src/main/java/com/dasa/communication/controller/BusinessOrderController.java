package com.dasa.communication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.communication.dao.BusinessOrderDAO;
import com.dasa.communication.vo.BusinessOrderVo;
import com.dasa.communication.vo.BusinessVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.Encoder;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.AttachVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.MobileNaviVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
public class BusinessOrderController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private BusinessOrderDAO dao;
	@Autowired
	private AttachManager am;
	
	private ModelAndView mv;
	private JSONObject jo;
	private JSONObject jvo;
	private JSONArray  ja;
	private JSONObject jsonObject;
	private JSONArray  jsonArray;
	private JSONObject response;
	
	private BusinessVo businessVo;
	private BusinessOrderVo businessOrderVo; 
	private BusinessOrderVo businessInserType;
	private List<AttachVo> attachVoList;
	private List<KeyValueVo> autoCompList;
	private List<KeyValueVo> businessOmList;
	private List<KeyValueVo> businessSmList;
	private List<KeyValueVo> businessEmList;
	private List<KeyValueVo> businessTmList; 
	private List<KeyValueVo> bizFixRoundList;
	private List<OrganizationVo> organizationList;
	private List<BusinessOrderVo> businessVoList;
	private List<BusinessOrderVo> businessVoPopupList;
	
	@RequestMapping("/70/70-300") 
	public String codePg() throws Exception {
		return "70/70-300";
	}
	
	@RequestMapping("/business/list") 
	public ModelAndView businessList(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		//int auth_flag = (Integer)session.getAttribute("auth_flag"); //권한코드
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		
		naviVo.setAuth_flag(auth_flag);
		naviVo = dao.businessOrderListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		businessVoList =  dao.businessOrderList(naviVo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(businessVoList);
		jo.put("navi", navi);
		jo.put("businessVoList", ja);
		jo.put("firstNo", naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/business/popupList")
	//public ModelAndView businessPopupList(@RequestParam("bmInnb") String bmInnb) throws Exception {
	public ModelAndView businessPopupList(@ModelAttribute("vo") NaviVo naviVo) throws Exception {	
		
		naviVo = dao.businessPopupListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		businessVoPopupList =  dao.businessPopupList(naviVo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(businessVoPopupList);
 		jo.put("navi", navi);
 		jo.put("businessVoPopupList", ja);
 		jo.put("firstNo", naviVo.getFirstRowNo());
 		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
    /**
     * @메소드명: m_businessList
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /business/m_businessList
     */
	@RequestMapping(value = "/business/m_businessList", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_businessList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/business/m_businessList");
        CommonUtil.setSessionVo(session);
        
		//Required Parameter
        int auth_flag = reqMobileNaviVo.getAuth_flag();
        String cm_code = reqMobileNaviVo.getCm_code();
        String em_no = reqMobileNaviVo.getEm_no();
		int rowSize = reqMobileNaviVo.getRowSize();
		int currPg = reqMobileNaviVo.getCurrPg();
		//System.out.println("auth_flag=" + auth_flag);
		//System.out.println("cm_code=" + cm_code);
		//System.out.println("em_no=" + em_no);
		//System.out.println("rowSize=" + rowSize);
		//System.out.println("currPg=" + currPg);
		
		//Check Required Parameter
		if (auth_flag < 0 || StringCheck.isNull(cm_code) || StringCheck.isNull(em_no) || rowSize == 0 || currPg == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String search_date_from = StringCheck.isNull(reqMobileNaviVo.getSearch_date_from(), "");
		String search_date_to = StringCheck.isNull(reqMobileNaviVo.getSearch_date_to(), "");
		String om_code = StringCheck.isNull(reqMobileNaviVo.getOm_code(), "");
		String em_nm = StringCheck.isNull(reqMobileNaviVo.getEm_nm(), "");
		//System.out.println("search_date_from=" + search_date_from);
		//System.out.println("search_date_to=" + search_date_to);
		//System.out.println("om_code=" + om_code);
		//System.out.println("em_nm=" + em_nm);
		
		search_date_from = search_date_from.replaceAll("-", "");
		search_date_to = search_date_to.replaceAll("-", "");
		
		if (auth_flag > 1) {
			//팀장이나 여사원인 경우, 지점코드를 무시
			om_code = "";
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("auth_flag", String.valueOf(auth_flag));//권한코드
		params.put("cm_code", cm_code);//회사코드
		params.put("em_no", em_no);//사원번호
		params.put("jijumOmCode", om_code);//지점코드
		params.put("jijumWord", "");//매장명
		params.put("em_nm", em_nm);//사원명
		params.put("key", "");//검색구분
		params.put("word", "");
		params.put("search_date_from", search_date_from);
		params.put("search_date_to", search_date_to);
		params.put("type", "MOBILE");
		params.put("team_val", "");
		params.put("teamWord", "");
		reqMobileNaviVo.setParams(params);
		
        reqMobileNaviVo = (MobileNaviVo) dao.businessOrderListCnt(reqMobileNaviVo);
        
        List<BusinessOrderVo> businessOrderList = dao.businessOrderList(reqMobileNaviVo);
        //System.out.println("businessOrderList=" + businessOrderList);
        
		if (businessOrderList != null && businessOrderList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_business = new JSONArray();

			//Response Body
			for (int i = 0; i < businessOrderList.size(); i++) {
				BusinessOrderVo businessOrderVo = businessOrderList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("bm_innb", businessOrderVo.getBm_innb());
				bodyVo.put("bm_sj", businessOrderVo.getBm_sj());
				bodyVo.put("regist_man", businessOrderVo.getRegist_man());
				bodyVo.put("regist_de", businessOrderVo.getRegist_de());
				bodyVo.put("reply_yn", businessOrderVo.getReceive_yn());
				body_business.add(bodyVo);
			}
			body.put("rowSize", rowSize);
			body.put("currPg", currPg);
			body.put("totRow", reqMobileNaviVo.getTotRow());
			body.put("business", body_business);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}	
	
	@RequestMapping("/businessfile/list")
	public ModelAndView organizationListByCp(@RequestParam("cm_code") String cm_code) throws Exception {
		organizationList = dao.selectListByCp(cm_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	@RequestMapping("/business/martList") 
	public ModelAndView martAutoComplate(@RequestParam("keyword") String keyword,  @RequestParam("om_code") String om_code, @RequestParam("cm_code") String cm_code) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		String[] omCodeArray = om_code.split(",");
		String auth_flag = (String)session.getAttribute("auth_flag"); //권한코드
		List<String> omCodeList = new ArrayList<String>();
		
		for(int i=0; i<omCodeArray.length; i++){
			omCodeList.add(omCodeArray[i]);
		}
		
		map.put("keyword", keyword);
		map.put("omCodeList", omCodeList);
		map.put("auth_flag", auth_flag);
		map.put("cm_code", cm_code);
		 
		autoCompList =  dao.martAutoComplate(map);
		 
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		jo.put("suggestions", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/business/martList_cp") 
	public ModelAndView martAutoComplate_cp(@RequestParam("keyword") String keyword, @RequestParam("om_code") String om_code) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		String[] omCodeArray = om_code.split(",");
		List<String> omCodeList = new ArrayList<String>();
		
		for(int i=0; i<omCodeArray.length; i++){
			omCodeList.add(omCodeArray[i]);
		}
		
		map.put("keyword", keyword);
//		map.put("keyword", Encoder.isoToUtf(keyword));
		map.put("omCodeList", omCodeList);
		autoCompList =  dao.martAutoComplate_cp(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		jo.put("suggestions", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/business/empList") 
	public ModelAndView emplAutoComplate(@RequestParam("keyword") String keyword,  @RequestParam("om_code") String om_code, @RequestParam("cm_code") String cm_code, @RequestParam("fix_gbn") String fix_gbn) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		String auth_flag = (String)session.getAttribute("auth_flag"); //권한코드
		List<String> omCodeList = new ArrayList<String>();
		String[] omCodeArray = om_code.split(",");
		String auth_Code = "";
		
		for(int i=0; i<omCodeArray.length; i++){
			omCodeList.add(omCodeArray[i]);
		}
		//0000000009 관리자, 0000000008 팀장, 0000000007 순회, 0000000006 고정  || 1 관리자, 2 팀장, 3 고정, 4 순회 
		if(auth_flag.equals("1")) {
			auth_Code = "0000000009";	
		}else if(auth_flag.equals("2")) {
			auth_Code = "0000000008";
		}else if(auth_flag.equals("4")) {
			auth_Code = "0000000007";
		}else if(auth_flag.equals("3")) {
			auth_Code = "0000000006";
		}
		
		/*String[] smCodeArray;
		List<String> smCodeList = new ArrayList<String>();
		if(sm_code.length() > 0){
			smCodeArray = sm_code.split(",");
			for(int i=0; i<smCodeArray.length; i++){
				smCodeList.add(smCodeArray[i]);
			}
			System.out.println("sm_code ==> " + sm_code);
			System.out.println("smCodeArray ==> " + smCodeArray.length);
		} 
		 for(int i=0; i<smCodeArray.length; i++){
			smCodeList.add(smCodeArray[i]);
		} 
		map.put("smCodeList", smCodeList); */
		
		
		map.put("keyword", keyword);
		map.put("omCodeList", omCodeList);
		map.put("auth_flag", auth_Code);
		map.put("cm_code", cm_code);
		map.put("fix_gbn", fix_gbn);
		
		//autoCompList =  dao.emplAutoComplate(Encoder.isoToUtf(keyword));
		autoCompList =  dao.emplAutoComplate(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		
		jo.put("suggestions", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/business/save1") 
	public ModelAndView noticeSave1(@ModelAttribute("vo") BusinessOrderVo businessOrderVo) throws Exception {
		
		String login_no  = (String)session.getAttribute("login_no");
		String cm_code   = (String) session.getAttribute("login_cp_cd");
		String auth_flag = (String)session.getAttribute("auth_flag"); //권한코드
		String auth_Code = "";
		
		if(auth_flag.equals("1")) {
			auth_Code = "0000000009";	
		}else if(auth_flag.equals("2")) {
			auth_Code = "0000000008";
		}else if(auth_flag.equals("4")) {
			auth_Code = "0000000007";
		}else if(auth_flag.equals("3")) {
			auth_Code = "0000000006";
		}
		
		businessOrderVo.setRegist_man(login_no);
		businessOrderVo.setCm_code(cm_code);
		businessOrderVo.setAuth_Code(auth_Code);
		
		String bm_innb =  dao.businessSave(businessOrderVo);
		if(bm_innb == ""){
			return null;
		}
//		System.out.println("bm_innb는?? " + bm_innb);
		int cnt = 0;
		if(!bm_innb.equals("") ){
			cnt =  dao.businessPush(bm_innb);
		}
		 
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", bm_innb);
		return mv;
	}
	
	@RequestMapping("/business/save2") 
	public ModelAndView noticeSave2(@ModelAttribute("vo") BusinessOrderVo businessOrderVo) throws Exception {
		
		String login_no = (String)session.getAttribute("login_no");
		String cm_code = (String) session.getAttribute("login_cp_cd");
		
		businessOrderVo.setRegist_man(login_no);
		businessOrderVo.setCm_code(cm_code);
		
		String bm_innb =  dao.businessSave(businessOrderVo);
		
		int cnt = 0;
		if(!bm_innb.equals("") ){
			cnt =  dao.businessPush(bm_innb);
		}
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", bm_innb);
		return mv;
	}
	
	@RequestMapping("/business/push") 
	public ModelAndView businessPush(@RequestParam("bm_innb") String bm_innb, @RequestParam("em_no") String em_no) throws Exception {
        
		int cnt =  dao.businessPush2(bm_innb, em_no);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		return mv;
	}
	
	@RequestMapping("/business/view") 
	public ModelAndView noticeView(@RequestParam("bmInnb") String bmInnb) throws Exception {
		
		businessOrderVo = dao.businessView(bmInnb);
		
		businessOmList = dao.businessOmList(bmInnb);
		//businessSmList = dao.businessSmList(bmInnb);
		businessEmList = dao.businessEmList(bmInnb);
		businessTmList = dao.businessTmList(bmInnb);
		bizFixRoundList = dao.bizFixRoundList(bmInnb);
		//businessInserType = dao.businessInserType(bmInnb);
		
		attachVoList = am.attachList(businessOrderVo.getAm_no());
		jo = new JSONObject();
		
		jvo = JSONObject.fromObject(JSONSerializer.toJSON(businessOrderVo));
		jo.put("businessOrderVo", jvo);

		ja = JSONArray.fromObject(businessOmList);
		jo.put("businessOmList", ja);
 
		//ja = JSONArray.fromObject(businessSmList);
		//jo.put("businessSmList", ja);
		
		ja = JSONArray.fromObject(businessEmList);
		jo.put("businessEmList", ja);
		
		ja = JSONArray.fromObject(businessTmList);
		jo.put("businessTmList", ja);
 
		ja = JSONArray.fromObject(bizFixRoundList);
		jo.put("bizFixRoundList", ja);
		
		ja = JSONArray.fromObject(attachVoList);
		jo.put("attachVoList", ja);
		 
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
    /**
     * @메소드명: m_businessReceiverView
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /business/m_businessReceiverView
     */
	@RequestMapping(value = "/business/m_businessReceiverView", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_businessReceiverView(@ModelAttribute("vo") BusinessOrderVo reqBusinessOrderVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/business/m_businessReceiverView");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
        int auth_flag = reqBusinessOrderVo.getAuth_flag();
        String em_no = reqBusinessOrderVo.getEm_no();
        String bm_innb = reqBusinessOrderVo.getBm_innb();
        //System.out.println("auth_flag=" + auth_flag);
        //System.out.println("em_no=" + em_no);
        //System.out.println("bm_innb=" + bm_innb);
		
		//Check Required Parameter
		if (auth_flag < 0 || StringCheck.isNull(em_no) || StringCheck.isNull(bm_innb)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
        
		BusinessOrderVo resBusinessOrderVo = dao.businessReceiverView(reqBusinessOrderVo);
		//System.out.println("resBusinessOrderVo=" + resBusinessOrderVo);
		
		if (resBusinessOrderVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_business = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("bm_innb", resBusinessOrderVo.getBm_innb());
			bodyVo.put("em_no", resBusinessOrderVo.getEm_no());
			bodyVo.put("bm_sj", resBusinessOrderVo.getBm_sj());
			bodyVo.put("bm_cn", resBusinessOrderVo.getBm_cn());
			bodyVo.put("regist_man", resBusinessOrderVo.getRegist_man());
			bodyVo.put("regist_de", resBusinessOrderVo.getRegist_de());
			bodyVo.put("am_no", resBusinessOrderVo.getAm_no());
			bodyVo.put("br_answer", resBusinessOrderVo.getBr_answer());
			bodyVo.put("br_receive_de", resBusinessOrderVo.getBr_receive_date());
			body_business.add(bodyVo);
			body.put("business", body_business);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}
	
    /**
     * @메소드명: m_businessReceiverReply
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /business/m_businessReceiverReply
     */
	@RequestMapping(value = "/business/m_businessReceiverReply", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_businessReceiverReply(@ModelAttribute("vo") BusinessOrderVo reqBusinessOrderVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/business/m_businessReceiverReply");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String bm_innb = reqBusinessOrderVo.getBm_innb();
		String em_no = reqBusinessOrderVo.getEm_no();
		String br_answer = reqBusinessOrderVo.getBr_answer();
		//System.out.println("bm_innb=" + bm_innb);
		//System.out.println("em_no=" + em_no);
		//System.out.println("br_answer=" + br_answer);
		
		//Check Required Parameter
		if (StringCheck.isNull(bm_innb) || StringCheck.isNull(em_no) || StringCheck.isNull(br_answer)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int result = dao.businessReceiverReply(reqBusinessOrderVo);
		
		if (result > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_regist_at = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("regist_at", "Y");
			body_regist_at.add(bodyVo);
			body.put("regist_at", body_regist_at);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
		
	}
	
	@RequestMapping("/business/popupDelete")
	public ModelAndView businessPopupDelete(@RequestParam("bmInnb") String bmInnb, @RequestParam("authCode") String authCode) throws Exception {
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		int auth_Code = 0;
		
		if(authCode.equals("0000000009")) {
			auth_Code = 1;	
		}else if(authCode.equals("0000000008")) {
			auth_Code = 2;
		}else if(authCode.equals("0000000007")) {
			auth_Code = 4;
		}else if(authCode.equals("0000000006")) {
			auth_Code = 3;
		}
		
		if(auth_flag > auth_Code) {
			return null;
		}
		
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bmInnb", bmInnb);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);
 		
		int resultCnt =  dao.businessPopupDelete(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	@RequestMapping("/businessfile/save")
	public ModelAndView noticeFileSave(@RequestParam("files") MultipartFile files[] ,@RequestParam("bmInnb") String bmInnb,@RequestParam("amNo") String amNo) throws Exception {
		int seq = am.updateFile(files,StringCheck.nullToZero(amNo));
		
		seq = dao.noticeFileSave(bmInnb ,seq);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", seq);
		return mv;
	}
	
	@RequestMapping("/business/modify") 
	public ModelAndView noticeView(@RequestParam("bmInnb") String bmInnb, @RequestParam("authCode") String authCode) throws Exception {
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		int auth_Code = 0;
		
		if(authCode.equals("0000000009")) {
			auth_Code = 1;	
		}else if(authCode.equals("0000000008")) {
			auth_Code = 2;
		}else if(authCode.equals("0000000007")) {
			auth_Code = 4;
		}else if(authCode.equals("0000000006")) {
			auth_Code = 3;
		}
		
		if(auth_flag > auth_Code) {
			return null;
		}
		
		businessOrderVo = dao.businessView(bmInnb);
		businessOmList = dao.businessOmList(bmInnb);
		//businessSmList = dao.businessSmList(bmInnb);
		businessEmList = dao.businessEmList(bmInnb);
		businessTmList = dao.businessTmList(bmInnb);
		bizFixRoundList = dao.bizFixRoundList(bmInnb);
		
		attachVoList = am.attachList(businessOrderVo.getAm_no());
		
		jo = new JSONObject();
		
		jvo = JSONObject.fromObject(JSONSerializer.toJSON(businessOrderVo));
		jo.put("businessOrderVo", jvo);

		ja = JSONArray.fromObject(businessOmList);
		jo.put("businessOmList", ja);
 
		//ja = JSONArray.fromObject(businessSmList);
		//jo.put("businessSmList", ja);
		
		ja = JSONArray.fromObject(businessEmList);
		jo.put("businessEmList", ja);
		
		ja = JSONArray.fromObject(businessTmList);
		jo.put("businessTmList", ja);
 
		ja = JSONArray.fromObject(bizFixRoundList);
		jo.put("bizFixRoundList", ja);
		
		ja = JSONArray.fromObject(attachVoList);
		jo.put("attachVoList", ja);
		 
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
}
