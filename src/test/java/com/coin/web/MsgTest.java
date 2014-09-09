package com.coin.web;
 
import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.coin.web.AbstractServiceTest;

@Service
public class MsgTest extends AbstractServiceTest {
	private static final Logger log = Logger.getLogger(MsgTest.class);

	@Autowired
	private MessageSource messageSource;

	String key = "account.name.inuse";
 
	
	@Test
    public void demo2(){
		 
	    String str =  messageSource.getMessage(key, null, key, LocaleContextHolder.getLocale());
        log.debug("str:" + str );
    }    

    
}