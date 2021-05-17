package com.test.test.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.controller.dto.UserDto;
import com.test.test.model.User;
import com.test.test.repository.UserRepository;
import com.test.test.service.UserGetByIdService;
import com.test.test.service.UserSavedService;
import com.test.test.service.UserUpdateService;
import com.test.test.validation.BusinessException;

//SOLID? not yet
@RestController
@RequestMapping("/users") // endpoint, when used you can define the HTTP verb post or get
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserSavedService userSavedService;
	@Autowired
	private UserUpdateService userUpdateService;
	@Autowired
	private UserGetByIdService userGetByIdService;

	@GetMapping
	public List<UserDto> listUser(@RequestBody(required = false) User user) {
		return UserDto.convert(userRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto) throws BusinessException {
		userSavedService.userSaved(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	//check
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> searchUserId(@PathVariable Long userId) {
		return userGetByIdService.userGetById(userId);
	}

	// change to @RequestBody later
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<User> searchUserCpf(@PathVariable Long cpf) {
		return userRepository.findByCpf(cpf).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{userId}")
	@Transactional
	public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto)
			throws BusinessException {

		userUpdateService.updateUser(userId, userDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{userId}")
	public void remove(@PathVariable Long userId) {
		userRepository.deleteById(userId);
	}
}
