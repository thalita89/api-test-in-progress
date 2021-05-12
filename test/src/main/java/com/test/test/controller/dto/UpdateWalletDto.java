package com.test.test.controller.dto;

import java.math.BigDecimal;

//to update valueAccount from wallet
public class UpdateWalletDto {

	private BigDecimal valueAccount;
	private Long cpf;

	public UpdateWalletDto() {
	}

	public UpdateWalletDto(BigDecimal valueAccount, Long cpf) {
		this.valueAccount = valueAccount;
		this.cpf = cpf;
	}

	public BigDecimal getValueAccount() {
		return valueAccount;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public void setValueAccount(BigDecimal valueAccount) {
		this.valueAccount = valueAccount;
	}
	
}
