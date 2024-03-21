package com.jindev.vict.service.serviceImpl;

import com.jindev.vict.service.SnsService;
import com.jindev.vict.util.SnsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SnsServiceImpl implements SnsService {
    @Autowired
    SnsUtils utils;

    @Override
    public String naverLogin() {
        String secret = utils.getSecret();
        String clientId = utils.getClientId();
        String api = utils.getApi();
        String auth = utils.getAuth();

        return null;
    }
}
