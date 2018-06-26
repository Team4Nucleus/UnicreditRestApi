package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.DocumentType;
import com.unicredit.cap.repository.DocumentTypeRepository;

@RestController
@RequestMapping("/rest/documenttype")
public class DocumentTypeController {

	 @Autowired
	 private DocumentTypeRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<DocumentType> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<DocumentType> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
	
}
