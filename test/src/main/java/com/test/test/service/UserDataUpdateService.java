package com.test.test.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.test.test.model.User;
import com.test.test.repository.UserRepository;

//to update - PUT
//to update - @PutMapping("/{id}")
// not used yet
@Service
public class UserDataUpdateService {
	
	private String userName;
	private String emailAddresses;
	private Long cpf;
	private LocalDate dateOfBirth;

	public User userDataUpdate(Long id, UserRepository userRepository) {
		User user = userRepository.getOne(id);
		user.setUserName(this.userName);
		user.setEmailAddresses(this.emailAddresses);
		user.setCpf(this.cpf);
		user.setDateOfBirth(this.dateOfBirth);
		return user;
	}
}
