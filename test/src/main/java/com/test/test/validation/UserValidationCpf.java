package com.test.test.validation;

import com.test.test.controller.dto.UserDto;

import org.springframework.stereotype.Component;

@Component
public class UserValidationCpf implements IUserValidation {

	public void validatedUser(UserDto userDto) 
	{
		validatedCpf(userDto.getCpf());
	}

	//cpf 11 numbers
	public static boolean validatedCpf(Long cpf)  
	{
		return cpf != null && cpf.toString().length() == 11;
			
	}
}