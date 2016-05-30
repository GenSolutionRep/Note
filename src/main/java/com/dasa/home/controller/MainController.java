package com.dasa.home.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	/**
	 * @메서드명: mainPg
	 * @작성일: 2015. 08. 25
	 * @작성자: 김진호
	 * @설명: 메인화면 이동
	 * @return "String"
	 */
	@RequestMapping("/main/main") 
	public String mainPg(HttpSession session){
		return "main/main";
	}
	/**
	 * @메서드명: mainPg
	 * @작성일: 2015. 08. 25
	 * @작성자: 김진호
	 * @설명: 메인화면 이동
	 * @return "String"
	 */
	@RequestMapping("/temp") 
	public String tempPg(HttpSession session){
		return "temp/temp";
	}
	@RequestMapping("/temp2") 
	public String temp2Pg(HttpSession session){
		return "temp/temp2";
	}
}
