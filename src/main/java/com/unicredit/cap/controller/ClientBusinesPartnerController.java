package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.ClientBusinesPartnerService;
import com.unicredit.cap.model.ClientBusinesPartner;

@RestController
@RequestMapping("/rest/businespartner")
public class ClientBusinesPartnerController {

	
	 @Autowired
	 private ClientBusinesPartnerService service;
	 
	 @GetMapping(value = "/all")
	    public List<ClientBusinesPartner> findAll() {
	        return service.getAllClientBusinesPartner();
	    }

	 @GetMapping(value = "/{id}")
	 public ClientBusinesPartner findById(@PathVariable final Long id){
		
	    return service.getClientByCodeNo(id);
	    }
	 
	
	 @GetMapping(value = "/jib/{jib}")
	 public ClientBusinesPartner findByJib(@PathVariable final String jib){
		
	    return service.getClientByJib(jib);
	    }
}


