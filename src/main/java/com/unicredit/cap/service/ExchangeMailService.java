package com.unicredit.cap.service;

import java.net.URI;
import java.util.List;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;


public class ExchangeMailService implements MailService {

	@Override
	public boolean SendMail(String from, List<String> to, String text, String link){
		
		 Boolean flag = false;
	        try {
	            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1); // your server version
	            ExchangeCredentials credentials = new WebCredentials("sapwebservices@poreskaupravars.org", "sap!2016","poreskaupravars.org");   // change them to your email username, password, email domain
	            service.setCredentials(credentials);
	         
	            service.setUrl(new URI("https://exchange1.purs.int/EWS/Exchange.asmx")); //https://webmail.poreskaupravars.org
	            EmailMessage msg = new EmailMessage(service);
	            msg.setSubject("This is a test!!!"); //email subject
	            msg.setBody(MessageBody.getMessageBodyFromText("This is a test!!! pls ignore it!")); //email body
	            msg.getToRecipients().add("mladen.todorovic@poreskaupravars.org"); //email receiver
//	        msg.getCcRecipients().add("test2@test.com"); // email cc recipients
//	        msg.getAttachments().addFileAttachment("D:\\Downloads\\EWSJavaAPI_1.2\\EWSJavaAPI_1.2\\Getting started with EWS Java API.RTF"); // email attachment
	            msg.send(); //send email
	            flag = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return flag;
	}
	
	
}

