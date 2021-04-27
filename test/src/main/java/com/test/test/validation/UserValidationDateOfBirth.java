package com.test.test.validation;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.Valid;

import com.test.test.controller.dto.UserDto;

public class UserValidationDateOfBirth implements IUserValidation {

	public void validUser(@Valid UserDto userDto) throws BusinessException {
		validatedDateOfBirth(userDto.getDateOfBirth());
	}

	// at least 18 years old
	public static void validatedDateOfBirth(LocalDate dateOfBirth) throws BusinessException {
		LocalDate now = LocalDate.now();
		if (!(Period.between(dateOfBirth, now).getYears() >= 18)) {
			throw new BusinessException("dateOfBirth.is.invalid");
		}
	}

}
