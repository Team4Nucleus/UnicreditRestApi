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
		
		Optional<Task> task = db.Task().findById(id);
		
		if(!task.isPresent())
			 throw new CapNotFoundException("Task with id=" + id + " was not found");
		
		return task.get();
	}
	
	public List<Task> getAllTask(){
		
		return db.Task().findAll();
		
	}
	
	public List<Task> getAllTaskByPlacement(Long id){
			Optional<Placement> placement = db.Placement().findById(id);
		
		if (!placement.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		
		return placement.get().getTasks();
	}
	
	public Task createNewTask(Task task, Long id) throws Exception{
		
		Optional<Placement> placement = db.Placement().findById(id);

		if (!placement.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		for (TaskDetail taskDetail : task.getTaskdetails())
			{
				taskDetail.setFromDate(new Date());
				taskDetail.setTask(task);
			}
		
		task.setCreateDate(new Date());
		task.setPlacement(placement.get());

		task = db.Task().save(task);
		
		TaskDetail taskDetail = db.TaskDetail().getFirstDetailOfTask(task.getId());
		
		
		if(taskDetail != null) {
						
			User fromUser = db.User().findById((long)taskDetail.getFromUser()).get();
			User toUser = db.User().findById((long)taskDetail.getToUser()).get();
			Organization fromOrg = db.Orgnaization().findById((long)taskDetail.getFromOrg()).get();
			TaskStatus status = db.TaskStatus().findById((long)task.getStatus()).get();
			
			HashMap<String, String> emailTemplateModel = new HashMap<>();
			emailTemplateModel.put("clientName", "Zadatak od: " + fromUser.getName() + " [" + fromOrg.getName() +"]");
			emailTemplateModel.put("placementType", "Plasman: " + placement.get().getPlacementtype().getName() + ", " + placement.get().getClientName());
			emailTemplateModel.put("status", "Status: " + status.getName() + ", Prioritet: " + task.getPriority());
			emailTemplateModel.put("description", "[" + taskDetail.getFromDate() + "] " + taskDetail.getText());
			emailTemplateModel.put("link", env.getProperty("app.domain")+ "/user-tasks/details/" + task.getId() );
			
		    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
		    
		    List<String> toRecipients = new ArrayList<String>();
		    
		    if (taskDetail.getToUser() != null)
		    	toRecipients.add(toUser.getEmail());
		    

		    mailService.SendMail("", toRecipients,"Novi zadatak", emailContent, "", env);
		}
		
		return task;
		
	}
	
	public Task confirmTask(Long id) throws Exception {
		Optional<Task> taskOp = db.Task().findById(id);
		
		if (!taskOp.isPresent()) {
			throw new CapNotFoundException("Task with id="+ id +" was not found");
		}
		
		Task task = taskOp.get();
		
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
		    
		    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
		    
		    List<String> toRecipients = new ArrayList<String>();
		    
		    if (firstTaskDetail.getToUser() != null)
		    toRecipients.add(firstTaskDetail.getToUserDetails().getEmail());
		    
		    mailService.SendMail("", toRecipients,"Zatvaranje zadatka", emailContent, "", env);
		    
		}
		
		return task;
	}
	
	public List<Task> getAllTasksByUserId(Long id){
		Optional<AppUser> appUser = db.AppUser().findById(id);
		
		if(!appUser.isPresent()) {
			throw new CapNotFoundException("User with id="+ id +" was not found");
		}
		
		return db.Task().getAllTasksByUserId(id);
	}
	
}
