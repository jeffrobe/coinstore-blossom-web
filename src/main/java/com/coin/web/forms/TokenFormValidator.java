package com.coin.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class TokenFormValidator implements Validator {
 
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(TokenForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "token", "required", "token is required");	
    }
    
    
    
 
}
