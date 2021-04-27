package com.test.test.validation;

import javax.validation.Valid;

import com.test.test.controller.dto.UserDto;

public class UserValidationCpf implements IUserValidation {

	public void validUser(@Valid UserDto userDto) throws BusinessException {
		validatedCpf(userDto.getCpf());
	}

	//cpf 11 numbers
	public static void validatedCpf(Long cpf) throws BusinessException {
		if (!(cpf != null) || !(cpf.toString().length() == 11)) {
			throw new BusinessException("cpf.is.invalid");
		}
	}
}
