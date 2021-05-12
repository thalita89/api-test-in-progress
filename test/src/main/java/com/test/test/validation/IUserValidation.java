package com.test.test.validation;

import com.test.test.controller.dto.UserDto;

public interface IUserValidation {

	//all interface method is public, because this, doesn't need put "public"
	void validatedUser(UserDto userDto);	
}