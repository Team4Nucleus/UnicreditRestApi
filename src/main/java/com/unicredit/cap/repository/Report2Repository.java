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


public interface Report2Repository extends JpaRepository<Report2, String>{

	
		@Query(value = 
				"    WITH DATA as (																																																				" +
						"  Select pt.PLACEMENT as PLACEMENT, pt.TO_ORG as ORG, pt.DATE_FROM as DATEFROM ,                                                                                                                                               " +
						"  CASE WHEN pt.FROM_ORG is NULL then 'PLAC_INIT' ELSE 'PLAC_MOV' END as ST FROM PLACEMENTTRANSFER pt                                                                                                                           " +
						"  join PLACEMENT p on p.ID = pt.PLACEMENT                                                                                                                                                                                      " +
						"  WHERE p.CREATING_DATE BETWEEN ? AND ? + 1                                                                                                                                                                  " +
						"                                                                                                                                                                                                                               " +
						"  UNION ALL                                                                                                                                                                                                                    " +
						"                                                                                                                                                                                                                               " +
						"  Select ID as PLACEMENT, CURRENT_ORG as ORG, CASE WHEN STATUS IN (32,33) then CLOSING_DATE ELSE SYSDATE END as DATEFROM, 'PLAC_CLOSE' as ST                                                                                   " +
						"  FROM PLACEMENT                                                                                                                                                                                                               " +
						"  WHERE CREATING_DATE BETWEEN ? AND ? + 1                                                                                                                                                                    " +
						"  ),                                                                                                                                                                                                                           " +
						"                                                                                                                                                                                                                               " +
						"  DATA_1 as (                                                                                                                                                                                                                  " +
						"  Select d.PLACEMENT, d.ORG, d.DATEFROM, d.ST,                                                                                                                                                                                 " +
						"     CASE WHEN p.STATUS IN (32,33) then 'CLOSED' ELSE 'OPEN' END as STATUS,  ROW_NUMBER()                                                                                                                                      " +
						"     OVER (PARTITION BY d.PLACEMENT ORDER BY d.PLACEMENT, d.DATEFROM) as RB                                                                                                                                                    " +
						"     FROM DATA d                                                                                                                                                                                                               " +
						"     JOIN PLACEMENT p on p.ID = d.PLACEMENT                                                                                                                                                                                    " +
						"     ),                                                                                                                                                                                                                        " +
						"  DATA_2 as (                                                                                                                                                                                                                  " +
						"     Select d1.PLACEMENT, d1.ORG, d1.DATEFROM, d1.ST, d1.STATUS, d2.DATEFROM as DATETO,                                                                                                                                        " +
						"     CALCULATE_TIME(d1.DATEFROM,d2.DATEFROM ) as TIME                                                                                                                                                                          " +
						"     from DATA_1 d1                                                                                                                                                                                                            " +
						"     join DATA_1 d2 on d1.PLACEMENT = d2.PLACEMENT and  d1.RB = d2.RB - 1                                                                                                                                                      " +
						"     ),                                                                                                                                                                                                                        " +
						"   DATA_3 as (                                                                                                                                                                                                                 " +
						"   Select type.CATEGORY as CAT, d.ORG, org.CODE, SUM(d.TIME) as TIME from DATA_2 d                                                                                                                                             " +
						"   join PLACEMENT p on p.ID = d.PLACEMENT                                                                                                                                                                                      " +
						"   join PLACEMENTTYPE type on type.ID = p.TYPE                                                                                                                                                                                 " +
						"   join HRORGANIZATION org on org.ID = d.ORG                                                                                                                                                                                   " +
						"   group by type.CATEGORY, d.ORG, org.CODE                                                                                                                                                                                     " +
						"   )  ,                                                                                                                                                                                                                        " +
						"                                                                                                                                                                                                                               " +
						"   LAST_MOVEMENT as                                                                                                                                                                                                            " +
						"   (                                                                                                                                                                                                                           " +
						"   Select PLACEMENT, MAX(DATE_FROM) as DATEFROM                                                                                                                                                                                " +
						"   from PLACEMENTTRANSFER pt                                                                                                                                                                                                   " +
						"   join HRORGANIZATION org on org.ID = pt.TO_ORG                                                                                                                                                                               " +
						"  where org.CODE  in ('PCZD','PCZ','PCID','PCI','PCIK','PCJF')                                                                                                                                                                 " +
						"  group by PLACEMENT                                                                                                                                                                                                           " +
						"   ),                                                                                                                                                                                                                          " +
						"   PLAC_DEC_DATE as                                                                                                                                                                                                            " +
						"  (                                                                                                                                                                                                                            " +
						"  Select plac.ID,                                                                                                                                                                                                              " +
						"  CASE WHEN ch.ORG = 'RI' then plac.DECISION_DATE                                                                                                                                                                              " +
						"       WHEN ch.ORG = 'CO' then plac.OPINION_OKR_DATE                                                                                                                                                                           " +
						"       WHEN ch.ORG = 'CC' then plac.OPINION_NBCO_DATE                                                                                                                                                                          " +
						"  END as DECISION_DATE,                                                                                                                                                                                                        " +
						"  CASE WHEN ch.ORG = 'RI' then plac.DECISION                                                                                                                                                                                   " +
						"       WHEN ch.ORG = 'CO' then plac.OPINION_OKR                                                                                                                                                                                " +
						"       WHEN ch.ORG = 'CC' then plac.OPINION_NBCO                                                                                                                                                                               " +
						"  END AS DECISION,                                                                                                                                                                                                             " +
						"  ch.NAME                                                                                                                                                                                                                      " +
						"  from PLACEMENT plac                                                                                                                                                                                                          " +
						"  join APPLICATION a on a.ID = plac.APPLICATION                                                                                                                                                                                " +
						"  left join COMPETENCYHOLDER ch on ch.ID = a.COMPETENCYHOLDER                                                                                                                                                                  " +
						"                                                                                                                                                                                                                               " +
						"  )                                                                                                                                                                                                                            " +
						"  Select cat.NAME as Kategorija,                                                                                                                                                                                               " +
						"  SUM (CASE WHEN  pdd.DECISION = 'POZITIVNO' or pdd.DECISION = 'USLOVNO POZITIVNO' then 1 else 0 end) as BrojOdobrenihPlasmana,                                                                                                " +
						"  SUM (CASE WHEN pdd.DECISION = 'POZITIVNO' or pdd.DECISION = 'USLOVNO POZITIVNO' then AMOUNT_IN_BAM_APPROVED else 0 end) as IznosOdobrenihPlasmana,                                                                           " +
						"  SUM (CASE WHEN pdd.DECISION = 'NEGATIVNO' then 1 else 0 end) as BrojOdbijenihPlasmana,                                                                                                                                       " +
						"  SUM (CASE WHEN pdd.DECISION = 'POZITIVNO' then AMOUNT_IN_BAM else 0 end) as IznosOdbijenihPlasmana,                                                                                                                          " +
						"  SUM (CASE WHEN pdd.DECISION IS NOT NULL then  CALCULATE_TIME(p.CREATING_DATE, pdd.DECISION_DATE ) else 0 end ) as TimeToDecision,                                                                                            " +
						"  SUM (CASE when p.STATUS IN (32,33)  AND ( p.DECISION = 'POZITIVNO' or p.DECISION = 'USLOVNO POZITIVNO' ) then 1 else 0 end ) as Isplaceno,                                                                                   " +
						"  SUM (CASE when p.STATUS IN (32,33)  AND ( p.DECISION = 'POZITIVNO' or p.DECISION = 'USLOVNO POZITIVNO' ) then CALCULATE_TIME(p.CREATING_DATE, NVL( lm.DATEFROM ,p.CLOSING_DATE) ) else 0 end) as TimeToCash,                 " +
						"  SUM (org_PCZ.TIME ) as org_PCZ ,                                                                                                                                                                                             " +
						"  SUM (org_LD.TIME  ) as org_LD  ,                                                                                                                                                                                             " +
						"  SUM (org_ED.TIME  ) as org_ED  ,                                                                                                                                                                                             " +
						"  SUM (org_DB.TIME  ) as org_DB  ,                                                                                                                                                                                             " +
						"  SUM (org_PR.TIME  ) as org_PR  ,                                                                                                                                                                                             " +
						"  SUM (org_RI.TIME  ) as org_RI  ,                                                                                                                                                                                             " +
						"  SUM (org_CO.TIME  ) as org_CO  ,                                                                                                                                                                                             " +
						"  SUM (org_CC.TIME  ) as org_CC  ,                                                                                                                                                                                             " +
						"  SUM (org_KA.TIME  ) as org_KA  ,                                                                                                                                                                                             " +
						"  SUM (org_PCZD.TIME) as org_PCZD,                                                                                                                                                                                             " +
						"  SUM (org_SDPS.TIME) as org_SDPS,                                                                                                                                                                                             " +
						"  SUM (org_PCID.TIME) as org_PCID,                                                                                                                                                                                             " +
						"  SUM (org_PCI.TIME ) as org_PCI ,                                                                                                                                                                                             " +
						"  SUM (org_PCIK.TIME) as org_PCIK,                                                                                                                                                                                             " +
						"  SUM (org_PCJF.TIME) as org_PCJF                                                                                                                                                                                              " +
						"  from PLACEMENT p                                                                                                                                                                                                             " +
						"  join APPLICATION a on a.ID = p.APPLICATION                                                                                                                                                                                   " +
						"  join PLACEMENTTYPE type on type.ID = p.TYPE                                                                                                                                                                                  " +
						"  join PLACEMENTTYPECATEGORY cat on cat.ID = type.CATEGORY                                                                                                                                                                     " +
						"  LEFT JOIN LAST_MOVEMENT lm on lm.PLACEMENT = p.ID                                                                                                                                                                            " +
						"  LEFT JOIN PLAC_DEC_DATE pdd on pdd.ID = p.ID                                                                                                                                                                                 " +
						"  left join DATA_3 org_PCZ    on org_PCZ.CAT  = cat.ID and org_PCZ.CODE  = 'PCZ'                                                                                                                                               " +
						"  left join DATA_3 org_LD     on org_LD.CAT   = cat.ID and org_LD.CODE   = 'LD'                                                                                                                                                " +
						"  left join DATA_3 org_ED     on org_ED.CAT   = cat.ID and org_ED.CODE   = 'ED'                                                                                                                                                " +
						"  left join DATA_3 org_DB     on org_DB.CAT   = cat.ID and org_DB.CODE   = 'DB'                                                                                                                                                " +
						"  left join DATA_3 org_PR     on org_PR.CAT   = cat.ID and org_PR.CODE   = 'PR'                                                                                                                                                " +
						"  left join DATA_3 org_RI     on org_RI.CAT   = cat.ID and org_RI.CODE   = 'RI'                                                                                                                                                " +
						"  left join DATA_3 org_CO     on org_CO.CAT   = cat.ID and org_CO.CODE   = 'CO'                                                                                                                                                " +
						"  left join DATA_3 org_CC     on org_CC.CAT   = cat.ID and org_CC.CODE   = 'CC'                                                                                                                                                " +
						"  left join DATA_3 org_KA     on org_KA.CAT   = cat.ID and org_KA.CODE   = 'KA'                                                                                                                                                " +
						"  left join DATA_3 org_PCZD   on org_PCZD.CAT = cat.ID and org_PCZD.CODE = 'PCZD'                                                                                                                                              " +
						"  left join DATA_3 org_SDPS   on org_SDPS.CAT = cat.ID and org_SDPS.CODE = 'SDPS'                                                                                                                                              " +
						"  left join DATA_3 org_PCID   on org_PCID.CAT = cat.ID and org_PCID.CODE = 'PCID'                                                                                                                                              " +
						"  left join DATA_3 org_PCI    on org_PCI.CAT  = cat.ID and org_PCI.CODE  = 'PCI'                                                                                                                                               " +
						"  left join DATA_3 org_PCIK   on org_PCIK.CAT = cat.ID and org_PCIK.CODE = 'PCIK'                                                                                                                                              " +
						"  left join DATA_3 org_PCJF	on org_PCJF.CAT = cat.ID and org_PCJF.CODE = 'PCJF'                                                                                                                                             " +
						"  WHERE p.CREATING_DATE BETWEEN ? AND ? + 1                                                                                                                                                                  " +
						" group by cat.NAME ",  nativeQuery = true)
	  public List<Report2> getReport2(Date from, Date to, Date from1, Date to1, Date from2, Date to2);
	  
}

