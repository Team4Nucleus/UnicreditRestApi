package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.Application;
import com.unicredit.cap.model.ApplicationTransfer;


public interface ApplicationTransferRepository extends JpaRepository<ApplicationTransfer, Long> {

	
	@Query(value = "SELECT * FROM APPLICATIONTRANSFER WHERE APPLICATION = ? ORDER BY ID DESC",  nativeQuery = true)
	public List<ApplicationTransfer> getAllTransfersOfApplication(long app);

	@Query(value = "SELECT * FROM APPLICATIONTRANSFER WHERE FROM_ORG = ? AND STATUS = ? ORDER BY ID DESC",  nativeQuery = true)
	public List<ApplicationTransfer> getAllApplicationTransfersByFromOrgAndStatus(long from, String status);

	@Query(value = "SELECT * FROM APPLICATIONTRANSFER WHERE TO_ORG = ? AND STATUS = ? ORDER BY ID DESC",  nativeQuery = true)
	public List<ApplicationTransfer> getAllApplicationTransfersByToOrgAndStatus(long to, String status);
	
}



