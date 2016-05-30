package com.vertexid.utill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.web.servlet.ModelAndView;

import com.vertexid.vo.SessionVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * @파일명: CommonUtil.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명:
 */
public class CommonUtil {

	public static JSONObject setSuccessResponse(JSONObject body) throws Exception {
		JSONObject response = new JSONObject();
		JSONObject header = new JSONObject();

		//Response Header
		header.put("result", Constant.SUCCESS_CD);
		header.put("msg", Constant.SUCCESS_MSG);

		//Response
		response.put("header", header);
		response.put("body", body);

		return response;
	}

	public static JSONObject setFailResponse() throws Exception {
		return setFailResponse(Constant.FAIL_CD, Constant.FAIL_MSG);
	}

	public static JSONObject setFailResponse(int code, String message) throws Exception {
		JSONObject response = new JSONObject();
		JSONObject header = new JSONObject();
		JSONObject body = new JSONObject();

		//Response Header
		header.put("result", code);
		header.put("msg", message);

		//Response
		response.put("header", header);
		response.put("body", body);

		return response;
	}

	public static ModelAndView setModelAndView(JSONObject response) throws Exception {
		ModelAndView mv = new ModelAndView(new JSONView());
		mv.addObject("ajax", response);
		return mv;
	}

	public static ModelAndView setModelAndViewOfInt(int result) throws Exception {
		JSONObject response = new JSONObject();
		response.put("result", Integer.toString(result));
		return setModelAndView(response);
	}

	public static ModelAndView setModelAndViewOfString(String result) throws Exception {
		JSONObject response = new JSONObject();
		response.put("result", result);
		return setModelAndView(response);
	}

	public static void setSessionVo(HttpSession session) throws Exception {
		SessionVo sessionVo = (SessionVo) session.getAttribute("sessionVo");
		if (sessionVo == null) {
			session.setAttribute("sessionVo", new SessionVo());
		}
	}
	
	public static String getCurrentDate() throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(new java.util.Date());
	}

	public static String getCurrentDateTime() throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new java.util.Date());
	}

	public static String getDutyCode(String type) throws Exception {
		String dutyCode = "";
		if (type.equals("FIX")) {
			dutyCode = "0000000006";//고정여사원
		} else if (type.equals("RND")) {
			dutyCode = "0000000007";//순회여사원
		} else if (type.equals("TIMHDR")) {
			dutyCode = "0000000008";//팀장
		} else if (type.equals("MNGR")) {
			dutyCode = "0000000009";//관리자
		}
		return dutyCode;
	}

	/**
	 * 리스트 안에 리스트 넣기 
	 * @param list		부모리스트
	 * @param childList 자식리시트
	 * @param key		키명
	 * @param code		키
	 * @param childKey	부모키명
	 * @param childCode	부모키
	 * @return			JSONObject
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject makeMobileRespons(List<Map<String, Object>> list, List<Map<String, String>> childList ,String key, String code, String childKey, String childCode,JSONObject body) throws Exception{
		JSONObject response = new JSONObject();
		
		if (list != null && list.size() > 0) {
			Map<String,Map<String,Object>> jsonMap = new HashMap<String, Map<String,Object>>();
			//System.out.println("jsonMap list: " + list);
			for (Map<String, Object> prdMap : list ) {
				jsonMap.put((String)prdMap.get(code), prdMap);
			}
			for (Map<String, String> itemMap : childList ) {
				if(jsonMap.get(itemMap.get(childCode)) != null){
					if(jsonMap.get(itemMap.get(childCode)).get(childKey) == null){
						List<Map<String, String>> itemList=  new ArrayList<Map<String, String>>();
						jsonMap.get(itemMap.get(childCode)).put(childKey, itemList);
					}
					((ArrayList<Map<String, String>>) jsonMap.get(itemMap.get(childCode)).get(childKey)).add(itemMap);
				}
			}
			//System.out.println("jsonMap : " + jsonMap);
			list = new ArrayList<Map<String, Object>>(jsonMap.values());
			JSONArray jArr = JSONArray.fromObject(list);
			//System.out.println("jArr : " + jArr);
		
			body.put(key, jArr);
			response = setSuccessResponse(body);
			//System.out.println("response : " + response);
		} else {
			response = setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		return response;
		
	}
	/**
	 * 리스트 안에 리스트 넣기 
	 * @param list		부모리스트
	 * @param childList 자식리시트
	 * @param key		키명
	 * @param code		키
	 * @param childKey	부모키명
	 * @param childCode	부모키
	 * @return			JSONObject
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject makeMobileRespons(List<Map<String, Object>> list, List<Map<String, String>> childList ,String key, String code, String childKey, String childCode, List<Map<String, String>> childList2, String childKey2, String childCode2) throws Exception{
		JSONObject response = new JSONObject();
		
		if (list != null && list.size() > 0) {
			Map<String,Map<String,Object>> jsonMap = new HashMap<String, Map<String,Object>>();
			//System.out.println("jsonMap list: " + list);
			for (Map<String, Object> prdMap : list ) {
				jsonMap.put((String)prdMap.get(code), prdMap);
			}
			for (Map<String, String> itemMap : childList ) {
				if(jsonMap.get(itemMap.get(childCode)) != null){
					if(jsonMap.get(itemMap.get(childCode)).get(childKey) == null){
						List<Map<String, String>> itemList=  new ArrayList<Map<String, String>>();
						jsonMap.get(itemMap.get(childCode)).put(childKey, itemList);
					}
					((ArrayList<Map<String, String>>) jsonMap.get(itemMap.get(childCode)).get(childKey)).add(itemMap);
				}
			}
			for (Map<String, String> itemMap : childList2 ) {
				//System.out.println("childList2" + itemMap);
				if(jsonMap.get(itemMap.get(childCode2)) != null){
					//System.out.println("배열생성전 : "+jsonMap.get(itemMap.get(childCode2)).get(childKey2));
					if(jsonMap.get(itemMap.get(childCode2)).get(childKey2) == null){
						//System.out.println("배열생성 : "+ childKey2);
						List<Map<String, String>> itemList2=  new ArrayList<Map<String, String>>();
						jsonMap.get(itemMap.get(childCode2)).put(childKey2, itemList2);
					}
					((ArrayList<Map<String, String>>) jsonMap.get(itemMap.get(childCode2)).get(childKey2)).add(itemMap);
				}
			}
			//System.out.println("jsonMap : " + jsonMap);
			list = new ArrayList<Map<String, Object>>(jsonMap.values());
			JSONArray jArr = JSONArray.fromObject(list);
			//System.out.println("jArr : " + jArr);
			
			JSONObject body = new JSONObject();
			body.put(key, jArr);
			response = setSuccessResponse(body);
			//System.out.println("response : " + response);
		} else {
			response = setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		return response;
		
	}

	@SuppressWarnings("rawtypes")
	public static ModelAndView makeMobileRespons(Object obj, String key) throws Exception {
		JSONObject response = new JSONObject();
		
		if (obj != null ) {
			
			if(obj instanceof List || obj instanceof ArrayList ){
				if(((List)obj).size() > 0){
					JSONArray jArr = JSONArray.fromObject(obj);
					//System.out.println("jArr : " + jArr);
					
					JSONObject body = new JSONObject();
					body.put(key, jArr);
					response = setSuccessResponse(body);
				}else{
					response = setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
				}
			}else{
				JSONObject jVo = JSONObject.fromObject(JSONSerializer.toJSON(obj));
//				JSONObject body = new JSONObject();
//				body.put(key, jVo);
				response = setSuccessResponse(jVo);
			}
			
			
		} else {
			response = setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		//System.out.println("response : " + response);
		return setModelAndView(response);
	}
	public static ModelAndView makeMobileSaveRespons(JSONObject response , int cnt) throws Exception {
		
		if(cnt > 0){
			response = new JSONObject();
			response.put("result_", cnt);
			response = CommonUtil.setSuccessResponse(response);
		}else {
			response = CommonUtil.setFailResponse(Constant.FAIL_CD, Constant.FAIL_MSG);
		}
		return CommonUtil.setModelAndView(response);
	}

	
    public static List<Map<String,String>> stringToList(String jsonString)throws JSONException {
    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	if(jsonString !=null && !jsonString.equals("")){
    		JSONArray jArr = JSONArray.fromObject(jsonString);
    		//System.out.println("jArr : " + jArr);
    		if(jArr !=null){
    			for(int i = 0; i < jArr.size(); i++) {
    				JSONObject value = (JSONObject) jArr.get(i);
    				Map<String, String> map = new HashMap<String, String>();
    				if(value instanceof JSONObject) {
    					map= jsonObjToMap(value);
    				}
    				list.add(map);
    			}	
    		}
    	}
    	return list;
    }
    
    @SuppressWarnings("unchecked")
	public static Map<String, String> jsonObjToMap(JSONObject object) throws JSONException{
        Map<String, String> map = new HashMap<String, String>();
        //System.out.println("object: " + object);
        Iterator<String> keysItr = object.keySet().iterator();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            //System.out.println("object.get(key) : " + object.get(key));
            String value = "";
            if(object.get(key) instanceof JSONArray) {
    			value= object.get(key)+"";
    		}else{
    			value = (String) object.get(key);
    		}
            map.put(key, value);
        }
        return map;
    }
    
    
    // 신규 배열  모두 배열로 만들기
    public static List<Map<String,Object>> stringToObjList(String jsonString)throws JSONException {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	JSONArray jArr = JSONArray.fromObject(jsonString);
    	//System.out.println("넘어온 처음 배열 : " + jArr);
    	if(jArr !=null){
    		for(int i = 0; i < jArr.size(); i++) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			if(jArr.get(i) instanceof JSONObject) {
    				map= objToMap((JSONObject) jArr.get(i));
    			}
    			list.add(map);
    		}	
    	}
    	return list;
    }
    
    // 신규 배열  모두 배열로 만들기
    public static Map<String,String> stringToObj(String jsonString)throws JSONException {
    	if(jsonString == null || jsonString.equals("")){
    		return null;
    	}
    	Map<String, String> map = new HashMap<String, String>();
    	try {
    		JSONObject object = JSONObject.fromObject(JSONSerializer.toJSON(jsonString));
    		//System.out.println("1차 아이템: " + object);
    		@SuppressWarnings("unchecked")
    		Iterator<String> keysItr = object.keySet().iterator();
    		while(keysItr.hasNext()) {
    			String key = keysItr.next();
    			map.put(key,object.get(key)+"");
    		}
			
		} catch (Exception e) {
			return null;
		}
		return map;
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> objToMap(JSONObject object) throws JSONException{
    	Map<String, Object> map = new HashMap<String, Object>();
    	//System.out.println("1차 아이템: " + object);
    	Iterator<String> keysItr = object.keySet().iterator();
    	while(keysItr.hasNext()) {
    		String key = keysItr.next();
    		if(object.get(key) instanceof JSONArray) {
    			map.put(key,arrToListMap((JSONArray)object.get(key))); //(List<Map<String,String>>)
    		}else if(object.get(key) instanceof JSONObject) {
    			map.put(key,objToMap((JSONObject)object.get(key)));
//    		}else if(object.get(key) instanceof String) {
    		}else{
    			map.put(key,object.get(key)+"");
    		}
    	}
    	return map;
    }
    public static List<Map<String,Object>> arrToListMap(JSONArray jArr) throws JSONException{
    	List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
    	//System.out.println("2차 배열: " + jArr);
    	if(jArr !=null){
    		for(int i = 0; i < jArr.size(); i++) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			if(jArr.get(i) instanceof JSONObject) {
    				map= objToMap((JSONObject) jArr.get(i));
    			}
    			listMap.add(map);
    		}	
    	}
    	return listMap;
    }
    
    public static String getBaseDe(String currDate) throws ParseException{
    	Date today = new SimpleDateFormat("yyyyMMdd").parse(currDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //System.out.println("요일(1~7, 1:일요일): " + dayOfWeek);

        int diffCnt = 0;
        if (dayOfWeek >= 5) {
           diffCnt = 5 - dayOfWeek;
        } else {
           diffCnt = 5 - dayOfWeek - 7;
        }

        calendar.add(Calendar.DATE, diffCnt);
        Date baseDate = calendar.getTime();
        String date = new SimpleDateFormat("yyyyMMdd").format(baseDate);
        return date;
    }
    
    public static String getSysBaseDe() throws ParseException {
    	return getBaseDe(new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }

    public static String getCalcDe(String paramDate, int diffCnt) throws ParseException{
    	Date today = new SimpleDateFormat("yyyyMMdd").parse(paramDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        
        calendar.add(Calendar.DATE, diffCnt);
        Date calcDe = calendar.getTime();
        String date = new SimpleDateFormat("yyyyMMdd").format(calcDe);
        return date;
    }

	public static List<Map<String, Object>>  makeMobileChildSet(List<Map<String, Object>> targetlist, List<Map<String, Object>> childlist, String targetCode, String childCode,String key) throws Exception {
//		JSONObject response = new JSONObject();
		
		if (targetlist != null && targetlist.size() > 0) {
			Map<String,Map<String,Object>> jsonMap = new HashMap<String, Map<String,Object>>();
			//System.out.println("jsonMap list: " + targetlist);
			for (Map<String, Object> targetMap : targetlist ) {
				jsonMap.put((String)targetMap.get(targetCode), targetMap);
			}
			for (Map<String, Object> itemMap : childlist ) {
				if(jsonMap.get(itemMap.get(childCode)) != null){
					if(jsonMap.get(itemMap.get(childCode)).get(key) == null){
						List<Map<String, String>> itemList=  new ArrayList<Map<String, String>>();
						jsonMap.get(itemMap.get(childCode)).put(key, itemList);
					}
					((ArrayList<Map<String, Object>>) jsonMap.get(itemMap.get(childCode)).get(key)).add(itemMap);
				}
			}
			//System.out.println("jsonMap : " + jsonMap);
			targetlist = new ArrayList<Map<String, Object>>(jsonMap.values());
//			JSONArray jArr = JSONArray.fromObject(list);
//			System.out.println("jArr : " + jArr);
//		
//			body.put(key, jArr);
//			response = setSuccessResponse(body);
//			System.out.println("response : " + response);
		} else {
//			response = setFailResponse(Constant.NO_RESULT_CD, Constant.NO_RESULT_MSG);
		}
		return targetlist;
	}

	public static JSONObject makeMobileListRespons(
			List<Map<String, Object>> evnlist, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getSysDe() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static JSONObject getUpdateInfo(HttpSession session) throws Exception {
		String filePath = session.getServletContext().getRealPath("/properties");
		String fileName = "updateInfoOfApp.txt";
		
		JSONObject body = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			File file = new File(filePath, fileName);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			
			String readLine = null;
			while ((readLine = br.readLine()) != null) {
				sb.append(readLine);
			}
			body = JSONObject.fromObject(sb.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null) { try{br.close();} catch(IOException e){} }			
			if (fr != null) { try{fr.close();} catch(IOException e){} }
		}
		
		return body;
	}
}
