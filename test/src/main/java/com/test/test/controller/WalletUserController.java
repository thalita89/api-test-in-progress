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

import com.test.test.controller.dto.CurrencyDto;
import com.test.test.controller.dto.UpdateWalletDto;
import com.test.test.controller.dto.UserDto;
import com.test.test.controller.dto.WalletDto;
import com.test.test.model.Wallet;
import com.test.test.repository.WalletRepository;
import com.test.test.service.DepositValueAccountWalletService;
import com.test.test.service.WalletService;
import com.test.test.service.WalletUserGetByIdService;
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
	@Autowired
	private WalletUserGetByIdService walletUserGetByIdService;

	@GetMapping
	public List<WalletDto> listWallet(@RequestBody(required = false) Wallet wallet) {
		return WalletDto.convert(walletRepository.findAll());
	}

	//check
	// include cpf validation
	@GetMapping("/{walletId}")
	public ResponseEntity<WalletDto> searchWalletId(@PathVariable Long walletId) {
		return walletUserGetByIdService.walletUserGetById(walletId);
	}

	// add valueAccount
	@PutMapping("/add/{walletId}")
	@Transactional
	public ResponseEntity<WalletDto> plusValueWallet(@PathVariable Long walletId,
			@RequestBody @Valid UpdateWalletDto updateWalletDto) throws BusinessException {
		depositValueAccountWalletService.addValueToWallet(walletId, updateWalletDto);
		return ResponseEntity.ok().build();
	}

	// withdraw valueAccount
	@PutMapping("/withdraw/{walletId}")
	@Transactional
	public ResponseEntity<WalletDto> subtractedValueWallet(@PathVariable Long walletId,
			@RequestBody @Valid UpdateWalletDto updateWalletDto) throws BusinessException {
		withdrawValueAccountWalletService.withdrawValueToWallet(walletId, updateWalletDto);
		return ResponseEntity.ok().build();
	}

	// not used yet
	// withdraw valueAccount
	@PutMapping("/transfers/{walletId}/{UserId}")
	@Transactional
	public ResponseEntity<WalletDto> transfersValueWallet(@PathVariable Long walletId,
			@RequestBody @Valid UpdateWalletDto updateWalletDto) throws BusinessException {
		withdrawValueAccountWalletService.withdrawValueToWallet(walletId, updateWalletDto);
		return ResponseEntity.ok().build();
	}

	// almost 
	// created new wallet and new currency
	@PostMapping("/users/{userId}")
	@Transactional
	public ResponseEntity<?> saveOrUpdateUser(@PathVariable Long userId, @RequestBody UserDto userDto, WalletDto walletDto, CurrencyDto currencyDto)
			throws BusinessException {
		walletService.newUserWallet(userDto, userId, walletDto, currencyDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// include cpf validation
	@DeleteMapping("/{walletId}")
	public void remove(@PathVariable Long walletId) {
		walletRepository.deleteById(walletId);
	}
}
