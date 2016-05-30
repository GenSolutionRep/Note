package com.vertexid.conteroller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.dao.CommonCodeDAO;
import com.vertexid.utill.CommonUtil;
import com.vertexid.utill.JSONView;
import com.vertexid.vo.CodeVo;
import com.vertexid.vo.JsonVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;



/**
 * @Class명: CommonCodeController
 * @작성일: 2014. 9. 18
 * @작성자: 김진호
 * @설명: 공통코드 관리
 */
@Controller
public class CodeController {
	
	private ModelAndView mv;			//모델엔뷰
	private List<CodeVo> codeVoList;	//하위 코드 목록
	private CodeVo vo;					//코드 정보
	
	
	private JSONObject jo;				//json obj
	private JSONArray ja;				//json 배열
	
	@Autowired
	private CommonCodeDAO dao;		//모델
	
	@Autowired
	private HttpSession session;		//세션	
	
	/**
	 * @메서드명: codePg
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 공통코드관리 화면 이동
	 * @return "String"
	 */
	@RequestMapping("/99/99-400")
	public String codePg() throws Exception{
		System.out.println("codePg 이동");
		System.out.println("user_id : " + (String) session.getAttribute("user_id"));
		return "/99/99-400";
	}
	@RequestMapping("/jsonTest")
	public String jsonTestPg() throws Exception{
		CommonUtil.setSessionVo(session);
		return "/json/json-test";
	}
	@RequestMapping("/sendJson")
	public ModelAndView sendJson(@ModelAttribute("vo") JsonVo vo ) throws Exception{
		CommonUtil.setSessionVo(session);
		
		System.out.println(vo.toString());
		mv = new ModelAndView(new JSONView());
		jo = new JSONObject();
//		jo.put("result", vo.getDataString());
		jo.put("result", JSONArray.fromObject(vo));
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	/**
	 * @메서드명: codeTree
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 트리 목록조회
	 * @param String code
	 * @return ModelAndView
	*/
	@RequestMapping("/code/codeTree")
	public ModelAndView codeTree(@RequestParam Map<String, String> map) throws Exception{
		System.out.println("codeTree 조회 : " + map.get("c_code"));
		jo = new JSONObject();
		
		codeVoList =  dao.codeTree(map);			
		
		ja = JSONArray.fromObject(codeVoList);		//VO json 변환
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		
		return mv;
	}
	
	
	
	/**
	 * @메서드명: codeComboBox
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 상위 코드 콤보박스 조회
	 * @param String parent_code
	 * @return ModelAndView
	*/
	@RequestMapping("/code/codeComboBox")
	public ModelAndView codeComboBox(@RequestParam("c_parent_code") String c_parent_code) throws Exception{
		//System.out.println("codeComboBox : " + c_parent_code);
		
		codeVoList =  dao.codeComboBox(c_parent_code); 
		
		ja = JSONArray.fromObject(codeVoList);		// List json 변환
		
		mv = new ModelAndView(new JSONView());	
		mv.addObject("ajax", ja); 		
		return mv;
	}
	/**
	 * @메서드명: codeComboBox
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 상위 코드 콤보박스 조회
	 * @param String parent_code
	 * @return ModelAndView
	 */
	@RequestMapping("/ds_code/codeComboBox")
	public ModelAndView ds_codeComboBox(@RequestParam("c_parent_code") String c_parent_code) throws Exception{
		CommonUtil.setSessionVo(session);
		codeVoList =  dao.codeComboBox(c_parent_code); 
		
		List<Map<String, String>> dsCodeVoList = new ArrayList<Map<String, String>>();
		if(codeVoList !=  null){
			for (CodeVo cVo: codeVoList) {
				Map<String, String> map =  new HashMap<String, String>();
				map.put("c_code", cVo.getC_code());
				map.put("c_name", cVo.getC_name());
				dsCodeVoList.add(map);
			}
		}
		
		ja = JSONArray.fromObject(dsCodeVoList);		// List json 변환
		
		jo = new JSONObject();
		jo.put("result", ja);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);	
		return mv;
	}
	
	/**
	 * @메서드명: codeForCal
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 상위 코드 콤보박스 조회
	 * @param String parent_code
	 * @return ModelAndView
	*/
	@RequestMapping("/code/codeForCal")
	public ModelAndView codeForCal(@RequestParam("c_parent_code") String c_parent_code,
									@RequestParam("c_ext1") String c_ext1) throws Exception{
		
		codeVoList =  dao.codeForCal(c_parent_code, c_ext1); 
		
		ja = JSONArray.fromObject(codeVoList);		// List json 변환
		
		mv = new ModelAndView(new JSONView());	
		mv.addObject("ajax", ja); 		
		return mv;
	}
	
	
	
	/**
	 * @메서드명: codeList
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 하위 코드 목록 조회
	 * @param String code
	 * @return ModelAndView
	*/	
	@RequestMapping("/code/codeList")
	public ModelAndView codeList(@RequestParam Map<String, String> map) throws Exception{
		
		codeVoList =  dao.codeList(map);
		
		jo = new JSONObject();
		ja = JSONArray.fromObject(codeVoList);
		jo.put("result", ja);
		
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jo);
		return mv;
	}
	
	
	
	/**
	 * @메서드명: codeRow
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 코드 상세 정보 조회
	 * @param String code
	 * @return ModelAndView
	*/		
	@RequestMapping("/code/codeView")
	public ModelAndView codeView(@RequestParam Map<String, String> map) throws Exception{
		
		vo =  dao.codeView(map);
			
		jo = JSONObject.fromObject(JSONSerializer.toJSON(vo));
		System.out.println("jsonObject : " + jo.toString());
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax",jo);
		return mv;
	}
	
	/**
	 * @메서드명: codeInsert
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 신규 코드 등록
	 * @param CodeVo vo
	 * @return ModelAndView
	*/	
	@RequestMapping("/code/codeSave")
	public ModelAndView codeInsert(@ModelAttribute("vo") CodeVo vo) throws Exception{
		System.out.println("codeInsert");
		String userId = (String) session.getAttribute("login_no");
		vo.setC_in_id((String) userId);
		vo.setC_up_id((String) userId);
		int cnt =  dao.codeSave(vo);
		System.out.println("vo.toString() : " + vo.toString());
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt+"");
		return mv;
	}
	
	
	/**
	 * @메서드명: codeDelete
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: 코드 삭제 
	 * @param String code, HttpSession session
	 * @return ModelAndView
	*/	
	@RequestMapping("/code/codeDelete")
	public ModelAndView codeDelete(@RequestParam String c_code) throws Exception{
		vo = new CodeVo();
		vo.setC_code(c_code);
		String userId = (String) session.getAttribute("login_no");
		vo.setC_in_id(userId);
		vo.setC_up_id(userId);
		
		int cnt =  dao.codeDelete(vo);
		
		System.out.println("cnt : " + cnt);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", cnt+"");
		return mv;
	}

}
