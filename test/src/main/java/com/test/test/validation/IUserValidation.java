package com.test.test.validation;

import javax.validation.Valid;

import com.test.test.controller.dto.UserDto;

public interface IUserValidation {

	void validUser(@Valid UserDto userDto) throws BusinessException;

}
