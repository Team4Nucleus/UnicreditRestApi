package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.service.ExchangeMailService;
import com.unicredit.cap.service.MailService;

@Service
public class PlacementTransferService {

	
	@Autowired
	private DbContext db;
	private MailService mailService = new ExchangeMailService();
	
	
	public PlacementTransfer getPlacementTransferById(Long id){
		
		Optional<PlacementTransfer> placementTransfer = db.PlacementTransfer().findById(id);
		
		if(!placementTransfer.isPresent())
			 throw new CapNotFoundException("Placement Transfer with id=" + id + " was not found");
		
		return placementTransfer.get();
		
	}
	
	
	public List<PlacementTransfer> getAllPlacementTransfer(){
		
		return db.PlacementTransfer().findAll();
	}
	
	
	public List<PlacementTransfer> getAllPlacementTransferByPlacement(Long id){
		
		Optional<Placement> plac = db.Placement().findById(id);
		if(!plac.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		return plac.get().getTransfers();
		
	}
	
	
	public PlacementTransfer createNewPlacementTransfer(PlacementTransfer placementTransfer, Long id){
		
		Optional<Placement> plac = db.Placement().findById(id);
		if(!plac.isPresent())
			 throw new CapNotFoundException("Placement with id=" + id + " was not found");
		
		placementTransfer.setPlacement(plac.get());
		
		db.PlacementTransfer().save(placementTransfer);
		
		return placementTransfer;
	}
	
}
