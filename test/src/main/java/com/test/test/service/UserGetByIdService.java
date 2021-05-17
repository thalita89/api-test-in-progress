package com.test.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.model.User;
import com.test.test.repository.UserRepository;

@Service
public class UserGetByIdService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<UserDto> userGetById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return ResponseEntity.ok(new UserDto(user.get()));
		}
		return ResponseEntity.notFound().build();
			 
    }
}
