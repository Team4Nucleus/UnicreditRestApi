package com.unicredit.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicredit.cap.busineslogic.TransferRulesService;
import com.unicredit.cap.model.TransferRules;

@RestController
@RequestMapping("/rest/transferrules")
public class TransferRulesController {

	 @Autowired
	 private TransferRulesService service;
	 
	 @GetMapping(value = "/all")
	    public List<TransferRules> findAll() {
	        return service.getAll();
	    }
	 
	 
	 @GetMapping(value = "/fromorg/{id}")
	 public List<TransferRules> findByFromOrg(@PathVariable final Long id){
		
	    return service.getAllByFromOrg(id);
	    }
	 
	 
	 @PostMapping(value = "/create")
	    public TransferRules createTransferRules(@RequestBody final TransferRules tr) {	       
		return service.create(tr);         
	    }
	
	 @PostMapping(value = "/delete")
	    public void deleteTransferRules(@RequestBody final TransferRules tr) {	       
		service.delete(tr);         
	    }
}
