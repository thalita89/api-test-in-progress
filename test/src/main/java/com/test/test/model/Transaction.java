package com.test.test.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "global_id", unique = true, nullable = false)
    private String globalId;

    @ManyToOne
    @JoinColumn(name = "type_transactionTypeId")
    private TransactionType type;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "wallet_walletId")
    private Wallet wallet;

    @ManyToOne
    @JoinColumn(name = "currency_currencyId")
    private Currency currency;
    
    public Transaction() {
	}

	public Transaction(Long transactionId, String globalId, TransactionType type, BigDecimal amount, Wallet wallet,
			Currency currency) {
		this.transactionId = transactionId;
		this.globalId = globalId;
		this.type = type;
		this.amount = amount;
		this.wallet = wallet;
		this.currency = currency;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getGlobalId() {
		return globalId;
	}

	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
