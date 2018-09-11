package com.unicredit.cap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicredit.cap.model.TaskTimeConsument;

public interface TaskTimeConsumentRepository extends JpaRepository<TaskTimeConsument, Long>{

	@Query(value = "Select p.APPLICATION, p.ID as PLACEMENT, td.TO_ORG as ORG_ID, org.NAME, SUM (extract(day from 24*60*(NVL(td.TO_DATE, systimestamp) - td.FROM_DATE)))  as UKUPNO " 
			+ " from TASKDETAIL td "
			+ " JOIN HRORGANIZATION org on org.ID = td.TO_ORG "
			+ " join TASK t on t.ID = td.TASK "
			+ " join PLACEMENT p on p.ID = t.PLACEMENT  "
			+ " WHERE t.PLACEMENT = ? and FROM_ORG = ? "
			+ " group by   p.APPLICATION, p.ID, td.TO_ORG, org.NAME ",  nativeQuery = true)
	public List<TaskTimeConsument> getTimeConsumentByPlacementAndOrg(Long id, int org);
	
	
	@Query(value = "Select p.APPLICATION, 0 as PLACEMENT, td.TO_ORG as ORG_ID, org.NAME, SUM (extract(day from 24*60*(NVL(td.TO_DATE, systimestamp) - td.FROM_DATE)))  as UKUPNO " 
			+ " from TASKDETAIL td "
			+ " JOIN HRORGANIZATION org on org.ID = td.TO_ORG "
			+ " join TASK t on t.ID = td.TASK "
			+ " join PLACEMENT p on p.ID = t.PLACEMENT  "
			+ " WHERE p.APPLICATION = ? and FROM_ORG = ? "
			+ " group by   p.APPLICATION, 0, td.TO_ORG, org.NAME ",  nativeQuery = true)
	public List<TaskTimeConsument> getTimeConsumentByApplicationAndOrg(Long id, int org);
	
}
