package com.test.test.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import com.test.test.controller.dto.UserDto;

public class UserValidationEmailAddresses implements IUserValidation {

	public void validUser(@Valid UserDto userDto) throws BusinessException {
		validEmailAddresses(userDto.getEmailAddresses());
	}

	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public static void validEmailAddresses(String emailAddresses) throws BusinessException {
		final Matcher emailAddressesMatcher = EMAIL_PATTERN.matcher(emailAddresses);
		if (!emailAddressesMatcher.matches()) {
			throw new BusinessException("emailAddresses.is.invalid");
		}
	}

}
