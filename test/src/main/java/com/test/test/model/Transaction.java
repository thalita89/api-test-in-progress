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

    public Transaction() {
	}

	public Transaction(Long transactionId, String globalId, TransactionType type, BigDecimal amount, Wallet wallet) {
		this.transactionId = transactionId;
		this.globalId = globalId;
		this.type = type;
		this.amount = amount;
		this.wallet = wallet;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public String getGlobalId() {
		return globalId;
	}

	public TransactionType getType() {
		return type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Wallet getWallet() {
		return wallet;
	}

}
