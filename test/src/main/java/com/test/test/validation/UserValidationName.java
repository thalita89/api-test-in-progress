package com.test.test.validation;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.test.controller.dto.UserDto;

public class UserValidationName implements IUserValidation {

	public void validatedUser(UserDto userDto) 
	{
		validName(userDto.getUserName());
	}

	private static final int MINIMUM_NAME_SIZE = 2;
	private static final Pattern NAME_PATTERN = Pattern
			.compile("^[a-zA-Z\\u00C0-\\u017F´'][a-zA-Z\\u00C0-\\u017F´'\\s]+$");

	public static boolean validName(String userName) 
	{
		final Matcher nameUserMatcher = NAME_PATTERN.matcher(userName);
		return nameUserMatcher.matches() && nameUserShorterThanMinimumSize(userName);
	}

	private static boolean nameUserShorterThanMinimumSize(String userName) 
	{
		return Arrays.asList(userName.split(" ")).stream().filter(n -> n.length() != 0)
				.anyMatch(n -> (n.length() > MINIMUM_NAME_SIZE));
	}

}
