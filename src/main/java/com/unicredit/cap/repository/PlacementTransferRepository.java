package com.unicredit.cap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.PlacementTransfer;
import com.unicredit.cap.model.TaskDetail;

public interface PlacementTransferRepository extends JpaRepository<PlacementTransfer, Long> {

	
	@Query(value = "SELECT A.* FROM "
			+ "( SELECT MAX(ID) AS ID FROM PLACEMENTTRANSFER WHERE PLACEMENT = ? ) B "
			+ "JOIN PLACEMENTTRANSFER A on A.ID = B.ID",  nativeQuery = true)
	public PlacementTransfer getLastTransferOfPlacement(Long id);
}



