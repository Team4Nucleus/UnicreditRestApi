package com.unicredit.cap.busineslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicredit.cap.exception.CapNotFoundException;
import com.unicredit.cap.model.DocumentType;
import com.unicredit.cap.model.Holiday;
import com.unicredit.cap.repository.DbContext;

@Service
public class HolidayService {

	
	@Autowired
	private DbContext db;
	
	public Holiday getHolidaybyId(Long id)
	{
		Holiday holiday = db.Holiday().findOne(id);
		
		if(holiday == null)
			 throw new CapNotFoundException("Holiday with id=" + id + " was not found");
		
		return holiday;
		
	}
	
	public List<Holiday> getAllHolidays()
	{	
		return db.Holiday().findAll();	
	}
	
	
	
	public Holiday createNewHoliday(Holiday holiday)
	{	
		List<Holiday> h = db.Holiday().findHolideyByDate(holiday.getHolidateDate());
		if (h.isEmpty())
		{
			db.Holiday().save(holiday);	
			return holiday;
		}
		else
		{
			 throw new CapNotFoundException("Holiday with date " + holiday.getHolidateDate() + " already in databse");
		}
	}
	
	public Holiday updateHoliday(Holiday holiday)
	{	
		List<Holiday> h = db.Holiday().findHolideyByDateAndOtherId(holiday.getHolidateDate(), holiday.getId());
		if (h.isEmpty())
		{
			db.Holiday().save(holiday);	
			return holiday;
		}
		else
		{
			 throw new CapNotFoundException("Holiday with date " + holiday.getHolidateDate() + " already in databse");
		}
	}
	
	public List<Holiday> GetAllHolidaysByYear(int year)
	{
		
		return db.Holiday().findHolideyByYear(year);
	}
	
	
	
}
