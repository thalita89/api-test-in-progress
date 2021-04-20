package com.test.test.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.test.test.model.User;

public class UserGetListDto {

	private Long id;
	private String userName;
	private String emailAddresses;
	private Long cpf;
	private LocalDate dateOfBirth;

	public UserGetListDto() {

	}

	// constructor (used to not need to generate the setters)
	public UserGetListDto(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.emailAddresses = user.getEmailAddresses();
		this.cpf = user.getCpf();
		this.dateOfBirth = user.getDateOfBirth();
	}

	// don't need setters, because this is provided by user class

	public Long getId() {
		return id;
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

	// Java8
	// map - to convert from UserDto to User
	// UserDto::new - calls the constructor that takes the user as a parameter
	// collect - converted as list
	// static - access without the class having to be instantiated
	public static List<UserDto> convert(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}

}
