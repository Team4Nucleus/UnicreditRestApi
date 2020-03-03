package com.unicredit.cap.helper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

import java.util.Date;

import javax.persistence.*;


@Entity
public class Report1 {


	 @Id
	 @Column(name = "ORG")
	 public String org;
	
	@Column(name = "BROJZAPOCETIH")
	 public int brojZapocetih;
	
	@Column(name = "BROJITERACIJA")
	 public int brojIteracija; 
	
	@Column(name = "BROJZAVRSENIH")
	 public int brojZavrsenih; 
	
	@Column(name = "PROSJECNOVRIJEMEZAVRSENIH", columnDefinition = "NUMBER(30,2)")
	 public Double prosjecnoVrijemeZavrsenih;
	
	public String getORG() {
		return org;
	}
	public void setORG(String oRG) {
		org = oRG;
	}
	public int getBrojZapocetih() {
		return brojZapocetih;
	}
	public void setBrojZapocetih(int brojZapocetih1) {
		brojZapocetih = brojZapocetih1;
	}
	public int getBrojIteracija() {
		return brojIteracija;
	}
	public void setBrojIteracija(int brojIteracija1) {
		brojIteracija = brojIteracija1;
	}
	public int getBrojZavrsenih() {
		return brojZavrsenih;
	}
	public void setBrojZavrsenih(int brojZavrsenih1) {
		brojZavrsenih = brojZavrsenih1;
	}
	public Double getProsjecnoVrijemeZavrsenih() {
		return prosjecnoVrijemeZavrsenih;
	}
	public void setProsjecnoVrijemeZavrsenih(Double prosjecnoVrijemeZavrsenih1) {
		prosjecnoVrijemeZavrsenih = prosjecnoVrijemeZavrsenih1;
	}

	 
	 
	public Report1() {}
	 
	   
}
