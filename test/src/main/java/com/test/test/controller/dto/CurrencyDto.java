package com.test.test.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.test.test.model.Currency;

public class CurrencyDto {

	private Long currencyId;
	private String currencyName;

	public CurrencyDto() {
	}

	public CurrencyDto(Currency currency) {
		this.currencyId = currency.getCurrencyId();
		this.currencyName = currency.getCurrencyName();
	}
	

	public Long getCurrencyId() {
		return currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public static List<CurrencyDto> convert(List<Currency> currencies) {
		return currencies.stream().map(CurrencyDto::new).collect(Collectors.toList());
	}

	public Currency convert() {
		return new Currency(currencyId, currencyName);
	}

	//public Currency convert(User user) {
		//return new Currency(id, valueAccount, user);
	//}
}
