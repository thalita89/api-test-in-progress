package com.test.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.repository.UserRepository;
import com.test.test.validation.IUserValidation;

//user - all validation
@Service
public class ValidationService {
	
	@Autowired //wired, link
	private UserRepository userRepository;

	private List<IUserValidation> validations;

	public ValidationService(List<IUserValidation> validations) 
	{
		this.validations = validations;
	}


	
}
