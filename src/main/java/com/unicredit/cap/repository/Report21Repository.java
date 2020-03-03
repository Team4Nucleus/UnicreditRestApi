package com.unicredit.cap.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.NamedStoredProcedureQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.unicredit.cap.helper.Report1;
import com.unicredit.cap.helper.Report2;
import com.unicredit.cap.helper.Report21;


public interface Report21Repository extends JpaRepository<Report21, String>{

	
		@Query(value = 
				" WITH DATA as (																																" +
						" Select pt.PLACEMENT as PLACEMENT, pt.TO_ORG as ORG, pt.DATE_FROM as DATEFROM ,                                                                " +
						" CASE WHEN pt.FROM_ORG is NULL then 'PLAC_INIT' ELSE 'PLAC_MOV' END as ST FROM PLACEMENTTRANSFER pt                                            " +
						" join PLACEMENT p on p.ID = pt.PLACEMENT                                                                                                       " +
						" WHERE p.CREATING_DATE BETWEEN ? AND ? + 1                                                                                     " +
						"                                                                                                                                               " +
						" UNION ALL                                                                                                                                     " +
						"                                                                                                                                               " +
						" Select ID as PLACEMENT, CURRENT_ORG as ORG, CASE WHEN STATUS IN (32,33) then CLOSING_DATE ELSE SYSDATE END as DATEFROM, 'PLAC_CLOSE' as ST    " +
						" FROM PLACEMENT                                                                                                                                " +
						" WHERE CREATING_DATE BETWEEN ? AND ? + 1                                                                                       " +
						" ),                                                                                                                                            " +
						"                                                                                                                                               " +
						" DATA_1 as (                                                                                                                                   " +
						" Select d.PLACEMENT, d.ORG, d.DATEFROM, d.ST,                                                                                                  " +
						"    CASE WHEN p.STATUS IN (32,33) then 'CLOSED' ELSE 'OPEN' END as STATUS,  ROW_NUMBER()                                                       " +
						"    OVER (PARTITION BY d.PLACEMENT ORDER BY d.PLACEMENT, d.DATEFROM) as RB                                                                     " +
						"    FROM DATA d                                                                                                                                " +
						"    JOIN PLACEMENT p on p.ID = d.PLACEMENT                                                                                                     " +
						"    ),                                                                                                                                         " +
						" DATA_2 as (                                                                                                                                   " +
						"    Select d1.PLACEMENT, d1.ORG, d1.DATEFROM, d1.ST, d1.STATUS, d2.DATEFROM as DATETO,                                                         " +
						"    CALCULATE_TIME(d1.DATEFROM,d2.DATEFROM ) as TIME                                                                                           " +
						"    from DATA_1 d1                                                                                                                             " +
						"    join DATA_1 d2 on d1.PLACEMENT = d2.PLACEMENT and  d1.RB = d2.RB - 1                                                                       " +
						"    )                                                                                                                                          " +
						" Select org.NAME,                                                                                                                              " +
						" SUM(d.TIME) as VRIJEMEOBRADE,                                                                                                                  " +
						" COUNT(distinct d.PLACEMENT) as BROJPLASMANA ,                                                                                                  " +
						" COUNT(*) as BROJITERACIJA,                                                                                                                    " +
						" COUNT(*) / COUNT(distinct d.PLACEMENT) as ITERPOPLASMANU                                                                                      " +
						" from DATA_2 d                                                                                                                                 " +
						" join HRORGANIZATION org on org.ID = d.ORG                                                                                                     " +
						" group by org.NAME  ",  nativeQuery = true)
	  public List<Report21> getReport21(Date from, Date to, Date from1, Date to1);
	  
}

