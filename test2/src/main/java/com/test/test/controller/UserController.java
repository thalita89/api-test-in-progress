package com.test.test.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.test.test.controller.dto.UserGetListDto;
import com.test.test.model.User;
import com.test.test.repository.UserRepository;
import com.test.test.validation.BusinessException;

@RestController
@RequestMapping("/user") //endpoint, when used you can define the HTTP verb post or get
public class UserController {
	
	@Autowired //wired, link
	private UserRepository userRepository;

	@GetMapping
	public UserGetListDto listUser(@RequestBody(required = false) UserGetListDto userGetListDto) { 
		return userGetListDto;
	}

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody @Valid UserDto userDto) throws JSONException, Exception, BusinessException {
		try {userValidation.validUser(userDto);
		
		} catch (BusinessException ex) {
			System.out.println("Catch iniciado");
			final JSONObject body = new JSONObject();
			final JSONArray bodyWrapper = new JSONArray();
			HttpStatus status = HttpStatus.BAD_REQUEST;
			String msg = ex.getMessage();
			body.put("status", status.value());
			body.put("statusText", status.getReasonPhrase());
			body.put("code", String.format("Error:%s", msg));
			bodyWrapper.put(body);
			return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());
		}
		User user = userDto.convert();
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(userRepository.save(user)));
	}
	
	@GetMapping("/{id}") //between braces, part of the url is dynamic, in this case: {id}
	public ResponseEntity<UserDto> searchUserId(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(new UserDto(user.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<UserDto> searchUserCpf(@PathVariable Long cpf) {
		//Refactor later - test for optional without implementing the method: user.isPresent()
		Optional<User> user = userRepository.findByCpf(cpf);
		if (user.isPresent()) {
			return ResponseEntity.ok(new UserDto(user.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto, UserValidation01 userValidation) throws JSONException, BusinessException { // form define somente os tópicos que desejo atualizar
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent()) {
			try {
			userValidation.validUser(userDto);
			} catch (BusinessException ex) {
				System.out.println("Catch iniciado");
				final JSONObject body = new JSONObject();
				final JSONArray bodyWrapper = new JSONArray();
				HttpStatus status = HttpStatus.BAD_REQUEST;
				String msg = ex.getMessage();
				body.put("status", status.value());
				body.put("statusText", status.getReasonPhrase());
				body.put("code", String.format("Error:%s", msg));
				bodyWrapper.put(body);
				return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());
			}
			return ResponseEntity.ok(new UserDto(userDto.update(id, userRepository)));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	private HttpHeaders defaultHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
