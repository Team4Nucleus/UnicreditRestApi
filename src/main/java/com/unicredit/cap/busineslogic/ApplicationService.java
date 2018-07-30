package com.unicredit.cap.busineslogic;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.MailService;

@Service
public class ApplicationService {


	@Autowired
	private DbContext db;
	private MailService mailService = new ExchangeMailService();
	
	public Application getApplicationById(long id){
		
		 Optional<Application> app = db.Application().findById(id);
		
		 if (!app.isPresent())
			 throw new CapNotFoundException("Application with id=" + id + " was not found");
		 
		 return app.get(); 
	}
	
	public List<Application> getAllApplications() {
		
		List<Application> list = db.Application().findTop100ByOrderByCreateDateDesc();
		
		return list;
	}
	
	public List<Application> getApplicationsByUser(int id){
		
		List<Application> list = db.Application().findTop100ByCreateUserOrderByCreateDateDesc(id);
		
		return list;
	}
	
	public Optional<Application> saveNewApplication(Application application){
				
		
		for (Placement placement : application.getPlacements())
		{
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
		   		transfer.setToOrg(placement.getCreatingOrg().longValue());
		   		transfer.setToUser(placement.getCreateUser().longValue());
		   		transfer.setMovementType("Kreiranje Plasmana");  
		   		transfer.setPlacement(placement);
		   		
		   		List<PlacementTransfer> list = new ArrayList<PlacementTransfer>();
		   		list.add(transfer);
		
		   		placement.setTransfers(list);
		   		placement.setCretaingDate(new Date());
		   		placement.setApplication(application);
		}
		
		 application.setCreateDate(new Date());
		
		 db.Application().save(application);	 
		 
		 Optional<Application> app = db.Application().findById(application.getId());		
		 return app;

		}

	public Application updateApplication(Application application)  {
		
		 Optional<Application> currentApplication = db.Application().findById(application.getId());

	        if (!currentApplication.isPresent())
	        {
	        	throw new CapNotFoundException("Application with id=" + application.getId() + " was not found"); 		
	        }

	        Application App = currentApplication.get();
	 
	        App.setApplicationDate(application.getApplicationDate());
	        App.setCode(application.getCode());
	        App.setDescription(application.getDescription());
        
	        db.Application().save(App);
		
	        return App;
	        
	}
	
	
}
