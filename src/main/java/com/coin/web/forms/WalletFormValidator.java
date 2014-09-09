package com.coin.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
 
public class WalletFormValidator implements Validator {

 
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(OrderForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
     
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "wallet.name.required", "wallet.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeId", "wallet.type.required", "wallet.type.required");
 
    }
    
    
    
 
}
