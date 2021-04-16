package com.test.test.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.test.controller.dto.UserDto;
import com.test.test.repository.UserRepository;

public class UserValidation {

	@Autowired
	UserRepository userRepository;
	
	private static final int MINIMUM_NAME_SIZE = 2;
	private static final Pattern NAME_PATTERN = Pattern
			.compile("^[a-zA-Z\\u00C0-\\u017F´'][a-zA-Z\\u00C0-\\u017F´'\\s]+$");

	private static final Pattern EMAIL_PATTERN = Pattern
	.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public void validUser(@Valid UserDto userDto) throws JSONException, BusinessException {
		validName(userDto.getUserName());
		validatedCpf(userDto.getCpf());
		validatedDateOfBirth(userDto.getDateOfBirth());
		validEmailAddresses(userDto.getEmailAddresses());
	}

	//name Valid 
	public static void validName(String userName) throws BusinessException {
		final Matcher nameUserMatcher = NAME_PATTERN.matcher(userName);
		if (!nameUserMatcher.matches() || nameUserShorterThanMinimumSize(userName)) {
			throw new BusinessException("userName.is.invalid");
		}
	}

	//cpf 11 numbers
	public static void validatedCpf(Long cpf) throws BusinessException {
		if (!(cpf != null) || !(cpf.toString().length() == 11)) {
			throw new BusinessException("cpf.is.invalid");
		}
	}
	
	//at least 18 years old
	public static void validatedDateOfBirth(LocalDate dateOfBirth) throws BusinessException {
		LocalDate now = LocalDate.now();
		if (!(Period.between(dateOfBirth, now).getYears() >= 18)) {
			throw new BusinessException("dateOfBirth.is.invalid");
		}	
	}

	public static void validEmailAddresses(String emailAddresses) throws BusinessException {
		final Matcher emailAddressesMatcher = EMAIL_PATTERN.matcher(emailAddresses);
		if (!emailAddressesMatcher.matches()) {
				throw new BusinessException("emailAddresses.is.invalid");
		}
	}

	private static boolean nameUserShorterThanMinimumSize(String userName) {
		return 
		Arrays.asList(userName.split(" "))
        .stream()
        .filter(n -> n.length() != 0)
        .anyMatch(n -> (n.length() < MINIMUM_NAME_SIZE));
	  }
}
