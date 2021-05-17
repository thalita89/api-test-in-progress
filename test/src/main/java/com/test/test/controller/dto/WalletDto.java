package com.test.test.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.test.test.model.User;
import com.test.test.model.Wallet;

public class WalletDto {

	private Long id;
	private BigDecimal valueAccount = BigDecimal.ZERO;

	public WalletDto() {
	}

	public WalletDto(Wallet wallet) {
		this.id = wallet.getWalletId();
		this.valueAccount = wallet.getValueAccount();
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValueAccount() {
		return valueAccount;
	}

	public static List<WalletDto> convert(List<Wallet> wallets) {
		return wallets.stream().map(WalletDto::new).collect(Collectors.toList());
	}

	public Wallet convert() {
		return new Wallet(valueAccount);
	}

	public Wallet convert(User user) {
		return new Wallet(id, valueAccount, user);
	}
}
