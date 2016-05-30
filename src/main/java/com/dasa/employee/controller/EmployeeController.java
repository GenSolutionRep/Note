package com.dasa.employee.controller;

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

import com.dasa.employee.dao.EmployeeDao;
import com.dasa.employee.vo.EmployeeVo;
import com.dasa.employee.vo.HrHistoryVo;
import com.dasa.employee.vo.WorkingStoreVo;
import com.dasa.login.dao.LoginDao;
import com.dasa.login.vo.LoginVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.CryptoUtil;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.AttachVo;
import com.vertexid.vo.MobileNaviVo;
import com.vertexid.vo.NaviVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * @파일명: EmployeeController.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 사원관리 Controller
 */
@Controller
public class EmployeeController {

	@Autowired
	private HttpSession session;
	@Autowired
	private EmployeeDao dao;
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private AttachManager attachManager;
	
	private ModelAndView mv;
	private JSONObject jo;
	private JSONObject jvo;
	private JSONArray ja;
	private JSONObject response;

	private EmployeeVo reqEmployeeVo;
	private EmployeeVo resEmployeeVo;
	private List<EmployeeVo> employeeList;
	private List<HrHistoryVo> hrHistoryList;
	private List<WorkingStoreVo> workingStoreList;
	private List<AttachVo> attachList;

	@RequestMapping("/60/60-100/{type}")
	public ModelAndView pageEmployee(@PathVariable("type") String type) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/60/60-100/" + type);
		mv = new ModelAndView();
		mv.addObject("type", type);//고정여사원(FIX), 순회여사원(RND), 팀장(TIMHDR), 관리자(MNGR)
		mv.setViewName("/60/60-100");
		return mv;
	}

	@RequestMapping("/employee/getEmployeeList")
	public ModelAndView getEmployeeList(@ModelAttribute("vo") NaviVo naviVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/getEmployeeList");
		naviVo = dao.getEmployeeListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();

		employeeList = dao.getEmployeeList(naviVo);
		//System.out.println("employeeList=" + employeeList);

		jo = new JSONObject();
		ja = JSONArray.fromObject(employeeList);
		jo.put("employeeList", ja);
		jo.put("navi", navi);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping(value = "/employee/m_getEmployeeList", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getEmployeeList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/m_getEmployeeList");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String type = reqMobileNaviVo.getType();
		int rowSize = reqMobileNaviVo.getRowSize();
		int currPg = reqMobileNaviVo.getCurrPg();
		//System.out.println("type=" + type);
		//System.out.println("rowSize=" + rowSize);
		//System.out.println("currPg=" + currPg);
		
		//Check Required Parameter
		if (StringCheck.isNull(type) || rowSize == 0 || currPg == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String cm_code = reqMobileNaviVo.getCm_code();
		String om_code = reqMobileNaviVo.getOm_code();
		String sm_code = reqMobileNaviVo.getSm_code();
		String sm_nm = reqMobileNaviVo.getSm_nm();
		String is_work = "WORK";//StringCheck.isNull(reqMobileNaviVo.getIs_work(), "");
		String except_em_no = reqMobileNaviVo.getExcept_em_no();
		//System.out.println("cm_code=" + cm_code);
		//System.out.println("om_code=" + om_code);
		//System.out.println("sm_code=" + sm_code);
		//System.out.println("sm_nm=" + sm_nm);
		//System.out.println("is_work=" + is_work);
		//System.out.println("except_em_no=" + except_em_no);
		
        //Check Optional Parameter (type=FIX or type=RND)
        if (type.equals("FIX") || type.equals("RND")) {
            if (StringCheck.isNull(om_code)) {
                response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
                return CommonUtil.setModelAndView(response);
            }
        }
        //Check Optional Parameter (type=TIMHDR or type=MNGR)
        if (type.equals("TIMHDR") || type.equals("MNGR")) {
            if (StringCheck.isNull(cm_code)) {
                response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
                return CommonUtil.setModelAndView(response);
            }
        }
        
		String em_dty_code = CommonUtil.getDutyCode(type);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("em_dty_code", em_dty_code);
		params.put("searchCompany", cm_code);
		params.put("searchBranch", om_code);
		params.put("searchStoreCode", sm_code);
		params.put("searchStoreName", sm_nm);
		params.put("searchName", "");
		params.put("searchId", "");
		params.put("searchKey", "");
		params.put("searchValueFrom", "");
		params.put("searchValueTo", "");
		params.put("searchUseAt", is_work);
		params.put("exceptEmployeeNo", except_em_no);
		reqMobileNaviVo.setParams(params);
		
		reqMobileNaviVo = (MobileNaviVo) dao.getEmployeeListCnt(reqMobileNaviVo);

		employeeList = dao.getEmployeeList(reqMobileNaviVo);
		//System.out.println("employeeList=" + employeeList);

		if (employeeList != null && employeeList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_employee = new JSONArray();

			//Response Body
			for (int i = 0; i < employeeList.size(); i++) {
				EmployeeVo employeeVo = employeeList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("em_no", employeeVo.getEm_no());
				bodyVo.put("em_nm", employeeVo.getEm_nm());
				bodyVo.put("em_rspofc_nm", employeeVo.getEm_rspofc_nm());
				bodyVo.put("em_brthdy", employeeVo.getEm_brthdy());
				if (employeeVo.getEm_mbtl_open_at().equals("N")) {
					bodyVo.put("em_mbtl_num", "010-XXXX-XXXX");
				} else {
					bodyVo.put("em_mbtl_num", employeeVo.getEm_mbtl_num());
				}
				bodyVo.put("image_url", employeeVo.getImage_url());
				if (type.equals("FIX")) {
					bodyVo.put("me_nm", employeeVo.getMe_nm());
					bodyVo.put("sm_nm", employeeVo.getSm_nm());
				}
				body_employee.add(bodyVo);
			}
			body.put("type", type);
			body.put("rowSize", rowSize);
			body.put("currPg", currPg);
			body.put("totRow", reqMobileNaviVo.getTotRow());
			body.put("employee", body_employee);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping("/employee/getEmployeeView")
	public ModelAndView getEmployeeView(@RequestParam("em_no") String em_no) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/getEmployeeView");
		resEmployeeVo = dao.getEmployeeView(em_no);
		//System.out.println("resEmployeeVo=" + resEmployeeVo);

		String em_password = CryptoUtil.decrypt(resEmployeeVo.getEm_password());//복호화
		resEmployeeVo.setEm_password(em_password);

		jo = new JSONObject();
		jvo = JSONObject.fromObject(JSONSerializer.toJSON(resEmployeeVo));
		jo.put("employeeVo", jvo);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}

	@RequestMapping("/employee/empListByOmCode")
	public ModelAndView getEmployeeListByOmCode(String om_code, String em_nm) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/empListByOmCode");
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");//로그인 유저의 사원번호
		Map<String, String> map = new HashMap<String, String>();
		map.put("cm_code", login_cp_cd);
		map.put("om_code", om_code);
		map.put("em_nm", em_nm);

		employeeList = dao.getEmployeeListByOmCode(map);
		//System.out.println("employeeList=" + employeeList);

		jo = new JSONObject();
		ja = JSONArray.fromObject(employeeList);
		jo.put("result", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	// 순회
	@RequestMapping("/employee/empListByTeamCode")
	public ModelAndView getEmployeeListByTeamCode(@RequestParam("om_code") String om_code,@RequestParam("team_code") String team_code) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/empListByTeamCode");
		Map<String, String> map = new HashMap<String, String>();
		map.put("om_code", om_code);
		map.put("team_code", team_code);
		
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
		String em_no = (String)session.getAttribute("login_no"); //권한코드
		String em_nm  = (String)session.getAttribute("login_nm"); //권한코드
		jo = new JSONObject();
		
		//System.out.println(auth_flag);
		//System.out.println(em_no);
		//System.out.println(em_nm);
		//System.out.println(om_code);
		//System.out.println(team_code);
		
		if(auth_flag < 3){
			employeeList = dao.getEmployeeListByTeamCode(map);
		}else{
			employeeList =  new ArrayList<EmployeeVo>();
			EmployeeVo vo =  new EmployeeVo();
			vo.setEm_nm(em_nm);
			vo.setEm_no(em_no);
			employeeList.add(vo);
		}
		ja = JSONArray.fromObject(employeeList);
		jo.put("result", ja);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}

	@RequestMapping("/employee/insertEmployee")
	public ModelAndView insertEmployee(@ModelAttribute("vo") EmployeeVo reqEmployeeVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/insertEmployee");
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");//로그인 유저의 회사코드
		reqEmployeeVo.setCm_code(login_cp_cd);

		String login_no = (String) session.getAttribute("login_no");//로그인 유저의 사원번호
		reqEmployeeVo.setRegist_man(login_no);
		reqEmployeeVo.setUpdt_man(login_no);

		String em_password = CryptoUtil.encrypt(reqEmployeeVo.getEm_password());//암호화
		reqEmployeeVo.setEm_password(em_password);

		resEmployeeVo = dao.checkUserId(reqEmployeeVo);
		//System.out.println("resEmployeeVo=" + resEmployeeVo);
		
		String check_id = resEmployeeVo.getCheck_id();//입력한 ID의 존재여부
		if (check_id.equals("Y")) {
			return CommonUtil.setModelAndViewOfString("-1");
		}
		
		if (reqEmployeeVo.getEm_dty_code().equals("0000000008")) {//입력한 직무가 팀장인 경우
			resEmployeeVo = dao.checkTeamHeader(reqEmployeeVo);
			//System.out.println("resEmployeeVo=" + resEmployeeVo);
			
			String check_timhdr = resEmployeeVo.getCheck_timhdr();
			if (check_timhdr.equals("Y")) {//입력한 팀의 팀장이 이미 존재하는 경우
				return CommonUtil.setModelAndViewOfString("-2");
			}
		}
		
		int result = dao.insertEmployee(reqEmployeeVo);
		//System.out.println("result=" + result);
		
		return CommonUtil.setModelAndViewOfInt(result);
	}

	@RequestMapping("/employee/updateEmployee")
	public ModelAndView updateEmployee(@ModelAttribute("vo") EmployeeVo reqEmployeeVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/updateEmployee");
		String login_no = (String) session.getAttribute("login_no");//로그인 유저의 사원번호
		reqEmployeeVo.setUpdt_man(login_no);

		String em_password = CryptoUtil.encrypt(reqEmployeeVo.getEm_password());//암호화
		reqEmployeeVo.setEm_password(em_password);
		
		resEmployeeVo = dao.checkUserId(reqEmployeeVo);
		//System.out.println("resEmployeeVo=" + resEmployeeVo);
		
		String check_id = resEmployeeVo.getCheck_id();//입력한 ID의 존재여부
		String exist_em_no = resEmployeeVo.getExist_em_no();//입력한 ID 존재시 사원번호
		if (check_id.equals("Y") && !exist_em_no.equals(reqEmployeeVo.getEm_no())) {
			return CommonUtil.setModelAndViewOfString("-1");
		}
		
		if (reqEmployeeVo.getEm_dty_code().equals("0000000008")) {//입력한 직무가 팀장인 경우
			resEmployeeVo = dao.checkTeamHeader(reqEmployeeVo);
			//System.out.println("resEmployeeVo=" + resEmployeeVo);
			
			String check_timhdr = resEmployeeVo.getCheck_timhdr();//입력한 팀의 팀장 존재여부
			String timhdr_em_no = resEmployeeVo.getTimhdr_em_no();//입력한 팀의 팀장 존재시 팀장사원번호
			if (check_timhdr.equals("Y") && !timhdr_em_no.equals(reqEmployeeVo.getEm_no())) {
				return CommonUtil.setModelAndViewOfString("-2");
			}
		}
		
		int result = dao.updateEmployee(reqEmployeeVo);
		//System.out.println("result=" + result);
		
		return CommonUtil.setModelAndViewOfInt(result);
	}

	@RequestMapping("/employee/changePassword")
	public ModelAndView changePassword(@ModelAttribute("vo") EmployeeVo reqEmployeeVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/changePassword");
		String login_no = (String) session.getAttribute("login_no");//로그인 유저의 사원번호
		reqEmployeeVo.setUpdt_man(login_no);

		String em_password = CryptoUtil.encrypt(reqEmployeeVo.getEm_password());//암호화
		reqEmployeeVo.setEm_password(em_password);

		int result = dao.changePassword(reqEmployeeVo);
		//System.out.println("result=" + result);
		
		return CommonUtil.setModelAndViewOfInt(result);
	}
	
	@RequestMapping(value = "/employee/m_changePassword", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_changePassword(@ModelAttribute("vo") EmployeeVo reqEmployeeVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/m_changePassword");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String em_no = reqEmployeeVo.getEm_no();
		String before_password = reqEmployeeVo.getBefore_password();
		String after_password = reqEmployeeVo.getAfter_password();
		//System.out.println("em_no=" + em_no);
		//System.out.println("before_password=" + before_password);
		//System.out.println("after_password=" + after_password);
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(before_password) || StringCheck.isNull(after_password)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		LoginVo reqLoginVo = new LoginVo();
		reqLoginVo.setEm_no(em_no);
		reqLoginVo.setEm_password(before_password);
		
		LoginVo resLoginVo = loginDao.login(reqLoginVo);
		//System.out.println("resLoginVo=" + resLoginVo);
		
		if (resLoginVo != null) {
			reqEmployeeVo.setUpdt_man(em_no);
			reqEmployeeVo.setEm_password(after_password);
			
			int result = dao.changePassword(reqEmployeeVo);
			//System.out.println("result=" + result);
			
			if (result > 0) {
				JSONObject body = new JSONObject();
				JSONArray body_change_at = new JSONArray();
				
				//Response Body
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("change_at", "Y");
				body_change_at.add(bodyVo);
				body.put("change_at", body_change_at);

				response = CommonUtil.setSuccessResponse(body);
			} else {
				response = CommonUtil.setFailResponse(Constant.PW_CHANGE_FAILED_CD, Constant.PW_CHANGE_FAILED_MSG);
			}
		} else {
			response = CommonUtil.setFailResponse(Constant.LOGIN_FAILED_CD, Constant.LOGIN_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/employee/m_changeContactOpen", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_changeContactOpen(@ModelAttribute("vo") EmployeeVo reqEmployeeVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/m_changeContactOpen");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String em_no = reqEmployeeVo.getEm_no();
		String em_mbtl_open_at = reqEmployeeVo.getEm_mbtl_open_at();
		//System.out.println("em_no=" + em_no);
		//System.out.println("em_mbtl_open_at=" + em_mbtl_open_at);
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(em_mbtl_open_at)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		reqEmployeeVo.setUpdt_man(em_no);
		int result = dao.changeContactOpen(reqEmployeeVo);
		//System.out.println("result=" + result);
		
		if (result > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_change_at = new JSONArray();
			
			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("change_at", "Y");
			body_change_at.add(bodyVo);
			body.put("change_at", body_change_at);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.CONTACT_OPEN_CHANGE_FAILED_CD, Constant.CONTACT_OPEN_CHANGE_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/employee/m_getProfilePicture", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getProfilePicture(@RequestParam("em_no") String em_no) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/m_getProfilePicture");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		//System.out.println("em_no=" + em_no);
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		resEmployeeVo = dao.getProfilePicture(em_no);
		//System.out.println("resEmployeeVo=" + resEmployeeVo);
		
		if (resEmployeeVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_am_no = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("am_no", resEmployeeVo.getAm_no());
			body_am_no.add(bodyVo);
			body.put("am_no", body_am_no);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/employee/setProfilePicture")
	public ModelAndView setProfilePicture(@ModelAttribute("vo") EmployeeVo reqEmployeeVo) throws Exception {
		
		int req_am_no = reqEmployeeVo.getAm_no();//첨부파일 고유번호
		int req_files_length = reqEmployeeVo.getFiles().length;//첨부파일 개수
		//System.out.println("req_am_no=" + req_am_no);
		//System.out.println("req_files_length=" + req_files_length);
		
		int res_am_no = attachManager.updateFile(reqEmployeeVo.getFiles(), req_am_no);
		//System.out.println("res_am_no=" + res_am_no);
		
		return CommonUtil.setModelAndViewOfInt(res_am_no);
	}	
	
	@RequestMapping(value = "/employee/m_setProfilePicture", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_setProfilePicture(@ModelAttribute("vo") EmployeeVo reqEmployeeVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/m_setProfilePicture");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String em_no = reqEmployeeVo.getEm_no();
		int am_no = reqEmployeeVo.getAm_no();
		//System.out.println("em_no=" + em_no);
		//System.out.println("am_no=" + am_no);
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || am_no == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		reqEmployeeVo.setUpdt_man(em_no);
		int result = dao.setProfilePicture(reqEmployeeVo);
		//System.out.println("result=" + result);
		
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
			response = CommonUtil.setFailResponse(Constant.PROFILE_PICTURE_FAILED_CD, Constant.PROFILE_PICTURE_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/employee/getHrHistoryList")
	public ModelAndView getHrHistoryList(@RequestParam("em_no") String em_no) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/getHrHistoryList");
		hrHistoryList = dao.getHrHistoryList(em_no);
		//System.out.println("hrHistoryList=" + hrHistoryList);

		jo = new JSONObject();
		ja = JSONArray.fromObject(hrHistoryList);
		jo.put("hrHistoryList", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}

	@RequestMapping("/hrHistory/setHrHistory")
	public ModelAndView setHrHistory(@ModelAttribute("vo") HrHistoryVo reqHrHistoryVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/hrHistory/setHrHistory");
		String login_no = (String) session.getAttribute("login_no");//로그인 유저의 사원번호
		reqHrHistoryVo.setUpdt_man(login_no);

		int result = dao.setHrHistory(reqHrHistoryVo);
		//System.out.println("result=" + result);
		
		return CommonUtil.setModelAndViewOfInt(result);
	}

	@RequestMapping("/employee/getWorkingStoreList")
	public ModelAndView getWorkingStoreList(@RequestParam("em_no") String em_no) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/getWorkingStoreList");
		workingStoreList = dao.getWorkingStoreList(em_no);
		//System.out.println("workingStoreList=" + workingStoreList);

		jo = new JSONObject();
		ja = JSONArray.fromObject(workingStoreList);
		jo.put("workingStoreList", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	@RequestMapping("/employee/getEmployeeExcelDown")
	public ModelAndView getEmployeeExcelDown(@RequestParam Map<String, String> reqMap) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/employee/getEmployeeExcelDown");

		employeeList = dao.getEmployeeExcelDown(reqMap);
		//System.out.println("employeeList=" + employeeList);

		jo = new JSONObject();
		ja = JSONArray.fromObject(employeeList);
		jo.put("employeeList", ja);
		
		mv = new ModelAndView();
		mv.addObject("ajax", jo);
		mv.setViewName("/60/60-100excel");
		return mv;
	}

}
