package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.Placement;
import com.unicredit.cap.model.PlacementTimeConsument;


public interface PlacementRepository extends JpaRepository<Placement, Long> {

	
}

