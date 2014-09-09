package com.coin.web.service.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.coin.model.Email;
import com.coin.web.common.Util;
import com.coin.web.service.EmailService;
import com.coin.web.service.PropertiesService;

@Service ("emailService")
public class EmailServiceImpl implements EmailService {
	private static final Logger log = Logger.getLogger(EmailServiceImpl.class);
	   
	@Autowired
	protected PropertiesService propertiesService;
	
	
	private VelocityEngine velocityEngine;
	public void setVelocityEngine (VelocityEngine v) { this.velocityEngine=v; }
    private JavaMailSender javaMailSender;
	public void setJavaMailSender (JavaMailSender v) { this.javaMailSender=v; }
    
	EmailServiceImpl( ) {
 
	}
	
	public void send (Email email, String to, boolean sendHtml) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", propertiesService.getSmtpHost() );

		Session session = Session.getDefaultInstance(properties);
		to = Util.getEmailTo(to, propertiesService);
		 
		try{
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(propertiesService.getEmailFrom()));

			message.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));

			message.setSubject(email.getSubject());

			if (sendHtml && email.getBodyHtml() != null) message.setText(email.getBodyHtml());
			else  message.setText(email.getBodyText());
			
			if(propertiesService.getSendEmail()) javaMailSender.send(message);
			if(propertiesService.getSendEmailToFile())
			try {
				message.writeTo(new FileOutputStream(new File(propertiesService.getEmailOutDir()+"/mail.eml")));
			} catch (Exception e) {
				log.error("log email error "+e.getMessage());
			}
			log.info("Sent message successfully.... to: "+to+ " subject: "+email.getSubject());
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	
	public Email getEmailFromTemplate (String template, Map<String,Object> model) {
		if (this.velocityEngine==null) {
			log.error("velocity engine is not initialized");
			return null;
		}
	
        String bodyText =  parseTemplate( "text", template, model, "");
        String bodyHtml =  parseTemplate( "html", template, model, "");
        String subject =  parseTemplate( "subject", template, model, "");

        Email email = new Email(subject, bodyText, bodyHtml);
        
        return email;
	}
	
	private String parseTemplate(String base, String template,  Map<String,Object> model, String def) {
		String str = def;
		
		try {
			str = VelocityEngineUtils.mergeTemplateIntoString(  velocityEngine, base+"/"+template+".vm", model);
		} catch (Exception e) { log.error("parse template "+e.getMessage() );}
		return str;
	}
	
}