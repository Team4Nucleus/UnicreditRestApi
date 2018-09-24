package com.unicredit.cap.service;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {

	private IMailService mail;

	public MailService(){
		mail = new ExchangeMailService();
	}
	
	@Override
	public boolean SendMail(String from, List<String> to, String subject, String text, String link, Environment env) {
		// TODO Auto-generated method stub
		return mail.SendMail(from, to, subject, text, link, env);
	}
	
	
			
}
