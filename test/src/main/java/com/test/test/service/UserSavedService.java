package com.test.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.controller.dto.UserDto;
import com.test.test.model.User;
import com.test.test.repository.UserRepository;
import com.test.test.validation.BusinessException;

//ok
@Service
public class UserSavedService {
	
	@Autowired
	ValidationService validationService;
	
	@Autowired
	UserRepository userRepository;

    public UserDto userSaved(UserDto userDto) throws BusinessException {

        validationService.validationUser(userDto);
		User user = userDto.convert();
		userRepository.save(user);
        return new UserDto(user);
        
    }

}
