package com.jindev.vict.member.controller;

import com.jindev.vict.member.LoginForm;
import com.jindev.vict.member.MemberDto;
import com.jindev.vict.member.MemberSaveDto;
import com.jindev.vict.service.UserService;
import com.jindev.vict.util.SHA256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService service;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("form") LoginForm login){

        return "member/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("form")  LoginForm loginForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model mv) throws NoSuchAlgorithmException {

        if(bindingResult.hasErrors()){
            log.info("Error={}", bindingResult);
            return "member/login";
        }
        //소셜키, 로그인타입, useYn=Y 인 경우 로그인 check
        MemberSaveDto memberSaveDto = new MemberSaveDto();
        memberSaveDto.setEmail(loginForm.getEmail());
        memberSaveDto.setPwd(loginForm.getPwd());

        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("email", loginForm.getEmail());
        loginMap.put("pwd", loginForm.getPwd());
        //사용자가 입력한 패스워드/db에 입력된 패스워드가 같은지
        int pwdEqualsTo = service.getPwdEqualsTo(loginMap);
        Boolean userUseYn = service.getUserUseYn(loginMap);
        int userExist = service.getEmailExist(loginMap.get("email"));

        if(userExist == 0){ //회원가입 x
            redirectAttributes.addFlashAttribute("message", "가입하지 않은 회원입니다.");
            return "redirect:/login";
        }
        if(userExist ==1 && pwdEqualsTo == 0){  //이메일은 존재하면서 패스워드가 다른 경우
            redirectAttributes.addFlashAttribute("message", "이메일/비밀번호를 다시 확인해주세요.");
            return "redirect:/login";
        }
        else if(!userUseYn){ //탈퇴한 회원 및 정지된 회원
            redirectAttributes.addFlashAttribute("message", "로그인에 실패했습니다.");
            return "redirect:/login";
        }
        return "member/dashBoard";
    }
}