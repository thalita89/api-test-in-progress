package com.test.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.validation.BusinessException;
import com.test.test.validation.IUserValidation;

@Service
public class ValidationService implements IUserValidation {

	private List<IUserValidation> validations;

	//constructor receiving list
	public ValidationService(List<IUserValidation> validations) 
	{
		this.validations = validations;
	}

	public void validationUser(UserDto userDto) throws BusinessException
	{
		this.validations.forEach(v -> v.validatedUser(userDto));
	}

	@Override
	public void validatedUser(UserDto userDto) {
		
	}
}
			//Open Closed Principle (OCP) - open to extends, but closed to modification
			//user - all validation
			//given validation v
			
