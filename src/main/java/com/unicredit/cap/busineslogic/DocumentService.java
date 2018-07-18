package com.unicredit.cap.busineslogic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.MailService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

	@Autowired
	private DbContext db;
	
	private MailService mailService = new ExchangeMailService();
	
	
	public Document getDocumentById(Long id)
	{
		
		Optional<Document> doc = db.Document().findById(id);
		
		if(!doc.isPresent())
			throw new CapNotFoundException("Document with id=" + id + " was not found");
		
		return doc.get();
	}
	
	
	public List<Document> getAllDocuments()
	{
		
		return db.Document().findAll();
	}
	
	public List<Document> getDocumentsByApplication(Long id)
	{
		
		// dodati kad se poveze u modelu
		//db.Application().findById(id)
		throw new CapNotFoundException("Not implemented in REST yet");

	}
	
	
	public List<Document> getDocumentsByPlacement(Long id)
	{
		
		Optional<Placement> plac = db.Placement().findById(id);
		
		if (!plac.isPresent())
		throw new CapNotFoundException("Placement with id="+id+" was not found!");
		
		return plac.get().getDocuments();

	}
	
	
	public Document createDocumentInApplication(Document document, Long id)
	{
		throw new CapNotFoundException("Not implemented in REST yet");
	}
	
	
	public Document createDocumentInPlacement(Document document, Long id)
	{
		Optional<Placement> plac = db.Placement().findById(id);
		
		if(!plac.isPresent())
		throw new CapNotFoundException("Placement with id="+id+" was not found!");
		
		document.setPlacement(plac.get());
		
		db.Document().save(document);
		
		return document;
		
	}
	
	
	 private void saveUploadedFiles(List<MultipartFile> files, Long idPlacement) throws IOException {

	        for (MultipartFile file : files) {

	            if (file.isEmpty()) {
	                continue; 
	            }

	            
	            File directory = new File("C:/Documents/"+idPlacement);
	            if (! directory.exists()){
	                directory.mkdir();
	            }
	            
	            
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(directory+"/"+file.getOriginalFilename());
	            Files.write(path, bytes);

	        }

	    }


	public ResponseEntity<?> uploadDocumentInPlacement(Long idPlacement, MultipartFile[] uploadFiles) {
		// TODO Auto-generated method stub
		
		 String uploadedFileName = Arrays.stream(uploadFiles).map(x -> x.getOriginalFilename())
	                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

	        if (StringUtils.isEmpty(uploadedFileName)) {
	        	 throw new CapNotFoundException("File was not selected: " );
	        }

	        try {

	            saveUploadedFiles(Arrays.asList(uploadFiles), idPlacement);

	        } catch (IOException e) {
	        	 throw new CapNotFoundException("Error saving file: " + e.getMessage());
	        }
		
		return new ResponseEntity("Successfully uploaded - "+ uploadedFileName, HttpStatus.OK);
		
	}
	
}
