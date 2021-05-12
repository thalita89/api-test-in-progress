package com.test.test.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UpdateWalletDto;
import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;
import com.test.test.validation.BusinessException;

@Service
public class DepositValueAccountWalletService {

	@Autowired
	private WalletRepository walletRepository;

	public void addValueToWallet(Long id, UpdateWalletDto updateWalletDto) throws BusinessException {
		Optional<Wallet> wallet = walletRepository.findById(id);
		if (!wallet.isPresent()) {
			throw new BusinessException("wallet.is.invalid");
		}

		if (!wallet.get().getUser().getCpf().equals(updateWalletDto.getCpf())) {
			throw new BusinessException("cpf.is.invalid");
		}

		BigDecimal updatedValue = wallet.get().getValueAccount().add(updateWalletDto.getValueAccount());
		wallet.get().setValueAccount(updatedValue);

		walletRepository.save(wallet.get());
	}

}