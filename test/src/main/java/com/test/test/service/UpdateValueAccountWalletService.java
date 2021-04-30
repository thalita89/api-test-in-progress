package com.test.test.service;

import java.math.BigDecimal;

import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;

public class UpdateValueAccountWalletService {

	private Long id;
	private BigDecimal valueAccount = BigDecimal.ZERO;

	public UpdateValueAccountWalletService() {
	}

	public UpdateValueAccountWalletService(Wallet wallet) {
		this.id = wallet.getId();
		this.valueAccount = wallet.getValueAccount();
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValueAccount() {
		return valueAccount;
	}

	public Wallet update(Long id, WalletRepository walletRepository) {
		Wallet wallet = walletRepository.getOne(id);
		wallet.setValueAccount(this.valueAccount);

		return wallet;
	}

}
