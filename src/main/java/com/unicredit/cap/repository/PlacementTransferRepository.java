package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.TaskDetail;

public interface PlacementTransferRepository extends JpaRepository<PlacementTransfer, Long> {

	
	@Query(value = "SELECT * FROM PLACEMENTTRANSFER WHERE PLACEMENT = ? AND ROWNUM = 1 ORDER BY DATE_FROM DESC",  nativeQuery = true)
	public PlacementTransfer getLastTransferOfPlacement(Long id);
}
