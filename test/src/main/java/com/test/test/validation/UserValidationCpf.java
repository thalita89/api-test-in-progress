package com.test.test.validation;

import com.test.test.controller.dto.UserDto;

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