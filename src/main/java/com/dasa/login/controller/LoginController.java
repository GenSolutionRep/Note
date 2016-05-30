package com.dasa.login.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.login.dao.LoginDao;
import com.dasa.login.vo.LoginVo;
import com.vertexid.utill.CryptoUtil;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.Constant;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.StringCheck;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * @파일명: LoginController.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 로그인 Controller
 */
@Controller
public class LoginController { 
	
    @Autowired
    private HttpSession session;  
    @Autowired
    private LoginDao dao;

    private ModelAndView mv;
    private JSONObject jo;
    private JSONObject jvo;
    private JSONArray ja;
    private JSONObject response;

    private LoginVo reqLoginVo;
    private LoginVo resLoginVo;

    @RequestMapping("/")
    public ModelAndView root() throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/");
        mv = new ModelAndView();
        mv.setViewName("/login/login");
        return mv;
    }

    @RequestMapping("/home")
    public ModelAndView home() throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/home");
        mv = new ModelAndView();
        mv.setViewName("/home");
        return mv;
    }

    /*
    @RequestMapping(value = "/m_interface", produces = "text/plain;charset=UTF-8")
    public ModelAndView m_interface() throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_interface");
        CommonUtil.setSessionVo(session);
        mv = new ModelAndView();
        mv.setViewName("/interface");
        return mv;
    }
    */

    @RequestMapping(value = {"/1","/ins_a","/install_android"}, produces = "text/plain;charset=UTF-8")
    public ModelAndView install_android() throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/install_android");
        CommonUtil.setSessionVo(session);
        mv = new ModelAndView();
        mv.setViewName("/install/android");
        return mv;
    }

    @RequestMapping(value = {"/2","/ins_i","/install_ios"}, produces = "text/plain;charset=UTF-8")
    public ModelAndView install_ios() throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/install_ios");
        CommonUtil.setSessionVo(session);
        mv = new ModelAndView();
        mv.setViewName("/install/ios");
        return mv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout() throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/logout");
        session.invalidate();

        mv = new ModelAndView();
        mv.setViewName("/login/login");
        return mv;
    }

    @RequestMapping("/checkLogin")
    public ModelAndView checkLogin(@ModelAttribute("vo") LoginVo reqLoginVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/checkLogin");
        CommonUtil.setSessionVo(session);

        String em_password = CryptoUtil.encrypt(reqLoginVo.getEm_password());//암호화
        reqLoginVo.setEm_password(em_password);

        resLoginVo = dao.checkLogin(reqLoginVo);
        //System.out.println("resLoginVo=" + resLoginVo);

        jo = new JSONObject();
        jvo = JSONObject.fromObject(JSONSerializer.toJSON(resLoginVo));
        jo.put("loginVo", jvo);

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", jo);
        return mv;
    }

    @RequestMapping(value = "/m_checkLogin", produces="text/plain;charset=UTF-8")
    public ModelAndView m_checkLogin(@ModelAttribute("vo") LoginVo reqLoginVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_checkLogin");
        CommonUtil.setSessionVo(session);

        //Required Parameter
        String req_em_id = reqLoginVo.getEm_id();//사원ID
        String req_em_password = reqLoginVo.getEm_password();//비밀번호
        String req_em_unique_id = reqLoginVo.getEm_unique_id();//디바이스 고유번호
        String req_em_device_type = reqLoginVo.getEm_device_type();//디바이스 유형
        int req_em_device_version = reqLoginVo.getEm_device_version();//디바이스 버전
        //System.out.println("req_em_id=" + req_em_id);
        //System.out.println("req_em_password=" + req_em_password);
        //System.out.println("req_em_unique_id=" + req_em_unique_id);
        //System.out.println("req_em_device_type=" + req_em_device_type);
        //System.out.println("req_em_device_version=" + req_em_device_version);

        //Check Required Parameter
        if (StringCheck.isNull(req_em_id) || StringCheck.isNull(req_em_password) || StringCheck.isNull(req_em_unique_id) || StringCheck.isNull(req_em_device_type) || req_em_device_version == 0) {
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

        resLoginVo = dao.checkLogin(reqLoginVo);
        //System.out.println("resLoginVo=" + resLoginVo);

        String res_check_id = resLoginVo.getCheck_id();
        String res_check_password = resLoginVo.getCheck_password();
        String res_check_unique_id = resLoginVo.getCheck_unique_id();
        String res_em_unique_id = resLoginVo.getEm_unique_id();
        
        if (res_check_id.equals("Y") && res_check_password.equals("Y") && res_check_unique_id.equals("N")) {
            //입력한 사원ID와 비밀번호가 일치하고, 모바일에서 최초로 로그인을 시도하는 경우 -> 입력한 디바이스 고유번호를 저장
            LoginVo loginVo = new LoginVo();
            loginVo.setEm_id(req_em_id);
            loginVo.setEm_password(req_em_password);
            loginVo.setEm_unique_id(req_em_unique_id);

            int result = dao.setUniqueId(loginVo);
            if (result > 0) {
                System.out.println("사원ID[" + req_em_id + "]의 디바이스 고유번호[" + req_em_unique_id + "] , 디바이스 인증여부[Y]를 저장하였습니다.");
            }
        } else if (res_check_id.equals("Y") && res_check_password.equals("Y") && res_check_unique_id.equals("Y")) {
            //입력한 사원ID와 비밀번호가 일치하고, 서버에 디바이스 고유번호가 존재하는 경우 -> 입력한 디바이스 고유번호와 비교
            if (!req_em_unique_id.equals(res_em_unique_id)) {
                response = CommonUtil.setFailResponse(Constant.CHANGED_PHONE_CD, Constant.CHANGED_PHONE_MSG);
                return CommonUtil.setModelAndView(response);
            }
        }

        if (resLoginVo != null) {
            JSONObject body = new JSONObject();
            JSONArray body_check = new JSONArray();

            //Response Body
            JSONObject bodyVo = new JSONObject();
            bodyVo.put("check_id", res_check_id);
            bodyVo.put("check_password", res_check_password);
            body_check.add(bodyVo);
            body.put("check", body_check);

            response = CommonUtil.setSuccessResponse(body);
        } else {
            response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
        }

        return CommonUtil.setModelAndView(response);
    }

    @RequestMapping("/login")
    public ModelAndView login(@ModelAttribute("vo") LoginVo reqLoginVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/login");

        //String em_password = CryptoUtil.encrypt(reqLoginVo.getEm_password());//암호화
        //reqLoginVo.setEm_password(em_password);
        reqLoginVo.setEm_password("");
        reqLoginVo.setFlag("WEB");

        resLoginVo = dao.login(reqLoginVo);
        //System.out.println("resLoginVo=" + resLoginVo);

        session.setAttribute("login_no", resLoginVo.getEm_no());//사원번호
        session.setAttribute("login_cp_cd", resLoginVo.getCm_code());//회사코드
        session.setAttribute("login_cp_nm", resLoginVo.getCm_nm());//회사명
        session.setAttribute("login_bhf_cd", resLoginVo.getBhf_code());//지점코드
        session.setAttribute("login_bhf_nm", resLoginVo.getBhf_nm());//지점명
        session.setAttribute("login_team_cd", resLoginVo.getTeam_code());//팀코드
        session.setAttribute("login_team_nm", resLoginVo.getTeam_nm());//팀명
        session.setAttribute("timhdr_no", resLoginVo.getTimhdr_em_no());//소속팀장번호
        session.setAttribute("timdhr_nm", resLoginVo.getTimhdr_em_nm());//소속팀장명
        session.setAttribute("login_nm", resLoginVo.getEm_nm());//사원명
        session.setAttribute("login_id", resLoginVo.getEm_id());//사원ID
        session.setAttribute("login_rspofc_cd", resLoginVo.getEm_rspofc_code());//직책코드
        session.setAttribute("login_rspofc_nm", resLoginVo.getEm_rspofc_nm());//직책명
        session.setAttribute("login_dty_cd", resLoginVo.getEm_dty_code());//직무코드
        session.setAttribute("login_dty_nm", resLoginVo.getEm_dty_nm());//직무명
        session.setAttribute("auth_flag", String.valueOf(resLoginVo.getAuth_flag()));//권한코드

        jo = new JSONObject();
        jvo = JSONObject.fromObject(JSONSerializer.toJSON(resLoginVo));
        jo.put("loginVo", jvo);

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", jo);
        return mv;
    }

    @RequestMapping(value = "/m_login", produces="text/plain;charset=UTF-8")
    public ModelAndView m_login(@ModelAttribute("vo") LoginVo reqLoginVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/m_login");
        CommonUtil.setSessionVo(session);

        //Required Parameter
        String req_em_id = reqLoginVo.getEm_id();//사원ID
        String req_em_password = reqLoginVo.getEm_password();//비밀번호
        String req_em_unique_id = reqLoginVo.getEm_unique_id();//디바이스 고유번호
        String req_em_push_id = reqLoginVo.getEm_push_id();//디바이스 PUSH ID
        String req_em_device_type = reqLoginVo.getEm_device_type();//디바이스 유형
        //System.out.println("req_em_id=" + req_em_id);
        //System.out.println("req_em_password=" + req_em_password);
        //System.out.println("req_em_unique_id=" + req_em_unique_id);
        //System.out.println("req_em_push_id=" + req_em_push_id);
        //System.out.println("req_em_device_type=" + req_em_device_type);

        //Check Required Parameter
        if (StringCheck.isNull(req_em_id) || StringCheck.isNull(req_em_password) || StringCheck.isNull(req_em_unique_id) || StringCheck.isNull(req_em_push_id) || StringCheck.isNull(req_em_device_type)) {
            response = CommonUtil.setFailResponse(Constant.INVALID_PARAM_CD, Constant.INVALID_PARAM_MSG);
            return CommonUtil.setModelAndView(response);
        }
        
        JSONObject jsonObject = CommonUtil.getUpdateInfo(session);
        String dasa_url = jsonObject.getString("dasa_url");
        String sp_url = jsonObject.getString("sp_url");
        //System.out.println("dasa_url=" + dasa_url);
        //System.out.println("sp_url=" + sp_url);

        reqLoginVo.setFlag("MOBILE");
        LoginVo resLoginVo = dao.login(reqLoginVo);
        //System.out.println("resLoginVo=" + resLoginVo);

        if (resLoginVo != null) {
            //입력한 디바이스 PUSH ID를 저장
            LoginVo loginVo = new LoginVo();
            loginVo.setEm_id(req_em_id);
            loginVo.setEm_password(req_em_password);
            loginVo.setEm_push_id(req_em_push_id);
            loginVo.setEm_device_type(req_em_device_type);

            int result = dao.setPushId(loginVo);
            if (result > 0) {
                System.out.println("사원ID[" + req_em_id + "]의 디바이스 PUSH ID[" + req_em_push_id + "] , 디바이스 유형[" + req_em_device_type + "]를 저장하였습니다.");
            }

            JSONObject body = new JSONObject();
            JSONArray body_login = new JSONArray();

            //Response Body
            JSONObject bodyVo = new JSONObject();
            bodyVo.put("em_no", resLoginVo.getEm_no());
            bodyVo.put("cm_code", resLoginVo.getCm_code());
            bodyVo.put("cm_nm", resLoginVo.getCm_nm());
            bodyVo.put("bhf_code", resLoginVo.getBhf_code());
            bodyVo.put("bhf_nm", resLoginVo.getBhf_nm());
            bodyVo.put("team_code", resLoginVo.getTeam_code());
            bodyVo.put("team_nm", resLoginVo.getTeam_nm());
            bodyVo.put("timhdr_em_no", resLoginVo.getTimhdr_em_no());
            bodyVo.put("timhdr_em_nm", resLoginVo.getTimhdr_em_nm());
            bodyVo.put("timhdr_om_la", resLoginVo.getTimhdr_om_la());
            bodyVo.put("timhdr_om_lo", resLoginVo.getTimhdr_om_lo());
            bodyVo.put("em_nm", resLoginVo.getEm_nm());
            bodyVo.put("em_id", resLoginVo.getEm_id());
            bodyVo.put("em_rspofc_code", resLoginVo.getEm_rspofc_code());
            bodyVo.put("em_rspofc_nm", resLoginVo.getEm_rspofc_nm());
            bodyVo.put("em_dty_code", resLoginVo.getEm_dty_code());
            bodyVo.put("em_dty_nm", resLoginVo.getEm_dty_nm());
            bodyVo.put("em_mbtl_open_at", resLoginVo.getEm_mbtl_open_at());
            bodyVo.put("auth_flag", resLoginVo.getAuth_flag());
            if (resLoginVo.getCm_code().equals("001")) {
            	bodyVo.put("connect_url", dasa_url);
            } else {
            	bodyVo.put("connect_url", sp_url);
            }
            
            body_login.add(bodyVo);
            body.put("login", body_login);

            response = CommonUtil.setSuccessResponse(body);
        } else {
            response = CommonUtil.setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
        }

        return CommonUtil.setModelAndView(response);
    }
    
    @RequestMapping(value = "/getSystemDate")
    public ModelAndView getSystemDate(@ModelAttribute("vo") LoginVo reqLoginVo) throws Exception {
        System.out.println("[" + CommonUtil.getCurrentDateTime() + "]" + "/getSystemDate");

        String flag = reqLoginVo.getFlag();
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
        reqLoginVo.setDate_format(date_format);

        resLoginVo = dao.getSystemDate(reqLoginVo);
        //System.out.println("resLoginVo=" + resLoginVo);

        mv = new ModelAndView(new JSONView());
        mv.addObject("ajax", resLoginVo.getSys_de());
        return mv;
    }    

}
