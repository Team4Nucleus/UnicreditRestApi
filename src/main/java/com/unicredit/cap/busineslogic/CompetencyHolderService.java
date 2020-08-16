package com.unicredit.cap.busineslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.CompetencyHolder;
import com.unicredit.cap.model.Currency;
import com.unicredit.cap.repository.DbContext;

@Service
public class CompetencyHolderService {

	
	@Autowired
	private DbContext db;
	
	
	public List<CompetencyHolder> getAll(){
		
		return db.CompetencyHolder().findAll();
	}
	
	
	public CompetencyHolder getById(Long id) {
		
		CompetencyHolder ch = db.CompetencyHolder().findOne(id);
		
		if (ch == null)
			throw new CapNotFoundException("Competency Holder with id=" + id + " was not found");   
		
		return ch;
	}
	
	
	public CompetencyHolder createNew(CompetencyHolder competencyHolder) {
		
		if (competencyHolder.getName().equals("") || competencyHolder.getOrg().equals(""))
		throw new CapNotFoundException("Please set the competency holder name and org");
		
		db.CompetencyHolder().save(competencyHolder);
		return competencyHolder;
	}
	
	public CompetencyHolder updateHolder (CompetencyHolder competencyHolder) {
		
		CompetencyHolder ch = db.CompetencyHolder().findOne(competencyHolder.getId());
		if (ch == null)
			throw new CapNotFoundException("Competency Holder with id=" + competencyHolder.getId() + " was not found");   
		
		ch.setName(competencyHolder.getName());
		ch.setOrg(competencyHolder.getOrg());
		
		db.CompetencyHolder().save(ch);
		return ch;
	}
	
}
