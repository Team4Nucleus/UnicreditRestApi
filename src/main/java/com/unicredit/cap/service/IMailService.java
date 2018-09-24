package com.unicredit.cap.service;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


public interface IMailService {

	public boolean SendMail(String from, List<String> to,String subject, String text, String link, Environment env);
	
}
