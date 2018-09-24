package com.unicredit.cap.busineslogic;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.IMailService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;

@Service
public class DocumentService {

	@Autowired
	private DbContext db;
	
	@Autowired
	private Environment env;
	
	private IMailService mailService = new ExchangeMailService();
	
	
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

	            
	            File directory = new File(env.getProperty("document.location")+idPlacement);
	            if (! directory.exists()){
	                directory.mkdir();
	            }
	            
	            
	            
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(directory+"/"+file.getOriginalFilename());
	            Files.write(path, bytes);


	        }

	    }


	public ResponseEntity<?> uploadDocumentInPlacement(Long idPlacement, MultipartFile[] uploadFiles, int type, int attachUser, String fileType) {
		// TODO Auto-generated method stub
		
		Optional<Placement> plac = db.Placement().findById(idPlacement);
		
		if(!plac.isPresent())
		throw new CapNotFoundException("Placement with id="+idPlacement+" was not found!");
		
		 String uploadedFileName = Arrays.stream(uploadFiles).map(x -> x.getOriginalFilename())
	                .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

	        if (StringUtils.isEmpty(uploadedFileName)) {
	        	 throw new CapNotFoundException("File was not selected: " );
	        }

	        try {
        	
	            saveUploadedFiles(Arrays.asList(uploadFiles), idPlacement);
	            
	            Document document = new Document();
	            document.setAttachUser(attachUser);
	            document.setType(type);
	            document.setOriginal(0);
	            document.setName(uploadFiles[0].getOriginalFilename());
	            document.setPath(env.getProperty("document.location")+idPlacement+"/"+uploadFiles[0].getOriginalFilename());
	            document.setFileType(fileType);
	            document.setPlacement(plac.get());
	            
	            Path path = Paths.get(env.getProperty("document.location")+idPlacement+"/"+uploadFiles[0].getOriginalFilename());
	            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
	            FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
	            
	            document.setMetaDate("Created: "+ attr.creationTime() + ", Uploaded: " + new Date());
	            document.setMetaName(uploadFiles[0].getOriginalFilename());	 
	            document.setMetaSize(attr.size() + " bytes");
	            document.setMetaUSer(ownerAttributeView.getOwner().getName());	
	            
	            db.Document().save(document);
	            
	        } catch (IOException e) {
	        	 throw new CapNotFoundException("Error saving file: " + e.getMessage());
	        }
		
		return new ResponseEntity("Successfully uploaded - "+ uploadedFileName, HttpStatus.OK);
		
	}

	
	public ResponseEntity<ByteArrayResource> getFile(Long id){
		
		 Optional<Document> document = db.Document().findById(id);
		 
		 if(!document.isPresent())
				throw new CapNotFoundException("Document with id="+id+" was not found!");
		 
		 	try {
		 
	     Path path = Paths.get(document.get().getPath());
	     File file = new File(document.get().getPath());
	     String contentType = Files.probeContentType(path);
	     if(contentType == null) {
	    	 contentType = "application/octet-stream";
	        }
	    
	     ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
	
	     return ResponseEntity.ok()
	             .contentLength(file.length())
	             .contentType(MediaType.parseMediaType(contentType))
	             .body(resource);	
	     
		} catch (IOException e) {
			throw new CapNotFoundException("Error: " + e.getMessage());
			
		}
	     
	}
	
	public void deleteDocument(Long id) {
		Optional<Document> documentOp = db.Document().findById(id);
		
		if(!documentOp.isPresent())
			throw new CapNotFoundException("Document with id="+id+" was not found!");
		
		db.Document().delete(documentOp.get());

	}
	
}
