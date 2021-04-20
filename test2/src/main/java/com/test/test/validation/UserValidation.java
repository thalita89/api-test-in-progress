package com.test.test.validation;

import javax.validation.Valid;

import org.json.JSONException;

import com.test.test.controller.dto.UserDto;

public interface UserValidation {

	void validUser(@Valid UserDto userDto) throws BusinessException, JSONException;

}
