package com.unicredit.cap.controller;

import java.io.IOException;
import java.security.Principal;
import org.apache.commons.codec.binary.Base64;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicredit.cap.busineslogic.PlacementService;
import com.unicredit.cap.helper.TimeConsumeWrapper;
import com.unicredit.cap.helper.ViewProfile;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTimeConsument;

import io.jsonwebtoken.Claims;



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
	 	 
	 
	 @GetMapping(value = "/user")
	 public String GetUser()
	 {
		 
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		    String jwtToken = request.getHeader("Authorization");
		    
		    String[] split_string = jwtToken.split("\\.");
	        String base64EncodedHeader = split_string[0];
	        String base64EncodedBody = split_string[1];
	        String base64EncodedSignature = split_string[2];


	        Base64 base64Url = new Base64(true);
	        String header = new String(base64Url.decode(base64EncodedHeader));
	        String body = new String(base64Url.decode(base64EncodedBody));
	        
	        try {
	        	ObjectMapper mapper = new ObjectMapper();
	        
				JsonNode rootNode = mapper.readTree(body);
				
				return rootNode.get("sub").asText();
				
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return "";
			
	 }
		
	 
}
