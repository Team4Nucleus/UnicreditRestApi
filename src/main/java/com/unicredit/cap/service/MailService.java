package com.unicredit.cap.service;

import java.util.List;

public interface MailService {

	public boolean SendMail(String from, List<String> to, String text, String link);
	
}
