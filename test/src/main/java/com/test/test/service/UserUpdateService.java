package com.test.test.service;

import java.time.LocalDate;

import com.test.test.model.User;
import com.test.test.repository.UserRepository;

//to update - PUT
//not used yet
public class UserUpdateService {

	private String userName;
	private String emailAddresses;
	private Long cpf;
	private LocalDate dateOfBirth;

	public User update(Long id, UserRepository userRepository) {
		User user = userRepository.getOne(id);
		user.setUserName(this.userName);
		user.setEmailAddresses(this.emailAddresses);
		user.setCpf(this.cpf);
		user.setDateOfBirth(this.dateOfBirth);

		return user;
	}

}
