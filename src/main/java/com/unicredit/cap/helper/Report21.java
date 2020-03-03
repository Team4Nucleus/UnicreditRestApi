package com.unicredit.cap.helper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Report21 {

	 @Id
	 @Column(name = "NAME")
	public String name;
	 
	 @Column(name = "VRIJEMEOBRADE", columnDefinition = "NUMBER(30,2)")
	public Double vrijemeObrade;
	 
	 @Column(name = "BROJPLASMANA")
	public int brojPlasmana;
	
	 @Column(name = "BROJITERACIJA")
	public int brojIteracija;
	
	 @Column(name = "ITERPOPLASMANU", columnDefinition = "NUMBER(30,2)")
	public Double iterPoPlasmanu;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getVrijemeObrade() {
		return vrijemeObrade;
	}

	public void setVrijemeObrade(Double vrijemeObrade) {
		this.vrijemeObrade = vrijemeObrade;
	}

	public int getBrojPlasmana() {
		return brojPlasmana;
	}

	public void setBrojPlasmana(int brojPlasmana) {
		this.brojPlasmana = brojPlasmana;
	}

	public int getBrojIteracija() {
		return brojIteracija;
	}

	public void setBrojIteracija(int brojIteracija) {
		this.brojIteracija = brojIteracija;
	}

	public Double getIterPoPlasmanu() {
		return iterPoPlasmanu;
	}

	public void setIterPoPlasmanu(Double iterPoPlasmanu) {
		this.iterPoPlasmanu = iterPoPlasmanu;
	}
	 
	 public Report21() {}
	
	
}
