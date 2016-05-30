package com.vertexid.utill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.vo.SessionVo;


public class SessionInterceptor implements HandlerInterceptor{
	
	@Autowired
	HttpSession session;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod)handler;
//			if(method.getBeanType().getCanonicalName().toString().equals("com.seoulmilk.asset.controller.MobileController")){
//			}else{
				SessionVo sessionVo = (SessionVo) session.getAttribute("sessionVo");
				if (sessionVo == null && modelAndView != null) {
//					response.setStatus(HttpServletResponse.SC_ACCEPTED, "세션 종료!!");
					System.out.println("인터셉트");
					modelAndView.setViewName("redirect:/");
//				}else if(sessionVo != null){
//					sessionVo.setSystem_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}else if (sessionVo == null && modelAndView == null) {
//					response.setStatus(HttpServletResponse.SC_ACCEPTED);
					modelAndView =  new  ModelAndView();
					modelAndView.setViewName("redirect:/");
				}
//			}
		}
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
