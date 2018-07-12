package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.PlacementStatusService;
import com.unicredit.cap.busineslogic.TaskStatusService;
import com.unicredit.cap.model.PlacementStatus;
import com.unicredit.cap.model.TaskStatus;
import com.unicredit.cap.repository.TaskStatusRepository;

@RestController
@RequestMapping("/rest/taskstatus")
public class TaskStatusController {

	 @Autowired
	 private TaskStatusService service;
	 
	 @GetMapping(value = "/all")
	    public List<TaskStatus> findAll() {
	        return service.getAllTaskStatus();
	    }

	 @GetMapping(value = "/{id}")
	 public TaskStatus findById(@PathVariable final Long id){
		
	    return service.getTaskStatusById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	    public TaskStatus createPlacementStatu(@RequestBody final TaskStatus status) {	       
		return service.createNewTaskStatus(status);         
	    }
	
}
