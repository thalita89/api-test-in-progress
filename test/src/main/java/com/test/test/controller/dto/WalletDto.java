package com.test.test.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.test.test.model.User;
import com.test.test.model.Wallet;

// SOLID? not yet
public class WalletDto {

	private Long walletId;
	private BigDecimal valueAccount = BigDecimal.ZERO;
	private String currencyName;

	public WalletDto() {
	}

	public WalletDto(Wallet wallet) {
		this.walletId = wallet.getWalletId();
		this.valueAccount = wallet.getValueAccount();
	}

	public Long getWalletId() {
		return walletId;
	}

	public BigDecimal getValueAccount() {
		return valueAccount;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public static List<WalletDto> convert(List<Wallet> wallets) {
		return wallets.stream().map(WalletDto::new).collect(Collectors.toList());
	}

	public Wallet convert() {
		return new Wallet(valueAccount);
	}

	public Wallet convert(User user, String currencyName) {
		return new Wallet(walletId, valueAccount, user, currencyName);
	}

}
