package com.test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.test.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
