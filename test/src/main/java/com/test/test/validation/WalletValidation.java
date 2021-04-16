package com.test.test.validation;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.test.controller.dto.WalletDto;
import com.test.test.repository.WalletRepository;

public class WalletValidation {
	
	@Autowired
	WalletRepository walletRepository;
	
	public void validWallet(@Valid WalletDto walletDto) throws JSONException, BusinessException {
		validValueAccount(walletDto.getValueAccount());
	}
	
	private static final Pattern VALUE_PATTERN = Pattern
			.compile("^[0-9]*$");
	
	public static void validValueAccount(BigDecimal valueAccount) throws BusinessException {
		final Matcher valueAccountMatcher = VALUE_PATTERN.matcher(valueAccount);
		if (!valueAccountMatcher.matches()) {
				throw new BusinessException("valueAccount.is.invalid");
		}
	}
	
}
