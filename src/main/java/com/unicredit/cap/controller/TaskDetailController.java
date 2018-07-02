package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.TaskDetail;
import com.unicredit.cap.repository.TaskDetailRepository;

@RestController
@RequestMapping("/rest/taskdetail")
public class TaskDetailController {

	
	 @Autowired
	 private TaskDetailRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<TaskDetail> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<TaskDetail> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
	
}
