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
import com.unicredit.cap.busineslogic.ApplicationTransferService;
import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.ApplicationTransfer;

@RestController
@RequestMapping("/rest/applicationtransfer")
public class ApplicationTransferController {

	 @Autowired 
	 private ApplicationTransferService service;
	 
	 @GetMapping(value = "/application/{id}")
	    public List<ApplicationTransfer> getAllApplicationTransfersByApplication(@PathVariable final long id) {
	        return service.getAllApplicationTransfersByApplication(id);
	    }
	
	 @GetMapping(value = "/fromorg/{id}/{status}")
	    public List<ApplicationTransfer> getAllApplicationTransfersByFromOrgAndStatus(@PathVariable final long id, @PathVariable final String status) {
	        return service.getAllApplicationTransfersByFromOrgAndStatus(id,status);
	    }
	 
	 @GetMapping(value = "/toorg/{id}/{status}")
	    public List<ApplicationTransfer> getAllApplicationTransfersByToOrgAndStatus(@PathVariable final long id, @PathVariable final String status) {
	        return service.getAllApplicationTransfersByToOrgAndStatus(id,status);
	    }	 
	 
	 
	 @PostMapping(value = "/create/{id}/{s}")
	    public ApplicationTransfer createApplicationTransfer(@RequestBody final ApplicationTransfer appTransfer, @PathVariable final long id, @PathVariable final int s) {
	        return service.createApplicationTransfer(appTransfer, id, s);
	    }
	 
	 @PostMapping(value = "/{id}/status/{status}")
	    public ApplicationTransfer updateApplicationTransferStatus(@PathVariable final long id, @PathVariable final String status) {
	        return service.updateApplicationTransferStatus(status, id);
	    }
	 
	 
}
