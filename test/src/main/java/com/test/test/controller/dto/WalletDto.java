package com.test.test.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;

public class WalletDto {
	
	private Long id;
	private BigDecimal valueAccount = BigDecimal.ZERO;
	//private User user;
	
	public WalletDto() {	
	}

	public WalletDto(Wallet wallet) {
		this.id = wallet.getId();
		this.valueAccount = wallet.getValueAccount();
		//this.user = wallet.getUser();
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
	
	public Wallet updateValueAccount(Long id, WalletRepository walletRepository) {
		Wallet wallet = walletRepository.getOne(id);
		wallet.setValueAccount(valueAccount);
		return wallet;
	}


}
