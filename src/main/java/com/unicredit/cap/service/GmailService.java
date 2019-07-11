package com.unicredit.cap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;


public class GmailService implements IMailService {



	
	@Override
	public boolean SendMail(String from, List<String> to, String subject,String text, String link, Environment env) {

		final String username = "team4nucleus@gmail.com";
		final String password = "TeamNucleus123.";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("team4nucleus@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					 to.toArray(new InternetAddress[to.size()])
			//	InternetAddress.parse("gogom.markovic@gmail.com")
				);
			message.setSubject(subject);
			message.setText(text);		
			Transport.send(message);
					
		return true;

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
		
	}
}

	
