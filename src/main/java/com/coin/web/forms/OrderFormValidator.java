package com.coin.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
 
public class OrderFormValidator implements Validator {

 
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(OrderForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
     
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fundingsourceId", "order.fundingsource.required", "order.fundingsource.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "walletId", "order.wallet.required", "order.wallet.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "order.amount.required", "order.amount.required");
  
    }
    
    
    
 
}
