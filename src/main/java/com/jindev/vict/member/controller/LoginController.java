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

}