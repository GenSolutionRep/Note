package com.gensolution.activity.controller;

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

import com.gensolution.activity.dao.ActivityOptionDAO;
import com.gensolution.activity.vo.ActivityOptionTreeVo;
import com.gensolution.activity.vo.ActivityOptionVo;
import com.vertexid.utill.JSONView;

@Controller
public class ActivityOptionController {
	private ModelAndView mv;			//모델엔뷰
	
	private JSONObject jObj;			//json obj
	private JSONArray jArr;				//json 배열
	
	private List<ActivityOptionVo> actOptVoList; 
	private List<ActivityOptionTreeVo> treeVoList;	//하위 코드 목록
	private ActivityOptionVo optionVo;	//하위 코드 목록
	
	@Autowired
	private ActivityOptionDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/99/99-500") 
	public String optionPg(){
		return "/99/99-500";
	}
	@RequestMapping("/option/fixing-options.json") 
	public String jsonFixingPg(){
		return "json/fixing-option";
	}
	@RequestMapping("/option/rnd-options.json") 
	public String jsonRndPg(){
		return "json/rnd-option";
	}
	
	@RequestMapping("/option/tree")
	public ModelAndView optionTree(@RequestParam Map<String, String> map) throws Exception{
		jObj = new JSONObject();
		
		treeVoList =  dao.optionTree(map);			
		
		jArr = JSONArray.fromObject(treeVoList);		//VO json 변환
		jObj.put("result", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		
		return mv;
	}
	
	@RequestMapping("/option/list") 
	public ModelAndView pogOptionList(@RequestParam("optionCode") String optionCode  ) throws Exception{
		
		jObj = new JSONObject();
		
		actOptVoList =  dao.optionList(optionCode);
		jArr = JSONArray.fromObject(actOptVoList);
		jObj.put("optionList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	@RequestMapping("/option/view") 
	public ModelAndView optionView(@RequestParam("optionCode") String optionCode  ) throws Exception{
		
		jObj = new JSONObject();
		
		optionVo =  dao.optionView(optionCode);
		jObj = JSONObject.fromObject(JSONSerializer.toJSON(optionVo));
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	@RequestMapping("/option/save") 
	public ModelAndView optionSave(@ModelAttribute("vo") ActivityOptionVo vo ) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		vo.setRegist_man(login_no);
		
		ActivityOptionVo result =  dao.optionSave(vo);
		jArr = JSONArray.fromObject(result.getOptionArr());
		jObj = new JSONObject();
		jObj.put("cnt", result.getCnt());
		jObj.put("usingArr", jArr);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
	
	@RequestMapping("/option/customSave") 
	public ModelAndView optionCustomSave(@ModelAttribute("vo") ActivityOptionVo vo ) throws Exception{
		String login_no = (String)session.getAttribute("login_no");
		vo.setRegist_man(login_no);
		ActivityOptionVo result =  dao.optionCustomSave(vo);
		jArr = JSONArray.fromObject(result.getOptionArr());
		jObj = new JSONObject();
		jObj.put("cnt", result.getCnt());
		jObj.put("usingArr", jArr);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
}
