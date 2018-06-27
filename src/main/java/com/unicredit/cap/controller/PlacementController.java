package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.Placement;
import com.unicredit.cap.repository.PlacementRepository;


@RestController
@RequestMapping("/rest/placement")
public class PlacementController {

	
	 @Autowired
	 private PlacementRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<Placement> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<Placement> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
}
