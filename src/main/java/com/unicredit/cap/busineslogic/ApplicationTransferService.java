package com.unicredit.cap.busineslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.CustomEmail;
import com.unicredit.cap.helper.EmailTemplateHelper;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.ApplicationTransfer;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.IMailService;
import com.unicredit.cap.service.MailService;

@Service
public class ApplicationTransferService {

	@Autowired
	private DbContext db;
	
	@Autowired 
	private PlacementTransferService tranService;
	
	@Autowired
	private Environment env;
	
	private MailService mailService = new MailService();
	
	Logger logger = Logger.getLogger(ApplicationTransferService.class);
	
//REQUESTED, APPROVED, REJECTED
	
	public List<ApplicationTransfer> getAllApplicationTransfersByApplication(long id)
	{
		Application app = db.Application().findOne(id);
		 if (app == null)
			 throw new CapNotFoundException("Application with id=" + id + " was not found");
		 
		return db.ApplicationTransfer().getAllTransfersOfApplication(id);
	}
	
	public List<ApplicationTransfer> getAllApplicationTransfersByFromOrgAndStatus(long from, String status)
	{		
		return db.ApplicationTransfer().getAllApplicationTransfersByFromOrgAndStatus(from, status);
	}
	
	public List<ApplicationTransfer> getAllApplicationTransfersByToOrgAndStatus(long to, String status)
	{		
		List<ApplicationTransfer> lst = db.ApplicationTransfer().getAllApplicationTransfersByToOrgAndStatus(to, status);
		List<ApplicationTransfer> ret = new ArrayList<ApplicationTransfer>();

		for (ApplicationTransfer at : lst)
		{
			if (at.getApplication().getCurrentOrg() == to )
				ret.add(at);

		}
		
		return ret;
	}
	

	
	public ApplicationTransfer createApplicationTransfer(ApplicationTransfer appTrans, Long id , int s)
	{
		Application app = db.Application().findOne(id);
		
		 if (app == null)
			 throw new CapNotFoundException("Application with id=" + id + " was not found");
		 
		 
		 appTrans.setApplication(app);
		 appTrans.setInitiationDate(new Date());
		 appTrans.setStatus("REQUESTED");
		 
		 db.ApplicationTransfer().save(appTrans);
		 
		 if (s == 1)
			return updateApplicationTransferStatus("APPROVED", appTrans.getId() ); 
		 
		 else {
		 
		 try {
			 
			User fromUser = db.User().findOne((long)appTrans.getFromUser());
			User toUser = appTrans.getToUser() == null ? null : db.User().findOne((long)appTrans.getToUser());
			Organization fromOrg = db.Orgnaization().findOne((long)appTrans.getFromOrg());
			Organization toOrg = db.Orgnaization().findOne((long)appTrans.getToOrg());
			
			HashMap<String, String> emailTemplateModel = new HashMap<>();
			emailTemplateModel.put("clientName", "Korisnik " + fromUser.getName() + " [" + fromOrg.getName() +"] je inicirao kretanje prema " + toOrg.getName());
			emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
			emailTemplateModel.put("status", "Status: REQUESTED");
			emailTemplateModel.put("description", "");
			emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/details/" + app.getId() );
			emailTemplateModel.put("headerText", "Prosledjivanje predmeta prema: " + ( toUser == null ? "" : toUser.getName() )  );
			emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
			emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
			
		    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
		    
		    List<String> toRecipients = new ArrayList<String>();
		    
		    List<User> emailUsers = db.User().findHrManagerOfOrg(fromOrg.getId());
		    
		    for (User u : emailUsers)
		    {
		    	toRecipients.add(u.getEmail());
		    }
		    
		    if (emailUsers.size() > 0)
		    mailService.SendMail("", toRecipients,"Predmet kretanje: "  + app.getDescription(), emailContent, "", env);
		 }
		 catch(Exception ex) {
			 logger.error(ex.getMessage());
				logger.error(ex.getStackTrace().toString());
		 }
		 
		    return appTrans;
		 }
	}

	public ApplicationTransfer updateApplicationTransferStatus(String status, Long id )
	{
		ApplicationTransfer appTran = db.ApplicationTransfer().findOne(id);
		
		 if (appTran == null)
			 throw new CapNotFoundException("Application transfer with id=" + id + " was not found");
		 
		 Application app = appTran.getApplication();
		 
		 appTran.setStatus(status);
		 appTran.setRespondDate(new Date());
		 
		 db.ApplicationTransfer().save(appTran);
		 
		if (status.equals("REJECTED"))
		{
			 try {
					User fromUser = db.User().findOne((long)appTran.getFromUser());
					User toUser = appTran.getToUser() == null ? null : db.User().findOne((long)appTran.getToUser());
					Organization fromOrg = db.Orgnaization().findOne((long)appTran.getFromOrg());
					Organization toOrg = db.Orgnaization().findOne((long)appTran.getToOrg());
					
					HashMap<String, String> emailTemplateModel = new HashMap<>();
					emailTemplateModel.put("clientName", "Prosledjivanje aplikacije je odbijeno. Sa " + fromUser.getName() + " [" + fromOrg.getName() +"], na " + ( toUser == null ? "" : toUser.getName() ) + " [" + toOrg.getName() +"] za " );
					emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
					emailTemplateModel.put("status", "Status: REJECTED");
					emailTemplateModel.put("description", "Poruka: " + appTran.getNote());
					emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/details/" + app.getId() );
					emailTemplateModel.put("headerText", "Odgovorna osoba za privatanje aplikacije je odbacila prezaduženje");
					emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
					emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
					
				    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
				    
				    List<String> toRecipients = new ArrayList<String>();
				    
				    toRecipients.add(fromUser.getEmail());
				    	
				    mailService.SendMail("", toRecipients,"Predmet kretanje: " + app.getDescription(), emailContent, "", env);
				 }
				 catch(Exception ex) {
					 logger.error(ex.getMessage());
						logger.error(ex.getStackTrace().toString());
				 }
		}
		
		if (status.equals("APPROVED"))
		{
			
			app.setCurrentOrg(appTran.getToOrg());
			app.setCurrentUser(appTran.getToUser());
			
			for (Placement placement : app.getPlacements())
			{
				PlacementTransfer pt = new PlacementTransfer();
				pt.setDateFrom(new Date());
				pt.setFromOrg(appTran.getFromOrg());
				pt.setFromUser(appTran.getFromUser());
				pt.setToOrg(appTran.getToOrg());
				pt.setToUser(appTran.getToUser());
				pt.setMovementType("Prezaduženje aplikacije");
							
				//dodat parametar da se ne salje mail
				PlacementTransfer rez = tranService.createNewPlacementTransfer(pt, placement.getId(), false);
								
			}
			
			db.Application().save(app);
			
			
			 try {
					User fromUser = db.User().findOne((long)appTran.getFromUser());
					User toUser = appTran.getToUser() == null ? null : db.User().findOne((long)appTran.getToUser());
					Organization fromOrg = db.Orgnaization().findOne((long)appTran.getFromOrg());
					Organization toOrg = db.Orgnaization().findOne((long)appTran.getToOrg());
					
					HashMap<String, String> emailTemplateModel = new HashMap<>();
					emailTemplateModel.put("clientName", "Proslijeđena je aplikacija od: " + fromUser.getName() + " [" + fromOrg.getName() +"] za " + ( toUser == null ? "" : toUser.getName() ) + " [" + toOrg.getName() +"]" );
					emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
					emailTemplateModel.put("status", "Status: APPROVED");
					emailTemplateModel.put("description", "Poruka: " + appTran.getNote());
					emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/details/" + app.getId() );
					emailTemplateModel.put("headerText", "Aplikacija i svi plasmani su prenešeni i prihvaćeni od strane nove Org. jedinice");
					emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
					emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
					
				    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
				    
				    List<String> toRecipients = new ArrayList<String>();
				    
				    toRecipients.add(fromUser.getEmail());
				    
				    if(appTran.getToUser() != null)
				    toRecipients.add(toUser.getEmail());
				    else
				    {
				    	if(toOrg.getEmail() != null && !toOrg.getEmail().equals("") )
				    		toRecipients.add(toOrg.getEmail());
				    }
				    	
				    mailService.SendMail("", toRecipients,"Predmet kretanje: " + app.getDescription(), emailContent, "", env);
				 }
				 catch(Exception ex) {	 
						logger.error(ex.getMessage());
						logger.error(ex.getStackTrace().toString());
				 }
				 
			
		}
		 
		return appTran;
	}


	public ApplicationTransfer addWatchersToApplication(List<CustomEmail> emails, long id) {
		
		ApplicationTransfer appTran = db.ApplicationTransfer().findOne(id);
		
		 if (appTran == null)
			 throw new CapNotFoundException("Application transfer with id=" + id + " was not found");
		 
		 Application app = appTran.getApplication();
		 
		 try {
				User fromUser = db.User().findOne((long)appTran.getFromUser());
				User toUser = appTran.getToUser() == null ? null : db.User().findOne((long)appTran.getToUser());
				Organization fromOrg = db.Orgnaization().findOne((long)appTran.getFromOrg());
				Organization toOrg = db.Orgnaization().findOne((long)appTran.getToOrg());
				
				HashMap<String, String> emailTemplateModel = new HashMap<>();
				emailTemplateModel.put("clientName", "Prosledjivanje aplikacije: Sa " + fromUser.getName() + " [" + fromOrg.getName() +"], na " + ( toUser == null ? "" : toUser.getName() ) + " [" + toOrg.getName() +"]" );
				emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
				emailTemplateModel.put("status", "Status: REJECTED");
				emailTemplateModel.put("description", "Poruka: " + appTran.getNote());
				emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/details/" + app.getId() );
				emailTemplateModel.put("headerText", "Korisnik " + fromUser.getName() + " Vas obavještava da je izvršeno kretanje aplikacije na " +toOrg.getName());
				emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
				emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
				
			    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
			    
			    List<String> toRecipients = new ArrayList<String>();
			    
			    for(CustomEmail em : emails  )
			    {
			    	toRecipients.add(em.getEmail());
			    }
			    
			    mailService.SendMail("", toRecipients,"Predmet kretanje:"  + app.getDescription(), emailContent, "", env);
			 }
			 catch(Exception ex) {
				 logger.error("Greška kod slanja maila - Kretanje Aplikacije - dodatni korisnici - id: " + id);
				 logger.error(ex.getMessage());
					logger.error(ex.getStackTrace().toString());
			 }
		
		 
		return appTran;
	}
	
	
}
