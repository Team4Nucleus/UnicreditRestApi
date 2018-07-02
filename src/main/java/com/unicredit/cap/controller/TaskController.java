package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.Task;
import com.unicredit.cap.repository.TaskRepository;

@RestController
@RequestMapping("/rest/task")
public class TaskController {
	
	 @Autowired
	 private TaskRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<Task> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<Task> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }

}
