package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.ClientBusinesPartner;
import com.unicredit.cap.repository.DbContext;

@Service
public class ClientBusinesPartnerService {

	@Autowired
	private DbContext db;
	
	
	public List<ClientBusinesPartner> getAllClientBusinesPartner()
	{		
		return db.ClientBusinesPartner().findAll();
	}
	
	public ClientBusinesPartner getClientByCodeNo(Long id)
	{
		
		Optional<ClientBusinesPartner> client = db.ClientBusinesPartner().findById(id);
		
		if(!client.isPresent())
			 throw new CapNotFoundException("Client with CoreNo=" + id + " was not found");
		
		return client.get();
		
	}
	
	public ClientBusinesPartner getClientByJib(String jib)
	{
		
		ClientBusinesPartner client = db.ClientBusinesPartner().getClientBusinesPartnerByJib(jib);
		
		if(client == null)
			 throw new CapNotFoundException("Client with JIB = " + jib + " was not found");
		
		return client;
		
	}
	
}
