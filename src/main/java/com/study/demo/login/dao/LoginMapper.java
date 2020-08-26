package com.study.demo.login.dao;

import org.apache.ibatis.annotations.Mapper;

import com.study.demo.vo.LoginLog;
import com.study.demo.vo.Member;

@Mapper
public interface LoginMapper{
 
    //�޼ҵ� �տ� public �����ص� �⺻������ public�� �ٴ´�.
    
    /* ȸ��������ȸ */
    Member getSelectMeberInfo(String id);
    /* ȸ������ */
    public int setInsertMember(Member member);
    /* �α��� �α� */
    public int setInsertLoginLog(LoginLog loginLog);
    /* ��й�ȣ Ʋ�� Ƚ�� �� */
    public int setUpdatePasswordLockCnt(String id);
    /* ��й�ȣ Ʋ��Ƚ�� �ʱ�ȭ */
    public int setUpdatePasswordLockCntReset(String id);
     
}