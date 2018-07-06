package com.unicredit.cap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.busineslogic.ApplicationService;
import com.unicredit.cap.busineslogic.PlacementService;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.MailService;

@RestController
@RequestMapping("/rest/application")
public class ApplicationController {
	
	 
	 @Autowired 
	 private ApplicationService service;
	 
	 @Autowired 
	 private PlacementService PlacementBusinesLogic;
	 
	 @JsonView(Application.class)
	 @GetMapping(value = "/all")
	    public List<Application> findAll() {
	        return service.getAllApplications();
	    }

	 @JsonView(Application.class)
	 @GetMapping(value = "/allapp")
	    public List<Application> findAllApp() {
		 
		 List<String> str = new ArrayList<>();
		 MailService mailService = new ExchangeMailService();
		 mailService.SendMail("", str, "", "");
		 
	        return service.getAllApplications();
	    }
	 
	 
	 @JsonView(Application.class)
	 @GetMapping(value = "/user/{id}")
	    public List<Application> findByUser(@PathVariable final int id) {
	        return  service.getApplicationsByUser(id);
	    }
	 
	 
	 @GetMapping(value = "/{id}")
	 public Application findById(@PathVariable final Long id){
	    return service.getApplicationById(id);
	    }

	 
	 @GetMapping(value = "/test/{id}")
	 public Application findById1(@PathVariable final long id){
		String a = PlacementBusinesLogic.Test(id);
	    return service.getApplicationById(id);								
	    }
	 
	 @PostMapping(value = "/create")
	    public Optional<Application> createApplication(@RequestBody final Application application) {	       
		 return service.saveNewApplication(application);         
	    }
	 
	 
	 @PutMapping(value = "/update")
	    public Application updateApplication(@RequestBody Application application) {	
	    return service.updateApplication(application);  
	    }
	 
	 
	
}
