package com.dasa.approval.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.approval.dao.ApprovalDao;
import com.dasa.approval.vo.ApprovalVo;
import com.dasa.login.vo.LoginVo;
import com.dasa.mobile.vo.MobileVo;
import com.vertexid.utill.AttachManager;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.utill.StringCheck;
import com.vertexid.vo.AttachVo;
import com.vertexid.vo.MobileNaviVo;
import com.vertexid.vo.NaviVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ ApprovalController.java
 * @ 2015. 10. 18.
 * @
 * @ 프로그램명: ApprovalController
 */
@Controller
public class ApprovalController {
	
	@Autowired
	private HttpSession session;
    @Autowired
    private ApprovalDao dao;
    @Autowired
    private AttachManager am;

    private ModelAndView mv;
    private JSONObject jo;
    private JSONArray ja;
    private JSONObject response;
    
    private ApprovalVo av;
    private List<ApprovalVo> avList;
    private List<AttachVo> attachVoList;

    /**
     * @메서드명: approvalPg
     * @작성일: 2014. 9. 30
     * @작성자: 김진호
     * @설명: 화면 이동
     * @return "String"
     */
    @RequestMapping("/30/30-100/{typeCode}")
    public ModelAndView approvalPg(@PathVariable("typeCode") String typeCode) throws Exception {
        //VAL_OM_CODE = typeCode;
        mv = new ModelAndView();
        mv.addObject("typeCode", typeCode);
        mv.setViewName("/30/30-100");
        return mv;
    }

    /**
     * @메서드명: approvalPg
     * @작성일: 2014. 9. 30
     * @작성자: 김진호
     * @설명: 화면 이동
     * @return "String"
     */
    @RequestMapping("/30/30-400/{typeCode}")
    public ModelAndView approvalReceivePg(@PathVariable("typeCode") String typeCode) throws Exception {
        //VAL_OM_CODE = typeCode;
        mv = new ModelAndView();
        mv.addObject("typeCode", typeCode);
        mv.setViewName("/30/30-400");
        return mv;
    }

    /**
     * @메서드명: approvalList
     * @작성일: 2015. 10. 18
     * @작성자: 최수영
     * @설명: 전자결재리스트
     */
    @RequestMapping("/approval/list")
    public ModelAndView approvalList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
    	String login_cp_cd = (String) session.getAttribute("login_cp_cd");
    	naviVo.setCm_code(login_cp_cd);
    	
        naviVo = dao.selectApprovalListCount(naviVo);
        String navi = new Navi(naviVo).getPageNavi();

        avList =  dao.selectApprovalList(naviVo);

        jo = new JSONObject();
        ja = JSONArray.fromObject(avList);
        System.out.println(ja);

        jo.put("result", ja);
        jo.put("navi",navi);
        jo.put("firstNo", naviVo.getFirstRowNo());
        
        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", jo);

        return mv;
    }
    
    /**
     * @메소드명: m_approvalList
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /approval/m_approvalList
     */
    @RequestMapping(value = "/approval/m_approvalList", produces = "text/plain;charset=UTF-8")
    public ModelAndView m_approvalList(@ModelAttribute("vo") MobileNaviVo reqMobileNaviVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/approval/m_approvalList");
        CommonUtil.setSessionVo(session);

        //Required Parameter
        String kind = reqMobileNaviVo.getKind();//상신(SEND),수신(RECEIVE)
        String type = reqMobileNaviVo.getType();//신청(APPLY),승인(APPROVAL),반려(REJECT)
        String ad_date_from = reqMobileNaviVo.getAd_date_from();
        String ad_date_to = reqMobileNaviVo.getAd_date_to();
        int rowSize = reqMobileNaviVo.getRowSize();
        int currPg = reqMobileNaviVo.getCurrPg();
        //System.out.println("kind=" + kind);
        //System.out.println("type=" + type);
        //System.out.println("ad_date_from=" + ad_date_from);
        //System.out.println("ad_date_to=" + ad_date_to);
        //System.out.println("rowSize=" + rowSize);
        //System.out.println("currPg=" + currPg);

        //Check Required Parameter
        if (StringCheck.isNull(kind) || StringCheck.isNull(type) || StringCheck.isNull(ad_date_from) || StringCheck.isNull(ad_date_to) || rowSize == 0 || currPg == 0) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }

        //Optional Parameter
        String em_no = reqMobileNaviVo.getEm_no();//상신자
        String am_approver_em_no = reqMobileNaviVo.getAm_approver_em_no();//결재자
        //System.out.println("em_no=" + em_no);
        //System.out.println("am_approver_em_no=" + am_approver_em_no);

        //Check Optional Parameter
        if (kind.equals("SEND") && StringCheck.isNull(em_no)) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        } else if (kind.equals("RECEIVE") && StringCheck.isNull(am_approver_em_no)) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }

        String am_status = "";
        if (type.equals("APPLY")) {
            am_status = "0000000063";//결재상신
        } else if (type.equals("APPROVAL")) {
            am_status = "0000000064";//결재완료
        } else if (type.equals("REJECT")) {
            am_status = "0000000065";//결재반려
        }
        
        ad_date_from = ad_date_from.replaceAll("-", "");
        ad_date_to = ad_date_to.replaceAll("-", "");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("flag", "B");
        params.put("ad_date_from", ad_date_from);
        params.put("ad_date_to", ad_date_to);
        params.put("om_code", "");
        params.put("em_no", em_no);
        params.put("em_nm", "");
        params.put("am_approver_em_no", am_approver_em_no);
        params.put("am_status", am_status);
        reqMobileNaviVo.setParams(params);

        reqMobileNaviVo = (MobileNaviVo) dao.selectApprovalListCount(reqMobileNaviVo);

        List<ApprovalVo> approvalList =  dao.selectApprovalList(reqMobileNaviVo);
        //System.out.println("approvalList=" + approvalList);

        if (approvalList != null && approvalList.size() > 0) {
            JSONObject body = new JSONObject();
            JSONArray body_approval = new JSONArray();

            //Response Body
            for (int i = 0; i < approvalList.size(); i++) {
                ApprovalVo approvalVo = approvalList.get(i);
                JSONObject bodyVo = new JSONObject();
                bodyVo.put("am_code", approvalVo.getAm_code());//관리번호
                bodyVo.put("ad_date_from", approvalVo.getAd_date_from());//근태 시작일
                bodyVo.put("ad_date_to", approvalVo.getAd_date_to());//근태 종료일
                bodyVo.put("ad_type_nm", approvalVo.getAd_type_nm());//결재유형
                if (kind.equals("RECEIVE")) {
                    bodyVo.put("em_nm", approvalVo.getEm_nm());//기안자
                }
                bodyVo.put("am_approval_date", approvalVo.getAm_approval_date());//기안일
                bodyVo.put("am_status", approvalVo.getAm_status_nm());//결재상태
                if (type.equals("APPROVAL")) {
                    bodyVo.put("signed_date", approvalVo.getUpdt_de());//결재일
                }
                if (type.equals("REJECT")) {
                    bodyVo.put("reject_date", approvalVo.getReject_date());//반려일
                }
                body_approval.add(bodyVo);
            }
            body.put("kind", kind);
            body.put("type", type);
            body.put("rowSize", rowSize);
            body.put("currPg", currPg);
            body.put("totRow", reqMobileNaviVo.getTotRow());
            body.put("approval", body_approval);

            response = CommonUtil.setSuccessResponse(body);
        } else {
            response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
        }

        return CommonUtil.setModelAndView(response);
    }

    /**
     * @메서드명: selectRejectHistoryList
     * @작성일: 2015. 10. 21
     * @작성자: 최수영
     * @설명: 반려 리스트
     */
    @RequestMapping("/approval/rejectHistoryList")
    public ModelAndView selectRejectHistoryList(@RequestParam String am_code) throws Exception{
        avList = dao.selectRejectList(am_code);

        jo = new JSONObject();
        ja = JSONArray.fromObject(avList);
        System.out.println(ja);

        jo.put("result", ja);

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", jo);

        return mv;
    }

    /**
     * @메서드명: approvalRow
     * @작성일: 2015. 10. 18
     * @작성자: 최수영
     * @설명: 전자결재 row
     */
    @RequestMapping("/approval/approvalRow")
    public ModelAndView approvalRow(@RequestParam String am_code, @RequestParam String am_status) throws Exception{
        String em_no = (String) session.getAttribute("login_no");

        ApprovalVo paramVo = new ApprovalVo();

        paramVo.setEm_no(em_no);
        paramVo.setAm_code(am_code);
        paramVo.setAm_status(am_status);

        av =  dao.selectApprovalRow(paramVo);

        jo = new JSONObject();
        ja = JSONArray.fromObject(av);
        jo.put("result", ja);

        if(av.getAm_no()!=null && !av.getAm_no().equals("")){
            attachVoList = am.attachList(Integer.parseInt(av.getAm_no()));
            ja = JSONArray.fromObject(attachVoList);
            jo.put("attachVoList", ja);
        }else{
            jo.put("attachVoList", "");
        }

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", jo);

        return mv;
    }
    
    /**
     * @메소드명: m_approvalRow
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /approval/m_approvalRow
     */
    @RequestMapping(value = "/approval/m_approvalRow", produces = "text/plain;charset=UTF-8")
    public ModelAndView m_approvalRow(@ModelAttribute("vo") ApprovalVo reqApprovalVo) throws Exception{
		System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/approval/m_approvalRow");
		CommonUtil.setSessionVo(session);
		
		//Required Parameter
		String am_code = reqApprovalVo.getAm_code();
		//System.out.println("am_code=" + am_code);
		
		//Check Required Parameter
		if (StringCheck.isNull(am_code)) {
			response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
			return CommonUtil.setModelAndView(response);
		}
		
		ApprovalVo resApprovalVo =dao.selectApprovalRow(reqApprovalVo);
		//System.out.println("resApprovalVo=" + resApprovalVo);
		
		if (resApprovalVo != null) {
			JSONObject body = new JSONObject();
			JSONArray body_approval = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("om_nm", resApprovalVo.getOm_nm());
			bodyVo.put("em_no", resApprovalVo.getEm_no());
			bodyVo.put("em_nm", resApprovalVo.getEm_nm());
			bodyVo.put("am_approval_date", resApprovalVo.getAm_approval_date());
			bodyVo.put("am_approver_em_no", resApprovalVo.getAm_approver_em_no());
			bodyVo.put("am_approver_em_nm", resApprovalVo.getAm_approver_em_nm());
			bodyVo.put("am_status", resApprovalVo.getAm_status());
			bodyVo.put("am_status_nm", resApprovalVo.getAm_status_nm());
			bodyVo.put("ad_type", resApprovalVo.getAd_type());
			bodyVo.put("ad_type_nm", resApprovalVo.getAd_type_nm());
			bodyVo.put("ad_date_from", resApprovalVo.getAd_date_from());
			bodyVo.put("ad_date_from_hhmm", resApprovalVo.getAd_date_from_hhmm().substring(0, 2) + ":" + resApprovalVo.getAd_date_from_hhmm().substring(2, 4));
			bodyVo.put("ad_date_to", resApprovalVo.getAd_date_to());
			bodyVo.put("ad_date_to_hhmm", resApprovalVo.getAd_date_to_hhmm().substring(0, 2) + ":" + resApprovalVo.getAd_date_to_hhmm().substring(2, 4));
			bodyVo.put("ad_reason", resApprovalVo.getAd_reason());
			if (resApprovalVo.getAm_status().equals("0000000064")) {//결재완료
				bodyVo.put("updt_de", resApprovalVo.getUpdt_de());
			}
			if (resApprovalVo.getAm_status().equals("0000000065")) {//결재반려
				bodyVo.put("ar_reason", resApprovalVo.getAr_reason());
				bodyVo.put("reject_date", resApprovalVo.getReject_date());
			}
			bodyVo.put("am_no", resApprovalVo.getAm_no());
			body_approval.add(bodyVo);
			body.put("approval", body_approval);

			response = CommonUtil.setSuccessResponse(body);
		} else {
			response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}

		return CommonUtil.setModelAndView(response);
    }

    /**
     * @메서드명: approvalBaseInfo
     * @작성일: 2015. 10. 19
     * @작성자: 최수영
     * @설명: 전자결재 기본정보
     */
    @RequestMapping("/approval/approvalBaseInfo")
    public ModelAndView approvalBaseInfo() throws Exception{
        //사원번호
        String em_no = (String) session.getAttribute("login_no");
        //사원명
        String em_nm = (String) session.getAttribute("login_nm");
        //지점코드
        String om_code = (String) session.getAttribute("login_bhf_cd");
        //지점명
        String om_nm = (String) session.getAttribute("login_bhf_nm");
        //팀장번호
        String timhdr_no = (String) session.getAttribute("timhdr_no");
        //팀장명
        String timdhr_nm = (String) session.getAttribute("timdhr_nm");
        //직무코드
        String login_dty_cd = (String) session.getAttribute("login_dty_cd");
        //직무명
        String login_dty_nm = (String) session.getAttribute("login_dty_nm");

        ApprovalVo dataVo = new ApprovalVo();
        dataVo.setEm_no(em_no);
        dataVo.setEm_nm(em_nm);
        dataVo.setOm_code(om_code);
        dataVo.setOm_nm(om_nm);
        dataVo.setAm_approver_em_no(timhdr_no);
        dataVo.setAm_approver_em_nm(timdhr_nm);
        dataVo.setEm_dty_code(login_dty_cd);
        dataVo.setEm_dty_nm(login_dty_nm);

        jo = new JSONObject();
        ja = JSONArray.fromObject(dataVo);

        jo.put("result", ja);

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", jo);

        return mv;
    }

    /**
     * @메서드명: saveApproval
     * @작성일: 2015. 10. 18
     * @작성자: 최수영
     * @설명: 전자결재 save
     */
    @RequestMapping("/approval/save")
    public ModelAndView saveApproval(ApprovalVo p_vo) throws Exception {
        String login_no = (String) session.getAttribute("login_no");
        
        int checkCount = 0;
        if(!p_vo.getFlag().equalsIgnoreCase("DELETE")){
        	p_vo.setAd_date_from(p_vo.getAd_date_from().replace("-", "" ));
        	p_vo.setAd_date_to(p_vo.getAd_date_to().replace("-", "" ));
	        
        	checkCount = dao.getCheckCount(p_vo);
        }
        
        if (checkCount > 0){
        	p_vo.setRes_code("-2"); //중복
        }else{
        	p_vo.setRegist_man(login_no);
            p_vo.setUpdt_man(login_no);
            dao.saveApproval(p_vo);
        }
        
        System.out.println("checkCount : " + checkCount);
        jo = new JSONObject();
        ja = JSONArray.fromObject(p_vo);

        jo.put("dto", ja);

        System.out.println(ja);

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", jo);

        return mv;
    }
    
    /**
     * @메소드명: m_saveApproval
     * @작성일: 2015.10.01
     * @작성자: 김광욱
     * @설명: /approval/m_saveApproval
     */
    @RequestMapping(value = "/approval/m_saveApproval", produces = "text/plain;charset=UTF-8")
    public ModelAndView m_saveApproval(ApprovalVo reqApprovalVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/approval/m_saveApproval");
        CommonUtil.setSessionVo(session);
        String kind = "";
        String type = "";
        String om_code = "";
        String em_no = "";
        String am_approver_em_no = "";
        String am_code = "";
        String ad_type = "";
        String ad_date_from = "";
        String ad_date_from_hhmm = "";
        String ad_date_to = "";
        String ad_date_to_hhmm = "";
        String ad_reason = "";
        String ar_reason = "";
        String am_no = "";
        
        //Required Parameter
        kind = reqApprovalVo.getKind();//상신(SEND),수신(RECEIVE)
        type = reqApprovalVo.getType();//신청(APPLY),승인(APPROVAL),반려(REJECT),재상신(UPDATE),삭제(DELETE)
        //System.out.println("kind=" + kind);
        //System.out.println("type=" + type);
        
        //Check Required Parameter
        if (StringCheck.isNull(kind) || StringCheck.isNull(type)) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }
        
        //Optional Parameter
        ad_date_from_hhmm = reqApprovalVo.getAd_date_from_hhmm();//근태 시작시간
        ad_date_to_hhmm = reqApprovalVo.getAd_date_to_hhmm();//근태 종료시간
        am_no = reqApprovalVo.getAm_no();//첨부파일 고유번호
        //System.out.println("ad_date_from_hhmm=" + ad_date_from_hhmm);
        //System.out.println("ad_date_to_hhmm=" + ad_date_to_hhmm);
        //System.out.println("am_no=" + am_no);        
        
        //Check Optional Parameter (kind=SEND,type=APPLY) [결재신청-상신]
        if (kind.equals("SEND") && type.equals("APPLY")) {
            om_code = reqApprovalVo.getOm_code();//기안자 지점코드
            em_no = reqApprovalVo.getEm_no();//기안자 사원번호
            am_approver_em_no = reqApprovalVo.getAm_approver_em_no();//결재자 사원번호
            ad_type = reqApprovalVo.getAd_type();//결재유형
            ad_date_from = reqApprovalVo.getAd_date_from();//근태 시작일
            ad_date_to = reqApprovalVo.getAd_date_to();//근태 종료일
            ad_reason = reqApprovalVo.getAd_reason();//사유
            //System.out.println("om_code=" + om_code);
            //System.out.println("em_no=" + em_no);
            //System.out.println("am_approver_em_no=" + am_approver_em_no);
            //System.out.println("ad_type=" + ad_type);
            //System.out.println("ad_date_from=" + ad_date_from);
            //System.out.println("ad_date_to=" + ad_date_to);
            //System.out.println("ad_reason=" + ad_reason);
            
            if (StringCheck.isNull(om_code) || StringCheck.isNull(em_no) || StringCheck.isNull(am_approver_em_no) || StringCheck.isNull(ad_type) || StringCheck.isNull(ad_date_from) || StringCheck.isNull(ad_date_to) || StringCheck.isNull(ad_reason)) {
                response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
                return CommonUtil.setModelAndView(response);
            }
        }
        //Check Optional Parameter (kind=RECEIVE,type=APPROVAL) [결재처리-결재]
        if (kind.equals("RECEIVE") && type.equals("APPROVAL")) {
            am_code = reqApprovalVo.getAm_code();//관리번호
            om_code = reqApprovalVo.getOm_code();//결재자 지점코드
            em_no = reqApprovalVo.getEm_no();//기안자 사원번호
            am_approver_em_no = reqApprovalVo.getAm_approver_em_no();//결재자 사원번호
            ad_date_from = reqApprovalVo.getAd_date_from();//근태 시작일
            ad_date_to = reqApprovalVo.getAd_date_to();//근태 종료일
            //System.out.println("am_code=" + am_code);
            //System.out.println("om_code=" + om_code);
            //System.out.println("em_no=" + em_no);
            //System.out.println("am_approver_em_no=" + am_approver_em_no);
            //System.out.println("ad_date_from=" + ad_date_from);
            //System.out.println("ad_date_to=" + ad_date_to);
            
            if (StringCheck.isNull(am_code) || StringCheck.isNull(om_code) || StringCheck.isNull(em_no) || StringCheck.isNull(am_approver_em_no) || StringCheck.isNull(ad_date_from) || StringCheck.isNull(ad_date_to)) {
                response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
                return CommonUtil.setModelAndView(response);
            }
        }
        //Check Optional Parameter (kind=RECEIVE,type=REJECT) [결재처리-반려]
        if (kind.equals("RECEIVE") && type.equals("REJECT")) {
            am_code = reqApprovalVo.getAm_code();//관리번호
            om_code = reqApprovalVo.getOm_code();//결재자 지점코드
            em_no = reqApprovalVo.getEm_no();//기안자 사원번호
            am_approver_em_no = reqApprovalVo.getAm_approver_em_no();//결재자 사원번호
            ad_date_from = reqApprovalVo.getAd_date_from();//근태 시작일
            ad_date_to = reqApprovalVo.getAd_date_to();//근태 종료일
            ar_reason = reqApprovalVo.getAr_reason();//반려사유
            //System.out.println("am_code=" + am_code);
            //System.out.println("om_code=" + om_code);
            //System.out.println("em_no=" + em_no);
            //System.out.println("am_approver_em_no=" + am_approver_em_no);
            //System.out.println("ad_date_from=" + ad_date_from);
            //System.out.println("ad_date_to=" + ad_date_to);
            //System.out.println("ar_reason=" + ar_reason);

            if (StringCheck.isNull(am_code) || StringCheck.isNull(om_code) || StringCheck.isNull(em_no) || StringCheck.isNull(am_approver_em_no) || StringCheck.isNull(ad_date_from) || StringCheck.isNull(ad_date_to) || StringCheck.isNull(ar_reason)) {
                response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
                return CommonUtil.setModelAndView(response);
            }
        }
        //Check Optional Parameter (kind=SEND,type=UPDATE) [결재재신청-재상신]
        if (kind.equals("SEND") && type.equals("UPDATE")) {
        	am_code = reqApprovalVo.getAm_code();//관리번호
            om_code = reqApprovalVo.getOm_code();//기안자 지점코드
            em_no = reqApprovalVo.getEm_no();//기안자 사원번호
            am_approver_em_no = reqApprovalVo.getAm_approver_em_no();//결재자 사원번호
            ad_date_from = reqApprovalVo.getAd_date_from();//근태 시작일
            ad_date_to = reqApprovalVo.getAd_date_to();//근태 종료일
            ad_reason = reqApprovalVo.getAd_reason();//사유
            //System.out.println("am_code=" + am_code);
            //System.out.println("om_code=" + om_code);
            //System.out.println("em_no=" + em_no);
            //System.out.println("am_approver_em_no=" + am_approver_em_no);
            //System.out.println("ad_date_from=" + ad_date_from);
            //System.out.println("ad_date_to=" + ad_date_to);
            //System.out.println("ad_reason=" + ad_reason);
            
            if (StringCheck.isNull(am_code) || StringCheck.isNull(om_code) || StringCheck.isNull(em_no) || StringCheck.isNull(am_approver_em_no) || StringCheck.isNull(ad_date_from) || StringCheck.isNull(ad_date_to) || StringCheck.isNull(ad_reason)) {
                response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
                return CommonUtil.setModelAndView(response);
            }
        }
        
        //Check Optional Parameter
        if (ad_type.equals("0000000059") && (StringCheck.isNull(ad_date_from_hhmm) || StringCheck.isNull(ad_date_to_hhmm)) ) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }

        String flag = "";
        if (type.equals("APPLY")) {//신청
            flag = "INSERT";
        } else if (type.equals("APPROVAL")) {//승인
            flag = "APPROVAL";
        } else if (type.equals("REJECT")) {//반려
            flag = "REJECT";
        } else if (type.equals("UPDATE")) {//재상신
            flag = "UPDATE";
        } else if (type.equals("DELETE")) {//삭제
            flag = "DELETE";
        }
        reqApprovalVo.setFlag(flag);

        ad_date_from = ad_date_from.replaceAll("-", "");
        ad_date_to = ad_date_to.replaceAll("-", "");
        reqApprovalVo.setAd_date_from(ad_date_from);
        reqApprovalVo.setAd_date_to(ad_date_to);

        ad_date_from_hhmm = StringCheck.isNull(ad_date_from_hhmm, "00:00");
        ad_date_to_hhmm = StringCheck.isNull(ad_date_to_hhmm, "00:00");
        ad_date_from_hhmm = ad_date_from_hhmm.replaceAll(":", "");
        ad_date_to_hhmm = ad_date_to_hhmm.replaceAll(":", "");
        reqApprovalVo.setAd_date_from_hhmm(ad_date_from_hhmm);
        reqApprovalVo.setAd_date_to_hhmm(ad_date_to_hhmm);

        reqApprovalVo.setRegist_man(em_no);
        reqApprovalVo.setUpdt_man(em_no);

        dao.saveApproval(reqApprovalVo);
        ApprovalVo resApprovalVo = reqApprovalVo;
        //System.out.println("resApprovalVo=" + resApprovalVo);
        
        String res_am_code = resApprovalVo.getRes_am_code();//관리번호
        String res_code = resApprovalVo.getRes_code();
        if (res_code.equals("0")) {
			JSONObject body = new JSONObject();
			JSONArray body_am_code = new JSONArray();

			//Response Body
			JSONObject bodyVo = new JSONObject();
			bodyVo.put("am_code", res_am_code);
			body_am_code.add(bodyVo);
			body.put("am_code", body_am_code);
			
			response = CommonUtil.setSuccessResponse(body);
			
			if (!StringCheck.isNull(am_no)) {
				//입력한 첨부파일 고유번호를 저장
				ApprovalVo approvalVo = new ApprovalVo();
				approvalVo.setAm_no(am_no);
				approvalVo.setAm_code(res_am_code);
				
				int result = dao.amNoUpdate(approvalVo);
				if (result > 0) {
					System.out.println("관리번호[" + res_am_code + "]에 첨부파일 고유번호[" + am_no + "]를 저장하였습니다.");
				}
			}
        } else {
        	response = CommonUtil.setFailResponse(Constant.APPROVAL_FAILED_CD, Constant.APPROVAL_FAILED_MSG);
        }
        
        return CommonUtil.setModelAndView(response);
    }

    /**
     * @메서드명: saveApprovalFiles
     * @작성일: 2015. 10. 18
     * @작성자: 최수영
     * @설명: 전자결재 파일 save
     */
    @RequestMapping("/approval/saveFiles")
    public ModelAndView saveApprovalFiles(@RequestParam("files") MultipartFile files[] ,
                                            @RequestParam("am_code") String am_code,
                                            @RequestParam("am_no") String am_no) throws Exception {

        int seq = am.updateFile(files,StringCheck.nullToZero(am_no));

        System.out.println("file seq : " + seq);
        ApprovalVo dataVo = new ApprovalVo();
        dataVo.setAm_code(am_code);
        dataVo.setAm_no(String.valueOf(seq));

        int resultCnt = dao.amNoUpdate(dataVo);

        //jo.put("dto", ja);
        jo = new JSONObject();
        ja = JSONArray.fromObject(resultCnt);
        System.out.println(ja);

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", ja);

        return mv;
    }
}
