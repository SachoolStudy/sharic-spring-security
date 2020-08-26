package com.study.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/********************************************
 * @Class Name : HashUtil
 * @Description : HashUtil Class
 * @Modification : Information
 * @
 * @	 수정일				수정자					수정내용
 * @ -------------------------------------------------------------------
 * @ 2020-08-26		sharic					최초생성
 * @
 * @author : sharic
 * @since : 2020-08-26
 * @version : 1.0
 * @see
 ********************************************/
public final class HashUtil {

	private HashUtil() {};
	
	/********************************************** 
	 * 1. MethodName : md5
	 * 2. Comment : MD5
	 * @param : HttpServletRequest request
	 * @return : String
	 * @throws : 
	 **********************************************/
	public static String md5(String str){
		String MD5 = ""; 
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			};
			
			MD5 = sb.toString();
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			MD5 = null; 
		};
		
		return MD5;
	};
	
	/********************************************** 
	 * 1. MethodName : sha256
	 * 2. Comment : SHA-256
	 * @param : HttpServletRequest request
	 * @return : String
	 * @throws : 
	 **********************************************/
	public static String sha256(String str) {
		String SHA = "";
		
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			};
			
			SHA = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			SHA = null;
		};
		
		return SHA;
	};
};