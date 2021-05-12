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

import com.test.test.controller.dto.UpdateWalletDto;
import com.test.test.controller.dto.UserDto;
import com.test.test.controller.dto.WalletDto;
import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;
import com.test.test.service.DepositValueAccountWalletService;
import com.test.test.service.WalletService;
import com.test.test.service.WithdrawValueAccountWalletService;
import com.test.test.validation.BusinessException;

//SOLID? not yet
@RestController
@RequestMapping("/wallets")
public class WalletUserController {

	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private WalletService walletService;
	@Autowired
	private DepositValueAccountWalletService depositValueAccountWalletService;
	@Autowired
	private WithdrawValueAccountWalletService withdrawValueAccountWalletService;

	@GetMapping
	public List<WalletDto> listWallet(@RequestBody(required = false) Wallet wallet) {
		return WalletDto.convert(walletRepository.findAll());
	}

	// include cpf validation
	@GetMapping("/{id}")
	public ResponseEntity<Wallet> searchWalletId(@PathVariable Long id) {
		return walletRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// add valueAccount
	@PutMapping("/add/{id}")
	@Transactional
	public ResponseEntity<WalletDto> plusValueWallet(@PathVariable Long id,
			@RequestBody @Valid UpdateWalletDto updateWalletDto) throws BusinessException {
		depositValueAccountWalletService.addValueToWallet(id, updateWalletDto);
		return ResponseEntity.ok().build();
	}

	// withdraw valueAccount
	@PutMapping("/withdraw/{id}")
	@Transactional
	public ResponseEntity<WalletDto> subtractedValueWallet(@PathVariable Long id,
			@RequestBody @Valid UpdateWalletDto updateWalletDto) throws BusinessException {
		withdrawValueAccountWalletService.withdrawValueToWallet(id, updateWalletDto);
		return ResponseEntity.ok().build();
	}

	// created new wallet
	@PostMapping("/user/{id}")
	@Transactional
	public ResponseEntity<?> saveOrUpdateUser(@PathVariable Long id, @RequestBody UserDto userDto, WalletDto walletDto)
			throws BusinessException {
		walletService.newUserWallet(userDto, id, walletDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// include cpf validation
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		walletRepository.deleteById(id);
	}
}
