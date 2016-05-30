package com.dasa.dashboard.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.dashboard.dao.DashDAO;
import com.dasa.dashboard.vo.DashVo;
import com.vertexid.utill.JSONView;

/**
 * @ DashController.java
 * @ 2015. 9. 18.
 * @
 * @ 프로그램명: DashController
 */
@Controller
public class DashController {

	private ModelAndView mv;			//모델엔뷰
	private List<DashVo> dashList;
	private DashVo vo;
	
	private JSONObject jsonObject;				//json 여러개 넘기기
	private JSONArray jsonArray;				//json 배열
	
	@Autowired
	private DashDAO dao;				
	
	@Autowired(required = true)
	private HttpSession session;
	
	
	/**
	 * @메서드명: dashPg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 메뉴관리 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/10/10-500") 
	public String menuPg(){
		return "/10/10-500";
	}
	
	@RequestMapping("/dash/chart/selectEmp") 
	public ModelAndView selectEmp(@RequestParam("om_code") String p_om_code) throws Exception{
		String auth_flag = (String)session.getAttribute("auth_flag");
		String login_cp_cd = (String)session.getAttribute("login_cp_cd");
		//String login_bhf_cd = (String)session.getAttribute("login_bhf_cd");
		String login_team_cd = (String)session.getAttribute("login_team_cd"); //팀코드
		String login_dty_cd = (String)session.getAttribute("login_dty_cd");
		String login_no = (String)session.getAttribute("login_no");
		
		//System.out.println("************auth_flag : " + auth_flag);

		//System.out.println("********* p_om_code:>>" + p_om_code+"<<");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("auth_flag", auth_flag);
		map.put("cm_code", login_cp_cd);
		map.put("om_code", p_om_code);
		map.put("team_code", login_team_cd);
		map.put("login_dty_cd", login_dty_cd);
		map.put("login_no", login_no); 
		
		dashList =  dao.selectEmp(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(dashList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		//System.out.println("차트에서 auth_flag : " + auth_flag);
		//System.out.println("chart1 :" + jsonObject);
			
		return mv;
	}
	
	@RequestMapping("/dash/chart/fix") 
	public ModelAndView selectFixAttdance(@RequestParam("om_code") String p_om_code) throws Exception {
		String auth_flag = (String)session.getAttribute("auth_flag");
		String login_cp_cd = (String)session.getAttribute("login_cp_cd");
		//String login_bhf_cd = (String)session.getAttribute("login_bhf_cd");
		String login_team_cd = (String)session.getAttribute("login_team_cd"); //팀코드
		String login_dty_cd = (String)session.getAttribute("login_dty_cd");
		String login_no = (String)session.getAttribute("login_no");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("auth_flag", auth_flag);
		map.put("cm_code", login_cp_cd);
		map.put("om_code", p_om_code);
		map.put("team_code", login_team_cd);
		map.put("login_dty_cd", login_dty_cd);
		map.put("login_no", login_no); 
		
		vo = dao.selectFixAttdance(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(vo);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		//System.out.println("chart2 :" + jsonObject);
		
		return mv;
	}
	
	@RequestMapping("/dash/chart/rnd") 
	public ModelAndView selectRndAttdance(@RequestParam("om_code") String p_om_code) throws Exception {
		String auth_flag = (String)session.getAttribute("auth_flag");
		String login_cp_cd = (String)session.getAttribute("login_cp_cd");
		//String login_bhf_cd = (String)session.getAttribute("login_bhf_cd");
		String login_team_cd = (String)session.getAttribute("login_team_cd"); //팀코드
		String login_dty_cd = (String)session.getAttribute("login_dty_cd");
		String login_no = (String)session.getAttribute("login_no");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("auth_flag", auth_flag);
		map.put("cm_code", login_cp_cd);
		map.put("om_code", p_om_code);
		map.put("team_code", login_team_cd);
		map.put("login_dty_cd", login_dty_cd);
		map.put("login_no", login_no); 
		
		vo = dao.selectRndAttdance(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(vo);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		//System.out.println("chart3 :" + jsonObject);
		
		return mv;
	}
	
}

