package com.jindev.vict.service;

import com.jindev.vict.dto.UserDto;
import com.jindev.vict.entity.Users;
import com.jindev.vict.member.LoginForm;
import com.jindev.vict.member.MemberDto;
import com.jindev.vict.member.MemberSaveDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface UserService {
    int insertMember(MemberSaveDto dto) throws NoSuchAlgorithmException;

    String selectTest();

    int getEmailExist(String email);

    LoginForm getLoginInfo(Map loginInfo);
    //사용자 입력 패스워드랑 db 패스워드 비교
    int getPwdEqualsTo(Map inputPwd) throws NoSuchAlgorithmException;

    String getUseYn(String email);

    @Transactional
    Users signup(UserDto userDto);

    @Transactional(readOnly = true)
    Optional<Users> getUserWithAuthorities(String username);

    @Transactional(readOnly = true)
    Optional<Users> getMyUserWithAuthorities();

}
