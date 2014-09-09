package com.coin.web.forms;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.coin.web.common.Util;

import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;

public class LoginFormValidator implements Validator {
	private List<Rule> ruleList = Util.getPasswordRules();
	
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(ProfileForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ProfileForm profileForm = (ProfileForm) target;
    	
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required", "password.required");
        
    	if (profileForm.getEmail() != null) 
        	if (!Util.validateEmail(profileForm.getEmail())) errors.rejectValue("email", "email.format", "Incorrect email format");

		if (profileForm.getPassword() != null && !profileForm.getPassword().isEmpty()) {
			PasswordValidator validator = new PasswordValidator(ruleList);
			PasswordData passwordData = new PasswordData(new Password(profileForm.getPassword()));
			 
			RuleResult result = validator.validate(passwordData);
			if (!result.isValid()) 
				for (String msg : validator.getMessages(result)) errors.rejectValue  ("password", "password.format", msg);    
		}
    }
}
