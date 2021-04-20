package com.test.test.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.repository.UserRepository;
import com.test.test.validation.BusinessException;
import com.test.test.validation.UserValidation;

@Service
public class UserValidationService {
	
	@Autowired //wired, link
	private UserRepository userRepository;

	private List<UserValidation> validations;

	public UserValidationService(List<UserValidation> validations) {
		this.validations = validations;
	}

	//Error
	public ResponseEntity<String> UserValidation(UserDto userDto) throws BusinessException {
		this.validations.forEach(v -> {
			try {
				v.validUser(userDto);
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
		});
		return ResponseEntity.notFound().build();
	}

	private HttpHeaders defaultHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
