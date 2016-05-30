package com.vertexid.utill;

/**
 * @파일명: StringCheck.java
 * @작성일: 2015.10.01
 * @작성자: 김광욱
 * @설명: 문자체크
 */
public class StringCheck {

	public static String nullToBlank(String tmp) {
		return (tmp == null || tmp.trim().length() == 0) ? "" : tmp;
	}

	public static int nullToZero(String pram) {
		int val = pram == null || pram.equals("") ? 0 : Integer.parseInt(pram);
		return val;
	}
	public static String nullToRplace(String pram,String str) {
		String val = pram == null || pram.equals("") ? str: pram;
		return val;
	}

	public static boolean isNull(String param) {
		return (param == null || param.trim().length() == 0) ? true : false;
	}

	public static String isNull(String param, String value) {
		return isNull(param) ? value : param;
	}

}
