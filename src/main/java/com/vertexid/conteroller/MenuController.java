package com.vertexid.conteroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.dao.MenuDAO;
import com.vertexid.utill.JSONView;
import com.vertexid.vo.MenuVo;

/**
 * @ PrototypeController.java
 * @ 2014. 9. 18.
 * @
 * @ 프로그램명: PrototypeController
 */
@Controller
public class MenuController {

	private ModelAndView mv;			//모델엔뷰
	private List<MenuVo> menuList;
	private JSONObject jsonObject;				//json 여러개 넘기기
	private JSONArray jsonArray;				//json 배열
	
	
	@Autowired
	private MenuDAO dao;				
	
	@Autowired(required = true)
	private HttpSession session;
	
	
	/**
	 * @메서드명: menuPg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 메뉴관리 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/99/99-600") 
	public String menuPg(){
		return "/99/99-600";
	}
	
	/**
	 * @메서드명: menuList
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 메뉴관리 트리 리스트
	 */
	@RequestMapping("/menu/list") 
	public ModelAndView menuList(@RequestParam("user_id") String user_id,
								@RequestParam("m_use_yn") String m_use_yn) throws Exception{
		HashMap map = new HashMap();
		String login_no = (String) session.getAttribute("login_no");
		String cm_code = (String) session.getAttribute("login_cp_cd");
		
		map.put("login_no", login_no);
		map.put("m_no", "");
		map.put("m_use_yn", m_use_yn);
		map.put("user_id", user_id);
		map.put("cm_code", cm_code);
		
		menuList =  dao.menuList(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(menuList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: nodeMenuList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 메뉴관리 트리 리스트
	 */
	@RequestMapping("/menu/menuList")
	public ModelAndView nodeMenuList(@RequestParam("m_no") String m_no) throws Exception {
		System.out.println("m_no : " +  m_no);
		String cm_code = (String) session.getAttribute("login_cp_cd");
		menuList = dao.selectMenuList(m_no, cm_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(menuList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: menuRow
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 메뉴관리 메뉴 row select
	 */
	@RequestMapping("/menu/menuRow")
	public ModelAndView menuRow(@RequestParam("m_no") String m_no) throws Exception {
		MenuVo vo = dao.selectMenuRow(m_no);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(vo);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: menuRow
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 상위메뉴 정보 가져오기
	 */
	@RequestMapping("/menu/menuParent")
	public ModelAndView menuParent(@RequestParam("m_no") String m_no) throws Exception {
		MenuVo vo = dao.getParentInfo(m_no);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(vo);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	
	/**
	 * @메서드명: firstTreeMenu
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 트리메뉴의 첫 node 메뉴 코드
	 */
	@RequestMapping("/menu/firstTreeMenu")
	public ModelAndView firstTreeMenu() throws Exception {
		String result = dao.selectFirstTreeMenu();

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(result);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: firstTreeMenu
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 콤보를 위한 상위메뉴 리스트
	 */
	@RequestMapping("/menu/menuParentList")
	public ModelAndView selectMenuParentList(@RequestParam("m_no") String m_no) throws Exception {
		if(m_no==null || m_no.equals("")){
			menuList = dao.selectMenuParentList();
		}else{
			menuList = dao.selectMenuChildList(m_no);
		}
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(menuList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: selectDepthList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 콤보를 위한 상위메뉴 리스트
	 */
	@RequestMapping("/menu/depthList")
	public ModelAndView selectDepthList(@RequestParam("m_no") String m_no, @RequestParam("m_depth") String m_depth) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("m_no", m_no);
		map.put("m_depth", m_depth);
		
		menuList = dao.selectDepthList(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(menuList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	
	@RequestMapping("/menu/saveMenu")
	public ModelAndView saveMenu(MenuVo p_Vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		p_Vo.setCm_code("001");
		p_Vo.setM_nm(p_Vo.getM_nm().trim());
		p_Vo.setM_url(p_Vo.getM_url().trim());
		p_Vo.setM_order(p_Vo.getM_order().trim());
		p_Vo.setM_note(p_Vo.getM_note().trim());
		p_Vo.setM_parent_no(p_Vo.getM_parent_no().trim());
		p_Vo.setM_in_id(login_no);
		p_Vo.setM_up_id(login_no);
		
		dao.saveMenu(p_Vo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(p_Vo);
		
		jsonObject.put("dto", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	
	/**
	 * @메서드명: insertMenu
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 메뉴관리 메뉴 insert
	 */
	@RequestMapping("/menu/insertMenu")
	public ModelAndView insertMenu(ModelMap modelMap, MenuVo p_menuVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("m_no", p_menuVo.getM_no());
		map.put("m_nm", p_menuVo.getM_nm());
		map.put("m_url", p_menuVo.getM_url());
		map.put("m_order", p_menuVo.getM_order());
		map.put("m_parent_no", p_menuVo.getM_parent_no());
		map.put("m_use_yn", p_menuVo.getM_use_yn());
		map.put("m_url", p_menuVo.getM_url());
		map.put("m_note", p_menuVo.getM_note());
		map.put("m_depth", p_menuVo.getM_depth());
		//map.put("m_in_id", sessionVo.getUser_id());
		map.put("m_in_id", login_no);
		
		int resultCnt = dao.InsertMenu(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: insertMenu
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 메뉴관리 메뉴 insert
	 */
	@RequestMapping("/menu/updateMenu")
	public ModelAndView updateMenu(ModelMap modelMap, MenuVo p_menuVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("m_no", p_menuVo.getM_no());
		map.put("m_nm", p_menuVo.getM_nm());
		map.put("m_url", p_menuVo.getM_url());
		map.put("m_order", p_menuVo.getM_order());
		map.put("m_parent_no", p_menuVo.getM_parent_no());
		map.put("m_use_yn", p_menuVo.getM_use_yn());
		map.put("m_url", p_menuVo.getM_url());
		map.put("m_note", p_menuVo.getM_note());
		map.put("m_depth", p_menuVo.getM_depth());
		//map.put("m_up_id", sessionVo.getUser_id());
		map.put("m_up_id", login_no);
		
		int resultCnt = dao.UpdateMenu(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: menuInit
	 * @작성일: 2015. 11. 11
	 * @작성자: 김종현
	 * @설명: 메뉴관리 메뉴 menuInit
	 */
	@RequestMapping("/menu/menuInit")
	public ModelAndView menuInit(@RequestParam("m_no") String m_no) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cm_code", login_cp_cd);
		map.put("login_no", login_no);
		map.put("m_no", m_no);
		
		menuList = dao.selectMenuInit(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(menuList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: menuInit
	 * @작성일: 2015. 11. 11
	 * @작성자: 김종현
	 * @설명: 메뉴관리 메뉴 menuInit
	 */
	@RequestMapping("/menu/topInit")
	public ModelAndView topInit(@RequestParam("m_use_yn") String m_use_yn) throws Exception {
		
		String login_no = (String) session.getAttribute("login_no");
		String login_nm = (String) session.getAttribute("login_nm");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login_no", login_no);
		map.put("m_nm", m_use_yn);
		menuList = dao.selectTopInit(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(menuList);
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("login_nm", login_nm);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
}

