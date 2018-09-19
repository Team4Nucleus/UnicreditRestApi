package com.unicredit.cap.busineslogic;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.loader.plan.build.internal.returns.EntityAttributeFetchImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.TimeConsumeWrapper;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTimeConsument;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.model.TaskTimeConsument;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.MailService;


@Service
public class PlacementService {
	
	@Autowired
	private DbContext db;
	private MailService mailService = new ExchangeMailService();
	
	
	public Placement getPlacementById(long id){
		
		Optional<Placement> plac = db.Placement().findById(id);
		
		if (!plac.isPresent())
			throw new CapNotFoundException("Placement with id=" + id + " was not found");     
			
		return plac.get();
	}
	
	public List<Placement> getAllPlacements(){
		
		List<Placement> list = db.Placement().findAll();		
		return list;
		
	}
	
	public Placement saveNewPlacement(Placement placement, Long id){
		
	
		
		Optional<Application> app = db.Application().findById(id);
		if (!app.isPresent())
			throw new CapNotFoundException("Appliaction with id=" + id + " was not found"); 
		
		Application application  = app.get();
		
		
		
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
		
		 Optional<Placement> currentPlacement = db.Placement().findById(placement.getId());
		 
	        if (!currentPlacement.isPresent())
	        {
	        	throw new CapNotFoundException("Placement with id=" + placement.getId() + " was not found");         		
	        }
	             
	        Placement plac = currentPlacement.get();
	 
	        plac.setClientCoreNo(placement.getClientCoreNo());
	        plac.setClientEmail(placement.getClientEmail());
	        plac.setClientJib(placement.getClientJib());
	        plac.setClientName(placement.getClientName());
	        plac.setClientPersonalDoc(placement.getClientName());
	        plac.setClientPhone(placement.getClientPhone());
	        plac.setClosingDate(placement.getClosingDate());
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
	        
     
	        db.Placement().save(plac);
		
	        return plac;
	}
	
	public String Test(long x)  {
		
	

		long a = 2 / (x-1);
		
		return "";
		
		
		
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
		Optional<Placement> plac = db.Placement().findById(id);
		
		if (!plac.isPresent())
			throw new CapNotFoundException("Placement with id=" + id + " was not found");    
		
		Placement placement = plac.get();
		
		placement.setStatus(IdStatus);
		db.Placement().save(placement);
		
		return placement;
		
	}
	
}
