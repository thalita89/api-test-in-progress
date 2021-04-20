package com.test.test.validation;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.json.JSONException;

import com.test.test.controller.dto.UserDto;

public class UserValidationName implements UserValidation {

	public void validUser(@Valid UserDto userDto) throws JSONException, BusinessException {
		validName(userDto.getUserName());
	}

	private static final int MINIMUM_NAME_SIZE = 2;
	private static final Pattern NAME_PATTERN = Pattern
			.compile("^[a-zA-Z\\u00C0-\\u017F´'][a-zA-Z\\u00C0-\\u017F´'\\s]+$");

	// name Valid
	public static void validName(String userName) throws BusinessException {
		final Matcher nameUserMatcher = NAME_PATTERN.matcher(userName);
		if (!nameUserMatcher.matches() || nameUserShorterThanMinimumSize(userName)) {
			throw new BusinessException("userName.is.invalid");
		}
	}

	private static boolean nameUserShorterThanMinimumSize(String userName) {
		return Arrays.asList(userName.split(" ")).stream().filter(n -> n.length() != 0)
				.anyMatch(n -> (n.length() < MINIMUM_NAME_SIZE));
	}

}
