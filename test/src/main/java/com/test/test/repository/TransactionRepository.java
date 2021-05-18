package com.test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.test.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
