package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.TaskStatus;
import com.unicredit.cap.repository.DbContext;

@Service
public class TaskStatusService {

	
	@Autowired
	private DbContext db;
	
	public TaskStatus getTaskStatusById(Long id){
		
		TaskStatus taskStatus = db.TaskStatus().findOne(id);
		
		if(taskStatus == null)
			throw new CapNotFoundException("Task Status with id=" + id + " was not found");
		
		return taskStatus;
	}
	
	
	public List<TaskStatus> getAllTaskStatus(){
		
		return db.TaskStatus().findAll();
		
	}
	
	
	public TaskStatus createNewTaskStatus(TaskStatus status)
	{
		db.TaskStatus().save(status);
		return status;
	}
	
}
