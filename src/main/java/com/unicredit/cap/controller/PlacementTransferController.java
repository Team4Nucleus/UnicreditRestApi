package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.PlacementTransferService;
import com.unicredit.cap.model.PlacementTransfer;

@RestController
@RequestMapping("/rest/placementtransfer")
public class PlacementTransferController {

	 @Autowired
	 private PlacementTransferService service;
	 
	 @GetMapping(value = "/all")
	    public List<PlacementTransfer> findAll() {
	        return service.getAllPlacementTransfer();
	    }

	 @GetMapping(value = "/{id}")
	 public PlacementTransfer findById(@PathVariable final Long id){
		
	    return service.getPlacementTransferById(id);
	    }
	 
	 
	 @GetMapping(value = "/placement/{id}")
	 public List<PlacementTransfer> findByPlacementId(@PathVariable final Long id){
		
	    return service.getAllPlacementTransferByPlacement(id);
	    }
	
	 @PostMapping(value = "/create/{id}")
	    public PlacementTransfer createPlacementTransfer(@RequestBody final PlacementTransfer placementTransfer, @PathVariable final Long id) {	       
		 return service.createNewPlacementTransfer(placementTransfer, id, true);       
	    }
	 
}
