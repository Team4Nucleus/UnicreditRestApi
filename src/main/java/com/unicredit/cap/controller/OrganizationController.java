package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.OrganizationService;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.model.Placement;


@RestController
@RequestMapping("/rest/organization")
public class OrganizationController {

	 @Autowired
	 private OrganizationService service;
	 
	 @GetMapping(value = "/all")
	    public List<Organization> findAll() {
	        return service.getAllOrganization();
	    }

	 @GetMapping(value = "/{id}")
	 public Organization findById(@PathVariable final Long id){
		
	    return service.getOrganizationById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	 public Organization createOrganization(@RequestBody final Organization organization) {
	        return service.createOrganization(organization);    
	    }
	 
	 
	 @PostMapping(value = "/update")
	 public Organization updateOrganization(@RequestBody final Organization organization) {
	        return service.updateOrganization(organization);    
	    }
	
}
