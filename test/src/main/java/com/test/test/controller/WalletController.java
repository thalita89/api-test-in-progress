package com.test.test.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.controller.dto.WalletDto;
import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;
import com.test.test.service.UpdateValueAccountWalletService;
import com.test.test.validation.BusinessException;

@RestController
@RequestMapping("/wallets")
public class WalletController {

	@Autowired
	private WalletRepository walletRepository;

	@GetMapping
	public List<WalletDto> listWallet(@RequestBody(required = false) Wallet wallet) {
		return WalletDto.convert(walletRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<WalletDto> saveWallet(@RequestBody WalletDto walletDto) throws BusinessException {
		Wallet wallet = walletDto.convert();
		walletRepository.save(wallet);
		return ResponseEntity.status(HttpStatus.CREATED).body(new WalletDto(wallet));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Wallet> searchWalletId(@PathVariable Long id) {
		return walletRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// update: valueAccount
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<WalletDto> updateWallet(@PathVariable Long id,
			@RequestBody @Valid UpdateValueAccountWalletService updateValueAccountWalletService)
			throws BusinessException {
		walletRepository.findById(id);
		return ResponseEntity.ok(new WalletDto(updateValueAccountWalletService.update(id, walletRepository)));
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		walletRepository.deleteById(id);
	}
}
