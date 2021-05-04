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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.controller.dto.UserDto;
import com.test.test.controller.dto.WalletDto;
import com.test.test.model.User;
import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;
import com.test.test.service.UpdateValueAccountWalletService;
import com.test.test.service.WalletService;
import com.test.test.validation.BusinessException;

@RestController
@RequestMapping("/wallets")
public class WalletUserController {

	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private WalletService walletService;

	@GetMapping
	public List<WalletDto> listWallet(@RequestBody(required = false) Wallet wallet) {
		return WalletDto.convert(walletRepository.findAll());
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

	@PutMapping("/user/{id}")
	@Transactional
	public ResponseEntity<?> saveOrUpdateUser(@PathVariable Long id, @RequestBody UserDto userDto, WalletDto walletDto,
			User user) throws BusinessException {
		walletService.newUserWallet(userDto, id, walletDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		walletRepository.deleteById(id);
	}
}
