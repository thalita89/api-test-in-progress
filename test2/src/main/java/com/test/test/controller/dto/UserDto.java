package com.test.test.controller.dto;

import java.time.LocalDate;

import com.test.test.model.User;

public class UserDto {

	private Long id;
	private String userName;
	private String emailAddresses;
	private Long cpf;
	private LocalDate dateOfBirth;

	public UserDto() {

	}
	// constructor (used to not need to generate the setters)
	public UserDto(User user) {
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
	
	//from user constructor
	public User convert() {
		return new User(userName, emailAddresses, cpf, dateOfBirth);
	}

}
