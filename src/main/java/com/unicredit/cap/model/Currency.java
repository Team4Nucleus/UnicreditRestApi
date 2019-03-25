package com.unicredit.cap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CURRENCY")
public class Currency {

	 	@Id
	    @Column(name = "CODE")
		private String code;
		
	    @Column(name = "NAME")
		private String name;
		
	    @Column(name = "EXCHANGERATE", columnDefinition = "NUMBER(10,5)")
		private double exchangerate;

		public String getCode() {
			return code;
		}

		public void setCodeNo(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getExchangerate() {
			return exchangerate;
		}

		public void setExchangerate(double exchangerate) {
			this.exchangerate = exchangerate;
		}
	
	    
	    
}
