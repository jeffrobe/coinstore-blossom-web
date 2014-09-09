package com.coin.web.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import si.mazi.rescu.RestProxyFactory;

import com.coin.service.PropertiesService;
import com.coin.web.model.CustomUserDetail;
import com.coin.model.LongSelOption;
import com.coin.model.User;
 
import com.coin.common.BaseConfig;

import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.WhitespaceRule;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

 

public class Config extends BaseConfig {
	public final static String[] propertiesFile = { 
		"/properties/externalsites.properties" ,
		"/properties/proxy.properties",
		"/properties/webapplication.properties"
		};
	
	public final static String memberHome="/coinbl/member/";
	public final static String memberRedirect = "redirect:/coinbl/member/index.html";
	public final static String tokenPage="token.html";
	
	public final static String memberDirectory="member";
	public final static String webHome="coinbl";
	public final static String adminHome="coinadmin";

	public static final String permsUserBasic = "user";
	

	public static final String emailOrderConfirmTemplate = "order_confirm";
	public static final String emailRegistrationConfirmTemplate = "registration_confirmation";
	public static final String emailForgotPasswordTemplate = "forgot_password";
    //public static final String emailRegistrationConfirmBody = "Email Confirmation notice. click this link to complete registration\n\n {url}";
    public static final String emailRegistrationUrl = "/register/register_email_confirm.html";
    public static final String emailForogotpasswordUrl = "/forgotpassword/resetpassword.html";
	
	public static final int MINIMUM_PASSWORD_LENGTH = 6;

	public static final boolean SelectOptionDefaultEntry = true;
	public static final String SelectOptionDefaultLabel = "-Select-";
	public static final String SelectOptionDefaultValue = "";
	
}