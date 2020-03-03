package com.unicredit.cap.helper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report2 {

	@Id
	 @Column(name = "KATEGORIJA")
	public String kategrija;
	 
	 @Column(name = "BROJODOBRENIHPLASMANA")
	public int brojOdobrenihPlasmana;
	 
	 @Column(name = "IZNOSODOBRENIHPLASMANA", columnDefinition = "NUMBER(30,2)")
	public Double iznosOdobrenihPlasmana;
	
	 @Column(name = "BROJODBIJENIHPLASMANA")
	public int brojOdbijenihPlasmana;
	
	 @Column(name = "IZNOSODBIJENIHPLASMANA", columnDefinition = "NUMBER(30,2)")
	public Double iznosOdbijenihPlasmana;
	
	 @Column(name = "TIMETODECISION", columnDefinition = "NUMBER(30,2)")
	public Double timeToDecision;
	
	 @Column(name = "ISPLACENO", columnDefinition = "NUMBER(30,2)")
	public Double isplaceno;
	
	 @Column(name = "TIMETOCASH", columnDefinition = "NUMBER(30,2)")
	public Double timeToCash;
	
	 @Column(name = "ORG_PCZ", columnDefinition = "NUMBER(30,2)")
	public Double orgPcz;
	
	 @Column(name = "ORG_LD", columnDefinition = "NUMBER(30,2)")
	 public Double orgLd;
	
	 @Column(name = "ORG_ED", columnDefinition = "NUMBER(30,2)")
	 public Double orgEd;
	
	 @Column(name = "ORG_DB", columnDefinition = "NUMBER(30,2)")
	 public Double orgDb;
	
	 @Column(name = "ORG_PR", columnDefinition = "NUMBER(30,2)")
	 public Double orgPr;
	
	 @Column(name = "ORG_RI", columnDefinition = "NUMBER(30,2)")
	 public Double orgRi;
	
	 @Column(name = "ORG_CO", columnDefinition = "NUMBER(30,2)")
	 public Double orgCo;
	
	 @Column(name = "ORG_CC", columnDefinition = "NUMBER(30,2)")
	 public Double orgCc;
	
	 @Column(name = "ORG_KA", columnDefinition = "NUMBER(30,2)")
	 public Double orgKa;
	
	 @Column(name = "ORG_PCZD", columnDefinition = "NUMBER(30,2)")
	 public Double orgPczd;
	
	 @Column(name = "ORG_SDPS", columnDefinition = "NUMBER(30,2)")
	 public Double orgSdps;
	
	 @Column(name = "ORG_PCID", columnDefinition = "NUMBER(30,2)")
	 public Double orgPcid;
	
	 @Column(name = "ORG_PCI", columnDefinition = "NUMBER(30,2)")
	 public Double orgPci;
	
	 @Column(name = "ORG_PCIK", columnDefinition = "NUMBER(30,2)")
	 public Double orgPcik;
	
	 @Column(name = "ORG_PCJF", columnDefinition = "NUMBER(30,2)")
	 public Double orgPcjf;

	public String getKategrija() {
		return kategrija;
	}

	public void setKategrija(String kategrija) {
		this.kategrija = kategrija;
	}

	public int getBrojOdobrenihPlasmana() {
		return brojOdobrenihPlasmana;
	}

	public void setBrojOdobrenihPlasmana(int brojOdobrenihPlasmana) {
		this.brojOdobrenihPlasmana = brojOdobrenihPlasmana;
	}

	public Double getIznosOdobrenihPlasmana() {
		return iznosOdobrenihPlasmana;
	}

	public void setIznosOdobrenihPlasmana(Double iznosOdobrenihPlasmana) {
		this.iznosOdobrenihPlasmana = iznosOdobrenihPlasmana;
	}

	public int getBrojOdbijenihPlasmana() {
		return brojOdbijenihPlasmana;
	}

	public void setBrojOdbijenihPlasmana(int brojOdbijenihPlasmana) {
		this.brojOdbijenihPlasmana = brojOdbijenihPlasmana;
	}

	public Double getIznosOdbijenihPlasmana() {
		return iznosOdbijenihPlasmana;
	}

	public void setIznosOdbijenihPlasmana(Double iznosOdbijenihPlasmana) {
		this.iznosOdbijenihPlasmana = iznosOdbijenihPlasmana;
	}

	public Double getTimeToDecision() {
		return timeToDecision;
	}

	public void setTimeToDecision(Double timeToDecision) {
		this.timeToDecision = timeToDecision;
	}

	public Double getIsplaceno() {
		return isplaceno;
	}

	public void setIsplaceno(Double isplaceno) {
		this.isplaceno = isplaceno;
	}

	public Double getTimeToCash() {
		return timeToCash;
	}

	public void setTimeToCash(Double timeToCash) {
		this.timeToCash = timeToCash;
	}

	public Double getOrgPcz() {
		return orgPcz;
	}

	public void setOrgPcz(Double orgPcz) {
		this.orgPcz = orgPcz;
	}

	public Double getOrgLd() {
		return orgLd;
	}

	public void setOrgLd(Double orgLd) {
		this.orgLd = orgLd;
	}

	public Double getOrgEd() {
		return orgEd;
	}

	public void setOrgEd(Double orgEd) {
		this.orgEd = orgEd;
	}

	public Double getOrgDb() {
		return orgDb;
	}

	public void setOrgDb(Double orgDb) {
		this.orgDb = orgDb;
	}

	public Double getOrgPr() {
		return orgPr;
	}

	public void setOrgPr(Double orgPr) {
		this.orgPr = orgPr;
	}

	public Double getOrgRi() {
		return orgRi;
	}

	public void setOrgRi(Double orgRi) {
		this.orgRi = orgRi;
	}

	public Double getOrgCo() {
		return orgCo;
	}

	public void setOrgCo(Double orgCo) {
		this.orgCo = orgCo;
	}

	public Double getOrgCc() {
		return orgCc;
	}

	public void setOrgCc(Double orgCc) {
		this.orgCc = orgCc;
	}

	public Double getOrgKa() {
		return orgKa;
	}

	public void setOrgKa(Double orgKa) {
		this.orgKa = orgKa;
	}

	public Double getOrgPczd() {
		return orgPczd;
	}

	public void setOrgPczd(Double orgPczd) {
		this.orgPczd = orgPczd;
	}

	public Double getOrgSdps() {
		return orgSdps;
	}

	public void setOrgSdps(Double orgSdps) {
		this.orgSdps = orgSdps;
	}

	public Double getOrgPcid() {
		return orgPcid;
	}

	public void setOrgPcid(Double orgPcid) {
		this.orgPcid = orgPcid;
	}

	public Double getOrgPci() {
		return orgPci;
	}

	public void setOrgPci(Double orgPci) {
		this.orgPci = orgPci;
	}

	public Double getOrgPcik() {
		return orgPcik;
	}

	public void setOrgPcik(Double orgPcik) {
		this.orgPcik = orgPcik;
	}

	public Double getOrgPcjf() {
		return orgPcjf;
	}

	public void setOrgPcjf(Double orgPcjf) {
		this.orgPcjf = orgPcjf;
	}
	 
	 public Report2() {}
	
	
}
