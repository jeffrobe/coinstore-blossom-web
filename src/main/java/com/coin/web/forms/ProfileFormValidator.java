package com.coin.web.forms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

 


import com.coin.web.common.Util;

import edu.vt.middleware.password.AbstractSequenceRule;
import edu.vt.middleware.password.AlphabeticalSequenceRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.NumericalSequenceRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.WhitespaceRule;
 
/**
 * Validator for validating profile form.
 */
public class ProfileFormValidator implements Validator {

 
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(ProfileForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ProfileForm profileForm = (ProfileForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "required", "First Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "required", "Last Name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required", "phone is required");

    	if (profileForm.getPhone() != null && !profileForm.getPhone().isEmpty()) 
        	if (!Util.isPhoneNumberValid(profileForm.getPhone())) errors.rejectValue("phone", "registration.phone.invalid", "registration.phone.invalid");

				
    }
    
    
    
 
}
