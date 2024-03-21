package com.jindev.vict.dto;

import com.jindev.vict.member.LoginForm;
import com.jindev.vict.member.MemberSaveDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDto_2 {

    int insert(MemberSaveDto dto);

    String testSelect();

    int getEmailExist(String email);

    LoginForm getLoginInfo(Map loginInfo);

    String getUseYn(String email);
}
