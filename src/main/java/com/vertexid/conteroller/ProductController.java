package com.vertexid.conteroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.dao.ProductDAO;
import com.vertexid.utill.JSONView;
import com.vertexid.vo.ProductJsonVo;
import com.vertexid.vo.ProductVo;

/**
 * @ PrototypeController.java
 * @ 2014. 9. 18.
 * @
 * @ 프로그램명: PrototypeController
 */
@Controller
public class ProductController {

	private ModelAndView mv;			//모델엔뷰
	private List<ProductVo> productList;
	private ProductVo vo;
	
	private JSONObject jo;				//json 여러개 넘기기
	private JSONArray ja;				//json 배열
	private JSONObject jVo;
	
	private Map<String,ProductJsonVo> prodJson;
	
	@Autowired
	private ProductDAO dao;				
	
	@Autowired(required = true)
	private HttpSession session;
	
	
	/**
	 * @메서드명: productPg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 품목관리 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/99/99-200") 
	public String productPg(){
		return "/99/99-200";
	}
	
	/**
	 * @메서드명: treelist
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 트리 리스트
	 */
	@RequestMapping("/product/treeList") 
	public ModelAndView getTreeList() throws Exception{
		productList =  dao.getTreeList();
		jo = new JSONObject();
		ja = JSONArray.fromObject(productList);
		
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	
	/**
	 * @메서드명: productList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 품목관리 리스트
	 */
	@RequestMapping("/product/list")
	public ModelAndView productList(@RequestParam("pm_code") String pm_code) throws Exception {
		productList = dao.selectList(pm_code);
		jo = new JSONObject();
		ja = JSONArray.fromObject(productList);
		
		jo.put("result", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	/**
	 * @메서드명: productList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 품목관리 리스트
	 */
	@RequestMapping("/product/listall")
	public ModelAndView productListAll() throws Exception {
		productList = dao.productListAll();
		prodJson = new HashMap<String,ProductJsonVo>();
		
		for (ProductVo vo : productList) {
			String pm_code = vo.getPm_code();
			String pm_parent_no = vo.getPm_parent_no();
			String pm_dp = vo.getPm_dp();
			String pm_nm = vo.getPm_nm();	
			
			ProductJsonVo pVo = new ProductJsonVo();
			pVo.setPm_code(pm_code);
			pVo.setPm_parent_no(pm_parent_no);
			pVo.setPm_dp(pm_dp);
			pVo.setPm_nm(pm_nm); 
			Map<String, ProductJsonVo >  map = new HashMap<String, ProductJsonVo>();
			pVo.setChild(map);
			
			if(pm_dp.equals("1")){
				prodJson.put(pm_code, pVo);
			}else if(pm_dp.equals("2")){
				prodJson.get(pm_parent_no).getChild().put(pm_code, pVo);
			}else if(pm_dp.equals("3")){
				prodJson.get(pm_parent_no.substring(0, 2)).getChild().get(pm_parent_no).getChild().put(pm_code, pVo);
			}else if(pm_dp.equals("4")){
				prodJson.get(pm_parent_no.substring(0, 2)).getChild().get(pm_parent_no.substring(0, 4)).getChild().get(pm_parent_no).getChild().put(pm_code, pVo);
			}else if(pm_dp.equals("5")){
				prodJson.get(pm_parent_no.substring(0, 2)).getChild().get(pm_parent_no.substring(0, 4)).getChild().get(pm_parent_no.substring(0, 6)).getChild().get(pm_parent_no).getChild().put(pm_code, pVo);
			}
		}
//		System.out.println("prodJson : "+prodJson);
		jVo = JSONObject.fromObject(JSONSerializer.toJSON(prodJson));
//		ja = JSONArray.fromObject(prodJson);
		mv = new ModelAndView(new JSONView());
//		System.out.println("jVo : " + jVo);
		mv.addObject("ajax", jVo);
		
		return mv;
	}
	
	/**
	 * @메서드명: productRow
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 메뉴관리 메뉴 row select
	 */
	@RequestMapping("/product/productRow")
	public ModelAndView productRow(@RequestParam("pm_code") String pm_code) throws Exception {
		ProductVo vo = dao.selectRow(pm_code);

		jo = new JSONObject();
		ja = JSONArray.fromObject(vo);
		
		jo.put("result", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	/**
	 * @메서드명: firstTreeMenu
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: 트리메뉴의 첫 node 메뉴 코드
	 */
	@RequestMapping("/product/firstTreeProduct")
	public ModelAndView getfirstTree() throws Exception {
		String result = dao.selectFirstTree();

		jo = new JSONObject();
		ja = JSONArray.fromObject(result);
		
		jo.put("result", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	
	/**
	 * @메서드명: depthList
	 * @작성일: 2015. 9. 16
	 * @작성자: 최수영
	 * @설명: depth별 조회
	 */
	@RequestMapping("/product/depthList")
	public ModelAndView depthList(@RequestParam("pm_code") String pm_code
								,@RequestParam("pm_dp") String pm_dp) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("pm_code", pm_code);
		map.put("pm_dp", pm_dp);
		
		productList = dao.selectDepthList(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(productList);
		
		jo.put("result", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	/**
	 * @메서드명: insertProduct
	 * @작성일: 2015. 9. 23
	 * @작성자: 최수영
	 * @설명: 품목관리  insert
	 */
	@RequestMapping("/product/insertProduct")
	public ModelAndView insertProduct(ProductVo p_Vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		p_Vo.setPm_code(p_Vo.getPm_code());
		p_Vo.setPm_parent_no(p_Vo.getPm_parent_no());
		p_Vo.setPm_nm(p_Vo.getPm_nm().trim());
		p_Vo.setPm_se(p_Vo.getPm_se());
		p_Vo.setPm_dp(p_Vo.getPm_dp());
		p_Vo.setPm_sort_ordr(p_Vo.getPm_sort_ordr());
		p_Vo.setPm_memo(p_Vo.getPm_memo());
		p_Vo.setUse_at(p_Vo.getUse_at());
		p_Vo.setDelete_at("N");
		p_Vo.setRegist_man(login_no);
		
		int resultCnt = dao.insertProduct(p_Vo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);

		return mv;
	}
	
	/**
	 * @메서드명: updateProduct
	 * @작성일: 2015. 9. 23
	 * @작성자: 최수영
	 * @설명: 품목관리 update
	 */
	@RequestMapping("/product/updateProduct")
	public ModelAndView updateProduct(ProductVo p_Vo) throws Exception {
		String login_no = (String) session.getAttribute("login_no");	
		
		System.out.println("p_Vo.getPm_code() :" + p_Vo.getPm_code());
		System.out.println("p_Vo.getPm_parent_no() :" + p_Vo.getPm_parent_no());
		p_Vo.setPm_code(p_Vo.getPm_code().trim());
		p_Vo.setPm_parent_no(p_Vo.getPm_parent_no().trim());
		p_Vo.setPm_nm(p_Vo.getPm_nm().trim());
		p_Vo.setPm_se(p_Vo.getPm_se());
		p_Vo.setPm_dp(p_Vo.getPm_dp().trim());
		p_Vo.setPm_sort_ordr(p_Vo.getPm_sort_ordr().trim());
		p_Vo.setPm_memo(p_Vo.getPm_memo().trim());
		p_Vo.setUse_at(p_Vo.getUse_at().trim());
		p_Vo.setUpdt_man(login_no);
				
		int resultCnt = dao.updateProduct(p_Vo);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	/**
	 * @메서드명: deleteProduct
	 * @작성일: 2015. 9. 18
	 * @작성자: 최수영
	 * @설명: 품목관리 delete
	 */
	@RequestMapping("/product/deleteProduct")
	public ModelAndView deleteProduct(String pm_code) throws Exception {
		String login_no = (String) session.getAttribute("login_no");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pm_code", pm_code);
		map.put("delete_at", 'Y');
		map.put("updt_man", login_no);

		int resultCnt = dao.deleteProduct(map);

		jo = new JSONObject();
		ja = JSONArray.fromObject(resultCnt);
		
		jo.put("resultCnt", ja);

		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	/**
	 * @메서드명: checkProductCode
	 * @작성일: 2015. 10. 07
	 * @작성자: 최수영
	 * @설명: 코드 유효성 검사
	 */
	@RequestMapping("/product/checkProductCode") 
	public ModelAndView checkProductCode(@RequestParam("pm_code") String pm_code) throws Exception{
		vo =  dao.checkProductCode(pm_code);
		jo = new JSONObject();
		ja = JSONArray.fromObject(vo);
		
		System.out.println(ja);
		if(vo==null)
			jo.put("result", null);
		else
			jo.put("result", ja);
		
		System.out.println(jo);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
			
		return mv;
	}
}

