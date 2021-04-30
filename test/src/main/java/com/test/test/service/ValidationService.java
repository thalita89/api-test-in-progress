package com.test.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.validation.BusinessException;
import com.test.test.validation.IUserValidation;

@Service
public class ValidationService {

	@Autowired
	private final List<IUserValidation> validationsList;

	//constructor receiving list
	public ValidationService(List<IUserValidation> validations) 
	{
		this.validationsList = validations;
	}

	public void validationUser(UserDto userDto) throws BusinessException
	{
		this.validationsList.forEach(v -> v.validatedUser(userDto));
	}
}
			//Open Closed Principle (OCP) - open to extends, but closed to modification
			//user - all validation
			//given validation v 
			

