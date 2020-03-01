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
import com.unicredit.cap.helper.TimeConsumeWrapper;
import com.unicredit.cap.helper.ViewProfile;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTimeConsument;



@RestController
@RequestMapping("/rest/placement")
public class PlacementController {


	 @Autowired
	 private PlacementService service;
	 
	 @JsonView(ViewProfile.Placement.class)
	 @GetMapping(value = "/all")
	    public List<Placement> findAll() {
	        return service.getAllPlacements();
	    }

	 
	 @GetMapping(value = "/{id}")
	 public Placement findById(@PathVariable final Long id){
	    return service.getPlacementById(id);
	    }
	 
	 
	 @PostMapping(value = "/create/{id}")
	 public Placement createPlacement(@RequestBody final Placement placement, @PathVariable final Long id) {       
	        return service.saveNewPlacement(placement, id);        
	    }
	 
	 
	 @PutMapping(value = "/update")
	 public Placement updatePlacement(@RequestBody final Placement placement) {
	        return service.updatePlacement(placement);    
	    }
	 
	 @GetMapping(value = "/timeConsument/{id}")
	 public List<TimeConsumeWrapper> getTimeConsumentByPlacement(@PathVariable final Long id){
	    return service.getTimeConsumentByPlacement(id);
	    }
	 
	 @GetMapping(value = "/{id}/status/{IdStatus}")
	 public Placement changeStatus(@PathVariable final Long id, @PathVariable final int IdStatus )
	 {
		Placement placement = service.UpdateStatus(id, IdStatus);
		return placement;		 
	 }
	 	 
	 @PostMapping(value = "/{id}/user/{user}")
	 public Placement setPlacementCurrentUser(@PathVariable final Long id, @PathVariable final Long user) {
	        return service.setPlacementCurrentUser(id, user);    
	    }
	 
	 
	 @GetMapping(value = "/{id}/opinionAndDecision/{OKR}/{NBCO}/{decision}")
	 public Placement opinionAndDecision(@PathVariable final Long id, @PathVariable final String OKR, @PathVariable final String NBCO, 
			 @PathVariable final String decision)
	 {
		Placement placement = service.SetOpinionAndDecision(id, OKR, NBCO, decision);
		return placement;		 
	 }
	 
	 
	 @GetMapping(value = "/{id}/setApprovedAmount/{amount}")
	 public Placement SetApprovedAmount(@PathVariable final Long id, @PathVariable final Double amount )
	 {
		Placement placement = service.SetApprovedAmount(id, amount);
		return placement;		 
	 }
	 	 
	 
}
