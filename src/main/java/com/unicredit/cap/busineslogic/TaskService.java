package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.Task;
import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.repository.DbContext;

@Service
public class TaskService {

	@Autowired
	private DbContext db;
	
	public Task getTaskById(Long id){
		
		Optional<Task> task = db.Tasks().findById(id);
		
		if(!task.isPresent())
			 throw new CapNotFoundException("Task with id=" + id + " was not found");
		
		return task.get();
	}
	
	public List<Task> getAllTask(){
		
		return db.Tasks().findAll();
		
	}
	
	public List<Task> getAllTaskByPlacement(Long id){
			Optional<Placement> placement = db.Placement().findById(id);
		
		if (!placement.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		
		return placement.get().getTasks();
	}
	
	public Task createNewTask(Task task, Long id){
		
		Optional<Placement> placement = db.Placement().findById(id);

		if (!placement.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		for (TaskDetail taskDetail : task.getTaskdetails())
			{
				taskDetail.setTask(task);	
			}
		
		task.setPlacement(placement.get());

		db.Tasks().save(task);		
		return task;
		
	}
	
	
	
}
