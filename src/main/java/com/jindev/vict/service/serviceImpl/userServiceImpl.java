package com.jindev.vict.service.serviceImpl;

import com.jindev.vict.dao.UserDao;
import com.jindev.vict.member.LoginForm;
import com.jindev.vict.member.MemberSaveDto;
import com.jindev.vict.service.UserService;
import com.jindev.vict.util.SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    UserDao dao;
    SHA256 sha256 = new SHA256();
    @Override
    public int insertMember(MemberSaveDto dto) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        String password = dto.getPwd();
        dto.setPwd(sha256.encrypt(password));

        return dao.insert(dto);
    }

    @Override
    public String selectTest() {
        return dao.testSelect();
    }

    @Override
    public int getEmailExist(String email) {
        int result = dao.getEmailExist(email);
//        String message = "사용 가능한 이메일입니다.";
//        // result 0 이상이면 중복된 아이디가 있는 것.
//        if(result > 0){
//            message = "이미 가입된 이메일입니다.";
//            return message;
//        }
        return result;
    }

    @Override
    public LoginForm getLoginInfo(Map loginInfo) {
        return dao.getLoginInfo(loginInfo);
    }

    @Override
    public int getPwdEqualsTo(Map login) throws NoSuchAlgorithmException {
        //db에서 갖고온 pwd
        LoginForm loginInfo = dao.getLoginInfo(login);
        //사용자가 입력한 패스워드랑 db 패스워드가 다를 때 : 1 성공 :200
        //사용자가 입력한 패스워드
        String encryptPwd = sha256.encrypt((String) login.get("email"));
        //db에서 가져온 pwd
        String comparePwd = sha256.encrypt(loginInfo.getPwd());
        //사용자가 입력한 pwd 다른 경우
        if(!encryptPwd.equals(comparePwd)){
            return 1;
        }
        return 200;
    }
    @Override
    public Boolean getUserUseYn(Map loginInfo) {
        LoginForm login = dao.getLoginInfo(loginInfo);
        if(login==null){
            return false;
        }else if(login.getUseYn().equals("N")){
            return false;
        }
        return true;
    }
}
