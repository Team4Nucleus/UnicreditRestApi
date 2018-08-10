package com.unicredit.cap.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	 
	 @PostMapping(value = "/delete/{id}")
	 public ResponseEntity<String>  deleteDocument (@PathVariable final Long id){		
		    service.deleteDocument(id);
		    
		    return new ResponseEntity<String>("Document successfully deleted",HttpStatus.OK);
		 }
	 
	 @PostMapping(value = "/upload")
	 public ResponseEntity<?>  uploadDocument ( 
			 @RequestParam("idPlacement") Long idPlacement, 
			 @RequestParam("files")  MultipartFile[] uploadFiles,
			 @RequestParam("type") int type,
			 @RequestParam("fileType") String fileType,
			 @RequestParam("attachUser") int attachUser){
		 
		    return service.uploadDocumentInPlacement(idPlacement, uploadFiles, type, attachUser, fileType);
		    
		 }
	 
	 
	 @GetMapping(value = "/download/{id}")
	 public ResponseEntity<ByteArrayResource> downloadFile (@PathVariable final Long id ) {
		 return service.getFile(id);
	 }
	 
	 
	 @GetMapping(value = "/download2/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 
			public @ResponseBody byte[] downloadFile2() {
		 
			    InputStream in = getClass().getResourceAsStream("C:/Documents/43/test.pdf");
			    try {
					return IOUtils.toByteArray(in);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					return null;
				}
			}
	 
}
