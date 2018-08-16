package com.unicredit.cap.busineslogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.EmailTemplateHelper;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.MailService;

@Service
public class PlacementTransferService {

	
	@Autowired
	private DbContext db;
	private MailService mailService = new ExchangeMailService();
	
	
	public PlacementTransfer getPlacementTransferById(Long id){
		
		Optional<PlacementTransfer> placementTransfer = db.PlacementTransfer().findById(id);
		
		if(!placementTransfer.isPresent())
			 throw new CapNotFoundException("Placement Transfer with id=" + id + " was not found");
		
		return placementTransfer.get();
		
	}
	
	
	public List<PlacementTransfer> getAllPlacementTransfer(){
		
		return db.PlacementTransfer().findAll();
	}
	
	
	public List<PlacementTransfer> getAllPlacementTransferByPlacement(Long id){
		
		Optional<Placement> plac = db.Placement().findById(id);
		if(!plac.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		return plac.get().getTransfers();
		
	}
	
	
	public PlacementTransfer createNewPlacementTransfer(PlacementTransfer placementTransfer, Long id){
		
		Optional<Placement> plac = db.Placement().findById(id);
		if(!plac.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		try {
			
		PlacementTransfer placementTransferLast = db.PlacementTransfer().getLastTransferOfPlacement(id);
		placementTransferLast.setDateTo(new Date());
		db.PlacementTransfer().save(placementTransferLast);
		
		placementTransfer.setDateFrom(new Date());
		placementTransfer.setPlacement(plac.get());
		db.PlacementTransfer().save(placementTransfer);
		
		Placement placement = plac.get();
		placement.setCurrentOrg(placementTransfer.getToOrg());
		placement.setCurrentUser(placementTransfer.getToUser());
		db.Placement().save(placement);
		
		HashMap<String, String> emailTemplateModel = new HashMap<>();
	    emailTemplateModel.put("description", placementTransfer.getUserComment());
	    emailTemplateModel.put("clientName", placement.getClientName());
	    emailTemplateModel.put("placementType",placement.getPlacementtype().getName());
	    
	        
	    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
	    
	    List<String> toRecipients = new ArrayList<String>();
	    
	    toRecipients.add(db.User().getOne((long)placementTransfer.getToOrg()).getEmail());
	    
	    new ExchangeMailService().SendMail("", toRecipients,"Predmet kretanje", emailContent, "");
		
		return placementTransfer;
		
		}
		catch (Exception e) {
			 throw new CapNotFoundException("Error: " + e.getMessage());
		}
	}
	
}
