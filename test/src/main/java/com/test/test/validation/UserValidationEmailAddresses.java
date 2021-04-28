package com.test.test.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.test.controller.dto.UserDto;

import org.springframework.stereotype.Component;

@Component
public class UserValidationEmailAddresses implements IUserValidation {

	public void validatedUser(UserDto userDto) 
	{
		validEmailAddresses(userDto.getEmailAddresses());
	}

	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public static boolean validEmailAddresses(String emailAddresses) 
	{
		final Matcher emailAddressesMatcher = EMAIL_PATTERN.matcher(emailAddresses);
		return emailAddressesMatcher.matches();
	}

}