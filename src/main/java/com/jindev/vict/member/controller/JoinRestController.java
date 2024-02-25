package com.jindev.vict.member.controller;

import com.jindev.vict.member.MemberSaveDto;
import com.jindev.vict.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JoinRestController {

    private final UserService service;

    @GetMapping("/emailDuplication/{email}")
    public int emailDuplication(@PathVariable("email") String email){
        int result = service.getEmailExist(email);
        return result;

    }
}
