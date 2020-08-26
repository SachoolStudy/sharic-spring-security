package com.study.demo.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/********************************************
 * @Class Name : CommonUtil
 * @Description : 공통 Util Class
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
public class CommonUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	/********************************************** 
	 * 1. MethodName : getClientIp
	 * 2. Comment : Client IP 조회
	 * @param : HttpServletRequest request
	 * @return : String
	 * @throws : 
	 **********************************************/
	public static String getClientIp(HttpServletRequest request) {
		
		// IP조회
		String ip = request.getHeader("X-Forwarded-For");
		logger.info("> X-FORWARDED-FOR : " + ip);
		
		// 서버 환경이나 프록시 등 중개서버가 다르기 때문에 각각 IP 셋팅
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