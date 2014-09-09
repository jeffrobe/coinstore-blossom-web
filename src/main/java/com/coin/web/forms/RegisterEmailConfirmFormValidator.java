package com.coin.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterEmailConfirmFormValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(ProfileForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tos", "register.tos.confirm", "register.tos.confirm");
    }
}
