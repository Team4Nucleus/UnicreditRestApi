package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.HolidayService;
import com.unicredit.cap.model.DocumentType;
import com.unicredit.cap.model.Holiday;

@RestController
@RequestMapping("/rest/holiday")
public class HolidayController {

	 @Autowired
	 private HolidayService service;
	
	 
	 @GetMapping(value = "/all")
	    public List<Holiday> findAll() {
	        return service.getAllHolidays();
	    }

	 
	 @GetMapping(value = "/year/{year}")
	    public List<Holiday> findAllByYear(@PathVariable final int year) {
	        return service.GetAllHolidaysByYear(year);
	    }
	 
	 @GetMapping(value = "/{id}")
	 public Holiday findById(@PathVariable final Long id){
		
	    return service.getHolidaybyId(id);
	    }
	
	 
	 
	 @PostMapping(value = "/create")
	    public Holiday createHoliday(@RequestBody final Holiday holiday) {	       
		 return service.createNewHoliday(holiday);         
	    }
	 
	 @PostMapping(value = "/update")
	    public Holiday updateHoliday(@RequestBody final Holiday holiday) {	       
		 return service.updateHoliday(holiday);         
	    }
	 
}
