package com.vertexid.utill;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.view.AbstractView; 

import com.vertexid.vo.SessionVo;


/**
 * @Class명: JSONView
 * @작성일: 2014. 9. 18
 * @작성자: 김진호
 * @설명: Ajax 핸들러
 */
public class JSONView extends AbstractView {    
	
	
	public JSONView(){     
		super();     
		setContentType( "text/x-json; charset=utf-8");     
	}
	

	
	/**
	 * @메서드명: renderMergedOutputModel
	 * @작성일: 2014. 9. 18
	 * @작성자: 김진호
	 * @설명: Ajax 세션 처리및 json 컨트롤
	 * @param String code
	 * @return ModelAndView
	*/
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType( "text/x-json; charset=utf-8");        
		response.setHeader("Cache-Control", "no-cache");                 
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		SessionVo sessionVo = (SessionVo) session.getAttribute("sessionVo");
		if(sessionVo == null){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			throw new AccessDeniedException("세션 정보가 없습니다.");
			
		} else{
			if(model.get("ajax") != null){
				if(model.get("ajax") instanceof JSONObject ){
					JSONObject jobj = (JSONObject) model.get("ajax");                 
					out.write(jobj.toString());
					
				}else if(model.get("ajax") instanceof JSONArray){
					JSONArray jobj = (JSONArray) model.get("ajax");                 
					out.write(jobj.toString());
					
				}else if(model.get("ajax") instanceof String){
					String jobj = (String) model.get("ajax");                 
					out.write(jobj);
					
				}else if(model.get("ajax") instanceof Integer){
					int jobj = (Integer) model.get("ajax");                 
					out.write(jobj+"");
				}else{
					out.write("-1");
				}
			}else if(model.get("ajaxForm") != null){
				System.out.println("ajaxForm response!!");
				response.setContentType( "text/html; charset=utf-8");
				if(model.get("ajaxForm") instanceof JSONObject ){
					JSONObject jobj = (JSONObject) model.get("ajaxForm");                 
					out.write(jobj.toString());
					
				}else if(model.get("ajaxForm") instanceof JSONArray){
					JSONArray jobj = (JSONArray) model.get("ajaxForm");                 
					out.write(jobj.toString());
					
				}else if(model.get("ajaxForm") instanceof String){
					String jobj = (String) model.get("ajaxForm");                 
					out.write(jobj);
					
				}else if(model.get("ajaxForm") instanceof Integer){
					int jobj = (Integer) model.get("ajaxForm");                 
					out.write(jobj+"");
				}else{
					out.write("-1");
				}			
			}else{
				out.write("-1");			
			}
			out.flush();
		}
		
	}      
}