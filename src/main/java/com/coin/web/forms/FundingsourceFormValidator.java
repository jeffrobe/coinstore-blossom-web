package com.coin.web.forms;

import org.apache.commons.validator.routines.checkdigit.ABANumberCheckDigit;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
 
public class FundingsourceFormValidator implements Validator {

 
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(FundingsourceForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	FundingsourceForm fundingsourceForm = (FundingsourceForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "bank.name.required", "bank.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account", "bank.account.required", "bank.account.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "routing", "bank.routing.required", "bank.routing.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "bank.type.required", "bank.type.required");
 
        
        if (fundingsourceForm.getRouting()!=null && !fundingsourceForm.getRouting().isEmpty()){
            ABANumberCheckDigit ab = new ABANumberCheckDigit();
            if (!ab.isValid(fundingsourceForm.getRouting()))
            	errors.rejectValue("routing",  "bank.routing.invalidformat", "bank.routing.invalidformat");
        }
        
    }
    
    
    
 
}
