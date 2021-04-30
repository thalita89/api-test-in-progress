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
import com.test.test.controller.dto.WalletDto;
import com.test.test.model.User;
import com.test.test.model.Wallet;
import com.test.test.repository.UserRepository;
import com.test.test.repository.WalletRepository;
import com.test.test.service.ValidationService;
import com.test.test.validation.BusinessException;
import com.test.test.validation.IUserValidation;

@RestController
@RequestMapping("/user") // endpoint, when used you can define the HTTP verb post or get
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ValidationService validationService;

	@Autowired
	private WalletRepository walletRepository;

	@GetMapping
	public List<UserDto> listUser(@RequestBody(required = false) User user) {
		return UserDto.convert(userRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto) throws BusinessException {
		validationService.validationUser(userDto);
		User user = userDto.convert();
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> searchUserId(@PathVariable Long id) {
		return userRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	// change latter to @RequestBody
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<User> searchUserCpf(@PathVariable Long cpf) {
		return userRepository.findByCpf(cpf).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto,
			IUserValidation userValidation) throws BusinessException {
		userRepository.findById(id);
		validationService.validationUser(userDto);
		return ResponseEntity.ok(new UserDto(userDto.update(id, userRepository)));
	}

	// refactor later
	@PutMapping("/wallet/{id}")
	@Transactional
	public ResponseEntity<?> saveOrUpdateUser(@PathVariable Long id, @RequestBody UserDto userDto, WalletDto walletDto)
			throws BusinessException {
		Wallet wallet = walletDto.convert();
		walletRepository.save(wallet);

		if (userRepository.findById(id).isPresent()) {
			validationService.validationUser(userDto);
			User user = userDto.update(id, userRepository);
			return ResponseEntity.ok(new UserDto(user));
		} else {
			validationService.validationUser(userDto);
			User user = userDto.convert();
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(user));
		}
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}
