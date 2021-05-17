package com.test.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.WalletDto;
import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;

@Service
public class WalletUserGetByIdService {

	@Autowired
	private WalletRepository walletRepository;

	public ResponseEntity<WalletDto> walletUserGetById(Long walletId) {

		Optional<Wallet> wallet = walletRepository.findById(walletId);
		if (wallet.isPresent()) {
			return ResponseEntity.ok(new WalletDto(wallet.get()));
		}
		return ResponseEntity.notFound().build();

	}
}
