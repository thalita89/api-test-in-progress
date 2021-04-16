package com.test.test.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.test.controller.dto.UserDto;
import com.test.test.controller.dto.WalletDto;
import com.test.test.model.Wallet;
import com.test.test.repository.UserRepository;
import com.test.test.repository.WalletRepository;
import com.test.test.validation.BusinessException;
import com.test.test.validation.UserValidation;
import com.test.test.validation.WalletValidation;

@RestController
@RequestMapping("/wallet") 
public class WalletController {
	
	@Autowired //wired, link
	private WalletRepository walletRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<WalletDto> listWallet(@RequestBody(required = false) Wallet wallet) { 
		return WalletDto.convert(walletRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<?> saveWallet(@RequestBody @Valid WalletDto walletDto, UserDto userDto, WalletValidation walletValidation, UserValidation userValidation) throws JSONException, Exception, BusinessException {
//		try {
//			walletValidation.validWallet(walletDto);
//		} catch (BusinessException ex) {
//			System.out.println("Catch iniciado");
//			final JSONObject body = new JSONObject();
//			final JSONArray bodyWrapper = new JSONArray();
//			HttpStatus status = HttpStatus.BAD_REQUEST;
//			String msg = ex.getMessage();
//			body.put("status", status.value());
//			body.put("statusText", status.getReasonPhrase());
//			body.put("code", String.format("Error:%s", msg));
//			bodyWrapper.put(body);
//			return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());
//		}
		
//		try {
//			userValidation.validUser(userDto);
//
//		} catch (BusinessException ex) {
//			System.out.println("Catch iniciado");
//			final JSONObject body = new JSONObject();
//			final JSONArray bodyWrapper = new JSONArray();
//			HttpStatus status = HttpStatus.BAD_REQUEST;
//			String msg = ex.getMessage();
//			body.put("status", status.value());
//			body.put("statusText", status.getReasonPhrase());
//			body.put("code", String.format("Error:%s", msg));
//			bodyWrapper.put(body);
//			return ResponseEntity.status(status).headers(defaultHeaders()).body(bodyWrapper.toString());
//		}
		//quero salvar a wallet e atribuir a um usuario específico
		Wallet wallet = walletDto.convert(userRepository);
		return ResponseEntity.status(HttpStatus.CREATED).body(new WalletDto(walletRepository.save(wallet)));
	}
	
	@GetMapping("/{id}") //between braces, part of the url is dynamic, in this case: {id}
	public ResponseEntity<WalletDto> searchWalletId(@PathVariable Long id) {
		Optional<Wallet> Wallet = walletRepository.findById(id);
		if (Wallet.isPresent()) {
			return ResponseEntity.ok(new WalletDto(Wallet.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Wallet> wallet = walletRepository.findById(id);
		if (wallet.isPresent()) {
			walletRepository.deleteById(id);
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
