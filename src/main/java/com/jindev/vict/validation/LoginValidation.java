package com.jindev.vict.validation;

import com.jindev.vict.member.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm user = (LoginForm) target;

        if(!StringUtils.hasText(user.getEmail())){
            errors.rejectValue("email","required");
        }
    }
}
