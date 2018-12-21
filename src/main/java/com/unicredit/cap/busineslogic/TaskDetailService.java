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
import com.unicredit.cap.service.MailService;

@Service
public class TaskDetailService {

	
	@Autowired
	private DbContext db;
	
	@Autowired
	private Environment env;
	
	private MailService mailService = new MailService();
	
	public TaskDetail getTaskDetailById(Long id){
		
		TaskDetail taskDetail = db.TaskDetail().findOne(id);
		
		if(taskDetail == null)
			 throw new CapNotFoundException("TaskDetail with id=" + id + " was not found");
		
		return taskDetail;
	}
	
	public List<TaskDetail> getAllTaskDetail(){
		
		return db.TaskDetail().findAll();
		
	}
	
	public List<TaskDetail> getAllTaskDetailByTask(Long id){
		Task task = db.Task().findOne(id);
	
			if (task == null)
				throw new CapNotFoundException("Task with id=" + id + " was not found");
	
				return task.getTaskdetails();
		}
	
	public TaskDetail createNewTaskDetail(TaskDetail taskDetail, Long id) throws Exception{
		
		Task task = db.Task().findOne(id);

		if (task == null)
			 throw new CapNotFoundException("Task with id=" + id + " was not found");
		
		TaskDetail taskDetailPrevious = db.TaskDetail().getLastDetailOfTask(id);
		taskDetailPrevious.setToDate(new Date());
		db.TaskDetail().save(taskDetailPrevious);
		
		taskDetail.setFromDate(new Date());
		taskDetail.setTask(task);
		db.TaskDetail().save(taskDetail);	
				
		int taskStatus = taskDetail.getToUser() == (task.getCreateUser() == null ? -1 : task.getCreateUser().intValue()) ? 36 : 35;
		Task taskWithStatusUpdate = task;
		taskWithStatusUpdate.setStatus(taskStatus);
		
		db.Task().save(taskWithStatusUpdate);
		
		User fromUser = db.User().findOne((long)taskDetail.getFromUser());
		User toUser = db.User().findOne((long)taskDetail.getToUser());
		Organization fromOrg = db.Orgnaization().findOne((long)taskDetail.getFromOrg());
		Placement placement = task.getPlacement();
		
		
		HashMap<String, String> emailTemplateModel = new HashMap<>();
		emailTemplateModel.put("clientName", "Zadatak od: " + fromUser.getName() + " [" + fromOrg.getName() +"]");
		emailTemplateModel.put("placementType", "Plasman: " + placement.getPlacementtype().getName() + ", " + placement.getClientName());
		emailTemplateModel.put("status", "Status: " + task.getTaskstatus().getName() + ", Prioritet: " + task.getPriority());
		emailTemplateModel.put("description", "[" + taskDetail.getFromDate() + "] " + taskDetail.getText());
	//	emailTemplateModel.put("link", "/task/" + id);
		emailTemplateModel.put("link", env.getProperty("app.domain")+ "/user-tasks/details/" + task.getId() );
		emailTemplateModel.put("headerText", "Zadatak od: " + fromUser.getName() );
		emailTemplateModel.put("poruka-uvod", "Ova poruka Vam je poslana jer ste učesnik u poslovnom procesu odobravanja kredita. U poruci su sadržane sve bitne informacije te postoji veza do programskog rješenje gdje možete izvršiti dalje radnje." );
		emailTemplateModel.put("poruka-footer", "Marija Bursać 7");
		 
	    String emailContent = EmailTemplateHelper.processEmailTemplate("task-template.html", emailTemplateModel);
	    
	    List<String> toRecipients = new ArrayList<String>();
	    
	    
	    toRecipients.add(toUser.getEmail());
	    
		mailService.SendMail("", toRecipients,"Zadatak", emailContent, "", env);
	       
		return taskDetail;
		
	}
	
}
