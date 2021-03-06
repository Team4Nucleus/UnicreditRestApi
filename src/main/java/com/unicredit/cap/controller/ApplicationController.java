package com.unicredit.cap.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.busineslogic.ApplicationService;
import com.unicredit.cap.busineslogic.PlacementService;
import com.unicredit.cap.helper.ApplicationWithPlacements;
import com.unicredit.cap.helper.Report1;
import com.unicredit.cap.helper.Report2;
import com.unicredit.cap.helper.Report21;
import com.unicredit.cap.helper.Report3;
import com.unicredit.cap.helper.TimeConsumeWrapper;
import com.unicredit.cap.helper.ViewProfile;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.repository.Report1Repository;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.IMailService;

//@CrossOrigin
@RestController
@RequestMapping("/rest/application")
public class ApplicationController {
	
	 
	 @Autowired 
	 private ApplicationService service;
	 
	 
	 @Autowired 
	 private PlacementService PlacementBusinesLogic;
	 
	 @JsonView(ViewProfile.Application.class)
	 @GetMapping(value = "/all")
//	 @PreAuthorize("hasRole('ROLE_RM')")
	    public List<Application> findAll() {
	        return service.getAllApplications();
	    }

	 
	 @GetMapping(value = "/allapplications")
	    public List<ApplicationWithPlacements> findAllwithPlacements() {
	        return service.getAllApplicationsWithPlacements();
	    }
	 
	 @JsonView(ViewProfile.Application.class)
	 @GetMapping(value = "/allapp")
	    public List<Application> findAllApp() {
		 
		 List<String> str = new ArrayList<>();
		 IMailService mailService = new ExchangeMailService();
		// mailService.SendMail("", str, "","", "");
		 
	        return service.getAllApplications();
	    }
	 
	 
	 @JsonView(ViewProfile.Application.class)
//	 @Secured("ROLE_RM")
	 @GetMapping(value = "/user/{id}")
	    public List<Application> findByUser(@PathVariable final int id) {
	        return  service.getApplicationsByUser(id);
	    }
	 
	 
	 @GetMapping(value = "/{id}")
	 public Application findById(@PathVariable final Long id){
	    return service.getApplicationById(id);
	    }

	 
	 
	 @PostMapping(value = "/create")
	    public Application createApplication(@RequestBody final Application application) {	       
		 return service.saveNewApplication(application);         
	    }
	 
	 
	 @PutMapping(value = "/update")
	    public Application updateApplication(@RequestBody Application application) {	
	    return service.updateApplication(application);  
	    }
	 
	 @GetMapping(value = "/org/{id}")
	 	public List<Application> getAllApplicationByCurrentOrg(@PathVariable final long id){
		return service.getAllApplicationByCurrentOrg(id); 
	 	}
	 
	 @PostMapping(value="/{id}/user/{user}")
	 	public Application setApplicationCurrentUser(@PathVariable final long id, @PathVariable final long user){
		 return service.setApplicationCurrentUser(id, user);
	 }
	 
	 
	 @GetMapping(value="/podaci")
	 public String podaci(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String currentPrincipalName = authentication.getName();
		 
		 return currentPrincipalName;
	 }
	 
	 
	 @GetMapping(value = "/timeConsument/{id}")
	 public List<TimeConsumeWrapper> getTimeConsumentByPlacement(@PathVariable final Long id){
	    return service.getTimeConsumentByApplication(id);
	    }
	 
	 
	 @GetMapping(value = "/report1/{DateFromS}/{DateToS}")
	 public List<Report1> getReport1(@PathVariable final Long DateFromS, @PathVariable final Long DateToS ){
		
		 ///{DateFrom}/{DateTo}
		 //@PathVariable final Long DateFromS, @PathVariable final Long DateToS
		 
	//	 Logger logger = Logger.getLogger(PlacementService.class);
		 
		Date DateFrom = new Date(DateFromS);
		Date DateTo = new Date(DateToS);
		
	//	logger.error(DateFrom.toString() + " " + DateTo.toString());
		
	    return service.getReport1(DateFrom, DateTo);
	    
	    }
	 

	 @GetMapping(value = "/report2/{DateFromS}/{DateToS}")
	 public List<Report2> getReport2(@PathVariable final Long DateFromS, @PathVariable final Long DateToS ){
		
		Date DateFrom = new Date(DateFromS);
		Date DateTo = new Date(DateToS);
	
	    return service.getReport2(DateFrom, DateTo);
	    
	    }
	 
	 @GetMapping(value = "/report21/{DateFromS}/{DateToS}")
	 public List<Report21> getReport21(@PathVariable final Long DateFromS, @PathVariable final Long DateToS ){
		
		Date DateFrom = new Date(DateFromS);
		Date DateTo = new Date(DateToS);
	
	    return service.getReport21(DateFrom, DateTo);
	    
	    }
	 
	 @GetMapping(value = "/report3/{DateFromS}/{DateToS}")
	 public List<Report3> getReport3(@PathVariable final Long DateFromS, @PathVariable final Long DateToS ){
		
		Date DateFrom = new Date(DateFromS);
		Date DateTo = new Date(DateToS);
	
		Logger logger = Logger.getLogger(PlacementService.class);
		logger.error("datum:" + DateFrom.toString() + " " + DateTo.toString() );
		
	    return service.getReport3(DateFrom, DateTo);
	    
	    }
}
