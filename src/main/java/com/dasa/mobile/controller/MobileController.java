package com.dasa.mobile.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.approval.dao.ApprovalDao;
import com.dasa.communication.dao.BusinessOrderDAO;
import com.dasa.communication.dao.MainEventDAO;
import com.dasa.communication.dao.NoticeDAO;
import com.dasa.communication.dao.NoticeMessageDAO;
import com.dasa.communication.vo.MainEventVo;
import com.dasa.communication.vo.NoticeVo;
import com.dasa.login.vo.LoginVo;
import com.dasa.mobile.dao.MobileDao;
import com.dasa.mobile.vo.MobileVo;
import com.vertexid.dao.CommonCodeDAO;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.CodeVo;
import com.vertexid.vo.MobileNaviVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @파일명: MobileController.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 모바일 Controller
 */
@Controller
public class MobileController {

	@Autowired
	private HttpSession session;
	@Autowired
	private MobileDao dao;
	@Autowired
	private ApprovalDao approvalDao;
	@Autowired
	private NoticeDAO noticeDao;
	@Autowired
	private MainEventDAO mainEventDao;
	@Autowired
	private BusinessOrderDAO businessOrderDao;
	@Autowired
	private NoticeMessageDAO noticeMessageDao;
	@Autowired
	private CommonCodeDAO commonCodeDao;
	@Autowired
	private AttachManager attachManager;	

	private ModelAndView mv;
	private JSONObject jo;
	private JSONObject jvo;
	private JSONArray ja;
	private JSONObject response;

	private MobileVo reqMobileVo;
	private MobileVo resMobileVo;
	private List<MobileVo> mobileList;
	private List<CodeVo> codeList;

	@RequestMapping(value = "/m_getCurrentDate", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getCurrentDate(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getCurrentDate");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String flag = reqMobileVo.getFlag();
		//System.out.println("flag=" + flag);

		//Check Required Parameter
		if (StringCheck.isNull(flag)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}

		String date_format = "";
		if (flag.equals("1")) {
			date_format = "%Y%m%d";
		} else if (flag.equals("2")) {
			date_format = "%Y-%m-%d";
		} else if (flag.equals("3")) {
			date_format = "%Y%m%d%H%i%S";
		} else if (flag.equals("4")) {
			date_format = "%Y-%m-%d %H:%i:%S";
		} else {
			date_format = "%Y-%m-%d";
		}
		reqMobileVo.setDate_format(date_format);

		resMobileVo = dao.getCurrentDate(reqMobileVo);
		//System.out.println("resMobileVo=" + resMobileVo);

		if (resMobileVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_curr_de = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("curr_de", resMobileVo.getCurr_de());
			body_curr_de.add(bodyVo);
			body.put("curr_de", body_curr_de);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getCGList", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getCGList() throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getCGList");
		CommonUtil.setSessionVo(session);

		mobileList = dao.getCustomerGroupList();
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_cg = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("cg_code", mobileVo.getCg_code());
				bodyVo.put("cg_nm", mobileVo.getCg_nm());
				body_cg.add(bodyVo);
			}
			body.put("cg", body_cg);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getMEListPerCG", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getMEListPerCG(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getMEListPerCG");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String cg_code = reqMobileVo.getCg_code();
		//System.out.println("cg_code=" + cg_code);

		//Check Required Parameter
		if (StringCheck.isNull(cg_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}

		mobileList = dao.getManageEnterpriseListPerCustomerGroup(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_me = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("me_code", mobileVo.getMe_code());
				bodyVo.put("me_nm", mobileVo.getMe_nm());
				body_me.add(bodyVo);
			}
			body.put("me", body_me);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getStoreListPerME", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getStoreListPerME(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getStoreListPerME");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String cg_code = reqMobileVo.getCg_code();
		String me_code = reqMobileVo.getMe_code();
		//System.out.println("cg_code=" + cg_code);
		//System.out.println("me_code=" + me_code);

		//Check Required Parameter
		if (StringCheck.isNull(cg_code) || StringCheck.isNull(me_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}

		mobileList = dao.getStoreListPerManageEnterprise(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_sm = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("sm_code", mobileVo.getSm_code());
				bodyVo.put("sm_nm", mobileVo.getSm_nm());
				body_sm.add(bodyVo);
			}
			body.put("sm", body_sm);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getStoreViewOfFIX", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getStoreViewOfFIX(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getStoreViewOfFIX");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String em_no = reqMobileVo.getEm_no();
		//System.out.println("em_no=" + em_no);

		//Check Required Parameter
		if (StringCheck.isNull(em_no)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}

		reqMobileVo.setEm_dty_code("0000000006");//고정여사원
		
		resMobileVo = dao.getStoreViewOfFix(reqMobileVo);
		//System.out.println("resMobileVo=" + resMobileVo);

		if (resMobileVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_sm = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("me_code", resMobileVo.getMe_code());
			bodyVo.put("me_nm", resMobileVo.getMe_nm());
			bodyVo.put("sm_code", resMobileVo.getSm_code());
			bodyVo.put("sm_nm", resMobileVo.getSm_nm());
			bodyVo.put("sm_la", resMobileVo.getSm_la());
			bodyVo.put("sm_lo", resMobileVo.getSm_lo());
			body_sm.add(bodyVo);
			body.put("sm", body_sm);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getStoreListOfRND", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getStoreListOfRND(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getStoreListOfRND");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String em_no = reqMobileVo.getEm_no();
		//System.out.println("em_no=" + em_no);
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String search_nm = StringCheck.isNull(reqMobileVo.getSearch_nm(), "");
		//System.out.println("search_nm=" + search_nm);
		
		search_nm = search_nm.replaceAll(" ", "");
		reqMobileVo.setSearch_nm(search_nm);		

		reqMobileVo.setEm_dty_code("0000000007");//순회여사원
		
		mobileList = dao.getStoreListOfRnd(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_sm = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("me_code", mobileVo.getMe_code());
				bodyVo.put("me_nm", mobileVo.getMe_nm());
				bodyVo.put("sm_code", mobileVo.getSm_code());
				bodyVo.put("sm_nm", mobileVo.getSm_nm());
				bodyVo.put("sm_la", mobileVo.getSm_la());
				bodyVo.put("sm_lo", mobileVo.getSm_lo());
				body_sm.add(bodyVo);
			}
			body.put("sm", body_sm);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getStoreListOfTIMHDR", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getStoreListOfTIMHDR(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getStoreListOfTIMHDR");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String om_code = reqMobileVo.getOm_code();
		//System.out.println("om_code=" + om_code);
		
		//Check Required Parameter
		if (StringCheck.isNull(om_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String search_nm = StringCheck.isNull(reqMobileVo.getSearch_nm(), "");
		//System.out.println("search_nm=" + search_nm);

		search_nm = search_nm.replaceAll(" ", "");
		reqMobileVo.setSearch_nm(search_nm);

		mobileList = dao.getStoreListOfTeamHeader(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_sm = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("sm_code", mobileVo.getSm_code());
				bodyVo.put("sm_nm", mobileVo.getSm_nm());
				body_sm.add(bodyVo);
			}
			body.put("sm", body_sm);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getStoreListOfMNGR", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getStoreListOfMNGR(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getStoreListOfMNGR");
		CommonUtil.setSessionVo(session);
		
		//Optional Parameter
		String search_nm = StringCheck.isNull(reqMobileVo.getSearch_nm(), "");
		//System.out.println("search_nm=" + search_nm);
		
		search_nm = search_nm.replaceAll(" ", "");
		reqMobileVo.setSearch_nm(search_nm);

		mobileList = dao.getStoreListOfManager(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_sm = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("sm_code", mobileVo.getSm_code());
				bodyVo.put("sm_nm", mobileVo.getSm_nm());
				body_sm.add(bodyVo);
			}
			body.put("sm", body_sm);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_getStoreListPerBranch", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getStoreListPerBranch(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getStoreListPerBranch");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String om_code = reqMobileVo.getOm_code();
		//System.out.println("om_code=" + om_code);
		
		//Check Required Parameter
		if (StringCheck.isNull(om_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String search_nm = StringCheck.isNull(reqMobileVo.getSearch_nm(), "");
		//System.out.println("search_nm=" + search_nm);
		
		search_nm = search_nm.replaceAll(" ", "");
		reqMobileVo.setSearch_nm(search_nm);
		
		mobileList = dao.getStoreListPerBranch(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_sm = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("sm_code", mobileVo.getSm_code());
				bodyVo.put("sm_nm", mobileVo.getSm_nm());
				body_sm.add(bodyVo);
			}
			body.put("sm", body_sm);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getAppType", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getAppType(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getAppType");
		CommonUtil.setSessionVo(session);

		String c_parent_code = "0000000058";//결재유형
		
		codeList = commonCodeDao.codeComboBox(c_parent_code);
		//System.out.println("codeList=" + codeList);

		if (codeList != null && codeList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_app_type = new JSONArray();

			//Response Body
			for (int i = 0; i < codeList.size(); i++) {
				CodeVo codeVo = codeList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("app_type_code", codeVo.getC_code());
				bodyVo.put("app_type_nm", codeVo.getC_name());
				body_app_type.add(bodyVo);
			}
			body.put("app_type", body_app_type);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getAppStatus", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getAppStatus(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getAppStatus");
		CommonUtil.setSessionVo(session);

		String c_parent_code = "0000000062";//결재상태
		
		codeList = commonCodeDao.codeComboBox(c_parent_code);
		//System.out.println("codeList=" + codeList);

		if (codeList != null && codeList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_app_status = new JSONArray();

			//Response Body
			for (int i = 0; i < codeList.size(); i++) {
				CodeVo codeVo = codeList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("app_status_code", codeVo.getC_code());
				bodyVo.put("app_status_nm", codeVo.getC_name());
				body_app_status.add(bodyVo);
			}
			body.put("app_status", body_app_status);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_getOrder", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getOrder(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getOrder");
		CommonUtil.setSessionVo(session);

		String c_parent_code = "0000000010";//차수
		
		codeList = commonCodeDao.codeComboBox(c_parent_code);
		//System.out.println("codeList=" + codeList);

		if (codeList != null && codeList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_order = new JSONArray();

			//Response Body
			for (int i = 0; i < codeList.size(); i++) {
				CodeVo codeVo = codeList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("order_code", codeVo.getC_code());
				bodyVo.put("order_nm", codeVo.getC_name());
				body_order.add(bodyVo);
			}
			body.put("order", body_order);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}	

	@RequestMapping(value = "/m_getBranchListPerCompany", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getBranchListPerCompany(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getBranchListPerCompany");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String cm_code = reqMobileVo.getCm_code();
		//System.out.println("cm_code=" + cm_code);

		//Check Required Parameter
		if (StringCheck.isNull(cm_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}

		mobileList = dao.getBranchListPerCompany(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_om = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("om_code", mobileVo.getOm_code());
				bodyVo.put("om_nm", mobileVo.getOm_nm());
				body_om.add(bodyVo);
			}
			body.put("om", body_om);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getEmployeeListPerBranch", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getEmployeeListPerBranch(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getEmployeeListPerBranch");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String om_code = reqMobileVo.getOm_code();
		//System.out.println("om_code=" + om_code);

		//Check Required Parameter
		if (StringCheck.isNull(om_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String type = reqMobileVo.getType();
		String search_nm = reqMobileVo.getSearch_nm();
		//System.out.println("type=" + type);
		//System.out.println("search_nm=" + search_nm);
		
		String em_dty_code = CommonUtil.getDutyCode(type);
		reqMobileVo.setEm_dty_code(em_dty_code);
		
		mobileList = dao.getEmployeeListPerBranch(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_employee = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("em_no", mobileVo.getEm_no());
				bodyVo.put("em_id", mobileVo.getEm_id());
				bodyVo.put("em_nm", mobileVo.getEm_nm());
				body_employee.add(bodyVo);
			}
			body.put("employee", body_employee);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}

	@RequestMapping(value = "/m_getEmployeeListPerStore", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getEmployeeListPerStore(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getEmployeeListPerStore");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String sm_code = reqMobileVo.getSm_code();
		//System.out.println("sm_code=" + sm_code);

		//Check Required Parameter
		if (StringCheck.isNull(sm_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String type = reqMobileVo.getType();
		String search_nm = reqMobileVo.getSearch_nm();
		//System.out.println("type=" + type);
		//System.out.println("search_nm=" + search_nm);
		
		String em_dty_code = CommonUtil.getDutyCode(type);
		reqMobileVo.setEm_dty_code(em_dty_code);
		
		mobileList = dao.getEmployeeListPerStore(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_employee = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("em_no", mobileVo.getEm_no());
				bodyVo.put("em_id", mobileVo.getEm_id());
				bodyVo.put("em_nm", mobileVo.getEm_nm());
				bodyVo.put("em_type_nm", mobileVo.getEm_type_nm());
				body_employee.add(bodyVo);
			}
			body.put("employee", body_employee);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_getMenuList", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getMenuList(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getMenuList");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		int auth_flag = reqMobileVo.getAuth_flag();
		//System.out.println("auth_flag=" + auth_flag);

		//Check Required Parameter
		if (auth_flag < 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		String filePath = session.getServletContext().getRealPath("/properties");
		String fileName = null;
		if (auth_flag == 1) {
			fileName = "menuListOfMNGR.txt";
		} else if (auth_flag == 2) {
			fileName = "menuListOfTIMHDR.txt";
		} else if (auth_flag == 3) {
			fileName = "menuListOfFIX.txt";
		} else if (auth_flag == 4) {
			fileName = "menuListOfRND.txt";
		}
		
		JSONObject body = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			File file = new File(filePath, fileName);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			body = JSONObject.fromObject(sb.toString());
			
			response = CommonUtil.setSuccessResponse(body);
		} catch (Exception e) {
			response = CommonUtil.setFailResponse(Constant.MENU_LIST_FAILED_CD, Constant.MENU_LIST_FAILED_MSG);
		} finally {
			if (br != null) { try{br.close();}catch(IOException e){} }			
			if (fr != null) { try{fr.close();}catch(IOException e){} }
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_getCommunityList", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getCommunityList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getCommunityList");
		CommonUtil.setSessionVo(session);

		//Required Parameter
        int auth_flag = reqMobileNaviVo.getAuth_flag();
        String cm_code = reqMobileNaviVo.getCm_code();
        String om_code = reqMobileNaviVo.getOm_code();
        String em_no = reqMobileNaviVo.getEm_no();
		String kind = reqMobileNaviVo.getKind();
		int rowSize = reqMobileNaviVo.getRowSize();
		int currPg = reqMobileNaviVo.getCurrPg();
		
		//System.out.println("auth_flag=" + auth_flag);
		//System.out.println("cm_code=" + cm_code);
		//System.out.println("om_code=" + om_code);
		//System.out.println("em_no=" + em_no);
		//System.out.println("kind=" + kind);
		//System.out.println("rowSize=" + rowSize);
		//System.out.println("currPg=" + currPg);
		
		//Check Required Parameter
		if (auth_flag < 0 || StringCheck.isNull(cm_code) || StringCheck.isNull(om_code) || StringCheck.isNull(em_no) || StringCheck.isNull(kind) || rowSize == 0 || currPg == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		String search_date_to = new SimpleDateFormat("yyyyMMdd").format(new Date());//검색종료일(현재날짜)
		String search_date_from = CommonUtil.getCalcDe(search_date_to, -365);//검색시작일(1년전 날짜)
        String yyyyMm = new SimpleDateFormat("yyyyMM").format(new Date());//주요행사년월
        String t_days = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//주요행사일(현재날짜)
		//System.out.println("search_date_from=" + search_date_from);
		//System.out.println("search_date_to=" + search_date_to);
		//System.out.println("yyyyMm=" + yyyyMm);
		//System.out.println("t_days=" + t_days);
		
		JSONObject body = new JSONObject();
		
		try {
			//공지사항 목록조회
			Map<String, String> noticeParams = new HashMap<String, String>();
			noticeParams.put("auth_flag", String.valueOf(auth_flag));//권한코드
			noticeParams.put("cm_code", cm_code);//회사코드
			noticeParams.put("em_no", em_no);//사원번호
			noticeParams.put("jijumOmCode", "");//지점코드
			noticeParams.put("jijumWord", "");//매장명
			noticeParams.put("key", "sj");//검색구분
			noticeParams.put("word", "");
			noticeParams.put("search_date_from", search_date_from);
			noticeParams.put("search_date_to", search_date_to);
			reqMobileNaviVo.setParams(noticeParams);
			
			reqMobileNaviVo = (MobileNaviVo) noticeDao.noticeListCnt(reqMobileNaviVo);
			
			List<NoticeVo> noticeList = noticeDao.noticeList(reqMobileNaviVo);
			//System.out.println("noticeList=" + noticeList);
			
			if (noticeList != null && noticeList.size() > 0) {
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
				body.put("notice", body_notice);
			}
			
			//주요행사 목록조회
			MainEventVo reqMainEventVo = new MainEventVo();
			reqMainEventVo.setKind(kind);
			reqMainEventVo.setAuth_flag(auth_flag);
			reqMainEventVo.setEm_no(em_no);
			reqMainEventVo.setYyyyMm(yyyyMm);
			reqMainEventVo.setOm_code(om_code);
			reqMainEventVo.setT_days(t_days);
			
			List<MainEventVo> mainEventList = mainEventDao.selectScheduleList(reqMainEventVo);
			//System.out.println("mainEventList=" + mainEventList);
			
			if (mainEventList != null && mainEventList.size() > 0) {
				JSONArray body_main_event = new JSONArray();

				//Response Body
				for (int i = 0; i < mainEventList.size(); i++) {
					MainEventVo mainEventVo = mainEventList.get(i);
					JSONObject bodyVo = new JSONObject();
					bodyVo.put("t_days", mainEventVo.getT_days());
					bodyVo.put("me_sj", mainEventVo.getMe_sj());
					bodyVo.put("me_cn", mainEventVo.getMe_cn());
					bodyVo.put("me_allday_at", mainEventVo.getMe_allday_at());
					bodyVo.put("schdul_bgnde_hhmm", mainEventVo.getSchdul_bgnde_hhmm());
					bodyVo.put("schdul_endde_hhmm", mainEventVo.getSchdul_endde_hhmm());
					body_main_event.add(bodyVo);
				}
				body.put("main_event", body_main_event);
			}
			
			/*
			//업무지시 목록조회
			Map<String, String> businessOrderParams = new HashMap<String, String>();
			businessOrderParams.put("auth_flag", String.valueOf(auth_flag));//권한코드
			businessOrderParams.put("cm_code", cm_code);//회사코드
			businessOrderParams.put("em_no", em_no);//사원번호
			
			businessOrderParams.put("jijumOmCode", "");//지점코드
			businessOrderParams.put("jijumWord", "");//매장명
			businessOrderParams.put("em_nm", "");//사원명
			businessOrderParams.put("key", "");//검색구분
			businessOrderParams.put("word", "");
			businessOrderParams.put("search_date_from", search_date_from);
			businessOrderParams.put("search_date_to", search_date_to);
			reqMobileNaviVo.setParams(businessOrderParams);
			
	        reqMobileNaviVo = (MobileNaviVo) businessOrderDao.businessOrderListCnt(reqMobileNaviVo);
	        
	        List<BusinessOrderVo> businessOrderList = businessOrderDao.businessOrderList(reqMobileNaviVo);
	        //System.out.println("businessOrderList=" + businessOrderList);
	        
			if (businessOrderList != null && businessOrderList.size() > 0) {
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
				body.put("business", body_business);
			}
			*/
			
			/*
			//알림메세지 목록조회
			Map<String, String> noticeMessageParams = new HashMap<String, String>();
			noticeMessageParams.put("auth_flag", String.valueOf(auth_flag));//권한코드
			noticeMessageParams.put("cm_code", cm_code);//회사코드
			noticeMessageParams.put("em_no", em_no);//사원번호
			noticeMessageParams.put("jijumOmCode70400", "");//지점코드
			noticeMessageParams.put("jijumWord70400", "");//매장코드
			noticeMessageParams.put("receiver70400", "");//수신자 이름
			noticeMessageParams.put("key70400", "cn");//검색구분
			noticeMessageParams.put("word70400", "");
			noticeMessageParams.put("search_date_from", search_date_from);
			noticeMessageParams.put("search_date_to", search_date_to);
			reqMobileNaviVo.setParams(noticeMessageParams);
			
			reqMobileNaviVo = (MobileNaviVo) noticeMessageDao.noticeMessageListCnt(reqMobileNaviVo);
			
			List<NoticeMessageVo> noticeMessageList = noticeMessageDao.noticeMessageList(reqMobileNaviVo);
			//System.out.println("noticeMessageList=" + noticeMessageList);
			
			if (noticeMessageList != null && noticeMessageList.size() > 0) {
				JSONArray body_notice_message = new JSONArray();

				//Response Body
				for (int i = 0; i < noticeMessageList.size(); i++) {
					NoticeMessageVo noticeMessageVo = noticeMessageList.get(i);
					JSONObject bodyVo = new JSONObject();
					bodyVo.put("nm_innb", noticeMessageVo.getNm_innb());
					bodyVo.put("nm_message", noticeMessageVo.getNm_massage());
					bodyVo.put("regist_man", noticeMessageVo.getRegist_man());
					bodyVo.put("regist_de", noticeMessageVo.getRegist_de());
					body_notice_message.add(bodyVo);
				}
				body.put("notice_message", body_notice_message);
			}
			*/
			
			response = CommonUtil.setSuccessResponse(body);
		} catch (Exception e) {
			response = CommonUtil.setFailResponse(Constant.COMMUNITY_LIST_FAILED_CD, Constant.COMMUNITY_LIST_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_getMenuNoticeCnt", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getMenuNoticeCnt(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getMenuNoticeCnt");
		CommonUtil.setSessionVo(session);

		//Required Parameter
        int auth_flag = reqMobileNaviVo.getAuth_flag();
        String cm_code = reqMobileNaviVo.getCm_code();
        String em_no = reqMobileNaviVo.getEm_no();
		//System.out.println("auth_flag=" + auth_flag);
		//System.out.println("cm_code=" + cm_code);
		//System.out.println("em_no=" + em_no);
		
		//Check Required Parameter
		if (auth_flag < 0 || StringCheck.isNull(cm_code) || StringCheck.isNull(em_no)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		JSONObject body = new JSONObject();
		try {
			//메인화면 메뉴 알림개수 조회(결재상신 개수)
	        String approval_em_no = "";//상신자
	        String approval_approver_em_no = "";//결재자
			String kind = "";//상신(SEND),수신(RECEIVE)
			String am_status = "0000000063";//결재상신
			
			if (auth_flag == 2) {
				//팀장인 경우
				kind = "RECEIVE";
				approval_em_no = "";
				approval_approver_em_no = em_no;
			} else if (auth_flag == 3 || auth_flag == 4) {
				//고정여사원, 순회여사원인 경우
				kind = "SEND";
				approval_em_no = em_no;
				approval_approver_em_no = "";
			}
			
	        Map<String, String> approvalParams = new HashMap<String, String>();
	        approvalParams.put("flag", "");
	        approvalParams.put("ad_date_from", "");
	        approvalParams.put("ad_date_to", "");
	        approvalParams.put("om_code", "");
	        approvalParams.put("em_no", approval_em_no);
	        approvalParams.put("em_nm", "");
	        approvalParams.put("am_approver_em_no", approval_approver_em_no);
	        approvalParams.put("am_status", am_status);
	        reqMobileNaviVo.setParams(approvalParams);

	        reqMobileNaviVo = (MobileNaviVo) approvalDao.selectApprovalListCount(reqMobileNaviVo);
	        //System.out.println("reqMobileNaviVo=" + reqMobileNaviVo);
	        
	        if (reqMobileNaviVo != null) {
				JSONArray body_approval = new JSONArray();

				//Response Body
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("approval_cnt", reqMobileNaviVo.getTotRow());
				body_approval.add(bodyVo);
				body.put("approval", body_approval);
	        }
	        
	        //메인화면 메뉴 알림개수 조회(업무지시 개수)
			Map<String, String> businessOrderParams = new HashMap<String, String>();
			businessOrderParams.put("auth_flag", String.valueOf(auth_flag));//권한코드
			businessOrderParams.put("cm_code", cm_code);//회사코드
			businessOrderParams.put("em_no", em_no);//사원번호
			businessOrderParams.put("jijumOmCode", "");//지점코드
			businessOrderParams.put("jijumWord", "");//매장명
			businessOrderParams.put("em_nm", "");//사원명
			businessOrderParams.put("key", "");//검색구분
			businessOrderParams.put("word", "");
			businessOrderParams.put("search_date_from", "");
			businessOrderParams.put("search_date_to", "");
			businessOrderParams.put("type", "CNT");
			reqMobileNaviVo.setParams(businessOrderParams);
			
			reqMobileNaviVo = (MobileNaviVo) businessOrderDao.businessOrderListCnt(reqMobileNaviVo);
	        //System.out.println("reqMobileNaviVo=" + reqMobileNaviVo);
	        
	        if (reqMobileNaviVo != null) {
				JSONArray body_business = new JSONArray();

				//Response Body
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("business_cnt", reqMobileNaviVo.getTotRow());
				body_business.add(bodyVo);
				body.put("business", body_business);
	        }
	        
			//메인화면 메뉴 알림개수 조회(알림메세지 개수)
			Map<String, String> noticeMessageParams = new HashMap<String, String>();
			noticeMessageParams.put("auth_flag", String.valueOf(auth_flag));//권한코드
			noticeMessageParams.put("cm_code", cm_code);//회사코드
			noticeMessageParams.put("em_no", em_no);//사원번호
			noticeMessageParams.put("jijumOmCode70400", "");//지점코드
			noticeMessageParams.put("jijumWord70400", "");//매장코드
			noticeMessageParams.put("receiver70400", "");//수신자 이름
			noticeMessageParams.put("key70400", "cn");//검색구분
			noticeMessageParams.put("word70400", "");
			noticeMessageParams.put("search_date_from", "");
			noticeMessageParams.put("search_date_to", "");
			noticeMessageParams.put("type", "CNT");
			reqMobileNaviVo.setParams(noticeMessageParams);
			
			reqMobileNaviVo = (MobileNaviVo) noticeMessageDao.noticeMessageListCnt(reqMobileNaviVo);
			//System.out.println("reqMobileNaviVo=" + reqMobileNaviVo);
			
	        if (reqMobileNaviVo != null) {
				JSONArray body_notice_msg = new JSONArray();

				//Response Body
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("notice_msg_cnt", reqMobileNaviVo.getTotRow());
				body_notice_msg.add(bodyVo);
				body.put("notice_msg", body_notice_msg);
	        }	        
	        
			response = CommonUtil.setSuccessResponse(body);
		} catch (Exception e) {
			response = CommonUtil.setFailResponse(Constant.MENU_NOTICE_CNT_FAILED_CD, Constant.MENU_NOTICE_CNT_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_getCommuteTime", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getCommuteTime(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getCommuteTime");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String em_no = reqMobileVo.getEm_no();
		String search_de = reqMobileVo.getSearch_de();
		//System.out.println("em_no=" + em_no);
		//System.out.println("search_de=" + search_de);
		
		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(search_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		search_de = search_de.replaceAll("-", "");
		reqMobileVo.setSearch_de(search_de);
		
		resMobileVo = dao.getCommuteTime(reqMobileVo);
		//System.out.println("resMobileVo=" + resMobileVo);

		if (resMobileVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_commute = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("attending_time", resMobileVo.getAttending_time());
			bodyVo.put("attending_image_url", resMobileVo.getAttending_image_url());
			bodyVo.put("leaving_time", resMobileVo.getLeaving_time());
			bodyVo.put("leaving_image_url", resMobileVo.getLeaving_image_url());
			body_commute.add(bodyVo);
			body.put("commute", body_commute);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}	
	
	@RequestMapping(value = "/m_setCommuteTime", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_setCommuteTime(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_setCommuteTime");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String type = reqMobileVo.getType();
		String em_no = reqMobileVo.getEm_no();
		String om_code = reqMobileVo.getOm_code();
		String curr_de = reqMobileVo.getCurr_de();
		int am_no = reqMobileVo.getAm_no();
		//System.out.println("type=" + type);
		//System.out.println("em_no=" + em_no);
		//System.out.println("om_code=" + om_code);
		//System.out.println("curr_de=" + curr_de);
		//System.out.println("am_no=" + am_no);
		
		//Check Required Parameter
		if (StringCheck.isNull(type) || StringCheck.isNull(em_no) || StringCheck.isNull(om_code) || StringCheck.isNull(curr_de) || am_no == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		curr_de = curr_de.replaceAll("-", "");
		reqMobileVo.setCurr_de(curr_de);
		reqMobileVo.setRegist_man(em_no);
		reqMobileVo.setUpdt_man(em_no);
		
		if (type.equals("ATTEND")) {
			resMobileVo = dao.setAttendingTime(reqMobileVo);
		} else if (type.equals("LEAVE")) {
			resMobileVo = dao.setLeavingTime(reqMobileVo);
		}
		//System.out.println("resMobileVo=" + resMobileVo);

		if (resMobileVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_commute = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			if (type.equals("ATTEND")) {
				bodyVo.put("commute_time", resMobileVo.getAttending_time());
			} else if (type.equals("LEAVE")) {
				bodyVo.put("commute_time", resMobileVo.getLeaving_time());
			}
			body_commute.add(bodyVo);
			body.put("commute", body_commute);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_getRndPlanList", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getRndPlanList(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getRndPlanList");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String em_no = reqMobileVo.getEm_no();//사원번호
		String plan_de = reqMobileVo.getPlan_de();//순방계획일
		String base_de = reqMobileVo.getBase_de();//순방기준일
		//System.out.println("em_no=" + em_no);
		//System.out.println("plan_de=" + plan_de);
		//System.out.println("base_de=" + base_de);

		//Check Required Parameter
		if (StringCheck.isNull(em_no) || StringCheck.isNull(plan_de) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		plan_de = plan_de.replaceAll("-", "");
		base_de = base_de.replaceAll("-", "");
		reqMobileVo.setPlan_de(plan_de);
		reqMobileVo.setBase_de(CommonUtil.getBaseDe(base_de));
		
		mobileList = dao.getRndPlanList(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_rnd_plan = new JSONArray();
			String prd_partclr_matter = "";

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("prdi_sm_code", mobileVo.getPrdi_sm_code());
				bodyVo.put("prdi_sm_nm", mobileVo.getPrdi_sm_nm());
				bodyVo.put("prdi_em_nm", mobileVo.getPrdi_em_nm());
				bodyVo.put("prdi_attend_at", mobileVo.getPrdi_attend_at());
				prd_partclr_matter = mobileVo.getPrd_partclr_matter();
				body_rnd_plan.add(bodyVo);
			}
			body.put("rnd_plan", body_rnd_plan);
			body.put("prd_partclr_matter", prd_partclr_matter);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping(value = "/m_setRndPlan", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_setRndPlan(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_setRndPlan");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String cm_code = reqMobileVo.getCm_code();//회사코드
		String om_code = reqMobileVo.getOm_code();//지점코드
		String em_no = reqMobileVo.getEm_no();//사원번호
		String plan_de = reqMobileVo.getPlan_de();//순방계획일
		String base_de = reqMobileVo.getBase_de();//순방기준일
		//System.out.println("cm_code=" + cm_code);
		//System.out.println("om_code=" + om_code);
		//System.out.println("em_no=" + em_no);
		//System.out.println("plan_de=" + plan_de);
		//System.out.println("base_de=" + base_de);

		//Check Required Parameter
		if (StringCheck.isNull(cm_code) || StringCheck.isNull(om_code) || StringCheck.isNull(em_no) || StringCheck.isNull(plan_de) || StringCheck.isNull(base_de)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		//Optional Parameter
		String prd_partclr_matter = StringCheck.isNull(reqMobileVo.getPrd_partclr_matter(), "");//순방비고
		//System.out.println("prd_partclr_matter=" + prd_partclr_matter);
		
		plan_de = plan_de.replaceAll("-", "");
		base_de = base_de.replaceAll("-", "");
		reqMobileVo.setPlan_de(plan_de);
		reqMobileVo.setBase_de(CommonUtil.getBaseDe(base_de));
		
		reqMobileVo.setPrd_partclr_matter(prd_partclr_matter);
		reqMobileVo.setRegist_man(em_no);
		reqMobileVo.setUpdt_man(em_no);
		
		int result = dao.setRndPlan(reqMobileVo);
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
			response = CommonUtil.setFailResponse(Constant.RND_PLAN_FAILED_CD, Constant.RND_PLAN_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}	
	
	@RequestMapping(value = "/m_getFileInfo", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_getFileInfo(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_getFileInfo");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		int am_no = reqMobileVo.getAm_no();
		//System.out.println("am_no=" + am_no);

		//Check Required Parameter
		if (am_no == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		mobileList = dao.getFileInfo(reqMobileVo);
		//System.out.println("mobileList=" + mobileList);

		if (mobileList != null && mobileList.size() > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_file_info = new JSONArray();

			//Response Body
			for (int i = 0; i < mobileList.size(); i++) {
				MobileVo mobileVo = mobileList.get(i);
				JSONObject bodyVo = new JSONObject();
				bodyVo.put("ai_no", mobileVo.getAi_no());
				bodyVo.put("ai_path", mobileVo.getAi_path());
				bodyVo.put("ai_nm", mobileVo.getAi_nm());
				bodyVo.put("ai_size", mobileVo.getAi_size());
				body_file_info.add(bodyVo);
			}
			body.put("file_info", body_file_info);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/m_fileUpload")
	public ModelAndView m_fileUpload(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]모바일 파일업로드 : " + "/m_fileUpload am_no : "+ reqMobileVo.getAm_no()+", em_no"+reqMobileVo.getEm_no());
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String req_type = reqMobileVo.getType();//유형
		String req_em_no = reqMobileVo.getEm_no();//사원번호
		int req_am_no = reqMobileVo.getAm_no();//첨부파일 고유번호
		int req_files_length = reqMobileVo.getFiles().length;//첨부파일 개수
		//System.out.println("req_type=" + req_type);
		//System.out.println("req_em_no=" + req_em_no);
		//System.out.println("req_am_no=" + req_am_no);
		//System.out.println("req_files_length=" + req_files_length);
		
		//Check Required Parameter
		if (StringCheck.isNull(req_type) || StringCheck.isNull(req_em_no) || req_files_length == 0) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		int res_am_no = attachManager.updateFile(reqMobileVo.getFiles(), req_am_no, req_em_no, req_type);
		//System.out.println("res_am_no=" + res_am_no);
		
		if (res_am_no > 0) {
			JSONObject body = new JSONObject();
			JSONArray body_am_no = new JSONArray();

			JSONObject bodyVo = new JSONObject();
			bodyVo.put("am_no", res_am_no);
			body_am_no.add(bodyVo);
			body.put("am_no", body_am_no);
			System.out.println("[" + CommonUtil.getCurrentDateTime() + "]모바일 파일업로드 return am_no : " +res_am_no);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			System.out.println("[" + CommonUtil.getCurrentDateTime() + "]모바일 파일업로드 실패 am_no : " +res_am_no);
			response = CommonUtil.setFailResponse(Constant.FILE_UPLOAD_FAILED_CD, Constant.FILE_UPLOAD_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
	@RequestMapping("/m_fileDownload")
	@ResponseBody
	public FileSystemResource m_fileDownload(@ModelAttribute("vo") MobileVo reqMobileVo, HttpServletResponse response) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_fileDownload");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		int ai_no = reqMobileVo.getAi_no();
		//System.out.println("ai_no=" + ai_no);
		
		return attachManager.downloadFile(ai_no, response);
	}
	
	@RequestMapping("/m_fileDownload_apk")
	@ResponseBody
	public FileSystemResource m_fileDownload_apk(@ModelAttribute("vo") MobileVo reqMobileVo, HttpServletResponse response) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_fileDownload_apk");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		int ai_no = reqMobileVo.getAi_no();
		//System.out.println("ai_no=" + ai_no);
		
		return attachManager.downloadFile_apk(ai_no, response);
	}
	
	@RequestMapping(value = "/m_setDeviceError", produces = "text/plain;charset=UTF-8")
	public ModelAndView m_setDeviceError(@ModelAttribute("vo") MobileVo reqMobileVo) throws Exception {
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_setDeviceError");
		CommonUtil.setSessionVo(session);

		//Required Parameter
		String om_code = reqMobileVo.getOm_code();//지점코드
		String sm_code = reqMobileVo.getSm_code();//매장코드
		String em_no = reqMobileVo.getEm_no();//사원번호
		String device_type = reqMobileVo.getDevice_type();//디바이스 유형
		String device_os_version = reqMobileVo.getDevice_os_version();//디바이스 OS 버전
		String device_app_version = reqMobileVo.getDevice_app_version();//디바이스 APP 버전
		String device_manufacture = reqMobileVo.getDevice_manufacture();//디바이스 제조사
		String device_model = reqMobileVo.getDevice_model();//디바이스 모델명
		String device_la = reqMobileVo.getDevice_la();//디바이스 위도
		String device_lo = reqMobileVo.getDevice_lo();//디바이스 경도
		String device_error_code = reqMobileVo.getDevice_error_code();//디바이스 오류코드
		String device_error_msg = reqMobileVo.getDevice_error_msg();//디바이스 오류메세지
		
		//System.out.println("om_code=" + om_code);
		//System.out.println("sm_code=" + sm_code);
		//System.out.println("em_no=" + em_no);
		//System.out.println("device_type=" + device_type);
		//System.out.println("device_os_version=" + device_os_version);
		//System.out.println("device_app_version=" + device_app_version);
		//System.out.println("device_manufacture=" + device_manufacture);
		//System.out.println("device_model=" + device_model);
		//System.out.println("device_la=" + device_la);
		//System.out.println("device_lo=" + device_lo);
		//System.out.println("device_error_code=" + device_error_code);
		//System.out.println("device_error_msg=" + device_error_msg);
		
        // Check Required Parameter
        if (StringCheck.isNull(om_code) || StringCheck.isNull(sm_code) || StringCheck.isNull(em_no) || StringCheck.isNull(device_type) || StringCheck.isNull(device_os_version) || StringCheck.isNull(device_app_version) || StringCheck.isNull(device_manufacture) || StringCheck.isNull(device_model) || StringCheck.isNull(device_la) || StringCheck.isNull(device_lo) || StringCheck.isNull(device_error_code) || StringCheck.isNull(device_error_msg)) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }
		
		reqMobileVo.setRegist_man(em_no);
		reqMobileVo.setUpdt_man(em_no);
		
		int result = dao.setDeviceError(reqMobileVo);
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
			response = CommonUtil.setFailResponse(Constant.DEVICE_ERROR_FAILED_CD, Constant.DEVICE_ERROR_FAILED_MSG);
		}
		
		return CommonUtil.setModelAndView(response);
	}
	
    @RequestMapping(value = "/m_checkAppUpdate", produces="text/plain;charset=UTF-8")
    public ModelAndView m_checkAppUpdate(@ModelAttribute("vo") LoginVo reqLoginVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_checkAppUpdate");
        CommonUtil.setSessionVo(session);

        //Required Parameter
        String req_em_device_type = reqLoginVo.getEm_device_type();//디바이스 유형
        int req_em_device_version = reqLoginVo.getEm_device_version();//디바이스 버전
        //System.out.println("req_em_device_type=" + req_em_device_type);
        //System.out.println("req_em_device_version=" + req_em_device_version);

        //Check Required Parameter
        if (StringCheck.isNull(req_em_device_type) || req_em_device_version == 0) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }
        
        JSONObject jsonObject = CommonUtil.getUpdateInfo(session);
        int and_ver = jsonObject.getInt("and_ver");
        String and_url = jsonObject.getString("and_url");
        int ios_ver = jsonObject.getInt("ios_ver");
        String ios_url = jsonObject.getString("ios_url");        
        //System.out.println("and_ver=" + and_ver);
        //System.out.println("and_url=" + and_url);
        //System.out.println("ios_ver=" + ios_ver);
        //System.out.println("ios_url=" + ios_url);
        
        //디바이스 APP 업데이트 확인
        if (req_em_device_type.equals("A")) {
        	if (and_ver > req_em_device_version) {
                response = CommonUtil.setFailResponse(Constant.APP_UPDATE_FAILED_CD, and_url);
                return CommonUtil.setModelAndView(response);
        	}
        } else if (req_em_device_type.equals("I")) {
        	if (ios_ver > req_em_device_version) {
                response = CommonUtil.setFailResponse(Constant.APP_UPDATE_FAILED_CD, ios_url);
                return CommonUtil.setModelAndView(response);
        	}
        }
        
        JSONObject body = new JSONObject();
        response = CommonUtil.setSuccessResponse(body);
        return CommonUtil.setModelAndView(response);
    }
	
}
