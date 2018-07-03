package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.model.Document;
import com.unicredit.cap.repository.DocumentRepository;

@RestController
@RequestMapping("/rest/document")
public class DocumentController {


	 @Autowired
	 private DocumentRepository repository;
	 
	 @GetMapping(value = "/all")
	    public List<Document> findAll() {
	        return repository.findAll();
	    }

	 @GetMapping(value = "/{id}")
	 public Optional<Document> findById(@PathVariable final Long id){
		
	    return repository.findById(id);
	    }
	
}