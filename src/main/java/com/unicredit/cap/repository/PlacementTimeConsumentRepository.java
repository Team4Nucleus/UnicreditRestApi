package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.PlacementTimeConsument;

public interface PlacementTimeConsumentRepository extends JpaRepository<PlacementTimeConsument, Long>{

	
	@Query(value = "Select p.APPLICATION, p.ID as PLACEMENT, pl.TO_ORG as ORG_ID, org.NAME, SUM (extract(day from 24*60*(NVL(pl.DATE_TO, systimestamp) - pl.DATE_FROM))) as UKUPNO  " 
			+ " from PLACEMENTTRANSFER pl "
			+ " JOIN PLACEMENT p on p.ID = pl.PLACEMENT "
			+ " JOIN HRORGANIZATION org on org.ID = pl.TO_ORG "
			+ " where p.ID = ? "
			+ " group by p.APPLICATION, p.ID, pl.TO_ORG, org.NAME ",  nativeQuery = true)
	public List<PlacementTimeConsument> getTimeConsumentByPlacement(Long id);
	
	
	
	@Query(value = "Select p.APPLICATION, 0 as PLACEMENT, pl.TO_ORG as ORG_ID, org.NAME, SUM (extract(day from 24*60*(NVL(pl.DATE_TO, systimestamp) - pl.DATE_FROM))) as UKUPNO  " 
			+ " from PLACEMENTTRANSFER pl "
			+ " JOIN PLACEMENT p on p.ID = pl.PLACEMENT "
			+ " JOIN HRORGANIZATION org on org.ID = pl.TO_ORG "
			+ " where p.APPLICATION = ? "
			+ " group by p.APPLICATION, 0, pl.TO_ORG, org.NAME ",  nativeQuery = true)
	public List<PlacementTimeConsument> getTimeConsumentByApplication(Long id);
	
	
}
