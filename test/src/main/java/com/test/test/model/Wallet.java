package com.test.test.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 10, scale = 2)
	private BigDecimal valueAccount = BigDecimal.ZERO;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", unique = true, nullable = false)
	private User user;

	public Wallet() {
	}

	// constructor to PUT UserController
	public Wallet(Long id, BigDecimal valueAccount, User user) {
		this.id = id;
		this.valueAccount = valueAccount;
		this.user = user;
	}

	// constructor to POST/PUT
	public Wallet(BigDecimal valueAccount) {
		this.valueAccount = valueAccount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValueAccount() {
		return valueAccount;
	}

	public void setValueAccount(BigDecimal valueAccount) {
		this.valueAccount = valueAccount;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
