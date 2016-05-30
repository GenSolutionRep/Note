package com.dasa.communication.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.communication.dao.MainEventDAO;
import com.dasa.communication.vo.MainEventTargetVo;
import com.dasa.communication.vo.MainEventVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.Encoder;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.AttachVo;
import com.vertexid.vo.KeyValueVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
public class MainEventController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private MainEventDAO dao;
	@Autowired
	private AttachManager am;
	
	private ModelAndView mv;
	private JSONObject jvo;
	private JSONObject jo;
	private JSONArray ja;
	private JSONObject response;
	
	private MainEventVo vo;
	private MainEventVo targetVo;
	private List<MainEventVo> meList;
	private List<MainEventTargetVo> mtList;
	private List<KeyValueVo> autoCompList;
	private List<KeyValueVo> omList;
	private List<KeyValueVo> smList;
	private List<AttachVo> attachVoList;
	
	/**
	 * @메서드명: mainEventPg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 주요행사
	 * @return "String"
	 */
	@RequestMapping("/70/70-200") 
	public String mainEventPg() throws Exception {
		return "/70/70-200";
	}
	
	/**
	 * @메서드명: selectList
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: 주요행사 
	 */
	@RequestMapping("/event/list") 
	public ModelAndView selectList(@RequestParam String yearMm, @RequestParam String om_code) throws Exception {
		HashMap<String, String> map =  new HashMap<String, String>();
		map.put("yearMm", yearMm);
		map.put("om_code", om_code);
		
		String auth_flag = (String) session.getAttribute("auth_flag");	
		map.put("auth_flag", auth_flag);
		
		String login_no = (String) session.getAttribute("login_no");
		map.put("em_no", login_no);

		meList =  dao.selectList(map);
		jo = new JSONObject();
		ja = JSONArray.fromObject(meList);
		
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
			
		return mv;
	}
	
	/**
	 * @메서드명: selectRow
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: row 조회
	 */
	@RequestMapping("/event/row") 
	public ModelAndView selectRow(@RequestParam("me_innb") String me_innb, @RequestParam("eventAuth") String eventAuth, @RequestParam("flag") String flag) throws Exception {
//		System.out.println("me_innb : " + me_innb);
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		int auth_Code = 0;
		
		if(eventAuth.equals("0000000009")) {
			auth_Code = 1;	
		}else if(eventAuth.equals("0000000008")) {
			auth_Code = 2;
		}else if(eventAuth.equals("0000000007")) {
			auth_Code = 4;
		}else if(eventAuth.equals("0000000006")) {
			auth_Code = 3;
		}
		
		//0000000009 관리자, 0000000008 팀장, 0000000007 순회, 0000000006 고정
//		System.out.println("auth_flag : " + auth_flag + "// auth_Code :" + auth_Code);
		if(flag.equals("u")) {
			if(auth_flag > auth_Code) {
				return null;
			}
		}
		vo = dao.selectRow(me_innb);
		omList = dao.omList(me_innb);
		smList = dao.smList(me_innb);
		
		/*if(vo.getAtchmnfl_innb()!=null &&vo.getAtchmnfl_innb()!=""){
			attachVoList = am.attachList(Integer.parseInt(vo.getAtchmnfl_innb()));
		}else{
			attachVoList = null;
		}*/
		
		jo = new JSONObject();
		
		if(vo.getAtchmnfl_innb()!=null && !vo.getAtchmnfl_innb().equals("")){
            attachVoList = am.attachList(Integer.parseInt(vo.getAtchmnfl_innb()));
            ja = JSONArray.fromObject(attachVoList);
            jo.put("attachVoList", ja);
        }else{
            jo.put("attachVoList", "");
        }
		
		jvo = JSONObject.fromObject(JSONSerializer.toJSON(vo));
		
		jo.put("vo", jvo);
		ja = JSONArray.fromObject(omList);
		jo.put("omList", ja);
		
		ja = JSONArray.fromObject(smList);
		jo.put("smList", ja);
		
		/*ja = JSONArray.fromObject(attachVoList);
		jo.put("attachVoList", ja);*/
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
		
		/*
		vo = dao.selectRow(me_innb);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(vo);
		
		jo.put("result", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;*/
	}
	
	/**
	 * @메서드명: selectList
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: 주요행사 
	 */
	@RequestMapping("/event/scheduleList") 
	public ModelAndView scheduleList(@RequestParam String yearMm, @RequestParam String om_code) throws Exception {
		vo= new MainEventVo();
		vo.setYyyyMm(yearMm);
		vo.setOm_code(om_code);
		
		String auth_flag = (String) session.getAttribute("auth_flag");	
		vo.setAuth_flag(Integer.parseInt(auth_flag));
		
		String login_no = (String) session.getAttribute("login_no");
		vo.setEm_no(login_no);
		
		meList =  dao.selectScheduleList(vo);
		jo = new JSONObject();
		ja = JSONArray.fromObject(meList);
		
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
			
		return mv;
	}
	
    /**
     * @메소드명: m_scheduleList
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /event/m_scheduleList
     */
	@RequestMapping(value = "/event/m_scheduleList", produces = "text/plain;charset=UTF-8") 
	public ModelAndView m_scheduleList(@ModelAttribute("vo") MainEventVo reqMainEventVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/event/m_scheduleList");
        CommonUtil.setSessionVo(session);
        
		//Required Parameter
        String kind = reqMainEventVo.getKind();
        int auth_flag = reqMainEventVo.getAuth_flag();
        String em_no = reqMainEventVo.getEm_no();
        String yyyyMm = reqMainEventVo.getYyyyMm();
        String om_code = reqMainEventVo.getOm_code();
        //System.out.println("kind=" + kind);
        //System.out.println("auth_flag=" + auth_flag);
        //System.out.println("em_no=" + em_no);
        //System.out.println("yyyyMm=" + yyyyMm);
        //System.out.println("om_code=" + om_code);
		
		// Check Required Parameter
		if (StringCheck.isNull(kind) || auth_flag < 0 || StringCheck.isNull(em_no) || StringCheck.isNull(yyyyMm) || StringCheck.isNull(om_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String sm_nm = StringCheck.isNull(reqMainEventVo.getSm_nm(), "");
		String t_days = StringCheck.isNull(reqMainEventVo.getT_days(), "");
		//System.out.println("sm_nm=" + sm_nm);
		//System.out.println("t_days=" + t_days);
		
        //Check Optional Parameter
        if (kind.equals("VIEW") && StringCheck.isNull(t_days)) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }
        
		List<MainEventVo> mainEventList = dao.selectScheduleList(reqMainEventVo);
		//System.out.println("mainEventList=" + mainEventList);
		
		if (mainEventList != null && mainEventList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_main_event = new JSONArray();

			//Response Body
			for (int i = 0; i < mainEventList.size(); i++) {
				MainEventVo mainEventVo = mainEventList.get(i);
				JSONObject bodyVo = new JSONObject();
				if (kind.equals("LIST")) {
					bodyVo.put("t_days", mainEventVo.getT_days());
					bodyVo.put("cnt", mainEventVo.getCnt());
				} else if (kind.equals("VIEW")) {
					bodyVo.put("me_sj", mainEventVo.getMe_sj());
					bodyVo.put("me_cn", mainEventVo.getMe_cn());
					bodyVo.put("me_allday_at", mainEventVo.getMe_allday_at());
					bodyVo.put("schdul_bgnde_hhmm", mainEventVo.getSchdul_bgnde_hhmm());
					bodyVo.put("schdul_endde_hhmm", mainEventVo.getSchdul_endde_hhmm());
				}
				body_main_event.add(bodyVo);
			}
			body.put("main_event", body_main_event);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	/**
	 * @메서드명: insertEvent
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: insert
	 */
	@RequestMapping("/event/save")
	public ModelAndView saveEvent(@ModelAttribute("vo") MainEventVo vo) throws Exception {
//		System.out.println("noticeVo : " + vo.toString());

		String login_no = (String) session.getAttribute("login_no");
		
		vo.setRegist_man(login_no);
		vo.setUpdt_man(login_no);
		
		//int resultCnt = dao.insertMainEvent(vo);
		
		String me_innb =  dao.saveMainEvent(vo);
		 
		int cnt = 0;
		/*if(!me_innb.equals("") &&  vo.getMe_ntcn_at()!="null" && vo.getMe_ntcn_at().equals("Y")){
			cnt =  dao.eventPush(vo.getMe_innb());
		}*/
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", me_innb);
		return mv;
	}
	
	/**
	 * @메서드명: updateEventPeriod
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: updateEventPeriod
	 */
	@RequestMapping("/event/updatePeriod")
	public ModelAndView updateEventPeriod(@RequestParam("schdul_bgnde") String schdul_bgnde
										,@RequestParam("me_innb") String me_innb
										) throws Exception {
		vo =  new MainEventVo();
		
		String login_no = (String) session.getAttribute("login_no");
		
		vo.setSchdul_bgnde(schdul_bgnde);
		vo.setMe_innb(me_innb);
		vo.setUpdt_man(login_no);
		
//		System.out.println("schdul_bgnde" + schdul_bgnde);
//		System.out.println("me_innb" + me_innb);
		
		int resultCnt =  dao.updateEventPeriod(vo);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	/**
	 * @메서드명: saveFile
	 * @작성일: 2015. 11. 09
	 * @작성자: 최수영
	 * @설명: saveFile
	 */
	@RequestMapping("/event/saveFile")
	public ModelAndView saveFile(@RequestParam("files") MultipartFile files[] ,@RequestParam("me_innb") String me_innb, @RequestParam("am_no") String am_no) throws Exception {
//		System.out.println("파일등록!!!"+files.length+"\nme_innb : " + me_innb);
//		System.out.println("amNo" + am_no + "입니다.");
		
		int seq = am.updateFile(files,StringCheck.nullToZero(am_no));
//		System.out.println("amNo2 : " + am_no);
//		System.out.println("seq2 : " + seq);
//		System.out.println("me_innb2 : " + me_innb);
		
		seq = dao.saveFile(me_innb ,seq);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", seq);
		return mv;
	}
	
	/**
	 * @메서드명: updateEvent
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: update
	 */
	@RequestMapping("/event/update")
	public ModelAndView updateEvent(@ModelAttribute("vo") MainEventVo vo) throws Exception {
		vo =  new MainEventVo();
		
		String login_no = (String) session.getAttribute("login_no");
		vo.setUpdt_man(login_no);
		
		int resultCnt = dao.updateMainEvent(vo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	
	/**
	 * @메서드명: updateEvent
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: update
	 */
	@RequestMapping("/event/delete")
	public ModelAndView deleteEvent(@RequestParam String me_innb, @RequestParam("eventAuth") String eventAuth ) throws Exception {
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		int auth_Code = 0;
		
		if(eventAuth.equals("0000000009")) {
			auth_Code = 1;	
		}else if(eventAuth.equals("0000000008")) {
			auth_Code = 2;
		}else if(eventAuth.equals("0000000007")) {
			auth_Code = 4;
		}else if(eventAuth.equals("0000000006")) {
			auth_Code = 3;
		}
		
		//0000000009 관리자, 0000000008 팀장, 0000000007 순회, 0000000006 고정
//		System.out.println("auth_flag : " + auth_flag + "// auth_Code :" + auth_Code);
		if(auth_flag > auth_Code) {
			return null;
		}
 
		vo =  new MainEventVo();
		
		String login_no = (String) session.getAttribute("login_no");
		vo.setUpdt_man(login_no);
		vo.setMe_innb(me_innb);
		
		int resultCnt = dao.deleteMainEvent(vo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	
	
	/**
	 * @메서드명: selectList
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: 주요행사 
	 */
	@RequestMapping("/event/target/list") 
	public ModelAndView selectTargetList(@RequestParam String mt_innb) throws Exception {
		MainEventTargetVo tVo =  new MainEventTargetVo();
		
		String auth_flag = (String) session.getAttribute("auth_flag");	
		
		tVo.setMt_innb(mt_innb);
		tVo.setMt_flag(auth_flag);
		meList =  dao.selectTargetList(tVo);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(meList);
		
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
			
		return mv;
	}
	
	/**
	 * @메서드명: selectRow
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: row 조회
	 */
	@RequestMapping("/event/target/row") 
	public ModelAndView selectTargetRow(@RequestParam("me_innb") String me_innb) throws Exception {
		vo = dao.selectTargetRow(me_innb);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(vo);
		
		jo.put("result", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	/**
	 * @메서드명: insertEvent
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: insert
	 */
	@RequestMapping("/event/target/insert")
	public ModelAndView insertTarget(@ModelAttribute("vo") MainEventTargetVo vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		vo.setRegist_man(login_no);
		vo.setUpdt_man(login_no);
		
		int resultCnt = dao.insertTarget(vo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	
	/**
	 * @메서드명: updateEvent
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: update
	 */
	@RequestMapping("/event/target/update")
	public ModelAndView updateTarget(@ModelAttribute("vo") MainEventTargetVo vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		vo.setUpdt_man(login_no);
		
		int resultCnt = dao.updateTarget(vo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	
	/**
	 * @메서드명: updateTarget
	 * @작성일: 2015. 10. 29
	 * @작성자: 최수영
	 * @설명: update
	 */
	@RequestMapping("/event/target/delete")
	public ModelAndView deleteTarget(@ModelAttribute("vo") MainEventTargetVo vo) throws Exception {
		
		String login_no = (String) session.getAttribute("login_no");
		vo.setUpdt_man(login_no);
		
		int resultCnt = dao.deleteTarget(vo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	
	
	@RequestMapping("/event/autoComplate_store") 
	public ModelAndView autoComplate_store(@RequestParam("keyword") String keyword
										) throws Exception {
		//System.out.println(Encoder.isoToUtf(keyword));
		//autoCompList =  dao.martAutoComplate(Encoder.isoToUtf(keyword));
		
		String login_no = (String) session.getAttribute("login_no");
		String auto_flag = (String) session.getAttribute("auth_flag");
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("keyword", keyword);
//		map.put("keyword", Encoder.isoToUtf(keyword));
		map.put("login_cp_cd", login_cp_cd);
		map.put("auto_flag", auto_flag);
		
		autoCompList =  dao.autoComplate_store(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		
		jo.put("suggestions", ja);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/event/autoComplate_bhf") 
	public ModelAndView autoComplate_bhf(@RequestParam("keyword") String keyword
										) throws Exception {
		String auto_flag = (String) session.getAttribute("auth_flag");
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("keyword", keyword);
//		map.put("keyword", Encoder.isoToUtf(keyword));
		map.put("login_cp_cd", login_cp_cd);
		map.put("auto_flag", auto_flag);
		
		autoCompList =  dao.autoComplate_bhf(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(autoCompList);
		
		jo.put("suggestions", ja);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/event/dashList") 
	public ModelAndView eventDashList(@RequestParam("om_code") String p_om_code) throws Exception {
		String auto_flag = (String) session.getAttribute("auth_flag");
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		//String om_code = (String) session.getAttribute("login_bhf_cd");
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("om_code", p_om_code);
		map.put("cm_code", login_cp_cd);
		map.put("auto_flag", auto_flag);
		
		meList =  dao.eventDashList(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(meList);
		
		jo.put("result", ja);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	
}
