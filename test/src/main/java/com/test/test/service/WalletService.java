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

//@PutMapping("/wallet/{id}")
@Service
public class WalletService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private ValidationService validationService;

	public UserDto newUserWallet(UserDto userDto, Long id, WalletDto walletDto) throws BusinessException {
		if (userRepository.findById(id).isPresent()) {
			validationService.validationUser(userDto);
			User user = userDto.update(id, userRepository);
			Wallet wallet = walletDto.convert(user);
			walletRepository.save(wallet);
			return new UserDto(user);

		} else {
			validationService.validationUser(userDto);
			User user = userDto.convert();
			userRepository.save(user);
			Wallet wallet = walletDto.convert(user);
			walletRepository.save(wallet);
			return new UserDto(user);
		}
	}
}
