package com.test.test.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long walletId;

	@Column(precision = 10, scale = 2)
	private BigDecimal valueAccount = BigDecimal.ZERO;

	private String currencyName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_userId", unique = true, nullable = false)
	private User user;

	@OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

	public Wallet() {
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	// constructor to POST UserController
	public Wallet(Long walletId, BigDecimal valueAccount, User user, String currencyName) {
		this.walletId = walletId;
		this.valueAccount = valueAccount;
		this.currencyName = currencyName;
		this.user = user;
	}

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


	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
