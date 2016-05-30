/***************************************************************************************************
* 파일명 : CryptoUtil.java
* 설명   : AES 암/복호화 클래스
* 작성일 : 2013-10-18
* 작성자 : (주)버텍스아이디 배재영 책임 연구원
* 수정자 : (주)버텍스아이디 김재중 연구원
* 수정자 : (주)버텍스아이디 전진기 수석 연구원
***************************************************************************************************/
package com.vertexid.utill;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	private static String key = "nevele7tercespoT";

	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0)
			return null;
		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

	public static String encrypt(String message) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
		return byteArrayToHex(encrypted);
	}

	public static String decrypt(String encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] original = cipher.doFinal(hexToByteArray(encrypted));
		String originalString = new String(original, "UTF-8");
		return originalString;
	}

}