package com.vertexid.conteroller;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.utill.AttachManager;
import com.vertexid.utill.JSONView;

/**
 * @Class명: AttachController
 * @작성일: 2015. 10. 02
 * @작성자: 김진호
 * @설명: 공통코드 관리
 */
@Controller
public class AttachController {
private ModelAndView mv;			
	
	
	private JSONObject jo;				
	
	@Autowired
	private AttachManager am;						// 파일관리자
	
	
	/**
	 * @메서드명: prevViewImge
	 * @작성일: 2015. 10. 12
	 * @작성자: 김진호
	 * @설명: 사진미리보기
	 * @return ModelAndView
	*/
	
	
	@RequestMapping("/photo/prev")
	public ModelAndView  prevViewImge(@RequestParam("imgFile") MultipartFile imgFile )throws Exception {
		System.out.println("photoPrev : " + imgFile.getOriginalFilename());
//		String src = "/resources/img/fivicon.png";
		String src = am.updatePrevViewFile(imgFile);
		
		jo = new JSONObject();
		jo.put("src", src);
		System.out.println("src : " + src);
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajaxForm", jo); 			 
		
		return mv;
	}
	
	
	@RequestMapping("/file/delete")
	public ModelAndView  attachDelete(@RequestParam("ai_no") int ai_no )throws Exception {
		System.out.println("fileDelete : " + ai_no);
		int cnt = am.attachDelete(ai_no);
		
		mv = new ModelAndView(new JSONView());
		mv.addObject("ajaxForm", cnt); 			 
		
		return mv;
	}
	
	@RequestMapping("/file/down")
	@ResponseBody
	public FileSystemResource  attachDownload(@RequestParam("ai_no") int ai_no ,HttpServletResponse response)throws Exception {
		System.out.println("file download : " + ai_no);
		return am.downloadFile(ai_no , response);
	}
}
