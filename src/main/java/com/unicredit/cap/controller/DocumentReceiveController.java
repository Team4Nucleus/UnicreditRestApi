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

import com.unicredit.cap.busineslogic.DocumentReceiveService;
import com.unicredit.cap.busineslogic.DocumentService;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.DocumentReceive;

@RestController
@RequestMapping("/rest/documentreceive")
public class DocumentReceiveController {

	
	 @Autowired
	 private DocumentReceiveService service;
	
	 
	 @GetMapping(value = "/doc/{docid}")
	 public List<DocumentReceive> findByDocId(@PathVariable final Long docid){	
	    return service.getDocumentReceiveByDocumentId(docid);   
	 }
	 
	 
	 
	 @PostMapping(value = "/create")
	 public DocumentReceive createDocumentReceive(@RequestBody DocumentReceive doc){	
		 
	    return service.createDocumentReceive(doc);   
	 }
	 
	 
	 @PutMapping(value = "/cancel/{id}")
	 public DocumentReceive cancelDocumentReceive(@PathVariable final Long id){	
		 
		    return service.cancelDocumentReceive(id);
		 }
	 
}
