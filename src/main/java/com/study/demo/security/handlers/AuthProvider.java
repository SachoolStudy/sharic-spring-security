package com.study.demo.security.handlers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.study.demo.security.service.SecurityService;
import com.study.demo.vo.Member;

@Component
public class AuthProvider implements AuthenticationProvider{
    
    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
    
    @Autowired
    SecurityService securityService;
 
    //�α��� ��ư�� ���� ���
 
    //ù��° ����
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(id, password);
    }
    
    //�ι��� ����
    private Authentication authenticate(String id, String password) throws AuthenticationException{
        
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        
        Member member = new Member();
            
        member = (Member)securityService.loadUserByUsername(id);
    
        if ( member == null ){
            logger.info("����� ������ �����ϴ�.");
            throw new UsernameNotFoundException(id);
        }else if(member != null && !member.getPassword().equals(password) ) {
            logger.info("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
            throw new BadCredentialsException(id);
        }
    
        grantedAuthorityList.add(new SimpleGrantedAuthority(member.getUserRole()));
        
        return new MyAuthentication(id, password, grantedAuthorityList, member);
 
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
 
}