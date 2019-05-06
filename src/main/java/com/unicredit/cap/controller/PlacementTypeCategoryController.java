package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.PlacementTypeCategoryService;
import com.unicredit.cap.model.PlacementTypeCategory;

@RestController
@RequestMapping("/rest/placementtypecategory")
public class PlacementTypeCategoryController {

	
	 @Autowired
	 private PlacementTypeCategoryService service;
	 
	 
	 @GetMapping(value = "/all")
	    public List<PlacementTypeCategory> findAll() {
	        return service.getAll();
	    }

	 
	 @GetMapping(value = "/{id}")
	 public PlacementTypeCategory findById(@PathVariable final Long id){
		
	    return service.getById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	    public PlacementTypeCategory createPlacementType(@RequestBody final PlacementTypeCategory placementTypeCategory) {	       
		 return service.create(placementTypeCategory);         
	    }
	 
	 @PostMapping(value = "/update")
	    public PlacementTypeCategory updatePlacementType(@RequestBody final PlacementTypeCategory placementTypeCategory) {	       
		 return service.create(placementTypeCategory);         
	    }
}
