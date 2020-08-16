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
				"    WITH CIB_SIB AS																																" +
						"   (                                                                                                                                               " +
						"   Select 'PCZD' as CODE, 'CIB' as CODEPARENT from dual                                                                                            " +
						"   UNION                                                                                                                                           " +
						"   Select 'PCZ' as CODE, 'CIB' as CODEPARENT from dual                                                                                             " +
						"   UNION                                                                                                                                           " +
						"   Select 'PCID' as CODE, 'CIB' as CODEPARENT from dual                                                                                            " +
						"   UNION                                                                                                                                           " +
						"   Select 'PCI' as CODE, 'CIB' as CODEPARENT from dual                                                                                             " +
						"   UNION                                                                                                                                           " +
						"   Select 'PCIK' as CODE, 'CIB' as CODEPARENT from dual                                                                                            " +
						"   UNION                                                                                                                                           " +
						"   Select 'PCJF' as CODE, 'CIB' as CODEPARENT from dual                                                                                            " +
						"   ),                                                                                                                                              " +
						"   APP_PLCS as                                                                                                                                     " +
						"   (                                                                                                                                               " +
						"   Select APPLICATION, ID, ROW_NUMBER() OVER (PARTITION BY APPLICATION ORDER BY APPLICATION, ID) as BR from PLACEMENT                              " +
						"                                                                                                                                                   " +
						"   ),                                                                                                                                              " +
						"   TRANSFERS_TO as                                                                                                                                 " +
						"   (                                                                                                                                               " +
						"   Select PLACEMENT, org.CODE, MIN(DATE_FROM) as MINDATE, MAX(DATE_FROM) as MAXDATE  from PLACEMENTTRANSFER pt                                     " +
						"   join HRORGANIZATION org on org.ID = pt.TO_ORG                                                                                                   " +
						"   group by PLACEMENT,  org.CODE                                                                                                                   " +
						"   ),                                                                                                                                              " +
						"   TRANSFERS_FROMRISK_TO as                                                                                                                        " +
						"   (                                                                                                                                               " +
						"   Select PLACEMENT, MIN(DATE_FROM) as MINDATE, MAX(DATE_FROM) as MAXDATE  from PLACEMENTTRANSFER pt                                               " +
						"   join HRORGANIZATION org on org.ID = pt.TO_ORG                                                                                                   " +
						"   join HRORGANIZATION org1 on org1.ID = pt.FROM_ORG                                                                                               " +
						"   where org1.CODE = 'RI'                                                                                                                          " +
						"   and org.CODE  in ('PCZD','PCZ','PCID','PCI','PCIK','PCJF')                                                                                      " +
						"   group by PLACEMENT,  org.CODE                                                                                                                   " +
						"   ),                                                                                                                                              " +
						"   TRANSFERS_FROM as                                                                                                                               " +
						"   (                                                                                                                                               " +
						"   Select PLACEMENT, org.CODE, MIN(DATE_FROM) as MINDATE, MAX(DATE_FROM) as MAXDATE  from PLACEMENTTRANSFER pt                                     " +
						"   join HRORGANIZATION org on org.ID = pt.FROM_ORG                                                                                                 " +
						"   group by PLACEMENT,  org.CODE                                                                                                                   " +
						"   ),                                                                                                                                              " +
						"   TIMEOKR as                                                                                                                                      " +
						"   (                                                                                                                                               " +
						"   Select PLACEMENT,                                                                                                                               " +
						"   SUM ( CASE WHEN p.DECISION_DATE is null then  0                                               " +
						"              WHEN p.DECISION_DATE between DATE_FROM AND NVL(DATE_TO, SYSDATE) then CALCULATE_TIME(DATE_FROM, DECISION_DATE)                       " +
						"              ELSE  CALCULATE_TIME(DATE_FROM, NVL(DATE_TO, SYSDATE)) END                                                                           " +
						"   ) as VRIJEME                                                                                                                                    " +
						"   from PLACEMENTTRANSFER v                                                                                                                        " +
						"   join HRORGANIZATION org on org.ID = v.TO_ORG                                                                                                    " +
						"   join PLACEMENT p on p.ID = v.PLACEMENT                                                                                                          " +
						"   WHERE org.CODE = 'RI'                                                                                                                           " +
						"   group by PLACEMENT                                                                                                                              " +
						"   ),                                                                                                                                              " +
						"   ITERATIONS as                                                                                                                                   " +
						"   (                                                                                                                                               " +
						
 						" SELECT pt.PLACEMENT, count(pt.ID) as BR  from PLACEMENTTRANSFER pt "+ 
 						" join PLACEMENT p on p.ID = pt.PLACEMENT                            "+
 						" join HRORGANIZATION of1 on of1.ID = pt.FROM_ORG                    "+
 						" join HRORGANIZATION ot on ot.ID = pt.TO_ORG                        "+
 						" join CIB_SIB cs on cs.CODE = ot.CODE                               "+
 						" WHERE of1.CODE = 'RI'                                              "+
 						" AND pt.DATE_TO < NVL( p.DECISION_DATE, to_date('31.12.9999','dd.mm.yyyy') )  "+
 						" group by pt.PLACEMENT    "+
						
						"   ) ,                                                                                                                                              " +
						
						"      RISKUSER as (									" +
						"      Select PLACEMENT, NAME from (                    " +
						"      Select v.PLACEMENT, MAX(v.TO_USER) as USER1      " +
						"      from PLACEMENTTRANSFER v                         " +                                                                                               
						"	  join HRORGANIZATION org on org.ID = v.TO_ORG      " +
						"	  WHERE org.CODE = 'RI'  and v.TO_USER is not null  " +
						"      group by PLACEMENT ) p                           " +
						"      join HRUSER u on u.ID = p.USER1                  " +
						"      )                                                " +
						
						"   Select                                                                                                                                          " +
						"   p.ID,                                                                                                                                           " +
						"   cs.CODEPARENT ,                                                                                                                                 " +
						"   p.CLIENT_NAME,                                                                                                                                  " +
						"   p.CLIENT_CORE_NO,                                                                                                                               " +
						"   p.SOCIALRISK,                                                                                                                                   " +
						"   type.NAME as Tip,                                                                                                                               " +
						"   p.AMOUNT_IN_BAM as TrazeniIznosPlasmana,                                                                                                        " +
						"   p.AMOUNT_IN_BAM_APPROVED as OdobreniIznosPlasmana,                                                                                              " +
						"   p.TOTAL_EXPOSURE as UkupnaIzlozenost,                                                                                                           " +
						"   CASE when plcs.BR = 1 then 1 else 0 end as Aplikacija,                                                                                          " +
						"   ofi.NAME as RM,                                                                                                                                 " +
						"   rus.NAME as RISKUSER,   " +
						"   trRI.MINDATE as PrviPutURISK,                                                                                                                   " +
						"   trVPO.MINDATE as PrviPutUVPO,                                                                                                                   " +
						"   trRI.MAXDATE as POslednjiPuTuRISK,                                                                                                              " +
						"   NVL( it.BR, 0 ) as BrojIteracija,                                                                                                               " +
						"   p.OPINION_OKR,                                                                                                                                  " +
						"   trRI1.MAXDATE as DAtumMisljenjeOKR,                                                                                                             " +
						"   ch.NAME as NosilacKOmpetencije,                                                                                                                 " +
						"   p.DECISION as Odluka,                                                                                                                           " +
						"   p.DECISION_DATE as DtumOdluke,                                                                                                                  " +
						"   trNBCO.MINDATE as DatumSlanjaNaNBCO,                                                                                                            " +
						"   p.OPINION_NBCO,                                                                                                                                 " +
						"   trNBCO1.MAXDATE as OdlukaNBCO,                                                                                                                  " +
						"   time.VRIJEME                                                                                                                                    " +
						"   from PLACEMENT p                                                                                                                                " +
						"   join APPLICATION a on a.ID = p.APPLICATION                                                                                                      " +
						"   LEFT JOIN COMPETENCYHOLDER ch on ch.ID = a.COMPETENCYHOLDER                                                                                     " +
						"   join HRORGANIZATION org on org.ID = p.CREATING_ORG                                                                                              " +
						"   join CIB_SIB cs on cs.CODE = org.CODE                                                                                                           " +
						"   left join APP_PLCS plcs on plcs.APPLICATION = p.APPLICATION AND plcs.ID = p.ID                                                                  " +
						"   join PLACEMENTTYPE type on type.ID = p.TYPE                                                                                                     " +
						"   join HRUSER ofi on ofi.ID = p.CREATE_USER                                                                                                       " +
						"   left join TRANSFERS_TO trRI on trRI.PLACEMENT = p.ID and trRI.CODE = 'RI'                                                                       " +
						"   left join TRANSFERS_FROMRISK_TO trVPO on trVPO.PLACEMENT = p.ID                                                                                 " +
						"   left join TRANSFERS_TO trNBCO on trNBCO.PLACEMENT = p.ID and trNBCO.CODE = 'CC'                                                                 " +
						"   left join TRANSFERS_FROM trRI1 on trRI1.PLACEMENT = p.ID and trRI1.CODE = 'CO'                                                                  " +
						"   left join TRANSFERS_FROM trNBCO1 on trNBCO1.PLACEMENT = p.ID and trNBCO1.CODE = 'CC'                                                            " +
						"   left join ITERATIONS it on it.PLACEMENT = p.ID                                                                                                  " +
						"   left join TIMEOKR time on time.PLACEMENT = p.ID                                                                                                " +
						" left join RISKUSER rus on rus.PLACEMENT = p.ID " +
						"   WHERE p.CREATING_DATE BETWEEN ? AND ? + 1  ",  nativeQuery = true)
	  public List<Report3> getReport3(Date from, Date to);
	  
}

