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
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.model.User;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;

@Service
public class TaskDetailService {

	
	@Autowired
	private DbContext db;
	
	@Autowired
	private Environment env;
	
	public TaskDetail getTaskDetailById(Long id){
		
		Optional<TaskDetail> taskDetail = db.TaskDetail().findById(id);
		
		if(!taskDetail.isPresent())
			 throw new CapNotFoundException("TaskDetail with id=" + id + " was not found");
		
		return taskDetail.get();
	}
	
	public List<TaskDetail> getAllTaskDetail(){
		
		return db.TaskDetail().findAll();
		
	}
	
	public List<TaskDetail> getAllTaskDetailByTask(Long id){
		Optional<Task> task = db.Task().findById(id);
	
			if (!task.isPresent())
				throw new CapNotFoundException("Task with id=" + id + " was not found");
	
				return task.get().getTaskdetails();
		}
	
	public TaskDetail createNewTaskDetail(TaskDetail taskDetail, Long id) throws Exception{
		
		Optional<Task> task = db.Task().findById(id);

		if (!task.isPresent())
			 throw new CapNotFoundException("Task with id=" + id + " was not found");
		
		TaskDetail taskDetailPrevious = db.TaskDetail().getLastDetailOfTask(id);
		taskDetailPrevious.setToDate(new Date());
		db.TaskDetail().save(taskDetailPrevious);
		
		taskDetail.setFromDate(new Date());
		taskDetail.setTask(task.get());
		db.TaskDetail().save(taskDetail);	
				
		int taskStatus = taskDetail.getToUser() == (task.get().getCreateUser() == null ? -1 : task.get().getCreateUser().intValue()) ? 36 : 35;
		Task taskWithStatusUpdate = task.get();
		taskWithStatusUpdate.setStatus(taskStatus);
		
		db.Task().save(taskWithStatusUpdate);
		
		User fromUser = db.User().findById((long)taskDetail.getFromUser()).get();
		User toUser = db.User().findById((long)taskDetail.getToUser()).get();
		Organization fromOrg = db.Orgnaization().findById((long)taskDetail.getFromOrg()).get();
		Placement placement = task.get().getPlacement();
		
		
		HashMap<String, String> emailTemplateModel = new HashMap<>();
		emailTemplateModel.put("clientName", "Zadatak od: " + fromUser.getName() + " [" + fromOrg.getName() +"]");
		emailTemplateModel.put("placementType", "Plasman: " + placement.getPlacementtype().getName() + ", " + placement.getClientName());
		emailTemplateModel.put("status", "Status: " + task.get().getTaskstatus().getName() + ", Prioritet: " + task.get().getPriority());
		emailTemplateModel.put("description", "[" + taskDetail.getFromDate() + "] " + taskDetail.getText());
		emailTemplateModel.put("link", "/task/" + id);
		emailTemplateModel.put("link", env.getProperty("app.domain")+ "/user-tasks/details/" + task.get().getId() );
	        
	    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
	    
	    List<String> toRecipients = new ArrayList<String>();
	    
	    
	    toRecipients.add(toUser.getEmail());
	    
	    new ExchangeMailService().SendMail("", toRecipients,"Zadatak", emailContent, "");
	       
		return taskDetail;
		
	}
	
}
