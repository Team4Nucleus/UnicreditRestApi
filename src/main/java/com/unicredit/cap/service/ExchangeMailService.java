package com.unicredit.cap.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

@Service
public class ExchangeMailService implements MailService {

	@Autowired
	private Environment env;
	
	@Override
	public boolean SendMail(String from, List<String> to, String subject,String text, String link){
		
		 Boolean flag = false;
	        try {
	            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1); // your server version
	           // ExchangeCredentials credentials = new WebCredentials("sapwebservices", "sap!2016");   // change them to your email username, password, email domain
	            ExchangeCredentials credentials = new WebCredentials( env.getProperty("exchange.username").toString(), env.getProperty("exchange.password").toString());
	           
	            service.setCredentials(credentials);
	         
	           // service.setUrl(new URI("https://exchange1.poreskaupravars.org/EWS/Exchange.asmx")); //https://webmail.poreskaupravars.org
	            service.setUrl(new URI(env.getProperty("exchange.webservice").toString()));
	            EmailMessage msg = new EmailMessage(service);
	            msg.setSubject(subject); //email subject
	            msg.setBody(MessageBody.getMessageBodyFromText(text)); //email body
	            for(String recipient: to) {
	            	msg.getToRecipients().add(recipient); //email receiver
	            }
	            
	            msg.send(); //send email
	            flag = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return flag;
	}
	
	
}

