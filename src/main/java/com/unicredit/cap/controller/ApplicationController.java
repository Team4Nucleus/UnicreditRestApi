package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.repository.ApplicationRepository;

@RestController
@RequestMapping("/rest/application")
public class ApplicationController {
	
	 @Autowired
	 private ApplicationRepository repository;
	 
	 @JsonView(Application.class)
	 @GetMapping(value = "/all")
	    public List<Application> findAll() {
	        return repository.findAll();
	    }

	 @JsonView(Application.class)
	 @GetMapping(value = "/allapp")
	    public List<Application> findAllApp() {
	        return repository.getAllAplications();
	    }
	 
	 
	 @JsonView(Application.class)
	 @GetMapping(value = "/byUser/{id}")
	    public List<Application> findByUser(@PathVariable final int id) {
	        return repository.getAllApplicationByCreateUser(id);
	    }
	 
	 
	 @GetMapping(value = "/{id}")
	 public Optional<Application> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }

}
