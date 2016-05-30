package com.dasa.communication.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.communication.dao.NoticeMessageDAO;
import com.dasa.communication.vo.BusinessOrderVo;
import com.dasa.communication.vo.NoticeMessageVo;
import com.dasa.communication.vo.NoticeVo;
import com.vertexid.utill.Encoder;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.AttachVo;
import com.vertexid.vo.CodeVo;
import com.vertexid.vo.KeyValueVo;
import com.vertexid.vo.MobileNaviVo;
import com.vertexid.vo.NaviVo;
import com.vertexid.vo.OrganizationVo;

@Controller
public class NoticeMessageController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private NoticeMessageDAO dao;
	@Autowired
	private AttachManager am;		

	private ModelAndView mv;
	private JSONObject jo;
	private JSONObject jvo;
	private JSONArray ja;
	private JSONObject jsonObject;
	private JSONArray jsonArray;
	private JSONObject response;
	
	private NoticeMessageVo noticeMessageVo;
	private List<NoticeMessageVo> noticeMessageVoList;
	private List<NoticeMessageVo> receiverdVoList;
	private List<KeyValueVo> autoCompList;
	private List<KeyValueVo> noticeOmList;
	private List<KeyValueVo> noticeSmList;
	private List<AttachVo> attachVoList;
	private List<OrganizationVo> organizationList;
	
	@RequestMapping("/70/70-400") 
	public String codePg() throws Exception {
		return "70/70-400";
	}
	
	
	@RequestMapping("/noticePopupOmlist/list") 
	public ModelAndView noticePopupOmlist(@RequestParam("om_code") String om_code) throws Exception {
		noticeMessageVoList = dao.noticePopupOmlist(om_code);
 
		jo = new JSONObject();
		ja = JSONArray.fromObject(noticeMessageVoList);
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/noticeMessage/martList_cp") 
	public ModelAndView msgAutoComplate(@RequestParam("keyword") String keyword, @RequestParam("om_code") String om_code) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		String[] omCodeArray = om_code.split(",");
		List<String> omCodeList = new ArrayList<String>();
		
		for(int i=0; i<omCodeArray.length; i++){
			omCodeList.add(omCodeArray[i]);
		}
		
		map.put("keyword", keyword);
//		map.put("keyword", Encoder.isoToUtf(keyword));
		map.put("omCodeList", omCodeList);
		autoCompList =  dao.msgAutoComplate(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		jo.put("suggestions", ja);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/noticeMessage/list") 
	public ModelAndView noticeList(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		
		naviVo = dao.noticeMessageListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		noticeMessageVoList =  dao.noticeMessageList(naviVo);
		receiverdVoList =  dao.receiverdCnt(naviVo);
		
		jo = new JSONObject();
		
		ja = JSONArray.fromObject(noticeMessageVoList);
		jo.put("noticeMessageVoList", ja);
		
		ja = JSONArray.fromObject(receiverdVoList);
		jo.put("receiverdVoList", ja);
		
		jo.put("navi", navi);
		jo.put("firstNo", naviVo.getFirstRowNo());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
    /**
     * @메소드명: m_noticeList
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /noticeMessage/m_noticeList
     */
	@RequestMapping(value = "/noticeMessage/m_noticeList", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_noticeList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/noticeMessage/m_noticeList");
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
		params.put("jijumOmCode70400", "");//지점코드
		params.put("jijumWord70400", "");//매장코드
		params.put("receiver70400", "");//수신자 이름
		params.put("key70400", "cn");//검색구분
		params.put("word70400", search_value);
		params.put("search_date_from", search_date_from);
		params.put("search_date_to", search_date_to);
		params.put("msg_auth_code", "");
		params.put("type", "MOBILE");
		reqMobileNaviVo.setParams(params);
		
		reqMobileNaviVo = (MobileNaviVo) dao.noticeMessageListCnt(reqMobileNaviVo);
		
		List<NoticeMessageVo> noticeMessageList = dao.noticeMessageList(reqMobileNaviVo);
		//System.out.println("noticeMessageList=" + noticeMessageList);
		
		if (noticeMessageList != null && noticeMessageList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_notice_message = new JSONArray();
			String arr_nmi_innb = "";
			
			//Response Body
			for (int i = 0; i < noticeMessageList.size(); i++) {
				NoticeMessageVo noticeMessageVo = noticeMessageList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("nm_innb", noticeMessageVo.getNm_innb());
				bodyVo.put("nm_message", noticeMessageVo.getNm_massage());
				bodyVo.put("regist_man", noticeMessageVo.getRegist_man());
				bodyVo.put("regist_de", noticeMessageVo.getRegist_de());
				bodyVo.put("read_yn", noticeMessageVo.getReceive_yn());
				body_notice_message.add(bodyVo);
				
				if ((i+1) < noticeMessageList.size()) {
					arr_nmi_innb += noticeMessageVo.getNmi_innb() + ", ";
				} else {
					arr_nmi_innb += noticeMessageVo.getNmi_innb();
				}
			}
			
			body.put("rowSize", rowSize);
			body.put("currPg", currPg);
			body.put("totRow", reqMobileNaviVo.getTotRow());
			body.put("notice_message", body_notice_message);

			response = CommonUtil.setSuccessResponse(body);
			
			//목록조회한 알림메세지 읽음처리
			NoticeMessageVo nmVo = new NoticeMessageVo();
			nmVo.setNmi_innb(arr_nmi_innb);
			dao.changeReadY(nmVo);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/noticeMessage/view") 
	public ModelAndView noticeView(@RequestParam("nmInnb") String nmInnb) throws Exception {
		noticeMessageVoList = dao.noticeMessageView(nmInnb);
		
		jo  = new JSONObject();
		ja = JSONArray.fromObject(noticeMessageVoList);
		
		jo.put("noticeMessageVoList", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/noticeMessage/save") 
	public ModelAndView noticeSave(@ModelAttribute("vo") NoticeMessageVo noticeMessageVo) throws Exception {
		
		String login_no = (String)session.getAttribute("login_no");
		noticeMessageVo.setRegist_man(login_no);
		String nm_innb =  dao.noticeMessageSave(noticeMessageVo);
	
		int cnt = 0;
		if(!nm_innb.equals("") ){
			cnt =  dao.noticeMessagePush(nm_innb);
		}
		System.out.println(cnt+"건 푸시 전송!!!!");
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", nm_innb);
		return mv;
	}
	
	/*@RequestMapping("/noticeOmlist/list")
	public ModelAndView organizationListByCp(@RequestParam("cm_code") String cm_code) throws Exception {
		organizationList = dao.selectListByCp(cm_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		System.out.println("organizationList : " +  jsonObject);

		return mv;
	}*/
	
	/*@RequestMapping("/noticeSmlist/list")
	public ModelAndView organizationListSm(@RequestParam("cm_code") String cm_code) throws Exception {
		System.out.println("cm_code : " + cm_code);
		organizationList = dao.organizationListSm(cm_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		System.out.println("수신매장리스트 : " +  jsonObject);

		return mv;
	}*/
	
	/*@RequestMapping("/noticeMessage/depthList")
	public ModelAndView depthList(@RequestParam("cm_code") String cm_code 
								,@RequestParam("om_code") String om_code
								,@RequestParam("om_orgnzt_se") String om_orgnzt_se) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("cm_code", cm_code);
		map.put("om_code", om_code);
		map.put("om_orgnzt_se", om_orgnzt_se);
		
		organizationList = dao.selectDepthList(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}*/
	
	@RequestMapping("/noticeMessage/receivedList")
	public ModelAndView receivedList(@RequestParam("om_cd") String om_cd,  @RequestParam("sm_cd") String sm_cd, @RequestParam("parent_no") String parent_no) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		String auth_Code = "";
		
		//0000000009 관리자, 0000000008 팀장, 0000000007 순회, 0000000006 고정  || 1 관리자, 2 팀장, 3 고정, 4 순회 
		if(auth_flag == 1) {
			auth_Code = "0000000009";	
		}else if(auth_flag == 2) {
			auth_Code = "0000000008";
		}else if(auth_flag == 4) {
			auth_Code = "0000000007";
		}else if(auth_flag == 3) {
			auth_Code = "0000000006";
		}
//		System.out.println("auth_flag : " + auth_flag + "// auth_Code :" + auth_Code);
		
		map.put("sm_cd", sm_cd);
		map.put("auth_flag", auth_Code);
		map.put("parent_no", parent_no);
		
		//receiverdVoList = dao.selectReceivedList(noticeMessageVo);
		receiverdVoList = dao.selectReceivedList(map);
		
		jsonObject = new JSONObject();
		jsonArray  = JSONArray.fromObject(receiverdVoList);
		jsonObject.put("receiverdVoList", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}
	
}
