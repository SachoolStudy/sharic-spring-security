package com.study.demo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.study.demo.security.handlers.AuthFailureHandler;
import com.study.demo.security.handlers.AuthProvider;
import com.study.demo.security.handlers.AuthSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {  
    
    @Autowired 
    AuthProvider authProvider;
 
    @Autowired 
    AuthSuccessHandler authSuccessHandler;
    
    @Autowired 
    AuthFailureHandler authFailureHandler;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        //CROF ������ �����մϴ�
        //�ʱ� ���߽ÿ��� �����մϴ�
        http.csrf().disable();
        
        http.authorizeRequests() 
            .antMatchers("/user/**").access("hasRole('ROLE_USER')")    // /user/** ����� ��� ROLE_USER�� ������ ���� ��쿡 ����Ѵ�
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")     // /admin/** ����� ��� ROLE_ADMIN�� ������ ���� ��쿡 ����Ѵ�
            
            // //������ ������ �� �ִ� �������̱� ������ ������ ������ �����ϴ� (.permitAll())
            .antMatchers("/home"
                    ,"/login/login"
                    , "/login/login-error"
                    , "/join/join"
                    , "/join/idCheck"
                    , "/login/find"
                    , "/join/insert"
            ).permitAll()
            .antMatchers("/**").authenticated();  //��Ÿ /** �� ��δ� ������ �ʿ�� �Ѵ�
        
        http.formLogin() 
            .loginPage("/login/login")  //�α��� �������� /, /login (���� ������)�� ������ ���� �α����� ������ ���̴�
            .loginProcessingUrl("/login/login-processing") //�α��� ��ư�� ������ /login-processing ��η� 
            .usernameParameter("id")  //input name �Ķ���ͷ� "id"�� �޴´�.
            .passwordParameter("password") //input name �Ķ���ͷ� "password"�� �޴´�.
            .failureHandler(authFailureHandler) //�α��� ���н� �����ϴ� Ŭ����
            .successHandler(authSuccessHandler); // �α��� ������ �����ϴ� Ŭ����
        
        http.logout() 
            .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout")) //logout ��η� ��û�� ���� ��� �� ��η� �����̷�Ʈ �ϰ� ���� �ʱ�ȭ
            .logoutSuccessUrl("/login/login")  // �� ��η� �����̷�Ʈ �ϰ�
            .invalidateHttpSession(true); // ���� �ʱ�ȭ
        
    }
    
    //JSP�� ���ҽ� �����̳� �ڹٽ�ũ��Ʈ ������ ����� ��δ� ���ø� �Ѵ�
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        .antMatchers("/api/**", "/resources/**");
    }
    
}