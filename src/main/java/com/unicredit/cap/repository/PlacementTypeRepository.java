package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.PlacementType;


public interface PlacementTypeRepository extends JpaRepository<PlacementType, Long> {

	@Query(value = "SELECT * FROM PLACEMENTTYPE WHERE CATEGORY = ?",  nativeQuery = true)
	List<PlacementType> findByCategory(Long id);

	
}
