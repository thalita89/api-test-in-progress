package com.test.test.controller.dto;

import java.time.LocalDate;

import com.test.test.model.User;
import com.test.test.repository.UserRepository;

public class UserUpdateDto {

	private Long id;
	private String userName;
	private String emailAddresses;
	private Long cpf;
	private LocalDate dateOfBirth;

	public UserUpdateDto() {

	}
	// constructor (used to not need to generate the setters)
	public UserUpdateDto(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.emailAddresses = user.getEmailAddresses();
		this.cpf = user.getCpf();
		this.dateOfBirth = user.getDateOfBirth();
	}

	public Long getId() {
		return id;
	}

	public User update(Long id, UserRepository userRepository) {
		return new User(userName, emailAddresses, cpf, dateOfBirth);
	}

}
