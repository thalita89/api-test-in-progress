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
	private Long walletId;

	@Column(precision = 10, scale = 2)
	private BigDecimal valueAccount = BigDecimal.ZERO;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_userId", unique = true, nullable = false)
	private User user;

	public Wallet() {
	}

	// constructor to PUT UserController
	public Wallet(Long walletId, BigDecimal valueAccount, User user) {
		this.walletId = walletId;
		this.valueAccount = valueAccount;
		this.user = user;
	}

	// constructor to POST/PUT
	public Wallet(BigDecimal valueAccount) {
		this.valueAccount = valueAccount;
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
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
