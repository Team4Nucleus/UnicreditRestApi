package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.PlacementStatus;
import com.unicredit.cap.repository.DbContext;

@Service
public class PlacementStatusService {


	@Autowired
	private DbContext db;
	
	public PlacementStatus getPlacementStatusById(Long id){
		
		Optional<PlacementStatus> placementStatus = db.PlacementStatuse().findById(id);
		
		if(placementStatus.isPresent())
			throw new CapNotFoundException("Placement Status with id=" + id + " was not found");
		
		return placementStatus.get();
	}
	
	
	public List<PlacementStatus> getAllPlacementStatus(){
		
		return db.PlacementStatuse().findAll();
		
	}
	
	
	public PlacementStatus createNewPlacementStatus(PlacementStatus status)
	{
		db.PlacementStatuse().save(status);
		return status;
	}
}
