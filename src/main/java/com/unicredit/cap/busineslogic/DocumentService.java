package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Document;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.MailService;

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
	
}
