package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.TaskStatus;
import com.unicredit.cap.repository.TaskStatusRepository;

@RestController
@RequestMapping("/rest/taskstatus")
public class TaskStatusController {

	 @Autowired
	 private TaskStatusRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<TaskStatus> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<TaskStatus> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
	
	
}
