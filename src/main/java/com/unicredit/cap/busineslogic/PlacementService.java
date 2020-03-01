package com.unicredit.cap.busineslogic;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hibernate.hql.internal.ast.tree.IsNullLogicOperatorNode;
import org.hibernate.loader.plan.build.internal.returns.EntityAttributeFetchImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.EmailTemplateHelper;
import com.unicredit.cap.helper.TimeConsumeWrapper;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTimeConsument;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.model.TaskTimeConsument;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.IMailService;


@Service
public class PlacementService {
	
	@Autowired
	private DbContext db;
	private IMailService mailService = new ExchangeMailService();
	
	@Autowired
	private Environment env;
	
	public Placement getPlacementById(long id){
		
		Placement plac = db.Placement().findOne(id);
		
		if (plac == null)
			throw new CapNotFoundException("Placement with id=" + id + " was not found");     
			
		return plac;
	}
	
	public List<Placement> getAllPlacements(){
		
		List<Placement> list = db.Placement().findAll();		
		return list;
		
		
	}
	
	public Placement setPlacementCurrentUser(long id, Long user) {
		
		Placement plac = db.Placement().findOne(id);
		
		 if (plac == null)
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		 
		User u = db.User().findOne(user);
		 if (u == null)
			 throw new CapNotFoundException("User with id=" + user + " was not found");
		 
		plac.setCurrentUser(user);
		
		db.Placement().save(plac);
		
		
		 try {
				//User fromUser = db.User().findOne((long)plac.getFromUser());
				//User toUser = db.User().findOne((long)plac.getToUser());
				//Organization fromOrg = db.Orgnaization().findOne((long)plac.getFromOrg());
				//Organization toOrg = db.Orgnaization().findOne((long)plac.getToOrg());
				
				HashMap<String, String> emailTemplateModel = new HashMap<>();
				emailTemplateModel.put("clientName", "Vaš predpostavljeni Vas je zadužio za Novi Plasman: " );
				emailTemplateModel.put("placementType", "Plasman: " + plac.getPlacementtype().getName() + ", " + plac.getClientName());
				emailTemplateModel.put("status", "Status: " + plac.getPlacementstatus().getName());
				emailTemplateModel.put("description", "");
				emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/placement/" + plac.getId() + "/-" );
				emailTemplateModel.put("headerText", "Dodjeljivanje Plasmana");
				emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
				emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
				
			    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
			    
			    List<String> toRecipients = new ArrayList<String>();
			    
			    toRecipients.add(u.getEmail());
			    	
			    mailService.SendMail("", toRecipients,"Plasman kretanje", emailContent, "", env);
			 }
			 catch(Exception ex) {}
		
		return plac;
		
		
	}
	
	
	public Placement saveNewPlacement(Placement placement, Long id){
		
	
		
		Application app = db.Application().findOne(id);
		if (app == null)
			throw new CapNotFoundException("Appliaction with id=" + id + " was not found"); 
		
		Application application  = app;
		
		
		
			for(Task task : placement.getTasks())
	   		{	   			
	   				for (TaskDetail taskDetail : task.getTaskdetails())
	   				{
	   					taskDetail.setFromDate(new Date());
	   					taskDetail.setTask(task);	
	   				}
	   				task.setCreateDate(new Date());
	   				task.setPlacement(placement);
	   		}
			
		   		for(Document doc : placement.getDocuments())
		   		{
		   			doc.setPlacement(placement);
		   		}
		   		
		   		for(PlacementTransfer transfer : placement.getTransfers())
		   		{
		   			transfer.setPlacement(placement);
		   		}
		   		
		   		PlacementTransfer  transfer = new PlacementTransfer();
		   		transfer.setDateFrom(new Date());
		   		transfer.setToOrg(placement.getCreatingOrg());
		   		transfer.setToUser(placement.getCreateUser().longValue());
		   		transfer.setMovementType("Kreiranje Plasmana");  
		   		transfer.setPlacement(placement);
		   		
		   		List<PlacementTransfer> list = new ArrayList<PlacementTransfer>();
		   		list.add(transfer);
		
		placement.setTransfers(list);
		placement.setCretaingDate(new Date());
		placement.setApplication(application);
		   		
		Placement plac = db.Placement().save(placement);	
		// mailService.SendMail("", new List<String>() , "", "");		
		return plac;
		}
	
	public Placement updatePlacement(Placement placement)
	{
		
		 Placement currentPlacement = db.Placement().findOne(placement.getId());
		 
	        if (currentPlacement == null)
	        {
	        	throw new CapNotFoundException("Placement with id=" + placement.getId() + " was not found");         		
	        }
	             
	        Placement plac = currentPlacement;
	 
	        plac.setClientCoreNo(placement.getClientCoreNo());
	        plac.setClientEmail(placement.getClientEmail());
	        plac.setClientJib(placement.getClientJib());
	        plac.setClientName(placement.getClientName());
	        plac.setClientPersonalDoc(placement.getClientPersonalDoc());
	        plac.setClientPhone(placement.getClientPhone());
	     //   plac.setClosingDate(placement.getClosingDate());
	        plac.setCreateUser(placement.getCreateUser());
	     //   plac.setCreatingOrg(placement.getCreatingOrg());
	     //   plac.setCretaingDate(placement.getCretaingDate());
	        plac.setCurrentOrg(placement.getCurrentOrg());
	        plac.setCurrentUser(placement.getCurrentUser());
	        plac.setDecisionNumber(placement.getDecisionNumber());
	        plac.setLoanAmount(placement.getLoanAmount());
	        plac.setLoanUsage(placement.getLoanUsage());
	        plac.setPaymentPeriod(placement.getPaymentPeriod());
	        plac.setRequestDate(placement.getRequestDate());
	        plac.setStatus(placement.getStatus());
	        plac.setTotalExposure(placement.getTotalExposure());
	        plac.setType(placement.getType());
	        plac.setUserComment(placement.getUserComment());
	        plac.setCurrency(placement.getCurrency());
	        plac.setAmountInBam(placement.getAmountInBam());
	        plac.setSocialRisk(placement.getSocialRisk());
	        
	        plac.setAmountInBamApproved(placement.getAmountInBamApproved() );
	      //  plac.setDecision(placement.getDecision());
	      //  plac.setOpinionOKR(placement.getOpinionOKR());
	     //   plac.setOpinionNBCO(placement.getOpinionNBCO());
	        
	        db.Placement().save(plac);
		
	        return plac;
	}
	
	
	public List<TimeConsumeWrapper> getTimeConsumentByPlacement (Long id){
		
		List<TimeConsumeWrapper> result = new ArrayList<TimeConsumeWrapper>();
		
		List<PlacementTimeConsument> PlacementTimeList = db.placementTime().getTimeConsumentByPlacement(id);
		
		for( PlacementTimeConsument placementTime : PlacementTimeList )
		{
			List<TaskTimeConsument> TaskTimeList = db.taskTime().getTimeConsumentByPlacementAndOrg(id, placementTime.getOrgID());
			
			result.add(new TimeConsumeWrapper(placementTime,TaskTimeList ));
		}
		
		return result;
		
	}
	
	
	public Placement UpdateStatus(Long id, int IdStatus)
	{
		Placement plac = db.Placement().findOne(id);
		
		if (plac == null)
			throw new CapNotFoundException("Placement with id=" + id + " was not found");    
		
		Placement placement = plac;
		
		placement.setStatus(IdStatus);
		
		placement.setClosingDate(new Date());
		db.Placement().save(placement);
		
		return placement;
		
	}
	
	
	public Placement SetApprovedAmount(Long id, Double amount)
	{
		Placement plac = db.Placement().findOne(id);
		
		if (plac == null)
			throw new CapNotFoundException("Placement with id=" + id + " was not found");    
		
		Placement placement = plac;
		
		placement.setAmountInBamApproved(amount);
		db.Placement().save(placement);
		
		return placement;
	}
	
	public Placement SetOpinionAndDecision(Long id, String opinionOKR, String opinionNBCO, String decision)
	{
		Placement plac = db.Placement().findOne(id);
		
		if (plac == null)
			throw new CapNotFoundException("Placement with id=" + id + " was not found");    
		
		Placement placement = plac;
		
		if (!opinionOKR.equals("0"))
		placement.setOpinionOKR(opinionOKR);
		
		if (opinionOKR.equals("NULL"))
			placement.setOpinionOKR(null);
		
		if (!opinionNBCO.equals("0"))
		placement.setOpinionNBCO(opinionNBCO);
		
		if (opinionNBCO.equals("NULL"))
			placement.setOpinionNBCO(null);
		
		if (!decision.equals("0")) {
		placement.setDecision(decision);
		placement.setDecisionDate(new Date());
		}
		
		if (decision.equals("NULL")) {
			placement.setDecision(null);
			placement.setDecisionDate(null);
			}
		
		
		db.Placement().save(placement);
		
		return placement;
	}
	
}
