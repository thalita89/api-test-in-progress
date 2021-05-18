package com.test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.test.model.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, String> {

}
