package com.unicredit.cap.busineslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.model.TransferRules;
import com.unicredit.cap.repository.DbContext;

@Service
public class TransferRulesService {

	@Autowired
	private DbContext db;
	
	
	public List<TransferRules> getAll(){
		
		return db.TransferRules().findAll();
	}
	
	public List<TransferRules> getAllByFromOrg(Long id){
		
		return db.TransferRules().findAllByFromOrg(id);
	}
	
	public TransferRules create(TransferRules tr)
	{
		db.TransferRules().save(tr);
		
		return tr;
	}
	
	public void delete(TransferRules tr)
	{
		db.TransferRules().delete(tr.getID());
		
	}
	
}
