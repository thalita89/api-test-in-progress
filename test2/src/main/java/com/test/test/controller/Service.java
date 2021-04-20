package com.test.test.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.test.repository.UserRepository;
import com.test.test.repository.WalletRepository;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WalletRepository walletRepository;

}
