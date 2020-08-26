package com.study.demo.security.handlers;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.study.demo.vo.Member;

import lombok.Data;

//���� �α����� ����� ��ü ���� DTO
@Data
public class MyAuthentication extends UsernamePasswordAuthenticationToken{
  private static final long serialVersionUID = 1L;
  
  Member member;
  
  public MyAuthentication(String id, String password, List<GrantedAuthority> grantedAuthorityList, Member member) {
      super(id, password, grantedAuthorityList);
      this.member = member;
  }
}