package com.unicredit.cap.busineslogic;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.repository.DbContext;

@Service
public class TaskDetailService {

	
	@Autowired
	private DbContext db;
	
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
	
	public TaskDetail createNewTaskDetail(TaskDetail taskDetail, Long id){
		
		Optional<Task> task = db.Task().findById(id);

		if (!task.isPresent())
			 throw new CapNotFoundException("Task with id=" + id + " was not found");
		
		TaskDetail taskDetailPrevious = db.TaskDetail().getLastDetailOfTask(id);
		taskDetailPrevious.setToDate(new Date());
		db.TaskDetail().save(taskDetailPrevious);
		
		taskDetail.setFromDate(new Date());
		taskDetail.setTask(task.get());
		db.TaskDetail().save(taskDetail);	
		
		int taskStatus = taskDetail.getToUser() == task.get().getCreateUser() ? 36 : 35;
		Task taskWithStatusUpdate = task.get();
		taskWithStatusUpdate.setStatus(taskStatus);
		
		db.Task().save(taskWithStatusUpdate);
			
		return taskDetail;
		
	}
	
}
