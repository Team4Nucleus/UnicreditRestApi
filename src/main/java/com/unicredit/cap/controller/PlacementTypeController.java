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

import com.unicredit.cap.busineslogic.PlacementTypeService;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.PlacementType;
import com.unicredit.cap.repository.PlacementTypeRepository;

@RestController
@RequestMapping("/rest/placementtype")
public class PlacementTypeController {

	
	 @Autowired
	 private PlacementTypeService service;
	 
	 
	 @GetMapping(value = "/all")
	    public List<PlacementType> findAll() {
	        return service.getAllPlacementType();
	    }

	 
	 @GetMapping(value = "/{id}")
	 public PlacementType findById(@PathVariable final Long id){
		
	    return service.getPlacementTypeById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	    public PlacementType createPlacementType(@RequestBody final PlacementType placementType) {	       
		 return service.createNewPlacementType(placementType);         
	    }
	 
	 
}
