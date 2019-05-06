package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.TaskService;
import com.unicredit.cap.model.Task;

@RestController
@RequestMapping("/rest/task")
public class TaskController {
	
	 @Autowired
	 private TaskService service;
	 
	 @GetMapping(value = "/all")
	    public List<Task> findAll() {
	        return service.getAllTask();
	    }

	 @GetMapping(value = "/{id}")
	 public Task findById(@PathVariable final Long id){
		
	    return service.getTaskById(id);
	    }
	 
	 
	 @GetMapping(value = "/placement/{id}")
	    public List<Task> findAllByPlacementId(@PathVariable final Long id) {
	        return service.getAllTaskByPlacement(id);
	    }

	 
	 @PostMapping(value = "/create/{id}")
	    public Task createTask(@RequestBody final Task task, @PathVariable final Long id) throws Exception {	       
		 return service.createNewTask(task, id);         
	   }
	 
	 @PostMapping(value = "/confirm/{id}")
	    public Task confirmTask(@PathVariable final Long id) throws Exception {	       		 
		 return service.confirmTask(id);         
	   }
	 
	 @GetMapping(value = "/user/{id}")
	    public List<Task> findAllByUserId(@PathVariable final Long id) {	       		 
		 return service.getAllTasksByUserId(id);         
	   }
	 
	 
	 @GetMapping(value = "/appOrg/{orgId}")
	    public List<Task> findTaskByAppOrg(@PathVariable final Long orgId) {	       		 
		 return service.getAllTaskByAppOrg(orgId);        
	   }
	 
	 @PostMapping(value = "/{taskId}/user/{userId}")
	    public Task asignUserToTask(@PathVariable final Long taskId, @PathVariable final Integer userId) throws Exception {	       		 
		 return service.asignUserToTask(taskId, userId);         
	   }
	 
	 
}
