package com.test.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.repository.UserRepository;
import com.test.test.validation.BusinessException;

@Service
public class UserUpdateService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ValidationService validationService;

	public UserDto updateUser(Long id, UserDto userDto) throws BusinessException {

		userRepository.findById(id);
		validationService.validationUser(userDto);
		return new UserDto(userDto.update(id, userRepository));
	}
}