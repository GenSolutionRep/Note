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

import com.dasa.communication.dao.NoticeDAO;
import com.dasa.communication.vo.NoticeVo;
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
public class NoticeController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private NoticeDAO dao;
	@Autowired
	private AttachManager am;

	private ModelAndView mv;
	private JSONObject jo;
	private JSONObject jvo;
	private JSONArray ja;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	private JSONObject response;
	
	private NoticeVo noticeVo;
	private List<NoticeVo> noticeVoList;
	private List<KeyValueVo> autoCompList;
	private List<KeyValueVo> noticeOmList;
	private List<KeyValueVo> noticeSmList;
	private List<KeyValueVo> noticeEmList;  
	private List<KeyValueVo> noticeTmList;
	private List<KeyValueVo> fixRoundList;
	private List<AttachVo> attachVoList;
	private List<OrganizationVo> organizationList;
	
	@RequestMapping("/70/70-100") 
	public String codePg() throws Exception {
		return "70/70-100";
	}
	
	
	@RequestMapping("/70/martList") 
	public ModelAndView martAutoComplate(@RequestParam("keyword") String keyword,  @RequestParam("om_code") String om_code ) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		String[] omCodeArray = om_code.split(",");
		List<String> omCodeList = new ArrayList<String>();
		
		for(int i=0; i<omCodeArray.length; i++){
			omCodeList.add(omCodeArray[i]);
		}
		
		String auth_flag = (String)session.getAttribute("auth_flag");
		String login_cp_cd = (String)session.getAttribute("login_cp_cd");
		
		map.put("keyword", keyword);
//		map.put("keyword", Encoder.isoToUtf(keyword));
		map.put("omCodeList", omCodeList);
		map.put("auth_flag", auth_flag);
		map.put("cm_code", login_cp_cd);
		 
		//autoCompList =  dao.martAutoComplate70100(map);
		autoCompList =  dao.teamAutoComplate70100(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		jo.put("suggestions", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/70/martList_cp") 
	public ModelAndView martAutoComplate_cp(@RequestParam("keyword") String keyword) throws Exception{
		autoCompList =  dao.martAutoComplate_cp(keyword);
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		jo.put("suggestions", ja);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo); 
		return mv;
	}
	
	@RequestMapping("/notice/list") 
	public ModelAndView noticeList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		
		naviVo = dao.noticeListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		noticeVoList =  dao.noticeList(naviVo);
		
		String login_team_cd = (String)session.getAttribute("login_team_cd"); //팀코드
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(noticeVoList);
		jo.put("navi", navi);
		jo.put("noticeVoList", ja);
		jo.put("firstNo", naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
    /**
     * @메소드명: m_noticeList
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /notice/m_noticeList
     */
	@RequestMapping(value = "/notice/m_noticeList", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_noticeList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/notice/m_noticeList");
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
		String search_value = StringCheck.isNull(reqMobileNaviVo.getSearch_value(), "");
		//System.out.println("search_date_from=" + search_date_from);
		//System.out.println("search_date_to=" + search_date_to);
		//System.out.println("search_value=" + search_value);
		
		search_date_from = search_date_from.replaceAll("-", "");
		search_date_to = search_date_to.replaceAll("-", "");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("auth_flag", String.valueOf(auth_flag));//권한코드
		params.put("cm_code", cm_code);//회사코드
		params.put("em_no", em_no);//사원번호
		params.put("jijumOmCode", "");//지점코드
		params.put("jijumWord", "");//매장명
		params.put("key", "sj");//검색구분
		params.put("word", search_value);
		params.put("search_date_from", search_date_from);
		params.put("search_date_to", search_date_to);
		params.put("team_val", "");
		params.put("teamWord", "");
		reqMobileNaviVo.setParams(params);
		
		reqMobileNaviVo = (MobileNaviVo) dao.noticeListCnt(reqMobileNaviVo);
		
		List<NoticeVo> noticeList = dao.noticeList(reqMobileNaviVo);
		//System.out.println("noticeList=" + noticeList);
		
		if (noticeList != null && noticeList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_notice = new JSONArray();

			//Response Body
			for (int i = 0; i < noticeList.size(); i++) {
				NoticeVo noticeVo = noticeList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("bm_innb", noticeVo.getBm_innb());
				bodyVo.put("bm_sj", noticeVo.getBm_sj());
				bodyVo.put("regist_man", noticeVo.getRegist_man());
				bodyVo.put("regist_de", noticeVo.getRegist_de());
				body_notice.add(bodyVo);
			}
			body.put("rowSize", rowSize);
			body.put("currPg", currPg);
			body.put("totRow", reqMobileNaviVo.getTotRow());
			body.put("notice", body_notice);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/notice/view") 
	public ModelAndView noticeView(@RequestParam("bmInnb") String bmInnb, @RequestParam("authCode") String authCode) throws Exception{
		
		noticeVo     = dao.noticeView(bmInnb);
		noticeOmList = dao.noticeOmList(bmInnb);
		noticeSmList = dao.noticeSmList(bmInnb);
		noticeEmList = dao.noticeEmList(bmInnb);
		noticeTmList = dao.noticeTmList(bmInnb);
		fixRoundList = dao.fixRoundList(bmInnb);
		
		attachVoList = am.attachList(noticeVo.getAm_no());
		
		jo = new JSONObject();
		
		jvo = JSONObject.fromObject(JSONSerializer.toJSON(noticeVo));
		jo.put("noticeVo", jvo);
		ja = JSONArray.fromObject(noticeOmList);
		jo.put("noticeOmList", ja);
		
		ja = JSONArray.fromObject(noticeSmList);
		jo.put("noticeSmList", ja);
		
		ja = JSONArray.fromObject(noticeTmList);
		jo.put("noticeTmList", ja);
		
		ja = JSONArray.fromObject(noticeEmList);
		jo.put("noticeEmList", ja);
		
		ja = JSONArray.fromObject(fixRoundList);
		jo.put("fixRoundList", ja);
		
		ja = JSONArray.fromObject(attachVoList);
		jo.put("attachVoList", ja);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
    /**
     * @메소드명: m_noticeView
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /notice/m_noticeView
     */
	@RequestMapping(value = "/notice/m_noticeView", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_noticeView(@ModelAttribute("vo") NoticeVo reqNoticeVo) throws Exception{
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/notice/m_noticeView");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String bm_innb = reqNoticeVo.getBm_innb();
//		System.out.println("bm_innb=" + bm_innb);
		
		//Check Required Parameter
		if (StringCheck.isNull(bm_innb)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		NoticeVo resNoticeVo = dao.noticeView(bm_innb);
		//System.out.println("resNoticeVo=" + resNoticeVo);
		
		if (resNoticeVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_notice = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("bm_innb", resNoticeVo.getBm_innb());
			bodyVo.put("bm_sj", resNoticeVo.getBm_sj());
			bodyVo.put("bm_cn", resNoticeVo.getBm_cn());
			bodyVo.put("regist_man", resNoticeVo.getRegist_man());
			bodyVo.put("regist_de", resNoticeVo.getRegist_de());
			bodyVo.put("am_no", resNoticeVo.getAm_no());
			body_notice.add(bodyVo);
			body.put("notice", body_notice);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}	
	
	@RequestMapping("/notice/modify") 
	public ModelAndView noticeModify(@RequestParam("bmInnb") String bmInnb, @RequestParam("authCode") String authCode) throws Exception{
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
		
		//0000000009 관리자, 0000000008 팀장, 0000000007 순회, 0000000006 고정
//		System.out.println("auth_flag : " + auth_flag + "// auth_Code :" + auth_Code);
		if(auth_flag > auth_Code) {
			return null;
		}
		
		noticeVo     = dao.noticeView(bmInnb);
		noticeOmList = dao.noticeOmList(bmInnb);
		noticeSmList = dao.noticeSmList(bmInnb);
		noticeEmList = dao.noticeEmList(bmInnb);
		noticeTmList = dao.noticeTmList(bmInnb);
		
		attachVoList = am.attachList(noticeVo.getAm_no());
		
		jo = new JSONObject();
		
		jvo = JSONObject.fromObject(JSONSerializer.toJSON(noticeVo));
		jo.put("noticeVo", jvo);
		ja = JSONArray.fromObject(noticeOmList);
		jo.put("noticeOmList", ja);
		
		ja = JSONArray.fromObject(noticeSmList);
		jo.put("noticeSmList", ja);
		
		ja = JSONArray.fromObject(noticeEmList);
		jo.put("noticeEmList", ja);
		
		ja = JSONArray.fromObject(noticeTmList);
		jo.put("noticeTmList", ja);
		
		ja = JSONArray.fromObject(attachVoList);
		jo.put("attachVoList", ja);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/notice/popupDelete")
	public ModelAndView noticePopupDelete(@RequestParam("bmInnb") String bmInnb, @RequestParam("authCode") String authCode) throws Exception {
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
		
		//0000000009 관리자, 0000000008 팀장, 0000000007 순회, 0000000006 고정
//		System.out.println("auth_flag : " + auth_flag + "// auth_Code :" + auth_Code);
		if(auth_flag > auth_Code) {
			return null;
		}
		
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bmInnb", bmInnb);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);
 		
		int resultCnt =  dao.noticePopupDelete(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	@RequestMapping("/notice/save") 
	public ModelAndView noticeSave(@ModelAttribute("vo") NoticeVo noticeVo) throws Exception{
		
		String login_no = (String)session.getAttribute("login_no");
		String cm_code = (String) session.getAttribute("login_cp_cd");
		 
		noticeVo.setRegist_man(login_no);
		noticeVo.setCm_code(cm_code);
		
		String bm_innb =  dao.noticeSave(noticeVo);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", bm_innb);
		return mv;
	}
	@RequestMapping("/noticefile/save")
	public ModelAndView noticeFileSave(@RequestParam("files") MultipartFile files[] ,@RequestParam("bmInnb") String bmInnb,@RequestParam("amNo") String amNo) throws Exception{
//		System.out.println("파일등록!!!"+files.length+"\nbmInnb : " + bmInnb);
//		System.out.println("amNo" + amNo + "입니다.");
		int seq = am.updateFile(files,StringCheck.nullToZero(amNo));
//		System.out.println("amNo2 : " + amNo);
//		System.out.println("seq : " + seq);
//		System.out.println("bmInnb : " + bmInnb);
		
		seq = dao.noticeFileSave(bmInnb ,seq);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", seq);
		return mv;
	}
	
	@RequestMapping("/noticefile/list")
	public ModelAndView organizationListByCp(@RequestParam("cm_code") String cm_code) throws Exception {
		organizationList = dao.selectListByCp(cm_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	@RequestMapping("/notice/auth")
	public ModelAndView depthdList(@RequestParam("om_code") String param_om_code,@RequestParam("om_orgnzt_se") String om_orgnzt_se) throws Exception {
		String login_no = (String) session.getAttribute("login_no"); //
		String cm_code = (String)session.getAttribute("login_cp_cd"); //회사코드
		String om_code = (String)session.getAttribute("login_bhf_cd"); //지점코드
		String login_team_cd = (String)session.getAttribute("login_team_cd"); //팀코드
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("auth_flag", auth_flag);
		map.put("tm_code", login_team_cd);
		map.put("cm_code", cm_code);
		map.put("om_code", om_code);
		map.put("param_om_code", param_om_code);
		map.put("om_orgnzt_se", om_orgnzt_se);
		
		organizationList = dao.selectAuthDepthList(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("auth_flag", auth_flag);
		jsonObject.put("cm_code", cm_code);
		jsonObject.put("om_code", om_code);
		jsonObject.put("em_code", login_no);
		jsonObject.put("tm_code", login_team_cd);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}
}
