package com.jindev.vict.service;

import com.jindev.vict.member.LoginForm;
import com.jindev.vict.member.MemberDto;
import com.jindev.vict.member.MemberSaveDto;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    int insertMember(MemberSaveDto dto) throws NoSuchAlgorithmException;

    String selectTest();

    int getEmailExist(String email);

    LoginForm getLoginInfo(Map loginInfo);
    //사용자 입력 패스워드랑 db 패스워드 비교
    int getPwdEqualsTo(Map inputPwd) throws NoSuchAlgorithmException;

    Boolean getUserUseYn(Map loginInfo);
}
