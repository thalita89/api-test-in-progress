package com.test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.test.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	//Currency findByName(String currencyName);

}
