package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.repository.PlacementTransferRepository;

@RestController
@RequestMapping("/rest/placementtransfer")
public class PlacementTransferController {

	 @Autowired
	 private PlacementTransferRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<PlacementTransfer> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<PlacementTransfer> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
	
	
}
