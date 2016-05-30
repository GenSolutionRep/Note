package com.vertexid.utill;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Encoder {
	public static String isoToEuc(String tmp){
		String euc = "";
		try {
			euc = new String(tmp.getBytes("ISO-8859-1"), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	public static String isoToUtf(String tmp){
		String utf = "";
		try {
			utf = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utf;
	}
	
	public static String eucToIso(String tmp){
		String iso = "";
		try {
			iso = new String(tmp.getBytes("EUC-KR"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return iso;
	}
	
	public static String utfToEuc(String tmp){
		String euc = "";
		try {
			euc = new String(tmp.getBytes("UTF-8"), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	
	public static String eucToUtf(String tmp){
		String utf = "";
		try {
			utf = new String(tmp.getBytes("EUC-KR"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utf;
	}
	@SuppressWarnings("rawtypes")
	public static Map<String,String> isoToUtfMap(Map<String,String> tmp){
//		Map<String,String> utf = new HashMap<String,String>();
		if(tmp != null){
			Set key = tmp.keySet();
			for (Iterator iterator = key.iterator(); iterator.hasNext();) {
				String keyName = (String) iterator.next();
				String valueName = (String) tmp.get(keyName);
				tmp.put(keyName, isoToUtf(valueName));
			}
		}
		return tmp;
	}
	public static String utfToiso(String tmp) {
	    String utf = "";
	    try {
	      utf = new String(tmp.getBytes("UTF-8"), "ISO-8859-1");
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    }
	    return utf;
	  }
}
