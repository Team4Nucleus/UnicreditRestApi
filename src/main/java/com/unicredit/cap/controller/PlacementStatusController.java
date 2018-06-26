package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.PlacementStatus;
import com.unicredit.cap.repository.PlacementStatusRepository;

@RestController
@RequestMapping("/rest/placementstatus")
public class PlacementStatusController {

	
	 @Autowired
	 private PlacementStatusRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<PlacementStatus> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<PlacementStatus> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
}
