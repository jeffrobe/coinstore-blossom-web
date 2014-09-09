package com.coin.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.coin.web.common.Util;

public class ForgotpasswordFormValidator implements Validator {
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(ProfileForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ProfileForm profileForm = (ProfileForm) target;
    	    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "E-mail is required");
        if (profileForm.getEmail() != null) 
        	if (!Util.validateEmail(profileForm.getEmail())) errors.rejectValue("email", "email.format", "Incorrect email format");

     }
}
