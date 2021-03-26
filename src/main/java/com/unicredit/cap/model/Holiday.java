package com.unicredit.cap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HOLIDAY")
public class Holiday {

	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long id;
    
    @Column(name = "HOLIDAYDATE")
	private Date holidateDate;
    
    @Column(name = "DESCRIPTION")
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getHolidateDate() {
		return holidateDate;
	}

	public void setHolidateDate(Date holidateDate) {
		this.holidateDate = holidateDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
