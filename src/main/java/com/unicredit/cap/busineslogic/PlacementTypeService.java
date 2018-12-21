package com.unicredit.cap.busineslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.PlacementType;
import com.unicredit.cap.repository.DbContext;

@Service
public class PlacementTypeService {


	@Autowired
	private DbContext db;
	
	
	public PlacementType getPlacementTypeById(Long id)
	{
		PlacementType placementType = db.PlacementType().findOne(id);
		
		if(placementType == null)
			 throw new CapNotFoundException("Placement Type with id=" + id + " was not found");
		
		return placementType;
	}
	
	
	public List<PlacementType> getAllPlacementType(){
		
		return db.PlacementType().findAll();
		
	}
	
	public PlacementType createNewPlacementType (PlacementType placementType){
		
		db.PlacementType().save(placementType);
		
		return placementType;
	}
}
