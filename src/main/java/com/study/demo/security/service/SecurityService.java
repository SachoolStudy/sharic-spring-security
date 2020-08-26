package com.study.demo.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.study.demo.vo.Member;

public interface SecurityService extends UserDetailsService {
    // ��ť��Ƽ ����� ����
    UserDetails loadUserByUsername(String id);
    // �ߺ����̵� üũ
    Member getSelectMeberInfo(String id) throws Exception;
    //ȸ������
    int setInsertMember(Member member)throws Exception;
    // ��й�ȣ Ʋ�� Ƚ�� ����
    int setUpdatePasswordLockCnt(String id) throws Exception;
    // ��й�ȣ Ʋ�� Ƚ�� �ʱ�ȭ
    int setUpdatePasswordLockCntReset(String id) throws Exception;
    
}