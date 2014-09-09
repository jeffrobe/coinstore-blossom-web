package com.coin.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
 
public class BtcaddressFormValidator implements Validator {

 
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(BtcaddressForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
     
       // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publickey", "btcaddress.publickey.required", "wallet.name.required");
      
    }
    
    
    
 
}
