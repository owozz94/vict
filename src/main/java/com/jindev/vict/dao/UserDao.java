package com.jindev.vict.dao;

import com.jindev.vict.member.LoginForm;
import com.jindev.vict.member.MemberDto;
import com.jindev.vict.member.MemberSaveDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    int insert(MemberSaveDto dto);

    String testSelect();

    int getEmailExist(String email);

    LoginForm getLoginInfo(Map loginInfo);
}
