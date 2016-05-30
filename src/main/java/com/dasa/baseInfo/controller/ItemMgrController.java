package com.dasa.baseInfo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dasa.baseInfo.dao.ItemMgrDAO;
import com.vertexid.utill.JSONView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class ItemMgrController {

	private ModelAndView modelAndView;			//모델엔뷰
	
	private JSONObject jsonObject;				//json 여러개 넘기기
	private JSONArray jsonArray;				//json 배열
	
	@Autowired
	private ItemMgrDAO dao;
	
	/**
	 * @메서드명: infoMgr
	 * @작성일: 2015. 8. 27
	 * @작성자: 김재성
	 * @설명: 분류관리
	 * @return "String"
	 */
	@RequestMapping("/baseInfo/itemMgr") 
	public String infoMgr(){
		return "baseInfo/itemMgr";
	}
	
	/**
	 * @메서드명: selectGroupMgrList
	 * @작성일: 2015. 08. 25
	 * @작성자: 김재성
	 * @설명: 분류관리목록조회
	 * @return "json"
	 */
	@RequestMapping("/baseInfo/selectGroupMgrList")
	public ModelAndView selectGroupMgrList(@RequestParam("param") String param) throws Exception{
		
		List<HashMap<String, String>> groupMgrList = dao.selectGroupMgrList();
		
		jsonArray = JSONArray.fromObject(groupMgrList);		//VO json 변환
		modelAndView = new ModelAndView(new JSONView());	//ajax ModelAndView 생성
		modelAndView.addObject("ajax", jsonArray); 			//JSONView() key 값은 ajax 
		
		return modelAndView;
	}
	
	/**
	 * @메서드명: selectItemMgrList
	 * @작성일: 2015. 08. 25
	 * @작성자: 김재성
	 * @설명: 품목관리목록조회
	 * @return "json"
	 */
	@RequestMapping("/baseInfo/selectItemMgrList")
	public ModelAndView selectItemMgrList(@RequestParam("param") String groupCode) throws Exception{
		
		
		List<HashMap<String, String>> groupMgrList = dao.selectItemMgrList(groupCode);
		
		jsonArray = JSONArray.fromObject(groupMgrList);		//VO json 변환
		modelAndView = new ModelAndView(new JSONView());	//ajax ModelAndView 생성
		modelAndView.addObject("ajax", jsonArray); 			//JSONView() key 값은 ajax 
		
		return modelAndView;
	}
	
	@RequestMapping("/baseInfo/test")
	public ModelAndView test(@RequestParam("param") String addr) throws Exception{
		
		System.out.println("주소 parameter : "+addr);
		
		String urlStr;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;
		double position[];
		
		try {
			urlStr = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address=" + URLEncoder.encode(addr, "utf-8");
			URL url = new URL(urlStr);
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(1000);
			// read the output from the server
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				stringBuilder = new StringBuilder();
	 
				String line = null;
				while ((line = reader.readLine()) != null)
				{
					stringBuilder.append(line + "\n");
				}
				
				System.out.println(stringBuilder.toString());
				
				JSONObject location = JSONObject.fromObject(stringBuilder.toString());
				JSONArray arr = (JSONArray) location.get("results");
				
				location = (JSONObject) arr.get(0);
				location = (JSONObject) location.get("geometry");
				location = (JSONObject) location.get("location");
				
				position = new double[2];
				
				position[0] = Double.parseDouble(String.valueOf(location.get("lat")));
				position[1] = Double.parseDouble(String.valueOf(location.get("lng")));
				
				
				System.out.println("111111111111111111 : "+String.valueOf(location.get("lat")));
				System.out.println("222222222222222222 : "+String.valueOf(location.get("lng")));
				
			} else {
				position = null;
			}
		} catch (IOException e) {
			position = null;
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		
		return modelAndView;
	}
}
