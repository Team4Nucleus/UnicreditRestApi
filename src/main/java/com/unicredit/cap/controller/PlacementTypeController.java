package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.PlacementType;
import com.unicredit.cap.repository.PlacementTypeRepository;

@RestController
@RequestMapping("/rest/placementtype")
public class PlacementTypeController {

	
	 @Autowired
	 private PlacementTypeRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<PlacementType> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<PlacementType> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
}
