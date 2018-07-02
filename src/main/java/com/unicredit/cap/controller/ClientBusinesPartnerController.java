package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.ClientBusinesPartner;
import com.unicredit.cap.repository.ClientBusinesPartnerRepository;

@RestController
@RequestMapping("/rest/businespartner")
public class ClientBusinesPartnerController {

	
	 @Autowired
	 private ClientBusinesPartnerRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<ClientBusinesPartner> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<ClientBusinesPartner> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
}
