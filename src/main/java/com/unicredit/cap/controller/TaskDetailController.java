package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.TaskDetailService;
import com.unicredit.cap.model.TaskDetail;

@RestController
@RequestMapping("/rest/taskdetail")
public class TaskDetailController {

	
	 @Autowired
	 private TaskDetailService service;
	 
	 @GetMapping(value = "/all")
	    public List<TaskDetail> findAll() {
	        return service.getAllTaskDetail();
	    }

	 @GetMapping(value = "/{id}")
	 public TaskDetail findById(@PathVariable final Long id){
		
	    return service.getTaskDetailById(id);
	    }
	 
	 
	 
	 @GetMapping(value = "/task/{id}")
	 public List<TaskDetail> findByTask(@PathVariable final Long id){
		
	    return service.getAllTaskDetailByTask(id);
	    }
	 
	 
	 @PostMapping(value = "/create/{id}")
	    public TaskDetail createApplication(@RequestBody final TaskDetail taskDetail, @PathVariable final Long id) {	       
		 return service.createNewTaskDetail(taskDetail, id);         
	    }
	
}
