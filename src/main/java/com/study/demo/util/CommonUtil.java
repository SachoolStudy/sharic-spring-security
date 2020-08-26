package com.study.demo.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/********************************************
 * @Class Name : CommonUtil
 * @Description : ���� Util Class
 * @Modification : Information
 * @
 * @	 ������				������					��������
 * @ -------------------------------------------------------------------
 * @ 2020-08-26		sharic					���ʻ���
 * @
 * @author : sharic
 * @since : 2020-08-26
 * @version : 1.0
 * @see
 ********************************************/
public class CommonUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	/********************************************** 
	 * 1. MethodName : getClientIp
	 * 2. Comment : Client IP ��ȸ
	 * @param : HttpServletRequest request
	 * @return : String
	 * @throws : 
	 **********************************************/
	public static String getClientIp(HttpServletRequest request) {
		
		// IP��ȸ
		String ip = request.getHeader("X-Forwarded-For");
		logger.info("> X-FORWARDED-FOR : " + ip);
		
		// ���� ȯ���̳� ���Ͻ� �� �߰������� �ٸ��� ������ ���� IP ����
		// Proxy-Client-IP
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("> Proxy-Client-IP : " + ip);
		};
		
		// WL-Proxy-Client-IP
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info("> WL-Proxy-Client-IP : " + ip);
		};
		
		// HTTP_CLIENT_IP
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			logger.info("> HTTP_CLIENT_IP : " + ip);
		};
		
		// HTTP_X_FORWARDED_FOR
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
		};
		
		// getRemoteAddr
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			logger.info("> getRemoteAddr : " + ip);
		};
		
		logger.info("> Result :  IP Address : " + ip);
		return ip;
	};
};