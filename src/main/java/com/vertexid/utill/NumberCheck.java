package com.vertexid.utill;

public class NumberCheck {
	
	/**
     * 메소드명      : nullToZero
     * 설명          :  null 을 확인 하는 메소드
     * 메소드리턴1    :   null일경우 0을리턴  Object(int) 
     * 관련 XML(SQL) : 
     */
	public static int nullToZero(String tmp){
		int num = 0;
		if(tmp !=null){
			num= numberCheck(tmp) ? Integer.parseInt(tmp) : 0;
		}
		return num;
	}
	
	public static float setFloat(String tmp){
		float num = 0;
		if(tmp !=null){
			num= floatCheck(tmp) ? Float.parseFloat(tmp) : 0;
		}
		return num;
	}

	/**
     * 메소드명      : numberCheck
     * 설명          :  수만 입력되었는지 확인하는 메소드
     * 메소드리턴1    :   isnum -  모두 숫자일경우 true를  리턴한고 아닐경우 false를 리턴한다 (boolean) 
     * 관련 XML(SQL) : 
     */	
	public static boolean numberCheck(String tmp){
		boolean isnum = true;
		int len = tmp.length();
		if(len  > 0){
			for(int i=0; i<len;i++){
				int c= tmp.charAt(i);
				if (c != 45 && (c<48 || c> 57)) {
					isnum=false;
					break;
				}
			}				
		}else{
			isnum = false;
		}
		return isnum;
	}
	public static boolean floatCheck(String tmp){
		boolean isnum = true;
		int len = tmp.length();
		for(int i=0; i<len;i++){
			int c= tmp.charAt(i);
			if (c != 46 && (c<48 || c> 57)) {
				isnum=false;
				break;
			}
		}				
		return isnum;
	}
}
