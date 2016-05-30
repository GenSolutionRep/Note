package com.vertexid.conteroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.dao.OrganizationDAO;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.JSONView;
import com.vertexid.vo.OrganizationVo;

/**
 * @ PrototypeController.java
 * @ 2014. 9. 18.
 * @
 * @ 프로그램명: PrototypeController
 */
@Controller
public class OrganizationController {

	private ModelAndView mv;			//모델엔뷰
	private List<OrganizationVo> organizationList;
	
	private JSONObject jsonObject;				//json 여러개 넘기기
	private JSONArray jsonArray;				//json 배열
	
	
	@Autowired
	private OrganizationDAO dao;				
	
	@Autowired(required = true)
	private HttpSession session;
	
	
	/**
	 * @메서드명: organizationPg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 조직관리 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/99/99-300") 
	public String organizationPg(){
		return "/99/99-300";
	}
	
	/**
	 * @메서드명: treelist
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 트리 리스트
	 */
	@RequestMapping("/organization/treeList") 
	public ModelAndView getTreeList() throws Exception{
		String login_cp_cd = (String) session.getAttribute("login_cp_cd");
		organizationList =  dao.getTreeList(login_cp_cd);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	
	/**
	 * @메서드명: organizationList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 조직관리 리스트
	 */
	@RequestMapping("/organization/list")
	public ModelAndView organizationList(@RequestParam("om_code") String om_code) throws Exception {
		String login_cp_cd = (String)session.getAttribute("login_cp_cd");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cm_code", login_cp_cd);
		map.put("om_code", om_code);
		
		organizationList = dao.selectList(map);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: organizationListByCp(회사 기준으로)
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 조직관리 리스트
	 */
	@RequestMapping("/organization/byCp/list")
	public ModelAndView organizationListByCp(@RequestParam("cm_code") String cm_code) throws Exception {
		
		String login_cp_cd = (String)session.getAttribute("login_cp_cd");
		organizationList = dao.selectListByCp(login_cp_cd);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		//System.out.println("organizationList : " +  jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: organizationListByBhf(지점 기준으로)
	 * @작성일: 2015. 10. 01
	 * @작성자: 김광욱
	 * @설명: 조직관리 리스트
	 */
	@RequestMapping("/organization/byBhf/list")
	public ModelAndView organizationListByBhf(@RequestParam("bhf_code") String bhf_code) throws Exception {
		String login_cp_cd = (String)session.getAttribute("login_cp_cd");
		
		organizationList = dao.selectListByBhf(login_cp_cd, bhf_code);
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		//System.out.println("organizationList : " +  jsonObject);

		return mv;
	}	

	/**
	 * @메서드명: menuRow
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 메뉴관리 메뉴 row select
	 */
	@RequestMapping("/organization/organizationRow")
	public ModelAndView organizationRow(@RequestParam("om_code") String om_code) throws Exception {
		OrganizationVo vo = dao.selectRow(om_code);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(vo);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: organizationRow
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 상위메뉴 정보 가져오기
	 */
	@RequestMapping("/organization/organizationParent")
	public ModelAndView organizationParent(@RequestParam("om_code") String om_code) throws Exception {
		OrganizationVo vo = dao.getParentInfo(om_code);
		
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
	@RequestMapping("/organization/firstTreeOrganization")
	public ModelAndView getfirstTree() throws Exception {
		String result = dao.selectFirstTree();

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(result);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: selectOrganizationParentList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 콤보를 위한 상위메뉴 리스트
	 */
	@RequestMapping("/organization/organizationParentList")
	public ModelAndView selectOrganizationParentList(@RequestParam("om_code") String om_code) throws Exception {
		if(om_code==null || om_code.equals("")){
			organizationList = dao.selectParentList();
		}else{
			organizationList = dao.selectChildList(om_code);
		}
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: selectBranchList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 지점리스트
	 */
	@RequestMapping("/organization/branchList")
	public ModelAndView selectBranchList(@RequestParam("cm_code") String cm_code) throws Exception {
		organizationList = dao.selectBranchList(cm_code);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}
	
	/**
	 * @메서드명: depthList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: depth별 조회
	 */
	@RequestMapping("/organization/depthList")
	public ModelAndView depthList(@RequestParam("cm_code") String cm_code 
								,@RequestParam("om_code") String om_code
								,@RequestParam("om_orgnzt_se") String om_orgnzt_se) throws Exception {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("cm_code", cm_code);
		map.put("om_code", om_code);
		map.put("om_orgnzt_se", om_orgnzt_se);
		
		organizationList = dao.selectDepthList(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}
	
	/**
	 * @메서드명: depthList
	 * @작성일: 2015. 9. 16
	 * @작성자: 김진호
	 * @설명: depth별 내 소속 조회
	 */
	@RequestMapping("/organization/auth")
	public ModelAndView depthdList(@RequestParam("om_code") String param_om_code,@RequestParam("om_orgnzt_se") String om_orgnzt_se) throws Exception {
		
		String cm_code = (String)session.getAttribute("login_cp_cd"); //회사코드
		String om_code = (String)session.getAttribute("login_bhf_cd"); //지점코드
		String login_team_cd = (String)session.getAttribute("login_team_cd"); //팀코드
		int auth_flag = Integer.parseInt((String)session.getAttribute("auth_flag")); //권한코드
//		System.out.println("om_orgnzt_se : " +  om_orgnzt_se);
//		System.out.println("param_om_code : " +  param_om_code);
//		System.out.println("login_cp_cd : " +  cm_code);
//		System.out.println("login_bhf_cd : " +  om_code);
//		System.out.println("login_team_cd : " +  login_team_cd);
		
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("auth_flag", auth_flag);
		map.put("tm_code", login_team_cd);
		map.put("cm_code", cm_code);
		map.put("om_code", om_code);
		map.put("param_om_code", param_om_code);
		map.put("om_orgnzt_se", om_orgnzt_se);
		
		organizationList = dao.selectAuthDepthList(map);
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(organizationList);
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("auth_flag", auth_flag);
		jsonObject.put("cm_code", cm_code);
		jsonObject.put("om_code", om_code);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}
	
	
	/**
	 * @메서드명: depthList
	 * @작성일: 2015. 9. 16
	 * @작성자: 김진호
	 * @설명: depth별 내 소속 조회
	 */
	@RequestMapping("/ds_organization/list")
	public ModelAndView ds_depthdList(@RequestParam("om_code") String param_om_code,@RequestParam("cm_code") String cm_code, @RequestParam("om_orgnzt_se") String om_orgnzt_se) throws Exception {
		System.out.println("["+CommonUtil.getCurrentDateTime() + " Web :/ds_organization/list] 동서식품 모바일 : 내 소속 조회 om_orgnzt_se : "+ om_orgnzt_se+", om_code"+ param_om_code);
		CommonUtil.setSessionVo(session);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if(cm_code == null || cm_code == ""){
			cm_code = "002";
		}
		
		map.put("cm_code", cm_code);
		map.put("om_code", param_om_code);
		map.put("om_orgnzt_se", om_orgnzt_se);
		
		organizationList = dao.selectDepthList(map);
		
		List<Map<String, String>> dsOrgaVoList = new ArrayList<Map<String, String>>();
		if(organizationList !=  null){
			for (OrganizationVo cVo: organizationList) {
				Map<String, String> mapVo =  new HashMap<String, String>();
				mapVo.put("om_code", cVo.getOm_code());
				mapVo.put("om_nm", cVo.getOm_nm());
				dsOrgaVoList.add(mapVo);
			}
		}
		
		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(dsOrgaVoList);
		
		jsonObject.put("result", jsonArray);
		jsonObject.put("auth_flag", "1");

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		return mv;
	}
	
	/**
	 * @메서드명: saveOrganization
	 * @작성일: 2015. 9. 23
	 * @작성자: 최수영
	 * @설명: 조직관리  save
	 */
	@RequestMapping("/organization/saveOrganization")
	public ModelAndView saveOrganization(OrganizationVo p_Vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		if(p_Vo.getOm_orgnzt_se()!=null)
			p_Vo.setOm_orgnzt_se(p_Vo.getOm_orgnzt_se().trim());
		if(p_Vo.getOm_sort_ordr()!=null)
			p_Vo.setOm_sort_ordr(p_Vo.getOm_sort_ordr().trim());
		if(p_Vo.getOm_memo()!=null)
			p_Vo.setOm_memo(p_Vo.getOm_memo().trim());
		
		if(p_Vo.getFlag().equals("DELETE"))
			p_Vo.setDelete_at("Y");
		else
			p_Vo.setDelete_at("N");
		p_Vo.setRegist_man(login_no);
		p_Vo.setUpdt_man(login_no);
		
		dao.saveOrganization(p_Vo);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(p_Vo);
		
		jsonObject.put("dto", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: insertOrganization
	 * @작성일: 2015. 9. 23
	 * @작성자: 최수영
	 * @설명: 조직관리  insert
	 */
	@RequestMapping("/organization/insertOrganization")
	public ModelAndView insertOrganization(ModelMap modelMap, OrganizationVo p_Vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("om_code", p_Vo.getOm_code());
		map.put("cm_code", p_Vo.getCm_code());
		map.put("om_parent_no", p_Vo.getOm_parent_no());
		map.put("om_nm", p_Vo.getOm_nm().trim());
		map.put("om_orgnzt_se", p_Vo.getOm_orgnzt_se().trim());
		map.put("om_sort_ordr", p_Vo.getOm_sort_ordr().trim());
		map.put("om_memo", p_Vo.getOm_memo().trim());
		map.put("use_at", p_Vo.getUse_at());
		map.put("delete_at", 'N');
		map.put("regist_man", login_no);
		map.put("updt_man", login_no);
		
		int resultCnt = dao.insertOrganization(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);

		return mv;
	}
	
	/**
	 * @메서드명: updateOrganization
	 * @작성일: 2015. 9. 23
	 * @작성자: 최수영
	 * @설명: 조직관리 update
	 */
	@RequestMapping("/organization/updateOrganization")
	public ModelAndView updateOrganization(ModelMap modelMap, OrganizationVo p_Vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("om_code", p_Vo.getOm_code());
		map.put("cm_code", p_Vo.getCm_code());
		map.put("om_parent_no", p_Vo.getOm_parent_no());
		map.put("om_nm", p_Vo.getOm_nm().trim());
		map.put("om_orgnzt_se", p_Vo.getOm_orgnzt_se().trim());
		map.put("om_sort_ordr", p_Vo.getOm_sort_ordr().trim());
		map.put("om_memo", p_Vo.getOm_memo().trim());
		map.put("use_at", p_Vo.getUse_at());
		map.put("updt_man", login_no);
				
		int resultCnt = dao.updateOrganization(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
	
	/**
	 * @메서드명: deleteOrganization
	 * @작성일: 2015. 9. 18
	 * @작성자: 최수영
	 * @설명: 조직관리 delete
	 */
	@RequestMapping("/organization/deleteOrganization")
	public ModelAndView deleteOrganization(String om_code) throws Exception {
		String login_no = (String) session.getAttribute("login_no");		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("om_code", om_code);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);
		
		int resultCnt = dao.deleteOrganization(map);

		jsonObject = new JSONObject();
		jsonArray = JSONArray.fromObject(resultCnt);
		
		jsonObject.put("resultCnt", jsonArray);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jsonObject);
		
		return mv;
	}
}

