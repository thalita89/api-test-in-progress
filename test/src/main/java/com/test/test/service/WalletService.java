package com.test.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.controller.dto.WalletDto;
import com.test.test.model.User;
import com.test.test.model.Wallet;
import com.test.test.repository.UserRepository;
import com.test.test.repository.WalletRepository;
import com.test.test.validation.BusinessException;

//@PutMapping("/wallet/{walletId}")
@Service
public class WalletService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private ValidationService validationService;
	
	public UserDto newUserWallet(UserDto userDto, String currencyName, Long userId) throws BusinessException {
		if (userRepository.findById(userId).isPresent()) {
			validationService.validationUser(userDto);
			User user = userDto.update(userId, userRepository);
			Wallet wallet = new WalletDto().convert(user, currencyName);
			walletRepository.save(wallet);

			return new UserDto(user);

		} else {
			validationService.validationUser(userDto);
			User user = userDto.convert();
			userRepository.save(user);
			Wallet wallet = new WalletDto().convert(user, currencyName);
			wallet.setCurrencyName(currencyName);
			walletRepository.save(wallet);
			return new UserDto(user);
		}
	}	
}