package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicredit.cap.busineslogic.CurrencyService;
import com.unicredit.cap.busineslogic.PlacementService;
import com.unicredit.cap.model.Currency;
import com.unicredit.cap.model.Placement;

@RestController
@RequestMapping("/rest/currency")
public class CurrencyController {

	
	 @Autowired
	 private CurrencyService service;
	 

	 @GetMapping(value = "/all")
	    public List<Currency> findAll() {
	        return service.getAllCurrencies();
	    }

	 
	 @GetMapping(value = "/{id}")
	 public Currency findByCode(@PathVariable final String code){
	    return service.getCurrencyByCode(code);
	    }
	 
	 
	 @PostMapping(value = "/create")
	 public Currency createCurrency(@RequestBody final Currency currency) {       
	        return service.saveNewCurrency(currency);        
	    }
	 
	 
	 @PutMapping(value = "/update")
	 public Currency updateCurrency(@RequestBody final Currency currency) {
	        return service.updateCurrency(currency);    
	    }
	 
	
	
}
