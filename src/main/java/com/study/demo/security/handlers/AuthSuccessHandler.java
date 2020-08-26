package com.study.demo.security.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.study.demo.security.service.SecurityService;
import com.study.demo.util.CommonUtil;
import com.study.demo.vo.LoginLog;

/*
 * ������ ��ť��Ƽ�� ����ϸ�, �α��� ������ �ΰ� �۾��� �Ϸ���, 
 * org.springframework.security.web.authentication.AuthenticationSuccessHandler�� �����ؾ� �Ѵ�.
 *������ authenticationSuccessHandler�� �������� ������ �⺻������ 
 * org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler�� ����ϰ� �ȴ�.
 */
 
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
    
    @Autowired
    SecurityService securityService;
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException{
      
        String ip = CommonUtil.getClientIp(request);
        logger.info("::::::::::::::::::::::::::::: �α��� ���� ::::::::::::::::::::::::::::: ");
        LoginLog loginLog = new LoginLog();
        String id = "";
        try {
            
            id = authentication.getName().toString();                        
            securityService.setUpdatePasswordLockCntReset(id);            
            loginLog.setLoginIp(ip);
            loginLog.setId(id);
            loginLog.setStatus("SUCCESS");
//            securityService.setInsertLoginLog(loginLog);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        super.setDefaultTargetUrl("/home");
        super.onAuthenticationSuccess(request, response, authentication);
    }
 
}