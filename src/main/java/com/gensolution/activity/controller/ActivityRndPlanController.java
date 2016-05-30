package com.gensolution.activity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gensolution.activity.dao.ActivityRndPlanDAO;
import com.gensolution.activity.vo.ActivityRndPlanDayVo;
import com.gensolution.activity.vo.ActivityRndPlanScheVo;
import com.gensolution.activity.vo.ActivityRndPlanStrVo;
import com.gensolution.activity.vo.ActivityRndPlanVo;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.StringCheck;

@Controller
public class ActivityRndPlanController {
	private ModelAndView mv;			//모델엔뷰
	
	private JSONObject jObj;				//json obj
//	private JSONObject jVo;
	private JSONObject response;
	private JSONArray jArr;				//json 배열
	
//	private ActivityRndPlanVo activityRndPlanVo;
	private List<ActivityRndPlanVo> rndPlanVoList;
	private List<ActivityRndPlanScheVo> rndPlanScheVoList;
	private List<ActivityRndPlanStrVo> rndPlanStrVoList;
	private List<ActivityRndPlanDayVo> rndPlanDayVoList;
	
	@Autowired
	private ActivityRndPlanDAO dao;
	
	@Autowired
	private HttpSession session;
//	
//	@Autowired
//	private AttachManager am;						// 파일관리자
	
	@RequestMapping("/10/10-100") 
	public String codePg(){
		return "10/10-100";
	}
	
	
	@RequestMapping("/rndplan/list")
	public ModelAndView rndPlanList(@RequestParam("base_de") String base_de ,@RequestParam("em_no") String em_no) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		
		rndPlanVoList = dao.rndPlanList(map);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(rndPlanVoList);
		jObj.put("rndPlanVoList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	@RequestMapping("/rndplan/schelist")
	public ModelAndView rndPlanScheList(@RequestParam("base_de") String base_de ,@RequestParam("em_no") String em_no) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("base_de", base_de);
		map.put("em_no", em_no);
		rndPlanScheVoList = dao.rndPlanScheList(map);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(rndPlanScheVoList);
		jObj.put("rndPlanScheVoList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	@RequestMapping("/rndplan/save")
	public ModelAndView rndPlanSave(@ModelAttribute("vo") ActivityRndPlanDayVo vo ) throws Exception{
		
		String cm_code = (String)session.getAttribute("login_cp_cd");
		String om_code = (String)session.getAttribute("login_bhf_cd");
		String login_no = (String)session.getAttribute("login_no");
		vo.setCm_code(cm_code);
		vo.setOm_code(om_code);
		vo.setRegist_man(login_no);
		vo.setUpdt_man(login_no);
		vo.setEm_no(login_no);
		
		int cnt = dao.rndPlanSave(vo);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		return mv;
	}
	
	// 순회 계획 매장 단건 저장
	@RequestMapping("/rndplan/m_smsave")
	public ModelAndView rndMobilePlanSave(@ModelAttribute("vo") ActivityRndPlanDayVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rndplan/m_smsave]  : 순회 계획 매장 단건 저장 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  plan_de : "+vo.getPlan_de()+",  prdi_sm_code : "+vo.getPrdi_sm_code()+",  prdi_sm_code_nm : "+vo.getPrdi_sm_code_nm());
		CommonUtil.setSessionVo(session);
		
		response = new JSONObject();
		
		if (       StringCheck.isNull(vo.getPrdi_sm_code())
				|| StringCheck.isNull(vo.getPrdi_sm_code_nm())
				|| StringCheck.isNull(vo.getPlan_de())
				|| StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getEm_no())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = dao.rndMobilePlanSave(vo);
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	// 순회 계획 매장 단건 삭제
	@RequestMapping("/rndplan/m_smdelete")
	public ModelAndView rndMobilePlanItemDelete(@ModelAttribute("vo") ActivityRndPlanDayVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rndplan/m_smdelete]  : 순회 계획 매장 단건 삭제 em_no : "+vo.getEm_no()+",  prdi_sm_code : "+vo.getPrdi_sm_code()+",  plan_de : "+vo.getPlan_de());
		CommonUtil.setSessionVo(session);
		
		response = new JSONObject();
		
		if (       StringCheck.isNull(vo.getPrdi_sm_code())
				|| StringCheck.isNull(vo.getPlan_de())
				|| StringCheck.isNull(vo.getEm_no())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int cnt = dao.rndMobilePlanItemDelete(vo);
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	
	
	@RequestMapping("/rndplan/mattersave")
	public ModelAndView rndPlanMatterSave(@ModelAttribute("vo") ActivityRndPlanDayVo vo ) throws Exception{
		
		String cm_code = (String)session.getAttribute("login_cp_cd");
		String om_code = (String)session.getAttribute("login_bhf_cd");
		String login_no = (String)session.getAttribute("login_no");
		vo.setCm_code(cm_code);
		vo.setOm_code(om_code);
		vo.setRegist_man(login_no);
		vo.setUpdt_man(login_no); 
		
		int cnt = dao.rndPlanMatterSave(vo);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		return mv;
	}
	@RequestMapping("/rndplan/m_mattersave")
	public ModelAndView rndPlanMobileMatterSave(@ModelAttribute("vo") ActivityRndPlanDayVo vo ) throws Exception{
		System.out.println("["+CommonUtil.getCurrentDateTime() + "Mobile:/rndplan/m_mattersave]  : 순회 계획 비고만 저장 em_no : "+vo.getEm_no()+",  base_de : "+vo.getBase_de()+",  plan_de : "+vo.getPlan_de());
		CommonUtil.setSessionVo(session);
		
		response = new JSONObject();
		
		if (       StringCheck.isNull(vo.getPlan_de())
				|| StringCheck.isNull(vo.getBase_de())
				|| StringCheck.isNull(vo.getCm_code())
				|| StringCheck.isNull(vo.getOm_code())
				|| StringCheck.isNull(vo.getEm_no())
				) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		vo.setRegist_man(vo.getEm_no());
		vo.setUpdt_man(vo.getEm_no());
		
		int cnt = dao.rndPlanMatterSave(vo);
		
		return CommonUtil.makeMobileSaveRespons(response,cnt);
	}
	@RequestMapping("/rndplan/strList")
	public ModelAndView rndPlanStrList(@RequestParam("em_no") String emNo) throws Exception{
		
		rndPlanStrVoList = dao.rndPlanStrList(emNo);
		
		jArr = JSONArray.fromObject(rndPlanStrVoList);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jArr);
		return mv;
	}
	@RequestMapping("/rndplan/strSelectList")
	public ModelAndView rndPlanStrSelectList(@RequestParam("em_no") String emNo,@RequestParam("planDe") String planDe) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("plan_de", planDe);
		map.put("em_no", emNo);
		
		rndPlanStrVoList = dao.rndPlanStrSelectList(map);
		
		jArr = JSONArray.fromObject(rndPlanStrVoList);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jArr);
		return mv;
	}
	@RequestMapping("/rndplan/dayList")
	public ModelAndView rndPlanDayList(@RequestParam("emNo") String emNo,@RequestParam("baseDe") String baseDe) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("base_de", baseDe);
		map.put("em_no", emNo);
		
		rndPlanDayVoList = dao.rndPlanDayList(map);
		jArr = JSONArray.fromObject(rndPlanDayVoList);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jArr);
		return mv;
	}
	
	@RequestMapping("/activityRndPlan/getEmpListByTeam")
	public ModelAndView getEmpListByTeam(@RequestParam("cm_code") String cm_code 
			,@RequestParam("bhf_code") String bhf_code
			,@RequestParam("team_code") String team_code) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("cm_code", cm_code);
		map.put("bhf_code", bhf_code);
		map.put("team_code", team_code);
		
		rndPlanVoList = dao.getEmpListByTeam(map);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(rndPlanVoList);
		
		jObj.put("result", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	@RequestMapping("/activityRndPlan/getActivityRndPlanHeaderList")
	public ModelAndView getActivityRndPlanHeaderList(@RequestParam("searchDate") String searchDate 
			,@RequestParam("searchEmp") String searchEmp) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("searchDate", searchDate);
		map.put("searchEmp", searchEmp);
		
		rndPlanVoList = dao.getActivityRndPlanHeaderList(map);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(rndPlanVoList);
		
		jObj.put("result", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/activityRndPlan/getActivityRndPlanList")
	public ModelAndView getActivityRndPlanList(@RequestParam("searchDate") String searchDate 
			,@RequestParam("searchEmp") String searchEmp) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("searchDate", searchDate);
		map.put("searchEmp", searchEmp);
		
		
		//rndPlanVoList = dao.getActivityRndPlanHeaderList(map);
		
		/*1번*/
		rndPlanVoList = new ArrayList<ActivityRndPlanVo>();
		ActivityRndPlanVo tmp1 = new ActivityRndPlanVo();
		ActivityRndPlanVo tmp2 = new ActivityRndPlanVo();
		ActivityRndPlanVo tmp3 = new ActivityRndPlanVo();
		rndPlanVoList.add(tmp1);
		rndPlanVoList.add(tmp2);
		rndPlanVoList.add(tmp3);
		/*1번*/
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(rndPlanVoList);
		
		jObj.put("result", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
}
