package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTBUSINESPARTNER")
public class ClientBusinesPartner {

    @Id
    @GeneratedValue
    @Column(name = "CORE_NO")
	private long codeNo;
	
    @Column(name = "NAME")
	private String name;
	
    @Column(name = "JIB")
	private String jib;
	
    @Column(name = "PERSONAL_DOC_NO")
	private String personalDocNo;
	
    @Column(name = "PHONE")
	private String phone;
	
    @Column(name = "EMAIL")
	private String email;

	public long getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(long codeNo) {
		this.codeNo = codeNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJib() {
		return jib;
	}

	public void setJib(String jib) {
		this.jib = jib;
	}

	public String getPersonalDocNo() {
		return personalDocNo;
	}

	public void setPersonalDocNo(String personalDocNo) {
		this.personalDocNo = personalDocNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
