package com.unicredit.cap.busineslogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.helper.EmailTemplateHelper;
import com.unicredit.cap.model.AppUser;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.model.TaskStatus;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.MailService;



@Service
public class TaskService {

	@Autowired
	private DbContext db;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MailService mailService = new MailService();
	
	public Task getTaskById(Long id){
		
		Task task = db.Task().findOne(id);
		
		if(task == null)
			 throw new CapNotFoundException("Task with id=" + id + " was not found");
		
		return task;
	}
	
	public List<Task> getAllTask(){
		
		return db.Task().findAll();
		
	}
	
	public List<Task> getAllTaskByPlacement(Long id){
			Placement placement = db.Placement().findOne(id);
		
		if (placement == null )
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		
		return placement.getTasks();
	}
	
	public Task createNewTask(Task task, Long id) throws Exception{
		
		Placement placement = db.Placement().findOne(id);

		if (placement == null)
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		for (TaskDetail taskDetail : task.getTaskdetails())
			{
				taskDetail.setFromDate(new Date());
				taskDetail.setTask(task);
			}
		
		task.setCreateDate(new Date());
		task.setPlacement(placement);

		task = db.Task().save(task);
		
		TaskDetail taskDetail = db.TaskDetail().getFirstDetailOfTask(task.getId());
		
		
		if(taskDetail != null) {
						
			User fromUser = db.User().findOne((long)taskDetail.getFromUser());
			User toUser = db.User().findOne((long)taskDetail.getToUser());
			Organization fromOrg = db.Orgnaization().findOne((long)taskDetail.getFromOrg());
			TaskStatus status = db.TaskStatus().findOne((long)task.getStatus());
			
			
			HashMap<String, String> emailTemplateModel = new HashMap<>();
			emailTemplateModel.put("clientName", "Zadatak od: " + fromUser.getName() + " [" + fromOrg.getName() +"]");
			emailTemplateModel.put("placementType", "Plasman: " + placement.getPlacementtype().getName() + ", " + placement.getClientName());
			emailTemplateModel.put("status", "Status: " + status.getName() + ", Prioritet: " + task.getPriority());
			emailTemplateModel.put("description", "[" + taskDetail.getFromDate() + "] " + taskDetail.getText());
			emailTemplateModel.put("link", env.getProperty("app.domain")+ "/user-tasks/details/" + task.getId() );
			emailTemplateModel.put("headerText", "Zadatak od: " + fromUser.getName() );
			emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
			emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
			
			
		    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
		    
		    List<String> toRecipients = new ArrayList<String>();
		    
		    if (taskDetail.getToUser() != null)
		    	toRecipients.add(toUser.getEmail());
		    

		    mailService.SendMail("", toRecipients,"Novi zadatak", emailContent, "", env);
		}
		
		return task;
		
	}
	
	public Task confirmTask(Long id) throws Exception {
		Task taskOp = db.Task().findOne(id);
		
		if (taskOp == null) {
			throw new CapNotFoundException("Task with id="+ id +" was not found");
		}
		
		Task task = taskOp;
		
	    TaskDetail taskDetail = db.TaskDetail().getLastDetailOfTask(task.getId());
	    
	    if (taskDetail != null) {
	    	taskDetail.setToDate(Calendar.getInstance().getTime());
	    	db.TaskDetail().save(taskDetail);
	    }
		
		task.setClosingDate(Calendar.getInstance().getTime());
		task.setStatus((int)db.TaskStatus().getTaskStatusByCode("FINISHED").getId());
		
		db.Task().save(task);
		
		TaskDetail firstTaskDetail = db.TaskDetail().getFirstDetailOfTask(task.getId());
		
		if(firstTaskDetail != null) {
			HashMap<String, String> emailTemplateModel = new HashMap<>();
			emailTemplateModel.put("clientName", "Zadatak od: " + firstTaskDetail.getFromUserDetails().getName() + " [" + firstTaskDetail.getFromOrgDetails().getName() +"]");
		    emailTemplateModel.put("placementType", "Plasman: " + firstTaskDetail.getTask().getPlacement().getPlacementtype().getName() + ", " + firstTaskDetail.getTask().getPlacement().getClientName());
		    emailTemplateModel.put("status", "Status: " + firstTaskDetail.getTask().getTaskstatus().getName() + ", Prioritet: " + taskDetail.getTask().getPriority());
		    emailTemplateModel.put("description", "Ovaj zadatak je uspješno izvršen!");
		    emailTemplateModel.put("link", env.getProperty("app.domain")+ "/user-tasks/details/" + id );
		    emailTemplateModel.put("headerText", "Zadatak od: " + firstTaskDetail.getFromUserDetails().getName() );
		    emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
			emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
			
		    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
		    
		    List<String> toRecipients = new ArrayList<String>();
		    
		    if (firstTaskDetail.getToUser() != null)
		    toRecipients.add(firstTaskDetail.getToUserDetails().getEmail());
		    
		    mailService.SendMail("", toRecipients,"Zatvaranje zadatka", emailContent, "", env);
		    
		}
		
		return task;
	}
	
	public List<Task> getAllTasksByUserId(Long id){
		
		User hrUser = db.User().findOne(id);
	//	AppUser appUser = db.AppUser().findOne(id);
		
		if(hrUser == null) {
			throw new CapNotFoundException("User with id="+ id +" was not found");
		}
		
		return db.Task().getAllTasksByUserId(id);
	}
	
}
