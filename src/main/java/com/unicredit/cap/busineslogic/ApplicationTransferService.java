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
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.ApplicationTransfer;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.IMailService;

@Service
public class ApplicationTransferService {

	@Autowired
	private DbContext db;
	
	@Autowired 
	private PlacementTransferService tranService;
	
	@Autowired
	private Environment env;
	
	private IMailService mailService = new ExchangeMailService();
	
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
		return db.ApplicationTransfer().getAllApplicationTransfersByToOrgAndStatus(to, status);
	}
	
	
	public ApplicationTransfer createApplicationTransfer(ApplicationTransfer appTrans, Long id )
	{
		Application app = db.Application().findOne(id);
		
		 if (app == null)
			 throw new CapNotFoundException("Application with id=" + id + " was not found");
		 
		 
		 appTrans.setApplication(app);
		 appTrans.setInitiationDate(new Date());
		 appTrans.setStatus("REQUESTED");
		 
		 db.ApplicationTransfer().save(appTrans);
		 
		 try {
			User fromUser = db.User().findOne((long)appTrans.getFromUser());
			User toUser = db.User().findOne((long)appTrans.getToUser());
			Organization fromOrg = db.Orgnaization().findOne((long)appTrans.getFromOrg());
			Organization toOrg = db.Orgnaization().findOne((long)appTrans.getToOrg());
			
			HashMap<String, String> emailTemplateModel = new HashMap<>();
			emailTemplateModel.put("clientName", "Proslijeđena Vam je aplikacija od: " + fromUser.getName() + " [" + fromOrg.getName() +"]");
			emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
			emailTemplateModel.put("status", "Status: REQUESTED");
			emailTemplateModel.put("description", "");
			emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/" + appTrans.getId() );
			emailTemplateModel.put("headerText", "Zadatak od: " + fromUser.getName() );
			emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
			emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
			
		    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
		    
		    List<String> toRecipients = new ArrayList<String>();
		    
		    if(appTrans.getToUser() != null)
		    toRecipients.add(toUser.getEmail());
		    else
		    {
		    	if(toOrg.getEmail() != null && !toOrg.getEmail().equals("") )
		    		toRecipients.add(toOrg.getEmail());
		    }
		    	
		    mailService.SendMail("", toRecipients,"Predmet kretanje", emailContent, "", env);
		 }
		 catch(Exception ex) {}
		 
		    return appTrans;
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
					User toUser = db.User().findOne((long)appTran.getToUser());
					Organization fromOrg = db.Orgnaization().findOne((long)appTran.getFromOrg());
					Organization toOrg = db.Orgnaization().findOne((long)appTran.getToOrg());
					
					HashMap<String, String> emailTemplateModel = new HashMap<>();
					emailTemplateModel.put("clientName", "Prosledjivanje aplikacije je odbijeno. Sa " + fromUser.getName() + " [" + fromOrg.getName() +"], na " + toUser.getName() + " [" + toOrg.getName() +"] za " );
					emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
					emailTemplateModel.put("status", "Status: REJECTED");
					emailTemplateModel.put("description", "");
					emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/" + appTran.getId() );
					emailTemplateModel.put("headerText", "Odgovorna osoba za privatanje aplikacije je odbacila prezaduženje");
					emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
					emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
					
				    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
				    
				    List<String> toRecipients = new ArrayList<String>();
				    
				    toRecipients.add(fromUser.getEmail());
				    	
				    mailService.SendMail("", toRecipients,"Predmet kretanje", emailContent, "", env);
				 }
				 catch(Exception ex) {}
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
				pt.setMovementType("Prezaduženje Plasmana usled prezaduženja aplikacije / predmeta");
							
				//dodat parametar da se ne salje mail
				PlacementTransfer rez = tranService.createNewPlacementTransfer(pt, placement.getId(), false);
								
			}
			
			db.Application().save(app);
			
			
			 try {
					User fromUser = db.User().findOne((long)appTran.getFromUser());
					User toUser = db.User().findOne((long)appTran.getToUser());
					Organization fromOrg = db.Orgnaization().findOne((long)appTran.getFromOrg());
					Organization toOrg = db.Orgnaization().findOne((long)appTran.getToOrg());
					
					HashMap<String, String> emailTemplateModel = new HashMap<>();
					emailTemplateModel.put("clientName", "Proslijeđena je aplikacija od: " + fromUser.getName() + " [" + fromOrg.getName() +"] za " + toUser.getName() + " [" + toOrg.getName() +"] za " );
					emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
					emailTemplateModel.put("status", "Status: APPROVED");
					emailTemplateModel.put("description", "");
					emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/" + appTran.getId() );
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
				    	
				    mailService.SendMail("", toRecipients,"Predmet kretanje", emailContent, "", env);
				 }
				 catch(Exception ex) {}
				 
			
		}
		 
		return appTran;
	}
	
	
}
