package com.test.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.controller.dto.UserUpdateDto;
import com.test.test.model.Wallet;
import com.test.test.repository.UserRepository;
import com.test.test.repository.WalletRepository;
import com.test.test.validation.BusinessException;

@Service
public class WalletService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private ValidationService validationService;

	public void newWalllet(Long id, UserDto userDto, UserUpdateDto userUpdateDto, Wallet wallet) 
	throws BusinessException
	{
		if (
			userRepository.findById(id) == null)
		{
			validationService.validationUser(userDto);
			walletRepository.save(wallet);
			
		} else {
	
			userRepository.getOne(id);
			userUpdateDto.update(id, userRepository);
			validationService.validationUser(userDto);
			userDto.convert();
			walletRepository.save(wallet);
		}
	}
}

			//findById(id) user 
			//if id is present 
			//update user 
			//convert UserDto in User 
			//created new wallet 
			//else created new wallet 
			
