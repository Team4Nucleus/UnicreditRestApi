package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.Organization;
import com.unicredit.cap.repository.OrganizationRepository;

@RestController
@RequestMapping("/rest/organization")
public class OrganizationController {

	 @Autowired
	 private OrganizationRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<Organization> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<Organization> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
	
}
