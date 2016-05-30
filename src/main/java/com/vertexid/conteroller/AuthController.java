package com.vertexid.conteroller;

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
import org.springframework.web.servlet.ModelAndView;

import com.dasa.employee.vo.EmployeeVo;
import com.vertexid.dao.AuthDAO;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.JSONView;
import com.vertexid.vo.AuthGroupEmpVo;
import com.vertexid.vo.AuthGroupVo;
import com.vertexid.vo.AuthVo;
import com.vertexid.vo.SessionVo;

/**
 * @ AuthController.java
 * @ 2014. 9. 18.
 * @
 * @ 프로그램명: PrototypeController
 */
@Controller
public class AuthController {

	private ModelAndView mv;			//모델엔뷰
	
	private List<AuthVo> aList;
	private AuthVo authVo;
	private List<AuthGroupVo> agList; 
	private AuthGroupVo agVo;
	private List<AuthGroupEmpVo> ageList;
	private AuthGroupEmpVo ageVo;
	private List<EmployeeVo> employeeList;
	
	
	private JSONObject jObj;				//json 여러개 넘기기 
	private JSONArray jArr;				//json 배열
	
	@Autowired
	private AuthDAO dao;				//모델
	
	@Autowired(required = true)
	private HttpSession session;
	
	/**
	 * @메서드명: authPg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 권한관리 화면 이동
	 * @return "String"
	 */
	@RequestMapping("99/99-700") 
	public String authPg(){
		return "99/99-700";
	}
	
	/**
	 * @메서드명: authGroupList
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹 저장
	 * 
	 */
	@RequestMapping("/auth/save") 
	public ModelAndView authSave(@ModelAttribute("vo") AuthVo vo) throws Exception {
		System.out.println("권한저장 : " + vo.toString());
		String login_no = (String) session.getAttribute("login_no");
		vo.setLogin_id(login_no);
		int cnt = dao.authSave(vo);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt);
		
		return mv;
	}
	@RequestMapping("/auth/thisMenuAuth")
	public ModelAndView thisMenuAuth(@RequestParam("m_no") String m_no) throws Exception {
		//System.out.println("권한 조회!!!!!!!!!!!!!!!!!!!"+m_no);
		String login_no = (String) session.getAttribute("login_no");
		Map<String, String> map = new HashMap<String, String>();
		map.put("login_no", login_no);
		map.put("m_no", m_no);
		
		jObj= new JSONObject();
		authVo = dao.thisMenuAuth(map);
		jObj = JSONObject.fromObject(JSONSerializer.toJSON(authVo));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	@RequestMapping("/auth/empList")
	public ModelAndView authEmpList(@RequestParam("om_code") String om_code, @RequestParam("em_nm") String em_nm) throws Exception {
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");//로그인 유저의 사원번호
		Map<String, String> map = new HashMap<String, String>();
		map.put("cm_code", login_cp_cd);
		map.put("om_code", om_code);
		map.put("em_nm", em_nm);
		
		employeeList = dao.authEmpList(map);
		System.out.println("employeeList=" + employeeList);
		
		jObj= new JSONObject();
		jArr = JSONArray.fromObject(employeeList);
		jObj.put("result", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	/**
	 * @메서드명: authGroupList
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹 리스트
	 * 
	 */
	@RequestMapping("/auth/authGroupList") 
	public ModelAndView selectAuthGroupList() throws Exception {
		
		agList = dao.selectAuthGroupList();
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(agList); 
		System.out.println("그룹권한 목록!!!  : " + jArr);
		jObj.put("result", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);

		return mv;
	}
	
	/**
	 * @메서드명: selectAuthGroupRow
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹 리스트
	 * 
	 */
	@RequestMapping("/auth/authGroupRow") 
	public ModelAndView selectAuthGroupRow(@RequestParam String ma_group_seq) throws Exception {
		
		agVo = dao.selectAuthGroupRow(ma_group_seq);
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(agVo);
		
		jObj.put("result", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);

		return mv;
	}
	
	
	/**
	 * @메서드명: saveAuthGroup
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹  SAVE
	 */
	@RequestMapping("/auth/saveAuthGroup")
	public ModelAndView saveAuthGroup(AuthGroupVo p_vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		p_vo.setMa_is_del("N");
		p_vo.setMa_in_id(login_no);
		p_vo.setMa_up_id(login_no);
		
		int resultCnt=0;
		
		if(p_vo.getFlag().equals("INSERT"))
			resultCnt = dao.insertAuthGroup(p_vo);
		else if(p_vo.getFlag().equals("UPDATE"))
			resultCnt = dao.updateAuthGroup(p_vo);

		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultCnt);
		
		jObj.put("resultCnt", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);

		return mv;
	}
	
	//=============================================================================
	
	/**
	 * @메서드명: authGroupEmpList
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹 리스트
	 * 
	 */
	@RequestMapping("/auth/authGroupEmpList") 
	public ModelAndView authGroupEmpList(String ma_group_seq) throws Exception {
		
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cm_code", login_cp_cd);
		map.put("ma_group_seq", ma_group_seq);
		
		ageList = dao.selectAuthGroupEmpList(map);
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(ageList);
		
		jObj.put("result", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);

		return mv;
	}
	
	/**
	 * @메서드명: saveAuthGroup
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹  SAVE
	 */
	@RequestMapping("/auth/saveAuthGroupEmp")
	public ModelAndView saveAuthGroupEmp(AuthGroupEmpVo p_vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		p_vo.setMae_is_del("N");
		p_vo.setMae_in_id(login_no);
		p_vo.setMae_up_id(login_no);
		
		int resultCnt=0;
		
		
		resultCnt = dao.deleteAuthGroupEmp(p_vo);
		resultCnt = dao.insertAuthGroupEmp(p_vo);

		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultCnt);
		
		jObj.put("resultCnt", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);

		return mv;
	}
	
	
	//=========================================================================
	
	/**
	 * @메서드명: authGroupList
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹 리스트
	 * 
	 */
	@RequestMapping("/auth/menuAuthList") 
	public ModelAndView menuAuthList(@RequestParam String ma_group_seq) throws Exception {
		
		System.out.println("아짜증123124124!!");
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		
		AuthVo p_vo = new AuthVo();
		p_vo.setM_cm_code(login_cp_cd);
		p_vo.setMa_group_seq(ma_group_seq);
		aList = dao.selectAuthList(p_vo);
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(aList);
		
		System.out.println("아짜증!!");
		
		//System.out.println("jArr :" + jArr.toString());
		
		jObj.put("result", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);

		return mv;
	}
	
	/**
	 * @메서드명: authGroupList
	 * @작성일: 2015. 10. 13
	 * @작성자: 최수영
	 * @설명: 권한그룹 리스트
	 * 
	 */
	@RequestMapping("/auth/saveAuth") 
	public ModelAndView saveAuth(AuthVo p_vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		int resultCnt=0;
		
		p_vo.setMa_in_id(login_no);
		p_vo.setMa_up_id(login_no);
		
		resultCnt = dao.deleteAuth(p_vo);
		resultCnt = dao.insertAuth(p_vo);

		jObj = new JSONObject();
		jArr = JSONArray.fromObject(resultCnt);
		
		jObj.put("resultCnt", jArr);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);

		return mv;
	}
}
