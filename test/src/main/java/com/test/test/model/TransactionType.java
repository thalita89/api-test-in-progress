package com.test.test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Type can be credit or debit ('C' and 'D' respectively)
@Entity
public class TransactionType {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionTypeId;
    
    public TransactionType() {
	}

	public TransactionType(String transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public String getTransactionTypeId() {
		return transactionTypeId;
	}
    
}
