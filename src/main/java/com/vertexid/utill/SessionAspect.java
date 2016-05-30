package com.vertexid.utill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.vertexid.vo.SessionVo;



//@Aspect
public class SessionAspect {
	
	@Autowired
	HttpSession session;
	
	public Object aroundRoleProcess(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Object proceedObj = null;
		
		SessionVo sessionVo = (SessionVo) session.getAttribute("sessionVo");
		System.out.println("????????????????????SessionAspect????");
		// Role과 Session은 여기서 체크한다.
//		System.out.println("-----------------------------aop-------------------------------");
//		HttpServletRequest request = (HttpServletRequest)args[0];
//		System.out.println(">>>>>>>>>>>>>>>>>> Login ID : " + sessionVo.getUser_id() + " <<<<<<<<<<<<<<<<<<<<<");
//		System.out.println("-----------------------------aop-------------------------------");
		try{
			// 요청 Method를 실행한다.
//			if(sessionVo == null){
//				return "redirect:/";
//			}
			proceedObj = joinPoint.proceed();
		} catch(Throwable e){
			// Method 실행시 오류가 났을 경우
			throw e;
		}
		return proceedObj;
	}

}
