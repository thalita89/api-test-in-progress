package com.test.test.validation;

import java.time.LocalDate;
import java.time.Period;

import com.test.test.controller.dto.UserDto;

import org.springframework.stereotype.Component;

@Component
public class UserValidationDateOfBirth implements IUserValidation {

	public void validatedUser(UserDto userDto)  
	{
		validatedDateOfBirth(userDto.getDateOfBirth());
	}

	// at least 18 years old
	public static boolean validatedDateOfBirth(LocalDate dateOfBirth)  
	{
		LocalDate now = LocalDate.now();
		return Period.between(dateOfBirth, now).getYears() >= 18;
	}

}