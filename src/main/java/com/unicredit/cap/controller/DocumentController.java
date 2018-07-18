package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.unicredit.cap.busineslogic.DocumentService;
import com.unicredit.cap.model.Document;


@RestController
@RequestMapping("/rest/document")
public class DocumentController {


	 @Autowired
	 private DocumentService service;
	 
	 @GetMapping(value = "/all")
	    public List<Document> findAll() {
	        return service.getAllDocuments();
	    }

	 
	 @GetMapping(value = "/{id}")
	 public Document findById(@PathVariable final Long id){	
	    return service.getDocumentById(id); 
	 }
	 
	 
	 @GetMapping(value = "/application/{id}")
	 public List<Document> findByApplicationId(@PathVariable final Long id){	
	    return service.getDocumentsByApplication(id);   
	 }
	 
	 @GetMapping(value = "/placement/{id}")
	 public List<Document>  findByPlacementId(@PathVariable final Long id){		
	    return service.getDocumentsByPlacement(id);
	 }
	 
	 @PostMapping(value = "/create/application/{id}")
	 public Document  createDocumentInApplication (@RequestBody Document document, @PathVariable final Long id){		
		    return service.createDocumentInApplication(document, id);
		 }
	
	 @PostMapping(value = "/create/placement/{id}")
	 public Document  createDocumentInPlacement (@RequestBody Document document, @PathVariable final Long id){		
		    return service.createDocumentInPlacement(document, id);
		 }
	 
	 
	 @PostMapping(value = "/upload")
	 public ResponseEntity<?>  uploadDocument ( @RequestParam("idPlacement") Long idPlacement, @RequestParam("files")  MultipartFile[] uploadFiles){		
		    return service.uploadDocumentInPlacement(idPlacement, uploadFiles);
		 }
	 
}
