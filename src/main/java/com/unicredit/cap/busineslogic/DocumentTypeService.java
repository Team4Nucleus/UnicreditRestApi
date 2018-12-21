package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.DocumentType;
import com.unicredit.cap.repository.DbContext;

@Service
public class DocumentTypeService {

	@Autowired
	private DbContext db;
	
	
	public DocumentType getDocumentTypebyId(Long id)
	{
		DocumentType doctype = db.Documenttype().findOne(id);
		
		if(doctype == null)
			 throw new CapNotFoundException("Document Type with id=" + id + " was not found");
		
		return doctype;
		
	}
	
	public List<DocumentType> getAllDocumentType()
	{	
		return db.Documenttype().findAll();	
	}
	
	
	
	public DocumentType createNewDocumentType(DocumentType docType)
	{	
		db.Documenttype().save(docType);	
		return docType;
	}
}
