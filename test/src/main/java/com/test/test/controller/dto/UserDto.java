package com.test.test.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.test.test.model.User;
import com.test.test.repository.UserRepository;

// refactor later
public class UserDto {

	private Long userId;
	private String userName;
	private String emailAddresses;
	private Long cpf;
	private LocalDate dateOfBirth;

	public UserDto() {

	}

	// constructor (used to not need to generate the setters)
	public UserDto(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.emailAddresses = user.getEmailAddresses();
		this.cpf = user.getCpf();
		this.dateOfBirth = user.getDateOfBirth();
	}

	// don't need setters, because this is provided by user class
	public Long getId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmailAddresses() {
		return emailAddresses;
	}

	public Long getCpf() {
		return cpf;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	// from user constructor
	public User convert() {
		return new User(userId, userName, emailAddresses, cpf, dateOfBirth);
	}

	// SERVICE
	// from user constructor
	// Java8
	// map - to convert from UserDto to User
	// UserDto::new - calls the constructor that takes the user as a parameter
	// collect - converted as list
	// static - access without the class having to be instantiated
	public static List<UserDto> convert(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}

	// service
	public User update(Long userId, UserRepository userRepository) {
		User user = userRepository.getOne(userId);
		user.setUserName(this.userName);
		user.setEmailAddresses(this.emailAddresses);
		user.setCpf(this.cpf);
		user.setDateOfBirth(this.dateOfBirth);
		return user;
	}
}
