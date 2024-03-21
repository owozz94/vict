package com.jindev.vict.member.controller;

import com.jindev.vict.member.MemberDto;
import com.jindev.vict.member.MemberSaveDto;
import com.jindev.vict.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class JoinController {

    private final UserService service;

    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute(
                "item", new MemberDto());
        return "member/joinForm";
    }
    //폼방식으로 전송
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("form") MemberSaveDto dto) throws NoSuchAlgorithmException {

        service.insertMember(dto);
        return "redirect:/login";
    }

    @GetMapping("/test")
    public String test(){
        String str = service.selectTest();
        System.out.println(str);
        return "/";
    }
}
