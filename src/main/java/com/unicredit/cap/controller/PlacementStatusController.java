package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.PlacementStatusService;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.PlacementStatus;
import com.unicredit.cap.repository.PlacementStatusRepository;

@RestController
@RequestMapping("/rest/placementstatus")
public class PlacementStatusController {

	
	 @Autowired
	 private PlacementStatusService service;
	 
	 @GetMapping(value = "/all")
	    public List<PlacementStatus> findAll() {
	        return service.getAllPlacementStatus();
	    }

	 @GetMapping(value = "/{id}")
	 public PlacementStatus findById(@PathVariable final Long id){
		
	    return service.getPlacementStatusById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	    public PlacementStatus createPlacementStatu(@RequestBody final PlacementStatus status) {	       
		return service.createNewPlacementStatus(status);         
	    }
	 
	 
}
