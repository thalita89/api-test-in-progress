package com.test.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.WalletDto;
import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;
import com.test.test.validation.BusinessException;

@Service
public class WalletSavedService {

	@Autowired
	ValidationService validationService;

	@Autowired
	WalletRepository walletRepository;

	public WalletDto walletSaved(WalletDto walletDto) throws BusinessException {

		Wallet wallet = walletDto.convert();
		walletRepository.save(wallet);
		return new WalletDto(wallet);

	}
}
