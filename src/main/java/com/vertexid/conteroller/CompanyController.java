package com.vertexid.conteroller;

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

import com.vertexid.dao.CompanyDAO;
import com.vertexid.utill.JSONView;
import com.vertexid.vo.CompanyVo;

/**
 * @ CompanyController.java
 * @ 2015. 9. 18.
 * @
 * @ 프로그램명: CompanyController
 */
@Controller
public class CompanyController {

	private ModelAndView mv;			//모델엔뷰
	private List<CompanyVo> companyList;
	
	private JSONObject jsonObject;				//json 여러개 넘기기
	private JSONArray jsonArray;				//json 배열
	
	@Autowired
	private CompanyDAO dao;				
	
	@Autowired(required = true)
	private HttpSession session;
	
	
	/**
	 * @메서드명: menuPg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 메뉴관리 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/99/99-100") 
	public String menuPg(){
		return "/99/99-100";
	}
	
	/**
	 * @메서드명: companyList
	 * @작성일: 2015. 9. 18
	 * @작성자: 최수영
	 * @설명: 회사관리
	 */
	@RequestMapping("/company/companyList") 
	public ModelAndView companyList() throws Exception{
		companyList =  dao.selectCompanyList();
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(companyList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
			
		return mv;
	}
	
	/**
	 * @메서드명: companyRow
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 해당 회사관리 row 조회
	 */
	@RequestMapping("/company/companyRow") 
	public ModelAndView companyRow(@RequestParam("cm_code") String cm_code) throws Exception {
		CompanyVo vo = dao.selectCompanyRow(cm_code);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(vo);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: insertComapny
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 회사관리 회사 insert
	 */
	@RequestMapping("/company/insertCompany")
	public ModelAndView insertComapny(CompanyVo p_companyVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
	
		map.put("cm_code", p_companyVo.getCm_code());
		map.put("cm_nm", p_companyVo.getCm_nm().trim());
		map.put("cm_sort_ordr", p_companyVo.getCm_sort_ordr().trim());
		map.put("cm_memo", p_companyVo.getCm_memo().trim());
		map.put("use_at", p_companyVo.getUse_at());
		map.put("delete_at", 'N');
		map.put("regist_man", login_no);
		map.put("updt_man", login_no);
		
		int resultCnt = dao.insertCompany(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: updateCompany
	 * @작성일: 2015. 9. 18
	 * @작성자: 최수영
	 * @설명: 회사관리 insert
	 */
	@RequestMapping("/company/updateCompany")
	public ModelAndView updateCompany(CompanyVo p_companyVo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("cm_code", p_companyVo.getCm_code());
		map.put("cm_nm", p_companyVo.getCm_nm().trim());
		map.put("cm_sort_ordr", p_companyVo.getCm_sort_ordr().trim());
		map.put("cm_memo", p_companyVo.getCm_memo().trim());
		map.put("use_at", p_companyVo.getUse_at());
		map.put("updt_man", login_no);
		
		int resultCnt = dao.updateCompany(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: deleteCompany
	 * @작성일: 2015. 9. 18
	 * @작성자: 최수영
	 * @설명: 회사관리 delete
	 */
	@RequestMapping("/company/deleteCompany")
	public ModelAndView deleteCompany(String cm_code) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cm_code", cm_code);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);
		
		int resultCnt = dao.deleteCompany(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
}

