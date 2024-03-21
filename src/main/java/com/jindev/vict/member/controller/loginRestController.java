package com.jindev.vict.member.controller;

import com.jindev.vict.member.LoginForm;
import com.jindev.vict.service.SnsService;
import com.jindev.vict.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class loginRestController {

    private final UserService userService;
    private final SnsService snsService;

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("form") LoginForm loginForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model mv) throws NoSuchAlgorithmException {

        if(bindingResult.hasErrors()){
            log.info("Error={}", bindingResult);
            return "member/login";
        }
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("email", loginForm.getEmail());
        loginMap.put("pwd", loginForm.getPwd());
        loginMap.put("useYn", loginForm.getUseYn());

        //사용자가 입력한 패스워드/db에 입력된 패스워드가 같은지
        int pwdEqualsTo = userService.getPwdEqualsTo(loginMap);
        String useYn = userService.getUseYn(loginMap.get("email"));
        int userExist = userService.getEmailExist(loginMap.get("email"));

        if(userExist == 0){ //회원가입 x
            redirectAttributes.addFlashAttribute("message", "가입하지 않은 회원입니다.");
            return "redirect:/login";
        }
        if(userExist == 1 && pwdEqualsTo == 0){  //이메일은 존재하면서 패스워드가 다른 경우
            redirectAttributes.addFlashAttribute("message", "이메일/비밀번호를 다시 확인해주세요.");
            return "redirect:/login";
        }
        else if(useYn.equals("0")){ //탈퇴한 회원 및 정지된 회원
            redirectAttributes.addFlashAttribute("message", "로그인에 실패했습니다.");
            return "redirect:/login";
        }
        return "member/dashBoard";
    }

    @PostMapping("/naverLogin")
    public String naverLogin(){
        String str = snsService.naverLogin();

        return str;
    }
}
