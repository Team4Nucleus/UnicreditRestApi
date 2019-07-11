package com.unicredit.cap.busineslogic;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.ApplicationWithPlacements;
import com.unicredit.cap.helper.EmailTemplateHelper;
import com.unicredit.cap.helper.TimeConsumeWrapper;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.Document;
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
public class ApplicationService {


	@Autowired
	private DbContext db;
	private IMailService mailService = new ExchangeMailService();
	
	@Autowired
	private Environment env;
	
	public Application getApplicationById(long id){
		
		 Application app = db.Application().findOne(id);
		
		 if (app == null)
			 throw new CapNotFoundException("Application with id=" + id + " was not found");
		 
		 return app; 
	}
	
	public List<Application> getAllApplicationByCurrentOrg(long currentOrg)
	{
		return db.Application().getAllApplicationByCurrentOrg(currentOrg);
	}
	
	public Application setApplicationCurrentUser(long id, Long user) {
		
		Application app = db.Application().findOne(id);
		
		 if (app == null)
			 throw new CapNotFoundException("Application with id=" + id + " was not found");
		 
		User u = db.User().findOne(user);
		 if (u == null)
			 throw new CapNotFoundException("User with id=" + user + " was not found");
		 
		app.setCurrentUser(user);
		
		for (Placement plac : app.getPlacements())
		{
			plac.setCurrentUser(user);
			db.Placement().save(plac);
		}
		
		db.Application().save(app);
		
		try {
		//********* slanje maila ****** //
		//User fromUser = db.User().findOne((long)placementTransfer.getFromUser());
		//User toUser = db.User().findOne((long)placementTransfer.getToUser());
		//Organization fromOrg = db.Orgnaization().findOne((long)placementTransfer.getFromOrg());
		//Organization toOrg = db.Orgnaization().findOne((long)placementTransfer.getToOrg());
		
		HashMap<String, String> emailTemplateModel = new HashMap<>();
		emailTemplateModel.put("clientName", "Vaš predpostaljeni Vam je dodjelio aplikaciju!");
		emailTemplateModel.put("placementType", "Aplikacija: " + app.getCode() + ", " + app.getDescription());
		emailTemplateModel.put("status", "Status: Aktivno");
		emailTemplateModel.put("description", "Komentar: " + app.getDescription());
		emailTemplateModel.put("link", env.getProperty("app.domain")+ "/#/loan-requests/details/" + app.getId() );
		emailTemplateModel.put("headerText", "");
		emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
		emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
		
	    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
	    
	    List<String> toRecipients = new ArrayList<String>();
	    
	    toRecipients.add(u.getEmail());
	   
	    mailService.SendMail("", toRecipients,"Predmet kretanje: " + app.getDescription(), emailContent, "", env);
		//******************************//
		} catch(Exception ex) {
			
		}
		
		
		return app;
		
		
		
	}
	
	public List<Application> getAllApplications() {
		
		List<Application> list = db.Application().findTop100ByOrderByCreateDateDesc();
		
		return list;
	}
	
	public List<Application> getApplicationsByUser(int id){
		
		List<Application> list = db.Application().findTop100ByCreateUserOrderByCreateDateDesc(id);
		
		return list;
	}
	
	public Application saveNewApplication(Application application){
				
		
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
		 application.setCurrentUser(application.getCreateUser());
		 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
		 int year = cal.get(Calendar.YEAR);
		 int month = cal.get(Calendar.MONTH) + 1;
		 application.setCode(year + "-"+ month + "-" + RandomStringUtils.randomAlphanumeric(7).toUpperCase());
		 
		 Long IdOrg = db.User().findOne(application.getCreateUser()).getHrOrganization();
		 application.setCurrentOrg(IdOrg);
		 
		 db.Application().save(application);	 
		 
		 Application app = db.Application().findOne(application.getId());		
		 return app;

		}

	public Application updateApplication(Application application)  {
		
		 Application currentApplication = db.Application().findOne(application.getId());

	        if (currentApplication == null)
	        {
	        	throw new CapNotFoundException("Application with id=" + application.getId() + " was not found"); 		
	        }

	        Application App = currentApplication;
	 
	        App.setApplicationDate(application.getApplicationDate());
	        App.setCode(application.getCode());
	        App.setDescription(application.getDescription());
        
	        db.Application().save(App);
		
	        return App;
	        
	}
	
	
	public List<TimeConsumeWrapper> getTimeConsumentByApplication (Long id){
		
		List<TimeConsumeWrapper> result = new ArrayList<TimeConsumeWrapper>();
		
		List<PlacementTimeConsument> PlacementTimeList = db.placementTime().getTimeConsumentByApplication(id);
		
		for( PlacementTimeConsument placementTime : PlacementTimeList )
		{
			List<TaskTimeConsument> TaskTimeList = db.taskTime().getTimeConsumentByApplicationAndOrg(id, placementTime.getOrgID());
			
			result.add(new TimeConsumeWrapper(placementTime,TaskTimeList ));
		}
		
		return result;
		
	}
	
	
	public List<ApplicationWithPlacements> getAllApplicationsWithPlacements(){
		
		List<ApplicationWithPlacements> list = new ArrayList<ApplicationWithPlacements>();
		List<Application> apps = db.Application().findAll();
		
		for(Application app : apps) {
			
			ApplicationWithPlacements awp = new ApplicationWithPlacements();
			awp.setApplication(app);
			awp.setPlacements(app.getPlacements());
		}
		
		return list;
		
	}
}
