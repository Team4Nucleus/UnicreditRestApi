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

import com.unicredit.cap.busineslogic.CompetencyHolderService;
import com.unicredit.cap.model.CompetencyHolder;


@RestController
@RequestMapping("/rest/competencyHolder")
public class CompetencyHolderController {

	 @Autowired
	 private CompetencyHolderService service;
	 
	 @GetMapping(value = "/all")
	    public List<CompetencyHolder> findAll() {
	        return service.getAll();
	    }

	 
	 @GetMapping(value = "/{id}")
	 public CompetencyHolder findByCode(@PathVariable final Long id){
	    return service.getById(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	 public CompetencyHolder createCompetencyHolder(@RequestBody final CompetencyHolder competencyHolder) {       
	        return service.createNew(competencyHolder); 
	    }
	 
	 
	 @PutMapping(value = "/update")
	 public CompetencyHolder updateCompetencyHolder(@RequestBody final CompetencyHolder competencyHolder) {
	        return service.updateHolder(competencyHolder);    
	    }
	 
}
