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

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.busineslogic.PlacementService;
import com.unicredit.cap.model.Placement;



@RestController
@RequestMapping("/rest/placement")
public class PlacementController {


	 @Autowired
	 private PlacementService service;
	 
	 @JsonView(Placement.class)
	 @GetMapping(value = "/all")
	    public List<Placement> findAll() {
	        return service.getAllPlacements();
	    }

	 
	 @GetMapping(value = "/{id}")
	 public Placement findById(@PathVariable final Long id){
	    return service.getPlacementById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	 public Placement createPlacement(@RequestBody final Placement placement) {       
	        return service.saveNewPlacement(placement);        
	    }
	 
	 
	 @PutMapping(value = "/update")
	 public Placement updatePlacement(@RequestBody final Placement placement) {
	        return service.updatePlacement(placement);    
	    }
	 
	 
}
