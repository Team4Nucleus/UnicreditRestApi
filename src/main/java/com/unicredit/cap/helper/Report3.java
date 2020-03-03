package com.unicredit.cap.helper;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report3 {

	
	 @Id
	 @Column(name = "CODEPARENT", length = 3, columnDefinition = "char")
	public String orgJedinica;

	 @Column(name = "CLIENT_NAME")
	 public String clientName;
	 
	 @Column(name = "CLIENT_CORE_NO")
	 public Long clientCoreNo;

	 @Column(name = "SOCIALRISK")
	 public String socialRisk;

	 @Column(name = "TIP")
	 public String tip;

	 
	 @Column(name = "TRAZENIIZNOSPLASMANA", columnDefinition = "NUMBER(30,2)")
	 public Double trazeniIznosPlasmana;
		
		 @Column(name = "ODOBRENIIZNOSPLASMANA", columnDefinition = "NUMBER(30,2)")
		 public Double odobreniIznosPlasmana;
		
		 @Column(name = "UKUPNAIZLOZENOST")
		 public Long ukupnaIzlozenost;
		
		 @Column(name = "APLIKACIJA")
		 public int aplikacija;
		
		 @Column(name = "RM")
		 public String rm;
		
		 @Column(name = "PRVIPUTURISK")
		 public Date prviUpitPremaRisk;
		
		 @Column(name = "PRVIPUTUVPO")
		 public Date prvoKretanjeVpo;
		
		 @Column(name = "POSLEDNJIPUTURISK")
		 public Date poslednjekretanjeUrisk;
		
		 @Column(name = "BROJITERACIJA")
		 public int brojIteracija;
		
		 @Column(name = "OPINION_OKR")
		 public String opinionOkr;
		
		 @Column(name = "DATUMMISLJENJEOKR")
		 public Date datumMisljenjaOkr;
		
		 @Column(name = "NOSILACKOMPETENCIJE")
		 public String nosilacKompetencije;
		
		 @Column(name = "ODLUKA")
		 public String odluka;
		
		 @Column(name = "DTUMODLUKE")
		 public Date datumOdluke;
		
		 @Column(name = "DATUMSLANJANANBCO")
		 public Date datumSlanjaUnbco;
		
		 @Column(name = "OPINION_NBCO")
		 public String opinionNbco;
		
		 @Column(name = "ODLUKANBCO")
		 public Date datumOdlukeNbco;
		
		 @Column(name = "VRIJEME", columnDefinition = "NUMBER(30,2)")
		 public Double  vrijeme;
	
		
}
