package com.vertexid.utill;


public class ExcelTest {

	public void test(int index){ 
		int paramIdx = index;
		String key[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
//		String key[] = {"A","B","C","D"}; //4
		int keyLen = (key.length);
		int lastIdx = paramIdx % (key.length);
		String id = "";
//		System.out.println("forLen : "+ index / key.length);
//		System.out.println(forLen);
//		System.out.println("idx : "+ idx);
		while(true) {
			int  thisIdx = 0;
			int  thisIdx2 = 0;
			while(true) {
				thisIdx2 =  paramIdx / keyLen;
				 if(thisIdx2 < keyLen){
//					 thisIdx2 
					 thisIdx = thisIdx2;
					 break;
				 }
			}
			System.out.println("thisIdx : "+ thisIdx);
			if(thisIdx < 1){
				id +=key[lastIdx-1];
				System.out.println("lastIdx : "+ (lastIdx-1));
				break;
			}else{
//				System.out.println("thisIdx : "+ thisIdx);
				id +=key[thisIdx-1];
			}
			
		}
		System.out.println(index+" column : "+id);
	}
	public static void main(String[] args) {
//		int idx=701; 
//		char base_digit[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','Z','Y','Z'};
		char base_digit[] = {'A','B'};
		int convert[] = new int[64];
		int usernum = 3;
		int base = 2;
		int index=0;
		do {
			convert[index] = usernum% base;
			index++;
			usernum = usernum/base;
		} while (usernum != 0);
		
		for (--index; index >= 0 ; --index) {
			System.out.print(base_digit[convert[index]]);
		}
		
//		ExcelTest et = new ExcelTest();
//		for (int i = 0; i < 26; i++) {
//			et.test(i);
//		}
//		et.test(500);
//		et.test(700);
		
//		int idx = 700;
//		String key[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","Z","Y","Z"};
////		String key[] = {"A","B","C","D"}; //4
//		int keyLen = key.length;
////		int lastIdx = idx % key.length;
//		String id = "";
//		System.out.println("keyLen : "+ key.length);
////		System.out.println(forLen);
//		System.out.println("idx : "+ idx);
////		System.out.println("lastIdx : "+ lastIdx);
//		while(true) {
//			int  thisIdx = idx / keyLen;
//			System.out.println("thisIdx : "+ thisIdx);
//			if(thisIdx == 0){
////				id +=key[lastIdx];
//				break;
//			}else{
//				id +=key[thisIdx-1];
//			}
//			idx = thisIdx;
//		}
//		System.out.println("column : "+id);
	}

}
