package com.test.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.test.controller.dto.UserDto;
import com.test.test.repository.UserRepository;
import com.test.test.validation.BusinessException;
import com.test.test.validation.IUserValidation;

//salve user
@org.springframework.stereotype.Service
public class UserSaveService {

	@Autowired
	UserRepository userRepository;

	private List<IUserValidation> validations;
	
	public UserSaveService(List<IUserValidation> validations) {
		this.validations = validations;
	}
	
	public UserDto saveUser(UserDto userDto) {
		//lógica de validar já implementada
		//implementar logica de salvar
		this.validations.forEach(v -> {
			try {
				v.validUser(userDto);
			} catch (BusinessException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		});
		return new UserDto(userRepository.save(userDto.convert()));
	}
}
