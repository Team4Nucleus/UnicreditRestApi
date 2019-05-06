package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.TransferRules;

public interface TransferRulesRepository extends JpaRepository<TransferRules, Long> {

	@Query(value = "SELECT * FROM TRANSFERRULES WHERE FROMORG = ?",  nativeQuery = true)
	List<TransferRules> findAllByFromOrg(Long id);

}
