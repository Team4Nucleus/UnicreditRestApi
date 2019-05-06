package com.unicredit.cap.busineslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.repository.DbContext;
import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.PlacementTypeCategory;;

@Service
public class PlacementTypeCategoryService {

	@Autowired
	private DbContext db;
	
	
	public List<PlacementTypeCategory> getAll()
	{
		return db.PlacementTypeCategory().findAll();
	}
	
	public PlacementTypeCategory getById(Long id)
	{
		return db.PlacementTypeCategory().findOne(id);
	}
	
	public PlacementTypeCategory create(PlacementTypeCategory ptc)
	{
		if (ptc.getName().equals("") || ptc.getName() == null)
			throw new CapNotFoundException("Name must not be empty");
		
		db.PlacementTypeCategory().save(ptc);
		
		return ptc;
	}
	
	
}
