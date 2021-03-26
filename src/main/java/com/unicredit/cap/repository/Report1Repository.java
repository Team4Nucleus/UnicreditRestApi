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
				"				     WITH  PRODAJA AS																																				"+																															
						"						   (                                                                                                                                                        "+
						"						   Select 'PCZD' as CODE, 'CIB' as CODEPARENT from dual                                                                                                     "+
						"						   UNION                                                                                                                                                    "+
						"                           Select 'PCZ' as CODE, 'CIB' as CODEPARENT from dual                                                                                                     "+
						"						   UNION                                                                                                                                                    "+
						"						   Select 'PCID' as CODE, 'CIB' as CODEPARENT from dual                                                                                                     "+
						"						   UNION                                                                                                                                                    "+
						"						   Select 'PCI' as CODE, 'CIB' as CODEPARENT from dual                                                                                                      "+
						"						   UNION                                                                                                                                                    "+
						"						   Select 'PCIK' as CODE, 'CIB' as CODEPARENT from dual                                                                                                     "+
						"						   UNION                                                                                                                                                    "+
						"						   Select 'PCJF' as CODE, 'CIB' as CODEPARENT from dual                                                                                                     "+
						"						   ),                                                                                                                                                       "+
						"                           ZAPOCETE as                                                                                                                                             "+
						"                           (                                                                                                                                                       "+
						"                           Select TO_ORG AS ORG, COUNT(DISTINCT APPLICATION) as BR from APPLICATIONTRANSFER                                                                        "+
						"                             WHERE STATUS = 'APPROVED'                                                                                                                             "+
						"                                AND RESPOND_DATE BETWEEN ? AND ? + 1                                                                                                               "+
						"                                group by TO_ORG                                                                                                                                    "+
						"                                ),                                                                                                                                                 "+
						"                            PLAC_DEC_DATE as                                                                                                                                       "+                         
						"						    (                                                                                                                                                       "+
						"						    Select                                                                                                                                                  "+
						"                            plac.ID,                                                                                                                                               "+
						"                            plac.APPLICATION,                                                                                                                                      "+
						"                            ch.NAME,                                                                                                                                               "+
						"                            ch.ORG,                                                                                                                                                "+
						"						   ( CASE WHEN ch.ORG = 'RI' then plac.DECISION_DATE                                                                                                        "+                     
						"						         WHEN ch.ORG = 'CO' then plac.OPINION_OKR_DATE                                                                                                      "+                        
						"						          WHEN ch.ORG = 'CC' then plac.OPINION_NBCO_DATE                                                                                                    "+                        
						"						    END ) as DAT                                                                                                                                            "+
						"                                                                                                                                                                                   "+
						"						    from PLACEMENT plac                                                                                                                                     "+                        
						"						    join APPLICATION a on a.ID = plac.APPLICATION                                                                                                           "+                        
						"						    left join COMPETENCYHOLDER ch on ch.ID = a.COMPETENCYHOLDER                                                                                             "+
						"						    ),                                                                                                                                                      "+
						"                            APP_DEC_DATE as                                                                                                                                        "+
						"                            (                                                                                                                                                      "+
						"                            Select q.APPLICATION, q.NAME, q.ORG, MIN(q.DAT) as DAT,  MIN (plac.ID) as FIRSTPLACEMENT                                                               "+
						"                            from PLAC_DEC_DATE q                                                                                                                                   "+
						"                            join PLACEMENT plac on plac.APPLICATION = q.APPLICATION                                                                                                "+
						"                            WHERE NOT EXISTS ( Select 1 from PLAC_DEC_DATE WHERE APPLICATION = q.APPLICATION and DAT IS NULL )                                                     "+
						"                            group by q.APPLICATION, q.NAME, q.ORG                                                                                                                  "+
						"                            )                                                                                                                                                      "+
						"                            ,                                                                                                                                                      "+
						"						    DATA AS (                                                                                                                                               "+                        
						"						    Select pt.APPLICATION, pt.TO_ORG as ORG, pt.FROM_ORG, pt.DATE_FROM as DATEFROM, pt.DATE_TO as DATETO, pt.DAT as ODLUKA,                                 "+
						"                            CASE                                                                                                                                                   "+
						"                            WHEN pt.DAT between pt.DATE_FROM AND NVL(pt.DATE_TO, SYSDATE) then CALCULATE_TIME(pt.DATE_FROM, pt.DAT)                                                "+
						"                            WHEN pt.DAT < pt.DATE_FROM then 0                                                                                                                      "+
						"                            WHEN pt.DAT > pt.DATE_FROM then CALCULATE_TIME(pt.DATE_FROM, NVL(pt.DATE_TO, SYSDATE))                                                                 "+
						"                            END as VRIJEME                                                                                                                                         "+
						"                            FROM (                                                                                                                                                 "+
						"                            Select                                                                                                                                                 "+
						"                            pd.APPLICATION, TO_ORG, FROM_ORG, DATE_FROM, DATE_TO, pd.DAT from PLACEMENTTRANSFER pt                                                                 "+
						"                            join APP_DEC_DATE pd on pd.FIRSTPLACEMENT = pt.PLACEMENT                                                                                               "+
						"                            UNION                                                                                                                                                  "+
						"                            Select pd.APPLICATION, TO_ORG, FROM_ORG, FROM_DATE as DATE_FROM, TO_DATE as DATE_TO, pd.DAT from TASKDETAIL td                                         "+
						"                            join TASK t on t.ID = td.TASK                                                                                                                          "+
						"                            join APP_DEC_DATE pd on pd.FIRSTPLACEMENT = t.PLACEMENT                                                                                                "+
						"                            ) pt                                                                                                                                                   "+
						"                            JOIN APPLICATION a on a.ID = pt.APPLICATION                                                                                                            "+
						"                            WHERE a.APPLICATION_DATE BETWEEN ? AND ? + 1                                                                                                     "+
						"						    )                                                                                                                                                       "+
						"                            ,                                                                                                                                                      "+
						"                                                                                                                                                                                   "+
						"                            UKUPNOVRIJEME as                                                                                                                                       "+
						"                            (                                                                                                                                                      "+
						"                                Select APPLICATION, SUM(VRIJEME) as VR from DATA                                                                                                   "+
						"                                group BY APPLICATION                                                                                                                               "+
						"                            ),                                                                                                                                                     "+
						"                         ITERATIONS as                                                                                                                                             "+
						"						  (                                                                                                                                                         "+
						" 						 SELECT dat.APPLICATION, of1.ID as ORG, count(*) as BR  from DATA dat                                                                                       "+
						" 						 join HRORGANIZATION of1 on of1.ID = dat.FROM_ORG                                                                                                           "+
						" 						 join HRORGANIZATION ot on ot.ID = dat.ORG                                                                                                                  "+
						" 						 WHERE of1.CODE not in ( Select CODE from PRODAJA )                                                                                                         "+
						"                         AND ot.CODE in ( Select CODE from PRODAJA)                                                                                                                "+
						" 						 AND dat.DATETO <  dat.ODLUKA                                                                                                                               "+
						" 						 group by dat.APPLICATION , of1.ID                                                                                                                          "+
						"                        ) ,                                                                                                                                                        "+
						"                                                                                                                                                                                   "+
						"                 DATA_3 as (                                                                                                                                                       "+              
						"						       Select d.ORG,                                                                                                                                        "+
						"                               1 as BrojZavrsenih,                                                                                                                                 "+
						"                               SUM (d.VRIJEME) as Ukupno,                                                                                                                          "+
						"                               u.VR as Sveukupno  ,                                                                                                                                "+          
						"						       NVL(i.BR , 0 ) as BrojIteracija                                                                                                                      "+                                                                                                                                                                                                                      
						"						       from DATA d                                                                                                                                          "+
						"                               join UKUPNOVRIJEME u on u.APPLICATION = d.APPLICATION                                                                                               "+
						"                               left join ITERATIONS i on i.APPLICATION = d.APPLICATION and i.ORG = d.ORG                                                                           "+
						"                                                                                                                                                                                   "+
						"						       group by d.ORG , u.VR  , i.BR                                                                                                                        "+                           
						"						       )                                                                                                                                                    "+
						"                                                                                                                                                                                   "+
						"						       Select o.NAME as ORG,                                                                                                                                "+
						"                               SUM(CASE WHEN z.BR is NULL then 1 else z.BR END) as BrojZapocetih,                                                                                  "+
						"                               SUM(BrojIteracija) as BrojIteracija,                                                                                                                "+
						"                               SUM (BrojZavrsenih) as BrojZavrsenih,                                                                                                               "+
						"						       ROUND (SUM( Ukupno / Sveukupno) , 2 )  as ProsjecnoVrijemeZavrsenih                                                                                  "+
						"						       from DATA_3 d                                                                                                                                        "+                        
						"						    join HRORGANIZATION o on o.ID = d.ORG                                                                                                                   "+
						"                             left join ZAPOCETE z on z.ORG = d.ORG                                                                                                                 "+
						"                            group by o.NAME                                                                                                                                        "
						,  nativeQuery = true)

	  public List<Report1> getReport1(Date from, Date to, Date from1, Date to1);
	  
}

