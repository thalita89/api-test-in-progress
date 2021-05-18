package com.test.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyId;

    private String currencyName;

	public Currency(){
	}
	
	public Currency(Long currencyId, String currencyName) {
		this.currencyId = currencyId;
		this.currencyName = currencyName;
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
    
}
