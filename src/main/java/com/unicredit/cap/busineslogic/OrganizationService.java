package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Organization;
import com.unicredit.cap.repository.DbContext;

@Service
public class OrganizationService {

	@Autowired
	private DbContext db;
	
	
	public Organization getOrganizationById(Long id){
		
		Optional<Organization> organization = db.Orgnaization().findById(id);
		
		if(organization.isPresent())
			 throw new CapNotFoundException("Organization with id=" + id + " was not found");
		
		return organization.get();
	}
	
	
	public List<Organization> getAllOrganization(){
		
		return db.Orgnaization().findAll();
	}
	
}