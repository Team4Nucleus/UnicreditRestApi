package com.unicredit.cap.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.NamedStoredProcedureQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.unicredit.cap.helper.Report1;


public interface Report1Repository extends JpaRepository<Report1, String>{

	
		@Query(value = 
	  " WITH APPINPROCESS as																																		" +
	  " (                                                                                                                                                         " +
	  " Select DISTINCT APPLICATION FROM PLACEMENT WHERE STATUS NOT IN  (32,33)                                                                                   " +
	  " ),                                                                                                                                                        " +
	  " APPFINISHED AS                                                                                                                                            " +
	  " (                                                                                                                                                         " +
	  " Select p.APPLICATION, MAX( p.CLOSING_DATE ) as DATEFROM FROM PLACEMENT p                                                                                  " +
	  " left join APPINPROCESS s on s.APPLICATION = p.APPLICATION                                                                                                 " +
	  " WHERE s.APPLICATION IS NULL                                                                                                                               " +
	  " GROUP BY p.APPLICATION                                                                                                                                    " +
	  " ),                                                                                                                                                        " +
	  " DATA AS (                                                                                                                                                 " +
	  " Select a.ID as APPLICATION, u.HRORGANIZATION as ORG, CREATE_DATE as DATEFROM , 'APP_INIT' as ST FROM APPLICATION a                                        " +
	  " join HRUSER u on u.ID = a.CREATE_USER                                                                                                                     " +
	  " WHERE a.CREATE_DATE BETWEEN ? AND ? + 1                                                                                                   " +
	  "                                                                                                                                                           " +
	  " UNION ALL                                                                                                                                                 " +
	  "                                                                                                                                                           " +
	  " Select t.APPLICATION, t.TO_ORG as ORG, t.RESPOND_DATE as DATEFROM , 'APP_TRANSFER' as ST from APPLICATIONTRANSFER t                                       " +
	  " JOIN APPLICATION a on a.ID = t.APPLICATION                                                                                                                " +
	  " WHERE t.STATUS = 'APPROVED'                                                                                                                               " +
	  " and a.CREATE_DATE BETWEEN ? AND ? + 1                                                                                                     " +
	  "                                                                                                                                                           " +
	  " UNION ALL                                                                                                                                                 " +
	  "                                                                                                                                                           " +
	  " SELECT a1.ID as APPLICATION, a1.CURRENT_ORG as ORG, NVL(o.DATEFROM, SYSDATE) as DATEFROM, 'APP_CLOSED' as ST FROM APPLICATION a1                          " +
	  " LEFT JOIN APPFINISHED o on o.APPLICATION = a1.ID                                                                                                          " +
	  " WHERE a1.CREATE_DATE BETWEEN ? AND ? + 1                                                                                                  " +
	  " ),                                                                                                                                                        " +
	  " DATA_1 as (                                                                                                                                               " +
	  " Select d.APPLICATION, d.ORG, d.DATEFROM, d.ST, CASE WHEN f.APPLICATION is NULL then 'OPEN' ELSE 'CLOSED' END as STATUS,  ROW_NUMBER()                     " +
	  "    OVER (PARTITION BY d.APPLICATION ORDER BY d.APPLICATION, d.DATEFROM) as RB                                                                             " +
	  "    FROM DATA d                                                                                                                                            " +
	  "    LEFT JOIN APPFINISHED f on f.APPLICATION = d.APPLICATION                                                                                               " +
	  "    ),                                                                                                                                                     " +
	  " DATA_2 as (                                                                                                                                               " +
	  "    Select d1.APPLICATION, d1.ORG, d1.DATEFROM, d1.ST, d1.STATUS, d2.DATEFROM as DATETO, CALCULATE_TIME(d1.DATEFROM,d2.DATEFROM ) as TIME from DATA_1 d1   " +
	  "    join DATA_1 d2 on d1.APPLICATION = d2.APPLICATION and  d1.RB = d2.RB - 1                                                                               " +
	  "    ),                                                                                                                                                     " +
	 
	  " DATA_4 as ( "+
	  "   Select APPLICATION, ORG, STATUS, SUM(TIME) as TIME, count(*) as iter " +
	  "   from DATA_2 " +
	  "	   group by APPLICATION, ORG, STATUS " +
	  "	   order by APPLICATION " +
	  "	   ), " +
	  
	  
	  " DATA_3 as (                                                                                                                                               " +
	  "    Select ORG,                                                                                                                                            " +
	  "    count(*) as BrojZapocetih,                                                                                   " +
	  "    COUNT( iter ) as BrojIteracija,                                                                                                                             " +
	  "    SUM ( CASE WHEN STATUS='CLOSED' THEN 1 else 0 end ) as BrojZavrsenih,                                                                " +
	  "    SUM ( CASE WHEN STATUS='CLOSED' THEN TIME ELSE 0 END) as UkupnoVrijemeZavrsenih                                                                        " +
	  "    from DATA_4                                                                                                                                            " +
	  "    group by ORG                                                                                                                                           " +
	  "    )                                                                                                                                                      " +
	  "    Select o.NAME as ORG, BrojZapocetih as BROJZAPOCETIH, BrojIteracija as BROJITERACIJA, BrojZavrsenih as BROJZAVRSENIH,                                                                                     " +
	  "    CASE WHEN BrojZavrsenih = 0 then 0 else  UkupnoVrijemeZavrsenih / BrojZavrsenih end as PROSJECNOVRIJEMEZAVRSENIH                                       " +
	  "    from DATA_3 d                                                                                                                                          " +
	  "    join HRORGANIZATION o on o.ID = d.ORG",  nativeQuery = true)
	  public List<Report1> getReport1(Date from, Date to, Date from1, Date to1, Date from2, Date to2);
	  
}

