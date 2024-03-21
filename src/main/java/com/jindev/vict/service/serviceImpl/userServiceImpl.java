package com.jindev.vict.service.serviceImpl;

import com.jindev.vict.dto.UserDto;
import com.jindev.vict.dto.UserDto_2;
import com.jindev.vict.entity.Authority;
import com.jindev.vict.entity.Users;
import com.jindev.vict.member.LoginForm;
import com.jindev.vict.member.MemberSaveDto;
import com.jindev.vict.repository.UserRepository;
import com.jindev.vict.service.UserService;
import com.jindev.vict.util.SHA256;
import com.jindev.vict.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class userServiceImpl implements UserService {
    private final UserDto_2 dao;
    private final SHA256 sha256;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int insertMember(@NotNull MemberSaveDto dto) throws NoSuchAlgorithmException {
        String salt = sha256.getSalt();
        String password = dto.getPwd();
        dto.setSalt(salt);
        dto.setPwd(sha256.Hashing(password.getBytes(),salt));

        return dao.insert(dto);
    }

    @Override
    public String selectTest() {
        return dao.testSelect();
    }

    @Override
    public int getEmailExist(String email) {
        int result = dao.getEmailExist(email);
        return result;
    }

    @Override
    public LoginForm getLoginInfo(Map loginInfo) {
        return dao.getLoginInfo(loginInfo);
    }

    @Override
    public int getPwdEqualsTo(Map loginInfo)  {
        //db에서 갖고온 pwd
        LoginForm login = dao.getLoginInfo(loginInfo);
        if(login != null){
            //사용자가 입력한 패스워드
            String inputPwd = (String) loginInfo.get("pwd");
            //사용자가 입력한 패스워드 + 해당 아이디 salt
            String encryptPwd = sha256.Hashing(inputPwd.getBytes(), login.getSalt());
            //사용자가 입력한 pwd 다른 경우
            if(!encryptPwd.equals(login.getPwd())){
                return 0;
            }
        }
        return 1;
    }
    @Override
    public String getUseYn(String email) {
        String useYn = dao.getUseYn(email);
        if(useYn==null){
            return "0";
        }else if(useYn.equals("N")){
            return "0";
        }
        return "1";
    }

    @Override
    public Users signup(UserDto userDto) {
        if(userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        //권한정보를 만듦
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER") //권한 한 개만 들어감
        .build();
        //유저 정보를 만듦
        Users user = Users.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Override
    public Optional<Users> getUserWithAuthorities(String username) {
        //username을 기준으로 정보를 가져옴
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Override
    public Optional<Users> getMyUserWithAuthorities() {
        //SecurityContext에 저장된 username을 가져옴
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
