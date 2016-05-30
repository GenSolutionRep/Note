package com.gensolution.activity.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gensolution.activity.dao.ActivityCvsDAO;
import com.gensolution.activity.vo.ActivityCvsItemVo;
import com.gensolution.activity.vo.ActivityCvsVo;
import com.gensolution.activity.vo.ActivityGridMobileVo;
import com.vertexid.utill.JSONView;
import com.vertexid.utill.Navi;
import com.vertexid.vo.NaviVo;

@Controller
public class ActivityCvsController {
	private ModelAndView mv;			//모델엔뷰
	
	private JSONObject jObj;			//json obj
	private JSONArray jArr;				//json 배열
	
	private List<ActivityCvsVo> cvsVoList;
	private List<ActivityCvsItemVo> cvsItemVoList;
	
	@Autowired
	private ActivityCvsDAO dao;
	
	@Autowired
	private HttpSession session;
//	
//	@Autowired
//	private AttachManager am;						// 파일관리자
	
	@RequestMapping("/10/10-400") 
	public String codePg(){
		return "10/10-400";
	}
	
	
	@RequestMapping("/cvs/list")
	public ModelAndView cvsList(@ModelAttribute("vo") NaviVo naviVo) throws Exception{
		System.out.println("[WEB] CVS 순회 리스트 ");

		System.out.println(naviVo.toString());
		naviVo = dao.cvsListCnt(naviVo);
		String navi = new Navi(naviVo).getPageNavi();
		cvsVoList =  dao.cvsList(naviVo);
		
		jObj = new JSONObject();
		jArr = JSONArray.fromObject(cvsVoList);
		jObj.put("navi", navi);
		jObj.put("fristNo", naviVo.getFirstRowNo());
		jObj.put("cvsVoList", jArr);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	@RequestMapping("/cvs/itemlist")
	public ModelAndView cvsItemList(@RequestParam("drcInnb") String drcInnb) throws Exception{
		cvsItemVoList = dao.cvsItemList(drcInnb);
		String matter = "";
		if(cvsItemVoList.size() == 0){
			matter = dao.cvsGetMatter(drcInnb);
		}
		
		jArr = JSONArray.fromObject(cvsItemVoList);
		
		jObj = new JSONObject();
		jObj.put("cvsVoList", jArr);
		jObj.put("matter", matter);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", jObj);
		return mv;
	}
	
}
