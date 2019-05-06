package com.unicredit.cap.busineslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.EmailTemplateHelper;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.DocumentReceive;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.MailService;

@Service
public class DocumentReceiveService {

	
	@Autowired
	private DbContext db;
	
	@Autowired
	private Environment env;
	
	
	private MailService mailService = new MailService();
	
	
	public 	List<DocumentReceive> getDocumentReceiveByDocumentId(Long docId) {
		
		return db.documentReceive().getByDocumentId(docId);
	}
	
	
	public DocumentReceive createDocumentReceive(DocumentReceive doc)
	{
		
		doc.setActive(1);
		doc.setReceiveDate(new Date());
		db.documentReceive().save(doc);
			
		try {
				
		User fromUser = db.User().findOne((long)doc.getFromUser());
		User toUser = db.User().findOne((long)doc.getToUser());
		Document document = db.Document().findOne(doc.getDocument());
		
		document.setOriginal(1);
		db.Document().save(document);
		
		HashMap<String, String> emailTemplateModel = new HashMap<>();
		emailTemplateModel.put("clientName", "Prijem originala dokumenta od : " + fromUser.getName() + " za " + toUser.getName() +"");
		emailTemplateModel.put("placementType", "Dokument: " + document.getName());
		emailTemplateModel.put("status", "");
		emailTemplateModel.put("description", "Zapriljen je original dokumenta ");
		emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/documents/" + document.getId() );
		emailTemplateModel.put("headerText", "Prijem originala dokumenta" );
		emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
		emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
		
	    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
	    
	    List<String> toRecipients = new ArrayList<String>();
	    
	   
	    toRecipients.add(toUser.getEmail());
	    toRecipients.add(fromUser.getEmail());

	    mailService.SendMail("", toRecipients,"Predmet kretanje", emailContent, "", env);
		
		}
		catch (Exception e) {
			 throw new CapNotFoundException("Error: " + e.getMessage());
		}
		
				
		return doc;
	}
	
	
	public DocumentReceive cancelDocumentReceive(Long docId)
	{
		
		DocumentReceive doc = db.documentReceive().findOne(docId);
		doc.setActive(0);
		
		db.documentReceive().save(doc);
			
		try {
			
			User fromUser = db.User().findOne((long)doc.getFromUser());
			User toUser = db.User().findOne((long)doc.getToUser());
			Document document = db.Document().findOne(doc.getDocument());
			
			document.setOriginal(0);
			db.Document().save(document);
			
			HashMap<String, String> emailTemplateModel = new HashMap<>();
			emailTemplateModel.put("clientName", "Poništen prijem originala dokumenta od : " + fromUser.getName() + " za " + toUser.getName() +"");
			emailTemplateModel.put("placementType", "Dokument: " + document.getName());
			emailTemplateModel.put("status", "");
			emailTemplateModel.put("description", "Poništen je prijem originala dokumenta ");
			emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/documents/" + document.getId() );
			emailTemplateModel.put("headerText", "Poništenje prijema originala dokumenta" );
			emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
			emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
			
		    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
		    
		    List<String> toRecipients = new ArrayList<String>();
		    		   
		    toRecipients.add(toUser.getEmail());
		    toRecipients.add(fromUser.getEmail());

		    mailService.SendMail("", toRecipients,"Predmet kretanje", emailContent, "", env);
			
			}
			catch (Exception e) {
				 throw new CapNotFoundException("Error: " + e.getMessage());
			}
					
		
		return doc;
	}
	
}
