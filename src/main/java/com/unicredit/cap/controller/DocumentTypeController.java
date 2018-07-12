package com.unicredit.cap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.DocumentTypeService;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.DocumentType;
import com.unicredit.cap.repository.DocumentTypeRepository;

@RestController
@RequestMapping("/rest/documenttype")
public class DocumentTypeController {

	 @Autowired
	 private DocumentTypeService service;
	 
	 @GetMapping(value = "/all")
	    public List<DocumentType> findAll() {
	        return service.getAllDocumentType();
	    }

	 @GetMapping(value = "/{id}")
	 public DocumentType findById(@PathVariable final Long id){
		
	    return service.getDocumentTypebyId(id);
	    }
	
	 
	 @PostMapping(value = "/create")
	    public DocumentType createDocumentType(@RequestBody final DocumentType docType) {	       
		 return service.createNewDocumentType(docType);         
	    }
	 
	 
}
