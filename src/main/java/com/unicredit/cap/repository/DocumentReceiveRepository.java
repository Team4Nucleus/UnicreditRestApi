package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.DocumentReceive;

public interface DocumentReceiveRepository extends JpaRepository<DocumentReceive, Long> {

	
	@Query(value = "SELECT * FROM DOCUMENTRECEIVE WHERE DOCUMENT = ? AND ACTIVE = 1 ORDER BY RECEIVEDATE DESC",  nativeQuery = true)
	List<DocumentReceive> getByDocumentId(Long docId);
	

}
