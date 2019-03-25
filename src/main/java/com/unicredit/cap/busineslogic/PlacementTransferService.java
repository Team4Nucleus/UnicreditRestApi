package com.unicredit.cap.busineslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.EmailTemplateHelper;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.TaskStatus;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.IMailService;
import com.unicredit.cap.service.MailService;

@Service
public class PlacementTransferService {

	
	@Autowired
	private DbContext db;
	
	private MailService mailService = new MailService();
	
	@Autowired
	private Environment env;
	
	public PlacementTransfer getPlacementTransferById(Long id){
		
		PlacementTransfer placementTransfer = db.PlacementTransfer().findOne(id);
		
		if(placementTransfer == null)
			 throw new CapNotFoundException("Placement Transfer with id=" + id + " was not found");
		
		return placementTransfer;
		
	}
	
	
	public List<PlacementTransfer> getAllPlacementTransfer(){
		
		return db.PlacementTransfer().findAll();
	}
	
	
	public List<PlacementTransfer> getAllPlacementTransferByPlacement(Long id){
		
		Placement plac = db.Placement().findOne(id);
		if(plac == null)
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		return plac.getTransfers();
		
	}
	
	
	public PlacementTransfer createNewPlacementTransfer(PlacementTransfer placementTransfer, Long id, boolean sendMail){
		
		Placement plac = db.Placement().findOne(id);
		if(plac == null)
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		try {
			
		PlacementTransfer placementTransferLast = db.PlacementTransfer().getLastTransferOfPlacement(id);
		placementTransferLast.setDateTo(new Date());
		db.PlacementTransfer().save(placementTransferLast);
		
		placementTransfer.setDateFrom(new Date());
		placementTransfer.setPlacement(plac);
		db.PlacementTransfer().save(placementTransfer);
		
		Placement placement = plac;
		placement.setCurrentOrg(placementTransfer.getToOrg());
		placement.setCurrentUser(placementTransfer.getToUser());
		db.Placement().save(placement);
		
				
		User fromUser = db.User().findOne((long)placementTransfer.getFromUser());
		User toUser = db.User().findOne((long)placementTransfer.getToUser());
		Organization fromOrg = db.Orgnaization().findOne((long)placementTransfer.getFromOrg());
		Organization toOrg = db.Orgnaization().findOne((long)placementTransfer.getToOrg());
		
		HashMap<String, String> emailTemplateModel = new HashMap<>();
		emailTemplateModel.put("clientName", "Zadatak od: " + fromUser.getName() + " [" + fromOrg.getName() +"]");
		emailTemplateModel.put("placementType", "Plasman: " + plac.getPlacementtype().getName() + ", " + plac.getClientName());
		emailTemplateModel.put("status", "Status: " +  plac.getPlacementstatus().getName());
		emailTemplateModel.put("description", "Komentar: " + placementTransfer.getUserComment());
		emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/placement/" + plac.getId() );
		emailTemplateModel.put("headerText", "Zadatak od: " + fromUser.getName() );
		emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
		emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
		
	    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
	    
	    List<String> toRecipients = new ArrayList<String>();
	    
	    if(placementTransfer.getToUser() != null)
	    toRecipients.add(toUser.getEmail());
	    else
	    {
	    	if(toOrg.getEmail() != null && !toOrg.getEmail().equals("") )
	    		toRecipients.add(toOrg.getEmail());
	    }
	    	
	    
	    if (sendMail)
	    mailService.SendMail("", toRecipients,"Predmet kretanje", emailContent, "", env);
		
		return placementTransfer;
		
		}
		catch (Exception e) {
			 throw new CapNotFoundException("Error: " + e.getMessage());
		}
	}
	
}
