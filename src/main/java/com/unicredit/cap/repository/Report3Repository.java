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
import com.unicredit.cap.helper.Report3;


public interface Report3Repository extends JpaRepository<Report3, String>{

	
		@Query(value = 
				" WITH CIB_SIB AS																										" + 
						" (                                                                                                                     " + 
						" Select 'PCZD' as CODE, 'CIB' as CODEPARENT from dual                                                                  " + 
						" UNION                                                                                                                 " + 
						" Select 'PCZ' as CODE, 'CIB' as CODEPARENT from dual                                                                   " + 
						" UNION                                                                                                                 " + 
						" Select 'PCID' as CODE, 'CIB' as CODEPARENT from dual                                                                  " + 
						" UNION                                                                                                                 " + 
						" Select 'PCI' as CODE, 'CIB' as CODEPARENT from dual                                                                   " + 
						" UNION                                                                                                                 " + 
						" Select 'PCIK' as CODE, 'CIB' as CODEPARENT from dual                                                                  " + 
						" UNION                                                                                                                 " + 
						" Select 'PCJF' as CODE, 'CIB' as CODEPARENT from dual                                                                  " + 
						" ),                                                                                                                    " + 
						" APP_PLCS as                                                                                                           " + 
						" (                                                                                                                     " + 
						" Select APPLICATION, CLIENT_PERSONAL_DOC, COUNT(*) as BR from PLACEMENT                                                " + 
						" group by APPLICATION, CLIENT_PERSONAL_DOC                                                                             " + 
						" ),                                                                                                                    " + 
						" TRANSFERS_TO as                                                                                                       " + 
						" (                                                                                                                     " + 
						" Select PLACEMENT, org.CODE, MIN(DATE_FROM) as MINDATE, MAX(DATE_FROM) as MAXDATE  from PLACEMENTTRANSFER pt           " + 
						" join HRORGANIZATION org on org.ID = pt.TO_ORG                                                                         " + 
						" group by PLACEMENT,  org.CODE                                                                                         " + 
						" ),                                                                                                                    " + 
						" TRANSFERS_FROM as                                                                                                     " + 
						" (                                                                                                                     " + 
						" Select PLACEMENT, org.CODE, MIN(DATE_FROM) as MINDATE, MAX(DATE_FROM) as MAXDATE  from PLACEMENTTRANSFER pt           " + 
						" join HRORGANIZATION org on org.ID = pt.FROM_ORG                                                                       " + 
						" group by PLACEMENT,  org.CODE                                                                                         " + 
						" ),                                                                                                                    " + 
						" TIMEOKR as                                                                                                            " + 
						" (                                                                                                                     " + 
						" Select PLACEMENT, SUM (CALCULATE_TIME(DATE_FROM, NVL(DATE_TO, sysdate ))) as VRIJEME from PLACEMENTTRANSFER v                        " + 
						" join HRORGANIZATION org on org.ID = v.TO_ORG                                                                          " + 
						" WHERE org.CODE = 'RI'                                                                                                 " + 
						" group by PLACEMENT                                                                                                    " + 
						" ),                                                                                                                    " + 
						" ITERATIONS as                                                                                                         " + 
						" (                                                                                                                     " + 
						" Select PLACEMENT,                                                                                                     " + 
						" COUNT(*) as BR                                                                                                        " + 
						" from PLACEMENTTRANSFER pt                                                                                             " + 
						" join HRORGANIZATION org on org.ID = pt.TO_ORG                                                                         " + 
						" group by PLACEMENT                                                                                                    " + 
						" )                                                                                                                     " + 
						" Select   p.ID,                                                                                                              " + 
						" cs.CODEPARENT ,                                                                                                       " + 
						" p.CLIENT_NAME,                                                                                                        " + 
						" p.CLIENT_CORE_NO,                                                                                                     " + 
						" p.SOCIALRISK,                                                                                                         " + 
						" type.NAME as Tip,                                                                                                     " + 
						" p.AMOUNT_IN_BAM as TRAZENIIZNOSPLASMANA,                                                                              " + 
						" p.AMOUNT_IN_BAM_APPROVED as ODOBRENIIZNOSPLASMANA,                                                                    " + 
						" p.TOTAL_EXPOSURE as UKUPNAIZLOZENOST,                                                                                 " + 
						" CASE when plcs.BR = 1 then 1 else 0 end as APLIKACIJA,                                                                " + 
						" ofi.NAME as RM,                                                                                                       " + 
						" trRI.MINDATE as PRVIPUTURISK,                                                                                         " + 
						" trVPO.MINDATE as PRVIPUTUVPO,                                                                                         " + 
						" trRI.MAXDATE as POSLEDNJIPUTURISK,                                                                                    " + 
						" it.BR as BROJITERACIJA,                                                                                               " + 
						" p.OPINION_OKR,                                                                                                        " + 
						" trRI1.MAXDATE as DATUMMISLJENJEOKR,                                                                                   " + 
						" ch.NAME as NOSILACKOMPETENCIJE,                                                                                       " + 
						" p.DECISION as ODLUKA,                                                                                                 " + 
						" p.DECISION_DATE as DTUMODLUKE,                                                                                        " + 
						" trNBCO.MINDATE as DATUMSLANJANANBCO,                                                                                  " + 
						" p.OPINION_NBCO,                                                                                                       " + 
						" trNBCO1.MAXDATE as ODLUKANBCO,                                                                                        " + 
						" time.VRIJEME                                                                                                          " + 
						" from PLACEMENT p                                                                                                    " + 
						" join APPLICATION a on a.ID = p.APPLICATION                                                                            " + 
						" LEFT JOIN COMPETENCYHOLDER ch on ch.ID = a.COMPETENCYHOLDER                                                           " + 
						" join HRORGANIZATION org on org.ID = p.CREATING_ORG                                                                    " + 
						" join CIB_SIB cs on cs.CODE = org.CODE                                                                                 " + 
						" left join APP_PLCS plcs on plcs.APPLICATION = p.APPLICATION AND plcs.CLIENT_PERSONAL_DOC = p.CLIENT_PERSONAL_DOC      " + 
						" join PLACEMENTTYPE type on type.ID = p.TYPE                                                                           " + 
						" join HRUSER ofi on ofi.ID = p.CREATE_USER                                                                             " + 
						" left join TRANSFERS_TO trRI on trRI.PLACEMENT = p.ID and trRI.CODE = 'RI'                                             " + 
						" left join TRANSFERS_TO trVPO on trVPO.PLACEMENT = p.ID and trVPO.CODE = 'VPO'                                         " + 
						" left join TRANSFERS_TO trNBCO on trNBCO.PLACEMENT = p.ID and trNBCO.CODE = 'NBCO'                                     " + 
						" left join TRANSFERS_FROM trRI1 on trRI1.PLACEMENT = p.ID and trRI1.CODE = 'RI'                                        " + 
						" left join TRANSFERS_FROM trNBCO1 on trNBCO1.PLACEMENT = p.ID and trNBCO1.CODE = 'NBCO'                                " + 
						" left join ITERATIONS it on it.PLACEMENT = p.ID                                                                        " + 
						" left join TIMEOKR time on time.PLACEMENT = p.ID                                                                       " + 
						" WHERE p.CREATING_DATE BETWEEN ? AND ? + 1  ",  nativeQuery = true)
	  public List<Report3> getReport3(Date from, Date to);
	  
}

