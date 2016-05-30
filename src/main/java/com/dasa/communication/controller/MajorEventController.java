package com.dasa.communication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MajorEventController {

	@RequestMapping("/majorEvent") 
	public String codePg(){
//		return "baseInfo/code";
		return "communication/majorEvent";
	}
}
